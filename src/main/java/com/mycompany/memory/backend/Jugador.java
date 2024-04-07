package com.mycompany.memory.backend;

/**
 *
 * @author saien
 */
public class Jugador {
    String nombre;
    int punteo;

    public Jugador() {
        punteo = 0;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPunteo() {
        return punteo;
    }

    public void setPunteo(int punteo) {
        this.punteo = punteo;
    }
    
    
}
