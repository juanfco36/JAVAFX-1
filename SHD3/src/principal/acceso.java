package principal;

import java.awt.TrayIcon.MessageType;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class acceso extends Application {
	
	@FXML private Button salir;
	@FXML private Button btnCarrera;
	@FXML private Button alumno;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent acceso = FXMLLoader.load(getClass().getResource("../vista/fxml/menu.fxml"));
		Scene scene = new Scene(acceso);
		primaryStage.setTitle("Menu");
	
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("../vista/estilo/DarkTheme.css").toExternalForm());

		primaryStage.show();
	}

	@FXML protected void menu(ActionEvent evnt) throws IOException{
		Stage primaryStage = new Stage();
		Parent acceso = FXMLLoader.load(getClass().getResource("../vista/fxml/carrera.fxml"));
		Scene scene = new Scene(acceso);
		primaryStage.setTitle("Carrera");
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("../vista/estilo/DarkTheme.css").toExternalForm());
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.initOwner(((Node)evnt.getSource()).getScene().getWindow());
		primaryStage.show();
	}
	
	@FXML protected void alumno(ActionEvent evnt) throws IOException{
		Stage primaryStage = new Stage();
		Parent acceso = FXMLLoader.load(getClass().getResource("../vista/fxml/alumno.fxml"));
		Scene scene = new Scene(acceso);
		primaryStage.setTitle("Alumno");
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("../vista/estilo/DarkTheme.css").toExternalForm());
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.initOwner(((Node)evnt.getSource()).getScene().getWindow());
		primaryStage.show();
	}
	
	@FXML protected void salir1(){
		
				System.exit(0);
	
	}	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
