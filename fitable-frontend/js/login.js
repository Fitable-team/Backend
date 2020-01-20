function handleLoginEvent() {
    const idInput = document.getElementById("login-input").innerText;
    const passwordInput = document.getElementById("password-input").innerText;

    var entity = new LoginEntity(idInput, passwordInput);

    var loginResult = fetch("/login", )
        .catch()
        .finally()
}

function LoginEntity(idInput, passwordInput) {
    if(!this instanceof LoginEntity) {
        return new LoginEntity(idInput, passwordInput);
    }
    this.idInput = idInput;
    this.passwordInput = passwordInput;
}
