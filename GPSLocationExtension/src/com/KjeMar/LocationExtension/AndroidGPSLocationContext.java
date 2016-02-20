package com.KjeMar.LocationExtension;

import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class AndroidGPSLocationContext extends FREContext {

	
	public android.app.Activity activity;
	public AndroidGPSLocationListener listener; 
	public AndroidBeaconLocationListener beaconListener;
	
	@Override
	public void dispose() {
		activity = null;

	}

	@Override
	public Map <String, FREFunction> getFunctions()
	 {
	    Map<String,FREFunction> functionMap=new java.util.HashMap<String,FREFunction>();
	    functionMap.put("ffiInit",new AndroidGPSLocationInitFunction());
	    functionMap.put("ffiStartListening", new AndroidGPSStartListening());
	    functionMap.put("ffiStartBeaconListening", new AndroidBeaconStartListening());

	    return functionMap;
	}


}
