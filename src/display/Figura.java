/**
 * 
 */
package display;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

/**
 * Clase encargada de mostrar las figuras en pantalla
 * @author cleotildesanabria
 *
 */
public class Figura extends JLabel {

	/**
	 * Color utilizado en el estado "normal" de la figura
	 */
	Color color = null;
	/**
	 * Color utlizado en el estado "seleccionado" de la figura
	 */
	Color colorSeleccionado = null;
	
	/**
	 * Constructor de la figura
	 * @param c1 Color de estado "normal" de la figura
	 * @param c2 Color de estado "selecionado" para la figura
	 */
	public Figura( Color c1, Color c2 ) {
		super("");
		
		color = c1;
		colorSeleccionado = c2;
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setAlignmentY(0.0f);
		this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		this.setOpaque(true);
		this.setBounds(35, 186, 150, 87);
		
		setSeleccionado( false );
		
		/**
		 * Agregar los eventos de mouse para cambiar el estado
		 */
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				((Figura)e.getSource()).setSeleccionado( true );
				((Figura)e.getSource()).paintImmediately(((Figura)e.getSource()).getVisibleRect());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				((Figura)e.getSource()).setSeleccionado( false );
				((Figura)e.getSource()).paintImmediately(((Figura)e.getSource()).getVisibleRect());
			}
		});
		
	}
	
	/**
	 * Cambia de estado (color) la figura  
	 * @param seleccionado
	 */
	public void setSeleccionado( boolean seleccionado ) {
		
		if( seleccionado ) {
			this.setBackground( colorSeleccionado );
		}
		else {
			this.setBackground( color );
		}
		
	}
	
	/**
	 * Cambia de estado en un intervalo para dar la sensación de animación
	 */
	public void animar( ) {
		try {
			setSeleccionado( false );
			paintImmediately(getVisibleRect());
			Thread.sleep( 500 );
			setSeleccionado( true );
			paintImmediately(getVisibleRect());
			Thread.sleep( 500 );
			setSeleccionado( false );
			paintImmediately(getVisibleRect());
		}
		catch( InterruptedException ie ) {
			;
		}
	}

}
