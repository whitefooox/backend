import { Auth } from "../model/auth.js";
import { Router } from "../model/router.js";

export const LoginPage = (() => {

    let html = `<div class='login-form' id='div_login'>
        <form>
            <input class='un' type='text' placeholder='Enter login' id='id_login'><br>
            <input class='pass' type='password' placeholder='Enter password' id='id_password'><br>
            <input class='but' type='button' value='Login' align='center' id='id_button'>
        </form>
    </div>`;

    let css = 'public/css/login.css';

    function setStyle(root, css){
        let style = document.createElement('link');
        style.href = css;
        style.rel = 'stylesheet';
        root.appendChild(style);
    }

    function clickLoginButton(){
        let login = document.getElementById("id_login").value;
        let password = document.getElementById("id_password").value;
        Auth.auth(login, password)
        .then(() => {
            Router.onNavigate('main');
        })
        .catch(() => {
            let loginForm = document.getElementById("div_login");
            let error = document.createTextNode("Неверный логин или пароль");
            loginForm.appendChild(error);
        });
    }

    function render(root){
        root.innerHTML = html;
        setStyle(root, css);
        let button = document.getElementById("id_button");
        button.addEventListener("click", clickLoginButton);
    }

    return {
        render: render
    }

})();