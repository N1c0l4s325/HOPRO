package config;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalcularDias {

	int dias;
	
public int calcularNoches(Date fechaReserva, Date fechaSalida) {
			
	Calendar cal1 = new GregorianCalendar(getAnio(fechaReserva),getMes(fechaReserva), getDia(fechaReserva));
	Calendar cal2 = new GregorianCalendar(getAnio(fechaSalida),getMes(fechaSalida), getDia(fechaSalida));
	
	long time1 = cal1.getTime().getTime();
	long time2 = cal2.getTime().getTime();
	long valor = time1 - time2; 
	long msDays = 1000 * 60 * 60 * 24;
	dias = (int) (valor / msDays);
		return  dias;
}

public int getDia (Date dato) {
	
	int d = 0;
	 
	Calendar cal = new GregorianCalendar();	
	cal.setTime(dato);
	d = cal.get(Calendar.DAY_OF_MONTH);
	
	return  d;
}

public int getMes(Date dato) {
	int m = 1;
	Calendar cal = new GregorianCalendar();	
	cal.setTime(dato);
	m = m + cal.get(Calendar.MONTH); 
	 
	return  m;
}


public int getAnio (Date dato) {
	
	int a = 0;
	Calendar cal = new GregorianCalendar();	
	cal.setTime(dato);
	a = cal.get(Calendar.YEAR); 
	 
	return  a;
}






 
	
}