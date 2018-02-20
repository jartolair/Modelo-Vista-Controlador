package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
	private int id;
	private String nombre;
	private String apellido;
	private int edad;
	private String dni;
	private Date fecha_nac;
	
	private ArrayList<Prestamo> prestamos;
	
	
	/**
	 * @return the prestamos
	 */
	public ArrayList<Prestamo> getPrestamos() {
		return prestamos;
	}
	/**
	 * @param prestamos the prestamos to set
	 */
	public void setPrestamos(ArrayList<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * @return the fecha_nac
	 */
	public Date getFecha_nac() {
		return fecha_nac;
	}
	/**
	 * @param fecha_nac the fecha_nac to set
	 */
	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	
	
	//Getters ta setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
}
