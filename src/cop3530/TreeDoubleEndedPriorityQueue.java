
package cop3530;

import java.util.Comparator;

/**
 *
 * Binary Tree implementation of the DoubleEndedPriorityQueue
 */
public class TreeDoubleEndedPriorityQueue<AnyType> implements DoubleEndedPriorityQueue<AnyType> {
    
    
    private Comparator<? super AnyType> cmp; 
    private Node<AnyType> root = null; //root of the tree
    private int theSize = 0;
    /*
    Node class 
    */
    private static class Node<AnyType>
    {
        private Node<AnyType> left;         //left node
        private Node<AnyType> right;        //right node
        private ListNode<AnyType> items;    //(singly)linked list for duplicates

        private static class ListNode<AnyType>
        {
            private AnyType data;
            private ListNode<AnyType> next;

            public ListNode( AnyType d, ListNode<AnyType> n )
            {
                data = d;
                next = n; 
            }
        }

        public Node( AnyType data )
        {
            left = right = null;
            items = new ListNode<AnyType>( data, null );
        }
        
        public void add( AnyType data)
        {
            items = new ListNode<AnyType>( data, items );
        }
        
        public void remove( )
        {
            items = items.next;
        }
        
        public String toString()
        {
            StringBuilder sb = new StringBuilder( );
            for( ListNode<AnyType> p = items; p != null; p = p.next )
                sb.append( p.data + " " );
            
            return new String( sb );
        }
    }
    
    public TreeDoubleEndedPriorityQueue( )
    {
        this( null );
    }
    
    public TreeDoubleEndedPriorityQueue( Comparator<? super AnyType> c )
    {
        root = null;
        theSize = 0;
        cmp = c;
    }

    @Override
    public void makeEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void add(AnyType x) 
    {
        root = add(x, root);
    }
    private Node<AnyType> add( AnyType x, Node<AnyType> t )
    {
        //if root is empty
        if( t == null )
        {
            ++theSize;
            return new Node<>( x );  //return new node
        }
        //compare x to the value at the root
        int compareResult = myCompare( x, t.items.data );
        
        //if x is less than the root
        if( compareResult < 0 )
        {
            t.left = add( x, t.left ); //go left
        }
        //else if x is greater than root
        else if( compareResult > 0 )
        {
            t.right = add( x, t.right );    //go right
        }
        //if it is equal
        else
        {
            ++theSize;
            t.add(x);   //add it to the linked list
        }
        
        return t;
    }

    @Override
    public AnyType deleteMin() {
        if(isEmpty())
            throw new UnsupportedOperationException("Not supported yet."); 
        
        AnyType minimum = findMin();   //minimum is the mininum node in the tree
        root = deleteMin(root);        //root gets the value of tree without min
        
        return minimum;
    }
    
    private Node<AnyType> deleteMin( Node<AnyType> t )
    {
        //if there is nothing on the left
        if( t.left == null )
        {
            --theSize;
            if(t.items.next != null) //if the root has a linked list
            {
                t.remove(); //remove one node from linked list
                return t;
            }
            else
                return t.right; //the root has been removed, right node is min
        }
        else
        {
           //keep going to the left until you can't go further left
            t.left = deleteMin( t.left );           
            return t;
        }
    }
    
    @Override
    public AnyType deleteMax() 
    {
        if(isEmpty())
        {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
        
        AnyType maximum = findMax();
        root = deleteMax(root); //tree without max
        
        return maximum;
    }
    
    private Node<AnyType> deleteMax( Node<AnyType> t )
    {
        //if there is nothing on the right
        if( t.right == null )
        {
            --theSize;           
             if(t.items.next != null)//if the root has a linked list
            {
                t.remove();//remove one node from linked list       
                return t;
            }
            else                
            {
                return t.left;//the root has been removed, left node is max
            }
        }
        else
        {
             //keep going to the left until you can't go further left
            t.right = deleteMax( t.right );            
            return t;
        }
    }

    @Override
    public AnyType findMin() {
        //if nothing at the root
        if( root == null )
            throw new IllegalStateException( );
        
        return findMin( root );  //findMin from the root      
    }
    
       private AnyType findMin( Node<AnyType> t )
    {
        //if there is nothing on the left node
        if( t.left == null )
            return t.items.data;    //return node you are on
        else
            return findMin( t.left );   //else keep going to the left
    }

    @Override
    public AnyType findMax() 
    {
         if( root == null )
            throw new IllegalStateException( );
        
        return findMax( root );   //findMax from the root  
    }
    
    private AnyType findMax( Node<AnyType> t )
    {
        //while there are still more nodes on the right
        while( t.right != null )
            t = t.right;
        
        return t.items.data;
    }

    @Override
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
     public int size( )
    {
        return theSize;
    }
    /*
    compares 2 values returns a negative integer, zero, or positive integer
    if the first argument is less than, equal to, or greater than the second
    argument
    */ 
    private int myCompare( AnyType lhs, AnyType rhs )
    {
        if( cmp == null )
            return ((Comparable)lhs).compareTo( rhs );
        else
            return cmp.compare( lhs, rhs );
    }
    
    //prints list enclosed in brackets
    public String toString( )
    {
        
        StringBuilder sb = new StringBuilder( "[ ");
        toString( root, sb );
        sb.append( "]" );
        
        return new String( sb );
    }
    
    private void toString( Node<AnyType> t, StringBuilder sb )
    {
        if( t != null )
        {
            toString( t.left, sb );
            sb.append( t.toString() );
            sb.append( " " );
            toString( t.right, sb );
        }
    }
}
    
    
   