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
import java.awt.Font;

public class VentanaListarVentas extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnEliminar;
	private JButton btnVerDetalle;
	private JButton btnAbrirArchivo;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtNumeroVenta;
	private JLabel lblBuscarPorNumero;
	private JButton btnListarTodas;
	private JButton btnSalir;

	public VentanaListarVentas() {
		setTitle("Listado de Ventas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 550);
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
		
		txtNumeroVenta = new JTextField();
		txtNumeroVenta.setBounds(180, 20, 120, 25);
		contentPane.add(txtNumeroVenta);
		txtNumeroVenta.setColumns(10);
		
		// Tabla de ventas
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 850, 380);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nº Venta");
		modelo.addColumn("Tipo Doc.");
		modelo.addColumn("RUC Cliente");
		modelo.addColumn("Cliente");
		modelo.addColumn("Fecha");
		modelo.addColumn("Total");
		table.setModel(modelo);
		
		// Botones
		btnVerDetalle = new JButton("Ver Detalle");
		btnVerDetalle.setFont(new Font("Arial", Font.BOLD, 12));
		btnVerDetalle.addActionListener(this);
		btnVerDetalle.setBounds(20, 461, 130, 30);
		contentPane.add(btnVerDetalle);
		
		btnAbrirArchivo = new JButton("Abrir Archivo");
		btnAbrirArchivo.setFont(new Font("Arial", Font.BOLD, 12));
		btnAbrirArchivo.addActionListener(this);
		btnAbrirArchivo.setBounds(160, 461, 130, 30);
		contentPane.add(btnAbrirArchivo);
		
		btnEliminar = new JButton("Anular Venta");
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(300, 461, 130, 30);
		contentPane.add(btnEliminar);
		
		btnListarTodas = new JButton("Listar Todas");
		btnListarTodas.setFont(new Font("Arial", Font.BOLD, 12));
		btnListarTodas.addActionListener(this);
		btnListarTodas.setBounds(440, 461, 130, 30);
		contentPane.add(btnListarTodas);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Arial", Font.BOLD, 12));
		btnSalir.addActionListener(this);
		btnSalir.setBounds(740, 461, 130, 30);
		contentPane.add(btnSalir);
		
		// Cargar todas las ventas al iniciar
		mostrarVentas();
	}
	
	ArregloVentas av = ArregloVentas.getInstancia();
	ArregloComida ac = ArregloComida.getInstancia();
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVerDetalle) {
			do_btnVerDetalle_actionPerformed(e);
		}
		if (e.getSource() == btnAbrirArchivo) {
			do_btnAbrirArchivo_actionPerformed(e);
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
	        String numeroStr = txtNumeroVenta.getText().trim();
	        
	        if (numeroStr.isEmpty()) {
	            JOptionPane.showMessageDialog(this, 
	                "Debe ingresar un número de venta", 
	                "Número vacío", JOptionPane.WARNING_MESSAGE);
	            txtNumeroVenta.requestFocus();
	            return;
	        }
	        
	        int numero = Integer.parseInt(numeroStr);
	        Venta venta = av.Buscar(numero);
	        
	        if (venta == null) {
	            JOptionPane.showMessageDialog(this, 
	                "No existe una venta con el número: " + numero, 
	                "Venta no encontrada", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        StringBuilder detalle = new StringBuilder();
	        detalle.append("=== DETALLE DE VENTA ===\n\n");
	        detalle.append("Número: ").append(venta.getNumeroVenta()).append("\n");
	        detalle.append("Tipo: ").append(venta.getTipoDocumento()).append("\n");
	        
	        if (venta.getRucCliente() != null) {
	            detalle.append("RUC Cliente: ").append(venta.getRucCliente()).append("\n");
	        }
	        
	        detalle.append("Cliente: ").append(venta.getRazonSocial()).append("\n");
	        
	        if (venta.getDomicilio() != null) {
	            detalle.append("Domicilio: ").append(venta.getDomicilio()).append("\n");
	        }
	        
	        detalle.append("Fecha: ").append(venta.getFecha()).append("\n\n");
	        detalle.append("--- PRODUCTOS ---\n");
	        
	        // ========== MODIFICACIÓN: Obtener descripción desde ArregloComida ==========
	        for (DetalleVenta dv : venta.getDetalles()) {
	            Comida producto = ac.Buscar(dv.getCodigoProducto());
	            String descripcion = producto != null ? producto.getDescripcion() : "Producto eliminado";
	            
	            detalle.append(String.format("• %s (Código: %d)\n", 
	                descripcion, dv.getCodigoProducto()));
	            detalle.append(String.format("  Cantidad: %d | Precio Unit.: S/. %.2f | Subtotal: S/. %.2f\n\n", 
	                dv.getCantidad(), dv.getPrecioUnitario(), dv.getSubtotal()));
	        }
	        
	        detalle.append("--- TOTALES ---\n");
	        detalle.append(String.format("SubTotal: S/. %.2f\n", venta.getSubTotal()));
	        detalle.append(String.format("IGV (18%%): S/. %.2f\n", venta.getIgv()));
	        detalle.append(String.format("TOTAL: S/. %.2f\n", venta.getTotal()));
	        
	        JOptionPane.showMessageDialog(this, detalle.toString(), 
	            "Detalle de Venta", JOptionPane.INFORMATION_MESSAGE);
	        
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, 
	            "El número de venta debe ser un valor numérico válido", 
	            "Formato inválido", JOptionPane.ERROR_MESSAGE);
	        txtNumeroVenta.requestFocus();
	    }
	}
	
	protected void do_btnAbrirArchivo_actionPerformed(ActionEvent e) {
		try {
			String numeroStr = txtNumeroVenta.getText().trim();
			
			if (numeroStr.isEmpty()) {
				JOptionPane.showMessageDialog(this, 
					"Debe ingresar un número de venta", 
					"Número vacío", JOptionPane.WARNING_MESSAGE);
				txtNumeroVenta.requestFocus();
				return;
			}
			
			int numero = Integer.parseInt(numeroStr);
			Venta venta = av.Buscar(numero);
			
			if (venta == null) {
				JOptionPane.showMessageDialog(this, 
					"No existe una venta con el número: " + numero, 
					"Venta no encontrada", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// Abrir archivo de la venta
			String rutaArchivo = venta.getRutaArchivo();
			if (rutaArchivo != null && !rutaArchivo.isEmpty()) {
				GestorBoletas.abrirArchivo(rutaArchivo);
				JOptionPane.showMessageDialog(this, 
					"Abriendo archivo de la venta...", 
					"Archivo", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, 
					"No se encontró el archivo asociado a esta venta", 
					"Archivo no disponible", JOptionPane.WARNING_MESSAGE);
			}
			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, 
				"El número de venta debe ser un valor numérico válido", 
				"Formato inválido", JOptionPane.ERROR_MESSAGE);
			txtNumeroVenta.requestFocus();
		}
	}
	
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		try {
	        String numeroStr = txtNumeroVenta.getText().trim();
	        
	        if (numeroStr.isEmpty()) {
	            JOptionPane.showMessageDialog(this, 
	                "Debe ingresar un número de venta para anular", 
	                "Número vacío", JOptionPane.WARNING_MESSAGE);
	            txtNumeroVenta.requestFocus();
	            return;
	        }
	        
	        int numero = Integer.parseInt(numeroStr);
	        
	        // ========== USAR ELIMINACIÓN SEGURA ==========
	        if (EliminacionSegura.eliminarVentaSegura(numero)) {
	            txtNumeroVenta.setText("");
	            mostrarVentas();
	        }
	        
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, 
	            "El número de venta debe ser un valor numérico válido", 
	            "Formato inválido", JOptionPane.ERROR_MESSAGE);
	        txtNumeroVenta.requestFocus();
	    }
	}
	
	protected void do_btnListarTodas_actionPerformed(ActionEvent e) {
		mostrarVentas();
		JOptionPane.showMessageDialog(this, 
			"Mostrando todas las ventas (" + av.Tamaño() + ")", 
			"Lista actualizada", JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	public void mostrarVentas() {
		av.Listar(table);
	}
}