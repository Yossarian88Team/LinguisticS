

/**
 * MainView.java
 *
 * Główne okno programu
 */
package gui;

import org.apache.log4j.Logger;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Worker;
import gui.parts.*;
import java.awt.Color;

/**
 *
 * @author Ariel
 */

public class MainView extends javax.swing.JFrame {
    
    // Obiekt statyczny danej klasy oraz obiekt obsługujy okno
    private static MainView mainWindow;
    private Worker worker = null;
    public WagesView wagesView=null;
    public FinalFormView finalFormView=null;
    public HelpView helpView=null;
     
    private static Logger logger = Logger.getLogger(MainView.class);
    
    public static boolean WindowsLookAndFeel = false;
    private static final long serialVersionUID = 1L;
    
    // Pola związane z wyświetlanymi elementami okna
    private JScrollPane scrollTableWithSummarizations = null;
    private TableWithSummarizations tableWS = null;
    private JPanel contentPanel = null;
    private JSplitPane splitPanel = null;
    private JToolBar buttonsBar = null;
    public CheckboxTree treeCheckBox=null;
    public TreeOfChoices treeOfChoices=null;
    private JScrollPane scrollTreeOfChoices = null;
    private JTable tableWithSummarizations = null;
    private JSpinner summSpinner = null;
    private JSpinner quaSpinner = null;
    private JButton readDataBtn = null;
    private JButton createHistogramsBtn = null;
    private JButton readChoicesBtn = null;
    private JButton generateBtn = null;
    private JButton wagesViewBtn = null;
    private JButton finalFormViewBtn = null;
    private JButton helpBtn = null;
    
    // Metody związane z tworzeniem samego okna
    
   /**
    * Konstruktor okna.
    */
    public MainView() {
                super();
		if (WindowsLookAndFeel) {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (UnsupportedLookAndFeelException e) {

			} catch (ClassNotFoundException e) {

			} catch (InstantiationException e) {

			} catch (IllegalAccessException e) {

			}
			SwingUtilities.updateComponentTreeUI(this);
		}
		worker = Worker.getInstance();
		worker.setMainView(this);
                
		initialize();
    }
    
   /**
    * Metoda statyczna, która zwraca obiekt okna.
    */
    public static MainView getMainWindow() {
		if (null == mainWindow) {
			mainWindow = new MainView();
		}
		return mainWindow;
    }
    
   /**
    * Metoda inicjalizująca tworzenie obiektów oraz ustawiająca parametry
    */
    
    private void initialize()
    {
        	this.setSize(815, 544);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                this.setContentPane(getContentPanel());
		this.setTitle("Podsumowania lingwistyczne");
                this.wagesView= new WagesView(this);
                this.finalFormView= new FinalFormView(this);
                this.helpView= new HelpView();
    }
    
    // Metody związane z ułożeniem elementów w oknie
    
   /**
    * Metoda zwracająca wszystkie poszczególne grupy elementów okna, jak pasek przycisków, drzewo oraz tabelę z podsumowaniami
    */
    private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getButtonsBar(), BorderLayout.SOUTH);
			contentPanel.add(getSplitPanel(), BorderLayout.CENTER);
		}
		return contentPanel;
    }
    
   /**
    * Metoda zwracająca panel składający się drzewa oraz tabeli podsumowań
    */
    
    private JSplitPane getSplitPanel() {
		if (splitPanel == null) {
			splitPanel = new JSplitPane();
			splitPanel.setOneTouchExpandable(true);
			splitPanel.setDividerSize(8);
			splitPanel.setRightComponent(getScrollTableWithSummarizations());
			splitPanel.setLeftComponent(getScrollTreeOfChoices());
			splitPanel.setDividerLocation(270);
		}
		return splitPanel;
    }
    
    
   /**
    * Metoda zwracająca panel przycisków
    */
    private JToolBar getButtonsBar() {
		if (buttonsBar == null) {
			buttonsBar = new JToolBar();
			buttonsBar.setPreferredSize(new Dimension(18, 30));
			buttonsBar.setFloatable(false);
			buttonsBar.add(getReadDataBtn());
			buttonsBar.addSeparator();
			buttonsBar.add(getReadChoicesBtn());
                        buttonsBar.addSeparator();
                        buttonsBar.add(getQuaSpinner());
                        buttonsBar.addSeparator();
                        buttonsBar.add(getSummSpinner());
                        buttonsBar.addSeparator();
			buttonsBar.add(getWagesViewBtn());
                        buttonsBar.addSeparator();
			buttonsBar.add(getCreateHistogramsBtn());
			buttonsBar.addSeparator();
			buttonsBar.add(getGenerateBtn());
                        buttonsBar.addSeparator();
			buttonsBar.add(getFinalFormViewBtn());
                        buttonsBar.addSeparator();
			buttonsBar.add(getHelpBtn());
		}
		return buttonsBar;
    }
    
    // Metody związane z tworzeniem drzewa z kwantyfikatorami, kwalifikatorami i sumaryzatorami
    
    public TreeOfChoices getTreeOfChoices() {
		if (treeOfChoices == null) {
			treeOfChoices = new TreeOfChoices();
		}
		return treeOfChoices;
    }
    
    private JScrollPane getScrollTreeOfChoices() {
		if (scrollTreeOfChoices == null) {
			scrollTreeOfChoices = new JScrollPane();
			scrollTreeOfChoices.setViewportView(getTreeCheckBox());
		}
		return scrollTreeOfChoices;
    }
     

   private CheckboxTree getTreeCheckBox() {
		if (treeCheckBox == null) {
			treeCheckBox = new CheckboxTree();
			getTreeOfChoices().setTree(treeCheckBox);
		}
		return treeCheckBox;
    }
   
   // metody związane z tabelę wyświetlającą podsumowania
   
   public void setTable(JTable table)
   {
        this.scrollTableWithSummarizations.setViewportView(table);
       	splitPanel.setRightComponent(scrollTableWithSummarizations);
   }
   
   
   public TableWithSummarizations getTableWithSummarizations() {
		if (tableWS == null) {
			tableWS = new TableWithSummarizations();
		}
		return tableWS;
   }
   
   public JTable getTable()
   {
       		if (tableWithSummarizations == null) {
			tableWithSummarizations = new JTable();
			tableWithSummarizations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableWithSummarizations.setShowGrid(true);
			tableWithSummarizations.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tableWithSummarizations.setFillsViewportHeight(true);
			tableWithSummarizations.setAutoCreateRowSorter(true);
			tableWithSummarizations.setModel(getTableWithSummarizations());
			tableWithSummarizations.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}	
				@Override
				public void mousePressed(MouseEvent e) {
				}
				@Override
				public void mouseExited(MouseEvent e) {
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void mouseClicked(MouseEvent e) {
                                              if (e.getClickCount() == 2) {
					      JTable tabela = (JTable)e.getSource();
					      int numerWiersza = tabela.getSelectedRow();
                                              String zaznaczone = tabela.getValueAt(numerWiersza,2).toString();
                                              logger.debug(zaznaczone);
                                              }
			}});
		}
		return tableWithSummarizations;
	}
       
  private JScrollPane getScrollTableWithSummarizations() {
        	if (scrollTableWithSummarizations == null) {
			scrollTableWithSummarizations = new JScrollPane();
			scrollTableWithSummarizations.setViewportView(getTable());
		}
		return scrollTableWithSummarizations;
  }
   
   
   // metody zmieniające kolor tekstu przycisków, wywołane gdy dane zostaną wczytane
   
   public void changeReadDataBtn(){
                readDataBtn.setForeground(Color.black);
   }
   
   public void changeReadChoicesBtn(){
                readChoicesBtn.setForeground(Color.black);
   }
   // Metody tworzące przyciski 
   
   
   /**
    * Metoda tworząca przycisk pobierania danych z pliku csv
    */
   private JButton getReadDataBtn() {
		if (readDataBtn == null) {
			readDataBtn = new JButton();
			readDataBtn.setText("Wczytaj dane");
                        readDataBtn.setForeground(Color.red);
			readDataBtn.setToolTipText("Wczytuje dane z pliku csv");
			readDataBtn.addActionListener(new ActionListener() {
                                @Override
				public void actionPerformed(ActionEvent arg0) {
					worker.readDataFromCsv();
				}

			});
		}
		return readDataBtn;
    }
   
   /**
    * Metoda tworząca przycisk pobierania danych z pliku csv
    */
   private JButton getCreateHistogramsBtn() {
		if (createHistogramsBtn == null) {
			createHistogramsBtn = new JButton();
			createHistogramsBtn.setText("Twórz histogramy");
			createHistogramsBtn.setToolTipText("Tworzy histogramy danych");
			createHistogramsBtn.addActionListener(new ActionListener() {
                                @Override
				public void actionPerformed(ActionEvent arg0) {
					worker.createHistograms();
				}

			});
		}
		return createHistogramsBtn;
    }
   
   /**
    * Metoda tworząca przycisk pobierania danych z pliku csv
    */
   private JButton getReadChoicesBtn() {
		if (readChoicesBtn == null) {
			readChoicesBtn = new JButton();
			readChoicesBtn.setText("Wczytaj cechy");
                        readChoicesBtn.setForeground(Color.red);
			readChoicesBtn.setToolTipText("Wczytuje dane do drzewa cech");
			readChoicesBtn.addActionListener(new ActionListener() {
                                @Override
				public void actionPerformed(ActionEvent arg0) {
					worker.readChoices();
				}

			});
		}
		return readChoicesBtn;
    }
   
   /**
    * Metoda tworząca przycisk pobierania danych z pliku csv
    */
   private JButton getGenerateBtn() {
		if (generateBtn == null) {
			generateBtn = new JButton();
			generateBtn.setText("Generuj");
			generateBtn.setToolTipText("Generuje podsumowania lingwistyczne");
			generateBtn.addActionListener(new ActionListener() {
                                @Override
				public void actionPerformed(ActionEvent arg0) {
					worker.generate();
				}

			});
		}
		return generateBtn;
    }
   
   
    public JSpinner getSummSpinner() {
		if (summSpinner == null) {
			SpinnerNumberModel model = new SpinnerNumberModel(1,1,3,1);
                        summSpinner = new JSpinner(model);
                        summSpinner.setToolTipText("Określenie maksymalnej ilości połączonych podsumowywanych cech.");
		}
		return summSpinner;
    }
    
    public JSpinner getQuaSpinner() {
		if (quaSpinner == null) {
			SpinnerNumberModel model = new SpinnerNumberModel(1,1,3,1);
                        quaSpinner = new JSpinner(model);
                        quaSpinner.setToolTipText("Określenie maksymalnej ilości połączonych cech określających przedmiot podsumowania");
		}
		return quaSpinner;
    }
   
   /**
    * Metoda tworząca przycisk wywołania okna ustawiania wag
    */
   private JButton getWagesViewBtn() {
       
		if (wagesViewBtn == null) {
			wagesViewBtn = new JButton();
			wagesViewBtn.setText("Ustaw wagi");
			wagesViewBtn.setToolTipText("Ustawia wagi miar podsumowań");
			wagesViewBtn.addActionListener(new ActionListener() {
                                @Override
				public void actionPerformed(ActionEvent arg0) {
                                        wagesView.initialize();
					wagesView.setVisible(true);
				}

			});
		}
		return wagesViewBtn;
    }
   
   /**
    * Metoda tworząca przycisk wywołania okna ustawiania wag
    */
   private JButton getFinalFormViewBtn() {
       
		if (finalFormViewBtn == null) {
			finalFormViewBtn  = new JButton();
			finalFormViewBtn .setText("Popraw i zapisz");
			finalFormViewBtn .setToolTipText("Edycja i zapis ostatecznej formy podsumowania");
			finalFormViewBtn .addActionListener(new ActionListener() {
                                @Override
				public void actionPerformed(ActionEvent arg0) {
                                        finalFormView.initialize(createString());
					finalFormView.setVisible(true);
				}

			});
		}
		return finalFormViewBtn;
    }
   
   /**
    * Metoda tworząca przycisk otwierający okno pomocy
    */
   private JButton getHelpBtn() {
		if (helpBtn == null) {
			helpBtn = new JButton();
			helpBtn.setText("Pomoc");
			helpBtn.setToolTipText("Otwórz okno pomocy.");
			helpBtn.addActionListener(new ActionListener() {
                                @Override
				public void actionPerformed(ActionEvent arg0) {
					helpView.setVisible(true);
				}

			});
		}
		return helpBtn;
    }
   
   
   public void refreshWages(double[] wages)
   {
       logger.debug("Zmieniamy ustawienia wag.");
       worker.refreshWages(wages);       
   }
   
   public String createString()
   {
		StringBuilder sb = new StringBuilder();
		sb.append("Podsumowanie:");
                for(String s: worker.finalSummarizations)
                {
                    sb.append("\n " + s);
                }
		return sb.toString();
   }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
