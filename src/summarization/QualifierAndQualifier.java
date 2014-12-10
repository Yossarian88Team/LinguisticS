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
public class QualifierAndQualifier implements QualifierInterface {
    
    private QualifierInterface qualifierFirst=null;
    private QualifierInterface qualifierSecond=null;
    private List<QualifierInterface> qualifiersList=null;
    private String label=null;
    private LinguisticValueTypeOne lV=null;
    private boolean isComplex=true;
    
    public QualifierAndQualifier(QualifierInterface qualifierFirst, QualifierInterface qualifierSecond){
        this.qualifierFirst=qualifierFirst;
        System.out.println(this.qualifierFirst.toString());
        this.qualifierSecond=qualifierSecond;
        qualifiersList=new ArrayList();
        if(qualifierFirst instanceof QualifierTypeOne)
        {
                 this.qualifiersList.add(this.qualifierFirst);
        }
        else
        {
             this.qualifiersList.addAll(qualifierFirst.getListOfQualifiers());
        }
        if(qualifierSecond instanceof QualifierTypeOne)
        {
                 this.qualifiersList.add(qualifierSecond);
        }
        else
        {
             this.qualifiersList.addAll(qualifierSecond.getListOfQualifiers());
        }       
        isComplex=true;
    }
    
    public String getQualifierLabel(){
        if(isComplex==false)
        {
            return lV.getLabel();
        }
        return this.qualifierFirst.getQualifierLabel() + " i " + this.qualifierSecond.getQualifierLabel();
    }
    
    
    public List<QualifierInterface> getListOfQualifiers(){
        return qualifiersList;
    }
    
    public String getColumnName(){
        return qualifierFirst.getColumnName() + " i " + qualifierSecond.getColumnName();
        
    }
    
    public double getMembershipValue(double key){
        return Math.min(qualifierFirst.getMembershipValue(key), qualifierSecond.getMembershipValue(key));
    }
    
    public LinguisticValueTypeOne getLV()
    {
        return this.lV;
    }
    
    
    public String toString(){
	  return qualifierFirst.getQualifierLabel() + " i " + qualifierSecond.getQualifierLabel();
     }

    public String getLabel() {
        return qualifierFirst.getQualifierLabel() + " i " + qualifierSecond.getQualifierLabel();
    }
    
    public void setLabel(String label) {
         this.label=label;
    }

    public List<QualifierInterface> getQualifiersList() {
        return qualifiersList;
    }
    
    


    public void setLabel(String labelFirst, String labelSecond) {
        qualifierFirst.setLabel(labelFirst);
        qualifierSecond.setLabel(labelSecond);
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
