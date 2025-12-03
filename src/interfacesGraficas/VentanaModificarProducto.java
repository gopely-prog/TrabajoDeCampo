package interfacesGraficas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
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

public class VentanaModificarProducto extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblDescripcion;
	private JTextField txtDescripcion;
	private JLabel lblPrecioUnitario;
	private JTextField txtPrecioUnitario;
	private JLabel lblCostoUnitario;
	private JTextField txtCostoUnitario;
	private JLabel lblPorcentajeGanancia;
	private JTextField txtPorcentajeGanancia;
	private JLabel lblStockActual;
	private JTextField txtStockActual;
	private JButton btnModificar;
	
	private Comida productoOriginal;

	public VentanaModificarProducto(Comida producto) {
		this.productoOriginal = producto;
		
		setTitle("Modificar Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
			new Color(255, 255, 255), new Color(160, 160, 160)), 
			"Modificar Producto", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(10, 11, 364, 339);
		contentPane.add(panel);
		
		lblCodigo = new JLabel("Código:");
		lblCodigo.setForeground(Color.BLACK);
		lblCodigo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblCodigo.setBounds(10, 25, 100, 20);
		panel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBackground(Color.LIGHT_GRAY);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(160, 25, 150, 20);
		panel.add(txtCodigo);
		
		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setForeground(Color.BLACK);
		lblDescripcion.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblDescripcion.setBounds(10, 60, 100, 20);
		panel.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(160, 60, 190, 20);
		panel.add(txtDescripcion);
		
		lblPrecioUnitario = new JLabel("Precio Unitario:");
		lblPrecioUnitario.setForeground(Color.BLACK);
		lblPrecioUnitario.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblPrecioUnitario.setBounds(10, 95, 140, 20);
		panel.add(lblPrecioUnitario);
		
		txtPrecioUnitario = new JTextField();
		txtPrecioUnitario.setColumns(10);
		txtPrecioUnitario.setBounds(160, 95, 150, 20);
		panel.add(txtPrecioUnitario);
		
		lblCostoUnitario = new JLabel("Costo Unitario:");
		lblCostoUnitario.setForeground(Color.BLACK);
		lblCostoUnitario.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblCostoUnitario.setBounds(10, 130, 140, 20);
		panel.add(lblCostoUnitario);
		
		txtCostoUnitario = new JTextField();
		txtCostoUnitario.setColumns(10);
		txtCostoUnitario.setBounds(160, 130, 150, 20);
		panel.add(txtCostoUnitario);
		
		lblPorcentajeGanancia = new JLabel("% Ganancia:");
		lblPorcentajeGanancia.setForeground(Color.BLACK);
		lblPorcentajeGanancia.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblPorcentajeGanancia.setBounds(10, 165, 140, 20);
		panel.add(lblPorcentajeGanancia);
		
		txtPorcentajeGanancia = new JTextField();
		txtPorcentajeGanancia.setEditable(false);
		txtPorcentajeGanancia.setBackground(Color.LIGHT_GRAY);
		txtPorcentajeGanancia.setColumns(10);
		txtPorcentajeGanancia.setBounds(160, 165, 150, 20);
		panel.add(txtPorcentajeGanancia);
		
		lblStockActual = new JLabel("Stock Actual:");
		lblStockActual.setForeground(Color.BLACK);
		lblStockActual.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblStockActual.setBounds(10, 200, 140, 20);
		panel.add(lblStockActual);
		
		txtStockActual = new JTextField();
		txtStockActual.setEditable(false);
		txtStockActual.setBackground(Color.LIGHT_GRAY);
		txtStockActual.setColumns(10);
		txtStockActual.setBounds(160, 200, 150, 20);
		panel.add(txtStockActual);
		
		btnModificar = new JButton("Guardar Cambios");
		btnModificar.addActionListener(this);
		btnModificar.setFont(new Font("Arial", Font.BOLD, 14));
		btnModificar.setBounds(10, 270, 344, 40);
		panel.add(btnModificar);
		
		cargarDatos();
		
		txtPrecioUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				calcularPorcentajeGanancia();
			}
		});
		
		txtCostoUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				calcularPorcentajeGanancia();
			}
		});
	}
	
	ArregloComida ac = ArregloComida.getInstancia();
	
	private void cargarDatos() {
		txtCodigo.setText(String.valueOf(productoOriginal.getCodigo()));
		txtDescripcion.setText(productoOriginal.getDescripcion());
		txtPrecioUnitario.setText(String.valueOf(productoOriginal.getpUnitario()));
		txtCostoUnitario.setText(String.valueOf(productoOriginal.getCostoUnitario()));
		txtPorcentajeGanancia.setText(String.format("%.2f%%", productoOriginal.calcularPorcentajeGanancia()));
		txtStockActual.setText(String.valueOf(productoOriginal.getStock()));
	}
	
	private void calcularPorcentajeGanancia() {
		try {
			String precioStr = txtPrecioUnitario.getText().trim();
			String costoStr = txtCostoUnitario.getText().trim();
			
			if (!precioStr.isEmpty() && !costoStr.isEmpty()) {
				double precio = Double.parseDouble(precioStr);
				double costo = Double.parseDouble(costoStr);
				
				if (costo > 0) {
					double porcentaje = ((precio - costo) / costo) * 100;
					txtPorcentajeGanancia.setText(String.format("%.2f%%", porcentaje));
				}
			}
		} catch (NumberFormatException e) {
			txtPorcentajeGanancia.setText("");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
	}
	
	String LeerDescripcion() {
		try {
			String descripcion = txtDescripcion.getText().trim();
			
			if (descripcion.isEmpty()) {
				JOptionPane.showMessageDialog(this, 
					"Error: El campo descripción no puede estar vacío.", 
					"Campo Vacío", JOptionPane.ERROR_MESSAGE);
				txtDescripcion.requestFocus();
				return null;
			}
			
			if (descripcion.matches("^[0-9]+$")) {
				JOptionPane.showMessageDialog(this, 
					"Error: La descripción no puede contener solo números.", 
					"Descripción Inválida", JOptionPane.ERROR_MESSAGE);
				txtDescripcion.requestFocus();
				return null;
			}
			
			return descripcion;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, 
				"Error inesperado al leer la descripción: " + e.getMessage(), 
				"Error", JOptionPane.ERROR_MESSAGE);
			txtDescripcion.requestFocus();
			return null;
		}
	}
	
	Double LeerPrecioUnitario() {
		try {
			String texto = txtPrecioUnitario.getText().trim();
			
			if (texto.isEmpty()) {
				JOptionPane.showMessageDialog(this, 
					"Error: El campo precio unitario no puede estar vacío.", 
					"Campo Vacío", JOptionPane.ERROR_MESSAGE);
				txtPrecioUnitario.requestFocus();
				return null;
			}
			
			double precio = Double.parseDouble(texto);
			
			if (precio <= 0) {
				JOptionPane.showMessageDialog(this, 
					"Error: El precio unitario debe ser mayor a 0.", 
					"Precio Inválido", JOptionPane.ERROR_MESSAGE);
				txtPrecioUnitario.requestFocus();
				return null;
			}
			
			return precio;
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, 
				"Error: El precio unitario debe ser un número válido (use punto para decimales).", 
				"Formato Inválido", JOptionPane.ERROR_MESSAGE);
			txtPrecioUnitario.requestFocus();
			return null;
		}
	}
	
	Double LeerCostoUnitario() {
		try {
			String texto = txtCostoUnitario.getText().trim();
			
			if (texto.isEmpty()) {
				JOptionPane.showMessageDialog(this, 
					"Error: El campo costo unitario no puede estar vacío.", 
					"Campo Vacío", JOptionPane.ERROR_MESSAGE);
				txtCostoUnitario.requestFocus();
				return null;
			}
			
			double costo = Double.parseDouble(texto);
			
			if (costo <= 0) {
				JOptionPane.showMessageDialog(this, 
					"Error: El costo unitario debe ser mayor a 0.", 
					"Costo Inválido", JOptionPane.ERROR_MESSAGE);
				txtCostoUnitario.requestFocus();
				return null;
			}
			
			return costo;
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, 
				"Error: El costo unitario debe ser un número válido (use punto para decimales).", 
				"Formato Inválido", JOptionPane.ERROR_MESSAGE);
			txtCostoUnitario.requestFocus();
			return null;
		}
	}
	
	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		try {
			String descripcion = LeerDescripcion();
			if (descripcion == null) return;
			
			Double precioUnitario = LeerPrecioUnitario();
			if (precioUnitario == null) return;
			
			Double costoUnitario = LeerCostoUnitario();
			if (costoUnitario == null) return;
			
			if (precioUnitario <= costoUnitario) {
				int respuesta = JOptionPane.showConfirmDialog(this, 
					"ADVERTENCIA: El precio de venta es menor o igual al costo.\n\n" +
					"Precio Unitario: S/. " + String.format("%.2f", precioUnitario) + "\n" +
					"Costo Unitario: S/. " + String.format("%.2f", costoUnitario) + "\n" +
					"Ganancia: " + String.format("%.2f%%", ((precioUnitario - costoUnitario) / costoUnitario) * 100) + "\n\n" +
					"¿Desea continuar de todas formas?", 
					"Precio bajo detectado", 
					JOptionPane.YES_NO_OPTION, 
					JOptionPane.WARNING_MESSAGE);
				
				if (respuesta != JOptionPane.YES_OPTION) {
					txtPrecioUnitario.requestFocus();
					return;
				}
			}
			
			int codigo = productoOriginal.getCodigo();
			if (ac.Modificar(codigo, descripcion, precioUnitario, costoUnitario)) {
				JOptionPane.showMessageDialog(this, 
					"¡Producto modificado exitosamente!\n\n" +
					"Código: " + codigo + "\n" +
					"Descripción: " + descripcion + "\n" +
					"Precio Unitario: S/. " + String.format("%.2f", precioUnitario) + "\n" +
					"Costo Unitario: S/. " + String.format("%.2f", costoUnitario) + "\n" +
					"% Ganancia: " + String.format("%.2f%%", productoOriginal.calcularPorcentajeGanancia()), 
					"Éxito", JOptionPane.INFORMATION_MESSAGE);
				
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, 
					"Error: No se pudo modificar el producto.", 
					"Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, 
				"Error inesperado: " + e1.getMessage(), 
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}