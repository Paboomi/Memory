package com.mycompany.memory.frontend.dialogs;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author saien
 */
public class Help extends JDialog{

    public Help(Frame parent) {
        super(parent, "Guia del Juego", true);
        setSize(500, 400);
        setLocationRelativeTo(parent);
        add(guiaUsuario());
    }

    public JPanel guiaUsuario() {
        JPanel panelGuia = new JPanel();
         panelGuia.setLayout(new GridLayout(0, 1)); // GridLayout con una sola columna
        // Agrega tus JLabels para la guía
        JLabel jugarLabel = new JLabel("<html><font color=\"#FFBD09\">Cómo jugar</font><br><font color=\"#F1F1F1\">El juego consiste en seleccionar dos cartas utilizando el Mouse o Touchpad.<br>El juego comparará las imagenes para verificar que sean iguales, si no lo son las cartas se voltearan.<br>El ganador será el  que tenga mayor puntaje.</font></html>");
        JLabel puntuacionLabel = new JLabel("<html><font color=\"#FFBD09\">Puntuación</font><br><font color=\"#F1F1F1\">Por cada pareja encontrada se bonifican 50 puntos al jugador en turno<br>Si las 2 cartas no son pareja se descontara 10 puntos al jugador en turno</font></html>");
        JLabel nivelesLabel = new JLabel("<html><font color=\"#FFBD09\">Niveles de dificultad</font><br><font color=\"#F1F1F1\">El juego cuenta con 3 distintos niveles<br>Nivel Fácil: Este nivel contiene 5 parejas<br>Nivel Intermedio: Este nivel contiene 10 parejas<br>Nivel Dificil: Este nivel contiene 15 parejas</font></html>");
        JLabel controlesLabel = new JLabel("<html><font color=\"#FFBD09\">Controles del juego</font><br><font color=\"#F1F1F1\">El juego tiene una barra de menús con distintas opciones<br><font color=\"#00DEA0\">Juego:</font> Desde aqui pueden seleccionarse distintas opciones:<br>Nueva Partida: Esta opcion crea una nueva partida, se pedira que se ingrese los nombres de los jugadores y el nivel en el que desea jugar<br>Nivel: Al terminar un nivel se puede seleccionar otro o el mismo manteniendo los jugadores actuales y sus punteos<br>Salir: Esta opcion cierra el por completo el juego<br><br><font color=\"#00DEA0\">Reportes:</font> Desde este menú podremos visualizar las estadisticas de las partidas que se han jugado<br>Historial de ganadores: Al seleccionar la opcion se mostrara una tabla con la información sobre los ganadores y perdedores<br>Mejor Jugador: Se muestra el jugador que mas puntos ha conseguido<br><br><font color=\"#00DEA0\">Acerca de:</font> Se muestra informacion general sobre el desarrollador del juego<br><br><font color=\"#00DEA0\">Ayuda:</font>Se muestra informacion necesaria para el uso del juego</font></html>");

        // Establece el tamaño preferido de los JLabels para que se ajusten automáticamente al contenido
       
        jugarLabel.setPreferredSize(new Dimension(400, 50));
        puntuacionLabel.setPreferredSize(new Dimension(400, 50));
        nivelesLabel.setPreferredSize(new Dimension(400, 50));
        controlesLabel.setPreferredSize(new Dimension(400, 50));

        // Agrega los JLabels al JPanel
        add(jugarLabel);
        add(puntuacionLabel);
        add(nivelesLabel);
        add(controlesLabel);
        
        
        
        return panelGuia;
    }

    
}
