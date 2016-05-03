package com.KjeMar.LocationExtension;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AndroidWiFiStartListening implements FREFunction{

AndroidLocationExtensionContext context;
	
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		this.context = (AndroidLocationExtensionContext)arg0;
		if(context.wifiListener == null){
			context.wifiListener = new AndroidWiFiLocationListener(context);
		}
		context.wifiListener.checkWiFi();
		return null;
	}

}
