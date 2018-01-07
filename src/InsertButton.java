import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

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
    
    /**
     * Sets column the button corresponds to
     * @param column as an int
     * @return void
     */
    public void setColumn(int column) {
    	this.column = column;
    }
    
    /**
     * Gets column the button corresponds to
     * @return column as an int
     */
    public int getColumn() {
    	return column;
    }
    
    /**
     * Custom enable/disable method for buttons
     * @param boolean to deterine if enabled
     * @return void
     */
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
