import java.util.ArrayList;
import java.util.Scanner;

//define the PowerofTwomaxHeap class
// The class is a max heap with a number of children that is a power of two
public class PowerOfTwoMaxHeap {
    private final int numChildren;
    private final ArrayList<Integer> heap;

    public PowerOfTwoMaxHeap(int power){
        this.numChildren=(int) Math.pow(2,power);//calculate 2^power
        this.heap=new ArrayList<>();
    }

    //insert a value into the heap
    public void insert(int value){
        heap.add(value);
        int index=heap.size()-1;//index of the last element
    
        while(index>0){
            int parentIndex=(index-1)/numChildren;
            if(heap.get(index)>heap.get(parentIndex)){
                swap(index,parentIndex);//swap value with its parent
                index=parentIndex;
            }else{
                break;
            }
        }
    }

//return and remove the maximum value from heap
    public int popMax() {
        if(heap.isEmpty()) throw new IllegalStateException("Heap is empty");
        int max=heap.get(0);// Max value is at root
        int last=heap.remove(heap.size()-1);

        if (!heap.isEmpty()){
            heap.set(0,last);//Move the last value to the root
            heapifyDown(0);
        }
    
    return max;
    
    } 


    //restore the heap property by moving the value at index down the heap
    private void heapifyDown(int index){
        int size=heap.size();//get the size of the heap
        while(true){
            int maxIndex=index;

            for(int i=1;i<= numChildren;i++){
                int childIndex=index*numChildren+i;//calculate the child index
                if (childIndex<size && heap.get(childIndex)>heap.get(maxIndex)){
                    maxIndex=childIndex;
                }
            }

            if (maxIndex==index) break;
            swap(index,maxIndex);//swap the value with the maximum child
            index=maxIndex;

        }
    }
    private void swap(int i,int j){
        int temp=heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,temp);
    }



    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the power of two for the number of children: ");
        int power=scanner.nextInt();

        PowerOfTwoMaxHeap heap=new PowerOfTwoMaxHeap(power);

        while(true){
            System.out.println("\nChoose an option:");
            System.out.println("1. Insert a value");
            System.out.println("2. Pop the max value");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice=scanner.nextInt();

            switch(choice){
                case 1:
                System.out.print("Enter a value to insert: ");
                int value=scanner.nextInt();
                heap.insert(value);
                System.out.print("Value inserted:" + value);
                break;

                case 2:
                try{
                    int max=heap.popMax();
                    System.out.println("Maximum value popped: "+ max);
                } catch(IllegalStateException e){
                    System.out.println("Heap is empty");
                }
                break;

                case 3:
                System.out.println("Exiting...");
                scanner.close();
                return;

                default:
                System.out.println("Invalid choice. Please try again.");

            }
        }

    }
}

