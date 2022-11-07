package cn.wangtk.algorithm;

public class 篮球5v5 {
    static boolean[] visited;

    public static void main(String[] args) {

        int[] num = new int[] { 1000, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        int sum = 0;
        visited = new boolean[10];

        for (int k : num) {
            sum += k;
        }
        // a |_____b_____|___min___|
        // b |_____b_____|
        // a+b = sum
        // b+min+b = sum

        for (int min = 0; min <= sum; min++) {
            int target = (sum - min);
            if (target % 2 == 0) {

                if (dfs(target / 2, num, 0)) {
                    System.out.println("zhei" + min);
                    break;
                }
            }

        }
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                System.out.print(num[i] + " ");
            }
        }

    }

    static public boolean dfs(int target, int[] arr, int index) {

        int length = arr.length;
        if (index > 5 || target < 0) {
            return false;
        }

        if (index == 5 && target == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {

            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            if (dfs(target - arr[i], arr, index + 1)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }

}
