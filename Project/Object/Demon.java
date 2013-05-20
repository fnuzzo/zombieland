package Project.Object;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Timer;
/**
 * @author Fabrizio Nuzzo
 *
 */
public class Demon extends Actor implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 */
	private int SPEED_DEMON = 2;
	/**
	 */
	private int dir;
	/**
	 */
	private int sex = 0; // Sex Demon 0=homme 1=famme
	/**
	 */
	private boolean reproduction = false;
	/**
	 */
	private ArrayList<String> directions;
	/**
	 */
	private String[] inizialDir = { "UP", "DOWN", "RIGHT", "LEFT" };
	/**
	 */
	private int icon_mouve;

	/**
	 */
	private int seq_dead;
	/**
	 */
	private int dead = 0;
	/**
	 */
	private Timer t1;
	/**
	 */
	private Timer t2;
	/**
	 */
	private Timer t3;

	public Demon() {

		icons = new String[30];

		// Add Sprites Male
		icons[0] = "zombiMaler1.png";
		icons[1] = "zombiMaler2.png";
		icons[2] = "zombiMaler3.png";
		icons[3] = "zombiMaleu1.png";
		icons[4] = "zombiMaleu2.png";
		icons[5] = "zombiMaleu3.png";
		icons[6] = "zombiMaled1.png";
		icons[7] = "zombiMaled2.png";
		icons[8] = "zombiMaled3.png";
		icons[9] = "zombiMalel1.png";
		icons[10] = "zombiMalel2.png";
		icons[11] = "zombiMalel3.png";

		icons[12] = "Death1.png";
		icons[13] = "Death2.png";
		icons[14] = "Death3.png";
		icons[15] = "Death4.png";
		icons[16] = "Death5.png";
		icons[17] = "Death6.png";

		// Add Sprites Female Male
		icons[18] = "zombiFemaler1.png";
		icons[19] = "zombiFemaler2.png";
		icons[20] = "zombiFemaler3.png";
		icons[21] = "zombiFemaleu1.png";
		icons[22] = "zombiFemaleu2.png";
		icons[23] = "zombiFemaleu3.png";
		icons[24] = "zombiFemaled1.png";
		icons[25] = "zombiFemaled2.png";
		icons[26] = "zombiFemaled3.png";
		icons[27] = "zombiFemalel1.png";
		icons[28] = "zombiFemalel2.png";
		icons[29] = "zombiFemalel3.png";

		this.setSex();
		createSpritesActor();

		this.setX();
		this.setY();

		dir = (int) (inizialDir.length * (Math.random()));
		direction = inizialDir[dir];

		starTimer();

	}// Demon

	/**************************************** GETTER AND SETTER ***********************************/

	public void setdead() {

		t1.stop();
		t3.start();
		this.setDead(1);
		direction = "MORTO";
	}

	/**
	 * @return
	 */
	public int getSex() {
		return sex;
	}

	public int getSpeed() {
		return SPEED_DEMON;
	}
	
	public void setSpeedDemon(int speed) {
		SPEED_DEMON = speed;
	}

	public void setSex() {
		sex = (int) (2 * (Math.random()));
	}

	public void setX() {
		x = (int) (x_max * (Math.random()));
	}

	public void setY() {
		y = (int) (300 * (Math.random()));
	}

	/**
	 * @return
	 */
	public boolean isReproduction() {
		return reproduction;
	}

	public void setReproduction() {
		this.reproduction = true;
	}

	public int isDead() {
		return dead;
	}

	/**
	 * @param  dead
	 */
	public void setDead(int dead) {
		this.dead = dead;
	}

	/************************* METHOD DEMON ****************************************/

	public void starTimer() {
		t1 = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (icon_mouve > 3)
					icon_mouve = 0;
				else
					icon_mouve = (icon_mouve + 1) % 3;

			}
		});

		t2 = new Timer(4000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeDirection();

			}
		});

		t2.start();

		t3 = new Timer(400, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				seq_dead++;
				if (seq_dead >= 6) {
					t3.stop();
					dead = 2;
					seq_dead = 0;
				}

			}
		});

	}

	public void changeDirection() {
		if (direction.equals("UP")) {
			y += SPEED_DEMON;
			direction = "DOWN";
		} else if (direction.equals("RIGHT")) {
			x -= SPEED_DEMON;
			direction = "LEFT";
		} else if (direction.equals("DOWN")) {
			y -= SPEED_DEMON;
			direction = "UP";
		} else if (direction.equals("LEFT")) {
			x += SPEED_DEMON;
			direction = "RIGHT";
		}

		t1.stop();

		if (dead == 0) {
			directions = new ArrayList<String>(Arrays.asList("UP", "RIGHT",
					"DOWN", "LEFT"));
			directions.remove(direction);
			direction = directions.get((int) (directions.size() * (Math
					.random())));
		}
	} // changeDirection

	public void changeDirection(Obstacle o) {
		t1.stop();
		boolean change = false;
		directions = new ArrayList<String>(Arrays.asList("UP", "RIGHT", "DOWN",
				"LEFT"));
		Point p1;
		Point p2;
		Point p3;

		Rectangle rd = this.getBounds();
		Rectangle ro = o.getBounds();

		p3 = new Point((int) rd.getCenterX(), (int) rd.getCenterY());

		if (direction == "UP") {

			p1 = new Point((int) rd.getMinX(), (int) rd.getMinY());
			p2 = new Point((int) rd.getMaxX(), (int) rd.getMinY());

			if (ro.contains(p1) || ro.contains(p2) | ro.contains(p3)) {
				directions.remove(direction);
				change = true;
			}

		} else if (direction == "RIGHT") {

			p1 = new Point((int) rd.getMaxX(), (int) rd.getMinY());
			p2 = new Point((int) rd.getMaxX(), (int) rd.getMaxY());

			if (ro.contains(p1) || ro.contains(p2) | ro.contains(p3)) {
				directions.remove(direction);
				change = true;
			}
		} else if (direction == "DOWN") {
			p1 = new Point((int) rd.getMinX(), (int) rd.getMaxY());
			p2 = new Point((int) rd.getMaxX(), (int) rd.getMaxY());

			if (ro.contains(p1) || ro.contains(p2) | ro.contains(p3)) {
				directions.remove(direction);
				change = true;
			}
		} else if (direction == "LEFT") {
			p1 = new Point((int) rd.getMinX(), (int) rd.getMaxY());
			p2 = new Point((int) rd.getMinX(), (int) rd.getMinY());

			if (ro.contains(p1) || ro.contains(p2) | ro.contains(p3)) {
				directions.remove(direction);
				change = true;
			}
		}

		if (change)
			direction = directions.get((int) (directions.size() * (Math
					.random())));

	} // changeDirection

	public void move() {
		t1.start();

		if (direction.equals("UP")) {
			direction = "UP";
			y -= SPEED_DEMON;

		} else if (direction.equals("RIGHT")) {
			direction = "RIGHT";
			x += SPEED_DEMON;

		} else if (direction.equals("DOWN")) {
			direction = "DOWN";
			y += SPEED_DEMON;
		} else if (direction.equals("LEFT")) {
			direction = "LEFT";
			x -= SPEED_DEMON;

		}

		if (x >= x_max - 4) {
			direction = "LEFT";

		} else if (x <= 4) {
			direction = "RIGHT";
		} else if (y >= y_max) {
			direction = "UP";

		} else if (y <= 4) {
			direction = "DOWN";
		}

	}

	public Image getImage() {

		if (sex == 1) {

			if (direction == "LEFT")
				image = iconspriteMaleLEFT.get(icon_mouve);
			else if (direction == "RIGHT")
				image = iconspriteMaleRIGHT.get(icon_mouve);
			else if (direction == "UP")
				image = iconspriteMaleUP.get(icon_mouve);
			else if (direction == "DOWN")
				image = iconspriteMaleDOWN.get(icon_mouve);
			else if (direction == "MORTO")
				image = iconspriteDEAD.get(seq_dead);

		} else {
			if (direction == "LEFT")
				image = iconspriteFemaleLEFT.get(icon_mouve);
			else if (direction == "RIGHT")
				image = iconspriteFemaleRIGHT.get(icon_mouve);
			else if (direction == "UP")
				image = iconspriteFemaleUP.get(icon_mouve);
			else if (direction == "DOWN")
				image = iconspriteFemaleDOWN.get(icon_mouve);
			else if (direction == "MORTO")
				image = iconspriteDEAD.get(seq_dead);
		}

		return image;

	}// getImage

}// Demon
