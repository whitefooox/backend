import { Auth } from "./auth.js";

export const Anime = new class Anime {

    async search(name){
        const response = await fetch("api/anime/search/" + name, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                login: Auth.getLogin(),
                token: Auth.getToken(),
            }
        });
        return response.ok ? response.json() : Promise.reject();
    }

    async getSource(url){
        console.log('get source');
        const response = await fetch("api/anime/source/", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                login: login,
                token: token,
                userAgent: window.navigator.userAgent
            },
            body: JSON.stringify(url)
        });
        return response.ok ? response.json() : Promise.reject();
    }
}