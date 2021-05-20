import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class Monster extends Movable {

	boolean shooter;
	public int type;


	public Monster(double x, double y) {
		super(x, y);
		alive = true;

		
		// TODO Auto-generated constructor stub
	}

	
	public void updatePosition(ArrayList<Platform> platforms, Point2D heroLocation) {
		
	}



}
