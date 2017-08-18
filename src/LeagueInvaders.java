import javax.swing.JFrame;

public class LeagueInvaders {
	private JFrame frame;
	private GamePanel gamePanel;
	static final int WIDTH = 500, HEIGHT = 800;

	public static void main(String[] args) {
		new LeagueInvaders();
	}

	public LeagueInvaders() {
		frame = new JFrame();
		gamePanel = new GamePanel();

		setup();
	}

	private void setup() {
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.add(gamePanel);
		frame.addKeyListener(gamePanel);
		gamePanel.startGame();
	}
}
