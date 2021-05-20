import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelLoader {
	Hero hero = null;
	ArrayList<Monster> monsters;
	ArrayList<Platform> platforms;
	ArrayList<Drawable> drawables;

	public LevelLoader() {

	}

	public ArrayList<Drawable> loadLevel(int id) {
		File file = new File("levels/level" + id + ".txt");
		this.monsters = new ArrayList<Monster>();
		this.drawables = new ArrayList<Drawable>();
		this.platforms = new ArrayList<Platform>();
		try {
			Scanner sc = new Scanner(file);
			int count = 0;
			while (sc.hasNextLine()) {
				String str = sc.nextLine();
				for (int i = 0; i < str.length(); i++) {
					char charAt = str.charAt(i);
					switch (charAt) {
					case 'L':
						Platform leftEdge = new Platform(i * 50, count * 50);
						leftEdge.setIsLeftEdge();
						drawables.add(leftEdge);
						platforms.add(leftEdge);
						break;
					case 'R':
						Platform rightEdge = new Platform(i * 50, count * 50);
						rightEdge.setIsRightEdge();
						drawables.add(rightEdge);
						platforms.add(rightEdge);
						break;
					case 'P':
						Platform platform = new Platform(i * 50, count * 50);
						drawables.add(platform);
						platforms.add(platform);
						break;
					case 'H':
						hero = new Hero(i * 50, count * 50 + 12.5);
						break;
					case '1':
						Monster monster1 = new Monster1(i * 50, count * 50 + 25);
						drawables.add(monster1);
						monsters.add(monster1);
						break;
					case '2':
						Monster monster2 = new Monster2(i * 50, count * 50 + 13);
						drawables.add(monster2);
						monsters.add(monster2);
						break;
					}
				}
				count++;

			}
			sc.close();
			return drawables;

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		return null;
	}

	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}

	public Hero getHero() {
		return this.hero;
	}

	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}

}
