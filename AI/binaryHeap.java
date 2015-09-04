package AI;
	/**
	 * @author vinnie
	 *
	 */
	public class binaryHeap {
	   
	    private static final int branchFactor = 2;
	    private static int index=0;
	    public Node[] heap;
	 
	    
	    public binaryHeap()
	    {
	        heap = new Node[10201];
	       
	    }
	 
	   /*
	    * function to check if the heap is empty
	    * 
	    */
	    public boolean isEmpty( )
	    {
	    	if(index == 0){
	    		return true;
	    	}else{
	    		return false;
	    	}
	        
	    }
	 
	    /*
	     * Function to insert new element in the heap
	     * 
	     * 
	     * 
	     */
	    
	    public void insert(Node x)
	    { 
	        heap[++index] = x;
	        minHeapifyUp(index);
	    }

	   
	    /*
	     * Function to remove particular element at index from the heap
	     * 
	     * 
	     * 
	     */
	    public void remove(int delIndex)
	    {	     
	        heap[delIndex] = heap[index];
	        index--;
	        minHeapifyUp(delIndex);
	        minHeapifyDown(delIndex);        
	      
	    }
	 
	  
	    private void minHeapifyUp(int childIndex)
	    {
	        Node temp = heap[childIndex]; 
	        int parentIndex=childIndex/branchFactor;
	        if(childIndex > 1 && temp.getFCost()==heap[parentIndex].getFCost()){
	        	while (childIndex > 1 && temp.getgCost() < heap[parentIndex].getgCost())
		        {
		        	
		            heap[childIndex] = heap[parentIndex];
		            childIndex = parentIndex;
		            parentIndex=childIndex/branchFactor;
		        }        
	        }
	        while (childIndex > 1 && temp.getFCost() < heap[parentIndex].getFCost())
	        {
	        	
	            heap[childIndex] = heap[parentIndex];
	            childIndex = parentIndex;
	            parentIndex=childIndex/branchFactor;
	        }                   
	        heap[childIndex] = temp;
	    }
	 
	 
	    private void minHeapifyDown(int delIndex)
	    {
	        int leftChild=2*delIndex;
	        int rightChild=(2*delIndex)+1;
	        Node temp=heap[delIndex];
	        while(leftChild<=index){
	        	if(rightChild<=index ){
	        		if(temp.getFCost()>heap[rightChild].getFCost() && heap[leftChild].getFCost()>heap[rightChild].getFCost()){
	        			heap[delIndex]=heap[rightChild];
	        			delIndex=rightChild;
	        		}else if (temp.getFCost()>heap[leftChild].getFCost() && heap[rightChild].getFCost()>heap[leftChild].getFCost()) {
	        			heap[delIndex]=heap[leftChild];
	        			delIndex=leftChild;
	        		}else{
	        			break;
	        		}
	        		
	        	}else if(temp.getFCost()>heap[leftChild].getFCost()){
	        		heap[delIndex]=heap[leftChild];
	        		delIndex=leftChild;
	        	}else{
	        		break;
	        	}
	        		leftChild=2*delIndex;
	        		rightChild=(2*delIndex)+1;
	        }
	       
	        heap[delIndex] = temp;
	    }
	 
	   
	  

		public int contains(Node nextNode) {
			// TODO Auto-generated method stub
			int temp=0;
			
			if(isEmpty()){
			
				return 0;
			}else{
			
			for (int i=1;i<=index;i++){
				if(heap[i].getX()==nextNode.getX() && heap[i].getY()==nextNode.getY() ){
					temp=i;
					break;
					
				}
			}
			}
			return temp;
		}

		public void makeEmpty() {
			// TODO Auto-generated method stub
			index=0;
		}

		public Node getMinElement() {
			// TODO Auto-generated method stub
			return heap[1];
		}     
	}
	 
