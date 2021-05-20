import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

	private GameComponent gc;
	private Hero hero;
	
	public MyKeyListener(GameComponent gc) {
		this.gc = gc;
		this.hero = gc.getHero();
		gc.setKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			this.hero.updateVelocityX(1);
			this.hero.setIsFacingRight(true);
			break;
		case KeyEvent.VK_LEFT:
			this.hero.updateVelocityX(-1);
			this.hero.setIsFacingRight(false);
			break;
		 case KeyEvent.VK_UP:
		 this.hero.jumpUp();
		 break;
		case KeyEvent.VK_D:
			gc.levelDown();
			break;
		case KeyEvent.VK_U:
			gc.levelUp();
			break;
		case KeyEvent.VK_SPACE:
			gc.shootBubble();
			break;
		case KeyEvent.VK_R:
			gc.restart();
			break;
		}
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			this.hero.updateVelocityX(0);
			break;
		case KeyEvent.VK_LEFT:
			this.hero.updateVelocityX(0);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void setHero(Hero h) {
		this.hero = h;
	}

}
