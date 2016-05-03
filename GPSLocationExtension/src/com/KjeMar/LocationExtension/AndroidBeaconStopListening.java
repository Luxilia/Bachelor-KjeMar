package com.KjeMar.LocationExtension;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AndroidBeaconStopListening implements FREFunction{

	AndroidLocationExtensionContext context;
	
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		context = (AndroidLocationExtensionContext)arg0;
		if(context.beaconListener != null){
			context.beaconListener.stopListening();
		}
		return null;
	}

}
