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
public class CreatingComplexQualifiers {
    
    HashSet<String> ands = new HashSet<String>();
    HashSet<String> ors = new HashSet<String>(); 
    List<QualifierInterface> complexQualifiers=null;
    
    public CreatingComplexQualifiers()
    {
        complexQualifiers=new ArrayList();
    }
    
    public List<QualifierInterface> create1ComplexQualifiers(List<QualifierTypeOne> qualifiers)
    {
        complexQualifiers.addAll(qualifiers);
        return complexQualifiers;
    }
    
    public List<QualifierInterface> create2ComplexQualifiers(List<QualifierTypeOne> qualifiers)
    {
        List<QualifierTypeOne> quaList=qualifiers;
        complexQualifiers.addAll(qualifiers);
        
        // AND
        
        for(int i=0;i<quaList.size();i++)
        {
            for(int j=1;j<quaList.size();j++)
            {
                if(j==i)continue;
                String s = Integer.toString(i) + Integer.toString(j);
                String s2 = Integer.toString(j) + Integer.toString(i);               
                if(containsAndSet(s)==false && containsAndSet(s2)==false)
                {
                    ands.add(s);
                    ands.add(s2);
                    QualifierAndQualifier qua = new QualifierAndQualifier(quaList.get(i),quaList.get(j));
                    complexQualifiers.add(qua);
                }
                
            }
        }
        for(int i=0;i<quaList.size();i++)
        {
            for(int j=1;j<quaList.size();j++)
            {
                if(j==i)continue;
                String s = Integer.toString(i) + Integer.toString(j);
                String s2 = Integer.toString(j) + Integer.toString(i);  
                if(containsOrsSet(s)==false && containsOrsSet(s2)==false)
                {
                    ors.add(s);
                    ors.add(s2);

                    QualifierOrQualifier qua = new QualifierOrQualifier(quaList.get(i),quaList.get(j));
                    complexQualifiers.add(qua);
                }
                
            }
        }
        
        return complexQualifiers;
    }
    
    public List<QualifierInterface> create3ComplexQualifiers(List<QualifierTypeOne> qualifiers)
    {
        List<QualifierTypeOne> quaList=qualifiers;
        complexQualifiers.addAll(qualifiers);
        
        // AND
        
        for(int i=0;i<quaList.size();i++)
        {
            for(int j=1;j<quaList.size();j++)
            {
                if(j==i)continue;
                String s = Integer.toString(i) + Integer.toString(j);
                String s2 = Integer.toString(j) + Integer.toString(i);               
                if(containsAndSet(s)==false && containsAndSet(s2)==false)
                {
                    ands.add(s);
                    ands.add(s2);
                    QualifierAndQualifier qua = new QualifierAndQualifier(quaList.get(i),quaList.get(j));
                    complexQualifiers.add(qua);
                }
                
            }
        }
        for(int i=0;i<quaList.size();i++)
        {
            for(int j=1;j<quaList.size();j++)
            {
                if(j==i)continue;
                String s = Integer.toString(i) + Integer.toString(j);
                String s2 = Integer.toString(j) + Integer.toString(i);  
                if(containsOrsSet(s)==false && containsOrsSet(s2)==false)
                {
                    ors.add(s);
                    ors.add(s2);
                    QualifierOrQualifier qua = new QualifierOrQualifier(quaList.get(i),quaList.get(j));
                    complexQualifiers.add(qua);
                }
                
            }
        }
        int listSize=complexQualifiers.size();
        for(int j=0;j<qualifiers.size();j++)
        {
            for(int i=qualifiers.size();i<listSize;i++)
            {
                    if(complexQualifiers.get(i).getListOfQualifiers().contains(complexQualifiers.get(j)))continue;
                    if(listContainsQualifier(complexQualifiers.get(j),complexQualifiers.get(i))==true)continue;
                    QualifierOrQualifier quaOr = new QualifierOrQualifier(complexQualifiers.get(j),complexQualifiers.get(i));
                    QualifierAndQualifier quaAnd = new QualifierAndQualifier(complexQualifiers.get(j),complexQualifiers.get(i));
                    complexQualifiers.add(quaAnd);
                    complexQualifiers.add(quaOr);
            }
        }
        return complexQualifiers;
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
    
    boolean containsQualifier(QualifierInterface qua, QualifierInterface qua2)
    {
        for(QualifierInterface quatemp: qua2.getListOfQualifiers())
            if(qua.getListOfQualifiers().contains(quatemp))
            {
                return true;
            }
        return false;
        
    }
    
    boolean listContainsQualifier(QualifierInterface qua,QualifierInterface qua2)
    {
        for(QualifierInterface quatemp: complexQualifiers){
                if(quatemp.getListOfQualifiers().contains(qua) && quatemp.getListOfQualifiers().containsAll(qua2.getListOfQualifiers())){
                    System.out.print("listContaintsQualifier");
                    return true;
                }
        }
        return false;
        
    }
    
}
