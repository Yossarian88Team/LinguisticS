/*
 * Klasa obsługująca zapisywanie plików tekstowych.
 * 
 */
package io;

/**
 *
 * @author Ariel
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

public class TxtIO {
    
    	private static Logger logger = Logger
			.getLogger(TxtIO.class);
        
        /**
         * Zapis wartości z buf do pliku tekstowego o ścieżce path
         * @param path
         * @param buf 
         */
        
        public static void writeTextFile(String path, String tmpString) {
		logger.debug("Rozpoczynamy zapis do pliku: " + path);
		try {
			FileWriter outFile = new FileWriter(path);
			PrintWriter out = new PrintWriter(outFile);
			out.println(tmpString);
			out.close();
		} catch (IOException e) {
			logger.error("Blad w trakcie zapisu do pliku," + e);
		}
	}
    
}
