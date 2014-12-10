
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.LayoutStyle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class WagesView extends JDialog {
    
        private static Logger logger = Logger.getLogger(WagesView.class);
    
    	private JPanel ContentPanel = null;
	private JLabel T1JL = null;
	private JLabel T2JL = null;
	private JLabel T3JL = null;
	private JLabel T4JL = null;
	private JLabel T5JL = null;
	private JLabel T6JL = null;
	private JLabel T7JL = null;
	private JLabel T8JL = null;
	private JLabel T9JL = null;
	private JLabel T10JL = null;
	private JLabel T11JL = null;
        private JTextField T1JT = null;
	private JTextField T2JT = null;
	private JTextField T3JT = null;
	private JTextField T4JT = null;
	private JTextField T5JT = null;
	private JTextField T6JT = null;
	private JTextField T7JT = null;
	private JTextField T8JT = null;
	private JTextField T9JT = null;
	private JTextField T10JT = null;
	private JTextField T11JT = null;
        private JButton acceptBtn = null;
        private MainView mainView;
        
        private double[] wages;
        
        
        
                
        
        WagesView(MainView mainView)
        {
            	super(mainView);
                this.mainView=mainView;
		//initialize();
		wages = new double[11];
		for (int i = 0; i < wages.length; i++) {
			wages[i] = 1.0d;
		}
            
        }
        
        /**
	 * Metoda inicjalizująca okno
	 * 
	 */
	public void initialize() {
		this.setSize(400, 400);
		this.setModal(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Konfiguracja wag ocen podsumowań");
		//this.addWindowListener(new ZamykanieOkna(this));
		this.setContentPane(getContentPanel());
	}

       /**
	 * Metoda tworzaca etykiety
	 * 
	 */
	private void initializeLabels() {
            	T1JL = new JLabel();
		T1JL.setBounds(new Rectangle(16, 16, 200, 16));
		T1JL.setText("T1 ( stopień prawdziwości ) :");
                
                T2JL = new JLabel();
		T2JL.setBounds(new Rectangle(14, 42, 200, 16));
		T2JL.setText("T2 ( stopień precyzyjności ) :");
                
                T3JL = new JLabel();
		T3JL.setBounds(new Rectangle(14, 70, 200, 16));
		T3JL.setText("T3 ( stopień pokrycia ) :");
                
                T4JL = new JLabel();
		T4JL.setBounds(new Rectangle(14, 98, 29, 16));
		T4JL.setText("T4 ( stopień trafności ) :");
                
                T5JL = new JLabel();
		T5JL.setBounds(new Rectangle(14, 126, 29, 16));
		T5JL.setText("T5 ( długość podsumowania ) :");

                T6JL = new JLabel();
		T6JL.setBounds(new Rectangle(14, 154, 29, 16));
		T6JL.setText("T6 ( st. precyzji kwantyfikatora ) :");
            
            	T7JL = new JLabel();
		T7JL.setBounds(new Rectangle(14, 182, 29, 16));
		T7JL.setText("T7 ( st. kard. kwantyfikatora ) :");
                
                T8JL = new JLabel();
		T8JL.setBounds(new Rectangle(14, 210, 29, 16));
		T8JL.setText("T8 ( st. kard. sumaryzatora ) :");
                
                T9JL = new JLabel();
		T9JL.setBounds(new Rectangle(14, 238, 29, 16));
		T9JL.setText("T9 ( st. precyzji kwalifikatora ) :");
                
                T10JL = new JLabel();
		T10JL.setBounds(new Rectangle(14, 266, 29, 16));
		T10JL.setText("T10 ( st. kard. kwalifikatora ) :");
                
                T11JL = new JLabel();
		T11JL.setBounds(new Rectangle(14, 294, 29, 16));
		T11JL.setText("T11 ( długość kwalifikatora ) :");
        }
        
        /**
	 * Metoda inicjalizująca pola tekstowe
	 * 
	 */
	private void initializeTextFields() {
            
        	T1JT = new JTextField();
		T1JT.setBounds(new Rectangle(49, 14, 85, 20));
		T1JT.setText("1.0");
		T1JT.setHorizontalAlignment(JTextField.RIGHT);
                
                T2JT = new JTextField();
		T2JT.setBounds(new Rectangle(49, 14, 85, 20));
		T2JT.setText("1.0");
		T2JT.setHorizontalAlignment(JTextField.RIGHT);
                
                T3JT = new JTextField();
		T3JT.setBounds(new Rectangle(49, 14, 85, 20));
		T3JT.setText("1.0");
		T3JT.setHorizontalAlignment(JTextField.RIGHT);
                
                T4JT = new JTextField();
		T4JT.setBounds(new Rectangle(49, 14, 85, 20));
		T4JT.setText("1.0");
		T4JT.setHorizontalAlignment(JTextField.RIGHT);
                
                T5JT = new JTextField();
		T5JT.setBounds(new Rectangle(49, 14, 85, 20));
		T5JT.setText("1.0");
		T5JT.setHorizontalAlignment(JTextField.RIGHT);
                
                T6JT = new JTextField();
		T6JT.setBounds(new Rectangle(49, 14, 85, 20));
		T6JT.setText("1.0");
		T6JT.setHorizontalAlignment(JTextField.RIGHT);
                
                T7JT = new JTextField();
		T7JT.setBounds(new Rectangle(49, 14, 85, 20));
		T7JT.setText("1.0");
		T7JT.setHorizontalAlignment(JTextField.RIGHT);
                
                T8JT = new JTextField();
		T8JT.setBounds(new Rectangle(49, 14, 85, 20));
		T8JT.setText("1.0");
		T8JT.setHorizontalAlignment(JTextField.RIGHT);
                
                T9JT = new JTextField();
		T9JT.setBounds(new Rectangle(49, 14, 85, 20));
		T9JT.setText("1.0");
		T9JT.setHorizontalAlignment(JTextField.RIGHT);
                
                T10JT = new JTextField();
		T10JT.setBounds(new Rectangle(49, 14, 85, 20));
		T10JT.setText("1.0");
		T10JT.setHorizontalAlignment(JTextField.RIGHT);
                
                T11JT = new JTextField();
		T11JT.setBounds(new Rectangle(49, 14, 85, 20));
		T11JT.setText("1.0");
		T11JT.setHorizontalAlignment(JTextField.RIGHT);
        }
        
        
        
       /**
	 * Metoda tworzaca panel z etykietami i polami tekstowymi
	 * 
	 */
	private JPanel getContentPanel() {
		if (ContentPanel == null) {
                    if(T11JL==null)
                        initializeLabels();
                    if(T11JT==null)
                        initializeTextFields();    
                    
                    ContentPanel = new JPanel();
                    ContentPanel.setLayout(new GridLayout(12,2));
                    ContentPanel.add(T1JL, null);
                    ContentPanel.add(T1JT, null);
                    
                    ContentPanel.add(T2JL, null);
                    ContentPanel.add(T2JT, null);
                    
                    ContentPanel.add(T3JL, null);
                    ContentPanel.add(T3JT, null);                   
                    
                    ContentPanel.add(T4JL, null);
                    ContentPanel.add(T4JT, null);
                    
                    ContentPanel.add(T5JL, null);
                    ContentPanel.add(T5JT, null);
                    
                    ContentPanel.add(T6JL, null);
                    ContentPanel.add(T6JT, null);
                                        
                    ContentPanel.add(T7JL, null);
                    ContentPanel.add(T7JT, null);
                    
                    ContentPanel.add(T8JL, null);
                    ContentPanel.add(T8JT, null);
                    
                    ContentPanel.add(T9JL, null);
                    ContentPanel.add(T9JT, null);
                    
                    ContentPanel.add(T10JL, null);
                    ContentPanel.add(T10JT, null);
                    
                    ContentPanel.add(T11JL, null);
                    ContentPanel.add(T11JT, null);
                    
                    ContentPanel.add(getAcceptBtn(),null);
		}
		return ContentPanel;
	}
        
   /**
    * Metoda obsługująca przycisk acceptBtn, odświeża tablicę wag w mainwindow i zamyka okno WagesView
    */
   private JButton getAcceptBtn() {
		if (acceptBtn == null) {
			acceptBtn = new JButton();
			acceptBtn.setText("Akceptuj");
			acceptBtn.setToolTipText("Ustawia wagi miar podsumowań");
                        acceptBtn.setHorizontalAlignment(JButton.CENTER);
                        acceptBtn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent arg0) {
                                        getWages();
                                    	mainView.refreshWages(wages);
                                    }

                            });
                       
		}
		return acceptBtn;
    }
        
   /**
    * Metoda aktualizująca wartość tabeli wagi
    */
   private boolean getWages() {
  
           try {
		wages[0] = Double.parseDouble(T1JT.getText());
		wages[1] = Double.parseDouble(T2JT.getText());
		wages[2] = Double.parseDouble(T3JT.getText());
		wages[3] = Double.parseDouble(T4JT.getText());
		wages[4] = Double.parseDouble(T5JT.getText());
		wages[5] = Double.parseDouble(T6JT.getText());
		wages[6] = Double.parseDouble(T7JT.getText());
		wages[7] = Double.parseDouble(T8JT.getText());
		wages[8] = Double.parseDouble(T9JT.getText());
		wages[9] = Double.parseDouble(T10JT.getText());
		wages[10] = Double.parseDouble(T11JT.getText());
	   }catch(NumberFormatException ex){
				
                logger.debug("Błąd ustawienia wag");
                return false;
	   }
           return true;
    }
        
    
}
