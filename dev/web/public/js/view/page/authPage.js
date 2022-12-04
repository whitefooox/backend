import { Auth } from "../../model/server/auth.js";
import { Router } from "../util/router.js";
import { User } from "../../model/dto/user.js"

export const AuthPage = new class AuthPage {

    constructor(){
        this.html = `<div class="main">  	
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
        this.css = 'public/css/auth.css';
    }

    #clickLoginButton(){
        const user = new User();
        user.login = document.getElementById("id_login_login").value;
        user.password = document.getElementById("id_login_password").value;
        Auth.auth(user)
        .then(() => {
            Router.go('main');
        })
        .catch(() => {

        });
    }

    #clickRegButton(){
        const user = new User();
        user.login = document.getElementById("id_reg_login").value;
        user.email = document.getElementById("id_reg_email").value;
        user.password = document.getElementById("id_reg_password").value;
        Auth.reg(user)
        .then(() => {
            Router.go('main');
        })
        .catch(() => {

        });
    }

    #setStyle(root){
        const style = document.createElement('link');
        style.href = this.css;
        style.rel = 'stylesheet';
        root.appendChild(style);
    }

    render(root){
        root.innerHTML = this.html;
        this.#setStyle(root);
        document.getElementById("id_login_button").addEventListener("click", this.#clickLoginButton);
        document.getElementById("id_reg_button").addEventListener("click", this.#clickRegButton);
    }
}