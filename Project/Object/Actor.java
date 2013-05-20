package Project.Object;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;




/**
 * @author Fabrizio Nuzzo
 *
 */
public abstract class Actor implements Serializable {

	private static final long serialVersionUID = 1L;

	
	public final String FILE_SEPARATOR = System.getProperty ( "file.separator");
	
	/**
	 */
	protected int dx;
	/**
	 */
	protected int dy;
	/**
	 */
	protected int x;
	/**
	 */
	protected int y;
	/**
	 */
	protected int width;
	/**
	 */
	protected int height;
	/**
	 */
	protected int x_max = 610;
	/**
	 */
	protected int y_max = 440;
	/**
	 */
	protected String direction;
	/**
	 */
	protected String icons[];
	/**
	 */
	protected boolean visibile = true;
	/**
	 */
	String imagedir = "Project"+FILE_SEPARATOR +"img"+FILE_SEPARATOR;

	/**
	 */
	transient protected ImageIcon ii;
	/**
	 */
	transient protected Image image;
	/**
	 */
	transient protected ArrayList<Image> iconspriteMaleUP = new ArrayList<Image>();
	/**
	 */
	transient protected ArrayList<Image> iconspriteMaleRIGHT = new ArrayList<Image>();
	/**
	 */
	transient protected ArrayList<Image> iconspriteMaleDOWN = new ArrayList<Image>();
	/**
	 */
	transient protected ArrayList<Image> iconspriteMaleLEFT = new ArrayList<Image>();
	/**
	 */
	transient protected ArrayList<Image> iconspriteFemaleUP = new ArrayList<Image>();
	/**
	 */
	transient protected ArrayList<Image> iconspriteFemaleRIGHT = new ArrayList<Image>();
	/**
	 */
	transient protected ArrayList<Image> iconspriteFemaleDOWN = new ArrayList<Image>();
	/**
	 */
	transient protected ArrayList<Image> iconspriteFemaleLEFT = new ArrayList<Image>();
	/**
	 */
	transient protected ArrayList<Image> iconsprite = new ArrayList<Image>();
	/**
	 */
	transient protected ArrayList<Image> iconspriteDEAD = new ArrayList<Image>();
	/**
	 */
	transient protected ArrayList<Image> iconspriteWIN = new ArrayList<Image>();

	/**************************************** GETTER AND SETTER ***********************************/

	public Rectangle getBounds() {

		return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));

	}

	/**
	 * @return
	 */
	public int getX() {

		return x;
	}

	/**
	 * @return
	 */
	public int getY() {

		return y;
	}

	/**
	 * @return
	 */
	public Image getImage() {
		return image;
	}

	public void createSpritesObject() {
		iconsprite = new ArrayList<Image>();

		for (int i = 0; i < icons.length; i++) {
			ii = createImageIcon(icons[i]);
			image = ii.getImage();

			iconsprite.add(image);

		}

		width = image.getHeight(null);
		height = image.getWidth(null);

	}

	public void createSpritesActor() {

		iconspriteMaleUP = new ArrayList<Image>();
		iconspriteMaleRIGHT = new ArrayList<Image>();
		iconspriteMaleDOWN = new ArrayList<Image>();
		iconspriteMaleLEFT = new ArrayList<Image>();
		iconspriteFemaleUP = new ArrayList<Image>();
		iconspriteFemaleRIGHT = new ArrayList<Image>();
		iconspriteFemaleDOWN = new ArrayList<Image>();
		iconspriteFemaleLEFT = new ArrayList<Image>();
		iconsprite = new ArrayList<Image>();
		iconspriteDEAD = new ArrayList<Image>();
		iconspriteWIN = new ArrayList<Image>();

		for (int i = 0; i < icons.length; i++) {
			ii = createImageIcon(icons[i]);
			image = ii.getImage();

			if (icons[i].contains("WalterRight")
					|| icons[i].contains("zombiMaler"))
				iconspriteMaleRIGHT.add(image);

			else if (icons[i].contains("WalterUp")
					|| icons[i].contains("zombiMaleu"))
				iconspriteMaleUP.add(image);
			else if (icons[i].contains("WalterDown")
					|| icons[i].contains("zombiMaled"))
				iconspriteMaleDOWN.add(image);
			else if (icons[i].contains("WalterLeft")
					|| icons[i].contains("zombiMalel"))
				iconspriteMaleLEFT.add(image);
			else if (icons[i].contains("zombiFemaler"))
				iconspriteFemaleRIGHT.add(image);
			else if (icons[i].contains("zombiFemaleu"))
				iconspriteFemaleUP.add(image);
			else if (icons[i].contains("zombiFemaled"))
				iconspriteFemaleDOWN.add(image);
			else if (icons[i].contains("zombiFemalel"))
				iconspriteFemaleLEFT.add(image);
			else if (icons[i].contains("Death"))
				iconspriteDEAD.add(image);
			else if (icons[i].contains("Win"))
				iconspriteWIN.add(image);
		}

		width = image.getHeight(null);
		height = image.getWidth(null);

	}

	ImageIcon createImageIcon(String file) {

		try {

			return new ImageIcon(getClass().getClassLoader().getResource(
					imagedir + file));

		} catch (Exception e) {

			return new ImageIcon("Null");
		}
	}

}