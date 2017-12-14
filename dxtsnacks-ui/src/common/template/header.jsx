import React from 'react'

export default props => (
    <header className='main-header'>
        <a href='/#/' className='logo'>
            <span className='logo-mini'><b>D</b>X<b>T</b></span>
            <span className='logo-lg'>
                <i className='fa fa-apple'></i>
                <b> DXT</b> Snacks
            </span>        
        </a>
        <nav className='navbar navbar-static-top'>
            <a href className='sidebar-toggle' data-toggle='offcanvas'></a>
        </nav>
    </header>
)