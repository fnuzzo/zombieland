package Project.Structure;
/**
 * @author Fabrizio Nuzzo
 *
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Project.Object.Options;

public class Configuration_panel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 */
	private String[] arg = { "UP", "RIGHT", "DOWN", "LEFT", "shoot",
			"numbre bullet", "numbre Zombies", "numbre obstacle" };
	
	/**
	 */
	Options options;

	/**
	 */
	private Start start;
	/**
	 */
	private boolean exception = false;

	/**
	 */
	JTextField jt1; // up
	/**
	 */
	JTextField jt2; // right
	/**
	 */
	JTextField jt3; // down
	/**
	 */
	JTextField jt4; // left
	/**
	 */
	JTextField jt5;// shoot
	/**
	 */
	JTextField jt6; // bullet
	/**
	 */
	JTextField jt7; // zombie
	/**
	 */
	JTextField jt8; // obstacle

	/**
	 */
	AscoltaActionListener aal;
	/**
	 */
	AscoltaMouseListener aml;
	/**
	 */
	AscoltaKeyListener akl;

	public Configuration_panel(Start start)

	{
		options = new Options();
		this.start = start;
		aal = new AscoltaActionListener();
		aml = new AscoltaMouseListener();
		akl = new AscoltaKeyListener();

		setLayout(new BorderLayout());

		add(option(), BorderLayout.CENTER);
		add(buttonOK(), BorderLayout.SOUTH);
		add(labelRIGHT(), BorderLayout.WEST);
		
		setFocusable(true);

	}

	private JTextArea labelRIGHT() {

		JTextArea jt = new JTextArea();
		jt.setEditable(false);
		jt.setText(" Le jeu consiste à conduire le soldat Walter vers \n"
					+" la liberation du monde des zombies hors de \n" +
					"controle à la recherche de chair humaine et \n" +
					"predisposés à la reproduction. \n"
					+"Les zombies sont des deux sexes et peuvent \n" +
					 "apparaitre à tout moment.\n" +
					 "Si deux zombies de sexe opposé se rencontrent,\n " +
					 "un nouveau zombie apparait dans le champ,\n" 
					+"mais la possibilité des zombies femelles \n" +
					"de se reproduire est d'une fois seule pour \n" +
					"la durée de la partie. Si par contre deux hommes \n" +
					"zombies entrent en collision, un seul survit.\n"
					+"Walter peut étre blessé trois fois avant de mourir.\n" +
					"Il est possibile de mettre en pause le jeu à tout moment \n"
					+"à travers la touche ENTRER, sauver la partie dans un \n" +
					"fichier .dat et le télècharger plus tard.\n"
					+"A' chaque nouveau jeu on donne la possibilité de choisir \n" +
					"les boutons à utiliser pour actionner Walter,\n"
					+"choisir le nombre maximum de zombies dans la \n" +
					"zone et le nombre de munitions disponibles \n" +
					"(infinies est un choix possible).\n"
					+"Toutes les informations pertinentes figurent dans \n" +
					"une barre positionné en haut du cadre.\n");
		jt.setBorder(BorderFactory.createTitledBorder("Description"));
		jt.setEditable(false);
		return jt;
	}

	private JButton buttonOK() {

		JButton jb = new JButton("OK");
		jb.addActionListener(aal);

		return jb;
	}

	private JPanel option() {

		JPanel jp = new JPanel(new GridLayout(8, 2));
		jp.setBorder(BorderFactory.createTitledBorder("OPTION"));

		jt1 = new JTextField(" " + KeyEvent.getKeyText(38));
		jt1.setName("jt1");
		jt1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jt1.setEditable(false);

		jt1.addMouseListener(aml);
		jt1.addKeyListener(akl);

		jt2 = new JTextField(" " + KeyEvent.getKeyText(39));
		jt2.setName("jt2");
		jt2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jt2.setEditable(false);
		jt2.addMouseListener(aml);
		jt2.addKeyListener(akl);

		jt3 = new JTextField(" " + KeyEvent.getKeyText(40));
		jt3.setName("jt3");
		jt3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jt3.setEditable(false);
		jt3.addMouseListener(aml);
		jt3.addKeyListener(akl);

		jt4 = new JTextField(" " + KeyEvent.getKeyText(37));
		jt4.setName("jt4");
		jt4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jt4.setEditable(false);
		jt4.addMouseListener(aml);
		jt4.addKeyListener(akl);

		jt5 = new JTextField(" " + KeyEvent.getKeyText(32));
		jt5.setName("jt5");
		jt5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jt5.setEditable(false);
		jt5.addMouseListener(aml);
		jt5.addKeyListener(akl);

		jt6 = new JTextField("infinite");
		jt6.setName("jt6");
		jt6.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		jt7 = new JTextField("20");
		jt7.setName("jt7");
		jt7.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		jt8 = new JTextField("10");
		jt8.setName("jt8");
		jt8.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		jp.add(create_JLabel(arg[0]));
		jp.add(jt1);

		jp.add(create_JLabel(arg[1]));
		jp.add(jt2);

		jp.add(create_JLabel(arg[2]));
		jp.add(jt3);

		jp.add(create_JLabel(arg[3]));
		jp.add(jt4);

		jp.add(create_JLabel(arg[4]));
		jp.add(jt5);

		jp.add(create_JLabel(arg[5]));
		jp.add(jt6);

		jp.add(create_JLabel(arg[6]));
		jp.add(jt7);

		jp.add(create_JLabel(arg[7]));
		jp.add(jt8);

		return jp;
	}

	private JLabel create_JLabel(String arg) {

		JLabel jl = new JLabel(arg);
		jl.setHorizontalAlignment(JLabel.CENTER);

		return jl;
	}

	class AscoltaActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String c = e.getActionCommand();
			if (c.compareTo("OK") == 0) {
				exception = false;

				try {
					if (jt6.getText().equals("infinite"))
						options.setBullet(-1);
					else
						options.setBullet(Integer.parseInt(jt6.getText()));

					options.setZombie(Integer.parseInt(jt7.getText()));

					options.setObstacles(Integer.parseInt(jt8.getText()));
				} catch (NumberFormatException nfe) {
					exception = true;
					JOptionPane.showMessageDialog(start,
							"NumberFormatExecption", "Exception",
							JOptionPane.WARNING_MESSAGE);

				}
				if (!exception) {
					start.newGame(options);

				}

			}

		}
	}

	// Mouse Listener:
	class AscoltaMouseListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			Component a = e.getComponent();
			String n = a.getName();

			if (n.equals("jt1")) {
				jt1.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

			} else if (n.equals("jt2")) {
				jt2.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

			} else if (n.equals("jt3")) {
				jt3.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

			} else if (n.equals("jt4")) {
				jt4.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

			} else if (n.equals("jt5")) {

				jt5.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

			}

		}

	}// class AscoltaMouseListener

	class AscoltaKeyListener implements KeyListener {

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {

			Component c = e.getComponent();

			String n = c.getName();

			if (n.equals("jt1")) {
				jt1.setText(KeyEvent.getKeyText(e.getKeyCode()));
				options.setUp(e.getKeyCode());
				jt1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

			}

			else if (n.equals("jt2")) {
				jt2.setText(KeyEvent.getKeyText(e.getKeyCode()));
				options.setRight(e.getKeyCode());

				if (options.getUp() == options.getRight()) {
					JOptionPane.showMessageDialog(start, "duplicate key",
							"Exception", JOptionPane.WARNING_MESSAGE);
					jt2.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				} else
					jt2.setBorder(BorderFactory
							.createLineBorder(Color.GREEN, 2));

			}

			else if (n.equals("jt3")) {
				jt3.setText(KeyEvent.getKeyText(e.getKeyCode()));
				options.setDown(e.getKeyCode());

				if (options.getUp() == options.getDown()
						|| options.getDown() == options.getRight()) {
					JOptionPane.showMessageDialog(start, "duplicate key",
							"Exception", JOptionPane.WARNING_MESSAGE);
					jt3.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				} else
					jt3.setBorder(BorderFactory
							.createLineBorder(Color.GREEN, 2));

			}

			else if (n.equals("jt4")) {
				jt4.setText(KeyEvent.getKeyText(e.getKeyCode()));
				options.setLeft(e.getKeyCode());

				if (options.getLeft() == options.getUp()
						|| options.getLeft() == options.getRight()
						|| options.getLeft() == options.getDown()) {
					JOptionPane.showMessageDialog(start, "duplicate key",
							"Exception", JOptionPane.WARNING_MESSAGE);
					jt4.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				} else
					jt4.setBorder(BorderFactory
							.createLineBorder(Color.GREEN, 2));

			}

			else if (n.equals("jt5")) {
				jt5.setText(KeyEvent.getKeyText(e.getKeyCode()));
				options.setShot(e.getKeyCode());

				if (options.getShot() == options.getLeft()
						|| options.getShot() == options.getUp()
						|| options.getShot() == options.getRight()
						|| options.getShot() == options.getDown()) {
					JOptionPane.showMessageDialog(start, "duplicate key",
							"Exception", JOptionPane.WARNING_MESSAGE);
					jt5.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				} else
					jt5.setBorder(BorderFactory
							.createLineBorder(Color.GREEN, 2));

			}

		}
	}

}// Configuration_panel
