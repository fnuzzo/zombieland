package Project.Object;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**
 * @author Fabrizio Nuzzo
 *
 */
public class Obstacle extends Actor implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 */
	ArrayList<Obstacle> Obstacles = new ArrayList<Obstacle>();

	/**
	 */
	private Random r;
	/**
	 */
	private int width;
	/**
	 */
	private int height;
	/**
	 */
	private int id;

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param  id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public Obstacle() {

		r = new Random();
		icons = new String[3];
		visibile = true;

		// Add Sprites
		icons[0] = "bid.png";
		icons[1] = "cop.png";
		icons[2] = "cas.png";

		createSpritesObject();

		this.setX();
		this.setY();
	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));

	}

	public Image getImage() {

		Image image = null;
		int i = r.nextInt(3);

		image = iconsprite.get(i);
		return image;
	}

	public void setX() {
		x = r.nextInt(x_max - 30);

	}

	public void setY() {
		y = r.nextInt(y_max - 30);
	}

	/**
	 * @return
	 */
	public int getWidth() {

		return width;

	}

	/**
	 * @return
	 */
	public int getHeight() {

		return height;

	}

}
