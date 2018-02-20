package modelo;

import java.util.Date;

public class Prestamo {
	private int idPrestamo;
	private Libro libro;
	private Usuario usuario;
	private Date fechaPrestamo;
	private Date fechaLimite;
	private boolean entregado;
	

	/**
	 * @return the idPrestamo
	 */
	public int getIdPrestamo() {
		return idPrestamo;
	}
	/**
	 * @param idPrestamo the idPrestamo to set
	 */
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	
	/**
	 * @return the libro
	 */
	public Libro getLibro() {
		return libro;
	}
	/**
	 * @param libro the libro to set
	 */
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the fechaPrestamo
	 */
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}
	/**
	 * @param fechaPrestamo the fechaPrestamo to set
	 */
	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	/**
	 * @return the fechaLimite
	 */
	public Date getFechaLimite() {
		return fechaLimite;
	}
	/**
	 * @param fechaLimite the fechaLimite to set
	 */
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	/**
	 * @return the entregado
	 */
	public boolean isEntregado() {
		return entregado;
	}
	/**
	 * @param entregado the entregado to set
	 */
	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
	
	
	
}
