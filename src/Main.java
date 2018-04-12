import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/* Jeremy Hsu
 * Main
 * This is where the frame gets built
 */
public class Main {
	final static int playerOne = 0;
	final static int playerTwo = 1;
	static int playerTurn = 0;
	static int countersUsed = 0;
	public static void main(String[] args) {
		//Declare frames and panels
		JFrame frame = new JFrame("Connect 4");
		JPanel windowPanel = new JPanel();
		JPanel boardPanel = new JPanel();
		JPanel counterPanel = new JPanel(new GridLayout(6, 7));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 7, 10, 10));
		JDialog optionDialog = new JDialog(frame, "Options", true);
		JPanel mainPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel playerPanel[] = new JPanel[2];
		playerPanel[0] = new JPanel();
		playerPanel[1] = new JPanel();
		JDialog statDialog = new JDialog(frame, "Options", false);
		//Set layouts for frames/panels
		boardPanel.setLayout(new OverlayLayout(boardPanel));
		windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		playerPanel[0].setLayout(new BoxLayout(playerPanel[0], BoxLayout.Y_AXIS));
		playerPanel[1].setLayout(new BoxLayout(playerPanel[1], BoxLayout.Y_AXIS));
		//Declare objects for the frames/panels
		Grid grid = new Grid();
		Counter counter[] = new Counter[2];
		String colors[] = {"Red", "Blue", "Yellow", "Green", "Purple", "Orange", "White", "Black"};
		String hex[] = {"#F22432", "#28A5E8", "#E5BF1C", "#35E828", "#C72FCA", "#E88528", "#FFFFFF", "#535353"};
		counter[playerOne] = new Counter("Red", "#F22432");
		counter[playerTwo] = new Counter("Blue", "#28A5E8");
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenu helpMenu = new JMenu("Help");
		JMenuItem resetItem = new JMenuItem("New Game");
		JMenuItem exitItem = new JMenuItem("Exit");
		JMenuItem helpItem = new JMenuItem("How to Play");
		JMenuItem aboutItem = new JMenuItem("About Connect 4");
		JMenuItem optionItem = new JMenuItem("Options");
		InsertButton insertButton[] = new InsertButton[7];
		JButton dialogButton = new JButton("Apply");
		JRadioButton radioButton[][] = new JRadioButton[2][colors.length];
		JTextField nameField[] = new JTextField[2];
		ButtonGroup group[] = new ButtonGroup[2];
		TitledBorder border[] = new TitledBorder[2];
		border[0] = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Player 1", TitledBorder.LEFT, TitledBorder.TOP);
		border[1] = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Player 2", TitledBorder.RIGHT, TitledBorder.TOP);
		JLabel display[] = new JLabel[2];
		Font bigFont = createFont(36);
		Font basicFont = createFont(18);
		JLabel gridCounters[][] = new JLabel[6][7];
		JLabel boardLabel = new JLabel(new ImageIcon(Main.class.getResource("board.png")));
		boardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel turnLabel = new JLabel();
		turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		turnLabel.setFont(bigFont);
		ActionListener insertAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InsertButton button =  (InsertButton) e.getSource();
				int col = button.getColumn();
				int row = grid.insertCounter(playerTurn + 1, col);
				int winCoordinates[][] = grid.checkForWin(playerTurn + 1, col, row);
				if (row == 6) {
					button.setEnable(false);
				}
				if (winCoordinates[0][0] == 0) {//If ANY Coordinate equal zero then no win
					if (playerTurn == playerOne) {
						gridCounters[row - 1][col - 1].setIcon(counter[playerOne].getCounter());//subtracts one to convert to array position
						turnLabel.setForeground(counter[playerTwo].getColor());
						turnLabel.setText("It is " + counter[playerTwo].getName() + "'s Turn");
						playerTurn = playerTwo;
					} else if (playerTurn == playerTwo) {
						gridCounters[row - 1][col - 1].setIcon(counter[playerTwo].getCounter());
						turnLabel.setForeground(counter[playerOne].getColor());
						turnLabel.setText("It is " + counter[playerOne].getName() + "'s Turn");
						playerTurn = playerOne;
					}
					countersUsed++;
					if (countersUsed >= (6 * 7)) {
						turnLabel.setForeground(Color.decode("#7F6A00"));
						turnLabel.setText("Tie!");
					}
				} else {
					if (playerTurn == playerOne) {
						for (int i = 0; i < winCoordinates.length; i++) {
							gridCounters[winCoordinates[i][1] - 1][winCoordinates[i][0] - 1].setIcon(counter[playerOne].getWinCounter());//Array x, y are switched
						}
						turnLabel.setText(counter[playerOne].getName() + "'s Wins!");
					} else if (playerTurn == playerTwo) {
						for (int i = 0; i < winCoordinates.length; i++) {
							gridCounters[winCoordinates[i][1] - 1][winCoordinates[i][0] - 1].setIcon(counter[playerTwo].getWinCounter());
						}
						turnLabel.setText(counter[playerTwo].getName() + "'s Wins!");
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
				reset(turnLabel, counter, grid, gridCounters, insertButton);
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
		ActionListener optionAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				optionDialog.setLocationRelativeTo(frame);
				optionDialog.setVisible(true);
			}
		};
		ActionListener radioButtonAction[] = new ActionListener[2];
		radioButtonAction[0] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String color = ((JRadioButton) e.getSource()).getText();
				display[playerOne].setIcon(new ImageIcon(Main.class.getResource(color.toLowerCase() + "_counter.png")));
				nameField[playerOne].setText(color);
			}
		};
		radioButtonAction[1] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String color = ((JRadioButton) e.getSource()).getText();
				display[playerTwo].setIcon(new ImageIcon(Main.class.getResource(color.toLowerCase() + "_counter.png")));
				nameField[playerTwo].setText(color);
			}
		};
		ActionListener applyOptAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < playerPanel.length; i++) {
					if (nameField[i].getText().length() == 0 || nameField[i].getText().length() > 10) {
						JOptionPane.showMessageDialog(optionDialog, "Sorry, Player names must be between"
								+ "\n1 - 10 characters...");
						return;
					}
					for (int j = 0; j < radioButton[i].length; j++) {
						if (radioButton[0][j].isSelected() && radioButton[1][j].isSelected()) {
							JOptionPane.showMessageDialog(optionDialog, "Sorry, Players must have different color counters...");
							return;
						}
					}
				}
				for (int i = 0; i < playerPanel.length; i++) {
					counter[i].setName(nameField[i].getText());
					for (int j = 0; j < radioButton[i].length; j++) {
						if (radioButton[i][j].isSelected()) {
							counter[i].setColor(colors[j], hex[j]);
							break;
						}
					}
				}
				reset(turnLabel, counter, grid, gridCounters, insertButton);
				optionDialog.dispose();
			}
		};
		ActionListener statAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

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
		menuBar.add(gameMenu);
		menuBar.add(helpMenu);
		gameMenu.add(resetItem);
		gameMenu.addSeparator();
		gameMenu.add(optionItem);
		gameMenu.addSeparator();
		gameMenu.add(exitItem);
		helpMenu.add(helpItem);
		helpMenu.add(aboutItem);
		resetItem.addActionListener(resetAction);
		helpItem.addActionListener(helpAction);
		aboutItem.addActionListener(aboutAction);
		optionItem.addActionListener(optionAction);
		exitItem.addActionListener(exitAction);
		windowPanel.add(Box.createRigidArea(new Dimension(0, 10)));//This is just a spacer
		for (int i = gridCounters.length - 1; i >= 0; i--) {
			for (int j = 0; j < gridCounters[i].length; j++) {
				gridCounters[i][j] = new JLabel(new ImageIcon(Main.class.getResource("blank_counter.png")));
				counterPanel.add(gridCounters[i][j]);
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
		playerTurn = (int) (Math.random()  + 0.5);
		if (playerTurn == playerOne) {
			turnLabel.setForeground(counter[playerOne].getColor());
			turnLabel.setText("It is " + counter[playerOne].getName() + "'s Turn");
		} else if (playerTurn == playerTwo) {
			turnLabel.setForeground(counter[playerTwo].getColor());
			turnLabel.setText("It is " + counter[playerTwo].getName() + "'s Turn");
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
		//Build Dialog
		for (int i = 0; i < playerPanel.length; i++) {
			playerPanel[i].setBorder(border[i]);
			nameField[i] = new JTextField();
			nameField[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			playerPanel[i].add(nameField[i]);
			group[i] = new ButtonGroup();
			for (int j = 0; j < radioButton[i].length; j++) {
				radioButton[i][j] = new JRadioButton(colors[j]);
				radioButton[i][j].setAlignmentX(Component.LEFT_ALIGNMENT);
				radioButton[i][j].addActionListener(radioButtonAction[i]);
				group[i].add(radioButton[i][j]);
				playerPanel[i].add(radioButton[i][j]);
			}
			radioButton[i][i].setSelected(true);
			nameField[i].setText(colors[i]);
			display[i] = new JLabel(new ImageIcon(Main.class.getResource(colors[i].toLowerCase() + "_counter.png")));
			display[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			playerPanel[i].add(display[i]);
			centerPanel.add(playerPanel[i]);
		}
		mainPanel.add(centerPanel);
		dialogButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		dialogButton.addActionListener(applyOptAction);
		mainPanel.add(dialogButton);
		mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
		optionDialog.add(mainPanel);
		optionDialog.setResizable(false);
		optionDialog.setSize(340, 384);
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
	/**
	 * Resets Game to beginning state
	 * @param label Player turn label
	 * @param counter counter object
	 * @param grid grid object
	 * @param gridCounters counter JLabel 
	 * @param button
	 * @return void
	 */
	public static void reset(JLabel label, Counter counter[], Grid grid, JLabel gridCounters[][], InsertButton button[]) {
		playerTurn = (int) (Math.random() + 0.5);
		if (playerTurn == playerOne) {
			label.setForeground(counter[playerOne].getColor());
			label.setText("It is " + counter[playerOne].getName() + "'s Turn");
		} else if (playerTurn == playerTwo) {
			label.setForeground(counter[playerTwo].getColor());
			label.setText("It is " + counter[playerTwo].getName() + "'s Turn");
		}
		for (int i = 0; i < button.length; i++) {
			button[i].setEnable(true);
		}
		for (int i = gridCounters.length - 1; i >= 0; i--) {
			for (int j = 0; j < gridCounters[i].length; j++) {
				gridCounters[i][j].setIcon(new ImageIcon(Main.class.getResource("blank_counter.png")));
			}
		}
		countersUsed = 0;
		grid.resetGrid();
	}
}//end class
