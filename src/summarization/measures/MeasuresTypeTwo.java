/*
 * Klasa zawierająca metody poszczególnych miar
 */
package summarization.measures;

import java.util.List;
import java.util.ArrayList;
import data.PlayerStat;
import summarization.*;
import fuzzycomponents.*;

/**
 *
 * @author Ariel
 */
public  class MeasuresTypeTwo {
    
    	public static double getDegreeOfFuzzinessTypeTwo(FuzzySetType2 tempSet) {
		double supp = tempSet.getLenghtSup();
		double X = tempSet.getSpaceForFuzzySet().getLenght();
                double in=supp/X;
		return in;
	}
        
        public static double getRatioOfCardinalityTypeTwo(FuzzySetType2 tempSet) {
                double Clm=tempSet.clm();
                double X = tempSet.getSpaceForFuzzySet().getLenght();
                double rc = Clm/X;
		return rc;
	}
        
        public static double getDegreeOfFuzziness(FuzzySet tempSet) {
		double supp = tempSet.getLenghtSup();
		double X = tempSet.getFspace().getLenght();
                double in=supp/X;
		return in;
	}
        
        public static double getRatioOfCardinality(FuzzySet tempSet) {
                double Clm=tempSet.getClm();
                double X= tempSet.getFspace().getLenght();
                double rc = Clm/X;
		return rc;
	}
    
    static double getDegreeOfTruthTypeTwo(SummarizationTypeTwo summarization, List<PlayerStat> players, boolean withQualifier){
        
        if(withQualifier==true) return getDegreeOfTruthTypeTwoWithQualifier(summarization,players);
        double degree=0.0;
        double r=0.0;
        String category = summarization.getSummarizer().getColumnName();

        boolean quantifierRelative=summarization.getQuantifier().isRelative();
        for(PlayerStat player: players)
        {
            double key = player.getValueByStringName(category);
            r+=summarization.getSummarizer().getMembershipValue(key);
        }
        if(quantifierRelative==false){
            degree=summarization.getQuantifier().getMembershipValue(r);
        }else{
            double sizeOfData = players.size();
            degree=summarization.getQuantifier().getMembershipValue(r/sizeOfData);
        }
        return degree;
    }
    
   static double getDegreeOfTruthTypeTwoWithQualifier(SummarizationTypeTwo summarization, List<PlayerStat> players){
        
        double degree=0.0;
        double r=0.0;
        double k=0.0;
        String category = summarization.getSummarizer().getColumnName();

        boolean quantifierRelative=summarization.getQuantifier().isRelative();
        for(PlayerStat player: players)
        {
            double key = player.getValueByStringName(category);
            k+=summarization.getQualifier().getMembershipValue(key);
            r+=Math.min(summarization.getSummarizer().getMembershipValue(key), summarization.getQualifier().getMembershipValue(key));
        }
        if(quantifierRelative==false){
            degree=summarization.getQuantifier().getMembershipValue(r);
        }else{
            double sizeOfData = players.size();
            degree=summarization.getQuantifier().getMembershipValue(r/sizeOfData);
        }
        return degree;
    }
   
        
     static double getDegreeOfImprecisionTypeTwo(SummarizationTypeTwo summarization, List<PlayerStat> players){
        
        double degree=0.0;
        double valueOfIn=1.0;
        double r=0.0;
        List<SummarizerInterface> summList=summarization.getSummarizer().getListOfSummarizers();
        for(SummarizerInterface summarizer : summList)
        {
            valueOfIn*=getDegreeOfFuzziness(summarizer.getLV().getSet());
        }

        degree=(1.0d - Math.pow((double)valueOfIn, (double)(1.0d/(double)summList.size())));
        return degree;
    }
    
    static double getDegreeOfCoveringTypeTwo(SummarizationTypeTwo summarization, List<PlayerStat> players)
    {
        int numberOfSummarizerTrue=getNumberOfSummarizerTrue(players, summarization);
        int numberOfQualifierTrue=getNumberOfQualifierTrue(players, summarization);
        if(numberOfQualifierTrue==0)return 0.0;
        double degree=(double)numberOfSummarizerTrue/(double)numberOfQualifierTrue;
        return degree;
    }
    
    static double getDegreeOfAppropriatenessTypeTwo(SummarizationTypeTwo summarization, List<PlayerStat> players, boolean withCovering)
    {
        List<SummarizationTypeTwo> summList=getSingleSummarizations(summarization,players);
        double value=1.0;
        double degree=0.0;
        double sizeOfPlayers=players.size();
        for(SummarizationTypeTwo summTemp : summList)
        {
            double r = getNumberOfSummarizerTrue(players,summTemp)/sizeOfPlayers;;
            value=value*r;
            
        }
        double degreeOfCovering=getDegreeOfCoveringTypeTwo(summarization,players);
        if(withCovering==true)
            degree = Math.abs(value-degreeOfCovering);
        else degree= Math.abs(value);
        return degree;
        
        
    }
    
    static double getLengthOfSummaryTypeTwo(SummarizationTypeTwo summarization)
    {
	int summLength=summarization.getSummarizer().getListOfSummarizers().size();
        double degree = (double)(2.0 * Math.pow(0.5, summLength));
        return degree;
    }
     
     
        
    static public int getNumberOfSummarizerTrue(List<PlayerStat> players, SummarizationTypeTwo summarization) {
		int number = 0;
                String category = summarization.getSummarizer().getColumnName();
		if (summarization.getQualifier() != null) {
			for (PlayerStat player : players) {
                             double key = player.getValueByStringName(category);
                             if (Math.min(summarization.getSummarizer().getMembershipValue(key), summarization.getQualifier().getMembershipValue(key)) > 0.0d) {
                                    number++;
                             }
			}
		} else {
			for (PlayerStat player : players) {
                             double key = player.getValueByStringName(category);
                             if (summarization.getSummarizer().getMembershipValue(key) > 0.0d) {
                                    number++;
                             }
			}
		}
		return number;
	}

    static public int getNumberOfQualifierTrue(List<PlayerStat> players, SummarizationTypeTwo summarization) {
		int number = 0;
                String category = summarization.getSummarizer().getColumnName();
		if (summarization.getQualifier() == null) {
                             number = players.size();
		} else {
			for (PlayerStat player : players) {
                             double key = player.getValueByStringName(category);
                             if (summarization.getQualifier().getMembershipValue(key) > 0.0d) {
                                    number++;
                             }
			}
		}
		return number;
	}
    
    private static List<SummarizationTypeTwo> getSingleSummarizations(SummarizationTypeTwo summarization, List<PlayerStat> players)
    {
        List<SummarizationTypeTwo> summList= new ArrayList();
        List<SummarizerInterface> summarizerList = summarization.getSummarizer().getListOfSummarizers();
        for(SummarizerInterface summarizer : summarizerList)
        {
            SummarizationTypeTwo summTemp= new SummarizationTypeTwo(summarization.getQuantifier(),summarizer, players);
            summList.add(summTemp);
        }
        return summList;
        
    }
    
    static double getDegreeOfQuantifierImprecisionTypeTwo(SummarizationTypeTwo summarization, List<PlayerStat> players){
        double dof=getDegreeOfFuzzinessTypeTwo(summarization.getQuantifier().getLingValue().getSet());
        double degree=(double)(1.0-dof);
        return degree;
    }
    
    static double getDegreeOfQuantifierCardinalityTypeTwo(SummarizationTypeTwo summarization, List<PlayerStat> players){
        double roc=getRatioOfCardinalityTypeTwo(summarization.getQuantifier().getLingValue().getSet());
        double degree=(double)(1.0-roc);
        return degree;
    }
    
    static double getDegreeOfSummarizerCardinalityTypeTwo(SummarizationTypeTwo summarization, List<PlayerStat> players)
    {
        List<SummarizerInterface> summList=summarization.getSummarizersList();
        double value=1.0;
        double degree=0.0;
        double sizeOfPlayers=players.size();
        for(SummarizerInterface summTemp : summList)
        {
            double r = getRatioOfCardinality(summTemp.getLV().getSet());
            value=value*r;
            
        }
        degree=Math.pow(value, 1.0 /(double)(summList.size()));
        degree= 1.0d - degree;
        return degree;
        
        
    }
 
    static double getDegreeOfQualifierImprecisionTypeTwo(SummarizationTypeTwo summarization, List<PlayerStat> players)
    {
        if(summarization.getQualifiersList()==null)return 0.0;
        List<QualifierInterface> quaList=summarization.getQualifiersList();
        double value=1.0;
        double degree=0.0;
        double sizeOfPlayers=players.size();
        for(QualifierInterface quaTemp : quaList)
        {
            double r = getDegreeOfFuzziness(quaTemp.getLV().getSet());
            value=value*r;
            
        }
        degree=Math.pow(value, 1.0 /(double)(quaList.size()));
        degree= 1.0d - degree;
        return degree;
        
        
    }
      
        
    static double getDegreeOfQualifierCardinalityTypeTwo(SummarizationTypeTwo summarization, List<PlayerStat> players)
    {
        if(summarization.getQualifiersList()==null)return 0.0;
        List<QualifierInterface> quaList=summarization.getQualifiersList();
        double value=1.0;
        double degree=0.0;
        double sizeOfPlayers=players.size();
        for(QualifierInterface quaTemp : quaList)
        {
            double r = getRatioOfCardinality(quaTemp.getLV().getSet());
            value=value*r;
            
        }
        degree=Math.pow(value, 1.0 /(double)(quaList.size()));
        degree= 1.0d - degree;
        return degree;
        
        
    }
    
    static double getLengthOfQualifierTypeTwo(SummarizationTypeTwo summarization, List<PlayerStat> players)
    {
        if(summarization.getQualifiersList()==null)return 0.0;
        List<QualifierInterface> quaList=summarization.getQualifiersList();
	int quaLength=quaList.size();
        double degree = (double)(2.0 * Math.pow(0.5, quaLength));
        return degree;
        
        
    }
      
      
}
