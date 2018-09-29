package com.fenfei.myapplicationdemo.ui;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fenfei.myapplicationdemo.R;

/**
 * 测试NFC功能
 */
public class NFCActivity extends AppCompatActivity {

    String TAG = "NFCActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        NfcManager nfcManager = (NfcManager) getSystemService(Context.NFC_SERVICE);
        NfcAdapter nfcAdapter = nfcManager.getDefaultAdapter();
        if (nfcAdapter!=null) {
            Log.e(TAG, "onCreate: " + "nfc 可用");

        }else {
            Log.e(TAG, "onCreate: " + "nfc 可用");
        }


    }
}
