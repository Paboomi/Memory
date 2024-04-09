package com.mycompany.memory.frontend.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author saien
 */
public abstract class CustomDialog extends JDialog{
    
       public CustomDialog(Frame parent, String title) {
        super(parent, title, true);
        setSize(400, 150);
        setPreferredSize(new Dimension(400, 150));
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        add(formatoMensaje(), BorderLayout.CENTER);
    }

    public abstract JLabel formatoMensaje();

    public void mostrarDialog() {
        setVisible(true);
    }
}
