/*
 * Klasa generująca podsumowanie na podstawie podanych kwantyfikatora, sumaryzatora/ów i ewentualnie kwalifikatora
 */
package summarization;

import data.PlayerStat;
import java.util.*;

/**
 *
 * @author Ariel
 */
public class SummarizationTypeTwo {
    
    private String summarizationText=null;
    private QuantifierTypeTwo quantifier=null;
    private SummarizerInterface summarizer=null;
    private QualifierInterface qualifier=null;
    private List<PlayerStat> playerStat=null;
    private List<SummarizerInterface> summarizersList=null;
    private List<QualifierInterface> qualifiersList=null;
    
    public SummarizationTypeTwo(QuantifierTypeTwo quantifier, SummarizerInterface summarizer, List<PlayerStat> playerStat)
    {
        this.quantifier=quantifier;
        this.summarizer=summarizer;
        summarizersList=summarizer.getListOfSummarizers();
        this.playerStat=playerStat;
        createSummarizationText();
    }
    
    public SummarizationTypeTwo(QuantifierTypeTwo quantifier, SummarizerInterface summarizer, QualifierInterface qualifier, List<PlayerStat> playerStat)
    {
        this.quantifier=quantifier;
        this.summarizer=summarizer;
        this.playerStat=playerStat;
        this.qualifier=qualifier;
        summarizersList=summarizer.getListOfSummarizers();
        qualifiersList=qualifier.getListOfQualifiers();
        createSummarizationTextWithQualifier();
    }
    
    public int getValueOfFitData()
    {
        int result=0;
        String attribute=summarizer.getColumnName();
        for(PlayerStat player: this.playerStat)
        {
            if(this.summarizer.getMembershipValue(player.getValueByStringName(attribute))>0.0d)
            {
                result++;
            }
        }
        return result;
    }

    public QuantifierTypeTwo getQuantifier() {
        return quantifier;
    }

    public void setQuantifier(QuantifierTypeTwo quantifier) {
        this.quantifier = quantifier;
    }

    public String getSummarizationText() {
        return summarizationText;
    }

    public void setSummarizationText(String summarizationText) {
        this.summarizationText = summarizationText;
    }

    public SummarizerInterface getSummarizer() {
        return summarizer;
    }

    public void setSummarizer(SummarizerInterface summarizer) {
        this.summarizer = summarizer;
        
    }

    public List<QualifierInterface> getQualifiersList() {
        return qualifiersList;
    }

    public void setQualifiersList(List<QualifierInterface> qualifiersList) {
        this.qualifiersList = qualifiersList;
    }

    public List<SummarizerInterface> getSummarizersList() {
        return summarizersList;
    }

    public void setSummarizersList(List<SummarizerInterface> summarizersList) {
        this.summarizersList = summarizersList;
    }
    
    

    public QualifierInterface getQualifier() {
        return qualifier;
    }

    public void setQualifier(QualifierInterface qualifier) {
        this.qualifier = qualifier;
    }


    
    
    
    
    
    private void createSummarizationText()
    {
                StringBuilder summarizationBuilder = new StringBuilder("");
		summarizationBuilder.append(quantifier.getLabel());
		summarizationBuilder.append(" graczy ");
		summarizationBuilder.append(" ma ");
		summarizationBuilder.append(summarizer.getSummarizerLabel());
                this.summarizationText=summarizationBuilder.toString();
    }
    
    private void createSummarizationTextWithQualifier()
    {
                StringBuilder summarizationBuilder = new StringBuilder("");
		summarizationBuilder.append(quantifier.getLabel());
		summarizationBuilder.append(" graczy, mających ");
                summarizationBuilder.append(qualifier.getQualifierLabel());
		summarizationBuilder.append(" , ma ");
		summarizationBuilder.append(summarizer.getSummarizerLabel());
                this.summarizationText=summarizationBuilder.toString();
    }
    
    

}
