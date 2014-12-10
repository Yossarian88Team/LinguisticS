/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

/**
 *
 * @author wlochaty
 */
public class TrapetzoidalMembershipFun implements MembershipFunInter{

    private double a;
    private double b;
    private double c;
    private double d;
    
    public TrapetzoidalMembershipFun() {
    }
    
    public TrapetzoidalMembershipFun(double a, double b, double c, double d)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
     public String getType() {
        return MembershipFunInter.trapetzoidal;
    }

    public double getValue(double val) {
        if(this.a <= val && val <= this.b) //skos
            if( b - a > 0)
                return (val - this.a) / (this.b - this.a);
            else
                return 1;
        else if(this.b < val && val <= this.c) // daszek
            return 1;
        else if( this.c < val && val <= d) //skos
            if(c - d > 0)
                return (val - d) / (c - d);
            else
                return 1;
        else
            return 0; // w ogole poza zbiorem
    }

    public double getStart() {
        return this.a;
    }

    public double getEnd() {
        return this.d;
    }

    public double getCenter() {
        return (this.c - this.b) / 2 + this.b;
    }

    public boolean ifSharp() {
        return false;
    }
    
    
}
