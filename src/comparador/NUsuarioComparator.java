package comparador;

import java.util.Comparator;

import modelo.Prestamo;
public class NUsuarioComparator implements Comparator<Prestamo>{

	@Override
	public int compare(Prestamo p0, Prestamo p1) {
		// TODO Auto-generated method stub
		return p0.getUsuario().getNombre().compareToIgnoreCase(p1.getUsuario().getNombre());
	}
	

}
