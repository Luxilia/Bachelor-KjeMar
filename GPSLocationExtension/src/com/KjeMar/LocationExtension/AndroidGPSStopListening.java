package com.KjeMar.LocationExtension;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AndroidGPSStopListening implements FREFunction{

	//TODO: Send message on exit.
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		AndroidLocationExtensionContext context = (AndroidLocationExtensionContext) arg0;
		if(context.listener != null){
			context.listener = null;
		}
		return null;
		
	}

}