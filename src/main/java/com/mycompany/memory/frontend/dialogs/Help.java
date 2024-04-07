package com.mycompany.memory.frontend.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author saien
 */
public class Help extends JDialog {

    public Help(JFrame parent) {
        super(parent, "Guia del Juego", true);
        setSize(1920, 1080);
        setLocationRelativeTo(parent);
        add(guiaUsuario());
    }

    public JScrollPane guiaUsuario() {
        //Creamos un panel
        JPanel panelGuia = new JPanel();
        panelGuia.setLayout(new GridLayout(4, 1)); // GridLayout con una sola columna
        //Agregamos color de fondo
        panelGuia.setBackground(Color.BLACK);
        
        // Agrega tus JLabels para la guía
        Font font = new Font("Arial", Font.PLAIN, 20); // Fuente Arial, tamaño 20

        JLabel jugarLabel = new JLabel("<html><font color=\"#FFBD09\">Cómo jugar</font><br><font color=\"#F1F1F1\">El juego consiste en seleccionar dos cartas utilizando el Mouse o Touchpad.<br>El juego comparará las imagenes para verificar que sean iguales, si no lo son las cartas se voltearan.<br>El ganador será el  que tenga mayor puntaje.</font></html>");
        jugarLabel.setFont(font); // Aplicar la fuente al JLabel
        
        JLabel puntuacionLabel = new JLabel("<html><font color=\"#FFBD09\">Puntuación</font><br><font color=\"#F1F1F1\">Por cada pareja encontrada se bonifican 50 puntos al jugador en turno<br>Si las 2 cartas no son pareja se descontara 10 puntos al jugador en turno</font></html>");
        puntuacionLabel.setFont(font); // Aplicar la fuente al JLabel

        JLabel nivelesLabel = new JLabel("<html><font color=\"#FFBD09\">Niveles de dificultad</font><br><font color=\"#F1F1F1\">El juego cuenta con 3 distintos niveles<br><br>Nivel Fácil: Este nivel contiene 5 parejas<br>Nivel Intermedio: Este nivel contiene 10 parejas<br>Nivel Dificil: Este nivel contiene 15 parejas</font></html>");
        nivelesLabel.setFont(font); // Aplicar la fuente al JLabel

        JLabel controlesLabel = new JLabel("<html><font color=\"#FFBD09\">Controles del juego</font><br><font color=\"#F1F1F1\">El juego tiene una barra de menús con distintas opciones<br><br><font color=\"#00DEA0\">Juego:</font> Desde aqui pueden seleccionarse distintas opciones:<br>Nueva Partida: Esta opcion crea una nueva partida, se pedira que se ingrese los nombres de los jugadores y el nivel en el que desea jugar<br>Nivel: Al terminar un nivel se puede seleccionar otro o el mismo manteniendo los jugadores actuales y sus punteos<br>Salir: Esta opcion cierra el por completo el juego<br><br><font color=\"#00DEA0\">Reportes:</font> Desde este menú podremos visualizar las estadisticas de las partidas que se han jugado<br>Historial de ganadores: Al seleccionar la opcion se mostrara una tabla con la información sobre los ganadores y perdedores<br>Mejor Jugador: Se muestra el jugador que mas puntos ha conseguido<br><br><font color=\"#00DEA0\">Acerca de:</font> Se muestra informacion general sobre el desarrollador del juego<br><br><font color=\"#00DEA0\">Ayuda:</font>Se muestra informacion necesaria para el uso del juego</font></html>");
        controlesLabel.setFont(font); // Aplicar la fuente al JLabel
        //Prueba1
        //JLabel jugarLabel = new JLabel("   Cómo jugar:\rEl juego consiste en seleccionar dos cartas utilizando el Mouse o Touchpad.\rEl juego comparará las imagenes para verificar que sean iguales, si no lo son las cartas se voltearan.\rEl ganador será el  que tenga mayor puntaje.");
//        JLabel jugarLabel = new JLabel("<html>Cómo jugar:<br>El juego consiste en seleccionar dos cartas utilizando el Mouse o Touchpad.<br>El juego comparará las imágenes para verificar que sean iguales; si no lo son, las cartas se voltearán.<br>El ganador será el que tenga mayor puntaje.</html>");
//        jugarLabel.setFont(font); // Aplicar la fuente al JLabel
//        jugarLabel.setForeground(Color.WHITE);
//        
//        JLabel puntuacionLabel = new JLabel("    Puntuación:\nPor cada pareja encontrada se bonifican 50 puntos al jugador en turno\rSi las 2 cartas no son pareja se descontara 10 puntos al jugador en turno");
//        puntuacionLabel.setFont(font); // Aplicar la fuente al JLabel
//        puntuacionLabel.setForeground(Color.WHITE);
//
//        JLabel nivelesLabel = new JLabel("  Niveles de dificulta:\nEl juego cuenta con 3 distintos niveles\rNivel Fácil: Este nivel contiene 5 parejas\rNivel Intermedio: Este nivel contiene 10 parejas\rNivel Dificil: Este nivel contiene 15 parejas");
//        nivelesLabel.setFont(font); // Aplicar la fuente al JLabel
//        nivelesLabel.setForeground(Color.WHITE);
//
//        JLabel controlesLabel = new JLabel("   Controles del juego:\nEl juego tiene una barra de menús con distintas opciones\rJuego:Desde aqui pueden seleccionarse distintas opciones:\rNueva Partida: Esta opcion crea una nueva partida, se pedira que se ingrese los nombres de los jugadores y el nivel en el que desea jugar\rNivel: Al terminar un nivel se puede seleccionar otro o el mismo manteniendo los jugadores actuales y sus punteos\rSalir: Esta opcion cierra el por completo el juego.\rReportes:\rDesde este menú podremos visualizar las estadisticas de las partidas que se han jugado<.\rHistorial de ganadores:\rAl seleccionar la opcion se mostrara una tabla con la información sobre los ganadores y perdedores.\rMejor Jugador:\rSe muestra el jugador que mas puntos ha conseguido.\rAcerca de:\rSe muestra informacion general sobre el desarrollador del juego.\rAyuda:\rSe muestra informacion necesaria para el uso del juego.");
//        controlesLabel.setFont(font); // Aplicar la fuente al JLabel
//        controlesLabel.setForeground(Color.WHITE);

        // Establece el tamaño preferido de los JLabels para que se ajusten automáticamente al contenido
        jugarLabel.setSize(100,2);
        jugarLabel.setPreferredSize(new Dimension(100, 2));
        puntuacionLabel.setSize(100, 2);
        puntuacionLabel.setPreferredSize(new Dimension(100, 2));
        nivelesLabel.setSize(100, 2);
        nivelesLabel.setPreferredSize(new Dimension(100, 2));
        controlesLabel.setSize(100, 350);
        controlesLabel.setPreferredSize(new Dimension(100, 350));

        // Agrega los JLabels al JPanel
        panelGuia.add(jugarLabel);
        panelGuia.add(puntuacionLabel);
        panelGuia.add(nivelesLabel);
        panelGuia.add(controlesLabel);
        
        JScrollPane scrollPane = new JScrollPane(panelGuia);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }

}
