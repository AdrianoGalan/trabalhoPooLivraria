package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe Data - converte o formato padr�o data para o formato br
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */

public class Data {
	/**
	 * M�todo de convers�o do formato data
	 * @param data
	 * @return sdf.parse(data)
	 * @throws ParseException
	 * @throws java.text.ParseException
	 */
	 public static Date parseDate(String data) throws ParseException, java.text.ParseException {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        sdf.setLenient(false);
	        return sdf.parse(data);
	    }

}
