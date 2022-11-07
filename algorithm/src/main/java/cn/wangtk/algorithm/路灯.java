package cn.wangtk.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class 路灯 {

    public static void main(String[] args) {

        for (int j = 0; j < 2; j++) {
            Scanner sc = new Scanner(System.in);
            String line1 = sc.nextLine();
            String[] line1Arrays = line1.split(" ");
            int num = Integer.parseInt(line1Arrays[0]);
            int[] lightNum = new int[num];
            int lightLong = Integer.parseInt(line1Arrays[1]);

            String line2 = sc.nextLine();
            String[] line2Arrays = line2.split(" ");
            for (int i = 0; i < line2Arrays.length; i++) {
                lightNum[i] = Integer.parseInt(line2Arrays[i]);
            }
            Arrays.sort(lightNum);

            int maxLens = 0;
            int last = 0;

            for (int i = 0; i < line2Arrays.length; i++) {
                if (lightNum[i] - last > maxLens) {
                    maxLens = lightNum[i] - last;
                }
                last = lightNum[i];
            }
            if (lightLong > last) {
                int tm = lightLong - last;
                if (tm > last) {
                    last = tm;
                }
            }
            System.out.printf("%.2f", Float.valueOf(maxLens) / 2);
        }
    }
}
// 519 13655109
// 8878441 2144518 13154905 1348964 1295664 1213365 9035019 10294848 2272662
// 9256024 3640314 3262221 9432847 10044108 7833178 5185688 12697144 8885683
// 3314718 12079020 3346887 687428 6641898 3162568 3622870 12163908 6534093
// 8747864 4523601 5755856 11076588 12136073 4158760 8103734 8214316 11680231
// 5592074 1557226 10179625 13494063 760347 11795786 8332234 1000880 5232193
// 11944938 13136604 4151811 13607790 7031579 3556315 7705285 4345456 9793779
// 9906112 3273343 7879352 3253520 11521847 1709675 9509155 8554751 12550665
// 12558368 8815091 2886753 10456180 7766309 10386258 10740610 5780234 10047440
// 7776073 9894276 1494083 10716712 13520452 8072949 2993939 6895869 472423
// 6387961 11308530 7866085 950599 12990505 3635731 11322963 12793766 13230308
// 4962624 4512146 12914546 5433804 373434 1373404 2302156 4969675 6936663
// 8615154 11651578 2420346 4816691 11540709 11470129 4715304 7550980 9410703
// 9771674 3918784 5856442 843290 2762837 7008065 9916055 1562767 12812397
// 11979710 10716152 7058519 3157372 4412207 5118056 2176806 9874317 8988207
// 7977805 567416 13225096 9529930 11328349 11517983 11386934 4580111 758620
// 2406480 12212458 9421602 682733 4183644 12525653 10295851 13386217 6386345
// 12555532 11253605 7415526 1177282 2133466 12831615 12971452 9778310 5112262
// 6256382 5514442 5860079 12623382 2121710 12137519 4598252 11192429 7621622
// 2297381 1973528 4186495 11798822 7191030 9366777 919268 1051111 11882338
// 2567839 1110814 10235690 11641536 12598471 9964747 1325990 1928712 8156472
// 11673114 9710143 8182128 4549275 4419013 10265618 8331878 12537061 6470991
// 10341143 2611548 12292953 3470298 8837146 4536473 12854924 4340049 4216550
// 10126850 11736892 4985788 4278549 13388487 7070163 6459948 1657781 25401
// 1179423 2425396 5567593 9419408 6573778 9583256 11091431 5419556 4375792
// 2924129 7079172 3647530 7362999 2755170 9556121 10169390 12150088 1880949
// 9358236 2589224 5598937 4165529 7512072 7168152 3649233 12095480 12842770
// 2208251 12035401 4408911 12280278 2265091 6706490 1365589 6306369 12213848
// 12520448 358500 6037271 2436013 12835039 9187118 2304339 10302255 2169136
// 11015662 13123539 1823524 10442810 7179057 11266231 5133009 10649884 2517137
// 5813850 9087481 7758624 3049969 7745779 10239966 10612156 9456612 8270030
// 7948918 2600686 8689338 6735630 1520400 3504795 6938544 12762793 12554883
// 12774380 3827232 10613391 11203375 155687 4641036 7924987 2134425 7616160
// 3366316 6819144 6834508 7803954 11816139 10253947 1625526 440240 468765
// 2218986 5281067 13122870 7784829 7843764 5691448 7325022 5272295 13181075
// 11535787 4592174 5122056 8229412 13599040 5685675 12227938 9256554 7327371
// 7444395 408773 3288278 8318826 9589767 11751321 6219285 266188 11706841
// 7221791 7839829 12245439 10450702 11180045 5169871 2390732 11862870 6718759
// 3133116 6265217 10876244 1009851 3530914 4910176 1959727 5543730 331686
// 10380579 4141955 2628662 4973591 4033342 10845484 2527196 3573292 10538292
// 11503282 11325799 11085041 2379327 3909724 2171125 8229715 13341210 4745821
// 5343504 8523576 11977484 9022037 6594073 8309965 4892298 11375132 2539212
// 699554 3686959 2431800 11529290 10825824 4238202 8137923 8973524 7333299
// 2011990 9033735 5816560 9411243 9419391 7044258 3024056 1592549 12922481
// 1252643 12508493 3030873 4131109 9840905 6692142 9064008 9008696 6544692
// 10336023 3034052 10277685 9238874 4454650 7921444 2092043 357950 607316
// 4225459 5633732 11295884 9262839 2124789 387392 5028822 5450264 2450455
// 7575965 12028450 3768382 11141027 1159849 4458500 12026681 12678643 204889
// 9581859 1034176 2578191 11365256 11379119 12837781 2089061 8409994 3966688
// 10077740 6748616 5444511 9360086 498390 7315809 8810675 5480336 8375355
// 12482413 9552667 5041467 8734143 1560760 11167638 9853709 13240 5977312 70296
// 11069005 717412 1220157 10500493 2627154 4874765 9302465 11079994 4866240
// 8555163 8251234 7705773 2571938 1203944 4322862 12216983 251935 402767
// 8662739 8214367 5929614 4764879 9519690 5879868 2012672 9661288 9647015
// 6388438 10814134 7576945 8970636 7356972 12630232 12396356 11865341 365888
// 12795867 9299880 1184788 7611016 3126056 8206711 1744909 9032793 8156750
// 3523427 2468960 6768578 4659050 1736120 5437719 4944625 13379892 5249659
// 4013096 10148439 6154641 2182809 13560861 11259452 6836013 4794663 1336640
// 11814879 4455632 4789535 7548720 719894