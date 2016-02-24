package com.KjeMar.LocationExtension;


import java.util.List;
import java.util.UUID;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import android.content.Context;

public class AndroidBeaconLocationListener {
	
	BeaconManager bm;
	Context appContext;
	Region region;
	AndroidGPSLocationContext context;
	
	
	public AndroidBeaconLocationListener(AndroidGPSLocationContext context){
		this.appContext = context.getActivity().getApplicationContext();
		this.context = context;
		
		context.dispatchStatusEventAsync("Beacon", "0,0");
		context.dispatchStatusEventAsync("Beacon", "1,1");
		bm = new BeaconManager(appContext);
		context.dispatchStatusEventAsync("Beacon", "2,2");
		region = new Region("ranged region",
                null, null, null);
		context.dispatchStatusEventAsync("Beacon", "3,3");
		 bm.setRangingListener(new BeaconManager.RangingListener() {
	            @Override
	            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
	                if (!list.isEmpty()) {
	                    int minor = list.get(0).getMinor();
	                    int major = list.get(0).getMajor();
	                   
	                    sendOutput(minor, major);
	                    
	                    
	                }
	            }
		 });
		 context.dispatchStatusEventAsync("Beacon", "2000,9000");
		 bm.connect(new BeaconManager.ServiceReadyCallback() {
		        @Override
		        public void onServiceReady() {
		            bm.startRanging(region);
		        }
		    });
		 
	}
	
	public void sendOutput(int minor, int major){
		 String output = minor + "," + major;
		context.dispatchStatusEventAsync("Beacon", output);
	}
	
	
	

}
