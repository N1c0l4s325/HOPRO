package com.controllers.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class contollerAdmin
 */
@WebServlet("/contollerAdmin")
public class Administrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
 public Administrador() {
        
    }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String metodo = request.getParameter("");
		
		switch (metodo) {
		case "agregarHabitacion":
			agregarHabitaciones(request, response);
			break;

		default:
			break;
		}
			
	}

private void agregarHabitaciones(HttpServletRequest request, HttpServletResponse response) {
	try {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		String [] images = request.getParameterValues("files");
		 String file = null;
		for (int i = 0; i < images.length; i++) {
			file = images[i];
			File ruta = new File(file);
			out.println("" + ruta.getAbsolutePath());
		}
		
		out.println("</body>");
		out.println("/html");
		
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	 
		
	
}









}
