package com.KjeMar.LocationExtension;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AndroidWiFiStopListening implements FREFunction{

AndroidLocationExtensionContext context;
	
//TODO: Send message on exit
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		this.context = (AndroidLocationExtensionContext)arg0;
		context.dispatchStatusEventAsync("Wifi Exit","Exited Wifi listening mode");
		if(context.wifiListener == null){
			return null;
		}
		context.wifiListener = null;
		return null;
	}

}