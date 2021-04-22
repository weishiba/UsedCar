package com.wsc.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author 18560
 */ //注解配置监听器
@WebListener
public class ApplicationListener implements ServletContextListener {

    /**
     * Default constructor.
     */
    public ApplicationListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 容器启动时调用
     *
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //获取容器对象
        ServletContext servletContext = sce.getServletContext();
        //获取系统应用路径
        String base = sce.getServletContext().getContextPath();
        //把应用路径放入容器对象
        servletContext.setAttribute("base", base);
        System.out.println("系统应用路径:"+ base);

    }


    /**
     * 容器关闭时调用
     *
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        //把应用路径从容器对象中移除
        servletContext.removeAttribute("base");
    }
}


