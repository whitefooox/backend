package controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.utils.DBUtil;

public class AddController {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public AddController(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void add() throws Exception{
        String name = (String) req.getParameter("name_add");
        String count = (String) req.getParameter("count_add");
        int userId =  (int) req.getSession().getAttribute("userId");
        String query = "insert into products (\"name\", count, user_id) values('" + name + "', " + count + ", " + userId + ")";
        DBUtil.dbExecuteUpdate(query);
        new LoginController(req, resp).showMainPage();
    }
}
