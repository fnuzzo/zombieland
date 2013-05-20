package Project.Object;
/**
 * @author Fabrizio Nuzzo
 *
 */
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.Timer;

import Project.Structure.Board;

public class Pistolero extends Actor implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 */
	private Board board;

	/**
	 */
	private ArrayList<Bullet> bullets;
	/**
	 */
	private int a;

	/**
	 */
	private int b;
	/**
	 */
	private int SPEED_PISTOLERO = 5;
	/**
	 */
	private int caricator = 100;
	/**
	 */
	private int life = 3;
	private static int up_key;
	private static int right_key;
	private static int down_key;
	private static int left_key;
	private static int shoot_key;
	/**
	 */
	private Timer t;
	/**
	 */
	private Timer t1;
	/**
	 */
	private Timer t2;
	/**
	 */
	private boolean dead = false;
	/**
	 */
	private boolean collision = false;

	public Pistolero() {

		icons = new String[22];

		icons[0] = "WalterRight1.png";
		icons[1] = "WalterRight2.png";
		icons[2] = "WalterRight3.png";
		icons[3] = "WalterUp1.png";
		icons[4] = "WalterUp2.png";
		icons[5] = "WalterUp3.png";
		icons[6] = "WalterDown1.png";
		icons[7] = "WalterDown2.png";
		icons[8] = "WalterDown3.png";
		icons[9] = "WalterLeft1.png";
		icons[10] = "WalterLeft2.png";
		icons[11] = "WalterLeft3.png";
		icons[12] = "WalterDeath1.png";
		icons[13] = "WalterDeath2.png";
		icons[14] = "WalterDeath3.png";
		icons[15] = "WalterDeath4.png";
		icons[16] = "WalterDeath5.png";
		icons[17] = "WalterWin1.png";
		icons[18] = "WalterWin2.png";
		icons[19] = "WalterWin3.png";
		icons[20] = "WalterWin4.png";
		icons[21] = "WalterWin4.png";

		// Situation start Pistolero

		direction = "UP";
		x = 260;
		y = 420;

		bullets = new ArrayList<Bullet>();
		createSpritesActor();
		Death();
		reload();

	}// Pistolero

	/**
	 * GETTER AND SETTER
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param  life
	 */
	public void setLife(int life) {
		this.life = life;
		if (life == 0) {
			direction = "MORTO";
			dead = true;
		}
	}

	public void winner() {
		direction = "WIN";
		dead = true;
	}

	/**
	 * @param  caricator
	 */
	public void setCaricator(int caricator) {

		this.caricator = caricator;
	}

	/**
	 * @return
	 */
	public int getSPEED_PISTOLERO() {
		return SPEED_PISTOLERO;
	}

	public void setUp_key(int upKey) {
		up_key = upKey;
	}

	public void setRight_key(int rightKey) {
		right_key = rightKey;
	}

	public void setDown_key(int downKey) {
		down_key = downKey;
	}

	public void setLeft_key(int leftKey) {
		left_key = leftKey;
	}

	public void setShoot_key(int shootKey) {
		shoot_key = shootKey;
	}

	/**
	 * @return
	 */
	public boolean isCollision() {
		return collision;
	}

	/**
	 * @param  collision
	 */
	public void setCollision(boolean collision) {
		this.collision = collision;

	}

	public boolean isVisibile() {
		return visibile;
	}

	public void setVisibile() {
		this.visibile = false;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public int getcaricator() {
		return caricator;

	}

	public void setSpeedPistolero(int speed) {
		SPEED_PISTOLERO = speed;
	}

	/**
	 * @param  board
	 */
	public void setBoard(Board board) {
		this.board = board;

	}

	public void reload() {

		t = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (a > 3)
					a = 0;
				else
					a = (a + 1) % 3;

			}
		});

	}

	public void Death() {

		t1 = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (b >= iconspriteDEAD.size() - 1) {
					t1.stop();
					b = 4;
					dx = 0;
					dy = 0;
				} else {
					b++;
					dx = -2;
					dy = +1;
				}
			}
		});

		t2 = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (a > 4)
					a = 0;
				else
					a = (a + 1) % 4;
			}
		});
	}

	public void ferito() {

		if (direction == "LEFT") {
			x += SPEED_PISTOLERO + 30;
		} else if (direction == "RIGHT") {
			x -= SPEED_PISTOLERO + 30;
		} else if (direction == "UP") {
			y += SPEED_PISTOLERO + 30;
		} else if (direction == "DOWN") {
			y -= SPEED_PISTOLERO + 30;
		}

	}

	public void move() {

		if (x >= x_max) {
			dx = 0;
			x -= SPEED_PISTOLERO + 2;
		} else if (x <= 0) {
			dx = 0;
			x += SPEED_PISTOLERO + 2;
		} else if (y >= y_max) {
			dy = 0;
			y -= SPEED_PISTOLERO + 2;
		} else if (y <= 0) {
			dy = 0;
			y += SPEED_PISTOLERO + 2;
		}

		x += dx;
		y += dy;
	}

	public Image getImage() {

		if (direction == "LEFT") {
			image = iconspriteMaleLEFT.get(a);
		} else if (direction == "RIGHT") {
			image = iconspriteMaleRIGHT.get(a);
		} else if (direction == "UP") {
			image = iconspriteMaleUP.get(a);
		} else if (direction == "DOWN") {
			image = iconspriteMaleDOWN.get(a);
		} else if (direction == "MORTO") {
			t1.start();
			image = iconspriteDEAD.get(b);
		} else if (direction == "WIN") {
			image = iconspriteWIN.get(a);
			t2.start();
		}
		return image;

	}// getImage

	/************************* METHOD PISTOLERO ****************************************/

	public void changeDirection(Obstacle o) {

		Point p1;
		Point p2;
		Point p3;

		Rectangle rd = this.getBounds();
		Rectangle ro = o.getBounds();

		if (direction == "UP") {

			p1 = new Point((int) rd.getMinX(), (int) rd.getMinY());
			p2 = new Point((int) rd.getMaxX(), (int) rd.getMinY());
			p3 = new Point((int) rd.getCenterX(), (int) rd.getCenterY());

			if (ro.contains(p1) || ro.contains(p2) || ro.contains(p3)) {
				dy = 0;
				y += SPEED_PISTOLERO + 2;
			}
		} else if (direction == "RIGHT") {
			p1 = new Point((int) rd.getMaxX(), (int) rd.getMinY());
			p2 = new Point((int) rd.getMaxX(), (int) rd.getMaxY());
			p3 = new Point((int) rd.getCenterX(), (int) rd.getCenterY());

			if (ro.contains(p1) || ro.contains(p2) || ro.contains(p3)) {
				dx = 0;
				x -= SPEED_PISTOLERO + 2;
			}
		} else if (direction == "DOWN") {
			p1 = new Point((int) rd.getMinX(), (int) rd.getMaxY());
			p2 = new Point((int) rd.getMaxX(), (int) rd.getMaxY());
			p3 = new Point((int) rd.getCenterX(), (int) rd.getCenterY());

			if (ro.contains(p1) || ro.contains(p2) || ro.contains(p3)) {
				dy = 0;
				y -= SPEED_PISTOLERO + 2;
			}
		} else if (direction == "LEFT") {
			p1 = new Point((int) rd.getMinX(), (int) rd.getMaxY());
			p2 = new Point((int) rd.getMinX(), (int) rd.getMinY());
			p3 = new Point((int) rd.getCenterX(), (int) rd.getCenterY());

			if (ro.contains(p1) || ro.contains(p2) || ro.contains(p3)) {
				dx = 0;
				x += SPEED_PISTOLERO + 2;
			}
		}

	} // changeDirection

	public void fire() {

		if (caricator == -1)
			bullets.add(new Bullet(x + width / 2, y + height, direction,
					SPEED_PISTOLERO));

		else if (caricator > 0) {
			bullets.add(new Bullet(x + width / 2, y + height, direction,
					SPEED_PISTOLERO));
			caricator--;
		} else
			caricator = 0;
	}// fire

	public void keyPressed(KeyEvent e) {

		if (!dead) {

			int key = e.getKeyCode();

			if (key == left_key) {
				t.start();
				direction = "LEFT";
				dx = -SPEED_PISTOLERO;
			}

			else if (key == right_key) {
				t.start();
				direction = "RIGHT";
				dx = SPEED_PISTOLERO;
			} else if (key == up_key) {
				t.start();
				direction = "UP";
				dy = -SPEED_PISTOLERO;
			}

			else if (key == down_key) {
				t.start();
				direction = "DOWN";
				dy = SPEED_PISTOLERO;
			}

			else if (key == shoot_key)
				fire();

			else if (key == KeyEvent.VK_ENTER) {
				if (board.getTimer().isRunning())
					board.pause();
				else
					board.restart();

			}
		}

	}// keyPressed

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == up_key) {
			dy = 0;
			t.stop();
		}

		else if (key == down_key) {
			dy = 0;
			t.stop();
		} else if (key == right_key) {
			dx = 0;
			t.stop();
		} else if (key == left_key) {
			dx = 0;
			t.stop();
		}

	}// keyReleased

}// Pistolero

