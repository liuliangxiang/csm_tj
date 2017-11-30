package com.example.csm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.security.Timestamp;

import zpSDK.zpSDK.zpSDK;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.os.SystemClock;
import android.os.storage.StorageManager;  
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android_serialport_api.SerialPort;

import com.example.csm.boxingview.boxingview;
import com.example.csm.boxingview.mytabview;
import com.example.csm.boxingview.mytabview.TabClickLister;
import com.example.csm.boxingview.selectSexview;
import com.example.csm.boxingview.selectSexview.selectSex;
import com.example.csm.boxingview.showsetting;
import com.example.csm.boxingview.showsetting.showmodeClickListener;
import com.example.csm.boxingview.thingshezView;
import com.example.csm.boxingview.thingshezView.thingsClickListener;
import com.example.csm.boxingview.tiaozhenView;
import com.example.csm.boxingview.tiaozhenView.tiaozhenClickListener;
import com.example.csm.boxingview.waverecord;
import com.example.csm.boxingview.waverecord.updateInfomationListener;
import com.example.csm.mainset.mainset;
import com.example.csm.mainset.timeTick;
import com.example.csm.mainset.timeTick.timeTickLister;
import com.example.csm.Application;

public class Csmmain extends Activity implements OnClickListener, timeTickLister, TabClickLister, selectSex,updateInfomationListener{
	
	private static final String TAG = "Csmmain";
	TextView timeYear;
	TextView timeHour;
	TextView timeScale;
	long nowtime;//SystemClock.uptimeMillis();
	long resumetime;
	long scaletime;
	SimpleDateFormat  sdf=new SimpleDateFormat("yyyy/MM/dd");
	SimpleDateFormat  sdf1=new SimpleDateFormat("HH:mm");
	SimpleDateFormat  sdf2=new SimpleDateFormat("HH:mm:ss");
	Date date=new Date();
	ImageView warningView;
	ImageView connectView;
	TextView warnCsiText;
	TextView warnConnectText;
	Animation baojingAnim;
	Animation warnConnectAnim;
	boxingview boxing;
	
	Button btnsci;
	Button btneeg;
	Button btndongjie;
	Button btnshezi;
	Button btnbinhao;
	Button btnpause;
	Button btnsave;
	Button btnwarning;
	static Button delbtn;
	
	static mainset mmainset;
	timeTick mtimeTick;
	
	static FrameLayout mmenuLayout;
	LinearLayout mjsdLayout;
	LinearLayout eegLayout;
	LinearLayout baojingsezLayout;
	LinearLayout shijianshezLayout;
	LinearLayout shijiangshezLayout;
	LinearLayout benjishezLayout;
	LinearLayout bingrenziliaoLayout;
	LinearLayout ruanjiangversionLayout;
	LinearLayout jiluxinxiLayout;
	LinearLayout showSettingLayout;
	LinearLayout boxinghuiguLayout;
	
	FrameLayout max_csiLayout;
	
	tiaozhenView shangxianshez;
	tiaozhenView xiaxianshez;
	static ArrayList<LinearLayout> mLayout=new ArrayList<LinearLayout>();
	Spinner majuiSpinner;
	Spinner majuirecSpinner;
	Spinner bannerSpinner;
	Spinner soundSpinner;
	Spinner eegScaleSpinner;
	Spinner eegRecScaleSpinner;
	Spinner eegSpinner;
	Spinner fuduSpinner;
	static mytabview mmytabView;
	mIntentReceiver receiver;
	static int mSetItem;
	Builder shijianshezbuiler;
	
	thingshezView mthingshezView;
	showsetting mshowsetting;
	
	EditText xingmingtext;
	EditText binglihaotext;
	EditText xingbietext;
	EditText nianyingtext;
	
	TextView binhao;
	TextView name;
	TextView sexText;
	
	TextView textpause;
	TextView warntext;
	
	protected SerialPort mSerialPort;
	protected OutputStream mOutputStream;
	private InputStream mInputStream;
	private ReadThread mReadThread;
	
	private boolean mReadData=false;
	private final int DataLen=131;
	private static int Datanum=0;
	private boolean isbaojing=false;
	private boolean isConnectWarn=false;
	private TextView dongjieText;
	private Button playbtn;
	private ScrollView mScrollView;
	private TextView mScrollTextView;	
	private int index;
	private boolean isRecord;
	//private boolean isStartRecord;
	//private int msgindex;
	private boolean isnamerecord;
	private StorageManager mStorageManager = null;
	
	SoundPool soundpool;
	int poolint;
	
	boolean isFileOpen=false;
	boolean isReadySaved;
	OutputStreamWriter out;
	OutputStreamWriter outPrint;
	AudioManager audioManager;//=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
	
	Integer[] EEGDATA=new Integer[100];
	ArrayList<byte[]> msgArray=new ArrayList<byte[]>();
	int saveDataLen=0;
	//private int realindex;
	Context context;
	
	int day;
	int year;
	int mon;
	int hour;
	int min;
	int sec;
	boolean istimeoutSave;
	boolean isStart=true;
	TextView textstart;
	waverecord mwaverecord;
	private ProgressDialog progress = null;
	boolean DEBUG=false;
	int connectnum;
	
	FrameLayout mainLayout;
	LinearLayout printerlayout;
	
	boolean isFirstConnect;
	
	TextView baterryView;
	
	TextView max_csiView;
	
	protected Application mApplication;
	private static boolean isNetConnect;
	private byte[] msgSendBuffer = new byte[Constant.bufferSize];
	public MulticastSocket multicastSocket = null;
	private PopupWindow mPopupWindow;

	public Handler handler=new Handler()
	{
		public void handleMessage(Message msg) {
			if(msg.what==1)
			{
				if(mmenuLayout.getVisibility()==View.VISIBLE)
				{
					//Log.i(TAG,"liuliangxiang mSetItem=2");
					mmenuLayout.setVisibility(View.GONE);
					mmytabView.clearHost();
					setLayoutVisible(null);
					mSetItem=0;
				}
				index=0;
			}	
			if(msg.what==2)
			{
				//index=index+1;
				//isRecord=true;
				//if((mmainset.boxingType==mmainset.SCITYPE)||(mmainset.boxingType==mmainset.SCI2TYPE))
				{
				//	if(index<mmainset.CSI.size())
				//	{
				//		if(((mmainset.CSI.get(index))&(mmainset.getthings()))!=0)
				//		{
							//mScrollTextView.setText(mScrollTextView.getText().toString()+Integer.toString(mmainset.CSI.get(index)&0xff)+"\n");
				//		}
				//	}
				//	index=index+1;
				//	if((index>12)||(index>mmainset.CSI.size()))
					{
						//isRecord=false;
						//handler.sendEmptyMessage(1);
						//mScrollTextView.setText("");
						if(mmenuLayout.getVisibility()==View.VISIBLE)
						{
						//	Log.i(TAG,"liuliangxiang mSetItem=2");
							mmenuLayout.setVisibility(View.GONE);
							mmytabView.clearHost();
							setLayoutVisible(null);
							mSetItem=0;
							//imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,
						    //InputMethodManager.HIDE_NOT_ALWAYS);
						}
						//Log.i(TAG,"liuliangxiang 11");
						if(!Checkinformation())
						{
						//	Log.i(TAG,"liuliangxiang 22");
							mmainset.isStartRecord=false;
							isnamerecord=false;
							mmainset.msgindex=5;
							Dialog alertDialog = new AlertDialog.Builder(Csmmain.this). 
					                setTitle(R.string.warning). 
					                setMessage(R.string.warn_infoempty). 
					                setIcon(R.drawable.ic_launcher). 
					                setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { 			                     
					                public void onClick(DialogInterface dialog, int which) { 
					                        // TODO Auto-generated method stub  
					                    } 
					                }).
					                create(); 
					        alertDialog.show(); 
					        return;
						}
						else
						{
							//Log.i(TAG,"liuliangxiang 33");
//							if(!mmainset.isStartRecord)
//							{
//								Log.i(TAG,"liuliangxiang 44");
//								mmainset.isStartRecord=true;
//								isnamerecord=false;
//								isReady = true;
//								mmainset.CSI.clear();
//								mmainset.EEG.clear();
//								mmainset.BLACK.clear();
//								mmainset.BS.clear();
//								mmainset.WHITE.clear();
//								mmainset.EMG.clear();
//								mmainset.mon=Calendar.getInstance().get(Calendar.MONTH)+1;
//								mmainset.year=Calendar.getInstance().get(Calendar.YEAR);
//								mmainset.day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//								mmainset.hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//								mmainset.min=Calendar.getInstance().get(Calendar.MINUTE);
//								mmainset.sec=Calendar.getInstance().get(Calendar.SECOND);
//								saveDataLen = 0;
//								nowtime = SystemClock.elapsedRealtime();
//								if(!isFileOpen)
//									handler.post(new mSaveRunnable());	
//							}
							if(isFileOpen){
								Log.i(TAG, "liuliangxiang isFileOpen="+isFileOpen);
								mmainset.isStartRecord=false;
								isReady = false;
								handler.sendEmptyMessageDelayed(2, 500);
							}else{
								binhao.setText(mmainset.mperinfomation.binlinao);
								name.setText(mmainset.mperinfomation.name);
								sexText.setText(mmainset.mperinfomation.sex);
								mmainset.isStartRecord=true;
								isnamerecord=false;
								isReady = true;
								mmainset.CSI.clear();
								mmainset.EEG.clear();
								mmainset.BLACK.clear();
								mmainset.SQI.clear();
								mmainset.BS.clear();
								mmainset.WHITE.clear();
								mmainset.EMG.clear();
								mmainset.mon=Calendar.getInstance().get(Calendar.MONTH)+1;
								mmainset.year=Calendar.getInstance().get(Calendar.YEAR);
								mmainset.day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
								mmainset.hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
								mmainset.min=Calendar.getInstance().get(Calendar.MINUTE);
								mmainset.sec=Calendar.getInstance().get(Calendar.SECOND);
								saveDataLen = 0;
								nowtime = SystemClock.elapsedRealtime();
								handler.post(new mSaveRunnable());	
							}
						}
					}				
				//	else
				//	{
				//		handler.sendEmptyMessageDelayed(2,1);
				//	}
				}
				/*
				else if((mmainset.boxingType==mmainset.EEGTYPE))
				{
					if(index<mmainset.EEG.size())
					{
						if(((mmainset.CSI.get(index))&(mmainset.getthings()))!=0)
						{
							Integer data=mmainset.EEG.get(index/100)[index%100];
							if(data>=0x80)
								data=0x7f-data;
							mScrollTextView.setText(mScrollTextView.getText().toString()+Integer.toString(data)+"\n");
						}
					}
					index=index+3;
					Log.i(TAG,"liuliangxiang index="+index);
					
					if((index>500)||(index>mmainset.EEG.size()))
					{
						isRecord=false;
						handler.sendEmptyMessage(1);
						mScrollTextView.setText("");
					}				
					else
					{
						handler.sendEmptyMessageDelayed(2,1);
					}
				}	*/	
				///mScrollView.scrollBy(0, (int) mScrollTextView.getHeight());		
			}
			else if(msg.what==3)
			{
				writeWarn((byte)1,(byte)1);
			}
			else if(msg.what==4)
			{
				writeWarn((byte)1,(byte)0);
			}
			else if(msg.what==5)
			{
				startTick1(tick);
			}
			else if(msg.what==6)
			{
				stopTick1();
			}
			else if(msg.what==7)
			{
				//stopTick2();
			}
			else if(msg.what==8)
			{
				//startTick2(tick);
			}
			else if(msg.what==9)
			{
				//Log.i(TAG,"liuliangxiang close");
				zpSDK.zp_close();
			}
			else if(msg.what==10)
			{
				//Log.i(TAG,"liuliangxiang close");
				Printbitmap(SelectedBDAddress);
			} else if(msg.what == 11){
				name.setText(mmainset.RecName);
		        binhao.setText(mmainset.RecNO);
		        sexText.setText(mmainset.RecSex);
			} else if(msg.what == 12){
	    		if(mmainset.isRecData){
	    			handler.removeMessages(12);
	        		max_csiLayout.setVisibility(View.GONE);
//	    			if(mmainset.RecCSI.size()>0){
//	    				int csi=mmainset.RecCSI.get(mmainset.RecCSI.size()-1)&0xff;
//	    				if(csi>100){
//	    					max_csiView.setText("▂ ▂");
//	    				}else{
//	    					max_csiView.setText(Integer.toString(csi));
//	    				}
//	    			}else{
//	    				max_csiView.setText("▂ ▂");
//	    			}
	    		}else{
	    			if(mmainset.CSI.size()>0){
	    				int csi=mmainset.CSI.get(mmainset.CSI.size()-1)&0xff;
	    				if(csi>100){
	    					max_csiView.setText("▂ ▂");
	    				}else{
	    					max_csiView.setText(Integer.toString(csi));
	    				}
	    			}else{
	    				max_csiView.setText("▂ ▂");
	    			}
	    		}
	    		handler.sendEmptyMessageDelayed(12,400);
			}
			else if(msg.what==19){
				Dialog alertDialog = new AlertDialog.Builder(Csmmain.this). 
		                setMessage(R.string.getrecfinish). 
		                setIcon(R.drawable.ic_launcher). 
		                setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { 			                     
		                public void onClick(DialogInterface dialog, int which) { 
		                        // TODO Auto-generated method stub  
		                    } 
		                }).
		                create(); 
		        alertDialog.show(); 
			}
			else if(msg.what==50)
			{
				try {
					mSerialPort = new SerialPort(new File("/dev/ttyS0"), 115200, 0);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        if(mSerialPort!=null)
		        {
					mOutputStream = mSerialPort.getOutputStream();
					mInputStream = mSerialPort.getInputStream();
					mmainset.DataReceived.clear();			
					mReadThread = new ReadThread();
					mReadThread.setPriority(Thread.MAX_PRIORITY);
					mReadThread.start();
		        }
			}
		}
	};
	boolean isReady = false;
	selectSexview mselectSexview;
	int soundvalue;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (Application) getApplication();
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
                WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        setContentView(R.layout.activity_csmmain);

        mainLayout=(FrameLayout)findViewById(R.id.framelayout);
    	printerlayout=(LinearLayout)findViewById(R.id.printerlayout);;

        receiver=new mIntentReceiver();
        IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_TIME_TICK);
		filter.addAction(Intent.ACTION_MEDIA_MOUNTED );
		filter.addAction(Intent.ACTION_MEDIA_UNMOUNTED );
		filter.addAction(Intent.ACTION_MEDIA_REMOVED );
		filter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		//filter.addAction(Intent.ACTION_BOOT_COMPLETED);
		//filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
			
		mselectSexview = (selectSexview)findViewById(R.id.sexView);
		mselectSexview.setselectSexListener(this);
    
        this.registerReceiver(receiver, filter);  
		timeYear=(TextView)findViewById(R.id.timeyear);
		timeHour=(TextView)findViewById(R.id.timehour);
		timeScale=(TextView)findViewById(R.id.timescale2);
		warningView=(ImageView)findViewById(R.id.baojing);
		connectView=(ImageView)findViewById(R.id.warnchannelImage);
		warnConnectText=(TextView)findViewById(R.id.warnchannel);
		warnCsiText=(TextView)findViewById(R.id.warnchannel);
		baterryView=(TextView)findViewById(R.id.baterry);
		
		isFirstConnect = true;
    	warningView.setImageResource(R.drawable.redstar);
		connectView.setImageResource(R.drawable.greenstar);	
		boxing=(boxingview)findViewById(R.id.boxing);
		nowtime = SystemClock.elapsedRealtime();
		
		btnsci=(Button)findViewById(R.id.btncsi);
		btnsci.setOnClickListener(this);
		btneeg=(Button)findViewById(R.id.btneeg);
		btneeg.setOnClickListener(this);
		btndongjie=(Button)findViewById(R.id.btndongjie);
		btndongjie.setOnClickListener(this);
		dongjieText=(TextView)findViewById(R.id.dongjieText);
		textstart=(TextView)findViewById(R.id.textstart);
		btnshezi=(Button)findViewById(R.id.btnshezi);
		btnshezi.setOnClickListener(this);
		btnbinhao=(Button)findViewById(R.id.btnbinhao);
		btnbinhao.setOnClickListener(this);
		btnpause=(Button)findViewById(R.id.btnpause);
		btnpause.setOnClickListener(this);
		btnsave=(Button)findViewById(R.id.btnsave);
		btnsave.setOnClickListener(this);
		btnwarning=(Button)findViewById(R.id.btnwarning);
		btnwarning.setOnClickListener(this);
		
		textpause=(TextView)findViewById(R.id.textpause);
		warntext=(TextView)findViewById(R.id.warntext);
		
		playbtn=(Button)findViewById(R.id.playbtn);
		playbtn.setOnClickListener(this);
		mmainset=null;
		mmainset=mainset.getInstance();
		mmainset.setContext(this);
		mmainset.setmajuitimescale(Settings.System.getInt(getContentResolver(), "majuitimescale",1));
		mmainset.seteegtimescale(Settings.System.getInt(getContentResolver(), "eegtimescale",1));
		mtimeTick=new timeTick();
		mtimeTick.setTimeTickListener(this);
		
		delbtn=(Button)findViewById(R.id.delbtn);
		delbtn.setOnClickListener(this);
		mmainset.warnoff=0;
		if(mmainset.warnoff==0)
		{
			btnwarning.setText(R.string.warnon);
		}
		else
		{
			btnwarning.setText(R.string.warnoff);
		}
		
		mmenuLayout=(FrameLayout)findViewById(R.id.menuLayout);
		max_csiLayout=(FrameLayout)findViewById(R.id.max_csi);
		max_csiView =(TextView)findViewById(R.id.max_csi_text);
		max_csiView.setTextColor(0xffFF00EE);
		mjsdLayout=(LinearLayout)findViewById(R.id.mjsd);
		eegLayout=(LinearLayout)findViewById(R.id.eegsj);
		baojingsezLayout=(LinearLayout)findViewById(R.id.baojingshezlayout);
		shijianshezLayout=(LinearLayout)findViewById(R.id.shijianshez);
		shijiangshezLayout=(LinearLayout)findViewById(R.id.shijiangshez);
		benjishezLayout=(LinearLayout)findViewById(R.id.benjishez);
		bingrenziliaoLayout=(LinearLayout)findViewById(R.id.bingrenziliao);
		ruanjiangversionLayout=(LinearLayout)findViewById(R.id.ruanjianversion);
		jiluxinxiLayout=(LinearLayout)findViewById(R.id.jiluxinxi);
		showSettingLayout=(LinearLayout)findViewById(R.id.showsetting);
		boxinghuiguLayout=(LinearLayout)findViewById(R.id.boxinghuigu);
		mLayout.add(mjsdLayout);
		mLayout.add(eegLayout);
		mLayout.add(baojingsezLayout);
		mLayout.add(shijianshezLayout);
		mLayout.add(shijiangshezLayout);
		mLayout.add(benjishezLayout);
		mLayout.add(bingrenziliaoLayout);
		mLayout.add(ruanjiangversionLayout);
		mLayout.add(jiluxinxiLayout);
		mLayout.add(showSettingLayout);
		mLayout.add(boxinghuiguLayout);
		mmytabView=(mytabview)findViewById(R.id.mytabView);
		mmytabView.SetTabClickListener(this);
				
		majuiSpinner = (Spinner)findViewById(R.id.majuiSpinner);  
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,  
                R.array.majui_array, android.R.layout.simple_spinner_item);  
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        majuiSpinner.setAdapter(adapter);  
        majuiSpinner.setPrompt("CSI");  
        majuiSpinner.setOnItemSelectedListener(new majuiSpinnerOnItemSelectListener()); 
        int value=Settings.System.getInt(getContentResolver(), "majuitimescale",1);
        if(value==1)
        {
        	majuiSpinner.setSelection(0,true);
        }else if(value==2)
        {
        	majuiSpinner.setSelection(1,true);
        }
        else if(value==5)
        {
        	majuiSpinner.setSelection(2,true);
        }
        else if(value==10)
        {
        	majuiSpinner.setSelection(3,true);
        }
        else if(value==60)
        {
        	majuiSpinner.setSelection(4,true);
        }
        else if(value==120)
        {
        	majuiSpinner.setSelection(5,true);
        }
        mmainset.setmajuitimescale(value);
        
        majuirecSpinner = (Spinner)findViewById(R.id.majuirecSpinner);  
        ArrayAdapter <CharSequence> adapter2 = ArrayAdapter.createFromResource(this,  
                R.array.majui_array, android.R.layout.simple_spinner_item);  
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        majuirecSpinner.setAdapter(adapter2);  
        majuirecSpinner.setPrompt("CSI");  
        majuirecSpinner.setOnItemSelectedListener(new majuirecSpinnerOnItemSelectListener()); 
        int value1=Settings.System.getInt(getContentResolver(), "majuirectimescale",1);
        if(value1==1)
        {
        	majuirecSpinner.setSelection(0,true);
        }
        else if(value1==2)
        {
        	majuirecSpinner.setSelection(1,true);
        }
        else if(value1==5)
        {
        	majuirecSpinner.setSelection(2,true);
        }
        else if(value1==10)
        {
        	majuirecSpinner.setSelection(3,true);
        }
        else if(value1==60)
        {
        	majuirecSpinner.setSelection(4,true);
        }
        else if(value1==120)
        {
        	majuirecSpinner.setSelection(5,true);
        }
        mmainset.setmajuirectimescale(value1);
        
        bannerSpinner = (Spinner)findViewById(R.id.bannerSpinner);  
        ArrayAdapter <CharSequence> banneradapter = ArrayAdapter.createFromResource(this,  
                R.array.banner_array, android.R.layout.simple_spinner_item);  
        banneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        bannerSpinner.setAdapter(banneradapter);  
        bannerSpinner.setPrompt("choose");  
        bannerSpinner.setOnItemSelectedListener(new bannerSpinnerOnItemSelectListener()); 
        value1=Settings.System.getInt(getContentResolver(), "bannerselect",1);
        if(value1==1)
        {
        	bannerSpinner.setSelection(0,true);
        }
        else if(value1==2)
        {
        	bannerSpinner.setSelection(1,true);
        }
        else if(value1==3)
        {
        	bannerSpinner.setSelection(2,true);
        }
        mmainset.setbannerSelect(value1);
        
        soundSpinner = (Spinner)findViewById(R.id.soundSpinner);  
        ArrayAdapter <CharSequence> soundadapter = ArrayAdapter.createFromResource(this,  
                R.array.sound_array, android.R.layout.simple_spinner_item);  
        soundadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        soundSpinner.setAdapter(soundadapter);  
        soundSpinner.setPrompt("choose");  
        soundSpinner.setOnItemSelectedListener(new soundSpinnerOnItemSelectListener()); 
        soundvalue=Settings.System.getInt(getContentResolver(), "soundselect",1);
        if(soundvalue==1)
        {
        	soundSpinner.setSelection(0,true);
        }
        else if(soundvalue==2)
        {
        	soundSpinner.setSelection(1,true);
        }
        else if(soundvalue==3)
        {
        	soundSpinner.setSelection(2,true);
        }
        mmainset.setSoundSelect(soundvalue);
        
        eegScaleSpinner = (Spinner)findViewById(R.id.eegscaleSpinner);  
        ArrayAdapter <CharSequence> eegScaleadapter = ArrayAdapter.createFromResource(this,  
                R.array.eeg_array, android.R.layout.simple_spinner_item);  
        eegScaleadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        eegScaleSpinner.setAdapter(eegScaleadapter);  
        eegScaleSpinner.setPrompt("choose");  
        eegScaleSpinner.setOnItemSelectedListener(new eegScaleSpinnerOnItemSelectListener()); 
        int eegvalue=Settings.System.getInt(getContentResolver(), "eegselect",2);
        if(eegvalue==1)
        {
        	eegScaleSpinner.setSelection(0,true);
        }
        else if(eegvalue==2)
        {
        	eegScaleSpinner.setSelection(1,true);
        }
        else if(eegvalue==3)
        {
        	eegScaleSpinner.setSelection(2,true);
        }
        mmainset.seteegScaleSelect(eegvalue);
        
        eegRecScaleSpinner = (Spinner)findViewById(R.id.eegrecscaleSpinner);  
        ArrayAdapter <CharSequence> eegrecScaleadapter = ArrayAdapter.createFromResource(this,  
                R.array.eeg_array, android.R.layout.simple_spinner_item);  
        eegrecScaleadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        eegRecScaleSpinner.setAdapter(eegrecScaleadapter);  
        eegRecScaleSpinner.setPrompt("choose");  
        eegRecScaleSpinner.setOnItemSelectedListener(new eegrecScaleSpinnerOnItemSelectListener()); 
        int eegrecvalue=Settings.System.getInt(getContentResolver(), "eegrecselect",2);
        if(eegrecvalue==1)
        {
        	eegRecScaleSpinner.setSelection(0,true);
        }
        else if(eegrecvalue==2)
        {
        	eegRecScaleSpinner.setSelection(1,true);
        }
        else if(eegrecvalue==3)
        {
        	eegRecScaleSpinner.setSelection(2,true);
        }
        mmainset.seteegrecScaleSelect(eegrecvalue);
        
        fuduSpinner = (Spinner)findViewById(R.id.fuduSpinner);
        ArrayAdapter <CharSequence> fuduadapter = ArrayAdapter.createFromResource(this,  
                R.array.fudu_array, android.R.layout.simple_spinner_item);  
        fuduadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuduSpinner.setAdapter(fuduadapter);  
        fuduSpinner.setPrompt("choose");  
        fuduSpinner.setOnItemSelectedListener(new fuduSpinnerOnItemSelectListener());
        int fuduvalue=Settings.System.getInt(getContentResolver(), "fuduselect",3);
        if(fuduvalue==1)
        {
        	fuduSpinner.setSelection(0,true);
        }
        else if(fuduvalue==2)
        {
        	fuduSpinner.setSelection(1,true);
        }
        else if(fuduvalue==3)
        {
        	fuduSpinner.setSelection(2,true);
        }
        else if(fuduvalue==4)
        {
        	fuduSpinner.setSelection(3,true);
        }
        mmainset.setfuduSelect(fuduvalue);

        eegSpinner = (Spinner)findViewById(R.id.eegSpinner);  
        ArrayAdapter <CharSequence> adapter1 = ArrayAdapter.createFromResource(this,  
                R.array.majui_array, android.R.layout.simple_spinner_item);  
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        value=Settings.System.getInt(getContentResolver(), "eegtimescale",1);
        if(value==1)
        {
        	eegSpinner.setSelection(0,true);
        }
        else if(value==5)
        {
        	eegSpinner.setSelection(1,true);
        }
        else if(value==10)
        {
        	eegSpinner.setSelection(2,true);
        }
        else if(value==60)
        {
        	eegSpinner.setSelection(3,true);
        }
        else if(value==120)
        {
        	eegSpinner.setSelection(4,true);
        }
        
        eegSpinner.setAdapter(adapter1);  
        eegSpinner.setPrompt("eeg");  
        eegSpinner.setOnItemSelectedListener(new eegSpinnerOnItemSelectListener()); 
        shangxianshez=(tiaozhenView)findViewById(R.id.shangxsz);
        shangxianshez.settiaozhenOnClickListener(new maxtiaozhenClickListener());
        value=Settings.System.getInt(getContentResolver(), "shangxiangshez",60);
        shangxianshez.setValue(value);
        shangxianshez.setMaxValue(value);
        mmainset.setshangxianshezValue(value);
        
        xiaxianshez=(tiaozhenView)findViewById(R.id.xiaxsz);
        xiaxianshez.settiaozhenOnClickListener(new mintiaozhenClickListener());
        value1=Settings.System.getInt(getContentResolver(), "xiaxiangshez",40);
        xiaxianshez.setValue(value1);
        xiaxianshez.setMinValue(value1);   
        mmainset.setxiaxianshezValue(value1);
       // shijianshezbuiler=showshijiansheziItem();
        
        mthingshezView=(thingshezView)findViewById(R.id.thingshezView);
        mthingshezView.setthingsClickListener(new mthingsClickListener());
        mshowsetting=(showsetting)findViewById(R.id.showSettingView);
        mshowsetting.setshowClickListener(new mShowSettingClickListener());
        Settings.System.putInt(getContentResolver(), "things",8);
        mmainset.setthings(8);
        mthingshezView.setChecked(value);
        
        Settings.System.putString(getContentResolver(), "nianying","");
        Settings.System.putString(getContentResolver(), "xingming","");
        Settings.System.putString(getContentResolver(), "binglihao","");
        
        xingmingtext=(EditText)findViewById(R.id.xingming);
        xingmingtext.setText(Settings.System.getString(getContentResolver(), "xingming")); 
        xingmingtext.setOnFocusChangeListener(new FocusChangeListener());
        binglihaotext=(EditText)findViewById(R.id.binglihao);
        binglihaotext.setText(Settings.System.getString(getContentResolver(), "binglihao"));
        binglihaotext.setOnFocusChangeListener(new FocusChangeListener());
        xingbietext=(EditText)findViewById(R.id.xingbie);

    	int xingbie = Settings.System.getInt(getContentResolver(), "xingbie",0);
		if(xingbie == 0){
			xingbietext.setText(R.string.man);
		}else{
			xingbietext.setText(R.string.woman);
		}
        xingbietext.setOnFocusChangeListener(new FocusChangeListener());
        nianyingtext=(EditText)findViewById(R.id.nianying);
        
        nianyingtext.setText(Settings.System.getString(getContentResolver(), "nianying"));
        nianyingtext.setOnFocusChangeListener(new FocusChangeListener()); 
        
        binhao=(TextView)findViewById(R.id.bianhao);
        binhao.setText(Settings.System.getString(getContentResolver(), "binglihao"));
        mmainset.mperinfomation.binlinao=binhao.getText().toString();
        name=(TextView)findViewById(R.id.name);
        name.setText(Settings.System.getString(getContentResolver(), "xingming"));
        mmainset.mperinfomation.name=name.getText().toString();
        sexText=(TextView)findViewById(R.id.sex);
		if(xingbie == 0){
			sexText.setText(R.string.man);
		}else{
			sexText.setText(R.string.woman);
		}
        mmainset.mperinfomation.sex=sexText.getText().toString();
        
        mScrollView=(ScrollView)findViewById(R.id.progressview);
        mScrollTextView=(TextView)findViewById(R.id.progresstext);
        context=this;
        audioManager=(AudioManager)this.getSystemService(Context.AUDIO_SERVICE); 
        mon=Calendar.getInstance().get(Calendar.MONTH)+1;
        year=Calendar.getInstance().get(Calendar.YEAR);
        day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        min=Calendar.getInstance().get(Calendar.MINUTE);
        sec=Calendar.getInstance().get(Calendar.SECOND);
        mwaverecord=(waverecord)findViewById(R.id.waverecordid);
        mwaverecord.setupdateInfomationListener(this);
        
        String sdcardState = Environment.getExternalStorageState();
        if(sdcardState.equals(Environment.MEDIA_MOUNTED))
        {
//        	File file=createSaveFile(Environment.getExternalStorageDirectory()+"/CSMDATA","csmdata-"+formatdate(nowtime)+".txt");
//        	try {
//				out=new OutputStreamWriter(new FileOutputStream(file));
//				isFileOpen=true;
//				isReadySaved=true;
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        	
        }else
        {
        	Toast.makeText(this, R.string.sdunmount, Toast.LENGTH_LONG).show();
        	isReadySaved=false;
        } 

        if(soundvalue==1)
        	initSound(R.raw.alarm_high);
        else if(soundvalue==2)
        	initSound(R.raw.alarm_low);
        else if(soundvalue==3)
        	initSound(R.raw.alarm_veryhigh);
        else if(soundvalue==4){
        	initSound(R.raw.alarm_beep_01);
        }else{
        	initSound(R.raw.alarm_low);
		}
        initPrinter();
        //handler.sendEmptyMessageDelayed(50, 2000);
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        
//        try {
//			mSerialPort = mApplication.getSerialPort();
//			mOutputStream = mSerialPort.getOutputStream();
//			mInputStream = mSerialPort.getInputStream();
//			/* Create a receiving thread */
//			mReadThread = new ReadThread();
//			mReadThread.start();
//		} catch (SecurityException e) {
//
//		} catch (IOException e) {
//
//		} 
        handler.sendEmptyMessageDelayed(50, 3200);
        //restartSerialPort();
        
   } 
    static InputMethodManager imm;
    
	private void restartSerialPort() {
		try {
			if (mReadThread != null) {
				if (mReadThread.isAlive())
					mReadThread.interrupt();
				mReadThread = null;
				if (mInputStream != null)
					mInputStream.close();
				mInputStream = null;
			}
			mApplication.closeSerialPort();
			handler.removeMessages(50);
			handler.sendEmptyMessageDelayed(50, 600L);
		} catch (SecurityException e) {

		} catch (IOException e) {

		}
	}
    
    
    public static BluetoothAdapter myBluetoothAdapter;
	public String SelectedBDAddress;
	StatusBox statusBox; 
    private void initPrinter()
    {
    //	if(!ListBluetoothDevice())finish();
        Button Button1 = (Button) findViewById(R.id.button1);
        mmainset = mainset.getInstance();
		statusBox = new StatusBox(this,Button1);
        Button1.setOnClickListener(new Button.OnClickListener()
        {
			public void onClick(View arg0)
			{
				//Printbitmap(SelectedBDAddress);
				handler.sendEmptyMessageAtTime(10, 100);
			}
        });
        Button Button2 = (Button) findViewById(R.id.button2);
        Button2.setOnClickListener(new Button.OnClickListener()
        {
			public void onClick(View arg0)
			{
				//Print2(SelectedBDAddress);
				if(printerlayout.getVisibility()==View.VISIBLE)
				{
				   mainLayout.setVisibility(View.VISIBLE);
				   printerlayout.setVisibility(View.GONE);
				}
				
			}
        });
        Button Button3 = (Button) findViewById(R.id.button3);
        Button3.setOnClickListener(new Button.OnClickListener()
        {
			public void onClick(View arg0)
			{
				//Print3(SelectedBDAddress);
			}
        });
        Button Button4 = (Button) findViewById(R.id.button4);
        Button4.setOnClickListener(new Button.OnClickListener()
        {
			public void onClick(View arg0)
			{
				//Print4(SelectedBDAddress);
			}
        });
        Button Button5 = (Button) findViewById(R.id.button5);
        Button5.setOnClickListener(new Button.OnClickListener()
        {
			public void onClick(View arg0)
			{
				//Print5(SelectedBDAddress);
			}
        });
        Button Button6 = (Button) findViewById(R.id.button6);
        Button6.setOnClickListener(new Button.OnClickListener()
        {
			public void onClick(View arg0)
			{
				//Print6(SelectedBDAddress);
			}
        });
    }
    
    public boolean ListBluetoothDevice()
    {
        final List<Map<String,String>> list=new ArrayList<Map<String, String>>(); 
        ListView listView = (ListView) findViewById(R.id.listView1);
        SimpleAdapter m_adapter = new SimpleAdapter( this,list,
		   		android.R.layout.simple_list_item_2,
		   		new String[]{"DeviceName","BDAddress"},
		   		new int[]{android.R.id.text1,android.R.id.text2}
		   		);
        listView.setAdapter(m_adapter);

        if((myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter())==null)
        {
     		Toast.makeText(this,R.string.no_bluetooth, Toast.LENGTH_LONG).show();
     		return false;
        }

        if(!myBluetoothAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);    
            startActivityForResult(enableBtIntent, 2);
        }

        Set <BluetoothDevice> pairedDevices = myBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() <= 0)return false;
        for (BluetoothDevice device : pairedDevices)
        {
        	Map<String,String> map=new HashMap<String, String>();
        	map.put("DeviceName", device.getName()); 
        	map.put("BDAddress", device.getAddress());
        	list.add(map);
        }
        listView.setOnItemClickListener(new ListView.OnItemClickListener() 
        {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
        	{
        		SelectedBDAddress = list.get(position).get("BDAddress");
        		if (((ListView)parent).getTag() != null){
        			((View)((ListView)parent).getTag()).setBackgroundDrawable(null);
        		}
        		((ListView)parent).setTag(view);
        		view.setBackgroundColor(Color.BLUE);
			}
        });
        return true;
    }
    public boolean OpenPrinter(String BDAddress)
    {
    	if(BDAddress==""||BDAddress==null)
    	{
			Toast.makeText(this,R.string.no_bluetooth, Toast.LENGTH_LONG).show();
    		return false;
    	}
		BluetoothDevice myDevice;
    	myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    	if(myBluetoothAdapter==null)
    	{
			Toast.makeText(this,R.string.no_bluetooth, Toast.LENGTH_LONG).show();
			return false;
    	}
    	myDevice = myBluetoothAdapter.getRemoteDevice(BDAddress);
    	if(myDevice==null)
    	{
			Toast.makeText(this,R.string.no_printer, Toast.LENGTH_LONG).show();
			return false;
    	}
		if(zpSDK.zp_open(myBluetoothAdapter,myDevice)==false)
		{
			Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
    }
    
    public void Printbitmap(String BDAddress)
    {
    	statusBox.Show("閿熸枻鎷烽敓鑺傝揪鎷峰嵃...");
    	Bitmap bmp=mmainset.genBitmap();
		if(!OpenPrinter(BDAddress))
		{
			statusBox.Close();
			return;
		}
//		zpSDK.zp_close();
//		
//		if(!OpenPrinter(BDAddress))
//		{
//			statusBox.Close();
//			return;
//		}
		
		if(!zpSDK.zp_page_create(200,120))
		{
     		Toast.makeText(this,"閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷峰嵃椤甸敓鏂ゆ嫹澶遍敓鏂ゆ嫹", Toast.LENGTH_LONG).show();
			statusBox.Close();
     		return;
		}
		zpSDK.TextPosWinStyle = false;
		//Bitmap bmp=mmainset.genBitmap();
		zpSDK.zp_draw_bitmap(bmp, 0, 400);
		if(zpSDK.zp_printer_check_error())
	    {
			Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
	    }
		else
		{
			if(!zpSDK.zp_page_print(true))
			{
				Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
			}
			zpSDK.zp_page_free();
			//zpSDK.zp_goto_mark_right(30);
		}
		

		//if(zpSDK.zp_printer_check_error())
	   // {
	//		Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
	 //   }
		//zpSDK.zp_close();
		statusBox.Close();
		bmp.recycle();
		handler.sendEmptyMessage(9);
		
    }
  
    
//    private StorageEventListener mStorageListener = new StorageEventListener() {  
//        public void onStorageStateChanged(String path, String oldState, String newState) {  
//            final boolean on = newState.equals(Environment.MEDIA_SHARED);  
//            switchDisplay(on);  
//        }  
//    };
    
    public File createSaveFile(String path,String filename)
    {
    	String str=path+"/"+filename;
    	File filepath=new File(path);
    	filepath.mkdir();
    	File file=new File(str);
    //	Log.i(TAG,"liuliangxiang createSaveFile str="+str);
    	if(!file.exists())
    	{
    		try {
				file.createNewFile();
				final String abspath = file.getAbsolutePath();
				Runtime.getRuntime().exec("chmod 777 "+abspath).waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
    	}
    	return file;
    }
    int size = 0;
    int[] DataReceive = new int[200];
    private class ReadThread extends Thread {
		public void run() {
			super.run();
			while(!isInterrupted()) {
				int size;
				try {
				//	synchronized (this)
					{					
						byte[] buffer = new byte[512];
						if (mInputStream == null) return;
						size = mInputStream.read(buffer);
						if ((size > 0)&&(!mmainset.isRecData)) {
							//synchronized (mApplication.mLock){
							{
								mmainset.timeOut=0;
								onDataReceived(buffer, size);
							}
						}

//						try {
//							Thread.sleep(10);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
//				if (msgArray.size() > 0) {
//					if (isNetConnect) {
//						if (multicastSocket == null) {
//							try {
//								multicastSocket = new MulticastSocket(
//										Constant.PORT);
//								multicastSocket.joinGroup(InetAddress
//										.getByName(Constant.MULTICAST_IP));
//								System.out.println("Socket started...");
//							} catch (Exception e) {
//								try {
//									if (null != multicastSocket
//											&& !multicastSocket.isClosed()) {
//										multicastSocket
//												.leaveGroup(InetAddress
//														.getByName(Constant.MULTICAST_IP));
//										multicastSocket.close();
//										multicastSocket = null;
//									}
//								} catch (Exception e1) {
//									e1.printStackTrace();
//								}
//								e.printStackTrace();
//							}
//						}
//						if (null != multicastSocket
//								&& !multicastSocket.isClosed()) {
//							DatagramPacket dp;
//							try {
//								//Log.i(TAG, "liuliangxiang sendMsg="
//								//		+ msgSendBuffer.toString());
//								dp = new DatagramPacket(
//										msgSendBuffer,
//										msgSendBuffer.length,
//										InetAddress
//												.getByName(Constant.MULTICAST_IP),
//										Constant.PORT);
//								multicastSocket.send(dp);
//							} catch (UnknownHostException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					} else {
//						if (null != multicastSocket
//								&& !multicastSocket.isClosed()) {
//							try {
//								multicastSocket.leaveGroup(InetAddress
//										.getByName(Constant.MULTICAST_IP));
//								multicastSocket.close();
//								multicastSocket = null;
//							} catch (UnknownHostException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}
//					msgArray.clear();
//				}
			}
		}
	}
    public void runWarnView()
    {
    	runOnUiThread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				if(mmainset.isConnected)
				{
					if(mmainset.isWarnConnected)
					{
						ConnectLevel=2;
						connecttishi();
					}
					else
					{
						ConnectLevel=0;
						connectxiaoshi();
					}
				}
				else
				{					
					ConnectLevel=1;
					connecttishi();
				}
				
			}
    	
    	});
    }

	int csiLevel;
	int curCSI=0xff;
	int errortime = 0;
	public ArrayList<Integer> DataReceived = new ArrayList<Integer>();

	protected void onDataReceived(final byte[] buffer, final int size) {
		// int curCSI;
		mReadData = true;
		mmainset.timeOut = 0;

		istimeoutSave = false;
		mmainset.isConnected = true;
//		Log.i(TAG,
//				" onDataReceived DataReceived= " + mmainset.DataReceived.size());
		if ((!isStart))
			return;
		for (int i = 0; i < size; i++) {
			mmainset.DataReceived.add(buffer[i] & 0xff);
		}
		if (mmainset.DataReceived.size() >= 3) {
//			Log.i(TAG, " onDataReceived = " + mmainset.DataReceived.size()
//					+ " data[0]=" + mmainset.DataReceived.get(0) + " data[1]="
//					+ mmainset.DataReceived.get(1) + " data[2]"
//					+ mmainset.DataReceived.get(2));
			if ((mmainset.DataReceived.get(0) != 0xff)
					&& (mmainset.DataReceived.get(1) != 0x01)
					&& (mmainset.DataReceived.get(2) != 0x7d)) {
				mmainset.DataReceived.clear();
				// errortime++;
				return;
			}
		}

		if (mmainset.DataReceived.size() >= DataLen) {
			if ((mmainset.DataReceived.get(0) == 0xff)
					&& (mmainset.DataReceived.get(1) == 0x01)
					&& (mmainset.DataReceived.get(2) == 0x7d)
					&& (mmainset.DataReceived.get(130) == 0xfe)) {

				if (calc_crc(mmainset.DataReceived, (DataLen - 4))) {
					curCSI = mmainset.DataReceived.get(14);
					if (curCSI > 100) {
						connectnum++;
					} else {
						connectnum = 0;
					}
					if (!isFirstConnect) {
						if (curCSI != 0xff) {
							isFirstConnect = true;
						}
					}
					if (!mmainset.isWarnConnected) {
						mmainset.CSI
								.add((curCSI & 0xff) | mmainset.getthings());
						for (int i = 0; i < 100; i++) {
							EEGDATA[i] = mmainset.DataReceived.get(28 + i);
							// eegbyte[i] = EEGDATA[i].byteValue();
							msgSendBuffer[54 + i] = EEGDATA[i].byteValue();
						}
						// EEGDATA[99]=mmainset.DataReceived.get(127);
						// mmainset.EEG.add(mmainset.DataReceived.get(127));
						mmainset.EEG.add((Integer[]) (EEGDATA.clone()));
						// BS=(mmainset.DataReceived.get(15)).byteValue();
						mmainset.BS.add(mmainset.DataReceived.get(15));
						// EMG=mmainset.DataReceived.get(19).byteValue();
						mmainset.EMG.add(mmainset.DataReceived.get(19));
						// SQI=mmainset.DataReceived.get(16).byteValue();
						mmainset.SQI.add(mmainset.DataReceived.get(16));
						// BLACK=mmainset.DataReceived.get(17).byteValue();
						mmainset.BLACK.add(mmainset.DataReceived.get(17));
						// WHITE=mmainset.DataReceived.get(18).byteValue();
						mmainset.WHITE.add(mmainset.DataReceived.get(18));
						Log.i(TAG, " onDataReceived curCSI= " + curCSI);
					}

				}
			}
			mmainset.DataReceived.clear();
			mReadData = false;
		}


	}

	boolean calc_crc(ArrayList<Integer> mList, int len) {
		int i, j;
		int CRC_Data = (mList.get(129)) & 0xff;
		CRC_Data = CRC_Data << 8;
		CRC_Data += (mList.get(128) & 0xff);
		int crc_reg = 0;
		int current;

		for (i = 0; i < len; i++) {
			current = (mList.get(i + 1) & 0xff) << 8;
			for (j = 0; j < 8; j++) {
				if ((short) (crc_reg ^ current) < 0) {
					crc_reg = (crc_reg << 1) ^ 0x1021;
				} else {
					crc_reg <<= 1;
				}
				current <<= 1;
			}
		}
//		Log.i(TAG, "calc_crc CRC_Data = 0x" + Integer.toHexString(CRC_Data)
//				+ " crc_reg= 0x " + Integer.toHexString(crc_reg & 0xffff));
		return ((crc_reg & 0xffff) == CRC_Data);
	}

    class FocusChangeListener implements View.OnFocusChangeListener
    {
		public void onFocusChange(View arg0, boolean arg1) {
			// TODO Auto-generated method stub
			if(arg0.equals(xingmingtext))
			{
				if(!arg1)
				{
					String str=xingmingtext.getText().toString();
					String str2=Settings.System.getString(getContentResolver(), "xingming");
					if(!str.equals(str2))
					{
						mmainset.isStartRecord=false;
						isnamerecord=false;
						mmainset.msgindex=4;
						Settings.System.putString(getContentResolver(), "xingming",xingmingtext.getText().toString());
						name.setText(xingmingtext.getText().toString());
						mmainset.mperinfomation.name=xingmingtext.getText().toString();
						mmainset.mperinfomation.indexname=mmainset.CSI.size();
						mmainset.setthings(8);
					}				
//					Settings.System.putString(getContentResolver(), "xingming",xingmingtext.getText().toString());
//					name.setText(xingmingtext.getText().toString());
//					mmainset.mperinfomation.name=xingmingtext.getText().toString();
//					mmainset.mperinfomation.indexname=mmainset.CSI.size();					
				}
			}
			if(arg0.equals(binglihaotext))
			{
				if(!arg1)
				{
					String str=binglihaotext.getText().toString();
					String str2=Settings.System.getString(getContentResolver(), "binglihao");
					if(!str.equals(str2))
					{
						mmainset.isStartRecord=false;
						isnamerecord=false;
						mmainset.msgindex=4;
						Settings.System.putString(getContentResolver(), "binglihao",binglihaotext.getText().toString());
						binhao.setText(binglihaotext.getText().toString());
						mmainset.mperinfomation.binlinao=binglihaotext.getText().toString();
						mmainset.mperinfomation.indexbinlinao=mmainset.CSI.size();
						mmainset.setthings(8);
					}
					
//					Settings.System.putString(getContentResolver(), "binglihao",binglihaotext.getText().toString());
//					binhao.setText(binglihaotext.getText().toString());
//					mmainset.mperinfomation.binlinao=binglihaotext.getText().toString();
//					mmainset.mperinfomation.indexbinlinao=mmainset.CSI.size();
					//Log.i(TAG,"liuliangxiang mperinfomation.binlinao="+mmainset.mperinfomation.binlinao);
				}
			}
			if(arg0.equals(xingbietext))
			{
				if(!arg1)
				{
					Settings.System.putString(getContentResolver(), "xingbie",xingbietext.getText().toString());
					mmainset.mperinfomation.sex=xingbietext.getText().toString();
					mmainset.mperinfomation.indexsex=mmainset.CSI.size();
				}
			}
			if(arg0.equals(nianyingtext))
			{
				if(!arg1)
				{
					String str=nianyingtext.getText().toString();
					String str2=Settings.System.getString(getContentResolver(), "nianying");
					if(!str.equals(str2))
					{
						mmainset.isStartRecord=false;
						isnamerecord=false;
						mmainset.msgindex=4;
						Settings.System.putString(getContentResolver(), "nianying",nianyingtext.getText().toString());	
						mmainset.mperinfomation.age=nianyingtext.getText().toString();
						mmainset.mperinfomation.indexage=mmainset.CSI.size();
						mmainset.setthings(8);
					}				
				}
			}
			{
			//	imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
			//		     InputMethodManager.HIDE_NOT_ALWAYS);]
				


			}
			//handler.removeMessages(1);
			//handler.sendEmptyMessageDelayed(1, 4000);

		}
    	
    }
    class mShowSettingClickListener implements showmodeClickListener
    {

		public void onshowmodesselect(int mode) {
			// TODO Auto-generated method stub
			mmainset.boxingType=mode+4;			
			mmainset.isRecData=false;
		}
    	
    }
    class mthingsClickListener implements thingsClickListener
    {

		public void onthingsselect(int things) {
			// TODO Auto-generated method stub
			mmainset.setthings(things);
			Settings.System.putInt(getContentResolver(), "things",things);	

		//	handler.removeMessages(1);
		//	handler.sendEmptyMessageDelayed(1, 4000);
		}
    	
    }
    
    class maxtiaozhenClickListener implements tiaozhenClickListener
    {

		public void onClickDownUp(int downup) {
			// TODO Auto-generated method stub
			
			if(downup==tiaozhenView.UP)
			{	
				int value=shangxianshez.getValue()+1;
				if(value<=100)
				{
					shangxianshez.setValue(value);
					shangxianshez.setMaxValue(value);
				}
			}
			else{
				int value=shangxianshez.getValue()-1;
				if(value>shangxianshez.getMinValue())
				{
					shangxianshez.setValue(value);
					shangxianshez.setMaxValue(value);
				}
			}	
			Settings.System.putInt(getContentResolver(), "shangxiangshez",shangxianshez.getValue());
			mmainset.setshangxianshezValue(shangxianshez.getValue());   
			//handler.removeMessages(1);
			//handler.sendEmptyMessageDelayed(1, 4000);
		}
    }
		
		
    
    class mintiaozhenClickListener implements tiaozhenClickListener
    {

		public void onClickDownUp(int downup) {
			// TODO Auto-generated method stub
			if(downup==tiaozhenView.UP)
			{	
				int value=xiaxianshez.getValue()+1;
				if(value<xiaxianshez.getMaxValue())
				{
					xiaxianshez.setValue(value);
					xiaxianshez.setMinValue(value);
				}
				
			}
			else{
				int value=xiaxianshez.getValue()-1;
				if(value>=0)
				{
					xiaxianshez.setValue(value);
					xiaxianshez.setMinValue(value);
				}
			}	
			
			Settings.System.putInt(getContentResolver(), "xiaxiangshez",xiaxianshez.getValue());
			mmainset.setxiaxianshezValue(xiaxianshez.getValue()); 
		//	handler.removeMessages(1);
		//	handler.sendEmptyMessageDelayed(1, 4000);
		}
    	
    }
    
    class majuiSpinnerOnItemSelectListener implements OnItemSelectedListener
    {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int value;
			switch(arg2)
			{
				case 0:
					value=1;
				break;
				case 1:
					value=2;
				break;
				case 2:
					value=5;
				break;
				case 3:
					value=10;
				break;
				case 4:
					value=60;
				break;
				case 5:
					value=120;
				break;
				default:
					value=1;
				break;	
			}
			Settings.System.putInt(getContentResolver(), "majuitimescale",value);
			mmainset.setmajuitimescale(value);
	//		handler.removeMessages(1);
	//		handler.sendEmptyMessageDelayed(1, 4000);
			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    class majuirecSpinnerOnItemSelectListener implements OnItemSelectedListener
    {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int value;
			switch(arg2)
			{
				case 0:
					value=1;
				break;
				case 1:
					value=2;
				break;
				case 2:
					value=5;
				break;
				case 3:
					value=10;
				break;
				case 4:
					value=60;
				break;
				case 5:
					value=120;
				break;
				default:
					value=1;
				break;	
			}
			Settings.System.putInt(getContentResolver(), "majuirectimescale",value);
			mmainset.setmajuirectimescale(value);
	//		handler.removeMessages(1);
	//		handler.sendEmptyMessageDelayed(1, 4000);
			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    class bannerSpinnerOnItemSelectListener implements OnItemSelectedListener
    {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int value;
			switch(arg2)
			{
				case 0:
					value=1;
				break;
				case 1:
					value=2;
				break;
				case 2:
					value=3;
				break;
				default:
					value=1;
				break;	
			}
			Settings.System.putInt(getContentResolver(), "bannerselect",value);
			mmainset.setbannerSelect(value);
	//		handler.removeMessages(1);
	//		handler.sendEmptyMessageDelayed(1, 4000);
			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    class soundSpinnerOnItemSelectListener implements OnItemSelectedListener
    {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int value;
			switch(arg2)
			{
				case 0:
					value=1;
					initSound(R.raw.alarm_high);
				break;
				case 1:
					value=2;
					initSound(R.raw.alarm_low);
				break;
				case 2:
					value=3;
					initSound(R.raw.alarm_veryhigh);
				break;
				case 3:
					value=4;
					initSound(R.raw.alarm_beep_01);
				break;
				default:
					value=1;
				break;	
			}
			Settings.System.putInt(getContentResolver(), "soundselect",value);
			mmainset.setSoundSelect(value);
	//		handler.removeMessages(1);
	//		handler.sendEmptyMessageDelayed(1, 4000);
			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    class eegScaleSpinnerOnItemSelectListener implements OnItemSelectedListener
    {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int value;
			switch(arg2)
			{
				case 0:
					value=1;		
				break;
				case 1:
					value=2;
				break;
				case 2:
					value=3;
				break;
				default:
					value=2;
				break;	
			}
			Settings.System.putInt(getContentResolver(), "eegselect",value);
			mmainset.seteegScaleSelect(value);
	//		handler.removeMessages(1);
	//		handler.sendEmptyMessageDelayed(1, 4000);
			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    class eegrecScaleSpinnerOnItemSelectListener implements OnItemSelectedListener
    {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int value;
			switch(arg2)
			{
				case 0:
					value=1;		
				break;
				case 1:
					value=2;
				break;
				case 2:
					value=3;
				break;
				default:
					value=2;
				break;	
			}
			Settings.System.putInt(getContentResolver(), "eegrecselect",value);
			mmainset.seteegrecScaleSelect(value);
	//		handler.removeMessages(1);
	//		handler.sendEmptyMessageDelayed(1, 4000);
			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    class fuduSpinnerOnItemSelectListener implements OnItemSelectedListener
    {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int value;
			switch(arg2)
			{
				case 0:
					value=1;		
				break;
				case 1:
					value=2;
				break;
				case 2:
					value=3;
				break;
				case 3:
					value=4;
				break;
				default:
					value=3;
				break;	
			}
			Settings.System.putInt(getContentResolver(), "fuduselect",value);
			mmainset.setfuduSelect(value);
	//		handler.removeMessages(1);
	//		handler.sendEmptyMessageDelayed(1, 4000);
			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    
    class eegSpinnerOnItemSelectListener implements OnItemSelectedListener
    {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int value;
			switch(arg2)
			{
				case 0:
					value=1;
				break;
				case 1:
					value=5;
				break;
				case 2:
					value=10;
				break;
				case 3:
					value=60;
				break;
				case 4:
					value=120;
				break;
				default:
					value=1;
				break;	
			}
			Settings.System.putInt(getContentResolver(), "eegtimescale",value);
			mmainset.seteegtimescale(value);
	//		handler.removeMessages(1);
	//		handler.sendEmptyMessageDelayed(1, 4000);			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
    }
      
    public class mIntentReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
                String action = intent.getAction();
                if(action.equals(Intent.ACTION_TIME_TICK)) 
                {
                	updatetime();
                }
                if(action.equals(Intent.ACTION_MEDIA_MOUNTED))
                {
//                	if(!isFileOpen)
//                	{
//                		File file=createSaveFile(Environment.getExternalStorageDirectory()+"/CSMDATA","csmdata-"+formatdate(nowtime+saveDataLen*1000)+".txt");
//                    	try {
//            				out=new OutputStreamWriter(new FileOutputStream(file));
//            				isFileOpen=true;
//            			} catch (FileNotFoundException e) {
//            				// TODO Auto-generated catch block
//            				e.printStackTrace();
//            			}
//                    	handler.post(new Runnable()
//                    	{
//
//							public void run() {
//								// TODO Auto-generated method stub
//								int temp=saveDataLen;
//								for(int i=temp;i<mmainset.CSI.size();i++)
//								{
//									//out.write(mmainset.perinfomation.name);
//									try {										
//										if(i==(mmainset.mperinfomation.indexname-1))
//										{
//											out.write("閿熸枻鎷烽敓鏂ゆ嫹: ");
//											out.write(mmainset.mperinfomation.name+" ");
//											out.write("閿熺殕鎲嬫嫹");
//											out.write(mmainset.mperinfomation.sex+" ");
//											out.write("閿熸枻鎷烽敓鏂ゆ嫹鐗涢敓锟�;
//											out.write(mmainset.mperinfomation.binlinao+" ");
//											out.write("\n");																					
//										}
//										out.write("CSI");
//										out.write(Integer.toString(i)+". ");
//										out.write(Integer.toString(mmainset.CSI.get(i))+" ");
//										out.write("BS");
//										out.write(Integer.toString(i)+". ");
//										out.write(Integer.toString(mmainset.BS.get(i))+" ");
//										out.write("EMG");
//										out.write(Integer.toString(i)+". ");
//										out.write(Integer.toString(mmainset.EMG.get(i))+" ");
//										out.write("SQI");
//										out.write(Integer.toString(i)+". ");
//										out.write(Integer.toString(mmainset.SQI.get(i))+" ");
//										out.write("BLACK");
//										out.write(Integer.toString(i)+". ");
//										out.write(Integer.toString(mmainset.BLACK.get(i))+" ");
//										out.write("WHITE");
//										out.write(Integer.toString(i)+". ");
//										out.write(Integer.toString(mmainset.WHITE.get(i))+" ");	
//										out.write("EEG");
//										out.write(Integer.toString(i)+". ");
//										Integer[] eegdata=new Integer[100];
//										eegdata=mmainset.EEG.get(i);
//										for(int j=0;j<100;j++)
//										{
//											out.write(eegdata[i]);
//										}
//										out.write("\n");
//										saveDataLen=i;
//									} catch (IOException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//								}
//								//saveDataLen=mmainset.CSI.size()-1;								
//							} 		
//                    	}
//                    	);
// 
//                	}
                }
                if(action.equals(Intent.ACTION_MEDIA_UNMOUNTED)||action.equals(Intent.ACTION_MEDIA_REMOVED)||action.equals(Intent.ACTION_MEDIA_BAD_REMOVAL))
                {
                	isReadySaved=false;
                	if(isFileOpen)
                	{
                		try {
							out.flush();
							out.close();
							out=null;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
                		finally
                		{
                			isFileOpen=false;
                		}
                	}
                }
                
//                if(action.equals(Intent.ACTION_BOOT_COMPLETED)){
//                	//Log.i(TAG,"liuliangxiang ACTION_BOOT_COMPLETED");
//                	//handler.sendEmptyMessageDelayed(50, 100);
//                }
//                if(action.equals("android.net.conn.CONNECTIVITY_CHANGE")){
//                	Log.i(TAG, "liuliangxiang action="+action);
//                	if(getActiveNetwork(Csmmain.this)!=null){
//                		isNetConnect=true;
//                	}else{
//                		isNetConnect=false;
//                	}
//                }
                
                if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
                	 int rawlevel = intent.getIntExtra("level", -1);//閿熸枻鎷风帿閿熻甯嫹閿燂拷 
                     int scale = intent.getIntExtra("scale", -1); 
     //閿熸枻鎷烽敓鏂ゆ嫹鑹块敓锟�
                     int level = -1; 
                     if (rawlevel >= 0 && scale > 0) { 
                         level = (rawlevel * 100) / scale; 
                     } 
                	//int scale = intent.getIntExtra("scale", -1);
                	int status = intent.getIntExtra("status", -1);
                	StringBuilder sb = new StringBuilder(); 
                	switch (status) {  
                 case BatteryManager.BATTERY_STATUS_UNKNOWN:  
                	 baterryView.setText("");  
                 break;  
                case BatteryManager.BATTERY_STATUS_CHARGING:  
                	baterryView.setText(R.string.charging);
                break; 
                
                case BatteryManager.BATTERY_STATUS_DISCHARGING:  
                	 case BatteryManager.BATTERY_STATUS_NOT_CHARGING:   
                		 baterryView.setText("[" + level + "]");  
                	  break;  
                	 case BatteryManager.BATTERY_STATUS_FULL:  
                		 baterryView.setText(R.string.chargfull);
                	  break;
               }
                	//baterryView.setText(sb.toString());
                }
        }
    }
    public static NetworkInfo getActiveNetwork(Context context){
        if (context == null)
            return null;
        ConnectivityManager mConnMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnMgr == null)
            return null;
        NetworkInfo aActiveInfo = mConnMgr.getActiveNetworkInfo(); // 获取活动网络连接信息
        return aActiveInfo;
    } 

    
    protected void onResume () 
    {
    	updatetime();
    	mtimeTick.setTimeTickListener(this);
    	Settings.System.putInt(this.getContentResolver(), "csmmain", 1);
    //	restartSerialPort();
    	super.onResume();
    }
    
    protected void onPause() {
    	Settings.System.putInt(this.getContentResolver(), "csmmain", 0);
    	super.onPause();
    }
    
//    protected void onPause()
//    {
//    	mtimeTick.unonSetTimeTickLister();
//    	super.onPause();
//    }
    
    protected void onStop()
    {
    	//this.unregisterReceiver(receiver);
    	//boxing.unregisterRecevier();
    	Settings.System.putInt(this.getContentResolver(), "csmmain", 0);
    	super.onStop();   	
    }
    public boolean onTouchEvent(MotionEvent event){
    	if(mmainset.isRecData){
    		return super.onTouchEvent(event);
    	}
    	int x=(int)event.getX();
    	int y=(int)event.getY();
    	if((MotionEvent.ACTION_CANCEL==event.getAction())||
    		(MotionEvent.ACTION_UP==event.getAction())){
    		if((x>0)&&(y>14)&&(x<220)&&(y<150)){
    			showPopupWindow();
    		}
    	}
    	
    	return super.onTouchEvent(event);
    }
    private void showPopupWindow(){
    	if(max_csiLayout.getVisibility()==View.GONE){
    		//LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		handler.sendEmptyMessage(12);
    		max_csiLayout.setVisibility(View.VISIBLE);
    	}else{
    		handler.removeMessages(12);
    		max_csiLayout.setVisibility(View.GONE);
    	}
    }
    protected void onDestory()
    {
    	this.unregisterReceiver(receiver);
    	boxing.unregisterRecevier();
    	try {
			mInputStream.close();
			mOutputStream.close();
			mSerialPort.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Settings.System.putInt(this.getContentResolver(), "csmmain", 0);
    	super.onDestroy();
    }
    
   /* 
    private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
        		updatetime();
        };
    };*/
    
    private void updatetime()
    {
    	timeYear.setText(sdf.format(new Date()));
    	timeHour.setText(sdf1.format(new Date()));
    }
    
	private int precsilevel;
    
    public void baojingtishi()
    {
    	//soundpool.play(poolint, 1.0f, 1.0f, 0, -1, 1.5f);
    	if((!isbaojing)||(csiLevel!=precsilevel))
    	{
    		isbaojing=true;
			precsilevel=csiLevel;
			if(csiLevel==1)
			{
				warnCsiText.setText(R.string.highcsi);
			}
			else if(csiLevel==2)
			{
				warnCsiText.setText(R.string.lowcsi);
			}
			if(mmainset.warnoff==0)
				baojingAnim.start();
    	}
		
    	//
    }
    
    public void baojingxiaoshi()
    {
    	//soundpool.stop(poolint);
    	if((isbaojing)||(csiLevel!=precsilevel))
    	{
	    	isbaojing=false;
			precsilevel=0;
	    	baojingAnim.cancel();
	    	warningView.setImageResource(R.drawable.redstar);
			warnCsiText.setText(R.string.normalcsi);
    	}
    	//warningView.setImageResource(R.drawable.redstar);
    }

	private int ConnectLevel;
	private int PreConnectLevel;
	public void connecttishi()
    {
    	//soundpool.play(poolint, 1.0f, 1.0f, 0, -1, 1.5f);
    	if((!isConnectWarn)||(PreConnectLevel!=ConnectLevel))
    	{
    		isConnectWarn=true;
			PreConnectLevel=ConnectLevel;
			if(ConnectLevel==1)
			{
				warnConnectText.setText(R.string.nochannel);
			}
			else if(ConnectLevel==2)
			{
				warnConnectText.setText(R.string.errorchannel);
			}
			if(mmainset.warnoff==0)
				warnConnectAnim.start();
    	}
		
    	//
    }
    
    @SuppressWarnings("deprecation")
	public void connectxiaoshi()
    {
    	//soundpool.stop(poolint);
    	if((isConnectWarn)||(PreConnectLevel!=ConnectLevel))
    	{
	    	isConnectWarn=false;
			PreConnectLevel=0;
	    	warnConnectAnim.cancel();
	    	connectView.setImageResource(R.drawable.greenstar);
			warnConnectText.setText(R.string.normalchannel);
    	}
    	//warningView.setImageResource(R.drawable.redstar);
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(isRecord)
		{
			isRecord=false;
			handler.sendEmptyMessage(1);
			mScrollTextView.setText("");
			return;
		}
		if(progress!=null)
		{
			if(progress.isShowing())
			{
				return;
			}
		}
		if(v.equals(btnsci))
		{
			mSetItem=0;
			if(mmainset.boxingType==mmainset.SCITYPE)
			{
				mmainset.boxingType=mainset.SCI2TYPE;
			}
			else if(mmainset.boxingType==mmainset.SCI2TYPE)
			{
				mmainset.boxingType=mainset.DOUBLETYPE;
			}
			else
			{
				mmainset.boxingType=mainset.SCITYPE;
			}
			if(mmenuLayout.getVisibility()==View.VISIBLE)
			{
				mmenuLayout.setVisibility(View.GONE);
				mmytabView.clearHost();
				setLayoutVisible(null);
				mSetItem=0;
			}
			mshowsetting.setChecked(-1);
		}
		
		else if(v.equals(btneeg))
		{
			mSetItem=1;
			if(mmainset.boxingType==mmainset.DOUBLETYPE)
			{
				mmainset.boxingType=mainset.EEGTYPE;
			}
			else if(mmainset.boxingType==mmainset.EEGTYPE)
			{
				mmainset.boxingType=mainset.DOUBLETYPE;
			}
			else
			{
				mmainset.boxingType=mainset.EEGTYPE;
			}
			if(mmenuLayout.getVisibility()==View.VISIBLE)
			{
				mmenuLayout.setVisibility(View.GONE);
				mmytabView.clearHost();
				setLayoutVisible(null);
				mSetItem=0;
			}
			mshowsetting.setChecked(-1);
		}
		
		else if(v.equals(btndongjie))
		{		
			//else
			if(!mmainset.isdongjie)
			{
				
				if(mSetItem!=2)
				{
//					if(mmainset.isRecData)
//					{
//						mmainset.isRecData=false;
//						return;
//					}
					mSetItem=2;
					mmenuLayout.setVisibility(View.VISIBLE);
					
					mmytabView.clearHost();		
					setLayoutVisible(null);
					mmytabView.addHost(getString(R.string.jilu));
					//mmytabView.addHost(getString(R.string.wavereview));
					//mmytabView.addHost(getString(R.string.print));
					mmytabView.setSelectHost(0);
					if(mmytabView.getSelectId()==0)
					{
						setLayoutVisible(bingrenziliaoLayout);
					}
					
//					mmytabView.addHost(getString(R.string.jilu));
//					mmytabView.addHost(getString(R.string.csitime));
//					mmytabView.addHost(getString(R.string.eegtime));
//					if(mmytabView.getSelectId()==0)
//					{
//						setLayoutVisible(jiluxinxiLayout);
//					//	mmytabView.setSelectHost(0);
//					}
//					else if(mmytabView.getSelectId()==1)
//					{
//					//	mmytabView.clearHost();
//					//	setLayoutVisible(null);
//						setLayoutVisible(mjsdLayout);
//					}
//					else if(mmytabView.getSelectId()==2)
//					{
//					//	mmytabView.clearHost();
//					//	setLayoutVisible(null);
//						setLayoutVisible(eegLayout);
//					}
					//handler.removeMessages(1);
					//handler.sendEmptyMessageDelayed(1, 4000);
				}
				else
				{
					if(mmenuLayout.getVisibility()==View.VISIBLE)
					{
						mmenuLayout.setVisibility(View.GONE);
						mmytabView.clearHost();
						setLayoutVisible(null);
						mSetItem=0;
					}
				}
			}
			else
			{
				mmainset.isdongjie=false;
				dongjieText.setText(R.string.jilu);
			}
		}
		else if(v.equals(btnshezi))
		{		
			if(mSetItem!=3)
			{
				mSetItem=3;
				mmenuLayout.setVisibility(View.VISIBLE);
				
				mmytabView.clearHost();		
				setLayoutVisible(null);
				mmytabView.addHost(getString(R.string.bjshezi));
				mmytabView.addHost(getString(R.string.sjshezi));
				mmytabView.addHost(getString(R.string.csitime));
				mmytabView.addHost(getString(R.string.benjishezi));
				//mmytabView.addHost(getString(R.string.benjishezi));	
				mmytabView.setSelectHost(0);
				if(mmytabView.getSelectId()==0)
				{	
					setLayoutVisible(baojingsezLayout);
				}
				else if(mmytabView.getSelectId()==1)
				{	
					//setLayoutVisible(shijianshezLayout);
					setLayoutVisible(mjsdLayout);
				}
				else if(mmytabView.getSelectId()==2)
				{	
					//setLayoutVisible(shijiangshezLayout);
					setLayoutVisible(eegLayout);
				}
				else if(mmytabView.getSelectId()==3)
				{	
					setLayoutVisible(benjishezLayout);
				}	
		//		handler.removeMessages(1);
		//		handler.sendEmptyMessageDelayed(1, 4000);
			}
			else
			{
				if(mmenuLayout.getVisibility()==View.VISIBLE)
				{
					mmenuLayout.setVisibility(View.GONE);
					mmytabView.clearHost();		
					setLayoutVisible(null);
					mSetItem=0;
				}
			}
		}
		else if(v==btnbinhao)
		{
			if(mSetItem!=4)
			{
				mSetItem=4;
				mmenuLayout.setVisibility(View.VISIBLE);
				
				mmytabView.clearHost();		
				setLayoutVisible(null);
					
				//mmytabView.addHost(getString(R.string.));
				mmytabView.addHost(getString(R.string.yansimos));		
				mmytabView.addHost(getString(R.string.ruanjianbanb));	
				mmytabView.addHost(getString(R.string.wavereview));	
				mmytabView.addHost(getString(R.string.print));
				mmytabView.setSelectHost(0);
				if(mmytabView.getSelectId()==0)
				{	
					setLayoutVisible(showSettingLayout);
				}
				else if(mmytabView.getSelectId()==1)
				{	
					setLayoutVisible(showSettingLayout);
					//
				}
				else if(mmytabView.getSelectId()==2)
				{	
					setLayoutVisible(ruanjiangversionLayout);
				}	
	//			handler.removeMessages(1);
	//			handler.sendEmptyMessageDelayed(1, 4000);
			}
			else
			{
				if(mmenuLayout.getVisibility()==View.VISIBLE)
				{
					mmenuLayout.setVisibility(View.GONE);
					mmytabView.clearHost();		
					setLayoutVisible(null);
					mSetItem=0;
				}
			}
		}	
		else if(v==btnpause)
		{
			
			mSetItem=5;
			if(mmainset.boxingType>=mmainset.DOUBLETYPESHOW)
			{
				return;
			}
			if(mmainset.isRecData)
			{
				return;
			}
			if(!isStart)
			{
				return;
			}
			if(!mmainset.isdongjie)
			{				
				textpause.setText(R.string.resume);
				mmainset.isdongjie=true;
				mmainset.csidongjielen=mmainset.CSI.size();
				mmainset.eegdongjielen=mmainset.EEG.size();
			}
			else
			{
				textpause.setText(R.string.pause);
				mmainset.isdongjie=false;
			}
		}
		else if(v==btnsave)
		{
			//mSetItem=6;
			//handler.post(new mSaveRunnable());
			if(isStart)
			{
				btnsave.setText(R.string.stop);
				isStart=true;
				handler.post(new mSaveRunnable());
				if(mmainset.isdongjie)
				{
					textpause.setText(R.string.pause);
					mmainset.isdongjie=false;		
				}
			}
			else
			{
				
				if(!Checkinformation())
				{
					 Dialog alertDialog = new AlertDialog.Builder(this). 
				                setTitle(R.string.warning). 
				                setMessage(R.string.warn_infoempty). 
				                setIcon(R.drawable.ic_launcher). 
				                setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { 			                     
				                public void onClick(DialogInterface dialog, int which) { 
				                        // TODO Auto-generated method stub  
				                    } 
				                }).
				                create(); 
				        alertDialog.show(); 
				        return;
				}
				if(isFileOpen)
				{
					Dialog alertDialog = new AlertDialog.Builder(this). 
			                setTitle(R.string.warning). 
			                setMessage(R.string.warn_savedata). 
			                setIcon(R.drawable.ic_launcher). 
			                setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { 			                     
			                public void onClick(DialogInterface dialog, int which) { 
			                        // TODO Auto-generated method stub  
			                    } 
			                }).
			                create(); 
			        alertDialog.show(); 
			        return;
				}
				mmainset.isRecData=false;
				btnsave.setText(R.string.start);
				binhao.setText(mmainset.mperinfomation.binlinao);
				name.setText(mmainset.mperinfomation.name);
				sexText.setText(mmainset.mperinfomation.sex);
				//if(!DEBUG)
    			{
        			{
        				mmainset.CSI.clear();
        				mmainset.EEG.clear();
        				mmainset.BS.clear();
        				mmainset.EMG.clear();
        				mmainset.SQI.clear();
        				mmainset.BLACK.clear();
        				mmainset.WHITE.clear();
        			}	
    			}
				isStart=true;
			}
		}
		else if(v==btnwarning)
		{
		//	mSetItem=7;
			if(mmainset.warnoff==0)
			{
				mmainset.warnoff=1;
			//	Settings.System.putInt(getContentResolver(), "warnoff",1);
				btnwarning.setText(R.string.warnoff);	
				baojingxiaoshi();
				connectxiaoshi();
			}
			else
			{
				mmainset.warnoff=0;
				isConnectWarn=false;
				isbaojing=false;
			//	Settings.System.putInt(getContentResolver(), "warnoff",0);
				btnwarning.setText(R.string.warnon);		
			}
		}
		else if(v==playbtn)
		{
			if(mSetItem==2)
			{
				if(mmytabView.getSelectId()==0)
				{
				//	imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,
				//			     InputMethodManager.HIDE_NOT_ALWAYS);
					if(mgetDataTask!=null){
				        mgetDataTask.cancel(true);
				        mgetDataTask = null;
				    }
					imm.hideSoftInputFromWindow(mmytabView.getApplicationWindowToken(),0);
//					if(mmainset.isRecData)
//					{
//						mmainset.isRecData=false;
//						handler.sendEmptyMessage(1);
//						mmainset.msgindex = 0;
//						return;
//					}
//					else
					{
						index=0;
						mmainset.isRecData=false;
						handler.sendEmptyMessage(1);
						mmainset.msgindex = 0;
						//mmainset.isdongjie=true;
						handler.sendEmptyMessageDelayed(2,800);
					}
				}
				else if(mmytabView.getSelectId()==1)
				{
//					if(mmainset.isRecData)
//					{
//						mmainset.isRecData=false;
//						return;
//					}
					if(!mwaverecord.isSetDispTime())
					{
						//handler.sendEmptyMessage(2);
						return;
					}
					if(isStart)
					{
						isStart=true;
					}
					mmainset.isStartRecord=false;
					isnamerecord=false;
					mmainset.msgindex=6;
					mmainset.isRecData=true;
					
					mmenuLayout.setVisibility(View.GONE);
					mmytabView.clearHost();
					setLayoutVisible(null);
					mSetItem=0;
					progress = ProgressDialog.show(Csmmain.this, "Working...", this.getText(R.string.warn_loaddata), true);
					handler.postDelayed(new mgetRecData(), 300);
					//progress.dismiss();	
				}
				else if(mmytabView.getSelectId()==2)
				{
					
				}
				else
				{
					
				}
			} else if (mSetItem==3){
				index=0;
				//mmainset.isdongjie=true;
				handler.sendEmptyMessage(1);
			} else if (mSetItem==4){
				if(mmytabView.getSelectId()==2)
				{
					if(!mwaverecord.isSetDispTime())
					{
						//handler.sendEmptyMessage(2);
						return;
					}
					if(isStart)
					{
						isStart=true;
					}
					mmainset.isRecData=true;
					imm.hideSoftInputFromWindow(mmytabView.getApplicationWindowToken(),0);
//					mmenuLayout.setVisibility(View.GONE);
//					mmytabView.clearHost();
//					setLayoutVisible(null);
					mSetItem=0;
					handler.sendEmptyMessage(1);
					progress = ProgressDialog.show(Csmmain.this, "Working...", this.getText(R.string.warn_loaddata), true);
					handler.postDelayed(new mgetRecData(), 300);
				}
				else {
					index=0;
					//mmainset.isdongjie=true;
					handler.sendEmptyMessage(1);
				}
	
			}else{
				handler.sendEmptyMessage(1);
			}
			
		//	delbtn.setVisibility(View.GONE);
			
			
//			else if(mSetItem==6)
//			{
//				mmainset.isdongjie=true;
//				dongjieText.setText(R.string.dongjie);
//				mmainset.dongjielen=mmainset.CSI.size();
//				handler.sendEmptyMessage(1);				
//			}
		}	
		else if(v.equals(delbtn))
		{
			//Log.i(TAG,"liuliangxiang strFile = "+ mwaverecord.strFile);
			if(mwaverecord.strFile!=null)
			{
				final File file=new File(mwaverecord.strFile);
				//Log.i(TAG,"liuliangxiang mwaverecord.strFile="+mwaverecord.strFile);
				if(file.exists())
				{
					//Log.i(TAG,"liuliangxiang file = "+ file.toString());
					//Log.i(TAG,"liuliangxiang file.exists()");
					String fileString = file.toString();//.substring(start)
					String filePrint=fileString.substring(0, fileString.length()-4)+"print.txt";
					final File printFile=new File(filePrint);
					//Log.i(TAG,"liuliangxiang fileprint"+filePrint);
					AlertDialog dialog = new AlertDialog.Builder(Csmmain.this).setIcon(null).setTitle(R.string.warning).setMessage(R.string.warn_deletedata).setPositiveButton("YES", new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface arg0, int arg1) {
						           file.delete();
						           if(printFile.exists()){
						        	   printFile.delete();
						           }
						           
						    }

						// 閿熸枻鎷烽敓缁炵櫢鎷烽敓绲猠tPositiveButton閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鍙敓鏂ゆ嫹绀轰竴閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓瑙掗潻鎷烽敓瑙掑府鎷烽敓渚ユ唻鎷烽敓鏂ゆ嫹閿熸枻鎷�
						}).setNegativeButton("NO", new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface arg0, int arg1) {                   

						    }

						}).create();

						//閿熸枻鎷风ず閿熺殕浼欐嫹閿熸枻鎷蜂篃閿熸枻鎷烽敓鏂ゆ嫹浣块敓鏂ゆ嫹showDialog閿熸枻鎷穒nt id閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹绀洪敓鐨嗕紮鎷烽敓鏂ゆ嫹

						dialog.show();


					//file.delete();
				}
				handler.sendEmptyMessage(1);
				//setLayoutVisible(baojingsezLayout);
				//mmenuLayout.setVisibility(View.GONE);
				//mmytabView.clearHost();
				//setLayoutVisible(null);
			}
			
		}
		else
		{
			handler.sendEmptyMessage(1);
		}
	}
	
	//ArrayList<String> nameList=new ArrayList<String>();
	//ArrayList<String> noList=new ArrayList<String>();
	public class FileComparator implements Comparator<File> {  
        public int compare(File file1, File file2) {  
            if(file1.lastModified()< file2.lastModified())  
            {  
                return 1;
            }else  
            {  
                return -1;  
            }  
        }  
    } 
	public void  getNames()
	{
		//nameList.clear();
		//noList.clear();
		//ArrayList<String> name=new ArrayList<String>();
		mwaverecord.recordnoname.clear();
		File file=new File(Environment.getExternalStorageDirectory().toString()+"/csmdata");
		//Log.i(TAG,"liuliangxiang listfile"+file.toString());
		if(file.isDirectory())
		{
			File listfile[]=file.listFiles();
			ArrayList<File> getfile = new ArrayList<File>();
			for(int i=0;i<listfile.length;i++){
				getfile.add(listfile[i]);
			}
			
			//Collections.sort(getfile, new FileComparator());
			//Log.i(TAG,"liuliangxiang listfile)"+listfile.length);
			for(int i=0;i<getfile.size();i++)
			{
				//Log.i(TAG,"liuliangxiang listfile[i].getName()"+listfile[i].getName());
				File nameFile = getfile.get(i);
				if((nameFile.isFile())&&(nameFile.getName().startsWith("csmdataN")))
				{
					String strname=nameFile.getName().substring(nameFile.getName().lastIndexOf("N")+2, nameFile.getName().lastIndexOf(")"));		
					String strno=nameFile.getName().substring(8, nameFile.getName().lastIndexOf("N"));
					String strnoname = strno+"--"+strname;
					if(!(mwaverecord.recordnoname.contains(strnoname))){
						mwaverecord.recordnoname.add(strnoname);
					}
				}
			}
			
		}
		//return name;
	}
	
//	public ArrayList<String> getNo()
//	{
//		ArrayList<String> name=new ArrayList<String>();
//		File file=new File(Environment.getExternalStorageDirectory().toString()+"/csmdata");
//		if(file.isDirectory())
//		{
//			File listfile[]=file.listFiles();
//			for(int i=0;i<listfile.length;i++)
//			{
//			//	Log.i(TAG,"liuliangxiang listfile[i].getName()"+listfile[i].getName());
//				if((listfile[i].isFile())&&(listfile[i].getName().startsWith("csmdata")))
//				{
//				//	Log.i(TAG,"liuliangxiang listfile[i].getName()"+listfile[i].getName());
//					String str=listfile[i].getName().substring(8, listfile[i].getName().lastIndexOf("N"));
//					if((name.size()==0))
//					{
//						name.add(str);	
//					}
//					else
//					{
//						if(!(name.get(name.size()-1).equals(str)))
//						{
//							name.add(str);
//						}
//					}
//				//	Log.i(TAG,"liuliangxiang str="+str);
//				}
//			}
//			
//		}
//		return name;
//	}
	
	public boolean Checkinformation()
	{
		boolean isNameOk=false;
		boolean isNoOk=false;
		if((mmainset.mperinfomation.name!=null)&&(!((mmainset.mperinfomation.name).trim()).equals("")))
		{
			isNameOk = true;
		}else{
			name.setText("");
		}
		if((!((mmainset.mperinfomation.binlinao).trim()).equals(""))&&(mmainset.mperinfomation.binlinao!=null))
		{
			isNoOk = true;
		}else{
			binhao.setText("");
		}
		return (isNameOk&&isNoOk);
	}
	public class mgetMessageRunnable implements Runnable
	{

		public void run() {
			// TODO Auto-generated method stub
			//ArrayList al;
			//Log.i(TAG,"liuliangxiang mgetMessageRunnable");
			getNames();
			mwaverecord.setListName(mwaverecord.recordnoname);
			//mwaverecord.setListNo(noList);
		}	
	}
	private getDataTask mgetDataTask;
	public class mgetRecData implements Runnable
	{

		public void run() {
			// TODO Auto-generated method stub	
//			mmainset.isStartRecord = false;
//			if(isPrint) {
//                isPrint = false;
//                if(mgetDataTask!=null){
//			        mgetDataTask.cancel(true);
//			        mgetDataTask = null;
//			    }
//                mwaverecord.getPrintData();
//			progress.dismiss();	
//			//handler.sendEmptyMessage(1);
////			Log.i(TAG,"liuliangxiang mmainset.RecName="+mmainset.RecName);
////			Log.i(TAG,"liuliangxiang mmainset.RecNO="+mmainset.RecNO);
////			Log.i(TAG,"liuliangxiang mmainset.RecSex="+mmainset.RecSex);
//			name.setText(mmainset.RecName);
//			binhao.setText(mmainset.RecNO);
//			sexText.setText(mmainset.RecSex);
//                handler.post(new mstartPrint());
//                mmainset.isFilePrint = true;
//				
//			}else{
//			    if(mgetDataTask!=null){
//			        mgetDataTask.cancel(true);
//			        mgetDataTask = null;
//			    }
//			    mgetDataTask = new getDataTask();
//			    mgetDataTask.execute("");
//                progress.dismiss();	
//                //handler.sendEmptyMessage(1);
////                Log.i(TAG,"liuliangxiang mmainset.RecName="+mmainset.RecName);
////                Log.i(TAG,"liuliangxiang mmainset.RecNO="+mmainset.RecNO);
////                Log.i(TAG,"liuliangxiang mmainset.RecSex="+mmainset.RecSex);
//			}
			 
			 if(mgetDataTask!=null){
			        mgetDataTask.cancel(true);
			        mgetDataTask = null;
			    }
			 mgetDataTask = new getDataTask();
			 mgetDataTask.execute("");
			 if(!isPrint){
				 if(progress.isShowing())
					 progress.dismiss();
			 }
			
		}
		
	}
   public class getDataTask extends AsyncTask<String, Void,String> {
       protected String doInBackground(String... params)  {
            try {
            	if(isPrint)
            		mwaverecord.getPrintData();
            	else{
					mmainset.isStartRecord = false;
            		mwaverecord.getRecData();
            	}
            } catch (Exception e) {
            }
            return null;
        }
        protected void onPostExecute(String result) {
        	name.setText(mmainset.RecName);
	        binhao.setText(mmainset.RecNO);
	        sexText.setText(mmainset.RecSex);
        	if(isPrint){
        		progress.dismiss();
        		isPrint = false;
        		mmainset.isFilePrint = true;
        		handler.post(new mstartPrint());
                
        	}else{
        		handler.sendEmptyMessage(19);
        	}
        	
            //Toast.makeText(Csmmain.this, R.string.getrecfinish, Toast.LENGTH_LONG);
        }
	}
	public class mstartPrint implements Runnable
	{
		public void run() {
			index=0;
			//mmainset.isdongjie=true;
			handler.sendEmptyMessage(1);
			mSetItem=0;
			Bitmap bmp=mmainset.genBitmap();
		//	startActivity(
		//            new Intent(Intent.ACTION_MAIN).setClassName(Csmmain.this,
		//            		"com.example.csm.MainActivity").putExtra("key", bmp));
			
			Intent intent = new Intent(Csmmain.this,
		    		MainActivity.class);
			try {
				saveMyBitmap("55",bmp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Csmmain.this.startActivity(intent);
		}
		
	}


	//intent.putExtra("key",bmp);
    
	public class mSaveRunnable implements Runnable
	{
		public void run() {
			// TODO Auto-generated method stub
	//		Log.i(TAG,"liuliangxiang Runnable");
//			if(mmainset.CSI.size()==0)
//        	{
//				mmainset.isStartRecord=false;
//				isnamerecord=false;
//				mmainset.msgindex=0;
//        		return;
//        	}
			synchronized (Application.mLock){
			if((!mmainset.isStartRecord) || (!isReady))
				return;
			String sdcardState = Environment.getExternalStorageState();
	        if(sdcardState.equals(Environment.MEDIA_MOUNTED))
	        {
	        	if(saveDataLen>=(mmainset.CSI.size()-1))
	        	{
	        		//Toast.makeText(context, R.string.saveOver, Toast.LENGTH_LONG).show();
	        		return;
	        	}
	        	if(getAvailaleSize()<(1024*1024)*4)
	        	{
	        		//Toast.makeText(context, R.string.sdfull, Toast.LENGTH_LONG).show();
	        		mmainset.isStartRecord=false;
	        		isnamerecord=false;
					mmainset.msgindex=2;
	        		return;
	        	}
	        	if(!isFileOpen)
            	{
//					mon=Calendar.getInstance().get(Calendar.MONTH);
//				    year=Calendar.getInstance().get(Calendar.YEAR);
//				    day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//				    hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//				    min=Calendar.getInstance().get(Calendar.MINUTE);
//				    sec=Calendar.getInstance().get(Calendar.SECOND);
				    String str=	"N"+mmainset.mperinfomation.binlinao+"N"+"("+mmainset.mperinfomation.name+")"+Integer.toString(mmainset.year)+"-"+Integer.toString(mmainset.mon)+"-"+Integer.toString(mmainset.day)+"-"+Integer.toString(mmainset.hour)+"-"+Integer.toString(mmainset.min)+"-"+Integer.toString(mmainset.sec);  
            		//File file=createSaveFile(Environment.getExternalStorageDirectory().toString(),"csmdata-"+formatdate(nowtime+saveDataLen*1000)+".txt");
	        		File file;
	        		File filePrint;
	        		if(saveDataLen == 0){
	        			file=createSaveFile(Environment.getExternalStorageDirectory().toString()+"/csmdata","csmdata"+str+"-l"+Integer.toString(saveDataLen)+"l"+".txt");
	        			filePrint=createSaveFile(Environment.getExternalStorageDirectory().toString()+"/csmdata","csmdata"+str+"-l"+Integer.toString(saveDataLen)+"l"+"print.txt");
	        		}
	        		else {
	        			file=createSaveFile(Environment.getExternalStorageDirectory().toString()+"/csmdata","csmdata"+str+"-l"+Integer.toString(saveDataLen-1)+"l"+".txt");
	        			filePrint=createSaveFile(Environment.getExternalStorageDirectory().toString()+"/csmdata","csmdata"+str+"-l"+Integer.toString(saveDataLen-1)+"l"+"print.txt");
	        		}
	        	//	Log.i(TAG,"liuliangxiang file="+file.getName());
                	try {
                		if(!isnamerecord){
                			out=new OutputStreamWriter(new FileOutputStream(file,false));
                			outPrint = new OutputStreamWriter(new FileOutputStream(filePrint,false));
                		}
                		else{
                			out=new OutputStreamWriter(new FileOutputStream(file,true));
                			outPrint = new OutputStreamWriter(new FileOutputStream(filePrint,true));
                		}
        				isFileOpen=true;
        			} catch (FileNotFoundException e) {
        				// TODO Auto-generated catch block
        				isFileOpen=false;    				
        				e.printStackTrace();
        			}
                	
                	int temp=saveDataLen;
                	int temp1=mmainset.CSI.size();
                	//int temp1=((temp+10)<mmainset.CSI.size())?(temp+10):mmainset.CSI.size();
        			for(int i=temp;i<temp1;i++)
        			{
        				//out.write(mmainset.perinfomation.name);
        				try {										
        					//if((i==(mmainset.mperinfomation.indexname-1))||(i==temp))
        					//if((i==(mmainset.mperinfomation.indexname-1))||(i==temp))
        					//{
        					    if((out==null)||(outPrint==null))
        					    	return;
        						if(!isnamerecord)
        						{
        							isnamerecord=true;
	        						out.write("Name:");
	        						out.write(mmainset.mperinfomation.name+" ");
	        						out.write("Sex:");
	        						out.write(mmainset.mperinfomation.sex+" ");
	        						out.write("PNo:");
	        						out.write(mmainset.mperinfomation.binlinao+" ");
	        						out.write("\n");	
	        						
	        						outPrint.write("Name:");
	        						outPrint.write(mmainset.mperinfomation.name+" ");
	        						outPrint.write("Sex:");
	        						outPrint.write(mmainset.mperinfomation.sex+" ");
	        						outPrint.write("PNo:");
	        						outPrint.write(mmainset.mperinfomation.binlinao+" ");
	        						outPrint.write("\n");
        						}
        					//}
        					out.write("NO"+Integer.toString(i)+". ");
        					
        					out.write("THING:");
        					
        					if((mmainset.CSI.get(i)>>8)==0x0)
        					{
        						out.write("诱导");
        					}
        					else if((mmainset.CSI.get(i)>>8)==0x1)
        					{
        						out.write("插管 ");
        					}
        					if((mmainset.CSI.get(i)>>8)==0x2)
        					{
        						out.write("维持");
        					}
        					if((mmainset.CSI.get(i)>>8)==0x3)
        					{
        						out.write("外科 ");
        					}
        					if((mmainset.CSI.get(i)>>8)==0x4)
        					{
        						out.write("注射 ");
        					}
        					if((mmainset.CSI.get(i)>>8)==0x5)
        					{
        						out.write("备注");
        					}
        					if((mmainset.CSI.get(i)>>8)==0x6)
        					{
        						out.write("维持结束");
        					}
        					if((mmainset.CSI.get(i)>>8)==0x7)
        					{
        						out.write("移动");
        					}
        					if((mmainset.CSI.get(i)>>8)==0x8)
        					{
        						out.write(" ");
        					}
        					out.write("CSI:");
        					out.write(Integer.toString((mmainset.CSI.get(i))&0xff)+" ");			
        					out.write("BS:");
        					out.write(Integer.toString(mmainset.BS.get(i)&0xff)+" ");
        					out.write("EMG:");       					
        					out.write(Integer.toString(mmainset.EMG.get(i)&0xff)+" ");
        					out.write("SQI:");
        					out.write(Integer.toString(mmainset.SQI.get(i)&0xff)+" ");
        					out.write("BLACK:");
        					out.write(Integer.toString(mmainset.BLACK.get(i)&0xff)+" ");
        					out.write("WHITE:");
        					out.write(Integer.toString(mmainset.WHITE.get(i)&0xff)+" ");	
        					out.write("EEG:");
        					Integer[] eegdata=new Integer[100];
        					eegdata=mmainset.EEG.get(i);
        					for(int j=0;j<100;j++)
        					{
        						out.write(Integer.toString(eegdata[j])+":");
        					}
        					out.write("\n");
        					saveDataLen=i;
        					
        					
        					if(((i%30)==0)&&(i>1)){
        					    outPrint.write("NO"+Integer.toString(i)+". ");
                                
        					    outPrint.write("THING:");
                                
        					    if((mmainset.CSI.get(i)>>8)==0x0)
            					{
        					    	outPrint.write("诱导");
            					}
            					if((mmainset.CSI.get(i)>>8)==0x1)
            					{
            						outPrint.write("插管 ");
            					}
            					if((mmainset.CSI.get(i)>>8)==0x2)
            					{
            						outPrint.write("维持");
            					}
            					if((mmainset.CSI.get(i)>>8)==0x3)
            					{
            						outPrint.write("外科 ");
            					}
            					if((mmainset.CSI.get(i)>>8)==0x4)
            					{
            						outPrint.write("注射 ");
            					}
            					if((mmainset.CSI.get(i)>>8)==0x5)
            					{
            						outPrint.write("备注");
            					}
            					if((mmainset.CSI.get(i)>>8)==0x6)
            					{
            						outPrint.write("维持结束");
            					}
            					if((mmainset.CSI.get(i)>>8)==0x7)
            					{
            						outPrint.write("移动");
            					}
            					if((mmainset.CSI.get(i)>>8)==0x8)
            					{
            						outPrint.write(" ");
            					}
                                outPrint.write("CSI:");
                                outPrint.write(Integer.toString(mmainset.CSI.get(i)&0xff)+" ");
                                
                                outPrint.write("BS:");
                                outPrint.write(Integer.toString(mmainset.BS.get(i)&0xff)+" ");
                                outPrint.write("EMG:");                          
                                outPrint.write(Integer.toString(mmainset.EMG.get(i)&0xff)+" ");
                                outPrint.write("SQI:");
                                outPrint.write(Integer.toString(mmainset.SQI.get(i)&0xff)+" ");
                                outPrint.write("BLACK:");
                                outPrint.write(Integer.toString(mmainset.BLACK.get(i)&0xff)+" ");
                                outPrint.write("WHITE:");
                                outPrint.write(Integer.toString(mmainset.WHITE.get(i)&0xff)+" ");    
                                outPrint.write("EEG:");
                                Integer[] eegdata1=new Integer[100];
                                eegdata1=mmainset.EEG.get(i);
                                for(int j=0;j<100;j++)
                                {
                                    outPrint.write(Integer.toString(eegdata1[j])+":");
                                }
                                outPrint.write("\n");
        					}
        					
        				} catch (IOException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        			}
        		
        			//saveDataLen=mmainset.CSI.size()-1;	
        			try {
        				isFileOpen=false;
        				out.flush();
        				out.close();
        				outPrint.flush();
        				outPrint.close();
        				//Log.i(TAG,"liuliangxiang saveDataLen="+saveDataLen);
        				file.renameTo(new File(Environment.getExternalStorageDirectory().toString()+"/csmdata"+"/csmdata"+str+"-l"+Integer.toString(saveDataLen)+"l"+".txt"));
        				filePrint.renameTo(new File(Environment.getExternalStorageDirectory().toString()+"/csmdata"+"/csmdata"+str+"-l"+Integer.toString(saveDataLen)+"l"+"print.txt"));
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
					/*
        			if(!DEBUG)
        			{
	        			if((mmainset.isWarnConnected)||(!isStart)||(!mmainset.isConnected))
	        			{
	        				mmainset.RecCSI.clear();
	        				mmainset.RecEEG.clear();
	        				mmainset.RecBS.clear();
	        				mmainset.RecEMG.clear();
	        				mmainset.RecSQI.clear();
	        				mmainset.RecBLACK.clear();
	        				mmainset.RecWHITE.clear();
	        			}	
        			}*/
        			
            	}
	        }else
	        {
	        	//Toast.makeText(context, R.string.sdunmount, Toast.LENGTH_LONG).show();
	        	//isReadySaved=false;
	        	mmainset.isStartRecord=false;
	        	isnamerecord=false;
	        	isFileOpen=false;
	        	mmainset.msgindex=1;
	        }	
	        saveDataLen=saveDataLen+1;
	//		Log.i(TAG,"liuliangxiang saveDataLen="+saveDataLen);	 
		} 		
		}
		
	}
	
//	public boolean haveALPUM() {
//
//		FileInputStream fis = null;
//		FileOutputStream fos = null;
//		File file = new File("/dev/taisky_ctrl");
//		byte rbuf[] = new byte[2];
//
//		int hour = 0;// sharedPreferences.getInt("hour", 0);
//		int minute = 0;//sharedPreferences.getInt("minute", 0);
//
//  
//    // int year = ca.get(Calendar.ZONE_OFFSET);//閿熸枻鎷峰彇閿熸枻鎷烽敓锟�	Time time = new Time();
//		time.setToNow();
//		Time ntime = new Time("GMT+0");
//		ntime.setToNow();
//		int nhour = hour - (time.hour - ntime.hour);
//		if(nhour > 23 ){
//			nhour =  nhour - 24;
//		}
//		Log.d("","lxy nhour:" + nhour);
//		// String s = hour+minute;
//		if (file.exists()) {
//
//			try {
//
//				fis = new FileInputStream(file);
//				fos = new FileOutputStream(file);
//
//				// wbuf[0] = hour.getBytes();
//				// wbuf[1] = minute.getBytes();
//
//				byte wbuf[] = { (byte) nhour, (byte) minute };
//				Log.d("", "eboda       hour" + wbuf[0] + "minute" + wbuf[1]);
//				fos.write(wbuf);
//
//				fos.flush();
//				int result = fis.read(rbuf);
//				Log.v("aurto", "** eboda twp ->result= " + result);
//				if (result > 0) {
//
//					return true;
//
//				}
//
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//
//				try {
//					if (fis != null) {
//						fis.close();
//						fos.close();
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return false;
//
//	}
	
	
	public void writeWarn(byte var0,byte var1)
	{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		File file = new File("/dev/taisky_ctrl");
		byte rbuf[] = new byte[2];
		// String s = hour+minute;
		if (file.exists()) {
			try {
				fos = new FileOutputStream(file);

				// wbuf[0] = hour.getBytes();
				// wbuf[1] = minute.getBytes();

				byte wbuf[] = { (byte) var0, (byte) var1 };
				fos.write(wbuf);
				fos.flush();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {		
						fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public long getAvailaleSize(){
		File path = Environment.getExternalStorageDirectory(); //鍙栭敓鏂ゆ嫹sdcard閿熶茎纭锋嫹璺敓鏂ゆ嫹
		StatFs stat = new StatFs(path.getPath()); 
		long blockSize = stat.getBlockSize(); 
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}
	private int tick;
	private int delaytime;
	public void onTick() {
		// TODO Auto-generated method stub	
		//boolean isOpen=imm.isFullscreenMode();
		
		//Log.i(TAG,"liuliangxiang isOpen="+isOpen);
//		if(isOpen) {
//				Settings.System.putInt(Csmmain.this.getContentResolver(), "csmmain", 0);
//		} else {
//				Settings.System.putInt(Csmmain.this.getContentResolver(), "csmmain", 1);
//		}
		handler.post(new Runnable()
		{
			public void run()
			{
		scaletime=(SystemClock.elapsedRealtime()-nowtime)/1000;
		
	//	Log.i(TAG,"liuliangxiang timescale ="+scaletime);
		String useTime=formatdate(scaletime);
//		mmainset.useTime=useTime;
		timeScale.setText(useTime);	
		{
			mmainset.timeOut++;
		//	Log.i(TAG,"liuliangxiang mmainset.timeOut "+mmainset.timeOut);
		//	if(mmainset.timeOut>=12)
			{
//				mmainset.timeOut=12;
//				mmainset.isConnected=false;
//				mmainset.isStartRecord=false;
//				isnamerecord=false;
//				mmainset.msgindex=0;
//				mmainset.CSI.clear();
//				mmainset.EEG.clear();
//				mmainset.BLACK.clear();
//				mmainset.BS.clear();
//				mmainset.WHITE.clear();
//				mmainset.EMG.clear();
//				mmainset.DataReceived.clear();
				
				//Log.i(TAG,"liuliangxiang mmainset.isConnected="+mmainset.isConnected);
				
			}
			//else
			//{
			//	mmainset.isConnected=true;
			//}
			
	/*		if(connectnum>600)
			{
				
			//	mmainset.isConnected=false;
				mmainset.isStartRecord=false;
				isnamerecord=false;
				mmainset.msgindex=3;
				if(mmainset.isConnected)
				{
					mmainset.isWarnConnected=true;
					mmainset.DataReceived.clear();
				}
				//Log.i(TAG,"liuliangxiang connectnum"+connectnum);
			}
			else */
			{
//				if(mmainset.isWarnConnected)
				{
//					mmainset.CSI.clear();
//					mmainset.EEG.clear();
//					mmainset.BLACK.clear();
//					mmainset.BS.clear();
//					mmainset.WHITE.clear();
//					mmainset.EMG.clear();
				}
//				mmainset.isWarnConnected=false;
				//mmainset.isConnected=true;
			}
			tick++;
			delaytime++;
			if(delaytime<20)
			{
				return;
			}
			else
			{
				delaytime=20;
			}
			if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			{
				if(tick>20)
				{
					tick=0;
					if(mmainset.isStartRecord)
					{
						if(!isFileOpen)
							handler.post(new mSaveRunnable());
					}
				}
				//stopTick2();
				handler.sendEmptyMessage(7);
				//Log.i(TAG,"liuliangxiang mmainset.isConnected"+mmainset.isConnected);
			}
			else
			{
				//startTick2(tick);
				handler.sendEmptyMessage(8);
				//Log.i(TAG,"liuliangxiang mmainset.isConnected"+mmainset.isConnected);
			}
		//	Log.i(TAG,"liuliangxiang curCSI="+curCSI);
		//	Log.i(TAG,"liuliangxiang getshangxianshezValue="+mmainset.getshangxianshezValue());
			if(curCSI>mmainset.getshangxianshezValue()||curCSI<mmainset.getxiaxianshezValue())
			{
				//startTick1(tick);
				handler.sendEmptyMessage(5);
			}
			else
			{
				//stopTick1();
				handler.sendEmptyMessage(6);
			}
			
			//Log.i(TAG,"liuliangxiang mmainset.timeOut="+mmainset.timeOut);
			/*
			if(!DEBUG)
			{
				if(connectnum>500)
				{
					mmainset.isWarnConnected=true;
					if(!istimeoutSave)
					{
						handler.post(new mSaveRunnable());
						istimeoutSave=true;
					}
					isStart=false;
				}
				else
				{
					mmainset.isWarnConnected=false;
				}
			}
			
			if(((mmainset.EEG.size()-saveDataLen)>=600))
			{
				if(!istimeoutSave)
				{
					handler.post(new mSaveRunnable());
					istimeoutSave=true;
				}
			}
			if(mmainset.timeOut>=5)
			{
				mmainset.isConnected=false;
				if(!istimeoutSave)
				{
					handler.post(new mSaveRunnable());
					istimeoutSave=true;
				}
				
			}*/
			//runWarnView();
			
//			boolean isOpen=imm.isActive();
//			if(isOpen) {
//				if(!isSetOpen) {
//					isSetOpen = true;
//					Settings.System.putInt(Csmmain.this.getContentResolver(), "csmmain", 0);
//				}
//			} else {
//				if(isSetOpen) {
//					isSetOpen = false;
//					Settings.System.putInt(Csmmain.this.getContentResolver(), "csmmain", 1);
//				}
//			}
		}}});
	}
	boolean isSetOpen=false;
	boolean isTick1;
	boolean isTick2;
	private void startTick1(int tick)
	{
		if(!isFirstConnect) {
			return;
		}
		if(mmainset.warnoff==0)
		{
			if((tick%4)==0)
			{
				warningView.setImageResource(R.drawable.redstar);
				//audioManager.playSoundEffect(AudioManager.ADJUST_LOWER);
				
			}
			else
			{
				warningView.setImageResource(R.drawable.greenstar);
			}
			if(isTick2)
				return;
			if(!isTick1)
			{
				isTick1=true;
				//writeWarn((byte)1,(byte)1);
				handler.sendEmptyMessageDelayed(3, 50);
			}
			if((tick%25)==0)
				playSound(R.raw.alarm_beep_01);
		}
		else
		{
			warningView.setImageResource(R.drawable.redstar);
			if(isTick1)
			{
				isTick1=false;
				handler.sendEmptyMessageDelayed(4, 50);
				//writeWarn((byte)1,(byte)0);
			}
		}
		
		
	}
	private void stopTick1()
	{
		if(mmainset.warnoff==1)
			warningView.setImageResource(R.drawable.greenstar);
		if(isTick2)
			return;
		if(isTick1)
		{
			isTick1=false;
			handler.sendEmptyMessageDelayed(4, 20);
		}
		
	}
	
	private void startTick2(int tick)
	{
		//Log.i(TAG,"liualingxiang tick="+tick);
		
		connectView.setImageResource(R.drawable.redstar);
		if(mmainset.warnoff==0)
		{
			if(!isTick2)
			{
				isTick2=true;
				//writeWarn((byte)1,(byte)1);
				handler.sendEmptyMessageDelayed(3,20);
			}
		}
		else
		{
			if(isTick2)
			{
				isTick2=false;
				//writeWarn((byte)0,(byte)0);
				handler.sendEmptyMessageDelayed(4,20);
			}
		}
		if(isTick2)
		{
			//audioManager.playSoundEffect(AudioManager.D);
			if((tick%10)==0)
				playSound(R.raw.alarm_beep_01);
			
		}
		
	}
	private void stopTick2()
	{
		connectView.setImageResource(R.drawable.greenstar);
		if(isTick2)
		{
			isTick2=false;
			handler.sendEmptyMessageDelayed(4,20);
			//writeWarn((byte)0,(byte)0);
		}
	}
	
	MediaPlayer m;
	public void initSound(int sound)
	{
		if(m!=null)
    	{
	    	 m.reset();//閿熻闈╂嫹閿熸枻鎷锋湭閿熸枻鎷峰閿熸枻鎷烽敓鏂ゆ嫹鐘舵�
	    	 m.release();
    	}
    	
         m=MediaPlayer.create(this,sound);//閿熸枻鎷峰彇閿熸枻鎷烽
     //    m.setOnCompletionListener(this);

         m.setAudioStreamType(AudioManager.STREAM_MUSIC);
         try {
			m.prepare();
			//m.seekTo(0);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	   public void playSound(int sound)
	    {
//	    	if(m!=null)
//	    	{
//		    	 m.reset();//閿熻闈╂嫹閿熸枻鎷锋湭閿熸枻鎷峰閿熸枻鎷烽敓鏂ゆ嫹鐘舵�
//		    	 m.release();
//	    	}
//	    	
//	         m=MediaPlayer.create(this,sound);//閿熸枻鎷峰彇閿熸枻鎷烽
//	     //    m.setOnCompletionListener(this);
//
//	         m.setAudioStreamType(AudioManager.STREAM_MUSIC);
//	         try {
//				m.prepare();
//				//m.seekTo(0);
//			} catch (IllegalStateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	         m.start();

	    }
	
	
	public String formatdate(long time)
	{
		int hour=(int)(scaletime/3600);
		int minute=(int)(scaletime%3600/60);
		int second=(int)(scaletime%60);
		String strhour=(hour<10)?String.format("0%d:", hour):String.format("%d:", hour);
		String strminute=(minute<10)?String.format("0%d:", minute):String.format("%d:", minute);
		String strsecond=(second<10)?String.format("0%d", second):String.format("%d", second);
		return strhour+strminute+strsecond;
	}
	
	boolean isPrint =false;

	public void onTabClick() {
		// TODO Auto-generated method stub
		if(mmytabView.getSelectId()==0)
		{
			if(mSetItem==2)
			{
				//setLayoutVisible(mjsdLayout);
				setLayoutVisible(bingrenziliaoLayout);
			}
			else if(mSetItem==3)
			{
				setLayoutVisible(baojingsezLayout);
			}
			else if(mSetItem==4)
			{
				setLayoutVisible(showSettingLayout);
			}
		//	else if(mSetItem==6)
		//	{
		//		setLayoutVisible(mjsdLayout);
		//	}
			
		}
		else if(mmytabView.getSelectId()==1)
		{
			if(mSetItem==2)
			{
				//setLayoutVisible(mjsdLayout);
				//getNames();
				/*
				if(isStart)
				{
					Dialog alertDialog = new AlertDialog.Builder(this). 
			                setTitle("閿熸枻鎷疯閿熸枻鎷风ず"). 
			                setMessage(R.string.recwarning). 
			                setIcon(R.drawable.ic_launcher). 
			                setPositiveButton("纭敓鏂ゆ嫹", new DialogInterface.OnClickListener() { 			                     
			                public void onClick(DialogInterface dialog, int which) { 
			                        // TODO Auto-generated method stub  
			                	handler.sendEmptyMessage(1);
			                    } 
			                }).
			                create(); 
			        alertDialog.show(); 
			        return;
				}*/
				
//				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
//				{
//					setLayoutVisible(boxinghuiguLayout);
//					mmainset.isStartRecord=false;
//					isnamerecord=false;
//					mmainset.msgindex=6;
//					handler.post(new mgetMessageRunnable());
//					delbtn.setVisibility(View.VISIBLE);
//				}
//				else
//				{
//					 Dialog alertDialog = new AlertDialog.Builder(this). 
//				                setTitle("閿熸枻鎷疯閿熸枻鎷风ず"). 
//				                setMessage(R.string.sdunmount). 
//				                setIcon(R.drawable.ic_launcher). 
//				                setPositiveButton("纭敓鏂ゆ嫹", new DialogInterface.OnClickListener() { 			                     
//				                public void onClick(DialogInterface dialog, int which) { 
//				                        // TODO Auto-generated method stub  
//				                	handler.sendEmptyMessage(1);
//				                    } 
//				                }).
//				                create(); 
//				        alertDialog.show(); 
//				        return;
//				}
			}
			
			else if(mSetItem==3)
			{
				setLayoutVisible(shijianshezLayout);
			}
			else if(mSetItem==4)
			{
				//setLayoutVisible(showSettingLayout);
				/*
				if(isStart)
				{
					Dialog alertDialog = new AlertDialog.Builder(this). 
			                setTitle("閿熸枻鎷疯閿熸枻鎷风ず"). 
			                setMessage(R.string.binrenwarning). 
			                setIcon(R.drawable.ic_launcher). 
			                setPositiveButton("纭敓鏂ゆ嫹", new DialogInterface.OnClickListener() { 			                     
			                public void onClick(DialogInterface dialog, int which) { 
			                        // TODO Auto-generated method stub  
			                	handler.sendEmptyMessage(1);
			                    } 
			                }).
			                create(); 
			        alertDialog.show(); 
			        return;
				}*/
				setLayoutVisible(ruanjiangversionLayout);
			}
		}	
		else if(mmytabView.getSelectId()==2)
		{
//			if(mSetItem==2)
//			{
//					//setLayoutVisible(eegLayout);
//					//Intent intent1=new Intent(this, llkActivity.class);
////				mmenuLayout.setVisibility(View.GONE);
////				mmytabView.clearHost();
////				setLayoutVisible(null);
//				index=0;
//				//mmainset.isdongjie=true;
//				handler.sendEmptyMessage(1);
//				mSetItem=0;
//				Bitmap bmp=mmainset.genBitmap();
////				startActivity(
////			            new Intent(Intent.ACTION_MAIN).setClassName(Csmmain.this,
////			            		"com.example.csm.MainActivity").putExtra("key", bmp));
//				
//				Intent intent = new Intent(Csmmain.this,
//	            		MainActivity.class);
//				try {
//					saveMyBitmap("55",bmp);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//
//
//				//intent.putExtra("key",bmp);
//			    this.startActivity(intent);
//				
////				mmenuLayout.setVisibility(View.GONE);
////				mmytabView.clearHost();
////				setLayoutVisible(null);
////				mSetItem=0;
////				
////				
////				if(ListBluetoothDevice())
////				{
////					 mainLayout.setVisibility(View.GONE);
////					 printerlayout.setVisibility(View.VISIBLE);
////				}
//
//				
//			}
			if(mSetItem==3)
			{
				setLayoutVisible(mjsdLayout);
				//shijianshezbuiler.show();
			}
			if(mSetItem==4)
			{
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
				{
//					imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
//						     InputMethodManager.HIDE_NOT_ALWAYS);
					//Log.i(TAG, "liuliangxiang binxinghuigu");
					setLayoutVisible(boxinghuiguLayout);
				//	mmainset.isStartRecord=false;
				//	isnamerecord=false;
				//	mmainset.msgindex=6;
					handler.post(new mgetMessageRunnable());
					delbtn.setVisibility(View.VISIBLE);
				}
				else
				{
					 Dialog alertDialog = new AlertDialog.Builder(this). 
				                setTitle(R.string.warning). 
				                setMessage(R.string.sdunmount). 
				                setIcon(R.drawable.ic_launcher). 
				                setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { 			                     
				                public void onClick(DialogInterface dialog, int which) { 
				                        // TODO Auto-generated method stub  
				                	handler.sendEmptyMessage(1);
				                    } 
				                }).
				                create(); 
				        alertDialog.show(); 
				        return;
				}
				//setLayoutVisible(ruanjiangversionLayout);
				//shijianshezbuiler.show();
			}	
//			if(mSetItem==2)
//			{
//				setLayoutVisible(eegLayout);
//			}
		}
		else if(mmytabView.getSelectId()==3)
		{
			if(mSetItem==3)
			{
				//setLayoutVisible(eegLayout);
				//Intent intent1=new Intent(this, llkActivity.class);
				{
					startActivity(
			            	new Intent(Intent.ACTION_MAIN).setClassName(
			            		"com.android.settings", "com.android.settings.bluetooth.BluetoothSettings"));
					//mmenuLayout.setVisibility(View.GONE);
					//mmytabView.clearHost();
					//setLayoutVisible(null);
					handler.sendEmptyMessage(1);
				}
			}
			else if(mSetItem==4){
				{
						//setLayoutVisible(eegLayout);
						//Intent intent1=new Intent(this, llkActivity.class);
//					mmenuLayout.setVisibility(View.GONE);
//					mmytabView.clearHost();
//					setLayoutVisible(null);
					//if(!mmainset.isRecData) 
					Log.i(TAG,"liuliangxiangprint");
					if(true){
						if(!(mwaverecord.strFile.equals(""))) {
							mmainset.isRecData=true;
							
							//mmenuLayout.setVisibility(View.GONE);
							//mmytabView.clearHost();
							//setLayoutVisible(null);
							//mSetItem=0;
							handler.sendEmptyMessage(1);
							isPrint = true;
							progress = ProgressDialog.show(Csmmain.this, "Working...", this.getText(R.string.warn_loaddata), true);
							handler.postDelayed(new mgetRecData(), 300);
						}else {
							index=0;
							//mmainset.isdongjie=true;
							handler.sendEmptyMessage(1);
							mSetItem=0;
							Bitmap bmp=mmainset.genBitmap();
//							startActivity(
//						            new Intent(Intent.ACTION_MAIN).setClassName(Csmmain.this,
//						            		"com.example.csm.MainActivity").putExtra("key", bmp));
							
							Intent intent = new Intent(Csmmain.this,
				            		MainActivity.class);
							try {
								saveMyBitmap("55",bmp);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							


							//intent.putExtra("key",bmp);
						    this.startActivity(intent);
						}
						
					} else {
					index=0;
					//mmainset.isdongjie=true;
					handler.sendEmptyMessage(1);
					mSetItem=0;
					Bitmap bmp=mmainset.genBitmap();
//					startActivity(
//				            new Intent(Intent.ACTION_MAIN).setClassName(Csmmain.this,
//				            		"com.example.csm.MainActivity").putExtra("key", bmp));
					
					Intent intent = new Intent(Csmmain.this,
		            		MainActivity.class);
					try {
						saveMyBitmap("55",bmp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					


					//intent.putExtra("key",bmp);
				    this.startActivity(intent);
					}
					
//					mmenuLayout.setVisibility(View.GONE);
//					mmytabView.clearHost();
//					setLayoutVisible(null);
//					mSetItem=0;
//					
//					
//					if(ListBluetoothDevice())
//					{
//						 mainLayout.setVisibility(View.GONE);
//						 printerlayout.setVisibility(View.VISIBLE);
//					}

					
				}
			}
		}
		else if(mmytabView.getSelectId()==4)
		{
			if(mSetItem==3)
			{
				setLayoutVisible(benjishezLayout);
				//shijianshezbuiler.show();
			}
		}
	//	handler.removeMessages(1);
	//	handler.sendEmptyMessageDelayed(1, 4000);
	}
	
	public void saveMyBitmap(String bitName,Bitmap bmp) throws IOException {
  //      File f = new File("/sdcard/DCIM/" + bitName + ".png");
        File f = createSaveFile(Environment.getExternalStorageDirectory().toString()+"/DCIM",bitName + ".png");
        f.createNewFile();
        FileOutputStream fOut = null;
        try {
                fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
        bmp.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
                fOut.flush();
        } catch (IOException e) {
                e.printStackTrace();
        }
        try {
                fOut.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
}
	
	private Builder showshijiansheziItem()
	{
		Builder builder=new Builder(this);            //閿熸枻鎷烽敓鐭浼欐嫹閿熸枻鎷烽敓閰电》鎷烽敓锟�           
		builder.setSingleChoiceItems(R.array.shijianshez_array, 0, new android.content.DialogInterface.OnClickListener(){                
//			public void onclick(DialogInterface dialog, int which) {            
//				String hoddy=getResources().getStringArray(R.array.shijianshez_array)[which];   
//				//edittext.settext("閿熸枻鎷烽�閿熸枻鎷烽敓鍓匡綇鎷�"+hoddy);    
//				Log.i(TAG,"liuliangxiang hoddy="+hoddy);
//				}

			public void onClick(DialogInterface arg0, int which) {
				// TODO Auto-generated method stub
				String hoddy=getResources().getStringArray(R.array.shijianshez_array)[which];   
				//edittext.settext("閿熸枻鎷烽�閿熸枻鎷烽敓鍓匡綇鎷�"+hoddy);    
				Log.i(TAG,"liuliangxiang hoddy="+hoddy);
				
			}           
			});
		return builder;
	}
	
	public static void setLayoutVisible(LinearLayout layout)
	{
		for(int i=0;i<mLayout.size();i++)
		{
			//Log.i(TAG,"liuliangxiang i="+i+mLayout.get(i).toString());
			if(layout==null)
			{
				//mmenuLayout.setVisibility(View.GONE);
				//shijianshezbuiler.
				mLayout.get(i).setVisibility(View.GONE);
			//	imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,
			//		     InputMethodManager.HIDE_NOT_ALWAYS);
			}
			else if(mLayout.get(i).equals(layout))
			{
				mLayout.get(i).setVisibility(View.VISIBLE);
			}
			else
			{
				mLayout.get(i).setVisibility(View.GONE);
			}
		}
		delbtn.setVisibility(View.GONE);
		
	}
	
	public boolean dispatchKeyEvent (KeyEvent event)
	{
	//	Log.i(TAG,"liuliangxiang dispatchKeyEvent");
		switch(event.getKeyCode())
		{
			case KeyEvent.KEYCODE_BACK:
			{
				if(mmenuLayout.getVisibility()==View.VISIBLE)
				{
					//mmenuLayout.setVisibility(View.GONE);
					//mmytabView.clearHost();		
					//setLayoutVisible(null);
					index=0;
					//mmainset.isdongjie=true;
					handler.sendEmptyMessage(2);
					return true;
				}
//				else
//				{
//					return super.dispatchKeyEvent(event);
//				}	
				if(printerlayout.getVisibility()==View.VISIBLE)
				{
				   mainLayout.setVisibility(View.VISIBLE);
				   printerlayout.setVisibility(View.GONE);
				}
			//	break;
				return true;
			}
		}
		//return true;
		return super.dispatchKeyEvent(event);
	}

	public void onSelectSex(int sex) {
		// TODO Auto-generated method stub
		//Log.i(TAG,"liuliangxiang sex="+sex);
		int str2=Settings.System.getInt(getContentResolver(), "xingbie",0);
		if(sex!=str2)
		{
			mmainset.isStartRecord=false;
			isnamerecord=false;
			mmainset.msgindex=4;
			Settings.System.putInt(getContentResolver(), "xingbie",sex);
			if(sex == 0){
				sexText.setText(R.string.man);
			}else{
				sexText.setText(R.string.woman);
			}
			mmainset.mperinfomation.sex=sexText.getText().toString();
			mmainset.mperinfomation.indexsex=mmainset.CSI.size();
			mmainset.setthings(8);
		}
		//sexText.setText(sex);
		
	}

	public void onupdateInfomation() {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(11);
	}
	
//	public class BootCompleteReceiver extends BroadcastReceiver{
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			// TODO Auto-generated method stub
//			Log.i(TAG,"liuliangxiang BootCompleteReceiver");
//			handler.sendEmptyMessageDelayed(50, 100);
//			
//		}
//	}
}
