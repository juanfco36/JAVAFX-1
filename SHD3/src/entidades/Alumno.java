package entidades;

import modelo.Conexion;

public class Alumno {
	
	private int idalumno;
	private String nombre;
	private String amaterno;
	private String apaterno;
	private String fecha;
	private String sexo;
	private Carrera Ocarrera;
	private Conexion cn;
	
	
	public Alumno(){
		this.idalumno=0;
		this.nombre="";
		this.amaterno="";
		this.apaterno="";
		this.fecha="";
		this.sexo="";
		this.cn= new Conexion();
	}


	public Alumno(String nombre, String apaterno, String amaterno , String fecha,
			String sexo, Carrera Ocarrera) {
		// TODO Auto-generated constructor stub
		this.nombre=nombre;
		this.apaterno=apaterno;
		this.amaterno=amaterno;
		this.fecha=fecha;
		this.sexo=sexo;
		this.Ocarrera=Ocarrera;
	}


	public int getIdalumno() {
		return idalumno;
	}


	public void setIdalumno(int idalumno) {
		this.idalumno = idalumno;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getAmaterno() {
		return amaterno;
	}


	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}


	public String getApaterno() {
		return apaterno;
	}


	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public Carrera getOcarrera() {
		return Ocarrera;
	}


	public void setOcarrera(Carrera ocarrera) {
		Ocarrera = ocarrera;
	}


	public Conexion getCn() {
		return cn;
	}


	public void setCn(Conexion cn) {
		this.cn = cn;
	}

	
	public String toString (){
		return this.idalumno+" "+this.nombre+" "+this.amaterno+" "+this.apaterno+" "+this.sexo+" "+this.fecha+" "+this.Ocarrera.getNombre();
	}
	
}
