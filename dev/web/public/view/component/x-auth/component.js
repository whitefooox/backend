import template from './template.js'
import { RouterFactory } from '../../route/router.js'
import { AuthFactory } from '../../../model/Auth.js'
import { User } from '../../../model/user.js';

class XAuth extends HTMLElement {

    constructor(){
        super();
        this.attachShadow({mode: 'open'});
        this.login = null;
        this.password = null;
        this.email = null;
    }

    connectedCallback(){
        this._render();
    }

    _render(){
        if(!this.ownerDocument.defaultView) return;    
        this.shadowRoot.innerHTML = template(this);
        const divSignup = this.shadowRoot.childNodes[1].childNodes[3].childNodes[1];
        const signupLogin = divSignup.childNodes[3];
        const signupEmail = divSignup.childNodes[5];
        const signupPassword = divSignup.childNodes[7];
        const signupButton = divSignup.childNodes[9];
        signupLogin.addEventListener('change', (e) => {
            e.stopPropagation();
            this.login = e.target.value;
        });
        signupEmail.addEventListener('change', (e) => {
            e.stopPropagation();
            this.email = e.target.value;
        });
        signupPassword.addEventListener('change', (e) => {
            e.stopPropagation();
            this.password = e.target.value;
        });
        signupButton.addEventListener('click', (e) => {
            e.stopPropagation();
            const router = RouterFactory.createInstance();
            const auth = AuthFactory.createInstance();
            const user = new User();
            user.login = this.login;
            user.password = this.password;
            user.email = this.email;
            auth.reg(user).then(() => {
                router.go('main');
            })
            .catch(() => {
                console.log('error')
            });
        });
        const divLogin = this.shadowRoot.childNodes[1].childNodes[5].childNodes[1];
        const loginLogin = divLogin.childNodes[3];
        const loginPassword = divLogin.childNodes[5];
        const loginButton = divLogin.childNodes[7];
        loginLogin.addEventListener('change', (e) => {
            e.stopPropagation();
            this.login = e.target.value;
        });
        loginPassword.addEventListener('change', (e) => {
            e.stopPropagation();
            this.password = e.target.value;
        });
        loginButton.addEventListener('click', (e) => {
            e.stopPropagation();
            const router = RouterFactory.createInstance();
            const auth = AuthFactory.createInstance();
            const user = new User();
            user.login = this.login;
            user.password = this.password;
            auth.auth(user).then(() => {
                router.go('main');
            })
            .catch(() => {
                console.log('error')
            });
        });
    }
}

customElements.define('x-auth', XAuth);