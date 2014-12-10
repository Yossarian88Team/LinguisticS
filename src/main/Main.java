
package main;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel;

import gui.MainView;

/**
 *
 * @author Ariel
 */
public class Main {
    
    	private static Logger logger = Logger.getLogger(Main.class);
        
        public static void main(String[] args) {
        
        	Runnable doWorkRunnable = new Runnable() {
			public void run() {
				logger.debug("Tworzymy GUI LookAndFeel");
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					UIManager.setLookAndFeel(new SubstanceNebulaBrickWallLookAndFeel());
				} catch (UnsupportedLookAndFeelException e) {
					logger.error("Mamy blad w tworzeniu okna", e);
				}
				logger.debug("Tworzymy glowne okno programu");
				MainView mainWindow = MainView.getMainWindow();
				logger.debug("Ustawiamy widocznosc");
				mainWindow.setVisible(true);
			}
		};
		logger.debug("STARTUJEMY");
		SwingUtilities.invokeLater(doWorkRunnable);
	}
    
}
