package com.KjeMar.LocationExtension;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AndroidLocationExtensionInitFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args)
	{
	    AndroidLocationExtensionContext adec=(AndroidLocationExtensionContext)context;
	    android.app.Activity activity=adec.getActivity();
	    adec.activity=activity;
	    return null;
	}

}
