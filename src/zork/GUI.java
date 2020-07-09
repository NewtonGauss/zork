package zork;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String EXTENSION_HISTORIA = ".zork";
    private static final String DIRECTORIO_HISTORIAS = "aventuras/";
    private PanelPrincipal panelPrincipal = new PanelPrincipal();
    private PanelJuego panelJuego = new PanelJuego();
    private JPanel cards = new JPanel();
    private InputOutput io;

    public GUI(InputOutput io) {
	this.io = io;
	cards.setLayout(new CardLayout());
	cards.add(panelPrincipal);
	cards.add(panelJuego);

	add(cards);

	pack();
	setVisible(true);
	setTitle("Zork");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	setResizable(false);
    }

    private class PanelPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	JScrollPane panelAventuras;

	public PanelPrincipal() {
	    setBackground(Color.decode("#7b7e85"));
	    GridBagLayout gb = new GridBagLayout();
	    setLayout(gb);
	    GridBagConstraints c = new GridBagConstraints();
	    JLabel zorkTitulo = new JLabel("Zork");
	    zorkTitulo.setFont(new Font("Monospaced", Font.BOLD, 50));
	    c.gridx = 1;
	    c.gridy = 0;
	    gb.setConstraints(zorkTitulo, c);
	    add(zorkTitulo);

	    panelAventuras = new JScrollPane();
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(0, 1));
	    cargarHistorias(panel);
	    panelAventuras = new JScrollPane(panel);
	    panelAventuras
		    .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    panelAventuras
		    .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    panelAventuras.setPreferredSize(new Dimension(400, 400));
	    c.gridx = 1;
	    c.gridy = 1;
	    gb.setConstraints(panelAventuras, c);
	    add(panelAventuras);
	}

	private void cargarHistorias(JPanel panel) {
	    File dirAventuras = new File(DIRECTORIO_HISTORIAS);
	    assert (dirAventuras.isDirectory());
	    File[] archivos = dirAventuras.listFiles();
	    for (File archivo : archivos) {
		String nombre = archivo.getName();
		if (archivo.isFile() && nombre.endsWith(EXTENSION_HISTORIA))
		    addBoton(panel, nombre.replaceAll(EXTENSION_HISTORIA, ""));
	    }
	}

	private void addBoton(JPanel panel, String nombre) {
	    JButton boton = new JButton(nombre);
	    boton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
		    io.cargarHistoria(DIRECTORIO_HISTORIAS + nombre + EXTENSION_HISTORIA);
		    CardLayout cl = (CardLayout) cards.getLayout();
		    cl.next(cards);
		}
	    });
	    panel.add(boton);
	}

    }

    private class PanelJuego extends JPanel {
	private static final long serialVersionUID = 1L;
	private PanelImagen imagen;
	private JTextPane cuadroJuego;
	private JTextField cuadroInstrucciones;
	private List<String> historialInstrucciones;
	private int indexActualInstruccion = -1;

	public PanelJuego() {
	    historialInstrucciones = Juego.getInstancia().getHistorialInstrucciones();
	    setLayout(new BorderLayout());
	    setBackground(Color.decode("#7b7e85"));
	    imagen = new PanelImagen();
	    imagen.setBackground(Color.BLACK);
	    imagen.setPreferredSize(new Dimension(500, 0));
	    add(imagen, BorderLayout.WEST);
	    
	    JPanel juego = new JPanel();
	    juego.setLayout(new BorderLayout());
	    cuadroJuego = new JTextPane();
	    cuadroJuego.setEditable(false);
	    cuadroJuego.setFont(new Font("Monospaced", Font.PLAIN, 16));
	    JScrollPane scroll = new JScrollPane(cuadroJuego);
	    juego.add(scroll, BorderLayout.CENTER);
	    cuadroInstrucciones = new JTextField();
	    cuadroInstrucciones.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    cuadroInstrucciones.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
		    String instruccion = cuadroInstrucciones.getText();
		    imprimirPrompt(io.getNombreJugador() + " > " + instruccion);
		    io.leerComando(instruccion);
		    indexActualInstruccion = historialInstrucciones.size();
		    cuadroInstrucciones.setText("");
		}
	    });
	    cuadroInstrucciones.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
		    switch (e.getExtendedKeyCode()) {
		    case KeyEvent.VK_UP:
			if (indexActualInstruccion > 0)
			    cuadroInstrucciones.setText(
				    historialInstrucciones.get(--indexActualInstruccion));
			break;
		    case KeyEvent.VK_DOWN:
			if (indexActualInstruccion < historialInstrucciones.size() - 1)
			    cuadroInstrucciones.setText(
				    historialInstrucciones.get(++indexActualInstruccion));
			else if (indexActualInstruccion == historialInstrucciones.size()
				- 1) {
			    indexActualInstruccion++;
			    cuadroInstrucciones.setText("");
			}
			break;
		    }
		}
	    });
	    juego.add(cuadroInstrucciones, BorderLayout.SOUTH);
	    add(juego);

	}

    }

    private class PanelImagen extends JPanel {
	Habitacion habitacionActual;

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D graphics = (Graphics2D) g;
	    BufferedImage fondo;
	    try {
		if (habitacionActual != null) {
		    fondo = ImageIO
			    .read(new File(DIRECTORIO_HISTORIAS + habitacionActual.getSpritePath()));
		    graphics.drawImage(fondo, null, 0, 0);
		    int x = 0;
		    for (Sitio sitio :  habitacionActual.getSitios()) {
			
			for (Item item : sitio.getItems()) {
			    BufferedImage itemImagen = ImageIO.
				    			read(new File(DIRECTORIO_HISTORIAS + item.getSpritePath()));
			    x += 2;
			    graphics.drawImage(itemImagen, null, x, 0);
			}
		    }
		} else
		    setBackground(Color.black);
	    } catch (IOException e) {
		setBackground(Color.black);
	    }
	}

	public void setHabitacionActual(Habitacion habitacionActual) {
	    this.habitacionActual = habitacionActual;
	}
    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(1000, 650);
    }

    public static void main(String[] args) {
	InputOutput io = new InputOutput();
	GUI gui = new GUI(io);
	io.setGui(gui);
	Juego.getInstancia().setIo(io);
    }

    public void imprimir(String mensaje, Habitacion habitacionActual) {
	String textoMostrar = panelJuego.cuadroJuego.getText() + mensaje + "\n\n";
	panelJuego.cuadroJuego.setText(textoMostrar);
	panelJuego.imagen.setHabitacionActual(habitacionActual);
	panelJuego.imagen.paintImmediately(panelJuego.imagen.getBounds());
    }

    private void imprimirPrompt(String mensaje) {
	String textoMostrar = panelJuego.cuadroJuego.getText() + mensaje + "\n\n";
	panelJuego.cuadroJuego.setText(textoMostrar);
    }

    public void finalizar(String mensaje) {
	JOptionPane.showMessageDialog(this, mensaje, "Has finalizado el juego",
		JOptionPane.OK_OPTION, null);
	System.exit(0);
    }
}
