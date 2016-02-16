package com.KjeMar.LocationExtension;

import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class AndroidGPSLocationContext extends FREContext {

	
	public android.app.Activity activity;
	
	@Override
	public void dispose() {
		activity = null;

	}

	@Override
	public Map <String, FREFunction> getFunctions()
	 {
	    Map<String,FREFunction> functionMap=new java.util.HashMap<String,FREFunction>();
	    functionMap.put("ffiInit",new AndroidGPSLocationInitFunction());

	    return functionMap;
	}


}
