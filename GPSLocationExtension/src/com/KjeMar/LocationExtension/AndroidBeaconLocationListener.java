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
	
		bm = new BeaconManager(appContext);
		region = new Region("ranged region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);
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
	}
	
	public void sendOutput(int minor, int major){
		 String output = minor + "," + major;
		context.dispatchStatusEventAsync("Beacon", output);
	}
	
	
	

}
