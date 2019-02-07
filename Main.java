import java.awt.*;
import javax.swing.*;
import com.yuempek.jdiv.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        int appWidth = 500;
        int appHeight = 500;

        GridLayout layout = new GridLayout();
        
        Div root = new Div(
                       new Div(
                           new Div()
                               .addComponent(new JButton("Test Button"))
                               .padding(5).border(5).margin(5)
                           ,
                           new Div()
                               .addComponent("JLabel text")
                               .padding(5, 20).border(5, 10, 15, 20).margin(5)
                               .width(200).height(200)
                           ,
                           new Div()
                               .floating(Floating.LEFT)
                               .width(100).height(400)
                               .padding(5).border(5).margin(5)
                           ,
                           new Div()
                               .floating(Floating.RIGHT)
                               .width(200).height(200)
                               .padding(5).border(5).margin(5)
                       ).padding(5, 20)
                       ,
                       new Div(
                           new Div()
                               .floating(Floating.LEFT)
                               .addComponent("IP")
                               .width(40).height(defaultHeight)
                           ,
                           new Div()
                               .floating(Floating.LEFT)
                               .addComponent(new JTextField("localhost"))
                               .width(200).height(defaultHeight)
                           ,
                           new Div()
                               .floating(Floating.LEFT)
                               .addComponent(new JButton("Start Connection"))
                               .width(defaultButtonWidth).height(defaultHeight)
                       )
                       ,
                       new Div(new FlowLayout(),
                           new Div()
                               .floating(Floating.LEFT)
                               .addComponent("Port")
                               .width(40).height(defaultHeight)
                           ,
                           new Div()
                               .floating(Floating.LEFT)
                               .addComponent(new JTextField("1234"))
                               .width(200).height(defaultHeight)
                           ,
                           new Div()
                               .floating(Floating.LEFT)
                               .addComponent(new JButton("Stop Connection"))
                               .width(defaultButtonWidth).height(defaultHeight)
                       )
                   );
        
        JFrame frame = new JFrame("JDiv Application"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new DivLayout());
        
        JPanel panel = renderer.render();
        frame.add(root);
        
        frame.setSize(appWidth, appHeight);
        frame.setVisible(true);
        
    }
    
}
