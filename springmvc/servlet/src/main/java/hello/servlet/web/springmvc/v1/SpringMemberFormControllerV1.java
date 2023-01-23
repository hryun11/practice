package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//스프링이 자동으로 스프링 빈으로 등록(내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨.)
@Controller
// RequestMappingHandlerMapping 은 스프링 빈 중에서 @RequestMapping 또는 @Controller 가
//클래스 레벨에 붙어 있는 경우에 매핑 정보로 인식한다.
//@Component
//@RequestMapping
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")   //요청 정보를 매핑. 해당 URL이 호출되면 이 메서드가 호출된다.
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
