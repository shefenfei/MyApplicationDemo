package com.fenfei.myapplicationdemo.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.fenfei.aspectjlib.TestController;
import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.beans.User;
import com.fenfei.myapplicationdemo.http.ServiceGenerator;
import com.fenfei.myapplicationdemo.interfaces.FileDownloadService;
import com.fenfei.myapplicationdemo.views.CircleLayout;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 测试RxJava
 */

public class RxJavaActivity extends AppCompatActivity {

    private String TAG = "RxJavaActivity";

    private Disposable mDisposable;

    @TargetApi(Build.VERSION_CODES.N)
    public RxJavaActivity() {
//        mOnSeekBarChangeListener = ()->{};

        List<User> users = new ArrayList<>();
        User user;
        for (int i=0; i< 10; i++) {
            user = new User();
            user.setUsername("username-"+i);
            user.setSex(i % 2 == 0 ? 1 : 0);
            users.add(user);
        }

        Flowable.fromCallable(this::getMessage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSubscriber());


        Flowable.fromCallable(()->{
            Thread.sleep(1000);
            return "";
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe();


        Flowable.fromArray(users)
                .firstElement()
                .map(new Function<List<User>, String>() {
                    @Override
                    public String apply(@NonNull List<User> users) throws Exception {

                        return null;
                    }
                }).filter(s -> false);



        users.parallelStream()
                .filter(user1 -> user1.getUsername().equals("username-1"))
                .distinct()
                .forEach(
                        user1 -> {
                            user1.setUsername("username-123");
                            System.out.println(user1.getUsername());
                        }
                );
    }


    private String getMessage() {
        Log.e(TAG, "getMessage: " + Thread.currentThread().getName());
        return "hello rxjava2" ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);


        FileDownloadService apiService = ServiceGenerator.generate(this)
                .setEndpoint("https://api.weixin.qq.com/cgi-bin/").getApiService(FileDownloadService.class);

        Observable<String> observable = apiService.fetchToken("wxe291a59dd0bba73a",
                "84a3746bfa275de37733f1ff48fd7c42",
                "client_credential");


        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, "accept Token is : " + s);
                    }
                });


        CircleLayout circleLayout = (CircleLayout) findViewById(R.id.circle_layout);
        circleLayout.setOnItemClickListener(new CircleLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Log.e(TAG, "onItemClick: ");
            }
        });

        circleLayout.setOnCenterClickListener(new CircleLayout.OnCenterClickListener() {
            @Override
            public void onCenterClick() {
                Log.e(TAG, "onCenterClick: ");
            }
        });

        testFloatRange(1);
        testController();


//        testAsyncSubect();
//        testObservable();

//        testDisposable();

//        changeThread();


        readAllRecords(1).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {
                Log.e(TAG, "onNext: " + strings);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


        //结合Retrofit2
//        retrofit2();
    }

    //Lambda表达式
    private View.OnClickListener mTakeListener = view -> {
        switch (view.getId()) {

        }

        Runnable runnable = () -> {

        };
    };

//    SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = (view, isChecked) -> {
        if (view instanceof CheckBox) {
            view.setChecked(isChecked);
        }
    };

    private void retrofit2() {
        FileDownloadService apiService = ServiceGenerator.generate(this)
                .setEndpoint("")
                .getApiService(FileDownloadService.class);

        Observable<String> observable = apiService.fetchInfo();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
//        CompositeDisposable compositeDisposable = new CompositeDisposable();
//        mDisposable.dispose();
    }

    /**
     * test AsyncSubect
     */
    private void testAsyncSubect() {
        final Throwable throwable = new Throwable("exception message");

        ObservableOnSubscribe<String> obserOnSub = new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("a");
                emitter.onNext("b");
                emitter.onNext("c");
                emitter.onError(throwable);
            }
        };

        Observable.create(obserOnSub)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String integer) throws Exception {
                        return "This is result " + integer;
                    }
                })
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(@NonNull Throwable throwable) throws Exception {
                        return "this is exception :" + throwable.getMessage();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e("....", "accept: " + throwable.getMessage());
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("....", "accept: " + s);
                    }
                });
    }


    //最简单的观察者
    private void testObservable() {
        //被观察者 ( 数据的发送者)
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onComplete();
            }
        });

        //观察者 (被观察者 注册 观察者 并能收到对应的数据)
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        };

        observable.subscribe(observer);


    }


    //支持背压的Flowable
    private void testFlowable() {
        //Action0 --> Action
        Action action = new Action() {
            @Override
            public void run() throws Exception {

            }
        };

        //Action1-Consumer
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {

            }
        };
        //Action2->BiConsumer
        BiConsumer biConsumer = new BiConsumer() {
            @Override
            public void accept(@NonNull Object o, @NonNull Object o2) throws Exception {

            }
        };


        Flowable.just("").map(new Function<String, Object>() {
            @Override
            public Object apply(@NonNull String s) throws Exception {
                return null;
            }
        });



    }


    //一次性的
    private void testDisposable() {
        //炫酷的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Log.e(TAG, "onNext(1): ");

                emitter.onNext(2);
                Log.e(TAG, "onNext(2): ");

                emitter.onComplete();
                Log.e(TAG, "onComplete(): ");

                emitter.onNext(3);
                Log.e(TAG, "onNext(3): ");
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                if (integer.intValue() == 2) {
                    mDisposable.dispose();
                }
                Log.e(TAG, "onNext: " + integer.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        });
    }


    //切换线程
    private void changeThread() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                Log.e(TAG, "subscribe: " + Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "accept: " + Thread.currentThread().getName());
                        Log.e(TAG, "accept: " + integer);
                        ++integer;
                    }
                })
                .observeOn(Schedulers.io())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "accept: " + Thread.currentThread().getName());
                        Log.e(TAG, "run: ");
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "accept: " + integer);
                    }
                });
    }


    private Action getAction() {
        return null;
    }



    public Observable<List<String>> readAllRecords(int type) {
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                List<String> strings = new ArrayList<String>();
                strings.add("a");
                strings.add("b");
                emitter.onNext(strings);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    ;


    //取from 到 to 之间的值
    private void testFloatRange(@FloatRange(from = 0, to = 2) float num) {
        Log.e(TAG, "test: " + num);
    }


    private Subscriber<Object> getSubscriber() {
        return new Subscriber<Object>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Object o) {
                Log.e(TAG, "onNext: " + o.toString());
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, "onError: " );
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: " );
            }
        };
    }



    private void testController() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hello asp");
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new TestController().mConsumer);
    }


}
