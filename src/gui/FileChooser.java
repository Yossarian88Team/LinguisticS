/*
 * Projekt nasze JFileChoosera
 */
package gui;

/**
 *
 * @author Ariel
 */

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

public class FileChooser {
    
    	private static Logger logger = Logger.getLogger(FileChooser.class);

	/**
	 * FileChooser javowy
	 */
	private JFileChooser filechooser;

	/**
	 * Konstruktor domyślny
	 */
	public FileChooser() {
		logger.debug("Tworzymy JFileChoosera");
		filechooser = new JFileChooser();
	}

	/**
	 * Metoda zwraca path do pliku *.txt, który wskazał użytkownik aplikacji.
	 */
	public String getPathToTxtFile() {
		logger.debug("Rozpoczynamy dzialanie JFileChoosera, aby pobrac sciezke do pliku *.txt");
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Text files", "txt");
		filechooser.setFileFilter(filter);
		filechooser.setDialogTitle("Wskaż plik *.txt");
		int returnVal = filechooser.showOpenDialog(null);
		String inputPath = null;
		if (returnVal == JFileChooser.OPEN_DIALOG) {
			inputPath = filechooser.getSelectedFile().getAbsolutePath();
			if (!inputPath.endsWith(".txt")) {
				inputPath += ".txt";
			}
			logger.info("Wskazany został plik: " + inputPath);
		}
		return inputPath;
	}
        
        /**
	 * Metoda zwraca path do pliku *.txt, który wskazał użytkownik aplikacji.
	 */
        
        public String getPathToXmlFile() {
		logger.debug("Rozpoczynamy dzialanie JFileChoosera, aby pobrac sciezke do pliku *.xml");
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Xml files", "xml");
		filechooser.setFileFilter(filter);
		filechooser.setDialogTitle("Wskaż plik *.xml");
		int returnVal = filechooser.showOpenDialog(null);
		String inputPath = null;
		if (returnVal == JFileChooser.OPEN_DIALOG) {
			inputPath = filechooser.getSelectedFile().getAbsolutePath();
			if (!inputPath.endsWith(".xml")) {
				inputPath += ".xml";
			}
			logger.info("Wskazany został plik: " + inputPath);
		}
		return inputPath;
	}
    
}
