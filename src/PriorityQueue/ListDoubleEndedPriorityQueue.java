/*
Linked list implementation of a DoubleEndedPriorityQueue interface 
*/

package PriorityQueue;

import java.util.Comparator;
import java.util.Random;

public class ListDoubleEndedPriorityQueue<AnyType> implements DoubleEndedPriorityQueue<AnyType> 
{
    private Node<AnyType> first = null;     //first node
    private Node<AnyType> last = null;      //last node
    private Comparator<? super AnyType> cmp;
    private int theSize;
    
    public ListDoubleEndedPriorityQueue( )
    {
        this( null );
    }
    
    public ListDoubleEndedPriorityQueue( Comparator<? super AnyType> c )
    {
        cmp = c;
        empty();
    }
    
    private void empty()
    {
        first = null;
        last = null;  
        theSize = 0;
    }
    //makes list empty
    public void makeEmpty()
    {
        empty();
    }
    
    public void add( AnyType x )
    {
        Node<AnyType> newNode = new Node<AnyType>(x, null, null ); //make 1 node
        
        if(isEmpty())   //if there are no nodes
        {
            first = last = newNode ; //node will be first and last node
            ++theSize;               //increment size by one
        }    
        else
        {
            //loop through every node
            for(Node<AnyType> p = first ; p != null ; p = p.next)
            {   
                //if what we want to insert(x) is less than or equal to what is 
                //in the list(p.data)
                if(myCompare(x, p.data) <= 0)
                {
                    if( p == first) //if p is the first node
                    {
                        //insert neNode as the first element of the list
                        newNode.next = first;
                        first.prev = newNode;
                        first = newNode;  
                       ++theSize;
                       break;
                    }
                    //insert newNode between 2 existing nodes
                    else
                    {   
                        newNode.prev = p.prev;
                        newNode.next = p;
                        p.prev.next = p.prev = newNode;
                         ++theSize;                       
                    }    
                    break;
                }
                else if(p == last)  //if the node we are on is the last node
                {
                    //insert newNode at the end
                    p.next =  newNode;
                    newNode.prev = last;
                    last = newNode;
                     ++theSize;
                     
                     break;
                }
            }
        }
    }
    
    public AnyType deleteMin()
    {
        //if there is nothing to delete
        if(isEmpty())
            throw new UnderFlowException("the list is empty ");
        //if there is only one node
        else if(size() == 1)
        {
            AnyType dLL = first.data;
            makeEmpty();
            return dLL;
        }
        /*first node is always min so delete first node and make the next node 
        first*/
        else
        {
            AnyType dLL = first.data;
            first = first.next;
            first.prev.next = first.prev = null;
            theSize--;

            return dLL;
        }
    }

    public AnyType deleteMax() 
    {
        //if there is nothing to delete
        if(isEmpty())
            throw new UnderFlowException("the list is empty ");
        //if there is only one node in the list
        else if(size() == 1)
        { 
            Node<AnyType> dLL = last;
            makeEmpty();
            theSize = 0;
            return dLL.data;
        }
        /*last node is always max so delete last node and make the prev node 
        last*/
        else
        {
            Node<AnyType> dLL = last;

            last = dLL.prev;
            dLL.prev.next = null;
            dLL.prev = null;
            theSize--;

            return dLL.data;
        }
    }

    public AnyType findMin()
    {
        //if list is empty
        if(isEmpty())
            throw new UnderFlowException("the list is empty ");
        //min is always first
        return first.data;
    }

    public AnyType findMax()
    {
        
        if(isEmpty())
            throw new UnderFlowException("the list is empty ");
        //last is always max
        return last.data; 
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }
    
    private int size() 
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
        
        return cmp.compare( lhs, rhs );
    }
    //prints list enclosed in brackets
    public String toString( )
    {
       
        StringBuilder sb = new StringBuilder( "[ " );
        
        for( Node<AnyType> p = first; p != null; p = p.next )        
            sb.append( p.data + " " );  
        sb.append( "]" );
        
        return new String( sb );
    }
    /*
        Node class
    */
    private static class Node<AnyType>  // just a nested class
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        { 
            data = d; 
            prev = p; 
            next = n; 
        }
        
        private AnyType data;
        private Node<AnyType> prev;
        private Node<AnyType> next;
    }
}
