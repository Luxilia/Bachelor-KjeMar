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
	AndroidLocationExtensionContext context;
	String scanId;
	CloudCallback<NearableInfo> callback;
	EstimoteCloud esCloud;
	
	
	public AndroidBeaconLocationListener(AndroidLocationExtensionContext context){
		this.appContext = context.getActivity().getApplicationContext();
		this.context = context;
		String appID = "superposition-al0";
		String appToken = "22cdb5affc119a3dfde6f3751eaea6e7";
		EstimoteSDK.initialize(appContext, appID, appToken );
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
		 
		  callback = new CloudCallback<NearableInfo>(){
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
				
			};
		 
		 
		 bm.setNearableListener(new BeaconManager.NearableListener(){

			 //TODO: Not fully implemented.
			@Override
			public void onNearablesDiscovered(List<Nearable> list) {
				if(!list.isEmpty()){
					Nearable testNearable = list.get(0);
					String identifier = testNearable.identifier;
					EstimoteCloud.getInstance().fetchNearableDetails(identifier,callback);
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
		 
		 
	}
	
	
	public void sendBeaconOutput(String output){
		context.dispatchStatusEventAsync("Beacon", output);
		
	}
	
	public void sendNearableOutput(String output){
		context.dispatchStatusEventAsync("Nearable", output);
		
	}
	
	public void sendExit(){
		context.dispatchStatusEventAsync("Beacon Exit", "Exited beacon range");
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
