package boundary;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;

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

			addCliente();

		}

		if (e.getTarget() == btCancelar) {

			
		}
	}

	@Override
	public Pane render() {
		HBox painel = new HBox();

		VBox vbEs = new VBox();
		vbEs.setPadding(new Insets(30, 12, 15, 12));
		vbEs.setSpacing(15);

		VBox vbDi = new VBox();
		vbDi.setPadding(new Insets(30, 12, 15, 12));
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

		tfNome = new TextField();
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

	private void addCliente() {

		ControleCliente cc = new ControleCliente();

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

		t.setTipo("CEL");
		t.setDdd("11");
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

		if (tfNome.getText().equals("")) {

			Mensagens.erro("Nome erro", "Nome invalido", "Digite um nome");

			return false;

		} else if (tfCpf.getText().equals("")) {

			//if (validaCpf(tfCpf.getText()) == true)

			
			return false;

		} else if (tfTelefone.getText().equals("") || tfTelefone.getText().length() != 9) {

			Mensagens.erro("Telefone erro", "Telefone invalido", "Digite um Telefone");

			return false;

		} else if (tfRua.getText().equals("")) {

			Mensagens.erro("Rua erro", "Rua invalida", "Digite uma Rua");

			return false;

		} else if (tfNum.getText().equals("") || Integer.parseInt(tfNum.getText()) < 0) {

			Mensagens.erro("Numero erro", "Numero invalido", "Digite um Numero");

			return false;

		} else if (tfBairro.getText().equals("")) {

			Mensagens.erro("Bairro erro", "Bairro invalido", "Digite um Bairro");

			return false;

		} else if (tfCidade.getText().equals("")) {

			Mensagens.erro("Cidade erro", "Cidade invalida", "Digite uma Cidade");

			return false;

		} else if (tfEstado.getText().equals("") || tfEstado.getText().length() != 2) {

			Mensagens.erro("Estado erro", "Estado invalido", "Digite um Estado");

			return false;

		} else if (tfComplemento.getText().equals("")) {

			Mensagens.erro("Complemento erro", "Complemento invalido", "Digite um Complemento");

			return false;

		} else if (tfCep.getText().equals("") || tfCep.getText().length() != 8) {

			Mensagens.erro("CEP erro", "CEP invalido", "Digite um CEP");

			return false;

		} else if (tfEmail.getText().equals("")) {

			Mensagens.erro("Email erro", "Email invalido", "Digite um Email");

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

	public static boolean validaCpf(String CPF) {

		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);

		} catch (InputMismatchException erro) {
			return (false);
		}
	}
	// Mascara CPF
	/*public static String imprimeCPF(String CPF) {
		return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-"
				+ CPF.substring(9, 11));
	}*/

}
