import React from 'react'
import { Router, Route, IndexRoute, Redirect, hashHistory } from 'react-router'

import App from './app'
import Dashboard from '../dashboard/dashboard'
import AuthOrApp from './authOrApp'

import Users from '../components/users/users'
export default props => (
    <Router history={hashHistory}>
        <Route path='/' component={AuthOrApp}>
            <IndexRoute component={Dashboard} />
            <Route path="/users" component={Users}/>
        </Route>
        <Redirect from='*' to='/' />
    </Router>
)