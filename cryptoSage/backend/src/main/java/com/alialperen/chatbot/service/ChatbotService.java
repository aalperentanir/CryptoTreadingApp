package com.alialperen.chatbot.service;

import com.alialperen.chatbot.response.ApiResponse;
import com.alialperen.chatbot.response.FunctionResponse;

public interface ChatbotService {

    ApiResponse getCoinDetails(String prompt) throws Exception;

    String simpleChat(String prompt);

    FunctionResponse getFunctionResponse(String prompt);


}
