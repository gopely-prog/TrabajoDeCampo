package interfacesGraficas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;

public class VentanaMenu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnTienda;
    private JButton btnCompras;
    private JButton btnComida;
    private JButton btnSalir;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JPanel panel_3;
    private JLabel icono;
    private JPanel panel_4;
    private JButton btnVerVentas;
    private JPanel panel_5;
    private JButton btnProveedores;
    private JLabel lblUsuarioActivo;
    
    // Variable para almacenar el tipo de usuario
    private String tipoUsuario;

    private static final String RUTA_LOGO = "/images/logo Polleria.png";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Para probar, inicia como ADMINISTRADOR
                VentanaMenu frame = new VentanaMenu("ADMINISTRADOR");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Constructor que recibe el tipo de usuario desde el login
     * @param tipoUsuario "ADMINISTRADOR" o "TRABAJADOR"
     */
    public VentanaMenu(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        
        setTitle("POLLERIA EXCELENCIA - Menú Principal");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 699, 550);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(205, 232, 254));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ========== ETIQUETA DE USUARIO ACTIVO ==========
        lblUsuarioActivo = new JLabel("Usuario Activo: " + tipoUsuario);
        lblUsuarioActivo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUsuarioActivo.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsuarioActivo.setBounds(400, 11, 272, 25);
        
        // Color según el rol
        if (tipoUsuario.equals("ADMINISTRADOR")) {
            lblUsuarioActivo.setForeground(new Color(0, 100, 0)); // Verde oscuro
        } else {
            lblUsuarioActivo.setForeground(new Color(0, 0, 139)); // Azul oscuro
        }
        
        contentPane.add(lblUsuarioActivo);

        // ========== BOTÓN TIENDA (Ventas) ==========
        panel = new JPanel();
        panel.setBounds(329, 50, 147, 100);
        contentPane.add(panel);
        panel.setLayout(new GridLayout(1, 0, 0, 0));

        btnTienda = new JButton("Tienda");
        btnTienda.setFont(new Font("Arial", Font.BOLD, 15));
        btnTienda.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/iconTienda.png")));
        panel.add(btnTienda);
        btnTienda.addActionListener(this);
        btnTienda.setBackground(new Color(255, 255, 255));
        btnTienda.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnTienda.setHorizontalTextPosition(SwingConstants.CENTER);

        // ========== BOTÓN COMPRAS ==========
        panel_1 = new JPanel();
        panel_1.setBounds(525, 50, 147, 100);
        contentPane.add(panel_1);
        panel_1.setLayout(new GridLayout(1, 0, 0, 0));

        btnCompras = new JButton("<html><center>Fac.<br>Compras</center></html>");
        btnCompras.setFont(new Font("Arial", Font.BOLD, 14));
        btnCompras.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/iconComida.png")));
        panel_1.add(btnCompras);
        btnCompras.addActionListener(this);
        btnCompras.setBackground(new Color(255, 255, 255));
        btnCompras.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCompras.setHorizontalTextPosition(SwingConstants.CENTER);

        // ========== BOTÓN AGREGAR PLATOS ==========
        panel_2 = new JPanel();
        panel_2.setBounds(329, 168, 147, 100);
        contentPane.add(panel_2);
        panel_2.setLayout(new GridLayout(1, 0, 0, 0));

        btnComida = new JButton("<html><center>Agregar.<br>Platos.</center></html>");
        btnComida.setFont(new Font("Arial", Font.BOLD, 15));
        btnComida.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/iconComida.png")));
        panel_2.add(btnComida);
        btnComida.addActionListener(this);
        btnComida.setBackground(new Color(255, 255, 255));
        btnComida.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnComida.setHorizontalTextPosition(SwingConstants.CENTER);

        // ========== BOTÓN VER VENTAS ==========
        panel_4 = new JPanel();
        panel_4.setBounds(525, 168, 147, 100);
        contentPane.add(panel_4);
        panel_4.setLayout(new GridLayout(1, 0, 0, 0));
        
        btnVerVentas = new JButton("<html><center>Ver<br>Ventas</center></html>");
        btnVerVentas.setFont(new Font("Arial", Font.BOLD, 14));
        btnVerVentas.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/iconTienda.png")));
        btnVerVentas.addActionListener(this);
        btnVerVentas.setBackground(new Color(255, 255, 255));
        btnVerVentas.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnVerVentas.setHorizontalTextPosition(SwingConstants.CENTER);
        panel_4.add(btnVerVentas);

        // ========== BOTÓN PROVEEDORES (NUEVO) ==========
        panel_5 = new JPanel();
        panel_5.setBounds(329, 286, 147, 100);
        contentPane.add(panel_5);
        panel_5.setLayout(new GridLayout(1, 0, 0, 0));
        
        btnProveedores = new JButton("Proveedores");
        btnProveedores.setFont(new Font("Arial", Font.BOLD, 14));
        btnProveedores.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/iconComida.png")));
        btnProveedores.addActionListener(this);
        btnProveedores.setBackground(new Color(255, 255, 255));
        btnProveedores.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnProveedores.setHorizontalTextPosition(SwingConstants.CENTER);
        panel_5.add(btnProveedores);

        // ========== BOTÓN SALIR ==========
        panel_3 = new JPanel();
        panel_3.setBounds(525, 286, 147, 100);
        contentPane.add(panel_3);
        panel_3.setLayout(new GridLayout(1, 0, 0, 0));

        btnSalir = new JButton("Salir");
        btnSalir.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/iconSalir.png")));
        btnSalir.setFont(new Font("Arial", Font.BOLD, 15));
        panel_3.add(btnSalir);
        btnSalir.addActionListener(this);
        btnSalir.setBackground(new Color(255, 255, 255));
        btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);

        icono = new JLabel("");
        icono.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/Sin título (2) (1).png")));
        icono.setHorizontalAlignment(SwingConstants.CENTER);
        icono.setBounds(10, 11, 283, 380);
        contentPane.add(icono);

        aplicarRestriccionesPorRol();
    }
    
    private void aplicarRestriccionesPorRol() {
        if (tipoUsuario.equals("TRABAJADOR")) {
            // TRABAJADOR: Solo puede realizar ventas
            btnCompras.setEnabled(false);
            btnCompras.setToolTipText("Acceso restringido - Solo ADMINISTRADOR");
            
            btnComida.setEnabled(false);
            btnComida.setToolTipText("Acceso restringido - Solo ADMINISTRADOR");
            
            btnVerVentas.setEnabled(false);
            btnVerVentas.setToolTipText("Acceso restringido - Solo ADMINISTRADOR");
            
            btnProveedores.setEnabled(false);
            btnProveedores.setToolTipText("Acceso restringido - Solo ADMINISTRADOR");
            
            // Cambiar color a gris para indicar deshabilitado
            btnCompras.setBackground(new Color(200, 200, 200));
            btnComida.setBackground(new Color(200, 200, 200));
            btnVerVentas.setBackground(new Color(200, 200, 200));
            btnProveedores.setBackground(new Color(200, 200, 200));
            
            System.out.println("✓ Modo TRABAJADOR activado - Solo ventas disponibles");
            
        } else if (tipoUsuario.equals("ADMINISTRADOR")) {
            // ADMINISTRADOR: Acceso completo
            btnTienda.setEnabled(true);
            btnCompras.setEnabled(true);
            btnComida.setEnabled(true);
            btnVerVentas.setEnabled(true);
            btnProveedores.setEnabled(true);
            
            System.out.println("✓ Modo ADMINISTRADOR activado - Acceso completo");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnProveedores) {
            do_btnProveedores_actionPerformed(e);
        }
        if (e.getSource() == btnVerVentas) {
            do_btnVerVentas_actionPerformed(e);
        }
        if (e.getSource() == btnSalir) {
            do_btnSalir_actionPerformed(e);
        }
        if (e.getSource() == btnComida) {
            do_btnProductos_actionPerformed(e);
        }
        if (e.getSource() == btnCompras) {
            do_btnCompras_actionPerformed(e);
        }
        if (e.getSource() == btnTienda) {
            do_btnTienda_actionPerformed(e);
        }
    }

    protected void do_btnTienda_actionPerformed(ActionEvent e) {
        VentanaVentas ventana = new VentanaVentas();
        ventana.setVisible(true);
    }

    protected void do_btnCompras_actionPerformed(ActionEvent e) {
        // Verificación adicional (por seguridad)
        if (tipoUsuario.equals("TRABAJADOR")) {
            JOptionPane.showMessageDialog(this, 
                "Acceso denegado\n\nSolo el ADMINISTRADOR puede acceder a este módulo", 
                "Acceso Restringido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        VentanaCompras ventana = new VentanaCompras();
        ventana.setVisible(true);
    }

    protected void do_btnProductos_actionPerformed(ActionEvent e) {
        if (tipoUsuario.equals("TRABAJADOR")) {
            JOptionPane.showMessageDialog(this, 
                "Acceso denegado\n\nSolo el ADMINISTRADOR puede gestionar productos", 
                "Acceso Restringido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        VentanaProductos ventana = new VentanaProductos();
        ventana.setVisible(true);
    }

    protected void do_btnSalir_actionPerformed(ActionEvent e) {
        VentanaLogin login = new VentanaLogin();
        login.setVisible(true);
        this.dispose();
    }
    
    protected void do_btnVerVentas_actionPerformed(ActionEvent e) {
        // Verificación adicional (por seguridad)
        if (tipoUsuario.equals("TRABAJADOR")) {
            JOptionPane.showMessageDialog(this, 
                "Acceso denegado\n\nSolo el ADMINISTRADOR puede ver el historial", 
                "Acceso Restringido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        VentanaListarVentas ventana = new VentanaListarVentas();
        ventana.setVisible(true);
    }
    
    protected void do_btnProveedores_actionPerformed(ActionEvent e) {
        // Verificación adicional (por seguridad)
        if (tipoUsuario.equals("TRABAJADOR")) {
            JOptionPane.showMessageDialog(this, 
                "Acceso denegado\n\nSolo el ADMINISTRADOR puede gestionar proveedores", 
                "Acceso Restringido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        VentanaProveedores ventana = new VentanaProveedores();
        ventana.setVisible(true);
    }
}