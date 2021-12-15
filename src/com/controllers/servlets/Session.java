package com.controllers.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOs.UsuarioDao;
import model.Usuarios;

/**
 * Servlet implementation class sesion
 */
@WebServlet("/Session")
public class Session extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Session() {
            
    }
	      
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
			
		processRequest(request, response);
						
}


	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		String accion = request.getParameter("accion");
		
		String usuario = request.getParameter("txt-user");
		String pass = request.getParameter("txt-pass");
		
		HttpSession sesion;		
		Usuarios usu = new Usuarios();
		UsuarioDao uDao = new UsuarioDao();
try {	
if (usuario.isEmpty() || pass.isEmpty()) {
	request.setAttribute("mje", "Por favor, complete los campos");
	request.getRequestDispatcher("/login.jsp").forward(request, response);	
			
						
} else {  if (accion != null && accion.equalsIgnoreCase("Ingresar") ) {
		
				usu.setUser(usuario);
				usu.setPassword(pass);
				usu = uDao.validar(usu);
		
		if (usu.getUser() != null && usu.getAcceso().getTipoAccess().equalsIgnoreCase("RECEPCIONISTA")) {
				sesion = request.getSession();
				sesion.setAttribute("usuario", usu);
				response.sendRedirect(request.getContextPath() + "/Recepcion?accion=inicio");
				//request.getRequestDispatcher("/empleado/recepcion/index.jsp").forward(request, response);	
				
		} else {
			
			request.setAttribute("mje", "Usuario o contraseña incorrectos");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
			}
		}	
}
	
	
	} catch(SQLException e) {
				
		 request.setAttribute("mensajeError", "ERROR SQL.... No se pudo procesar peticion");
		 request.getRequestDispatcher("/login.jsp").forward(request, response);	
		e.printStackTrace();
	}catch(Exception e) {
		 request.setAttribute("mensajeError", "ERROR Exception.... No se pudo procesar peticion");
		 request.getRequestDispatcher("/login.jsp").forward(request, response);	
		 e.printStackTrace();
	}
		
	}
}