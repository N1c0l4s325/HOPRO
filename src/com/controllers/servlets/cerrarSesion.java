package com.controllers.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class cerrarSesion
 */
@WebServlet("/cerrarSesion")
public class cerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession sesion = request.getSession();
		sesion.setAttribute("usuario", null);
		sesion.invalidate();
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		//request.getRequestDispatcher("/login.jsp").forward(request, response);
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
		
	}

}
