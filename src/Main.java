import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;

/* Jeremy Hsu
 * 
 */
public class Main {
	final static int playerOne = 1;
	final static int playerTwo = 2;
	static int playerTurn = 0;
	public static void main(String[] args) {
		//Declare frames and panels
		JFrame frame = new JFrame("Connect 4");
		JPanel windowPanel = new JPanel();
		JPanel boardPanel = new JPanel();
		JPanel counterPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		//Set layouts for frames/panels
		boardPanel.setLayout(new OverlayLayout(boardPanel));
		counterPanel.setLayout(new GridLayout(6, 7));
		buttonPanel.setLayout(new GridLayout(1, 7, 10, 10));
		windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));
		//Declare objects for the frames/panels
		JButton insertButton[] = new MapButton[7];
		InputStream inputStream = Main.class.getResourceAsStream("GoogleSans-Regular.ttf");
		Font bigFont = new Font("Comic Sans MS", Font.BOLD, 32);
		Font basicFont = new Font("Comic Sans MS", Font.BOLD, 16);
		/*try {
			bigFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(Font.PLAIN, 32);
			basicFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(Font.PLAIN, 16);
			inputStream.close();
		} catch (FontFormatException e) {
			System.err.println( "FontFormaException: " + e.getMessage() );
			System.exit( 1 );
		} catch (IOException e) {
			System.err.println( "IOException: " + e.getMessage() );
			System.exit( 1 );
		}*/
		JLabel counters[] = new JLabel[6 * 7];
		JLabel boardLabel = new JLabel(new ImageIcon(Main.class.getResource("board.png")));
		boardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel turnLabel = new JLabel();
		turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		turnLabel.setFont(bigFont);
		//Build Frame
		windowPanel.add(Box.createRigidArea(new Dimension(0, 10)));//This is just a spacer
		for (int i = 0; i < counters.length; i++) {
			counters[i] = new JLabel(new ImageIcon(Main.class.getResource("blank_counter.png")));
			counterPanel.add(counters[i]);
		}
		counterPanel.setOpaque(false);
		boardPanel.add(counterPanel);
		boardPanel.add(boardLabel);
		windowPanel.add(boardPanel);
		for (int j = 0; j < insertButton.length; j++) {
			insertButton[j] = new MapButton("Drop");
			insertButton[j].setFont(basicFont);
			buttonPanel.add(insertButton[j]);
		}
		buttonPanel.setOpaque(false);
		windowPanel.add(buttonPanel);
		playerTurn = (int) (Math.random() * 1) + 1;
		if (playerTurn == playerOne) {
			turnLabel.setForeground(Color.decode("#F22432"));
			turnLabel.setText("It is Red's Turn");
		} else if (playerTurn == playerTwo) {
			turnLabel.setForeground(Color.decode("#28A5E8"));
			turnLabel.setText("It is Blue's Turn");
		}
		windowPanel.add(turnLabel);
		windowPanel.setBackground(Color.decode("#A0E5E5"));
		frame.add(windowPanel);
		//Pack Frame
		frame.pack();
		System.out.println(frame.getSize());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}//end main
}//end class
