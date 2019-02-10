package com.yuempek.jdiv;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.util.ArrayList;

public class DivLayout implements LayoutManager {
  public DivLayout() {}
  
  @Override
  public void addLayoutComponent(String name, Component comp) {}
  
  @Override
  public void removeLayoutComponent(Component comp) {}
  
  @Override
  public Dimension minimumLayoutSize(Container parent) { return null; }
  
  @Override
  public Dimension preferredLayoutSize(Container container) {
    synchronized (container.getTreeLock()) {
      Insets insets = container.getInsets();
      int componentCounts = container.getComponentCount();
      int w, h;
      
      w = h = 0;
      
      for (int i = 0; i < componentCounts; i++) {
        Component c = container.getComponent(i);
        Dimension d = c.getPreferredSize();
        w = Math.max(w, d.width + c.getX());
        h = Math.max(h, d.height + c.getY());
      }
      
      return new Dimension(insets.left + insets.right + w,
                           insets.top + insets.bottom + h);
    }
  }
  
  @Override
  public void layoutContainer(Container container) {
    ArrayList<Div> divComponents = new ArrayList<Div>();
    synchronized (container.getTreeLock()) {
      int maxwidth = container.getWidth();
      int maxheight = container.getHeight();
      
      int componentCount = container.getComponentCount();
      
      for (int i = 0; i < componentCount; i++) {
        Component child = container.getComponent(i);
        if (child.isVisible()) {
          if (child.getClass().getName() == Div.getClassName()) {
            divComponents.add((Div) child);
          }
        }
      }
      
      
      int totalWidth = 0;
      int totalHeight = 0;
      
      Point horizontalFloatingPoint_LtR = new Point(0, 0);
      Point horizontalFloatingPoint_RtL = new Point(maxwidth, 0);
      
      componentCount = divComponents.size();
      
      for (int i = 0; i < componentCount; i++) {
        Div divChild = divComponents.get(i);
        
        Dimension childPreferredSize = divChild.getPreferredSize();
        
        switch (divChild.floating) {
          case LEFT:
            divChild.left = horizontalFloatingPoint_LtR.x;
            divChild.top = horizontalFloatingPoint_LtR.y;
            horizontalFloatingPoint_LtR.x = divChild.width + divChild.left;
            break;
             
          case RIGHT:
            divChild.left = horizontalFloatingPoint_RtL.x - divChild.width;
            divChild.top = horizontalFloatingPoint_RtL.y;
            horizontalFloatingPoint_RtL.x = divChild.left;
            break;
            
          case NONE:
          default:
            divChild.top = totalHeight;
            horizontalFloatingPoint_LtR.y = divChild.top;
            horizontalFloatingPoint_RtL.y = divChild.top;
            
            switch (divChild.widthUnit) {
              case AUTO:
              case ENLARGE:
                divChild.width = maxwidth;
                break;
                
              case WRAP:
                divChild.width = childPreferredSize.width;
                break;
                
              default:
                break;
            }
            

            switch (divChild.heightUnit) {
              case ENLARGE:
                divChild.height = maxheight;
                break;
                
              case AUTO:
              case WRAP:
                divChild.height = childPreferredSize.height;
                break;
                
              default:
                break;
            }
            
            horizontalFloatingPoint_LtR.x = divChild.width + divChild.left;
            
            break;
        }
        
        divChild.setBounds(divChild.left, divChild.top, divChild.width, divChild.height);
        
        totalWidth = Math.max(totalWidth, (divChild.width + divChild.left));
        totalHeight = Math.max(totalHeight, (divChild.height + divChild.top));
      }
    }
  }


}
