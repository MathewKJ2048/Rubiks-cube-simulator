import cube.Cube;

import javax.swing.*;
import java.util.Scanner;
public class Main
{
    private static void divide()
    {
        for(int i = 1 ; i <= 4 ; i++)
        {
            System.out.println();
        }
    }
    private static void cls()
    {
        System.out.print('\f');
    }
    private static String input()
    {
        return new Scanner(System.in).next();
    }
    //
    public static void main(String args[])
    {
        GUI_Cube f = new GUI_Cube();

        return;
        /*
        Cube c = new Cube();
        String in;
        do
        {
            cls();
            c.print();
            divide();
            in = input();
            if(in.equals("q"))
            {
                break;
            }
            c.process(in);
        }
        while(!in.equals("q"));
        cls();*/
    }
}