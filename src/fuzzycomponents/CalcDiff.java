/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

/**
 *
 * @author wlochaty
 */

//http://www.algorytm.org/procedury-numeryczne/calkowanie-numeryczne-metoda-prostokatow/metoda-prostokatow-j.html
public class CalcDiff {

    public CalcDiff() {
    }
    
    public double trapMetod(FuzzySet fs)
    {
        FuzzySpace fspace = fs.getFspace();
        double a = fspace.getStart();
        double b = fspace.getEnd();
        double n = 1000; //dokładność
        double fdx = 0.0;
        
        double dx = (Math.abs(a - b)) / n;
        double x = a + dx;
        
        for(double i = x; i < b; i += dx)
        {
            fdx = fdx + ((fs.getMemberFun(i + dx) + fs.getMemberFun(i)) * dx / 2);
        }
        
        fdx = fdx + ((fs.getMemberFun(b + dx) + fs.getMemberFun(b)) * dx / 2);
        
        return fdx;
    }
}
