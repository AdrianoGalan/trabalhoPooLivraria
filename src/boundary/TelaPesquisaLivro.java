package boundary;

import control.ControleTelas;
import control.GetenciadorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class TelaPesquisaLivro implements ControleTelas, EventHandler<ActionEvent>  {

	@Override
	public void handle(ActionEvent e) {
		
		
	}

	@Override
	public Pane render() {
		Pane painel = new Pane();
		
	
		Label lblUser = new Label("Tela pesquisa livro ");
		lblUser.relocate(20, 14);

		

		painel.getChildren().addAll(lblUser);


		

	
		return painel;
	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub
		
	}

}