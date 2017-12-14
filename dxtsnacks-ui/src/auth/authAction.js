import axios from 'axios'
import {toastr as msg} from 'react-redux-toastr'
import consts from '../main/constant'

export const login = (user) => {
    return (dispatch) => {
        const body = `username=${user.username}&password=${user.password}&grant_type=password`;
                
        axios.defaults.headers.common['Authorization'] = 'Basic ZHh0c25hY2s6OXg3NW40aw==';
        axios.defaults.headers.common['Content-Type'] = 'application/x-www-form-urlencoded';
        
        axios.post(`${consts.API_URL}/oauth/token`, body)
            .then(response => {
                if (response.status == 200) {
                   
                        dispatch({
                            type: 'USER_FETCHED',
                            payload: response.data
                        })
                    
                } else {
                    alert("Email-Senha incorretos")
                }
            }).catch(e => {
                alert("Email-Senha incorretos")
            })
    }
}

export function logout() {

    return (dispatch) => {
        const requestInfo = {
            method: 'DELETE',
            headers: new Headers({
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': `Bearer ${JSON.parse(localStorage.getItem("userSession")).access_token}`
            })
        };

        fetch(`${consts.API_URL}/tokens/revoke`, requestInfo)
            .then(response => { 
                dispatch({
                    type: 'TOKEN_VALIDATED',
                    payload: false
                })
            })
    }

}

export function validateToken(payload) {
    return dispatch => {
        if (payload) {
            axios.defaults.headers.common['Authorization'] = `Bearer ${JSON.parse(payload).access_token}`;
            axios.get(`${consts.API_URL}/tokens/validToken`)
                .then(resp => {
                    if (resp.status < 400) {
                        dispatch({ type: 'TOKEN_VALIDATED', payload: payload })
                    } else {
                        dispatch({ type: 'TOKEN_VALIDATED', payload: false })
                    }
                })
                .catch(e => dispatch({ type: 'TOKEN_VALIDATED', payload: false }))
        } else {
            dispatch({ type: 'TOKEN_VALIDATED', payload: false })
        }
    }
}