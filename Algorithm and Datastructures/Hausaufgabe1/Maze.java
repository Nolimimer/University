
import javax.swing.*;

import java.awt.*;
import java.applet.*;
import java.util.*;

public class Maze extends Applet {
	private static final long serialVersionUID = 1L;

	private class Field extends JPanel {
		private static final long serialVersionUID = 1L;
		Point p;
		int x, y;

		public Field(int x, int y) {
			this.x = x;
			this.y = y;
			p = getLocation();
		}

		public void paint(Graphics g) {
			GradientPaint wallPaint = new GradientPaint(10, 50, Color.DARK_GRAY, getWidth(), 0, Color.DARK_GRAY);
			GradientPaint floorPaint = new GradientPaint(10, 50, Color.WHITE, getWidth(), 0, Color.WHITE);
			GradientPaint pathPaint = new GradientPaint(15, 0, Color.GREEN, getWidth(), 0, Color.LIGHT_GRAY);
			GradientPaint positionPaint = new GradientPaint(15, 0, Color.RED, getWidth(), 0, Color.LIGHT_GRAY);

			super.paint(g);
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			if (spielFeld[x][y]) {
				((Graphics2D) g).setPaint(wallPaint);
			} else {
				((Graphics2D) g).setPaint(floorPaint);
			}
			g.fillRect(p.x, p.y, getWidth(), getHeight());

			if (sol != null && sol[x][y]) {
				((Graphics2D) g).setPaint(pathPaint);
				g.fillOval((int) (getWidth() * .25), (int) (getHeight() * .25), (int) (getWidth() * .5),
						(int) (getHeight() * .5));
			}
			if (x == posx && y == posy) {
				((Graphics2D) g).setPaint(positionPaint);
				g.fillOval((int) (getWidth() * .25), (int) (getHeight() * .25), (int) (getWidth() * .5),
						(int) (getHeight() * .5));
			}
		}
	}

	public static void setSolution(boolean[][] sol) {
		r.sol = sol;
	}

	private JFrame myFrame = new JFrame("Spielfeld");
	private JPanel pan = new JPanel();
	private boolean[][] spielFeld;
	private int posx, posy;

	private boolean[][] sol = null;

	public Maze(boolean[][] spielfeld) {
		this.spielFeld = spielfeld;
	}

	private Maze(int px, int py, boolean[][] feld) {
		int width = feld.length;
		int height = feld[0].length;
		spielFeld = new boolean[width][height];
		// careful: we define as x growing to the right, y growing to the
		// bottom, while GridLayout defines height first, width second!
		pan.setLayout(new GridLayout(height, width));

		Field[][] field = new Field[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				field[x][y] = new Field(x, y);
				pan.add(field[x][y]);
				spielFeld[x][y] = feld[x][y];
			}
		}

		myFrame.getContentPane().add(pan);
		int windowWidth = 800, windowHeight = 800;
		if (width >= height) {
			windowHeight = windowHeight * height / width;
		} else {
			windowWidth = windowWidth * width / height;
		}
		myFrame.setSize(windowWidth, windowHeight);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		update(px, py, feld);
	}

	private void update(int px, int py, boolean[][] feld) {
		if (feld.length != spielFeld.length) {
			System.err.println("Spielfeld darf sich nicht vergroessern/verkleinern...");
		}
		if (feld[0].length != spielFeld[0].length) {
			System.err.println("Spielfeld darf sich nicht vergroessern/verkleinern...");
		}

		for (int x = 0; x < spielFeld.length; x++) {
			for (int y = 0; y < spielFeld[0].length; y++) {
				spielFeld[x][y] = feld[x][y];
			}
		}
		this.posx = px;
		this.posy = py;
		pan.repaint();
	}

	private static Maze r;

	public static void draw(int x, int y, boolean[][] feld, boolean[][] solution) {
		if (r == null) {
			r = new Maze(x, y, feld);
		}

		r.sol = solution;
		r.update(x, y, feld);

		try {
			Thread.sleep(0);
		} catch (InterruptedException ie) {
		}
	}

	public static void main(String[] args) {
		boolean[][] spielfeld = generateMaze(50, 50);
		draw(1, 0, spielfeld, null);
		draw(1, 1, spielfeld, null);
		Maze maze = new Maze(spielfeld);
		try {
			maze.walk(1, 1);
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Unmoegliches Labyrinth");
		}

	}

	//Vier verschiedene Zustände
	public enum Blick {
		Vorne, Unten, Rechts, Links
	};

	private static Blick blick = Blick.Rechts;

	public void walk(int x, int y) {
		this.posx = x;
		this.posy = y;
		while (!(FoundExit())) {
			waitOneSec();
			if (noRightWall(posx, posy)) {
				rotateRight();
				step(posx, posy);
			} else if (noFrontWall(posx, posy)) {
				step(posx, posy);
			} else {
				rotateLeft();
			}
			saveStep(posx, posy);
		}
	}

	//Für grüne Linie
	private void saveStep(int posx, int posy) {
		if (sol == null) {
			this.sol = new boolean[spielFeld.length][spielFeld[0].length];
		}
		sol[posx][posy] = true;
		draw(this.posx, this.posy, this.spielFeld, sol);
	}

	private void waitOneSec() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//Je nach Blick in andere Position laufen
	private void step(int x, int y) {
		if (blick == Blick.Rechts) {
			setPointRight(x, y);
		} else if (blick == Blick.Links) {
			setPointLeft(x, y);
		} else if (blick == Blick.Vorne) {
			setPointUp(x, y);
		} else if (blick == Blick.Unten) {
			setPointDown(x, y);
		}
	}

	//checkt ob es eine FrontWand gibt
	private boolean noFrontWall(int x, int y) {
		if (blick == Blick.Rechts) {
			return !(checkRight(x, y));
		} else if (blick == Blick.Links) {
			return !(checkLeft(x, y));
		} else if (blick == Blick.Vorne) {
			return !(checkUp(x, y));
		} else if (blick == Blick.Unten) {
			return !(checkDown(x, y));
		}
		System.err.println("Unerwarteter Fehler");
		return true;
	}

	//checkt ob es eine RechteWand gibt
	private boolean noRightWall(int x, int y) {
		if (blick == Blick.Rechts) {
			return !(checkDown(x, y));
		} else if (blick == Blick.Links) {
			return !(checkUp(x, y));
		} else if (blick == Blick.Vorne) {
			return !(checkRight(x, y));
		} else if (blick == Blick.Unten) {
			return !(checkLeft(x, y));
		}
		System.err.println("Unerwarteter Fehler");
		return true;
	}

	//Linksdrehung
	private void rotateLeft() {
		if (blick == Blick.Rechts) {
			blick = Blick.Vorne;
		} else if (blick == Blick.Links) {
			blick = Blick.Unten;
		} else if (blick == Blick.Vorne) {
			blick = Blick.Links;
		} else if (blick == Blick.Unten) {
			blick = Blick.Rechts;
		}
	}

	//Rechtsdrehung
	private void rotateRight() {
		if (blick == Blick.Rechts) {
			blick = Blick.Unten;
		} else if (blick == Blick.Links) {
			blick = Blick.Vorne;
		} else if (blick == Blick.Vorne) {
			blick = Blick.Rechts;
		} else if (blick == Blick.Unten) {
			blick = Blick.Links;
		}
	}

	// Wenn falsch, dann ist das Spielfeld frei
	private boolean checkRight(int x, int y) {
		return (spielFeld[x + 1][y]);
	}

	private boolean checkLeft(int x, int y) {
		return (spielFeld[x - 1][y]);
	}

	private boolean checkUp(int x, int y) {
		return (spielFeld[x][y - 1]);
	}

	private boolean checkDown(int x, int y) {
		return (spielFeld[x][y + 1]);
	}

	// Punkt setzen
	private void setPointRight(int x, int y) {
		draw((posx = x + 1), y, spielFeld, null);
	}

	private void setPointLeft(int x, int y) {
		draw((posx = x - 1), y, spielFeld, null);
	}

	private void setPointUp(int x, int y) {
		draw(x, (posy = y - 1), spielFeld, null);
	}

	private void setPointDown(int x, int y) {
		draw(x, (posy = y + 1), spielFeld, null);
	}

	// Exit-Bedingung
	private boolean FoundExit() {
		if (posx == spielFeld.length - 1 && posy == spielFeld[0].length - 2) {
			System.out.println("Du hast den Weg raus gefunden!");
			return true;
		} else {
			return false;
		}
	}

	public static boolean[][] generateMaze() {
		return generateMaze(11, 11);
	}

	public static boolean[][] generateMaze(int width, int height) {
		Random r = new Random();
		return generateMazeInternal(width, height, r);
	}

	public static boolean[][] generateStandardMaze() {
		return generateStandardMaze(11, 11);
	}

	public static boolean[][] generateStandardMaze(int width, int height) {
		Random r = new Random();
		r.setSeed(0);
		return generateMazeInternal(width, height, r);
	}

	private static boolean[][] generateMazeInternal(int width, int height, Random rng) {
		if (width < 3) {
			width = 3;
		}
		if (height < 3) {
			height = 3;
		}
		boolean[][] maze = new boolean[width][height];

		// borders
		for (int x = 0; x < width; x++) {
			maze[x][0] = true;
			maze[x][height - 1] = true;
		}
		for (int y = 0; y < height; y++) {
			maze[0][y] = true;
			maze[width - 1][y] = true;
		}

		// create random obstacles
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (rng.nextInt(4) == 0) {
					maze[x][y] = true;
				}
			}
		}

		// entrance and exit
		maze[1][0] = false;
		maze[1][1] = false;
		maze[width - 1][height - 2] = false;
		maze[width - 2][height - 2] = false;

		return maze;
	}

}
