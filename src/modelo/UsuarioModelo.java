package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;




public class UsuarioModelo extends Conector{
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	public ArrayList<Usuario> selectAll(){
		ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
		try {
			Statement st = super.conexion.createStatement();
			ResultSet rst=st.executeQuery("SELECT * FROM usuarios");
			while(rst.next()){
				Usuario usuario=new Usuario();
				usuario.setId(rst.getInt("id"));
				usuario.setNombre(rst.getString("nombre"));
				usuario.setApellido(rst.getString("apellido"));
				usuario.setEdad(rst.getInt("edad"));
				usuario.setDni(rst.getString("dni"));
				usuario.setFecha_nac(rst.getDate("fecha_nac"));
				
				usuarios.add(usuario);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
		
	}
	
	public Usuario select(int id){
		Statement st;
		Usuario usuario=null;
		try {
			st = conexion.createStatement();
			ResultSet rst=st.executeQuery("SELECT * FROM usuarios WHERE id="+id);
			while(rst.next()){
				usuario=new Usuario();
				usuario.setId(rst.getInt("id"));
				usuario.setNombre(rst.getString("nombre"));
				usuario.setApellido(rst.getString("apellido"));
				usuario.setEdad(rst.getInt("edad"));
				usuario.setDni(rst.getString("dni"));
				usuario.setFecha_nac(rst.getDate("fecha_nac"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return (usuario);

	}
	
	public void delete(int id){
		PreparedStatement pst;
		try {
			pst = conexion.prepareStatement("DELETE FROM usuarios WHERE id=?");
			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void update(Usuario usuario){
		try {
			PreparedStatement pst=conexion.prepareStatement("UPDATE usuarios SET nombre=?, apellido=?, edad=?, dni=?, fecha_nac=? WHERE id=?");
			pst.setString(1, usuario.getNombre());
			pst.setString(2, usuario.getApellido());
			pst.setInt(3, usuario.getEdad());
			pst.setString(4,usuario.getDni());
			//pst.setString(5, formatter.format(usuario.getFecha_nac()));
			
			java.util.Date utilDate =usuario.getFecha_nac();
			java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
			pst.setDate(5, sqlDate);
			pst.setInt(6, usuario.getId());
			
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void insertar(Usuario usuario){
		try {
			PreparedStatement pst=super.conexion.prepareStatement("INSERT INTO usuarios(nombre,apellido,edad,dni,fecha_nac) values(?,?,?,?,?)");
			pst.setString(1, usuario.getNombre());
			pst.setString(2, usuario.getApellido());
			pst.setInt(3, usuario.getEdad());
			pst.setString(4,usuario.getDni());
			pst.setString(5, formatter.format(usuario.getFecha_nac()));
			pst.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Usuario> selectPorNombre(String nombre){
		ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet rst=st.executeQuery("SELECT * FROM usuarios WHERE nombre="+nombre);
			while(rst.next()){
				Usuario usuario=new Usuario();
				usuario.setId(rst.getInt("id"));
				usuario.setNombre(rst.getString("nombre"));
				usuario.setApellido(rst.getString("apellido"));
				usuario.setEdad(rst.getInt("edad"));
				usuario.setDni(rst.getString("dni"));
				usuario.setFecha_nac(rst.getDate("fecha_nac"));
				
				usuarios.add(usuario);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public ArrayList<Usuario> selectMenorDeEdad(){
		ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet rst=st.executeQuery("SELECT * FROM usuarios WHERE edad<18");
			while(rst.next()){
				Usuario usuario=new Usuario();
				usuario.setId(rst.getInt("id"));
				usuario.setNombre(rst.getString("nombre"));
				usuario.setApellido(rst.getString("apellido"));
				usuario.setEdad(rst.getInt("edad"));
				usuario.setDni(rst.getString("dni"));
				usuario.setFecha_nac(rst.getDate("fecha_nac"));
				
				usuarios.add(usuario);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
	}
	public ArrayList<Usuario> selectContieneEnApellido(String cadena){
		ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet rst=st.executeQuery("SELECT * FROM usuarios WHERE apellido like %"+cadena+"%");
			while(rst.next()){
				Usuario usuario=new Usuario();
				usuario.setId(rst.getInt("id"));
				usuario.setNombre(rst.getString("nombre"));
				usuario.setApellido(rst.getString("apellido"));
				usuario.setEdad(rst.getInt("edad"));
				usuario.setDni(rst.getString("dni"));
				usuario.setFecha_nac(rst.getDate("fecha_nac"));
				
				usuarios.add(usuario);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
	}

	public Usuario selectPorDNI(String dni) {
		Statement st;
		Usuario usuario=null;
		try {
			PreparedStatement pst=super.conexion.prepareStatement("SELECT * FROM usuarios WHERE dni=?");
			pst.setString(1, dni);
			ResultSet rst=pst.executeQuery();
			
			if(rst.next()){
				usuario=new Usuario();
				usuario.setId(rst.getInt("id"));
				usuario.setNombre(rst.getString("nombre"));
				usuario.setApellido(rst.getString("apellido"));
				usuario.setEdad(rst.getInt("edad"));
				usuario.setDni(rst.getString("dni"));
				usuario.setFecha_nac(rst.getDate("fecha_nac"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return (usuario);
	}
}
