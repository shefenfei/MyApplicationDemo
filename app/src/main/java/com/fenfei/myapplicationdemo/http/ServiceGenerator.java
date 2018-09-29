package com.fenfei.myapplicationdemo.http;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.utils.NoSSLv3SocketFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Network Utils <br/>
 * Created by shefenfei on 15/9/11.
 *
 * @version 1.0
 */
public final class ServiceGenerator {

    private boolean USE_HTTPS = true; //是否使用https方式请求
    private boolean KEEP_ALIVE = true; //是否使用保持连接方式
    private static Retrofit.Builder mBuilder;
    private OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    //    private HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static Gson gson;

    public static String apiBaseUrl = "";

    private volatile static ServiceGenerator INSTANCE;

    private ServiceGenerator(Context context) {
        gson = new GsonBuilder().create();
        //.setDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS).create();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        okHttpClient.interceptors().add(logging);
        if (!KEEP_ALIVE) {
            okHttpClient.addInterceptor(new HeaderSelectionInterceptor());
        }
        mBuilder = new Retrofit.Builder();
        mBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        mBuilder.addConverterFactory(GsonConverterFactory.create(gson));
        mBuilder.client(USE_HTTPS ? generateHttpsClient(context) : getHttpClient());
    }

    /**
     * singleInstance
     *
     * @return
     */
    public static ServiceGenerator generate(Context c) {//此类做法效率最高并为线程安全的
        if (INSTANCE == null) {
            synchronized (ServiceGenerator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceGenerator(c);
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 运行时改变baseUrl
     * @param newApiBaseUrl
     */
    public static void changeApiBaseUrl(String newApiBaseUrl) {
        apiBaseUrl = newApiBaseUrl;
        mBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(apiBaseUrl);
    }


    /**
     * setting host url
     */
    public ServiceGenerator setEndpoint(String apiBaseUrl) {
        mBuilder.baseUrl(apiBaseUrl).build();
        return INSTANCE;
    }


    /**
     * getApiService
     *
     * @param service Service
     * @param <T>
     * @return service
     */
    public <T> T getApiService(Class<T> service) {
        return mBuilder.build().create(service);
    }


    /**
     * 使用http方式请求网络
     *
     * @return
     */
    @NonNull
    private OkHttpClient getHttpClient() {
        okHttpClient.connectTimeout(10, TimeUnit.SECONDS);
        return okHttpClient.build();
    }


    /**
     * 使用https方式请求网络
     *
     * @param context
     * @return
     */
    private OkHttpClient generateHttpsClient(Context context) {
        try {
            SSLSocketFactory socketFactory = null;
            socketFactory = new NoSSLv3SocketFactory(getSSLConfig(context, R.raw.shsmile).getSocketFactory());
            okHttpClient.sslSocketFactory(socketFactory);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return okHttpClient.build();
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static SSLContext getSSLConfig(Context context, int certificate) throws
            CertificateException,
            IOException,
            KeyStoreException,
            NoSuchAlgorithmException, KeyManagementException {

        // 从文件中加载CAs
        CertificateFactory cf = null;
        cf = CertificateFactory.getInstance("X.509");

        Certificate ca;
        try (InputStream cert = context.getResources().openRawResource(certificate)) {
            ca = cf.generateCertificate(cert);
        }

        // 使用信任的CAs创建一个KeyStore
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        // 根据我们的KeyStore创建TrustManager
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        // 使用我们的TrustManager创建SSLSocketFactory
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        return sslContext;
    }

    //对请求头做处理
    static final class HeaderSelectionInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder().addHeader("Connection" , "close").build();
            return chain.proceed(request);
        }
    }

}
