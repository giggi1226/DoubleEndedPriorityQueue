import PriorityQueue.DoubleEndedPriorityQueue;
import PriorityQueue.ListDoubleEndedPriorityQueue;
import PriorityQueue.TreeDoubleEndedPriorityQueue;
import java.io.PrintStream;

class MyTester
{
    public static void test1( )
    {
        TreeDoubleEndedPriorityQueue p = new TreeDoubleEndedPriorityQueue();
        System.out.println("tree test");
        p.add(40);
        System.out.println(p);
        
        p.add(15);
        System.out.println(p);
        
        p.add(45);
        
        System.out.println(p);
        
        p.add(40);
        System.out.println(p);
        
        p.add(5);
        System.out.println(p);
        System.out.println("minimum " +p.findMin() + "\nmax: " +p.findMax());
     
        p.deleteMax();
        System.out.println(p);
        
        p.deleteMin();
        System.out.println(p);
        
    }
    
    public static void test2( )
    {
        ListDoubleEndedPriorityQueue p = new ListDoubleEndedPriorityQueue();
        
        System.out.println("List test");
        p.add(20);
        System.out.println(p);
        
        p.add(12);
        System.out.println(p);
        
        p.add(12);
        
        System.out.println(p);
        
        p.add(20);
        System.out.println(p);
        
        p.add(12);
        System.out.println(p);
        System.out.println("minimum " +p.findMin() + "\nmax: " +p.findMax());
     
        p.deleteMax();
        System.out.println(p);
        
        p.deleteMin();
        System.out.println(p);
        
    }
    
    public static void main(String[] args)
    {
        test1();
        test2();
    }
}
