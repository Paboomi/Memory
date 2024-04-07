package com.mycompany.memory.backend;

import com.mycompany.memory.frontend.util.ActualizarDatos;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author saien
 */
public class LogicaMemory {

    Random rand = new Random();

    private boolean caraUp = false;
    private ImageIcon img1;
    private ImageIcon img2;
    private JButton[] pbtn = new JButton[2];
    private boolean segunda = false;
    private boolean termino = false;
    private int cantidadParejas;
    Jugador[] jugadores;
    private int indiceJugadorEnTurno;
    private ActualizarDatos observador;
    private String resultado;

    public LogicaMemory(Jugador jugador1, Jugador jugador2) {
        indiceJugadorEnTurno = 0;
        jugadores = new Jugador[2];
        jugadores[0] = jugador1;
        jugadores[1] = jugador2;
    }

    public LogicaMemory() {
    }

    public ActualizarDatos getObservador() {
        return observador;
    }

    public void setObservador(ActualizarDatos observador) {
        this.observador = observador;
    }
    
    private void notificarObservador(){
        if (observador != null) {
            observador.actualizar();
        }
    }
 

    public int[] setCardNumbers() {

        int[] numbers = new int[getCantidadParejas() * 2];
        int count = 0;

        while (count < cantidadParejas * 2) {
            //Generamos un numero aleatorio entre 1 y 5
            int na = rand.nextInt(getCantidadParejas()) + 1;
            int nvr = 0;

            for (int i = 0; i < numbers.length; i++) {
                /*Si el numero almacenado en la posicion i coincide con el numero aleatorio
                nvr pasa a incrementarse esto para solo tener 1 par de cada numero*/
                if (numbers[i] == na) {
                    nvr++;
                }

            }
            /*Si durante el ciclo no se encontro que hubiese un par del numero generado aleatoriamente
              Entonces agregamos en la posicion count el numero generado aleatoriamente*/
            if (nvr < 2) {
                numbers[count] = na;
                count++;
            }

        }
        return numbers;
    }

    public void compararImagenes() {
        //Verificamos que dos botones ya han sido presionados
        if (caraUp && segunda) {
            //Si las imagenes son diferentes volvemos a habilitar el boton
            if (img1.getDescription().compareTo(img2.getDescription()) != 0) {
                pbtn[0].setEnabled(true);
                pbtn[1].setEnabled(true);
                if (ObtenerJugadorEnTurno().getPunteo() >= 10) {
                    ObtenerJugadorEnTurno().setPunteo(ObtenerJugadorEnTurno().getPunteo() - 10);
                } else {
                    ObtenerJugadorEnTurno().setPunteo(0);
                }
            } else {
                ObtenerJugadorEnTurno().setPunteo(ObtenerJugadorEnTurno().getPunteo() + 50);
            }
            caraUp = false;
            cambiarTurno();
            notificarObservador();
        }

    }

    public void btnEnabled(JButton btn, JButton[] buttons) {
        //Al presionar el primer boton se entra en esta condicion
        if (!caraUp) {
            btn.setEnabled(false);
            img1 = (ImageIcon) btn.getDisabledIcon();
            pbtn[0] = btn;
            segunda = false;
            caraUp = true;
            //Al presionar el segundo boton se entra a esta condicion
        } else {
            btn.setEnabled(false);
            img2 = (ImageIcon) btn.getDisabledIcon();
            pbtn[1] = btn;
            segunda = true;
            juegoTerminado(buttons);
        }

    }

    public void juegoTerminado(JButton[] buttons) {
        int contador = 0;
        for (JButton button : buttons) {
            if (!button.isEnabled()) {
                contador++;
            }
        }
        if (contador == buttons.length) {
            if (jugadores[0].getPunteo() > jugadores[1].getPunteo() ) {
                setResultado(String.format("<html>%s ha ganado<br>Puntaje: %d</html>",jugadores[0].getNombre(), jugadores[0].getPunteo()));
            }else if (jugadores[0].getPunteo() < jugadores[1].getPunteo()){
                setResultado(String.format("<html>%s ha ganado<br>Puntaje: %d</html>",jugadores[1].getNombre(), jugadores[1].getPunteo()));
                
            }else{
                setResultado(String.format("Han empatado"));
                
            }
            
            setTermino(true);

        }

    }

    public void IndicarQuienEmpieza() {
        boolean empezar = rand.nextBoolean();
        if (empezar) {
            indiceJugadorEnTurno = 0;
        } else {
            indiceJugadorEnTurno = 1;
        }
    }

    public void cambiarTurno() {

        do {
            if (indiceJugadorEnTurno < jugadores.length - 1) {
                indiceJugadorEnTurno++;
                return;
            } else {
                indiceJugadorEnTurno = 0;
                return;
            }

        } while (true);
    }

    public Jugador ObtenerJugadorEnTurno() {
        return jugadores[indiceJugadorEnTurno];
    }

    private int paresAleatorios(int parejas) {
        return rand.nextInt(parejas) * 5 + 5;
    }

    public boolean isTermino() {
        return termino;
    }

    public void setTermino(boolean termino) {
        this.termino = termino;
    }

    public int getCantidadParejas() {
        return cantidadParejas;
    }

    public void setCantidadParejas(int cantidadParejas) {
        this.cantidadParejas = cantidadParejas;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    

}
