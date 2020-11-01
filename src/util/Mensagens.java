package util;

import javafx.scene.control.Alert;

public class Mensagens {

	public static void erro(String titulo, String cabecalho, String msg) {

		Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
		dialogoErro.setTitle(titulo);
		dialogoErro.setHeaderText(cabecalho);
		dialogoErro.setContentText(msg);
		dialogoErro.showAndWait();
	}

	public static void informacao(String titulo, String cabecalho, String msg) {

		Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
		dialogoInfo.setTitle(titulo);
		dialogoInfo.setHeaderText(cabecalho);
		dialogoInfo.setContentText(msg);
		dialogoInfo.showAndWait();
	}

}
