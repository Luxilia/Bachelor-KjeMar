package com.KjeMar.LocationExtension;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AndroidGPSStartListening implements FREFunction{

	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		AndroidLocationExtensionContext context = (AndroidLocationExtensionContext) arg0;
		try{
			context.listener = new AndroidGPSLocationListener(context);
		}
		catch (Exception e){
		}
		return null;
		
	}

}
