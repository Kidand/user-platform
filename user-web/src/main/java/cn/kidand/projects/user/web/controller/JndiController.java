package cn.kidand.projects.user.web.controller;


import cn.kidand.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

@Path("/jndi")
public class JndiController implements PageController {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "jndi-test.jsp";
    }

}
