import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MapButton extends JButton implements MouseListener {
    Color textColor = Color.decode("#000000");
    Color backgroundColor = Color.decode("#A0E5E5");
    Color hoverColor = Color.decode("#00E51A");
    Color disabledColor = Color.lightGray;
    
    public MapButton(String str) {
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setText(str);
        this.setBorder(BorderFactory.createEmptyBorder(
                3, 3, 3, 3)); 
        this.setForeground(textColor);
        this.setBackground(backgroundColor);
        //this.setOpaque(false);
        addMouseListener(this);
    }
    public MapButton(ImageIcon image, ImageIcon disabledImage) {//unused
        this.setFocusPainted(false);
        this.setDisabledIcon(disabledImage);//This prevents image going grey when disabled
        this.setIcon(image);
        this.setBorder(BorderFactory.createEmptyBorder(
                3, 3, 3, 3)); 
        this.setForeground(textColor);
        this.setBackground(backgroundColor);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        //this.setBorderPainted(false);
        addMouseListener(this);
    }
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) { 
        if (e.getSource()==this && this.isEnabled()) {  
            this.setBackground(this.hoverColor);
        } else if (e.getSource()==this) {
        	this.setBackground(this.disabledColor);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) { 
        if (e.getSource()==this && this.isEnabled()) { 
            this.setBackground(this.backgroundColor);
        } else if (e.getSource()==this) {
        	this.setBackground(this.disabledColor);
        }
    }
}
