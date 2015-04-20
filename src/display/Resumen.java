package display;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextArea;

public class Resumen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfUsuario;

	public Resumen(JDialog padre, String titulo, ModalityType modal) {
		super(padre, titulo, modal);
		
		setBounds(100, 100, 342, 196);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPuntajeLogrado = new JLabel("Puntaje logrado:");
		lblPuntajeLogrado.setForeground(new Color(61, 181, 189));
		lblPuntajeLogrado.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPuntajeLogrado.setBounds(111, 6, 161, 29);
		contentPanel.add(lblPuntajeLogrado);
		
		JLabel lblPuntaje = new JLabel("0");
		lblPuntaje.setForeground(new Color(61, 181, 189));
		lblPuntaje.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblPuntaje.setBounds(270, 6, 44, 29);
		contentPanel.add(lblPuntaje);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(156, 47, 134, 28);
		contentPanel.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setForeground(UIManager.getColor("Button.disabledText"));
		lblNombreDeUsuario.setBounds(25, 53, 134, 16);
		contentPanel.add(lblNombreDeUsuario);
		
		JTextArea txtrConEsteNombre = new JTextArea();
		txtrConEsteNombre.setBackground(UIManager.getColor("CheckBox.background"));
		txtrConEsteNombre.setEditable(false);
		txtrConEsteNombre.setFocusable(false);
		txtrConEsteNombre.setForeground(UIManager.getColor("Button.disabledText"));
		txtrConEsteNombre.setLineWrap(true);
		txtrConEsteNombre.setWrapStyleWord(true);
		txtrConEsteNombre.setText("Con este nombre de usuario puedes almacenar tu puntaje y ser listado en el ranking del juego.");
		txtrConEsteNombre.setBounds(44, 81, 246, 48);
		contentPanel.add(txtrConEsteNombre);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
