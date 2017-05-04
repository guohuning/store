package web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用servlet
 */
public class BaseServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取子类
			Class clazz = this.getClass();
			// 获取请求的方法
			String m = request.getParameter("method");
			// 获取方法对象
			Method method = clazz.getMethod(m, HttpServletRequest.class, HttpServletResponse.class);
			// 执行方法，返回值为请求转发的路径
			String s = (String) method.invoke(this, request,response);
			// 判断返回值是否为空
			if (s != null) {
				request.getRequestDispatcher(s).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} 
	}

}
