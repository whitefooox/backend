import { AnimeFactory } from '../../../model/Anime.js';
import template from './template.js'

class XSearch extends HTMLElement {

    constructor(){
        super();
        this.attachShadow({mode: 'open'});
        this.query = null;
        this.anime = AnimeFactory.createInstance();
    }

    connectedCallback(){
        this._render();
    }

    _render(){
        if(!this.ownerDocument.defaultView) return;    
        this.shadowRoot.innerHTML = template(this);
        this.shadowRoot.childNodes[1].childNodes[1].addEventListener('change', (e) => {
            this._search(e);
        })
    }

    _search(event){
        event.stopPropagation();
        this.query = event.target.value;
        this.anime.search(this.query);
    }
}

customElements.define('x-search', XSearch);