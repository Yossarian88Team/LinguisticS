/*
 * Klasa będąca rozszerzeniem standardowej klasy tabeli udostępnianej przez Javę, dostosowana do potrzeb projektu
 */
package gui.parts;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ariel
 */
public class TableWithSummarizations extends AbstractTableModel{
    
    private List<String> listWithSummarizations=null;
    private List<double[]> measures;
    private int columns=0;
    private int rows=0;
    private String[] labels=null;
    
    public TableWithSummarizations()
    {
        super();
        
    }
    
    public TableWithSummarizations(List<String> summarizations, List<double[]> measures)
    {
        super();
        this.listWithSummarizations=summarizations;
        this.measures=measures;
        if(measures.get(0).length<7)
        {
            this.labels = new String[]{ "Podsumowanie", "T1", "T2", "T3", "T4", "T5", "T" };
        }else
        {
            this.labels = new String[]{ "Podsumowanie", "T1", "T2", "T3", "T4", "T5",
                                        "T6", "T7", "T8", "T9", "T10", "T11", "T"};
        }
        this.columns=measures.get(0).length+1;
        this.rows=summarizations.size();
    }
    

    
    public void setData(List<String> summarizations, List<double[]> measures)
    {
        this.listWithSummarizations=summarizations;
        this.measures=measures;
//        System.out.println("length: " + measures.get(0)[0]);
        if(measures.get(0).length<7)
        {
            this.labels = new String[]{ "Podsumowanie", "T1", "T2", "T3", "T4", "T5", "T" };
        }else
        {
            this.labels = new String[]{ "Podsumowanie", "T1", "T2", "T3", "T4", "T5",
                                        "T6", "T7", "T8", "T9", "T10", "T11", "T"};
        }
        this.columns=measures.get(0).length+1;
        this.rows=summarizations.size();
    }
    
    @Override
    public int getColumnCount() {
	return this.columns;
    }

    @Override
    public int getRowCount() {
	return this.rows;
    }
    
    @Override   
    public boolean isCellEditable(int row, int col){ 
        if(col==0)return true; 
        else return false;
    }

    @Override
    public String getColumnName(int column){
	    
	    return labels[column];
    }
    
    @Override
    public Object getValueAt(int row, int column) {
	String summarization = listWithSummarizations.get(row);
	double[] measures = this.measures.get(row);
        switch(column){
            case 0:
                return summarization;
            case 1:
                return measures[0];
            case 2:
                return measures[1];
            case 3:
                return measures[2];
            case 4:
                return measures[3];
            case 5:
                return measures[4];
            case 6:
                return measures[5];
            case 7:
                return measures[6];
            case 8:
                return measures[7];
            case 9:
                return measures[8];
            case 10:
                return measures[9];
            case 11:
                return measures[10];
            case 12:
                return measures[11];
        }
    return null;
    }
    
    
    
}
