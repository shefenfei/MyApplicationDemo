package com.fenfei.myapplicationdemo.ui;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.beans.User;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subjects.PublishSubject;

public class FileSelectorActivity extends AppCompatActivity {

    private String TAG = "FileSelectorActivity";
    private Single<User> mUserSingle;
    private SingleEmitter<User> mEmitter;

    @TargetApi(24)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_selector);

        Single.create((SingleOnSubscribe<User>) e -> {
            mEmitter = e;
        }).subscribe(mSingleObserver);

        Button button = (Button) findViewById(R.id.button_select);
        button.setOnClickListener(view -> {
            User user = new User();
            user.setUsername("shefenfei");
            mEmitter.onSuccess(user);
        });


        findViewById(R.id.activity_file_selector).setOnClickListener(this::test);

        List<User> users = new ArrayList<>();
        User user;
        for (int i = 0; i < 10; i++) {
            user = new User();
            user.setUsername("username-" + i);
            user.setSex(i % 2 == 0 ? 1 : 0);
            user.setAge(i);
            users.add(user);
        }


        TestSubject();


        ObservableSource<User> obserSouce = (observer) -> observer.onNext(new User());


        users.parallelStream()
                .filter(u -> u.getSex() == 1)
                .collect(Collectors.toList())
                .forEach(u -> System.out.println(u.getUsername()));


        OptionalDouble sum = users.parallelStream()
                .filter(u -> u.getSex() == 1)
                .mapToInt(User::getAge)
                .average();
        System.out.println("平均：" + sum);


        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return user.getUsername().compareTo(t1.getUsername());
            }
        });


        Function<User, String> userStringFunction = User::getUsername;

        users.parallelStream()
                .forEach(u -> u.print(userStringFunction));


        Predicate<User> predicate = p -> p.getSex() == 1;
        System.out.println("...." + predicate.toString());

        Collections.sort(users, (user1, user2) -> user1.getUsername().compareTo(user2.getUsername()));


        Map<Integer, IntSummaryStatistics> collect = users.parallelStream()
                .filter(user1 -> user1.getUsername().equals("username-1"))
                .distinct()
                .collect(Collectors.groupingBy(p -> p.getSex(), Collectors.summarizingInt(p -> 1)));

        //Action0-->Action
        Action action = () -> {

        };

        //Action1-->Consumer
        Consumer consumer = (t) -> {

        };

        //Action2-->BiConsumer
        BiConsumer biConsumer = (t1, t2) -> {

        };

        //ActionN
        Consumer<Object[]> consumer1 = (t) -> {

        };

        io.reactivex.functions.Function function = o -> null;

        Function3 function3 = (t1, t2, t3) -> t3;
        Function4<User, String, Integer, Boolean, User> function4 = (t1, t2, t3, t4) -> t1;

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };


        Flowable.timer(1000 , TimeUnit.MILLISECONDS).map(new io.reactivex.functions.Function<Long, String>() {
            @Override
            public String apply(@NonNull Long aLong) throws Exception {
                return null;
            }
        }).filter(s -> s.length() > 2);

//        Flowable.amb()

        Flowable.range(1, 10)
                .subscribe(subscriber);

        Flowable.just(1)
                .subscribe(
                        subscriber::onNext,
                        subscriber::onError
                );

        Flowable.create((FlowableEmitter<Integer> emitter) -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
        }, BackpressureStrategy.BUFFER);
    }

    private void TestSubject() {
        Flowable.just("test.txt")
                .map(name -> File.listRoots())
                .subscribe(
                        files -> System.out.println(files.length),
                        error -> System.out.println(error.getMessage())
                );

        TestScheduler testScheduler = new TestScheduler();
        PublishSubject<User> publishSubject = PublishSubject.create();
        TestObserver<User> test = publishSubject.delay(1000, TimeUnit.MILLISECONDS, testScheduler)
                .test();

        test.assertEmpty();
        User user1 = new User();
        user1.setUsername("username_123");
        test.onNext(user1);
        testScheduler.advanceTimeBy(999, TimeUnit.MILLISECONDS);
    }

    private SingleObserver<User> mSingleObserver = new SingleObserver<User>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onSuccess(@NonNull User user) {
            Log.e(TAG, "onSuccess: " + user.getUsername());
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }
    };

    private void singleTest(Single<User> mUserSingle) {
        mUserSingle.subscribe(new SingleObserver<User>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull User user) {
                Log.e("TAG", "onSuccess: " + user.getUsername());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }


    private void test(View view) {

    }
}
