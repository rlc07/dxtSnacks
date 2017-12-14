import React from 'react'
import MenuItem from './menuItem'
import MenuTree from './menuTree'

export default props => (
    <ul className='sidebar-menu'>
        <MenuItem path='/' label='Dashboard' icon='dashboard' />

        <MenuItem path='/users' label='Usuarios do Sistema' icon='users' />
        <MenuItem path='/menu' label='Cardapio' icon='book' />
        <MenuItem path='/snacks' label='Lanches' icon='pie-chart ' />
        
    </ul>
)