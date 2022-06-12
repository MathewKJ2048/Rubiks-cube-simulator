import cube.Cube;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class GUI_Cube extends JFrame
{
    private final Cube cube = new Cube();
    private JPanel mainPanel;
    private JTextField inputTextField;
    private JTextArea drawArea;
    private JButton solveButton;
    private JTextArea logArea;
    private JButton clearButton;

    private void set_cube()
    {
        StringBuilder b = new StringBuilder();
        b.append("\n\t ------------------");
        b.append("\n\t|\\  "+cube.cube[0][0][0].up+"  \\  "+cube.cube[0][0][1].up+"  \\  "+cube.cube[0][0][2].up+"  \\");
        b.append("\n\t|"+cube.cube[0][0][0].left+" ------------------");
        b.append("\n\t|\\|\\  "+cube.cube[0][1][0].up+"  \\  "+cube.cube[0][1][1].up+"  \\  "+cube.cube[0][1][2].up+"  \\");
        b.append("\n\t|"+cube.cube[1][0][0].left+"|"+cube.cube[0][1][0].left+" ------------------");
        b.append("\n\t|\\|\\|\\  "+cube.cube[0][2][0].up+"  \\  "+cube.cube[0][2][1].up+"  \\  "+cube.cube[0][2][2].up+"  \\");
        b.append("\n\t\\"+cube.cube[2][0][0].left+"|"+cube.cube[1][1][0].left+"\\"+cube.cube[0][2][0].left+" ------------------");
        b.append("\n\t \\|\\|\\|  "+cube.cube[0][2][0].face+"  |  "+cube.cube[0][2][1].face+"  |  "+cube.cube[0][2][2].face+"  |");
        b.append("\n\t  \\"+cube.cube[2][1][0].left+"\\"+cube.cube[1][2][0].left+" ------------------");
        b.append("\n\t   \\|\\|  "+cube.cube[1][2][0].face+"  |  "+cube.cube[1][2][1].face+"  |  "+cube.cube[1][2][2].face+"  |");
        b.append("\n\t    \\"+cube.cube[2][2][0].left+" ------------------");
        b.append("\n\t     \\|  "+cube.cube[2][2][0].face+"  |  "+cube.cube[2][2][1].face+"  |  "+cube.cube[2][2][2].face+"  |");
        b.append("\n\t       ------------------");
        drawArea.setText(b.toString());
    }
    GUI_Cube()
    {
        super("Rubik's cube");
        ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        set_cube();
        inputTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyChar()=='\n')
                {
                    String input = inputTextField.getText();
                    inputTextField.setText("");
                    try{
                        cube.process(input);
                        set_cube();
                        logArea.append("\n"+input);
                    }
                    catch(Exception ex)
                    {
                        logArea.append("\nunidentified move:" +"\""+input+"\"");
                    }
                    System.out.println("enter");
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.setText("");
            }
        });
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cube_solver.layer_1(cube);
                set_cube();
                drawArea.repaint();
                logArea.append("\nlayer 1 solved");
                logArea.revalidate();
                logArea.repaint();
                time_delay(1000);
                //
                Cube_solver.layer_2(cube);
                set_cube();
                drawArea.repaint();
                logArea.append("\nlayer 2 solved");
                logArea.repaint();
                time_delay(1000);
                //
                Cube_solver.layer_3(cube);
                set_cube();
                drawArea.repaint();
                logArea.append("\nlayer 3 solved");
                logArea.repaint();
            }
        });
    }
    private void time_delay(int mil)
    {
        int c_t = (Calendar.getInstance()).get(Calendar.MILLISECOND) + 1000*(Calendar.getInstance()).get(Calendar.SECOND) + 60*1000*(Calendar.getInstance()).get(Calendar.MINUTE);
        int d;
        do
        {
            d = (Calendar.getInstance()).get(Calendar.MILLISECOND) + 1000*(Calendar.getInstance()).get(Calendar.SECOND) + 60*1000*(Calendar.getInstance()).get(Calendar.MINUTE) - c_t;
        }
        while(d <= mil);
    }
}
