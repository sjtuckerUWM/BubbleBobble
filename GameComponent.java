
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GameComponent extends JComponent {

	public int score;
	public int lives;
	private static final int UPDATE_INTERVAL_MS = 5;
	private boolean hasShownNullErrorMessage = false;
	private ArrayList<Drawable> drawables;
	private ArrayList<Platform> platforms;
	private ArrayList<Bubble> bubbles;
	private ArrayList<Bullet> bullets;
	private ArrayList<Monster> monsters;
	private ArrayList<Fruit> fruits;
	private Hero hero;
	private LevelLoader ll;
	private int currentLevelId;
	private MyKeyListener kl;
	private boolean lost;
	private boolean won;

	public GameComponent() {
		score = 0;
		this.ll = new LevelLoader();
		currentLevelId = 1;
		drawables = ll.loadLevel(currentLevelId);
		platforms = ll.getPlatforms();
		monsters = ll.getMonsters();
		this.hero = ll.getHero();
		bubbles = new ArrayList<>();
		bullets = new ArrayList<>();
		fruits = new ArrayList<>();
		lives = hero.getLives();
		this.lost = false;
		this.won = win();
		Timer advanceStateTimer = new Timer(UPDATE_INTERVAL_MS, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkLose();
				checkComplete();
				win();
				updatePositions();
				repaint();

			}
		});
		advanceStateTimer.start();
		
		Timer advanceStateTimerTwo = new Timer(2000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shootHero();
			}

		});
		advanceStateTimerTwo.start();
		
	}

	protected void checkComplete() {
		if(currentLevelId <= 3 && checkAllMonstersDead() && checkAllBubblesPopped()) {
			levelUp();
		}
	}

	private boolean checkAllBubblesPopped() {
		for(Bubble b: bubbles) {
			if(!b.alive) {
				return true;
			}
		}
		return false;
	}

	private boolean checkAllFruitsGone() {
		for(Fruit f: fruits) {
			if(f.alive) {
				return false;
			}
		}
		return true;
	}

	private boolean checkAllMonstersDead() {
		for(Monster m: monsters) {
			if(m.height != 0) {
				return false;
			}
		}
		return true;
	}

	protected void checkLose() {
		// TODO Auto-generated method stub
		if (lost) {
			hero.setHeight(0);
			hero.setWidth(0);
		}
	}

	protected void updatePositions() {
		// TODO Auto-generated method stub
		hero.updatePosition(platforms);
		for (Monster m : this.monsters) {
			m.updatePosition(platforms, getHerosLocation());
		}
		for (Bubble b : this.bubbles) {
			b.travel();
			if(b.alive) {
				checkReleaseMonster(b);				
			}
		}
		for (Bullet bullet : this.bullets) {
			bullet.travel();
		}
		for (Fruit fruit: this.fruits) {
			fruit.updatePosition();
		}
		checkKillHero();
		checkCapMonster();
		checkPopBubble();
		checkEat();
	}

	private void checkPopBubble() {
		// TODO Auto-generated method stub
		for(Bubble b: bubbles) {
			if(b.full) {
				if(hero.getShape().intersects((Rectangle2D) b.getShape())) {
					setScore(getScore() + (b.getMonsterType() * 150));
					b.disable();
					dropFruit(b.getCapturedMonster());
				}
			}
		}
	}

	private void dropFruit(Point2D location) {
		// TODO Auto-generated method stub
		Fruit fruit = new Fruit(location.getX(), location.getY());
		fruits.add(fruit);
	}

	private void setScore(int i) {
		score += i;
	}

	private int getScore() {
		return this.score;
	}

	private void checkKillHero() {
		// TODO Auto-generated method stub
		for (Bullet bullet : bullets) {
			if (hero.getShape().intersects((Rectangle2D) bullet.getShape())) {
				if (hero.getLives() >= 1) {
					hero.respawn();
					hero.setLives(hero.getLives() - 1);
				} 
				if(hero.getLives() == 0) {
					this.lost = true;
				}
			}
		}
		for (Monster monster : monsters) {
			if (hero.getShape().intersects((Rectangle2D) monster.getShape())) {
				if (hero.getLives() > 0) {
					hero.respawn();
					hero.setLives(hero.getLives() - 1);
					for(Bullet b: bullets) {
						b.disable();
					}
				} else {
					this.lost = true;
				}
			}
		}
	}

	private void checkCapMonster() {
		for (Bubble bubble : bubbles) {
			if(!bubble.full) {
				for (Monster monster : monsters) {
					if (monster.getShape().intersects((Rectangle2D) bubble.getShape())) {
						bubble.setCaputerdMonster(monster.originalX, monster.originalY, monster.type);
						monster.disable();
					}
				}
			}
		}
	}

	private void checkReleaseMonster(Bubble bubble) {
		if(bubble.releaseCounter == 500) {
			bringBackMonster(bubble.getCapturedMonster(), bubble.getMonsterType());
			bubble.disable();
		}
	}

	private void bringBackMonster(Point2D location, int monsterType) {
		if(monsterType == 1) {
			Monster monster = new Monster1(location.getX(), location.getY());
			monsters.add(monster);
		}
		if(monsterType == 2) {
			Monster monster = new Monster2(location.getX(), location.getY());
			monsters.add(monster);
		}
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);

		// List<Drawable> drawableParts = this.game.getDrawableParts();
		for (Platform p : platforms) {
			p.draw(g2);
		}
		for (Monster m : monsters) {
			m.draw(g2);
		}
		for (Bubble b : bubbles) {
			b.draw(g2);
		}
		for (Bullet bullet : bullets) {
			bullet.draw(g2);
		}
		for(Fruit fruit: fruits) {
			fruit.draw(g2);
		}
		hero.draw(g2);
	}

	public void setKeyListener(MyKeyListener kl) {
		this.kl = kl;
	}

	public Hero getHero() {
		return this.hero;
	}

	public Point2D getHerosLocation() {
		return new Point2D.Double(hero.getX(), hero.getY());
	}

	public ArrayList<Platform> getPlatforms() {
		return this.platforms;
	}

	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}

	public void levelDown() {
		if (currentLevelId > 1) {
			currentLevelId -= 1;
			drawables = ll.loadLevel(currentLevelId);
			platforms = ll.getPlatforms();
			hero = ll.getHero();
			monsters = ll.getMonsters();
			bubbles = new ArrayList<>();
			bullets = new ArrayList<>();
			fruits = new ArrayList<>();
			this.kl.setHero(hero);
		}
	}

	public void levelUp() {
		if (currentLevelId < 4) {
			currentLevelId += 1;
			drawables = ll.loadLevel(currentLevelId);
			platforms = ll.getPlatforms();
			hero = ll.getHero();
			hero.setLives(3);
			monsters = ll.getMonsters();
			bubbles = new ArrayList<>();
			bullets = new ArrayList<>();
			fruits = new ArrayList<>();
			this.kl.setHero(hero);
		}
	}

	public void shootBubble() {
		Bubble bubble = new Bubble(hero.getX(), hero.getY(), hero.getIsFacingRight());
		bubbles.add(bubble);
	}

	public void shootHero() {
		for (Monster m : monsters) {
			if (m.shooter == true && m.alive) {
				Bullet bullet = new Bullet(m.getX(), m.getY(), this.getHerosLocation());
				bullets.add(bullet);
			}
		}
	}
	
	public void restart() {
		score = 0;
		this.ll = new LevelLoader();
		currentLevelId = 1;
		drawables = ll.loadLevel(currentLevelId);
		platforms = ll.getPlatforms();
		monsters = ll.getMonsters();
		this.hero = ll.getHero();
		bubbles = new ArrayList<>();
		bullets = new ArrayList<>();
		fruits = new ArrayList<>();
		hero.setLives(3);
		this.lost = false;
		this.kl.setHero(hero);
	}
	
	public void checkEat() {
		for(Fruit f: fruits) {
			if(hero.getShape().intersects((Rectangle2D) f.getShape())) {
				setScore(getScore() + 250);
				f.disable();
			}
		}
	}
	
	public boolean win() {
		if(currentLevelId == 4 && checkAllMonstersDead() && checkAllBubblesPopped()) {
			return true;
		}
		return false;
	}

}
