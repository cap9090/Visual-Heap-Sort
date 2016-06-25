
package thevisualheap;
import java.awt.Color;
import simplegui.SimpleGUI;
import static thevisualheap.Main.SIZE;


/**
 *
 * @author Christian Prajzner
 */

public class Heap {
    
    int WIDTH = 640;//for graphic purposes
    Integer [] heap;
    Integer [] prevHeap;

    
    public Heap(int size){
        heap = new Integer[size];
        prevHeap = new Integer[size];
    }
    
 
    
    
    int getLastNonNullIndex(){
        int i = 0;
        if(heap[0] == null){
            return 0;
        }
        while(heap[i] != null && i<heap.length)
            i++;
        i--;
        return i;
    }
   
    int getFirstChildIndex(int parentIndex){
        return 2*parentIndex + 1;
    }
    
    void delete(){   
        
        
        fillUpPreviousHeap();
        int i = getLastNonNullIndex();
        if (i >= heap.length) return;
        //if(i == heap.length) i--;
        int LIHindex = 0;
        heap[LIHindex] = heap[i];
        heap[i] = null;
        boolean isLargerThanEither = true;

        do{
            int child1 = getFirstChildIndex(LIHindex);
            int child2 = child1 + 1;
            
            if (child1 >= i)
                break;
            
            if(heap[child1] > heap[LIHindex])
                isLargerThanEither = false;
            
            if(heap[child2] != null){
                if(heap[LIHindex] < heap[child2])
                    isLargerThanEither = false;
                else
                    isLargerThanEither = true;    
            }
            
            if(!isLargerThanEither)
                break;
            
            int smallest = -1;
            
            if(heap[child2] == null){
                if(heap[child1] < heap[LIHindex])
                    smallest = child1;
            }
            else{
                smallest = (heap[child1] > heap[child2]) ? child2 : child1;
            }
            
            if(smallest == -1)
                System.out.println("out of bounds");
            
            
            //here we swap
            Integer temp = heap[LIHindex];
            heap[LIHindex] = heap[smallest];
            heap[smallest] = temp;
            
            LIHindex = smallest;
            
        } while(true);
        
    }
    
    
    
    void buildHeap(int start){
        
        for (int i = (heap.length - 1); i > start ; i--){
            int index = i;
            int parentIndex = getParentIndex(index);
            
            while(index != 0 && heap[index].intValue() < heap[parentIndex].intValue()){
                swapWithParent(index, parentIndex, heap);
                index = parentIndex;
                parentIndex = getParentIndex(index);
            }
            
        }
    }
    
    public void sort(){
        for (int start = 0; start < heap.length/2 +1; start++)
            buildHeap(start);
    }
    
    public void fillHeapWithRandomInt(){
            fillUpPreviousHeap();
            int index = determineNextOpenIndex();
            if(index >= heap.length) return;
            int parentIndex = getParentIndex(index);
            Integer i = new Integer((int)((Math.random())*100));
            heap[index] = i;
            
            //I kind of built in the sorting within this method
            while (index != 0 && heap[index].intValue() < heap[parentIndex].intValue()){
                swapWithParent(index, parentIndex, heap);
                index = parentIndex;
                parentIndex = getParentIndex(index);
        }
          
        
    }
    
    void fillUpPreviousHeap(){
        int i = 0;
        for (int j = 0 ; j < heap.length; j++){
            if(heap[j] == null)
                break;
            prevHeap[j] = heap [j];
        }
      
    }
    
    
    void visualize(SimpleGUI sg){
        
        sg.eraseAllDrawables("a");
        
        for (int i = 0, j = 0; j < SIZE ; i += WIDTH/SIZE, j++){
           //j is the index variable
           //use i for graphics
            
            sg.drawText("" + heap[j], i, 100, Color.black, 1, "a"); 
            
            if(heap[j] == null){
                sg.drawFilledBox(i, 100, WIDTH/SIZE - 5, SIZE, Color.GRAY, 1, null);
            }
            else if (prevHeap[j] == null){
                sg.drawFilledBox(i, 100, WIDTH/SIZE - 5, SIZE, Color.GREEN, 1, null);
            }
            if (heap[j] != prevHeap[j]){
                sg.drawFilledBox(i, 100, WIDTH/SIZE - 5, SIZE, Color.RED, 1, null);
            }
            else{
                sg.drawFilledBox(i, 100, WIDTH/SIZE - 5, SIZE, Color.GREEN, 1, null);
            }
        }
    }
    
   
    
    int getParentIndex(int childIndex){
        return (int) (Math.floor(((double)(childIndex)/2)));
    }
    
    
    void swapWithParent(int childIndex, int parentIndex, Integer [] D){
        int temp = D[parentIndex].intValue();
        D[parentIndex] = new Integer(D[childIndex]);
        D[childIndex] = new Integer(temp);
    }
    
    void swapWithChild(int parentIndex, int childIndex, Integer [] D){
        
    }
    
    int determineNextOpenIndex(){
        int i = heap.length;
        if(heap[0] == null)
            return 0;
        while(heap[--i] == null);
        return i + 1;
    }
    
    
    
    
}



