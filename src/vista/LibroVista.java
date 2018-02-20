package vista;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import modelo.Libro;
import modelo.LibroModelo;

public class LibroVista {
	
	public void menuLibro(){
		
		final int LISTAR=1;
		final int INSERTAR=2;
		final int SALIR=0;
		
		Scanner lector=new Scanner(System.in);
		int opcion;
		do{
			System.out.println("-----MENU DE LIBROS-----");
			System.out.println(LISTAR +"- Listar");
			System.out.println(INSERTAR+"- Insertar");
			System.out.println(SALIR+"- Salir");
			opcion=Integer.parseInt(lector.nextLine());
			LibroModelo libroModelo=new LibroModelo();
			switch(opcion){
			case LISTAR:
				ArrayList<Libro> libros= libroModelo.selectAll();
				mostrarLbros(libros);
				break;
			case INSERTAR:
				System.out.println("Titulo del libro:");
				String titulo=lector.nextLine();
				System.out.println("Autor del libro:");
				String autor=lector.nextLine();
				
				Libro libro=new Libro();
				libro.setTitulo(titulo);
				libro.setAutor(autor);
				libroModelo.insert(libro);
				break;
			case SALIR:
				System.out.println("Adios...");
				break;
			default:
				System.out.println("Esa opcion no existe");
			}
			
		}while(opcion!=SALIR);
		
	}

	public void mostrarLbros(ArrayList<Libro> libros) {
		// TODO Auto-generated method stub
		Iterator<Libro> i=libros.iterator();
		
		while(i.hasNext()){
			mostrarLibro(i.next());
		}
		
	}

	private void mostrarLibro(Libro libro) {
		// TODO Auto-generated method stub
		System.out.println(libro.getId()+" - "+libro.getTitulo()+" - "+libro.getAutor());
	}
}
