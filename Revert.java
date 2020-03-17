import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//class responsible for revert button

public class Revert extends JButton implements ActionListener
{
    private Core core;
    private GUI gui;

    //constructor

    public Revert(String s)
    {
        super(s);
        setPreferredSize(new Dimension(205, 30));
        addActionListener(this);
        core = Main.core;
        setEnabled(false);
        setFocusable(false);
    }

    //method adding gui reference (little problem with initialization order)

    public void setGUI(GUI gui)
    {
        this.gui = gui;
    }

    //method called upon pressing revert button
    public void actionPerformed(ActionEvent e)
    {
        if(isEnabled())
        {
            //calls core method that reverts one move
            core.revert();

            //disables button (only one move can be reverted)
            setEnabled(false);

            //refreshes values displayed by gui
            gui.display();
        }
    }
}