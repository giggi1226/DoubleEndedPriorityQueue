package cop3530;

public interface DoubleEndedPriorityQueue<AnyType>
{ 
    void makeEmpty( ); 
    void add( AnyType x ); 
    AnyType deleteMin( ); 
    AnyType deleteMax( ); 
    AnyType findMin( ); 
    AnyType findMax( ); 
    boolean isEmpty( ); 
} 

