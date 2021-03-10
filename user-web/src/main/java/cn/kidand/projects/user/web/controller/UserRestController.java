package cn.kidand.projects.user.web.controller;


import cn.kidand.projects.user.service.UserService;
import cn.kidand.projects.user.service.UserServiceImpl;
import cn.kidand.projects.user.domain.Result;
import cn.kidand.projects.user.domain.User;
import cn.kidand.web.mvc.controller.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.util.Objects;

/**
*
*  ██╗  ██╗██╗██████╗  █████╗ ███╗   ██╗██████╗
*  ██║ ██╔╝██║██╔══██╗██╔══██╗████╗  ██║██╔══██╗
*  █████╔╝ ██║██║  ██║███████║██╔██╗ ██║██║  ██║
*  ██╔═██╗ ██║██║  ██║██╔══██║██║╚██╗██║██║  ██║
*  ██║  ██╗██║██████╔╝██║  ██║██║ ╚████║██████╔╝
*  ╚═╝  ╚═╝╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝
*
* @description:UserRestController
* @author: Kidand
* @date: 2021/3/9 7:35 下午
* Copyright © 2019-Kidand.
*/
@Path("/user")
public class UserRestController implements RestController {

    private UserService userService = new UserServiceImpl();

    @Path("/all")
    public Result all(HttpServletRequest request, HttpServletResponse response) {
        return Result.ok("操作成功", userService.queryAll());
    }

    @Path("/delete")
    public Result delete(HttpServletRequest request, HttpServletResponse response) {
        Long userId = stringCharsetConvert(request.getParameter("id"))
                .map(id -> Long.valueOf(id))
                .orElse(null);
        if (Objects.isNull(userId)) {
            return Result.error();
        }
        User user = new User();
        user.setId(userId);
        boolean deregister = userService.deregister(user);
        return deregister ? Result.ok() : Result.error();
    }

}
