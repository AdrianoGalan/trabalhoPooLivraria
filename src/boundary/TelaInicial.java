package boundary;

import control.ControleTelas;
import control.GetenciadorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class TelaInicial implements ControleTelas, EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pane render() {
		Pane painel = new Pane();

		Label lblUser = new Label("Integrantes do Grupo: ");
		lblUser.relocate(20, 15);
		
		Label lblAdriano = new Label("Adriano Galan RA: ....");
		lblAdriano.relocate(30, 50);

		Label lblGustavo = new Label("Gustavo Narciso RA: ...");
		lblGustavo.relocate(30, 100);

		Label lblRoberto = new Label("Roberto Mitsunari RA ...");
		lblRoberto.relocate(30, 150);


		painel.getChildren().addAll(lblUser, lblAdriano, lblGustavo, lblRoberto);
		return painel;
	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

}
