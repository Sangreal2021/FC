package org.delivery.storeadmin.presentation;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class PageController {
    
    @RequestMapping(path = {"","/main"})
    public ModelAndView main() {
        // 아래 main 에 .html 이 붙어서 Front-End 로 연결
        ModelAndView modelAndView = new ModelAndView("main");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        modelAndView.addObject("username", currentUserName);
        return modelAndView;
    }
    
    @RequestMapping("/order")
    public ModelAndView order(){
        // order 패키지 아래 order.html 로 연동
        return new ModelAndView("order/order");
    }
}
