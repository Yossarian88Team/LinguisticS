/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

/**
 *
 * @author wlochaty
 */
public interface MembershipFunInter {
    
    public static String triangular = "Trojkatna";
    public static String trapetzoidal = "Trapezoidalna";
    public static String rectengular = "Prostokątna";
    public static String unit = "Jednostkowa";
    public static String type2 = "Typ2";
    
    public String getType();
    public double getValue(double val);
    public double getStart();
    public double getEnd();
    public double getCenter();
    public boolean ifSharp();
    
}
