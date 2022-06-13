import cube.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GUI_Cube extends JFrame
{
    private final Cube cube = new Cube();
    private JPanel mainPanel;
    private JTextField inputTextField;
    private JButton solveButton;
    private JTextArea logArea;
    private JButton clearButton;
    private JPanel drawPanel;

    private void set_cube()
    {
        drawPanel.repaint();
    }
    private void set_log()
    {
        List<String> history = this.cube.get_history();
        StringBuilder b = new StringBuilder("");
        for(String s : history)b.append(s+"\n");
        this.logArea.setText(b.toString());
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
                        set_log();
                    }
                    catch(Exception ex)
                    {
                        logArea.append("\nunidentified move:" +"\""+input+"\"");
                    }
                }
            }
        });
        clearButton.addActionListener(e -> {
            logArea.setText("");cube.clear_history();
        });
        solveButton.addActionListener(e -> {
            cube.append_history("solution:");
            Cube_solver.solve(cube);
            set_cube();
            set_log();
        });
    }
    public static int scale = 20;
    public static int distance = 20;
    private void createUIComponents() {
        // TODO: place custom component creation code here
        class DrawPanel extends JPanel
        {
            public Color get_colour(char ch)
            {
                if(ch == 'W')return Color.WHITE;
                else if(ch == 'Y')return Color.YELLOW;
                else if(ch == 'B')return Color.BLUE;
                else if(ch == 'G')return Color.GREEN;
                else if(ch == 'O')return Color.ORANGE;
                else if(ch == 'R')return Color.RED;
                else return Color.BLACK;
            }
            public void paint(Graphics g)
            {
                class Polygon
                {
                    int[] x;
                    int[] y;
                    Polygon(int[] x, int[] y, int x_origin, int y_origin, int scale)
                    {
                        this.x = new int[x.length];
                        this.y = new int[y.length];
                        for(int i=0;i<x.length;i++)
                        {
                            this.x[i] = x[i]*scale+x_origin;
                        }
                        for(int i=0;i<y.length;i++)
                        {
                            this.y[i] = -y[i]*scale+y_origin;
                        }
                    }
                    void draw()
                    {
                        g.drawPolygon(x,y,x.length);
                    }
                    void fill()
                    {
                        g.fillPolygon(x,y,x.length);
                    }
                }
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int x1 = 2*i;
                        int x2 = x1+2;
                        int x3 = x1+2;
                        int x4 = x1;
                        int y1 = i-2*j-2;
                        int y2 = y1+1;
                        int y3 = y1+3;
                        int y4 = y1+2;
                        g.setColor(get_colour(cube.get_cube()[j][2][i].face));
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},250,250,scale).fill();
                    }
                }
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int x1 = -2*i;
                        int x2 = x1-2;
                        int x3 = x1-2;
                        int x4 = x1;
                        int y1 = i-2*j-2;
                        int y2 = y1+1;
                        int y3 = y1+3;
                        int y4 = y1+2;
                        g.setColor(get_colour(cube.get_cube()[j][2-i][0].left));
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},250,250,scale).fill();
                    }
                }
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int x1 = 2*(i-j);
                        int x2 = x1+2;
                        int x3 = x1;
                        int x4 = x1-2;
                        int y1 = (i+j);
                        int y2 = y1+1;
                        int y3 = y1+2;
                        int y4 = y1+1;
                        g.setColor(get_colour(cube.get_cube()[0][2-j][i].up));
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},250,250,scale).fill();
                    }
                }
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int x1 = 2*i;
                        int x2 = x1+2;
                        int x3 = x1+2;
                        int x4 = x1;
                        int y1 = -i+2*j;
                        int y2 = y1-1;
                        int y3 = y1+1;
                        int y4 = y1+2;
                        g.setColor(get_colour(cube.get_cube()[2-j][0][2-i].back));
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},250+distance*scale,250,scale).fill();
                    }
                }
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int x1 = -2*i;
                        int x2 = x1-2;
                        int x3 = x1-2;
                        int x4 = x1;
                        int y1 = -i+2*j;
                        int y2 = y1-1;
                        int y3 = y1+1;
                        int y4 = y1+2;
                        g.setColor(get_colour(cube.get_cube()[2-j][i][2].right));
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},250+distance*scale,250,scale).fill();
                    }
                }
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int x1 = 2*(i-j);
                        int x2 = x1+2;
                        int x3 = x1;
                        int x4 = x1-2;
                        int y1 = -(i+j);
                        int y2 = y1-1;
                        int y3 = y1-2;
                        int y4 = y1-1;
                        g.setColor(get_colour(cube.get_cube()[2][j][2-i].down));
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},250+distance*scale,250,scale).fill();
                    }
                }
                //borders
                g.setColor(Color.BLACK);
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int x1 = 2*i;
                        int x2 = x1+2;
                        int x3 = x1+2;
                        int x4 = x1;
                        int y1 = i-2*j-2;
                        int y2 = y1+1;
                        int y3 = y1+3;
                        int y4 = y1+2;
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},250,250,scale).draw();
                    }
                }
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int x1 = -2*i;
                        int x2 = x1-2;
                        int x3 = x1-2;
                        int x4 = x1;
                        int y1 = i-2*j-2;
                        int y2 = y1+1;
                        int y3 = y1+3;
                        int y4 = y1+2;
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},250,250,scale).draw();
                    }
                }
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int x1 = 2*(i-j);
                        int x2 = x1+2;
                        int x3 = x1;
                        int x4 = x1-2;
                        int y1 = (i+j);
                        int y2 = y1+1;
                        int y3 = y1+2;
                        int y4 = y1+1;
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},250,250,scale).draw();
                    }
                }
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int x1 = 2*i;
                        int x2 = x1+2;
                        int x3 = x1+2;
                        int x4 = x1;
                        int y1 = -i+2*j;
                        int y2 = y1-1;
                        int y3 = y1+1;
                        int y4 = y1+2;
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},250+distance*scale,250,scale).draw();
                    }
                }
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int x1 = -2*i;
                        int x2 = x1-2;
                        int x3 = x1-2;
                        int x4 = x1;
                        int y1 = -i+2*j;
                        int y2 = y1-1;
                        int y3 = y1+1;
                        int y4 = y1+2;
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},250+distance*scale,250,scale).draw();
                    }
                }
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int x1 = 2*(i-j);
                        int x2 = x1+2;
                        int x3 = x1;
                        int x4 = x1-2;
                        int y1 = -(i+j);
                        int y2 = y1-1;
                        int y3 = y1-2;
                        int y4 = y1-1;
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},250+distance*scale,250,scale).draw();
                    }
                }
            }
        }

        drawPanel = new DrawPanel();
    }
}
