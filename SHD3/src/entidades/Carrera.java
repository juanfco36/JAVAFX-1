package entidades;

import modelo.Conexion;

public class Carrera {
	
	private int idcarrera;
	private String nombre;
	private String siglas;
	private String jefeC;
	private int matricula;
	private String acreditado;
	private Conexion cn;

	
	public Carrera(){
		this.idcarrera=0;
		this.nombre="";
		this.siglas="";
		this.jefeC="";
		this.matricula=0;
		this.acreditado="";
		this.cn= new Conexion();
	}
	
	public Carrera(String nombre, String siglas,String jefeC,int matricula,String acreditado){
		setNombre(nombre);
		setSiglas(siglas);
		setJefeC(jefeC);
		setMatricula(matricula);
		setAcreditado(acreditado);
		
	}

	public int getIdcarrera() {
		return idcarrera;
	}

	public void setIdcarrera(int idcarrera) {
		this.idcarrera = idcarrera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public String getJefeC() {
		return jefeC;
	}

	public void setJefeC(String jefeC) {
		this.jefeC = jefeC;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getAcreditado() {
		return acreditado;
	}

	public void setAcreditado(String acreditado) {
		this.acreditado = acreditado;
	}
	
	public String toString(){
		return this.nombre;
	}


	
}
