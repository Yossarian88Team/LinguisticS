/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summarization.linguisticelements;

import java.util.*;

/**
 *
 * @author Ariel
 */
public class LinguisticVariable {
    
    String L;
    List<LinguisticValueTypeOne> H;
    
    LinguisticVariable(String name, List<LinguisticValueTypeOne> valuesH)
    {
        this.L=name;
        this.H=valuesH;
    }
    
}
