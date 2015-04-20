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
	
	/**
	 * Objeto de juego
	 */
	Simon simon = null;
	
	private JLabel lblPuntaje = null;
	private JLabel lblPuntajeLogrado = null;
	private JLabel lblTurnoActual = null;
	private JLabel lblMovientosALograr = null;
	private JLabel lblMovientos = null;

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
		btnNewButton = new JButton("Reiniciar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Reiniciar el juego
				simon.reset();
			}
		});
		btnNewButton.setBounds(6, 199, 117, 29);
		btnNewButton.setVisible(false);
		contentPane.add(btnNewButton);
		
		// Primera figura
		Figura figura1 = new Figura( new Color(107, 70, 70), new Color(127, 110, 110) );
		figura1.setBounds(18, 88, 150, 87);
		contentPane.add(figura1);
		simon.addFigura(figura1);
		
		// Segunda figura
		Figura figura2 = new Figura(new Color(70, 106, 70), new Color(110, 126, 110));
		figura2.setBounds(342, 88, 150, 87);
		contentPane.add(figura2);
		simon.addFigura(figura2);
		
		// Tercera figura
		Figura figura3 = new Figura(new Color(70, 70, 104), new Color(110, 110, 124));
		figura3.setBounds(180, 88, 150, 87);
		contentPane.add(figura3);
		simon.addFigura(figura3);
		
		lblPuntaje = new JLabel("0");
		lblPuntaje.setForeground(new Color(0, 128, 128));
		lblPuntaje.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblPuntaje.setBounds(437, 16, 44, 29);
		contentPane.add(lblPuntaje);
		
		lblPuntajeLogrado = new JLabel("Puntaje logrado:");
		lblPuntajeLogrado.setForeground(new Color(0, 128, 128));
		lblPuntajeLogrado.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPuntajeLogrado.setBounds(278, 16, 161, 29);
		contentPane.add(lblPuntajeLogrado);
		
		lblTurnoActual = new JLabel("Listo para jugar");
		lblTurnoActual.setForeground(new Color(255, 140, 0));
		lblTurnoActual.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblTurnoActual.setBounds(21, 47, 471, 29);
		contentPane.add(lblTurnoActual);
		
		lblMovientosALograr = new JLabel("Movimientos:");
		lblMovientosALograr.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblMovientosALograr.setBounds(18, 16, 123, 29);
		contentPane.add(lblMovientosALograr);
		
		lblMovientos = new JLabel("0");
		lblMovientos.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblMovientos.setBounds(153, 16, 44, 29);
		contentPane.add(lblMovientos);
		
	}
}
