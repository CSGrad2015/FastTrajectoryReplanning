package AI;
import java.util.ArrayList;




public class Node {
	private int x;
	private int y;
	private int fCost;
	private int hNewVal;
	int search=0;
	private int gCost;
	private int hDistance;
	private boolean blocked;
	private boolean visited;
	private boolean traversed;
	public boolean isTraversed() {
		return traversed;
	}

	public void setTraversed(boolean traversed) {
		this.traversed = traversed;
	}

	private Node parent ;
	
	private ArrayList<Node> neighbors ;
	


	public Node(int x,int y){
		this.x= x;
		this.y= y;
	}

	public Node(){
		this.x = 0;
		this.y = 0;
		this.blocked = false;
		
	}
	
	public Node(int x, int y, boolean blocked){
		this.x = x;
		this.y = y;
		this.blocked = blocked;
		this.neighbors = new ArrayList<Node>();
			
	}
	
	
	
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getgCost() {
		return gCost;
	}
	public void setgCost(int gCost) {
		this.gCost = gCost;
	}
	public int gethDistance() {
		return hDistance;
	}
	public void sethDistance(int hDistance) {
		this.hDistance = hDistance;
	}

	public void setFCost(int fCost) {
		// TODO Auto-generated method stub
		this.fCost=fCost;

	}
	public int getFCost() {
		// TODO Auto-generated method stub
		return fCost;

	}
	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(ArrayList<Node> neighbors) {
		this.neighbors = neighbors;
	}

	public void setHnewVal(int i) {
		// TODO Auto-generated method stub
		this.hNewVal=i;
		
	}
	public int eetHnewVal() {
		return hNewVal;
	}
	
}
