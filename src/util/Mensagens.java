package util;

import javafx.scene.control.Alert;

/**
 * Classe de Mensagens 
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */

public class Mensagens {
	
	/**
	 * M�todo que mostra mensagens de erro
	 * 
	 * @param titulo
	 * @param cabecalho
	 * @param msg
	 */

	public static void erro(String titulo, String cabecalho, String msg) {

		Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
		dialogoErro.setTitle(titulo);
		dialogoErro.setHeaderText(cabecalho);
		dialogoErro.setContentText(msg);
		dialogoErro.showAndWait();
	}
	
	/**
	 * M�todo que mostra informa��es 
	 * 
	 * @param titulo
	 * @param cabecalho
	 * @param msg
	 */

	public static void informacao(String titulo, String cabecalho, String msg) {

		Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
		dialogoInfo.setTitle(titulo);
		dialogoInfo.setHeaderText(cabecalho);
		dialogoInfo.setContentText(msg);
		dialogoInfo.showAndWait();
	}

}
