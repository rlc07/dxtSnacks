import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import ContentHeader from '../../common/template/contentHeader'
import Content from '../../common/template/content'
import TabHeader from '../../common/tab/tabHeader'
import TabContent from '../../common/tab/tabContent'

import {findAll} from './usersAction'

class Users extends Component {

    componentWillMount(){
        this.props.findAll();
    }
    renderRows() {
       

       if(this.props.users){
        console.log(this.props.users.users);
        const list = this.props.users.users || []
        return list.map((item, index) => (
            <tr key={index}>
             <td>{item.name}</td>
             <td>{item.email}</td>
             <td>{item.create_date}</td>

             <td>
                    <button type='button' className='btn btn-success'
                        onClick={() => this.add(index + 1)}>
                        <i className="fa fa-plus"></i>
                    </button>
                    <button type='button' className='btn btn-warning'
                        onClick={() => this.add(index + 1, item)}>
                        <i className="fa fa-clone"></i>
                    </button>
                    <button type='button' className='btn btn-danger'
                        onClick={() => this.remove(index)}>
                        <i className="fa fa-trash-o"></i>
                    </button>
                </td>
           </tr>
        ))
       }
    }

    render() {
        return (
            <div>
                <ContentHeader title='Usuários do Sistema' small='Listagem' />
                <Content>
                    <div className="box-body no-padding">
                        <div className="box box-success">
                        <div className="row"></div>
                            <div className="row">
                                <div className="col-md-12 ">
                                <hr/>
                                <table className="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>Nome</th>
                                            <th>Email</th>
                                            <th>Data de Criação</th>
                                            <th>Ações</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    {this.renderRows()}
                                    </tbody>
                                </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </Content>
            </div>
        )
    }

}

const mapStateToProps = state => ({ users: state.users })
const mapDispatchToProps = dispatch => bindActionCreators({ findAll },
    dispatch)
export default connect(mapStateToProps, mapDispatchToProps)(Users)