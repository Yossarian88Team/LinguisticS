/*
 * Klasa zajmująca się obsługą żądań płynących z okien aplikacji programu czyli pakietu gui
 */
package main;

import gui.MainView;
import gui.parts.*;
import data.MainDataFunctions;
import data.PlayerStat;
import java.util.*;
import fuzzycomponents.*;
import summarization.*;
import summarization.measures.ServiceOfMeasures;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import io.XmlReader;

import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class Worker {
    private static Logger logger = Logger.getLogger(Worker.class);
      
    //instancje używanych klas
    private static Worker instance = null;
    private MainView mainView;
    private static MainDataFunctions dataFunctions = null;
    
    // główna lista z danymi z bazy
    private List<PlayerStat> playersData = null;
    // tablica z wagami miar ( Uwaga 12-elementowa, pozycja 0 nie jest brana pod uwagę)
    private double[] wages;
    // lista stringów z zaznaczonymi podsumowaniami, które mają trafiać do finalnego okna
    public List<String> finalSummarizations = null;
    List<SummarizerTypeOne> listOfSummarizerOne = null;
    List<QualifierTypeOne> listOfQualifierOne = null;
    List<QuantifierTypeOne> listOfQuantifierOne = null;
    List<QuantifierTypeTwo> listOfQuantifierTwo = null;
    List<SummarizerInterface> listOfTaggedSummarizerOne = null;
    List<QualifierInterface> listOfTaggedQualifierOne = null;
    List<QuantifierTypeOne> listOfTaggedQuantifierOne = null;
    List<QuantifierTypeTwo> listOfTaggedQuantifierTwo = null;
    public TreeOfChoices treeOfChoices=null;
    

    
    Worker(){
        logger.debug("Tworzymy workera.");
        initializeWages();
        finalSummarizations=new ArrayList();
        //logger.debug("Po utworzeniu workera.");

    }
    
    private void initializeWages()
    {
        logger.debug("InicjalizujemyWagi");
        wages = new double[11];
        for (int i = 0; i < wages.length; i++) {
		wages[i] = 1.0d/11.0d;
	}
    }
    
    public static Worker getInstance() {
	if (instance == null) {
		instance = new Worker();
	}
	return instance;
    }

    public MainView getMainView() {
	return mainView;
    }
    
    public void setMainView(MainView mainView) {
	this.mainView = mainView;
	//przekazywanie sumaryzatorów, kwalifikatorów itd
    }

    public TreeOfChoices getTreeOfChoices() {
        return treeOfChoices;
    }

    public void setTreeOfChoices(TreeOfChoices treeOfChoices) {
        this.treeOfChoices = treeOfChoices;
    }
    
    
    
    /**
     * Tworzy listę główną z danymi
     */    
    public void readDataFromCsv()
    {
        logger.debug("Wczytujemy plik CSV");
        if (dataFunctions == null) {
            dataFunctions = new MainDataFunctions();
	}
        mainView.changeReadDataBtn();
        playersData=dataFunctions.readData();
        logger.debug("Wczytano poprawnie dane o " + playersData.size() + " zawodnikach.");
        
    }
    
    /**
     * Tworzy histogramy wszystkich kolumn z bazy
     */    
    public void createHistograms()
    {
        logger.debug("Tworzymy histogramy");
        if (dataFunctions == null) {
            dataFunctions = new MainDataFunctions();
	}
        //playersData=dataFunctions.readData();
        dataFunctions.createHistograms();
        for(SummarizerTypeOne summ: treeOfChoices.getTaggedSummarizers())
        {
            summ.createHistogram();
        }
        for(QuantifierTypeOne qua: treeOfChoices.getTaggedQuantifiersOne())
        {
            qua.createHistogram();
        }
        for(QuantifierTypeTwo qua: treeOfChoices.getTaggedQuantifiersTwo())
        {
            qua.createHistogram();
        }
        for(QualifierTypeOne qua: treeOfChoices.getTaggedQualifiers())
        {
            qua.createHistogram();
        }
        
    }
    
    /**
     * Tworzy histogramy wszystkich kolumn z bazy
     */    
    public void readChoices()
    {
        treeOfChoices=mainView.getTreeOfChoices();
        logger.debug("Wczytujemy dane do drzewa");
        if (dataFunctions == null) {
            dataFunctions = new MainDataFunctions();
	}
        //mainView.changeReadChoicesBtn();
        XmlReader xmlReader = new XmlReader();
        xmlReader.readElementsFromFile("elements.xml");
        this.listOfQualifierOne=xmlReader.getListOfQualifierOne();
        this.listOfQuantifierOne=xmlReader.getListOfQuantifierOne();
        this.listOfQuantifierTwo=xmlReader.getListOfQuantifierTwo();
        this.listOfSummarizerOne=xmlReader.getListOfSummarizerOne();
        treeOfChoices.erase();
        for(SummarizerTypeOne summarizer : listOfSummarizerOne)
        {
            treeOfChoices.addSummarizer(summarizer);
        }
        for(QualifierTypeOne qualifier : listOfQualifierOne)
        {
            treeOfChoices.addQualifier(qualifier);
        }        
        for(QuantifierTypeOne quantifier : listOfQuantifierOne)
        {
            treeOfChoices.addQuantifierOne(quantifier);
        }
        for(QuantifierTypeTwo quantifier : listOfQuantifierTwo)
        {
            treeOfChoices.addQuantifierTwo(quantifier);
        }
        treeOfChoices.refresh();
        
    }
    
    /**
     * Tworzy histogramy wszystkich kolumn z bazy
     */    
    public void generate()
    {
        logger.debug("Zaczynamy generowanie podsumowan.");
        if(treeOfChoices==null)
        {
            logger.debug("Uwaga ! Nie wczytano składników.");
            return;
        }
        if(dataFunctions==null)
        {
            logger.debug("Uwaga ! Nie wczytano danych z bazy.");
            return;
        }
        
        CreatingComplexQualifiers creatingQua = new CreatingComplexQualifiers();
        CreatingComplexSummarizers creatingSumm = new CreatingComplexSummarizers();
        
        
        this.listOfTaggedQuantifierOne=treeOfChoices.getTaggedQuantifiersOne();
        this.listOfTaggedQuantifierTwo=treeOfChoices.getTaggedQuantifiersTwo();
        switch(mainView.getQuaSpinner().getValue().hashCode()){
            case 1:
                this.listOfTaggedQualifierOne=creatingQua.create1ComplexQualifiers(treeOfChoices.getTaggedQualifiers());
                break;
            case 2:
                this.listOfTaggedQualifierOne=creatingQua.create2ComplexQualifiers(treeOfChoices.getTaggedQualifiers());
                break;
            case 3:
                this.listOfTaggedQualifierOne=creatingQua.create3ComplexQualifiers(treeOfChoices.getTaggedQualifiers());
                break;
        }
        switch(mainView.getSummSpinner().getValue().hashCode()){
            case 1:
                this.listOfTaggedSummarizerOne=creatingSumm.create1ComplexSummarizers(treeOfChoices.getTaggedSummarizers());
                break;
            case 2:
                this.listOfTaggedSummarizerOne=creatingSumm.create2ComplexSummarizers(treeOfChoices.getTaggedSummarizers());
                break;
            case 3:
                this.listOfTaggedSummarizerOne=creatingSumm.create3ComplexSummarizers(treeOfChoices.getTaggedSummarizers());
                break;
        }
        List<SummarizationTypeOne> summarizations = new ArrayList();
        List<SummarizationTypeTwo> summarizationsTypeTwo = new ArrayList();
        List<double[]> measures = new ArrayList();
        ServiceOfMeasures service = new ServiceOfMeasures(wages,playersData);
        
        //Podsumowanie typu I
        for(QuantifierTypeOne quantifier : this.listOfTaggedQuantifierOne)
        {
            for(SummarizerInterface summarizer : this.listOfTaggedSummarizerOne)
            {
                SummarizationTypeOne summarization = new SummarizationTypeOne(quantifier,summarizer,playersData);
                summarizations.add(summarization);
                measures.add(service.getTableExtensionTypeOne(summarization, false));
                for(QualifierInterface qualifier : this.listOfTaggedQualifierOne)
                {
                    SummarizationTypeOne summarizationWithQualifier = new SummarizationTypeOne(quantifier,summarizer,qualifier,playersData);
                    summarizations.add(summarizationWithQualifier);
                    measures.add(service.getTableExtensionTypeOne(summarizationWithQualifier, true));
                }
            }
        }
        
        //Podsumowania typu II
        for(QuantifierTypeTwo quantifier : this.listOfTaggedQuantifierTwo)
        {
            for(SummarizerInterface summarizer : this.listOfTaggedSummarizerOne)
            {
                SummarizationTypeTwo summarizationTypeTwo = new SummarizationTypeTwo(quantifier,summarizer,playersData);
                summarizationsTypeTwo.add(summarizationTypeTwo);
                measures.add(service.getTableExtensionTypeTwo(summarizationTypeTwo, false));
                for(QualifierInterface qualifier : this.listOfTaggedQualifierOne)
                {
                    SummarizationTypeTwo summarizationWithQualifier = new SummarizationTypeTwo(quantifier,summarizer,qualifier,playersData);
                    summarizationsTypeTwo.add(summarizationWithQualifier);
                    measures.add(service.getTableExtensionTypeTwo(summarizationWithQualifier, true));
                }
            }
        }
                
        TableWithSummarizations table = mainView.getTableWithSummarizations();
        List<String> summString=new ArrayList();
        for(SummarizationTypeOne summ : summarizations)
        {
            summString.add(summ.getSummarizationText());
        }
        for(SummarizationTypeTwo summ : summarizationsTypeTwo)
        {
            summString.add(summ.getSummarizationText());
        }
        table.setData(summString, measures);
	table.fireTableDataChanged();
        createTable(table);
        
    }
    
    private void createTable(TableWithSummarizations tableWS)
    {
        	        JTable table = new JTable();
			table.setShowGrid(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setFillsViewportHeight(true);
			table.setAutoCreateRowSorter(true);
			table.setModel(tableWS);
			table.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}	
				@Override
				public void mousePressed(MouseEvent e) {
				}
				@Override
				public void mouseExited(MouseEvent e) {
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void mouseClicked(MouseEvent e) {
                                              if (e.getClickCount() == 1) {
					      JTable tabela = (JTable)e.getSource();
					      int numerWiersza = tabela.getSelectedRow();
                                              String zaznaczone = tabela.getValueAt(numerWiersza,0).toString();
                                              finalSummarizations.add(zaznaczone);
                                              logger.debug(zaznaczone); 
                                              }
			}});
		
               mainView.setTable(table);
    }
    
    /**
     * Odświeża wartość wag po zmianie w oknie zmiany wag
     */    
    public void refreshWages(double[] newWages)
    {
        logger.debug("Zaczynamy wprowadzanie nowych wag.");
        if(wages.length!=newWages.length)
        {
            logger.debug("Błąd, tablice są róznych długości");
            return;
        }
        // I sposób, suma wag = 1;
        double summ=0.0;
        for (int i = 0; i < newWages.length; i++) {
		summ+=newWages[i];
	}
        for (int i = 0; i < newWages.length; i++) {
		wages[i]=newWages[i]/summ;
	}
        // II sposób wagi przyjmowane są w postaci podanej
        //System.arraycopy(newWages, 0, wages, 0, wages.length);
        
    }
    
}
