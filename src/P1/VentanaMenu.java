package P1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnTienda;
	private JButton btnProductos;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu frame = new VentanaMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Restaurante (------------------)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 19));
		lblNewLabel.setBounds(132, 11, 244, 57);
		contentPane.add(lblNewLabel);
		
		btnTienda = new JButton("Tienda");
		btnTienda.addActionListener(this);
		btnTienda.setBackground(SystemColor.activeCaption);
		btnTienda.setBounds(42, 67, 72, 47);
		contentPane.add(btnTienda);
		
		btnProductos = new JButton("Productos");
		btnProductos.addActionListener(this);
		btnProductos.setBackground(SystemColor.activeCaption);
		btnProductos.setBounds(203, 67, 93, 47);
		contentPane.add(btnProductos);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBackground(SystemColor.activeCaption);
		btnSalir.setBounds(372, 67, 72, 47);
		contentPane.add(btnSalir);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnProductos) {
			do_btnProductos_actionPerformed(e);
		}
		if (e.getSource() == btnTienda) {
			do_btnNewButton_actionPerformed(e);
		}
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		GUI ventana = new GUI();
        ventana.setVisible(true);
	}
	protected void do_btnProductos_actionPerformed(ActionEvent e) {
		VentanaProductos ventana = new VentanaProductos();
		ventana.setVisible(true);
	}
}
