import java.util.Random;
public class Core
{
    //array that contains values displayed in game panel
    private int [][] fields;
    //array used for calling revert function
    private int [][] prev;
    private GUI gui;

    public Core()
    {
        //initializing fields array with zeros
        fields = new int [4][];
        for (int i=0; i<4; i++)
        {
            fields [i] = new int [4];
            for (int j=0; j<4; j++)
            {
                fields[i][j] = 0;
            }
        }

        //adding 2 numbers to array
        add();
        add();

        //initializing prev array
        prev = new int [4][4];
    }

    //adding gui reference
    public void addGUI()
    {
        gui = Main.gui;
    }

    //method that adds randomly chosen number (2 or 4) to random free field
    public void add()
    {
        int x, y;
        Random rand = new Random();
        do
        {
            x = rand.nextInt(4);
            y = rand.nextInt(4);
        }
        while (fields[x][y] != 0);
        Random rand2 = new Random();
        if (rand2.nextInt(9) == 0) {
            fields[x][y] = 4;
        } else {
            fields[x][y] = 2;
        }
    }

    //field vale getter used to refresh displayed numbers
    public int getValue(int x, int y)
    {
        return fields[x][y];
    }

    //big method
    //responsible for performing player moves

    public void swipe(char c)
    {
        // initializing temp array that contains field values from current state of game
        int [][] temp;
        temp = new int [4][];
        for (int i=0; i<4; i++)
        {
            temp[i] = new int [4];
            for (int j=0; j<4; j++)
            {
                temp[i][j] = fields[i][j];
            }
        }

        //switch instruction is responsible for recognizing in which direction players move if performed
        //methods horizontally reverse and transposition are responsible for flipping fields array
        //so that move can be performed always in the same direction
        //and then fields array can be flipped back to normal
        //fields = Core.basicSwipeStep2(Core.basicSwipeStep1(fields)) replaces fields array with post move one
        switch ((int)c)
        {
            case (int)'l':
                fields = Core.basicSwipeStep2(Core.basicSwipeStep1(fields));
                break;
            case (int)'r':
                horizontallyReverse();
                fields = Core.basicSwipeStep2(Core.basicSwipeStep1(fields));
                horizontallyReverse();
                break;
            case (int)'u':
                transposition();
                fields = Core.basicSwipeStep2(Core.basicSwipeStep1(fields));
                transposition();
                break;
            case (int)'d':
                transposition();
                horizontallyReverse();
                fields = Core.basicSwipeStep2(Core.basicSwipeStep1(fields));
                horizontallyReverse();
                transposition();
                break;
        }

        //this fragment of code counts if any item has been moved in fields array during performing move
        //it uses temp array initialized before

        int itemsMoved = 0;

        for (int i=0; i<4; i++)
        {
            for (int j=0; j<4; j++)
            {
                if (fields[i][j] != temp[i][j])
                {
                    itemsMoved++;
                }
            }
        }

        //if no items have been moved, that means that move hasn't been performed
        //so prev array responsible for reverting moves isn't overwritten

        if (itemsMoved != 0)
        {
            gui.getRevert().setEnabled(true);
            add();
            for (int i=0; i<4; i++)
            {
                for (int j=0; j<4; j++)
                {
                    prev[i][j] = temp[i][j];
                }
            }
        }

        //this part of code checks if whole fields array is full
        //so that program doesn't always have to check if player lost

        boolean isFull = true;
        for (int i=0; i<4; i++)
        {
            for (int j=0; j<4; j++)
            {
                if (fields[i][j] == 0)
                {
                    isFull = false;
                }
            }
        }

        //if fields array is full
        //checks if there are any possible moves (loseCondition() method)
        //calls lose condition method in gui, check rest in gui

        if (isFull)
        {
            if (loseCondition())
            {
                gui.loseCondition();
            }
        }
    }

    //first method used in swipe method to generate post move array
    //it checks if there are any holes (zeros) between values in fields array in horizontal or vertical direction(i don't remember)
    //then it moves values towards desired direction omitting holes (ie. to left [0 2 0 2 0] -> [2 2 0 0 0])
    private static int [][] basicSwipeStep1(int array[][])
    {
        int [][] temp = new int [4][];
        for (int i=0; i<4; i++)
        {
            temp[i] = new int [4];
            for (int j=0; j<4; j++)
            {
                temp[i][j] = 0;
            }
        }
        for (int i=0; i<4; i++)
        {
            int position = 0;
            for (int j=0; j<4; j++)
            {
                if (array[i][j] != 0)
                {
                    temp[i][position] = array[i][j];
                    position++;
                }
            }
        }

        return temp;
    }

    //second method checks if adjacent values in fields array are the same (in our move direction)
    //if they are the same it merges them ( [2 2 2 0 0] -> [4 0 2 0 0])
    //then it uses previous method to fill the hole between numbers ([4 0 2 0 0] -> [4 2 0 0 0])
    //returns post move array
    private static int [][] basicSwipeStep2(int temp[][])
    {
        for(int i=0; i<4; i++)
        {
            for (int j=0; j<3; j++)
            {
                if (temp[i][j] == temp[i][j+1])
                {
                    temp[i][j] = 2*temp [i][j];
                    temp[i][j+1] = 0;
                    temp = Core.basicSwipeStep1(temp);
                }
            }
        }
        return temp;
    }

    //transposes fields array
    private void transposition()
    {
        int [][] temp = new int [4][4];
        for (int i=0; i<4; i++)
        {
            for (int j=0; j<4; j++)
            {
                temp[i][j] = fields[j][i];
            }
        }
        fields = temp;
    }

    //reverses fields array horizontally
    private void horizontallyReverse()
    {
        int [][] temp = new int [4][4];
        for (int i=0; i<4; i++)
        {
            for (int j=0; j<4; j++)
            {
                temp[i][j] = fields[i][3-j];
            }
        }
        fields = temp;
    }

    //method called by confirmation button in Comms class window
    //resets fields values to 0 and adds 2 random numbers to array
    public void restart()
    {
        for (int i=0; i<4; i++)
        {
            for (int j=0; j<4; j++)
            {
                fields[i][j] = 0;
            }
        }
        add();
        add();
    }

    //method called by revert button
    //replaces fields array values with those from prev array
    //prev array stores fields values from before player moved
    public void revert()
    {
        for (int i=0; i<4; i++)
        {
            for (int j=0; j<4; j++)
            {
                fields[i][j] = prev[i][j];
            }
        }
    }

    //method that checks if adjacent values are the same (horizontally and vertically)
    //if there is any pair, it means that player has still available move
    //if there are no moves, method returns true (game over)
    public boolean loseCondition()
    {
        for (int i=0; i<4; i++)
        {
            for (int j=0; j<3; j++)
            {
                if ((fields[i][j] == fields[i][j+1]) || (fields[j][i] == fields[j+1][i]))
                {
                    return false;
                }
            }
        }
        return true;
    }
}