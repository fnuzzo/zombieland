package Project.Object;
/**
 * @author Fabrizio Nuzzo
 *
 */
public class Options {

	/**
	 */
	private int up = 38;
	/**
	 */
	private int right = 39;
	/**
	 */
	private int down = 40;
	/**
	 */
	private int left = 37;
	/**
	 */
	private int shot = 32;
	/**
	 */
	private int bullet = -1;
	/**
	 */
	private int zombie = 20;
	/**
	 */
	private int obstacles = 10;

	/**
	 * @return
	 */
	public int getShot() {
		return shot;
	}

	/**
	 * @param  shot
	 */
	public void setShot(int shot) {
		this.shot = shot;
	}

	/**
	 * @return
	 */
	public int getObstacles() {
		return obstacles;
	}

	/**
	 * @param  obstacles
	 */
	public void setObstacles(int obstacles) {
		this.obstacles = obstacles;
	}

	/**
	 * @return
	 */
	public int getUp() {
		return up;
	}

	/**
	 * @param  up
	 */
	public void setUp(int up) {
		this.up = up;
	}

	/**
	 * @return
	 */
	public int getRight() {
		return right;
	}

	/**
	 * @param  right
	 */
	public void setRight(int right) {
		this.right = right;
	}

	/**
	 * @return
	 */
	public int getDown() {
		return down;
	}

	/**
	 * @param  down
	 */
	public void setDown(int down) {
		this.down = down;
	}

	/**
	 * @return
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * @param  left
	 */
	public void setLeft(int left) {
		this.left = left;
	}

	/**
	 * @return
	 */
	public int getBullet() {
		return bullet;
	}

	/**
	 * @param  bullet
	 */
	public void setBullet(int bullet) {
		this.bullet = bullet;
	}

	/**
	 * @return
	 */
	public int getZombie() {
		return zombie;
	}

	/**
	 * @param  zombie
	 */
	public void setZombie(int zombie) {
		this.zombie = zombie;
	}

}
