package boundary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import control.ControleTelas;
import javafx.application.Application;
import control.GetenciadorPrincipal;
import entity.Cliente;
import entity.Endereco;
import entity.Telefone;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Data;
import util.Mensagens;

public class TelaCadastroCliente implements ControleTelas, EventHandler<ActionEvent> {

	private Button btOk;
	private Button btCancelar;
	private TextField tfNome;
	private TextField tfTelefone;
	private TextField tfCpf;
	private TextField tfRua;
	private TextField tfNum;
	private TextField tfBairro;
	private TextField tfCidade;
	private TextField tfEstado;
	private TextField tfComplemento;
	private TextField tfCep;
	private TextField tfEmail;
	private TextField tfDtnasc;
	
	private GregorianCalendar data = new GregorianCalendar();

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btOk && verificaCampos()) {
			
			
			
		}
		
		if (e.getTarget() == btCancelar) {
			
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
		vbDi.setSpacing(6.2);

		HBox hbBotao = new HBox();
		hbBotao.setSpacing(40);

		btOk = new Button("Cadastrar");
		btOk.addEventHandler(ActionEvent.ACTION, this);
		btCancelar = new Button("Cancelar");
		btCancelar.addEventHandler(ActionEvent.ACTION, this);

		hbBotao.getChildren().add(btOk);
		hbBotao.getChildren().add(btCancelar);

		vbEs.getChildren().add(new Label("Nome:"));
		vbEs.getChildren().add(new Label("Telefone:"));
		vbEs.getChildren().add(new Label("CPF:"));
		vbEs.getChildren().add(new Label("Rua:"));
		vbEs.getChildren().add(new Label("Numero:"));
		vbEs.getChildren().add(new Label("Bairro:"));
		vbEs.getChildren().add(new Label("Cidade:"));
		vbEs.getChildren().add(new Label("Estado:"));
		vbEs.getChildren().add(new Label("Complemento:"));
		vbEs.getChildren().add(new Label("CEP:"));
		vbEs.getChildren().add(new Label("Email:"));
		vbEs.getChildren().add(new Label("Data de nascimento:"));

		vbEs.getChildren().add(hbBotao);

		TextField tfNome = new TextField();
		tfNome.setPrefWidth(330);
		tfTelefone = new TextField();
		tfCpf = new TextField();
		tfRua = new TextField();
		tfNum = new TextField();
		tfBairro = new TextField();
		tfCidade = new TextField();
		tfEstado = new TextField();
		tfComplemento = new TextField();
		tfCep = new TextField();
		tfEmail = new TextField();
		tfDtnasc = new TextField();

		vbDi.getChildren().add(tfNome);
		vbDi.getChildren().add(tfTelefone);
		vbDi.getChildren().add(tfCpf);
		vbDi.getChildren().add(tfRua);
		vbDi.getChildren().add(tfNum);
		vbDi.getChildren().add(tfBairro);
		vbDi.getChildren().add(tfCidade);
		vbDi.getChildren().add(tfEstado);
		vbDi.getChildren().add(tfComplemento);
		vbDi.getChildren().add(tfCep);
		vbDi.getChildren().add(tfEmail);
		vbDi.getChildren().add(tfDtnasc);

		painel.getChildren().add(vbEs);
		painel.getChildren().add(vbDi);

		return painel;
	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}
	
	private Cliente boundaryParaCliente() {
		
		Cliente c = new Cliente();
		Telefone t = new Telefone();
		Endereco e = new Endereco();
		
		e.setNumero(Integer.parseInt(tfNum.getText()));
		e.setBairro(tfBairro.getText());
		e.setRua(tfRua.getText());
		e.setCidade(tfCidade.getText());
		e.setEstado(tfEstado.getText());
		e.setComplemento(tfComplemento.getText());
		e.setCep(tfCep.getText());
		
		c.setNome(tfNome.getText());
		c.setCpf(tfCpf.getText());
		c.setEmail(tfEmail.getText());
		

				
		return c;
		
	}

	private boolean verificaCampos() {

		if (tfNome.getText().equals("")) {

			Mensagens.erro("Nome erro", "Nome invalido", "Digite um nome");

			return false;

		} else if (tfCpf.getText().equals("")) {

			Mensagens.erro("CPF erro", "CPF invalida", "Digite um CPF");

			return false;

		} else if (tfTelefone.getText().equals("")) {

			Mensagens.erro("Telefone erro", "Telefone invalido", "Digite um Telefone");

			return false;

		} else if (tfRua.getText().equals("")) {

			Mensagens.erro("Rua erro", "Rua invalida", "Digite uma Rua");

			return false;

		} else if (tfNum.getText().equals("")) {

			Mensagens.erro("Numero erro", "Numero invalida", "Digite um Numero");

			return false;

		} else if (tfBairro.getText().equals("")) {

			Mensagens.erro("Bairro erro", "Bairro invalida", "Digite um Bairro");

			return false;

		} else if (tfEmail.getText().equals("")) {

			Mensagens.erro("Email erro", "Email invalida", "Digite um Email");

			return false;

		} else {
			
			try {
				
				Date data = Data.parseDate(tfDtnasc.getText());
				
			} catch (ParseException e) {

				Mensagens.erro("Data erro", "Data invalida", "Digite uma Data valida");

				return false;

			}
		}
		
		return true;
		
	}
}
