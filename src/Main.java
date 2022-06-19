import cube.Cube;

import javax.swing.*;
import java.util.Scanner;
public class Main
{
    public static void main(String args[])
    {
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {}
        GUI_Cube.load_preferences();
        GUI_Cube f = new GUI_Cube();
    }
}