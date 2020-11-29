package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe Data - converte o formato padrão data para o formato br
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */

public class Data {
	
	 public static Date parseDate(String data) throws ParseException, java.text.ParseException {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        sdf.setLenient(false);
	        return sdf.parse(data);
	    }

}
