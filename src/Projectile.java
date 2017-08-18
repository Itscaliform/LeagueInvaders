import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {
	private int speed = 10;

	public Projectile(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update() {
		super.update();
		y -= speed;
		if (y < 0)
			isAlive = false;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
}
