import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start
 * by running main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Buffalo 
 * mohammed and samuel
 *
 */
public class Main {
	private static final int WIDTH = 620;
	private static final int HEIGHT = 650;
	private int gameScore = 0;
	private int playerLives;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		GameComponent gameComponent = new GameComponent();
		
		JFrame frame = new JFrame("BUBBLE BOBBLE!!!");

		
		MyKeyListener keyboard = new MyKeyListener(gameComponent); 
		JPanel scorePanel = new JPanel();
		scorePanel.setSize(400, HEIGHT);
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));
		JLabel score = new JLabel();
		JLabel lives = new JLabel();
		scorePanel.add(score);
		scorePanel.add(lives, BorderLayout.SOUTH);
		scorePanel.setBackground(Color.lightGray);
		frame.add(scorePanel,BorderLayout.NORTH);
		frame.setSize(WIDTH, HEIGHT);
		frame.add(gameComponent);
		frame.addKeyListener(keyboard);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		Timer myTimer = new Timer(500, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(gameComponent.win()) {
					JOptionPane.showMessageDialog(frame, "YOU WON!!!");
					gameComponent.restart();
				}
			    score.setText("score: " + Integer.toString(gameComponent.score));
			    lives.setText("lives: " + Integer.toString(gameComponent.getHero().getLives()));
			    }
			});
			myTimer.start();

	}
	
	

	

}
