    import java.lang.Math;
    import java.util.*;

class program {
    public static void main(String[] args) {
        
        //define starting and target coordinates
        int[] s = new int[2];
        int[] j1 = new int[2];
        int[] j2 = new int[2];
        int[] j3 = new int[2];
        s[0] = 0;
        s[1] = 1;
        j1[0] = 0;
        j1[1] = 2;
        j2[0] = 3;
        j2[1] = 3;
        j3[0] = 1;
        j3[1] = 2;
        
        ArrayList<int[]> coordsList = new ArrayList<int[]>();
        //add points to the coordinate list
        coordsList.add(j1);
        coordsList.add(j2);
        coordsList.add(j3);
       
        List<Object[]> result = pathFind(s, coordsList);
       for(int a=0;a<result.size();a++){
        Object[] aStep = result.get(a);
            //print results
           System.out.println("("+ aStep[0]+" , "+ aStep[1] + ")"); 
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
        if( point2[0] > point1[0]){
            HrztlMv[0] = point2[0] - point1[0];
            HrztlMv[1] = "R";
            path.add(HrztlMv);
        } else if( point2[0] < point1[0]){
            HrztlMv[0] = point1[0] - point2[0];
            HrztlMv[1] = "L";
            path.add(HrztlMv);
            
        } // else no need to move left or right

        //calculate vertiical movement
        if( point2[1] > point1[1]){
            VrtMv[0] = point2[1] - point1[1];
            VrtMv[1] = "U";
            path.add(VrtMv);
        } else if( point2[1] < point1[1]){
            VrtMv[0] = point1[1] - point2[1];
            VrtMv[1] = "D";
            path.add(VrtMv);
        } // else no need to move up or down

        
       
        return path;

    }
    /**
     * Find the steps that the robot needs to take to get from the start to n targets and back
     * startcoord - starting coordinates
     * coordslist - list of target coordinates
     * returns a ArrayList 
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
            int[] nextLoc = coordsList.remove(coordsList.size()-1);
            //Pathfind from each coordinate to the next and add it to the full ArrayList.
            ArrayList<Object[]> movements = orthagPath(startCoord, nextLoc);
            for (int i=0;i<movements.size();i++){
                
               steps.add(movements.get(i));
                
            }
            curr[0] = nextLoc[0];
            curr[1] = nextLoc[1];
        }
        //Add the steps to go from the furthest point back to the starting point.

        if (curr[0] != startCoord[0] || curr[1] != startCoord[0]){
            ArrayList<Object[]> movements = orthagPath(curr, startCoord);
            for (int i=0;i<movements.size();i++){
                
                steps.add(movements.get(i));
            
            }
        }

        return steps;
    }

    
    
}