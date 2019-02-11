# Div blocks for Java
JDiv is used to desing a window with blocks like divs in html.

JDiv has 2 base classes;
1) Div: is a structure class to make user friendly component relation.
2) DivLayout: is Layout manager for divs.

## Usage
Div is added like a JComponent.

    JFrame frame = new JFrame("JDiv Application"); 
    frame.add(new Div());

Div can accept, N Div component as parameter.

    frame.add(new Div(new Div(), new Div(), new Div(), new Div(), new Div(), new Div()));

A Div can be set all parameters with **method chaining**

    frame.add(new Div(
              new Div()
                  .width(200)
                  .height(200)
                  .padding(10) 
                  .border(10)
                  .margin(10)
                  .floating(Floating.NONE)
                  .addComponent(new JButton("Button"))
              ,
              new Div()
                  .width(SizeUnit.AUTO)             //AUTO, WRAP, ENLARGE, PIXEL, PERCENTAGE(Not Active)
                  .height(SizeUnit.WRAP)
                  .padding(10)                      // (ltrb) or (leftright, topbottom) or (left, top, right, bottom)
                  .border(5, 10, 15, 20, Color.RED) // (ltrb[, Color]) or (left, top, right, bottom[, Color])
                  .margin(5, 10)                    // (ltrb) or (leftright, topbottom) or (left, top, right, bottom)
                  .floating(Floating.NONE)          // NONE: new line, LEFT: snap to right of the previous div, RIGHT: snap to left of the previous right one
                  .addComponent("just a string for JLabel")
           )
    );
