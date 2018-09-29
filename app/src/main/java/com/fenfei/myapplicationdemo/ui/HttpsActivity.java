package com.fenfei.myapplicationdemo.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fenfei.myapplicationdemo.R;
import com.fenfei.myapplicationdemo.interfaces.FileDownloadService;
import com.fenfei.myapplicationdemo.utils.NoSSLv3SocketFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit中https的使用
 */
public class HttpsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_https);


        FileDownloadService service = buildClient().create(FileDownloadService.class);
        final Call call = service.test();

        Button button = (Button) findViewById(R.id.https);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.clone().enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        boolean isSuccess = response.isSuccessful();
                        Log.e("Success" , "" + isSuccess);
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("ERROR" ,  t.getMessage() + "...");
                    }
                });
            }
        });
    }

    Retrofit buildClient() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.shsmile.com.cn")
                .client(generateClient());
        return builder.build();
    }

    private OkHttpClient generateClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            SSLSocketFactory socketFactory = null;
            socketFactory = new NoSSLv3SocketFactory(getSSLConfig(this , R.raw.shsmile).getSocketFactory());
            builder.sslSocketFactory(socketFactory);
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
        return builder.build();
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static SSLContext getSSLConfig(Context context , int certificate) throws CertificateException, IOException,
            KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        // 从文件中加载CAs
        CertificateFactory cf = null;
        cf = CertificateFactory.getInstance("X.509");

        Certificate ca;
        try (InputStream cert = context.getResources().openRawResource(certificate)) {
            ca = cf.generateCertificate(cert);
        }

        // 使用信任的CAs创建一个KeyStore
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore   = KeyStore.getInstance(keyStoreType);
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


}
