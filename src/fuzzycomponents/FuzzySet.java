/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

import java.util.List;       

/**
 *
 * @author wlochaty
 */
//
public class FuzzySet {

    protected FuzzySpace fspace;
    protected MembershipFunInter memberFun;
    
    public FuzzySet()
    {
        //
    }
    
    public FuzzySet(FuzzySpace fs, MembershipFunInter memFun)
    {
        this.fspace = fs;
        this.memberFun = memFun;
    }

    public boolean emptySet()
    {
        for(double i = getFspace().getStart(); i <= getFspace().getEnd(); i += getFspace().getStep())
        {
            if(getMemberFun(i) > 0)
                return false;
        }
        return true;
    }
    public FuzzySpace getFspace() {
        return fspace;
    }

    public double getMemberFun(double val) {
        return memberFun.getValue(val);
    }

    public MembershipFunInter getMemberFun() {
        return memberFun;
    }
    
    public double getHeight()
    {
        double height = 0;
        for(double i = getFspace().getStart(); i <= getFspace().getEnd(); i += getFspace().getStep())
        {
            height = Math.max(getMemberFun(i), height);
        }
        return height;
    }

    public double getNonZeroValueStart() //Początej niezerowej przestrzeni przynależności, potrzebne do nośnika
    {
        double val = -1;
        for(double i = getFspace().getStart(); i <= getFspace().getEnd(); i += getFspace().getStep())
        {
            if(getMemberFun(i) > 0)
            {
                return i;
            }
        }
        return val;
    }
    
    public double getNonZeroValueEnd() //Koniec niezerowej przestrzeni przynależności, potrzebne do nośnika
    {
        double val = -1;
        for(double i = getFspace().getEnd(); i >= getFspace().getStart(); i -= getFspace().getStep())
        {
            if(getMemberFun(i) > 0)
            {
                return i;
            }
        }
        return val;
    }
    
    public double getLenghtSup()
    {
        return getNonZeroValueEnd() - getNonZeroValueStart();
    }    
    
    public boolean convexSet()
    {                
        for(double alfa = 0.0; alfa < 1.0; alfa += getFspace().getStep())
        {
            FuzzySet fs = new ASectionSet(this, alfa);
            if(!scConvexSet(fs))
            {
                return false;
            }
        }
        return true;
    }
    
    private boolean scConvexSet(FuzzySet fs)
    {
        for(double i = fs.getNonZeroValueStart(); i <= fs.getNonZeroValueEnd(); i += getFspace().getStep())
        {
            if( fs.getMemberFun(i) < 1.0)
            {
                return false;
            }
        }
        return true;
    }
    
    public double getMembershipAlfa(double val, double alfa)
    {
        double membership = this.getMemberFun(val);
        
        if(membership >= alfa)
            return 1.0;
        
        return 0.0;
    }
    
    public double getClm()
    {
        CalcDiff cd = new CalcDiff();
        return cd.trapMetod(this);
    }
    
    public boolean normal()
    {
        return (getHeight() >= 1.0);
    }
}
