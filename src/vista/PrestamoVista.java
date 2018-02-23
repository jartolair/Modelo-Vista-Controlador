package vista;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import comparador.FechaPrestamoComparator;
import comparador.NUsuarioComparator;
import comparador.TituloComparator;
import modelo.Libro;
import modelo.LibroModelo;
import modelo.Prestamo;
import modelo.PrestamoModelo;
import modelo.Usuario;
import modelo.UsuarioModelo;

public class PrestamoVista {
	
	public void menuPrestamo(){
		final int TOMAR=1;
		final int ENTREGAR=2;
		final int LISTAR=3;
		final int ORDENAR_TITULO=1;	
		final int ORDENAR_N_USUARIO=2;
		final int ORDENAR_F_PRESTAMO=3;
		final int SALIR=0;
		int opcion;
		Scanner lector=new Scanner(System.in);
		PrestamoModelo prestamoModelo=new PrestamoModelo();
		do{
			System.out.println("----MENU DE PRESTAMOS----");
			System.out.println(TOMAR+"- Tomar libro prestado");
			System.out.println(ENTREGAR+"- Entregar libro");
			System.out.println(LISTAR+"- Listar prestamos");
			System.out.println(SALIR+"- Salir ");
			opcion=Integer.parseInt(lector.nextLine());
			
			switch(opcion){
			case TOMAR:
				realizarPrestamo(lector);
				break;
			case ENTREGAR:
				finalizarPrestamo(lector);
				break;
			case LISTAR:
				ArrayList<Prestamo> prestamos=prestamoModelo.selectAll();
				
				int opcionOrdenar;
				do{
					mostrarPrestamos(prestamos);
					System.out.println(ORDENAR_TITULO+"- Ordenar por titulo");
					System.out.println(ORDENAR_N_USUARIO+"- Ordenar por nombre de usuario");
					System.out.println(ORDENAR_F_PRESTAMO+"- Ordenar por fecha de prestamo");
					System.out.println(SALIR+"- Atras");
					opcionOrdenar=Integer.parseInt(lector.nextLine());
					
					switch(opcionOrdenar){
					case ORDENAR_TITULO:
						TituloComparator tc=new TituloComparator();
						prestamos.sort(tc);
						break;
					case ORDENAR_N_USUARIO:
						NUsuarioComparator nuc=new NUsuarioComparator();
						prestamos.sort(nuc);
						break;
					case ORDENAR_F_PRESTAMO:
						FechaPrestamoComparator fpc=new FechaPrestamoComparator();
						prestamos.sort(fpc);
						break;
					case SALIR:
						break;
					default:
						System.out.println("Esa opcion no exite");
					}
				}while(opcionOrdenar!=SALIR);
				break;
				
			case SALIR:
				break;
			default:
				System.out.println("Esa opcion no exite");
			}
		}while(opcion!=SALIR);
	}
		
	





	private void mostrarPrestamos(ArrayList<Prestamo> prestamos) {
		// TODO Auto-generated method stub
		Iterator<Prestamo> i=prestamos.iterator();
		while(i.hasNext()){
			mostrarPrestamo(i.next());
		}
		
	}

	private void mostrarPrestamo(Prestamo prestamo) {
		// TODO Auto-generated method stub
		System.out.print(prestamo.getUsuario().getNombre()+" : "
				+prestamo.getUsuario().getApellido()+" : "
				+prestamo.getLibro().getTitulo()+" : "
				+prestamo.getLibro().getAutor()+" : "
				+prestamo.getFechaPrestamo()+" : "
				+prestamo.getFechaLimite()+" : ");
		if (prestamo.isEntregado()){
			System.out.println("Entregado");
		}else{
			System.out.println("Sin entregar");
		}
		
	}




	/*
	 * lelengo dnixe eskatzeu, gero aber existitze aldun
	 * bigarren liburue, ta existitze aldun
	 * gero prestamo horrek existitze aldun
	 * bukatzeko liburue entregau dula jarri 
	 */
	private void finalizarPrestamo(Scanner lector){
		// TODO Auto-generated method stub
		//usuario
		Usuario usuario=null;
		Prestamo prestamo=null;
		do{
			System.out.println("Cual es tu DNI");
			String dni=lector.nextLine();
			
			//buscar usuario
			UsuarioModelo usuarioModelo=new UsuarioModelo();
			usuario=usuarioModelo.selectPorDNI(dni);
			if(usuario!=null){
				//libro
				do{
				System.out.println("Introduce el titulo del libro");
				String titulo =lector.nextLine();
				
				//buscar libro
				LibroModelo libroModelo=new LibroModelo();
				Libro libro =libroModelo.selectPorTitulo(titulo);
				if(libro!=null){
					//buscar prestamo
					PrestamoModelo prestamoModelo=new PrestamoModelo();
					prestamo=prestamoModelo.selectPorLibroUsuarioEntrega(libro, usuario, false);
					if (prestamo!=null){
						prestamo.setEntregado(true);
						
						prestamoModelo.update(prestamo);
					}else{
						System.out.println(usuario.getNombre()+" "+usuario.getApellido()+" no cogio prestado el libro de "+libro.getTitulo());
					}
				}else{
					System.out.println("Ese libro no existe");
				}
				System.out.println("Intente de nuevo");
			}while(usuario==null || prestamo==null);
			}else{
				System.out.println("Ese usuario no existe, intente de nuevo");
			}
		}while(usuario==null);
	}


	private void realizarPrestamo(Scanner lector) {
		// TODO Auto-generated method stub
		Libro libro=null;
		Usuario usuario=null;
		do{
			System.out.println("Introduce el titulo del libro");
			String titulo =lector.nextLine();
			PrestamoModelo prestamoModelo=new PrestamoModelo();
			LibroModelo libroModelo=new LibroModelo();
			libro =libroModelo.selectPorTitulo(titulo);
			if(libro!=null){
				if(prestamoModelo.estaDisponible(libro.getId())){
					
					do{
						System.out.println("Cual es tu DNI");
						String dni=lector.nextLine();
						UsuarioModelo usuarioModelo=new UsuarioModelo();
						usuario=usuarioModelo.selectPorDNI(dni);
						if(usuario!=null){
							Prestamo prestamo =crearPrestamo(libro,usuario);
							
							prestamoModelo.insertar(prestamo);
							
						}else{
							System.out.println("Ese usuario no existe, intente de nuevo");
						}
					}while(usuario==null);
				}else{
					System.out.println("Ese libro no esta disponible");
				}
			}else{
				System.out.println("No tenemos ese libro, prueba con otro");
			}
		}while(libro==null);
	}



	private Prestamo crearPrestamo(Libro libro, Usuario usuario) {
		// TODO Auto-generated method stub
		Prestamo prestamo=new Prestamo();
		prestamo.setLibro(libro);
		prestamo.setUsuario(usuario);
		Date fechaDeHoy=new Date();
		prestamo.setFechaPrestamo(fechaDeHoy);
		Date fechaLimite = new Date(fechaDeHoy.getTime() + ((1000 * 60 * 60 * 24) * 7 *3));
		prestamo.setFechaLimite(fechaLimite);
		prestamo.setEntregado(false);
		return prestamo;
	}
}
