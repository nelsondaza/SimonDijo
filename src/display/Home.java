/**
 * 
 */

package display;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import simon.Simon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


/**
 * Clase que se encarga de la ventana inicial
 * @author nelsondaza
 *
 */
public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Contenedor principal de la ventana
	 */
	private JPanel contentPane;

	/**
	 * Botón de reinicio de juego
	 */
	JButton btnNewButton = null;
	private JButton btnReset;
	
	/**
	 * Objeto de juego
	 */
	Simon simon = null;
	
	private JLabel lblPuntaje = null;
	private JLabel lblPuntajeLogrado = null;
	private JLabel lblTurnoActual = null;
	private JLabel lblMovimientosALograr = null;
	private JLabel lblMovimientos = null;

	/**
	 * Inicio de la aplicación
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Creación de la ventana principal
			Home frame = new Home();
			// Mostrar la ventana principal
			frame.setVisible(true);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * Contructor de la ventana principal (frame)
	 */
	public Home() {
		// Inicialización del juego
		simon = new Simon();
		
		// Operación de cerrado de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Tamaño inicial de la ventana
		setBounds(100, 100, 514, 267);
		
		// Contenedor de los elementos dentro de la ventana 
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		// Para poder posicionar los elementos a voluntad 
		contentPane.setLayout(null);
		
		// Inicialización de botón de reinicio
		btnReset = new JButton("Reiniciar");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Reiniciar el juego
				simon.reset();
			}
		});
		btnReset.setBounds(6, 199, 117, 29);
		btnReset.setVisible(false);
		contentPane.add(btnReset);
		simon.setBotonReset( btnReset );
		
		// Primera figura
		Figura figura1 = new Figura( new Color(0x3D, 0xB5, 0xBD), new Color(0x6D, 0xE5, 0xED) );
		figura1.setBounds(18, 88, 150, 87);
		contentPane.add(figura1);
		simon.addFigura(figura1);
		
		// Segunda figura
		Figura figura2 = new Figura(new Color(0x5A, 0x59, 0x5A), new Color(0x8A, 0x89, 0x8A));
		figura2.setBounds(180, 88, 150, 87);
		contentPane.add(figura2);
		simon.addFigura(figura2);
		
		// Tercera figura
		Figura figura3 = new Figura(new Color(0x00, 0x9A, 0xD0), new Color(0x30, 0xCA, 0xFF));
		figura3.setBounds(342, 88, 150, 87);
		contentPane.add(figura3);
		simon.addFigura(figura3);
		
		lblPuntajeLogrado = new JLabel("Puntaje logrado:");
		lblPuntajeLogrado.setForeground(new Color(0x3D, 0xB5, 0xBD));
		lblPuntajeLogrado.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPuntajeLogrado.setBounds(278, 16, 161, 29);
		contentPane.add(lblPuntajeLogrado);
		
		lblPuntaje = new JLabel("0");
		lblPuntaje.setForeground(new Color(0x3D, 0xB5, 0xBD));
		lblPuntaje.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblPuntaje.setBounds(437, 16, 44, 29);
		contentPane.add(lblPuntaje);
		simon.setLabelPuntaje( lblPuntaje );
		
		
		lblTurnoActual = new JLabel("Listo para jugar");
		lblTurnoActual.setForeground(new Color(0xFF, 0xA7, 0x00));
		lblTurnoActual.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblTurnoActual.setBounds(21, 47, 471, 29);
		contentPane.add(lblTurnoActual);
		simon.setLabelInfo( lblTurnoActual );
		
		
		lblMovimientosALograr = new JLabel("Movimientos:");
		lblMovimientosALograr.setForeground(new Color(38, 39, 38));
		lblMovimientosALograr.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblMovimientosALograr.setBounds(18, 16, 123, 29);
		contentPane.add(lblMovimientosALograr);
		
		lblMovimientos = new JLabel("0/1");
		lblMovimientos.setForeground(new Color(38, 39, 38));
		lblMovimientos.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblMovimientos.setBounds(153, 16, 69, 29);
		contentPane.add(lblMovimientos);
		simon.setLabelMovimientos( lblMovimientos );

		
	}
}
