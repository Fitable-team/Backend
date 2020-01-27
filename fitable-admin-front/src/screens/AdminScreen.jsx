import React from 'react'
import SideNavigation from '../components/SideNavigation'
import '@trendmicro/react-sidenav/dist/react-sidenav.css';


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