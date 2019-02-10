package com.yuempek.jdiv;

import javax.swing.JPanel;

public class DivRenderer {
    private Div root;
    private JPanel panel;
    
    public DivRenderer(Div root) {
        this.root = root;
        this.panel = new JPanel();
    }
    
    public JPanel render() {
        panel.removeAll();
        panel = this.render(this.root);
        //this.root.reshape();
        
        return panel;
    }
    
    private JPanel render(Div div) {
        if (div == null) return null;
        
        int totalWidth = 0;
        int totalHeight = 0;
        int horizontalFloatingPointX = 0;
        int horizontalFloatingPointY = 0;
        
        if (div.children != null) {
            for(Div divChild : div.children) {
                JPanel childPanel = render(divChild);
                div.contentPanel.add(childPanel);
                
                switch (divChild.floating) {
                    case LEFT:
                        divChild.left += horizontalFloatingPointX;
                        divChild.top += horizontalFloatingPointY;
                        break;
                    case RIGHT:
                    case NONE:
                    default:
                        divChild.top += totalHeight;
                        horizontalFloatingPointY = divChild.top;
                        break;
                }
                
                if (totalWidth < (divChild.width + divChild.left)) {
                    totalWidth = (divChild.width + divChild.left);
                }

                if (totalHeight < (divChild.height + divChild.top)) {
                    totalHeight = (divChild.height + divChild.top);
                }
                
                horizontalFloatingPointX = divChild.width + divChild.left;
            }
        }
        
        JPanel divPanel = div.marginPanel;
        
        totalWidth += div.marginLeft + div.marginRight +
                      div.borderLeft + div.borderRight +
                      div.paddingLeft + div.paddingRight;
                      
        totalHeight += div.marginTop + div.marginBottom +
                       div.borderTop + div.borderBottom +
                       div.paddingTop + div.paddingBottom;
                      
        if (div.width < totalWidth) 
            div.width = totalWidth;
            
        if (div.height < totalHeight) 
            div.height = totalHeight;
        
        return divPanel;
    }
}
