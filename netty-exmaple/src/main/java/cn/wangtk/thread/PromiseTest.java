package cn.wangtk.thread;

import org.riversun.promise.Func;
import org.riversun.promise.Promise;

/**
 * 参考文章
 */
public class PromiseTest {
    public static void main(String[] args) {

        Func function1 = (action, data) -> {
            new Thread(() -> {
                System.out.println("Process-1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                //Specify result value.(Any type can be specified)
                action.resolve("Result-1");
            }).start();
        };

        Func function2 = (action, data) -> {
            System.out.println("Process-2 result=" + data);
        };

        Promise.resolve()
                .then(new Promise(function1))
                .then(new Promise(function2))
                .start();// start Promise operation

        System.out.println("Hello,Promise");
    }
}
