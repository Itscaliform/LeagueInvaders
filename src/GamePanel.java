import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	private final int MENU_STATE = 0;
	private final int GAME_STATE = 1;
	private final int END_STATE = 2;

	private int currentState = MENU_STATE;
	private Timer timer;
	private Font titleFont;
	private Font subTitleFont;
	private Rocketship rocket;
	private ObjectManager objectManager;

	public GamePanel() {
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		subTitleFont = new Font("Arial", Font.PLAIN, 24);
		rocket = new Rocketship(250, 700, 50, 50);
		objectManager = new ObjectManager();
		objectManager.addObject(rocket);
	}

	public void startGame() {
		timer.start();
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
		repaint();
	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 20, 200);

		g.setFont(subTitleFont);
		g.drawString("Press ENTER to start", 110, 300);
		g.drawString("Press SPACE for instructions", 70, 400);
	}

	public void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		objectManager.draw(g);
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 60, 100);

		g.setFont(subTitleFont);
		g.drawString("You killed 0 aliens.", 120, 300);
		g.drawString("Press BACKSPACE to Restart", 60, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
		
		repaint();
	}

	public void updateMenuState() {
		currentState = MENU_STATE;
	}

	public void updateGameState() {
		currentState = GAME_STATE;

		objectManager.manageEnemies();
		objectManager.checkCollision();
		objectManager.update();
		
		if (!rocket.isAlive) {
			currentState = END_STATE;
			System.out.println("End state!!!");
		}
	}

	public void updateEndState() {
		currentState = END_STATE;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_ENTER) {
			currentState++;
			if (currentState > END_STATE)
				currentState = MENU_STATE;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			rocket.x += rocket.speed;
		} else if (keyCode == KeyEvent.VK_LEFT) {
			rocket.x -= rocket.speed;
		} else if (keyCode == KeyEvent.VK_UP) {
			rocket.y -= rocket.speed;
		} else if (keyCode == KeyEvent.VK_DOWN) {
			rocket.y += rocket.speed;
		} else if (keyCode == KeyEvent.VK_SPACE) {
			objectManager.addObject(new Projectile(rocket.x + rocket.width/2, rocket.y, 10, 10));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
