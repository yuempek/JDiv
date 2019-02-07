package com.yuempek.jdiv;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class Div extends JPanel {
    
    private static final long serialVersionUID = 7196291179716777236L;
    private static Div divInstance = new Div();
    public static String getClassName() {
        return Div.divInstance.getClass().getName(); 
    }
    
    public int paddingLeft, paddingTop, paddingRight, paddingBottom;
    public int marginLeft, marginTop, marginRight, marginBottom;
    public int borderLeft, borderTop, borderRight, borderBottom;
    public int left, top, right, bottom;
    public int height, width;
    
    public SizeUnit leftUnit, topUnit, rightUnit, bottomUnit;
    public SizeUnit heightUnit, widthUnit;
    
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
        this(new DivLayout(), (Div[]) null);
    }
    
    public Div(LayoutManager layout) {
        this(layout, (Div[]) null);
    }
    
    public Div(Div... children) {
        this(new DivLayout(), children);
    }
    
    public Div(LayoutManager layout, Div... children) {
        if (layout != null) this.contentPanelLayout = layout;
        if (children == null) {
            this.contentPanelLayout = new GridLayout();
        }
        
        this.createPanels();
        this.setDefaults();
        
        if (children != null) {
            this.children = children;
            for (Div child : children) {
                child.parent = this;
                this.addComponent(child);
            }
        }
        
        this.drawBorders();
    }

    
    public void setDefaults() {
        this.padding(5);
        this.border(0);
        this.margin(0);
        this.floating(Floating.NONE);
        this.width(-1, SizeUnit.AUTO);
        this.height(-1, SizeUnit.AUTO);
        this.borderColor = Color.GRAY;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
    }
    
    public void createPanels() {
        GridLayout gapLayout = new GridLayout();
        
        this.marginPanel = this;
        this.borderPanel = new JPanel();
        this.paddingPanel = new JPanel();
        this.contentPanel = new JPanel();

        this.marginPanel.add(this.borderPanel);
        this.borderPanel.add(this.paddingPanel);
        this.paddingPanel.add(this.contentPanel);

        this.marginPanel.setLayout(gapLayout);
        this.borderPanel.setLayout(gapLayout);
        this.paddingPanel.setLayout(gapLayout);
        this.contentPanel.setLayout(this.contentPanelLayout);
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
        
        this.drawBorders();
        
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
        
        this.drawBorders();
        
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
        
        this.drawBorders();
        
        return this;
    }
    
    public Div addComponent(String label) {
        this.addComponent(new JLabel(label));
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
    
    
    
    public Div width(int widthPixel) {
        return width(widthPixel, SizeUnit.PIXEL);
    }

    public Div width(float widthPercentage) {
        if (widthPercentage < 0) widthPercentage = 0;
        if (widthPercentage > 1) widthPercentage = 1;
        
        int percentage = (int)(widthPercentage * 100);
        return width(percentage, SizeUnit.PERCENTAGE);
    }
    
    public Div width(SizeUnit unit) {
        return width(this.width, unit);
    }
    
    public Div width(int width, SizeUnit unit) {
        this.width = width;
        this.widthUnit = unit;
        
        this.sizeChanged();
        
        return this;
    }
    
    
    
    public Div height(int heightPixel) {
        return height(heightPixel, SizeUnit.PIXEL);
    }

    public Div height(float heightPercentage) {
        if (heightPercentage < 0) heightPercentage = 0;
        if (heightPercentage > 1) heightPercentage = 1;
        
        int percentage = (int)(heightPercentage * 100);
        return height(percentage, SizeUnit.PERCENTAGE);
    }
    
    public Div height(SizeUnit unit) {
        return height(this.height, unit);
    }
    
    public Div height(int height, SizeUnit unit) {
        this.height = height;
        this.heightUnit = height;
        
        this.sizeChanged();
        
        return this;
    }
    
    private void sizeChanged() {
        Dimension d = null;
        
        if (this.isAbsolute()) {
            d = new Dimension(this.width, this.height);
        }
        
        // if d is null prefferedSize will be calculated every time. 
        this.setPrefferedSize(d);
    }
    
    
    private boolean isAbsolute() {
        if (this.heightUnit == SizeUnit.PIXEL && this.widhtUnit == SizeUnit.PIXEL)
            return true;
        return false;
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
    
    public Div border(Border border) {
        this.contentPanel.setBorder(border);
        return this;
    }
    
    public Div layout(LayoutManager layout) {
        this.contentPanel.setLayout(layout);
        return this;
    }
    
    public void drawBorders() {
        //this.marginPanel.setBorder(new MatteBorder(this.marginTop, this.marginLeft, this.marginBottom, this.marginRight, Color.BLUE));
        //this.paddingPanel.setBorder(new MatteBorder(this.paddingTop, this.paddingLeft, this.paddingBottom, this.paddingRight, Color.GREEN));
        
        this.marginPanel.setBorder(new EmptyBorder(this.marginTop, this.marginLeft, this.marginBottom, this.marginRight));
        this.paddingPanel.setBorder(new EmptyBorder(this.paddingTop, this.paddingLeft, this.paddingBottom, this.paddingRight));
        this.borderPanel.setBorder(new MatteBorder(this.borderTop, this.borderLeft, this.borderBottom, this.borderRight, this.borderColor));
    }
}
