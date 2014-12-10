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
public class DiscClassicSet extends MainSet{

    private List<Double> space;         
    private static Logger logger = Logger.getLogger(TestClass.class);
    
    public DiscClassicSet()
    {}

    public DiscClassicSet(int _value, List<Double> _space, String type)
    {
        value = _value;            
        elementList = new ArrayList();
        completeOfSetList = new ArrayList();
        space = new ArrayList();
        space = _space;
        this.type = type;
    }               

    public void addToDiscreteList(double val)
    {    
        if(space.contains(val))
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
        for(int i = 0; i < space.size(); i++)
        {
            if(space.get(i) == val)
            {
                return 1;
            }
        }
        return 0;
    }
        
}
