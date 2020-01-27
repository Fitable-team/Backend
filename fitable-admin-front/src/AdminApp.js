import React from 'react';
import LoginScreen from './screens/LoginScreen';
import AdminScreen from './screens/AdminScreen'

class AdminApp extends React.Component {

    constructor(props) {
        super(props)
    }

    render() {
        return(<AdminScreen/>)
    }
}

export default AdminApp;