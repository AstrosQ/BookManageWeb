package com.book.filter;

import com.book.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// 主拦截器
@WebFilter("/*")
public class MainFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException, IOException {
        String url = request.getRequestURL().toString();  // 获取请求的URL
        if (!url.contains("/static/") && !url.contains("login")) {
            // 检查请求是否来自静态资源或登录页面
            // 检查用户是否登录
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                // 用户未登录，重定向到登录页面
                response.sendRedirect("login");
                return;
            }
        }
            chain.doFilter(request, response);
    }
}
