package com.fenfei.myapplicationdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.beans.User;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxDemoActivity extends AppCompatActivity {


    private String TAG = "RxDemoActivity";
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_demo);

//        rxMethod();

//        rxDebeounce();
//        rxElementAt();
//        rxFilter();
//        rxFirst();
//        rxIgnoreElements();
//        rxLast();
//        rxSample();
//        rxSkip();
//        rxTake();
//        rxAnd_Then_When();
//        rxErrorHandler();
//        onErrorResumeNext();
//        onExceptionResumeNe;
//        rxAll();
//        rxBlocking();
//        testBuffer();
//        testEditText();
//        testButtonClick();
        testTimestamp();
    }

    private void testTimestamp() {
        List<User> datas = new ArrayList<>();
        datas.add(new User("uid2", "username1"));
        datas.add(new User("uid3", "username1"));
        datas.add(new User("uid4", "username1"));
        datas.add(new User("uid5", "username1"));

        Flowable.fromIterable(datas)
                /*
                .doOnEach(userNotification -> {
                    userNotification.getValue().setUsername("changed name ");
                })
                */
                .delay(3 , TimeUnit.SECONDS)
                .doOnEach(new Subscriber<User>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.e(TAG, "onNext: " + user.getUsername());
                        user.setUsername("changed name");
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError: " + t.getMessage() );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " );
                    }
                })
                .timestamp(TimeUnit.SECONDS)
                .timeInterval()
                .doOnTerminate(() -> {
                    Log.e(TAG, "testTimestamp: doOnTerminate" );
                })
                .doOnNext(userTimed -> {
                    Log.e(TAG, "testTimestamp: OnNext 之前执行 " + userTimed.value().value().getUsername() );
                    userTimed.value().value().setUsername("再一次改名 name changed");
                })
                .subscribe(s -> Log.e(TAG, "testTimestamp: " + s),
                        error -> {
                            Log.e(TAG, "testTimestamp:Error= " + error.getMessage());
                        });
    }


    //防按钮多次点击
    private void testButtonClick() {
        Button button = (Button) findViewById(R.id.click_double);
        Flowable.create(s -> {
            button.setOnClickListener(v -> {
                s.onNext("1111");
            });
        }, BackpressureStrategy.BUFFER)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(s -> {
                    Log.e(TAG, "testButtonClick: ");
                });
    }

    private void testEditText() {
        mEditText = (EditText) findViewById(R.id.edittext_query);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Flowable.create(s -> {
                    s.onNext(editable.toString());
                }, BackpressureStrategy.BUFFER)
                        .debounce(2000, TimeUnit.MILLISECONDS) //2s 之前没有发送数据，在之后没有数据发送的时候，发第一条
                        .observeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .filter(content -> content.toString().trim().length() > 0)
                        .flatMap(map -> {                     // 执行网络操作
                            List<String> list = new ArrayList<String>();
                            for (Character sequence : map.toString().toCharArray()) {
                                list.add(sequence.toString());
                            }
                            return Flowable.just(list);
                        })
                        .subscribe(strings -> {
                            Log.e(TAG, "afterTextChanged: " + strings);
                        });
            }
        });
    }

    //rxjava2 all操作符
    private void rxAll() {
        List<User> datas = new ArrayList<>();
        datas.add(new User("uid2", "username1"));
        datas.add(new User("uid3", "username1"));
        datas.add(new User("uid4", "username1"));
        datas.add(new User("uid5", "username1"));


//        datas.add(1);
//        datas.add(2);
//        datas.add("abd");
//        datas.add("hello");

        /*
        Flowable.fromIterable(datas)
                .toList()
                .subscribe(s -> {
                    Log.e(TAG, "rxAll: " + s.size());
                    Log.e(TAG, "rxAll: " + s);
                });
        */

        Flowable.fromIterable(datas)
                .toMap(this::generateKey)
                .subscribe(s -> {
                    Log.e(TAG, "rxAll: " + s);
                });



        /*
        Flowable.fromIterable(datas)
                .contains(1)
//                .all(o -> o instanceof User)
                .subscribe(s -> {
                    Log.e(TAG, "rxAll: " + s );
                });

       */
//        Flowable.amb(datas)
//        Flowable.ambArray(datas);
//                .ambWith()
/*
        Flowable.create(Emitter::onComplete, BackpressureStrategy.BUFFER)
                .defaultIfEmpty("default 小盘股" )
                .subscribe(s -> {
                    Log.e(TAG, "rxAll: "+ s );
                });


        Flowable.create(Emitter::onComplete , BackpressureStrategy.BUFFER)
                .switchIfEmpty(Flowable.just(1 ,2,3))
                .subscribe(s -> {
                    Log.e(TAG, "rxAll: ." + s );
                });
                */

        List<User> lists = new ArrayList<>();
        lists.add(new User());
        lists.add(new User());
        lists.add(new User());
        lists.add(new User());

//        Flowable.ambArray(Flowable.fromIterable(lists) , Flowable.fromIterable(datas));

        /*
        Flowable.fromIterable(lists)
                .skipUntil(Flowable.just(1 , 2))
                .subscribe(s -> {
                    Log.e(TAG, "rxAll: " + s );
                });
        */

//        //类似于merge 操作
//        Flowable.concat(Flowable.fromIterable(lists) , Flowable.fromIterable(datas) )
//            .subscribe(s -> {
//                Log.e(TAG, "rxAll: " + s );
//            });
    }


    private void rxBlocking() {
        List<User> datas = new ArrayList<>();
        datas.add(new User("uid2", "username1"));
        datas.add(new User("uid3", "username1"));
        datas.add(new User("uid4", "username1"));
        datas.add(new User("uid5", "username1"));

        Flowable.fromIterable(datas)
                .blockingForEach(s -> {
                    Log.e(TAG, "rxBlocking: " + s);
                });

    }

    /**
     * 测试缓存
     */
    private void testBuffer() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                for (int i = 0; i < 10; i++) {
                    e.onNext(i + "");
                }
                e.onComplete();
            }
        }).buffer(500, TimeUnit.MILLISECONDS)  //缓存500毫秒内发射的数据
                .subscribe(strings -> {
                    //这里参数就为list，缓存了 500毫秒内发射的String
                    Log.e(TAG, "accept: " + strings.size());
                });
    }

    private <K> String generateKey(User obj) {
        return obj.getUid();
    }


    private void rxMethod() {
        //这个getMessage()方法在子线程中执行 ，可执行一些耗时的操作
        Flowable.fromCallable(() -> {
            //在这里可以执行耗时的操作
            return getMessage();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.e(TAG, "onCreate: " + s);
                });

        //这个getMessage()方法在主线程中执行
        Observable<String> o = getObservable(getMessage());
        o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.e(TAG, "onCreate: " + s);
                });


        Flowable.fromArray(new String[]{"a", "b", "c"})
                .map(this::convertStr)
                .filter(s -> s > 1)
                .firstElement()
                .subscribe(e -> {
                    Log.e(TAG, "onCreate: " + e);
                });


        User[] users = {new User("uid1", "username1", 1),
                new User("uid2", "username1", 2),
                new User("uid3", "username1", 3),
                new User("uid4", "username2", 4),
                new User("uid5", "username2", 5)};


//        rxGroupBy();
//        rxMap(users);

        ArrayList<String[]> list = new ArrayList<>();
        String[] words1 = {"Hello", "I am", "China!"};
        String[] words2 = {"Hello", "I am", "Beijing!", "SOHO"};
        list.add(words1);
        list.add(words2);

//        flatMapOperator(list);

//        switchMapOperator(list);

        List<Integer> inters = new ArrayList<>();
        inters.add(1);
        inters.add(1);
        inters.add(1);
        inters.add(1);
        inters.add(1);
        inters.add(1);
        inters.add(1);
        Flowable.fromIterable(inters)
                .scan(this::rxScanInt)
                .subscribe(s -> {
                    Log.e(TAG, "onCreate: scan..." + s);
                });


        Flowable.fromArray(users)
                .scan(this::rxScanUser)
//                .map(this::changeUserName)
//                .take(1)
                .subscribe(
                        s -> Log.e(TAG, "onCreate: ...scan..." + s.getUsername() + "......." + s.getAge()),
                        e -> Log.e(TAG, "onCreate: ...scan..Error" + e.getMessage())
                );


        Flowable.fromArray(users)
                .debounce(3, TimeUnit.SECONDS)
//                .buffer(2)
                .subscribe(users1 -> Log.e(TAG, "onCreate: debounce...." + users1));


        // TODO: 2017/7/11 fromFuture 操作符
        Flowable.fromFuture(new FutureTask<>(this::getMessage))
                .subscribe(s -> {
                    String a = new FutureTask<>(this::getMessage).get();
                    Log.e(TAG, "onCreate: fromFuture" + a);
                });
    }

    private User rxScanUser(User user, User user1) {
        User user2 = new User();
        int age = user.getAge() + user1.getAge();
        user2.setAge(age);
        return user2;
    }

    private Integer rxScanInt(Integer integer, Integer integer1) {
        return integer + integer1;
    }

    private String[] rxScan(String[] strings, String[] strings1) {

        return new String[0];
    }

    /**
     * switchMap 操作符
     *
     * @param list
     */
    private void switchMapOperator(ArrayList<String[]> list) {
        Flowable.fromIterable(list)
                .switchMap(this::rxSwitchMap)
                .subscribe(s -> {
                    Log.e(TAG, "onCreate: switchMap....." + s);
                });
    }

    private Flowable rxSwitchMap(String[] strings) {
        return Flowable.just(strings[2]);
    }

    private void flatMapOperator(ArrayList<String[]> list) {
        //需要更多的转换工作
        Flowable.fromArray(list) //注意fromArray的返回值
                .flatMap(this::rxFlatMap)
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.e(TAG, "onCreate:fromArray = " + s));

        //相对来说更加灵活
        Flowable.fromIterable(list) //注意fromIterable的返回值
                .flatMap(this::rxFlatMap) //更多的是合并数据源
                .subscribe(s -> Log.e(TAG, "onCreate:fromIterable = " + s));


        Flowable.fromIterable(list)
                .concatMap(this::rxFlatMap)
                .subscribe(s -> Log.e(TAG, "onCreate: concatMap....." + s));
    }

    /**
     * 使用rxJava2 groupBy 操作符
     */
    private void rxGroupBy() {
        User[] users = {new User("uid1", "username1"),
                new User("uid2", "username1"),
                new User("uid3", "username1"),
                new User("uid4", "username2"),
                new User("uid5", "username2")};

        Flowable.fromArray(users)
                //使用什么条件来分组 ,这里使用 username 来分组
                .groupBy(User::getUsername)
                .subscribe(groupedFlowable -> {
                    groupedFlowable.subscribe(user -> {
                        Log.e(TAG, "accept: " + groupedFlowable.getKey() + "....." + user.getUid());
                    });
                });
    }

    //rxjava map操作
    private void rxMap(User[] users) {
        Flowable.fromArray(users)
                .map(this::changeUserName)
                .subscribe(s -> {
                    Log.e(TAG, "onCreate: " + s.getUsername());
                });
    }

    //rxjava flatmap操作
    private Flowable rxFlatMap(String[] datas) {
        datas[0] = "rxFlatMap..." + datas[0];
        return Flowable.fromArray(datas);
    }

    //rxjava flatmap 操作
    @Nullable
    private Flowable rxFlatMap(ArrayList<String[]> list) {
        return Flowable.fromCallable(() -> {
            ArrayList<String> datasets = new ArrayList<>();
            for (String[] d : list) {
                for (String s : d) {
                    datasets.add(s);
                }
            }
            return datasets;
        });
    }


    private void rxElementAt() {
        User[] users = {new User("uid1", "username1"),
                new User("uid2", "username1"),
                new User("uid3", "username1"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid5", "username2")};

        Flowable.fromArray(users)
//                .elementAt(1)
                .elementAt(18, users[0]) //如果索引超过这个数据的个数，会返回默认的值 ，如果小于的话就会抛出异常
                .subscribe(
                        user -> Log.e(TAG, "rxElementAt: " + user.getUsername()),
                        error -> Log.e(TAG, "rxElementAt: " + error.getMessage())
                );
    }


    //过滤操作符
    private void rxFilter() {
        List<Object> datas = new ArrayList<>();
        datas.add(new User("uid2", "username1"));
        datas.add(new User("uid3", "username1"));
        datas.add(new User("uid4", "username1"));
        datas.add(new User("uid5", "username1"));
        datas.add(1);
        datas.add(2);
        datas.add("abd");
        datas.add("hello");


        Flowable.fromIterable(datas)
                .ofType(String.class) //对数据源的类型进行过虑
                .subscribe(
                        user -> Log.e(TAG, "rxElementAt: " + user),
                        error -> Log.e(TAG, "rxElementAt: " + error.getMessage())
                );
    }

    //first操作符
    private void rxFirst() {
        List<Object> datas = new ArrayList<>();
        datas.add(new User("uid2", "username1"));
        datas.add(new User("uid3", "username1"));
        datas.add(new User("uid4", "username1"));
        datas.add(new User("uid5", "username1"));
        datas.add(1);
        datas.add(2);
        datas.add("abd");
        datas.add("hello");

        Flowable.fromIterable(datas)
                .firstElement()
                .subscribe(s -> {
                    Log.e(TAG, "rxFirst: " + s);
                });
    }


    //IgnoreElements操作符
    private void rxIgnoreElements() {
        List<Object> datas = new ArrayList<>();
        datas.add(new User("uid2", "username1"));
        datas.add(new User("uid3", "username1"));
        datas.add(new User("uid4", "username1"));
        datas.add(new User("uid5", "username1"));
        datas.add(1);
        datas.add(2);
        datas.add("abd");
        datas.add("hello");

        Flowable.fromIterable(datas)
                .ignoreElements()
                .subscribe(() -> {
                    Log.e(TAG, "rxIgnoreElements: completed");
                });
    }


    //last操作符
    private void rxLast() {
        List<Object> datas = new ArrayList<>();
        datas.add(new User("uid2", "username1"));
        datas.add(new User("uid3", "username1"));
        datas.add(new User("uid4", "username1"));
        datas.add(new User("uid5", "username1"));
        datas.add(1);
        datas.add(2);
        datas.add("abd");
        datas.add("hello");

        Flowable.fromIterable(datas)
                .lastElement()
                .subscribe(s -> {
                    Log.e(TAG, "rxFirst: " + s);
                });
    }


    //sample操作符
    private void rxSample() {
        List<Object> datas = new ArrayList<>();
        datas.add(new User("uid2", "username1"));
        datas.add(new User("uid3", "username1"));
        datas.add(new User("uid4", "username1"));
        datas.add(new User("uid5", "username1"));
        datas.add(1);
        datas.add(2);
        datas.add("abd");
        datas.add("hello");

        Flowable.fromIterable(datas)
                .sample(3, TimeUnit.SECONDS)
                .subscribe(s -> {
                    Log.e(TAG, "rxFirst: " + s);
                });
    }


    //skip操作符
    private void rxSkip() {
        List<Object> datas = new ArrayList<>();
        datas.add(new User("uid2", "username1"));
        datas.add(new User("uid3", "username1"));
        datas.add(new User("uid4", "username1"));
        datas.add(new User("uid5", "username1"));
        datas.add(1);
        datas.add(2);
        datas.add("abd");
        datas.add("hello");

        Flowable.fromIterable(datas)
                .skip(1)
                .skipLast(1)
                .subscribe(s -> {
                    Log.e(TAG, "rxFirst: " + s);
                });
    }

    //take , 操作符
    private void rxTake() {
        List<User> datas = new ArrayList<>();
        datas.add(new User("uid2", "username1"));
        datas.add(new User("uid3", "username1"));
        datas.add(new User("uid4", "username1"));
        datas.add(new User("uid5", "username1"));
//        datas.add(1);
//        datas.add(2);
//        datas.add("abd");
//        datas.add("hello");

        Flowable.fromIterable(datas)
//                .flatMap(Flowable::just)
//                .take(1) //取前几个数据
                .takeUntil(u -> { //取数据直到条件成立
                    return u.getUid().equals("uid4");
                })
                .subscribe(s -> {
                    Log.e(TAG, "rxFirst: " + s);
                }, error -> {
                }, () -> {
                    Log.e(TAG, "rxTake: " + "completed");
                });
    }


    /**
     * rxjava2 Debounce操作符
     */
    private void rxDebeounce() {
        User[] users = {new User("uid1", "username1"),
                new User("uid2", "username1"),
                new User("uid3", "username1"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid5", "username2")};

        Flowable.fromArray(users)
//                .debounce(400 , TimeUnit.MILLISECONDS , AndroidSchedulers.mainThread())
//                .debounce(Flowable::just)
//                .distinct()
//                .distinct(User::getUid)
//                .distinctUntilChanged() //判断跟它的直接前驱是不是不同
                .distinctUntilChanged(User::getUid)
                .subscribe(s -> {
                    Log.e(TAG, "distinct: " + s);
                });
    }

    //错误处理
    private void rxErrorHandler() {
        //onErrorReturn
        onErrorReturn();
    }


    //onExceptionResumeNext
    private void onExceptionResumeNext() {
        Flowable.create((s) -> {
            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    s.onError(new Exception("出现异常数据了...")); //注意此处的Exception
                }
                s.onNext(i);
            }
            s.onComplete();
        }, BackpressureStrategy.BUFFER)
                /*
                .onExceptionResumeNext(s -> { //使用onExceptionResumeNext 来接收
                    s.onNext("错误替换的消息");
                    s.onComplete();
                })
                */
                .retry(3, t -> {
                    Log.e(TAG, "onExceptionResumeNext: " + t.getMessage());
                    return true;
                })
                .subscribe(s -> {
                    Log.e(TAG, "rxErrorHandler: " + s);
                }, error -> {
                    Log.e(TAG, "rxErrorHandler: " + error.getMessage());
                }, () -> {
                    Log.e(TAG, "rxErrorHandler: " + "完成 complete");
                });
    }


    //onErrorResumeNext是返回一个重新定义的Observable，onErrorNext返回的是发射的数据格式
    private void onErrorResumeNext() {
        Flowable.create((s) -> {
            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    s.onError(new Throwable("出现异常数据了..."));
                }
                s.onNext(i);
            }
            s.onComplete();
        }, BackpressureStrategy.BUFFER)
                .onErrorResumeNext(Flowable.just("出错了，来自onErrorResumeNext"))
                .subscribe(s -> {
                    Log.e(TAG, "rxErrorHandler: " + s);
                }, error -> {
                    Log.e(TAG, "rxErrorHandler: " + error.getMessage());
                }, () -> {
                    Log.e(TAG, "rxErrorHandler: " + "完成 complete");
                });
    }

    private void onErrorReturn() {
        Flowable.create((s) -> {
            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    s.onError(new Throwable("出现异常数据了..."));
                }
                s.onNext(i);
            }
            s.onComplete();
        }, BackpressureStrategy.BUFFER)
                .onErrorReturn((e) -> "error messgae: " + e.getMessage())  //当出现错误时，捕捉onError 。 发送一个数据发送complete状态
                .subscribe(result -> {
                    Log.e(TAG, "rxErrorHandler: " + result);
                }, error -> {

                }, () -> {
                    Log.e(TAG, "rxErrorHandler: 完成 complete ");
                });
    }


    //结合操作
    private void rxAnd_Then_When() {
        //and
        User[] users = {new User("uid1", "username1"),
                new User("uid2", "username1"),
                new User("uid3", "username1"),
                new User("uid4", "username2"),
                new User("uid4", "username2"),
                new User("uid4", "username2")};


        Integer[] inters = {1, 2, 3, 4, 5, 6, 7, 8, 9};


        Flowable f1 = Flowable.fromArray(users);
        Flowable f2 = Flowable.fromArray(inters);
        Flowable f3 = Flowable.interval(3000, TimeUnit.MILLISECONDS);

        /*
        Flowable.mergeDelayError(f1, f2).subscribe(o -> {
            Log.e(TAG, "rxAnd_Then_When: " + o );
        } , error -> {
            Log.e(TAG, "rxAnd_Then_When: " + error );
        });
        */

        /*
        Flowable.combineLatest(f1, f2, (o1, o2) -> o1 + "..." + o2) //组合最后发射的数据
            .subscribe(o -> {
                Log.e(TAG, "rxAnd_Then_When: " + o );
            });
       */


        Flowable.zip(f1, f3, (arg1, arg2) -> arg1 + " ... " + arg2)
                .startWith("hello im first element")
                .elementAt(-1)
                .onErrorResumeNext(observer -> {
                    observer.onSuccess(1);
                    observer.onSuccess(2);
                })
                .onExceptionResumeNext(observer -> observer.onError(new Throwable("hahahhaha")))
                .onErrorReturn(o -> "出错了")
//                .onErrorReturnItem("出错了！！！")
                .subscribe(s -> {
                    Log.e(TAG, "rxAnd_Then_When: " + s);
                }, error -> {
                    Log.e(TAG, "rxAnd_Then_When: " + error);
                });

//        Flowable.switchOnNext(f1)
    }


    private <R> User changeUserName(User user) {
        user.setUsername("username has changed hahaha");
        return user;
    }

    private <R> String changeName(String m) {
        return "hello ...." + m;
    }

    private <R> Integer convertStr(String str) {
        if (str.equals("a")) return 2;
        return -1;
    }


    Observable<String> getObservable(final String s) {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return s;
            }
        });
    }


    private String getMessage() {
        Log.e(TAG, "getMessage: " + Thread.currentThread().getName());
        return "RxDemoActivity hello rxjava2";
    }


    private Subscriber<String> getSubscriber() {
        return new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String o) {
                Log.e(TAG, "onNext........: " + o);
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        };
    }
}
