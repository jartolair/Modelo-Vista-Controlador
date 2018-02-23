package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;

public class PrestamoModelo extends Conector{
	
	public ArrayList<Prestamo> selectAll(){
		UsuarioModelo usuarioModelo=new UsuarioModelo();
		LibroModelo libroModelo=new LibroModelo();
		ArrayList<Prestamo> prestamos=new ArrayList<Prestamo>();
		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM prestamos");
			
			while (rs.next()){
				Prestamo prestamo=new Prestamo();
				prestamo.setIdPrestamo(rs.getInt("id_prestamo"));
				prestamo.setUsuario(usuarioModelo.select(rs.getInt("id_usuario")));
				prestamo.setLibro(libroModelo.select(rs.getInt("id_libro")));
				prestamo.setFechaPrestamo(rs.getDate("fecha_prestamo"));
				prestamo.setFechaLimite(rs.getDate("fecha_limite"));
				prestamo.setEntregado(rs.getBoolean("entregado"));
				
				prestamos.add(prestamo);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestamos;
		
	}
	
	public void insertar(Prestamo prestamo){
			try {
				PreparedStatement pst =super.conexion.prepareStatement("INSERT INTO prestamos(id_prestamo,id_libro,id_usuario,fecha_prestamo,fecha_limite,entregado) VALUES(?,?,?,?,?,?)");
				pst.setInt(1, prestamo.getIdPrestamo());
				pst.setInt(2, prestamo.getLibro().getId());
				pst.setInt(3, prestamo.getUsuario().getId());
				
				java.sql.Date sqlDatePres=cambiarUtilSql(prestamo.getFechaPrestamo());
				pst.setDate(4, sqlDatePres);
				java.sql.Date sqlDateLim=cambiarUtilSql(prestamo.getFechaLimite());
				pst.setDate(5, sqlDateLim);
				pst.setBoolean(6, prestamo.isEntregado());
				
				pst.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public Prestamo selectPorLibroUsuarioEntrega(Libro libro,Usuario usuario, boolean entregado){
		UsuarioModelo usuarioModelo=new UsuarioModelo();
		LibroModelo libroModelo=new LibroModelo();
		Prestamo prestamo=null;
		
		try {
			PreparedStatement pst=this.conexion.prepareStatement("SELECT * FROM prestamos WHERE id_libro=? and id_usuario=? and entregado=?");
			pst.setInt(1, libro.getId());
			pst.setInt(2,usuario.getId());
			pst.setBoolean(3, entregado);
			ResultSet rs=pst.executeQuery();
			if (rs.next()){
				prestamo =new Prestamo();
				prestamo.setIdPrestamo(rs.getInt("id_prestamo"));
				prestamo.setLibro(libroModelo.select(rs.getInt("id_libro")));
				prestamo.setUsuario(usuarioModelo.select(rs.getInt("id_usuario")));
				prestamo.setFechaPrestamo(rs.getDate("fecha_prestamo"));
				prestamo.setFechaLimite(rs.getDate("fecha_limite"));
				prestamo.setEntregado(rs.getBoolean("entregado"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prestamo;
	}
	
	public void update(Prestamo prestamo){
		UsuarioModelo usuarioModelo=new UsuarioModelo();
		LibroModelo libroModelo=new LibroModelo();
		try {
			PreparedStatement pst=this.conexion.prepareStatement("UPDATE prestamos SET id_libro=?, id_usuario=?, fecha_prestamo=?, fecha_limite=?, entregado=? WHERE id_prestamo=?");
			pst.setInt(1, prestamo.getLibro().getId());
			pst.setInt(2, prestamo.getUsuario().getId());
			Date fechaPres=cambiarUtilSql(prestamo.getFechaPrestamo());
			pst.setDate(3, fechaPres);
			Date fechaLimit=cambiarUtilSql(prestamo.getFechaLimite());
			pst.setDate(4, fechaLimit);
			pst.setBoolean(5, prestamo.isEntregado());
			
			pst.setInt(6, prestamo.getIdPrestamo());
			
			pst.execute();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Date cambiarUtilSql(java.util.Date utilDate) {
		// TODO Auto-generated method stub
		java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}

	public boolean estaDisponible(int idLibro) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst=this.conexion.prepareStatement("Select * from prestamos where id_libro=? and entregado=false");
			pst.setInt(1, idLibro);
			ResultSet rst=pst.executeQuery();
			if(rst.next()){
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
}
