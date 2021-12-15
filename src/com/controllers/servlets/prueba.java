package com.controllers.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.PersonsDao;
import DAOs.ReservaDao;
import config.CalcularDias;
import model.Persons;
import model.Reserva;



/**
 * Servlet implementation class prueba
 */
@WebServlet("/prueba")
public class prueba extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public prueba() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
PrintWriter out = response.getWriter();
	
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String ClienteSt = request.getParameter("documento");
		String idHabitacion = request.getParameter("habitacion");
 		try {
 			
			
						
			out.println("<html><body>");
					
			if  ( ClienteSt.isEmpty()||idHabitacion.isEmpty() ) {
						
				out.println("uno de los dos esta vacio");
			} else {
				out.println("Documento"+ClienteSt+"<br>");
				out.println("ID"+idHabitacion+"<br>");
			}		
 					
			out.println("</body></html>");
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
					
		

		
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
