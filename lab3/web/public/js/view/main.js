var MainPage = (function(){

    var html = `<div align='center'>
                    <form class="example">
                        <input placeholder="Введите название аниме..." type="text", id='id_main_text_search'>
                        <input type='button' value='Поиск' id='id_main_button_search'>
                    </form>
                    <br>
                    <div id='id_main_div_anime'></div>
                </div>`

    var css = 'public/css/main.css';

    function appendAnime(anime){
        var div = document.createElement('div');
        var text = document.createElement('p');
        var image = document.createElement('img');
        image.setAttribute('class', 'logo');
        text.setAttribute('class', 'name');
        text.textContent = anime.name;
        image.src = anime.image;
        div.appendChild(text);
        div.appendChild(document.createElement('br'));
        div.appendChild(image);
        div.appendChild(document.createElement('br'));
        var seasonSelect = document.createElement('select');
        var seriaSelect = document.createElement('select');
        var download = document.createElement('button');
        var video = document.createElement('iframe');
        video.class = 'video';
        video.setAttribute('allowFullScreen', '');
        video.setAttribute('width', '720');
        video.setAttribute('height', '405');
        download.textContent = 'смотреть';
        div.appendChild(seasonSelect);
        div.appendChild(seriaSelect);
        div.appendChild(download);
        div.appendChild(document.createElement('br'));
        div.appendChild(video);
        for(var season in anime.data){
            var seasonOption = document.createElement('option');
            seasonOption.value = season;
            seasonOption.text = season;
            seasonSelect.appendChild(seasonOption);
        }
        for(var seria in anime.data[seasonSelect.value]){
            var seriaOption = document.createElement('option');
            seriaOption.value = seria;
            seriaOption.text = seria;
            seriaSelect.appendChild(seriaOption);
        }
        download.href = anime.data[seasonSelect.value][seriaSelect.value];
        seasonSelect.addEventListener('change', function(){
            var season = seasonSelect.value;
            seriaSelect.replaceChildren();
            for(var seria in anime.data[season]){
                var seriaOption = document.createElement('option');
                seriaOption.value = seria;
                seriaOption.text = seria;
                seriaSelect.appendChild(seriaOption);
            }
        });
        download.addEventListener('click', function(){
            Anime.download(anime.data[seasonSelect.value][seriaSelect.value], function(xhr){
                if (xhr.readyState !== 4) {
                    return;
                }
                if (xhr.status === 200) {
                    console.log(xhr.responseText);
                    video.src = JSON.parse(xhr.responseText);
                } 
                else {
                    console.log('bad', xhr.responseText);
                }
            });
        })
        return div;
    }

    function render(){
        var root = document.getElementById('root');
        root.innerHTML = html;
        var style = document.createElement('link');
        style.href = css;
        style.rel = 'stylesheet';
        root.appendChild(style);
        var buttonSearch = document.getElementById('id_main_button_search');
        buttonSearch.addEventListener('click', function(){
            var name = document.getElementById('id_main_text_search').value;
            Anime.search(name, function(xhr){
                if (xhr.readyState !== 4) {
                    return;
                }
                if (xhr.status === 200) {
                    var text = document.getElementById('id_main_text_search');
                    text.value = '';
                    var anime = JSON.parse(xhr.responseText);
                    if(anime == null){
                        text.placeholder = 'Не найдено';
                        return;
                    }
                    text.placeholder = 'Найдено';
                    var div = document.getElementById('id_main_div_anime');
                    var node = appendAnime(anime);
                    div.replaceChildren();
                    div.appendChild(node);
                } 
                else {
                    console.log('bad', xhr.responseText);
                }
            });
        })
    }

    return {
        render: render
    }
})();