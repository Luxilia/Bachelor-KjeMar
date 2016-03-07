package com.KjeMar.LocationExtension;

import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class AndroidGPSLocationContext extends FREContext {

	
	public android.app.Activity activity;
	public AndroidGPSLocationListener listener; 
	public AndroidBeaconLocationListener beaconListener;
	public AndroidWiFiLocationListener wifiListener;
	
	@Override
	public void dispose() {
		activity = null;

	}

	@Override
	public Map <String, FREFunction> getFunctions()
	 {
	    Map<String,FREFunction> functionMap=new java.util.HashMap<String,FREFunction>();
	    functionMap.put("ffiInit",new AndroidGPSLocationInitFunction());
	    functionMap.put("ffiStartGPSListening", new AndroidGPSStartListening());
	    functionMap.put("ffiStartBeaconListening", new AndroidBeaconStartListening());
	    functionMap.put("ffiStopBeaconListening", new AndroidBeaconStopListening());
	    functionMap.put("ffiCheckBeacons", new AndroidBeaconCheckBeacons());
	    functionMap.put("ffiStartWifiListening", new AndroidWiFiStartListening());

	    return functionMap;
	}


}
