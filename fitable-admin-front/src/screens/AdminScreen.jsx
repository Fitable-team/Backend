import React from 'react'
import SideNavigation from '../components/SideNavigation'
import './styles/sidebar.css'

class AdminScreen extends React.Component {
    
    constructor(props) {
        super(props)
    }

    render() {
        return(
            <SideNavigation/>
        )
    }


}

export default AdminScreen;