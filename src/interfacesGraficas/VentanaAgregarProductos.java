package interfacesGraficas;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import clases.ArregloComida;
import clases.Comida;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAgregarProductos extends JFrame implements ActionListener {

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
	private JButton btnAgregar;

	public VentanaAgregarProductos() {
		setTitle("Agregar Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
			new Color(255, 255, 255), new Color(160, 160, 160)), 
			"Nuevo Producto", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(10, 11, 364, 289);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblCodigo = new JLabel("Código:");
		lblCodigo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblCodigo.setBounds(10, 25, 100, 20);
		panel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(160, 25, 150, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblDescripcion.setBounds(10, 60, 100, 20);
		panel.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(160, 60, 190, 20);
		panel.add(txtDescripcion);
		
		lblPrecioUnitario = new JLabel("Precio Unitario:");
		lblPrecioUnitario.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblPrecioUnitario.setBounds(10, 95, 140, 20);
		panel.add(lblPrecioUnitario);
		
		txtPrecioUnitario = new JTextField();
		txtPrecioUnitario.setColumns(10);
		txtPrecioUnitario.setBounds(160, 95, 150, 20);
		panel.add(txtPrecioUnitario);
		
		lblCostoUnitario = new JLabel("Costo Unitario:");
		lblCostoUnitario.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblCostoUnitario.setBounds(10, 130, 140, 20);
		panel.add(lblCostoUnitario);
		
		txtCostoUnitario = new JTextField();
		txtCostoUnitario.setColumns(10);
		txtCostoUnitario.setBounds(160, 130, 150, 20);
		panel.add(txtCostoUnitario);
		
		lblPorcentajeGanancia = new JLabel("% Ganancia:");
		lblPorcentajeGanancia.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblPorcentajeGanancia.setBounds(10, 165, 140, 20);
		panel.add(lblPorcentajeGanancia);
		
		txtPorcentajeGanancia = new JTextField();
		txtPorcentajeGanancia.setEditable(false);
		txtPorcentajeGanancia.setBackground(Color.LIGHT_GRAY);
		txtPorcentajeGanancia.setColumns(10);
		txtPorcentajeGanancia.setBounds(160, 165, 150, 20);
		panel.add(txtPorcentajeGanancia);
		
		btnAgregar = new JButton("Agregar Producto");
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 14));
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(10, 230, 344, 40);
		panel.add(btnAgregar);
		
		// Listeners para calcular % ganancia en tiempo real
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
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			do_btnAgregar_actionPerformed(e);
		}
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
	
	Integer LeerCodigo() {
		try {
			String texto = txtCodigo.getText().trim();
			
			if (texto.isEmpty()) {
				JOptionPane.showMessageDialog(this, 
					"Error: El campo código no puede estar vacío.", 
					"Campo Vacío", JOptionPane.ERROR_MESSAGE);
				txtCodigo.requestFocus();
				return null;
			}
			
			int codigo = Integer.parseInt(texto);
			
			if (codigo <= 0) {
				JOptionPane.showMessageDialog(this, 
					"Error: El código debe ser un número positivo.", 
					"Código Inválido", JOptionPane.ERROR_MESSAGE);
				txtCodigo.requestFocus();
				return null;
			}
			
			return codigo;
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, 
				"Error: El código debe ser un número entero válido.", 
				"Formato Inválido", JOptionPane.ERROR_MESSAGE);
			txtCodigo.requestFocus();
			return null;
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
	
	void limpiarCampos() {
		txtCodigo.setText("");
		txtDescripcion.setText("");
		txtPrecioUnitario.setText("");
		txtCostoUnitario.setText("");
		txtPorcentajeGanancia.setText("");
		txtCodigo.requestFocus();
	}
	
	protected void do_btnAgregar_actionPerformed(ActionEvent e) {
		try {
			// Leer y validar todos los campos
			Integer codigo = LeerCodigo();
			if (codigo == null) return;
			
			String descripcion = LeerDescripcion();
			if (descripcion == null) return;
			
			Double precioUnitario = LeerPrecioUnitario();
			if (precioUnitario == null) return;
			
			Double costoUnitario = LeerCostoUnitario();
			if (costoUnitario == null) return;
			
			// Verificar si ya existe el código
			Comida c = ac.Buscar(codigo);
			if (c != null) {
				JOptionPane.showMessageDialog(this, 
					"Error: Ya existe un producto con el código " + codigo + ".\n" +
					"Producto existente: " + c.getDescripcion(), 
					"Código Duplicado", JOptionPane.ERROR_MESSAGE);
				txtCodigo.requestFocus();
				return;
			}
			
			// Advertir si el precio es menor o igual al costo
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
			
			// Crear producto (stock inicia en 0)
			Comida nuevoProducto = new Comida(codigo, descripcion, precioUnitario, costoUnitario);
			ac.Adicionar(nuevoProducto);
			
			JOptionPane.showMessageDialog(this, 
				"¡Producto agregado exitosamente!\n\n" +
				"Código: " + codigo + "\n" +
				"Descripción: " + descripcion + "\n" +
				"Precio Unitario: S/. " + String.format("%.2f", precioUnitario) + "\n" +
				"Costo Unitario: S/. " + String.format("%.2f", costoUnitario) + "\n" +
				"% Ganancia: " + String.format("%.2f%%", nuevoProducto.calcularPorcentajeGanancia()) + "\n" +
				"Stock: 0 (agregue stock desde Fac. Compras)", 
				"Éxito", JOptionPane.INFORMATION_MESSAGE);
			
			limpiarCampos();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, 
				"Error inesperado al agregar el producto:\n" + ex.getMessage(), 
				"Error del Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}
}