export const Router = new class Router {

    constructor(){
        this.root = document.getElementById('root');
        this.routes = []
    }

    add(name, page){
        const route = {name, page};
        this.routes.push(route);
    }

    go(name){
        this.routes.forEach(route => {
            if(route.name == name){
                route.page.render(root);
            }
        })
    }
}