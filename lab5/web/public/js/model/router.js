export const Router = (() => {

    let root = document.getElementById('root');
    let routes = [];

    function appendRoute(name, page){
        let route = {
            name: name,
            page: page
        };
        routes.push(route);
    }

    function onNavigate(name){
        routes.forEach(function(route){
            if(route.name == name){
                route.page.render(root);
            }
        });
    }

    return {
        appendRoute: appendRoute,
        onNavigate: onNavigate
    }

})();