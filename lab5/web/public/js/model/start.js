import { Router } from "./router.js";
import { MainPage } from "../view/main.js"
import { AuthPage } from '../view/auth.js'

Router.appendRoute('auth', AuthPage);
Router.appendRoute('main', MainPage);
Router.onNavigate('auth');