(
    async () => {
        const routerModule = await import('./view/route/router.js');
        const router = routerModule.RouterFactory.createInstance();

        router.add('auth', 'x-auth-page');
        router.add('main', 'x-main-page');
        router.default('auth');
        router.go();
    }

)();
