/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 179923
 */
public class SingleNode <T>
{
    private T value;
    private SingleNode nextNode;
    
    public SingleNode(T var)
    {
        value = var;
        nextNode = null;
    }
    
    public SingleNode(T var, SingleNode ref)
    {
        value = var;
        nextNode = ref;
    }
    
    public T getValue()
    {
       return value;
    }
    
    public SingleNode getNext()
    {
        return nextNode;
    }
    
    public void setNext(SingleNode s)
    {
        nextNode = s;
    }
    
    public void setValue(T x)
    {
        value = x;
    }
}


