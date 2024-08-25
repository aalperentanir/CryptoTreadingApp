package com.alialperen.chatbot.service;

import com.alialperen.chatbot.entity.CoinDto;
import com.alialperen.chatbot.response.ApiResponse;
import com.alialperen.chatbot.response.FunctionResponse;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ChatbotServiceImp implements ChatbotService{

   String GEMINI_API_KEY = "AIzaSyDu9jAmx1LQxD8hCZDIn4W673yIMlFYRIc";

    private double convertToDouble(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).doubleValue();
        } else if (obj instanceof Long) {
            return ((Long) obj).doubleValue();
        } else if (obj instanceof Double) {
            return (Double) obj;
        } else {
            throw new IllegalArgumentException("Unsupported type: " + obj.getClass().getName());
        }
    }


    //En aşağıda bir fazla ) var
    @Override
    public FunctionResponse getFunctionResponse(String prompt){

        String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + GEMINI_API_KEY;

        JSONObject requestBodyJSON = new JSONObject()
                .put("contents",new JSONArray()
                        .put(new JSONObject()
                                .put("parts", new JSONArray()
                                        .put(new JSONObject()
                                                .put("text",prompt)
                                        )
                                )
                        )
                )
                .put("tools",new JSONArray()
                        .put(new JSONObject()
                                .put("functionDeclarations", new JSONArray()
                                        .put(new JSONObject()
                                                .put("name","getCoinDetails")
                                                .put("description","Get coin details")
                                                .put("parameters",new JSONObject()
                                                        .put("type","OBJECT")
                                                        .put("properties",new JSONObject()
                                                                .put("currencyName", new JSONObject()
                                                                        .put("type","STRING")
                                                                        .put("description",
                                                                                "The currency name, " +
                                                                                        "id, symbol.")
                                                                )
                                                                        .put("currencyData",new JSONObject()
                                                                                .put("type","STRING")
                                                                                .put("description",
                                                                                        "Currency data id"+
                                                                                        "symbol, " +
                                                                                        "name, "+
                                                                                        "image, "+
                                                                                        "current_price, "+
                                                                                        "market_cap, "+
                                                                                        "market_cap_rank, "+
                                                                                        "fully_diluted_valutaion, "+
                                                                                        "total_volume, high_24h, "+
                                                                                        "low_24h, price_change_24h, "+
                                                                                        "price_change_percentage_24h, "+
                                                                                        "market_cap_change_24h, "+
                                                                                        "market_cap_change_percentage_24h, "+
                                                                                        "circulating_supply, "+
                                                                                        "total_supply, "+
                                                                                        "max_supply, "+
                                                                                        "ath, "+
                                                                                        "ath_change_percentage, "+
                                                                                        "ath_date, "+
                                                                                        "atl, "+
                                                                                        "atl_change_percentage, "+
                                                                                        "atl_date, last_updated.")
                                                                        )
                                                                )
                                                                .put("required",new JSONArray()
                                                                        .put("currencyName")
                                                                        .put("currencyData")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        );


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBodyJSON.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(GEMINI_API_URL, requestEntity, String.class);

        String responseBody = response.getBody();

        JSONObject jsonObject = new JSONObject(responseBody);

        JSONArray candidates = jsonObject.getJSONArray("candidates");
        JSONObject firstCandidate = candidates.getJSONObject(0);

        JSONObject content = firstCandidate.getJSONObject("content");
        JSONArray parts = content.getJSONArray("parts");
        JSONObject firstPart = parts.getJSONObject(0);
        JSONObject functionCall = firstPart.getJSONObject("functionCall");

        String functionName = functionCall.getString("name");
        JSONObject args = functionCall.getJSONObject("args");
        String currencyName = args.getString("currencyName");
        String currencyData = args.getString("currencyData");

        System.out.println("Funciton name: " + functionName);
        System.out.println("Currency name: " + currencyName);
        System.out.println("Currency data: " + currencyData);


        FunctionResponse res = new FunctionResponse();
        res.setFunctionName(functionName);
        res.setCurrencyName(currencyName);
        res.setCurrencyData(currencyData);


        return res;
    }

    public CoinDto getCoinDetailsAPI(String currencyName) throws Exception {
        String url = "https://api.coingecko.com/api/v3/coins/"+currencyName;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url,Map.class);
        Map<String,Object> responseBody = responseEntity.getBody();

        if (responseEntity != null){
            Map<String,Object> image = (Map<String, Object>) responseBody.get("image");
            Map<String,Object> marketData = (Map<String, Object>) responseBody.get("market_data");

            CoinDto coinDto = new CoinDto();
            coinDto.setId((String)responseBody.get("id"));
            coinDto.setName((String)responseBody.get("name"));
            coinDto.setSymbol((String)responseBody.get("symbol"));
            coinDto.setImage((String)image.get("large"));

           coinDto.setCurrentPrice(convertToDouble(((Map<String,Object>)marketData.get("current_price")).get("usd")));
           coinDto.setMarketCap(convertToDouble(((Map<String,Object>)marketData.get("market_cap")).get("usd")));
           coinDto.setMarketCapRank(convertToDouble(((int)marketData.get("market_cap_rank"))));
           coinDto.setTotalVolume(convertToDouble(((Map<String,Object>)marketData.get("total_volume")).get("usd")));
           coinDto.setHigh24h(convertToDouble(((Map<String,Object>)marketData.get("high_24h")).get("usd")));
           coinDto.setLow24h(convertToDouble(((Map<String,Object>)marketData.get("high_24h")).get("usd")));

           coinDto.setPriceChange24h(convertToDouble((marketData.get("price_change_24h"))));
           coinDto.setPriceChangePercentage24h(convertToDouble((marketData.get("price_change_percentage_24h"))));
           coinDto.setMarketCapChange24h(convertToDouble((marketData.get("market_cap_change_24h"))));
           coinDto.setMarketCapChangePercentage24h(convertToDouble((marketData.get("market_cap_change_percentage_24h"))));
           coinDto.setTotalSupply(convertToDouble((marketData.get("total_supply"))));
           coinDto.setCirculatingSupply(convertToDouble((marketData.get("circulating_supply"))));




            return coinDto;

        }
        throw new Exception("Coin not found");
    }

    @Override
    public ApiResponse getCoinDetails(String prompt) throws Exception {
        FunctionResponse functionResponse = getFunctionResponse(prompt);
        CoinDto coinDto = getCoinDetailsAPI(functionResponse.getCurrencyName());


        String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + GEMINI_API_KEY;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body  = new JSONObject()
                .put("contents",new JSONArray()
                        .put(new JSONObject()
                                .put("role","user")
                                .put("parts",new JSONArray()
                                        .put(new JSONObject()
                                                .put("text",prompt)
                                        )
                                )
                        )
                        .put(new JSONObject()
                                .put("role","model")
                                .put("parts",new JSONArray()
                                        .put(new JSONObject()
                                                .put("functionCall",new JSONObject()
                                                        .put("name","getCoinDetails")
                                                        .put("args",new JSONObject()
                                                                .put("currencyName",functionResponse.getCurrencyName())
                                                                .put("currencyData",functionResponse.getCurrencyData())
                                                        )
                                                )
                                        )
                                )
                        )
                        .put(new JSONObject()
                                .put("role","function")
                                .put("parts",new JSONArray()
                                        .put(new JSONObject()
                                                .put("functionResponse",new JSONObject()
                                                        .put("name","getCoinDetails")
                                                        .put("response",new JSONObject()
                                                                .put("name","getCoinDetails")
                                                                .put("content",coinDto)
                                                        )
                                                )
                                        )
                                )
                        )
                )
                .put("tools",new JSONArray()
                        .put(new JSONObject()
                                .put("functionDeclarations",new JSONArray()
                                        .put(new JSONObject()
                                                .put("name","getCoinDetails")
                                                .put("description","Get crypto currency data from given currency object")
                                                .put("parameters",new JSONObject()
                                                        .put("type","OBJECT")
                                                        .put("properties",new JSONObject()
                                                                .put("currencyName",new JSONObject()
                                                                        .put("type","STRING")
                                                                        .put("description","The currency Name, "+
                                                                                "id, " +
                                                                                "symbol.")
                                                                )
                                                                .put("currencyData",new JSONObject()
                                                                        .put("type","STRING")
                                                                        .put("description",
                                                                                "The currency data id, " +
                                                                                "symbol, currenct price, "+
                                                                                "image, " +
                                                                                "market cap extra...")
                                                                )
                                                        )
                                                        .put("required",new JSONArray()
                                                                .put("currencyName")
                                                                .put("currencyData")
                                                        )
                                                )
                                        )
                                )
                        )
                ).toString();


        HttpEntity<String> request = new HttpEntity<>(body,headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(GEMINI_API_URL,request,String.class);

        String responseBody = response.getBody();

        System.out.println("Response body: " + responseBody);


        JSONObject jsonObject = new JSONObject(responseBody);

        JSONArray candidates = jsonObject.getJSONArray("candidates");
        JSONObject firstCandidate = candidates.getJSONObject(0);

        JSONObject content = firstCandidate.getJSONObject("content");
        JSONArray parts = content.getJSONArray("parts");
        JSONObject firstPart = parts.getJSONObject(0);
        String text = firstPart.getString("text");

        ApiResponse res = new ApiResponse();
        res.setMessage(text);

        return res;
    }

    @Override
    public String simpleChat(String prompt) {
        //'Content-Type: application/json' \
        //     -d '{"contents":[
        //            {"role": "user",
        //              "parts":[{"text": "Give me five subcategories of jazz?"}]}]}' \
        //     "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=${API_KEY}"

        String GEMINI_API_KEY_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=" + GEMINI_API_KEY;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = new JSONObject()
                .put("contents",new JSONArray()
                        .put(new JSONObject()
                                .put("parts", new JSONArray()
                                   .put(new JSONObject()
                                            .put("text",prompt))))
        ).toString();

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody,headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(GEMINI_API_KEY_URL, requestEntity, String.class);


        return response.getBody();
    }
}
