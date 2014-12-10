/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summarization;

import summarization.linguisticelements.LinguisticValueTypeOne;
import data.Histogram;
import java.util.*;
import fuzzycomponents.*;
/**
 *
 * @author Ariel
 */
public class SummarizerOrSummarizer implements SummarizerInterface {
    
    private SummarizerInterface summarizerFirst=null;
    private SummarizerInterface summarizerSecond=null;
    private List<SummarizerInterface> summarizersList=null;
    private String label=null;
    private LinguisticValueTypeOne lV=null;
    private boolean isComplex=true;
    
    
    
    public SummarizerOrSummarizer( SummarizerInterface summarizerFirst, SummarizerInterface summarizerSecond){
        this.summarizerFirst=summarizerFirst;
        this.summarizerSecond=summarizerSecond;
        summarizersList=new ArrayList();
        if(summarizerFirst instanceof SummarizerTypeOne)
        {
                 summarizersList.add(summarizerFirst);
        }
        else
        {
             summarizersList.addAll(summarizerFirst.getListOfSummarizers());
        }
        if(summarizerSecond instanceof SummarizerTypeOne)
        {
                 summarizersList.add(summarizerSecond);
        }
        else
        {
             summarizersList.addAll(summarizerSecond.getListOfSummarizers());
        }       
        isComplex=true;
    }
    
    public String getSummarizerLabel(){
        if(isComplex==false)
        {
            return lV.getLabel();
        }
        return this.summarizerFirst.getSummarizerLabel() + " lub " + this.summarizerSecond.getSummarizerLabel();
    }
    
    
    public List<SummarizerInterface> getListOfSummarizers(){
        return summarizersList;
    }
    
    public String getColumnName(){
        return summarizerFirst.getColumnName() + " lub " + summarizerSecond.getColumnName();
        
    }
    
    public double getMembershipValue(double key){
        return Math.max(summarizerFirst.getMembershipValue(key), summarizerSecond.getMembershipValue(key));
    }
    
    public LinguisticValueTypeOne getLV()
    {
        return this.lV;
    }
    

    public String toString(){
	  return summarizerFirst.getLabel() + " lub " + summarizerSecond.getLabel();
     }

    public String getLabel() {
        return summarizerFirst.getSummarizerLabel() + " lub " + summarizerSecond.getSummarizerLabel();
    }
    
    public void setLabel(String label) {
         this.label=label;
    }

    public void setLabel(String labelFirst, String labelSecond) {
        summarizerFirst.setLabel(labelFirst);
        summarizerSecond.setLabel(labelSecond);
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
        hist.Plot(label, x, y);
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
            res[1]=this.getMembershipValue(i);
            tempDouble.add(res);
        }
        return tempDouble;
    }
    
}


