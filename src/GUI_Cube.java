import cube.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GUI_Cube extends JFrame
{
    public static long interval = 500; // interval between steps of solution in milliseconds
    public static long frame_rate = 250; // number of milliseconds interval between drawing successive frames
    public static int scale = 16;       // scale of the whole image (scales the distance also)
    public static int distance = 20;    // distance between the centres along x axis
    // co-ordinates of centre of left cube (changed with scale)
    public static int x=10;
    public static int y=10;
    public static void load_preferences()
    {
        JSONParser jp = new JSONParser();
        try
        {
            JSONObject obj = (JSONObject) jp.parse(new FileReader("program files/config.json"));
            try
            {
                scale = (int)(long)obj.get("scale");
                distance = (int)(long)obj.get("distance");
                frame_rate = (int)(long)obj.get("framerate");
                interval = (int)(long)obj.get("interval");
                x = (int)(long)obj.get("x");
                y = (int)(long)obj.get("y");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(new JFrame(),e.getMessage()+"\nconfig.json has been corrupted, default settings will be used","Warning",JOptionPane.WARNING_MESSAGE);
                rewrite_config();
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(new JFrame(),"config.json not found, using default settings","Warning",JOptionPane.WARNING_MESSAGE);
            rewrite_config();
        }
    }
    public static void rewrite_config()
    {
        JSONObject obj = new JSONObject();
        obj.put("scale",scale);
        obj.put("distance",distance);
        obj.put("framerate",frame_rate);
        obj.put("interval",interval);
        obj.put("x",x);
        obj.put("y",y);
        try
        {
            Files.writeString(Paths.get("program files/config.json"), obj.toString());
        }catch(Exception ignored){}
    }
    private final Cube cube = new Cube();
    private JPanel mainPanel;
    private JTextField inputTextField;
    private JButton solveButton;
    private JTextArea logArea;
    private JButton clearButton;
    private JPanel drawPanel;
    private JButton UButton;
    private JButton UIButton;
    private JButton DButton;
    private JButton DIButton;
    private JButton LButton;
    private JButton LIButton;
    private JButton scrambleButton;
    private JPanel drawBorderPanel;
    private JButton RFButton;
    private JButton RButton;
    private JButton RIButton;
    private JButton FButton;
    private JButton FIButton;
    private JButton BButton;
    private JButton BIButton;
    private JButton TLButton;
    private JButton TRButton;
    private JButton RBButton;
    private JRadioButton stepRadioButton;

    private void set_cube()
    {
        drawPanel.paint(drawPanel.getGraphics());
        set_log();
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
        super("Rubik's cube simulator");
        ImageIcon icon = new ImageIcon("program files/icon.png");
        drawPanel.setMinimumSize(new Dimension(scale*(2*x+distance),scale*(2*y)));
        stepRadioButton.setSelected(true);
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
        class Control
        {
            boolean paint;
            Control()
            {
                paint = false;
            }
        }
        Control control = new Control();
        class AutoPainter extends Thread
        {
            @Override
            public void run()
            {
                while(true)
                {
                    try
                    {
                        if(control.paint)set_cube();
                        Thread.sleep(frame_rate);
                    }catch(Exception ignored){}
                }
            }
        }
        AutoPainter ap = new AutoPainter();
        ap.start();
        solveButton.addActionListener(e -> {
            control.paint = stepRadioButton.isSelected();
            Cube_solver.solve(cube, stepRadioButton.isSelected()?interval:0);
            try{Thread.sleep(stepRadioButton.isSelected()?2*(frame_rate+interval):0);}catch(Exception ex){}
            control.paint = false;
            set_cube();
        });
        UButton.addActionListener(e -> {
            cube.u();
            set_cube();
        });
        UIButton.addActionListener(e -> {
            cube.ui();
            set_cube();
        });
        DButton.addActionListener(e -> {
            cube.d();
            set_cube();
        });
        DIButton.addActionListener(e -> {
            cube.di();
            set_cube();
        });
        LButton.addActionListener(e -> {
            cube.l();
            set_cube();
        });
        LIButton.addActionListener(e -> {
            cube.li();
            set_cube();
        });
        RButton.addActionListener(e -> {
            cube.r();
            set_cube();
        });
        RIButton.addActionListener(e -> {
            cube.ri();
            set_cube();
        });
        FButton.addActionListener(e -> {
            cube.f();
            set_cube();
        });
        FIButton.addActionListener(e -> {
            cube.fi();
            set_cube();
        });
        BButton.addActionListener(e -> {
            cube.b();
            set_cube();
        });
        BIButton.addActionListener(e -> {
            cube.bi();
            set_cube();
        });
        TLButton.addActionListener(e -> {
            cube.turn_r(); // left and right have been switched intentionally to make the controls more intuitive
            set_cube();
        });
        TRButton.addActionListener(e -> {
            cube.turn_l();
            set_cube();
        });
        RFButton.addActionListener(e -> {
            cube.rf();
            set_cube();
        });
        RBButton.addActionListener(e -> {
            cube.rb();
            set_cube();
        });
        scrambleButton.addActionListener(e -> {
            cube.scramble();
            set_cube();
        });
    }
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
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},x*scale,y*scale,scale).fill();
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
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},x*scale,y*scale,scale).fill();
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
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},x*scale,y*scale,scale).fill();
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
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},x*scale+distance*scale,y*scale,scale).fill();
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
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},x*scale+distance*scale,y*scale,scale).fill();
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
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},x*scale+distance*scale,y*scale,scale).fill();
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
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},x*scale,y*scale,scale).draw();
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
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},x*scale,y*scale,scale).draw();
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
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},x*scale,y*scale,scale).draw();
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
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},x*scale+distance*scale,y*scale,scale).draw();
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
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},x*scale+distance*scale,y*scale,scale).draw();
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
                        new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4},x*scale+distance*scale,y*scale,scale).draw();
                    }
                }
            }
        }

        drawPanel = new DrawPanel();
    }
}
