
public class Main
{

    //initialization of gui and programs' core
    public static Core core = new Core();
    public static GUI gui = new GUI(core);
    public static void main(String [] args)
    {
        //method adding gui reference to core
        core.addGUI();
        //method adding gui reference to revert button class
        gui.revertAddGUI();
        //last step of initialization - adding values to buttons representing game fields
        gui.display();
    }
}
