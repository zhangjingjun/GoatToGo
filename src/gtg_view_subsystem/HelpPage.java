package gtg_view_subsystem;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class HelpPage extends JPanel{
	private JEditorPane editorPane;

	public HelpPage(String helpContent){
		this.setPreferredSize(new Dimension(1000, 500));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        editorPane = new JEditorPane();
        editorPane.setSize(new Dimension(1000, 500));
        editorPane.setEditable(false);
        editorPane.setContentType("text/html;charset=ISO-8859-1");
        editorPane.setText(helpContent);	
        editorPane.setAutoscrolls(true);
        
        JScrollPane sp = new JScrollPane(editorPane);
        sp.setSize(new Dimension(1000, 500));
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setEnabled(true);
        this.add(sp);
	}
	
}
