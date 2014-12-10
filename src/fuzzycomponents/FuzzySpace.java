/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

/**
 *
 * @author wlochaty
 */
public class FuzzySpace {
    
    private double start;
    private double end;
    private double step;
    
    public FuzzySpace(double start, double end, double step)
    {
        this.end = end;
        this.start = start;
        this.step = step;
    }

    public double getEnd() {
        return end;
    }

    public double getStart() {
        return start;
    }

    public double getStep() {
        return step;
    }
    
    public double getLenght()
    {
        return (this.end - this.start);
    }
}
