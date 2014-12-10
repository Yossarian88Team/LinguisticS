/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author wlochaty
 */
public class SumDiscClassicSet {

    private List<DiscClassicSet> listOfSets;
    private static Logger logger = Logger.getLogger(TestClass.class);
    
    public SumDiscClassicSet(List<DiscClassicSet> listOfSets) 
    {
        this.listOfSets = new ArrayList<DiscClassicSet>();
        this.listOfSets = listOfSets;
    }
    
    public SumDiscClassicSet(DiscClassicSet dcs1, DiscClassicSet dcs2)
    {
        this.listOfSets = new ArrayList<DiscClassicSet>();
        listOfSets.add(dcs1);
        listOfSets.add(dcs2);
    }
    
    public List<Element> sumOperation(List<DiscClassicSet> listOfSets)
    {
        List<Element> elemList = new ArrayList<Element>();

        for(DiscClassicSet discCl : listOfSets)
        {
            for(Element el : discCl.getElementList())
            {
                if(elemList.contains(el))
                {
                    for(Element el2 : elemList)
                    {
                        if( el2.getValue() == el.getValue())
                        {
                            if( el2.getMembership() < el.getMembership())
                            {
                                elemList.remove(el2);
                                elemList.add(el);
                            }
                            break;
                        }
                    }
                }else{
                    elemList.add(el);
                }
                
            }
        }
        
        return elemList;
    }

    public List<DiscClassicSet> getListOfSets() {
        return listOfSets;
    }
    
}
