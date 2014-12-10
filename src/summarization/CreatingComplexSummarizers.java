/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package summarization;

import java.util.*;

/**
 *
 * @author Ariel
 */
public class CreatingComplexSummarizers {
    HashSet<String> ands = new HashSet<String>();
    HashSet<String> ors = new HashSet<String>();
    List<SummarizerInterface> complexSummarizers=null;
    
    public CreatingComplexSummarizers()
    {
        complexSummarizers=new ArrayList();
    }
    
    public List<SummarizerInterface> create1ComplexSummarizers(List<SummarizerTypeOne> summarizers)
    {
        complexSummarizers.addAll(summarizers);
        return complexSummarizers;
    }
    
    public List<SummarizerInterface> create2ComplexSummarizers(List<SummarizerTypeOne> summarizers)
    {
        List<SummarizerTypeOne> summList=summarizers;
        complexSummarizers.addAll(summarizers);
        
        // AND
        
        for(int i=0;i<summList.size();i++)
        {
            for(int j=1;j<summList.size();j++)
            {
                if(j==i)continue;
                String s = Integer.toString(i) + Integer.toString(j);
                String s2 = Integer.toString(j) + Integer.toString(i);               
                if(containsAndSet(s)==false && containsAndSet(s2)==false)
                {
                    ands.add(s);
                    ands.add(s2);
                    SummarizerAndSummarizer summ = new SummarizerAndSummarizer(summList.get(i),summList.get(j));
                    complexSummarizers.add(summ);
                }
                
            }
        }
        for(int i=0;i<summList.size();i++)
        {
            for(int j=1;j<summList.size();j++)
            {
                if(j==i)continue;
                String s = Integer.toString(i) + Integer.toString(j);
                String s2 = Integer.toString(j) + Integer.toString(i);  
                if(containsOrsSet(s)==false && containsOrsSet(s2)==false)
                {
                    ors.add(s);
                    ors.add(s2);
                    SummarizerOrSummarizer summ = new SummarizerOrSummarizer(summList.get(i),summList.get(j));
                    complexSummarizers.add(summ);
                }
                
            }
        }
        
        return complexSummarizers;
    }
    
    public List<SummarizerInterface> create3ComplexSummarizers(List<SummarizerTypeOne> summarizers)
    {
        List<SummarizerTypeOne> summList=summarizers;
        complexSummarizers.addAll(summarizers);
        
        // AND
        
        for(int i=0;i<summList.size();i++)
        {
            for(int j=1;j<summList.size();j++)
            {
                if(j==i)continue;
                String s = Integer.toString(i) + Integer.toString(j);
                String s2 = Integer.toString(j) + Integer.toString(i);               
                if(containsAndSet(s)==false && containsAndSet(s2)==false)
                {
                    ands.add(s);
                    ands.add(s2);
                    SummarizerAndSummarizer summ = new SummarizerAndSummarizer(summList.get(i),summList.get(j));
                    complexSummarizers.add(summ);
                }
                
            }
        }
        for(int i=0;i<summList.size();i++)
        {
            for(int j=1;j<summList.size();j++)
            {
                if(j==i)continue;
                String s = Integer.toString(i) + Integer.toString(j);
                String s2 = Integer.toString(j) + Integer.toString(i);  
                if(containsOrsSet(s)==false && containsOrsSet(s2)==false)
                {
                    ors.add(s);
                    ors.add(s2);
                    SummarizerOrSummarizer summ = new SummarizerOrSummarizer(summList.get(i),summList.get(j));
                    complexSummarizers.add(summ);
                }
                
            }
        }
        int listSize=complexSummarizers.size();
        for(int j=0;j<summarizers.size();j++)
        {
            for(int i=summarizers.size();i<listSize;i++)
            {
                    if(complexSummarizers.get(i).getListOfSummarizers().contains(complexSummarizers.get(j)))continue;
                    if(listContainsSummarizer(complexSummarizers.get(j),complexSummarizers.get(i))==true)continue;
                    SummarizerOrSummarizer summOr = new SummarizerOrSummarizer(complexSummarizers.get(j),complexSummarizers.get(i));
                    SummarizerAndSummarizer summAnd = new SummarizerAndSummarizer(complexSummarizers.get(j),complexSummarizers.get(i));
                    complexSummarizers.add(summAnd);
                    complexSummarizers.add(summOr);
            }
        }
        return complexSummarizers;
    }
    
    boolean containsAndSet(String s)
    {
        if(ands.contains(s))
        {
            return true;
        }
        return false;
        
    }
    
    boolean containsOrsSet(String s)
    {
        if(ors.contains(s))
        {
            return true;
        }
        return false;
        
    }
    
    boolean containsSummarizer(SummarizerInterface summ, SummarizerInterface summ2)
    {
        for(SummarizerInterface summtemp: summ2.getListOfSummarizers())
            if(summ.getListOfSummarizers().contains(summtemp))
            {
                return true;
            }
        return false;
        
    }
    
    boolean listContainsSummarizer(SummarizerInterface summ,SummarizerInterface summ2)
    {
        for(SummarizerInterface summtemp: complexSummarizers){
                if(summtemp.getListOfSummarizers().contains(summ) && summtemp.getListOfSummarizers().containsAll(summ2.getListOfSummarizers())){
                    System.out.print("listContaintsSummarizer");
                    return true;
                }
        }
        return false;
        
    }
    
}
   

