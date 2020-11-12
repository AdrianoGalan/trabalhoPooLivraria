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

public class Cadastro_Autor extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

HBox painel = new HBox();
		

		Scene cena = new Scene(painel, 600, 200);
		
		VBox vbEs = new VBox();
		vbEs.setPadding(new Insets(15, 12, 15, 12));
		vbEs.setSpacing(15);
		
		VBox vbDi = new VBox();
		vbDi.setPadding(new Insets(15, 12, 15, 12));
		vbDi.setSpacing(5);
						
		HBox hbBotao = new HBox();
		hbBotao.setSpacing(40);

		Button btOk = new Button("Cadastrar");
		Button btCancelar = new Button("Cancelar");

		hbBotao.getChildren().add(btOk);
		hbBotao.getChildren().add(btCancelar);
		
		vbEs.getChildren().add(new Label("Nome:"));
		vbEs.getChildren().add(new Label("Nacionalidade:"));
		vbEs.getChildren().add(new Label("Data de nascimento:"));
		
		vbEs.getChildren().add(hbBotao);
		
	
	
		TextField tfNome = new TextField();
		tfNome.setPrefWidth(330);
		TextField tfNa = new TextField();
		TextField tfDtnasc = new TextField();
		
		vbDi.getChildren().add(tfNome);
		vbDi.getChildren().add(tfNa);
		vbDi.getChildren().add(tfDtnasc);
		
				
		painel.getChildren().add(vbEs);
		painel.getChildren().add(vbDi);

		primaryStage.setTitle("Cadastro Autor");
		primaryStage.setScene(cena);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(Cadastro_Autor.class, args);
	}

}
