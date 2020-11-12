import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cadastro_Cliente extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

HBox painel = new HBox();
		

		Scene cena = new Scene(painel, 600, 400);
		
		VBox vbEs = new VBox();
		vbEs.setPadding(new Insets(15, 12, 15, 12));
		vbEs.setSpacing(15);
		
		VBox vbDi = new VBox();
		vbDi.setPadding(new Insets(15, 12, 15, 12));
		vbDi.setSpacing(6.2);
						
		HBox hbBotao = new HBox();
		hbBotao.setSpacing(40);

		Button btOk = new Button("Cadastrar");
		Button btCancelar = new Button("Cancelar");

		hbBotao.getChildren().add(btOk);
		hbBotao.getChildren().add(btCancelar);
		
		vbEs.getChildren().add(new Label("Nome:"));
		vbEs.getChildren().add(new Label("Telefone:"));
		vbEs.getChildren().add(new Label("CPF:"));
		vbEs.getChildren().add(new Label("Número:"));
		vbEs.getChildren().add(new Label("Rua:"));
		vbEs.getChildren().add(new Label("Logradouro:"));
		vbEs.getChildren().add(new Label("Bairro:"));
		vbEs.getChildren().add(new Label("Email:"));
		vbEs.getChildren().add(new Label("Data de nascimento:"));
		
		vbEs.getChildren().add(hbBotao);
		
	
	
		TextField tfNome = new TextField();
		tfNome.setPrefWidth(330);
		TextField tfTelefone = new TextField();
		TextField tfCpf = new TextField();
		TextField tfNum = new TextField();
		TextField tfRua = new TextField();
		TextField tfLogradouro = new TextField();
		TextField tfBairro = new TextField();
		TextField tfEmail = new TextField();
		TextField tfDtnasc = new TextField();
		
		vbDi.getChildren().add(tfNome);
		vbDi.getChildren().add(tfTelefone);
		vbDi.getChildren().add(tfCpf);
		vbDi.getChildren().add(tfNum);
		vbDi.getChildren().add(tfRua);
		vbDi.getChildren().add(tfLogradouro);
		vbDi.getChildren().add(tfBairro);
		vbDi.getChildren().add(tfEmail);
		vbDi.getChildren().add(tfDtnasc);
		
				
		painel.getChildren().add(vbEs);
		painel.getChildren().add(vbDi);

		primaryStage.setTitle("Cadastro Cliente");
		primaryStage.setScene(cena);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(Cadastro_Cliente.class, args);
	}

}
