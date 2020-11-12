package boundary;

import control.ControleTelas;
import control.GetenciadorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TelaControleUsuario implements ControleTelas, EventHandler<ActionEvent> {
	
	

	@Override
	public Pane render() {
		Pane painel = new Pane();
		
		
		HBox hbBotao = new HBox();
		hbBotao.setSpacing(180);
		
		VBox vbEs = new VBox();
		// new Insets(y, ?, ?, x)
		vbEs.setPadding(new Insets(25, 0, 15, 100));
		vbEs.setSpacing(15);
		
		TableView<String> tbvPesqCliente = new TableView<String>();
		TableColumn<String, String> coluna = new TableColumn<String, String>("Usuario");
		tbvPesqCliente.getColumns().add(coluna);
		tbvPesqCliente.setPrefWidth(600);
		
		Button btNovoUsuario = new Button("Novo usuário");
		Button btAlterar = new Button("Alterar usuário");
		Button btExcluir = new Button("Excluir usuário");
		hbBotao.getChildren().addAll(btNovoUsuario,btAlterar,btExcluir);
		
	
		vbEs.getChildren().add(tbvPesqCliente);
		vbEs.getChildren().add(hbBotao);
		
		
		painel.getChildren().add(vbEs);


		

	
		return painel;
	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

}
