package View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import Schema.Column;
import Schema.DataBase;
import Schema.Table;

public class DataBaseExplorer extends JPanel {

	private DefaultTreeCellRenderer style = new DefaultTreeCellRenderer();
	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	JTree exprorateur;

	public DataBaseExplorer() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(250, 500));
		this.setBorder(BorderFactory.createTitledBorder("Explorateur BD:"));

		this.style.setClosedIcon(new ImageIcon("images/table.png"));
		this.style.setOpenIcon(new ImageIcon("images/table.png"));
		this.style.setLeafIcon(new ImageIcon("images/field.png"));


	}

	public void CreateDatabaseTree(DataBase database) {
		root = new DefaultMutableTreeNode(database.getName());
		for (Table table : database.getTables()) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(
					table.getName());
			System.out.println(table.getName());
			for (Column column : table.getColumns()) {
				DefaultMutableTreeNode subnode = new DefaultMutableTreeNode(
						column.getName());
				node.add(subnode);
			}
			root.add(node);
		}
		this.model=new DefaultTreeModel(root);
		exprorateur=new JTree();
		exprorateur.setModel(model);		
		exprorateur.setCellRenderer(this.style);
		exprorateur.setPreferredSize(new Dimension(240, 500));
		this.add(exprorateur, BorderLayout.WEST);
		model.reload();


	}
}
