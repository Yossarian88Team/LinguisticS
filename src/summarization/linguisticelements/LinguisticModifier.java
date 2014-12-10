/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summarization.linguisticelements;

import java.util.*;

/**
 *
 * @author Ariel
 */
public class LinguisticModifier {
    
    private HashMap<String,Double> rValues = new HashMap();


    private String actualLabel=null;
    private Double actualRValue=null;
    
    public LinguisticModifier(String Label)
    {
        initializeHashValues();
        this.actualLabel=Label;
        setActualRValue();
    }
    
    private void initializeHashValues()
    {
        this.rValues.put(" nieco", 0.25);
        this.rValues.put(" mniej", 0.5);
        this.rValues.put(" niecałe", 0.75);
        this.rValues.put(" ", 1.0);
        this.rValues.put(" ponad", 1.25);
        this.rValues.put(" dużo", 1.5);
        this.rValues.put(" znacząco", 1.75);
        this.rValues.put(" bardzo", 2.0);
        this.rValues.put(" całkowicie", 4.0);
    }
    
    private void setActualRValue()
    {
        this.actualRValue=rValues.get(this.actualLabel);
    }
    
        public String getActualLabel() {
        return actualLabel;
    }

    public void setActualLabel(String actualLabel) {
        this.actualLabel = actualLabel;
    }

    public Double getActualRValue() {
        return actualRValue;
    }

    public void setActualRValue(Double actualRValue) {
        this.actualRValue = actualRValue;
    }
    
    
    
}
