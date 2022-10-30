var Auth = (function(){

    function run(login, password, callback){
        var user = new User(login, password);
        send("POST", "api/user/auth", {body: JSON.stringify(user)}, callback);
    }

    function save(login, token){
        localStorage.setItem('login', login);
        localStorage.setItem('token', token);
    }

    return {
        run: run,
        save: save
    }
})();