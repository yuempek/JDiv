package con.yuempek.jdiv;

import java.awt.Color;
import java.awt.Component;
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
    
    public Div addComponent(Component comp) {
        this.addComponent(comp, "default");
    
        return this;
    }
    
    public Div addComponent(Component comp, String className) {
        //TODO add to hastable to modify from className
        this.contentPanel.add(comp);
    
        return this;
    }
    
    
    public Div floating(Floating floating) {
        this.floating = floating;
        return this;
    }
    
    public Div width(int width) {
        this.width = width;
        return this;
    }
    
    public Div height(int height) {
        this.height = height;
        return this;
    }
    
    public Div position(int x, int y) {
        this.position(x, y, -1, -1);
        return this;
    }
    
    public Div position(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        
        return this;
    }
    
    public Div setBorder(Border border) {
        this.contentPanel.setBorder(border);
        return this;
    }
    
    public Div setLayout(LayoutManager layout) {
        this.contentPanel.setLayout(layout);
        return this;
    }
    
    public void createPanels() {
        this.marginPanel = new JPanel();
        this.borderPanel = new JPanel();
        this.paddingPanel = new JPanel();
        this.contentPanel = new JPanel();

        this.marginPanel.add(this.borderPanel);
        this.borderPanel.add(this.paddingPanel);
        this.paddingPanel.add(this.contentPanel);

        this.marginPanel.setLayout(null);
        this.borderPanel.setLayout(null);
        this.paddingPanel.setLayout(null);
        this.contentPanel.setLayout(this.contentPanelLayout);
    }
    
    public void reshape() {
        
        int w = this.width;
        int h = this.height;
        this.marginPanel.setSize(w, h);
        
        w -= this.marginLeft + this.marginRight;
        h -= this.marginTop + this.marginBottom;
        this.borderPanel.setSize(w, h);
        
        w -= this.borderLeft + this.borderRight;
        h -= this.borderTop + this.borderBottom;
        this.paddingPanel.setSize(w, h);
        
        w -= this.paddingLeft + this.paddingRight;
        h -= this.paddingTop + this.paddingBottom;
        this.contentPanel.setSize(w, h);
        
        int x = this.left;
        int y = this.top;
        this.marginPanel.setLocation(x, y);

        x = this.marginLeft;
        y = this.marginTop;
        this.borderPanel.setLocation(x, y);
        
        x = this.borderLeft;
        y = this.borderTop;
        this.paddingPanel.setLocation(x, y);
        
        x = this.paddingLeft;
        y = this.paddingTop;
        this.contentPanel.setLocation(x, y);
        
        this.marginPanel.setBorder(new EmptyBorder(this.marginTop, this.marginLeft, this.marginBottom, this.marginRight));
        this.paddingPanel.setBorder(new EmptyBorder(this.paddingTop, this.paddingLeft, this.paddingBottom, this.paddingRight));
        this.borderPanel.setBorder(new MatteBorder(this.borderTop, this.borderLeft, this.borderBottom, this.borderRight, this.borderColor));
        
        if (this.children != null) {
            for (Div child : this.children) {
                child.reshape;
            }
        }
    }
}
