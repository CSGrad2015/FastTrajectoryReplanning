package AI;

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Renderer extends JPanel{

	Node[][] rect;
	private List<Point> fillCells;
	int x,y;
	private ArrayList<Node> printgraph;
	Graphics graph;
	
	public Renderer() {
		setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	public Renderer(Node start) {
		this.x=start.getX();
		this.y=start.getY();
		
	}

	public List<Point> getFillCells() {
		return fillCells;
	}

	public void setFillCells(List<Point> fillCells) {
		this.fillCells = fillCells;
	}
	public void setNode(int x,int y) {
		this.x = x;
		this.y = y;
	}

	public Node[][] getRect() {
		return rect;
	}

	public void setRect(Node[][] rect) {
		this.rect = rect;
	}

	public Renderer(Node[][] nodes) {
		fillCells = new ArrayList<Point>();
	}

	
	public Dimension getPreferredSize() {
		return new Dimension(Maze.MazeSize*10, Maze.MazeSize*10);
		
	}
	public void setprintgraph(ArrayList<Node> printgraph) {
		// TODO Auto-generated method stub
		this.printgraph=printgraph;
		
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < Maze.MazeSize*10; i = i + 10) {
			for (int j = 0; j < Maze.MazeSize*10; j = j + 10) {
				g.drawRect(i, j, 10, 10);
			}
		}
		for (Point fillCell : fillCells) {
			
            int cellX = (fillCell.x * 10);
            int cellY = (fillCell.y * 10);
            g.setColor(Color.BLACK);
            g.fillRect(cellX, cellY, 10, 10);
        }
		
		for (Node node : printgraph) {
			
            int cellX = (node.getX() * 10);
            int cellY = (node.getY() * 10);
            
            g.setColor(Color.BLUE);
            g.fillRect(cellX, cellY, 10, 10);
        }
		
		
	}
	
}
