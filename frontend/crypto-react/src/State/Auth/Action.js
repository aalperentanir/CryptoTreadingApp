import axios from "axios"
import { GET_USER_FAILURE, GET_USER_REEQUEST, GET_USER_SUCCESS, LOGIN_FAILURE, LOGIN_REEQUEST, LOGIN_SUCCESS, LOGOUT, REGISTER_FAILURE, REGISTER_REQUEST, REGISTER_SUCCESS } from "./ActionType";

export const register = (userData) => async(dispatch)=>{

    dispatch({type:REGISTER_REQUEST})
    
    const base_url ="http://localhost:8081/auth"
    try {
        const response = await axios.post(`${base_url}/signup`,userData);
        const user = response.data;
        
        localStorage.setItem("jwt",user.jwt)
        console.log("registeredUser : ",user);
        dispatch({type:REGISTER_SUCCESS, payload:user.jwt})
        

    } catch (error) {
        console.log(error);
        dispatch({type:REGISTER_FAILURE, payload:error.message})
    }
}

export const login = (userData) => async(dispatch)=>{

    dispatch({type:LOGIN_REEQUEST})
    
    const base_url ="http://localhost:8081/auth"
    try {
        const response = await axios.post(`${base_url}/signin`,userData.data);
        const user = response.data;
        console.log("Signin user: ",user);

        dispatch({type:LOGIN_SUCCESS, payload:user.jwt})
        localStorage.setItem("jwt",user.jwt)
        userData.navigate("/")
        

    } catch (error) {
        console.log(error);
        dispatch({type:LOGIN_FAILURE, payload:error.message})
    }
}

export const getUser = (jwt) => async(dispatch)=>{

    dispatch({type:GET_USER_REEQUEST})
    
    const base_url ="http://localhost:8081/api/users"
    try {
        const response = await axios.get(`${base_url}/profile`,{
            headers:{
                Authorization:`Bearer ${jwt}`
            }
        });
        const user = response.data;
        console.log(user);
        dispatch({type:GET_USER_SUCCESS, payload:user})

    } catch (error) {
        console.log(error);
        dispatch({type:GET_USER_FAILURE, payload:error.message})
    }
}

export const logout = () => (dispatch) =>{
    localStorage.clear();
    dispatch({type:LOGOUT})
}