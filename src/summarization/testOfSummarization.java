/*
 * Klasa testująca, w której dane wprowadzane są na tzw. "sztywno".
 */
package summarization;

import java.util.List;
import java.util.ArrayList;
import data.*;
import org.apache.log4j.Logger;
import fuzzycomponents.*;
import summarization.linguisticelements.*;
import summarization.measures.ServiceOfMeasures;
/**
 *
 * @author Ariel
 */
public class testOfSummarization {
    
    public testOfSummarization(List<PlayerStat> players){
        this.playerData=players;
        this.sMeasures = new ServiceOfMeasures(players);
        this.listOfSummarizationTexts=new ArrayList();
        this.measuresOfSummarizations=new ArrayList(); 
        
    }
     
    private List<PlayerStat> playerData;
    private List<Double> universe;
    private static Logger logger = Logger.getLogger(testOfSummarization.class);
    private ServiceOfMeasures sMeasures=null;
    List<String> listOfSummarizationTexts=null;
    List<double[]> measuresOfSummarizations=null;
    
    public void testOfSummarizationTypeOneWithQualifier()
    {
        double startEl = 0;
        double endEl = 10000;
        double stepEl = 1;
        FuzzySpace fSpace = new FuzzySpace(startEl, endEl, stepEl);
        TriangularMembershipFun mem = new TriangularMembershipFun(100, 1000, 2500);
        FuzzySet fset = new FuzzySet(fSpace, mem);
        LinguisticValueTypeOne lVQuantifier = new LinguisticValueTypeOne("średnio", fset);
        QuantifierTypeOne quantifier = new QuantifierTypeOne(lVQuantifier,true);

        // Tworzymy sumaryzator
        startEl = 0;
        endEl = 25;
        stepEl = 0.1;
        FuzzySpace fSpace2 = new FuzzySpace(startEl, endEl, stepEl);
        TriangularMembershipFun mem2 = new TriangularMembershipFun(8, 10, 15);
        FuzzySet fset2 = new FuzzySet(fSpace2, mem2);
        LinguisticValueTypeOne lVSummarizer = new LinguisticValueTypeOne("mało", fset2);
        SummarizerTypeOne summarizer = new SummarizerTypeOne("zwycięstw","wins", mem2, lVSummarizer ); //funkcja przynależności powinna być odpowiednia dla sumaryzatora
        
                // Tworzymy sumaryzator
        startEl = 0;
        endEl = 25;
        stepEl = 0.1;
        FuzzySpace fSpace3 = new FuzzySpace(startEl, endEl, stepEl);
        TriangularMembershipFun mem3 = new TriangularMembershipFun(8, 10, 15);
        FuzzySet fset3 = new FuzzySet(fSpace3, mem3);
        LinguisticValueTypeOne lVQualifier = new LinguisticValueTypeOne("mało", fset3);
        QualifierTypeOne qualifier = new QualifierTypeOne("zwycięstw","wins", mem3, lVQualifier ); //funkcja przynależności powinna być odpowiednia dla sumaryzatora
        
        // Tworzymy podsumowanie 

        SummarizationTypeOne summarization = new SummarizationTypeOne(quantifier,summarizer, playerData);
        //double T1= sMeasures.getDegreeOfTruth(summarization);
        this.listOfSummarizationTexts.add(summarization.getSummarizationText());
       // this.measuresOfSummarizations.add(sMeasures.getTableTypeOne(summarization));
        
        //logger.debug(summarization.getSummarizationText()+ " T1=" + T1);
    }
    
    public void testOfSummarizationTypeOne()
    {
        // więc tutaj chciałbym zobaczyć, czy to w ogóle dziala... potrzebujemy stworzyć
        // 1. Podsumowanie składające się z:
        //    a) sumaryzatora składającego się z 
        //       - nazwy badanej kolumny,
        //       - funkcji przynależności,
        //       - wartości lingwistycznej ( ta zaś składa się głównie z pojedynczego zbioru rozmytego )
        //    b) kwantyfikatora składającego się głównie z 
        //       - wartosci lingwistycznej ( składającej się z pojedynczego zbioru rozmytego) 
        // dlatego jakbyś mógł to utwórz mi tutaj jakieś instancje f. przynależności, i dwóch zbiorów rozmytych bym
        // mógl stworzyć sumaryzatora i kwantyfikatora
        
        // Tworzymy kwantyfikator
        
        double startEl = 0;
        double endEl = 10000;
        double stepEl = 1;
        FuzzySpace fSpace = new FuzzySpace(startEl, endEl, stepEl);
        TriangularMembershipFun mem = new TriangularMembershipFun(100, 1000, 2500);
        FuzzySet fset = new FuzzySet(fSpace, mem);
        LinguisticValueTypeOne lVQuantifier = new LinguisticValueTypeOne("średnio", fset);
        QuantifierTypeOne quantifier = new QuantifierTypeOne(lVQuantifier,true);

        // Tworzymy sumaryzator
        startEl = 0;
        endEl = 25;
        stepEl = 0.1;
        FuzzySpace fSpace2 = new FuzzySpace(startEl, endEl, stepEl);
        TriangularMembershipFun mem2 = new TriangularMembershipFun(8, 10, 15);
        FuzzySet fset2 = new FuzzySet(fSpace, mem2);
        LinguisticValueTypeOne lVSummarizer = new LinguisticValueTypeOne("mało", fset2);
        SummarizerTypeOne summarizer = new SummarizerTypeOne("zwycięstw","wins", mem2, lVSummarizer ); //funkcja przynależności powinna być odpowiednia dla sumaryzatora
        
        // Tworzymy podsumowanie 

        SummarizationTypeOne summarization = new SummarizationTypeOne(quantifier,summarizer, playerData);
       // double T1= sMeasures.getDegreeOfTruth(summarization);
        this.listOfSummarizationTexts.add(summarization.getSummarizationText());
       // this.measuresOfSummarizations.add(sMeasures.getTableTypeOne(summarization));
        
      //  logger.debug(summarization.getSummarizationText()+ " T1=" + T1);
    }

    public List<String> getListOfSummarizationTexts() {
        return listOfSummarizationTexts;
    }

    public void setListOfSummarizationTexts(List<String> listOfSummarizationTexts) {
        this.listOfSummarizationTexts = listOfSummarizationTexts;
    }

    public List<double[]> getMeasuresOfSummarizations() {
        return measuresOfSummarizations;
    }

    public void setMeasuresOfSummarizations(List<double[]> measuresOfSummarizations) {
        this.measuresOfSummarizations = measuresOfSummarizations;
    }
    
    
    
}
