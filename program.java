    import java.lang.Math;

class program {
    public static void main(String[] args) {
        
        //define starting and target coordinates
        int[] s = new int[2];
        int[] j1 = new int[2];
        int[] j2 = new int[2];
        s[0] = 0;
        s[1] = 1;
        j1[0] = 0;
        j1[1] = 2;
        j2[0] = 3;
        j2[1] = 3;
        // pathfind and print result
        //int[][] resultoffind = pathFind(grid);
       // System.out.println(resultoffind[0][0]);
       int[] testpoint1 = new int[2];
       int[] testpoint2 = new int[2];
       testpoint1[0] = 0;
       testpoint1[1] = 1;
       testpoint2[0] = 1;
       testpoint2[1] = 2;

       
       double sample = distance(s,j1);
       System.out.println(sample);
       
 
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
    static int[][] orthagPath(int[] point1, int[] point2){
        int[][] path = new int[2][2];
        // for the second value in the 2 dimensional array, 1 is up , 2 is left, 3 is down, 4 is right, and 0 is the same place
        if( point2[0] > point1[0]){
            //find if the x coord of point 2 is greater than the x coord of point 1
            path[0][0] = point2[0] - point1[0];
            path[0][1] = 4;


        } else if( point2[0] < point1[0]){
            path[0][0] = point1[0] - point2[0];
            path[0][1] = 2;
        } else{
            path[0][0] = 0;
            path[0][1] = 0;

        }
        if( point2[1] > point1[1]){
            path[1][0] = point2[1] - point1[1];
            path[1][1] = 1;


        } else if( point2[1] < point1[1]){
            path[1][0] = point1[0] - point2[0];
            path[1][1] = 3;
        } else{
            path[1][0] = 0;
            path[1][1] = 0;
        }
        return path;

    }
    /**
     * Find the steps that the robot needs to take to get from the start to two targets and back
     * startcoord - starting coordinates
     * coords1 - coordinates of 1st target
     * coords2 - coordinates of 2nd target
     * 
     */

    static int[][]  pathFind(int[] startcoord, int[] coords1, int[] coords2){
        int[][] result = new int[6][2];
        
        
        //find and compare the distance between start and each coordinate
        double distance1 = distance(startcoord, coords1);
        double distance2 = distance(startcoord, coords2);
        //state the 3 arrays for each movement
        int[][] movement1 = new int[2][2];
        int[][] movement2 = new int[2][2];
        int[][] movement3 = new int[2][2];
        
        if(distance1 < distance2){
            movement1 = orthagPath(startcoord, coords1);
            movement2 = orthagPath(coords1, coords2);
            movement3 = orthagPath(coords2, startcoord);
        } else {
            movement1 = orthagPath(startcoord, coords2);
            movement2 = orthagPath(coords2, coords1);
            movement3 = orthagPath(coords1, startcoord);
        }

        for(int i = 0;i<3;i++){
            

        }

        
        
        return result;
    }

    
    
}