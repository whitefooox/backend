var Router = (function (){
    
    var routes = [];

    function ping (){
        console.log('ping');
    }

    function appendRoute(name, page){
        var route = {
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
        ping: ping,
        appendRoute: appendRoute,
        onNavigate: onNavigate
    }
})();