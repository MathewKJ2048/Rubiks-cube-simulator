import java.util.Calendar;
import cube.Cube;
public class Test
{
    public static void cube_time()
    {
        Cube c = new Cube();
        
        Calendar cl;
        cl = Calendar.getInstance();
        System.out.println(cl);
        for(int i = 1 ; i <= 10000 ; i++)
        {
            c.scramble();
        }
        cl = Calendar.getInstance();
        System.out.println(cl);
        for(int i = 1 ; i <= 10000 ; i++)
        {
            c.scramble();
            Cube_solver.solve(c,0);
        }
        
        cl = Calendar.getInstance();
        System.out.println(cl);
        //
        
    }
    public static int num(String[] moves) throws Exception // returns the number of times a given sequence of moves must be repeated before cube returns to original state
    {
        Cube c = new Cube();
        int count = 0;
        do
        {
            for(int i = 0 ; i < moves.length ; i++)
            {
                c.process(moves[i]);
            }
            count++;
        }
        while(!c.is_solved());
        return count;
    }
}
