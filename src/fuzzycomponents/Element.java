/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

/**
 *
 * @author wlochaty
 */
public class Element {
    
    private double value;
    private double membershipValue;
    
    Element(double _value, double _memb)
    {
        this.value = _value;
        this.membershipValue = _memb;
    }
    
    double getValue()
    {
        return value;
    }
    
    double getMembership()
    {
        return this.membershipValue;
    }
        
}
