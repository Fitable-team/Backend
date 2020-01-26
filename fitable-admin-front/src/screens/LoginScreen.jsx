import React from 'react'
import { Form, Button } from 'react-bootstrap'
import './styles/login.css'

class LoginScreen extends React.Component {
    
    constructor(props) {
        super(props)
    }

    render() {
        return(
            <div className="fitable-login login-center-component">
                <h4>fitable logo</h4>
                <div className="login-center-caption">
                    Fitable 정보등록 관리자 시스템입니다. <br/>
                    Fitable 운영자로부터 공유받은 ID와 Password를 입력해서 로그인해주세요.
                </div>
                <div className="login-center-inputbox">
                    <div className="login-userid">
                        <div className="login-id-box login-id-caption"> Studio ID</div> 
                        <Form.Control type="text" id="login-input" className="login-inputbox login-id-box login-id-input" placeholder="Studio ID" />
                    </div>
                    <div className="login-userpwd">
                        <div className="login-pwd-box login-pwd-caption"> Password</div> 
                        <Form.Control type="password" id="password-input" className="login-inputbox login-pwd-box login-pwd-input" />
                    </div>
                </div>
                <div className="login-center-buttongroup">
                    <div className="login-button">
                        <Button variant="success" className="login-button-box">로그인</Button>
                    </div>
                    <div className="login-iforgot">
                        <a className="login-pwd-forgot" href="#">비밀번호 찾기</a>
                    </div>
                </div>
            </div>
        )
    }
}

export default LoginScreen