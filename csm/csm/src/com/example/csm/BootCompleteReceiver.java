package com.example.csm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompleteReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("BootCompleteReceiver","liuliangxiang BootCompleteReceiver");
		//handler.sendEmptyMessageDelayed(50, 100);
		
	}
}
