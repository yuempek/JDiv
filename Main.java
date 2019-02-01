import java.awt.*;
import javax.swing.*;
import com.yuempek.jdiv.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        int width = 500;
        int defaultHeight = 35;
        int defaultButtonWidth = 150;
        
        GridLayout layout = new GridLayout();
        
        Div root = new Div(
                       new Div(
                           new Div(layout)
                               .floating(Floating.LEFT)
                               .addComponent(new JLabel("IP"))
                               .width(40).height(defaultHeight)
                               .padding(5)
                           ,
                           new Div(layout)
                               .floating(Floating.LEFT)
                               .addComponent(new JTextField("localhost"))
                               .width(200).height(defaultHeight)
                               .padding(5)
                           ,
                           new Div(layout)
                               .floating(Floating.LEFT)
                               .addComponent(new JButton("Start Connection"))
                               .width(defaultButtonWidth).height(defaultHeight)
                               .padding(5)
                       ).padding(0, 20)
                       ,
                       new Div(
                           new Div(layout)
                               .floating(Floating.LEFT)
                               .addComponent(new JLabel("Port"))
                               .width(40).height(defaultHeight)
                               .padding(5)
                           ,
                           new Div(layout)
                               .floating(Floating.LEFT)
                               .addComponent(new JTextField("1234"))
                               .width(200).height(defaultHeight)
                               .padding(5)
                           ,
                           new Div(layout)
                               .floating(Floating.LEFT)
                               .addComponent(new JButton("Stop Connection"))
                               .width(defaultButtonWidth).height(defaultHeight)
                               .padding(5)
                       )
                   );
        
        DivRenderer renderer = new DivRenderer(root);
        
        JFrame frame = new JFrame("JDiv Application"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        JPanel panel = renderer.render();
        frame.add(panel);
        
        frame.setSize(500, 100);
        frame.setVisible(true);
        
    }
    
}
