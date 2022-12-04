import { Router } from "./router.js";
import { MainPage } from "../page/main.js"
import { AuthPage } from '../page/authPage.js'

Router.add('auth', AuthPage);
Router.add('main', MainPage);
Router.go('auth');