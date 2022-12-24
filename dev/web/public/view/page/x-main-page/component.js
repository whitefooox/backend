import template from "./template.js";
import '../../component/x-search/component.js'
import '../../component/x-info/component.js'
import '../../component/x-player/component.js'

class XMainPage extends HTMLElement {

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

customElements.define('x-main-page', XMainPage);