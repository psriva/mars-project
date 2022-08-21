import java.util.*;
import java.io.*;
import java.lang.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 179923
 */
public class SingleLinkedList<T> 
{
    private SingleNode<T> head;
    private SingleNode<T> pointer;
    private Comparator<T> comparer;
    
    public SingleLinkedList(Comparator c)
    {
    	comparer = c;
    }
    public SingleLinkedList()
    {
        head = null;
        pointer = null;
    }
    public SingleLinkedList(T var)
    {
        head = new SingleNode<T>(var);
        pointer = head;
    }
    
    public void add(T n)
    {
         if(head == null)
         {
             head = new SingleNode<T>(n);
             pointer = head;
         }
         else if(pointer.getNext() == null)
         {
            SingleNode<T> s = new SingleNode<T>(n);
            pointer.setNext(s);
            pointer = head;
            return;
         }
         else
         {
            pointer = pointer.getNext();
            add(n);
         }
    }
    
    public int size()
    {
    	if(head == null)
        {
            return 0;
        }
        else if(pointer.getNext() == null)
        {
            pointer = head;
            return 1;
        }
        else 
        {
            pointer = pointer.getNext();
            return 1 + size();
        }
    }
     	
    public T get(int x)
    {
        if(head == null)
        {
            return null;
        }
        else if(x == 0)
        {
            T val = pointer.getValue();
            pointer = head;
            return val;
        }
        else
        {
            pointer = pointer.getNext();
            return get(x - 1);
        }
    }
    
    public T set(int index, T value)
    {
        if(head == null)
        {
            return null;
        }
        if(pointer.getNext() == null)
        {
            add(value);
            return null;
        }
        else if(index == 0)
        {
            T val = pointer.getValue();
            pointer.setValue(value);
            pointer = head;
            return val;
        }
        else
        {
            pointer = pointer.getNext();
            return set(index - 1, value);
        }       
    }
    
    public T remove(int x)
    {
    	if(head == null)
    	{
            return null;
    	}
    	else if(x == 0)
        {
            T val = pointer.getValue();
            head = pointer.getNext();
            pointer = head;
            return val;
        }
        else if(x == 1)
        {
            SingleNode<T> ref = pointer;
            pointer = pointer.getNext();
            T val = pointer.getValue();
            SingleNode<T> ref2 = pointer.getNext();
            ref.setNext(ref2);
            pointer = head;
            return val;
        }
        else
        {
            pointer = pointer.getNext();
            return remove(x - 1);
        }     
    
    }
    public void clear()
    {
    	head = null;
    	pointer = null;
    }
    
    public void add(int index, T value)
    {
        if(head == null)
    	{
            return;
    	}
        
        else if(index == 0)
        {
            SingleNode<T> s = new SingleNode<T>(value);
            s.setNext(head);
            head = s;
            pointer = head;
            return;
        }
        else if(index == 1)
        {
        	SingleNode<T> newNode = new SingleNode<T>(value);
        	newNode.setNext(pointer.getNext());
        	pointer.setNext(newNode);
        	pointer = head;
        	return;
        }
        else
        {
            pointer = pointer.getNext();
            add(index - 1, value);
        }   
    }
    
    public String toString()
    {
    	String x = "[";
    	if(size() == 0)
    	{
            x += "]";
    	}
    	else
    	{
            for(int i = 0; i < size()-1; i++)
            {
                x += get(i) + ", ";
            }
            x += get(size() - 1) + "]";
    	}
    	return x;
    }
    public int indexOf(T value)
    {
        int index = -1;
    	for(int i = 0; i < size(); i++)
    	{
            if(comparer != null)
            {
                int x = comparer.compare(get(i), value);
                if(x == 0)
                {
                    index = i;
                }
            }
            else
            {
                Comparable c = (Comparable)get(i);
                int x = c.compareTo(value);
                if(x == 0)
                {
                    index = i;
                }
            }
    	}
        return index;
    }
}
