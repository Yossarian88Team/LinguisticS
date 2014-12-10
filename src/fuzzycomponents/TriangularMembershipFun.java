/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

/**
 *
 * @author wlochaty
 */
public class TriangularMembershipFun implements MembershipFunInter{

    private double a;
    private double b;
    private double c;
    
    public TriangularMembershipFun() {
    
    }
    
    public TriangularMembershipFun(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String getType() {
        return MembershipFunInter.triangular;
    }

    public double getValue(double val) {
        if(this.a <= val && val <= this.b)
            return (val - this.a) / (this.b - this.a);
        else if(this.b < val && val <= this.c)
            return (val - this.c) / (this.b - this.c);
        else
            return 0.0d;
    }

    public double getStart() {
        return this.a;
    }

    public double getEnd() {
        return this.c;
    }

    public double getCenter() {
        return this.b;
    }

    public boolean ifSharp() {
        return true;
    }
    
}
