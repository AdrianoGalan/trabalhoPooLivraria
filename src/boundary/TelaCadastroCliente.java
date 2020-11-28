package boundary;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import DAO.EnderecoDao;
import control.ControleCliente;
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
import javafx.scene.control.ComboBox;
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
	private TextField tfDdd;
	private TextField tfCpf;
	private TextField tfRua;
	private TextField tfNum;
	private TextField tfBairro;
	private TextField tfCidade;
	private TextField tfComplemento;
	private TextField tfCep;
	private TextField tfEmail;
	private TextField tfDtnasc;
	private TextField tfEstado;
//	private ComboBox<String> cbEstado;
	private ComboBox<String> cbTipoTelefone;

	private GregorianCalendar data = new GregorianCalendar();

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btOk && verificaCampos()) {

			addCliente();

		}

		if (e.getTarget() == btCancelar) {

		}
	}

	@Override
	public Pane render() {
		HBox painel = new HBox();

		VBox vbEs = new VBox();
		vbEs.setPadding(new Insets(15, 1, 15, 12));
		vbEs.setSpacing(18);

		VBox vbDi = new VBox();
		vbDi.setPadding(new Insets(15, 12, 15, 1));
		vbDi.setSpacing(9);

		HBox hbBotao = new HBox();
		hbBotao.setSpacing(40);

		btOk = new Button("Cadastrar");
		btOk.addEventHandler(ActionEvent.ACTION, this);
		btCancelar = new Button("Cancelar");
		btCancelar.addEventHandler(ActionEvent.ACTION, this);

		hbBotao.getChildren().add(btOk);
		hbBotao.getChildren().add(btCancelar);

		vbEs.getChildren().add(new Label("Nome:"));
		vbEs.getChildren().add(new Label("Telefone tipo:"));
		vbEs.getChildren().add(new Label("DDD"));
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

		tfNome = new TextField();
		tfNome.setPrefWidth(330);
		tfTelefone = new TextField();
		tfDdd = new TextField("11");
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

/*		cbEstado = new ComboBox<String>();
		cbEstado.getItems().addAll("SP", "Rj", "MG");
		cbEstado.setPrefWidth(80);
		cbEstado.getSelectionModel().select(0);   */
		
		cbTipoTelefone = new ComboBox<String>();
		cbTipoTelefone.getItems().addAll("CEL", "COM", "RES");
		cbTipoTelefone.setPrefWidth(80);
		cbTipoTelefone.getSelectionModel().select(0);

		vbDi.getChildren().add(tfNome);
		vbDi.getChildren().add(cbTipoTelefone);
		vbDi.getChildren().add(tfDdd);
		vbDi.getChildren().add(tfTelefone);
		vbDi.getChildren().add(tfCpf);
		vbDi.getChildren().add(tfRua);
		vbDi.getChildren().add(tfNum);
		vbDi.getChildren().add(tfBairro);
		vbDi.getChildren().add(tfCidade);
//		vbDi.getChildren().add(cbEstado);
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

	private void addCliente() {

		ControleCliente cc = new ControleCliente();

		Cliente c = new Cliente();
		Telefone t = new Telefone();
		Endereco e = new Endereco();

		e.setNumero(Integer.parseInt(tfNum.getText()));
		e.setBairro(tfBairro.getText());
		e.setRua(tfRua.getText());
		e.setCidade(tfCidade.getText());
	//	e.setEstado(cbEstado.getSelectionModel().getSelectedItem());
		e.setComplemento(tfComplemento.getText());
		e.setCep(tfCep.getText());

		t.setTipo(cbTipoTelefone.getSelectionModel().getSelectedItem());
		t.setDdd(tfDdd.getText());
		t.setNumero(tfTelefone.getText());

		c.setNome(tfNome.getText());
		c.setCpf(tfCpf.getText());
		c.setEmail(tfEmail.getText());
		try {
			c.setDataNascimento(Data.parseDate(tfDtnasc.getText()));
		} catch (ParseException e2) {

		}

		try {
			cc.addCliente(c, e, t);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private boolean verificaCampos() {

		if (tfNome.getText().equals("") || (!tfNome.getText().matches("^[a-zA-Z]+(([\\'\\,\\.\\- ][a-zA-Z ])?[a-zA-Z]*)*$"))) {  //"^[a-zA-Z]+$"

			Mensagens.erro("NOME ERRO", "Nome invalido", "Digite um nome");

			return false;

		} else 
			
			if (tfDdd.getText().equals("") || (!tfDdd.getText().matches("\\d+"))) {

			Mensagens.erro("DDD ERRO", "DDD invalido", "Digite um DDD");

			return false;

		}else 
			
			if (tfTelefone.getText().equals("") || tfTelefone.getText().length() != 9 || (!tfTelefone.getText().matches("\\d+"))) {

			Mensagens.erro("TELEFONE ERRO", "Telefone invalido", "Digite um Telefone");

			return false;

		}else if (tfCpf.getText().equals("") || tfCpf.getText().length() != 11 || (!tfCpf.getText().matches("\\d+"))) {

			Mensagens.erro("CPF ERRO", "CPF invalida", "Digite um CPF");

			return false;

		}  else if (tfRua.getText().equals("") || (!tfRua.getText().matches("^(/w|/W|[^<>+?$%{}&])+$"))) {

			Mensagens.erro("RUA ERRO", "Rua invalida", "Digite uma Rua");

			return false;

		} else if (tfNum.getText().equals("") || (!tfNum.getText().matches("\\d+"))) {

			Mensagens.erro("NUMERO ERRO", "Numero invalida", "Digite um Numero");

			return false;

		} else if (tfBairro.getText().equals("")) {

			Mensagens.erro("BAIRRO ERRO", "Bairro invalida", "Digite um Bairro");

			return false;

		}else 
			
			if (tfCidade.getText().equals("")) {

			Mensagens.erro("CIDADE ERRO", "Cidade invalida", "Digite uma Cidade");

			return false;

		}else 
			
			if (tfEstado.getText().equals("") || (!tfEstado.getText().matches("^(AC|AL|AM|AP|BA|CE|DF|ES|GO|MA|MG|MS|MT|PA|PB|PE|PI|PR|RJ|RN|RO|RR|RS|SC|SE|SP|TO)$"))) {

			Mensagens.erro("ESTADO ERRO", "Estado invalida", "Digite uma UF");

			return false;

		}else 
			
			if (tfComplemento.getText().equals("")) {

			Mensagens.erro("COMPLEMENTO ERRO", "Complemento invalido", "Digite um Complemento");

			return false;

		}else if (tfCep.getText().equals("") || tfCep.getText().length() != 8 || (!tfCep.getText().matches("\\d+"))) {

			Mensagens.erro("CEP ERRO", "CEP invalido", "Digite um CEP");

			return false;

		} else if (tfEmail.getText().equals("") || (!tfEmail.getText().matches("^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+(?:[a-zA-Z]{2}|aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel)$"))) {

			Mensagens.erro("EMAIL ERRO", "Email invalido", "Digite um Email");

			return false;

		} else {

			try {

				Date data = Data.parseDate(tfDtnasc.getText());

			} catch (ParseException e) {

				Mensagens.erro("DATA ERRO", "Data invalida", "Digite uma Data valida");

				return false;

			}
		}

		return true;

	}

}
