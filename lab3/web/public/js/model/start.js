window.onload = function() {   
    Router.ping();
    Router.appendRoute('login', LoginPage);
    Router.appendRoute('main', MainPage);
    Router.onNavigate('login');
};