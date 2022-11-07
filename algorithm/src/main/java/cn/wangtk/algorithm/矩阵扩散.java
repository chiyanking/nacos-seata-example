package cn.wangtk.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 矩阵扩散 {
    
    public static void main(String[] args) {
        Queue<int[]> queue = new LinkedList<>();
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        String[] values = line.split(",");

        int n1 = Integer.parseInt(values[0]);
        int n2 = Integer.parseInt(values[1]);

        int n3 = Integer.parseInt(values[2]);
        int n4 = Integer.parseInt(values[3]);


        int n5 = Integer.parseInt(values[4]);
        int n6 = Integer.parseInt(values[5]);

        int[][] matrix = new int[n1][n2];
        int times = 0;
        queue.add(new int []{n3,n4});
        matrix[n3][n4] = 1;
        queue.add(new int []{n5,n6});
        matrix[n5][n6] = 1;
        while(!queue.isEmpty()){

            int length = queue.size();
            Boolean hasEmpty = false;
            for (int i = 0; i < length; i++) {
                int[] z = queue.poll();
                int x = z[0];
                int y = z[1];
                if (x + 1 < matrix.length && matrix[x + 1][y] == 0) {
                    queue.add(new int[] { x + 1, y });
                    matrix[x+1][y] = 1;
                    hasEmpty = true;
                }
                if (x - 1 >= 0 && matrix[x - 1][y] == 0) {
                    queue.add(new int[] { x - 1, y });
                    matrix[x-1][y] = 1;
                    hasEmpty = true;
                }
                if (y + 1 < matrix[0].length && matrix[x][y + 1] == 0) {
                    queue.add(new int[] { x, y + 1 });
                    matrix[x][y + 1] = 1;
                    hasEmpty = true;
                }
                if (y - 1 >= 0 && matrix[x][y - 1] == 0) {
                    queue.add(new int[] { x, y - 1 });
                    matrix[x][y - 1] = 1;
                    hasEmpty = true;
                }
            }
            if(hasEmpty){
                times++;
            }
        }
        System.out.println(times);
    }
   
}