loginPage = "<div class='login-form'>" + 
"<p class='sign' align='center'>ToDo ✔</p>" +
"<form>" + 
"<input class='un' type='text' placeholder='Enter login' id='id_login'><br>" + 
"<input class='pass' type='password' placeholder='Enter password' id='id_password'><br>" +
"<input class='but' type='button' value='Login' align='center' id='id_button'>" +
"</form>" + 
"<p class='error' id='id_error'></p>" + 
"</div>";

function auth() {
    var login = document.getElementById("id_login").value;
    var password = document.getElementById("id_password").value;
    var user = {
        "login": login,
        "password": password
    };
    xhr("POST", "api/auth", {body: JSON.stringify(user)}, function (xhr){
        if (xhr.readyState !== 4) {
            return;
        }
        if (xhr.status === 200) {
            var data = JSON.parse(xhr.responseText);
            localStorage.setItem('login', data.login);
            localStorage.setItem('token', data.token);
            loadMain();
        } else if (xhr.status === 401) {
            var p = document.getElementById("id_error");
            p.innerText = 'Неверный логин или пароль';
        }
        else {
            console.log('err', xhr.responseText);
        }
    });
}

document.addEventListener("DOMContentLoaded", function () {
    setPage(loginPage, 'ui/style/login.css');
    var button = document.getElementById("id_button");
    button.addEventListener("click", function () {
        auth();
    });
})