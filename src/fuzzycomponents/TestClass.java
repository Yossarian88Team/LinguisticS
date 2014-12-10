/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

import java.util.List;
import main.Worker;
import data.*;
import java.util.*;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author wlochaty
 */
public class TestClass {
    
    private List<PlayerStat> playerData;
    private List<Integer> readColumnList;
    private List<Double> space;
    private List<Double> space1;
    private static Logger logger = Logger.getLogger(TestClass.class);
    
    public TestClass()
    {
        //
    }
    
    public TestClass(List<PlayerStat> playersData)
    {
        this.playerData = new ArrayList<PlayerStat>();
        this.readColumnList = new ArrayList<Integer>();
        this.space = new ArrayList<Double>();
        this.space1 = new ArrayList<Double>();
        this.playerData = playersData;
    }
    
    public void setReadColumn()
    {
        for(PlayerStat ps : playerData)
            readColumnList.add(ps.getWins());
    }
    
    public void writeElementList()
    {
        logger.debug("Wypisuje elementy do testu.");
        for(Integer integ : readColumnList)
            logger.debug(integ);
        logger.debug("Koniec elementow do testu.");
    }
    
    public void createSpace()
    {
        for(int i = 0; i < 15; i++)
        {
            space.add(Double.parseDouble(Integer.toString(i)));
        }
        for(int i = 15; i < 56; i++)
        {
            space1.add(Double.parseDouble(Integer.toString(i)));
        }
        //logger.debug("Wielkosc listy: " + space1.size());
        for(int i = 0; i < space1.size(); i++)
        {
            //logger.debug(space1.get(i));
        }
    }
    
    public void testDiscClassic()
    {
        /*DiscClassicSet dcs = new DiscClassicSet(0, space, "malo");
        DiscClassicSet dcs2 = new DiscClassicSet(0, space1, "wiecej");
        List<Element> tempList = new ArrayList<Element>();
        List<Element> tempList2 = new ArrayList<Element>();
        List<Element> sumList = new ArrayList<Element>();
        List<Element> prodList = new ArrayList<Element>();
        
        double val;
        for(int i = 0; i < readColumnList.size(); i++)
        {
            val = Double.parseDouble(Integer.toString(readColumnList.get(i)));
            dcs.addToDiscreteList(val);
            
            dcs2.addToDiscreteList(val);
        }
        
        tempList = dcs.getElementList();
        tempList2 = dcs.getCompleteOfSetList();
        
        SumDiscClassicSet sumDisc = new SumDiscClassicSet(dcs, dcs2);
        sumList = sumDisc.sumOperation(sumDisc.getListOfSets());
        
        ProductDiscClassicSet prodDisc = new ProductDiscClassicSet(dcs, dcs2);
        prodList = prodDisc.productOperation(dcs, dcs2);
        
        for(int i = 0; i < prodList.size(); i++)
        {
            logger.debug("Wartosc: " + prodList.get(i).getValue()+ " - Przynaleznosc: " + prodList.get(i).getMembership());
        }*/
        
        double startEl = 0;
        double endEl = 50;
        double stepEl = 0.1;
        FuzzySpace fSpace = new FuzzySpace(startEl, endEl, stepEl);
        TriangularMembershipFun mem = new TriangularMembershipFun(0, 5, 10);
        
        FuzzySet fset = new FuzzySet(fSpace, mem);
        logger.debug("wartosc: " + fset.getNonZeroValueEnd());
        
    }
}
