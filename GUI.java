import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.*;

import static java.awt.event.KeyEvent.*;

public class GUI extends javax.swing.JFrame implements KeyListener
{
    private Core core;
    private JButton [][] array;
    private Restart restart;
    private Revert revert;

    //constructor of jframe

    public GUI(Core core)
    {
        super("2048");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(this);

        //passing core reference
        this.core = core;

        //cosmetics
        setPreferredSize(new Dimension(450, 515));
        setLocation(500, 200);
        setResizable(false);
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(31, 46, 46));

        //initializing upper panel + cosmetics
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(425, 40));
        menu.setBackground(Color.darkGray);

        //initializing menu buttons
        restart = new Restart("Restart");
        revert = new Revert("Revert");
        menu.add(restart);
        menu.add(revert);

        //initializing lower panel and adding it both panels to frame
        JPanel game = new JPanel();
        game.setPreferredSize(new Dimension(425, 425));
        game.setBackground(Color.darkGray);
        add(menu);
        add(game);

        //initializing buttons in game panel (lower panel)
        array = new JButton [4][];
        for (int i=0; i<4; i++) {
            array[i] = new JButton[4];
            for (int j = 0; j < 4; j++) {
                array[i][j] = new JButton(i + " " + j);
                array[i][j].setPreferredSize(new Dimension(100, 100));
                array[i][j].setFont(array[i][j].getFont().deriveFont(24.0f));
                game.add(array[i][j]);
                array[i][j].setEnabled(false);
            }
            pack();
        }
    }

    //method that refreshes values and colors of game panel buttons

    public void display()
    {
        for (int i=0; i<4; i++)
        {
            for (int j=0; j<4; j++)
            {
                //refreshing value displayed on buttons
                array[i][j].setText(String.valueOf(core.getValue(i,j)));

                /* strange method copypasted from internet
                 * i couldn't change foreground color of disabled buttons
                 * seems like it was the only way i could fix my problem
                 * this method also appears later
                */
                array[i][j].setUI(new MetalButtonUI() {
                    protected Color getDisabledTextColor() {
                        return Color.white;
                    }
                });

                //switch/case instruction dealing with back- and foreground colors of buttons
                switch (core.getValue(i,j))
                {
                    case 0:
                        array[i][j].setText(" ");
                        array[i][j].setBackground(Color.lightGray);
                        break;
                    case 2:
                        array[i][j].setBackground(new Color(245, 255, 250));
                        array[i][j].setUI(new MetalButtonUI() {
                            protected Color getDisabledTextColor() {
                                return Color.black;
                            }
                        });
                        break;
                    case 4:
                        array[i][j].setBackground(new Color(255, 255, 153));
                        array[i][j].setUI(new MetalButtonUI() {
                            protected Color getDisabledTextColor() {
                                return Color.black;
                            }
                        });
                        break;

                    case 8:
                        array[i][j].setBackground(new Color(255, 133, 51));
                        break;
                    case 16:
                        array[i][j].setBackground(new Color(255, 71, 26));
                        break;
                    case 32:
                        array[i][j].setBackground(new Color(255, 71, 26));
                        break;
                    case 64:
                        array[i][j].setBackground(new Color (255, 51, 0));
                        break;
                    case 128:
                        array[i][j].setBackground(new Color(255, 255, 77));
                        array[i][j].setUI(new MetalButtonUI() {
                            protected Color getDisabledTextColor() {
                                return Color.black;
                            }
                        });
                        break;
                    case 256:
                        array[i][j].setBackground(new Color(255, 255, 51));
                        array[i][j].setUI(new MetalButtonUI() {
                            protected Color getDisabledTextColor() {
                                return Color.black;
                            }
                        });
                        break;
                    case 512:
                        array[i][j].setBackground(new Color(255, 255, 26));
                        array[i][j].setUI(new MetalButtonUI() {
                            protected Color getDisabledTextColor() {
                                return Color.black;
                            }
                        });
                        break;
                    default:
                        if (core.getValue(i,j)>=1024 && core.getValue(i,j)<=2048)
                        {
                            array[i][j].setBackground(new Color(255, 255, 0));
                            array[i][j].setUI(new MetalButtonUI() {
                                protected Color getDisabledTextColor() {
                                    return Color.black;
                                }
                            });
                        }
                        else
                        {
                            array[i][j].setBackground(Color.black);
                            if (core.getValue(i,j)>9000)
                            {
                                array[i][j].setFont(array[i][j].getFont().deriveFont(22.0f));
                            }
                            if (core.getValue(i,j)>90000)
                            {
                                array[i][j].setFont(array[i][j].getFont().deriveFont(19.0f));
                            }
                        }
                        break;
                }
            }
        }
    }

    //method passing gui reference to revert button class

    public void revertAddGUI()
    {
        revert.setGUI(this);
    }

    //methods passing restart and revert buttons references

    public Restart getRestart()
    {
        return restart;
    }

    public Revert getRevert()
    {
        return revert;
    }

    //method calling command that opens lose condition window (renamed "are you sure" window opened by restart button)

    public void loseCondition()
    {
        restart.actionPerformed(new ActionEvent(new Object(), 654, "ABC"));
    }

    //methods from keylistener interface, adding key controls

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case VK_W:
                core.swipe('u');
                break;
            case KeyEvent.VK_S:
                core.swipe('d');
                break;
            case 65:
                core.swipe('l');
                break;
            case 68:
                core.swipe('r');
                break;
            case VK_KP_UP:
                core.swipe('u');
                break;
            case VK_KP_LEFT:
                core.swipe('l');
                break;
            case VK_KP_DOWN:
                core.swipe('d');
                break;
            case VK_KP_RIGHT:
                core.swipe('r');
                break;
        }
        display();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case 38:
                core.swipe('u');
                break;
            case 37:
                core.swipe('l');
                break;
            case 40:
                core.swipe('d');
                break;
            case 39:
                core.swipe('r');
                break;
            case VK_R:
                restart.actionPerformed(new ActionEvent(new Object(),1,"JD"));
                break;
            case VK_BACK_SPACE:
                revert.actionPerformed(new ActionEvent(new Object(), 0, "JD"));
                break;
        }
        display();

    }
}
