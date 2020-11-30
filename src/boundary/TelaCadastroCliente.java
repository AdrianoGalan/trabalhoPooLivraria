package boundary;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import DAO.EnderecoDao;
import DAO.PessoaDao;
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
import util.Mascaras;
import util.Mensagens;

public class TelaCadastroCliente implements ControleTelas, EventHandler<ActionEvent> {

	private Button btOk;
	private Button btCancelar;
	private Button btUpd;
	private TextField tfNome;
	private TextField tfTelefone;
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
	private Cliente c = null;
	private Telefone t;
	private Endereco e;
	private ControleCliente controle = new ControleCliente();
	private TelaPesquisaCliente tela;

	
	private Date dataAtual = new Date();
	
	TelaCadastroCliente(){}
	
	TelaCadastroCliente(Cliente c, TelaPesquisaCliente tela){
		this.c = c;
		this.tela = tela;
	}

	@Override
	public void handle(ActionEvent evt) {
		if (evt.getTarget() == btOk && verificaCampos()) {

			if(verificaDuplicata()) {
				addCliente();
				limpaCampos();
			}

		}

		if (evt.getTarget() == btCancelar) {
			
			limpaCampos();

		}
		
		if (evt.getTarget() == btUpd) {
			try {
				DadosParaEntidades();
				controle.alterarCliente(c, e, t);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Mensagens.informacao("Alteracao de funcionario", "O funcionario foi alterado com sucesso", "");
			tela.carregarTabela();
			tela.stage.close();
		}
	}

	private void limpaCampos() {
		
		tfNome.setText(""); 
		tfTelefone.setText(""); 
		tfCpf.setText("");
		tfRua.setText("");
		tfNum.setText("");
		tfBairro.setText("");
		tfCidade.setText("");
		tfEstado.setText("");
		tfComplemento.setText("");
		tfCep.setText("");
		tfEmail.setText("");
		tfDtnasc.setText("");
		
		
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

		

		vbEs.getChildren().add(new Label("Nome:"));
		vbEs.getChildren().add(new Label("Telefone tipo:"));
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

		

		tfNome = new TextField();
		tfNome.setPrefWidth(330);
		tfTelefone = new TextField();
		Mascaras.mascaraTelefone(tfTelefone);
		tfCpf = new TextField();
		Mascaras.mascaraCPF(tfCpf);
		tfRua = new TextField();
		tfNum = new TextField();
		Mascaras.mascaraApenasNum(tfNum);
		tfBairro = new TextField();
		tfCidade = new TextField();
		tfEstado = new TextField();
		tfComplemento = new TextField();
		tfCep = new TextField();
		Mascaras.mascaraCEP(tfCep);
		tfEmail = new TextField();
		tfDtnasc = new TextField();
		Mascaras.mascaraData(tfDtnasc);

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

		
		//verifica se a tela vai ser usada para cadastrar ou alterar
		if(c != null) {
			btUpd = new Button("Alterar");
			btUpd.addEventHandler(ActionEvent.ACTION, this);
			carregaDadosCampos();
			hbBotao.getChildren().add(btUpd);
		}else {
			btOk = new Button("Cadastrar");
			btOk.addEventHandler(ActionEvent.ACTION, this);
			hbBotao.getChildren().add(btOk);
		}
		
		
		btCancelar = new Button("Cancelar");
		btCancelar.addEventHandler(ActionEvent.ACTION, this);

		
		hbBotao.getChildren().add(btCancelar);
		vbEs.getChildren().add(hbBotao);
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
		e.setEstado(tfEstado.getText());
		e.setComplemento(tfComplemento.getText());
		e.setCep(tfCep.getText().replaceAll("[-]", ""));

		t.setTipo(cbTipoTelefone.getSelectionModel().getSelectedItem());
		String telefone = tfTelefone.getText().replaceAll("[()-]", "");
		t.setDdd(telefone.substring(0,2));
		t.setNumero(telefone.substring(2,telefone.length()));

		c.setNome(tfNome.getText());
		c.setCpf(tfCpf.getText().replaceAll("[.-]", ""));
		c.setEmail(tfEmail.getText());
		c.setDataCadastro(dataAtual);
		
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
		Mensagens.informacao("Cliente cadastrado", "O cliente foi cadastrado com sucesso", "");
	}
	
	private boolean verificaDuplicata() {
		try {
			PessoaDao pDao = new PessoaDao();
			if(pDao.verificaDuplicCpf(tfCpf.getText())) {
				Mensagens.erro("Cpf erro", "Cpf inválido", "Cpf inválido ou já cadastrado");
				return false;
			}else if(pDao.verificaDuplicEmail(tfEmail.getText())) {
				Mensagens.erro("Email erro", "Email inválido", "Email inválido ou já cadastrado");
				return false;
			}else {
				return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	private void carregaDadosCampos() {
		
		try {
			e = controle.buscaEnderecoClient(c.getFkEdetecoPessoa());
			t = controle.buscaTelefoneClient(c.getIdPessoa());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		tfNome.setText(c.getNome());
		tfTelefone.setText(t.getDdd()+t.getNumero());
		tfCpf.setText(c.getCpf());
		tfRua.setText(e.getRua());
		tfNum.setText(Integer.toString(e.getNumero()));
		tfBairro.setText(e.getBairro());
		tfCidade.setText(e.getCidade());
		tfComplemento.setText(e.getComplemento());
		tfCep.setText(e.getCep());
		tfEmail.setText(c.getEmail());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		tfDtnasc.setText(formatador.format(c.getDataNascimento()));
		tfEstado.setText(e.getEstado());
		cbTipoTelefone.setValue(t.getTipo());
		
	}
	
	private void DadosParaEntidades() {

		e.setNumero(Integer.parseInt(tfNum.getText()));
		e.setBairro(tfBairro.getText());
		e.setRua(tfRua.getText());
		e.setCidade(tfCidade.getText());
		e.setEstado(tfEstado.getText());
		e.setComplemento(tfComplemento.getText());
		e.setCep(tfCep.getText().replaceAll("[-]", ""));
		t.setTipo(cbTipoTelefone.getSelectionModel().getSelectedItem());
		String telefone = tfTelefone.getText();
		t.setDdd(telefone.substring(0, 2));
		t.setNumero(telefone.substring(2, telefone.length()));
		c.setNome(tfNome.getText());
		c.setCpf(tfCpf.getText());
		c.setEmail(tfEmail.getText());

		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date data = formato.parse(tfDtnasc.getText().replaceAll("-", "/"));
			c.setDataNascimento(data);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

	}

	private boolean verificaCampos() {

		if (tfNome.getText().equals("") || (!tfNome.getText().matches("^[a-zA-Z]+(([\\'\\,\\.\\- ][a-zA-Z ])?[a-zA-Z]*)*$")) || tfNome.getText().length() > 200) {  //"^[a-zA-Z]+$"

			Mensagens.erro("NOME ERRO", "Nome invalido", "Digite um nome");

			return false;

		} else 
			
			if (tfTelefone.getText().equals("") || tfTelefone.getText().replaceAll("[()-]", "").length() <= 7 ) {

			Mensagens.erro("TELEFONE ERRO", "Telefone invalido", "Digite um Telefone");

			return false;

		}else if (tfCpf.getText().equals("") || tfCpf.getText().length() != 14 ) {

			Mensagens.erro("CPF ERRO", "CPF invalida", "Digite um CPF");

			return false;

		}  else if (tfRua.getText().equals("") || (!tfRua.getText().matches("^(/w|/W|[^<>+?$%{}&])+$")) || tfRua.getText().length() > 200) {

			Mensagens.erro("RUA ERRO", "Rua invalida", "Digite uma Rua");

			return false;

		} else if (tfNum.getText().equals("") || (!tfNum.getText().matches("\\d+"))) {

			Mensagens.erro("NUMERO ERRO", "Numero invalida", "Digite um Numero");

			return false;

		} else if (tfBairro.getText().equals("") || tfBairro.getText().length() > 150) {

			Mensagens.erro("BAIRRO ERRO", "Bairro invalida", "Digite um Bairro");

			return false;

		}else 
			
			if (tfCidade.getText().equals("") || tfCidade.getText().length() > 150) {

			Mensagens.erro("CIDADE ERRO", "Cidade invalida", "Digite uma Cidade");

			return false;

		}else 
			
			if (tfEstado.getText().equals("") || (!tfEstado.getText().matches("^(AC|AL|AM|AP|BA|CE|DF|ES|GO|MA|MG|MS|MT|PA|PB|PE|PI|PR|RJ|RN|RO|RR|RS|SC|SE|SP|TO)$"))) {

			Mensagens.erro("ESTADO ERRO", "Estado invalida", "Digite uma UF");

			return false;

		}else 
			
			if (tfComplemento.getText().equals("") || tfComplemento.getText().length() > 100) {

			Mensagens.erro("COMPLEMENTO ERRO", "Complemento invalido", "Digite um Complemento");

			return false;

		}else if (tfCep.getText().equals("") || tfCep.getText().replaceAll("[-]", "").length() != 8) {

			Mensagens.erro("CEP ERRO", "CEP invalido", "Digite um CEP");

			return false;

		} else if (tfEmail.getText().equals("") || tfEmail.getText().length() > 100 || (!tfEmail.getText().matches("^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+(?:[a-zA-Z]{2}|aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel)$"))) {

			Mensagens.erro("EMAIL ERRO", "Email invalido", "Digite um Email");

			return false;

		} else {

			try {

				Date data = Data.parseDate(tfDtnasc.getText());
				if(data.compareTo(dataAtual) > 0) {
					Mensagens.erro("Data erro", "Data invalida", "Digite uma Data valida");
					return false;

				}
			} catch (ParseException e) {

				Mensagens.erro("DATA ERRO", "Data invalida", "Digite uma Data valida");

				return false;

			}
		}

		return true;

	}
	
	

}
