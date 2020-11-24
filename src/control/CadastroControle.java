package control;

import javax.swing.JOptionPane;

import boundary.TelaCadastroCliente;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadastroControle {

	private Button btOk;
	private Button btCancelar;
	private TextField tfNome;
	private TextField tfTelefone;
	private TextField tfCpf;
	private TextField tfNum;
	private TextField tfRua;
	private TextField tfLogradouro;
	private TextField tfBairro;
	private TextField tfEmail;
	private TextField tfDtnasc;

	public void acaoProfessor(ActionEvent event) {

		String cmd = event.getSource().toString();
		System.out.println(cmd);

		ControleCliente clienteController = new ControleCliente(tfNome, tfCpf, tfTelefone, tfNum, tfRua, tfLogradouro,
				tfBairro, tfEmail, tfDtnasc);

		if ((cmd.contains("Inserir") || cmd.contains("Atualizar"))
				&& (tfCpf.getText().isEmpty() || tfNome.getText().isEmpty() || tfTelefone.getText().isEmpty())) {
			JOptionPane.showMessageDialog(null, "Preencha os campos", "ERRO", JOptionPane.ERROR_MESSAGE);
		}

	}

}
