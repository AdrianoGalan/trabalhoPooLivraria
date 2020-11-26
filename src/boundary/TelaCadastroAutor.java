package boundary;

import java.sql.SQLException;
import java.text.ParseException;

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
import util.Data;
import util.Mensagens;

public class TelaCadastroAutor implements ControleTelas, EventHandler<ActionEvent> {

	private Button btOk;
	private Button btCancelar;
	private TextField tfNome;
	private TextField tfNa;
	private TextField tfDtnasc;

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btOk && verificaCampos()) {

			addAutor();

		}
		if (e.getTarget() == btCancelar) {

		}
	}

	private void addAutor() {
		// TODO Auto-generated method stub
		ControleAutor aa = new ControleAutor();

		Autor a = new Autor();

		a.setNome(tfNome.getText());
		a.setNacionalidade(tfNa.getText());

		/*
		 * a.setDataNascimento(tfDtnasc.getText());
		 * 
		 * try { a.setDataNascimento(Data.parseDate(tfDtnasc.getText())); } catch
		 * (ParseException e2) {
		 * 
		 * }
		 */

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

		}

		return true;
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
		vbEs.getChildren().add(new Label("Data de nascimento:"));

		vbEs.getChildren().add(hbBotao);

		TextField tfNome = new TextField();
		tfNome.setPrefWidth(330);
		tfNa = new TextField();
		tfDtnasc = new TextField();

		vbDi.getChildren().add(tfNome);
		vbDi.getChildren().add(tfNa);
		vbDi.getChildren().add(tfDtnasc);

		painel.getChildren().add(vbEs);
		painel.getChildren().add(vbDi);

		return painel;
	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

}