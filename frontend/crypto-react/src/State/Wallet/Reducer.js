import { GET_USER_WALLET_REQUEST, DEPOSIT_MONEY_REQUEST, TRANSFER_MONEY_REQUEST, GET_WALLET_TRANSACTION_SUCCESS, GET_USER_WALLET_SUCCESS, TRANSFER_MONEY_SUCCESS, DEPOSIT_MONEY_SUCCESS, GET_USER_WALLET_FAILURE, DEPOSIT_MONEY_FAILURE, TRANSFER_MONEY_FAILURE } from "./ActionType";


const initialState = {
    userWallet:{},
    loading:false,
    error:null,
}


const walletReducer = (state= initialState, action) => {

    
switch (action.type) {
    case GET_USER_WALLET_REQUEST:
    
    case DEPOSIT_MONEY_REQUEST:
    case TRANSFER_MONEY_REQUEST:
        return {
            ...state,
            loading:true,
            error:null
        }
    
    
    case GET_USER_WALLET_SUCCESS:
    case TRANSFER_MONEY_SUCCESS:
        return{
            ...state,
            userWallet:action.payload,
            loading:false,
            error:null,
        }
    
    case DEPOSIT_MONEY_SUCCESS:
        return {
            ...state,
            userWallet:action.payload,
            loading:false,
            error:null
        }
    
    case GET_USER_WALLET_FAILURE:
    case DEPOSIT_MONEY_FAILURE:
    case TRANSFER_MONEY_FAILURE:
        return{
            ...state,
            loading:false,
            error:null
        }

    default:
        return state;
}
};

export default walletReducer;