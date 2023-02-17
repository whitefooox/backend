import { AnimeFactory } from '../../../model/Anime.js';
import template from './template.js'

class XPlayer extends HTMLElement {

    constructor(){
        super();
        this.attachShadow({mode: 'open'});
        this.Anime = AnimeFactory.createInstance();
        this.Anime.subscribe((anime) => {
            this._update(anime);
        });
    }

    connectedCallback(){
        this._render();
    }

    _render(){}

    _update(anime){

        if(anime == null) {
            this.shadowRoot.innerHTML = '';
            return;
        }

        if(!this.ownerDocument.defaultView) return;    
        this.shadowRoot.innerHTML = template(this);

        function setSeries(series, seriaSelect){
            seriaSelect.replaceChildren();
            for(const seria in series){
                const seriaOption = document.createElement('option');
                seriaOption.value = seria;
                seriaOption.text = seria;
                seriaSelect.appendChild(seriaOption);
            }
        }

        function setSeasons(seasons, seasonSelect){
            seasonSelect.replaceChildren();
            for(const season in seasons){
                const seasonOption = document.createElement('option');
                seasonOption.value = season;
                seasonOption.text = season;
                seasonSelect.appendChild(seasonOption);
            }
        }

        const div = this.shadowRoot.childNodes[1];
        const seasonSelect = div.childNodes[1];
        const seriaSelect = div.childNodes[3];
        const button = div.childNodes[5];
        const iframe = div.childNodes[9];
        setSeasons(anime.data, seasonSelect);
        setSeries(anime.data[seasonSelect.value], seriaSelect);
        seasonSelect.addEventListener('change', () => {
            const season = seasonSelect.value;
            setSeries(anime.data[season], seriaSelect);
        });
        button.addEventListener('click', () => {
            const url = anime.data[seasonSelect.value][seriaSelect.value];
            this.Anime.getSource(url)
            .then(src => {
                iframe.src = src;
            })
        });
    }
}

customElements.define('x-player', XPlayer);