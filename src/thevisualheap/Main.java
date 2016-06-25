


package thevisualheap;

import java.awt.Color;
import simplegui.SimpleGUI;
import simplegui.GUIListener;

/**
 *
 * @author Christian Prajzner
 */


public class Main implements GUIListener {

    final static int SIZE = 20;
    SimpleGUI sg = new SimpleGUI();
    final int WIDTH = sg.getWidth();
    final int HEIGHT = sg.getHeight();
    Heap h = new Heap(SIZE);
    
    
    public static void main(String[] args) {
        new Main();
    }
    
    Main(){
        
        
        this.visualizeEmptyHeap();
        sg.registerToGUI(this);
        sg.labelButton1("ADD");
        sg.labelButton2("DELETE ROOT");
        
    }

    
    void visualizeEmptyHeap(){
        
        for (int i = 0, j = 0; j < SIZE ; i += WIDTH/SIZE, j++){
            sg.drawFilledBox(i, 100, WIDTH/SIZE - 5, SIZE, Color.GRAY, 1, null);
        }
    }
    
    
    @Override //insert
    public void reactToButton1() {
        
        if(h.heap[h.heap.length -1] == null)
        {
        h.fillHeapWithRandomInt();
        h.visualize(sg);
        }
        for (int i = 0 ; i < SIZE; i++){
            System.out.print(" " + h.heap[i]);
        }
        System.out.println();
        for (int i = 0 ; i < SIZE; i++){
            System.out.print(" " + h.prevHeap[i] );
        }
        System.out.println();
        System.out.println();
    }

    @Override //deletion
    public void reactToButton2() {
        
        
        h.delete();
        h.visualize(sg);
        
        for (int i = 0 ; i < SIZE; i++){
            System.out.print(" " + h.heap[i]);
        }
        System.out.println();
        for (int i = 0 ; i < SIZE; i++){
            System.out.print(" " + h.prevHeap[i] );
        }
        System.out.println();
        System.out.println();
        
    }

    @Override
    public void reactToSwitch(boolean bln) {
    }

    @Override
    public void reactToSlider(int i) {
    }
    
    
    
}

