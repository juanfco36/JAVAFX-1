package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import entidades.Carrera;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class cdCarrera {
	
	private Conexion cn;
	
	public cdCarrera(){
			this.cn= new Conexion();
		
	}
	
	public String guardar(Carrera c){
		String mensaje="";
		try{
			if(cn.conectar()==true){
				String query="insert into carrera values (default,?,?,?,?,?)";
				PreparedStatement comando = cn.getConexion().prepareStatement(query);
				comando.setString(1, c.getNombre());
				comando.setString(2, c.getSiglas());
				comando.setString(3, c.getJefeC());
				comando.setInt(4, c.getMatricula());
				comando.setString(5, c.getAcreditado());
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
	
	public String eliminar(Carrera c){
		String mensaje="";
		try{
			if(cn.conectar()){
				String query="delete from carrera where idcarrera="+c.getIdcarrera();
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
	
	public String modificar(Carrera c){
		String mensaje="";
		try{
			if(cn.conectar()==true){
				String query="update carrera set nombre=?, siglas=?, jefec=?, matricula=?, acreditado=?" +
						" where idcarrera="+c.getIdcarrera();
				PreparedStatement comando = cn.getConexion().prepareStatement(query);
				comando.setString(1, c.getNombre());
				comando.setString(2, c.getSiglas());
				comando.setString(3, c.getJefeC());
				comando.setInt(4, c.getMatricula());
				comando.setString(5, c.getAcreditado());
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
public 	ObservableList<Carrera> lista () throws ClassNotFoundException, SQLException{
	ObservableList<Carrera> lc = FXCollections.observableArrayList();
	Conexion cn = new Conexion();
	cn.conectar();
	Carrera c = new Carrera();
	String sql="select idcarrera,nombre,siglas,jefec,matricula,acreditado from carrera";
	PreparedStatement comando= cn.getConexion().prepareStatement(sql);
	ResultSet resultado= comando.executeQuery(sql);
	while(resultado.next()){
		c.setIdcarrera(resultado.getInt("idcarrera"));
		c.setNombre(resultado.getString("nombre"));
		c.setSiglas(resultado.getString("siglas"));
		c.setJefeC(resultado.getString("jefec"));
		c.setMatricula(resultado.getInt("matricula"));
		c.setAcreditado(resultado.getString("acreditado"));
		lc.add(c);
	}
	
	cn.desconectar();
	return lc;
	
}

}
