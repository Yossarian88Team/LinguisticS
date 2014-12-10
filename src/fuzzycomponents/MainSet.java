/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author wlochaty
 */
public class MainSet {
    
    public String type;
    public int value;
    public List<Double> floatElementList; // w planach elementy gdzie podaje się 
                                  //  tylko wartości bez przynależności
    public List<Element> elementList; // elementy należace do zbioru
    public List<Element> completeOfSetList; //dopełnienie zbioru
    
    public MainSet()
    {
        //
    }

    public List<Element> getCompleteOfSetList() {
        return completeOfSetList;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public List<Double> getFloatElementList() {
        return floatElementList;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
        
}
