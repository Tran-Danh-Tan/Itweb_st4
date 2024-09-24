package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/forgotpass")
public class ForgotController extends HttpServlet{
	IUserService userService = new UserServiceImpl();
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/forgotpass.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		UserModel user = userService.FindByUserName(username);
		String alertMsg = "";
		if(user != null) {
		if (user.getEmail().equals(email) && user.getUserName().equals(username)) {
			alertMsg = "Mật khẩu của bạn là: ";
			req.setAttribute("alert", alertMsg + user.getPassWord());
			req.getRequestDispatcher("/views/forgotpass.jsp").forward(req, resp);
		}else{
			alertMsg = "Email của bạn không chính xác";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/forgotpass.jsp").forward(req, resp);
		}
		}else {
			alertMsg = "Tài khoản của bạn không chính xác";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/forgotpass.jsp").forward(req, resp);
		}
	}
}
