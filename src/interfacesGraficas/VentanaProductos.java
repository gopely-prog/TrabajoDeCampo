package interfacesGraficas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import clases.ArregloComida;
import clases.Comida;
import clases.EliminacionSegura;

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
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtCodigoBuscar;
	private JTextField txtDescripcionBuscar;
	private JLabel lblBuscarPorCodigo;
	private JLabel lblBuscarPorDescripcion;
	private JButton btnListarTodos;
	// Almacena el producto seleccionado en la tabla
	private Comida productoSeleccionado = null;

	public VentanaProductos() {
		setTitle("Gestión de Productos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				seleccionarProductoDeTabla();
			}
		});
		
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
		
		lblBuscarPorCodigo = new JLabel("Buscar por Código:");
		lblBuscarPorCodigo.setFont(new Font("Arial", Font.BOLD, 13));
		lblBuscarPorCodigo.setBounds(20, 20, 150, 25);
		contentPane.add(lblBuscarPorCodigo);
		
		txtCodigoBuscar = new JTextField();
		txtCodigoBuscar.setBounds(20, 50, 150, 25);
		contentPane.add(txtCodigoBuscar);
		txtCodigoBuscar.setColumns(10);
		
		txtCodigoBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				filtrarProductos();
			}
		});
		
		lblBuscarPorDescripcion = new JLabel("Buscar por Descripción:");
		lblBuscarPorDescripcion.setFont(new Font("Arial", Font.BOLD, 13));
		lblBuscarPorDescripcion.setBounds(200, 20, 180, 25);
		contentPane.add(lblBuscarPorDescripcion);
		
		txtDescripcionBuscar = new JTextField();
		txtDescripcionBuscar.setColumns(10);
		txtDescripcionBuscar.setBounds(200, 50, 300, 25);
		contentPane.add(txtDescripcionBuscar);
		
		btnVerProveedores = new JButton("Ver Proveedores");
		btnVerProveedores.addActionListener(this);
		btnVerProveedores.setFont(new Font("Arial", Font.BOLD, 12));
		btnVerProveedores.setBounds(486, 460, 141, 30);
		btnVerProveedores.setToolTipText("Ver todos los proveedores que han vendido este producto");
		contentPane.add(btnVerProveedores);
		
		txtDescripcionBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				filtrarProductos();
			}
		});
		
		mostrarProductos();
	}
	
	ArregloComida ac = ArregloComida.getInstancia();
	private JButton btnVerProveedores;
	// Filtra productos en tiempo real según código y/o descripción
	private void filtrarProductos() {
		String textoCodigo = txtCodigoBuscar.getText().trim().toLowerCase();
		String textoDescripcion = txtDescripcionBuscar.getText().trim().toLowerCase();
		
		if (textoCodigo.isEmpty() && textoDescripcion.isEmpty()) {
			mostrarProductos();
			return;
		}
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		
		for (int i = 0; i < ac.Tamaño(); i++) {
			Comida producto = ac.obtenerPorIndice(i);
			
			String codigoProducto = String.valueOf(producto.getCodigo()).toLowerCase();
			String descripcionProducto = producto.getDescripcion().toLowerCase();
			
			boolean cumpleCodigo = textoCodigo.isEmpty() || codigoProducto.contains(textoCodigo);
			boolean cumpleDescripcion = textoDescripcion.isEmpty() || descripcionProducto.contains(textoDescripcion);
			
			if (cumpleCodigo && cumpleDescripcion) {
				modelo.addRow(new Object[]{
					producto.getCodigo(),
					producto.getDescripcion(),
					String.format("S/. %.2f", producto.getpUnitario()),
					String.format("S/. %.2f", producto.getCostoUnitario()),
					String.format("%.2f%%", producto.calcularPorcentajeGanancia()),
					producto.getStock()
				});
			}
		}
		
		int resultados = modelo.getRowCount();
		
	}
	// Captura el producto seleccionado al hacer clic en la tabla
	private void seleccionarProductoDeTabla() {
		int filaSeleccionada = table.getSelectedRow();
		
		if (filaSeleccionada != -1) {
			int codigo = (int) table.getValueAt(filaSeleccionada, 0);
			
			productoSeleccionado = ac.Buscar(codigo);
			
			if (productoSeleccionado != null) {
				System.out.println("Producto seleccionado: " + productoSeleccionado.getDescripcion());
				
				table.setSelectionBackground(new Color(144, 238, 144)); 
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVerProveedores) {
			do_btnVerProveedores_actionPerformed(e);
		}
		if (e.getSource() == btnAgregar) {
			do_btnAgregar_actionPerformed(e);
		}
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
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
				limpiarFiltros();
			}
		});
		ventana.setVisible(true);
	}
	
	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		if (productoSeleccionado == null) {
			JOptionPane.showMessageDialog(this, 
				"Debe seleccionar un producto de la tabla para modificar\n\n" +
				"Haga clic sobre la fila del producto que desea modificar", 
				"Ningún producto seleccionado", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		VentanaModificarProducto ventana = new VentanaModificarProducto(productoSeleccionado);
		ventana.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				mostrarProductos();
				limpiarFiltros();
				productoSeleccionado = null; 
			}
		});
		ventana.setVisible(true);
	}
	// Llama a EliminacionSegura para verificar referencias FK
	//Muestra si que hay movimiento con ese producto.
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		
	    if (productoSeleccionado == null) {
	        JOptionPane.showMessageDialog(this, 
	            "Debe seleccionar un producto de la tabla para eliminar\n\n" +
	            "Haga clic sobre la fila del producto que desea eliminar", 
	            "Ningún producto seleccionado", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    
	    String infoUso = EliminacionSegura.obtenerInfoUsoProducto(productoSeleccionado.getCodigo());
	    
	    int opcion = JOptionPane.showOptionDialog(this,
	        infoUso + "\n¿Desea intentar eliminar este producto?",
	        "Información del Producto",
	        JOptionPane.YES_NO_CANCEL_OPTION,
	        JOptionPane.QUESTION_MESSAGE,
	        null,
	        new Object[]{"Ver más info", "Eliminar", "Cancelar"},
	        "Cancelar");
	    
	    if (opcion == 1) { 
	    	
	        if (EliminacionSegura.eliminarProductoSeguro(productoSeleccionado.getCodigo())) {
	            JOptionPane.showMessageDialog(this,
	                "✅ Producto eliminado exitosamente\n\n" +
	                "El producto no tenía referencias en compras ni ventas.",
	                "Éxito",
	                JOptionPane.INFORMATION_MESSAGE);
	            
	            productoSeleccionado = null;
	            mostrarProductos();
	            limpiarFiltros();
	        }
	    }
	}
	
	protected void do_btnListarTodos_actionPerformed(ActionEvent e) {
		limpiarFiltros();
		mostrarProductos();
		productoSeleccionado = null;
		JOptionPane.showMessageDialog(this, 
			"Mostrando todos los productos (" + ac.Tamaño() + ")", 
			"Lista actualizada", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void limpiarFiltros() {
		txtCodigoBuscar.setText("");
		txtDescripcionBuscar.setText("");
	}
	
	public void mostrarProductos() {
		ac.Listar(table);
	}
	protected void do_btnVerProveedores_actionPerformed(ActionEvent e) {
				if (productoSeleccionado == null) {
					JOptionPane.showMessageDialog(this, 
						"Debe seleccionar un producto de la tabla\n\n" +
						"Haga clic sobre la fila del producto cuyos proveedores desea ver", 
						"Ningún producto seleccionado", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				VentanaProveedoresProducto ventana = new VentanaProveedoresProducto(productoSeleccionado);
				ventana.setVisible(true);
				
				System.out.println("Abriendo ventana de proveedores para: " + 
				                   productoSeleccionado.getDescripcion());
	}
}