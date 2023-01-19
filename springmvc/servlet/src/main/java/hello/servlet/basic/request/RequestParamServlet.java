package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 1. 파라미터 전송기능
* http://localhost:8080/request-param?username=hello&age=20
*
*/
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[전체 파라미터 조회] - start");

        req.getParameterNames().asIterator().forEachRemaining(paramName -> System.out.println(paramName + "=" + req.getParameter(paramName)));  // paramName은 key. getParameter를 하면 값

        System.out.println("[전체 파라미터 조회] - end");
//        req.getParameterNames();

        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");
        String username = req.getParameter("username"); // .getParameter는 하나의 파라미터 이름에 단 하나의 값만 있을 때 사용
        String age = req.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = req.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        resp.getWriter().write("ok");
    }
}
