package com.KjeMar.LocationExtension;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AndroidNearableStopListening implements FREFunction{

	AndroidGPSLocationContext context;
	
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		this.context = (AndroidGPSLocationContext)arg0;
		if(context.beaconListener == null){
			context.beaconListener = new AndroidBeaconLocationListener(context);
		}
		context.beaconListener.stopCheckNearables();
		return null;
	}

}
