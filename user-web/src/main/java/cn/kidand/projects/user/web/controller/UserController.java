package cn.kidand.projects.user.web.controller;


import cn.kidand.projects.user.service.UserService;
import cn.kidand.projects.user.service.UserServiceImpl;
import cn.kidand.projects.user.domain.User;
import cn.kidand.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * ██╗  ██╗██╗██████╗  █████╗ ███╗   ██╗██████╗
 * ██║ ██╔╝██║██╔══██╗██╔══██╗████╗  ██║██╔══██╗
 * █████╔╝ ██║██║  ██║███████║██╔██╗ ██║██║  ██║
 * ██╔═██╗ ██║██║  ██║██╔══██║██║╚██╗██║██║  ██║
 * ██║  ██╗██║██████╔╝██║  ██║██║ ╚████║██████╔╝
 * ╚═╝  ╚═╝╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝
 *
 * @description:UserController
 * @author: Kidand
 * @date: 2021/3/9 6:48 下午
 * Copyright © 2019-Kidand.
 */
@Path("/user")
public class UserController implements PageController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private UserService userService = new UserServiceImpl();

    @Path("/register/page")
    public String registerPage(HttpServletRequest request, HttpServletResponse response) {
        return "register.jsp";
    }

    @Path("/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        stringCharsetConvert(request.getParameter("name")).ifPresent(user::setName);

        String password = request.getParameter("password");
        if (password.length() >= 6 && password.length() <= 32) {
            stringCharsetConvert(request.getParameter("password")).ifPresent(user::setPassword);
        } else {
            return "redirect:passwordsize/fail";
        }

        if (request.getParameter("email").contains("@")) {
            stringCharsetConvert(request.getParameter("email")).ifPresent(user::setEmail);
        } else {
            return "redirect:emailsize/fail";
        }

        String phoneNumber = request.getParameter("phoneNumber");

        if (isNumeric(phoneNumber) && phoneNumber.length() == 11) {
            stringCharsetConvert(request.getParameter("phoneNumber")).ifPresent(user::setPhoneNumber);
        } else {
            return "redirect:telsize/fail";
        }

        // 重复性校验
        User dbUser = userService.queryUserByName(user.getName());
        Optional.ofNullable(dbUser).ifPresent(u -> logger.info(String.format("dbUser[%s]", u.toString())));
        if (dbUser != null) {
            logger.warning(String.format("user[%s] 用户名重复", user.toString()));
            return "redirect:register/fail";
        }

        boolean isRegisterSuccess = userService.register(user);
        logger.info(String.format("user[%s] isRegisterSuccess[%s]", user.toString(), isRegisterSuccess));
        return isRegisterSuccess ? "redirect:register/success" : "redirect:register/fail";
    }

    @Path("/register/success")
    public String registerSuccess(HttpServletRequest request, HttpServletResponse response) {
        return "register-success.jsp";
    }

    @Path("/register/fail")
    public String registerFail(HttpServletRequest request, HttpServletResponse response) {
        return "register-fail.jsp";
    }

    @Path("/passwordsize/fail")
    public String passwordSizeFail(HttpServletRequest request, HttpServletResponse response) {
        return "passwordsize-fail.jsp";
    }

    @Path("/telsize/fail")
    public String telSizeFail(HttpServletRequest request, HttpServletResponse response) {
        return "telsize-fail.jsp";
    }

    @Path("/emailsize/fail")
    public String emailSizeFail(HttpServletRequest request, HttpServletResponse response) {
        return "emailsize-fail.jsp";
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return null;
    }

    private boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
