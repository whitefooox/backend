export const Auth = (() => {

    function auth(user){
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
                save(user.login, token);
                resolve();        
            })
            .catch(() => {
                console.log('Ошибка авторизации');
                reject();
            })
        })
    }

    function reg(user){
        return new Promise((resolve, reject) => {
            fetch("api/user/reg", {
                method: "POST",
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify(user)
            })
            .then(response => response.json())
            .then(token => {          
                console.log('Успешная регистрация');
                save(user.login, token);
                resolve();        
            })
            .catch(() => {
                console.log('Ошибка регистрации');
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
        getToken: getToken,
        reg: reg
    }
})();