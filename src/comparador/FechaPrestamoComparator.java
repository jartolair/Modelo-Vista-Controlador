
package comparador;
import java.util.Comparator;
import modelo.Prestamo;

public class FechaPrestamoComparator implements Comparator<Prestamo>{

	@Override
	public int compare(Prestamo p1, Prestamo p2) {
		// TODO Auto-generated method stub
		return p1.getFechaPrestamo().compareTo(p2.getFechaPrestamo());
	}

}
