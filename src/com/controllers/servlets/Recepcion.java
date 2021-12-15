package com.controllers.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOs.*;
import config.GenerarNumeroSerie;
import model.Consumos;
import model.Factura;
import model.Habitaciones;
import model.Persons;
import model.Productos;
import model.Reclamos;
import model.Reserva;
import model.TipoPersona;
import model.Usuarios;

/**
 * Servlet implementation class Recepcion
 */
@WebServlet(name = "Recepcion" , urlPatterns = {"/Recepcion"})
public class Recepcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	  Habitaciones hab = new Habitaciones(); 
	  Consumos consumo = new Consumos();
	  Reclamos reclamo = new Reclamos();
	  Persons persona = new Persons(); 
	  Reserva reserva = new Reserva();
	  TipoPersona tipoPer = new TipoPersona();
	  Productos producto = new Productos();
	  Factura factura = new Factura();
	  
	  ReservaDao reservaDao = new ReservaDao();
	  HabitacionesDao habitacionDao = new HabitacionesDao(); 
	  ConsumosDao consumoDao = new ConsumosDao(); 
	  ProductosDao productoDao = new ProductosDao(); 
	  PersonsDao personaDao = new PersonsDao();
	  ReclamosDao reclamoDao = new ReclamosDao(); 
	  FacturaDao facturaDao = new FacturaDao(); 
	  EmpleadoDao empleadoDao = new EmpleadoDao();
	  TipoPersonaDao tipoPerDao = new TipoPersonaDao();
	  
	 
	
		 
			  
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		  try {
					  
		  
		  
		  String accion = request.getParameter("accion");		
		  
		  switch (accion) {
		  
		  case "cerrarSesion":
				
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuario", null);
				sesion.invalidate();
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			  
				break;
				
		  case "factura":
				
				Facturacion(request, response);
			  
				break;
				
		  case "liberarHabitacion":
			  liberarHabitacion(request, response);
				break;
		  
		  case "Listar-habitaciones":
				MostrarHabitacion(request, response);
				break;
				
		  case "mostrar-reservas":
				mostrarReservas(request, response);
				break;
				
		  case "mostrarCliente":
			  	mostrarCliente(request, response);
			  	break;
			
		  case "mostrar-consumos":
				mostrarConsumo(request, response);
				break;
				
		  case "mostrar-reclamos":
				mostrarReclamos(request, response);
				break;		
				
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		  case "Agregar cliente":
			  	agregarCliente(request, response);  
			  	break;
			  	
		  case "Realizar reserva":
				realizarReservas(request, response);
				break;
			
			case "Agregar producto":
				agregarConsumo(request, response);
				break;
			 	
		  
		  case "Guardar reclamo":
				agregarReclamos(request, response);
				break;		
				
		////////////////////////////////////////////////////////////////////////
				
		  case "eliminarCliente":
			  	eliminarCliente(request, response);
			  	break;
			  	
		  case "eliminar_reserva":
				eliminarReservas(request, response);
				break;
				
		  case "eliminar_consumo":
				eliminarConsumo(request, response);
				break;

		  case "eliminar-reclamos":
				eliminarReclamos(request, response);
				break;	
				
		  /////////////////////////////////////////////////////////////////////////
				
				
		  case "Actualizar cliente":
			  	actualizarCliente(request, response);
			  	break;
			  	
		  case "Actualizar reserva":
				actualizarReservas(request, response);
				break;
					
		  case "Actualizar reclamo":
				modificarReclamos(request, response);
				break;
		  /////////////////////////////////////////////////////////////////////
				

			case "editar_reserva":
				editarReservas(request, response);
				break;
				
		   case "editarCliente":
			  	editarCliente(request, response);
			  	break; 				

			case "editar-reclamos":
				editarReclamos(request, response);
				break;
		//////////////////////////////////////////////////////////////
			
			case "configuraciones":
				 configuraciones(request, response);	
				break;
		  	case "confirmar-reserva":
				confirmarReservas(request, response);
				break;				

				case "Imprimir-factura":
				imprimirFactura(request, response);
				break;
			
			case "inicio":
				request.getRequestDispatcher("/empleado/recepcion/index.jsp").forward(request, response);
				break;
			}
					
			
	  } catch (Exception e) {
			System.out.println("ERROR DE PETICION" + e.getMessage());
			e.printStackTrace();
		}
	  }	  

	  
	private void Facturacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numeroSerie;
		FacturaDao facturaDao = new FacturaDao();
		String habitacion = request.getParameter("habitacion");
		String operacion = request.getParameter("operacion");
		String documento = request.getParameter("documento");
		int idReserva = 0;
		//String importeReserva = request.getParameter("importeReserva");
		double totalImporte = 00000.00;
		try {
			numeroSerie = facturaDao.GenerarNumero();
			if (numeroSerie == null) {
				numeroSerie = "00000001";
			}else {
			int	incrementar = Integer.valueOf(numeroSerie);
			GenerarNumeroSerie Ns = new GenerarNumeroSerie();
			numeroSerie = Ns.generarNumero(incrementar);
			}
			switch (operacion) {
			case "inicio":
				List<Habitaciones> listaHabitacion = habitacionDao.getSelect();
				request.setAttribute("habitacion", listaHabitacion);
				request.getRequestDispatcher("/empleado/recepcion/facturacion.jsp").forward(request, response);
				break;
			case "Cliente":
						if (documento.isEmpty() || habitacion.isEmpty()) {
							
							request.setAttribute("mensajeCliente", "Por favor, ingrese numero de documento o habitacion del cliente");
							request.getRequestDispatcher("Recepcion?accion=factura&operacion=inicio").forward(request, response);
						}else {
							String fechaEmision = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
							String tipo = "cliente";
							//String estado = "pagada";							
							persona = personaDao.buscarCliente(tipo, documento);							
							List<Reserva> listaReserva = reservaDao.paraConsumoYfactura(Integer.valueOf(documento),Integer.valueOf(habitacion));
							for (Reserva r : listaReserva) {
								totalImporte = totalImporte + r.getImporte();
								idReserva = r.getId();
							}
							request.setAttribute("idHabitacion", habitacion);
							request.setAttribute("idReserva", idReserva );
							request.setAttribute("fechaEmision", fechaEmision );
							request.setAttribute("NumeroComprobante", numeroSerie);	
							request.setAttribute("cantidad", "1" );
							request.setAttribute("reserva", listaReserva);
							request.setAttribute("idHabitacion", habitacion);
							request.setAttribute("cl", persona);
							request.setAttribute("importeTotal", totalImporte );
							request.getRequestDispatcher("Recepcion?accion=factura&operacion=inicio").forward(request, response);
						}
				
				break;
			case "consumo":
				if (documento.isEmpty()) {
					List<Habitaciones> listaHabitacion2 = habitacionDao.getSelect();
					request.setAttribute("habitacion", listaHabitacion2);
					request.setAttribute("mensajeConsumo", "Por favor, primero ingrese el documento del cliente");
					request.getRequestDispatcher("/empleado/recepcion/facturacion.jsp").forward(request, response);
					
				} else {
					String tipo = "cliente";						
					String idReserva2 = request.getParameter("idReserva");
					double importeConsumo = Double.parseDouble(request.getParameter("importe"));
					List<Consumos> listaConsumos = consumoDao.getSelect(Integer.valueOf(idReserva2));
					String fechaEmision = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
					persona = personaDao.buscarCliente(tipo, documento);							
					List<Reserva> listaReserva = reservaDao.paraConsumoYfactura(Integer.valueOf(documento),Integer.valueOf(habitacion));
					for (Consumos con : listaConsumos) {
						importeConsumo = importeConsumo + con.getSaldo(); 
					}
					request.setAttribute("consumos", listaConsumos );
					request.setAttribute("idReserva", idReserva2 );
					request.setAttribute("fechaEmision", fechaEmision );
					request.setAttribute("NumeroComprobante", numeroSerie);	
					request.setAttribute("cantidad", "1" );
					request.setAttribute("reserva", listaReserva);
					request.setAttribute("idHabitacion", habitacion);
					request.setAttribute("cl", persona);
					request.setAttribute("importeTotal", importeConsumo );
					request.getRequestDispatcher("Recepcion?accion=factura&operacion=inicio").forward(request, response);
				}
				
				break;
				
			case "Guardar factura":
				String idReservaFactura = request.getParameter("idReserva");
				String numComprobante = request.getParameter("numeroComprobate");
				String fechaEmision = request.getParameter("fechaEmision");
				String formaPago = request.getParameter("formaDePago");
				String estado = "Pagada";
				String estadoReserva = "Pagada";
				String estadoHabitacion="En limpieza";
				String idHabitacion = request.getParameter("idHabitacion");
				
				
				Factura fac = new Factura();
				FacturaDao fDao = new FacturaDao();
				
				fac.setID_reserva(Integer.valueOf(idReservaFactura));
				fac.setNumComprobante(numComprobante);
				fac.setFechaEmision(fechaEmision);
				fac.setFormaPago(formaPago);
				fac.setEstado(estado);
				
				fDao.getInsert(fac);
				
				reservaDao.actualizarEstado(estadoReserva, idReservaFactura);
				habitacionDao.cambiarEstadoHabitacion(estadoHabitacion,Integer.valueOf(idHabitacion));
				
				request.setAttribute("mensajeOperacion", "Factura guardada");
				request.getRequestDispatcher("Recepcion?accion=factura&operacion=inicio").forward(request, response);
				break;
			}
		}  catch (SQLException e) {
			request.setAttribute("mensajeError", "Error SQL... No se pudo procesar peticion");
			request.getRequestDispatcher("Recepcion?accion=factura&operacion=inicio").forward(request, response);
			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("mensajeError", "Error de Exception... no se pudo procesar peticion");
			request.getRequestDispatcher("Recepcion?accion=factura&operacion=inicio").forward(request, response);
			e.printStackTrace();
		}	
		
	}

	
	
private void configuraciones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int idUser = Integer.valueOf(request.getParameter("idUsu")); 
		String user = request.getParameter("usuarioNuevo");
		String cont1 = request.getParameter("cont1");
		String cont2 = request.getParameter("cont2");		
		String operacion = request.getParameter("operacion");
		
				
	try {
		Usuarios u = new Usuarios();
		UsuarioDao uDao = new UsuarioDao();
		u.setID(idUser);
		u = uDao.validar(u);
		
		switch (operacion) {
		case "mostrar":		
				request.setAttribute("u", u);
				request.getRequestDispatcher("/empleado/recepcion/configuraciones.jsp").forward(request, response);
			
			
			break;
			case "Actualizar usuario":
				if (user.isEmpty() || user == null) {
					request.setAttribute("u", u);
					request.setAttribute("mensajeError", "Por favor, ingrese un usuario");
					request.getRequestDispatcher("/empleado/recepcion/configuraciones.jsp").forward(request, response);
				} else {
					if (cont1 != null && cont1.equals(cont2)) {
						u.setUser(user);
						u.setPassword(cont1);
						u.setID(idUser);
						uDao.getUpdate(u);
						request.getRequestDispatcher("Recepcion?accion=cerrarSesion").forward(request, response);	
						
					} else {
						request.setAttribute("u", u);
						request.setAttribute("mensajeError", "Las contraseñas no coinciden");
						request.getRequestDispatcher("/empleado/recepcion/configuraciones.jsp").forward(request, response);
					}
				}
					
			break;
		
		}
		} catch (SQLException e) {
			
			request.setAttribute("mensajeError", "Error SQL no se pudo procesar peticion");
			request.getRequestDispatcher("/empleado/recepcion/configuraciones.jsp").forward(request, response);
			e.printStackTrace();
		} catch (Exception e) {
			
			request.setAttribute("mensajeError", "Error SQL no se pudo procesar peticion");
			request.getRequestDispatcher("/empleado/recepcion/configuraciones.jsp").forward(request, response);
			e.printStackTrace();
		}			

}

private void liberarHabitacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int idHabitacion = Integer.valueOf(request.getParameter("idHabitacion"));
		String estado = "Libre";
		
		try {
			habitacionDao.cambiarEstadoHabitacion(estado, idHabitacion);
			
			request.setAttribute("mensajeOperacion", "Operacion exitosa");
			request.getRequestDispatcher("/empleado/recepcion/rooms.jsp").forward(request, response);
						
		} catch (SQLException e) {
			request.setAttribute("mensajeError", "Error SQL no se pudo procesar peticion");
			request.getRequestDispatcher("Recepcion?Listar-habitaciones&operacion=inicio").forward(request, response);
			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("mensajeError", "Error SQL no se pudo procesar peticion");
			request.getRequestDispatcher("Recepcion?Listar-habitaciones&operacion=inicio").forward(request, response);
			e.printStackTrace();
		}
		
		
		
	}





private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int idCliente = Integer.valueOf(request.getParameter("idCliente"));
			persona.setID(idCliente);
			personaDao.getDelete(persona);
			String mensaje = "Operacion de borrado exitosa";
			request.setAttribute("mensajeOperacion", mensaje);
			request.getRequestDispatcher("Recepcion?accion=mostrarCliente").forward(request, response);
		}catch (SQLException e) {
				
				String mensaje = "Error SQL: no se pudo procesar peticion";
				request.setAttribute("mensajeError", mensaje);
				request.getRequestDispatcher("Recepcion?accion=mostrarCliente").forward(request, response);
				e.printStackTrace();
				
				
			}catch (Exception e) {
				String mensaje = "Error: no se pudo procesar peticion";
				request.setAttribute("mensajeError", mensaje);				
				request.getRequestDispatcher("Recepcion?accion=mostrarCliente").forward(request, response);
				e.printStackTrace();
			}
			
		
	}







private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String documento = request.getParameter("Iden"); 
	String TipoDoc = request.getParameter("tipoIde");
	String nombre = request.getParameter("nombre");
	String apellido = request.getParameter("apellido"); 
	String direccion = request.getParameter("direccion");
	String telefono = request.getParameter("telefono");
	String email = request.getParameter("email");
	String pais = request.getParameter("pais");
	String idPersona = request.getParameter("idPersona");
	
	try {
		persona.setCountry(pais);
		persona.setTypeIdentification(TipoDoc);
		persona.setIdentification(Integer.valueOf(documento));
		persona.setSurName(apellido);
		persona.setName(nombre);
		persona.setAddress(direccion);
		persona.setPhone(telefono);
		persona.setEmail(email);
		persona.setID(Integer.valueOf(idPersona));
		
		personaDao.getUpdate(persona);
		request.setAttribute("mensajeOperacion", "Cliente ha sido actualizado");
		request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=buscarCliente&filter=" + documento).forward(request, response);
		
	} catch (SQLException e) {
		
		String mensaje = "Error SQL: no se pudo procesar peticion";
		request.setAttribute("mensajeError", mensaje);
		request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
		e.printStackTrace();
		
		
	}catch (Exception e) {
		String mensaje = "Error: no se pudo procesar peticion";
		request.setAttribute("mensajeError", mensaje);				
		request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
		e.printStackTrace();
	}
	
		
	}







private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String idCliente = request.getParameter("idCliente");
	
	try {
		Persons persona = personaDao.getSelect(Integer.valueOf(idCliente));
		request.setAttribute("cl", persona);
		request.getRequestDispatcher("/empleado/recepcion/customers.jsp").forward(request, response);
		
	}  catch (SQLException e) {
		
		String mensaje = "Error SQL: no se pudo procesar peticion";
		request.setAttribute("mensajeError", mensaje);
		request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
		e.printStackTrace();
		
		
	}catch (Exception e) {
		String mensaje = "Error: no se pudo procesar peticion";
		request.setAttribute("mensajeError", mensaje);				
		request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
		e.printStackTrace();
	}
	

}


private void mostrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
	String cliente = request.getParameter("filter");
	String operacion = request.getParameter("operacion");
	String dato = "cliente";
	List <Persons> personaList = null;
	try {
			switch (operacion) {
			case "buscarCliente":
				
				if (cliente.isEmpty()) {
					request.setAttribute("mensajeError", "Por favor, ingrese una identificacion para buscar cliente");
					request.getRequestDispatcher("/empleado/recepcion/customers.jsp").forward(request, response);
				} else {
					personaList = personaDao.getSelect(dato, cliente);	
					request.setAttribute("cliente", personaList );
					request.getRequestDispatcher("/empleado/recepcion/customers.jsp").forward(request, response);
				}
				
				
				break;

			case "inicio":
				
				personaList = personaDao.getSelect(dato);	
				request.setAttribute("cliente", personaList );
				request.getRequestDispatcher("/empleado/recepcion/customers.jsp").forward(request, response);
				break;
			}
		
			
					
		
		
	} catch (SQLException e) {
		
		String mensaje = "Error SQL: no se pudo procesar peticion";
		request.setAttribute("mensajeError", mensaje);
		request.getRequestDispatcher("/empleado/recepcion/customers.jsp").forward(request, response);
		e.printStackTrace();
		
		
	}catch (Exception e) {
		String mensaje = "Error: no se pudo procesar peticion";
		request.setAttribute("mensajeError", mensaje);				
		request.getRequestDispatcher("/empleado/recepcion/customers.jsp").forward(request, response);
		e.printStackTrace();
	}
	
	
	
}


private void agregarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			
	String documento = request.getParameter("Iden"); 
	String TipoDoc = request.getParameter("tipoIde");
	String nombre = request.getParameter("nombre");
	String apellido = request.getParameter("apellido"); 
	String direccion = request.getParameter("direccion");
	String telefono = request.getParameter("telefono");
	String email = request.getParameter("email");
	String pais = request.getParameter("pais");
	String tipo = "cliente";	
	StringBuilder campoVacio = new StringBuilder();
			
	try {		
			if (documento.length() == 0 ) {				
				campoVacio.append("Ingrese un numero de identificacion"); 
				request.setAttribute("mje", campoVacio);
				request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
				
			}else if (documento.length() != 0 && TipoDoc.length() == 0) {				
				campoVacio.append("Ingrese un tipo de identificacion");
				request.setAttribute("mje", campoVacio);
				request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
				
			}else if (documento.length() != 0 && TipoDoc.length() != 0 && nombre.length() == 0) {
								
				campoVacio.append("Ingrese un nombre de cliente");
				request.setAttribute("mje", campoVacio);
				request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
			}else if (documento.length() != 0 && TipoDoc.length() != 0 && nombre.length() != 0 && apellido.length() == 0) {				
				campoVacio.append( "Ingrese un apellido"); 
				request.setAttribute("mje", campoVacio);
				request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
				
			}else if (documento.length() != 0 && TipoDoc.length() != 0 && nombre.length() != 0 && apellido.length() != 0 && email.length() == 0) {				
				campoVacio.append("Ingrese un email"); 
				request.setAttribute("mje", campoVacio);
				request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
			}else if (documento.length() != 0 && TipoDoc.length() != 0 && nombre.length() != 0 && apellido.length() != 0 && email.length() != 0 && pais.length() == 0) {				
				campoVacio.append("Ingrese el pais de origen"); 
				request.setAttribute("mje", campoVacio);
				request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
			}else {				
					int documentoInt = 0;
					documentoInt = Integer.valueOf(documento);
									
					Persons validar = personaDao.validarCliente(documentoInt, tipo);
					
					if (documentoInt != validar.getIdentification() ) {
						int idTipo = tipoPerDao.getTipoPersona(tipo); 
						persona.setIdentification(documentoInt);
						persona.setTypeIdentification(TipoDoc);
						persona.setName(nombre);
						persona.setSurName(apellido);
						persona.setAddress(direccion);
						persona.setPhone(telefono);
						persona.setEmail(email);
						persona.setCountry(pais);				
						persona.setId_tipo(idTipo);
						
						personaDao.getInsert(persona);
																
						request.setAttribute("mensajeOperacion","Cliente guardado");
						request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
						
					}else {						
						
						request.setAttribute("mensajeError", "El cliente que intenta agregar con este documento ya existe");
						request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
					}
					
			}		
				
				
			} catch (SQLException e) {
				
				String mensaje = "Error SQL: no se pudo procesar peticion";
				request.setAttribute("mensajeError", mensaje);
				request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
				e.printStackTrace();
				
				
			}catch (Exception e) {
				String mensaje = "Error: no se pudo procesar peticion";
				request.setAttribute("mensajeError", mensaje);				
				request.getRequestDispatcher("Recepcion?accion=mostrarCliente&operacion=inicio").forward(request, response);
				e.printStackTrace();
			}
			
			
			
	
	
	
		
	}



private void imprimirFactura(HttpServletRequest request, HttpServletResponse response) {
		
		
	}


private void modificarReclamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  int idEmpleado = Integer.valueOf(request.getParameter("idEmpleado"));
	  String descripcion = request.getParameter("detalle");
	  int idReclamo = Integer.valueOf(request.getParameter("idReclamo"));
	  String documento = request.getParameter("documento");

	try {
	 	  
	  reclamo.setID_empleado(idEmpleado); 
	  reclamo.setDescripcion(descripcion);
	  reclamo.setID(idReclamo);
	  reclamoDao.getUpdate(reclamo);
	  
	  request.setAttribute("idEmpleado", idEmpleado);
		request.setAttribute("mensajeOperacion", "Reclamo actualizado, a la espera de resolucion");
		request.getRequestDispatcher("Recepcion?accion=mostrar-reclamos&operacion=buscarCliente&documento="+documento).forward(request, response);
		
		
	} catch (SQLException e) {
		request.setAttribute("idEmpleado", idEmpleado);
		request.setAttribute("mensajeError", "Error SQL... No se pudo procesar peticion");
		request.getRequestDispatcher("Recepcion?accion=mostrar-reclamos&operacion=inicio").forward(request, response);
		e.printStackTrace();
		
	} catch (Exception e) {
		
		request.setAttribute("idEmpleado", idEmpleado);
		request.setAttribute("mensajeError", "Error, No se pudo procesar peticion");
		request.getRequestDispatcher("Recepcion?accion=mostrar-reclamos&operacion=inicio").forward(request, response);
		e.printStackTrace();
		

	}
	 
}

private void editarReclamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int idReclamo = Integer.valueOf(request.getParameter("IdReclamo"));
			String idEmpleado = request.getParameter("idEmpleado");
					
			
try {
			  
				 Reclamos reclamo = reclamoDao.getSelect(idReclamo);
			  	 request.setAttribute("idEmpleado", idEmpleado);
				 request.setAttribute("detalleReclamo", reclamo);
				 request.getRequestDispatcher("/empleado/recepcion/claim.jsp").forward(request, response);
				 
			  
					}catch(SQLException e) {
						request.setAttribute("mensajeError", "Error SQL no se pudo procesar peticion");						
						request.getRequestDispatcher("/empleado/recepcion/claim.jsp").forward(request, response);
						e.printStackTrace();
					} catch (Exception e) {
						request.setAttribute("mensajeError", "Error SQL no se pudo procesar peticion");
						request.getRequestDispatcher("/empleado/recepcion/claim.jsp").forward(request, response);
						e.printStackTrace();
						
					}
			 
}



private void eliminarReclamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idEmpleado = request.getParameter("idEmpleado");
			int idReclamo = Integer.valueOf(request.getParameter("idReclamo"));
			reclamoDao.getDelete(idReclamo);
			request.setAttribute("idEmpleado", idEmpleado);
			request.setAttribute("mensajeOperacion",  "Reclamo eliminado satisfactoriamente");
			request.getRequestDispatcher("Recepcion?accion=mostrar-reclamos&operacion=inicio").forward(request, response);
		   	
		} catch(SQLException e) {
			String mensaje = " No se pudo procesar peticion, error SQLException... ";
			String idEmpleado = request.getParameter("idEmpleado");
			e.printStackTrace();
			request.setAttribute("idEmpleado", idEmpleado);
			request.setAttribute("mensajeError", mensaje);
			request.getRequestDispatcher("Recepcion?accion=mostrar-reclamos&operacion=inicio").forward(request, response);
		} catch (Exception e) {
			String mensaje = "Error exception desconocido...";
			String idEmpleado = request.getParameter("idEmpleado");
			e.printStackTrace();
			request.setAttribute("idEmpleado", idEmpleado);
			request.setAttribute("mensajeError", mensaje);
			request.getRequestDispatcher("Recepcion?accion=mostrar-reclamos&operacion=inicio").forward(request, response);
			
		}		
		
	
		
	}





private void agregarReclamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			int idEmpleado = Integer.valueOf(request.getParameter("idEmpleado"));
			int idReserva = Integer.valueOf(request.getParameter("idReserva"));
			String documento = request.getParameter("documento");
			String detalleReclamo =  request.getParameter("detalle");
			String fechaIngreso = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			String estado = "Espera de resolucion";
					
		try {
			
			reclamo.setID_empleado(idEmpleado);
			reclamo.setID_reserva(idReserva);
			reclamo.setDescripcion(detalleReclamo);
			reclamo.setFechaIngreso(fechaIngreso);
			reclamo.setEstado(estado);
			
			reclamoDao.getInsert(reclamo);
			
			request.setAttribute("idEmpleado", idEmpleado);
			request.setAttribute("mensajeOperacion", "Reclamo guardado, a la espera de resolucion");
			request.getRequestDispatcher("Recepcion?accion=mostrar-reclamos&operacion=buscarCliente&cliente="+documento).forward(request, response);
			
			
		} catch (SQLException e) {
			request.setAttribute("idEmpleado", idEmpleado);
			request.setAttribute("mensajeError", "Error SQL... No se pudo procesar peticion");
			request.getRequestDispatcher("empleado/recepcion/claim.jsp").forward(request, response);
			e.printStackTrace();
			
		} catch (Exception e) {
			
			request.setAttribute("idEmpleado", idEmpleado);
			request.setAttribute("mensajeError", "Error, No se pudo procesar peticion");
			request.getRequestDispatcher("empleado/recepcion/claim.jsp").forward(request, response);
			e.printStackTrace();
			

		}
		
	}

private void mostrarReclamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

		String documentoSt = request.getParameter("cliente");;
		String idEmpleado = request.getParameter("idEmpleado");
		List<Reserva> listReserva = null;
		List<Reclamos> listReclamo = null; 
		String operacion = request.getParameter("operacion");
		int totalRegistros = 0;	
		try {
			
				switch (operacion) {
				case "inicio":
				request.setAttribute("idEmpleado", idEmpleado);	
				request.getRequestDispatcher("/empleado/recepcion/claim.jsp").forward(request, response);	
					break;
				case "buscarCliente":					
					if (documentoSt.isEmpty()) {
						request.setAttribute("mensajeError", "Por favor, ingrese identificacion del huesped");
						request.setAttribute("idEmpleado", idEmpleado);
						request.getRequestDispatcher("/empleado/recepcion/claim.jsp").forward(request, response);	
					}else {
						String estadoReserva = "pagada";
						listReserva = reservaDao.infoParaReclamoyFactura(documentoSt, estadoReserva);
						for (Reserva r : listReserva) {
							if (r.getFechaIngreso() == null) {
								
								request.setAttribute("mensajeError", "El huesped no tiene registrado un ingreso al hotel");
								request.setAttribute("idEmpleado", idEmpleado);
								request.getRequestDispatcher("/empleado/recepcion/claim.jsp").forward(request, response);								
								
							} else {
								
								String tipo = "cliente";
								persona = personaDao.buscarCliente(tipo, documentoSt);
								reserva.setId(r.getId());
								listReclamo = reclamoDao.getSelect(reserva);
								for (int i = 0; i < listReclamo.size(); i++) {
									totalRegistros = totalRegistros + 1;
								}
								request.setAttribute("cl", persona);
								request.setAttribute("reclamo",listReclamo);
								request.setAttribute("reserva", listReserva );
								request.setAttribute("totalRegistros", totalRegistros );
								request.setAttribute("idEmpleado", idEmpleado);
								request.getRequestDispatcher("/empleado/recepcion/claim.jsp").forward(request, response);							
								
							}
						}						
						
					}					
						break;
				
				}
				
			
		} catch (SQLException e) {
			request.setAttribute("idEmpleado", idEmpleado);
			request.setAttribute("mensajeError", "Error SQL ... no se pudo procesar peticion");		
			request.getRequestDispatcher("/empleado/recepcion/claim.jsp").forward(request, response);	
			e.printStackTrace();

		} catch (Exception e) {
			request.setAttribute("idEmpleado", idEmpleado);
			request.setAttribute("mensajeError", "Error ... no se pudo procesar peticion");		
			request.getRequestDispatcher("/empleado/recepcion/claim.jsp").forward(request, response);	
			e.printStackTrace();
		}		
					
		
}


private void eliminarConsumo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("ID_consumo"));
		String documento = request.getParameter("documento");
	try {
			
			consumoDao.getDelete(id);			
			request.setAttribute("mensajeOperacion", "Producto eliminado");		
			request.getRequestDispatcher("Recepcion?accion=mostrar-consumos&operacion=buscarCliente&documento="+documento).forward(request, response);
		
		
	} catch (SQLException e) {
		request.setAttribute("mensajeError", "Error SQL... no se pudo procesar peticion");		
		request.getRequestDispatcher("Recepcion?accion=mostrar-consumos&operacion=buscarCliente&documento="+documento).forward(request, response);
		e.printStackTrace();
		
	} catch (Exception e) {
		request.setAttribute("mensajeError", "Error ... no se pudo procesar peticion");		
		request.getRequestDispatcher("Recepcion?accion=mostrar-consumos&operacion=buscarCliente&documento="+documento).forward(request, response);
		e.printStackTrace();

	}
			
}

/*------------------------------------------------------------------------------------------------------------------------------------*/



private void agregarConsumo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				int idProd = Integer.parseInt(request.getParameter("idProducto"));
				String documento = request.getParameter("documento");
				int idReserva = Integer.parseInt(request.getParameter("idReserva"));
				String fechaPedido = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
				String cantidadSt = request.getParameter("cantidad");
				String estado = request.getParameter("estado");
				String habitacion = request.getParameter("habitacion");
				double saldo = 00000.00;
				int stockFinal = 0;
			   
				
	try {
		
		reserva = reservaDao.ExtraerIdParaConsumo(idReserva);
		producto = productoDao.buscarProducto(request.getParameter("idProducto"));
		

		if (reserva.getId() == 0) {
			//out.println("El huesped no tiene una reservacion resgistrada");
			request.setAttribute("mensajeError", "El huesped no tiene una reservacion resgistrada");
			request.getRequestDispatcher(
					"Recepcion?accion=mostrar-consumos&operacion=buscarCliente&documento=" + documento+"&habitacion="+habitacion)
					.forward(request, response);
		} else {
							
				if (reserva.getFechaIngreso() == null) {
					/*out.println("El huesped no tiene un ingreso resgistrado");
					out.println("Documento: "+documento+" ID habitacion: "+habitacion);*/
					request.setAttribute("mensajeError", "El huesped no tiene un ingreso resgistrado");
					request.getRequestDispatcher("Recepcion?accion=mostrar-consumos&operacion=buscarCliente&documento="+ documento + "&habitacion=" + habitacion).forward(request, response);

				} else {
					
					if (producto.getStock() < 0) {
						//out.println("Lo siento, no hay stock de este producto");
						request.setAttribute("mensajeError", "Lo siento, no hay stock de este producto");
						request.getRequestDispatcher("Recepcion?accion=mostrar-consumos&operacion=buscarCliente&documento=" + documento+ "&habitacion=" + habitacion)
								.forward(request, response);
					} else {
						//out.println("Consumo guardado");
						
						stockFinal = producto.getStock() - Integer.valueOf(cantidadSt);
						int cantidad = Integer.valueOf(cantidadSt);
						saldo = productoDao.precioVenta(idProd) * cantidad;					
						if (estado.isEmpty() || estado == null) {
							estado = "falta pagar";
						}
						consumo.setID_producto(idProd);
						consumo.setID_reserva(idReserva);
						consumo.setFechaHoraPedido(fechaPedido);
						consumo.setCantidad(cantidad);
						consumo.setSaldo(saldo);
						consumo.setEstado(estado);

						consumoDao.getInsert(consumo);
						productoDao.disminuirStock(stockFinal, idProd);
						request.setAttribute("mensajeOperacion", "Producto agregado");
						request.getRequestDispatcher(
								"Recepcion?accion=mostrar-consumos&operacion=buscarCliente&documento=" + documento+ "&habitacion=" + habitacion)
								.forward(request, response);
					}
				}
			}
		
			
			
			
			
			
			
			
			
							
		
	} catch (SQLException e) {
		request.setAttribute("mensajeError", "Error SQL... no se pudo procesar peticion");		
		request.getRequestDispatcher("Recepcion?accion=mostrar-consumos").forward(request, response);
		e.printStackTrace();
		
	} catch (Exception e) {
		request.setAttribute("mensajeError", "Error ... no se pudo procesar peticion");		
		request.getRequestDispatcher("Recepcion?accion=mostrar-consumos").forward(request, response);
		e.printStackTrace();

	}
}



/*-----------------------------------------------------------------------------------------------------------------------*/

private void mostrarConsumo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	String ClienteSt = null;
	ClienteSt = request.getParameter("documento");	
	String idProducto = request.getParameter("idProducto");
	String nombrePro = request.getParameter("producto");
	String operacion = request.getParameter("operacion");
	String habitacion = request.getParameter("habitacion");
	String tipo = "cliente";
	
	List<Consumos> consumo = null;
	List<Productos> listaProductos = null;
	List<Reserva> listaReserva= null;	
	int idReserva = 0;
	

	
	double totalConsumo = 0.0;
	
try {	
	 	listaProductos = productoDao.getSelect();
		
		switch (operacion) {
		
			case "buscarCliente":
				
					if  ( ClienteSt.isEmpty() || habitacion.isEmpty()) {
					request.setAttribute("mensajeError", "Ingrese la identificacion o la habitacion del huesped");
					request.setAttribute("productos", listaProductos);
					request.getRequestDispatcher("/empleado/recepcion/sell.jsp").forward(request, response);
										
				} else {					    
					    listaReserva = reservaDao.paraConsumoYfactura(Integer.valueOf(ClienteSt), Integer.valueOf(habitacion));
					    
						if (listaReserva == null ) {							
							
							request.setAttribute("productos", listaProductos);
							request.setAttribute("mensajeError", "El huesped no tiene una reservacion realizada");
							request.getRequestDispatcher("/empleado/recepcion/sell.jsp").forward(request, response);
							
						} else {		for (Reserva r : listaReserva) {
													idReserva = r.getId();
											}
										
										List<Consumos> listaConsumos = consumoDao.getSelect(idReserva);
										for (Consumos cons : listaConsumos) {
											totalConsumo = totalConsumo + cons.getSaldo(); 
										}
										List<Habitaciones> listHabitacion = habitacionDao.getSelect();
										persona = personaDao.buscarCliente(tipo, ClienteSt);
										hab = habitacionDao.getSelectId(Integer.valueOf(habitacion)); 
										request.setAttribute("productos", listaProductos);
										request.setAttribute("consumo", listaConsumos);
										request.setAttribute("habitacion", listHabitacion);
										request.setAttribute("hab", hab);
										request.setAttribute("c", persona);
										request.setAttribute("idReserva", idReserva);
										request.setAttribute("total", totalConsumo);
										request.getRequestDispatcher("/empleado/recepcion/sell.jsp").forward(request, response);									
									}									
				}
							
			break;
			
			case "seleccionarProducto":
				
				List<Reserva> listaReserva2 = reservaDao.paraConsumoYfactura(Integer.valueOf(ClienteSt), Integer.valueOf(habitacion));
				for (Reserva r : listaReserva2) {
					idReserva = r.getId();
				}
				List<Habitaciones> habitaciones = habitacionDao.habitacionesPorCliente(Integer.valueOf(ClienteSt));
				hab = habitacionDao.getSelectId(Integer.valueOf(habitacion));
				producto = productoDao.buscarProducto(idProducto);
				persona = personaDao.buscarCliente(tipo, ClienteSt);
				consumo = consumoDao.getSelect(idReserva);					
			
				for (Consumos con : consumo) {
					totalConsumo = totalConsumo + con.getSaldo();
				}
				request.setAttribute("idReserva", idReserva);
				request.setAttribute("habitacion", habitaciones);
				request.setAttribute("hab", hab);
				request.setAttribute("p", producto);
				request.setAttribute("consumo", consumo);
				request.setAttribute("total", totalConsumo);
				request.setAttribute("productos", listaProductos);
				request.setAttribute("c", persona);
				request.getRequestDispatcher("/empleado/recepcion/sell.jsp").forward(request, response);
			break;
			
			case "listarProducto":
				if (ClienteSt.isEmpty()) {
					listaProductos =  productoDao.getSelect(nombrePro);
					request.setAttribute("productos", listaProductos);
					request.getRequestDispatcher("/empleado/recepcion/sell.jsp").forward(request, response);
				} else {
					
					List<Habitaciones> habitaciones2 = habitacionDao.habitacionesPorCliente(Integer.valueOf(ClienteSt));
					listaProductos =  productoDao.getSelect(nombrePro);				
					persona.setIdentification(Integer.valueOf(ClienteSt));
					
					idReserva = reservaDao.getSelect_IdReserva(persona);
					
					consumo = consumoDao.getSelect(idReserva);			
					for (Consumos con : consumo) {
						totalConsumo = totalConsumo + con.getSaldo();
					}
					
					request.setAttribute("habitacion", habitaciones2);
					request.setAttribute("p", producto);
					request.setAttribute("consumo", consumo);
					request.setAttribute("total", totalConsumo);
					request.setAttribute("productos", listaProductos);
					request.setAttribute("c", persona);
					request.getRequestDispatcher("/empleado/recepcion/sell.jsp").forward(request, response);
				}
				
			break;
			
			case "inicio":
			List<Habitaciones> listaHabitaciones = habitacionDao.getSelect();
			request.setAttribute("habitacion", listaHabitaciones);
			request.setAttribute("productos", listaProductos);
			request.getRequestDispatcher("/empleado/recepcion/sell.jsp").forward(request, response);
			break;
			
			
		}
										
				
	} catch (SQLException e) {
		
		request.setAttribute("producto", producto);
		request.setAttribute("mensajeError", "Error SQL... no se pudo procesar peticion");		
		request.getRequestDispatcher("/empleado/recepcion/sell.jsp").forward(request, response);
		e.printStackTrace();
		
	} catch (Exception e) {
		request.setAttribute("producto", producto);
		request.setAttribute("mensajeError", "Error ... no se pudo procesar peticion");		
		request.getRequestDispatcher("/empleado/recepcion/sell.jsp").forward(request, response);
		e.printStackTrace();

	}
	
	}
/*-------------------------------------------------------------------------------------------------------------*/
private void confirmarReservas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String idEmpleado = request.getParameter("idEmpleado");
	int id = Integer.valueOf(request.getParameter("idReserva"));
	String estado = "Cliente en habitacion";
	
	try {		
		reservaDao.confirmarIngreso(id, estado);		
		request.setAttribute("idEmpleado", idEmpleado);
		request.setAttribute("mensajeOperacion", "Ingreso de husped guardado");		
		request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);
	}catch (SQLException e) {
			request.setAttribute("idEmpleado", idEmpleado);
			request.setAttribute("mensajeError", "Error SQL al procesar peticion");
			e.printStackTrace();
			request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("idEmpleado", idEmpleado);
			request.setAttribute("mensajeError", "Error al procesar peticion");
			e.printStackTrace();
			request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);

		}

	
}

/*------------------------------------------------------------------------------------------------------------------------*/

private void actualizarReservas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	Reserva reserva = new Reserva();
	int IdReserva = Integer.valueOf(request.getParameter("idReserva"));
	int id_habitacion = Integer.valueOf(request.getParameter("habitacion"));
	String idEmpleado = request.getParameter("idEmpleado");
	
						
try {	
			Date fecha_reserva = f.parse(request.getParameter("fechaReserva"));
			Date fecha_salida = f.parse(request.getParameter("fechaSalida"));	
			
	
			
				reserva.setId_habitacion(id_habitacion);
				reserva.setFechaReserva(fecha_reserva);
				reserva.setFechaSalida(fecha_salida);
				reserva.setId(IdReserva);
			
				reservaDao.getUpdate(reserva);
				request.setAttribute("idEmpleado", idEmpleado);
				request.setAttribute("mensajeOperacion", "Reserva actualizada satisfactoriamente");			
				request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);
			
	
	
			
			
	} catch(SQLException e) {
		
		request.setAttribute("mensajeError", "Error SQL no se pudo procesar peticion");
		request.setAttribute("idEmpleado", idEmpleado);
		e.printStackTrace();
		request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);
	} catch (Exception e) {
		request.setAttribute("idEmpleado", idEmpleado);
		request.setAttribute("mensajeError", "Error SQL no se pudo procesar peticion");
		e.printStackTrace();
		request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);
		
	}
	
	}


/*----------------------------------------------------------------------------------------------------------------------------*/

private int comprobarFechaRserva(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
	
	int fechaComprobada = 1;
	
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");	
	Date fechaReserva1Original = null;
	Date fechaSalidaOriginal= null;
	Date fechaReserva = f.parse( request.getParameter("fechaReserva"));
	Date fechaSalida = f.parse( request.getParameter("fechaSalida"));             
	String IdHabitacion = request.getParameter("habitacion");
	
	try {
			reserva = reservaDao.ComprobarFecha(IdHabitacion);
			fechaReserva1Original = reserva.getFechaReserva();
			fechaSalidaOriginal = reserva.getFechaSalida();
						
			if (fechaReserva.before(fechaSalidaOriginal) == true && fechaReserva.after(fechaReserva1Original) == true)
			 {
				fechaComprobada = 0;
				
			}else if(fechaReserva.before(fechaReserva1Original) == true && fechaReserva.before(fechaSalidaOriginal) == true) {
				
				fechaComprobada = 0;
				
			}else if(fechaReserva.before(fechaReserva1Original) == true || fechaSalida.equals(fechaReserva1Original)) {
				
				fechaComprobada = 1;
				
			}
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	return fechaComprobada;
	
}


private void editarReservas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	String id = request.getParameter("idReserva");
	 	String idEmpleado = request.getParameter("idEmpleado");
	
	try {
		  Reserva rv = reservaDao.getSelect_Id(id);					
		  request.setAttribute("rv", rv); 
		  request.setAttribute("idEmpleado", idEmpleado);
		  request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);
		 
		
	} catch (SQLException e) {
		  request.setAttribute("idEmpleado", idEmpleado);
		request.setAttribute("mensajeError", "Error SQL al procesar peticion");
		e.printStackTrace();
		request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);
	} catch (Exception e) {
		  request.setAttribute("idEmpleado", idEmpleado);
		request.setAttribute("mensajeError", "Error al procesar peticion");
		e.printStackTrace();
		request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);

	}

}

/*-------------------------------------------------------------------------------------------------------------------------------*/

private void eliminarReservas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
				 	
					int id = Integer.parseInt(request.getParameter("IdReserva"));
					int idHabitacion = Integer.valueOf(request.getParameter("IdHabitacion"));
					String idEmpleado = request.getParameter("idEmpleado");
					String estado = "En limpieza";
		try {		
				
				reservaDao.getDelete(id);
				habitacionDao.cambiarEstadoHabitacion(estado, idHabitacion);
				request.setAttribute("idEmpleado", idEmpleado );
				request.setAttribute("mensajeOperacion", "Reserva eliminada sastifactoriamente");
				request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);
			
		} catch(SQLException e) {
			request.setAttribute("mensajeError", "Error al procesar peticion");
			request.setAttribute("idEmpleado", idEmpleado );
			request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);
			e.printStackTrace();
			
		} catch (Exception e) {
			request.setAttribute("idEmpleado", idEmpleado );
			request.setAttribute("mensajeError", "Error al procesar peticion");
			request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);
			e.printStackTrace();
			
		}
	
	
	
		
	}

/*----------------------------------------------------------------------------------------------*/

private void realizarReservas(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
{
 try {
	 	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd"); 	
	 	
	 	
		String idHabitacionSt = request.getParameter("habitacion");
		String idCliente = request.getParameter("idCliente");		
		Date fechaReserva = f.parse(request.getParameter("fechaReserva") );
		Date fechaSalida= f.parse(request.getParameter("fechaSalida") );
		int idEmpleado = Integer.valueOf(request.getParameter("idEmpleado"));
		List<Reserva> ListReserva = reservaDao.getSelect();		
		
		
		if (idCliente.isEmpty()) {
			request.setAttribute("idEmpleado", idEmpleado);
			request.setAttribute("mensajeError","Por favor, ingrese datos del huesped que realizara la reservacion");
			request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);	
		} else {
			
			int idPer =  Integer.valueOf(idCliente);				
			int idHab = Integer.valueOf(idHabitacionSt);
			int comprobarFecha = comprobarFechaRserva(request, response);
	    		    
			    if (comprobarFecha == 0) {
			    	request.setAttribute("idEmpleado", idEmpleado);
					request.setAttribute("mensajeError","Lo siento, esta habitacion ya esta reservada para el dia ingresado");
					request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);	
				} else {
					reserva.setId_empleado(idEmpleado);				
					reserva.setId_persona(idPer);
					reserva.setId_habitacion(idHab);
					reserva.setFechaReserva(fechaReserva);
					reserva.setFechaSalida(fechaSalida);
					reserva.setEstado("Espera de ingreso");								
					reservaDao.getInsert(reserva);
					String habitacionOcupada = "Ocupada";
					habitacionDao.cambiarEstadoHabitacion(habitacionOcupada, idHab);
					request.setAttribute("reserva", ListReserva);
					request.setAttribute("idEmpleado", idEmpleado);
					request.setAttribute("mensajeOperacion","Recepcion realizada con exito");
					request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);	
				}
		}	
		
		
	
			
							
		
	} catch (SQLException e) {
		
		request.setAttribute("mensajeError", "Error al procesar peticion");
		request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);
		e.printStackTrace();
		
	}catch (Exception e) {
		
		request.setAttribute("mensajeOperacion", "Error al procesar peticion");
		request.getRequestDispatcher("Recepcion?accion=mostrar-reservas&operacion=inicio").forward(request, response);
		e.printStackTrace();
		
	}
	
}



/*----------------------------------------------------------------------------------------------*/

private void mostrarReservas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String cliente = null;							
				List<Reserva> listReserva = null;				
				List<Habitaciones> listHabitaciones= null;
				cliente = request.getParameter("cliente");	
			    String idEmpleado = request.getParameter("idEmpleado");				
				String operacion = request.getParameter("operacion");
				String tipo = "cliente";
				int totalRegistros = 0;
				
try {   		
				switch (operacion) {
				case "inicio":
					listHabitaciones = habitacionDao.getSelect();
					listReserva = reservaDao.getSelect();					
					for (int i = 0; i < listReserva.size(); i++) {
						totalRegistros = totalRegistros + 1;
					}						
					request.setAttribute("habitaciones", listHabitaciones);
					request.setAttribute("totalRegistros", totalRegistros);		
					request.setAttribute("reservas", listReserva);
					request.setAttribute("idEmpleado", idEmpleado);
					request.getRequestDispatcher("/empleado/recepcion/booking.jsp").forward(request, response);	
					break;
					
				case "filtrarReservas":
					listHabitaciones = habitacionDao.getSelect();
					listReserva = reservaDao.getSelect(Integer.valueOf(cliente));					
					for (int i = 0; i < listReserva.size(); i++) {
						totalRegistros = totalRegistros + 1;
					}						
					request.setAttribute("habitaciones", listHabitaciones);
					request.setAttribute("totalRegistros", totalRegistros);		
					request.setAttribute("reservas", listReserva);
					request.setAttribute("idEmpleado", idEmpleado);
					request.getRequestDispatcher("/empleado/recepcion/booking.jsp").forward(request, response);	
					break;
					
				case "buscarCliente":					
					if (cliente.isEmpty() || cliente == null ) {						
						request.setAttribute("mensajeError", "Ingrese la identificacion del huesped");
						listHabitaciones = habitacionDao.getSelect();
						listReserva = reservaDao.getSelect();
										
						request.setAttribute("idEmpleado", idEmpleado );
						request.setAttribute("habitaciones", listHabitaciones);						
						request.setAttribute("reservas", listReserva);
						request.setAttribute("idEmpleado", idEmpleado);
						request.getRequestDispatcher("/empleado/recepcion/booking.jsp").forward(request, response);			
						
					} else {
									persona = personaDao.validarCliente(Integer.valueOf(cliente), tipo);							
									if (persona.getIdentification() == 0) {
									
									listHabitaciones = habitacionDao.getSelect();
									listReserva = reservaDao.getSelect();
									request.setAttribute("mensajeError", "El huesped no se encuentra registrado en la base de datos");	
									request.setAttribute("habitaciones", listHabitaciones);						
									request.setAttribute("reservas", listReserva);
									request.setAttribute("idEmpleado", idEmpleado );
									request.getRequestDispatcher("/empleado/recepcion/booking.jsp").forward(request, response);	
									
								} else {
									Persons cl = personaDao.buscarCliente(tipo, cliente);
									listHabitaciones = habitacionDao.getSelect();
									listReserva = reservaDao.getSelect();
									request.setAttribute("idEmpleado", idEmpleado );
									request.setAttribute("c", cl);	
									request.setAttribute("habitaciones", listHabitaciones);						
									request.setAttribute("reservas", listReserva);						
									request.getRequestDispatcher("/empleado/recepcion/booking.jsp").forward(request, response);	
								}
								
														
								
							}									
				 break;
				
				}		 		
				
	} catch (SQLException e) {
		
		request.setAttribute("mensajeError", "Error al procesar peticion");
		request.setAttribute("idEmpleado", idEmpleado );
		request.getRequestDispatcher("/empleado/recepcion/booking.jsp").forward(request, response);	
		e.printStackTrace();
		
	}catch (Exception e) {
		
		request.setAttribute("mensajeOperacion", "Error al procesar peticion");
		request.setAttribute("idEmpleado", idEmpleado );
		request.getRequestDispatcher("/empleado/recepcion/booking.jsp").forward(request, response);	
		e.printStackTrace();
		
	}
	
	
}

 /*------------------------------------------------------------------------------------------------------*/

	private void MostrarHabitacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String tipo_habitacion = request.getParameter("txt-tipo");
		String habitacionesLibresSt = "Libre";
		String habitacionesOcupadasSt = "Ocupada";
		String habitacionesEnLimpiezaSt = "En limpieza";
		String operacion = request.getParameter("operacion");
		int totalRegistros = 0;
		HabitacionesDao habitacionDao = new HabitacionesDao();
try {	
	
				switch (operacion) {
				case "Buscar habitacion":
					
					if (tipo_habitacion.equalsIgnoreCase("matrimonial") || tipo_habitacion.equalsIgnoreCase("doble") || tipo_habitacion.equalsIgnoreCase("tiple") ) {		
						int habitacionesLibres = habitacionDao.cantidadHabitaciones(habitacionesLibresSt);
						int habitacionesOcupadas = habitacionDao.cantidadHabitaciones(habitacionesOcupadasSt);
						int habitacionesEnLimpieza= habitacionDao.cantidadHabitaciones(habitacionesEnLimpiezaSt);
				
						request.setAttribute("habitacionesLibres", habitacionesLibres );
						request.setAttribute("habitacionesOcupadas", habitacionesOcupadas );
						request.setAttribute("habitacionesLimpieza", habitacionesEnLimpieza);
						List<Habitaciones> filter = habitacionDao.getSelect(tipo_habitacion);
						for (int i = 0; i < filter.size(); i++) {
							totalRegistros = totalRegistros + 1;
						}
						request.setAttribute("totalRegistros", totalRegistros);
						request.setAttribute("habitaciones", filter);
						request.getRequestDispatcher("/empleado/recepcion/rooms.jsp").forward(request, response);	
				
			} else {						
						int habitacionesLibres = habitacionDao.cantidadHabitaciones(habitacionesLibresSt);
						int habitacionesOcupadas = habitacionDao.cantidadHabitaciones(habitacionesOcupadasSt);
						int habitacionesEnLimpieza= habitacionDao.cantidadHabitaciones(habitacionesEnLimpiezaSt);
						
						request.setAttribute("habitacionesLibres", habitacionesLibres );
						request.setAttribute("habitacionesOcupadas", habitacionesOcupadas );
						request.setAttribute("habitacionesLimpieza", habitacionesEnLimpieza);
						List<Habitaciones> listHab = habitacionDao.getSelect();
						for (int i = 0; i < listHab.size(); i++) {
							totalRegistros = totalRegistros + 1;
						}
						request.setAttribute("totalRegistros", totalRegistros);
						request.setAttribute("habitaciones", listHab);
						request.setAttribute("mensajeError", "Por favor, seleccione un tipo de habitacion correcto");
						request.getRequestDispatcher("/empleado/recepcion/rooms.jsp").forward(request, response);		
			}
					
					break;

				case "inicio":
					int habitacionesLibres = habitacionDao.cantidadHabitaciones(habitacionesLibresSt);
					int habitacionesOcupadas = habitacionDao.cantidadHabitaciones(habitacionesOcupadasSt);
					int habitacionesEnLimpieza= habitacionDao.cantidadHabitaciones(habitacionesEnLimpiezaSt);
					
					request.setAttribute("habitacionesLibres", habitacionesLibres );
					request.setAttribute("habitacionesOcupadas", habitacionesOcupadas);
					request.setAttribute("habitacionesLimpieza", habitacionesEnLimpieza);
					List<Habitaciones> listHab = habitacionDao.getSelect();
					for (int i = 0; i < listHab.size(); i++) {
						totalRegistros = totalRegistros + 1;
					}
					request.setAttribute("totalRegistros", totalRegistros);
					request.setAttribute("habitaciones", listHab);
					request.getRequestDispatcher("/empleado/recepcion/rooms.jsp").forward(request, response);
					break;
				}
	
	
	
	
	
					
			
	} catch (SQLException e) {
		request.setAttribute("mensajeError", "No se pudo procesar peticion");
		e.printStackTrace();
		request.getRequestDispatcher("Recepcion?accion=Listar-habitaciones&operacion=inicio").forward(request, response);
	} catch (Exception e) {		
		request.setAttribute("mensajeError", "No se pudo procesar peticion");
		e.printStackTrace();
		request.getRequestDispatcher("Recepcion?accion=Listar-habitaciones&operacion=inicio").forward(request, response);
	}
	 
	}
	
	
	
	
	
	
	
}