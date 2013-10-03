package vista.controlador;

import java.awt.TrayIcon.MessageType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;


import com.sun.javafx.font.AmbleMapper;

import principal.acceso;


import modelo.cdAlumno;
import modelo.cdCarrera;


import entidades.Alumno;
import entidades.Carrera;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
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

public class ControladorAccesoAlumno implements Initializable  {

	
	@FXML private Button btnguardar;
	@FXML private Button btneliminar;
	@FXML private Button btnModificar;
	@FXML private TextField txtnombre;
	@FXML private  TextField txtApaterno;
	@FXML private TextField txtmaterno;
	@FXML private TextField txtfechanac;
	@FXML private ComboBox<Carrera> cboCarrera;
	@FXML private ComboBox<String> cbosexo;

	@FXML TableView<Alumno> jtable;
	ObservableList<Alumno> lista;
	ObservableList<Carrera> lista2;

	Alumno alumnomodificado = null;
	cdAlumno alumno= new cdAlumno();
	
	
	
	
	public void Limpiar(){
		txtApaterno.setText("");
		txtmaterno.setText("");
		txtnombre.setText("");
		txtfechanac.setText("");
		cbosexo.setValue("");
	}
	
	
	public void LlenarTabla(){
		
		/*cdAlumno a = new cdAlumno();
		try {
			lista=a.lista();
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jtable.setItems(lista);
		*/
	}
	
public void Combo(){
		
		cdAlumno a = new cdAlumno();
		try {
			lista2=a.Combo();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cboCarrera.setItems(lista2);
		
	}

	public void select(){
		alumnomodificado=jtable.getSelectionModel().getSelectedItem();
		if(alumnomodificado!=null){
			txtnombre.setText(alumnomodificado.getNombre());
			txtApaterno.setText(alumnomodificado.getApaterno());
			txtmaterno.setText(alumnomodificado.getAmaterno());
			txtfechanac.setText(alumnomodificado.getFecha());
			cbosexo.setValue(alumnomodificado.getSexo());
			cboCarrera.setValue(alumnomodificado.getOcarrera());

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

			if(txtnombre.getText().trim().isEmpty() | txtApaterno.getText().trim().isEmpty()
					| txtmaterno.getText().trim().isEmpty() | txtfechanac.getText().trim().isEmpty()| cbosexo.getValue().trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "Faltan datos por ingresar");
				
			}else{
				
				alumno.guardar(new Alumno(txtnombre.getText(), txtApaterno.getText(),
						txtmaterno.getText(),txtfechanac.getText(),cbosexo.getValue(),(Carrera)cboCarrera.getValue()));
			}
			LlenarTabla();
			Limpiar();
		}catch(Exception e1){
			e1.getMessage();
		}
		
	}
	
	@FXML protected void eliminar(ActionEvent e){
		if(txtnombre.getText().trim().isEmpty() | txtApaterno.getText().trim().isEmpty()
				| txtmaterno.getText().trim().isEmpty() | txtfechanac.getText().trim().isEmpty()| cbosexo.getValue().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Faltan datos por ingresar");	
		}else{
		if(alumnomodificado!=null){
			
				alumno.eliminar(alumnomodificado);
				
			
			alumnomodificado=null;
			Limpiar();
			LlenarTabla();
		}
		}	
	}
	
	@FXML protected void modificar(ActionEvent e){
		if(txtnombre.getText().trim().isEmpty() | txtApaterno.getText().trim().isEmpty()
				| txtmaterno.getText().trim().isEmpty() | txtfechanac.getText().trim().isEmpty()| cbosexo.getValue().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Faltan datos por ingresar");
			
		}else{
		if(alumnomodificado!=null){
		
				
				
				alumnomodificado.setNombre(txtnombre.getText());
				alumnomodificado.setApaterno(txtApaterno.getText());
				alumnomodificado.setAmaterno(txtmaterno.getText());
				alumnomodificado.setFecha(txtfechanac.getText());
				alumnomodificado.setSexo(cbosexo.getValue());
				alumnomodificado.setOcarrera((Carrera)cboCarrera.getValue());
				alumno.modificar(alumnomodificado);
				
			
alumnomodificado=null;
			Limpiar();
			LlenarTabla();
		}
		}	
	}
	

	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TableColumn<Alumno, String> nombre = new TableColumn<Alumno,String>("NOMBRE");
		TableColumn<Alumno, String> paterno = new TableColumn<Alumno,String>("PATERNO");
		TableColumn<Alumno, String> materno = new TableColumn<Alumno,String>("MATERNO");
		TableColumn<Alumno, Integer> fecha = new TableColumn<Alumno,Integer>("FECHA NAC");
		TableColumn<Alumno, String> sexo = new TableColumn<Alumno,String>("SEXO");
		TableColumn<Alumno, Integer> carrera = new TableColumn<Alumno,Integer>("CARRERA");

		
		nombre.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nombre"));
		paterno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apaterno"));
		materno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("amaterno"));
		fecha.setCellValueFactory(new PropertyValueFactory<Alumno,Integer>("fecha"));
		sexo.setCellValueFactory(new PropertyValueFactory<Alumno,String>("sexo"));
		carrera.setCellValueFactory(new PropertyValueFactory<Alumno,Integer>("Ocarrera"));

		jtable.getColumns().addAll(nombre,paterno,materno,fecha,sexo,carrera);
		
		LlenarTabla();
		Combo();
		jtable.setOnMouseClicked(new Click());
		
	
	}
}
