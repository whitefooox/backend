import { Auth } from "./auth.js";

export const Anime = (() => {

    function search(name){
        return new Promise((resolve, reject) => {
            console.log("Поиск");
            let login = Auth.getLogin();
            let token = Auth.getToken();
            fetch("api/anime/search/" + name, {
                method: "GET",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                    login: login,
                    token: token
                }
            })
            .then(response => response.json())
            .then(anime => {          
                console.log('Найдено аниме');
                resolve(anime);        
            })
            .catch(() => {
                console.log('Ошибка при поиске аниме');
                reject();
            })
        })
    }

    function getSource(url){
        console.log("Получение источника");
        let login = Auth.getLogin();
        let token = Auth.getToken();
        return new Promise((resolve, reject) => {
            fetch("api/anime/source/", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                    login: login,
                    token: token,
                    userAgent: window.navigator.userAgent
                },
                body: JSON.stringify(url)
            })
            .then(response => response.json())
            .then(src => {          
                console.log('Источник получен');
                resolve(src);        
            })
            .catch(() => {
                console.log('Ошибка при получении источника');
                reject();
            })
        });
    }

    return {
        search: search,
        getSource: getSource
    }
})();