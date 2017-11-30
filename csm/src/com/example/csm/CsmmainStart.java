package com.example.csm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android_serialport_api.SerialPort;

import com.example.csm.Application;

public class CsmmainStart extends Activity {
	protected Application mApplication;
	protected SerialPort mSerialPort;
	protected OutputStream mOutputStream;
	private InputStream mInputStream;
	private ReadThread mReadThread;
	private ImageView animationView; 
	private AnimationDrawable animationDrawable; 
	int drawable;
	
	private class ReadThread extends Thread {
		public void run() {
			super.run();
			while(!isInterrupted()) {
				int size;
				try {
					byte[] buffer = new byte[64];
					if (mInputStream == null) return;
					size = mInputStream.read(buffer);
					if (size > 0) {
						onDataReceived(buffer, size);
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}
	myBroadCast broadcast;
	public Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			//int num = msg.what;
			//Log.i("num", "liuliangxiang num="+num);
			if(msg.what>=0){
				if(drawable < R.drawable.aa145){
					animationView.setImageResource(drawable);
					handler.sendEmptyMessageDelayed(0,5);
				}else{
					animationView.setImageResource(R.drawable.aa145);
					handler.sendEmptyMessageDelayed(-1,3000);
				}
				drawable = drawable+2;
			}else{
//				Intent intent = new Intent(CsmmainStart.this,
//			    		Csmmain.class);
//				CsmmainStart.this.startActivity(intent);
//				CsmmainStart.this.finish();
				Intent intent = new Intent(CsmmainStart.this,
			    		Csmmain.class);
				Intent intent1 = new Intent();
				intent1.setAction("helpstart.broadcast");
				   
				   //锟斤拷锟斤拷 一锟斤拷锟斤拷锟斤拷悴�
				 CsmmainStart.this.sendBroadcast(intent1);
			}
			
//			if(num>=146){
//				handler.sendEmptyMessageDelayed(-1,1000);
//			} else if(num<0){
//				Intent intent = new Intent(CsmmainStart.this,
//			    		Csmmain.class);
//				CsmmainStart.this.startActivity(intent);
//				CsmmainStart.this.finish();
//			}
//			else{
//				animationView.setImageResource(R.drawable.a01+num);
//				handler.sendEmptyMessageDelayed(num+1,50);
//			}
		}
	};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 mApplication = (Application) getApplication();
	        requestWindowFeature(Window.FEATURE_NO_TITLE);  
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.helpstart);
		animationView = (ImageView) findViewById(R.id.startView); 
		drawable = R.drawable.aa001;
		
//		animationDrawable = (AnimationDrawable) animationView.getDrawable();  
//		animationDrawable.start(); 
//		try {
//			mSerialPort = mApplication.getSerialPort();
//			mOutputStream = mSerialPort.getOutputStream();
//			mInputStream = mSerialPort.getInputStream();
//			/* Create a receiving thread */
//			mReadThread = new ReadThread();
//			mReadThread.start();
			
			
			broadcast = new myBroadCast();
			IntentFilter filter = new IntentFilter();
			filter.addAction("helpstart.broadcast");
			filter.setPriority(-100);
			this.registerReceiver(broadcast, filter);
			
//		} catch (SecurityException e) {
//
//		} catch (IOException e) {
//
//		} 
//		mTimer = new Timer(true);
//    	StartTimer();
	}
	public void onResume(){
		super.onResume();
//		mTimer = new Timer(true);
//    	StartTimer();
    	Settings.System.putInt(this.getContentResolver(), "csmmain", 1);
		animationView.setImageResource(drawable);
    	handler.sendEmptyMessageDelayed(0, 4000);
	}
	
	public void onDestroy(){
		super.onDestroy();
		if(broadcast!=null){
			this.unregisterReceiver(broadcast);
		}
	}
	

    public void StartTimer(){     
    	if (mTimer != null){     
    		if (mTimerTask != null){      
    			mTimerTask.cancel();  //锟斤拷原锟斤拷锟斤拷佣锟斤拷锟斤拷锟斤拷瞥锟� 
    			}                
    			mTimerTask = new MyTimerTask();  // 锟铰斤拷一锟斤拷锟斤拷锟斤拷            
    			mTimer.schedule(mTimerTask, 5000);
    		}   
     }


    class MyTimerTask extends TimerTask{ 
    	public void run() {   // TODO Auto-generated method stub   Log.i(TAG, "run...");   
    		Intent intent = new Intent(CsmmainStart.this,
		    		Csmmain.class);
			Intent intent1 = new Intent();
			intent1.setAction("helpstart.broadcast");
			   
			   //锟斤拷锟斤拷 一锟斤拷锟斤拷锟斤拷悴�
			 CsmmainStart.this.sendBroadcast(intent1);
			
			//finish();
    	}
    }
    private Timer mTimer;
    private MyTimerTask mTimerTask;
    
    public class myBroadCast extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(arg0,
		    		Csmmain.class);
//			try {
//				wait(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			CsmmainStart.this.startActivity(intent);
			//CsmmainStart.this.unregisterReceiver(broadcast);
			CsmmainStart.this.finish();
		}
    	
    }
	
	protected void onDataReceived(final byte[] buffer, final int size) {
		runOnUiThread(new Runnable() {
			public void run() {
//				Intent intent = new Intent(CsmmainStart.this,
//			    		Csmmain.class);
//				CsmmainStart.this.startActivity(intent);
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				finish();
//				mReadThread.interrupt();
			}
		});
	}
}
