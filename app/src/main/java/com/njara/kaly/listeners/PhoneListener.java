package com.njara.kaly.listeners;

import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneListener extends PhoneStateListener {
	private boolean onCall = false;
	private Context context;
    @Override
    public void onCallStateChanged(int state, String numero ) {
        if(TelephonyManager.CALL_STATE_RINGING == state) 
           Log.i("LTM", "RINGING, num�ro: " + numero);
            
        if(TelephonyManager.CALL_STATE_OFFHOOK == state) 
           Log.i("LTM", "OFFHOOK");
            
        if(TelephonyManager.CALL_STATE_IDLE == state){ 
        	if (onCall == true) {
   		                    Intent restart = context.getPackageManager().    		
       			                        getLaunchIntentForPackage(context.getPackageName());       		
        			                    restart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		
        			                    context.startActivity(restart);
                           onCall = false;
        		
              }     		
        			          
        }
    }
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	public PhoneListener(Context context) {
		super();
		this.context = context;
	}
	
    
}
