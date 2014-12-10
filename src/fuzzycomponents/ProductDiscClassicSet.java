/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wlochaty
 */
public class ProductDiscClassicSet {
    
    private List<DiscClassicSet> listOfSets;
    
    public ProductDiscClassicSet(List<DiscClassicSet> listOfSets) 
    {
        this.listOfSets = new ArrayList<DiscClassicSet>();
        this.listOfSets = listOfSets;
    }
    
    public ProductDiscClassicSet(DiscClassicSet dcs1, DiscClassicSet dcs2)
    {
        this.listOfSets = new ArrayList<DiscClassicSet>();
        listOfSets.add(dcs1);
        listOfSets.add(dcs2);
    }
    
    public List<Element> productOperation(DiscClassicSet dcs1, DiscClassicSet dcs2)
    {
        List<Element> elemList = new ArrayList<Element>();
        List<Element> dcs1List = new ArrayList<Element>();
        List<Element> dcs2List = new ArrayList<Element>();
        List<Double> dcs2Val = new ArrayList<Double>();

        dcs1List = dcs1.elementList;
        dcs1List.addAll(dcs1.completeOfSetList);
        dcs2List = dcs2.elementList;
        dcs2List.addAll(dcs2.completeOfSetList);
        for(Element el : dcs2List)
        {
            dcs2Val.add(el.getValue());
        }
        
        for(Element el1 : dcs1List)
        {
            if(dcs2Val.contains(el1.getValue()))
            {
                for(Element el2 : dcs2List)
                {
                    if(el2.getValue() == el1.getValue())
                    {
                        if(el2.getMembership() > el1.getMembership())
                        {
                            elemList.add(el1);
                        }else{
                            elemList.add(el2);
                        }
                        break;
                    }
                }
            }
        }
        return elemList;
    }

    public List<DiscClassicSet> getListOfSets() {
        return listOfSets;
    }
}
