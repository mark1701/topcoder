import java.util.*;

public class Jewelry_StackOverflow {
    static int MAX_SUM=30000;
    static int MAX_N=30;

    /*  
        General Concept: 
            - Sort array 
            - Incrementally divide array into two partitions
               + Accomplished by using two different arrays - L for left, R for right 
            - Take all possible sums on the left side and match with all possible sums
              on the right side (multiply these numbers to get totals for each sum)
            - Adjust for common sums so as to not overcount
            - Adjust for duplicate numbers
    */
    public static long howMany(int[] values) {

        int n=values.length;

        /* 
            Incrementally split the array and calculate sums on either side
            For eg. if val={2, 3, 4, 5, 9}, we would partition this as 
            {2 | 3, 4, 5, 9} then {2, 3 | 4, 5, 9}, etc.  
            First, sort it ascendingly and generate its sum matrix L
            Then, sort it descendingly, and generate another sum matrix R
            In later calculations, manipulate indexes to simulate the partitions
            So at any point L[i] would correspond to R[n-i-1]. eg. L[1] = R[5-1-1]=R[3]
        */

        // Sort ascendingly
        Arrays.sort(values);

        // Generate all sums for the "Left" partition using the sorted array
        long[][] L = countWaysSum(values);
        //printMatrix(L);

        // Sort descendingly by reversing the existing array.
        // Java 8 doesn't support Arrays.sort for primitive int types
        // Use Comparator or sort manually. This uses the manual sort.
        for(int i = 0;  i < n/2; i++) { 
            int tmp = values[i];
            values[i] = values[n-i-1];
            values[n-i-1] = tmp;
        }

        // Generate all sums for the "Right" partition using the re-sorted array
        long[][] R = countWaysSum(values);
        //printMatrix(R);
        
        // Re-sort in ascending order as we will be using values[] as reference later 
        Arrays.sort(values);

        // Pre-compute C(n,r) and store in combinations[][]
        long[][] combinations = nCr();

        long tot = 0;
        for(int i = 0; i < n; i++) {
            int dup=0;

            // How many duplicates of values[i] do we have?
            for(int j = 0; j < n; j++)
                if(values[j] == values[i]) dup++;

            /*
            Calculate total by iterating through each sum and multiplying counts on
            both partitions for that sum
            However, there may be count of sums that get duplicated
            For instance, if val={2, 3, 4, 5, 9}, you'd get:
                {2, 3 | 4, 5, 9} and {2, 3, 4 | 5, 9}  (on two different iterations) 
            In this case, the subset {2, 3 | 5} is counted twice
            To account for this, exclude the current largest number, val[i], from L's
            sum and exclude it from R's i index
            There is another issue of duplicate numbers
            Eg. If values={2, 3, 3, 3, 4}, how do you know which 3 went to L? 
            To solve this, group the same numbers
            Applying to {2, 3, 3, 3, 4} :
                - Exclude 3, 6 (3+3) and 9 (3+3+3) from L's sum calculation
                - Exclude 1, 2 and 3 from R's index count 
            We're essentially saying that we will exclude the sum contribution of these
            elements to L and ignore their count contribution to R
            */

            for(int u = 1; u <= dup; u++) {
                int dupSum = u * values[i];
                for(int sum = dupSum; sum < MAX_SUM; sum++) {
                    // (ways to pick u numbers from dup) * (ways to get sum-dupSum from i numbers) * (ways to get sum from n-i-u numbers)
                    if(n-i-u>=0)
                        tot += combinations[dup][u] * L[i][sum-dupSum] * R[n-i-u][sum];
                }
            }

            // Skip past the duplicates of values[i] that we've now accounted for
            i += dup-1;
        }
        return tot;
    }

    // Generate all possible sums
    // ways[i][sum] = number of ways to compute sum using the first i numbers from val[]
    //this is foundamentally knapsack
    private static long[][] countWaysSum(int[] val) {

        long[][] ways = new long[MAX_N+1][MAX_SUM];

        ways[0][0] = 1;
        for(int i = 0; i < val.length; i++) 
            for(int sum = 0; sum < MAX_SUM; sum++) {
                // Carry over the sum from i to i+1 for each sum
                // Problem definition allows excluding numbers from calculating sums
                // So we are essentially excluding the last number for this calculation
                ways[i+1][sum] = ways[i][sum];

                // DP: (Number of ways to generate sum using i+1 numbers = 
                //         Number of ways to generate sum-val[i] using i numbers)
                if(sum >= val[i])
                    ways[i+1][sum] += ways[i][sum-val[i+1-1]]; //i+1-1 is the normalised index since it runs over val[] and not over ways[][]
            }
        
        return ways;
    }

    // C(n, r) - all possible combinations of choosing r numbers from n numbers
    // Leverage Pascal's polynomial co-efficients for an n-degree polynomial
    private static long[][] nCr() {
        long[][] combinations = new long[MAX_N+1][MAX_N+1]; 
        combinations[0][0] = 1;
        for(int n = 1; n <= MAX_N; n++) {
            combinations[n][0] = 1;
            for(int r=1; r <= MAX_N; r++)
                combinations[n][r] = combinations[n-1][r-1] + combinations[n-1][r]; 
         }
         
         return combinations;
    }

    private static void printMatrix(long[][] matrix){
        System.out.println("Matrix start ------------------");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Matrix end -------------------");
    }
}