/*
 * Klasa będąca interfejsem dla kwalifikatorów.
 */
package summarization;

import java.util.List;
import summarization.linguisticelements.*;

/**
 *
 * @author Ariel
 */
public interface QualifierInterface {
    
    public List<QualifierInterface> getListOfQualifiers();
    public String getColumnName();
    public double getMembershipValue(double key);
    public LinguisticValueTypeOne getLV();
    public String getQualifierLabel();
    public String getLabel();
    public void setLabel(String label);
    
}
