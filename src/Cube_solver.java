import cube.Cube;
import java.util.Scanner;
public class Cube_solver
{
    public static void turn_till_face(Cube c,int[] p_pos)
    {
        while(!is_face(c,p_pos))
        {
            c.turn_r();
        }
    }
    //
    public static int[][] centres()
    {
        return new int[][]{
                            {1,2,1},
                            {1,1,0},
                            {1,0,1},
                            {1,1,2}
                           };
    }
    public static int[][] v_edges(int l)
    {
        return new int[][]{
                            {l,2,0},
                            {l,0,0},
                            {l,0,2},
                            {l,2,2}
                           };
    }
    public static int lvl(Cube c,int[] p_pos)
    {
        return c.get_pos_of(p_pos)[0];
    }
    public static int side(Cube c,int[] p_pos)
    {
        return c.get_pos_of(p_pos)[2];
    }
    public static char face_colour(Cube c,int[] pos,char ch)
    {
        if(ch == 'p')
        {
            return (c.get_col(c.get_pos_of(pos),'f'));
        }
        else if(ch == 'c')
        {
            return (c.get_col(pos,'f'));
        }
        else
        {
            return c.get_col(0);
        }
    }
    //
    public static boolean is_same(Cube c,int[] pos,char ch)
    {
        if(ch == 'p')
        {
            if(c.get_col(c.get_pos_of(pos),'f') == c.get_col(centres()[0],'f'))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(ch == 'c')
        {
            if(c.get_col(pos,'f') == c.get_col(centres()[0],'f'))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }      
    }
    public static boolean is_face(Cube c,int[] p_pos)
    {
        if(c.get_pos_of(p_pos)[1] == 2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean is_correct_pos_c(Cube c,int[] pos)
    {
        int[] p_pos = c.get_p_pos_of(pos);
        boolean r = true; 
        for(int i = 0 ; i < 3 ; i++)
        {
            if(p_pos[i] != pos[i])
            {
                r = false;
            }
        }
        return r;
    }
    public static boolean is_correct_pos_p(Cube c,int[] p_pos)
    {
        int[] pos = c.get_pos_of(p_pos);
        boolean r = true; 
        for(int i = 0 ; i < 3 ; i++)
        {
            if(pos[i] != p_pos[i])
            {
                r = false;
            }
        }
        return r;
    }
    //
    //
    private static void white_cross(Cube c)
    {        
        int[] p_pos_c;
        int[] p_pos_p;
        for(int i = 0 ; i < 4 ; i++)
        {
            p_pos_c = centres()[i];
            p_pos_p = new int[]{0,p_pos_c[1],p_pos_c[2]};
            turn_till_face(c,p_pos_p);
            if(lvl(c,p_pos_p) == 0)
            {
                c.f();               
                c.f();               
            }
            else if(lvl(c,p_pos_p) == 1)
            {
                if(side(c,p_pos_p) == 0)
                {
                    c.fi();                   
                    c.d();                   
                    c.f();                   
                }
                else if(side(c,p_pos_p) == 2)
                {
                    c.f();                
                    c.d();                    
                    c.fi();                   
                }
            }
            //
            turn_till_face(c,p_pos_c);
            while(!is_face(c,p_pos_p))
            {
                c.d();               
            }
            //
            if(!is_same(c,p_pos_p,'p'))
            {
                c.fi();
                c.ui(); 
                c.r();
                c.u();
            }
            else
            {
                c.f();               
                c.f();               
            }
            
        }
    }
    //
    private static void white_corners(Cube c)
    {
        int[] p_pos_c;
        int[] p_pos_crn;
        for(int i = 0 ; i < 4 ; i++)
        {
            p_pos_c = centres()[i];
            p_pos_crn = v_edges(0)[i];
            turn_till_face(c,p_pos_crn);
            
            if(lvl(c,p_pos_crn) == 0)
            {
                if(side(c,p_pos_crn) == 0)
                {
                    c.fi();                   
                    c.d();                   
                    c.f();                   
                }
                else if(side(c,p_pos_crn) == 2)
                {
                    c.f();                
                    c.d();                    
                    c.fi();                   
                }
            }
            //
            turn_till_face(c,p_pos_c);
            while(!is_face(c,p_pos_crn))
            {
                c.d();               
            }
            if(side(c,p_pos_crn) == 2)
            {
                c.d();
            }
            //
            if(is_same(c,p_pos_crn,'p'))
            {
                c.l();
                c.di();
                c.li();
            }
            else
            {
                if(face_colour(c,p_pos_crn,'p') == c.get_col(0))
                {
                    c.fi();
                    c.d();
                    c.f();
                }
                else
                {
                    c.fi();
                    c.d();
                    c.d();
                    c.f();
                    c.di();
                    c.fi();
                    c.d();
                    c.f();
                }
            }            
        }
    }
    //
    private static void layer_1(Cube c)
    {
        if(c.get_col(new int[]{0,1,1},'u') != c.get_col(0))
        {
            c.invert();
        }
        white_cross(c);
        white_corners(c);
    }
    //
    //
    private static void insert_mid(Cube c,char s)
    {
        if(s == 'r')
        {
            c.u();
            c.r();
            c.ui();
            c.ri();
            c.ui();
            c.fi();
            c.u();
            c.f();
        }
        if(s == 'l')
        {
            c.ui();
            c.li();
            c.u();
            c.l();
            c.u();
            c.f();
            c.ui();
            c.fi();
        }        
    }    
    private static void layer_2(Cube c)
    {
        if(c.get_col(new int[]{0,1,1},'u') != c.get_col(1))
        {
            c.invert();
        }
        int[] p_pos_c;
        int[] p_pos_mid;
        for(int i = 0 ; i < 4 ; i++)
        {
            p_pos_c = centres()[i];
            p_pos_mid = v_edges(1)[i];
            turn_till_face(c,p_pos_mid);
            //
            if(lvl(c,p_pos_mid) == 1)
            {
                if(side(c,p_pos_mid) == 0)
                {
                    insert_mid(c,'l');                  
                }
                else if(side(c,p_pos_mid) == 2)
                {
                    insert_mid(c,'r');             
                }
            }
            //
            turn_till_face(c,p_pos_c);
            while(!is_face(c,p_pos_mid))
            {
                c.u();               
            }
            //
            if(is_same(c,p_pos_mid,'p'))
            {
                insert_mid(c,'r');
            }
            else
            {
                c.turn_r();
                c.ui();
                insert_mid(c,'l');
            }           
        }
    }
    //
    //
    private static boolean[] cross_condition(Cube c)
    {
        boolean[] cross = new boolean[4];
        //
        for(int i = 0 ; i < 4 ; i++)
        {
            if(c.get_col(new int[]{0,c.get_pos_of(centres()[i])[1],c.get_pos_of(centres()[i])[2]},'u') == c.get_col(1))
            {
                cross[i] = true;
            }           
        }
        //
        return cross;
    }
    private static void change_cross(Cube c)
    {
        c.f();
        c.u();
        c.r();
        c.ui();
        c.ri();
        c.fi();
    }
    private static void yellow_cross_bring(Cube c)
    {
        turn_till_face(c,centres()[0]);
        boolean[] cr;
        int n = 0;        
        cr = cross_condition(c);
        for(int i = 0 ; i < 4 ; i++)
        {
            if(cr[i])
            {
                n++;
            }
        }
        //
        if(n == 0)
        {
            change_cross(c);
            c.u();
            change_cross(c);
            change_cross(c);
        }
        else if(n == 2)
        {
            if(cr[0]&&cr[2])
            {
                c.u();
                change_cross(c);
                change_cross(c);
            }
            else if(cr[1]&&cr[3])
            {
                change_cross(c);
                change_cross(c);
            }
            else
            {
                do
                {
                    c.u();
                    cr = cross_condition(c);
                }
                while(!(cr[2]&&cr[3]));
                change_cross(c);
            }
        }      
    }
    //
    private static boolean[] cross_setting(Cube c)
    {
        boolean[] cross = new boolean[4];
        //
        for(int i = 0 ; i < 4 ; i++)
        {
            if(is_same(c,new int[]{0,centres()[0][1],centres()[0][2]},'c'))
            {
                cross[i] = true;
            }
            c.turn_r();
        }
        //
        return cross;
    }
    private static void change_cross_setting(Cube c)
    {
        c.r();
        c.u();
        c.ri();
        c.u();
        c.r();
        c.u();
        c.u();
        c.ri();
        c.u();
    }
    private static void yellow_cross_set(Cube c)
    {
        turn_till_face(c,centres()[0]);
        int ct;
        do
        {
            ct = 0;
            c.u();
            for(int i = 0 ; i < 4 ; i++)
            {
                if(cross_setting(new Cube(c))[i])
                {
                    ct++;
                }
            }
        }
        while(ct < 2);
        //
        if(!(cross_setting(new Cube(c))[0]&&cross_setting(new Cube(c))[1]&&cross_setting(new Cube(c))[2]))
        {
            while(cross_setting(new Cube(c))[0])
            {
                c.turn_l();
            }
            if(!cross_setting(new Cube(c))[1])
            {
                c.turn_r();
            }
            if(cross_setting(new Cube(c))[3])
            {
                change_cross_setting(c);
                c.u();
                c.turn_r();
                change_cross_setting(c);
            }
            else
            {
                change_cross_setting(c);
            }
        }
    }
    //
    private static boolean[] corner_condition(Cube c)
    {
        turn_till_face(c,centres()[0]);
        c.invert();
        boolean[] crn = new boolean[4];
        //
        for(int i = 0 ; i < 4 ; i++)
        {
            if(is_correct_pos_c(c,v_edges(2)[i]))
            {
                crn[i] = true;
            }
        }
        //
        return crn;
    }
    private static void change_corner_condition(Cube c)
    {
        c.u();
        c.r();
        c.ui();
        c.li();
        c.u();
        c.ri();
        c.ui();
        c.l();
    }
    private static void yellow_corners_bring(Cube c)
    {
        turn_till_face(c,centres()[0]);
        int count;
        int index;
        //
        do
        {
            change_corner_condition(c);
            count = 0;
            for(int i = 0 ; i < 4 ; i++)
            {
                if(corner_condition(new Cube(c))[i])
                {
                    count++;
                }
            }       
        }
        while(count<1);
        //
        index = 0;
        for(int i = 0 ; i < 4 ; i++)
        {
            if(corner_condition(new Cube(c))[i])
            {
                break;
            }
            else
            {
                index++;
            }
        }
        for(int i = 1 ; i <= index ; i++)
        {
            c.turn_r();
        }
        //      
        do
        {
            change_corner_condition(c);
        }
        while(!(corner_condition(new Cube(c))[0]&&corner_condition(new Cube(c))[1]));
    }
    //
    private static void change_corner_setting(Cube c)
    {
        c.r();
        c.u();
        c.ri();
        c.ui();
        c.r();
        c.u();
        c.ri();
        c.ui();
    }
    private static void yellow_corners_set(Cube c)
    {
        turn_till_face(c,centres()[0]);
        for(int i = 1 ; i <= 4 ; i++)
        {
            if(face_colour(c,new int[]{2,2,2},'c') != face_colour(c,new int[]{2,2,1},'c'))
            {
                if(face_colour(c,new int[]{2,2,2},'c') == c.get_col(1))
                {
                    change_corner_setting(c);
                    change_corner_setting(c);
                }
                else
                {
                    change_corner_setting(c);
                }
            }
            c.di();
        }
    }
    //
    private static void layer_3(Cube c)
    {
        if(c.get_col(new int[]{0,1,1},'u') != c.get_col(1))
        {
            c.invert();
        }
        yellow_cross_bring(c);
        yellow_cross_set(c);
        yellow_corners_bring(c);
        c.invert();
        yellow_corners_set(c);
    }
    //
    //
    public static void solve(Cube c)
    {
        layer_1(c);
        layer_2(c);
        layer_3(c);
    }
}