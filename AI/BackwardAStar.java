package AI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.*;

import java.awt.*;



public class BackwardAStar extends Maze  {
	Node start;
	Node target;
	Node current;
	int hDistance;
	static int counter;
	binaryHeap Open=new binaryHeap();
	Stack<Node> Close=new Stack<Node>();
	ArrayList<Node> printgraph=new ArrayList<Node>();
	long startTime;
	ArrayList<Node> BlockedRec=new ArrayList<>();
	Renderer r = new Renderer();
	
	
	public BackwardAStar(int startX,int startY,int targetX,int targetY ){
		 startTime = System.currentTimeMillis();
		this.start=nodes[startX][startY];
		this.target=nodes[targetX][targetY];
		start.setBlocked(false);
		target.setBlocked(false);
		
	}



	public void mainCompute(){
		
		boolean traversed=false;
		hDistance=calculateDistance(start, target);
		start.sethDistance(hDistance);
		target.setgCost(0);
		target.setFCost(start.getgCost()+start.gethDistance());
		printgraph.add(start);
		printgraph.add(target);
		
		while(!equals(start,target)){
			
			Node initialStart=start;
			counter++;	
			
				
			target.search=counter;
			start.setgCost(Integer.MAX_VALUE);
			start.search=counter;
			Open.makeEmpty();
			Close.clear();
			Open.insert(target);
			
			ComputePath();
			if(Open.isEmpty() ){
				System.out.println("I can not reach the Target");
				break;
			}
			
				
				
						
				Node next1;
				ArrayList<Node> path=new ArrayList<Node>();
				
				path.add(start);
				while(!equals(start.getParent(),target)){
						
						 next1=Close.pop();
						
						if(equals(start.getParent(),next1)){
							start=next1;
				
						path.add(start);
						}
						
						
					
				}
				start=initialStart;
					
				int temp=0;
				int checkMove=0;
				while(!equals(start,target) && temp<path.size()){
					checkMove++;
						Node traverse=path.get(temp);
						
						if(traverse.isBlocked()){
BlockedRec.add(traverse);
							
							start=path.get(temp-1);
							break;
						}
						printgraph.add(traverse);
						
						if(equals(traverse,target)){
							traversed=true;
							System.out.println("I reached the Target");
							break;
						}
						temp++;
					}
				
				
					
					
			
				
			if(traversed){
				break;
			}
			/*if(checkMove<2){
				System.out.println("start is moved at least one block ");
				
			}else{
				System.out.println("I can not reach the target");
				break;
			}*/
			
			if(equals(start,initialStart)){
				break;
			}
		}
		
		renderMaze();
	}
	
	
	
	public JFrame renderMaze(){
		
		JFrame f = new JFrame("Maze");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		List<Point> fillCells = new ArrayList<Point>();
		
		for (int i = 0; i < MazeSize; i++) {
			for (int j = 0; j < MazeSize; j++) {
				if (nodes[i][j].isBlocked()) {
					fillCells.add(new Point(i,j));    
				}
			}
		}
		
		r.setFillCells(fillCells);
		r.setprintgraph(printgraph);
		f.add(r);
		 long endTime   = System.currentTimeMillis();
         long totalTime = endTime - startTime;
         System.out.println(totalTime);
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
		while(start.getgCost()>target.getFCost()){

			
			Open.remove(1);
			Close.push(current);
			
			
			for(Node nextNode : successors){
			
		
				if(nextNode.search< counter){
					nextNode.setgCost(Integer.MAX_VALUE);
					nextNode.search=counter;
				}
			
				if(Close.contains(nextNode)){
					
					continue;
				}
				
				if(BlockedRec.contains(nextNode)){
				
					continue;
				}
				

				if(nextNode.getgCost() > current.getgCost() +1)
				{
					nextNode.setgCost(current.getgCost() +1);
					hDistance=calculateDistance(nextNode,start);
					nextNode.sethDistance(hDistance);
					nextNode.setFCost(nextNode.getgCost()+ hDistance);
					nextNode.setParent(current);
					if(equals(nextNode,start)){
						
						start=nextNode;
						Open.insert(nextNode);
						reached=true;
						break;
					}
					
					Open.insert(nextNode);
						if(reached){
						Close.push(nextNode);
						break;
					}

				}
			}
			
			if(reached){
				break;
			}
			
			if(!Open.isEmpty()){
			current=Open.getMinElement();
			
			}else{
				return;
			}
			
			initiateNeighbors(current);
			 successors=current.getNeighbors();
			
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


