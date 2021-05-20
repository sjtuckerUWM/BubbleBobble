import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

public class Bubble extends Movable {
	
	private boolean facingRight;
	public boolean full;
	private Point2D capturedMonster;
	private int monsterType = 0;
	private boolean atTop = false;
	public int releaseCounter;
	Timer advanceStateTimer;
	
	public Bubble(double x, double y, boolean directionOfTravel) {
		super(x, y + 10);
		this.width = 20;
		releaseCounter = 0;
		this.capturedMonster = new Point2D.Double();
		this.height = 20;
		this.facingRight = directionOfTravel;
		this.full = false;
		this.setColor(Color.gray);
		this.setShape(new Rectangle2D.Double(x, y + 10, this.width, this.height));
		advanceStateTimer = new Timer(5, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				releaseCounter++;
			}
		});
	}
	
	public void travel() {
		if (!full) {
			if (facingRight == true) {
				this.updateVelocityX(1);
				this.setX(getX() + getVelocityX());
			}
			if (facingRight == false) {
				this.updateVelocityX(-1);
				this.setX(getX() + getVelocityX());
			}
		} 
		else {
			if (this.getY() != 0) {
				this.updateVelocityY(-0.5);
				checkBounds();
				this.setY(getY() + getVelocityY());
			} else {
				this.atTop = true;
				startTimer();
			}
		}
		this.setShape(new Rectangle2D.Double(this.getX(), this.getY(), this.width, this.height));
	}
	
	@Override
	public void checkBounds() {
		if(this.getY() <= 0) {
			this.updateVelocityY(0);
		}
	}

	public void setCaputerdMonster(double x, double y, int type) {
		full = true;
		this.setColor(Color.MAGENTA);
		capturedMonster = new Point2D.Double(x, y);
		monsterType = type;
	}
	
	public int getMonsterType() {
		return monsterType;
	}
	
	public Point2D getCapturedMonster() {
		return capturedMonster;
	}

	public boolean isAtTop() {
		return atTop;
	}

	public void setAtTop(boolean atTop) {
		this.atTop = atTop;
	}
	
	public void startTimer() {
		advanceStateTimer.start();
	}
}
