package AI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;




import javax.swing.*;

import java.awt.*;



public class AdaptiveAStar extends Maze  {
	Node start;
	Node target;
	Node current;
	Node finalTarget;
	Node initialStart;
	int hDistance;
	static int counter;
	binaryHeap Open=new binaryHeap();
	Stack<Node> Close=new Stack<Node>();
	ArrayList<Node> printgraph=new ArrayList<Node>();
	long startTime;
	
	Renderer r = new Renderer();
	private ArrayList<Node> BlockedRec=new ArrayList<>();
	
	
	public AdaptiveAStar(int startX,int startY,int targetX,int targetY ){
		 startTime = System.currentTimeMillis();
		this.start=nodes[startX][startY];
		this.target=nodes[targetX][targetY];
		start.setBlocked(false);
		target.setBlocked(false);
		this.initialStart=start;
		this.finalTarget=target;
	}



	public void mainCompute(){
		
		boolean traversed=false;
		hDistance=calculateDistance(start, target);
		start.sethDistance(hDistance);
		start.setgCost(0);
		start.setFCost(start.getgCost()+start.gethDistance());
		
		printgraph.add(start);
		printgraph.add(target);
		while(!equals(start,finalTarget)){
			
			counter++;	
			
				
			start.search=counter;
			target.setgCost(Integer.MAX_VALUE);
			target.search=counter;
			Open.makeEmpty();
			Close.clear();
			Open.insert(start);
			
			ComputePath();
			
			if(Open.isEmpty()){
				System.out.println("I can not reach the Target");
				break;
			}
			
				Node next1 ;
				Stack<Node> path=new Stack<Node>();
			
				path.push(target);
				while(!equals(target.getParent(),start)){
						
						 next1=Close.pop();
						next1.sethDistance(target.getgCost()-next1.getgCost());
						
						if(equals(target.getParent(),next1)){
						target=next1;
						path.push(target);
						}
						
						
					//}
					//i++;
						//}
				}
					target=finalTarget;
					
				while(!equals(start,target)){
					
						Node traverse=path.pop();
						
						if(traverse.isBlocked()){
							
							start=traverse.getParent();
							
							break;
						}
						traverse.setTraversed(true);
						printgraph.add(traverse);
						
						if(equals(traverse,target)){
							traversed=true;
							System.out.println("I reached the Target");
							break;
						}
					}
					
					
					
			if(traversed){
				
				break;
			}
			
		}
		 long endTime   = System.currentTimeMillis();
         long totalTime = endTime - startTime;
         System.out.println("Time :"+totalTime);
		
		renderMaze();
	}
	
	
	
	public JFrame renderMaze(){
		
		JFrame f = new JFrame("Maze");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		List<Point> fillCells = new ArrayList<Point>();
		//Stack<Node> printgraph = null;
		for (int i = 0; i < MazeSize; i++) {
			for (int j = 0; j < MazeSize; j++) {
				if (nodes[i][j].isBlocked()) {
				
					fillCells.add(new Point(i,j));    // Check
				}
			}
		}
		
		r.setFillCells(fillCells);
		r.setprintgraph(printgraph);
		/* long endTime   = System.currentTimeMillis();
         long totalTime = endTime - startTime;
         System.out.println(totalTime);*/
		f.add(r);
		
		f.pack();
		f.setVisible(true);
		return f;
	}

	

	public void ComputePath(){
		boolean reached = false;
		current=Open.getMinElement();
		
		initiateNeighbors(current);
		ArrayList<Node> successors=current.getNeighbors();
		Iterator< Node> ite=successors.iterator();
		Node temp;
		while(ite.hasNext()){
			temp=ite.next();
			if(temp.isBlocked()){
				BlockedRec.add(temp);
				ite.remove();
				
			}
		}
		
		
		while(target.getgCost()>current.getFCost()){

			Open.remove(1);
			Close.push(current);
			
			if(successors.isEmpty()){
				return;
			}
			
			for(Node nextNode : successors){
			
		
				if(nextNode.search< counter){
					nextNode.setgCost(Integer.MAX_VALUE);
					nextNode.search=counter;
				}
				
				if(BlockedRec.contains(nextNode)){
					
					continue;
				}
				if(Close.contains(nextNode)){
					
					continue;
				}
				
				

				if(nextNode.getgCost() > current.getgCost() +1)
				{
					nextNode.setgCost(start.getgCost() +1);
					hDistance=calculateDistance(nextNode,target);
					nextNode.sethDistance(hDistance);
					nextNode.setFCost(nextNode.getgCost()+ hDistance);
					nextNode.setParent(current);
					if(equals(nextNode,target)){
						
						target=nextNode;
						
							Open.insert(nextNode);
							
						reached=true;
						break;
					}
					int index=Open.contains(nextNode);
					
					if(index != 0){
						
						Open.insert(nextNode);
					}else{
						Open.insert(nextNode);
					}

				}
			}
			
			
			if(reached){
				break;
			}
			
			if(!Open.isEmpty()){
				current=Open.getMinElement();
			
			initiateNeighbors(current);
			successors=current.getNeighbors();
			}else{
				return;
			}
			
			
			
			
		}
		
	}
	public boolean equals(Node N1,Node N2){
		if(N1.getX()==N2.getX() && N1.getY()==N2.getY()){
			return true;
		}
		return false;
	}

	public int calculateDistance(Node N1,Node N2){
		return Math.abs(N1.getX() - N2.getX()) + Math.abs(N1.getY() - N2.getY());
	}
}


