/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summarization.linguisticelements;

import fuzzycomponents.FuzzySetType2;
import java.util.*;

/**
 *
 * @author Ariel
 */
public class LinguisticValueTypeTwo {

    
    String label=null;
    FuzzySetType2 set=null;
    LinguisticModifier modifier=null;
    
    public LinguisticValueTypeTwo(String label,FuzzySetType2 set)
    {
        this.label=label;
        this.set=set;
    }
    
    public LinguisticValueTypeTwo(String label,FuzzySetType2 set,String modifierLabel)
    {
        this.label=label;
        this.set=set;
        this.modifier=new LinguisticModifier(modifierLabel);
    }
    
    public double membershipValue(double key)
    {
        if(modifier==null)
        {
            return set.getFuzzySetMembership(key);
        } else
            return modifiedMemberValue(set.getFuzzySetMembership(key));
    }
    
        public LinguisticModifier getHedge() {
        return modifier;
    }

    public void setModifier(LinguisticModifier modifier) {
        this.modifier = modifier;
    }

    public String getLabel() {
        if(modifier==null)
            return label;
        else return modifier.getActualLabel() + " " + label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public FuzzySetType2 getSet() {
        return set;
    }

    public void setSet(FuzzySetType2 set) {
        this.set = set;
    }
    
    private double modifiedMemberValue(double normalValue)
    {
        return Math.pow(normalValue, modifier.getActualRValue());
    }
    
    
    
}
