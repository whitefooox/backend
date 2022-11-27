import { Auth } from "../model/auth.js";
import { Router } from "../model/router.js";
import { User } from "../model/dto/user.js"

export const AuthPage = (() => {

    let html = `<div class="main">  	
    <input type="checkbox" id="chk" aria-hidden="true">

        <div class="signup">
            <div>
                <label for="chk" aria-hidden="true">Sign up</label>
                <input type="text" placeholder="Login" id="id_reg_login">
                <input type="email" placeholder="Email" id="id_reg_email">
                <input type="password" placeholder="Password" id="id_reg_password">
                <button id="id_reg_button">Sign up</button>
            </div>
        </div>

        <div class="login">
            <div>
                <label for="chk" aria-hidden="true">Login</label>
                <input type="text" placeholder="Login" id="id_login_login">
                <input type="password" placeholder="Password" id="id_login_password">
                <button id="id_login_button">Login</button>
            </div>
        </div>
    </div>`;

    let css = 'public/css/auth.css';

    function clickLoginButton(){
        let user = new User();
        user.login = document.getElementById("id_login_login").value;
        user.password = document.getElementById("id_login_password").value;
        Auth.auth(user)
        .then(() => {
            Router.onNavigate('main');
        })
        .catch(() => {

        });
    }

    function clickRegButton(){
        let user = new User();
        user.login = document.getElementById("id_reg_login").value;
        user.email = document.getElementById("id_reg_email").value;
        user.password = document.getElementById("id_reg_password").value;
        Auth.reg(user)
        .then(() => {
            Router.onNavigate('main');
        })
        .catch(() => {

        });
    }

    function setStyle(root, css){
        let style = document.createElement('link');
        style.href = css;
        style.rel = 'stylesheet';
        root.appendChild(style);
        let loginButton = document.getElementById("id_login_button");
        loginButton.addEventListener("click", clickLoginButton);
        let regButton = document.getElementById("id_reg_button");
        regButton.addEventListener("click", clickRegButton);
    }

    function render(root){
        root.innerHTML = html;
        setStyle(root, css);
    }

    return {
        render: render
    }

})();