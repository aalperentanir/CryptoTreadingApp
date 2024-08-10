import api, { BASE_URL } from "@/config/api";
import { DEPOSIT_MONEY_FAILURE, DEPOSIT_MONEY_REQUEST, DEPOSIT_MONEY_SUCCESS, GET_USER_WALLET_FAILURE, GET_USER_WALLET_REQUEST, GET_USER_WALLET_SUCCESS, TRANSFER_MONEY_FAILURE, TRANSFER_MONEY_REQUEST, TRANSFER_MONEY_SUCCESS } from "./ActionType";




export const getUserWallet =(jwt)=> async(dispatch)=>{
    dispatch({type:GET_USER_WALLET_REQUEST});

    try {
        const response = await api.get(`${BASE_URL}/api/wallet`,{
            headers:{
                Authorization: `Bearer ${jwt}`,
            }
        });

        dispatch({type:GET_USER_WALLET_SUCCESS, payload:response.data})
        console.log("userWallet",response.data)
    } catch (error) {
        console.log(error);
        dispatch({type:GET_USER_WALLET_FAILURE, error:error.message})
    }
}

export const depositMoney =({jwt, orderId, paymentId, navigate})=> async(dispatch)=>{
    dispatch({type:DEPOSIT_MONEY_REQUEST});

    console.log("---------------------",orderId,paymentId)

    try {
        const response = await api.put(`${BASE_URL}/api/wallet/deposit`,null,{

            
            params:{
                order_id : orderId,
                payment_id : paymentId
            },
            headers:{
                Authorization: `Bearer ${jwt}`,
            }
        });

        dispatch({type:DEPOSIT_MONEY_SUCCESS, payload:response.data})
        navigate("/wallet")
        console.log("depositeMoney",response.data)
    } catch (error) {
        console.log("error",error);
        dispatch({type:DEPOSIT_MONEY_FAILURE, error:error.message})
        navigate("/wallet")
    }
}

export const paymentHandler = ({ jwt, amount, paymentMethod }) => async (dispatch) => {
    dispatch({ type: DEPOSIT_MONEY_REQUEST });

    try {
        const response = await api.post(`${BASE_URL}/api/payment/${paymentMethod}/amount/${amount}`, null, {
            headers: {
                Authorization: `Bearer ${jwt}`,
            },
        });

        console.log("paymentHandler", response.data);

            window.location.href = response.data.paymentUrl;
    } catch (error) {
        console.error("Error in paymentHandler:", error);
        dispatch({ type: DEPOSIT_MONEY_FAILURE, error: error.message });
    }
};


export const transferMoney =({jwt, walletId, reqData})=> async(dispatch)=>{
    dispatch({type:TRANSFER_MONEY_REQUEST});

    try {
        const response = await api.put(`${BASE_URL}/api/wallet/${walletId}/transfer`,reqData,{
            headers:{
                Authorization: `Bearer ${jwt}`,
            }
        });


        dispatch({type:TRANSFER_MONEY_SUCCESS, payload:response.data})
        console.log("transferMoney",response.data)
    } catch (error) {
        console.log(error);
        dispatch({type:TRANSFER_MONEY_FAILURE, error:error.message})
    }
}