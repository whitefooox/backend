class User {
    constructor(login, password) {
        this.login = login;
        this.password = password;
    }
}

export const Auth = (() => {

    function auth(login, password){
        let user = new User(login, password);
        return new Promise((resolve, reject) => {
            fetch("api/user/auth", {
                method: "POST",
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify(user)
            })
            .then(response => response.json())
            .then(token => {          
                console.log('Успешная авторизация');
                save(login, token);
                resolve();        
            })
            .catch(() => {
                console.log('Ошибка авторизации');
                reject();
            })
        })
    }

    function save(login, token){
        localStorage.setItem('login', login);
        localStorage.setItem('token', token);
    }

    function getLogin(){
        return localStorage.getItem('login');
    }

    function getToken(){
        return localStorage.getItem('token');
    }

    return {
        auth: auth,
        getLogin: getLogin,
        getToken: getToken
    }
})();