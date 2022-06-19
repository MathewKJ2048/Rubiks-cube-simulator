import cube.Cube;

import javax.swing.*;
import java.util.Scanner;
public class Main
{
    public static void main(String args[])
    {
        try
        {   // everything is in a try block incase of unforeseen errors/exceptions
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            GUI_Cube.load_preferences();
            GUI_Cube f = new GUI_Cube();
        } catch (Exception e) {e.printStackTrace();}
    }
}