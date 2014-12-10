/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

/**
 *
 * @author wlochaty
 */
public class RectengularMembershipFun implements MembershipFunInter{
    
    private double a;
    private double b;

    public RectengularMembershipFun(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public RectengularMembershipFun() {
    }
    
    
    public String getType() {
        return MembershipFunInter.rectengular;
    }

    public double getValue(double val) {
        if(a <= val && val <= b)
            return 1.0;
        else
            return 0.0;
    }

    public double getStart() {
        return a;
    }

    public double getEnd() {
        return b;
    }

    public double getCenter() {
        return (b - a) / 2 + a;
    }

    public boolean ifSharp() {
        return false;
    }
    
    
}
