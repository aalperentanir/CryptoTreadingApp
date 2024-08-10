import api, { BASE_URL } from "@/config/api"
import { GET_ASSET_DETAILS_FAILURE, GET_ASSET_DETAILS_REQUEST, GET_ASSET_DETAILS_SUCCESS, GET_ASSET_FAILURE, GET_ASSET_REQUEST, GET_ASSET_SUCCESS, GET_USER_ASSETS_FAILURE, GET_USER_ASSETS_REQUEST, GET_USER_ASSETS_SUCCESS } from "./ActionType"


export const getAssetById = (assetId) => async (dispatch) => {
    dispatch({type:GET_ASSET_REQUEST})

    try {
        const response = await api.get(`${BASE_URL}/api/asset/${assetId}`, {
        })
        dispatch({type:GET_ASSET_SUCCESS, payload:response.data})
        console.log("getAsset",response.data)
    } catch (error) {
        dispatch({type:GET_ASSET_FAILURE, error:error.message})
        console.log("error",error.message)
        
    }
}


export const getAssetDetails = ({jwt, coinId}) => async (dispatch) => {
    dispatch({type:GET_ASSET_DETAILS_REQUEST})

    try {
        const response = await api.get(`${BASE_URL}/api/asset/coin/${coinId}/user`, {
            headers:{
                Authorization: `Bearer ${jwt}`
            }
        })
        dispatch({type:GET_ASSET_DETAILS_SUCCESS, payload:response.data})
        console.log("getAssetDetails",response.data)
    } catch (error) {
        dispatch({type:GET_ASSET_DETAILS_FAILURE, error:error.message})
        console.log("error",error.message)
        
    }
}



export const getUserAsset = (jwt) => async (dispatch) => {
    dispatch({type:GET_USER_ASSETS_REQUEST})

    try {
        const response = await api.get(`${BASE_URL}/api/asset`, {
            headers:{
                Authorization: `Bearer ${jwt}`
            }
        })
        dispatch({type:GET_USER_ASSETS_SUCCESS, payload:response.data})
        console.log("getUserAsset",response.data)
    } catch (error) {
        dispatch({type:GET_USER_ASSETS_FAILURE, error:error.message})
        console.log("error",error.message)
        
    }
}