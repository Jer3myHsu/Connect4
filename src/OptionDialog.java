import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class OptionDialog extends JDialog {
	final static int playerOne = 0;
	final static int playerTwo = 1;
	private JButton dialogButton = new JButton("Apply");
	public OptionDialog(JFrame frame) {
		super(frame, "Options", true);
		JPanel mainPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel playerPanel[] = new JPanel[2];
		playerPanel[0] = new JPanel();
		playerPanel[1] = new JPanel();
		String colors[] = {"Red", "Blue", "Yellow", "Green", "Purple", "Orange", "White", "Black"};
		ButtonGroup group[] = new ButtonGroup[2];
		JRadioButton radioButton[][] = new JRadioButton[2][colors.length];
		JTextField nameField[] = new JTextField[2];
		TitledBorder border[] = new TitledBorder[2];
		border[0] = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Player 1", TitledBorder.LEFT, TitledBorder.TOP);
		border[1] = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Player 2", TitledBorder.RIGHT, TitledBorder.TOP);
		JLabel display[] = new JLabel[2];
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
		mainPanel.add(dialogButton);
		mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
		add(mainPanel);
		setResizable(false);
		setSize(340, 384);
	}
	public void addActionListener(ActionListener action) {
		dialogButton.addActionListener(action);
	}
}
