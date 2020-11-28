package boundary;

import java.sql.SQLException;
import java.text.ParseException;
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

public class TelaCadastroFuncionario implements ControleTelas, EventHandler<ActionEvent> {

	private Button btOk;
	private Button btCancelar;
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
	private TextField tfNumMatricula;
	private TextField tfDataAdmicao;
	private ComboBox<String> cbEstado;
	private ComboBox<String> cbTipoTelefone;
	private ComboBox<String> cbCargo;
	

	@Override
	public void handle(ActionEvent e) {
		
		if (e.getTarget() == btOk && verificaCampos()) {
			if(verificaDuplicata()) {
				addCliente();
				limpaCampos();
			}
			

		}

		if (e.getTarget() == btCancelar) {
			
		}
	}

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

		btOk = new Button("Cadastrar");
		btOk.addEventHandler(ActionEvent.ACTION, this);
		btCancelar = new Button("Cancelar");
		btCancelar.addEventHandler(ActionEvent.ACTION, this);

		hbBotao.getChildren().add(btOk);
		hbBotao.getChildren().add(btCancelar);

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

		vbEs.getChildren().add(hbBotao);
		


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
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "GERENTE",
			        "FUNCIONARIO"
			    );
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
		painel.getChildren().add(vbEs);
		painel.getChildren().add(vbDi);

		return painel;
	}

	@Override
	public void setGerenciadorPrincipal(GetenciadorPrincipal cat) {
		// TODO Auto-generated method stub

	}

	private void addCliente() {

		ControleFuncionario cf = new ControleFuncionario();

		Funcionario f = new Funcionario();
		Telefone t = new Telefone();
		Endereco e = new Endereco();

		e.setNumero(Integer.parseInt(tfNum.getText()));
		e.setBairro(tfBairro.getText());
		e.setRua(tfRua.getText());
		e.setCidade(tfCidade.getText());
		e.setEstado(cbEstado.getSelectionModel().getSelectedItem());
		e.setComplemento(tfComplemento.getText());
		e.setCep(tfCep.getText().replaceAll("[-]", ""));
		t.setTipo(cbTipoTelefone.getSelectionModel().getSelectedItem());
		String telefone = tfTelefone.getText().replaceAll("[()-]", "");
		t.setDdd(telefone.substring(0,2));
		t.setNumero(telefone.substring(2,telefone.length()));
		f.setNome(tfNome.getText());
		f.setCpf(tfCpf.getText().replaceAll("[.-]", ""));
		f.setEmail(tfEmail.getText());
		f.setCargo(cbCargo.getSelectionModel().getSelectedItem());
		f.setMatricula(tfNumMatricula.getText());
		

		try {
			f.setDataNascimento(Data.parseDate(tfDtnasc.getText()));
			f.setDataAdmissao(Data.parseDate(tfDataAdmicao.getText()));
		} catch (ParseException e2) {

		}

		try {
			cf.addFuncionario(f, e, t);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private boolean verificaCampos() {

		if (tfNome.getText().equals("")) {

			Mensagens.erro("Nome erro", "Nome invalido", "Digite um nome");

			return false;

		} else if (tfCpf.getText().equals("") || tfCpf.getText().length() != 14) {

			Mensagens.erro("CPF erro", "CPF invalida", "Digite um CPF");

			return false;

		}else if (tfTelefone.getText().equals("") || tfTelefone.getText().replaceAll("[()-]", "").length() <= 7) {

			Mensagens.erro("Telefone erro", "Telefone invalido", "Digite um Telefone");

			return false;

		} else if (tfRua.getText().equals("")) {

			Mensagens.erro("Rua erro", "Rua invalida", "Digite uma Rua");

			return false;

		} else if (tfNum.getText().equals("") || Integer.parseInt(tfNum.getText()) <= 0){
			System.out.println(Integer.parseInt(tfNum.getText()) < 0);
			Mensagens.erro("Numero erro", "Numero invalido", "Digite um Numero");

			return false;

		} else if (tfBairro.getText().equals("")) {

			Mensagens.erro("Bairro erro", "Bairro invalida", "Digite um Bairro");

			return false;

		} else if (tfEmail.getText().equals("")) {

			Mensagens.erro("Email erro", "Email invalida", "Digite um Email");

			return false;

		}else if(tfCep.getText().equals("") || tfCep.getText().replaceAll("[-]", "").length() != 8){
			Mensagens.erro("Cep erro", "Cep invalido", "Digite um Cep");

			return false;
		}else if(cbCargo.getSelectionModel().isEmpty()){
			Mensagens.erro("Cargo erro", "Cargo invalido", "Selecione um cargo");

			return false;			
		}else if(tfNumMatricula.getText().equals("") || Integer.parseInt(tfNum.getText()) <= 0){
			Mensagens.erro("Num de matricula erro", "Matricula invalido", "Insira uma matricula");

			return false;	
		}else {

			try {

				Date data = Data.parseDate(tfDtnasc.getText());
				Date dataAtual = new Date(); 
				if(data.compareTo(dataAtual) > 0) {
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
				if(data.compareTo(dataAtual) > 0) {
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
	

}
