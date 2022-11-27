import { Anime } from "../model/anime.js";

export const MainPage = (() => {

    let html = `<div align='center'>
        <form class="example">
            <input placeholder="Введите название аниме..." type="text", id='id_main_text_search'>
            <input type='button' value='Поиск' id='id_main_button_search'>
        </form>
        <br>
        <div id='id_main_div_anime'></div>
    </div>`

    let css = 'public/css/main.css';

    function setStyle(root, css){
        let style = document.createElement('link');
        style.href = css;
        style.rel = 'stylesheet';
        root.appendChild(style);
    }

    function createInfo(anime){
        let div = document.createElement('div');
        let text = document.createElement('h2');
        let image = document.createElement('img');
        let br = document.createElement('br');
        let br2 = document.createElement('br');
        image.setAttribute('class', 'logo');
        image.src = anime.image;
        text.setAttribute('class', 'name');
        text.textContent = anime.name;
        [text, br, image, br2].forEach(element => {
            div.appendChild(element);
        });
        return div;
    }

    function createPlayer(anime){

        function setSeries(series, seriaSelect){
            seriaSelect.replaceChildren();
            for(let seria in series){
                let seriaOption = document.createElement('option');
                seriaOption.value = seria;
                seriaOption.text = seria;
                seriaSelect.appendChild(seriaOption);
            }
        }

        let div = document.createElement('div');
        let seasonSelect = document.createElement('select');
        let seriaSelect = document.createElement('select');
        let playButton = document.createElement('button');
        let br = document.createElement('br');
        let iframePlayer = document.createElement('iframe');
        let videoAttr = [['class', 'video'],
                        ['allowFullScreen', ''],
                        ['width', '720'],
                        ['height', '405']];
        videoAttr.forEach(attr => {
            iframePlayer.setAttribute(attr[0], attr[1]);
        });
        [seasonSelect, seriaSelect, playButton, br, iframePlayer].forEach(element => {
            div.appendChild(element);
        })
        for(let season in anime.data){
            let seasonOption = document.createElement('option');
            seasonOption.value = season;
            seasonOption.text = season;
            seasonSelect.appendChild(seasonOption);
        }
        setSeries(anime.data[seasonSelect.value], seriaSelect);
        playButton.textContent = 'Смотреть';
        playButton.href = anime.data[seasonSelect.value][seriaSelect.value];
        seasonSelect.addEventListener('change', () => {
            let season = seasonSelect.value;
            setSeries(anime.data[season], seriaSelect);
        });
        playButton.addEventListener('click', () => {
            let url = anime.data[seasonSelect.value][seriaSelect.value];
            Anime.getSource(url)
            .then(src => {
                iframePlayer.src = src;
            })
            .catch(() => {})
        })
        return div;
    }

    function createAnime(anime){
        let div = document.createElement('div');
        div.appendChild(createInfo(anime));
        div.appendChild(createPlayer(anime));
        return div;
    }

    function clickSearchButton(){
        let name = document.getElementById('id_main_text_search').value;
        Anime.search(name)
        .then((anime) => {
            if(anime == null){
                return;
            }
            let div = document.getElementById('id_main_div_anime');
            let node = createAnime(anime);
            div.replaceChildren();
            div.appendChild(node);
        })
        .catch(() => {})
    }

    function render(root){
        root.innerHTML = html;
        setStyle(root, css);
        let buttonSearch = document.getElementById('id_main_button_search');
        buttonSearch.addEventListener('click', clickSearchButton);
    }

    return {
        render: render
    }
})();