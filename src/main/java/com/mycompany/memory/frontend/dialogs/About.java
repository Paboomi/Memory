package com.mycompany.memory.frontend.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author saien
 */
public class About extends CustomDialog{

    public About(Frame parent) {
        super(parent, "Sobre el desarrollador");
    }

    @Override
    public JLabel formatoMensaje() {
         JLabel lblInfo = new JLabel("Desarrollador: Daniel Rodriguez \n Email: githubpaboomi@gmail.com");
        Font font = lblInfo.getFont();
        Font nuevaFont = new Font(font.getName(), font.getStyle(), 20);
        lblInfo.setFont(nuevaFont);
        lblInfo.setForeground(Color.ORANGE);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        
        return lblInfo;
    }
    
}
