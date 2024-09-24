package vn.iotstar.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.models.UserModel;

@WebServlet(urlPatterns = "/profile")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,  // 10 MB
	    maxRequestSize = 1024 * 1024 * 50 // 50 MB
	)
public class ProfileController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

    public void init() {
        userDao = new UserDaoImpl();
    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  UserModel user = (UserModel) req.getSession().getAttribute("account");
	        if (user != null) {
	            user = userDao.findById(user.getId());
	            req.setAttribute("user", user);
	        }
	        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
            UserModel user = (UserModel) req.getSession().getAttribute("account");
            if (user == null) {
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }

            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            
            Part filePart = req.getPart("images");
            String fileName = null;
            if (filePart != null && filePart.getSize() > 0) {
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("") + "uploads";
                Files.createDirectories(Paths.get(uploadPath));
                String filePath = uploadPath + "/" + fileName;
                filePart.write(filePath);
            }
            user.setFullName(fullname);
            user.setEmail(email);
            user.setPhone(phone);
            if (fileName != null) {
                user.setImages("uploads/" + fileName);
            }
            int result = userDao.editProfile(user);
            if (result > 0) {
                req.getSession().setAttribute("account", user);
                req.setAttribute("message", "Profile updated successfully");
            } else {
                req.setAttribute("error", "Failed to update profile");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred while updating the profile");
        }
		resp.sendRedirect(req.getContextPath() + "/profile");
	}
}
