package com.example.taurus.web.controller.admin;

import com.example.taurus.model.domain.User;
import com.example.taurus.model.dto.JsonResult;
import com.example.taurus.model.enums.ResultCodeEnum;
import com.example.taurus.web.controller.core.BaseController;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static com.example.taurus.model.dto.TaurusConst.USER_SESSION_KEY;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {
    @GetMapping(value = "/index")
    public String index(HttpSession session){
        return "admin/admin_index";
    }
    @PostMapping("/getLogin")
    public JsonResult Login(@ModelAttribute("loginName")String loginName,@ModelAttribute("loginPwd")String loginPwd)
    {
        log.info("开始");
        return new JsonResult(ResultCodeEnum.SUCCESS.getCode());
    }
    @GetMapping(value = "/login")
    public String login(HttpSession session)
    {
        final User user = (User)session.getAttribute(USER_SESSION_KEY);
        if(user!=null){
            return "redirect:/admin/index";
        }
        return "admin/admin_login";
    }
    @GetMapping(value = "/logOut")
    public String logOut(HttpSession session){
        final User user=(User)session.getAttribute(USER_SESSION_KEY);
        session.removeAttribute(USER_SESSION_KEY);
        log.info("User {} has logged out",user.getUserName());
        return "redirect:/admin/login";
    }
}
