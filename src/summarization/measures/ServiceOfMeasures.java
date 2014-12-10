/*
 * Klasa obsługująca działania na miarach zaimplementowanych w klasie Measures.
 */
package summarization.measures;

import java.util.List;
import data.PlayerStat;
import summarization.*;

/**
 *
 * @author Ariel
 */
public class ServiceOfMeasures {
    
    double[] wages;
    SummarizationTypeOne summarization;
    List<PlayerStat> players;
    
    public ServiceOfMeasures(double[] wages, List<PlayerStat> players)
    {
        this.wages=wages;
        this.summarization=summarization;
        this.players=players;
    }
    
    public ServiceOfMeasures(List<PlayerStat> players)
    {
        this.players=players;
    }
    
    public double getDegreeOfTruth(SummarizationTypeOne summarization,boolean withQualifier)
    {
        return Measures.getDegreeOfTruthTypeOne(summarization, players,withQualifier);
    }
    
    public double[] getTableTypeOne(SummarizationTypeOne summarization, boolean withQualifier)
    {
        double[] measures = new double[6];
        measures[0]=Measures.getDegreeOfTruthTypeOne(summarization, players,withQualifier);
        measures[1]=Measures.getDegreeOfImprecisionTypeOne(summarization, players);
        measures[2]=Measures.getDegreeOfCoveringTypeOne(summarization, players);
        measures[3]=Measures.getDegreeOfAppropriatenessTypeOne(summarization, players, withQualifier);
        measures[4]=Measures.getLengthOfSummaryTypeOne(summarization);
        measures[5]=getOptimalSummary(measures);
        return measures;
    }
    
    public double[] getTableExtensionTypeOne(SummarizationTypeOne summarization, boolean withQualifier)
    {
        double[] measures = new double[12];
        measures[0]=Measures.getDegreeOfTruthTypeOne(summarization, players,withQualifier);
        measures[1]=Measures.getDegreeOfImprecisionTypeOne(summarization, players);
        measures[2]=Measures.getDegreeOfCoveringTypeOne(summarization, players);
        measures[3]=Measures.getDegreeOfAppropriatenessTypeOne(summarization, players, withQualifier);
        measures[4]=Measures.getLengthOfSummaryTypeOne(summarization);
        measures[5]=Measures.getDegreeOfQuantifierImprecisionTypeOne(summarization, players);
        measures[6]=Measures.getDegreeOfQuantifierCardinalityTypeOne(summarization, players);
        measures[7]=Measures.getDegreeOfSummarizerCardinalityTypeOne(summarization, players);
        measures[8]=Measures.getDegreeOfQualifierImprecisionTypeOne(summarization, players);
        measures[9]=Measures.getDegreeOfQualifierCardinalityTypeOne(summarization, players);
        measures[10]=Measures.getLengthOfQualifierTypeOne(summarization, players);
        measures[11]=getOptimalSummary(measures);
        return measures;
    }
    
    public double[] getTableExtensionTypeTwo(SummarizationTypeTwo summarization, boolean withQualifier)
    {
        double[] measures = new double[12];
        measures[0]=MeasuresTypeTwo.getDegreeOfTruthTypeTwo(summarization, players,withQualifier);
        measures[1]=MeasuresTypeTwo.getDegreeOfImprecisionTypeTwo(summarization, players);
        measures[2]=MeasuresTypeTwo.getDegreeOfCoveringTypeTwo(summarization, players);
        measures[3]=MeasuresTypeTwo.getDegreeOfAppropriatenessTypeTwo(summarization, players, withQualifier);
        measures[4]=MeasuresTypeTwo.getLengthOfSummaryTypeTwo(summarization);
        measures[5]=MeasuresTypeTwo.getDegreeOfQuantifierImprecisionTypeTwo(summarization, players);
        measures[6]=MeasuresTypeTwo.getDegreeOfQuantifierCardinalityTypeTwo(summarization, players);
        measures[7]=MeasuresTypeTwo.getDegreeOfSummarizerCardinalityTypeTwo(summarization, players);
        measures[8]=MeasuresTypeTwo.getDegreeOfQualifierImprecisionTypeTwo(summarization, players);
        measures[9]=MeasuresTypeTwo.getDegreeOfQualifierCardinalityTypeTwo(summarization, players);
        measures[10]=MeasuresTypeTwo.getLengthOfQualifierTypeTwo(summarization, players);
        measures[11]=getOptimalSummary(measures);
        return measures;
    }
    
    public double getOptimalSummary(double[] measures)
    {
        double summ=0.0;
        for(int i=0;i<measures.length-1;i++)
        {
            summ+=measures[i]*this.wages[i];
        }
        return summ;
    }
    
}
