import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {

	int x, y, width, height, speed;

	public Rocketship(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		speed = 5;
	}

	public void update() {
		super.update();
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
