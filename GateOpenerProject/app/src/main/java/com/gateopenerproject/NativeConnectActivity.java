package com.gateopenerproject;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NativeConnectActivity extends AppCompatActivity {
    private BluetoothLeScanner scanner = null;
    private BluetoothAdapter bluetooth_adapter = null;
    private Context context1;

    private Button scanBtn;
    private ListView deviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_connect);
        deviceList = (ListView) findViewById(R.id.listView);
        scanBtn = (Button) findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetooth_adapter = BluetoothAdapter.getDefaultAdapter();
                Set<BluetoothDevice> pairedDevices = bluetooth_adapter.getBondedDevices();
                final List<String> devicesList = new ArrayList<String>();
                for(BluetoothDevice device : pairedDevices) {
                    devicesList.add(device.getName());
                }
                final ArrayAdapter<String> arrAdapt = new ArrayAdapter<String>(NativeConnectActivity.this, android.R.layout.simple_list_item_1, devicesList);
                deviceList.setAdapter(arrAdapt);
                // check bluetooth is available and on
                if (bluetooth_adapter == null || !bluetooth_adapter.isEnabled()) {
                    Log.d(Constants.TAG, "Bluetooth is NOT switched on");
                    Intent enableBtIntent = new Intent( BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, 100);
                }
                else
                    Log.d(Constants.TAG, "Bluetooth is switched on");
            }
        });
    }

    public NativeConnectActivity(){

    }
}
