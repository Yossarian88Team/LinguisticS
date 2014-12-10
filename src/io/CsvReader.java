/*
 * Klasa wczytująca dane z pliku .csv 
 */

package io;

import data.Token;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.*;

/**
 *
 * @author Ariel
 */
public class CsvReader {
    
    private String strFile = "src/data/resources/Pitching.csv";
    

    
    public List<List<Token>> readDataFromFile()
    {
           List<List<Token>> tokensList = new ArrayList();

           try
           {
                //create BufferedReader to read csv file
                BufferedReader br = new BufferedReader( new FileReader(strFile));
                String strLine = "";
                StringTokenizer st = null;

                int lineNumber = 0, tokenNumber = 0;
                        
                //read comma separated file line by line
                while( (strLine = br.readLine()) != null && lineNumber <10001) // && lineNumber <10001
                {
                      List<Token> tokens = new ArrayList();

                      lineNumber++;            
                      //break comma separated line using ","
                      st = new StringTokenizer(strLine, ",");
                                
                      while(st.hasMoreTokens())
                      {
                          
                              String tempValue=st.nextToken();
                              Token tempToken=new Token(tempValue, tokenNumber, lineNumber);
                              tokens.add(tempToken);
                              //display csv values
                              tokenNumber++;
                            //  System.out.println("Line # " + lineNumber +//); 
                             //                           ", Token # " + tokenNumber 
                              //                          + ", Token : "+ tempValue);

                              
                      }
                      tokensList.add(tokens);
                      //reset token number
                      tokenNumber = 0;
       
               }
                        
               
         }
         catch(Exception e)
         {
                System.out.println("Exception while reading csv file: " + e);                   
         }

         return tokensList;

        
    }
    
}