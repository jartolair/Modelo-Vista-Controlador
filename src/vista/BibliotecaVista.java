package vista;

import java.util.Scanner;

public class BibliotecaVista {
	
	
	
	public void menuBiblioteca(){
		UsuarioVista usuarioVista=new UsuarioVista();
		LibroVista libroVista=new LibroVista();
		PrestamoVista prestamoVista=new PrestamoVista();
		final int USUARIOS=1;
		final int LIBROS=2;
		final int PRESTAMOS=3;
		final int SALIR=0;
		Scanner lector=new Scanner(System.in);
		int opcion;
		do{
			System.out.println("MENU DE BIBLIOTECA");
			System.out.println(USUARIOS+ "- Gestionar usuarios");
			System.out.println(LIBROS+ "- Gestionar libros");
			System.out.println(PRESTAMOS+ "- Gestionar prestamos");
			System.out.println(SALIR+ "- Salir");
			opcion=Integer.parseInt(lector.nextLine());
			
			switch(opcion){
			case USUARIOS:
				usuarioVista.menuUsuario();
				break;
			case LIBROS:
				libroVista.menuLibro();
				break;
			case PRESTAMOS:
				prestamoVista.menuPrestamo();
				break;
			case SALIR:
				System.out.println("Adios...");
				break;
			default:
				System.out.println("Esa opcion no existe");
			}
		}while(opcion!=SALIR);
	}
}
