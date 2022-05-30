/**
 * Nikolaidis Stavros
 * AEM: 3975
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DPnet {

    // function to find the smaller cost for task l
    static int mini(int l, int i, int m, int[][] costs, int[][] arr1, int[][] arr2) {
        int min = costs[l - 1][1] + arr2[1][i] + arr1[l][i];
        for (int k = 0; k < m; k++) {   // O(m) complexity
            int val = costs[l - 1][k] + arr2[k][i] + arr1[l][i];
            if (val < min)
                min = val;
        }
        return min;
    }

    // main function
    public static void main(String[] args) {
        // reading all the data
        try (BufferedReader buffer = new BufferedReader(new FileReader(args[0]))) {
            int n = Integer.parseInt(buffer.readLine());
            int m = Integer.parseInt(buffer.readLine());

            String line;
            int[][] arr1 = new int[n][m];
            int[][] arr2 = new int[m][m];

            buffer.readLine(); //skip line

            // filling the first array (arr1)
            int j=0;
            while(j<n){
                line = buffer.readLine();
                String[] s1 = line.split(" "); // putting the line's ints to an array
                for(int i=0; i<m; i++){
                    arr1[j][i] = Integer.parseInt(s1[i]);
                }
                j++;
            }

            buffer.readLine(); //skip line

            // filling the second array (arr2)
            j=0;
            while(j<m){
                line = buffer.readLine();
                String[] s1 = line.split(" "); // putting the line's ints to an array
                for(int i=0; i<m; i++)
                    arr2[j][i] = Integer.parseInt(s1[i]);
                j++;
            }

            int[][] costs = new int[n][m];

            // first row of the costs array
            System.arraycopy(arr1[0], 0, costs[0], 0, m);

            // filling the costs array
            for (int l = 1; l < n; l++)       // n tasks            O(n) complexity
                for (int i = 0; i < m; i++)   // m visual machines  O(m) complexity
                    costs[l][i] = mini(l, i, m, costs, arr1, arr2);
            // total complexity O(n*m*m) = O(n*(m^2))

            // printing the results
            for (int i = 0; i < n; i++) {
                for (int k = 0; k < m; k++) {
                    if (k != m - 1)
                        System.out.print(costs[i][k] + " ");
                    else
                        System.out.print(costs[i][k]);
                }
                System.out.println(); // printing the change of line
            }
            //exception error handling
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
