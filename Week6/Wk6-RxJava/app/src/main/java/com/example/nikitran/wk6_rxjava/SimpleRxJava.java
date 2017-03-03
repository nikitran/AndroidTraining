package com.example.nikitran.wk6_rxjava;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by nikitran on 3/2/17.
 */

public class SimpleRxJava {
    public static void main(String[] args){
        System.out.println("Hello");

        //1. create the observable component
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Observable<Integer> observable1 = Observable.just(11, 12, 15);

        //2. create and observer
        Observer<Integer> observer = new Observer<Integer>() {

            // the 3 method that all observers have
            @Override
            public void onCompleted() {
                System.out.println("onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext: " + integer + " " + Thread.currentThread());

            }
        };

// example 1. observe all ----------------------------------------------------------------
//        observable.subscribe(observer);

// example 2. applies the a filter -------------------------------------------------------
//        observable.filter(new Func1<Integer, Boolean>() {
//
//            @Override
//            public Boolean call(Integer integer) {
//                return integer % 2 == 1;
//            }
//        }).subscribe(observer);                                            //returns a boolean
//
//        observable.subscribe(observer);

// example 3. concatenation ----------------------------------------------------------------
//        Observable.concat(observable, observable1)
//                .subscribe(observer);
//
// example 4. doing work on one thread and displaying to another thread --------------------
//        observable.subscribeOn(Schedulers.io())             //io block - off the main thread
//                .observeOn(Schedulers.newThread())          //Android: AndroidSchedulers.mainThread - return to the main thread
//                .subscribe(observer);

// example 5. applying a filter and tracking the thread that's doing work --------------------
        observable
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        System.out.println(Thread.currentThread());
                        return integer % 2 == 1;
                    }
                })
                .subscribeOn(Schedulers.io())               // where the thread is doing work
                .observeOn(Schedulers.newThread())          // where the data is being returned, Android: AndroidSchedulers.mainThread - return to the main thread
                .subscribe(observer);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
