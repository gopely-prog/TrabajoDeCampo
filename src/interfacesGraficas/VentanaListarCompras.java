package interfacesGraficas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import clases.*;
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

public class VentanaListarCompras extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnEliminar;
	private JButton btnVerDetalle;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtNumeroCompra;
	private JLabel lblBuscarPorNumero;
	private JButton btnListarTodas;
	private JButton btnSalir;

	public VentanaListarCompras() {
		setTitle("Listado de Compras");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Búsqueda
		lblBuscarPorNumero = new JLabel("Buscar por Número:");
		lblBuscarPorNumero.setFont(new Font("Arial", Font.BOLD, 13));
		lblBuscarPorNumero.setBounds(20, 20, 150, 25);
		contentPane.add(lblBuscarPorNumero);
		
		txtNumeroCompra = new JTextField();
		txtNumeroCompra.setBounds(180, 20, 120, 25);
		contentPane.add(txtNumeroCompra);
		txtNumeroCompra.setColumns(10);
		
		// Tabla de compras
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 800, 380);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nº Compra");
		modelo.addColumn("Tipo Doc.");
		modelo.addColumn("RUC Proveedor");
		modelo.addColumn("Nombre Proveedor");
		modelo.addColumn("Fecha");
		modelo.addColumn("Total");
		table.setModel(modelo);
		
		// Botones
		btnVerDetalle = new JButton("Ver Detalle");
		btnVerDetalle.setFont(new Font("Arial", Font.BOLD, 12));
		btnVerDetalle.addActionListener(this);
		btnVerDetalle.setBounds(20, 461, 130, 30);
		contentPane.add(btnVerDetalle);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(160, 461, 130, 30);
		contentPane.add(btnEliminar);
		
		btnListarTodas = new JButton("Listar Todas");
		btnListarTodas.setFont(new Font("Arial", Font.BOLD, 12));
		btnListarTodas.addActionListener(this);
		btnListarTodas.setBounds(300, 461, 130, 30);
		contentPane.add(btnListarTodas);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Arial", Font.BOLD, 12));
		btnSalir.addActionListener(this);
		btnSalir.setBounds(690, 461, 130, 30);
		contentPane.add(btnSalir);
		
		// Cargar todas las compras al iniciar
		mostrarCompras();
	}
	
	ArregloCompras acompras = ArregloCompras.getInstancia();
	ArregloComida ac = ArregloComida.getInstancia();
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVerDetalle) {
			do_btnVerDetalle_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnListarTodas) {
			do_btnListarTodas_actionPerformed(e);
		}
		if (e.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(e);
		}
	}
	
	protected void do_btnVerDetalle_actionPerformed(ActionEvent e) {
		try {
	        String numeroStr = txtNumeroCompra.getText().trim();
	        
	        if (numeroStr.isEmpty()) {
	            JOptionPane.showMessageDialog(this, 
	                "Debe ingresar un número de compra", 
	                "Número vacío", JOptionPane.WARNING_MESSAGE);
	            txtNumeroCompra.requestFocus();
	            return;
	        }
	        
	        int numero = Integer.parseInt(numeroStr);
	        Compra compra = acompras.Buscar(numero);
	        
	        if (compra == null) {
	            JOptionPane.showMessageDialog(this, 
	                "No existe una compra con el número: " + numero, 
	                "Compra no encontrada", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        // ========== MODIFICACIÓN: Obtener proveedor por ID ==========
	        ArregloProveedor ap = ArregloProveedor.getInstancia();
	        Proveedor proveedor = ap.BuscarPorId(compra.getIdProveedor());
	        
	        String nombreProveedor = proveedor != null ? proveedor.getNombre() : "Desconocido";
	        String rucProveedor = proveedor != null ? proveedor.getRuc() : "---";
	        
	        StringBuilder detalle = new StringBuilder();
	        detalle.append("=== DETALLE DE COMPRA ===\n\n");
	        detalle.append("Número: ").append(compra.getNumeroCompra()).append("\n");
	        detalle.append("Tipo: ").append(compra.getTipoDocumento()).append("\n");
	        detalle.append("RUC Proveedor: ").append(rucProveedor).append("\n");
	        detalle.append("Nombre: ").append(nombreProveedor).append("\n");
	        detalle.append("Fecha: ").append(compra.getFecha()).append("\n\n");
	        detalle.append("--- PRODUCTOS ---\n");
	        
	        // ========== MODIFICACIÓN: Obtener descripción desde ArregloComida ==========
	        for (DetalleCompra dc : compra.getDetalles()) {
	            Comida producto = ac.Buscar(dc.getCodigoProducto());
	            String descripcion = producto != null ? producto.getDescripcion() : "Producto eliminado";
	            
	            detalle.append(String.format("• %s (Código: %d)\n", 
	                descripcion, dc.getCodigoProducto()));
	            detalle.append(String.format("  Cantidad: %d | Costo Unit.: S/. %.2f | Subtotal: S/. %.2f\n\n", 
	                dc.getCantidad(), dc.getCostoUnitario(), dc.getSubtotal()));
	        }
	        
	        detalle.append("--- TOTALES ---\n");
	        detalle.append(String.format("SubTotal: S/. %.2f\n", compra.getSubTotal()));
	        detalle.append(String.format("IGV (18%%): S/. %.2f\n", compra.getIgv()));
	        detalle.append(String.format("TOTAL: S/. %.2f\n", compra.getTotal()));
	        
	        JOptionPane.showMessageDialog(this, detalle.toString(), 
	            "Detalle de Compra", JOptionPane.INFORMATION_MESSAGE);
	        
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, 
	            "El número de compra debe ser un valor numérico válido", 
	            "Formato inválido", JOptionPane.ERROR_MESSAGE);
	        txtNumeroCompra.requestFocus();
	    }
	}
	
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		try {
	        String numeroStr = txtNumeroCompra.getText().trim();
	        
	        if (numeroStr.isEmpty()) {
	            JOptionPane.showMessageDialog(this, 
	                "Debe ingresar un número de compra para eliminar", 
	                "Número vacío", JOptionPane.WARNING_MESSAGE);
	            txtNumeroCompra.requestFocus();
	            return;
	        }
	        
	        int numero = Integer.parseInt(numeroStr);
	        
	        // ========== USAR ELIMINACIÓN SEGURA ==========
	        if (EliminacionSegura.eliminarCompraSegura(numero)) {
	            txtNumeroCompra.setText("");
	            mostrarCompras();
	        }
	        
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, 
	            "El número de compra debe ser un valor numérico válido", 
	            "Formato inválido", JOptionPane.ERROR_MESSAGE);
	        txtNumeroCompra.requestFocus();
	    }
	}
	
	protected void do_btnListarTodas_actionPerformed(ActionEvent e) {
		mostrarCompras();
		JOptionPane.showMessageDialog(this, 
			"Mostrando todas las compras (" + acompras.Tamaño() + ")", 
			"Lista actualizada", JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	public void mostrarCompras() {
		acompras.Listar(table);
	}
}