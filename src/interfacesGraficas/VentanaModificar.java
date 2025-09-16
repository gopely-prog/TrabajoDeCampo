package interfacesGraficas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;

import clases.ArregloComida;
import clases.Comida;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaModificar extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblDescripcin;
	private JTextField txtDesc;
	private JLabel lblPrecio;
	private JTextField txtPUnit;
	private JLabel lblStock;
	private JTextField txtStock;
	private JButton btnModificar;

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

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 355, 267);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Producto", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.windowText));
		panel.setBounds(10, 14, 326, 206);
		contentPane.add(panel);
		
		lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setForeground(Color.BLACK);
		lblDescripcin.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblDescripcin.setBackground(Color.WHITE);
		lblDescripcin.setBounds(10, 64, 86, 18);
		panel.add(lblDescripcin);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(123, 64, 162, 20);
		panel.add(txtDesc);
		
		lblPrecio = new JLabel("Precio Unitario");
		lblPrecio.setForeground(Color.BLACK);
		lblPrecio.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblPrecio.setBackground(Color.WHITE);
		lblPrecio.setBounds(10, 105, 103, 18);
		panel.add(lblPrecio);
		
		txtPUnit = new JTextField();
		txtPUnit.setColumns(10);
		txtPUnit.setBounds(123, 105, 86, 20);
		panel.add(txtPUnit);
		
		lblStock = new JLabel("Stock");
		lblStock.setForeground(Color.BLACK);
		lblStock.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblStock.setBackground(Color.WHITE);
		lblStock.setBounds(10, 149, 103, 18);
		panel.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(123, 149, 86, 20);
		panel.add(txtStock);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setFont(new Font("Arial", Font.BOLD, 12));
		btnModificar.setBounds(230, 143, 86, 53);
		panel.add(btnModificar);
		
		lblNewLabel = new JLabel("Código");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 13, 63, 18);
		panel.add(lblNewLabel);
		
		txtCod = new JTextField();
		txtCod.setColumns(10);
		txtCod.setBounds(10, 34, 86, 20);
		panel.add(txtCod);

	}
	ArregloComida ac = ArregloComida.getInstancia();
	VentanaAgregarComida va = new VentanaAgregarComida();
	private JLabel lblNewLabel;
	private JTextField txtCod;
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
	}
	
	
	int LeerCodigo() {
		return Integer.parseInt(txtCod.getText());	
	}
	String LeerDesc() {
		return txtDesc.getText();
	}
	double LeerPUnit() {
		return Double.parseDouble(txtPUnit.getText());
	}
	int LeerStock() {
		return Integer.parseInt(txtStock.getText());
	}
	
	
	protected void do_btnModificar_actionPerformed(ActionEvent e) {	
		try {
			Comida enc = ac.Buscar(LeerCodigo());
			if(enc != null) {
				enc.setDescripcion(LeerDesc());
				enc.setpUnitario(LeerPUnit());
				enc.setStock(LeerStock());
				
				txtCod.setText("");
				txtDesc.setText("");
				txtPUnit.setText("");
				txtStock.setText("");
				txtCod.requestFocus();
				JOptionPane.showMessageDialog(this, "Plato modificado.");
			}
			else JOptionPane.showMessageDialog(this, "El código no existe.");
		}
		catch (Exception e1){
			JOptionPane.showMessageDialog(this, "Rellena todos los campos para continuar.");
		}	
	}
}
