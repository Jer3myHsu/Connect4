import java.awt.Color;

import javax.swing.ImageIcon;

public class Counter {
	private String name = "";
	private Color color = Color.black;
	private ImageIcon count = new ImageIcon();
	private ImageIcon win = new ImageIcon();
	public Counter(String color, String hex) {
		setName(color);
		setColor(color.toLowerCase(), hex);
	}
	public void setColor(String color, String hex) {
		this.color = Color.decode(hex);
		count.setImage(new ImageIcon(Counter.class.getResource(color.toLowerCase() + "_counter.png")).getImage());
		win.setImage(new ImageIcon(Counter.class.getResource(color.toLowerCase() + "_win.png")).getImage());
	}
	public Color getColor() {
		return color;
	}
	public ImageIcon getCounter() {
		return count;
	}
	public ImageIcon getWinCounter() {
		return win;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
