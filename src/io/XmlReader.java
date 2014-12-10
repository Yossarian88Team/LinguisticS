/*
 * Klasa wczytująca dane z plików XML.
 */
package io;

import org.apache.log4j.Logger;

import fuzzycomponents.*;
import summarization.linguisticelements.*;
import summarization.*;


import java.util.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import summarization.*;

/**
 *
 * @author Ariel
 */
public class XmlReader {
    
       private static Logger logger = Logger.getLogger(XmlReader.class);

        List<SummarizerTypeOne> listOfSummarizerOne = null;
        List<QualifierTypeOne> listOfQualifierOne = null;
        List<QuantifierTypeOne> listOfQuantifierOne = null;
        List<QuantifierTypeTwo> listOfQuantifierTwo = null;
        
        public void  readElementsFromFile(String filename) {
            String fileName=filename;
            fileName="elements.xml";
            listOfSummarizerOne = new ArrayList();
            listOfQualifierOne = new ArrayList();
            listOfQuantifierOne = new ArrayList();
            listOfQuantifierTwo = new ArrayList();
            
            try {
                logger.debug("Rozpoczynamy wczytywanie wyników z pliku: " + fileName);
                
		File fXmlFile = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
                
                
                // zaczynamy wczytywać kwantyfikatory typu pierwszego
                logger.debug("Rozpoczynamy wczytywanie kwantyfikatorów pierwszego typu");
		NodeList nList = doc.getElementsByTagName("quantifierTypeOne"); 
		for (int i = 0; i < nList.getLength(); i++) {
                   String name=null;
                   String modifierLabel=null;
                   MembershipFunInter mem=null;
                   FuzzySpace fSpace=null;
                   boolean isRelative;
                   
                   
		   Node nNode = nList.item(i);
                   Element element=(Element)nNode;
                   if(element.getAttribute("type").equalsIgnoreCase("relative"))
                       isRelative=true;
                   else isRelative=false;
                   Node childNode=nNode.getFirstChild();
                   childNode=childNode.getNextSibling();                
                   Element childElement=(Element)childNode;
                   name=childElement.getTextContent();
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                   modifierLabel=childElement.getTextContent();
                   
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                   
                   
                  // logger.debug("atrybut" + childElement.getAttribute("type"));
                   if(childElement.getAttribute("type").equals("continuous")){
                               double startEl = Double.parseDouble(getTagValue("start",childElement));
                               double endEl = Double.parseDouble(getTagValue("end",childElement));
                               double stepEl = Double.parseDouble(getTagValue("step",childElement)); 
                               //logger.debug("StartEl: " + startEl + " " + endEl + " " + stepEl);
                               fSpace = new FuzzySpace(startEl, endEl, stepEl);
                   }else
                   {
                       // tutaj będziemy tworzyć przestrzeń dyskretną,
                   };
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                   logger.debug("atrybut" + childElement.getAttribute("type"));
                   if(childElement.getAttribute("type").equals("triangular")){
                               double a = Double.parseDouble(getTagValue("a",childElement));
                               double b = Double.parseDouble(getTagValue("b",childElement));
                               double c = Double.parseDouble(getTagValue("c",childElement)); 
                               //logger.debug("StartEl: " + a + " " + b + " " + c);
                               mem = new TriangularMembershipFun(a, b, c);
                   }else
                   {
                               //logger.debug("rectengular "+childElement.getNodeName());
                               //logger.debug("rectengular" + getTagValue("a",childElement));
                               double a = Double.parseDouble(getTagValue("a",childElement));
                               double b = Double.parseDouble(getTagValue("b",childElement));
                               mem = new RectengularMembershipFun(a, b);
                   };
                   FuzzySet fset = new FuzzySet(fSpace, mem);
                   LinguisticValueTypeOne lVQuantifier = new LinguisticValueTypeOne(name, fset,modifierLabel); // gdyby było bez modyfikatora wywołalibyśmy konstruktor bez ostatniego parametru
                   QuantifierTypeOne quantifier = new QuantifierTypeOne(lVQuantifier,isRelative);
                   listOfQuantifierOne.add(quantifier);
                }
                
                nList = doc.getElementsByTagName("quantifierTypeTwo"); 
                logger.debug("Rozpoczynamy wczytywanie kwantyfikatorów drugiego typu"); 
		for (int i = 0; i < nList.getLength(); i++) {
                   String name=null;
                   String modifierLabel=null;
                   MembershipFunInter mem=null;
                   FuzzySpace fSpace=null;
                   boolean isRelative;
                   String membershipFunType=null;
                   
                   
		   Node nNode = nList.item(i);
                   Element element=(Element)nNode;
                   if(element.getAttribute("type").equalsIgnoreCase("relative"))
                       isRelative=true;
                   else isRelative=false;
                   Node childNode=nNode.getFirstChild();
                   childNode=childNode.getNextSibling();                
                   Element childElement=(Element)childNode;
                   name=childElement.getTextContent();
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                   
                   modifierLabel=childElement.getTextContent();                   
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                   
                   if(childElement.getAttribute("type").equals("continuous")){
                               double startEl = Double.parseDouble(getTagValue("start",childElement));
                               double endEl = Double.parseDouble(getTagValue("end",childElement));
                               double stepEl = Double.parseDouble(getTagValue("step",childElement)); 
                               //logger.debug("StartEl: " + startEl + " " + endEl + " " + stepEl);
                               fSpace = new FuzzySpace(startEl, endEl, stepEl);
                   }else
                   {
                       // tutaj będziemy tworzyć przestrzeń dyskretną,
                   };
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                   membershipFunType=childElement.getAttribute("typeofmembership");
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                   if(childElement.getAttribute("type").equals("triangular")){
                               double a = Double.parseDouble(getTagValue("a",childElement));
                               double b = Double.parseDouble(getTagValue("b",childElement));
                               double c = Double.parseDouble(getTagValue("c",childElement)); 
                               mem = new TriangularMembershipFun(a, b, c);
                   }else
                   {
                               double a = Double.parseDouble(getTagValue("a",childElement));
                               double b = Double.parseDouble(getTagValue("b",childElement));
                               mem = new RectengularMembershipFun(a, b);
                   };
                   FuzzySet fset = new FuzzySet(fSpace, mem);
                   FuzzySetType2 fset2 = new FuzzySetType2(fset,membershipFunType);
                   LinguisticValueTypeTwo lVQuantifier = new LinguisticValueTypeTwo(name, fset2, modifierLabel); // gdyby było bez modyfikatora wywołalibyśmy konstruktor bez ostatniego parametru
                   QuantifierTypeTwo quantifier = new QuantifierTypeTwo(lVQuantifier,isRelative);
                   listOfQuantifierTwo.add(quantifier);
                }
                logger.debug("Rozpoczynamy wczytywanie kwalifikatorów pierwszego typu");
                // zaczynamy wczytywać kwalifikatorów typu pierwszego
                nList = doc.getElementsByTagName("qualifier"); 
		for (int i = 0; i < nList.getLength(); i++) {
                   String name=null;
                   String label=null;
                   String attribute=null;
                   MembershipFunInter mem=null;
                   FuzzySpace fSpace=null;
                   
                   
		   Node nNode = nList.item(i);
                   Node childNode=nNode.getFirstChild();
                   childNode=childNode.getNextSibling();                
                   Element childElement=(Element)childNode;
                   name=childElement.getTextContent();
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                   label=childElement.getTextContent();
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                   attribute=childElement.getTextContent();
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling(); 
                   childElement=(Element)childNode;
                  // logger.debug("nazwy " + label + " " + name);                  
                  // logger.debug("atrybut " + childElement.getAttribute("type"));
                   if(childElement.getAttribute("type").equals("continuous")){
                               double startEl = Double.parseDouble(getTagValue("start",childElement));
                               double endEl = Double.parseDouble(getTagValue("end",childElement));
                               double stepEl = Double.parseDouble(getTagValue("step",childElement)); 
                               //logger.debug("StartEl: " + startEl + " " + endEl + " " + stepEl);
                               fSpace = new FuzzySpace(startEl, endEl, stepEl);
                   }else
                   {
                       // tutaj będziemy tworzyć przestrzeń dyskretną,
                   };
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                  // logger.debug("atrybut" + childElement.getAttribute("type"));
                   if(childElement.getAttribute("type").equals("triangular")){
                               double a = Double.parseDouble(getTagValue("a",childElement));
                               double b = Double.parseDouble(getTagValue("b",childElement));
                               double c = Double.parseDouble(getTagValue("c",childElement)); 
                               //logger.debug("StartEl: " + a + " " + b + " " + c);
                               mem = new TriangularMembershipFun(a, b, c);
                   }else
                   {
                               double a = Double.parseDouble(getTagValue("a",childElement));
                               double b = Double.parseDouble(getTagValue("b",childElement));
                               mem = new RectengularMembershipFun(a, b);
                   };
                   FuzzySet fset = new FuzzySet(fSpace, mem);
                   LinguisticValueTypeOne lVQualifier = new LinguisticValueTypeOne(name, fset);
                   QualifierTypeOne qualifier = new QualifierTypeOne(label,attribute,mem,lVQualifier);
                   listOfQualifierOne.add(qualifier);
                }
                
                logger.debug("Rozpoczynamy wczytywanie sumaryzatorów pierwszego typu");
                nList = doc.getElementsByTagName("summarizer"); 
		for (int i = 0; i < nList.getLength(); i++) {
                   String name=null;
                   String label=null;
                   String attribute=null;
                   MembershipFunInter mem=null;
                   FuzzySpace fSpace=null;
                   
                   
		   Node nNode = nList.item(i);
                   Node childNode=nNode.getFirstChild();
                   childNode=childNode.getNextSibling();                
                   Element childElement=(Element)childNode;
                   name=childElement.getTextContent();
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                   label=childElement.getTextContent();
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                   attribute=childElement.getTextContent();
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                   logger.debug("label " + label + " n " + name);                  
                   logger.debug("atrybut " + attribute);
                   if(childElement.getAttribute("type").equals("continuous")){
                               double startEl = Double.parseDouble(getTagValue("start",childElement));
                               double endEl = Double.parseDouble(getTagValue("end",childElement));
                               double stepEl = Double.parseDouble(getTagValue("step",childElement)); 
                               //logger.debug("StartEl: " + startEl + " " + endEl + " " + stepEl);
                               fSpace = new FuzzySpace(startEl, endEl, stepEl);
                   }else
                   {
                       // tutaj będziemy tworzyć przestrzeń dyskretną,
                   };
                   childNode=childNode.getNextSibling();
                   childNode=childNode.getNextSibling();
                   childElement=(Element)childNode;
                  // logger.debug("atrybut" + childElement.getAttribute("type"));
                   if(childElement.getAttribute("type").equals("triangular")){
                               double a = Double.parseDouble(getTagValue("a",childElement));
                               double b = Double.parseDouble(getTagValue("b",childElement));
                               double c = Double.parseDouble(getTagValue("c",childElement)); 
                               //logger.debug("StartEl: " + a + " " + b + " " + c);
                               mem = new TriangularMembershipFun(a, b, c);
                   }else
                   {
                               double a = Double.parseDouble(getTagValue("a",childElement));
                               double b = Double.parseDouble(getTagValue("b",childElement));
                               mem = new RectengularMembershipFun(a, b);
                   };
                   FuzzySet fset = new FuzzySet(fSpace, mem);
                   LinguisticValueTypeOne lVSummarizer = new LinguisticValueTypeOne(name, fset);
                   SummarizerTypeOne summarizer = new SummarizerTypeOne(label,attribute,mem,lVSummarizer);
                   listOfSummarizerOne.add(summarizer);
                }

		
            
	  } catch (Exception e) {
		e.printStackTrace();
                logger.debug("Błąd przy przetwarzaniu pliku: " + fileName);
          }
          return;
        }
        
        private String getTagValue(String sTag, Element eElement) {
            NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
            
 
            Node nValue = (Node) nlList.item(0);
 
            return nValue.getNodeValue();
        }

    public List<QualifierTypeOne> getListOfQualifiersOne() {
        return listOfQualifierOne;
    }

    public void setListOfQualifiersOne(List<QualifierTypeOne> listOfQualifiersOne) {
        this.listOfQualifierOne = listOfQualifiersOne;
    }

    public List<QualifierTypeOne> getListOfQualifierOne() {
        return listOfQualifierOne;
    }

    public void setListOfQualifierOne(List<QualifierTypeOne> listOfQualifierOne) {
        this.listOfQualifierOne = listOfQualifierOne;
    }

    public List<QuantifierTypeOne> getListOfQuantifierOne() {
        return listOfQuantifierOne;
    }

    public void setListOfQuantifierOne(List<QuantifierTypeOne> listOfQuantifierOne) {
        this.listOfQuantifierOne = listOfQuantifierOne;
    }

    public List<QuantifierTypeTwo> getListOfQuantifierTwo() {
        return listOfQuantifierTwo;
    }

    public void setListOfQuantifierTwo(List<QuantifierTypeTwo> listOfQuantifierTwo) {
        this.listOfQuantifierTwo = listOfQuantifierTwo;
    }

    public List<SummarizerTypeOne> getListOfSummarizerOne() {
        return listOfSummarizerOne;
    }

    public void setListOfSummarizerOne(List<SummarizerTypeOne> listOfSummarizerOne) {
        this.listOfSummarizerOne = listOfSummarizerOne;
    }
    
    
        
        
    
}
