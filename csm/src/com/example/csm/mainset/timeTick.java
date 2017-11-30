package com.example.csm.mainset;

import android.os.Handler;
import android.os.Message;

public class timeTick {
	
	public interface timeTickLister
	{
		void onTick();
	}
	protected timeTickLister mtimeTickLister;
	public void setTimeTickListener(timeTickLister t)
	{
		mtimeTickLister=t;
		mHandler.sendEmptyMessageDelayed(0, 0);
	}
	public void unonSetTimeTickLister()
	{
		mHandler.removeMessages(0);
		//mHandler=null;
		mtimeTickLister=null;
		
	}
	
	 private Handler mHandler = new Handler() {
		 
		 public void handleMessage(Message msg)
		 {
			 synchronized (timeTick.this)
			 {
				 mHandler.sendEmptyMessageDelayed(0, 1000);
				 mtimeTickLister.onTick();
			 }
		 }
	 
	 };
	

}
