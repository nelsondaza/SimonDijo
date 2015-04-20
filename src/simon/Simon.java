/**
 * El archivo Simon.java contiene la definición de la clase Simon
 * Hace parte del paquete XX
 * 
 * 
 */
package simon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import display.Figura;

/**
 * Clase encargada de la lógica del juego
 *
 */
public class Simon {
	
	/**
	 * Listado de figuras a usar
	 */
	private Vector<Figura> figuras = null;
	
	/**
	 * Número de secuencias que el usuario ha completado exitosamente, que se tomará como el puntaje logrado.
	 */
	private int secuenciasCompletadas = 0;
	
	/**
	 * Número de movimientos que necesita la secuecia actual
	 */
	private int movimientosSecuenciaActual = 0;
	
	/**
	 * Movimiento en el que se encuentra el usuario en la secuencia actual 
	 */
	private int movimientoActual = 0;

	/**
	 * Listado de figuras usadas en secuencia
	 */
	private Vector<Figura> secuencia = null;
	
	/**
	 * Bandera que indica si se está jugando de forma automática
	 */
	private boolean autoPlay = false;
	
	/**
	 * Constructor
	 */
	public Simon() {
		figuras = new Vector<Figura>();
		reset();
	}
	
	/**
	 * Agrega una figura al listado de figuras que se pueden usar
	 * @param figura
	 */
	public void addFigura( Figura figura ) {
		
		figuras.add( figura );
		/**
		 * Agregar los eventos de mouse para cada figura
		 */
		figura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actionOn(((Figura)e.getSource()));
			}
		});
		
	}
	
	/**
	 * Se genera una acción en una de las figuras de la lista
	 * @param figura
	 */
	public void actionOn( Figura figura ) {
		if( !autoPlay ) {
			// 
			if( secuenciasCompletadas == 0 || movimientoActual == movimientosSecuenciaActual + 1 ) {
				figura.setSeleccionado( false );
				addSecuencia( figura );
				
				secuenciasCompletadas ++;
				movimientosSecuenciaActual = secuenciasCompletadas;
				movimientoActual = 0;
				System.out.println("Agregada!!");
				
				repetirSecuencia();
			}
			else {
				Figura actual = secuencia.get( movimientoActual );
				if( actual == figura ) {
					movimientoActual ++;
				}
				else {
					System.out.println("FIN!!");
					// Juego Terminado
				}
			}
		}
	}
	
	/**
	 * Repetición automática de la secuencia
	 */
	private void repetirSecuencia( ) {
		autoPlay = true;
		for( int c = 0; c < secuencia.size(); c ++ ) {
			System.out.println("Auto: " + c);
			Figura actual = secuencia.get( c );
			actual.animar();
		}
		
		int pos = (int)Math.round( Math.random( ) * ( figuras.size( ) - 1 ) );
		System.out.println("Auto: " + pos);
		Figura rand = figuras.get( pos );
		addSecuencia( rand );
		rand.animar();
		
		autoPlay = false;
	}
	/**
	 * Agrega una figura a la secuencia que se va a repetir
	 * @param figura
	 */
	private void addSecuencia( Figura figura ) {
		secuencia.addElement( figura );
	}
	
	/**
	 * Volver las variables y todo el juego a su estado inicial sin eliminar las figuras agregadas
	 */
	public void reset( ) {
		secuencia = new Vector<Figura>();
		movimientoActual = 0;
		secuenciasCompletadas = 0;
		movimientosSecuenciaActual = 0;
		autoPlay = false;
	}
	
}
