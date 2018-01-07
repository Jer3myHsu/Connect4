import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class InsertButton extends JButton implements MouseListener {
    Color textColor = Color.decode("#7F6A00");
    Color backgroundColor = Color.decode("#F2E091");
    Color hoverColor = Color.decode("#38E249");
    Color disabledColor = Color.lightGray;
    int column = 0;
    
    public InsertButton(String str) {
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setText(str);
        this.setBorder(BorderFactory.createEmptyBorder(
                3, 3, 3, 3)); 
        this.setForeground(textColor);
        this.setBackground(backgroundColor);
        addMouseListener(this);
    }
    public InsertButton(ImageIcon image, ImageIcon disabledImage) {//unused
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
    public void setColumn(int column) {
    	this.column = column;
    }
    public int getColumn() {
    	return column;
    }
    public void setEnable(boolean isEnabled) {
    	this.setOpaque(isEnabled);
    	this.setEnabled(isEnabled);
    }
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) { 
        if (e.getSource()==this) {  
            this.setBackground(this.hoverColor);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) { 
        if (e.getSource()==this) { 
            this.setBackground(this.backgroundColor);
        }
    }
}
