/*
 * Klasa głównych operacji programu
 */
package data;

import java.util.List;

import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class MainDataFunctions {
    
    private static Logger logger = Logger.getLogger(MainDataFunctions.class);
    
    /**
     * Tworzenie histogramów
     */
    
    public void createHistograms()
    {
        logger.debug("Tworzymy histogramy wczytanych danych");
        DataOperations dataO = new DataOperations();
        dataO.createHistogramsOfColumns(dataO.read());
    }
    
    /**
     * Główne czytanie danych z pliku .csv podczas obsługi programu
     * @return 
     */
    
    public List<PlayerStat> readData()
    {
        logger.debug("Wczytujemy dane");
        DataOperations dataO = new DataOperations();
        return dataO.reduceData(dataO.read());
    }
    
    /**
     * Czytanie pojedynczej kolumny, której numer podajemy w parametrze
     * @param numberOfColumn 
     */
    
    public void readColumn(Integer numberOfColumn)
    {
        logger.debug("Tworzymy dane kolumny: " + numberOfColumn);
        DataOperations dataO = new DataOperations();
        dataO.readColumn(numberOfColumn);
    }
    
    
    
}
