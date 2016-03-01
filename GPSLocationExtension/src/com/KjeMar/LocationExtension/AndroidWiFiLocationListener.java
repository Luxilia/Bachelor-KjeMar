package com.KjeMar.LocationExtension;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class AndroidWiFiLocationListener {
	
	WifiManager wifiManager;
	WifiInfo wifiInfo;
	AndroidGPSLocationContext context;
	Context appContext;
	BroadcastReceiver receiver;
	String wifiID;
	
	public AndroidWiFiLocationListener(AndroidGPSLocationContext context){
		this.context = context;
		this.appContext = context.getActivity().getApplicationContext();
		wifiManager = (WifiManager)appContext.getSystemService(Context.WIFI_SERVICE);
		context.dispatchStatusEventAsync("WiFi", "Wifitest");
		receiver = (new BroadcastReceiver(){
			

			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				if(WifiManager.NETWORK_STATE_CHANGED_ACTION.equals (action)){
					NetworkInfo netInfo = intent.getParcelableExtra (WifiManager.EXTRA_NETWORK_INFO);
			        if (ConnectivityManager.TYPE_WIFI == netInfo.getType ()) {
			        	checkWiFi();
			        	
			        }
				}
				
			}
		});
	}

	public void checkWiFi(){
		wifiID = "256";
		sendUpdatedSSID();
		wifiInfo = wifiManager.getConnectionInfo ();
		if (wifiInfo.getSupplicantState()== SupplicantState.COMPLETED) {
            wifiID = wifiInfo.getSSID();
		}else {
			wifiID = "wifiInfo.getSupplicantState() is NOT Completed";
		}
		sendUpdatedSSID();
    	
	}
	
	public void sendUpdatedSSID(){
		context.dispatchStatusEventAsync("WiFi", wifiID);
	}

}
