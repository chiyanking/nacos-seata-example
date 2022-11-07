package cn.wangtk.algorithm;

public class 出错的或电路 {

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);

        // String strA = sc.nextLine();

        // String strB = sc.nextLine();

        // byte[] byteA = strA.getBytes();
        // byte[] byteB = strB.getBytes();

        // for (int i = 0; i < byteA.length; i++) {
        // int ad = ((byteA[i] - '0') ^ (byteB[i] - '0')) ^ 1;
        // System.out.print(ad+" ");
        // }

        int[] arr = new int[] { 3, 5, 6 };

        int val1 = 7258^6579^2602^6716^1773;
        int val2 = 3050^3564^5396;
        System.out.println(val1 == val2);

    }
}
