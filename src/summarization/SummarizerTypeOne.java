/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summarization;

import java.util.*;
import fuzzycomponents.*;
import summarization.linguisticelements.*;
import summarization.measures.ServiceOfMeasures;
import data.Histogram;

/**
 *
 * @author Ariel
 */
public class SummarizerTypeOne implements SummarizerInterface {
    
    
    private List<SummarizerInterface> summarizersList=null;
    private String columnName=null;
    private String label="nienazwane";
    private MembershipFunInter membFunction=null;
    private LinguisticValueTypeOne lV=null;
    private boolean isComplex=false;
    
    public SummarizerTypeOne(String label, String columnName, MembershipFunInter membFunction, List<SummarizerInterface> summarizersList){
        this.columnName=columnName;
        this.membFunction=membFunction;
        this.summarizersList=summarizersList;
        isComplex=true;
        this.label=label;
    }
    
   public SummarizerTypeOne( String columnName, MembershipFunInter membFunction, List<SummarizerInterface> summarizersList){
        this.columnName=columnName;
        this.membFunction=membFunction;
        this.summarizersList=summarizersList;
        isComplex=true;
    }
    
    public String getSummarizerLabel(){
        if(isComplex==false)
        {
            return lV.getLabel();
        }
        return lV.getLabel(); // zrobić stringa który będzie opisywał kilka złożonych sumaryzatorów jeśli będą
    }
    
    public SummarizerTypeOne(String label, String columnName, MembershipFunInter membFunction, LinguisticValueTypeOne lV){
        this.columnName=columnName;
        this.membFunction=membFunction;
        this.lV=lV;
        this.label=label;
        this.summarizersList=new ArrayList();
        summarizersList.add(this);
        isComplex=false;
    }
    
    public SummarizerTypeOne( String columnName, MembershipFunInter membFunction, LinguisticValueTypeOne lV){
        this.columnName=columnName;
        this.membFunction=membFunction;
        this.summarizersList=new ArrayList();
        summarizersList.add(this);
        this.lV=lV;
        isComplex=false;
    }
    
    public List<SummarizerInterface> getListOfSummarizers(){
        return summarizersList;
    }
    
    public String getColumnName(){
        return columnName;
        
    }
    
    public double getMembershipValue(double key){
        return membFunction.getValue(key);
    }
    
    public LinguisticValueTypeOne getLV(){
        return lV;
        
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label =label;
    }
    
    public String toString(){
	  return this.getSummarizerLabel();
     }
    
    public void createHistogram()
    {
        List<double[]> tempDouble=getValuesToHistogram();
        double x[] = new double[tempDouble.size()];
        double y[] = new double[tempDouble.size()];
        for(int i=0;i<tempDouble.size();i++)
        {
            x[i]=tempDouble.get(i)[0];
            y[i]=tempDouble.get(i)[1];
        }
        Histogram hist = new Histogram();
        hist.Plot(this.getSummarizerLabel(), x, y);
    }
    
    private List<double[]> getValuesToHistogram()
    {
        List<double[]> tempDouble=new ArrayList();
        double start=lV.getSet().getFspace().getStart();
        double end=lV.getSet().getFspace().getEnd();
        double step=lV.getSet().getFspace().getStep();
        for(double i=start;i<end;i=i+step)
        {
            double[] res=new double[2];
            res[0]=i;
            res[1]=this.membFunction.getValue(i);
            tempDouble.add(res);
        }
        return tempDouble;
    }
    
}
