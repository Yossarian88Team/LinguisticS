/*
 * Klasa implementująca formę drzewa checkbox
 */
package gui.parts;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import summarization.*;

/**
 *
 * @author Ariel
 */

public class TreeOfChoices {
    
    	private DefaultMutableTreeNode nodeOfQuantifiersOne;
        private DefaultMutableTreeNode nodeOfQuantifiersTwo;
	private DefaultMutableTreeNode nodeOfQualifiers;
	private DefaultMutableTreeNode nodeOfSummarizers;
	private DefaultMutableTreeNode root;
	private CheckboxTree tree;
        
        public TreeOfChoices() {
            super();
            root = new DefaultMutableTreeNode("Elementy do zdefiniowania podsumowania");
            nodeOfQuantifiersOne = new DefaultMutableTreeNode("Cecha ilościowa I");
            root.add(nodeOfQuantifiersOne);
            nodeOfQuantifiersTwo = new DefaultMutableTreeNode("Cecha ilościowa II");
            root.add(nodeOfQuantifiersTwo);
            nodeOfQualifiers = new DefaultMutableTreeNode("Cecha określająca graczy");
            root.add(nodeOfQualifiers);
            nodeOfSummarizers = new DefaultMutableTreeNode("Podsumowywana cecha");
            root.add(nodeOfSummarizers);


        }
        


	public DefaultMutableTreeNode getRoot(){
		return root;
	}
	
	public void addQuantifierOne(QuantifierTypeOne quantifier){   
		nodeOfQuantifiersOne.add(new DefaultMutableTreeNode(quantifier));
	}
	
        public void addQuantifierTwo(QuantifierTypeTwo quantifier){   
		nodeOfQuantifiersTwo.add(new DefaultMutableTreeNode(quantifier));
	}
        
	public void addQualifier(QualifierTypeOne qualifier){   
		nodeOfQualifiers.add(new DefaultMutableTreeNode(qualifier));
	}
	
	public void addSummarizer(SummarizerTypeOne summarizer){   
		nodeOfSummarizers.add(new DefaultMutableTreeNode(summarizer));
	}
        
        public List<SummarizerTypeOne> getTaggedSummarizers()
        {
            List<SummarizerTypeOne> summList= new ArrayList();
            DefaultMutableTreeNode root=nodeOfSummarizers;
            int nodes=root.getChildCount();
            for(int k=0;k<nodes;k++)
            {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)root.getChildAt(k);
                TreePath pathNode = new TreePath(node.getPath());
			if(tree.isPathChecked(pathNode)){
				SummarizerTypeOne summ =(SummarizerTypeOne)node.getUserObject();
				summList.add(summ);
			}
            }
            return summList;
        }
        
        public List<QualifierTypeOne> getTaggedQualifiers()
        {
            List<QualifierTypeOne> quaList= new ArrayList();
            DefaultMutableTreeNode root=nodeOfQualifiers;
            int nodes=root.getChildCount();
            for(int k=0;k<nodes;k++)
            {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)root.getChildAt(k);
                TreePath pathNode = new TreePath(node.getPath());
			if(tree.isPathChecked(pathNode)){
				QualifierTypeOne qua =(QualifierTypeOne)node.getUserObject();
				quaList.add(qua);
			}
            }
            return quaList;
        }
        
        
        public List<QuantifierTypeOne> getTaggedQuantifiersOne()
        {
            List<QuantifierTypeOne> quaList= new ArrayList();
            DefaultMutableTreeNode root=nodeOfQuantifiersOne;
            int nodes=root.getChildCount();
            for(int k=0;k<nodes;k++)
            {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)root.getChildAt(k);
                TreePath pathNode = new TreePath(node.getPath());
			if(tree.isPathChecked(pathNode)){
				QuantifierTypeOne qua =(QuantifierTypeOne)node.getUserObject();
				quaList.add(qua);
			}
            }
            return quaList;
        }
        
        public List<QuantifierTypeTwo> getTaggedQuantifiersTwo()
        {
            List<QuantifierTypeTwo> quaList= new ArrayList();
            DefaultMutableTreeNode root=nodeOfQuantifiersTwo;
            int nodes=root.getChildCount();
            for(int k=0;k<nodes;k++)
            {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)root.getChildAt(k);
                TreePath pathNode = new TreePath(node.getPath());
			if(tree.isPathChecked(pathNode)){
				QuantifierTypeTwo qua =(QuantifierTypeTwo)node.getUserObject();
				quaList.add(qua);
			}
            }
            return quaList;
        }
        
        public void refresh(){
		((DefaultTreeModel)tree.getModel()).reload();
		tree.expandAll();
	}
	
	public void erase(){
		nodeOfQuantifiersOne.removeAllChildren();
                nodeOfQuantifiersTwo.removeAllChildren();
		nodeOfQualifiers.removeAllChildren();
		nodeOfSummarizers.removeAllChildren();
		refresh();
	}
        
        public CheckboxTree getTree() {
		return tree;
	}

	public void setTree(CheckboxTree tempTree) {
		this.tree = tempTree;
		DefaultTreeModel model = new DefaultTreeModel(root); 
		tree.setModel(model);
		refresh();
	}
        

    
}
