import java.io.IOException;
import java.io.PrintWriter;
import controller.AddController;
import controller.DeleteController;
import controller.LoginController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WebServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        resp.setCharacterEncoding("cp866");
        PrintWriter pw = resp.getWriter();
        if(servletPath.equals("/login")){
            try {
                new LoginController(req, resp).login();
            } catch (Exception e) {
                pw.println(e);
            }
        } else if(servletPath.equals("/add")){
            try {
                new AddController(req, resp).add();
            } catch (Exception e) {
                pw.println(e);
            }
        } else if(servletPath.equals("/delete")){
            try {
                new DeleteController(req, resp).delete();
            } catch (Exception e) {
                pw.println(e);
            }
        }
    }
}
