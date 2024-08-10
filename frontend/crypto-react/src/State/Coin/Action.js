import {
    FETCH_COIN_BY_ID_FAILURE,
    FETCH_COIN_BY_ID_REQUEST,
  FETCH_COIN_BY_ID_SUCCESS,
  FETCH_COIN_DETAILS_FAILURE,
  FETCH_COIN_DETAILS_REQUEST,
  FETCH_COIN_DETAILS_SUCCESS,
  FETCH_COIN_LIST_FAILURE,
  FETCH_COIN_LIST_REQUEST,
  FETCH_COIN_LIST_SUCCESS,
  FETCH_MARKET_CHART_FAILURE,
  FETCH_MARKET_CHART_REQUEST,
  FETCH_MARKET_CHART_SUCCESS,
  FETCH_TOP_50_COINS_REQUEST,
  FETCH_TOP_50_COINS_SUCCESS,
  SEARCH_COIN_FAILURE,
  SEARCH_COIN_REQUEST,
  SEARCH_COIN_SUCCESS,
} from "./ActionType";
import axios from "axios";

export const getCoinList = (page) => async (dispatch) => {
  dispatch({ type: FETCH_COIN_LIST_REQUEST });

  const base_url ="http://localhost:8081"

  
  try {
    const {data} = await axios.get(`${base_url}/coins?page=${page}`);

    console.log("coinList : ", data);
    dispatch({ type: FETCH_COIN_LIST_SUCCESS, payload: data });
  } catch (error) {
    console.log(error);
    dispatch({ type: FETCH_COIN_LIST_FAILURE, payload: error.message });
  }
};

export const getTop50CoinList = () => async (dispatch) => {
  dispatch({ type: FETCH_TOP_50_COINS_REQUEST });

  const base_url ="http://localhost:8081"


  try {
    const response = await axios.get(`${base_url}/coins/top50`);

    console.log("top50CoinList : ", response.data);
    dispatch({ type: FETCH_TOP_50_COINS_SUCCESS, payload: response.data });
  } catch (error) {
    console.log(error);
    dispatch({ type: FETCH_COIN_LIST_FAILURE, payload: error.message });
  }
};

export const fetchMarketChart = ({ coinId, days, jwt }) => async (dispatch) => {
    dispatch({ type: FETCH_MARKET_CHART_REQUEST });

    const base_url ="http://localhost:8081"

    try {
      const response = await axios.get(
        `${base_url}/coins/${coinId}/chart?days=${days}`,
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );

      console.log("marketChart: ", response.data)
      dispatch({ type: FETCH_MARKET_CHART_SUCCESS, payload: response.data });

    } catch (error) {
      console.log(error);
      dispatch({ type: FETCH_MARKET_CHART_FAILURE, payload: error.message });
    }
  };


  export const fetchCoinById = (coinId) => async (dispatch) => {
    dispatch({ type: FETCH_COIN_BY_ID_REQUEST });

    const base_url ="http://localhost:8081"

  
  
    try {
      const response = await axios.get(`${base_url}/coins/${coinId}`);
  
      console.log("coinById : ", response.data);
      dispatch({ type: FETCH_COIN_BY_ID_SUCCESS, payload: response.data });
    } catch (error) {
      console.log(error);
      dispatch({ type: FETCH_COIN_BY_ID_FAILURE, payload: error.message });
    }
  };

  export const fetchCoinDetails = ({coinId,jwt}) => async (dispatch) => {
    dispatch({ type: FETCH_COIN_DETAILS_REQUEST });

    const base_url ="http://localhost:8081"

  
  
    try {
      const response = await axios.get(`${base_url}/coins/details/${coinId}`,
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        });
  
      
      dispatch({ type: FETCH_COIN_DETAILS_SUCCESS, payload: response.data });
      console.log("coinDetails : ", response.data);
    } catch (error) {
      console.log(error);
      dispatch({ type: FETCH_COIN_DETAILS_FAILURE, payload: error.message });
    }
  };


  export const searchCoin = (keyword) => async (dispatch) => {
    dispatch({ type: SEARCH_COIN_REQUEST });

    const base_url ="http://localhost:8081"

  
    try {
      const response = await axios.get(`${base_url}/coins/search?q=${keyword}`);
  
      
      dispatch({ type: SEARCH_COIN_SUCCESS, payload: response.data });
      console.log("searchCoin : ", response.data)
    } catch (error) {
      console.log(error);
      dispatch({ type: SEARCH_COIN_FAILURE, payload: error.message });
    }
  };