var Anime = (function(){

    function getAll(callback){
        send("GET", "api/anime", {
            headers: {
                login: localStorage.getItem('login'),
                token: localStorage.getItem('token')
            }
        }, callback);
    }

    function search(name, callback){
        send("GET", "api/anime/search/" + name, {
            headers: {
                login: localStorage.getItem('login'),
                token: localStorage.getItem('token')
            }
        }, callback);
    }

    function download(url, callback){
        send("POST", "api/anime/download/", {
            headers: {
                login: localStorage.getItem('login'),
                token: localStorage.getItem('token'),
                userAgent: window.navigator.userAgent
            },
            body: JSON.stringify(url)
        }, callback);
    }

    return {
        getAll: getAll,
        search: search,
        download: download
    }
})();