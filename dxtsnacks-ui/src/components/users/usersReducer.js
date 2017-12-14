import Constant from '../../main/constant'

const INITIAL_STATE = { users: null }

export default (state = INITIAL_STATE, action) => {
    switch (action.type) {
        
        case Constant.LIST_USERS:
                return { ...state, users: action.payload }
            
        default:
            return state
    }

  

}