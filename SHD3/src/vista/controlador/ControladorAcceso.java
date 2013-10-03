package vista.controlador;

import java.awt.TrayIcon.MessageType;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

import principal.acceso;

import sun.awt.AWTAccessor.KeyEventAccessor;

import modelo.cdCarrera;


import entidades.Carrera;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorAcceso implements Initializable  {

	
	@FXML Button btnguardar;
	@FXML Button btneliminar;
	@FXML Button btnmodificar;
	@FXML Button btnsalir;
	@FXML TextField txtnombre;
	@FXML TextField txtsiglas;
	@FXML TextField txtMatricula;
	@FXML TextField txtJefeCarrera;
	@FXML ComboBox<String> cboAcreditado;
	@FXML TableView<Carrera> jtable;
	ObservableList<Carrera> lista;
	Carrera carreraModificada = null;
	cdCarrera carrera= new cdCarrera();
	
	
	
	
	public void Limpiar(){
		txtJefeCarrera.setText("");
		txtMatricula.setText("");
		txtnombre.setText("");
		txtsiglas.setText("");
		cboAcreditado.setValue("");
	}
	
	
	public void LlenarTabla(){
		
		/*cdCarrera c = new cdCarrera();
		try {
			lista=c.lista();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		jtable.setItems(lista);*/
		
	}
	public void select(){
		carreraModificada=jtable.getSelectionModel().getSelectedItem();
		if(carreraModificada!=null){
			txtnombre.setText(carreraModificada.getNombre());
			txtsiglas.setText(carreraModificada.getSiglas());
			txtJefeCarrera.setText(carreraModificada.getJefeC());
			txtMatricula.setText(String.valueOf(carreraModificada.getMatricula()));
			cboAcreditado.setValue(carreraModificada.getAcreditado());
		}
	}
	
	public class Click implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getClickCount()==2){
				select();
			}
			
		}
		
	}
	


	
	
	 
		
	@FXML protected void guardar (ActionEvent e){

		try{

			if(txtJefeCarrera.getText().trim().isEmpty() | txtMatricula.getText().trim().isEmpty()
					| txtnombre.getText().trim().isEmpty() | txtsiglas.getText().trim().isEmpty()| cboAcreditado.getValue().trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "Faltan datos por ingresar");
				
			}else{
				
				carrera.guardar(new Carrera(txtnombre.getText(), txtsiglas.getText(),
						txtJefeCarrera.getText(),Integer.valueOf(txtMatricula.getText()),cboAcreditado.getValue()));
			}
			LlenarTabla();
			Limpiar();
		}catch(Exception e1){
			e1.getMessage();
		}
		
	}
	
	@FXML protected void eliminar(ActionEvent e){
		if(txtJefeCarrera.getText().trim().isEmpty() | txtMatricula.getText().trim().isEmpty()
				| txtnombre.getText().trim().isEmpty() | txtsiglas.getText().trim().isEmpty()| cboAcreditado.getValue().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Campos vacios");
			
		}else{
		if(carreraModificada!=null){
		
				
				carrera.eliminar(carreraModificada);
			
			carreraModificada=null;
			Limpiar();
			LlenarTabla();
		}
		}	
	}
	
	@FXML protected void modificar(ActionEvent e){
		if(txtJefeCarrera.getText().trim().isEmpty() | txtMatricula.getText().trim().isEmpty()
				| txtnombre.getText().trim().isEmpty() | txtsiglas.getText().trim().isEmpty() | cboAcreditado.getValue().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Campos vacios");
			
		}else{
		if(carreraModificada!=null){
			
				carreraModificada.setNombre(txtnombre.getText());
				carreraModificada.setSiglas(txtsiglas.getText());
				carreraModificada.setJefeC(txtJefeCarrera.getText());
				carreraModificada.setMatricula(Integer.valueOf(txtMatricula.getText()));
				carreraModificada.setAcreditado(cboAcreditado.getValue());
				carrera.modificar(carreraModificada);
				
			
			carreraModificada=null;
			Limpiar();
			LlenarTabla();
		}
		}	
	}
	@FXML protected void salir(){
	
				System.exit(0);
	
	}	
	

	
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TableColumn<Carrera, Integer> id = new TableColumn<Carrera,Integer>("ID");
		TableColumn<Carrera, String> nombre = new TableColumn<Carrera,String>("NOMBRE");
		TableColumn<Carrera, String> siglas = new TableColumn<Carrera,String>("SIGLAS");
		TableColumn<Carrera, String> jefe = new TableColumn<Carrera,String>("JEFE");
		TableColumn<Carrera, Integer> Matricula = new TableColumn<Carrera,Integer>("MATRICULA");
		TableColumn<Carrera, String> acreditada = new TableColumn<Carrera,String>("ACREDITADO");
		
		id.setCellValueFactory(new PropertyValueFactory<Carrera,Integer>(""));
		nombre.setCellValueFactory(new PropertyValueFactory<Carrera,String>("nombre"));
		siglas.setCellValueFactory(new PropertyValueFactory<Carrera,String>("siglas"));
		jefe.setCellValueFactory(new PropertyValueFactory<Carrera,String>("jefeC"));
		Matricula.setCellValueFactory(new PropertyValueFactory<Carrera,Integer>("matricula"));
		acreditada.setCellValueFactory(new PropertyValueFactory<Carrera,String>("acreditado"));
		
		jtable.getColumns().add(id);
		jtable.getColumns().add(nombre);
		jtable.getColumns().add(siglas);
		jtable.getColumns().add(jefe);
		jtable.getColumns().add(Matricula);
		jtable.getColumns().add(acreditada);

		

		
		LlenarTabla();
		jtable.setOnMouseClicked(new Click());
			
	}
}
