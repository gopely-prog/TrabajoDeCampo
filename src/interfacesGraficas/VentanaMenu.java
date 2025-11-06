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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
    

    private static final String RUTA_LOGO = "/images/logo Polleria.png";
    private JPanel panel_4;
    private JButton btnVerVentas;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaMenu frame = new VentanaMenu();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaMenu() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 699, 426);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(205, 232, 254));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panel = new JPanel();
        panel.setBounds(329, 11, 147, 100);
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

        panel_1 = new JPanel();
        panel_1.setBounds(525, 11, 147, 100);
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

        panel_2 = new JPanel();
        panel_2.setBounds(329, 129, 147, 100);
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

        panel_3 = new JPanel();
        panel_3.setBounds(413, 253, 147, 100);
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
        icono.setHorizontalAlignment(SwingConstants.CENTER);
        icono.setBounds(10, 11, 283, 380);
        contentPane.add(icono);

        javax.swing.SwingUtilities.invokeLater(() -> setScaledIcon(icono, RUTA_LOGO));
        
        panel_4 = new JPanel();
        panel_4.setBounds(525, 129, 147, 100);
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

        icono.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setScaledIcon(icono, RUTA_LOGO);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == btnVerVentas) {
    		do_btnComida_1_actionPerformed(e);
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
        VentanaCompras ventana = new VentanaCompras();
        ventana.setVisible(true);
    }

    protected void do_btnProductos_actionPerformed(ActionEvent e) {
        VentanaProductos ventana = new VentanaProductos();
        ventana.setVisible(true);
    }

    protected void do_btnSalir_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void setScaledIcon(JLabel label, String resourcePath) {
        java.net.URL url = getClass().getResource(resourcePath);
        if (url == null) {
            System.err.println("No se encontró el recurso: " + resourcePath);
            return;
        }
        ImageIcon original = new ImageIcon(url);

        int w = label.getWidth();
        int h = label.getHeight();
        if (w <= 0 || h <= 0) return;

        Image img = original.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(img));
    }
	protected void do_btnComida_1_actionPerformed(ActionEvent e) {
		VentanaListarVentas ventana = new VentanaListarVentas();
	    ventana.setVisible(true);
	}
}