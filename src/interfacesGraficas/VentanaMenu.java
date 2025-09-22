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
    private JButton btnComida;
    private JButton btnSalir;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JLabel icono;

    private static final String RUTA_LOGO = "/images/logo Polleria.png";

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
        setBounds(100, 100, 507, 475);
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
        panel_1.setBounds(329, 153, 147, 100);
        contentPane.add(panel_1);
        panel_1.setLayout(new GridLayout(1, 0, 0, 0));

        btnComida = new JButton("Comida");
        btnComida.setFont(new Font("Arial", Font.BOLD, 15));
        btnComida.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/iconComida.png")));
        panel_1.add(btnComida);
        btnComida.addActionListener(this);
        btnComida.setBackground(new Color(255, 255, 255));
        btnComida.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnComida.setHorizontalTextPosition(SwingConstants.CENTER);

        panel_2 = new JPanel();
        panel_2.setBounds(329, 298, 147, 100);
        contentPane.add(panel_2);
        panel_2.setLayout(new GridLayout(1, 0, 0, 0));

        btnSalir = new JButton("Salir");
        btnSalir.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/iconSalir.png")));
        btnSalir.setFont(new Font("Arial", Font.BOLD, 15));
        panel_2.add(btnSalir);
        btnSalir.addActionListener(this);
        btnSalir.setBackground(new Color(255, 255, 255));
        btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);

        icono = new JLabel("");
        icono.setHorizontalAlignment(SwingConstants.CENTER);
        icono.setBounds(0, 11, 331, 414); 
        contentPane.add(icono);

        javax.swing.SwingUtilities.invokeLater(() -> setScaledIcon(icono, RUTA_LOGO));

        
        icono.addComponentListener(new ComponentAdapter() {
            @Override public void componentResized(ComponentEvent e) {
                setScaledIcon(icono, RUTA_LOGO);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalir) {
            do_btnSalir_actionPerformed(e);
        }
        if (e.getSource() == btnComida) {
            do_btnProductos_actionPerformed(e);
        }
        if (e.getSource() == btnTienda) {
            do_btnNewButton_actionPerformed(e);
        }
    }

    protected void do_btnNewButton_actionPerformed(ActionEvent e) {
        VentanaVentas ventana = new VentanaVentas();
        ventana.setVisible(true);
    }

    protected void do_btnProductos_actionPerformed(ActionEvent e) {
        VentanaComida ventana = new VentanaComida();
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
}
