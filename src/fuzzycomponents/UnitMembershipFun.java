/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

/**
 *
 * @author wlochaty
 */
public class UnitMembershipFun implements MembershipFunInter{

    public String getType() {
        return MembershipFunInter.unit;
    }

    public double getValue(double val) {
        return 1;
    }

    public double getStart() {
        return 0;
    }

    public double getEnd() {
        return 0;
    }

    public double getCenter() {
        return 0;
    }

    public boolean ifSharp() {
        return true;
    }
    
    
}
