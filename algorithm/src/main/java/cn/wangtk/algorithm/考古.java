package cn.wangtk.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 考古 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> list = new ArrayList();

        while (true) {
            String str = br.readLine();
            Integer num = Integer.parseInt(str);
            if (num == 0) {
                break;
            }
            list.add(num);
        }
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            int count = 0;
            while (num > 2) {
                int drunk = num / 3;
                num = num - drunk * 3 + drunk;
                count += drunk;
            }
            if (num == 2) {
                System.out.println(count + 1);
            } else {
                System.out.println(count);
            }
        }

    }
}