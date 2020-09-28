package com.wangtk.ribbon.reactive;

import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ObservableTestTest {


    @Test
    public void testCreate() {
        //初始化被观察者Observable，并给其加上数据处理器Observable.OnSubscribe
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("杨");
                subscriber.onNext("月");
                subscriber.onCompleted();
            }
        });


        //初始化观察者Observer，视作结果接收器
        Observer observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String string) {
                System.out.println("RxJava 结果:" + string);
            }
        };
        //订阅
        observable.subscribe(observer);

    }

    @Test
    public void testJust() {


        Observable<String> myObservable = Observable.just("just1", "just2", "just1", "just1", "just1", "just1", "just1", "just1", "just1", "just1");

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("onNext " + s);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted.................");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError....................");
            }
        };

        myObservable.subscribe(mySubscriber);

    }

    @Test
    public void testForm() {
        // 将数据转换成为Observables，而不是需要混合使用Observables和其它类型的数据

        String[] items = {"just1", "just1", "just1", "just1", "just1", "just1"};

        Observable<String> myObservable = Observable.from(items);

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted.................");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError....................");
            }
        };

        myObservable.subscribe(mySubscriber);
    }

    @Test
    public void testRepeat() throws ExecutionException, InterruptedException {
        String[] items = {"just1", "just2", "just3", "just4", "just5", "just6"};

        Observable<String> myObservable = Observable.from(items).repeat(2);


        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted.................");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError....................");
            }
        };

        myObservable.subscribe(mySubscriber);


        Future tFuture = Observable.defer(() -> {
            return Observable.unsafeCreate(new Observable.OnSubscribe<Object>() {
                @Override
                public void call(Subscriber<? super Object> subscriber) {
                    subscriber.onStart();
                    subscriber.onNext("123");
                    subscriber.onCompleted();
                }
            });
        }).toBlocking().toFuture();


        tFuture.get();
    }

}