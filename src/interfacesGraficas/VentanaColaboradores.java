package interfacesGraficas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaColaboradores extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnCerrar;
    private JLabel lblTitulo;
    private JLabel lblFoto1;
    private JLabel lblNombre1;
    private JLabel lblFoto2;
    private JLabel lblNombre2;
    private JLabel lblFoto3;
    private JLabel lblNombre3;

    public VentanaColaboradores() {
        setTitle("Colaboradores del Proyecto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 550);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(205, 232, 254));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblTitulo = new JLabel("EQUIPO DE DESARROLLO");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(10, 11, 864, 40);
        contentPane.add(lblTitulo);
        
        // Panel Colaborador 1
        JPanel panelColaborador1 = new JPanel();
        panelColaborador1.setBorder(new TitledBorder(null, "Colaborador 1", 
            TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        panelColaborador1.setBounds(20, 62, 200, 300);
        contentPane.add(panelColaborador1);
        panelColaborador1.setLayout(null);
        
        lblFoto1 = new JLabel("");
        lblFoto1.setIcon(new ImageIcon(VentanaColaboradores.class.getResource("/images/N00402368.jpg")));
        lblFoto1.setHorizontalAlignment(SwingConstants.CENTER);
        lblFoto1.setBounds(10, 25, 180, 200);
        lblFoto1.setBorder(new javax.swing.border.LineBorder(Color.GRAY, 2));
        // Puedes agregar tu imagen aquí: lblFoto1.setIcon(new ImageIcon(VentanaColaboradores.class.getResource("/images/foto1.png")));
        panelColaborador1.add(lblFoto1);
        
        lblNombre2 = new JLabel("Ortega Ordinola, Karlo");
        lblNombre2.setBounds(10, 239, 180, 50);
        panelColaborador1.add(lblNombre2);
        lblNombre2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre2.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Panel Colaborador 2
        JPanel panelColaborador2 = new JPanel();
        panelColaborador2.setBorder(new TitledBorder(null, "Colaborador 2", 
            TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        panelColaborador2.setBounds(240, 62, 200, 300);
        contentPane.add(panelColaborador2);
        panelColaborador2.setLayout(null);
        
        lblFoto2 = new JLabel("");
        lblFoto2.setIcon(new ImageIcon(VentanaColaboradores.class.getResource("/images/yoo.jpg")));
        lblFoto2.setHorizontalAlignment(SwingConstants.CENTER);
        lblFoto2.setBounds(10, 25, 180, 200);
        lblFoto2.setBorder(new javax.swing.border.LineBorder(Color.GRAY, 2));
        // Puedes agregar tu imagen aquí: lblFoto2.setIcon(new ImageIcon(VentanaColaboradores.class.getResource("/images/foto2.png")));
        panelColaborador2.add(lblFoto2);
        
        lblNombre1 = new JLabel("Coll Málaga Gonzalo");
        lblNombre1.setBounds(10, 236, 180, 50);
        panelColaborador2.add(lblNombre1);
        lblNombre1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre1.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Panel Colaborador 3
        JPanel panelColaborador3 = new JPanel();
        panelColaborador3.setBorder(new TitledBorder(null, "Colaborador 3", 
            TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        panelColaborador3.setBounds(460, 62, 200, 300);
        contentPane.add(panelColaborador3);
        panelColaborador3.setLayout(null);
        
        lblFoto3 = new JLabel("");
        lblFoto3.setIcon(new ImageIcon(VentanaColaboradores.class.getResource("/images/angelaupn.jpg")));
        lblFoto3.setHorizontalAlignment(SwingConstants.CENTER);
        lblFoto3.setBounds(10, 25, 180, 200);
        lblFoto3.setBorder(new javax.swing.border.LineBorder(Color.GRAY, 2));
        // Puedes agregar tu imagen aquí: lblFoto3.setIcon(new ImageIcon(VentanaColaboradores.class.getResource("/images/foto3.png")));
        panelColaborador3.add(lblFoto3);
        
        lblNombre3 = new JLabel("Vilchez Gutierrez, Angela");
        lblNombre3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre3.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre3.setBounds(10, 235, 180, 50);
        panelColaborador3.add(lblNombre3);
        
        // Panel Colaborador 4
        JPanel panelColaborador4 = new JPanel();
        panelColaborador4.setBorder(new TitledBorder(null, "Colaborador 4", 
            TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        panelColaborador4.setBounds(680, 62, 200, 300);
        contentPane.add(panelColaborador4);
        panelColaborador4.setLayout(null);
        
        JLabel lblFoto4 = new JLabel("");
        lblFoto4.setHorizontalAlignment(SwingConstants.CENTER);
        lblFoto4.setBounds(10, 25, 180, 200);
        lblFoto4.setBorder(new javax.swing.border.LineBorder(Color.GRAY, 2));
        // Puedes agregar tu imagen aquí: lblFoto4.setIcon(new ImageIcon(VentanaColaboradores.class.getResource("/images/foto4.png")));
        panelColaborador4.add(lblFoto4);
        
        JLabel lblNombre4 = new JLabel("Llantoy Quispe, Leonardo");
        lblNombre4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre4.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre4.setBounds(10, 235, 180, 50);
        panelColaborador4.add(lblNombre4);
        
        JLabel lblDescripcion = new JLabel("Sistema de Facturación - Pollería Excelencia © 2025");
        lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescripcion.setFont(new Font("Arial", Font.ITALIC, 12));
        lblDescripcion.setBounds(10, 373, 864, 25);
        contentPane.add(lblDescripcion);
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.addActionListener(this);
        btnCerrar.setBounds(390, 410, 120, 35);
        contentPane.add(btnCerrar);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCerrar) {
            do_btnCerrar_actionPerformed(e);
        }
    }
    
    protected void do_btnCerrar_actionPerformed(ActionEvent e) {
        this.dispose();
    }
}