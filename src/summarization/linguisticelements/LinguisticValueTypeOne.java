/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summarization.linguisticelements;

import fuzzycomponents.FuzzySet;
import java.util.*;

/**
 *
 * @author Ariel
 */
public class LinguisticValueTypeOne {

    
    String label=null;
    FuzzySet set=null;
    LinguisticModifier modifier=null;
    
    public LinguisticValueTypeOne(String label,FuzzySet set)
    {
        this.label=label;
        this.set=set;
    }
    
    public LinguisticValueTypeOne(String label,FuzzySet set,String modifierLabel)
    {
        this.label=label;
        this.set=set;
        this.modifier=new LinguisticModifier(modifierLabel);
    }
    
    public double membershipValue(double key)
    {
        if(modifier==null)
        {
            return set.getMemberFun(key);
        } else
            return modifiedMemberValue(set.getMemberFun(key));
    }
    
        public LinguisticModifier getHedge() {
        return modifier;
    }

    public void setHedge(LinguisticModifier hedge) {
        this.modifier = hedge;
    }

    public String getLabel() {
        if(modifier==null)
            return label;
        else return modifier.getActualLabel() + " " + label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public FuzzySet getSet() {
        return set;
    }

    public void setSet(FuzzySet set) {
        this.set = set;
    }
    
    private double modifiedMemberValue(double normalValue)
    {
        //System.out.println(modifier.getActualLabel() + " " + modifier.getActualRValue());
        return Math.pow(normalValue, modifier.getActualRValue());
    }
    
    
    
}
