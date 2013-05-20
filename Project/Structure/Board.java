package Project.Structure;
/**
 * @author Fabrizio Nuzzo
 *
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import Project.Object.Bullet;
import Project.Object.Demon;
import Project.Object.Obstacle;
import Project.Object.Options;
import Project.Object.Pistolero;

public class Board extends JPanel implements ActionListener, Serializable {

	private static final long serialVersionUID = 1L;

	
	
	public final String FILE_SEPARATOR = System.getProperty ( "file.separator");
	/**
	 */
	private Pistolero pistolero;
	/**
	 */
	private Demon d;

	/**
	 */
	private int width;
	/**
	 */
	private int height;
	/**
	 */
	private ArrayList<Obstacle> obstacles;
	/**
	 */
	private ArrayList<Demon> demons;
	/**
	 */
	private ArrayList<Bullet> ms;
	/**
	 */
	private boolean beckground = false;
	/**
	 */
	private int speedDemon = 2;
	/**
	 */
	private Dimension dimension;
	/**
	 */
	private int demonson = 0;
	/**
	 */
	private int count = 0;
	/**
	 */
	private int DEMON_NUMBRE = 10;
	/**
	 */
	private int demon_live = 0;
	/**
	 */
	private int OBSTACLE_NUMBRE = 10;
	/**
	 */
	private Timer t1;
	/**
	 */
	private Timer timer;

	/**
	 */
	transient private BufferedImage immagine;

	public Board() {
		super(true); // crea un JPanel con doubleBuffered true
						// //isDoubleBuffered();

		setPreferredSize(new Dimension(640, 480));
		addKeyListener(new Adapter());
		setFocusable(true);

		pistolero = new Pistolero();
		
		obstacles = new ArrayList<Obstacle>();
		demons = new ArrayList<Demon>();
		ms = new ArrayList<Bullet>();

		loadBeckground();
		startTimers();

		pistolero.setBoard(this);
	}// Board

	/**
	 * GETTER AND SETTER
	 */

	public int getSpeedDemon() {
		return speedDemon;
	}

	/**
	 * @return
	 */
	public Timer getTimer() {
		return timer;
	}

	public void setStateBeck() {
		beckground = false;
	}
	
	public int getSpeedPistolero() {
		return pistolero.getSPEED_PISTOLERO();

	}

	public void setOption(Options o) {

		pistolero.setUp_key(o.getUp());
		pistolero.setRight_key(o.getRight());
		pistolero.setDown_key(o.getDown());
		pistolero.setLeft_key(o.getLeft());
		pistolero.setShoot_key(o.getShot());
		pistolero.setCaricator(o.getBullet());
		DEMON_NUMBRE = o.getZombie();
		OBSTACLE_NUMBRE = o.getObstacles();

		this.popola();

	}

	/************************* METHOD BOARD ****************************************/

	private void loadBeckground() {
		System.out.println("Project"+FILE_SEPARATOR+"img"+FILE_SEPARATOR+"forest.jpg");
		try {
			this.immagine = ImageIO.read(getClass().getClassLoader()
					.getResource("Project"+FILE_SEPARATOR+"img"+FILE_SEPARATOR+"forest.jpg"));

			width = immagine.getWidth();
			height = immagine.getHeight();
			dimension = new Dimension(width, height);

		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	public void pause() {

		timer.stop();
		if(t1.isRunning())
			t1.stop();
		
	}

	public void restart() {

		timer.start();
		if (!t1.isRunning())
		t1.start();
	}

	public void loadGame() {

		addKeyListener(new Adapter());
		loadBeckground();
		pistolero.createSpritesActor();
		pistolero.reload();
		this.startTimers();

		for (int s = 0; s < demons.size(); s++) {
			d = demons.get(s);
			d.createSpritesActor();
			d.starTimer();
		}

		for (int s = 0; s < ms.size(); s++) {
			Bullet b = ms.get(s);
			b.createSpritesObject();
		}

		for (int s = 0; s < obstacles.size(); s++) {
			Obstacle o = obstacles.get(s);
			o.createSpritesObject();

		}

	}

	public void changeSpeedDemon(int speed) {
		speedDemon = speed;
		d.setSpeedDemon(speed);

	}

	public void changeSpeedPistolero(int speed) {
		pistolero.setSpeedPistolero(speed);
	}
	
	private void startTimers() {

		timer = new Timer(80, this);
		
		t1 = new Timer(5000, new ActionListener() {
			
			
			
			
			public void actionPerformed(ActionEvent e) {
				
				int a = (DEMON_NUMBRE -demon_live);
				if (a>=3) 
					 a = 3;
				else if (a<3) 
						a= (DEMON_NUMBRE - demon_live);
					
				else if (a==0){
					a=demons.size();
					t1.stop();
				}
						for (int i = 0; i < a; i++) 
							{
								 d = new Demon();
								while (!obstacleControlCollission(d)) {
									d = new Demon();
									Toolkit.getDefaultToolkit().beep();
								}
								d.setSex();
								demons.add(d);
								demon_live++;
							}
								
					
			}
		});
	}

	private void popola() {

		while (obstacles.size() < OBSTACLE_NUMBRE) {
			Obstacle o = new Obstacle();
			if (obstacleControlCollission(o))
				obstacles.add(o);

		}
		int dn = 0;
		if (DEMON_NUMBRE >= 5) 
			{
				dn = 5;
			} 
		else
			dn = DEMON_NUMBRE;

		while (demons.size() < dn) {

				 d = new Demon();
				if (obstacleControlCollission(d)) {
					d.setSpeedDemon(speedDemon);
					demons.add(d);
					demon_live++;
				}
			}
		t1.start();
	}

	private boolean obstacleControlCollission(Demon d) {
		boolean ok = true;
		
		
		for (int s = 0; s < obstacles.size(); s++) {
			Obstacle o = obstacles.get(s);

			Rectangle r1 = d.getBounds();
			Rectangle r2 = o.getBounds();
			if (r1.intersects(r2))
				ok = false;

		}
		for (int s = 0; s < demons.size(); s++) {
			Demon d1 = demons.get(s);
			
			if (!d1.equals(d)) {
				Rectangle r1 = d.getBounds();
				Rectangle r2 = d1.getBounds();

				if (r1.intersects(r2))
					ok = false;

			}

		}

		return ok;

	}// obstacleControlCollission

	private boolean obstacleControlCollission(Obstacle o1) {
		boolean ok = true;

		for (int s = 0; s < obstacles.size(); s++) {
			Obstacle o2 = obstacles.get(s);
			if (!o1.equals(o2)) {
				Rectangle r1 = o1.getBounds();
				Rectangle r2 = o2.getBounds();

				if (r1.intersects(r2))
					ok = false;

			}
		}
		return ok;

	}// obstacleControlCollission

	public void actionPerformed(ActionEvent e) {
		if (demon_live < DEMON_NUMBRE) {
			for (int i = 0; i < demonson; i++) {

				while (!this.obstacleControlCollission(d)) {
					d = new Demon();
					Toolkit.getDefaultToolkit().beep();
				}
				d.setSex();
				demons.add(d);
				demon_live++;
				demonson = 0;

			}

		} else
			demonson = 0;

		pistolero.move();

		ms = pistolero.getBullets();

		for (int i = 0; i < demons.size(); i++) {
			d = demons.get(i);
			d.setSpeedDemon(speedDemon);
			if (d.isDead() == 0)
				d.move();
			else if (d.isDead() == 2)
				demons.remove(d);

		}

		for (int i = 0; i < ms.size(); i++) {
			Bullet m = ms.get(i);
			if (m.isVisible())
				m.move();
			else
				ms.remove(m);
		}

		//if (demons.size()==0)
		if (count==DEMON_NUMBRE)
			pistolero.winner();
		else
			collisionsDetect();

		this.repaint();

	}// actionPerformed

	private class Adapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			pistolero.keyReleased(e);

		}

		public void keyPressed(KeyEvent e) {
			pistolero.keyPressed(e);

		}

	}// Adapter

	// sovrascrivi il metodo paintComponent passandogli l'immagine partendo
	// dalle coordinate 0,0 senza usare un ImageObserver (null)
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;

		if (!beckground) {
			Graphics gs = immagine.createGraphics();

			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle o = obstacles.get(i);
				gs.drawImage(o.getImage(), o.getX(), o.getY(), this);
			}
			beckground = true;

		} else {

			g.drawImage(immagine, 0, 0, null);

			if (pistolero.isVisibile()) {
				g2d.drawImage(pistolero.getImage(), pistolero.getX(), pistolero
						.getY(), this);

				for (int i = 0; i < ms.size(); i++) {
					Bullet m = ms.get(i);
					if (m.isVisible())
						g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
				}
			}

			for (int j = 0; j < demons.size(); j++) {
				d = demons.get(j);
				g2d.drawImage(d.getImage(), d.getX(), d.getY(), this);
			}

		}

		String msg = "Bullets = " + pistolero.getcaricator();
		Font small = new Font("Helvetica", Font.BOLD, 12);

		if (pistolero.getcaricator() == -1) {
			g.setColor(Color.BLACK);
			msg = "Bullets = inf";
		}

		else if (pistolero.getcaricator() < 10)
			g.setColor(Color.RED);
		else
			g.setColor(Color.BLACK);

		g.setFont(small);
		g.drawString(msg, 5, 15);
		String demon = "Zombies: " + String.valueOf(DEMON_NUMBRE - count);
		g.drawString(demon, 105, 15);
		String killed = "Eliminated: " + String.valueOf(count);
		g.drawString(killed, 205, 15);

		String life = "Life: " + String.valueOf(pistolero.getLife());
		g.drawString(life, 315, 15);

		if (pistolero.getLife() <= 0) {
			
			msg = "Game Over \n Powered By Fabrizio Nuzzo";
			small = new Font("Helvetica", Font.BOLD, 20);
			FontMetrics metr = this.getFontMetrics(small);
			g.setFont(small);
			g.drawString(msg, (dimension.width - metr.stringWidth(msg)) / 2,
					dimension.height / 2);
		}

		
		  if(DEMON_NUMBRE == count){ 
			 msg ="Walter The World is save"; 
			 small = new Font("Helvetica", Font.BOLD, 20); 
			 FontMetrics metr =this.getFontMetrics(small);
			 g.setColor(Color.white); g.setFont(small); g.drawString(msg,
			(dimension.width - metr.stringWidth(msg)) / 2, dimension.height / 2);
			 //timer.stop();	  
		  
		  }
		 

		Toolkit.getDefaultToolkit().sync();
		g.dispose();

	}// PaintComponent

	public void collisionsDetect() {

		Rectangle r1 = pistolero.getBounds();

		for (int j = 0; j < obstacles.size(); j++) {
			Obstacle o = obstacles.get(j);
			Rectangle r2 = o.getBounds();

			if (r1.intersects(r2)) {
				pistolero.changeDirection(o);

			}

		}

		for (int i = 0; i < ms.size(); i++) {
			Bullet b = ms.get(i);
			Rectangle r3 = b.getBounds();

			for (int j = 0; j < demons.size(); j++) {
				 d = demons.get(j);
				Rectangle r4 = d.getBounds();

				if (r3.intersects(r4) && d.isDead() != 1) {
					d.setdead();
					ms.remove(b);
					count++;
				}
			}

			for (int j = 0; j < obstacles.size(); j++) {
				Obstacle o = obstacles.get(j);
				Rectangle r5 = o.getBounds();

				if (r3.intersects(r5)) {

					ms.remove(b);
				}
			}

		}
		for (int s = 0; s < demons.size(); s++) {
			 d = demons.get(s);
			Rectangle r6 = d.getBounds();

			if (r6.intersects(r1) && d.isDead() == 0
					&& pistolero.getLife() == 0) {
				pistolero.setLife(0);

			} else if (r6.intersects(r1) && d.isDead() == 0
					&& pistolero.getLife() >= 1) {

				pistolero.setLife(pistolero.getLife() - 1);
				pistolero.ferito();

			}

			for (int j = 0; j < obstacles.size(); j++) {

				Obstacle o = obstacles.get(j);
				Rectangle r7 = o.getBounds();
				if (r6.intersects(r7)) {
					d.changeDirection(o);

				}

			}
			for (int x = 0; x < demons.size(); x++) {
				Demon d2 = demons.get(x);
				Rectangle r8 = d2.getBounds();

				if (r6.intersects(r8) && !d.equals(d2)) {
					if (d.getSex() != d2.getSex() && !d.isReproduction()) {
						demonson += 1;
						d.setReproduction();

					} else if (d.getSex() == 1 && d2.getSex() == 1
								&& d.isDead() == 0 && d2.isDead() == 0) {
																		

						d.setdead();
						count++;
					}

					d.changeDirection();
					d2.changeDirection();

				}
			}

		}
	}// collisionsDetect

}
