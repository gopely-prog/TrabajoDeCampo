package interfacesGraficas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clases.ArregloComida;
import clases.Comida;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAgregarComida extends JFrame implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	TitledBorder bordeProductos = new TitledBorder("Producto");
	private JLabel lblNewLabel;
	private JTextField txtCodigo;
	private JLabel lblDescripcin;
	private JTextField txtDescripcion;
	private JLabel lblPrecio;
	private JTextField txtPUnitario;
	private JLabel lblStock;
	private JTextField txtStock;
	private JButton btnAgregarProducto;

	/**
	 * Create the frame.
	 */
	public VentanaAgregarComida() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 362, 273);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		panel.setBounds(10, 11, 307, 195);
		contentPane.add(panel);
		panel.setBorder(bordeProductos);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Código");
		lblNewLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 63, 18);
		panel.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(182, 9, 86, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setForeground(Color.BLACK);
		lblDescripcin.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblDescripcin.setBackground(Color.WHITE);
		lblDescripcin.setBounds(10, 34, 86, 18);
		panel.add(lblDescripcin);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(182, 32, 86, 20);
		panel.add(txtDescripcion);
		
		lblPrecio = new JLabel("Precio Unitario");
		lblPrecio.setForeground(Color.BLACK);
		lblPrecio.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblPrecio.setBackground(Color.WHITE);
		lblPrecio.setBounds(10, 58, 103, 18);
		panel.add(lblPrecio);
		
		txtPUnitario = new JTextField();
		txtPUnitario.setColumns(10);
		txtPUnitario.setBounds(182, 56, 86, 20);
		panel.add(txtPUnitario);
		
		lblStock = new JLabel("Stock");
		lblStock.setForeground(Color.BLACK);
		lblStock.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblStock.setBackground(Color.WHITE);
		lblStock.setBounds(10, 81, 103, 18);
		panel.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(182, 79, 86, 20);
		panel.add(txtStock);
		
		btnAgregarProducto = new JButton("Agregar");
		btnAgregarProducto.addActionListener(this);
		btnAgregarProducto.setBounds(103, 131, 89, 23);
		panel.add(btnAgregarProducto);
	}
	ArregloComida ac = ArregloComida.getInstancia();
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregarProducto) {
			do_btnAgregarProducto_actionPerformed(e);
		}
	}
	int LeerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}
	String LeerDescripcion() {
		return txtDescripcion.getText();
	}
	double LeerPUnitario() {
		return Double.parseDouble(txtPUnitario.getText());
	}
	int LeerStock() {
		return Integer.parseInt(txtStock.getText());
	}

	protected void do_btnAgregarProducto_actionPerformed(ActionEvent e) {
		Comida c = ac.Buscar(LeerCodigo());
		if(c==null) {
			Comida c1= new Comida(LeerCodigo(),LeerDescripcion(),LeerPUnitario(),LeerStock());
			ac.Adicionar(c1);
			txtCodigo.setText("");
			txtDescripcion.setText("");
			txtPUnitario.setText("");
			txtStock.setText("");
			txtCodigo.requestFocus();
			JOptionPane.showMessageDialog(this, "Plato agregado.");
		}else JOptionPane.showMessageDialog(this, "Ya existe el plato de comida en registro.");
	}
}
