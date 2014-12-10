/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summarization;

import summarization.linguisticelements.LinguisticValueTypeTwo;
import data.Histogram;
import java.util.*;
/**
 *
 * @author Ariel
 */
public class QuantifierTypeTwo {
    
    private LinguisticValueTypeTwo lingValue=null;
    public enum QuantifierType { RELATIVE, ABSOLUTE };
    private QuantifierType type=null;
    
    public QuantifierTypeTwo(LinguisticValueTypeTwo lingValue, boolean relative)
    {
        this.lingValue=lingValue;
        if(relative==true)this.type=QuantifierType.RELATIVE;
        else this.type=QuantifierType.ABSOLUTE;
    }

    public LinguisticValueTypeTwo getLingValue() {
        return lingValue;
    }

    public double getMembershipValue(double key){
        return getLingValue().membershipValue(key);
    }

    public void setLingValue(LinguisticValueTypeTwo lingValue) {
        this.lingValue = lingValue;
    }

    public QuantifierType getType() {
        return type;
    }

    public void setType(QuantifierType type) {
        this.type = type;
    }
    
    public String getLabel() {
        return this.lingValue.getLabel();
    }
    
    public boolean isRelative() {
        if(this.type==QuantifierType.RELATIVE) return true;
        else return false;
    }
    
     public String toString(){
	  return this.getLabel();
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
        hist.Plot(lingValue.getLabel(), x, y);
    }
    
    private List<double[]> getValuesToHistogram()
    {
        List<double[]> tempDouble=new ArrayList();
        double start=lingValue.getSet().getSpaceForFuzzySet().getStart();
        double end=lingValue.getSet().getSpaceForFuzzySet().getEnd();
        double step=lingValue.getSet().getSpaceForFuzzySet().getStep();
        for(double i=start;i<end;i=i+step)
        {
            double[] res=new double[2];
            res[0]=i;
            res[1]=getLingValue().membershipValue(i);
            tempDouble.add(res);
        }
        return tempDouble;
    }
    
    
}

    
    

