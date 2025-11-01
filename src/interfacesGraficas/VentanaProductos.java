package interfacesGraficas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import clases.ArregloComida;
import clases.Comida;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VentanaProductos extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtCodigoBuscar;
	private JTextField txtDescripcionBuscar;
	private JLabel lblBuscarPorCodigo;
	private JLabel lblBuscarPorDescripcion;
	private JButton btnListarTodos;

	public VentanaProductos() {
		setTitle("Gestión de Productos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Tabla de productos
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 100, 690, 350);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Descripción"); 
		modelo.addColumn("P. Unitario");
		modelo.addColumn("Costo Unit.");
		modelo.addColumn("% Ganancia");
		modelo.addColumn("Stock");
		table.setModel(modelo);
		
		// Botones principales
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 12));
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(20, 461, 100, 30);
		contentPane.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Arial", Font.BOLD, 12));
		btnModificar.addActionListener(this);
		btnModificar.setBounds(130, 461, 100, 30);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(240, 461, 100, 30);
		contentPane.add(btnEliminar);
		
		btnListarTodos = new JButton("Listar Todos");
		btnListarTodos.setFont(new Font("Arial", Font.BOLD, 12));
		btnListarTodos.addActionListener(this);
		btnListarTodos.setBounds(350, 461, 120, 30);
		contentPane.add(btnListarTodos);
		
		// Búsqueda por código
		lblBuscarPorCodigo = new JLabel("Buscar por Código:");
		lblBuscarPorCodigo.setFont(new Font("Arial", Font.BOLD, 13));
		lblBuscarPorCodigo.setBounds(20, 20, 150, 25);
		contentPane.add(lblBuscarPorCodigo);
		
		txtCodigoBuscar = new JTextField();
		txtCodigoBuscar.setBounds(20, 50, 150, 25);
		contentPane.add(txtCodigoBuscar);
		txtCodigoBuscar.setColumns(10);
		
		// Búsqueda por descripción
		lblBuscarPorDescripcion = new JLabel("Buscar por Descripción:");
		lblBuscarPorDescripcion.setFont(new Font("Arial", Font.BOLD, 13));
		lblBuscarPorDescripcion.setBounds(200, 20, 180, 25);
		contentPane.add(lblBuscarPorDescripcion);
		
		txtDescripcionBuscar = new JTextField();
		txtDescripcionBuscar.setColumns(10);
		txtDescripcionBuscar.setBounds(200, 50, 200, 25);
		contentPane.add(txtDescripcionBuscar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(420, 50, 100, 25);
		contentPane.add(btnBuscar);
		
		// Cargar todos los productos al iniciar
		mostrarProductos();
	}
	
	ArregloComida ac = ArregloComida.getInstancia();
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			do_btnAgregar_actionPerformed(e);
		}
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
		if (e.getSource() == btnListarTodos) {
			do_btnListarTodos_actionPerformed(e);
		}
	}
	
	protected void do_btnAgregar_actionPerformed(ActionEvent e) {
		VentanaAgregarProductos ventana = new VentanaAgregarProductos();
		ventana.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				mostrarProductos();
			}
		});
		ventana.setVisible(true);
	}
	
	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		try {
			String codigoStr = txtCodigoBuscar.getText().trim();
			
			if (codigoStr.isEmpty()) {
				JOptionPane.showMessageDialog(this, 
					"Debe ingresar un código para modificar", 
					"Código vacío", JOptionPane.WARNING_MESSAGE);
				txtCodigoBuscar.requestFocus();
				return;
			}
			
			int codigo = Integer.parseInt(codigoStr);
			Comida producto = ac.Buscar(codigo);
			
			if (producto == null) {
				JOptionPane.showMessageDialog(this, 
					"No existe un producto con el código: " + codigo, 
					"Producto no encontrado", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			VentanaModificarProducto ventana = new VentanaModificarProducto(producto);
			ventana.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosed(java.awt.event.WindowEvent windowEvent) {
					mostrarProductos();
					txtCodigoBuscar.setText("");
				}
			});
			ventana.setVisible(true);
			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, 
				"El código debe ser un número válido", 
				"Formato inválido", JOptionPane.ERROR_MESSAGE);
			txtCodigoBuscar.requestFocus();
		}
	}
	
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		try {
			String codigoStr = txtCodigoBuscar.getText().trim();
			
			if (codigoStr.isEmpty()) {
				JOptionPane.showMessageDialog(this, 
					"Debe ingresar un código para eliminar", 
					"Código vacío", JOptionPane.WARNING_MESSAGE);
				txtCodigoBuscar.requestFocus();
				return;
			}
			
			int codigo = Integer.parseInt(codigoStr);
			Comida producto = ac.Buscar(codigo);
			
			if (producto == null) {
				JOptionPane.showMessageDialog(this, 
					"No existe un producto con el código: " + codigo, 
					"Producto no encontrado", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int respuesta = JOptionPane.showConfirmDialog(this, 
				"¿Está seguro de eliminar el producto?\n\n" +
				"Código: " + producto.getCodigo() + "\n" +
				"Descripción: " + producto.getDescripcion() + "\n" +
				"Stock actual: " + producto.getStock(),
				"Confirmar eliminación", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE);
			
			if (respuesta == JOptionPane.YES_OPTION) {
				ac.Eliminar(producto);
				JOptionPane.showMessageDialog(this, 
					"Producto eliminado exitosamente", 
					"Éxito", JOptionPane.INFORMATION_MESSAGE);
				txtCodigoBuscar.setText("");
				mostrarProductos();
			}
			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, 
				"El código debe ser un número válido", 
				"Formato inválido", JOptionPane.ERROR_MESSAGE);
			txtCodigoBuscar.requestFocus();
		}
	}
	
	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
		String codigoStr = txtCodigoBuscar.getText().trim();
		String descripcion = txtDescripcionBuscar.getText().trim();
		
		// Validar que al menos uno esté lleno
		if (codigoStr.isEmpty() && descripcion.isEmpty()) {
			JOptionPane.showMessageDialog(this, 
				"Debe ingresar un código o una descripción para buscar", 
				"Campos vacíos", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Comida encontrado = null;
		
		// Buscar por código si está lleno
		if (!codigoStr.isEmpty()) {
			try {
				int codigo = Integer.parseInt(codigoStr);
				encontrado = ac.Buscar(codigo);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, 
					"El código debe ser un número válido", 
					"Formato inválido", JOptionPane.ERROR_MESSAGE);
				txtCodigoBuscar.requestFocus();
				return;
			}
		}
		// Buscar por descripción si código no dio resultado
		else if (!descripcion.isEmpty()) {
			encontrado = ac.BuscarPorDescripcion(descripcion);
		}
		
		// Mostrar resultado
		if (encontrado != null) {
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			modelo.setRowCount(0);
			modelo.addRow(new Object[]{
				encontrado.getCodigo(),
				encontrado.getDescripcion(),
				String.format("S/. %.2f", encontrado.getpUnitario()),
				String.format("S/. %.2f", encontrado.getCostoUnitario()),
				String.format("%.2f%%", encontrado.calcularPorcentajeGanancia()),
				encontrado.getStock()
			});
			
			JOptionPane.showMessageDialog(this, 
				"Producto encontrado", 
				"Éxito", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, 
				"No se encontró el producto", 
				"Sin resultados", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	protected void do_btnListarTodos_actionPerformed(ActionEvent e) {
		mostrarProductos();
		JOptionPane.showMessageDialog(this, 
			"Mostrando todos los productos (" + ac.Tamaño() + ")", 
			"Lista actualizada", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void mostrarProductos() {
		ac.Listar(table);
	}
}