import axios from 'axios'
import Constant from '../../main/constant'

export function findAll(){
    return (dispatch) => {
        axios.get(Constant.API_URL+"/api/user")
          .then(response =>{
            dispatch({
                type: Constant.LIST_USERS,
                payload: response.data
            })
          })
          .catch(e => {
            console.log(e);
          })
    }
}