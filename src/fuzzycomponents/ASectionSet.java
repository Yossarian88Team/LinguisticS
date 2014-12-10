/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

/**
 *
 * @author wlochaty
 */
public class ASectionSet extends FuzzySet{
    
    private double alfa;

    public ASectionSet(FuzzySet fSet, double alfa)
    {
        this.fspace = fSet.getFspace();
        this.alfa = alfa;
        this.memberFun = fSet.getMemberFun();
    }

    public ASectionSet() {
    }
    
    public double getMemberFun(double val)
    {
        double membership = super.getMemberFun(val);
        
        if(membership >= alfa)
            return 1.0;
        
        return 0.0;
    }
}
