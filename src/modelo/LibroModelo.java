package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LibroModelo extends Conector{

	
	/**
	 * devuelve un ArrayList de todos los libros de la base de datos 
	 * @return
	 */
	public ArrayList<Libro> selectAll(){
		//crear arraylist
		ArrayList<Libro> libros= new ArrayList<Libro>();
		try {
			//obtener datos
			Statement st=super.conexion.createStatement();
			ResultSet rs=st.executeQuery("select * from libros");
			
			//crear libros y añadirlos al arraylist
			while(rs.next()){
				Libro libro=new Libro();
				libro.setId(rs.getInt("id"));
				libro.setTitulo(rs.getString("Titulo"));
				libro.setAutor(rs.getString("autor"));
				
				libros.add(libro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//devolver el arraylist
		return libros;
	}
	
	/**
	 * devuelve un solo libro
	 * @param id - el identificador del libro
	 * @return
	 */
	public Libro select(int id){
		//crear el libro
		Libro libro=new Libro();
		try {
			//obtener datos
			PreparedStatement pst= super.conexion.prepareStatement("Select * from libros where id=?");
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			
			//guardar detos en libro
			while(rs.next()){
				libro.setId(id);
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//devolver libro
		return libro;
	}
	
	/**
	 * devuelve un ArrayList con todos los libros con el mismo titulo
	 * @param titulo
	 * @return
	 */
	public ArrayList<Libro> select(String titulo){
		//crear arraylist
		ArrayList<Libro> libros= new ArrayList<Libro>();
		try {
			//obtener datos
			PreparedStatement pst=super.conexion.prepareStatement("select * from libros where titulo=?");
			pst.setString(1, titulo);
			ResultSet rs=pst.executeQuery();
			
			//crear libros y añadirlos al arraylist
			while(rs.next()){
				Libro libro=new Libro();
				libro.setId(rs.getInt("id"));
				libro.setTitulo(rs.getString("Titulo"));
				libro.setAutor(rs.getString("autor"));
				
				libros.add(libro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//devolver el arraylist
		return libros;
	}
	public Libro selectPorTitulo(String titulo){
		//crear libro
		Libro libro=null;
		try {
			//obtener datos
			PreparedStatement pst=super.conexion.prepareStatement("select * from libros where titulo=?");
			pst.setString(1, titulo);
			ResultSet rs=pst.executeQuery();
			
			
			//crear libro
			if(rs.next()){
				libro=new Libro();
				libro.setId(rs.getInt("id"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				
				return libro;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//devolver libro
		return libro;
	}
	/**
	 * actualiza el libro de la base de datos
	 * @param libro
	 */
	public void update(Libro libro){
		
		try {
			PreparedStatement pst= super.conexion.prepareStatement("UPDATE libros SET titulo=?, autor=? where id=?");
			pst.setString(1, libro.getTitulo());
			pst.setString(2, libro.getAutor());
			pst.setInt(3, libro.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * elimina el libro con el id introducido
	 * @param id
	 */
	public void delete(int id){
		try {
			PreparedStatement pst=super.conexion.prepareStatement("DELETE FROM libros WHERE id=?");
			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(Libro libro){
		try {
			PreparedStatement pst =super.conexion.prepareStatement("INSERT INTO libros(titulo,autor) VALUES(?,?)");
			pst.setString(1, libro.getTitulo());
			pst.setString(2, libro.getAutor());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
