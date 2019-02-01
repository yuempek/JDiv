package con.yuempek.jdiv;

import java.awt.Color;
import java.awt.Componenet;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class Div {
    public int paddingLeft, paddingTop, paddingRight, paddingBottom;
    public int marginLeft, marginTop, marginRight, marginBottom;
    public int borderLeft, borderTop, borderRight, borderBottom;

    public JPanel marginPanel;
    public JPanel borderPanel;
    public JPanel paddingPanel;
    public JPanel contentPanel;

    public Floating floating;
    
    public Color borderColor;
    
    public Div[] children;
    
    public Div parent;
    
    public LayoutManager contentPanelLayout;
    
    public Div() {
        this((LayoutManager) null, (Div[]) null);
    }
    
    public Div(LayoutManager layout) {
        this(layout, (Div[]) null);
    }
    
    public Div(Div... children) {
        this((LayoutManager) null, children);
    }
    
    public Div(LayoutManager layout, Div... children) {
        this.setDefaults();
        
        if (layout != null) this.contentPanelLayout = layout;
        
        if (children != null) {
            this.children = children;
            
            for (Div child : children) {
                child.parent = this;
            }
        }
        
        this.createPanels();
    }

}
