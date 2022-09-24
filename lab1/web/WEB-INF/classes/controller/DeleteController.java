package controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.utils.DBUtil;

public class DeleteController {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public DeleteController(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void delete() throws Exception{
        String[] delete = req.getParameterValues("delete");
        String query = "delete from products where id = ";
        for(int i = 0; i < delete.length; i++){
            DBUtil.dbExecuteUpdate(query + delete[i]);
        }
        new LoginController(req, resp).showMainPage();
    }
}
