import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject {
	private int x, y, width, height;

	public Alien(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void update() {
		super.update();
		y++;
	}

	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
	}
}
