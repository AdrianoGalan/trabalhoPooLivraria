package control;

import javafx.scene.layout.Pane;

/**
 * Interface controle de telas
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public interface ControleTelas {
	
	Pane render();
	void setGerenciadorPrincipal(GetenciadorPrincipal cat);

}
