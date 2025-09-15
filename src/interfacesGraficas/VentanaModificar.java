package interfacesGraficas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaModificar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtStock;
	private JTextField txtPrecioUni;
	private JTextField txtDescri;
	private JTextField txtcod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificar frame = new VentanaModificar();
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
	public VentanaModificar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Modificar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(27, 22, 45, 18);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblNewLabel.setBackground(Color.WHITE);
		panel.add(lblNewLabel);
		
		txtStock = new JTextField();
		txtStock.setBounds(155, 123, 86, 20);
		txtStock.setColumns(10);
		panel.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio Unitario");
		lblPrecio.setBounds(27, 78, 107, 18);
		lblPrecio.setForeground(Color.BLACK);
		lblPrecio.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblPrecio.setBackground(Color.WHITE);
		panel.add(lblPrecio);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(35, 122, 57, 18);
		lblStock.setForeground(Color.BLACK);
		lblStock.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblStock.setBackground(Color.WHITE);
		panel.add(lblStock);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(27, 168, 96, 18);
		lblDescripcin.setForeground(Color.BLACK);
		lblDescripcin.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblDescripcin.setBackground(Color.WHITE);
		panel.add(lblDescripcin);
		
		txtPrecioUni = new JTextField();
		txtPrecioUni.setBounds(155, 79, 86, 20);
		txtPrecioUni.setColumns(10);
		panel.add(txtPrecioUni);
		
		txtDescri = new JTextField();
		txtDescri.setBounds(155, 169, 86, 20);
		txtDescri.setColumns(10);
		panel.add(txtDescri);
		
		txtcod = new JTextField();
		txtcod.setBounds(27, 47, 86, 20);
		txtcod.setColumns(10);
		panel.add(txtcod);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(281, 100, 123, 67);
		btnModificar.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(btnModificar);

	}
}
