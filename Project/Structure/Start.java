package Project.Structure;
/**
 * @author Fabrizio Nuzzo
 *
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import Project.Object.Options;

public class Start extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/**
	 */
	Board board;
	/**
	 */
	Configuration_panel confPanel;
	/**
	 */
	Options ops;
	/**
	 */
	Container contentPane;
	/**
	 */
	LoadSave loadsave;

	/**
	 */
	JMenuBar menuBar;
	/**
	 */
	JMenu menu;
	/**
	 */
	JMenuItem menuItem1;
	/**
	 */
	JMenuItem menuItem2;
	/**
	 */
	JMenuItem menuItem3;
	/**
	 */
	JMenuItem menuItem4;
	/**
	 */
	JSlider js;

	/**
	 */
	JPanel sliders;//

	/**
	 */
	private File f = null;
	/**
	 */
	private JFileChooser fileChooser;

	public Start() {

		// JFRAME IMPOSTAZIONI

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setResizable(false);
		this.setTitle(" The Zombie Land ");
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		loadsave = new LoadSave();
		fileChooser = new JFileChooser();

		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
				"File DAT", "dat"));

		contentPane = getContentPane();
		contentPane.add(menubar(), BorderLayout.NORTH);

		confPanel = new Configuration_panel(this);
		contentPane.add(confPanel, BorderLayout.SOUTH);
		this.pack();

	}

	private void newConf() {

		contentPane.removeAll();

		confPanel = new Configuration_panel(this);
		contentPane.add(menubar(), BorderLayout.NORTH);
		contentPane.add(confPanel, BorderLayout.SOUTH);

		this.pack();
	}

	public void newGame(Options o) {

		contentPane.removeAll();

		contentPane.add(menubar(), BorderLayout.NORTH);

		board = new Board();
		contentPane.add(board, BorderLayout.SOUTH);

		board.setVisible(true);
		board.requestFocus();
		board.setOption(o);
		board.restart();
		sliders = new JPanel();
		sliders.add(CreateJSliderPistolero(board));
		sliders.add(CreateJSliderDemons(board));

		contentPane.add(sliders, BorderLayout.CENTER);

		this.pack();

	}

	private JSlider CreateJSliderDemons(final Board board) {

		js = new JSlider(0, 10);

		js.setMajorTickSpacing(1);
		js.setPreferredSize(new Dimension(300, 60));
		js.setPaintTicks(true);
		js.setPaintLabels(true);
		js.setValue(board.getSpeedDemon());
		js.setBorder(BorderFactory.createTitledBorder(" Deamon "));
		js.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent event) {
				int d = ((JSlider) event.getSource()).getValue();
				board.changeSpeedDemon(d);
				board.requestFocus();
			}
		});

		return js;
	}

	private JSlider CreateJSliderPistolero(final Board board) {

		js = new JSlider(0, 10);

		js.setMajorTickSpacing(1);
		js.setPaintTicks(true);
		js.setPaintLabels(true);
		js.setPreferredSize(new Dimension(300, 60));
		js.setValue(board.getSpeedPistolero());
		js.setBorder(BorderFactory.createTitledBorder(" Pistolero "));
		js.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent event) {
				int t = ((JSlider) event.getSource()).getValue();
				board.changeSpeedPistolero(t);
				board.requestFocus();
			}
		});

		return js;
	}

	private JMenuBar menubar() {

		menuBar = new JMenuBar();

		menu = new JMenu("File");
		menuBar.add(menu);

		menuItem1 = new JMenuItem("New Game");
		menuItem1.addActionListener(this);
		menuItem1.setActionCommand("new");

		menuItem2 = new JMenuItem("Exit");
		menuItem2.addActionListener(this);
		menuItem2.setActionCommand("exit");

		menuItem3 = new JMenuItem("Save");
		menuItem3.addActionListener(this);
		menuItem3.setActionCommand("save");

		menuItem4 = new JMenuItem("Load");
		menuItem4.addActionListener(this);
		menuItem4.setActionCommand("load");

		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menu.add(menuItem4);

		return menuBar;
	}

	public void actionPerformed(ActionEvent e) {
		if ("exit".equals(e.getActionCommand()))
			System.exit(1);

		else if ("new".equals(e.getActionCommand()))
			newConf();

		else if ("save".equals(e.getActionCommand())) {
			this.board.pause();
			fileChooser.showSaveDialog(this);

			if (loadsave.save(this.board, fileChooser.getSelectedFile()) == 1)
				JOptionPane.showMessageDialog(board, "No extension ",
						"No extension ", 0);
			
			this.board.restart();
		}

		else if ("load".equals(e.getActionCommand())) {
			if(board.getTimer().isRunning())
				board.pause();
			int response = fileChooser.showOpenDialog(this);

			if (response == JFileChooser.APPROVE_OPTION) {
				f = fileChooser.getSelectedFile();
				Board board = new Board();
				board = loadsave.load(f);

				contentPane.removeAll();
				contentPane.add(menubar(), BorderLayout.NORTH);

				sliders = new JPanel();
				sliders.add(CreateJSliderPistolero(board));
				sliders.add(CreateJSliderDemons(board));

				contentPane.add(sliders, BorderLayout.CENTER);
				contentPane.add(board, BorderLayout.SOUTH);

				board.setStateBeck();
				board.setVisible(true);
				board.loadGame();
				board.requestFocus();
				board.restart();
			}

			this.pack();

		}

	}

	public static void main(String[] args) {

		// new ProvaStart();

		// Schedule a job for event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Start();
			}
		});

	}

}
