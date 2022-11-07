package cn.wangtk.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 斗地主之顺子 {
    public static void main(String[] args) {

        String str = "2 9 J 10 3 4 K A 7 Q A 5 6";
        String[] sortList = str.split(" ");

        sortList = Stream.of(sortList).sorted((a, b) -> {
            return Integer.compare(getInt(a), getInt(b));
        }).distinct().toArray(String[]::new);

        List<String> res = new ArrayList<>();
        List<String> num = new ArrayList<>();
        if(!Objects.equals(sortList[0], "2")){
            num.add(sortList[0]);
        }
        
        

        for (int i = 1; i < sortList.length; i++) {
            if (Objects.equals(sortList[i], sortList[i-1])) {
                continue;
            } else if (!Objects.equals(sortList[i], "2")
                    && Integer.compare(getInt(sortList[i-1]) + 1, getInt(sortList[i])) == 0) {
                num.add(sortList[i]);
            } else {
                if (num.size() >= 5) {
                    res.add(num.stream().collect(Collectors.joining(",")));
                    num.clear();
                    num.add(sortList[i]);
                } else {
                    num.clear();
                }
            }

        }
        if(num.size() >= 5){
            res.add(num.stream().collect(Collectors.joining(",")));
        }

        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    static int getInt(String poker) {
        if (poker.equals("J")) {
            return 11;
        } else if (poker.equals("Q")) {
            return 12;
        } else if (poker.equals("K")) {
            return 13;
        } else if (poker.equals("A")) {
            return 14;
        } else {
            return Integer.parseInt(poker);
        }
    }

}
