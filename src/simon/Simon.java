/**
 * El archivo Simon.java contiene la definición de la clase Simon
 * Hace parte del paquete XX
 * 
 * 
 */
package simon;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import display.Figura;
import display.Resumen;

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
	 * Bandera que indica si el juego está activo, usada para el momento en el que se falla en una figura
	 */
	private boolean activo = false;
	
	/**
	 * Label en el que se escribirá el puntaje alcanzado
	 */
	private JLabel lPuntaje = null;
	
	/**
	 * Label en el que se mostrará el movimiento en el que se encuentra el usuario y el total de movimientos
	 */
	private JLabel lMovimientos = null;
	
	/**
	 * Label en el que se mostrará información general
	 */
	private JLabel lInfo = null;
	
	/**
	 * Botón para reiniciar juego
	 */
	private JButton bReset = null;
	
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
		if( !autoPlay && activo ) {
			// 
			
			if( bReset != null ) {
				bReset.setVisible( true );
				bReset.paintImmediately(bReset.getVisibleRect());
			}
			
			movimientoActual ++;
			
			if( movimientoActual == movimientosSecuenciaActual ) {
				figura.setSeleccionado( false );
				addSecuencia( figura );
				
				secuenciasCompletadas ++;
				movimientosSecuenciaActual ++;
				movimientoActual = 0;
				
				showMovimientos();
				showPuntaje();
				
				repetirSecuencia();
			}
			else {
				System.out.println("Mov:" + movimientoActual );
				Figura actual = secuencia.get( movimientoActual - 1 );
				if( actual == figura ) {
					showMovimientos();
					if( movimientoActual == movimientosSecuenciaActual - 1 ) {
						showWarning("¡Agrega una figura nueva!");
					}
				}
				else {
					showError("¡Fallaste!");
					activo = false;
					// Juego Terminado
					JDialog window = (JDialog)SwingUtilities.getAncestorOfClass(JDialog.class, figura);
					Resumen resumen = new Resumen(window, "Resumen de Juego", Dialog.ModalityType.APPLICATION_MODAL);
					resumen.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					resumen.setVisible(true);
				}
			}
			
		}
	}
	
	/**
	 * Repetición automática de la secuencia
	 */
	private void repetirSecuencia( ) {
		
		if( bReset != null ) {
			bReset.setVisible( false );
			bReset.paintImmediately(bReset.getVisibleRect());
		}
		
		for( int c = 0; c < figuras.size(); c ++ ) {
			Figura actual = figuras.get( c );
			actual.setVisible(false);
			actual.paintImmediately(actual.getVisibleRect());
		}
		
		showInfo("Es mi turno... ¡Memoriza!");
		try {
			Thread.sleep( 500 );
		}
		catch( InterruptedException ie ) {
			;
		}
		
		for( int c = 0; c < figuras.size(); c ++ ) {
			Figura actual = figuras.get( c );
			actual.setVisible(true);
			actual.paintImmediately(actual.getVisibleRect());
		}
		
		autoPlay = true;
		for( int c = 0; c < secuencia.size(); c ++ ) {
			Figura actual = secuencia.get( c );
			actual.animar();
		}
		
		int pos = (int)Math.round( Math.random( ) * ( figuras.size( ) - 1 ) );
		Figura rand = figuras.get( pos );
		addSecuencia( rand );
		rand.animar();
		
		showAccion( "¡Tu turno!" );
		
		movimientoActual = 0;
		movimientosSecuenciaActual ++;
		showMovimientos();
		
		if( bReset != null ) {
			bReset.setVisible( true );
			bReset.paintImmediately(bReset.getVisibleRect());
		}

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
		movimientosSecuenciaActual = 1;
		autoPlay = false;
		activo = true;
		showWarning("Listo para jugar");
		showPuntaje();
		showMovimientos();
		if( bReset != null )
			bReset.setVisible( false );
	}
	
	/**
	 * Asigna el label para el total de puntaje
	 * @param l
	 */
	public void setLabelPuntaje( JLabel l ) {
		lPuntaje = l;
	}
	
	/**
	 * Asigna el label para el total de movimientos
	 * @param l
	 */
	public void setLabelMovimientos( JLabel l ) {
		lMovimientos = l;
	}
	
	/**
	 * Asigna el label para mostrar información general
	 * @param l
	 */
	public void setLabelInfo( JLabel l ) {
		lInfo = l;
	}
	
	/**
	 * Asigna el botón de reset
	 * @param b
	 */
	public void setBotonReset( JButton b ) {
		bReset = b;
	}
	/**
	 * Muestra un mensaje informativo con un color específico
	 * @param text
	 * @param color
	 */
	private void showText( String text, Color color ) {
		if( lInfo != null ) {
			lInfo.setText(text);
			lInfo.setForeground( color );
			lInfo.paintImmediately(lInfo.getVisibleRect());
		}
	}
	/**
	 * Muestra un mensaje azul #0096D0
	 * @param text
	 */
	private void showInfo( String text ) {
		showText(text, new Color(0x00, 0x96, 0xD0));
	}
	/**
	 * Muestra un mensaje naranja #FFA700
	 * @param text
	 */
	private void showWarning( String text ) {
		showText(text, new Color(0xFF, 0xA7, 0x00));
	}
	/**
	 * Muestra un mensaje rojo #E83C86
	 * @param text
	 */
	private void showError( String text ) {
		showText(text, new Color(0xE8, 0x3C, 0x86));
	}
	/**
	 * Muestra un mensaje verde #3EB1B5
	 * @param text
	 */
	private void showAccion( String text ) {
		showText(text, new Color(0x3E, 0xB1, 0xB5));
	}
	/**
	 * Muestra el total de movimientos y los movimientos restante
	 */
	private void showMovimientos( ) {
		if( lMovimientos != null ) {
			lMovimientos.setText( movimientoActual + "/" + movimientosSecuenciaActual );
			lMovimientos.paintImmediately(lMovimientos.getVisibleRect());
		}
	}
	/**
	 * Muestra el puntaje actual (Secuencias completadas)
	 */
	private void showPuntaje( ) {
		if( lPuntaje != null ) {
			lPuntaje.setText( ( movimientosSecuenciaActual - 1 ) + "" );
			lPuntaje.paintImmediately(lPuntaje.getVisibleRect());
		}
	}
	
}
