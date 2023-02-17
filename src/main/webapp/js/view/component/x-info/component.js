import { AnimeFactory } from '../../../model/Anime.js';
import template from './template.js'

class XInfo extends HTMLElement {

    constructor(){
        super();
        this.attachShadow({mode: 'open'});
        const Anime = AnimeFactory.createInstance();
        Anime.subscribe((anime) => {
            this._update(anime);
        });
    }

    connectedCallback(){
        this._render();
    }

    _render(){
        if(!this.ownerDocument.defaultView) return;    
        this.shadowRoot.innerHTML = template(this);
    }

    _update(anime){
        const div = this.shadowRoot.childNodes[1];
        const h2 = div.childNodes[1];
        const img = div.childNodes[5];
        if(anime == null){
            h2.textContent = 'Ничего не найдено';
            img.src = '';
            return;
        }
        h2.textContent = anime.name;
        img.src = anime.image;
    }
}

customElements.define('x-info', XInfo);