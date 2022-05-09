import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.yuempek.jdiv.Div;
import com.yuempek.jdiv.Floating;
import com.yuempek.jdiv.SizeUnit;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        int appWidth = 800;
        int appHeight = 600;
        
        int defaultHeight = 35;
        int defaultButtonWidth = 200;

        
        Div root = new Div(
                           new Div().addComponent("Structure & examples of the JDiv")
                             ,
                           new Div(
                                   new Div(
                                           new Div().addComponent("Margin Panel").padding(0, 0, 0, 15)
                                             ,
                                           new Div(
                                                   new Div().addComponent("Border Panel").padding(0, 0, 0, 15)
                                                     ,
                                                   new Div(
                                                           new Div().addComponent("Padding Panel").padding(0, 0, 0, 15)
                                                             ,
                                                           new Div(
                                                                   new Div().addComponent("Content Panel").padding(0, 0, 0, 15)
                                                                  ).border(1).width(100).height(100)
                                                          ).border(1).width(SizeUnit.WRAP).padding(30, 0, 30, 30)
                                                  ).border(1).width(SizeUnit.WRAP).padding(30, 0, 30, 30)
                                          ).border(1).width(SizeUnit.WRAP).padding(30, 0, 30, 30)
                                  ).margin(0, 20)
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
                           new Div(
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
                             ,
                           new Div(
                                   new Div()
                                     .addComponent("1.row with (10px margin) (20px padding (default)) (1px border)")
                                     .border(1).margin(10).padding(20)
                                     ,
                                   new Div()
                                     .addComponent("2.row with (5px margin) (5px padding (default)) (1px border)")
                                     .border(1).margin(5)
                                     ,
                                   new Div()
                                     .addComponent("3.row with (0px margin) (0px padding) (1px border)")
                                     .border(1).padding(0)
                                     ,
                                   new Div()
                                     .addComponent("4.row with (-1px margin) (0px padding) (1px border)")
                                     .border(1).padding(0).margin(-1)
                                  ).border(1).margin(0, 20).padding(0)
                             ,
                           new Div(
                                   new Div()
                                     .addComponent(new JButton("Button in default Div"))
                                     .padding(5).margin(5).border(0, 1, 0, 1)
                                     ,
                                   new Div()
                                     .addComponent("Floating: NONE, size: Absolute")
                                     .padding(5, 20).border(5, 10, 15, 20).margin(5)
                                     .width(300).height(200)
                                     ,
                                   new Div()
                                     .addComponent("Floating: LEFT, size: Absolute")
                                     .floating(Floating.LEFT)
                                     .width(200).height(400)
                                     .padding(5).border(5).margin(5)
                                     ,
                                   new Div()
                                     .floating(Floating.RIGHT)
                                     .width(200).height(200)
                                     .padding(5).border(5).margin(5)
                                     .addComponent("Floating: RIGHT")
                                  ).padding(5, 20).border(1)
                          );
        
        JFrame frame = new JFrame("JDiv Application"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout());
        
        //JPanel panel = renderer.render();
        JScrollPane scrollPane = new JScrollPane(root);

        frame.add(scrollPane);
        
        frame.setVisible(true);
        frame.setSize(appWidth, appHeight);
        
    }
    
}
