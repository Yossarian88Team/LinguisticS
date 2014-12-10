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
public class QualifierTypeOne implements QualifierInterface {
    
    
    private List<QualifierInterface> qualifiersList=null;
    private String columnName=null;
    private String columnPolishName=null;
    private String label=null;
    private MembershipFunInter membFunction=null;
    private LinguisticValueTypeOne lV=null;
    private boolean isComplex=false;
    
    public QualifierTypeOne(String label,String columnName, MembershipFunInter membFunction, List<QualifierInterface> qualifiersList){
        this.columnName=columnName;
        this.membFunction=membFunction;
        this.qualifiersList=qualifiersList;
        isComplex=true;
        this.label=label;
    }
    
    public String getQualifierLabel(){
        if(isComplex==false)
        {
            return lV.getLabel();
        }
        return this.lV.getLabel();
    }
    
    public QualifierTypeOne(String label, String columnName, MembershipFunInter membFunction, LinguisticValueTypeOne lV){
        this.columnName=columnName;
        this.membFunction=membFunction;
        this.lV=lV;
        this.qualifiersList=new ArrayList();
        qualifiersList.add(this);
        this.label=label;
        isComplex=false;;
    }
    
    public List<QualifierInterface> getListOfQualifiers(){
        return qualifiersList;
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

    public String getColumnPolishName() {
        return columnPolishName;
    }

    public void setColumnPolishName(String columnPolishName) {
        this.columnPolishName = columnPolishName;
    }
    
    public String toString(){
	  return getQualifierLabel();
     }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        hist.Plot(this.getQualifierLabel(), x, y);
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
