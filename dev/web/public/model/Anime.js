import { AuthFactory } from "./Auth.js";
import { Store } from "./Store.js";

class Anime extends Store {
  constructor() {
    super();
    this.auth = AuthFactory.createInstance();
  }

  async search(name) {
    const response = await fetch("api/anime/search/" + name, {
      method: "GET",
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
        login: this.auth.getLogin(),
        token: this.auth.getToken(),
      },
    });
    if(response.ok){
        response.json().then((anime) => {
            this._emit(anime);
        });
    } else {
        console.log('ошибка при получении данных', response.status);
    }
  }

  async getSource(url) {
    const response = await fetch("api/anime/source/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
        login: this.auth.getLogin(),
        token: this.auth.getToken(),
        userAgent: window.navigator.userAgent,
      },
      body: JSON.stringify(url),
    });
    return response.ok ? response.json() : Promise.reject();
  }
}

export class AnimeFactory {
  static _anime = null;

  static _createInstance() {
    return new Anime();
  }

  static createInstance() {
    if (AnimeFactory._anime === null) {
      AnimeFactory._anime = AnimeFactory._createInstance();
    }
    return AnimeFactory._anime;
  }
}
