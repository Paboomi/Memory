package com.mycompany.memory.frontend;

import com.mycompany.memory.frontend.dialogs.About;
import com.mycompany.memory.backend.*;
import com.mycompany.memory.exceptions.ImagenException;
import com.mycompany.memory.frontend.dialogs.Help;
import com.mycompany.memory.frontend.util.ActualizarDatos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

/**
 *
 * @author saien
 */
public class Principal extends JFrame implements ActualizarDatos{

    private LogicaMemory log;
    GenerarBotones crearBotones;
    private About acercaDe = new About(this);
    private Help help = new Help(this);

    Jugador jugador1;
    Jugador jugador2;
    //Obtengo carpeta de imagenes
    private static final String PATH_IMAGENES_FRUTAS = "com/mycompany/memory/images";
    JLabel lblInfo = new JLabel();
    private JButton[] buttons;
    JPanel panelBotones = new JPanel();
    JLabel lblJugador1 = new JLabel();
    JLabel lblJugador2 = new JLabel();
    Color color = Color.decode("#333333");
    

    public Principal() throws HeadlessException {
        jugador1 = new Jugador();
        jugador2 = new Jugador();
        log = new LogicaMemory(jugador1, jugador2);
        log.setObservador(this);
        crearBotones = new GenerarBotones(log);
        setTitle("Memory Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new GridLayout(2, 5));

        //pedirNombre();
        pedirDatosInicio();
        setJMenuBar(crearMenu());
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void setCards() {
        int numbers[] = log.setCardNumbers();

        for (int i = 0; i < buttons.length; i++) {

            try {
                // Asignar el ImageIcon al botón
                buttons[i].setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH_IMAGENES_FRUTAS + "/" + numbers[i] + ".png")));
                buttons[i].setVerticalTextPosition(AbstractButton.CENTER);
                buttons[i].setHorizontalTextPosition(AbstractButton.CENTER);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private JMenuBar crearMenu() {

        //Creamos la barra de opciones
        JMenuBar miBarra = new JMenuBar();

        //Creamos las opciones
        JMenu juego = new JMenu("Juego");
        JMenu reportes = new JMenu("Reportes");
        JMenu about = new JMenu("Acerca de");
        JMenu ayuda = new JMenu("Ayuda");
        JMenu nivel = new JMenu("Nivel");

        //Creamos los items para cada opcion
        JMenuItem nuevoJuego = new JMenuItem("Nuevo Juego");
        JMenuItem salir = new JMenuItem("Salir");
        JMenuItem estadisticas = new JMenuItem("Estadisticas");
        JMenuItem mejorJugador = new JMenuItem("Mejor Jugador");
        JMenuItem informacion = new JMenuItem("Sobre el desarrollador");
        JMenuItem instrucciones = new JMenuItem("Instrucciones");
        informacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    acercaDe.mostrarDialog();
                } catch (ExceptionInInitializerError e) {
                    JOptionPane.showMessageDialog(Principal.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        instrucciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    //Mostrar las instrucciones al usuario
                    help.setVisible(true);
                } catch (Exception e) {
                }
            }
        });

        JMenuItem nivel1 = new JMenuItem("5 parejas");
        nivel1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    log.setCantidadParejas(5);
                    log.IndicarQuienEmpieza();
                    nivelCambiado(log.getCantidadParejas());

                } catch (Exception e) {
                }
            }

        });
        JMenuItem nivel2 = new JMenuItem("10 parejas");
        nivel2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                log.setCantidadParejas(10);
                log.IndicarQuienEmpieza();
                nivelCambiado(log.getCantidadParejas());
            }

        });
        JMenuItem nivel3 = new JMenuItem("15 parejas");
        nivel3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                log.setCantidadParejas(15);
                log.IndicarQuienEmpieza();
                nivelCambiado(log.getCantidadParejas());
            }

        });

        //para el nivel
        nivel.add(nivel1);
        nivel.add(nivel2);
        nivel.add(nivel3);

        //Para el Juego
        juego.add(nuevoJuego);
        juego.add(nivel);
        juego.add(salir);

        //Para Reportes
        reportes.add(estadisticas);
        reportes.add(mejorJugador);

        //para Acerda del desarrollador
        about.add(informacion);

        //Para Ayuda
        ayuda.add(instrucciones);

        //agreagamos las opciones a la barra
        miBarra.add(juego);
        miBarra.add(reportes);
        miBarra.add(about);
        miBarra.add(ayuda);

        return miBarra;
    }
    
    private void nivelCambiado(int cantidadParejas) {
        for (Component comp : panelBotones.getComponents()) {
            if (comp instanceof JButton) {
                panelBotones.remove(comp);
            }
        }

        switch (cantidadParejas) {
            case 5:
                panelBotones.setLayout(new GridLayout(2, 5));
                break;
            case 10:
                panelBotones.setLayout(new GridLayout(4, 5));

                break;
            default:
                panelBotones.setLayout(new GridLayout(6, 5));

        }
        //Generamos los botones
        try {

            buttons = crearBotones.generarBotones(cantidadParejas * 2, this);
        } catch (ImagenException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        for (JButton button : buttons) {
            //Agregamos los botones al JFrame
            panelBotones.add(button);

        }//Agregado
        JPanel infoPanel = new JPanel(new GridLayout(1, 3)); // Un GridLayout de 2x1 para los labels jugador1 y jugador2
        infoPanel.setBackground(color);
        infoPanel.add(labelJugador1());
        infoPanel.add(mostrarJugadorTurno());
        infoPanel.add(labelJugador2());

        panelBotones.revalidate();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelBotones, BorderLayout.CENTER);
        getContentPane().add(infoPanel, BorderLayout.NORTH);
        setCards();
        revalidate();
        setLocationRelativeTo(null);
        pack();
    }

    private JLabel mostrarJugadorTurno() {

        lblInfo.setText(String.format("<html>Jugador en turno: %s<br>Punteo: %d</html>", log.ObtenerJugadorEnTurno().getNombre(), log.ObtenerJugadorEnTurno().getPunteo()));
        lblInfo.setForeground(Color.ORANGE);
        lblInfo.setFont(new Font("Verdana", Font.BOLD, 20));
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        //lblInfo.setOpaque(true);
        //lblInfo.setBackground(Color.BLACK);
        return lblInfo;
    }

    public JLabel labelJugador1() {
        lblJugador1.setText(String.format("<html>Jugador 1: %s<br>Punteo: %d</html>", jugador1.getNombre(), jugador1.getPunteo()));
        //lblJugador1.setHorizontalAlignment(SwingConstants.NORTH_WEST);
        lblJugador1.setFont(new Font("Verdana", Font.BOLD, 20));
        lblJugador1.setForeground(Color.ORANGE);
        lblJugador1.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        return lblJugador1;
    }

    public JLabel labelJugador2() {
        lblJugador2.setText(String.format("<html>Jugador 2: %s<br>Punteo: %d</html>", jugador2.getNombre(), jugador2.getPunteo()));
        lblJugador2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblJugador2.setFont(new Font("Verdana", Font.BOLD, 20));
        lblJugador2.setForeground(Color.ORANGE);
        lblJugador2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        return lblJugador2;
    }

    private void pedirDatosInicio() {
        jugador1.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del jugador 1:"));
        jugador2.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del jugador 2:"));

        JRadioButton facilButton = new JRadioButton("Fácil: 5 parejas");
        facilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    log.setCantidadParejas(5);
                    log.IndicarQuienEmpieza();
                    nivelCambiado(log.getCantidadParejas());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        JRadioButton intermedioButton = new JRadioButton("Intermedio: 10 parejas");
        intermedioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    log.setCantidadParejas(10);
                    log.IndicarQuienEmpieza();
                    nivelCambiado(log.getCantidadParejas());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        JRadioButton dificilButton = new JRadioButton("Difícil: 15 parejas");
        dificilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    log.setCantidadParejas(15);
                    log.IndicarQuienEmpieza();
                    nivelCambiado(log.getCantidadParejas());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        // Agrupar los botones de radio para que solo se pueda seleccionar uno
        ButtonGroup grupoNiveles = new ButtonGroup();
        grupoNiveles.add(facilButton);
        grupoNiveles.add(intermedioButton);
        grupoNiveles.add(dificilButton);

        // Crear un panel para los botones de radio
        JPanel panelNiveles = new JPanel();
        panelNiveles.setLayout(new BoxLayout(panelNiveles, BoxLayout.Y_AXIS));
        panelNiveles.add(facilButton);
        panelNiveles.add(intermedioButton);
        panelNiveles.add(dificilButton);

        // Mostrar un diálogo con los botones de radio para seleccionar el nivel
        JOptionPane.showConfirmDialog(null, panelNiveles, "Seleccionar Nivel", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

    }

    @Override
    public void actualizar() {
        // Actualizar los JLabels con los datos actualizados
        lblJugador1.setText(String.format("<html>Jugador 1: %s<br>Punteo: %d</html>", jugador1.getNombre(), jugador1.getPunteo()));
        lblJugador2.setText(String.format("<html>Jugador 2: %s<br>Punteo: %d</html>", jugador2.getNombre(), jugador2.getPunteo()));
        lblInfo.setText(String.format("<html>Jugador en turno: %s<br>Punteo: %d</html>", log.ObtenerJugadorEnTurno().getNombre(), log.ObtenerJugadorEnTurno().getPunteo()));

    }

}
