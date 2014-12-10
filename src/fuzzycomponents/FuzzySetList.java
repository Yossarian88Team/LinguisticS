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

// Klasa mająca na celu przetrzymywanie na listach wszystkich zbiorów klasycznych

public class FuzzySetList {
    private List<DenseClassicSet> denseSetList;
    private List<DiscClassicSet> discSetList;
    
    public FuzzySetList()
    {
        denseSetList = new ArrayList<DenseClassicSet>();
        discSetList = new ArrayList<DiscClassicSet>();
    }
    
    public void addToFuzzySetList(DenseClassicSet clSet)
    {
        denseSetList.add(clSet);
    }
    
    public void addToFuzzySetList(DiscClassicSet clSet)
    {
        discSetList.add(clSet);
    }

    public List<DenseClassicSet> getDenseSetList() {
        return denseSetList;
    }

    public List<DiscClassicSet> getDiscSetList() {
        return discSetList;
    }
    
    
}
