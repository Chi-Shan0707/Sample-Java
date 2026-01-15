import java.util.Random;
public class PolyPolyNominalMulVerifier{
    public static boolean check(int[] a , int[] b)
    {
        java.util.Random random = new java.util.Random();
        int d = b.length;
        int x = random.nextInt(d*100);

        int l=1,r=0;
        for( int i = 0; i< a.length ; ++i)
        {
            l *= (x -a[i]);
        }
        for( int j=0; j< b.length; ++j)
        {
            r += (int)(Math.pow(x, j) * b[j]);
        }
        System.out.println("l= "+ l + " r= "+ r);
        System.out.println("x= "+ x);
        System.out.println("d= "+ d);
        return l==r;

        
    }
    public static void main(String [] args)
    {
        int a[]= { -1, -2, -3};
        int b[] = { 6, 11, 6, 1};
        System.out.println(check(a,b));
        
    }
}