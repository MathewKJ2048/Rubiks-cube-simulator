package cube;
public class Cube
{
    char[] colours = new char[6];
    public Block[][][] cube = new Block[3][3][3];
    //
    public Cube()
    {
        this.colours = new char[]{'W','Y','R','O','G','B'};
        set_cube();
    }
    public Cube(Cube c)
    {
        for(int i = 0 ; i < 6 ; i++)
        {
            this.colours[i] = c.colours[i];
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                for(int k = 0 ; k < 3 ; k++)
                {
                    this.cube[i][j][k] = new Block(c.cube[i][j][k]);
                }
            }
        }
    }
    //
    private void make_blocks()
    {
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                for(int k = 0 ; k < 3 ; k++)
                {
                    this.cube[i][j][k] = new Block(k,j,i);
                }
            }
        }
    }
    private void paint()
    {
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[0][i][j].up = this.colours[0];
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[2][i][j].down = this.colours[1];
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[i][j][0].left = this.colours[2];
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[i][j][2].right = this.colours[3];
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                 this.cube[i][0][j].back = this.colours[4];
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[i][2][j].face = this.colours[5];
            }
        }
    }
    private void update()
    {
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                for(int k = 0 ; k < 3 ; k++)
                {
                    this.cube[i][j][k].z = i;
                    this.cube[i][j][k].y = j;
                    this.cube[i][j][k].x = k;
                }
            }
        }
    }
    void set_cube()
    {
        make_blocks();
        paint();
        update();
    }
    //
    public void f()
    {
        Block[][] temp = new Block[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                temp[i][j] = new Block(this.cube[2-j][2][i]);
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[i][2][j] = new Block(temp[i][j]);
                this.cube[i][2][j].f();
            }
        }
        update();
    }
    public void fi()
    {
        Block[][] temp = new Block[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                temp[i][j] = new Block(this.cube[j][2][2-i]);
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[i][2][j] = new Block(temp[i][j]);
                this.cube[i][2][j].fi();
            }
        }
        update();
    }
    //
    public void b()
    {
        Block[][] temp = new Block[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                temp[i][j] = new Block(this.cube[2-j][0][i]);
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[i][0][j] = new Block(temp[i][j]);
                this.cube[i][0][j].f();
            }
        }
        update();
    }
    public void bi()
    {
        Block[][] temp = new Block[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                temp[i][j] = new Block(this.cube[j][0][2-i]);
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[i][0][j] = new Block(temp[i][j]);
                this.cube[i][0][j].fi();
            }
        }
        update();
    }
    //
    public void l()
    {
        Block[][] temp = new Block[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                temp[i][j] = new Block(this.cube[2-j][i][0]);
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[i][j][0] = new Block(temp[i][j]);
                this.cube[i][j][0].ri();
            }
        }
        update();
    }
    public void li()
    {
        Block[][] temp = new Block[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                temp[i][j] = new Block(this.cube[j][2-i][0]);
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[i][j][0] = new Block(temp[i][j]);
                this.cube[i][j][0].r();
            }
        }
        update();
    }
    //
    public void r()
    {
        Block[][] temp = new Block[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                temp[i][j] = new Block(this.cube[j][2-i][2]);
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[i][j][2] = new Block(temp[i][j]);
                this.cube[i][j][2].r();
            }
        }
        update();
    }
    public void ri()
    {
        Block[][] temp = new Block[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                temp[i][j] = new Block(this.cube[2-j][i][2]);
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[i][j][2] = new Block(temp[i][j]);
                this.cube[i][j][2].ri();
            }
        }
        update();
    }
    //
    public void u()
    {
        Block[][] temp = new Block[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                temp[i][j] = new Block(this.cube[0][2-j][i]);
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[0][i][j] = new Block(temp[i][j]);
                this.cube[0][i][j].u();
            }
        }
        update();
    }
    public void ui()
    {
        Block[][] temp = new Block[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                temp[i][j] = new Block(this.cube[0][j][2-i]);
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[0][i][j] = new Block(temp[i][j]);
                this.cube[0][i][j].ui();
            }
        }
        update();
    }
    //
    public void d()
    {
        Block[][] temp = new Block[3][3];
         for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                temp[i][j] = new Block(this.cube[2][2-j][i]);
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[2][i][j] = new Block(temp[i][j]);
                this.cube[2][i][j].u();
            }
        }
        update();
    }
    public void di()
    {
        Block[][] temp = new Block[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                temp[i][j] = new Block(this.cube[2][j][2-i]);
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                this.cube[2][i][j] = new Block(temp[i][j]);
                this.cube[2][i][j].ui();
            }
        }
        update();
    }
    //
    public void turn_r()
    {
        Block[][] temp = new Block[3][3];
        for(int l = 0 ; l < 3 ; l++)
        {
            for(int i = 0 ; i < 3 ; i++)
            {
                for(int j = 0 ; j < 3 ; j++)
                {
                    temp[i][j] = new Block(this.cube[l][2-j][i]);
                }
            }
            for(int i = 0 ; i < 3 ; i++)
            {
                for(int j = 0 ; j < 3 ; j++)
                {
                    this.cube[l][i][j] = new Block(temp[i][j]);
                    this.cube[l][i][j].u();
                }
            }
        }
        update();
    }
    public void turn_l()
    {
        Block[][] temp = new Block[3][3];
        for(int l = 0 ; l < 3 ; l++)
        {
            for(int i = 0 ; i < 3 ; i++)
            {
                for(int j = 0 ; j < 3 ; j++)
                {
                    temp[i][j] = new Block(this.cube[l][j][2-i]);
                }
            }
            for(int i = 0 ; i < 3 ; i++)
            {
                for(int j = 0 ; j < 3 ; j++)
                {
                    this.cube[l][i][j] = new Block(temp[i][j]);
                    this.cube[l][i][j].ui();
                }
            }
        }
        update();
    }
    //
    public void invert()
    {
        Block[][] temp = new Block[3][3];
        for(int l = 0 ; l < 3 ; l++)
        {
            for(int n = 1 ; n <= 2 ; n++)
            {
                for(int i = 0 ; i < 3 ; i++)
                {
                    for(int j = 0 ; j < 3 ; j++)
                    {
                        temp[i][j] = new Block(this.cube[2-j][l][i]);
                    }
                }
                for(int i = 0 ; i < 3 ; i++)
                {
                    for(int j = 0 ; j < 3 ; j++)
                    {
                       this.cube[i][l][j] = new Block(temp[i][j]);
                       this.cube[i][l][j].f();
                    }
                }
            }           
        }
        update();
    }
    //
    public void scramble()
    {
        int r;
        for(int i = 0 ; i < 10000 ; i++)
        {
            r = (int)(Math.random()*1000)%12;
            if(r == 0)
            {
               f();
            }
            else if(r == 1)
            {
               fi();
            }
            else if(r == 2)
            {
               b();
            }
            else if(r == 3)
            {
               bi();
            }
            else if(r == 4)
            {
               u();
            }
            else if(r == 5)
            {
               ui();
            }
            else if(r == 6)
            {
               d();
            }
            else if(r == 7)
            {
               di();
            }
            else if(r == 8)
            {
               r();
            }
            else if(r == 9)
            {
               ri();
            }
            else if(r == 10)
            {
               l();
            }
            else if(r == 11)
            {
               li();
            }
        }
    }
    //
    public int[] get_pos_of(int[] p_pos)
    {
        int[] p = new int[3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                for(int k = 0 ; k < 3 ; k++)
                {
                    if((this.cube[i][j][k].z_o == p_pos[0])&&(this.cube[i][j][k].y_o == p_pos[1])&&(this.cube[i][j][k].x_o == p_pos[2]))
                    {
                        p[0] = i;
                        p[1] = j;
                        p[2] = k;
                    }
                }
            }
        }
        return p;
    }
    public int[] get_p_pos_of(int[] pos)
    {
        int[] p_pos = new int[3];
        p_pos[0] = this.cube[pos[0]][pos[1]][pos[2]].z_o;
        p_pos[1] = this.cube[pos[0]][pos[1]][pos[2]].y_o;
        p_pos[2] = this.cube[pos[0]][pos[1]][pos[2]].x_o;
        return p_pos;
    }
    public char get_col(int[] pos,char f)
    {
        char r = 'v';
        if(f == 'u')
        {
            r = this.cube[pos[0]][pos[1]][pos[2]].up;
        }
        else if(f == 'd')
        {
            r = this.cube[pos[0]][pos[1]][pos[2]].down;
        }
        else if(f == 'l')
        {
            r = this.cube[pos[0]][pos[1]][pos[2]].left;
        }
        else if(f == 'r')
        {
            r = this.cube[pos[0]][pos[1]][pos[2]].right;
        }
        else if(f == 'f')
        {
            r = this.cube[pos[0]][pos[1]][pos[2]].face;
        }
        else if(f == 'b')
        {
            r = this.cube[pos[0]][pos[1]][pos[2]].back;
        } 
        return r;
    }
    public char get_col(int index)
    {
        return this.colours[index];
    }
    //
    public boolean is_solved()
    {
        char[][][] faces = new char[6][3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                faces[0][i][j] = this.cube[0][i][j].up;
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                faces[1][i][j] = this.cube[2][i][j].down;
            }
        }
        //
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                faces[2][i][j] = this.cube[i][j][0].left;
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                faces[3][i][j] = this.cube[i][j][2].right;
            }
        }
        //
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                faces[4][i][j] = this.cube[i][2][j].face;
            }
        }
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                faces[5][i][j] = this.cube[i][0][j].back;
            }
        }
        //
        boolean r = true;
        for(int i = 0 ; i < 6 ; i++)
        {
            if(!face_solved(faces[i]))
            {
                r = false;
            }
        }
        return r;
    }
    private boolean face_solved(char[][] f)
    {
        char col = f[0][0];
        boolean s = true;
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                if(f[i][j] != col)
                {
                    s = false;
                }
            }
        }
        return s;
    }
    //
    //
    public void process(String move) throws Exception
    {
        if(move.equals("u"))
        {
            u();
        }
        else if(move.equals("ui"))
        {
            ui();
        }
        else if(move.equals("d"))
        {
            d();
        }
        else if(move.equals("di"))
        {
            di();
        }
        else if(move.equals("f"))
        {
            f();
        }
        else if(move.equals("fi"))
        {
            fi();
        }
        else if(move.equals("b"))
        {
            b();
        }
        else if(move.equals("bi"))
        {
            bi();
        }
        else if(move.equals("l"))
        {
            l();
        }
        else if(move.equals("li"))
        {
            li();
        }
        else if(move.equals("r"))
        {
            r();
        }
        else if(move.equals("ri"))
        {
            ri();
        }
        else if(move.equals("tr"))
        {
            turn_r();
        }
        else if(move.equals("tl"))
        {
            turn_l();
        }
        else if(move.equals("i"))
        {
            invert();
        }
        else if(move.equals("scr"))
        {
            scramble();
        }
        else throw new Exception("unidentified move");
    }
    public void print()
    {
        System.out.println(" ------------------");
        System.out.println("|\\  "+this.cube[0][0][0].up+"  \\  "+this.cube[0][0][1].up+"  \\  "+this.cube[0][0][2].up+"  \\");
        System.out.println("|"+this.cube[0][0][0].left+" ------------------");
        System.out.println("|\\|\\  "+this.cube[0][1][0].up+"  \\  "+this.cube[0][1][1].up+"  \\  "+this.cube[0][1][2].up+"  \\");
        System.out.println("|"+this.cube[1][0][0].left+"|"+this.cube[0][1][0].left+" ------------------");
        System.out.println("|\\|\\|\\  "+this.cube[0][2][0].up+"  \\  "+this.cube[0][2][1].up+"  \\  "+this.cube[0][2][2].up+"  \\");
        System.out.println("\\"+this.cube[2][0][0].left+"|"+this.cube[1][1][0].left+"\\"+this.cube[0][2][0].left+" ------------------");
        System.out.println(" \\|\\|\\|  "+this.cube[0][2][0].face+"  |  "+this.cube[0][2][1].face+"  |  "+this.cube[0][2][2].face+"  |");
        System.out.println("  \\"+this.cube[2][1][0].left+"\\"+this.cube[1][2][0].left+" ------------------");
        System.out.println("   \\|\\|  "+this.cube[1][2][0].face+"  |  "+this.cube[1][2][1].face+"  |  "+this.cube[1][2][2].face+"  |");
        System.out.println("    \\"+this.cube[2][2][0].left+" ------------------");
        System.out.println("     \\|  "+this.cube[2][2][0].face+"  |  "+this.cube[2][2][1].face+"  |  "+this.cube[2][2][2].face+"  |");
        System.out.println("       ------------------");
    }
    /*
    
     -----------------
    |\  W  \  W  \  W  \
    |R------------------
    |\|\  W  \  W  \  W  \
    |R|R------------------
    |\|\|\  W  \  W  \  W  \
    \R|R\R------------------
     \|\|\|  B  |  B  |  B  |
      \R\R------------------
       \|\|  B  |  B  |  B  |
        \R------------------
         \|  B  |  B  |  B  |
          ------------------
    */
}