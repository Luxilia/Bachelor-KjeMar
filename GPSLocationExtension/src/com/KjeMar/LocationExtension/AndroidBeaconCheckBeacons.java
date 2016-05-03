package com.KjeMar.LocationExtension;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AndroidBeaconCheckBeacons implements FREFunction{

	AndroidLocationExtensionContext context;
	
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		this.context = (AndroidLocationExtensionContext)arg0;
		if(this.context.beaconListener == null){
			this.context.beaconListener = new AndroidBeaconLocationListener(this.context);
		}
		this.context.beaconListener.checkBeacons();
		return null;
	}

}
