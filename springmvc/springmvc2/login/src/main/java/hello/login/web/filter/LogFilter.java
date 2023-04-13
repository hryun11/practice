package hello.login.web.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        // 일반 ServletRequest는 HttpServletRequest의 부모 객체. 기능이 별로 없기 때문에 http로 캐스팅 필요.
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [ {} ][ {} ]", uuid, requestURI);
            chain.doFilter(request, response);  // 다음 필터 호출. 없으면 서블릿으로.
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("RESPONSE [ {} ] [ {} ]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
