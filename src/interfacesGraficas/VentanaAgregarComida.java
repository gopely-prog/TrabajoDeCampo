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
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		panel.setBounds(10, 11, 326, 212);
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
		txtCodigo.setBounds(10, 32, 86, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setForeground(Color.BLACK);
		lblDescripcin.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblDescripcin.setBackground(Color.WHITE);
		lblDescripcin.setBounds(10, 63, 86, 18);
		panel.add(lblDescripcin);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(123, 63, 162, 20);
		panel.add(txtDescripcion);
		
		lblPrecio = new JLabel("Precio Unitario");
		lblPrecio.setForeground(Color.BLACK);
		lblPrecio.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblPrecio.setBackground(Color.WHITE);
		lblPrecio.setBounds(10, 104, 103, 18);
		panel.add(lblPrecio);
		
		txtPUnitario = new JTextField();
		txtPUnitario.setColumns(10);
		txtPUnitario.setBounds(123, 104, 86, 20);
		panel.add(txtPUnitario);
		
		lblStock = new JLabel("Stock");
		lblStock.setForeground(Color.BLACK);
		lblStock.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblStock.setBackground(Color.WHITE);
		lblStock.setBounds(10, 148, 103, 18);
		panel.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(123, 148, 86, 20);
		panel.add(txtStock);
		
		btnAgregarProducto = new JButton("Agregar");
		btnAgregarProducto.setFont(new Font("Arial", Font.BOLD, 12));
		btnAgregarProducto.addActionListener(this);
		btnAgregarProducto.setBounds(230, 148, 86, 53);
		panel.add(btnAgregarProducto);
	}
	
	ArregloComida ac = ArregloComida.getInstancia();
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregarProducto) {
			do_btnAgregarProducto_actionPerformed(e);
		}	
	}
	Integer LeerCodigo() {
		try {
			String texto = txtCodigo.getText().trim();
			
			// Verificar si el campo esta vacio
			if (texto.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Error: El campo código no puede estar vacío.", 
					"Campo Vacío", JOptionPane.ERROR_MESSAGE);
				txtCodigo.requestFocus();
				return null;
			}
			
			// Convertir a entero
			int codigo = Integer.parseInt(texto);
			
			if (codigo <= 0) {
				JOptionPane.showMessageDialog(this, "Error: El código debe ser un número positivo.", 
					"Código Inválido", JOptionPane.ERROR_MESSAGE);
				txtCodigo.requestFocus();
				return null;
			}
			
			return codigo;
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Error: El código debe ser un número entero válido.", 
				"Formato Inválido", JOptionPane.ERROR_MESSAGE);
			txtCodigo.requestFocus();
			return null;
		}
	}
	String LeerDescripcion() {
		try {
			String descripcion = txtDescripcion.getText().trim();
			
			// Verificar si el campo está vacío
			if (descripcion.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Error: El campo descripción no puede estar vacío.", 
					"Campo Vacío", JOptionPane.ERROR_MESSAGE);
				txtDescripcion.requestFocus();
				return null;
			}
			
			// Validar que no contenga solo números
			if (descripcion.matches("^[0-9]+$")) {
				JOptionPane.showMessageDialog(this, "Error: La descripción no puede contener solo números.", 
					"Descripción Inválida", JOptionPane.ERROR_MESSAGE);
				txtDescripcion.requestFocus();
				return null;
			}
			
			return descripcion;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error inesperado al leer la descripción: " + e.getMessage(), 
				"Error", JOptionPane.ERROR_MESSAGE);
			txtDescripcion.requestFocus();
			return null;
		}
	}
	Double LeerPUnitario() {
		try {
			String texto = txtPUnitario.getText().trim();
			
			// Verificar si el campo está vacío
			if (texto.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Error: El campo precio unitario no puede estar vacío.", 
					"Campo Vacío", JOptionPane.ERROR_MESSAGE);
				txtPUnitario.requestFocus();
				return null;
			}
			
			// Convertir a double
			double precio = Double.parseDouble(texto);

			if (precio <= 0) {
				JOptionPane.showMessageDialog(this, "Error: El precio unitario debe ser mayor a 0.", 
					"Precio Inválido", JOptionPane.ERROR_MESSAGE);
				txtPUnitario.requestFocus();
				return null;
			}			
			return precio;
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Error: El precio unitario debe ser un número válido (use punto para decimales).", 
				"Formato Inválido", JOptionPane.ERROR_MESSAGE);
			txtPUnitario.requestFocus();
			return null;
		}
	}
	Integer LeerStock() {
		try {
			String texto = txtStock.getText().trim(); // CORREGIDO: era txtCodigo.getText()
			
			// Verificar si el campo está vacío
			if (texto.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Error: El campo stock no puede estar vacío.", 
					"Campo Vacío", JOptionPane.ERROR_MESSAGE);
				txtStock.requestFocus();
				return null;
			}

			int stock = Integer.parseInt(texto);

			if (stock < 0) {
				JOptionPane.showMessageDialog(this, "Error: El stock no puede ser negativo.", 
					"Stock Inválido", JOptionPane.ERROR_MESSAGE);
				txtStock.requestFocus();
				return null;
			}
			
			// Advertir si el stock es 0
			if (stock == 0) {
				int respuesta = JOptionPane.showConfirmDialog(this, 
					"Advertencia: El stock es 0 (producto agotado). ¿Desea continuar?", 
					"Stock Agotado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (respuesta != JOptionPane.YES_OPTION) {
					txtStock.requestFocus();
					return null;
				}
			}
			
			return stock;
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Error: El stock debe ser un número entero válido.", 
				"Formato Inválido", JOptionPane.ERROR_MESSAGE);
			txtStock.requestFocus();
			return null;
		}
	}
	void limpiarCampos() {
	    txtCodigo.setText("");
	    txtDescripcion.setText("");
	    txtPUnitario.setText("");
	    txtStock.setText("");
	    txtCodigo.requestFocus();
	}
	protected void do_btnAgregarProducto_actionPerformed(ActionEvent e) {
		try {
			// Leer y validar todos los campos
			Integer codigo = LeerCodigo();
			if (codigo == null) return; 
			
			String descripcion = LeerDescripcion();
			if (descripcion == null) return;
			
			Double precioUnitario = LeerPUnitario();
			if (precioUnitario == null) return;
			
			Integer stock = LeerStock();
			if (stock == null) return;
			
			// Verificar si ya existe el código
			Comida c = ac.Buscar(codigo);
			if (c != null) {
				JOptionPane.showMessageDialog(this, 
					"Error: Ya existe un plato con el código " + codigo + ".\n" +
					"Plato existente: " + c.getDescripcion(), 
					"Código Duplicado", JOptionPane.ERROR_MESSAGE);
				txtCodigo.requestFocus();
				return;
			}
			Comida nuevoPlato = new Comida(codigo, descripcion, precioUnitario, stock);
			ac.Adicionar(nuevoPlato);
			
			JOptionPane.showMessageDialog(this, 
				"¡Plato agregado exitosamente!\n" +
				"Código: " + codigo + "\n" +
				"Descripción: " + descripcion + "\n" +
				"Precio: S/. " + String.format("%.2f", precioUnitario) + "\n" +
				"Stock: " + stock + " unidades", 
				"Éxito", JOptionPane.INFORMATION_MESSAGE);

			limpiarCampos();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, 
				"Error inesperado al agregar el plato:\n" + ex.getMessage() + 
				"\n\nPor favor, verifique todos los campos e intente nuevamente.", 
				"Error del Sistema", JOptionPane.ERROR_MESSAGE);
		}

	}
}
