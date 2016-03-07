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
	Region fullRegion;
	AndroidGPSLocationContext context;
	
	
	public AndroidBeaconLocationListener(AndroidGPSLocationContext context){
		this.appContext = context.getActivity().getApplicationContext();
		this.context = context;
		context.dispatchStatusEventAsync("Beacon", "Fuck, off");
		bm = new BeaconManager(appContext);
		fullRegion = new Region("ranged region",
                null, null, null);
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
		 bm.setMonitoringListener(new BeaconManager.MonitoringListener() {
			    @Override
			    public void onEnteredRegion(Region region, List<Beacon> list) {
			    	int minor = list.get(0).getMinor();
			    	int major = list.get(0).getMajor();
			    	sendOutput(minor, major);
			    	startListening();
			    }
			    public void onExitedRegion(Region region) {
			       stopListening();
			       
			    }
			});
		 context.dispatchStatusEventAsync("Beacon", "2000,9000"); //For debugging purposes
		 
		 
	}
	
	public void sendOutput(int minor, int major){
		 String output = minor + "," + major;
		context.dispatchStatusEventAsync("Beacon", output);
		
	}
	
	public void sendExit(){
		context.dispatchStatusEventAsync("Beacon Exit", "Does it matter?");
	}
	
	public void startListening(){
		bm.connect(new BeaconManager.ServiceReadyCallback() {
	        @Override
	        public void onServiceReady() {
	            bm.startRanging(fullRegion);
	        }
	    });
	}
	
	public void stopListening(){
		this.bm.stopRanging(fullRegion);
		sendExit();
	}
	
	public void checkBeacons(){
		bm.connect(new BeaconManager.ServiceReadyCallback() {
	        @Override
	        public void onServiceReady() {
	        	bm.startMonitoring(fullRegion);
	        }
	    });
	}
		

}
