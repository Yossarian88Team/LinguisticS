/**
 * Klasa okna umożliwiającego edycję wybranych z głównego okna podsumowań i zapisania ich do pliku
 */
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
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import io.TxtIO;

import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class FinalFormView extends JDialog {
    
        private static Logger logger = Logger.getLogger(WagesView.class);
    
    	private JPanel contentPanel = null;
	private JLabel describeJL = null;
        private JTextArea summarizationJT = null;

        private JButton acceptBtn = null;
        private MainView mainView;
        private FileChooser fileChooser;
        
        private String textValue;
        
        
        
                
        
        FinalFormView(MainView mainView)
        {
            	super(mainView);
                this.mainView=mainView;
                fileChooser = new FileChooser();
            
        }
        
        /**
	 * Metoda inicjalizująca okno
	 * 
	 */
	public void initialize(String text) {
		this.setSize(900, 600);
		this.setModal(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Formowanie ostatecznego tekstu");
                this.textValue=text;
		//this.addWindowListener(new ZamykanieOkna(this));
		this.setContentPane(getContentPanel());
	}

       /**
	 * Metoda tworzaca etykiety
	 * 
	 */
	private void initializeLabels() {
            	describeJL = new JLabel();
		describeJL.setBounds(new Rectangle(16, 16, 20, 16));
		describeJL.setText("Ostateczne podsumowanie :");
                
        }
        
        /**
	 * Metoda inicjalizująca pola tekstowe
	 * 
	 */
	private void initializeTextFields() {
            
        	summarizationJT = new JTextArea();
		summarizationJT.setBounds(new Rectangle(10, 50 , 38, 30));
		summarizationJT.setText(textValue);
                
        }
        
        
        
       /**
	 * Metoda tworzaca panel z etykietami i polami tekstowymi
	 * 
	 */
	private JPanel getContentPanel() {
		if (contentPanel == null) {
                    if(describeJL==null)
                        initializeLabels();
                    if(summarizationJT==null)
                        initializeTextFields();    
                    
                    contentPanel = new JPanel();
                    contentPanel.setLayout(new BorderLayout());
                    contentPanel.add(describeJL, BorderLayout.NORTH);
                    contentPanel.add(summarizationJT, BorderLayout.CENTER);
                    
                    
                    contentPanel.add(getAcceptBtn(),BorderLayout.SOUTH);
		}
		return contentPanel;
	}
        
   /**
    * Metoda obsługująca przycisk acceptBtn, odświeża tablicę wag w mainwindow i zamyka okno WagesView
    */
   private JButton getAcceptBtn() {
		if (acceptBtn == null) {
			acceptBtn = new JButton();
			acceptBtn.setText("Akceptuj");
                        acceptBtn.setBounds(150, 350, 100, 50);
			acceptBtn.setToolTipText("Zapisuje ostateczne podsumowanie do pliku");
                        //acceptBtn.setHorizontalAlignment(JButton.CENTER);
                        acceptBtn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent arg0) {
                                        	String path = fileChooser.getPathToTxtFile();
                                                TxtIO.writeTextFile(path, summarizationJT.getText());
                                    }
                                       
                            });
                       
		}
		return acceptBtn;
    }
        
        
    
}
