package controller;
import java.util.ArrayList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.product.Product;
import model.product.ProductDAO;
import model.user.UserDAO;

public class LoginController {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public LoginController(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void login() throws Exception{
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(UserDAO.validate(login, password)){
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            session.setAttribute("userId", UserDAO.getId(login, password));
            showMainPage();
        }
    }

    public void showMainPage() throws Exception {
        ArrayList<Product> list = ProductDAO.getAll((String) req.getSession().getAttribute("login"));
        int size = list.size();
        int[] count = new int[size];
        String[] name = new String[size];
        int[] id = new int[size];
        for(int i = 0; i < size; i++){
            count[i] = list.get(i).getCount();
            name[i] = list.get(i).getName();
            id[i] = list.get(i).getId();
        }
        req.setAttribute("size", size);
        req.setAttribute("name", name);
        req.setAttribute("count", count);
        req.setAttribute("id", id);
        req.getRequestDispatcher("WEB-INF/main.jsp").forward(req, resp);
    }
}
