package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";    //WEB-INF 안의 경로는 외부에서 호출할수 없음. 항상 컨트롤러, 서블릿을 통해서만 호출 가능.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//controller에서 view로 이동할 때 사용
        dispatcher.forward(request, response); //servlet에서 jsp를 호출. 다른 서블릿이나 jsp로 이동할수 있는 기능. 서버 내부에서 다시 호출이 발생. redirect아님.

        //서버 내부에서 호출하여 제어권을 넘겨줌
    }
}
