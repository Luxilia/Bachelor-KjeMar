package com.KjeMar.LocationExtension;


import java.util.List;
import java.util.UUID;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.EstimoteSDK;
import com.estimote.sdk.Nearable;
import com.estimote.sdk.Region;
import com.estimote.sdk.cloud.CloudCallback;
import com.estimote.sdk.cloud.EstimoteCloud;
import com.estimote.sdk.cloud.model.NearableInfo;
import com.estimote.sdk.cloud.model.NearableType;
import com.estimote.sdk.exception.EstimoteServerException;

import android.content.Context;

public class AndroidBeaconLocationListener {
	
	BeaconManager bm;
	Context appContext;
	Region fullRegion;
	AndroidGPSLocationContext context;
	String scanId;
	CloudCallback<NearableInfo> callback;
	
	
	public AndroidBeaconLocationListener(AndroidGPSLocationContext context){
		this.appContext = context.getActivity().getApplicationContext();
		this.context = context;
		EstimoteSDK.initialize(appContext, "superposition-al0", "22cdb5affc119a3dfde6f3751eaea6e7");
		bm = new BeaconManager(appContext);
		fullRegion = new Region("ranged region",
                null, null, null);
		 bm.setRangingListener(new BeaconManager.RangingListener() {
	            @Override
	            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
	                if (!list.isEmpty()) {
	                    int minor = list.get(0).getMinor();
	                    int major = list.get(0).getMajor();
	                    String output = minor + "," + major;
	                    sendBeaconOutput(output);
	                    
	                    
	                }
	            }
		 });
		 bm.setNearableListener(new BeaconManager.NearableListener(){

			@Override
			public void onNearablesDiscovered(List<Nearable> list) {
				if(!list.isEmpty()){
					Nearable testNearable = list.get(0);
					EstimoteCloud.getInstance().fetchNearableDetails(testNearable.identifier,new CloudCallback<NearableInfo>(){

						@Override
						public void failure(EstimoteServerException arg0) {
							// TODO Auto-generated method stub
							if(arg0.getMessage() != null){	
								sendNearableOutput(arg0.getMessage());
							}
							else{
								sendNearableOutput("Unknown error connecting to EstimoteCloud");
							}
							
						}

						@Override
						public void success(NearableInfo info) {
							sendNearableOutput(info.type.text);
							
						}
						
					});
				}
				
			}
		 });
		 bm.setMonitoringListener(new BeaconManager.MonitoringListener() {
			    @Override
			    public void onEnteredRegion(Region region, List<Beacon> list) {
			    	int minor = list.get(0).getMinor();
			    	int major = list.get(0).getMajor();
			    	String output = minor + "," + major;
			    	sendBeaconOutput(output);
			    	startListening();
			    }
			    public void onExitedRegion(Region region) {
			       stopListening();
			       
			    }
			});
		 context.dispatchStatusEventAsync("Beacon", "2000,9000"); //For debugging purposes
		 
		 
	}
	
	
	public void sendBeaconOutput(String output){
		context.dispatchStatusEventAsync("Beacon", output);
		
	}
	
	public void sendNearableOutput(String output){
		context.dispatchStatusEventAsync("Nearable", output);
		
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
	
	public void checkNearables(){
		bm.connect(new BeaconManager.ServiceReadyCallback(){
			@Override public void onServiceReady() {
			      scanId = bm.startNearableDiscovery();
			    }
			  });
	}
	
	public void stopCheckNearables(){
		bm.stopNearableDiscovery(scanId);
	}
		

}
