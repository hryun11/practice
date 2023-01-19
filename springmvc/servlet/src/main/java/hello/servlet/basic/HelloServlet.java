package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")  // /hello로 오면 실행됨
public class HelloServlet extends HttpServlet {
    // 서블릿이 호출되면 service 메소드가 호출된다
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("req = " + req);
        System.out.println("resp = " + resp);

        String username = req.getParameter("username");
        System.out.println("username = " + username);

        resp.setContentType("text/plain");  // content type 지정
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("hello " + username); // http 메세지 body에 들어갈 데이터
    }
}
