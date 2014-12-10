/*
 * Klasa będąca interfejsem dla wszystkich typów sumaryzatorów
 */
package summarization;

/**
 *
 * @author Ariel
 */

import java.util.*;
import summarization.linguisticelements.*;

public interface SummarizerInterface {
    
    public List<SummarizerInterface> getListOfSummarizers();
    public String getColumnName();
    public double getMembershipValue(double key);
    public LinguisticValueTypeOne getLV();
    public String getSummarizerLabel();
    public String getLabel();
    public void setLabel(String label);
    
}
