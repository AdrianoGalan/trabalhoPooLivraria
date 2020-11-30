package boundary;

import java.sql.SQLException;
import control.ControleAutor;
import control.ControleTelas;
import control.GetenciadorPrincipal;
import entity.Autor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.Mensagens;

public class TelaCadastroAutor implements ControleTelas, EventHandler<ActionEvent> {

	private Button btOk;
	private Button btCancelar;
	private TextField tfNome;
	private TextField tfNa;
	private ControleAutor control = new ControleAutor();

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btOk && verificaCampos()) {
			
			try {
				
				if(!control.verificaDuplicata(tfNome.getText())) {
					addAutor();
					limpaCampos();
				}else {
					Mensagens.erro("Erro nome autor", "Por favor insira um nome diferente", "Nome de autor já cadastrado");
				}
				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			

		}
		if (e.getTarget() == btCancelar) {

		}
	}

	private void addAutor() {

		ControleAutor aa = new ControleAutor();

		Autor a = new Autor();

		a.setNome(tfNome.getText());
		a.setNacionalidade(tfNa.getText());
		

		try {
			aa.addAutor(a);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private boolean verificaCampos() {
		// TODO Auto-generated method stub

		if (tfNome.getText().equals("")) {

			Mensagens.erro("Nome erro", "Nome invalido", "Digite um nome");

			return false;

		} else if (tfNa.getText().equals("")) {

			Mensagens.erro("Nacionalidade erro", "Nacionalidade invalida", "Digite uma Nacionalidade");

			return false;

		}else {
			return true;
		}

		
	}

	@Override
	public Pane render() {

		HBox painel = new HBox();

		VBox vbEs = new VBox();
		vbEs.setPadding(new Insets(15, 12, 15, 12));
		vbEs.setSpacing(15);

		VBox vbDi = new VBox();
		vbDi.setPadding(new Insets(15, 12, 15, 12));
		vbDi.setSpacing(5);

		HBox hbBotao = new HBox();
		hbBotao.setSpacing(40);

		btOk = new Button("Cadastrar");
		btOk.addEventHandler(ActionEvent.ACTION, this);
		btCancelar = new Button("Cancelar");
		btCancelar.addEventHandler(ActionEvent.ACTION, this);

		hbBotao.getChildren().add(btOk);
		hbBotao.getChildren().add(btCancelar);

		vbEs.getChildren().add(new Label("Nome:"));
		vbEs.getChildren().add(new Label("Nacionalidade:"));

		vbEs.getChildren().add(hbBotao);

		tfNome = new TextField();
		tfNome.setPrefWidth(330);
		tfNa = new TextField();

		vbDi.getChildren().add(tfNome);
		vbDi.getChildren().add(tfNa);

		painel.getChildren().add(vbEs);
		painel.getChildren().add(vbDi);

		return painel;
	}

	
	private void limpaCampos() {
		tfNome.setText("");
		tfNa.setText("");
	}
	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

}
