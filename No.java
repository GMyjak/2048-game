import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//class responsible for no button in restart window

public class No extends JButton implements ActionListener
{
    private Core core;
    private GUI gui;

    public No(String s)
    {
        super(s);
        addActionListener(this);
        core = Main.core;
        gui = Main.gui;
        setPreferredSize(new Dimension(150, 30));

        //cosmetics
        setBackground(Color.red);
    }

    //method actionPerformed simply making restart window invisible
    public void actionPerformed(ActionEvent e)
    {
        if(gui.getRestart().getComm() != null)
        {
            gui.getRestart().getComm().setVisible(false);
        }
        if (gui.getRestart().getYouLost() != null)
        {
            gui.getRestart().getYouLost().setVisible(false);
        }
    }

}
