import javax.swing.*;
import java.awt.*;

//class that displays restart frame
//called on game over or upon pressing restart button
//has two private fields - yes and no buttons

public class Comm extends JFrame
{
    private Yes yes;
    private No no;

    public Comm (String s)
    {
        super(s);
        yes = new Yes("Yes");
        no = new No("No");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        add(yes);
        add(no);
        setLayout(new FlowLayout());
        pack();

        //cosmetics
        setLocation(560,400);
        setResizable(false);
        getContentPane().setBackground(Color.lightGray);
    }
}