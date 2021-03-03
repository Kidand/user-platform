package cn.kidand.projects.user.web.controller;

import cn.kidand.projects.user.domain.User;
import cn.kidand.projects.user.service.UserService;
import cn.kidand.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/hello")
public class HelloWorldController implements PageController {
    private UserService userService;

    @GET
    @POST
    @Path("/world") // /hello/world -> HelloWorldController
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "index.jsp";
    }

    @GET
    @Path("/view/register")
    public String registerView(){
        return  "user/register.jsp";
    }



    @POST
    @Path("/register")
    public String register(User user , HttpServletRequest request){
        boolean register = userService.register(user);
        String view;
        if (register){
            //获取所以用户
            request.setAttribute("users" , userService.queryAll());
            view = "user/system.jsp";
        }else {
            view = "user/error.jsp";
        }
        return view;
    }
}
