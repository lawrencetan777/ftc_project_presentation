    import java.lang.Math;
    import java.util.*;

/**
 * This program generations movement instructions for a bot on a grid from a starting point to visit
 * any number of junction points in the order of distance from the previous point, then back to the 
 * start.
 */
class program {
    public static void main(String[] args) {
        
        //define starting  coordinate
        int[] startingPoint = new int[2];

        //define final junction coordinates
        ArrayList<int[]> coordsList = new ArrayList<int[]>();

        Scanner scannerInput = new Scanner(System.in);
        
        //Display simulation information
        System.out.println();
        System.out.println("======================================");
        System.out.println("Welcome to the bot movement simulation");
        System.out.println("======================================");
        System.out.println();

        System.out.println("This program routes a bot on a grid from user inputed starting point to any number of junction points in the order of distance from the previous point,then back to the start.");
        System.out.println();
        System.out.println();

        //Prompts user input and records the coordinates of the starting point
        System.out.println("Enters the coordinates of the starting point as x,y (no spaces). X and Y has to be between 0 and 10: ");
        String startCoordInput = scannerInput.nextLine();
        String[] startCoord = startCoordInput.split(",");
        startingPoint[0] = Integer.valueOf(startCoord[0].trim());
        startingPoint[1] = Integer.valueOf(startCoord[1].trim());
        //System.out.println(startingPoint[0] + " , " + startingPoint[1]);

        //Prompts user for number of junction points
        System.out.println("Enters the number of junction points you want to reach:");
        int numOfJunctions = 0;

        try {
            numOfJunctions = Integer.valueOf(scannerInput.nextLine());
        } catch (Exception e) {
            // Incorrect input format
            System.out.println("Please enter the integer only. Please try again.");
            System.exit(1);
            
        }


        //Prompts user for each junction coordinate
        for (int i = 0; i< numOfJunctions; i++) {
            int[] junctionCoord = new int[2];
            System.out.println("Enters the coordinates of next target point as x,y (no spaces). X and Y has to be between 0 and 10: ");
            String junctionCoordInput = scannerInput.nextLine();
            String[] junctionPoint = junctionCoordInput.split(",");
            junctionCoord[0] = Integer.valueOf(junctionPoint[0].trim());
            junctionCoord[1] = Integer.valueOf(junctionPoint[1].trim());
            
            //System.out.println(junctionCoord[0] + " , " + junctionCoord[1]);
                    
            //add points to the coordinate list
            coordsList.add(junctionCoord);

        }

        scannerInput.close();


       // call the function
        List<Object[]> result = pathFind(startingPoint, coordsList);

        //print results
        System.out.println();
        System.out.println("========");
        System.out.println("RESULT");
        System.out.println("========");
        System.out.println("Here are the steps the bot will take to go from the starting point to all target points, and back to the start. The result is in the format (u , d) which u is the amount of units moved and d is the direction (U for up, D for down, L for left , R for right)");
        System.out.println();

        System.out.println("Starting Point: (" + startingPoint[0] + ","+ startingPoint[1]+")");
        int distanceMoved = 0;
       for(int a=0;a<result.size();a++){
        Object[] aStep = result.get(a);

        switch ((String)aStep[1]){
            case "U":
            startingPoint[1] += (int) aStep[0];
            break;
            case "D":
            startingPoint[1] -= (int) aStep[0];
            break;
            case "L":
            startingPoint[0] -= (int) aStep[0];
            break;
            case "R":
            startingPoint[0] += (int) aStep[0];
        }
        distanceMoved += (int)aStep[0];
        
            //print results
           System.out.println("("+ aStep[0]+" , "+ aStep[1] + ") AT ("+startingPoint[0] +","+startingPoint[1]+") distance traveled = " + distanceMoved); 
       } 
       
    }

    /**
     * Finds the distance between two points.
     * point1 - coordinates of 1st point
     * point2 - coordinates of 2nd point
     * returns the straight line distance between points
     */
    static double distance(int[] point1, int[] point2){
        //find the distance between 2 points using distance formula 
        double distance = (point2[0]-point1[0])^2 + (point2[1]-point1[1])^2;
        
        distance = Math.sqrt(distance);

        return distance;
    }
    /**
     * finds the path from one point to the other with only orthagonal movements
     * point1 - coordinates of point 1
     * point2 - coordinates of point 2
     * returns a 2d array , in which each row is a step, column 1 is the number of units moved and column 2 is the direction (u,d,l,r)
     */
    static ArrayList<Object[]> orthagPath(int[] point1, int[] point2){

        ArrayList<Object[]> path = new ArrayList<>();
        
        // calculate horizontal movement
        Object[] HrztlMv = new Object[2];
        Object[] VrtMv = new Object[2];
        if(point2[0] > point1[0]){
            HrztlMv[0] = point2[0] - point1[0];
            HrztlMv[1] = "R";
            path.add(HrztlMv);

        } else if(point2[0] < point1[0]){
            HrztlMv[0] = point1[0] - point2[0];
            HrztlMv[1] = "L";
            path.add(HrztlMv);
            
        } // else no need to move left or right

        //calculate vertiical movement
        if(point2[1] > point1[1]){
            VrtMv[0] = point2[1] - point1[1];
            VrtMv[1] = "U";
            path.add(VrtMv);

        } else if(point2[1] < point1[1]){
            VrtMv[0] = point1[1] - point2[1];
            VrtMv[1] = "D";
            path.add(VrtMv);
        } // else no need to move up or down

        
       
        return path;

    }


    /**
     * Dad's better path find algorithm.
     */
    static int pathFind2(int[] curr, ArrayList<int[]> coordsList, int distance, List<Object[]> steps) {
        if (coordsList.isEmpty()) return distance;

        coordsList.sort(Comparator.comparing(coord -> manhattanDistance(coord, curr)));

        for (int i=0;i<coordsList.size();i++) {
            
        }

        return 1;
    }

    static int manhattanDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    /**
     * Find the steps that the robot needs to take to get from the start to n targets and back
     * startcoord - starting coordinates
     * coordslist - list of target coordinates
     * returns a ArrayList of steps the robot needs to take 
     */

    static List<Object[]> pathFind(int[] startCoord, ArrayList<int[]> coordsList){

        int[] curr = new int[2];
        curr[0] = startCoord[0];
        curr[1] = startCoord[1];
        

        List<Object[]> steps = new ArrayList<>();

        while(!coordsList.isEmpty()){

            // sort desc by distance
            coordsList.sort(new Comparator<int[]>() {
                public int compare(int[] p1, int[] p2) {
                    double d1 = distance(curr, p1);
                    double d2 = distance(curr, p2);
                    if (d1 > d2) return -1;
                    if (d2 > d1) return 1;
                    return 0;
                }
            });
            int[] nextLoc = coordsList.remove(coordsList.size() - 1);

            //Pathfind from each coordinate to the next and add it to the full ArrayList.
            ArrayList<Object[]> movements = orthagPath(curr, nextLoc);
            for (int i = 0; i < movements.size() ; i++){
                
               steps.add(movements.get(i));
                
            }
            curr[0] = nextLoc[0];
            curr[1] = nextLoc[1];
            
        }

        //Add the steps to go from the furthest point back to the starting point.
        if (curr[0] != startCoord[0] || curr[1] != startCoord[0]){
            ArrayList<Object[]> movements = orthagPath(curr, startCoord);
            for (int i = 0; i < movements.size() ; i++){
                
                steps.add(movements.get(i));
            
            }
        }

        return steps;
    }

    
    
}