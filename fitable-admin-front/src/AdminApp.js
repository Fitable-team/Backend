import React from 'react';
import LoginScreen from './screens/LoginScreen';

class AdminApp extends React.Component {

    constructor(props) {
        super(props)
    }

    render() {
        return(<LoginScreen/>)
    }
}

export default AdminApp;