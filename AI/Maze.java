package AI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;




public class Maze {
	public static int MazeSize = 101; 
	
	private static List<Node> visited;
	protected Node[][] nodes;
	private Stack<Node> nodeStack;
	int nodeCounter=0;
	
	public Maze(){
		nodes = new Node[MazeSize][MazeSize];
		nodeStack = new Stack<Node>();
		
		for (int i = 0; i < MazeSize; i++) {
			for (int j = 0; j < MazeSize; j++) {
				nodes[i][j] = new Node(i,j,false);//initiate all nodes
				
			}
			
		}
		
		visited = new ArrayList<Node>();
		genMaze();
	}
	
	public Node initiateNeighbors(Node n){
		
		ArrayList<Node> nbrs = new ArrayList<Node>();
		ArrayList<Node> temp = new ArrayList<Node>();
		
		if(n.getY() < Maze.MazeSize -1)
			nbrs.add(nodes[n.getX()][n.getY()+1]) ;
		
		if(n.getY() > 0)
			nbrs.add(nodes[n.getX()][n.getY()-1]) ;
			
		if(n.getX() > 0)
			nbrs.add(nodes[n.getX()-1][n.getY()]) ;
		
		if(n.getX() < Maze.MazeSize -1)
			nbrs.add(nodes[n.getX()+1][n.getY()]) ;
		
		
		n.setNeighbors(nbrs);
		
		return n;
	}
	
	
	
	public void genMaze(){
		Node current = nodes[(int)(Math.random()*(MazeSize-1))][(int)(Math.random()*(MazeSize-1))];
		
		while(visited.size() < MazeSize*MazeSize){
			
		if(!visited.contains(current)){
				current.setVisited(true);
				visited.add(current);
				nodeStack.push(current);
		
				while(!nodeStack.isEmpty()){
					traverse(nodes);
				}
			}
		}
	
	}
		

	public Node[][] traverse(Node[][] nodes)
	{
		
		if(!nodeStack.empty()){	
		
			Node current = nodeStack.pop();
			current = initiateNeighbors(current);
			current.setVisited(true);
			visited.add(current);
			
			double randProb = Math.random();
			
			if(randProb <= 0.2d){
				current.setBlocked(true);
				
			}
			
			
					
			ArrayList<Node> neighbors = current.getNeighbors();
			for(Node nbr: neighbors){
				if(!visited.contains(nbr)){
					nodeStack.push(nbr);
					
				}
			}
		}
			return nodes;
	}


	
	
}
