package com.book.servlet.auth;

import com.book.service.UesrService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

// 登录界面
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UesrService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        // 查看是否有登录失败标记
        if (req.getSession().getAttribute("login_failure") != null) {
            // 设置登录失败标记
            context.setVariable("failure", true);
            // 移除登录失败标记, 防止下次登录时再次出现
            req.getSession().removeAttribute("login_failure");
        }
        // 渲染登录页面
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("index");
        }else {
            ThymeleafUtil.process("login.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember-me");
        if (service.auth(username, password, req.getSession())) {
            resp.getWriter().write("登录成功");
        }else {
            // 添加登录失败标记
            req.getSession().setAttribute("login_failure", true);
            this.doGet(req, resp);
        }
    }
}
