import template from "./template.js";
import '../../component/x-auth/component.js'

class XAuthPage extends HTMLElement {

    constructor(){
        super();
        this.attachShadow({mode: 'open'});
    }

    connectedCallback(){
        this._render();
    }

    _render(){
        if(!this.ownerDocument.defaultView) return;    
        this.shadowRoot.innerHTML = template(this);
    }
}

customElements.define('x-auth-page', XAuthPage);