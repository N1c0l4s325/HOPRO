package com.controllers.servlets;

import java.io.*;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.itextpdf.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import model.Habitaciones;

/**
 * Servlet implementation class facturaPdf
 */
@WebServlet("/facturaPdf")
public class facturaPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public facturaPdf() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/pdf");		
		//PrintWriter out = response.getWriter();		
		
		OutputStream outStream = response.getOutputStream();
		
		try {
			try {
				Document document = new Document();
				PdfWriter.getInstance(document, outStream);
				
				document.open();
				document.add("");
				
				
			} catch (Exception e) {
				e.getMessage();
			}
		} finally {
			outStream.close()
		}
				
	}
}
