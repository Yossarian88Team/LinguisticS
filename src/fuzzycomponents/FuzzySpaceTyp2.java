/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

/**
 *
 * @author wlochaty
 */
public class FuzzySpaceTyp2 implements MembershipFunInter{

    MembershipFunInter memFun;
    double shift;
    double start;
    double end;

    public FuzzySpaceTyp2() {
    }
    
    public FuzzySpaceTyp2(FuzzySpace fSpace, MembershipFunInter mFun, double shift)
    {
        this.memFun = mFun;
        this.shift = shift;     
        this.start = findStartSpace(mFun, fSpace, shift);
        this.end = findEndSpace(mFun, fSpace, shift);
    }
    
    public double getValue(double val, MembershipFunInter mFun, double shift)
    {
        double result = mFun.getValue(val) + shift;
        
        if(result > 1.0)
            return 1.0;
        else
            return result;
    }
    
    private double findStartSpace(MembershipFunInter mFun, FuzzySpace fSpace, double shift)
    {
        for(double i = mFun.getStart(); i <= fSpace.getStart(); i -= fSpace.getStep())
            if(getValue(i, mFun, shift) == 0)
                return i;
        
        return fSpace.getStart();
    }
    
    private double findEndSpace(MembershipFunInter mFun, FuzzySpace fSpace, double shift)
    {
        for(double i = mFun.getEnd(); i <= fSpace.getEnd(); i += fSpace.getStep())
            if(getValue(i, mFun, shift) == 0)
                return i;
        
        return fSpace.getEnd();
    }
    
    public String getType() {
        return MembershipFunInter.type2;
    }

    public double getValue(double val) {
        double result = memFun.getValue(val) + this.shift;
        
        if(result > 1.0)
            return 1.0;
        else if(result < 0.0)
            return 0.0;
        else
            return result;
    }

    public double getStart() {
        return this.start;
    }

    public double getEnd() {
        return this.end;
    }

    public double getCenter() {
        return this.memFun.getCenter();
    }

    public boolean ifSharp() {
        return true;
    }
    
    
    
}
