import java.util.*;
public class ListTree
{
    private ListNode front;
    public ListNode getFront(){
        return front;
    }
    public ListTree(ListNode l){
        front=l;
    }
    public ListTree(){
        front=null;
    }
    public void add(int key){//Linked-list add function
        if(front == null){
            front = new ListNode(key);
        }else{
            ListNode current = front;
            while(current.next!=null){
                current=current.next;
            }
            current.next=new ListNode(key);
        }
    }    
    public int height(){
        //TODO        
    }
    public int depth(int key){
        //TODO        
    }
}
class ListNode
{
    public int key;
    ListNode next;
    public ListNode(int key){
        this.key=key;
        this.next=null;
    }
    public ListNode(int key,ListNode next){
        this.key=key;
        this.next=next;
    }
}