package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


import entidades.Alumno;
import entidades.Carrera;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class cdAlumno {
	
	private Conexion cn;
	
	public cdAlumno(){
			this.cn= new Conexion();
		
	}
	
	public String guardar(Alumno c){
		String mensaje="";
		try{
			if(cn.conectar()==true){
				String query="insert into alumno values (default,?,?,?,?,?,?)";
				PreparedStatement comando = cn.getConexion().prepareStatement(query);
				comando.setString(1, c.getNombre());
				comando.setString(2, c.getApaterno());
				comando.setString(3, c.getAmaterno());
				comando.setString(4, c.getFecha());
				comando.setString(5, c.getSexo());
				comando.setInt(6, c.getOcarrera().getIdcarrera());
				comando.executeUpdate();
				JOptionPane.showMessageDialog(null, "Datos guardados");
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Datos no guardados");
		}finally{
			cn.desconectar();
		}
		
		return mensaje;
		
	}
	
	public String eliminar(Alumno c){
		String mensaje="";
		try{
			if(cn.conectar()){
				String query="delete from alumno where idalumno="+c.getIdalumno();
				Statement comando= cn.getConexion().createStatement();
				comando.executeUpdate(query);
				JOptionPane.showMessageDialog(null, "Datos eliminados");
			}
			
		}catch(Exception e1){
			System.out.println(e1.getMessage());
			JOptionPane.showMessageDialog(null, "No se eliminaron");
		}finally{
			cn.desconectar();
		}
		return mensaje;
	}
	
	public String modificar(Alumno c){
		String mensaje="";
		try{
			if(cn.conectar()==true){
				String query="update Alumno set nombre=?, apaterno=?, amaterno=?, fechanac=?, sexo=?, idcarrera=?" +
						" where idAlumno="+c.getIdalumno();
				PreparedStatement comando = cn.getConexion().prepareStatement(query);
				comando.setString(1, c.getNombre());
				comando.setString(2, c.getApaterno());
				comando.setString(3, c.getAmaterno());
				comando.setString(4, c.getFecha());
				comando.setString(5, c.getSexo());
				comando.setInt(6, c.getOcarrera().getIdcarrera());
				comando.executeUpdate();
				JOptionPane.showMessageDialog(null, "Datos modificados");
			}
		}catch(Exception e1){
			System.out.println(e1.getMessage());
			JOptionPane.showMessageDialog(null, "Datos no modificados");
		}finally{
			cn.desconectar();
		}
		return mensaje;

	}
	
public 	ObservableList<Alumno> lista () throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
	ObservableList< Alumno> lc = FXCollections.observableArrayList();
	Conexion cn = new Conexion();
	cn.conectar();
	String sql="select idalumno,alumno.nombre,apaterno,amaterno,fechanac,sexo,carrera.nombre as carrera, carrera.carrera from " +
			" alumno inner join carrera on carrera.idcarrera=alumno.idcarrera ";
	System.out.println(sql);
	Statement comando= cn.getConexion().createStatement();
	ResultSet resultado= comando.executeQuery(sql);
	while(resultado.next()){
		Alumno a = new Alumno();
		Carrera c = new Carrera();
		c.setIdcarrera(resultado.getInt("idcarrera"));
		c.setNombre(resultado.getString("nombre"));
		
		a.setIdalumno(resultado.getInt("idalumno"));
		a.setNombre(resultado.getString("nombre"));
		a.setApaterno(resultado.getString("apaterno"));
		a.setAmaterno(resultado.getString("amaterno"));
		a.setFecha(resultado.getString("fechanac"));
		a.setSexo(resultado.getString("sexo"));
		a.setOcarrera(c);
		
		lc.add(a);
	}
	
	cn.desconectar();
	return lc;
	
}

public 	ObservableList<Carrera> Combo() throws ClassNotFoundException, SQLException{
	ObservableList< Carrera> lc = FXCollections.observableArrayList();
	Conexion cn = new Conexion();
	cn.conectar();
	String sql="select  idcarrera,nombre from carrera";
	System.out.println("yaaa");
	Statement comando= cn.getConexion().createStatement();
	ResultSet resultado= comando.executeQuery(sql);
	while(resultado.next()){
		Carrera c = new Carrera();
		c.setIdcarrera(resultado.getInt("idcarrera"));
		c.setNombre(resultado.getString("nombre"));
		
		lc.add(c);
	}
	
	cn.desconectar();
	return lc;
	
}

}
