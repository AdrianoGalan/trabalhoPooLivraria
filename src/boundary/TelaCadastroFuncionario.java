package boundary;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import DAO.PessoaDao;
import control.ControleFuncionario;
import control.ControleTelas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import control.GetenciadorPrincipal;
import entity.Endereco;
import entity.Funcionario;
import entity.Telefone;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.Data;
import util.Mascaras;
import util.Mensagens;

/**
 * Classe tela que cadastra funcionario e implementa a interface ControleTelas do package control
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class TelaCadastroFuncionario implements ControleTelas, EventHandler<ActionEvent> {

	/** Propriedade btOk */
	private Button btOk;
	
	/** Propriedade btAlterar */
	private Button btAlterar;
	
	/** Propriedade btCancelar */
	private Button btCancelar;
	
	/** Propriedade tfNome */
	private TextField tfNome;
	
	/** Propriedade tfTelefone */
	private TextField tfTelefone;
	
	/** Propriedade tfCpf */
	private TextField tfCpf;
	
	/** Propriedade tfRua */
	private TextField tfRua;
	
	/** Propriedade tfNum */
	private TextField tfNum;
	
	/** Propriedade tfBairro */
	private TextField tfBairro;
	
	/** Propriedade tfCidade */
	private TextField tfCidade;
	
	/** Propriedade tfComplemento */
	private TextField tfComplemento;
	
	/** Propriedade tfCep */
	private TextField tfCep;
	
	/** Propriedade tfEmail */
	private TextField tfEmail;
	
	/** Propriedade tfDtnasc */
	private TextField tfDtnasc;
	
	/** Propriedade tfNumMatricula */
	private TextField tfNumMatricula;
	
	/** Propriedade tfDataAdmicao */
	private TextField tfDataAdmicao;
	
	/** Propriedade Funcionario f */
	private Funcionario f = null;
	
	/** Propriedade Endereco e */
	private Endereco e;
	
	/** Propriedade Telefone t */
	private Telefone t;
	
	/** Propriedade ComboBox cbEstado */
	private ComboBox<String> cbEstado;
	
	/** Propriedade ComboBox cbTipoTelefone */
	private ComboBox<String> cbTipoTelefone;
	
	/** Propriedade ComboBox cbCargo*/
	private ComboBox<String> cbCargo;
	
	/** Propriedade ControleFuncionario cf */
	private ControleFuncionario cf = new ControleFuncionario();
	
	/** Propriedade TelaPesquisaFunc telaPesquisaFunc */
	private TelaPesquisaFunc telaPesquisaFunc;

	
	TelaCadastroFuncionario() {
	}

	/**
	 * TelaCadastroFuncionario
	 * 
	 * @param f
	 * @param telaPesquisaFunc
	 */
	TelaCadastroFuncionario(Funcionario f, TelaPesquisaFunc telaPesquisaFunc) {
		this.f = f;
		this.telaPesquisaFunc = telaPesquisaFunc;
	}

	/**
	 * Método handle - acao aos botoes - adicionar e alterar funcionario e cancelar acao
	 */
	@Override
	public void handle(ActionEvent ev) {

		if (ev.getTarget() == btOk && verificaCampos()) {
			if (verificaDuplicata()) {
				addFuncio();
				limpaCampos();
				Mensagens.informacao("Funcionario cadastrado", "O funcionario foi cadastrado com sucesso", "");
			}

		} else if (ev.getSource() == btAlterar) {
			DadosParaEntidades();
			try {
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date data = formato.parse(tfDtnasc.getText().replaceAll("-", "/"));
				f.setDataNascimento(data);
				data = formato.parse(tfDataAdmicao.getText().replaceAll("-", "/"));
				f.setDataAdmissao(data);
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			try {
				cf.alterarFuncionario(f, e, t);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Mensagens.informacao("Alteracao de funcionario", "O funcionario foi alterado com sucesso", "");
			telaPesquisaFunc.carregaTabela();
			telaPesquisaFunc.stage.close();

		} else if (ev.getTarget() == btCancelar) {
			limpaCampos();
		}

	}

	/**
	 * Painel Render
	 */
	@Override
	public Pane render() {
		HBox painel = new HBox();

		VBox vbEs = new VBox();
		vbEs.setPadding(new Insets(17, 12, 15, 12));
		vbEs.setSpacing(15.3);

		VBox vbDi = new VBox();
		vbDi.setPadding(new Insets(15, 12, 15, 12));
		vbDi.setSpacing(7);

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
		vbEs.getChildren().add(new Label("Cargo: "));
		vbEs.getChildren().add(new Label("Numero Matricula: "));
		vbEs.getChildren().add(new Label("Data Adimição: "));

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
		tfComplemento = new TextField();
		tfCep = new TextField();
		Mascaras.mascaraCEP(tfCep);
		tfEmail = new TextField();
		tfDtnasc = new TextField();
		Mascaras.mascaraData(tfDtnasc);
		ObservableList<String> options = FXCollections.observableArrayList("GERENTE", "FUNCIONARIO");
		cbCargo = new ComboBox<String>(options);
		tfNumMatricula = new TextField();
		Mascaras.mascaraApenasNum(tfNumMatricula);
		tfDataAdmicao = new TextField();
		Mascaras.mascaraData(tfDataAdmicao);
		cbEstado = new ComboBox<String>();
		cbEstado.getItems().addAll("SP", "Rj", "MG");
		cbEstado.setPrefWidth(80);
		cbEstado.getSelectionModel().select(0);

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
		vbDi.getChildren().add(cbEstado);
		vbDi.getChildren().add(tfComplemento);
		vbDi.getChildren().add(tfCep);
		vbDi.getChildren().add(tfEmail);
		vbDi.getChildren().add(tfDtnasc);
		vbDi.getChildren().add(cbCargo);
		vbDi.getChildren().add(tfNumMatricula);
		vbDi.getChildren().add(tfDataAdmicao);

		// Verifica se a tela está sendo chamada pra cadastrar ou alterar
		if (f != null) {
			carregaDadosCampos(f);
			btAlterar = new Button("Alterar");
			btAlterar.addEventHandler(ActionEvent.ACTION, this);
			hbBotao.getChildren().add(btAlterar);
		} else {
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

	/**
	 * Gerenciador Principal
	 */
	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

	/**
	 * Método que adiciona dados do funcionario
	 */
	private void addFuncio() {
		f = new Funcionario();
		t = new Telefone();
		e = new Endereco();
		DadosParaEntidades();
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date data = formato.parse(tfDtnasc.getText());
			f.setDataNascimento(data);
			data = formato.parse(tfDataAdmicao.getText());
			f.setDataAdmissao(data);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		try {

			cf.addFuncionario(f, e, t);
			f = null; // não tire isso
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Método DadosParaEntidades
	 */
	private void DadosParaEntidades() {

		e.setNumero(Integer.parseInt(tfNum.getText()));
		e.setBairro(tfBairro.getText());
		e.setRua(tfRua.getText());
		e.setCidade(tfCidade.getText());
		e.setEstado(cbEstado.getSelectionModel().getSelectedItem());
		e.setComplemento(tfComplemento.getText());
		e.setCep(tfCep.getText().replaceAll("[-]", ""));
		t.setTipo(cbTipoTelefone.getSelectionModel().getSelectedItem());
		String telefone = tfTelefone.getText().replaceAll("[()-]", "");
		t.setDdd(telefone.substring(0, 2));
		t.setNumero(telefone.substring(2, telefone.length()));
		f.setNome(tfNome.getText());
		f.setCpf(tfCpf.getText().replaceAll("[.-]", ""));
		f.setEmail(tfEmail.getText());
		f.setCargo(cbCargo.getSelectionModel().getSelectedItem());
		f.setMatricula(tfNumMatricula.getText());

		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
			Date data = formato.parse(tfDtnasc.getText().replaceAll("-", "/"));
			f.setDataNascimento(data);
			data = formato.parse(tfDataAdmicao.getText().replaceAll("-", "/"));
			f.setDataAdmissao(data);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

	}

	/**
	 * Método que verifica se os campos foram preenchidos corretamente.
	 * 
	 * @return true or false 
	 */
	private boolean verificaCampos() {

		if (tfNome.getText().equals("")) {

			Mensagens.erro("Nome erro", "Nome invalido", "Digite um nome");

			return false;

		} else if (tfCpf.getText().equals("") || tfCpf.getText().length() != 14) {

			Mensagens.erro("CPF erro", "CPF invalida", "Digite um CPF");

			return false;

		} else if (tfTelefone.getText().equals("") || tfTelefone.getText().replaceAll("[()-]", "").length() <= 7) {

			Mensagens.erro("Telefone erro", "Telefone invalido", "Digite um Telefone");

			return false;

		} else if (tfRua.getText().equals("")) {

			Mensagens.erro("Rua erro", "Rua invalida", "Digite uma Rua");

			return false;

		} else if (tfNum.getText().equals("") || Integer.parseInt(tfNum.getText()) <= 0) {
			Mensagens.erro("Numero erro", "Numero invalido", "Digite um Numero");

			return false;

		} else if (tfBairro.getText().equals("")) {

			Mensagens.erro("Bairro erro", "Bairro invalida", "Digite um Bairro");

			return false;

		} else if (tfEmail.getText().equals("") || (!tfEmail.getText().matches("^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+(?:[a-zA-Z]{2}|aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel)$"))) {

			Mensagens.erro("Email erro", "Email invalida", "Digite um Email");

			return false;

		} else if (tfCep.getText().equals("") || tfCep.getText().replaceAll("[-]", "").length() != 8) {
			Mensagens.erro("Cep erro", "Cep invalido", "Digite um Cep");

			return false;
		} else if (cbCargo.getSelectionModel().isEmpty()) {
			Mensagens.erro("Cargo erro", "Cargo invalido", "Selecione um cargo");

			return false;
		} else if (tfNumMatricula.getText().equals("") || Integer.parseInt(tfNum.getText()) <= 0) {
			Mensagens.erro("Num de matricula erro", "Matricula invalido", "Insira uma matricula");

			return false;
		} else {

			try {

				Date data = Data.parseDate(tfDtnasc.getText());
				Date dataAtual = new Date();
				if (data.compareTo(dataAtual) > 0) {
					Mensagens.erro("Data erro", "Data invalida", "Digite uma Data valida");
					return false;
				}

			} catch (ParseException e) {

				Mensagens.erro("Data erro", "Data invalida", "Digite uma Data valida");
				return false;
			}

			try {

				Date data = Data.parseDate(tfDataAdmicao.getText());
				Date dataAtual = new Date();
				if (data.compareTo(dataAtual) > 0) {
					Mensagens.erro("Data erro", "Data de adimissão invalida", "Digite uma Data valida");
					return false;
				}

			} catch (ParseException e) {

				Mensagens.erro("Data erro", "Data de adimissão invalida", "Digite uma Data valida");
				return false;
			}
		}

		return true;

	}

	/**
	 * Método que limpa os campos
	 */
	public void limpaCampos() {
		tfNome.setText("");
		tfTelefone.setText("");
		tfCpf.setText("");
		tfRua.setText("");
		tfNum.setText("");
		tfBairro.setText("");
		tfCidade.setText("");
		tfComplemento.setText("");
		tfCep.setText("");
		tfEmail.setText("");
		tfDtnasc.setText("");
		tfNumMatricula.setText("");
		tfDataAdmicao.setText("");
		cbCargo.setValue(null);
		cbEstado.setValue(null);
		cbTipoTelefone.setValue(null);
	}

	/**
	 * Método que carrega os dados do funcionario
	 * 
	 * @param f
	 */
	public void carregaDadosCampos(Funcionario f) {

		try {
			e = cf.buscaEnderecoFunc(f.getFkEdetecoPessoa());
			t = cf.buscaTelefoneFunc(f.getIdPessoa());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		tfNome.setText(f.getNome());
		tfTelefone.setText(t.getDdd() + t.getNumero());
		tfCpf.setText(f.getCpf());
		tfRua.setText(e.getRua());
		tfNum.setText(Integer.toString(e.getNumero()));
		tfBairro.setText(e.getBairro());
		tfCidade.setText(e.getCidade());
		tfComplemento.setText(e.getComplemento());
		tfCep.setText(e.getCep());
		tfEmail.setText(f.getEmail());
		tfDtnasc.setText(formatador.format(f.getDataNascimento()));
		tfNumMatricula.setText(f.getMatricula());
		tfDataAdmicao.setText(formatador.format(f.getDataAdmissao()));
		cbTipoTelefone.setValue(t.getTipo());
		cbCargo.setValue(f.getCargo());

	}

	/**
	 * Método que verifica se tem dados duplicados. 
	 * @return true or false
	 */
	private boolean verificaDuplicata() {
		try {
			PessoaDao pDao = new PessoaDao();
			if (pDao.verificaDuplicCpf(tfCpf.getText())) {
				Mensagens.erro("Cpf erro", "Cpf inválido", "Cpf inválido ou já cadastrado");
				return false;
			} else if (pDao.verificaDuplicEmail(tfEmail.getText())) {
				Mensagens.erro("Email erro", "Email inválido", "Email inválido ou já cadastrado");
				return false;
			} else {
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

}
