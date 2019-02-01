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

    
    public void setDefaults() {
        this.padding(0);
        this.border(0);
        this.margin(0);
        this.floating(Floating.NONE);
        this.width(0);
        this.height(0);
        this.borderColor = Color.DARK_GRAY;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.contentPanelLayout = null;
    }
    
    public Div padding(int ltrbPixel) {
        this.padding(ltrbPixel, ltrbPixel, ltrbPixel, ltrbPixel);
        return this;
    }
    
    public Div padding(int lrPixel, int tbPixel) {
        this.padding(lrPixel, tbPixel, lrPixel, tbPixel);
        return this;
    }
    
    public Div padding(int leftPixel, int topPixel, int rightPixel, int bottomPixel) {
        this.paddingLeft = leftPixel;
        this.paddingTop = topPixel;
        this.paddingRight = rightPixel;
        this.paddingBottom = bottomPixel;
        
        return this;
    }
    
    public Div border(int ltrbPixel) {
        this.border(ltrbPixel, ltrbPixel, ltrbPixel, ltrbPixel);
        return this;
    }
    
    public Div border(int ltrbPixel, Color borderColor) {
        this.border(ltrbPixel, ltrbPixel, ltrbPixel, ltrbPixel, borderColor);
        return this;
    }
    
    public Div border(int leftPixel, int topPixel, int rightPixel, int bottomPixel) {
         border(leftPixel, topPixel, rightPixel, bottomPixel, this.borderColor);
        return this;
    }
    
    public Div border(int leftPixel, int topPixel, int rightPixel, int bottomPixel, Color borderColor) {
        this.borderColor =  borderColor;
        
        this.borderLeft = leftPixel;
        this.borderTop = topPixel;
        this.borderRight = rightPixel;
        this.borderBottom = bottomPixel;
        
        return this;
    }
    
    
    public Div margin(int ltrbPixel) {
        this.margin(ltrbPixel, ltrbPixel, ltrbPixel, ltrbPixel);
        return this;
    }
    
    public Div margin(int lrPixel, int tbPixel) {
        this.margin(lrPixel, tbPixel, lrPixel, tbPixel);
        return this;
    }
    
    public Div margin(int leftPixel, int topPixel, int rightPixel, int bottomPixel) {
        this.marginLeft = leftPixel;
        this.marginTop = topPixel;
        this.marginRight = rightPixel;
        this.marginBottom = bottomPixel;
        
        return this;
    }
    
    
    
}
