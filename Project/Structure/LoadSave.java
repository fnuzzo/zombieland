package Project.Structure;
/**
 * @author Fabrizio Nuzzo
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class LoadSave {

	/**
	 */
	ObjectOutputStream output = null;
	/**
	 */
	ObjectInputStream ois = null;

	public int save(Board board, File file) {

		int result = 0;
		try {
			if (!file.getName().contains("dat"))
				result = 1;
			
			else {
				output = new ObjectOutputStream(new FileOutputStream(file));

				output.writeObject(board);
				output.close();
				output.flush();
				result = 0;
			}
		} catch (FileNotFoundException e) {

			System.out.println("save eccezione");

		//	e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(board, this,
					"Impossible to save the file " + file.toString(), 0);
			result = 1;
			//e.printStackTrace();
		}catch (Exception e){}

		return result;
	}

	public Board load(File f) {

		Board board = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(f));

			board = (Board) ois.readObject();

			ois.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(board, this,
					"Impossible to load the file ", 0);
			e.printStackTrace();
		} 

		return board;
	}

}
