import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

/* Jeremy Hsu
 * Main
 * This is where the frame gets built
 */
public class Main {
	final static int playerOne = 1;
	final static int playerTwo = 2;
	static int playerTurn = 0;
	static int countersUsed = 0;
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
		Grid grid = new Grid();
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		JMenuItem resetItem = new JMenuItem("Reset");
		JMenuItem exitItem = new JMenuItem("Exit");
		JMenuItem helpItem = new JMenuItem("How to Play");
		JMenuItem aboutItem = new JMenuItem("About Connect 4");
		InsertButton insertButton[] = new InsertButton[7];
		Font bigFont = createFont(36);
		Font basicFont = createFont(18);
		JLabel counters[][] = new JLabel[6][7];
		JLabel boardLabel = new JLabel(new ImageIcon(Main.class.getResource("board.png")));
		boardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel turnLabel = new JLabel();
		turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		turnLabel.setFont(bigFont);
		ImageIcon redCounter = new ImageIcon(Main.class.getResource("red_counter.png"));
		ImageIcon blueCounter = new ImageIcon(Main.class.getResource("blue_counter.png"));
		ImageIcon blankCounter = new ImageIcon(Main.class.getResource("blank_counter.png"));
		ImageIcon redWinCounter = new ImageIcon(Main.class.getResource("red_win.png"));
		ImageIcon blueWinCounter = new ImageIcon(Main.class.getResource("blue_win.png"));
		ActionListener insertAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InsertButton button =  (InsertButton) e.getSource();
				int col = button.getColumn();
				int row = grid.insertCounter(playerTurn, col);
				//int row = 1;//Just for Testing
				int winCoordinates[][] = grid.checkForWin(playerTurn, col, row);
				//int winCoordinates[][] = new int[4][2];
				//int winCoordinates[][] = {{1, 1}, {2, 2}, {3, 3}, {4, 4}};
				if (row == 6) {
					button.setEnable(false);
				}
				if (winCoordinates[0][0] == 0) {//If ANY Coordinate equal zero then no win
					if (countersUsed >= (6 * 7)) {
						turnLabel.setForeground(Color.decode("#7F6A00"));
						turnLabel.setText("Tie!");
					} else if (playerTurn == playerOne) {
						counters[row - 1][col - 1].setIcon(redCounter);//subtracts one to convert to array position
						turnLabel.setForeground(Color.decode("#28A5E8"));
						turnLabel.setText("It is Blue's Turn");
						playerTurn = playerTwo;
					} else if (playerTurn == playerTwo) {
						counters[row - 1][col - 1].setIcon(blueCounter);
						turnLabel.setForeground(Color.decode("#F22432"));
						turnLabel.setText("It is Red's Turn");
						playerTurn = playerOne;
					}
					countersUsed++;
				} else {
					if (playerTurn == playerOne) {
						for (int i = 0; i < winCoordinates.length; i++) {
							counters[winCoordinates[i][1] - 1][winCoordinates[i][0] - 1].setIcon(redWinCounter);//Array x, y are switched
						}
						turnLabel.setText("Red's Wins!");
					} else if (playerTurn == playerTwo) {
						for (int i = 0; i < winCoordinates.length; i++) {
							counters[winCoordinates[i][1] - 1][winCoordinates[i][0] - 1].setIcon(blueWinCounter);
						}
						turnLabel.setText("Blue's Wins!");
					}
					for (int i = 0; i < insertButton.length; i++) {
						insertButton[i].setEnable(false);
					}
				}
			}
		};
		ActionListener resetAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerTurn = (int) (Math.random() * 2) + 1;
				if (playerTurn == playerOne) {
					turnLabel.setForeground(Color.decode("#F22432"));
					turnLabel.setText("It is Red's Turn");
				} else if (playerTurn == playerTwo) {
					turnLabel.setForeground(Color.decode("#28A5E8"));
					turnLabel.setText("It is Blue's Turn");
				}
				for (int i = 0; i < insertButton.length; i++) {
					insertButton[i].setEnable(true);
				}
				for (int i = counters.length - 1; i >= 0; i--) {
					for (int j = 0; j < counters[i].length; j++) {
						counters[i][j].setIcon(blankCounter);
					}
				}
				countersUsed = 0;
				grid.resetGrid();
			}
		};
		ActionListener helpAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "The objective of the game to get 4 of your"
				    + " counters in a row.\n"
				    + "These counters can be vertical, horizontal, or diagonal.\n"
				    + "Players take turns placing a counter of their colour by pressing the drop button."
				    + "\n\nGood Luck!",
						"How to play", JOptionPane.PLAIN_MESSAGE);
			}
		};
		ActionListener aboutAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "This game was created for practice."
						+ "\n\nBy Jeremy Hsu and Vivian Hsu\nCopyright 2018.", "About",
						JOptionPane.PLAIN_MESSAGE);
			}
		};
		ActionListener exitAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		//Build Frame
		frame.setIconImage(new ImageIcon(Main.class.getResource("icon.png")).getImage());
		/*try {//This probably looks worse
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}*/
		frame.setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(resetItem);
		menu.addSeparator();
		menu.add(helpItem);
		menu.add(aboutItem);
		menu.addSeparator();
		menu.add(exitItem);
		resetItem.addActionListener(resetAction);
		helpItem.addActionListener(helpAction);
		aboutItem.addActionListener(aboutAction);
		exitItem.addActionListener(exitAction);
		windowPanel.add(Box.createRigidArea(new Dimension(0, 10)));//This is just a spacer
		for (int i = counters.length - 1; i >= 0; i--) {
			for (int j = 0; j < counters[i].length; j++) {
				counters[i][j] = new JLabel(blankCounter);
				counterPanel.add(counters[i][j]);
			}
		}
		counterPanel.setOpaque(false);
		boardPanel.add(counterPanel);
		boardPanel.setOpaque(false);
		counterPanel.setMaximumSize(new Dimension(630, 540));
		boardPanel.add(boardLabel);
		boardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		windowPanel.add(boardPanel);
		for (int i = 0; i < insertButton.length; i++) {
			insertButton[i] = new InsertButton("Drop");
			insertButton[i].setFont(basicFont);
			insertButton[i].setColumn(i + 1);
			insertButton[i].addActionListener(insertAction);
			buttonPanel.add(insertButton[i]);
		}
		buttonPanel.setOpaque(false);
		buttonPanel.setMaximumSize(new Dimension(630, 30));
		windowPanel.add(buttonPanel);
		playerTurn = (int) (Math.random()  * 2) + 1;
		if (playerTurn == playerOne) {
			turnLabel.setForeground(Color.decode("#F22432"));
			turnLabel.setText("It is Red's Turn");
		} else if (playerTurn == playerTwo) {
			turnLabel.setForeground(Color.decode("#28A5E8"));
			turnLabel.setText("It is Blue's Turn");
		}
		windowPanel.add(turnLabel);
		windowPanel.setBackground(Color.decode("#F2E091"));
		frame.add(windowPanel);
		//Pack Frame
		frame.pack();
		frame.setMinimumSize(new Dimension(660, 687));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}//end main
	
	/**
	   * Creates font
	   * @param font size
	   * @return Formated font
	   */
	public static Font createFont(int size) {
		InputStream inputStream = Main.class.getResourceAsStream("GoogleSans-Regular.ttf");
		try {
			return Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(Font.BOLD, size);
		} catch (FontFormatException e) {
			return new Font("Comic Sans MS", Font.BOLD, size);
		} catch (IOException e) {
			return new Font("Comic Sans MS", Font.BOLD, size);
		}
	}
}//end class
