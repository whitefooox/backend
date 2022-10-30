var LoginPage = (function(){

    var html = "<div class='login-form' id='div_login'>" +
    "<form>" + 
    "<input class='un' type='text' placeholder='Enter login' id='id_login'><br>" + 
    "<input class='pass' type='password' placeholder='Enter password' id='id_password'><br>" +
    "<input class='but' type='button' value='Login' align='center' id='id_button'>" +
    "</form>" + 
    "</div>";

    var css = "public/css/login.css"

    function render(){
        var root = document.getElementById('root');
        root.innerHTML = html;
        var style = document.createElement('link');
        style.href = css;
        style.rel = 'stylesheet';
        root.appendChild(style);
        var button = document.getElementById("id_button");
        button.addEventListener("click", function () {
            var login = document.getElementById("id_login").value;
            var password = document.getElementById("id_password").value;
            Auth.run(login, password, function(xhr){
                if (xhr.readyState !== 4) {
                    return;
                }
                if (xhr.status === 200) {
                    var token = JSON.parse(xhr.responseText);
                    Auth.save(login, token);
                    Router.onNavigate('main');
                } else if (xhr.status === 401) {
                    var loginForm = document.getElementById("div_login");
                    var error = document.createTextNode("Неверный логин или пароль");
                    loginForm.appendChild(error);
                }
                else {
                    console.log(xhr.responseText);
                }
            })
        });
    }

    return {
        render: render
    }
})();