package AI;

import java.util.Scanner;



public class MazeGeneration {

	public static void main(String[] args) {
		
		  @SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		 
		System.out.println("\nSelect Operation to perform\n");
        System.out.println("1. Repeated forward Astar ");
        System.out.println("2. Repeated backward Astar");
        System.out.println("3. Adaptive AStar");            
        System.out.println("4. Repeated forward Astar tie breaking in favour of smaller g values");
       
        int choice = scan.nextInt();      
        switch (choice)
        {
        case 1 : 
            try
            {
                System.out.println("Enter start co-ordinates");
                int startX=scan.nextInt();
                int startY=scan.nextInt();
                System.out.println("Enter goal co-ordinates");
                int targetX=scan.nextInt();
                int targetY=scan.nextInt();
                AStarAlgorithm astar=new AStarAlgorithm(startX,startY,targetX,targetY);//random tie breaking in favour of greater g value
        		astar.mainCompute();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage() );
            }
            break;                          
        case 2 : 
            try
            {
            	System.out.println("Enter start co-ordinates");
                int startX=scan.nextInt();
                int startY=scan.nextInt();
                System.out.println("Enter goal co-ordinates");
                int targetX=scan.nextInt();
                int targetY=scan.nextInt();
            	BackwardAStar Backastar=new BackwardAStar(startX,startY,targetX,targetY);//start n target
        		Backastar.mainCompute();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage() );
            }                 
            break;                                
        case 3 : 
        	 try
             {
        		 System.out.println("Enter start co-ordinates");
                 int startX=scan.nextInt();
                 int startY=scan.nextInt();
                 System.out.println("Enter goal co-ordinates");
                 int targetX=scan.nextInt();
                 int targetY=scan.nextInt();
                
                 
                
        		 AdaptiveAStar adaptAStar=new AdaptiveAStar(startX,startY,targetX,targetY);//start n target
        			adaptAStar.mainCompute();
        			
             }
             catch (Exception e)
             {
                 System.out.println(e.getMessage() );
             }                 
             break;                                    
        case 4 : 
        	 try
             {
        		 System.out.println("Enter start co-ordinates");
                 int startX=scan.nextInt();
                 int startY=scan.nextInt();
                 System.out.println("Enter goal co-ordinates");
                 int targetX=scan.nextInt();
                 int targetY=scan.nextInt();
        		 CopyOfAStarAlgorithm copyastar=new CopyOfAStarAlgorithm(startX,startY,targetX,targetY);//random tie breaking in favour of smaller g value
        		 copyastar.mainCompute();
             }
             catch (Exception e)
             {
                 System.out.println(e.getMessage() );
             }                 
             break;    
                 
        default : 
            System.out.println("Wrong Entry \n ");
            break;   
        }
	}
	
}
