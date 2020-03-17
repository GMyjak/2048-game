import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//class responsible for restart button

public class Restart extends JButton implements ActionListener
{
    private Comm comm;
    private Comm youLost;

    //constructor

    public Restart(String s)
    {
        super(s);
        setPreferredSize(new Dimension(205, 30));
        addActionListener(this);
        setFocusable(false);
    }

    //method responsible for displaying restart windows
    //if their references are empty, it calls constructor
    //else it just makes them visible

    public void actionPerformed(ActionEvent e)
    {

            if(e.getActionCommand().equals("ABC"))
            {
                if (youLost == null)
                {
                    youLost = new Comm("You lost. Restart?");
                }
                youLost.setVisible(true);
            }
            else
            {
                if (comm == null)
                {
                    comm = new Comm("Are you sure?");
                }
                comm.setVisible(true);
            }

    }

    //two simple methods that pass references

    public Comm getComm()
    {
        return comm;
    }

    public Comm getYouLost()
    {
        return youLost;
    }
}
