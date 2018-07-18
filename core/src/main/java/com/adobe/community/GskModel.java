package com.adobe.community;

import com.adobe.cq.sightly.WCMUse;

public class GskModel extends WCMUse {

	InvokeSling invokeSling;
	
	@Override
	public void activate() throws Exception {
		invokeSling = getSlingScriptHelper().getService(InvokeSling.class);
		
	}

	public InvokeSling getInvokeSling() {
		return invokeSling;
	}
	
}
