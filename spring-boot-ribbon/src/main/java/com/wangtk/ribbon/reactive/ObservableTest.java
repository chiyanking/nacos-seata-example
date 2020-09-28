package com.wangtk.ribbon.reactive;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

@Slf4j
public class ObservableTest {


    public static void main(String[] args) {

        //创建一个观察者
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {
                log.info("完成");
            }

            @Override
            public void onError(Throwable e) {
                log.info("Error");
            }

            @Override
            public void onNext(String s) {
                log.info("onNext " + s);
            }
        };
        //使用Observable.create()创建被观察者
        Observable observable1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                subscriber.onNext("data1");
                subscriber.onNext("data2");
                subscriber.onCompleted();
            }
        });

        observable1.subscribe(observer);


        Observable observable2 = Observable.just("Hello", "World");
        observable2.subscribe(o -> {
            System.out.println("action " + o);

        }, (Action1<Throwable>) throwable -> {
            System.out.println("action " + throwable);
        });
    }


}
