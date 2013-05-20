package Project.Object;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
/**
 * @author Fabrizio Nuzzo
 *
 */
public class Bullet extends Actor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 */
	String dir = "";
	/**
	 */
	private int BULLET_SPEED = 20;
	/**
	 */
	private boolean visible = true;

	/**
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param  visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Bullet(int x, int y, String dir, int SPEED_PISTOLERO) {

		BULLET_SPEED += SPEED_PISTOLERO + 3;

		this.dir = dir;
		icons = new String[2];
		// visibile = true;

		if (dir == "LEFT") {

			this.x = x - 30;
			this.y = y - 20;

		} else if (dir == "RIGHT") {

			this.x = x + 10;
			this.y = y - 20;

		} else if (dir == "UP") {

			this.x = x - 10;
			this.y = y - 40;
		} else {

			this.x = x - 10;
			this.y = y + 5;

		}

		icons[0] = "Projectile_orizontal.png";
		icons[1] = "Projectile_vertical.png";

		createSpritesObject();

	}

	public Image getImage() {
		if (dir == "LEFT")
			image = iconsprite.get(0);
		else if (dir == "RIGHT")
			image = iconsprite.get(0);
		else if (dir == "UP")
			image = iconsprite.get(1);
		else if (dir == "DOWN")
			image = iconsprite.get(1);

		return image;
	}

	public void move() {

		if (dir == "UP") {

			y -= BULLET_SPEED;
			if (y < 0)
				visibile = false;

		} else if (dir == "DOWN") {

			y += BULLET_SPEED;
			if (y > y_max)
				visibile = false;

		} else if (dir == "LEFT") {

			x -= BULLET_SPEED;
			if (x <= 0)
				visibile = false;

		} else if (dir == "RIGHT") {

			x += BULLET_SPEED;
			if (x >= x_max)
				visibile = false;
		}
	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));

	}

}// Bullet
