import './auth.css'

import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'


import { login } from './authAction'

class Auth extends Component {

    sendForm(event){

        var user = {
            username: this.username.value,
            password: this.password.value
        }
        
        if(user.username == "" || user.password == ""){
           alert("Preencha os campos para acessar o sistema")
        }else{
            this.props.login(user);
        }
        
    }

    render() {
        return (
            <div className="login-box">
                <div className="login-logo"><b> DXT</b> Snacks</div>
                <div className="login-box-body">
                    <p className="login-box-msg">Bem vindo!</p>
                    <form className="form-vertical login-form" >
                        <div className="form-group">
                            <div className="inner-addon left-addon">
                                <input type="email" className="form-control" placeholder="Informe seu email"
                                    required ref={(input) => this.username = input}  />
                            </div>
                        </div>

                        <div className="form-group">
                            <div className="inner-addon left-addon">
                                <input type="password" className="form-control" placeholder="Informe sua senha"
                                    required ref={(input) => this.password = input}  />
                            </div>
                        </div>

                        <div className="form-group btnLogin">
                            <button className="btn btn-success" type="button"
                                data-loading-text="<i class='fa fa-circle-o-notch fa-spin'></i> Processing Order" onClick={(event) => this.sendForm(event)}><span className="fa fa-sign-in "></span> Entrar</button>
                        </div>
                    </form>
                </div>
            </div>

        )
    }
}

const mapStateToProps = state => ({ auth: state.auth })
const mapDispatchToProps = dispatch => bindActionCreators({ login },
    dispatch)
export default connect(mapStateToProps, mapDispatchToProps)(Auth)