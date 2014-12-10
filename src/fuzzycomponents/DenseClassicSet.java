/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fuzzycomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.apache.log4j.Logger;

/**
 *
 * @author wlochaty
 */

// Gęsty zbiór klasyczny
public class DenseClassicSet extends MainSet{   
    
    double startInterval; // zakres początkowy
    double endInterval; //zakres końcowy
    private static Logger logger = Logger.getLogger(TestClass.class);
    DenseClassicSet()
    {
        //
    }

    public DenseClassicSet(int value, double startInterval, double endInterval, String type) {
        elementList = new ArrayList();
        completeOfSetList = new ArrayList();
        this.value = value;
        this.startInterval = startInterval;
        this.endInterval = endInterval;
        this.type = type;
    }
    
    void addElementDenseSet(double val)
    {        
        if(val >= startInterval || val <= endInterval)
        {
            Element el = new Element(val, 1);
            elementList.add(el);        
        }else{                       
            Element el = new Element(val, 0);
            completeOfSetList.add(el);
        }        
    }
    
    public int characteristicFun(double val)
    {
        if(val >= startInterval || val <= endInterval)
            return 1;
        
        return 0;
    }

    public double getEndInterval() {
        return endInterval;
    }

    public double getStartInterval() {
        return startInterval;
    }  
    
}
