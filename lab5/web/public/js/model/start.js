import { Router } from "./router.js";
import { LoginPage } from "../view/login.js"
import { MainPage } from "../view/main.js"

Router.appendRoute('login', LoginPage);
Router.appendRoute('main', MainPage);
Router.onNavigate('login');