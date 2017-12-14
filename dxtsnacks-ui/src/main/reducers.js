import { combineReducers } from 'redux'
import { reducer as formReducer } from 'redux-form'
import { reducer as toastrReducer } from 'react-redux-toastr'

import DashboardReducer from '../dashboard/dashboardReducer'
import AuthReducer from '../auth/authReducer'
import UsersReducer from '../components/users/usersReducer'

const rootReducer = combineReducers({
    dashboard: DashboardReducer,
    toastr: toastrReducer,
    auth: AuthReducer,
    users:UsersReducer
})

export default rootReducer