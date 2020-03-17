import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//class responsible for yes button in restart window

public class Yes extends JButton implements ActionListener
{
    private Core core;
    private GUI gui;

    public Yes(String s)
    {
        super(s);
        addActionListener(this);
        core = Main.core;
        setPreferredSize(new Dimension(150, 30));
        gui = Main.gui;

        //cosmetics
        setBackground(Color.GREEN);
    }

    //method actionPerformed called upon pressing yes in restart window

    public void actionPerformed(ActionEvent e)
    {
        //calls restart method in core
        core.restart();

        //makes restart window invisible
        if (gui.getRestart().getComm() != null)
        {
            gui.getRestart().getComm().setVisible(false);
        }
        if (gui.getRestart().getYouLost() != null)
        {
            gui.getRestart().getYouLost().setVisible(false);
        }

        //calls display method to refresh values displayed by gui
        gui.display();

        //disables revert button
        gui.getRevert().setEnabled(false);
    }
}
