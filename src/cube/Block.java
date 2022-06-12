package cube;
public class Block
{
   protected final int x_o;
   protected final int y_o;
   protected final int z_o;
   //
   public char up;
   protected char down;
   public char face;
   protected char back;
   public char left;
   protected char right;
   //
   protected int x;
   protected int y;
   protected int z;
   //
   Block(int x,int y,int z)
   {
       this.x_o = x;
       this.y_o = y;
       this.z_o = z;
       //
       this.up = 'v';
       this.down = 'v';
       this.face = 'v';
       this.back = 'v';
       this.left = 'v';
       this.right = 'v';
       //
       this.x = 0;
       this.y = 0;
       this.z = 0;
   }
   Block(Block b)
   {
       this.x_o = b.x_o;
       this.y_o = b.y_o;
       this.z_o = b.z_o;
       //
       this.up = b.up;
       this.down = b.down;
       this.face = b.face;
       this.back = b.back;
       this.left = b.left;
       this.right = b.right;
       //
       this.x = b.x;
       this.y = b.y;
       this.z = b.z;
   }

   //
   public void u()
   {
       char temp = this.face;
       this.face = this.right;
       this.right = this.back;
       this.back = this.left;
       this.left = temp;
   }
   public void ui()
   {
       char temp = this.face;
       this.face = this.left;
       this.left = this.back;
       this.back = this.right;
       this.right = temp;
   }
   //
   public void f()
   {
       char temp = this.up;
       this.up = this.left;
       this.left = this.down;
       this.down = this.right;
       this.right = temp;
   }
   public void fi()
   {
       char temp = this.up;
       this.up = this.right;
       this.right = this.down;
       this.down = this.left;
       this.left = temp;
   }
   //
   public void r()
   {
       char temp = this.up;
       this.up = this.face;
       this.face = this.down;
       this.down = this.back;
       this.back = temp;
   }
   public void ri()
   {
       char temp = this.up;
       this.up = this.back;
       this.back = this.down;
       this.down = this.face;
       this.face = temp;
   }
}