/*
 * Klasa będąca szablonem danych podczas wczytywania pliku .csv
 */
package data;

/**
 *
 * @author Ariel
 */
public class Token {
    
    private String value;
    private Integer tokenNumber;
    private Integer lineNumber;
    
    public Token(String pValue, Integer pTokenNumber, Integer pLineNumber)
    {
        this.value=pValue;
        this.tokenNumber=pTokenNumber;
        this.lineNumber=pLineNumber;        
    }
    
    public Integer getTN()
    {
        return this.tokenNumber;
    }
    
    public Integer getLN()
    {
        return this.tokenNumber;
    }
    
    public Integer getIntegerValue()
    {
        return Integer.parseInt(this.value);
    }
    
    public String getStringValue()
    {
        return this.value;
    }
    
    public Double getDoubleValue()
    {
        return Double.parseDouble(this.value);
    }
    

}
