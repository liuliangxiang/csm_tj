package com.example.csm.boxingview;

import static android.os.BatteryManager.BATTERY_STATUS_UNKNOWN;

import java.security.SignedObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.R.integer;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.example.csm.Application;
import com.example.csm.R;
import com.example.csm.mainset.mainset;

public class boxingview extends SurfaceView implements Callback{
	
	public SurfaceHolder mSurfaceHolder = null;
	
	private Paint groundPaint;
	private Paint CoordinatePaint;
	private Paint mSLinePaint;
	private Paint mCSILinePaint;
	private Paint mSlinePaint1;
	private Paint mELinePaint;
	private Paint mGLinePaint;
	private Paint mSLine2Paint;
	private Paint mSLine2ePaint;
	private Paint TextPaint;
	private Paint EmergePaint;
	private Paint CoordTextPaint;
	private Paint blueTextPaint;
	private Paint orageTextPaint;
	private Paint greenTextPaint;
	private Paint grayTextPaint;
	private Paint TblueTextPaint;
	private Paint TredTextPaint;
	private Paint TorageTextPaint;
	private Paint CSITextPaint;
	private Paint CurCSIPaint;
	private Paint BSTextPaint;
	private Paint BSTextPaint2;
	private Paint BSTextCoordiatePaint;
	private Paint RTextPaint;
	private Paint shandowPaint;
		
	private Path PointPath;
	private Path mLinePath;
	private Path miLinePath;
	private Path meLinePath;
	private Canvas mCanvas;
	public boolean mIsRunning;
	
	private Paint DataPaintGray;
	private Paint WhitePaint;
	private Paint WhitePaint2;
	//private Paint 
	
	int width;
	int height;
	int left;
	int top;

	Point spoint[]=new Point[10];
	Point ipoint[]=new Point[3];
	Point epoint[]=new Point[5];
	
	float iy[]=new float[10];
	float ix[]=new float[18];
	float siy[]=new float[10];
	float six[]=new float[18];
	float ey[]=new float[16];
	float ex[]=new float[30];
	float sey[]=new float[16];
	float sex[]=new float[30];
	
	float bix[]=new float[18];
	float biy[]=new float[10];
	
	

	float iyScale;
	float ix1Scale;
	float ix5Scale;
	float ix2Scale;
	float ix10Scale;
	float ix60Scale;
	float ix120Scale;
	
	float eyScale;
	float ex1Scale;
	float ex5Scale;
	float ex10Scale;
	float ex60Scale;
	float ex120Scale;

	float seyScale;
	float siyScale;
	float six1Scale;
	float six5Scale;
	float six2Scale;
	float six10Scale;
	float six60Scale;
	float six120Scale;
	

	float sex1Scale;
	float sex5Scale;
	float sex10Scale;
	float sex60Scale;
	float sex120Scale; 
	
	float bitmapupx;
	float bitmapupy;
	float bitmapdownx;
	float bitmapdowny;
	
	float seeg12Scale;
	float seeg25Scale;
	float seeg50Scale;
	float eegyScale;
	float seegyScale;
	
	SimpleDateFormat  sdf=new SimpleDateFormat("HH:mm:ss");
	
	private static final String TAG="boxingView";
	
	
	mainset mmainset;
	Context mcontext;
	
	int pluggedInStatus;
	int batteryLevel;
	
	BatteryReceiver receiver;
	
	int preDataSize;
	int preEEGDataSize;
	
	float touchDownX=-1;
	float touchDownY=-1;
	
	Bitmap bitmapDown;
	Bitmap bitmapUp;
	private ArrayList<Float> bmpuparray=new ArrayList<Float>();
	private ArrayList<Float> bmpdownarray=new ArrayList<Float>();
	
	int EEGflen=0;
	int startscishowx=0;
	int starteegshowx=0;
	int startsci2showx=0;
	
	int scimovereclen=0;
	int eegmovereclen=0;
	int sci2movereclen=0;
	
	int scimovedongjielen=0;
	int eegmovedongjielen=0;
	int sci2movedongjielen=0;

	int DataNum=-1;
	int CurDataLen=0;
	
	float cursex=-1;
	float cursix=-1;
	float curseegx = -1;
	float cureegx = -1;
	float curex=-1;
	float curix=-1;
	myRunnable mRunnable;
	Handler handler=new Handler();	
	int animStep;
	int animShowStep;
	int preEEGSize;
	
	Paint CsiTitlePaint;
	Paint CsiTitlePaint2;
	Paint EMGPaint;
	
	Paint EMGPaintText;
	Paint BSTextPaintText;
	
	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
	{
		int specMode = MeasureSpec.getMode(widthMeasureSpec);		
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);	
		if(specMode==MeasureSpec.AT_MOST)
		{
			setMeasuredDimension(width, height);
		}
		else
		{
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	public boxingview(Context context, AttributeSet attrs) {
		super(context, attrs);
		mcontext=context;
		initPaint();
		// TODO Auto-generated constructor stub
	}

	public boxingview(Context context) {
		super(context);
		mcontext=context;
		initPaint();
		// TODO Auto-generated constructor stub
	}
	Paint paint;
	
	public void initPaint()
	{
		mmainset=mainset.getInstance();
		Resources resources = getResources();
		DisplayMetrics display=resources.getDisplayMetrics();
		height=display.heightPixels;
		width=display.widthPixels;
		mmainset.width=width;
		mmainset.height=height;
		
		paint=new Paint();
		paint.setColor(0xffffffff);
		
		TredTextPaint=new Paint();
		TredTextPaint.setColor(0xffff0000);
		TredTextPaint.setTextSize(20);
				
		mLinePath = new Path();
		groundPaint=new Paint();
		groundPaint.setColor(0xee000000);
		groundPaint.setStrokeWidth(2);
		
		PointPath = new Path();
		miLinePath=new Path();
		meLinePath=new Path();
		
		
		CoordinatePaint=new Paint();
		CoordinatePaint.setColor(Color.YELLOW);
		//CoordinatePaint.setStrokeWidth(2);
		
		mGLinePaint=new Paint();
		mGLinePaint.setColor(0x88ffffff);
		mGLinePaint.setStrokeWidth(1.0f);
		PathEffect effects = new DashPathEffect(new float[]{5,5,5,5},1);  
		mGLinePaint.setPathEffect(effects);  
		
		mSLinePaint = new Paint();
		mSLinePaint.setColor(0xff7FFF00);
		mSLinePaint.setAntiAlias(true);
		mSLinePaint.setStyle(Paint.Style.STROKE);
		mSLinePaint.setPathEffect(new CornerPathEffect(10));
		mSLinePaint.setStrokeCap(Paint.Cap.ROUND);
		mSLinePaint.setStrokeWidth(2);
		
		mCSILinePaint = new Paint();
		mCSILinePaint.setColor(0xffFF00EE);
		mCSILinePaint.setAntiAlias(true);
		mCSILinePaint.setStyle(Paint.Style.STROKE);
		mCSILinePaint.setPathEffect(new CornerPathEffect(10));
		mCSILinePaint.setStrokeCap(Paint.Cap.ROUND);
		mCSILinePaint.setStrokeWidth(2);
		
		mSlinePaint1 = new Paint();
		mSlinePaint1.setColor(0xffee0000);
		mSlinePaint1.setAntiAlias(true);
		mSlinePaint1.setStyle(Paint.Style.STROKE);
		mSlinePaint1.setPathEffect(new CornerPathEffect(10));
		mSlinePaint1.setStrokeCap(Paint.Cap.ROUND);
		mSlinePaint1.setStrokeWidth(2);
		

		
		mSLine2ePaint = new Paint();
		mSLine2ePaint.setColor(0xeeff4422);
		
		mSLine2Paint = new Paint();
		mSLine2Paint.setColor(0xeeff4422);
		
		mELinePaint = new Paint();
		mELinePaint.setColor(0xffff7799);
		mELinePaint.setAntiAlias(true);
		mELinePaint.setStyle(Paint.Style.STROKE);
		mELinePaint.setPathEffect(new CornerPathEffect(10));
		mELinePaint.setStrokeCap(Paint.Cap.ROUND);
		mELinePaint.setStrokeWidth(2);
		
		EmergePaint=new Paint();
		EmergePaint.setColor(0xee991111);
		EmergePaint.setTextSize(16);
		
		CoordTextPaint=new Paint();
	//	CoordTextPaint.setColor(0xeeaacccc);
		CoordTextPaint.setColor(Color.YELLOW);
		CoordTextPaint.setTextSize(16);
		
		blueTextPaint=new Paint();
		blueTextPaint.setColor(0xee5555cc);
		blueTextPaint.setTextSize(20);
		
		orageTextPaint=new Paint();
		orageTextPaint.setColor(0xeeFF3311);
		orageTextPaint.setTextSize(20);
		
		greenTextPaint=new Paint();
		greenTextPaint.setColor(0xee22ff33);
		greenTextPaint.setTextSize(20);
		
		grayTextPaint=new Paint();
		grayTextPaint.setColor(0xeeaaaabb);
		grayTextPaint.setTextSize(20);
		
		CSITextPaint=new Paint();
		CSITextPaint.setColor(0xffFF00EE);
	//	CSITextPaint.setTextSize(height/24);
		CSITextPaint.setTextSize(80);
		CurCSIPaint=new Paint();
		CurCSIPaint.setColor(0xffFF00EE);
		CurCSIPaint.setTextSize(22);
		
		CsiTitlePaint=new Paint();
		CsiTitlePaint.setColor(0xffFF00EE);
		CsiTitlePaint.setTextSize(30);
		
		CsiTitlePaint2=new Paint();
		CsiTitlePaint2.setColor(0xffFF00EE);
		CsiTitlePaint2.setTextSize(45);
		
		EMGPaint=new Paint();
		EMGPaint.setColor(0xff7FFF00);
		EMGPaint.setTextSize(30);
		
		BSTextPaint=new Paint();
		BSTextPaint.setColor(0xff7FFF00);
		BSTextPaint.setTextSize(22);
		
		
		BSTextPaint2=new Paint();
		BSTextPaint2.setColor(0xff7FFF00);
		BSTextPaint2.setTextSize(30);
		
		BSTextCoordiatePaint=new Paint();
		BSTextCoordiatePaint.setColor(0xff7FFF00);
		BSTextCoordiatePaint.setTextSize(22);
		
		EMGPaintText=new Paint();
		EMGPaintText.setColor(0xff7FFF00);
		EMGPaintText.setTextSize(40);
		
		BSTextPaintText=new Paint();
		BSTextPaintText.setColor(0xff7FFF00);
		BSTextPaintText.setTextSize(40);
		
		RTextPaint=new Paint();
		RTextPaint.setColor(0xff555555);
		RTextPaint.setTextSize(20);
				
		TblueTextPaint=new Paint();
		TblueTextPaint.setColor(0xff22EEff);
		TblueTextPaint.setTextSize(20);
		
		TorageTextPaint=new Paint();
		TorageTextPaint.setColor(0xff2222ee);
		TorageTextPaint.setTextSize(24);
		
		DataPaintGray=new Paint();
		DataPaintGray.setColor(0xff777777);
		
		WhitePaint=new Paint();
		WhitePaint.setColor(0xffffffff);
		WhitePaint.setTextSize(20);
		
		WhitePaint2=new Paint();
		WhitePaint2.setColor(0xffffffff);
		WhitePaint2.setTextSize(24);
		
		shandowPaint = new Paint();
		shandowPaint.setColor(0xaaFFee11);
			
		spoint[0]=new Point();
		spoint[0].x=width/20;
		spoint[0].y=0+height/48+height/10+height/20;
		spoint[1]=new Point();
		spoint[1].x=width/20;
		spoint[1].y=spoint[0].y+height*9/64+height/38;
		spoint[2]=new Point();
		spoint[2].x=spoint[1].x;
		spoint[2].y=spoint[1].y+height*9/64+height/38;
		
		spoint[3]=new Point();
		spoint[3].x=spoint[1].x;
		spoint[3].y=spoint[2].y+height/64+height/38;		
		spoint[4]=new Point();
		spoint[4].x=width/20;
		spoint[4].y=spoint[3].y+height*9/64+height/38;
		spoint[5]=new Point();
		spoint[5].x=spoint[1].x;
		spoint[5].y=spoint[4].y+height*9/64+height/38;
		
		spoint[6]=new Point();
		spoint[6].x=width-width/25;
		spoint[6].y=spoint[1].y;	
		
		spoint[7]=new Point();
		spoint[7].x=width-width/25;
		spoint[7].y=spoint[2].y;
		
		spoint[8]=new Point();
		spoint[8].x=width-width/25;
		spoint[8].y=spoint[4].y;	
		
		spoint[9]=new Point();
		spoint[9].x=width-width/25;
		spoint[9].y=spoint[5].y;
	////	
		mmainset.mspoint[0]=new Point();
		mmainset.mspoint[0].x=30;
		mmainset.mspoint[0].y=60-20;
		mmainset.mspoint[1]=new Point();
		mmainset.mspoint[1].x=30;
		mmainset.mspoint[1].y=300-20;
		mmainset.mspoint[2]=new Point();
		mmainset.mspoint[2].x=30;
		mmainset.mspoint[2].y=320-10;
		
		mmainset.mspoint[3]=new Point();
		mmainset.mspoint[3].x=30;
		mmainset.mspoint[3].y=416-10;		
		mmainset.mspoint[4]=new Point();
		mmainset.mspoint[4].x=30;
		mmainset.mspoint[4].y=512-10;
		mmainset.mspoint[5]=new Point();
		mmainset.mspoint[5].x=1200;
		mmainset.mspoint[5].y=300-20;
	
		mmainset.mspoint[6]=new Point();
		mmainset.mspoint[6].x=mmainset.mspoint[5].x;
		mmainset.mspoint[6].y=mmainset.mspoint[3].y;	
		
		mmainset.mspoint[7]=new Point();
		mmainset.mspoint[7].x=mmainset.mspoint[5].x;
		mmainset.mspoint[7].y=mmainset.mspoint[4].y;
		
		
		siyScale=(float)(spoint[2].y-spoint[0].y)/100.0f;	
		six1Scale=(float)(spoint[7].x-spoint[2].x)/18.0f;
		six5Scale=(float)(spoint[7].x-spoint[2].x)/90.0f;
		six2Scale=(float)(spoint[7].x-spoint[2].x)/36.0f;
		six10Scale=(float)(spoint[7].x-spoint[2].x)/180.0f;
		six60Scale=(float)(spoint[7].x-spoint[2].x)/1080.0f;
		six120Scale=(float)(spoint[7].x-spoint[2].x)/2160.0f;
		
		seeg12Scale = (float)(spoint[7].x-spoint[2].x)/12.0f;
		seeg25Scale = (float)(spoint[7].x-spoint[2].x)/6.0f;
		seeg50Scale = (float)(spoint[7].x-spoint[2].x)/3.0f;
		seegyScale = (float)(spoint[2].y-spoint[0].y)/400f;
		
		epoint[0]=new Point();
		epoint[0].x=width/20;
		epoint[0].y=0+height/48+height/128+height/10+height/20;
		
		epoint[1]=new Point();
		epoint[1].x=width/20;
		epoint[1].y=epoint[0].y+height*7/24+height/14;
		
		epoint[2]=new Point();
		epoint[2].x=width/20;
		epoint[2].y=epoint[1].y+height*7/24+height/14;
		
		epoint[3]=new Point();
		epoint[3].x=width-width/30;
		epoint[3].y=epoint[1].y;
		
		epoint[4]=new Point();
		epoint[4].x=width-width/30;
		epoint[4].y=epoint[2].y;
		
		
		
		for(int i=0;i<ey.length;i++)
		{
			//ey[i]=epoint[0].y+i*(epoint[2].y-epoint[0].y)/ey.length;
			if(i>=8)
			{
				ey[i]=epoint[1].y+(i-7)*(epoint[2].y-epoint[1].y)*2/ey.length;
			}
			else
			{
				ey[i]=epoint[1].y-(i+1)*(epoint[1].y-epoint[0].y)*2/ey.length;
			}
		}

		for(int j=0;j<ex.length;j++)
		{
			ex[j]=epoint[0].x+(j+1)*(epoint[3].x-epoint[0].x)/ex.length;
		}	
		
		for(int i=0;i<sey.length;i++)
		{
			//ey[i]=epoint[0].y+i*(epoint[2].y-epoint[0].y)/ey.length;
			if(i>=8)
			{
				sey[i]=spoint[4].y+(i-7)*(spoint[5].y-spoint[4].y)*2/sey.length;
			}
			else
			{
				sey[i]=spoint[4].y-(i+1)*(spoint[4].y-spoint[3].y)*2/sey.length;
			}
		}

		for(int j=0;j<sex.length;j++)
		{
			sex[j]=spoint[4].x+(j+1)*(spoint[8].x-spoint[4].x)/sex.length;
		}
		
		for(int i=0;i<biy.length;i++)
		{
			biy[i]=spoint[3].y+i*(spoint[5].y-spoint[3].y)/biy.length;
			
		}
		for(int j=0;j<bix.length;j++)
		{
			bix[j]=spoint[2].x+(j+1)*(spoint[7].x-spoint[2].x)/bix.length;
		}
		
		eyScale=(ey[0]-ey[1])/16;
		ex1Scale=(ex[1]-ex[0])/10;///ex.length;
		ex5Scale=ex1Scale/(5);
		ex10Scale=ex1Scale/(10);
		ex60Scale=ex1Scale/(60);
		ex120Scale=ex1Scale/(120);
		
		seyScale=(sey[0]-sey[1])/16;
		sex1Scale=(sex[1]-sex[0])/10;///ex.length;
		sex5Scale=sex1Scale/(5);
		sex10Scale=sex1Scale/(10);
		sex60Scale=sex1Scale/(60);
		sex120Scale=sex1Scale/(120);

		ipoint[0]=new Point();
		ipoint[0].x=width/20;
		ipoint[0].y=0+height/48+height/64+height/10+height/20;
		
		ipoint[1]=new Point();
		ipoint[1].x=width/20;
		ipoint[1].y=ipoint[0].y+height*17/24;//+height/12;
	
		ipoint[2]=new Point();
		ipoint[2].x=width-width/25;
		ipoint[2].y=ipoint[1].y;
		eegyScale = (float)(ipoint[2].y-ipoint[0].y)/255f;
		for(int i=0;i<iy.length;i++)
		{
			iy[i]=ipoint[0].y+i*(ipoint[1].y-ipoint[0].y)/iy.length;
			
		}
		for(int j=0;j<ix.length;j++)
		{
			ix[j]=ipoint[0].x+(j+1)*(ipoint[2].x-ipoint[0].x)/ix.length;
		}
		
		for(int i=0;i<siy.length;i++)
		{
			siy[i]=spoint[0].y+i*(spoint[2].y-spoint[0].y)/siy.length;
			
		}
		for(int j=0;j<six.length;j++)
		{
			six[j]=spoint[2].x+(j+1)*(spoint[7].x-spoint[2].x)/six.length;
		}
		
		for(int i=0;i<mmainset.msiy.length;i++)
		{
			mmainset.msiy[i]=mmainset.mspoint[0].y+i*(mmainset.mspoint[1].y-mmainset.mspoint[0].y)/mmainset.msiy.length;
			
		}
		for(int j=0;j<mmainset.msix.length;j++)
		{
			mmainset.msix[j]=mmainset.mspoint[2].x+(j+1)*(mmainset.mspoint[5].x-mmainset.mspoint[1].x)/mmainset.msix.length;
		}
		
//		for(int i=0;i<mmainset.msey.length;i++)
//		{
//			//ey[i]=epoint[0].y+i*(epoint[2].y-epoint[0].y)/ey.length;
//			if(i>=2)
//			{
//				mmainset.msey[i]=mmainset.mspoint[3].y+(i-7)*(mmainset.mspoint[5].y-mmainset.mspoint[4].y)*2/mmainset.msey.length;
//			}
//			else
//			{
//				mmainset.msey[i]=mmainset.mspoint[4].y-(i+1)*(mmainset.mspoint[4].y-mmainset.mspoint[3].y)*2/mmainset.msey.length;
//			}
//			mmainset.msey[i]=mmainset.mspoint[2].y-(i+1)*(mmainset.mspoint[4].y-mmainset.mspoint[3].y)*2/mmainset.msey.length;
//		}
		mmainset.msey[0]=mmainset.mspoint[2].y+(mmainset.mspoint[3].y-mmainset.mspoint[2].y)/2;
		mmainset.msey[1]=mmainset.mspoint[2].y;
		mmainset.msey[2]=mmainset.mspoint[3].y+(mmainset.mspoint[4].y-mmainset.mspoint[3].y)/2;
		mmainset.msey[3]=mmainset.mspoint[4].y;

		for(int j=0;j<mmainset.msex.length;j++)
		{
			mmainset.msex[j]=mmainset.mspoint[4].x+(j+1)*(mmainset.mspoint[7].x-mmainset.mspoint[4].x)/mmainset.msex.length;
		}
		
		for(int j=0;j<mmainset.msey.length;j++)
		{
			mmainset.msey[j]=mmainset.mspoint[2].y+j*(mmainset.mspoint[4].y-mmainset.mspoint[2].y)/mmainset.msey.length;
		}
		
		iyScale=(iy[1]-iy[0])/10;
		//iyScale=(ipoint[1].y-ipoint[0].y)/100;
		ix1Scale=(ix[1]-ix[0]);
		ix5Scale=(ix1Scale)/5;
		ix2Scale=(ix1Scale)/2;
		ix10Scale=(ix1Scale)/10;
		ix60Scale=(ix1Scale)/60;
		ix120Scale=(ix1Scale)/120;
		//ix600Scale=(ipoint[2].x-ipoint[0].x)/360;
		//ix600Scale=(ipoint[2].x-ipoint[0].x)/360;
		
		bitmapDown=BitmapFactory.decodeResource(getResources(), R.drawable.down);
		bitmapUp=BitmapFactory.decodeResource(getResources(), R.drawable.up);
		
		mSurfaceHolder=this.getHolder();
		mSurfaceHolder.addCallback(this);
		//mCanvas=new Canvas();	
		
	//	mmainset=mainset.getInstance();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		receiver = new BatteryReceiver();
		mcontext.registerReceiver(receiver,filter);	
		mRunnable=new myRunnable();
		BitmapFactory.Options options=new BitmapFactory.Options(); 
		mmainset.taijilogo=BitmapFactory.decodeResource(resources, R.drawable.black2);
	}
	
	public void unregisterRecevier()
	{
		mcontext.unregisterReceiver(receiver);
	}
	
	private class BatteryReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			 pluggedInStatus = intent
                     .getIntExtra("status", BATTERY_STATUS_UNKNOWN);
             batteryLevel = intent.getIntExtra("level", 0);
			
		}
		
	}
	
	public void drawbackground()
	{
		mCanvas.drawRect(new RectF(0,0,width,height),groundPaint);
		mCanvas.drawRect(new RectF(0,height-54,width,height),WhitePaint);
	}
	
	public void drawscoordrec()
	{
		mCanvas.drawLine(spoint[0].x, spoint[0].y, spoint[2].x, spoint[2].y, CoordinatePaint);
		mCanvas.drawLine(spoint[3].x, spoint[3].y, spoint[5].x, spoint[5].y, CoordinatePaint);
	//	mCanvas.drawLine(spoint[1].x, spoint[1].y, spoint[6].x, spoint[6].y, CoordinatePaint);
		mCanvas.drawLine(spoint[2].x, spoint[2].y, spoint[7].x, spoint[7].y, CoordinatePaint);
	//	mCanvas.drawLine(spoint[4].x, spoint[4].y, spoint[8].x, spoint[8].y, CoordinatePaint);
		mCanvas.drawLine(spoint[5].x, spoint[5].y, spoint[9].x, spoint[9].y, CoordinatePaint);
		
		for(int i=0;i<siy.length;i++)
		{
			mCanvas.drawLine(spoint[0].x, siy[i], spoint[6].x, siy[i], mGLinePaint);
		}
		for(int j=0;j<six.length;j++)
		{
			mCanvas.drawLine(six[j],spoint[0].y ,six[j], spoint[2].y, mGLinePaint);
		}
		
		for(int i=0;i<biy.length;i++)
		{
			mCanvas.drawLine(spoint[3].x, biy[i], spoint[7].x, biy[i], mGLinePaint);
		}
		for(int j=0;j<bix.length;j++)
		{
			mCanvas.drawLine(bix[j],spoint[3].y ,bix[j], spoint[5].y, mGLinePaint);
		}
		mCanvas.drawText("100",spoint[0].x-28, siy[0]+8, CoordTextPaint);
		mCanvas.drawText("80",spoint[1].x-20, siy[2]+8, CoordTextPaint);
		mCanvas.drawText("60",spoint[1].x-20, siy[4]+8, CoordTextPaint);
		mCanvas.drawText("40",spoint[1].x-20, siy[6]+8, CoordTextPaint);
		mCanvas.drawText("20",spoint[1].x-20, siy[8]+8, CoordTextPaint);
		mCanvas.drawText("0",spoint[2].x-12, spoint[2].y-2, CoordTextPaint);
		
		mCanvas.drawText("100",spoint[0].x-28, biy[0]+8, CoordTextPaint);
		mCanvas.drawText("80",spoint[1].x-20, biy[2]+8, CoordTextPaint);
		mCanvas.drawText("60",spoint[1].x-20, biy[4]+8, CoordTextPaint);
		mCanvas.drawText("40",spoint[1].x-20, biy[6]+8, CoordTextPaint);
		mCanvas.drawText("20",spoint[1].x-20, biy[8]+8, CoordTextPaint);
		mCanvas.drawText("0",spoint[2].x-12, spoint[5].y-2, CoordTextPaint);
		mCanvas.drawText(mcontext.getString(R.string.csi_review), six[14]+4, siy[1]-2, CurCSIPaint);
		mCanvas.drawText(mcontext.getString(R.string.bs_review), sex[24], sey[6], BSTextCoordiatePaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		//int CSIData=
		float xScale=six1Scale;
		timeScale=mmainset.majuirectimeScale;
		scale=1;
		if(timeScale==5)
		{
			xScale=six5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}else if(timeScale==2)
		{
			xScale=six2Scale;
			pointSize=36;
			pointScale=1;
			scale=2;
		}
		else if(timeScale==10)
		{
			xScale=six10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=six60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=six120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		int value;
		int bannerSelect=mmainset.bannerSelect;
		float bannerX;
		float bannerYS;
		float bannerYL;

		bannerX = spoint[0].x;
		bannerYS = spoint[0].y;
		int tempValue = mmainset.shangxianshezValue;
		if (tempValue > 100)
			tempValue = 0;
		value = 100 - tempValue;
		if (value < 100)
			bannerYS = siy[value / 10] + siyScale * (value % 10);
		else if (value == 0)
			bannerYS = spoint[0].y;
		else if (value == 100)
			bannerYS = spoint[2].y;
		bannerYL = spoint[2].y;
		tempValue = mmainset.xiaxianshezValue;
		if (tempValue > 100)
			tempValue = 0;
		value = 100 - tempValue;
		if (value < 100)
			bannerYL = siy[value / 10] + siyScale * (value % 10);
		else if (value == 0)
			bannerYL = spoint[0].y;
		else if (value == 100)
			bannerYL = spoint[2].y;
		if(bannerSelect==1){
			mCanvas.drawLine(bannerX, bannerYS, spoint[6].x, bannerYS, shandowPaint);
			mCanvas.drawLine(bannerX, bannerYL, spoint[6].x, bannerYL, shandowPaint);
		}else if(bannerSelect==2){
			mCanvas.drawRect(bannerX, bannerYS,spoint[6].x,bannerYL,shandowPaint);
		}
		startX=scimovereclen;
		dataSize=pointSize;
		if(mmainset.RecCSI.size()<=(pointSize))
		{
			startX=-1;
			dataSize=mmainset.RecCSI.size();
		}
		else if(startX>=(mmainset.RecCSI.size()-pointSize))
		{
			startX=mmainset.RecCSI.size()-pointSize-1;
			scimovereclen=mmainset.RecCSI.size()-pointSize;
			dataSize=pointSize;
		}
		CurDataLen=startX+dataSize;	
		if(CurDataLen>=mmainset.RecCSI.size())
			CurDataLen=mmainset.RecCSI.size()-1;
		float hx = ipoint[0].x;
        float hy = ipoint[1].y;
	//	Log.i(TAG,"liuliangxiang hx="+hx.length);
		int SCIData=0;
        if(mmainset.RecCSI.size()>0)
		{
			PointPath.reset();
			bmpdownarray.clear();
			bmpuparray.clear();
			if(mmainset.RecCSI.size()>0)
			{	
				if(mmainset.RecCSI.size()<=pointSize)
				{
					PointPath.moveTo(spoint[2].x, spoint[2].y);
				}
				else
				{
					hx=spoint[0].x;
					hy=spoint[2].y;
					int temp=mmainset.RecCSI.get(startX)&0xff;
					if(temp>100)temp=0;
					SCIData=100-temp;
					if(SCIData<100)
						hy=siy[SCIData/10]+siyScale*(SCIData%10);
					else if(SCIData==0)
						hy=spoint[0].y;
					else if(SCIData==100)
						hy=spoint[2].y;
					PointPath.moveTo(hx, hy);
				}
			}
			float px;
			float height = 0;
//			for(int i=1;i<hx.length;i=i+pointScale)
//			{
//				int temp=mmainset.RecCSI.get(i+startX)&0xff;
//				if(temp>100)temp=0;
//				SCIData=100-temp;
//				hy[i]=spoint[2].y;			
//				if(SCIData<100)
//					hy[i]=siy[SCIData/10]+siyScale*(SCIData%10);
//				else if(SCIData==0)
//					hy[i]=spoint[0].y;
//				else if(SCIData==100)
//					hy[i]=spoint[2].y;
//				hx[i]=six[(i)/scale]-xScale*(scale-i%scale-1)+xScale/6;
//				PointPath.lineTo(hx[i], hy[i]);
//
//			}
			for(int i=1;i<dataSize+1;i=i+pointScale)
            {
                int temp = mmainset.RecCSI.get(startX+i)&0xff;
                if(temp>100)
                    temp=0;
                SCIData=100-temp;
                hy=spoint[2].y;;         
                if(SCIData<100)
                    hy=siy[SCIData/10]+siyScale*(SCIData%10);
                else if(SCIData==0)
                    hy=spoint[0].y;
                else if(SCIData==100)
                    hy=spoint[2].y;
                if(i<scale)
                {
                    hx=spoint[0].x+xScale*(i%scale)+xScale/6;
                }
                else
                {
                    hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
                }
                PointPath.lineTo(hx, hy);
            }
			
			if(dataSize>0)
			{
				PointPath.moveTo(hx, hy);
			}
		}
		mCanvas.drawPath(PointPath, mCSILinePaint);
		if(cursix!=-1)
		{
			int temp=(int)((cursix-spoint[0].x)/xScale)+startX+1;
			if(DataNum>=mmainset.RecCSI.size())
			{
				DataNum=mmainset.RecCSI.size()-1;
			}
			else
			{
				DataNum=temp;
			}
			if((temp<mmainset.RecCSI.size())&&(temp>0))
			{
				int data=mmainset.RecCSI.get(temp);
				int hour=(temp+mmainset.curminTime)/3600;
				int min=(temp+mmainset.curminTime)%3600/60;
				int second=(temp+mmainset.curminTime)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				mCanvas.drawText("("+strmin+":"+strsec+","+Integer.toString(data&0xff)+")", cursix, spoint[0].y+40, CurCSIPaint);
				mCanvas.drawLine(cursix, spoint[0].y, cursix, spoint[2].y, mSLine2ePaint);
			}
			
		}
		PointPath.close();
		for(int j=0;j<bmpuparray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapDown, bmpuparray.get(j)-8, bmpuparray.get(j+1), null);
		}
		for(int j=0;j<bmpdownarray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapUp, bmpdownarray.get(j)-8, bmpdownarray.get(j+1), null);
		}
		
		for(int k=0,j=0;k<=pointSize;k=k+6*timeScale,j=j+6)
		{
			float x;//x1[]=new int[19];
			x=spoint[0].x+j*six1Scale;
		//	int scale=timeScale;
			if(k%6==0)
			{
				int hour=(mmainset.curminTime+startX+1+k)/3600;
				int min=(mmainset.curminTime+startX+1+k)%3600/60;
				int second=(mmainset.curminTime+startX+1+k)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
			}
		}
		PointPath.reset();
	

		xScale=six1Scale;
		timeScale=mmainset.majuirectimeScale;
		scale=1;
		if(timeScale==5)
		{
			xScale=six5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}else if(timeScale==2)
		{
			xScale=six2Scale;
			pointSize=36;
			pointScale=1;
			scale=2;
		}
		else if(timeScale==10)
		{
			xScale=six10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=six60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=six120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		startX=scimovereclen;
		dataSize=pointSize;
		if(mmainset.RecBS.size()<=(pointSize))
		{
			startX=-1;
			dataSize=mmainset.RecBS.size();
		}
		else if(startX>=(mmainset.RecBS.size()-pointSize))
		{
			startX=mmainset.RecBS.size()-pointSize-1;
			scimovereclen=mmainset.RecBS.size()-pointSize;
			dataSize=pointSize;
		}
		CurDataLen=startX+dataSize;	
		if(CurDataLen>=mmainset.RecBS.size())
			CurDataLen=mmainset.RecBS.size()-1;
		hx=spoint[0].x;
		hy=spoint[5].y;
	//	Log.i(TAG,"liuliangxiang hx="+hx.length);
		SCIData=0;
        if(mmainset.RecBS.size()>0)
		{
			PointPath.reset();
			bmpdownarray.clear();
			bmpuparray.clear();
			if(mmainset.RecBS.size()>0)
			{	
				if(mmainset.RecBS.size()<=pointSize)
				{
					PointPath.moveTo(spoint[1].x, spoint[5].y);
				}
				else
				{
					hx=spoint[0].x;
					hy=spoint[5].y;
					int temp=mmainset.RecBS.get(startX)&0xff;
					if(temp>100)temp=0;
					SCIData=100-temp;
					if(SCIData<100)
						hy=biy[SCIData/10]+siyScale*(SCIData%10);
					else if(SCIData==0)
						hy=spoint[3].y;
					else if(SCIData==100)
						hy=spoint[5].y;
					PointPath.moveTo(hx, hy);
				}
			}
			float px;
			float height = 0;
//			for(int i=1;i<hx.length;i=i+pointScale)
//			{
//				int temp=mmainset.RecBS.get(i+startX)&0xff;
//				if(temp>100)temp=0;
//				SCIData=100-temp;
//				hy[i]=spoint[5].y;			
//				if(SCIData<100)
//					hy[i]=biy[SCIData/10]+siyScale*(SCIData%10);
//				else if(SCIData==0)
//					hy[i]=spoint[3].y;
//				else if(SCIData==100)
//					hy[i]=spoint[5].y;
//				hx[i]=six[(i-1)/scale]-xScale*(scale-i%scale-1)+xScale/6;
//				PointPath.lineTo(hx[i], hy[i]);
//
//			}
			for(int i=1;i<dataSize+1;i=i+pointScale)
            {
                int temp = mmainset.RecBS.get(startX+i)&0xff;
                if(temp>100)
                    temp=0;
                SCIData=100-temp;
                hy=spoint[5].y;;         
                if(SCIData<100)
                    hy=biy[SCIData/10]+siyScale*(SCIData%10);
                else if(SCIData==0)
                    hy=spoint[3].y;
                else if(SCIData==100)
                    hy=spoint[5].y;
                if(i<scale)
                {
                    hx=spoint[0].x+xScale*(i%scale)+xScale/6;
                }
                else
                {
                    hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
                }
                PointPath.lineTo(hx, hy);

            }
			
			if(dataSize>0)
			{
				PointPath.moveTo(hx, hy);
			}
		}
		mCanvas.drawPath(PointPath, mSLinePaint);
		if(cursix!=-1)
		{
			int temp=(int)((cursix-spoint[0].x)/xScale)+startX+1;
			if(DataNum>=mmainset.RecBS.size())
			{
				DataNum=mmainset.RecBS.size()-1;
			}
			else
			{
				DataNum=temp;
			}
			if((temp<mmainset.RecBS.size())&&(temp>0))
			{
				int data=mmainset.RecBS.get(temp);
				int hour=(temp+mmainset.curminTime)/3600;
				int min=(temp+mmainset.curminTime)%3600/60;
				int second=(temp+mmainset.curminTime)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				mCanvas.drawText("("+strmin+":"+strsec+","+Integer.toString(data&0xff)+")", cursix, spoint[3].y+40, BSTextPaint);
				mCanvas.drawLine(cursix, spoint[3].y, cursix, spoint[5].y, mSLine2ePaint);
			}
			
		}
		PointPath.close();
		for(int j=0;j<bmpuparray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapDown, bmpuparray.get(j)-8, bmpuparray.get(j+1), null);
		}
		for(int j=0;j<bmpdownarray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapUp, bmpdownarray.get(j)-8, bmpdownarray.get(j+1), null);
		}
		
		for(int k=0,j=0;k<=pointSize;k=k+6*timeScale,j=j+6)
		{
			float x;//x1[]=new int[19];
			x=spoint[0].x+j*six1Scale;
		//	int scale=timeScale;
			if(k%6==0)
			{
				int hour=(mmainset.curminTime+startX+1+k)/3600;
				int min=(mmainset.curminTime+startX+1+k)%3600/60;
				int second=(mmainset.curminTime+startX+1+k)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, spoint[5].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, spoint[5].y+12,CoordinatePaint);
			}
		}
		PointPath.reset();
	}
	
	
	public void drawseegcoordrec()
	{
		mCanvas.drawLine(spoint[0].x, spoint[0].y, spoint[2].x, spoint[2].y, CoordinatePaint);
		mCanvas.drawLine(spoint[3].x, spoint[3].y, spoint[5].x, spoint[5].y, CoordinatePaint);
	//	mCanvas.drawLine(spoint[1].x, spoint[1].y, spoint[6].x, spoint[6].y, CoordinatePaint);
		mCanvas.drawLine(spoint[2].x, spoint[2].y, spoint[7].x, spoint[7].y, CoordinatePaint);
		mCanvas.drawLine(spoint[4].x, spoint[4].y, spoint[8].x, spoint[8].y, CoordinatePaint);
		mCanvas.drawLine(spoint[5].x, spoint[5].y, spoint[9].x, spoint[9].y, CoordinatePaint);
		
		for(int i=0;i<siy.length;i++)
		{
			mCanvas.drawLine(spoint[0].x, siy[i], spoint[6].x, siy[i], mGLinePaint);
		}
		for(int j=0;j<six.length;j++)
		{
			mCanvas.drawLine(six[j],spoint[0].y ,six[j], spoint[2].y, mGLinePaint);
		}
		
//		for(int i=0;i<biy.length;i++)
//		{
//			mCanvas.drawLine(spoint[3].x, biy[i], spoint[7].x, biy[i], mGLinePaint);
//		}
//		for(int j=0;j<bix.length;j++)
//		{
//			mCanvas.drawLine(bix[j],spoint[3].y ,bix[j], spoint[5].y, mGLinePaint);
//		}
		
		mCanvas.drawText("100",spoint[0].x-28, siy[0]+8, CoordTextPaint);
		mCanvas.drawText("80",spoint[1].x-20, siy[2]+8, CoordTextPaint);
		mCanvas.drawText("60",spoint[1].x-20, siy[4]+8, CoordTextPaint);
		mCanvas.drawText("40",spoint[1].x-20, siy[6]+8, CoordTextPaint);
		mCanvas.drawText("20",spoint[1].x-20, siy[8]+8, CoordTextPaint);
		mCanvas.drawText("0",spoint[2].x-12, spoint[2].y-2, CoordTextPaint);
		
		mCanvas.drawText(mcontext.getString(R.string.csi_review), six[14]+4, siy[1]-2, CurCSIPaint);
		mCanvas.drawText(mcontext.getString(R.string.eeg_review), sex[24], sey[6], CurCSIPaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		//int CSIData=
		float xScale=six1Scale;
		timeScale=mmainset.majuirectimeScale;
		float bannerSelect=mmainset.bannerSelect;
		float bannerX;
		float bannerYS;
		float bannerYL;
		int value;

		bannerX = spoint[0].x;
		bannerYS = spoint[0].y;
		int tempValue = mmainset.shangxianshezValue;
		if (tempValue > 100)
			tempValue = 0;
		value = 100 - tempValue;
		if (value < 100)
			bannerYS = siy[value / 10] + siyScale * (value % 10);
		else if (value == 0)
			bannerYS = spoint[0].y;
		else if (value == 100)
			bannerYS = spoint[2].y;
		bannerYL = spoint[2].y;
		tempValue = mmainset.xiaxianshezValue;
		if (tempValue > 100)
			tempValue = 0;
		value = 100 - tempValue;
		if (value < 100)
			bannerYL = siy[value / 10] + siyScale * (value % 10);
		else if (value == 0)
			bannerYL = spoint[0].y;
		else if (value == 100)
			bannerYL = spoint[2].y;
		if(bannerSelect==1){
			mCanvas.drawLine(bannerX, bannerYS, spoint[6].x, bannerYS, shandowPaint);
			mCanvas.drawLine(bannerX, bannerYL, spoint[6].x, bannerYL, shandowPaint);
		}else if(bannerSelect==2){
			mCanvas.drawRect(bannerX, bannerYS,spoint[6].x,bannerYL,shandowPaint);
		}
		scale=1;
		if(timeScale==5)
		{
			xScale=six5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}else if(timeScale==2)
		{
			xScale=six2Scale;
			pointSize=36;
			pointScale=1;
			scale=2;
		}
		else if(timeScale==10)
		{
			xScale=six10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=six60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=six120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		startX=scimovereclen;
		dataSize=pointSize;
		if(mmainset.RecCSI.size()<=(pointSize))
		{
			startX=-1;
			dataSize=mmainset.RecCSI.size();
		}
		else if(startX>=(mmainset.RecCSI.size()-pointSize))
		{
			startX=mmainset.RecCSI.size()-pointSize-1;
			scimovereclen=mmainset.RecCSI.size()-pointSize;
			dataSize=pointSize;
		}
		CurDataLen=startX+dataSize;	
		if(CurDataLen>=mmainset.RecCSI.size())
			CurDataLen=mmainset.RecCSI.size()-1;
		float hx = ipoint[0].x;
        float hy = ipoint[1].y;
	//	Log.i(TAG,"liuliangxiang hx="+hx.length);
		int SCIData=0;
        if(mmainset.RecCSI.size()>0)
		{
			PointPath.reset();
			bmpdownarray.clear();
			bmpuparray.clear();
			if(mmainset.RecCSI.size()>0)
			{	
				if(mmainset.RecCSI.size()<=pointSize)
				{
					PointPath.moveTo(spoint[2].x, spoint[2].y);
				}
				else
				{
					hx=spoint[0].x;
					hy=spoint[2].y;
					int temp=mmainset.RecCSI.get(startX)&0xff;
					if(temp>100)temp=0;
					SCIData=100-temp;
					if(SCIData<100)
						hy=siy[SCIData/10]+siyScale*(SCIData%10);
					else if(SCIData==0)
						hy=spoint[0].y;
					else if(SCIData==100)
						hy=spoint[2].y;
					PointPath.moveTo(hx, hy);
				}
			}
			float px;
			float height = 0;
//			for(int i=1;i<hx.length;i=i+pointScale)
//			{
//				int temp=mmainset.RecCSI.get(i+startX)&0xff;
//				if(temp>100)temp=0;
//				SCIData=100-temp;
//				hy[i]=spoint[2].y;			
//				if(SCIData<100)
//					hy[i]=siy[SCIData/10]+siyScale*(SCIData%10);
//				else if(SCIData==0)
//					hy[i]=spoint[0].y;
//				else if(SCIData==100)
//					hy[i]=spoint[2].y;
//				hx[i]=six[(i)/scale]-xScale*(scale-i%scale-1)+xScale/6;
//				PointPath.lineTo(hx[i], hy[i]);
//
//			}
			for(int i=1;i<dataSize+1;i=i+pointScale)
            {
                int temp = mmainset.RecCSI.get(startX+i)&0xff;
                if(temp>100)
                    temp=0;
                SCIData=100-temp;
                hy=spoint[2].y;;         
                if(SCIData<100)
                    hy=siy[SCIData/10]+siyScale*(SCIData%10);
                else if(SCIData==0)
                    hy=spoint[0].y;
                else if(SCIData==100)
                    hy=spoint[2].y;
                if(i<scale)
                {
                    hx=spoint[0].x+xScale*(i%scale)+xScale/6;
                }
                else
                {
                    hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
                }
                PointPath.lineTo(hx, hy);
            }
			
			if(dataSize>0)
			{
				PointPath.moveTo(hx, hy);
			}
		}
		mCanvas.drawPath(PointPath, mCSILinePaint);
		if(cursix!=-1)
		{
			int temp=(int)((cursix-spoint[0].x)/xScale)+startX+1;
			if(DataNum>=mmainset.RecCSI.size())
			{
				DataNum=mmainset.RecCSI.size()-1;
			}
			else
			{
				DataNum=temp;
			}
			if((temp<mmainset.RecCSI.size())&&(temp>0))
			{
				int data=mmainset.RecCSI.get(temp);
				int hour=(temp+mmainset.curminTime)/3600;
				int min=(temp+mmainset.curminTime)%3600/60;
				int second=(temp+mmainset.curminTime)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if((data&0xff)>100){
					if (hour > 0) {

						mCanvas.drawText("(" + strhour + ":" + strmin + ":"
								+ strsec + "," + "▂ ▂"+ ")", cursix,
								spoint[0].y + 40, CurCSIPaint);
					} else {
						mCanvas.drawText("(" + strhour + ":" + strmin + ":"
								+ strsec + "," + "▂ ▂"+ ")", cursix,
								spoint[0].y + 40, CurCSIPaint);
					}
				}else{
					if (hour > 0) {
						mCanvas.drawText("(" + strhour + ":" + strmin + ":"
								+ strsec + "," + Integer.toString(data & 0xff)
								+ ")", cursix, spoint[0].y + 40, CurCSIPaint);
					} else {
						mCanvas.drawText("(" + strhour + ":" + strmin + ":"
								+ strsec + "," + Integer.toString(data & 0xff)
								+ ")", cursix, spoint[0].y + 40, CurCSIPaint);
					}
				}
				mCanvas.drawLine(cursix, spoint[0].y, cursix, spoint[2].y, mSLine2ePaint);
			}
		}
		PointPath.close();
		for(int j=0;j<bmpuparray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapDown, bmpuparray.get(j)-8, bmpuparray.get(j+1), null);
		}
		for(int j=0;j<bmpdownarray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapUp, bmpdownarray.get(j)-8, bmpdownarray.get(j+1), null);
		}
		
		for(int k=0,j=0;k<=pointSize;k=k+6*timeScale,j=j+6)
		{
			float x;//x1[]=new int[19];
			x=spoint[0].x+j*six1Scale;
		//	int scale=timeScale;
			if(k%6==0)
			{
				int hour=(mmainset.curminTime+startX+1+k)/3600;
				int min=(mmainset.curminTime+startX+1+k)%3600/60;
				int second=(mmainset.curminTime+startX+1+k)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
			}
		}
		float Scale=seeg25Scale;
		int ScaleSize = 6;
		int StartSize;
		int EndSize;
		int SeStartSize;
		int SeEndSize;
		int number=0;
		int eegScaleSet=mmainset.eegrecScale;
		if(eegScaleSet==1){
			Scale=seeg12Scale;
			ScaleSize=12;
			mCanvas.drawText("12.5mm/s", spoint[0].x+4, biy[9]+10, CurCSIPaint);
		}else if(eegScaleSet==2){
			Scale=seeg25Scale;
			ScaleSize=6;
			mCanvas.drawText("25mm/s", spoint[0].x+4, biy[9]+10, CurCSIPaint);
		}else if(eegScaleSet==3){
			Scale=seeg50Scale;
			ScaleSize=3;
			mCanvas.drawText("50mm/s", spoint[0].x+4, biy[9]+10, CurCSIPaint);
		}
		StartSize = eegmovereclen;
		EndSize = StartSize+ScaleSize;
		if(StartSize<0){
			StartSize=0;
			eegmovereclen=0;
		}
		if(EndSize>mmainset.RecEEG.size()){
			EndSize=mmainset.RecEEG.size();
			StartSize=mmainset.RecEEG.size()-ScaleSize;
		}else if(StartSize>=(mmainset.RecEEG.size()-ScaleSize))
		{
			StartSize=mmainset.RecEEG.size()-ScaleSize;
			eegmovereclen=mmainset.RecEEG.size()-ScaleSize;
			EndSize=mmainset.RecEEG.size();
		}
		if(StartSize<0){
			StartSize=0;
			eegmovereclen=0;
			EndSize=mmainset.RecEEG.size();
		}
		
		Integer[] eegData=new Integer[100];
		PointPath.reset();
		mLinePath.reset();
		miLinePath.reset();
		float yScale=(spoint[5].y-spoint[3].y)/8;
		mCanvas.drawLine(spoint[3].x,spoint[3].y ,spoint[6].x, spoint[3].y, mGLinePaint);
		for(int i=1;i<ScaleSize+1;i++)
		{
			mCanvas.drawLine(spoint[0].x+Scale*i, spoint[5].y, spoint[0].x+Scale*i, spoint[3].y, mGLinePaint);
		}
		for(int i=1;i<8;i++)
		{
			if(i!=4)
				mCanvas.drawLine(spoint[0].x, spoint[3].y+yScale*i, spoint[6].x, spoint[3].y+yScale*i, mGLinePaint);
		}
		
		for(int i=StartSize;i<EndSize;i++){
			int hour;//=(startX+1+k)/3600;
			int min;//=(startX+1+k)%3600/60;
			int second;//=(startX+1+k)%60;
			hour=(i)/3600;
			min=(i)%3600/60;
			second=(i)%60;
			String strhour=Integer.toString(hour);
			String strmin=Integer.toString(min);
			
			if(min<10)
			{
				strmin="0"+strmin;
			}
			String strsec=Integer.toString(second);
			if(second<10)
			{
				strsec="0"+strsec;
			}
			if(hour<=0)
				mCanvas.drawText(strmin+":"+strsec, ipoint[0].x+(i-StartSize)*Scale-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+6,CoordinatePaint);
			else
				mCanvas.drawText(strhour+":"+strmin+":"+strsec, ipoint[0].x+(i-StartSize)*Scale-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+6,CoordinatePaint);
		}
		hx = spoint[0].x;
        hy = spoint[5].y;
        int fudu=mmainset.fudu;
        int max=200;
		int fuduScale=1;
		if(fudu==1){
			mCanvas.drawText("5uV", six[16]-20, biy[9]+10, CurCSIPaint);
			max=20;
			fuduScale=10;
		}else if(fudu==2){
			mCanvas.drawText("10uV", six[16]-20, biy[9]+10, CurCSIPaint);
			max=40;
			fuduScale=5;
		}else if(fudu==3){
			mCanvas.drawText("25uV", six[16]-20, biy[9]+10, CurCSIPaint);
			max=100;
			fuduScale=2;
		}else if(fudu==4){
			mCanvas.drawText("50uV", six[16]-20, biy[9]+10, CurCSIPaint);
			max=200;
			fuduScale=1;
		}
		mCanvas.drawText(Integer.toString(max),spoint[0].x-30, spoint[3].y+8, CoordTextPaint);
		mCanvas.drawText("0",spoint[1].x-12, spoint[4].y+8, CoordTextPaint);
		mCanvas.drawText(Integer.toString(-max),spoint[2].x-38, spoint[5].y-2, CoordTextPaint);
		if(StartSize<=0){
			miLinePath.moveTo(spoint[0].x, spoint[4].y);
		}else{
			//Integer[] eegData=new Integer[100];
			eegData=mmainset.RecEEG.get(StartSize);
			//Log.i(TAG, "liuliangxiang eegData0="+((signed char)eegData[0]));
			int temp=eegData[0];
			if(temp>127){
				temp=(256-temp);
				temp=temp*45/32;
				if(temp>=max)temp=max;
				hy=spoint[4].y+temp*fuduScale*seegyScale;
			}else{
				temp=temp*45/32;
				hy=spoint[4].y-temp*fuduScale*seegyScale;
			}
			miLinePath.moveTo(spoint[0].x, hy);
		}
		if((EndSize>0)&&(mmainset.RecEEG.size()>0)){
			for(int i = StartSize;i<EndSize;i++){
				//Integer[] eegData=new Integer[100];
				eegData=mmainset.RecEEG.get(i);
				
				for(int j=0;j<100;j=j+1){
				//	Log.i(TAG, "liuliangxiang eegData["+j+"]"+eegData[j]);
					if(eegData[j]==null)break;
					int data=(int)(eegData[j]);
					if(data>127){
						data=(256-data);
						data=data*45/32;
						if(data>=max)data=max;
						hy=spoint[4].y+data*fuduScale*seegyScale;
					}else{
						data=data*45/32;
						if(data>max)data=max;
						hy=spoint[4].y-data*fuduScale*seegyScale;
					};
					hx=spoint[0].x+Scale*number+j*Scale/100;
					miLinePath.lineTo(hx, hy);
				}
				number++;
			}
		}
		mCanvas.drawPath(miLinePath, mCSILinePaint);
//	//	mCanvas.drawPoint(hx,hy,mELinePaint);
		miLinePath.close();
	}



	
	public void drawscoord()
	{
		mCanvas.drawLine(spoint[0].x, spoint[0].y, spoint[2].x, spoint[2].y, CoordinatePaint);
		mCanvas.drawLine(spoint[3].x, spoint[3].y, spoint[5].x, spoint[5].y, CoordinatePaint);
	//	mCanvas.drawLine(spoint[1].x, spoint[1].y, spoint[6].x, spoint[6].y, CoordinatePaint);
		mCanvas.drawLine(spoint[2].x, spoint[2].y, spoint[7].x, spoint[7].y, CoordinatePaint);
	//	mCanvas.drawLine(spoint[4].x, spoint[4].y, spoint[8].x, spoint[8].y, CoordinatePaint);
		mCanvas.drawLine(spoint[5].x, spoint[5].y, spoint[9].x, spoint[9].y, CoordinatePaint);
		
		for(int i=0;i<siy.length;i++)
		{
			mCanvas.drawLine(spoint[0].x, siy[i], spoint[6].x, siy[i], mGLinePaint);
		}
		for(int j=0;j<six.length;j++)
		{
			mCanvas.drawLine(six[j],spoint[0].y ,six[j], spoint[2].y, mGLinePaint);
		}
		
		for(int i=0;i<biy.length;i++)
		{
			mCanvas.drawLine(spoint[3].x, biy[i], spoint[7].x, biy[i], mGLinePaint);
		}
		for(int j=0;j<bix.length;j++)
		{
			mCanvas.drawLine(bix[j],spoint[3].y ,bix[j], spoint[5].y, mGLinePaint);
		}
		
		mCanvas.drawText("100",spoint[0].x-28, siy[0]+8, CoordTextPaint);
		mCanvas.drawText("80",spoint[1].x-20, siy[2]+8, CoordTextPaint);
		mCanvas.drawText("60",spoint[1].x-20, siy[4]+8, CoordTextPaint);
		mCanvas.drawText("40",spoint[1].x-20, siy[6]+8, CoordTextPaint);
		mCanvas.drawText("20",spoint[1].x-20, siy[8]+8, CoordTextPaint);
		mCanvas.drawText("0",spoint[2].x-12, spoint[2].y-2, CoordTextPaint);
		
		mCanvas.drawText("100",spoint[0].x-28, biy[0]+8, CoordTextPaint);
		mCanvas.drawText("80",spoint[1].x-20, biy[2]+8, CoordTextPaint);
		mCanvas.drawText("60",spoint[1].x-20, biy[4]+8, CoordTextPaint);
		mCanvas.drawText("40",spoint[1].x-20, biy[6]+8, CoordTextPaint);
		mCanvas.drawText("20",spoint[1].x-20, biy[8]+8, CoordTextPaint);
		mCanvas.drawText("0",spoint[2].x-12, spoint[5].y-2, CoordTextPaint);
		mCanvas.drawText("CSI", six[16]-30, siy[1]-2, CurCSIPaint);
		mCanvas.drawText("BS", six[16]-26, biy[1]-2, BSTextCoordiatePaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		float xScale=ix1Scale;
		timeScale=mmainset.majuitimescale;
		scale=1;
		int endXSize;
		int startX2;
		int endX2Size;
		if(timeScale==1){
			mCanvas.drawText("/1sec", six[16]+4, siy[1]-2, CurCSIPaint);
		}
		else if(timeScale==2)
		{
			xScale=ix2Scale;
			pointSize=36;
			pointScale=1;
			scale=2;
			mCanvas.drawText("/2sec", six[16]+4, siy[1]-2, CurCSIPaint);
		}
		else if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
			mCanvas.drawText("/5sec", six[16]+4, siy[1]-2, CurCSIPaint);
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
			mCanvas.drawText("/10sec", six[16]+4, siy[1]-2, CurCSIPaint);
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
			mCanvas.drawText("/1min", six[16]+4, siy[1]-2, CurCSIPaint);
		}
		else if(timeScale==120)
		{
			xScale=ix120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
			mCanvas.drawText("/2min", six[16]+4, siy[1]-2, CurCSIPaint);
		}
		
		int value;
		int bannerSelect=mmainset.bannerSelect;
		float bannerX;
		float bannerYS;
		float bannerYL;

		bannerX = spoint[0].x;
		bannerYS = spoint[0].y;
		int tempValue = mmainset.shangxianshezValue;
		if (tempValue > 100)
			tempValue = 0;
		value = 100 - tempValue;
		if (value < 100)
			bannerYS = siy[value / 10] + siyScale * (value % 10);
		else if (value == 0)
			bannerYS = spoint[0].y;
		else if (value == 100)
			bannerYS = spoint[2].y;
		bannerYL = spoint[2].y;
		tempValue = mmainset.xiaxianshezValue;
		if (tempValue > 100)
			tempValue = 0;
		value = 100 - tempValue;
		if (value < 100)
			bannerYL = siy[value / 10] + siyScale * (value % 10);
		else if (value == 0)
			bannerYL = spoint[0].y;
		else if (value == 100)
			bannerYL = spoint[2].y;
		if(bannerSelect==1){
			mCanvas.drawLine(bannerX, bannerYS, spoint[6].x, bannerYS, shandowPaint);
			mCanvas.drawLine(bannerX, bannerYL, spoint[6].x, bannerYL, shandowPaint);
		}else if(bannerSelect==2){
			mCanvas.drawRect(bannerX, bannerYS,spoint[6].x,bannerYL,shandowPaint);
		}
	//	Log.i(TAG,"liuliangxiang timeScale ="+timeScale);
		int starttemp=(mmainset.CSI.size()-1)/pointSize*pointSize;
		startX=starttemp-1;
		endXSize=mmainset.CSI.size()-startX;
		if(mmainset.CSI.size()%pointSize==0)
		{
			startX2=-2;
			endX2Size=-1;
		}
		else
		{
			startX2=starttemp-pointSize;
			endX2Size=pointSize+1;
		}		
		for(int k=0,j=0;k<=pointSize;k=k+6*timeScale,j=j+6)
		{
			{
				float x;//x1[]=new int[19];
				x=ipoint[0].x+j*ix1Scale;
				int hour;//=(startX+1+k)/3600;
				int min;//=(startX+1+k)%3600/60;
				int second;//=(startX+1+k)%60;
				if((k>endXSize)&&(startX2>=0))
				{
					hour=(startX-pointSize+1+k)/3600;
					min=(startX-pointSize+1+k)%3600/60;
					second=(startX-pointSize+1+k)%60;
				}
				else
				{
					hour=(startX+1+k)/3600;
					min=(startX+1+k)%3600/60;
					second=(startX+1+k)%60;
				}
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
			}
		}
		miLinePath.reset();
		mLinePath.reset();
		float hx = spoint[0].x;
		float hy = spoint[2].y;
		int SCIData=0;
		
		if(startX<=0)
		{
			miLinePath.moveTo(spoint[1].x,spoint[2].y);
		}
		else
		{
			hx=spoint[0].x;
			hy=spoint[2].y;
			int temp = mmainset.CSI.get(startX)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			if(SCIData<100)
				hy=siy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[0].y;
			else if(SCIData==100)
				hy=spoint[2].y;
			miLinePath.moveTo(hx, hy);
			miLinePath.lineTo(hx, hy);
		}
		int temp = 0;

		for(int i=1;i<endXSize;i=i+pointScale)
		{
			temp = mmainset.CSI.get(i+startX)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=ipoint[1].y; 		
			if(SCIData<100)
				hy=siy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[0].y;
			else if(SCIData==100)
				hy=spoint[2].y;
			if(i<scale)
			{
				hx=spoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			miLinePath.lineTo(hx, hy);
		}
		miLinePath.moveTo(hx,hy);
		if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			mCanvas.drawPath(miLinePath, mCSILinePaint);
		miLinePath.close();		
		if((startX2>-1))
		{	
			if((endXSize)<scale)
			{
				hx=spoint[0].x+xScale*((endXSize)%scale);
			}
			else
			{
				hx=six[(endXSize)/scale-1]+xScale*((endXSize)%scale);
			}
	
			temp = mmainset.CSI.get(startX2+endXSize)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=spoint[2].y; 		
			if(SCIData<100)
				hy=siy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[0].y;
			else if(SCIData==100)
				hy=spoint[2].y;
			mLinePath.moveTo(hx,hy);
			//int j=1;
			for(int i=endXSize+1;i<endX2Size;i=i+pointScale)
			{
				temp = mmainset.CSI.get(i+startX2)&0xff;
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				hy=spoint[2].y; 		
				if(SCIData<100)
					hy=siy[SCIData/10]+siyScale*(SCIData%10);
				else if(SCIData==0)
					hy=spoint[0].y;
				else if(SCIData==100)
					hy=spoint[2].y;
				if(i<scale)
				{
					hx=spoint[0].x+xScale*(i%scale)+xScale/6;
				}
				else
				{
					hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
				}
				mLinePath.lineTo(hx, hy);
			}
			mLinePath.moveTo(hx,hy);
			if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
				mCanvas.drawPath(mLinePath, mSlinePaint1);
		}
		mLinePath.close();
		miLinePath.reset();
		mLinePath.reset();
	
		pointSize=18;
		pointScale=1;
		xScale=ix1Scale;
		timeScale=mmainset.majuitimescale;
		scale=1;
		if(timeScale==1){
			mCanvas.drawText("/1sec", six[16]+4, biy[1]-2, BSTextCoordiatePaint);
		}
		else if(timeScale==2)
		{
			xScale=ix2Scale;
			pointSize=36;
			pointScale=1;
			scale=2;
			mCanvas.drawText("/2sec", six[16]+4, biy[1]-2, BSTextCoordiatePaint);
		}
		else if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
			mCanvas.drawText("/5sec", six[16]+4, biy[1]-2, BSTextCoordiatePaint);
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
			mCanvas.drawText("/10sec", six[16]+4, biy[1]-2, BSTextCoordiatePaint);
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
			mCanvas.drawText("/1min", six[16]+4, biy[1]-2, BSTextCoordiatePaint);
		}
		else if(timeScale==120)
		{
			xScale=ix120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
			mCanvas.drawText("/2min", six[16]+4, biy[1]-2, BSTextCoordiatePaint);
		}
		starttemp=(mmainset.BS.size()-1)/pointSize*pointSize;
		startX=starttemp-1;
		endXSize=mmainset.BS.size()-startX;
		if(mmainset.BS.size()%pointSize==0)
		{
			startX2=-2;
			endX2Size=-1;
		}
		else
		{
			startX2=starttemp-pointSize;
			endX2Size=pointSize+1;
		}		
		for(int k=0,j=0;k<=pointSize;k=k+6*timeScale,j=j+6)
		{
			{
				float x;//x1[]=new int[19];
				x=ipoint[0].x+j*ix1Scale;
				int hour;//=(startX+1+k)/3600;
				int min;//=(startX+1+k)%3600/60;
				int second;//=(startX+1+k)%60;
				if((k>endXSize)&&(startX2>=0))
				{
					hour=(startX-pointSize+1+k)/3600;
					min=(startX-pointSize+1+k)%3600/60;
					second=(startX-pointSize+1+k)%60;
				}
				else
				{
					hour=(startX+1+k)/3600;
					min=(startX+1+k)%3600/60;
					second=(startX+1+k)%60;
				}
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, spoint[5].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, spoint[5].y+12,CoordinatePaint);
			}
		}
		miLinePath.reset();
		mLinePath.reset();
		hx = spoint[0].x;
		hy = spoint[5].y;
		SCIData=0;
		
		if(startX<=0)
		{
			miLinePath.moveTo(spoint[1].x,spoint[5].y);
		}
		else
		{
			hx=spoint[0].x;
			hy=spoint[5].y;
			temp = mmainset.BS.get(startX)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			if(SCIData<100)
				hy=biy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[3].y;
			else if(SCIData==100)
				hy=spoint[5].y;
			miLinePath.moveTo(hx, hy);
			miLinePath.lineTo(hx, hy);
		}
		temp = 0;

		for(int i=1;i<endXSize;i=i+pointScale)
		{
			temp = mmainset.BS.get(i+startX)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=spoint[5].y; 		
			if(SCIData<100)
				hy=biy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[3].y;
			else if(SCIData==100)
				hy=spoint[5].y;
			if(i<scale)
			{
				hx=spoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			miLinePath.lineTo(hx, hy);
		}
		miLinePath.moveTo(hx,hy);
		if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			mCanvas.drawPath(miLinePath, mSLinePaint);
		miLinePath.close();		
		if((startX2>-1))
		{	
			if((endXSize)<scale)
			{
				hx=spoint[0].x+xScale*((endXSize)%scale);
			}
			else
			{
				hx=six[(endXSize)/scale-1]+xScale*((endXSize)%scale);
			}
	
			temp = mmainset.BS.get(startX2+endXSize)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=spoint[2].y; 		
			if(SCIData<100)
				hy=biy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[3].y;
			else if(SCIData==100)
				hy=spoint[5].y;
			mLinePath.moveTo(hx,hy);
			//int j=1;
			for(int i=endXSize+1;i<endX2Size;i=i+pointScale)
			{
				temp = mmainset.BS.get(i+startX2)&0xff;
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				hy=spoint[5].y; 		
				if(SCIData<100)
					hy=biy[SCIData/10]+siyScale*(SCIData%10);
				else if(SCIData==0)
					hy=spoint[3].y;
				else if(SCIData==100)
					hy=spoint[5].y;
				if(i<scale)
				{
					hx=spoint[0].x+xScale*(i%scale)+xScale/6;
				}
				else
				{
					hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
				}
				mLinePath.lineTo(hx, hy);
			}
			mLinePath.moveTo(hx,hy);
			if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
				mCanvas.drawPath(mLinePath, mSlinePaint1);
		}
		mLinePath.close();
		miLinePath.reset();
		mLinePath.reset();
	}
	
	public void drawscoorddongjie()
	{
		mCanvas.drawLine(spoint[0].x, spoint[0].y, spoint[2].x, spoint[2].y, CoordinatePaint);
		mCanvas.drawLine(spoint[3].x, spoint[3].y, spoint[5].x, spoint[5].y, CoordinatePaint);
		mCanvas.drawLine(spoint[1].x, spoint[1].y, spoint[6].x, spoint[6].y, CoordinatePaint);
		mCanvas.drawLine(spoint[2].x, spoint[2].y, spoint[7].x, spoint[7].y, CoordinatePaint);
		mCanvas.drawLine(spoint[4].x, spoint[4].y, spoint[8].x, spoint[8].y, CoordinatePaint);
	//	mCanvas.drawLine(spoint[5].x, spoint[5].y, spoint[9].x, spoint[9].y, CoordinatePaint);
		
		mCanvas.drawText("100",spoint[0].x-28, spoint[0].y+8, CoordTextPaint);
		mCanvas.drawText("50",spoint[1].x-20, spoint[1].y+8, CoordTextPaint);
		mCanvas.drawText("0",spoint[2].x-12, spoint[2].y-4, CoordTextPaint);
		
		mCanvas.drawText("128", spoint[3].x-30, spoint[3].y+8, CoordTextPaint);
		mCanvas.drawText("0uv", spoint[4].x-24, spoint[4].y+8,CoordTextPaint);
		mCanvas.drawText("-128",spoint[5].x-36, spoint[5].y+2, CoordTextPaint);
		mCanvas.drawText("CSI", six[16]+4, siy[1]-2, WhitePaint);
		mCanvas.drawText("EEG", sex[28], sey[6], WhitePaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		//int CSIData=
		float xScale=six1Scale;
		timeScale=mmainset.majuitimescale;
		scale=1;
		if(timeScale==5)
		{
			xScale=six5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			xScale=six10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=six60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=six120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		if(scimovedongjielen<0)
		{
			scimovedongjielen=0;
		}
		startX=mmainset.csidongjielen-pointSize-1-scimovedongjielen;
		dataSize=pointSize;
		if(startX<-1)
		{
			startX=-1;
			scimovedongjielen=mmainset.csidongjielen-pointSize;
		}
		if(mmainset.csidongjielen<=(pointSize))
		{
			startX=-1;
			dataSize=mmainset.csidongjielen;
		}
		CurDataLen=startX+dataSize;
		float[] hx=new float[dataSize+1];
		float[] hy=new float[dataSize+1];
	//	Log.i(TAG,"liuliangxiang hx="+hx.length);
		int SCIData=0;
        if(mmainset.CSI.size()>0)
		{
			PointPath.reset();
			bmpdownarray.clear();
			bmpuparray.clear();
			if(mmainset.CSI.size()>0)
			{	
				if((mmainset.csidongjielen<=pointSize)||(startX<0))
				{
					PointPath.moveTo(spoint[2].x, spoint[2].y);
				}
				else
				{
					hx[0]=spoint[0].x;
					hy[0]=spoint[2].y;
					int temp=mmainset.CSI.get(startX)&0xff;
					if(temp>100)temp=0;
					SCIData=100-temp;
					if(SCIData<100)
						hy[0]=siy[SCIData/10]+siyScale*(SCIData%10);
					else if(SCIData==0)
						hy[0]=spoint[0].y;
					else if(SCIData==100)
						hy[0]=spoint[2].y;
					PointPath.moveTo(hx[0], hy[0]);
				}
			}
			float px;
			float height = 0;
			for(int i=1;i<hx.length;i=i+pointScale)
			{
				int temp=mmainset.CSI.get(i+startX)&0xff;
				if(temp>100)temp=0;
				SCIData=100-temp;
				hy[i]=spoint[2].y;			
				if(SCIData<100)
					hy[i]=siy[SCIData/10]+siyScale*(SCIData%10);
				else if(SCIData==0)
					hy[i]=spoint[0].y;
				else if(SCIData==100)
					hy[i]=spoint[2].y;
				hx[i]=six[(i)/scale-1]-xScale*(scale-i%scale-1)+xScale/6;
				PointPath.lineTo(hx[i], hy[i]);

			}
			if(dataSize>0)
			{
				PointPath.moveTo(hx[dataSize], hy[dataSize]);
			}
		}
		mCanvas.drawPath(PointPath, mSLinePaint);
		if(cursix!=-1)
		{
			int temp=(int)((cursix-spoint[0].x)/xScale)+startX+1;
			if(temp>=mmainset.csidongjielen)
				DataNum=temp-1;
			else
				DataNum=temp;
			if((temp<mmainset.csidongjielen)&&(temp>0))
			{
				int data=mmainset.CSI.get(temp);
				int hour=(temp)/3600;
				int min=(temp)%3600/60;
				int second=(temp)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				mCanvas.drawText("("+strmin+":"+strsec+","+Integer.toString(data&0xff)+")", cursix, spoint[0].y+40, BSTextPaint);
				mCanvas.drawLine(cursix, spoint[0].y, cursix, spoint[2].y, mSLine2ePaint);
			}
			
		}
		PointPath.close();
		for(int j=0;j<bmpuparray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapDown, bmpuparray.get(j)-8, bmpuparray.get(j+1), null);
		}
		for(int j=0;j<bmpdownarray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapUp, bmpdownarray.get(j)-8, bmpdownarray.get(j+1), null);
		}
		
		for(int k=0;k<=18;k=k+6)
		{
			float x;//x1[]=new int[19];
			x=spoint[0].x+k*six1Scale;
		//	int scale=timeScale;
			if(k%6==0)
			{
				int hour=(startX+1+k*timeScale)/3600;
				int min=(startX+1+k*timeScale)%3600/60;
				int second=(startX+1+k*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
			}
		}
	

		pointSize=300;
		pointScale=1;
		boolean isLess=false;
		//int CSIData=
		xScale=sex1Scale;
		timeScale=mmainset.eegtimescale;
		int eegLen=mmainset.eegdongjielen;
		//Log.i(TAG,"Liuliangxiang eegLen="+eegLen);
		scale=10;
		
//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==5)
		{
			xScale=sex5Scale;
			pointSize=1500;
			pointScale=5;
			scale=50;
		}
		else if(timeScale==10)
		{
			xScale=sex10Scale;
			pointSize=3000;
			pointScale=10;
			scale=100;
		}
		else if(timeScale==60)
		{
			xScale=sex60Scale;
			pointSize=18000;
			pointScale=53;
			scale=600;
		}
		else if(timeScale==120)
		{
			xScale=sex120Scale;
			pointSize=36000;
			pointScale=95;
			scale=1200;
		}
		
		startX=(eegLen-pointSize/100)*100-eegmovedongjielen*100;
		if(startX<0)
		{
			startX=0;
			eegmovereclen=(eegLen-pointSize/100);
		}
		dataSize=pointSize;
		if(eegLen<=pointSize/100)
		{
			startX=0;
			dataSize=eegLen*100;
		}
	//	Log.i(TAG,"liuliangxiang eegLen="+eegLen);
		float hex = 0;
		float hey = 0;
		Integer[] EEGData=new Integer[100];
		float hyk;		
		if((dataSize>=100))
		{
			mLinePath.reset();			
			if(dataSize<=pointSize)
			{
				mLinePath.moveTo(spoint[4].x, spoint[4].y);
			}
			else
			{
				EEGData=mmainset.EEG.get(startX/100);
				hex=spoint[4].x;
				int temp=startX%100;
				
				if(EEGData[temp]>=0x80)
				{
					EEGData[temp]=0x7f-EEGData[temp];
				}
			//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
				if(EEGData[temp]>=16)
				{
					hyk=sey[EEGData[temp]/16-1]-seyScale*(EEGData[temp]%16);
				}
				else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
				{
					hyk=spoint[4].y-seyScale*(EEGData[temp]);
				}
				else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
				{
					hyk=spoint[4].y-EEGData[temp]*seyScale;
				}
				else
				{
					hyk=sey[-EEGData[temp]/16+7]-seyScale*(EEGData[temp]%16);
				}
				mLinePath.moveTo(hex, hyk);
			}
			int step = 1;
			if(scale==10)
			{
				step=1;
			}
			else if(scale==50)
			{
				step=2;
			}
			else if(scale==100)
			{
				step=5;
			}
			else if(scale==600)
			{
				step=29;
			}
			else if(scale==1200)
			{
				step=53;
			}
		//	Log.i(TAG,"liuliangxiang dataSize="+dataSize);
			if(isLess)
			{	
				for(int k=0;k<(dataSize-100+EEGflen);k=k+step)
				{
					//hx[k]=ex[k/10]-xScale*((10-k)%10);
					hex=sex[k/scale]-xScale*(scale-k%scale-1);
					int temp;
					EEGData=mmainset.EEG.get((startX+k)/100);
					temp=(startX+k)%100;
					if(EEGData[temp]>=0x80)
					{
						EEGData[temp]=0x7f-EEGData[temp];
					}
				//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
					if(EEGData[temp]>=16)
					{
						hey=sey[EEGData[temp]/16-1]-seyScale*(EEGData[temp]%16);
					}
					else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
					{
						hey=spoint[4].y-seyScale*(EEGData[temp]);
					}
					else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
					{
						hey=spoint[4].y-EEGData[temp]*seyScale;
					}
					else
					{
						hey=sey[-EEGData[temp]/16+7]-seyScale*(EEGData[temp]%16);
					}
					mLinePath.lineTo(hex, hey);
				}
			}
			else
			{
				//Log.i(TAG,"liuliangxiang isLess=false");
				for(int k=0;k<(dataSize);k=k+step)
				{
					//hx[k]=ex[k/10]-xScale*((10-k)%10);
					int temp;
					hex=sex[k/scale]-xScale*(scale-k%scale-1);
					temp=(startX+k)%100;
					EEGData=mmainset.EEG.get((startX+k)/100);				
					if(EEGData[temp]>=0x80)
					{
						EEGData[temp]=0x7f-EEGData[temp];
					}
				//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
					if(EEGData[temp]>=16)
					{
						hey=sey[EEGData[temp]/16-1]-seyScale*(EEGData[temp]%16);
					}
					else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
					{
						hey=spoint[4].y-seyScale*(EEGData[temp]);
					}
					else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
					{
						hey=spoint[4].y-EEGData[temp]*seyScale;
					}
					else
					{
						hey=ey[-EEGData[temp]/16+7]-eyScale*(EEGData[temp]%16);
					}
					mLinePath.lineTo(hex, hey);
				}
			}
		}
		
		if(dataSize>0)
		{
			mLinePath.moveTo(hex, hey);
		}
		mCanvas.drawPath(mLinePath, mELinePaint);
		if(cursex!=-1)
		{
			int temp=(int)((cursex-spoint[0].x)/xScale)+startX;
			if((temp/100)>=mmainset.eegdongjielen)
				DataNum=temp/100-1;
			else
				DataNum=temp/100;
		//	Log.i(TAG,"liuliangxiang temp="+temp/100);
			if(((temp/100)<mmainset.eegdongjielen)&&(temp>0))
			{
				//int data=mmainset.RecCSI.get(temp);
				Integer[] data1=mmainset.EEG.get(temp/100);
				int data=(int)data1[temp%100];
				int hour=(temp/100)/3600;
				int min=(temp/100)%3600/60;
				int second=(temp/100)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				mCanvas.drawText("("+strmin+":"+strsec+","+Integer.toString(temp%100)+","+Integer.toString(data)+")", cursex, spoint[3].y+40, BSTextPaint);
				mCanvas.drawLine(cursex, spoint[3].y, cursex, spoint[5].y, mSLine2ePaint);
			}
			
		}
		mLinePath.close();
		for(int k=0;k<=30;k=k+10)
		{
			float x;//x1[]=new int[19];

			x=spoint[0].x+(k*10)*sex1Scale;
			{
				int hour=(startX/100+(k/10)*timeScale)/3600;
				int min=(startX/100+(k/10)*timeScale)%3600/60;
				int second=(startX/100+(k/10)*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, spoint[5].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, spoint[5].y+12,CoordinatePaint);
			}
		}	
	}
	

/*	
	public void drawscoorddongjie()
	{
		mCanvas.drawLine(spoint[0].x, spoint[0].y, spoint[2].x, spoint[2].y, CoordinatePaint);
		mCanvas.drawLine(spoint[3].x, spoint[3].y, spoint[5].x, spoint[5].y, CoordinatePaint);
		mCanvas.drawLine(spoint[1].x, spoint[1].y, spoint[6].x, spoint[6].y, CoordinatePaint);
		mCanvas.drawLine(spoint[2].x, spoint[2].y, spoint[7].x, spoint[7].y, CoordinatePaint);
		mCanvas.drawLine(spoint[4].x, spoint[4].y, spoint[8].x, spoint[8].y, CoordinatePaint);
	//	mCanvas.drawLine(spoint[5].x, spoint[5].y, spoint[9].x, spoint[9].y, CoordinatePaint);
		
		mCanvas.drawText("100",spoint[0].x-28, spoint[0].y+8, CoordTextPaint);
		mCanvas.drawText("50",spoint[1].x-20, spoint[1].y+8, CoordTextPaint);
		mCanvas.drawText("0",spoint[2].x-12, spoint[2].y+8, CoordTextPaint);
		
		mCanvas.drawText("128", spoint[3].x-28, spoint[3].y+8, CoordTextPaint);
		mCanvas.drawText("0uv", spoint[4].x-24, spoint[4].y+8,CoordTextPaint);
		mCanvas.drawText("-128",spoint[5].x-28, spoint[5].y+8, CoordTextPaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		//int CSIData=
		float xScale=six1Scale;
		timeScale=mmainset.majuitimescale;
		scale=1;
		
//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==5)
		{
			xScale=six5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			xScale=six10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=six60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=six120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		
		if(mmainset.csidongjielen>(pointSize))
		{
			startX=(mmainset.csidongjielen-pointSize-1);
			dataSize=pointSize;
		}
		else
		{
			startX=-1;
			dataSize=mmainset.csidongjielen-1;
		}
		if(startX<0)
		{
			startX=0;
			dataSize=mmainset.csidongjielen-1;
		}
		float[] hx=new float[dataSize+1];
		float[] hy=new float[dataSize+1];
		int SCIData=0;

		//if(preDataSize!=mmainset.CSI.size())
		{
			PointPath.reset();
			preDataSize=mmainset.csidongjielen;
			bmpdownarray.clear();
			bmpuparray.clear();
			if(mmainset.CSI.size()>0)
			{
				
				hx[0]=spoint[0].x;
				hy[0]=spoint[2].y;				
				if(mmainset.CSI.size()<=18)
				{
					PointPath.moveTo(spoint[2].x, spoint[2].y);
					//mLinePath.lineTo(spoint[2].x, spoint[2].y);
				}
				else
				{
					int temp=mmainset.CSI.get(startX)&0xff;
					if(temp>100)temp=0;
					SCIData=100-temp;
					if(SCIData<100)
						hy[0]=siy[SCIData/10]+siyScale*(SCIData%10);
					else if(SCIData==0)
						hy[0]=spoint[0].y;
					else if(SCIData==100)
						hy[0]=spoint[2].y;
					PointPath.moveTo(hx[0], hy[0]);
					//mLinePath.lineTo(hx[0], hy[0]);
				}
			}
			float px;
			float height = 0;
			for(int i=1;i<dataSize+1;i=i+pointScale)
			{
				int temp=mmainset.CSI.get(i+startX)&0xff;
				if(temp>100)temp=0;
				SCIData=100-temp;
				hy[i]=spoint[2].y;			
				if(SCIData<100)
					hy[i]=siy[SCIData/10]+siyScale*(SCIData%10);
				else if(SCIData==0)
					hy[i]=spoint[0].y;
				else if(SCIData==100)
					hy[i]=spoint[2].y;

				
				//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
				//hx[i]=spoint[0].x+(i+pointScale)*xScale+xScale/6;
				hx[i]=six[(i)/scale-1]-xScale*(scale-i%scale-1)+xScale/6;
				//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
				PointPath.lineTo(hx[i], hy[i]);
//				if(i>=pointScale)
//				{
//					if(((mmainset.CSI.get(i+startX)&0xff)>mmainset.getshangxianshezValue())&&((mmainset.CSI.get(i+startX-pointScale)&0xff)<mmainset.getshangxianshezValue()))
//					{
//						SCIData=100-mmainset.getshangxianshezValue();
//						if(SCIData<100)
//							height=siy[SCIData/10]+siyScale*(SCIData%10);
//						else if(SCIData==100)
//							height=ipoint[1].y;
//						if(timeScale>=10)
//						{
//							px=hx[i-1]+(xScale)/2;
//						}
//						else
//						{
//							px=hx[i-1]+(height-hy[i-1])*xScale/(hy[i]-hy[i-1]);
//							//px=hx[i]-(hy[i-1]-hy[i])*(mmainset.CSI.get(i*scale+startX)-mmainset.getshangxianshezValue())/(xScale);
//						}
//						bmpuparray.add(px);
//						bmpuparray.add(height);
//					//	mCanvas.drawBitmap(bitmapDown, px-2, height, null);
//					}
				

//				if(((mmainset.CSI.get(i+startX)&0xff)<mmainset.getxiaxianshezValue())&&((mmainset.CSI.get(i+startX-pointScale)&0xff)>mmainset.getxiaxianshezValue()))
//				{
//					SCIData=100-mmainset.getxiaxianshezValue();
//					if(SCIData<100)
//						height=siy[SCIData/10]+siyScale*(SCIData%10);
//					else if(SCIData==100)
//						height=spoint[0].y;
//					if(timeScale>=10)
//					{
//						px=hx[i-1]+(xScale)/2;
//					}
//					else{
//						//px=hx[i-1]+(mmainset.xiaxianshezValue-mmainset.CSI.get(i*scale+startX-1)&0xff)*(xScale)/(mmainset.CSI.get(i*scale+startX)&0xff-mmainset.CSI.get(i*scale+startX-1)&0xff);
//						px=hx[i]-(height-hy[i])*xScale/(hy[i-1]-hy[i]);
//					}
//					bmpdownarray.add(px);
//					bmpdownarray.add(height);
//				//	mCanvas.drawBitmap(bitmapUp, px-2, height, null);
//				}
//				}

			}
			if(dataSize>0)
			{
				PointPath.moveTo(hx[dataSize-1], hy[dataSize-1]);
				Log.i(TAG,"SCIData="+SCIData);
				Log.i(TAG,"hy["+(mmainset.CSI.size()-1)+"]"+hy[dataSize-1]);
			}
		}
		mCanvas.drawPath(PointPath, mSLinePaint);
		PointPath.close();
		for(int j=0;j<bmpuparray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapDown, bmpuparray.get(j)-8, bmpuparray.get(j+1), null);
		}
		for(int j=0;j<bmpdownarray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapUp, bmpdownarray.get(j)-8, bmpdownarray.get(j+1), null);
		}
		
		for(int k=0;k<=18;k=k+6)
		{
			float x;//x1[]=new int[19];
			x=spoint[0].x+k*six1Scale;
		//	int scale=timeScale;
			if(k%6==0)
			{
				int hour=(startX+1+k*timeScale)/3600;
				int min=(startX+1+k*timeScale)%3600/60;
				int second=(startX+1+k*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
			}
		}
	

		pointSize=300;
		pointScale=1;
		boolean isLess;
		//int CSIData=
		xScale=sex1Scale;
		timeScale=mmainset.eegtimescale;
		int eegLen=mmainset.EEG.size()/100;
		//Log.i(TAG,"Liuliangxiang eegLen="+eegLen);
		scale=10;
		
//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==5)
		{
			xScale=sex5Scale;
			pointSize=1500;
			pointScale=5;
			scale=50;
		}
		else if(timeScale==10)
		{
			xScale=sex10Scale;
			pointSize=3000;
			pointScale=10;
			scale=100;
		}
		else if(timeScale==60)
		{
			xScale=sex60Scale;
			pointSize=18000;
			pointScale=53;
			scale=600;
		}
		else if(timeScale==120)
		{
			xScale=sex120Scale;
			pointSize=36000;
			pointScale=95;
			scale=1200;
		}
		
		if(mmainset.eegdongjielen>(pointSize/100))
		{
			startX=((eegLen-1)*100-pointSize);
			//Log.i(TAG,"liuliangxiang startX="+startX);
			dataSize=pointSize;
			isLess=false;
		}
		else
		{
			startX=0;
			isLess=true;
			dataSize=mmainset.eegdongjielen;
		}
		//Log.i(TAG,"liuliangxiang dataSize="+dataSize);
	//	float[] hx=new float[dataSize];
	//	float[] hy=new float[dataSize];
		float hex = 0;
		float hey = 0;
		Integer[] EEGData=new Integer[100];
		if(preEEGDataSize!=eegLen)
		{
			preEEGDataSize=eegLen;
			EEGflen=0;
		}
		float hyk;		
		if((EEGflen<=100)&&(dataSize>=100))
		{
			mLinePath.reset();			
			if(dataSize<pointSize)
			{
				mLinePath.moveTo(spoint[4].x, spoint[4].y);
			}
			else
			{
				EEGData=mmainset.EEG.get(startX/100);
				hex=spoint[4].x;
				int temp=startX%100;
				
				if(EEGData[temp]>=0x80)
				{
					EEGData[temp]=0x7f-EEGData[temp];
				}
			//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
				if(EEGData[temp]>=16)
				{
					hyk=sey[EEGData[temp]/16-1]-seyScale*(EEGData[temp]%16);
				}
				else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
				{
					hyk=spoint[4].y-seyScale*(EEGData[temp]);
				}
				else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
				{
					hyk=spoint[4].y-EEGData[temp]*seyScale;
				}
				else
				{
					hyk=sey[-EEGData[temp]/16+7]-seyScale*(EEGData[temp]%16);
				}
				mLinePath.moveTo(hex, hyk);
			}
			int step = 1;
			if(scale==10)
			{
				step=1;
			}
			else if(scale==50)
			{
				step=2;
			}
			else if(scale==100)
			{
				step=5;
			}
			else if(scale==600)
			{
				step=29;
			}
			else if(scale==1200)
			{
				step=53;
			}
//			else if(scale==12000)
//			{
//				step=100;
//			}
			if(isLess)
			{	
				for(int k=0;k<(dataSize-100+EEGflen);k=k+step)
				{
					//hx[k]=ex[k/10]-xScale*((10-k)%10);
					hex=sex[k/scale]-xScale*(scale-k%scale-1);
					int temp;
					EEGData=mmainset.EEG.get((startX+k)/100);
					temp=(startX+k)%100;
					if(EEGData[temp]>=0x80)
					{
						EEGData[temp]=0x7f-EEGData[temp];
					}
				//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
					if(EEGData[temp]>=16)
					{
						hey=sey[EEGData[temp]/16-1]-seyScale*(EEGData[temp]%16);
					}
					else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
					{
						hey=spoint[4].y-seyScale*(EEGData[temp]);
					}
					else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
					{
						hey=spoint[4].y-EEGData[temp]*seyScale;
					}
					else
					{
						hey=sey[-EEGData[temp]/16+7]-seyScale*(EEGData[temp]%16);
					}
					mLinePath.lineTo(hex, hey);
				}
			}
			else
			{
				//Log.i(TAG,"liuliangxiang isLess=false");
				for(int k=0;k<(dataSize);k=k+step)
				{
					//hx[k]=ex[k/10]-xScale*((10-k)%10);
					int temp;
					hex=sex[k/scale]-xScale*(scale-k%scale-1);
					temp=(startX+k+EEGflen)%100;
					EEGData=mmainset.EEG.get((startX+k+EEGflen)/100);				
					if(EEGData[temp]>=0x80)
					{
						EEGData[temp]=0x7f-EEGData[temp];
					}
				//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
					if(EEGData[temp]>=16)
					{
						hey=sey[EEGData[temp]/16-1]-seyScale*(EEGData[temp]%16);
					}
					else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
					{
						hey=spoint[4].y-seyScale*(EEGData[temp]);
					}
					else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
					{
						hey=spoint[4].y-EEGData[temp]*seyScale;
					}
					else
					{
						hey=ey[-EEGData[temp]/16+7]-eyScale*(EEGData[temp]%16);
					}
					mLinePath.lineTo(hex, hey);
				}
			}
			if(scale==10)
				EEGflen=EEGflen+20;
			else
				EEGflen=EEGflen+20;
		}
		
	//	Log.i(TAG,"EEGflen="+EEGflen);
		if(dataSize>0)
		{
			mLinePath.moveTo(hex, hey);
		//	Log.i(TAG,"SCIData="+SCIData);
		//	Log.i(TAG,"hy["+(mmainset.CSI.size()-1)+"]"+hy[dataSize-1]);
		}
		mCanvas.drawPath(mLinePath, mELinePaint);
		mLinePath.close();
		for(int k=0;k<=30;k=k+10)
		{
			float x;//x1[]=new int[19];

			x=spoint[0].x+(k*10)*sex1Scale;
			//x=ex[k/10]-xScale*(9-k%10);
		//	int scale=timeScale;
			//if(k%6==0)
			{
				int hour=(startX/100+(k/10)*timeScale)/3600;
				int min=(startX/100+(k/10)*timeScale)%3600/60;
				int second=(startX/100+(k/10)*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, spoint[5].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, spoint[5].y+12,CoordinatePaint);
			}
		}	
	}
*/	
	public void drawscoordshow()
	{
		mCanvas.drawLine(spoint[0].x, spoint[0].y, spoint[2].x, spoint[2].y, CoordinatePaint);
		mCanvas.drawLine(spoint[3].x, spoint[3].y, spoint[5].x, spoint[5].y, CoordinatePaint);
	//	mCanvas.drawLine(spoint[1].x, spoint[1].y, spoint[6].x, spoint[6].y, CoordinatePaint);
		mCanvas.drawLine(spoint[2].x, spoint[2].y, spoint[7].x, spoint[7].y, CoordinatePaint);
	//	mCanvas.drawLine(spoint[4].x, spoint[4].y, spoint[8].x, spoint[8].y, CoordinatePaint);
		mCanvas.drawLine(spoint[5].x, spoint[5].y, spoint[9].x, spoint[9].y, CoordinatePaint);
		
		for(int i=0;i<siy.length;i++)
		{
			mCanvas.drawLine(spoint[0].x, siy[i], spoint[6].x, siy[i], mGLinePaint);
		}
		for(int j=0;j<six.length;j++)
		{
			mCanvas.drawLine(six[j],spoint[0].y ,six[j], spoint[2].y, mGLinePaint);
		}
		
		for(int i=0;i<biy.length;i++)
		{
			mCanvas.drawLine(spoint[3].x, biy[i], spoint[7].x, biy[i], mGLinePaint);
		}
		for(int j=0;j<bix.length;j++)
		{
			mCanvas.drawLine(bix[j],spoint[3].y ,bix[j], spoint[5].y, mGLinePaint);
		}
		
		mCanvas.drawText("100",spoint[0].x-28, spoint[0].y+8, CoordTextPaint);
		mCanvas.drawText("50",spoint[1].x-20, spoint[1].y+8, CoordTextPaint);
		mCanvas.drawText("0",spoint[2].x-12, spoint[2].y-4, CoordTextPaint);
		
		mCanvas.drawText("100",spoint[0].x-28, spoint[3].y+8, CoordTextPaint);
		mCanvas.drawText("50",spoint[1].x-20, spoint[4].y+8, CoordTextPaint);
		mCanvas.drawText("0",spoint[2].x-12, spoint[5].y-4, CoordTextPaint);
		mCanvas.drawText(mcontext.getString(R.string.csi_show), six[14]+4, siy[1]-2, CurCSIPaint);
		mCanvas.drawText(mcontext.getString(R.string.bs_show), sex[24], sey[6], BSTextCoordiatePaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		float xScale=ix1Scale;
		timeScale=10;
		scale=1;
		int endXSize;
		int startX2;
		int endX2Size;
		if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=ix120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		startscishowx++;
		//if((startscishowx>mmainset.CSISHOW.length)||(startscishowx<pointSize))
		if((startscishowx>mmainset.CSISHOW.length))
		{
			startscishowx=0;
		}
		//startscishowx=94;
		int starttemp=(startscishowx-1)/pointSize*pointSize;
		startX=starttemp-1;
		endXSize=startscishowx-startX;
		if(startscishowx%pointSize==0)
		{
			startX2=-2;
			endX2Size=-1;
		}
		else
		{
			startX2=starttemp-pointSize;
			endX2Size=pointSize+1;
		}		
		for(int k=0;k<=pointSize;k=k+6*timeScale)
		{
	//		Log.i(TAG,"liuliangxiang k="+k);
			{
				float x;//x1[]=new int[19];
				x=ipoint[0].x+k/timeScale*ix1Scale;
				int hour;//=(startX+1+k)/3600;
				int min;//=(startX+1+k)%3600/60;
				int second;//=(startX+1+k)%60;
				if((k>endXSize)&&(startX2>=0))
				{
					hour=(startX-pointSize+1+k)/3600;
					min=(startX-pointSize+1+k)%3600/60;
					second=(startX-pointSize+1+k)%60;
				}
				else
				{
					hour=(startX+1+k)/3600;
					min=(startX+1+k)%3600/60;
					second=(startX+1+k)%60;
				}
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
			}
		}
//		Log.i(TAG,"liuliangxiang endXSize="+endXSize);
		miLinePath.reset();
		mLinePath.reset();
		float hx = spoint[0].x;
		float hy = spoint[2].y;
		int SCIData=0;
		
		if(startX<=0)
		{
			miLinePath.moveTo(spoint[1].x,spoint[2].y);
		}
		else
		{
			hx=spoint[0].x;
			hy=spoint[2].y;
			int temp = mmainset.CSISHOW[startX]&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			if(SCIData<100)
				hy=siy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[0].y;
			else if(SCIData==100)
				hy=spoint[2].y;
			miLinePath.moveTo(hx, hy);
			miLinePath.lineTo(hx, hy);
		}
		int temp;

		for(int i=1;i<endXSize;i=i+pointScale)
		{
			temp = mmainset.CSISHOW[i+startX]&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=ipoint[1].y; 		
			if(SCIData<100)
				hy=siy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[0].y;
			else if(SCIData==100)
				hy=spoint[2].y;
			//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
			if(i<scale)
			{
				hx=spoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
			//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
			miLinePath.lineTo(hx, hy);
		}
		miLinePath.moveTo(hx,hy);
		mCanvas.drawPath(miLinePath, mCSILinePaint);
	//	mCanvas.drawPoint(hx,hy,mELinePaint);
		miLinePath.close();
		
		if(startX2>-1)
		{			
			if((endXSize)<scale)
			{
				hx=spoint[0].x+xScale*((endXSize)%scale);
			//	Log.i(TAG,"liuliangxiang endXSize11="+endXSize);
			}
			else
			{
				hx=six[(endXSize)/scale-1]+xScale*((endXSize)%scale);
			//	Log.i(TAG,"liuliangxiang endXSize22="+endXSize);
			}
	
			temp = mmainset.CSISHOW[startX2+endXSize]&0xff;
		//	Log.i(TAG,"liuliangxiang temp="+temp);
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=spoint[2].y; 		
			if(SCIData<100)
				hy=siy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[0].y;
			else if(SCIData==100)
				hy=spoint[2].y;
			mLinePath.moveTo(hx,hy-seyScale*56);
			//int j=1;
			for(int i=endXSize+1;i<endX2Size;i=i+pointScale)
			{
				//j++;
				//Log.i(TAG,"liuliangxiang endX2Size222="+endX2Size);
				//Log.i(TAG,"liuliangxiang i="+i);
				//Log.i(TAG,"liuliangxiang i="+i);
				temp = mmainset.CSISHOW[i+startX2]&0xff;
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				hy=spoint[2].y; 		
				if(SCIData<100)
					hy=siy[SCIData/10]+siyScale*(SCIData%10);
				else if(SCIData==0)
					hy=spoint[0].y;
				else if(SCIData==100)
					hy=spoint[2].y;
				//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
				if(i<scale)
				{
					hx=spoint[0].x+xScale*(i%scale)+xScale/6;
				}
				else
				{
					hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
				}
				//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
				//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
				mLinePath.lineTo(hx, hy-seyScale*56);
			}
			mLinePath.moveTo(hx,hy);
			mCanvas.drawPath(mLinePath, mSlinePaint1);
		}
		mLinePath.close();
	
		miLinePath.reset();
		mLinePath.reset();
	


		pointSize=18;
		pointScale=1;
		xScale=ix1Scale;
		timeScale=10;
		scale=1;
		if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=ix120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		startscishowx++;
		//if((startscishowx>mmainset.CSISHOW.length)||(startscishowx<pointSize))
		if((startscishowx>mmainset.BSSHOW.length))
		{
			startscishowx=0;
		}
		//startscishowx=94;
		starttemp=(startscishowx-1)/pointSize*pointSize;
		startX=starttemp-1;
		endXSize=startscishowx-startX;
		if(startscishowx%pointSize==0)
		{
			startX2=-2;
			endX2Size=-1;
		}
		else
		{
			startX2=starttemp-pointSize;
			endX2Size=pointSize+1;
		}		
		for(int k=0;k<=pointSize;k=k+6*timeScale)
		{
	//		Log.i(TAG,"liuliangxiang k="+k);
			{
				float x;//x1[]=new int[19];
				x=ipoint[0].x+k/timeScale*ix1Scale;
				int hour;//=(startX+1+k)/3600;
				int min;//=(startX+1+k)%3600/60;
				int second;//=(startX+1+k)%60;
				if((k>endXSize)&&(startX2>=0))
				{
					hour=(startX-pointSize+1+k)/3600;
					min=(startX-pointSize+1+k)%3600/60;
					second=(startX-pointSize+1+k)%60;
				}
				else
				{
					hour=(startX+1+k)/3600;
					min=(startX+1+k)%3600/60;
					second=(startX+1+k)%60;
				}
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, spoint[5].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, spoint[5].y+12,CoordinatePaint);
			}
		}
//		Log.i(TAG,"liuliangxiang endXSize="+endXSize);
		miLinePath.reset();
		mLinePath.reset();
		hx = spoint[0].x;
		hy = spoint[5].y;
		SCIData=0;
		
		if(startX<=0)
		{
			miLinePath.moveTo(spoint[1].x,spoint[5].y);
		}
		else
		{
			hx=spoint[0].x;
			hy=spoint[2].y;
			temp = mmainset.BSSHOW[startX]&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			if(SCIData<100)
				hy=biy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[3].y;
			else if(SCIData==100)
				hy=spoint[5].y;
			miLinePath.moveTo(hx, hy);
			miLinePath.lineTo(hx, hy);
		}

		for(int i=1;i<endXSize;i=i+pointScale)
		{
			temp = mmainset.BSSHOW[i+startX]&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=ipoint[1].y; 		
			if(SCIData<100)
				hy=biy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[3].y;
			else if(SCIData==100)
				hy=spoint[5].y;
			//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
			if(i<scale)
			{
				hx=spoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
			//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
			miLinePath.lineTo(hx, hy);
		}
		miLinePath.moveTo(hx,hy);
		mCanvas.drawPath(miLinePath, mSLinePaint);
	//	mCanvas.drawPoint(hx,hy,mELinePaint);
		miLinePath.close();
		
		if(startX2>-1)
		{			
			if((endXSize)<scale)
			{
				hx=spoint[0].x+xScale*((endXSize)%scale);
			//	Log.i(TAG,"liuliangxiang endXSize11="+endXSize);
			}
			else
			{
				hx=six[(endXSize)/scale-1]+xScale*((endXSize)%scale);
			//	Log.i(TAG,"liuliangxiang endXSize22="+endXSize);
			}
	
			temp = mmainset.BSSHOW[startX2+endXSize]&0xff;
		//	Log.i(TAG,"liuliangxiang temp="+temp);
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=spoint[5].y; 		
			if(SCIData<100)
				hy=biy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[3].y;
			else if(SCIData==100)
				hy=spoint[5].y;
			mLinePath.moveTo(hx,hy-seyScale*56);
			//int j=1;
			for(int i=endXSize+1;i<endX2Size;i=i+pointScale)
			{
				//j++;
				//Log.i(TAG,"liuliangxiang endX2Size222="+endX2Size);
				//Log.i(TAG,"liuliangxiang i="+i);
				//Log.i(TAG,"liuliangxiang i="+i);
				temp = mmainset.BSSHOW[i+startX2]&0xff;
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				hy=spoint[5].y; 		
				if(SCIData<100)
					hy=biy[SCIData/10]+siyScale*(SCIData%10);
				else if(SCIData==0)
					hy=spoint[3].y;
				else if(SCIData==100)
					hy=spoint[5].y;
				//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
				if(i<scale)
				{
					hx=spoint[0].x+xScale*(i%scale)+xScale/6;
				}
				else
				{
					hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
				}
				//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
				//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
				mLinePath.lineTo(hx, hy-seyScale*56);
			}
			mLinePath.moveTo(hx,hy);
			mCanvas.drawPath(mLinePath, mSlinePaint1);
		}
		mLinePath.close();
	
		miLinePath.reset();
		mLinePath.reset();
	}


	public void drawicoordshow()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawText(mcontext.getString(R.string.csi_show), ix[14]+4, iy[1]-2,CurCSIPaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		float xScale=ix1Scale;
		timeScale=5;
		scale=1;
		int endXSize;
		int startX2;
		int endX2Size;

		//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=ix120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		startscishowx++;
	//	if((startscishowx>mmainset.CSISHOW.length)||(startscishowx<pointSize))
		if((startscishowx>mmainset.CSISHOW.length))
		{
			startscishowx=0;
		}
		//startscishowx=94;
		int starttemp=(startscishowx-1)/pointSize*pointSize;
		startX=starttemp-1;
		endXSize=startscishowx-startX;
		if(startscishowx%pointSize==0)
		{
			startX2=-2;
			endX2Size=-1;
		}
		else
		{
			startX2=starttemp-pointSize;
			endX2Size=pointSize+1;
		}
	//	Log.i(TAG,"liuliangxiang startscishowx="+startscishowx);
	//	Log.i(TAG,"liuliangxiang startX2="+startX2);
	//	Log.i(TAG,"liuliangxiang endX2Size="+endX2Size);
	//	Log.i(TAG,"liuliangxiang endXSize="+endXSize);
		
		for(int k=0;k<=pointSize;k=k+6*timeScale)
		{
			Log.i(TAG,"liuliangxiang k="+k);
			//float x;//x1[]=new int[19];
			//x=ipoint[0].x+k*ix1Scale;
		//	int scale=timeScale;
			//if(k%(6*timeScale)==0)
			{
				float x;//x1[]=new int[19];
				//Log.i(TAG);
				x=ipoint[0].x+k/timeScale*ix1Scale;
			//	int hour=(startX+1+k)/3600;
			//	int min=(startX+1+k)%3600/60;
			//	int second=(startX+1+k)%60;
				int hour;//=(startX+1+k)/3600;
				int min;//=(startX+1+k)%3600/60;
				int second;//=(startX+1+k)%60;
				if((k>endXSize)&&(startX2>=0))
				{
					hour=(startX-pointSize+1+k)/3600;
					min=(startX-pointSize+1+k)%3600/60;
					second=(startX-pointSize+1+k)%60;
				}
				else
				{
					hour=(startX+1+k)/3600;
					min=(startX+1+k)%3600/60;
					second=(startX+1+k)%60;
				}
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			}
		}
	//	Log.i(TAG,"liuliangxiang endXSize="+endXSize);
		/*
		if(startX2>0)
		{
			for(int k=endXSize;k<=pointSize;k=k+timeScale)
			{
			//	Log.i(TAG,"liuliangxiang k="+k);
				
			//	int scale=timeScale;
				if(k%(6*timeScale)==0)
				{
					float x;//x1[]=new int[19];
					x=ipoint[0].x+k/timeScale*ix1Scale;
					int hour=(startX2+1+k*timeScale)/3600;
					int min=(startX2+1+k*timeScale)%3600/60;
					int second=(startX2+1+k*timeScale)%60;
					String strhour=Integer.toString(hour);
					String strmin=Integer.toString(min);
					if(min<10)
					{
						strmin="0"+strmin;
					}
					String strsec=Integer.toString(second);
					if(second<10)
					{
						strsec="0"+strsec;
					}
					if(hour<=0)
						mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
					else
						mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
				}
			}
		}*/
		
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y+0, CoordTextPaint);
		//if(mmainset.CSI.size()<=0)
			//return;
		miLinePath.reset();
		mLinePath.reset();
		float hx = ipoint[0].x;
		float hy = ipoint[1].y;
		int SCIData=0;
		
		if(startX<=0)
		{
			miLinePath.moveTo(ipoint[1].x,ipoint[1].y);
		}
		else
		{
			hx=ipoint[1].x;
			hy=ipoint[1].y;
			int temp = mmainset.CSISHOW[startX]&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			if(SCIData<100)
				hy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=ipoint[0].y;
			else if(SCIData==100)
				hy=ipoint[1].y;
			miLinePath.moveTo(hx, hy);
			miLinePath.lineTo(hx, hy);
		}
		int temp;

		for(int i=1;i<endXSize;i=i+pointScale)
		{
			temp = mmainset.CSISHOW[i+startX]&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=ipoint[1].y; 		
			if(SCIData<100)
				hy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=ipoint[0].y;
			else if(SCIData==100)
				hy=ipoint[1].y;
			//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
			if(i<scale)
			{
				hx=ipoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=ix[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
			//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
			miLinePath.lineTo(hx, hy);
		}
		miLinePath.moveTo(hx,hy);
		mCanvas.drawPath(miLinePath, mCSILinePaint);
	//	mCanvas.drawPoint(hx,hy,mELinePaint);
		miLinePath.close();
		if((endX2Size<0)||(startX2<-1))
			return;
		
		if((endXSize)<scale)
		{
			hx=ipoint[0].x+xScale*((endXSize)%scale);
		//	Log.i(TAG,"liuliangxiang endXSize11="+endXSize);
		}
		else
		{
			hx=ix[(endXSize)/scale-1]+xScale*((endXSize)%scale);
		//	Log.i(TAG,"liuliangxiang endXSize22="+endXSize);
		}

		temp = mmainset.CSISHOW[startX2+endXSize]&0xff;
	//	Log.i(TAG,"liuliangxiang temp="+temp);
		if(temp>100)
			temp=0;
		SCIData=100-temp;
		hy=ipoint[1].y; 		
		if(SCIData<100)
			hy=iy[SCIData/10]+iyScale*(SCIData%10);
		else if(SCIData==0)
			hy=ipoint[0].y;
		else if(SCIData==100)
			hy=ipoint[1].y;
		mLinePath.moveTo(hx,hy);
		//int j=1;
		for(int i=endXSize+1;i<endX2Size;i=i+pointScale)
		{
			//j++;
			//Log.i(TAG,"liuliangxiang endX2Size222="+endX2Size);
			//Log.i(TAG,"liuliangxiang i="+i);
			//Log.i(TAG,"liuliangxiang i="+i);
			temp = mmainset.CSISHOW[i+startX2]&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=ipoint[1].y; 		
			if(SCIData<100)
				hy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=ipoint[0].y;
			else if(SCIData==100)
				hy=ipoint[1].y;
			//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
			if(i<scale)
			{
				hx=ipoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=ix[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
			//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
			mLinePath.lineTo(hx, hy);
		}
		mLinePath.moveTo(hx,hy);
		mCanvas.drawPath(mLinePath, mSlinePaint1);
		mLinePath.close();
		miLinePath.reset();
		mLinePath.reset();
	}



	public void drawicoord()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawText("CSI", ix[16]-30, iy[1]-2, CurCSIPaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		float xScale=ix1Scale;
		timeScale=mmainset.majuitimescale;
		float bannerSelect=mmainset.bannerSelect;
		float bannerX;
		float bannerYS;
		float bannerYL;
		int value;

		bannerX = ipoint[0].x;
		bannerYS = ipoint[0].y;
		int tempValue = mmainset.shangxianshezValue;
		if (tempValue > 100)
			tempValue = 0;
		value = 100 - tempValue;
		if (value < 100)
			bannerYS = iy[value / 10] + iyScale * (value % 10);
		else if (value == 0)
			bannerYS = ipoint[0].y;
		else if (value == 100)
			bannerYS = ipoint[1].y;
		bannerYL = ipoint[1].y;
		tempValue = mmainset.xiaxianshezValue;
		if (tempValue > 100)
			tempValue = 0;
		value = 100 - tempValue;
		if (value < 100)
			bannerYL = iy[value / 10] + iyScale * (value % 10);
		else if (value == 0)
			bannerYL = ipoint[0].y;
		else if (value == 100)
			bannerYL = ipoint[1].y;
		if(bannerSelect==1){
			mCanvas.drawLine(bannerX, bannerYS, ipoint[2].x, bannerYS, shandowPaint);
			mCanvas.drawLine(bannerX, bannerYL, ipoint[2].x, bannerYL, shandowPaint);
		}else if(bannerSelect==2){
			mCanvas.drawRect(bannerX, bannerYS,ipoint[2].x,bannerYL,shandowPaint);
		}
		scale=1;
		int endXSize;
		int startX2;
		int endX2Size;
		if(timeScale==1){
			mCanvas.drawText("/1sec", ix[16]+4, iy[1]-2, CurCSIPaint);
		}
		else if(timeScale==2)
		{
			xScale=ix2Scale;
			pointSize=36;
			pointScale=1;
			scale=2;
			mCanvas.drawText("/2sec", ix[16]+4, iy[1]-2, CurCSIPaint);
		}
		else if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
			mCanvas.drawText("/5sec", ix[16]+4, iy[1]-2, CurCSIPaint);
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
			mCanvas.drawText("/10sec", ix[16]+4, iy[1]-2, CurCSIPaint);
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
			mCanvas.drawText("/1min", ix[16]+4, iy[1]-2, CurCSIPaint);
		}
		else if(timeScale==120)
		{
			xScale=ix120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
			mCanvas.drawText("/2min", ix[16]+4, iy[1]-2, CurCSIPaint);
		}
//		startscishowx++;
//		if((startscishowx>mmainset.CSISHOW.length))
//		{
//			startscishowx=0;
//		}
		int starttemp=(mmainset.CSI.size()-1)/pointSize*pointSize;
		startX=starttemp-1;
		endXSize=mmainset.CSI.size()-startX;
		if(mmainset.CSI.size()%pointSize==0)
		{
			startX2=-2;
			endX2Size=-1;
		}
		else
		{
			startX2=starttemp-pointSize;
			endX2Size=pointSize+1;
		}
		for(int k=0,j=0;k<=pointSize;k=k+6*timeScale,j=j+6)
		{

			float x;//x1[]=new int[19];
			x=ipoint[0].x+j*ix1Scale;
			int hour;//=(startX+1+k)/3600;
			int min;//=(startX+1+k)%3600/60;
			int second;//=(startX+1+k)%60;
			if((k>endXSize)&&(startX2>=0))
			{
				hour=(startX-pointSize+1+k)/3600;
				min=(startX-pointSize+1+k)%3600/60;
				second=(startX-pointSize+1+k)%60;
			}
			else
			{
				hour=(startX+1+k)/3600;
				min=(startX+1+k)%3600/60;
				second=(startX+1+k)%60;
			}
			String strhour=Integer.toString(hour);
			String strmin=Integer.toString(min);
			Log.i(TAG,"liuliangxiang hour="+hour);
			Log.i(TAG,"liuliangxiang x="+x);
			if(min<10)
			{
				strmin="0"+strmin;
			}
			String strsec=Integer.toString(second);
			if(second<10)
			{
				strsec="0"+strsec;
			}
			if(hour<=0)
				mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			else
				mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
		}		
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y+0, CoordTextPaint);
		miLinePath.reset();
		mLinePath.reset();
		if(mmainset.CSI.size()<=0)
			return;
		float hx = ipoint[0].x;
		float hy = ipoint[1].y;
		int SCIData=0;
		
		if(startX<=0)
		{
			miLinePath.moveTo(ipoint[1].x,ipoint[1].y);
		}
		else
		{
			hx=ipoint[1].x;
			hy=ipoint[1].y;
			int temp = mmainset.CSI.get(startX)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			if(SCIData<100)
				hy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=ipoint[0].y;
			else if(SCIData==100)
				hy=ipoint[1].y;
			miLinePath.moveTo(hx, hy);
			miLinePath.lineTo(hx, hy);
		}
		int temp;

		for(int i=1;i<endXSize;i=i+pointScale)
		{
			temp = mmainset.CSI.get(i+startX)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=ipoint[1].y; 		
			if(SCIData<100)
				hy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=ipoint[0].y;
			else if(SCIData==100)
				hy=ipoint[1].y;
			//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
			if(i<scale)
			{
				hx=ipoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=ix[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
			//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
			miLinePath.lineTo(hx, hy);
		}
		miLinePath.moveTo(hx,hy);
		if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			mCanvas.drawPath(miLinePath, mCSILinePaint);
	//	mCanvas.drawPoint(hx,hy,mELinePaint);
		miLinePath.close();
		if((endX2Size<0)||(startX2<-1))
			return;
		
		if((endXSize)<scale)
		{
			hx=ipoint[0].x+xScale*((endXSize)%scale);
		}
		else
		{
			hx=ix[(endXSize)/scale-1]+xScale*((endXSize)%scale);
		}

		temp = mmainset.CSI.get(startX2+endXSize)&0xff;
		//Log.i(TAG,"liuliangxiang temp="+temp);
		if(temp>100)
			temp=0;
		SCIData=100-temp;
		hy=ipoint[1].y; 		
		if(SCIData<100)
			hy=iy[SCIData/10]+iyScale*(SCIData%10);
		else if(SCIData==0)
			hy=ipoint[0].y;
		else if(SCIData==100)
			hy=ipoint[1].y;
		mLinePath.moveTo(hx,hy);
		for(int i=endXSize;i<endX2Size;i=i+pointScale)
		{
			temp = mmainset.CSI.get(i+startX2)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=ipoint[1].y; 		
			if(SCIData<100)
				hy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=ipoint[0].y;
			else if(SCIData==100)
				hy=ipoint[1].y;
			//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
			if(i<scale)
			{
				hx=ipoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=ix[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
			//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
			mLinePath.lineTo(hx, hy);
		}
		mLinePath.moveTo(hx,hy);
		if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			mCanvas.drawPath(mLinePath, mSlinePaint1);
		mLinePath.close();
		miLinePath.reset();
		mLinePath.reset();
	}


	
	
/*	
	public void drawicoord()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		//int CSIData=
		float xScale=ix1Scale;
		timeScale=mmainset.majuitimescale;
		scale=1;
		
//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=ix120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		
		if(mmainset.CSI.size()>(pointSize))
		{
			startX=(mmainset.CSI.size()-pointSize-1);
			dataSize=pointSize;
		}
		else
		{
			startX=-1;
			dataSize=mmainset.CSI.size()-1;
		}
		Log.i(TAG,"liuliangxiang startX="+startX);
		float[] hx=new float[dataSize+1];
		float[] hy=new float[dataSize+1];
		int SCIData=0;
		
		for(int k=0;k<=18;k=k+6)
		{
			float x;//x1[]=new int[19];
			x=ipoint[0].x+k*ix1Scale;
		//	int scale=timeScale;
			if(k%6==0)
			{
				int hour=(startX+1+k*timeScale)/3600;
				int min=(startX+1+k*timeScale)%3600/60;
				int second=(startX+1+k*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			}
		}
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y+8, CoordTextPaint);
		if(mmainset.CSI.size()<=0)
			return;

		//if(preDataSize!=mmainset.CSI.size())
		{
			miLinePath.reset();
			preDataSize=mmainset.CSI.size();
			bmpdownarray.clear();
			bmpuparray.clear();
			{				
				hx[0]=ipoint[1].x;
				hy[0]=ipoint[1].y;
				if(mmainset.CSI.size()<=18)
				{
					miLinePath.moveTo(ipoint[1].x, ipoint[1].y);
					miLinePath.lineTo(ipoint[1].x, ipoint[1].y);
				}
				else
				{
					int temp = mmainset.CSI.get(startX)&0xff;
					if(temp>100)
						temp=0;
					SCIData=100-temp;
					if(SCIData<100)
						hy[0]=iy[SCIData/10]+iyScale*(SCIData%10);
					else if(SCIData==0)
						hy[0]=ipoint[0].y;
					else if(SCIData==100)
						hy[0]=ipoint[1].y;
					miLinePath.moveTo(hx[0], hy[0]);
					miLinePath.lineTo(hx[0], hy[0]);
				}
			}
			float px;
			float height = 0;
			for(int i=1;i<dataSize+1;i=i+pointScale)
			{
				int temp = mmainset.CSI.get(startX+i)&0xff;
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				hy[i]=ipoint[1].y;			
				if(SCIData<100)
					hy[i]=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					hy[i]=ipoint[0].y;
				else if(SCIData==100)
					hy[i]=ipoint[1].y;
				if(i<scale)
				{
					hx[i]=ipoint[0].x+xScale(i%scale);
				}
				else
				{
					hx[i]=ix[i/scale-1]+xScale*(i%scale)+xScale/6;
				}
				//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
				hx[i]=ix[(i)/scale-1]-xScale*(scale-i%scale-1)+xScale/6;
				//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
				//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
				miLinePath.lineTo(hx[i], hy[i]);
//				if(i>=pointScale)
//				{
//					if(((mmainset.CSISHOW[i+startX]&0xff)>mmainset.getshangxianshezValue())&&((mmainset.CSISHOW[i+startX-pointScale]&0xff)<mmainset.getshangxianshezValue()))
//					{
//						SCIData=100-mmainset.getshangxianshezValue();
//						if(SCIData<100)
//							height=iy[SCIData/10]+iyScale*(SCIData%10);
//						else if(SCIData==100)
//							height=ipoint[1].y;
//						if(timeScale>=10)
//						{
//							px=hx[i-1]+(xScale)/2;
//						}
//						else
//						{
//							px=hx[i-1]+(height-hy[i-1])*xScale/(hy[i]-hy[i-1]);
//							//px=hx[i]-(hy[i-1]-hy[i])*(mmainset.CSI.get(i*scale+startX)-mmainset.getshangxianshezValue())/(xScale);
//						}
//						bmpuparray.add(px);
//						bmpuparray.add(height);
//						//mCanvas.drawBitmap(bitmapDown, px-8, height, null);
//					}
//	
//					if(((mmainset.CSISHOW[i+startX]&0xff)<mmainset.getxiaxianshezValue())&&((mmainset.CSISHOW[i+startX-pointScale]&0xff)>mmainset.getxiaxianshezValue()))
//					{
//						SCIData=100-mmainset.getxiaxianshezValue();
//						if(SCIData<100)
//							height=iy[SCIData/10]+iyScale*(SCIData%10);
//						else if(SCIData==100)
//							height=ipoint[1].y;
//						if(timeScale>=10)
//						{
//							px=hx[i-1]+(xScale)/2;
//						}
//						else{
//							//px=hx[i-1]+(mmainset.xiaxianshezValue-mmainset.CSI.get(i*scale+startX-1)&0xff)*(xScale)/(mmainset.CSI.get(i*scale+startX)&0xff-mmainset.CSI.get(i*scale+startX-1)&0xff);
//							px=hx[i]-(height-hy[i])*xScale/(hy[i-1]-hy[i]);
//						}
//						bmpdownarray.add(px);
//						bmpdownarray.add(height);
//						//mCanvas.drawBitmap(bitmapUp, px-8, height, null);
//					}
//				}

			}
			if(dataSize>0)
			{
				miLinePath.moveTo(hx[dataSize], hy[dataSize]);
			}
		}
		mCanvas.drawPath(miLinePath, mSLinePaint);
		miLinePath.close();
		for(int j=0;j<bmpuparray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapDown, bmpuparray.get(j)-8, bmpuparray.get(j+1), null);
		}
		for(int j=0;j<bmpdownarray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapUp, bmpdownarray.get(j)-8, bmpdownarray.get(j+1), null);
		}
//		for(int k=0;k<=18;k=k+6)
//		{
//			float x;//x1[]=new int[19];
//			x=ipoint[0].x+k*ix1Scale;
//		//	int scale=timeScale;
//			if(k%6==0)
//			{
//				int hour=(startX+k*timeScale)/3600;
//				int min=(startX+k*timeScale)%3600/60;
//				int second=(startX+k*timeScale)%60;
//				String strhour=Integer.toString(hour);
//				String strmin=Integer.toString(min);
//				if(min<10)
//				{
//					strmin="0"+strmin;
//				}
//				String strsec=Integer.toString(second);
//				if(second<10)
//				{
//					strsec="0"+strsec;
//				}
//				if(hour<=0)
//					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
//				else
//					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
//			}
//		}
//		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
//		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
//		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
//		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
//		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
//		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y+8, CoordTextPaint);
	}
*/	
	public void drawicoordrec()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawText(mcontext.getString(R.string.csi_review), ix[14]+4, iy[1]-2, CurCSIPaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		//int CSIData=
		float xScale=ix1Scale;
		timeScale=mmainset.majuirectimeScale;
		float bannerSelect=mmainset.bannerSelect;
		float bannerX;
		float bannerYS;
		float bannerYL;
		int value;

		bannerX = ipoint[0].x;
		bannerYS = ipoint[0].y;
		int tempValue = mmainset.shangxianshezValue;
		if (tempValue > 100)
			tempValue = 0;
		value = 100 - tempValue;
		if (value < 100)
			bannerYS = iy[value / 10] + iyScale * (value % 10);
		else if (value == 0)
			bannerYS = ipoint[0].y;
		else if (value == 100)
			bannerYS = ipoint[1].y;
		bannerYL = ipoint[1].y;
		tempValue = mmainset.xiaxianshezValue;
		if (tempValue > 100)
			tempValue = 0;
		value = 100 - tempValue;
		if (value < 100)
			bannerYL = iy[value / 10] + iyScale * (value % 10);
		else if (value == 0)
			bannerYL = ipoint[0].y;
		else if (value == 100)
			bannerYL = ipoint[1].y;
		if(bannerSelect==1){
			mCanvas.drawLine(bannerX, bannerYS, ipoint[2].x, bannerYS, shandowPaint);
			mCanvas.drawLine(bannerX, bannerYL, ipoint[2].x, bannerYL, shandowPaint);
		}else if(bannerSelect==2){
			mCanvas.drawRect(bannerX, bannerYS,ipoint[2].x,bannerYL,shandowPaint);
		}
		scale=1;
		
//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}else if(timeScale==2)
		{
			xScale=ix2Scale;
			pointSize=36;
			pointScale=1;
			scale=2;
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=ix120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		startX=scimovereclen;
		dataSize=pointSize;
		if(mmainset.RecCSI.size()<=(pointSize))
		{
			startX=-1;
			dataSize=mmainset.RecCSI.size();
		}
		else if(startX>=(mmainset.RecCSI.size()-pointSize))
		{
			startX=mmainset.RecCSI.size()-pointSize-1;
			scimovereclen=mmainset.RecCSI.size()-pointSize-1;
			dataSize=pointSize;
		}
		CurDataLen=(startX+dataSize);
		if(CurDataLen>=mmainset.RecCSI.size())
			CurDataLen=mmainset.RecCSI.size()-1;
		
		float hx = ipoint[0].x;
        float hy = ipoint[1].y;
		int SCIData=0;
		
		for(int k=0,j=0;k<=pointSize;k=k+6*timeScale,j=j+6)
		{

			float x;//x1[]=new int[19];
			x=ipoint[0].x+j*ix1Scale;
			int hour;//=(startX+1+k)/3600;
			int min;//=(startX+1+k)%3600/60;
			int second;//=(startX+1+k)%60;
			
			hour=(mmainset.curminTime+startX+1+k)/3600;
			min=(mmainset.curminTime+startX+1+k)%3600/60;
			second=(mmainset.curminTime+startX+1+k)%60;
			
			String strhour=Integer.toString(hour);
			String strmin=Integer.toString(min);
			if(min<10)
			{
				strmin="0"+strmin;
			}
			String strsec=Integer.toString(second);
			if(second<10)
			{
				strsec="0"+strsec;
			}
			if(hour<=0)
				mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			else
				mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
		}		
	/*	
		for(int k=0;k<=18;k=k+6)
		{
			float x;//x1[]=new int[19];
			x=ipoint[0].x+k*ix1Scale;
		//	int scale=timeScale;
			if(k%6==0)
			{
				int hour=(mmainset.curminTime+startX+1+k*timeScale)/3600;
				int min=(mmainset.curminTime+startX+1+k*timeScale)%3600/60;
				int second=(mmainset.curminTime+startX+1+k*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[2].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[2].y+12,CoordinatePaint);
			}
		}*/
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y+0, CoordTextPaint);
		if(mmainset.RecCSI.size()<=0)
			return;
		{
			miLinePath.reset();
			bmpdownarray.clear();
			bmpuparray.clear();
			{				
				hx=ipoint[1].x;
				hy=ipoint[1].y;
				if(mmainset.RecCSI.size()<=dataSize)
				{
					miLinePath.moveTo(ipoint[1].x, ipoint[1].y);
					miLinePath.lineTo(ipoint[1].x, ipoint[1].y);
				}
				else
				{
					if(startX<=-1){
						hx=ipoint[1].x;
						hy=ipoint[1].y;
					}else{
						int temp = mmainset.RecCSI.get(startX)&0xff;
						if(temp>100)
							temp=0;
						SCIData=100-temp;
						if(SCIData<100)
							hy=iy[SCIData/10]+iyScale*(SCIData%10);
						else if(SCIData==0)
							hy=ipoint[0].y;
						else if(SCIData==100)
							hy=ipoint[1].y;
					}
					miLinePath.moveTo(hx, hy);
					miLinePath.lineTo(hx, hy);
				}
			}
			float px;
			float height = 0;
			for(int i=1;i<dataSize+1;i=i+pointScale)
			{
				int temp = mmainset.RecCSI.get(startX+i)&0xff;
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				hy=ipoint[1].y;			
				if(SCIData<100)
					hy=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					hy=ipoint[0].y;
				else if(SCIData==100)
					hy=ipoint[1].y;
				if(i<scale)
				{
					hx=ipoint[0].x+xScale*(i%scale)+xScale/6;
				}
				else
				{
					hx=ix[i/scale-1]+xScale*(i%scale)+xScale/6;
				}
				miLinePath.lineTo(hx, hy);

			}
			if(dataSize>0)
			{
				miLinePath.moveTo(hx, hy);
			}
		}
		mCanvas.drawPath(miLinePath, mCSILinePaint);
		if(curix!=-1)
		{
			int temp=(int)((curix-ipoint[0].x)/xScale)+startX+1;
			if(temp>=mmainset.RecCSI.size())
				DataNum=temp-1;
			else
				DataNum=temp;
			if((temp<mmainset.RecCSI.size())&&(temp>0))
			{
				int data=mmainset.RecCSI.get(temp);
				int hour=(temp+mmainset.curminTime)/3600;
				int min=(temp+mmainset.curminTime)%3600/60;
				int second=(temp+mmainset.curminTime)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if((data&0xff)>100){
					if(hour>0)
						mCanvas.drawText("("+strhour+":"+strmin+":"+strsec+","+"▂ ▂"+")", curix, ipoint[0].y+40, CurCSIPaint);
					else {
						mCanvas.drawText("("+strmin+":"+strsec+","+"▂ ▂"+")", curix, ipoint[0].y+40, CurCSIPaint);
					}
				}else{
					if(hour>0)
						mCanvas.drawText("("+strhour+":"+strmin+":"+strsec+","+Integer.toString(data&0xff)+")", curix, ipoint[0].y+40, CurCSIPaint);
					else {
						mCanvas.drawText("("+strmin+":"+strsec+","+Integer.toString(data&0xff)+")", curix, ipoint[0].y+40, CurCSIPaint);
					}
				}
				mCanvas.drawLine(curix, ipoint[0].y, curix, ipoint[2].y, mSLine2ePaint);
			}
			
		}
		miLinePath.close();
		for(int j=0;j<bmpuparray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapDown, bmpuparray.get(j)-8, bmpuparray.get(j+1), null);
		}
		for(int j=0;j<bmpdownarray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapUp, bmpdownarray.get(j)-8, bmpdownarray.get(j+1), null);
		}
	}

	
/*	public void drawi2coord()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=90;
		int pointScale=1;
		//int CSIData=
		float xScale=ix1Scale;
		timeScale=mmainset.majuitimescale;
		scale=5;
		
//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==1)
		{
			timeScale=5;
		}
		if(timeScale==5)
		{
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		
		if(mmainset.CSI.size()>(pointSize))
		{
			startX=(mmainset.CSI.size()-pointSize)/scale*scale;
			dataSize=pointSize;
		}
		else
		{
			startX=0;
			dataSize=mmainset.CSI.size()/scale*scale;
		}
		//float[] hx=new float[dataSize];
		//float[] hy=new float[dataSize];
		float hstartx;
		float hendx;
		float hmiddlex;
		float hstarty;
		float hendy;
		float maxy;
		float miny;
		int SCIData=0;

	//	if(preDataSize!=(mmainset.CSI.size()/scale*scale))
		if(mmainset.CSI.size()>0)
		{
		//	preDataSize=(mmainset.CSI.size()/scale*scale);
			for(int i=0;i<dataSize;i=i+scale)
			{
				int temp=mmainset.CSI.get(i+startX)&0xff;
				if(temp>100)
				{
					temp=0;
				}
				SCIData=100-temp;
				hstarty=ipoint[1].y;			
				if(SCIData<100)
					hstarty=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					hstarty=ipoint[0].y;
				else if(SCIData==100)
					hstarty=ipoint[1].y;
				hstartx=ix[i/scale]-xScale*2/3;
				temp=mmainset.CSI.get(i+scale-1+startX)&0xff;
				if(temp>100)
				{
					temp=0;
				}
				SCIData=100-temp;
				hendy=ipoint[1].y;			
				if(SCIData<100)
					hendy=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					hendy=ipoint[0].y;
				else if(SCIData==100)
					hendy=ipoint[1].y;
				hendx=ix[i/scale]-xScale/3;
				hmiddlex=ix[i/scale]-xScale/2;
				
				int Value[]=getThreadValue(mmainset.CSI,i,i+scale);
				Log.i(TAG,"liuliangxiang value0="+Value[0]);
				Log.i(TAG,"liuliangxiang value1="+Value[1]);
				temp=Value[0];
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				miny=ipoint[1].y;			
				if(SCIData<100)
					miny=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					miny=ipoint[0].y;
				else if(SCIData==100)
					miny=ipoint[1].y;
				temp=Value[1];
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				maxy=ipoint[1].y;			
				if(SCIData<100)
					maxy=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					maxy=ipoint[0].y;
				else if(SCIData==100)
					maxy=ipoint[1].y;
				
				mCanvas.drawLine(hstartx, maxy, hendx, maxy, mSLine2Paint);
				mCanvas.drawLine(hstartx, miny, hendx, miny, mSLine2Paint);
				if(hstarty>hendy)
				{
					mCanvas.drawRect(new RectF(hstartx,hendy,hendx,hstarty), mSLine2Paint);
					mCanvas.drawLine(hmiddlex,hstarty,hmiddlex,miny,mSLine2Paint);
					mCanvas.drawLine(hmiddlex,maxy,hmiddlex,hendy,mSLine2Paint);
				}
				else
				{
					mCanvas.drawRect(new RectF(hstartx,hstarty,hendx,hendy), mSLine2ePaint);
					mCanvas.drawLine(hmiddlex,maxy,hmiddlex,hstarty,mSLine2Paint);
					mCanvas.drawLine(hmiddlex,hendy,hmiddlex,miny,mSLine2Paint);
				}
			}
		}
		for(int k=0;k<=18;k=k+6)
		{
			float x;//x1[]=new int[19];
			x=ipoint[0].x+k*ix1Scale;
		//	int scale=timeScale;
			if(k%6==0)
			{
				int hour=(startX+k*timeScale)/3600;
				int min=(startX+k*timeScale)%3600/60;
				int second=(startX+k*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			}
		}
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y+8, CoordTextPaint);
	}
	*/
	
	public void drawi2coord()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawText("CSI-T", ix[16]+4, iy[1]-2, WhitePaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=2160;
		int pointScale=1;
		//int CSIData=
		float xScale=ix1Scale;
		timeScale=120;
		scale=120;
		int endXSize;
		int startX2;
		int endX2Size;
		if(timeScale==1)
		{
			timeScale=5;
		}
		if(timeScale==5)
		{
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		float hstartx;
		float hendx;
		float hmiddlex;
		float hstarty;
		float hendy;
		float maxy;
		float miny;
		float avg;
		int SCIData=0;
		ArrayList<Integer> tempArray=new ArrayList<Integer>();
		tempArray.clear();
		int starttemp=(mmainset.CSI.size()-1)/pointSize*pointSize;
		startX=starttemp;
		endXSize=mmainset.CSI.size()/scale*scale-startX;
		if(mmainset.CSI.size()%pointSize==0)
		{
			startX2=-2;
			endX2Size=-1;
		}
		else
		{
			startX2=starttemp-pointSize;
			endX2Size=pointSize;
		}
		for(int i=0;i<pointSize;i++)
		{
			if(i<endXSize)
			{
				tempArray.add(mmainset.CSI.get(i+startX));
			}
			else
			{
				if(startX2<0)
				{
					break;
				}
				tempArray.add(mmainset.CSI.get(i+startX2));
			}
		}
		for(int i=0;i<tempArray.size();i=i+scale)
		{				
			SCIData=100-tempArray.get(i)&0xff;
			if(SCIData<0)
				SCIData=0;
			hstarty=ipoint[1].y;			
			if(SCIData<100)
				hstarty=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==100)
				hstarty=ipoint[0].y;
			hstartx=ix[i/scale]-xScale*2/3;
			SCIData=100-tempArray.get(i+scale-1)&0xff;
			hendy=ipoint[1].y;		
			if(SCIData<0)
				SCIData=0;
			if(SCIData<100)
				hendy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==100)
				hendy=ipoint[1].y;
			hendx=ix[i/scale]-xScale/3;
			hmiddlex=ix[i/scale]-xScale/2;		
			int Value[]=getThreadValue(tempArray,i,i+scale);
			SCIData=100-Value[0];
			if(SCIData<0)
				SCIData=0;
			miny=ipoint[1].y;			
			if(SCIData<100)
				miny=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==100)
				miny=ipoint[1].y;
			
			SCIData=100-Value[1];
			if(SCIData<0)
				SCIData=0;
			maxy=ipoint[1].y;			
			if(SCIData<100)
				maxy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==100)
				maxy=ipoint[1].y;
			
			SCIData=100-Value[2];
			if(SCIData<0)
				SCIData=0;
		//	Log.i(TAG,"liuliangxiang value[2]"+Value[2]);
			avg=ipoint[1].y;			
			if(SCIData<100)
				avg=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==100)
				avg=ipoint[1].y;
			
			if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			{
				//mCanvas.drawLine(hstartx, maxy, hendx, maxy, mSLine2Paint);
				//mCanvas.drawLine(hstartx, miny, hendx, miny, mSLine2Paint);
				mCanvas.drawLine(hmiddlex,maxy, hmiddlex, miny, mSLine2Paint);
				mCanvas.drawRect(new RectF(hstartx,avg-2,hendx,avg+2), mSLine2ePaint);
			}
		}
		for(int k=0;k<=pointSize;k=k+6*timeScale)
		{
			float x;//x1[]=new int[19];
			x=ipoint[0].x+k/timeScale*ix1Scale;
			int hour;//=(startX+1+k)/3600;
			int min;//=(startX+1+k)%3600/60;
			int second;//=(startX+1+k)%60;
			if((k>endXSize)&&(startX2>=0))
			{
				hour=(startX-pointSize+k)/3600;
				min=(startX-pointSize+k)%3600/60;
				second=(startX-pointSize+k)%60;
			}
			else
			{
				hour=(startX+k)/3600;
				min=(startX+k)%3600/60;
				second=(startX+k)%60;
			}
			String strhour=Integer.toString(hour);
			String strmin=Integer.toString(min);
			if(min<10)
			{
				strmin="0"+strmin;
			}
			String strsec=Integer.toString(second);
			if(second<10)
			{
				strsec="0"+strsec;
			}
			if(hour<=0)
				mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			else
				mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
		}
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y, CoordTextPaint);
	}
	
	
	public void drawi2coordrec()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawText(mcontext.getString(R.string.csi_t_review), ix[14]+4, iy[1]-2, WhitePaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		float avg;
		int pointSize=2160;
		int pointScale=1;
		float xScale=ix1Scale;
		timeScale=120;
		scale=120;
		if(timeScale==1)
		{
			timeScale=5;
		}
		if(timeScale==5)
		{
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		
		startX=scimovereclen/scale*scale;
		dataSize=pointSize;
		if(mmainset.RecCSI.size()<=(pointSize))
		{
			startX=0;
			dataSize=mmainset.RecCSI.size();
		}
		else if(startX>=(mmainset.RecCSI.size()-pointSize))
		{
			startX=(mmainset.RecCSI.size()-pointSize)/scale*scale;
			scimovereclen=mmainset.RecCSI.size()-pointSize-1;
			dataSize=pointSize;
		}
		CurDataLen=(startX+dataSize);
		if(CurDataLen>=mmainset.RecCSI.size())
			CurDataLen=mmainset.RecCSI.size()-1;
		//float[] hx=new float[dataSize];
		//float[] hy=new float[dataSize];
		float hstartx;
		float hendx;
		float hmiddlex;
		float hstarty;
		float hendy;
		float maxy;
		float miny;
		int SCIData=0;

	//	if(preDataSize!=(mmainset.CSI.size()/scale*scale))
		if(mmainset.RecCSI.size()>0)
		{
			preDataSize=(mmainset.RecCSI.size()/scale*scale);
			for(int i=0;i<dataSize;i=i+scale)
			{
				if((i+startX)>=mmainset.RecCSI.size()){
					break;
				}
				if((i+scale-1+startX)>=(mmainset.RecCSI.size())){
					break;
				}
				int temp=mmainset.RecCSI.get(i+startX)&0xff;
				if(temp>100)
				{
					temp=0;
				}
				SCIData=100-temp;
				hstarty=ipoint[1].y;			
				if(SCIData<100)
					hstarty=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					hstarty=ipoint[0].y;
				else if(SCIData==100)
					hstarty=ipoint[1].y;
				hstartx=ix[i/scale]-xScale*2/3;
				temp=mmainset.RecCSI.get(i+scale-1+startX)&0xff;
				if(temp>100)
				{
					temp=0;
				}
				SCIData=100-temp;
				hendy=ipoint[1].y;			
				if(SCIData<100)
					hendy=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					hendy=ipoint[0].y;
				else if(SCIData==100)
					hendy=ipoint[1].y;
				hendx=ix[i/scale]-xScale/3;
				hmiddlex=ix[i/scale]-xScale/2;
				
				int Value[]=getThreadValue(mmainset.RecCSI,i+startX,i+startX+scale);
		//		Log.i(TAG,"liuliangxiang value0="+Value[0]);
		//		Log.i(TAG,"liuliangxiang value1="+Value[1]);
				temp=Value[0];
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				miny=ipoint[1].y;			
				if(SCIData<100)
					miny=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					miny=ipoint[0].y;
				else if(SCIData==100)
					miny=ipoint[1].y;
				temp=Value[1];
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				maxy=ipoint[1].y;			
				if(SCIData<100)
					maxy=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					maxy=ipoint[0].y;
				else if(SCIData==100)
					maxy=ipoint[1].y;
				
				
				avg=ipoint[1].y;			
				if(SCIData<100)
					avg=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==100)
					avg=ipoint[1].y;
				
				//mCanvas.drawLine(hstartx, maxy, hendx, maxy, mSLine2Paint);
				//mCanvas.drawLine(hstartx, miny, hendx, miny, mSLine2Paint);
				
//				mCanvas.drawLine(hstartx, maxy, hendx, maxy, mSLine2Paint);
				//	mCanvas.drawLine(hstartx, miny, hendx, miny, mSLine2Paint);
					mCanvas.drawLine(hmiddlex,maxy, hmiddlex, miny, mSLine2Paint);
					mCanvas.drawRect(new RectF(hstartx,avg-2,hendx,avg+2), mSLine2ePaint);
//				if(hstarty>hendy)
//				{
//					mCanvas.drawRect(new RectF(hstartx,hendy,hendx,hstarty), mSLine2Paint);
//					mCanvas.drawLine(hmiddlex,hstarty,hmiddlex,miny,mSLine2Paint);
//					mCanvas.drawLine(hmiddlex,maxy,hmiddlex,hendy,mSLine2Paint);
//				}
//				else
//				{
//					mCanvas.drawRect(new RectF(hstartx,hstarty,hendx,hendy), mSLine2ePaint);
//					mCanvas.drawLine(hmiddlex,maxy,hmiddlex,hstarty,mSLine2Paint);
//					mCanvas.drawLine(hmiddlex,hendy,hmiddlex,miny,mSLine2Paint);
//				}
			}
		}
	
		if(curix!=-1)
		{
			int temp=(int)((curix-ipoint[0].x)/xScale)+startX;
			if(temp>=mmainset.RecCSI.size())
				DataNum=temp-1;
			else
				DataNum=temp;
			if((temp<mmainset.RecCSI.size())&&(temp>0))
			{
				int data=mmainset.RecCSI.get(temp);
				int hour=(temp+mmainset.curminTime)/3600;
				int min=(temp+mmainset.curminTime)%3600/60;
				int second=(temp+mmainset.curminTime)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				mCanvas.drawText("("+strmin+":"+strsec+","+Integer.toString(data&0xff)+")", curix, ipoint[0].y+40, BSTextPaint);
				mCanvas.drawLine(curix, ipoint[0].y, curix, ipoint[2].y, mSLine2ePaint);
			}
			
		}
		
		for(int k=0;k<=18;k=k+6)
		{
			float x;//x1[]=new int[19];
			x=ipoint[0].x+k*ix1Scale;
		//	int scale=timeScale;
			if(k%6==0)
			{
				int hour=(startX+k*timeScale)/3600;
				int min=(startX+k*timeScale)%3600/60;
				int second=(startX+k*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			}
		}
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y, CoordTextPaint);
	}
	
	private int[] getThreadValue(ArrayList<Integer> array,int start,int end)
	{
		int[] value=new int[3];
		value[0]=(array.get(start)&0xff);
		value[1]=(array.get(start)&0xff);
		value[2]=0;
		int j=0;
		for(int i=start;i<end;i++)
		{
			if((array.get(i)&0xff)>100)
				continue;
			if(((array.get(i)&0xff)>value[1]) || (value[1]>100))
			{
				value[1]=(array.get(i)&0xff);
			}
			if(((array.get(i)&0xff)<value[0]) || (value[0]>100))
			{
				value[0]=(array.get(i)&0xff);
			}
			value[2]=(array.get(i)&0xff)+value[2];
			j++;
		}
		if(j>0)
			value[2]=value[2]/j;
		if(value[0]>100)
		{
			value[0]=0;
		}
		if(value[1]>100)
		{
			value[1]=0;
		}
	//	Log.i(TAG,"liuliangxiang value[0]"+value[0]);
	//	Log.i(TAG,"liuliangxiang value[1]"+value[1]);
		return value;
	}
	
	private int[] getThreadValue(int[] array,int start,int end)
	{
		int[] value=new int[2];
		value[0]=(array[start]&0xff);
		for(int i=start;i<end;i++)
		{
			if((array[i]&0xff)>value[1])
			{
				value[1]=(array[i]&0xff);
			}
			if((array[i]&0xff)<value[0])
			{
				value[0]=(array[i]&0xff);
			}
		}
		return value;
	}
	
	public void drawi2coorddongjie()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawText("CSI-T", ix[16]+4, iy[1]-2, WhitePaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=90;
		int pointScale=1;
		//int CSIData=
		float xScale=ix1Scale;
		timeScale=mmainset.majuitimescale;
		scale=5;
		
//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==1)
		{
			timeScale=5;
		}
		if(timeScale==5)
		{
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		
		if(scimovedongjielen<0)
		{
			scimovedongjielen=0;
		}
		if(mmainset.CSI.size()>(pointSize))
		{
			startX=(mmainset.CSI.size()-pointSize)/scale*scale;
			dataSize=pointSize;
		}
		else
		{
			startX=0;
			dataSize=mmainset.CSI.size()/scale*scale;
		}
		
		startX=(mmainset.csidongjielen-pointSize-scimovedongjielen)/scale*scale;
		dataSize=pointSize;
		if(startX<0)
		{
			startX=0;
			scimovedongjielen=mmainset.csidongjielen-pointSize;
		}
		if(mmainset.csidongjielen<=(pointSize))
		{
			startX=0;
			dataSize=mmainset.csidongjielen/scale*scale;
		}
		CurDataLen=(startX+dataSize);
		//float[] hx=new float[dataSize];
		//float[] hy=new float[dataSize];
		float hstartx;
		float hendx;
		float hmiddlex;
		float hstarty;
		float hendy;
		float maxy;
		float miny;
		int SCIData=0;

	//	if(preDataSize!=(mmainset.CSI.size()/scale*scale))
		if(mmainset.CSI.size()>0)
		{
			//preDataSize=(mmainset.CSI.size()/scale*scale);
			for(int i=0;i<dataSize;i=i+scale)
			{
				int temp=mmainset.CSI.get(i+startX)&0xff;
				if(temp>100)
				{
					temp=0;
				}
				SCIData=100-temp;
				hstarty=ipoint[1].y;			
				if(SCIData<100)
					hstarty=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					hstarty=ipoint[0].y;
				else if(SCIData==100)
					hstarty=ipoint[1].y;
				hstartx=ix[i/scale]-xScale*2/3;
				temp=mmainset.CSI.get(i+scale-1+startX)&0xff;
				if(temp>100)
				{
					temp=0;
				}
				SCIData=100-temp;
				hendy=ipoint[1].y;			
				if(SCIData<100)
					hendy=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					hendy=ipoint[1].y;
				else if(SCIData==100)
					hendy=ipoint[0].y;
				hendx=ix[i/scale]-xScale/3;
				hmiddlex=ix[i/scale]-xScale/2;
				
				int Value[]=getThreadValue(mmainset.CSI,i,i+scale);
	//			Log.i(TAG,"liuliangxiang value0="+Value[0]);
	//			Log.i(TAG,"liuliangxiang value1="+Value[1]);
				temp=Value[0];
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				miny=ipoint[1].y;			
				if(SCIData<100)
					miny=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					miny=ipoint[1].y;
				else if(SCIData==100)
					miny=ipoint[0].y;
				temp=Value[1];
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				maxy=ipoint[1].y;			
				if(SCIData<100)
					maxy=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					maxy=ipoint[1].y;
				else if(SCIData==100)
					maxy=ipoint[0].y;
				
				mCanvas.drawLine(hstartx, maxy, hendx, maxy, mSLine2Paint);
				mCanvas.drawLine(hstartx, miny, hendx, miny, mSLine2Paint);
				if(hstarty>hendy)
				{
					mCanvas.drawRect(new RectF(hstartx,hendy,hendx,hstarty), mSLine2Paint);
					mCanvas.drawLine(hmiddlex,hstarty,hmiddlex,miny,mSLine2Paint);
					mCanvas.drawLine(hmiddlex,maxy,hmiddlex,hendy,mSLine2Paint);
				}
				else
				{
					mCanvas.drawRect(new RectF(hstartx,hstarty,hendx,hendy), mSLine2ePaint);
					mCanvas.drawLine(hmiddlex,maxy,hmiddlex,hstarty,mSLine2Paint);
					mCanvas.drawLine(hmiddlex,hendy,hmiddlex,miny,mSLine2Paint);
				}
			}
		}
	
		if(curix!=-1)
		{
			int temp=(int)((curix-ipoint[0].x)*5/(xScale))+startX;
			if(temp>=mmainset.csidongjielen)
				DataNum=temp-1;
			else
				DataNum=temp;
			if((temp<mmainset.csidongjielen)&&(temp>0))
			{
				int data=mmainset.CSI.get(temp);
				int hour=(temp)/3600;
				int min=(temp)%3600/60;
				int second=(temp)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				mCanvas.drawText("("+strmin+":"+strsec+","+Integer.toString(data&0xff)+")", curix, ipoint[0].y+40, BSTextPaint);
				mCanvas.drawLine(curix, ipoint[0].y, curix, ipoint[2].y, mSLine2ePaint);
			}
			
		}
		
		for(int k=0;k<=18;k=k+6)
		{
			float x;//x1[]=new int[19];
			x=ipoint[0].x+k*ix1Scale;
		//	int scale=timeScale;
			if(k%6==0)
			{
				int hour=(startX+k*timeScale)/3600;
				int min=(startX+k*timeScale)%3600/60;
				int second=(startX+k*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			}
		}
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y, CoordTextPaint);
	}


	
	public void drawi2coordshow()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawText(mcontext.getString(R.string.csi_t_show), ix[14]+4, iy[1]-2, WhitePaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=90;
		int pointScale=1;
		//int CSIData=
		float xScale=ix1Scale;
		timeScale=5;
		scale=5;
		int endXSize;
		int startX2;
		int endX2Size;
		
//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==1)
		{
			timeScale=5;
		}
		if(timeScale==5)
		{
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		
		
		
//		
//		if(mmainset.CSISHOW.length>(pointSize))
//		{
//			startX=(mmainset.CSISHOW.length-pointSize-1)/scale*scale;
//			dataSize=pointSize;
//		}
//		else
//		{
//			startX=0;
//			dataSize=pointSize;
//		}
		//float[] hx=new float[dataSize];
		//float[] hy=new float[dataSize];
		float hstartx;
		float hendx;
		float hmiddlex;
		float hstarty;
		float hendy;
		float maxy;
		float miny;
		float avg;
		int SCIData=0;
		ArrayList<Integer> tempArray=new ArrayList<Integer>();
		tempArray.clear();
		startsci2showx++;
		if(startsci2showx>=mmainset.CSISHOW.length)
		{
			startsci2showx=0;
		}
		//startsci2showx=95;
//		for(int i=startsci2showx/scale*scale;i<dataSize;i++)
//		{
//			tempArray.add(mmainset.CSISHOW[i]);
//		}
//		for(int i=0;i<startsci2showx/scale*scale;i++)
//		{
//			tempArray.add(mmainset.CSISHOW[i]);
//		}
		
		int starttemp=(startsci2showx-1)/pointSize*pointSize;
		startX=starttemp;
		endXSize=startsci2showx/scale*scale-startX;
//		Log.i(TAG,"liuliangxiang startX="+startX);
//		Log.i(TAG,"liuliangxiang endXSize="+endXSize);
//		Log.i(TAG,"liuliangxiang mmainset.CSISHOW.length="+mmainset.CSISHOW.length);
		if(startsci2showx%pointSize==0)
		{
			startX2=-2;
			endX2Size=-1;
		}
		else
		{
			startX2=starttemp-pointSize;
			endX2Size=pointSize;
		}
		for(int i=0;i<pointSize;i++)
		{
			if(i<endXSize)
			{
				tempArray.add(mmainset.CSISHOW[i+startX]);
			}
			else
			{
				if(startX2<0)
				{
					break;
				}
				tempArray.add(mmainset.CSISHOW[i+startX2]);
			}
		}
		

	//	if(preDataSize!=(mmainset.CSI.size()/scale*scale))
		{
		//	preDataSize=(mmainset.CSI.size()/scale*scale);
			for(int i=0;i<tempArray.size();i=i+scale)
			{				
				SCIData=100-tempArray.get(i)&0xff;
				hstarty=ipoint[1].y;			
				if(SCIData<100)
					hstarty=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==100)
					hstarty=ipoint[0].y;
			//	Log.i(TAG,"liuliangxiang dataSize="+dataSize);
				hstartx=ix[i/scale]-xScale*2/3;
				SCIData=100-tempArray.get(i+scale-1)&0xff;
				hendy=ipoint[1].y;			
				if(SCIData<100)
					hendy=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==100)
					hendy=ipoint[0].y;
				hendx=ix[i/scale]-xScale/3;
				hmiddlex=ix[i/scale]-xScale/2;
				
				int Value[]=getThreadValue(tempArray,i,i+scale);

				SCIData=100-Value[0];
				miny=ipoint[1].y;			
				if(SCIData<100)
					miny=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==100)
					miny=ipoint[0].y;
				
				SCIData=100-Value[1];
				maxy=ipoint[1].y;			
				if(SCIData<100)
					maxy=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==100)
					maxy=ipoint[0].y;
				
				SCIData=100-Value[2];
				avg=ipoint[1].y;			
				if(SCIData<100)
					avg=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==100)
					avg=ipoint[0].y;
				
			//	mCanvas.drawLine(hstartx, maxy, hendx, maxy, mSLine2Paint);
			//	mCanvas.drawLine(hstartx, miny, hendx, miny, mSLine2Paint);
				mCanvas.drawLine(hmiddlex,maxy, hmiddlex, miny, mSLine2Paint);
				mCanvas.drawRect(new RectF(hstartx,avg-2,hendx,avg+2), mSLine2ePaint);
				
//				if(hstarty>hendy)
//				{
//					mCanvas.drawRect(new RectF(hstartx,hendy,hendx,hstarty), mSLine2Paint);
//					//mCanvas.drawLine(hmiddlex,hstarty,hmiddlex,miny,mSLine2Paint);
//					//mCanvas.drawLine(hmiddlex,maxy,hmiddlex,hendy,mSLine2Paint);
//				}
//				else
//				{
//					mCanvas.drawRect(new RectF(hstartx,hstarty,hendx,hendy), mSLine2ePaint);
//					mCanvas.drawLine(hmiddlex,maxy,hmiddlex,hstarty,mSLine2Paint);
//					mCanvas.drawLine(hmiddlex,hendy,hmiddlex,miny,mSLine2Paint);
//				}
			}
		}
		for(int k=0;k<=pointSize;k=k+6*timeScale)
		{
			//Log.i(TAG,"liuliangxiang k="+k);
			//float x;//x1[]=new int[19];
			//x=ipoint[0].x+k*ix1Scale;
		//	int scale=timeScale;
			//if(k%(6*timeScale)==0)
			{
				float x;//x1[]=new int[19];
				//Log.i(TAG);
				x=ipoint[0].x+k/timeScale*ix1Scale;
			//	int hour=(startX+1+k)/3600;
			//	int min=(startX+1+k)%3600/60;
			//	int second=(startX+1+k)%60;
				int hour;//=(startX+1+k)/3600;
				int min;//=(startX+1+k)%3600/60;
				int second;//=(startX+1+k)%60;
				if((k>endXSize)&&(startX2>=0))
				{
					hour=(startX-pointSize+k)/3600;
					min=(startX-pointSize+k)%3600/60;
					second=(startX-pointSize+k)%60;
				}
				else
				{
					hour=(startX+k)/3600;
					min=(startX+k)%3600/60;
					second=(startX+k)%60;
				}
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			}
		}
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y, CoordTextPaint);
	}

	public boolean onTouchEvent(MotionEvent event)
	{
		float x=event.getX();
		float y=event.getY();
		if((!mmainset.isdongjie)&&(!mmainset.isRecData))
			return false;
	//	Log.i(TAG,"liuliangxiang onTouchEvent event.getAction()="+event.getAction());
		switch(event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				touchDownX=x;
				touchDownY=y;
				if((mmainset.boxingType==mmainset.DOUBLETYPE))
				{
				//	Log.i(TAG,"liuliangxiang mmainset.boxingType="+mmainset.boxingType);
					if((x<spoint[8].x)&&(x>spoint[4].x)&&(y<spoint[2].y)&&(y>spoint[0].y))
					{	
						//if(mmainset.isRecData)
							cursix=x;
						//Log.i(TAG,"liuliangxiang cursex="+cursex);
					}
					else if((x<spoint[6].x)&&(x>spoint[1].x)&&(y<spoint[5].y)&&(y>spoint[3].y))
					{
						//if(mmainset.isRecData)
							curseegx=x;
						//else
						//{
							
						//}
					}
				}
				else if((mmainset.boxingType==mmainset.EEGTYPE))
				{
					if((x<epoint[3].x)&&(x>epoint[0].x)&&(y<epoint[2].y)&&(y>epoint[0].y))
					{
						//if(mmainset.isRecData)
							curex=x;
					}
				}
				else if((mmainset.boxingType==mmainset.SCITYPE)||(mmainset.boxingType==mmainset.SCI2TYPE))
				{
					//Log.i(TAG,"liuliangxiang curix22="+curix);
					if((x<ipoint[2].x)&&(x>ipoint[0].x)&&(y<ipoint[2].y)&&(y>ipoint[0].y))
					{
						//if(mmainset.isRecData)
							curix=x;
							cursix=x;
//							Log.i(TAG,"liuliangxiang mmainset.boxingType="+mmainset.boxingType);
//							Log.i(TAG,"liuliangxiang curix="+curix);
					}
				}
				break;
			case MotionEvent.ACTION_MOVE:
//				if((mmainset.boxingType==mmainset.SCITYPE)||(mmainset.boxingType==mmainset.SCI2TYPE))
//				{
//					float xScale=ix1Scale;
//					int timeScale=mmainset.majuitimescale;		
//					if(mmainset.boxingType==mmainset.SCI2TYPE)
//					{
//						xScale=ix5Scale;
//						if(timeScale<5)
//						{
//							timeScale=5;
//						}
//					}
//					if(timeScale==5)
//					{
//						xScale=ix5Scale;
//					}
//					else if(timeScale==10)
//					{
//						xScale=ix10Scale;
//					}
//					else if(timeScale>=60)
//					{
//						xScale=ix60Scale;
//					}
//					else if(timeScale>=60)
//					{
//						xScale=ix120Scale;
//					}
//					if((x<ipoint[2].x)&&(x>ipoint[0].x)&&(y<ipoint[1].y)&&(y>ipoint[0].y))
//					{
//						Log.i(TAG,"liuliangxiang MotionEvent.ACTION_MOVE");
//						if((touchDownX==-1)||(touchDownY==-1))
//						{
//							touchDownX=x;
//							touchDownY=y;
//							curix=x;
//							break;
//						}
//						if(Math.abs(x-touchDownX)>6)
//						{
//							synchronized (mSurfaceHolder)
//							{
//								if(mmainset.isRecData)
//								{
//									scimovereclen=(int)(scimovereclen+Math.floor((touchDownX-x)/xScale));
//									curix=x;
//									if(scimovereclen<=0)
//									{
//										scimovereclen=0;
//									}
//								}
//								else
//								{
//									if(mmainset.isdongjie)
//									{
//										scimovedongjielen=(int)(scimovedongjielen+Math.floor((touchDownX-x)/xScale));
//										curix=x;
//										if(scimovedongjielen<=0)
//										{
//											scimovedongjielen=0;
//										}
//									}
//								}
//								
//								
//							/*
//								if((x<touchDownX)&&(mmainset.csidongjielen<=18*timeScale))
//								{
//									break;
//								}*/
//								/*
//								mmainset.csidongjielen=(int)(mmainset.csidongjielen+Math.floor((touchDownX-x)/xScale));
//								//if(mmainset.dongjielen<0)
//									//mmainset.dongjielen=0;
//								if(mmainset.csidongjielen>mmainset.CSI.size())
//									mmainset.csidongjielen=mmainset.CSI.size();
//								else if(mmainset.csidongjielen<=18*timeScale)
//									mmainset.csidongjielen=18*timeScale;
//								
//								touchDownX=x;
//								touchDownY=y;*/
//
//							}
//						}
//					}
//					else 
//					{
//						touchDownX=-1;
//						touchDownY=-1;
//						curix=-1;
//						DataNum=-1;
//					}
//				}
				if((mmainset.boxingType==mmainset.SCITYPE)||(mmainset.boxingType==mmainset.SCI2TYPE)||(mmainset.boxingType==mmainset.EEGTYPE))
				{
					
					if((x<ipoint[2].x)&&(x>ipoint[0].x)&&(y<ipoint[2].y)&&(y>ipoint[0].y))
					{
							float xScale=ix1Scale;
							int timeScale=mmainset.majuirectimeScale;	
							if(timeScale==5)
							{
								xScale=ix5Scale;
							}else if(timeScale==2){
								xScale=ix2Scale;
							}
							else if(timeScale==10)
							{
								xScale=ix10Scale;
							}
							else if(timeScale==60)
							{
								xScale=ix60Scale;
							}
							else if(timeScale>=60)
							{
								xScale=ix120Scale;
							}
							if((touchDownX==-1)||(touchDownY==-1))
							{
								touchDownX=x;
								touchDownY=y;
								curix=x;
								cursix=x;
								break;
							}
							if(Math.abs(x-touchDownX)>6)
							{
								synchronized (mSurfaceHolder)
								{
									if(mmainset.isRecData)
									{
										scimovereclen=(int)(scimovereclen+Math.floor((touchDownX-x)/xScale));
										curix=x;
										cursix=x;
										//Log.i(TAG, "liuliangxiang scimovereclen="+scimovereclen);
										if(scimovereclen<=0)
										{
											scimovereclen=0;
										}
									}
									else
									{
										if(mmainset.isdongjie)
										{
											scimovedongjielen=(int)(scimovedongjielen+Math.floor((touchDownX-x)/xScale));
											curix=x;
											if(scimovedongjielen<=0)
											{
												scimovedongjielen=0;
											}
										}
									}									
									touchDownX=x;
									touchDownY=y;
								}
							}
					}
					else 
					{
						touchDownX=-1;
						touchDownY=-1;
						DataNum=-1;
						curix=-1;
						cursix=-1;
					}
				}		
//				else if((mmainset.boxingType==mmainset.EEGTYPE))
//				{
//					float xScale=ex1Scale;
//					int timeScale=mmainset.eegtimescale;					
//
//					if(timeScale==5)
//					{
//						xScale=ex5Scale;
//					}
//					else if(timeScale==10)
//					{
//						xScale=ex10Scale;
//					}
//					else if(timeScale==60)
//					{
//						xScale=ex60Scale;
//					}
//					else if(timeScale==120)
//					{
//						xScale=ex120Scale;
//					}
//					
//					if((x<epoint[3].x)&&(x>ipoint[0].x)&&(y<ipoint[2].y)&&(y>ipoint[0].y))
//					{
//						if((touchDownX==-1)||(touchDownY==-1))
//						{
//							touchDownX=x;
//							touchDownY=y;
//							curex=x;
//							break;
//						}
//						if(Math.abs(x-touchDownX)>6)
//						{
//							synchronized (mSurfaceHolder)
//							{
//								if(mmainset.isRecData)
//								{
//									eegmovereclen=(int)(eegmovereclen+Math.floor((touchDownX-x)/(xScale*80)));
//									//eegmovereclen=eegmovereclen/10;
//									curex=x;
//									if(eegmovereclen<=0)
//									{
//										eegmovereclen=0;
//									}
//								}
//								else
//								{
//									if(mmainset.isdongjie)
//									{
//										eegmovedongjielen=(int)(eegmovedongjielen+Math.floor((touchDownX-x)/(xScale*80)));
//										//eegmovedongjielen=eegmovedongjielen/10;
//										curex=x;
//										if(eegmovedongjielen<=0)
//										{
//											eegmovedongjielen=0;
//										}
//									}
//								}
//							/*
//								if((x<touchDownX)&&(mmainset.eegdongjielen<(timeScale*300)))
//								{
//									break;
//								}
//								mmainset.eegdongjielen=(int)(mmainset.eegdongjielen+Math.floor((touchDownX-x)/xScale));
//								Log.i(TAG,"liuliangxiang eegdongjielen="+mmainset.eegdongjielen);
//								//if(mmainset.dongjielen<0)
//									//mmainset.dongjielen=0;
//								if(mmainset.eegdongjielen>mmainset.EEG.size())
//									mmainset.eegdongjielen=mmainset.EEG.size();
//								else if(mmainset.eegdongjielen<=timeScale*300)
//									mmainset.eegdongjielen=timeScale*300;
//								
//								touchDownX=x;
//								touchDownY=y;*/
//
//							}
//						}
//					}
//					else 
//					{
//						touchDownX=-1;
//						touchDownY=-1;
//						curex=-1;
//						DataNum=-1;
//					}
//				}	
				else if((mmainset.boxingType==mmainset.DOUBLETYPE))
				{
				//	Log.i(TAG,"liuliangxiang mmainset.boxingType="+mmainset.boxingType);
//					if((x<spoint[8].x)&&(x>spoint[4].x)&&(y<spoint[5].y)&&(y>spoint[2].y))
//					{
//						float xScale=sex1Scale;
//						int timeScale=mmainset.eegtimescale;					
//	
//						if(timeScale==5)
//						{
//							xScale=sex5Scale;
//						}
//						else if(timeScale==10)
//						{
//							xScale=sex10Scale;
//						}
//						else if(timeScale==60)
//						{
//							xScale=sex60Scale;
//						}
//						else if(timeScale==120)
//						{
//							xScale=sex120Scale;
//						}	
//						if((touchDownX==-1)||(touchDownY==-1))
//						{
//							touchDownX=x;
//							touchDownY=y;
//							cursix=x;
//							break;
//						}
//						if(Math.abs(x-touchDownX)>6)
//						{
//							synchronized (mSurfaceHolder)
//							{
////								if((x<touchDownX)&&(mmainset.eegdongjielen<(timeScale*300)))
////								{
////									break;
////								}
//								if(mmainset.isRecData)
//								{
//									eegmovereclen=(int)(eegmovereclen+Math.floor((touchDownX-x)/(xScale*60)));
//									//eegmovereclen=eegmovereclen/10;
//									cursex=x;
//									if(eegmovereclen<=0)
//									{
//										eegmovereclen=0;
//									}
//								}
//								else
//								{
//									eegmovedongjielen=(int)(eegmovedongjielen+Math.floor((touchDownX-x)/(xScale*60)));
//									//eegmovedongjielen=eegmovedongjielen/10;
//									cursix=x;
//									if(eegmovedongjielen<=0)
//									{
//										eegmovedongjielen=0;
//									}
//								}
//								
////								Log.i(TAG,"liuliangxiang eegmovereclen="+eegmovereclen);
//								
////								mmainset.eegdongjielen=(int)(mmainset.eegdongjielen+Math.floor((touchDownX-x)/xScale));
////								Log.i(TAG,"liuliangxiang eegdongjielen="+mmainset.eegdongjielen);
////								//if(mmainset.dongjielen<0)
////									//mmainset.dongjielen=0;
////								if(mmainset.eegdongjielen>mmainset.EEG.size())
////									mmainset.eegdongjielen=mmainset.EEG.size();
////								else if(mmainset.eegdongjielen<=timeScale*300)
////									mmainset.eegdongjielen=timeScale*300;
//								
//								touchDownX=x;
//								touchDownY=y;
//
//							}
//						}
//					}
//					else
					if((x<spoint[6].x)&&(x>spoint[1].x)&&(y<spoint[2].y)&&(y>spoint[0].y))
					{
						float xScale=six1Scale;
						int timeScale=mmainset.majuirectimeScale;					
//						if(timeScale==1)
//						{
//							xScale=ix1Scale;
//							pointSize=19;
//						}
//						else 
						if(timeScale==5)
						{
							xScale=six5Scale;
						}else if(timeScale==2)
						{
							xScale=six2Scale;
						}
						else if(timeScale==10)
						{
							xScale=six10Scale;
						}
						else if(timeScale==60)
						{
							xScale=six60Scale;
						}
						else if(timeScale>=60)
						{
							xScale=six120Scale;
						}
						if((touchDownX==-1)||(touchDownY==-1))
						{
							touchDownX=x;
							touchDownY=y;
							cursix=x;
							break;
						}
						if(Math.abs(x-touchDownX)>6)
						{
							synchronized (mSurfaceHolder)
							{
//								if((x<touchDownX)&&(mmainset.csidongjielen<=18*timeScale))
//								{
//									break;
//								}
								if(mmainset.isRecData)
								{
									scimovereclen=(int)(scimovereclen+Math.floor((touchDownX-x)/xScale));
									cursix=x;
									if(scimovereclen<=0)
									{
										scimovereclen=0;
									}
								}
								else
								{
									if(mmainset.isdongjie)
									{
										scimovedongjielen=(int)(scimovedongjielen+Math.floor((touchDownX-x)/xScale));
										cursix=x;
										if(scimovedongjielen<=0)
										{
											scimovedongjielen=0;
										}
									}
								}
//								mmainset.csidongjielen=(int)(mmainset.csidongjielen+Math.floor((touchDownX-x)/xScale));
//								//if(mmainset.dongjielen<0)
//									//mmainset.dongjielen=0;
//								if(mmainset.csidongjielen>mmainset.CSI.size())
//									mmainset.csidongjielen=mmainset.CSI.size();
//								else if(mmainset.csidongjielen<=18*timeScale)
//									mmainset.csidongjielen=18*timeScale;
								
								touchDownX=x;
								touchDownY=y;

							}
						}
					}else if((x<spoint[6].x)&&(x>spoint[1].x)&&(y<spoint[5].y)&&(y>spoint[3].y)){
						float xScale=seeg12Scale/3;
						int eegScaleSet=mmainset.eegrecScale;
//						if(eegScaleSet==1){
//							xScale=seeg12Scale;
//						}else if(eegScaleSet==2){
//							xScale=seeg25Scale;
//						}else if(eegScaleSet==3){
//							xScale=seeg50Scale;
//						}
						int timeScale=mmainset.majuitimescale;
						if((touchDownX==-1)||(touchDownY==-1))
						{
							touchDownX=x;
							touchDownY=y;
							curseegx=x;
							break;
						}
						if(Math.abs(x-touchDownX)>6)
						{
							synchronized (mSurfaceHolder)
							{
								if(mmainset.isRecData)
								{
									eegmovereclen=(int)(eegmovereclen+Math.floor((touchDownX-x)/xScale));
									curseegx=x;
									if(eegmovereclen<=0)
									{
										eegmovereclen=0;
									}
								}
								else
								{
									if(mmainset.isdongjie)
									{
										scimovedongjielen=(int)(scimovedongjielen+Math.floor((touchDownX-x)/xScale));
										curseegx=x;
										if(scimovedongjielen<=0)
										{
											scimovedongjielen=0;
										}
									}
								}									
								touchDownX=x;
								touchDownY=y;
							}
						}
					}
					else 
					{
						touchDownX=-1;
						touchDownY=-1;
						DataNum=-1;
						cursix=-1;
						cureegx=-1;
						cursex=-1;
					}
				}		
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				touchDownX=-1;
				touchDownY=-1;
				cursex=-1;
				cursix=-1;
				curex=-1;
				curix=-1;
				curseegx=-1;
				DataNum=-1;
				break;
		}
		
		/*
		if((mmainset.boxingType==mmainset.SCITYPE)||(mmainset.boxingType==mmainset.SCI2TYPE))
		{
			if((x<ipoint[2].x)&&(x>ipoint[0].x)&&(y<ipoint[1].y)&&(y>ipoint[0].y))
			{
				if(touchDownX==-1)
					touchDownX=x;
				if(touchDownY==-1)
					touchDownY=y;
				synchronized (mSurfaceHolder)
				{
					mmainset.dongjielen=(int)(mmainset.dongjielen+(x-touchDownX)*18/(ipoint[2].x-ipoint[0].x));
				//	mmainset.dongjielen=(int)(((mmainset.dongjielen>mmainset.CSI.size())?(mmainset.CSI.size()):(mmainset.dongjielen+(x-touchDownX)*18/(ipoint[2].x-ipoint[0].x)));
					if(mmainset.dongjielen<0)
						mmainset.dongjielen=0;
					if(mmainset.dongjielen>mmainset.CSI.size())
						mmainset.dongjielen=mmainset.CSI.size();
					Log.i(TAG,"liuliangxiang mmainset.dongjielen="+mmainset.dongjielen);
					touchDownX=x;
					touchDownY=y;
				}
			}
			else
			{
				touchDownX=-1;
				touchDownY=-1;
			}
		}
		else if(mmainset.boxingType==mmainset.EEGTYPE)
		{
			if((x<ipoint[2].x)&&(x>ipoint[0].x)&&(y<ipoint[1].y)&&(y>ipoint[0].y))
			{
				if(touchDownX==-1)
					touchDownX=x;
				if(touchDownY==-1)
					touchDownY=y;
				mmainset.dongjielen=(int)((mmainset.dongjielen>mmainset.CSI.size())?(mmainset.CSI.size()):((mmainset.dongjielen+(x-touchDownX))*18/(epoint[2].x-epoint[0].x)));
				if(mmainset.dongjielen<0)
					mmainset.dongjielen=0;
				touchDownX=x;
				touchDownY=y;
			}
			else
			{
				touchDownX=-1;
				touchDownY=-1;
			}
		}
		
		
		
		if((event.getAction()==MotionEvent.ACTION_UP)||(event.getAction()==MotionEvent.ACTION_CANCEL))
		{
			touchDownX=-1;
			touchDownY=-1;
		}
		/*
		switch(event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				if(mmainset.boxingType==mmainset.SCITYPE)
				{
					if((x<ipoint[2].x)&&(x>ipoint[0].x)&&(y<ipoint[1].y)&&(y>ipoint[0].y))
					{
						touchDownX=x;
						touchDownY=y;
					}
					else
					{
						touchDownX=
					}
				}
				
				break;
			case MotionEvent.ACTION_MOVE:
				
				break;
		}*/
		return true;
		
		//return super.onTouchEvent(event);
	}
	

	
	public void drawicoorddongjie()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawText("CSI", ix[16]+4, iy[1]-2, WhitePaint);
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y, CoordTextPaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		//int CSIData=
		float xScale=ix1Scale;
	//	float yScale=(height)*15/2400;
		timeScale=mmainset.majuitimescale;
		scale=1;
		
//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=six120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		if(scimovedongjielen<0)
		{
			scimovedongjielen=0;
		}
		startX=mmainset.csidongjielen-pointSize-1-scimovedongjielen;
		dataSize=pointSize;
		if(startX<-1)
		{
			startX=-1;
			scimovedongjielen=mmainset.csidongjielen-pointSize;
		}
		if(mmainset.csidongjielen<=(pointSize))
		{
			startX=-1;
			dataSize=mmainset.csidongjielen;
		}
		CurDataLen=startX+dataSize;
		float[] hx=new float[dataSize+1];
		float[] hy=new float[dataSize+1];
//		Log.i(TAG,"liuliangxiang hx="+hx.length);
		int SCIData=0;
        if(mmainset.CSI.size()>0)
		{
			PointPath.reset();
			bmpdownarray.clear();
			bmpuparray.clear();
			if(mmainset.CSI.size()>0)
			{	
				if((mmainset.csidongjielen<=pointSize)||(startX<0))
				{
					PointPath.moveTo(ipoint[1].x, ipoint[1].y);
				}
				else
				{
					hx[0]=ipoint[0].x;
					hy[0]=ipoint[1].y;
					int temp=mmainset.CSI.get(startX)&0xff;
					if(temp>100)temp=0;
					SCIData=100-temp;
					if(SCIData<100)
						hy[0]=iy[SCIData/10]+iyScale*(SCIData%10);
					else if(SCIData==0)
						hy[0]=ipoint[0].y;
					else if(SCIData==100)
						hy[0]=ipoint[1].y;
					PointPath.moveTo(hx[0], hy[0]);
				}
			}
			float px;
			float height = 0;
			for(int i=1;i<hx.length;i=i+pointScale)
			{
				int temp=mmainset.CSI.get(i+startX)&0xff;
				if(temp>100)temp=0;
				SCIData=100-temp;
				hy[i]=ipoint[2].y;			
				if(SCIData<100)
					hy[i]=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					hy[i]=ipoint[0].y;
				else if(SCIData==100)
					hy[i]=ipoint[2].y;
				if(i<scale)
				{
					hx[i]=ipoint[0].x+xScale*(i%scale)+xScale/6;
				}
				else
				{
					hx[i]=ix[i/scale-1]+xScale*(i%scale)+xScale/6;
				}
				PointPath.lineTo(hx[i], hy[i]);
			}
			if(dataSize>0)
			{
				PointPath.moveTo(hx[dataSize], hy[dataSize]);
			}
		}
		mCanvas.drawPath(PointPath, mSLinePaint);
//		Log.i(TAG,"liuliangxiang cursix="+cursix);
		if(curix!=-1)
		{
			
			int temp=(int)((curix-ipoint[0].x)/xScale)+startX+1;
		//	Log.i(TAG,"liuliangxiang temp="+temp);
			if(temp>=mmainset.csidongjielen)
				DataNum=temp-1;
			else
				DataNum=temp;
			if((temp<mmainset.csidongjielen)&&(temp>0))
			{
				int data=mmainset.CSI.get(temp);
				int hour=(temp)/3600;
				int min=(temp)%3600/60;
				int second=(temp)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				mCanvas.drawText("("+strmin+":"+strsec+","+Integer.toString(data&0xff)+")", curix, ipoint[0].y+40, BSTextPaint);
				mCanvas.drawLine(curix, ipoint[0].y, curix, ipoint[2].y, mSLine2ePaint);
			}		
		}
		PointPath.close();
		for(int j=0;j<bmpuparray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapDown, bmpuparray.get(j)-8, bmpuparray.get(j+1), null);
		}
		for(int j=0;j<bmpdownarray.size();j=j+2)
		{
			mCanvas.drawBitmap(bitmapUp, bmpdownarray.get(j)-8, bmpdownarray.get(j+1), null);
		}
		
		for(int k=0;k<=18;k=k+6)
		{
			float x;//x1[]=new int[19];
			x=ipoint[0].x+k*ix1Scale;
		//	int scale=timeScale;
			if(k%6==0)
			{
				int hour=(startX+1+k*timeScale)/3600;
				int min=(startX+1+k*timeScale)%3600/60;
				int second=(startX+1+k*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			}
		}
	}
/*
	public void drawecoord()
	{
		mCanvas.drawLine(epoint[0].x,epoint[0].y,epoint[2].x,epoint[2].y,CoordinatePaint);
		mCanvas.drawLine(epoint[1].x,epoint[1].y,epoint[3].x,epoint[3].y,CoordinatePaint);
		mCanvas.drawLine(epoint[2].x,epoint[2].y,epoint[4].x,epoint[4].y,mGLinePaint);
		
		for(int i=0;i<ey.length;i++)
		{
			mCanvas.drawLine(epoint[0].x, ey[i], epoint[3].x, ey[i], mGLinePaint);
		}
		for(int j=0;j<ex.length;j++)
		{
			mCanvas.drawLine(ex[j],epoint[0].y ,ex[j], epoint[2].y, mGLinePaint);
		}

		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=300;
		int pointScale=1;
		boolean isLess;
		//int CSIData=
		float xScale=ex1Scale;
		timeScale=mmainset.eegtimescale;
		int eegLen=mmainset.EEG.size();
		//Log.i(TAG,"Liuliangxiang eegLen="+eegLen);
		scale=10;
		
//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==5)
		{
			xScale=ex5Scale;
			pointSize=1500;
			pointScale=5;
			scale=50;
		}
		else if(timeScale==10)
		{
			xScale=ex10Scale;
			pointSize=3000;
			pointScale=10;
			scale=100;
		}
		else if(timeScale==60)
		{
			xScale=ex60Scale;
			pointSize=18000;
			pointScale=53;
			scale=600;
		}
		else if(timeScale==120)
		{
			xScale=ex120Scale;
			pointSize=36000;
			pointScale=95;
			scale=1200;
		}
		
		if(eegLen>(pointSize/100))
		{
			startX=((eegLen-1)*100-pointSize);
			//Log.i(TAG,"liuliangxiang startX="+startX);
			dataSize=pointSize;
			isLess=false;
		}
		else
		{
			startX=0;
			isLess=true;
			dataSize=(mmainset.EEG.size())*100;
		}
		//Log.i(TAG,"liuliangxiang dataSize="+dataSize);
	//	float[] hx=new float[dataSize];
	//	float[] hy=new float[dataSize];
		float hx = 0;
		float hy = 0;
		Integer[] EEGData=new Integer[100];
		if(preEEGDataSize!=eegLen)
		{
			preEEGDataSize=eegLen;
			EEGflen=0;
		}
		float hyk;		
		if((EEGflen<=100)&&(dataSize>=100))
		{
			meLinePath.reset();			
			if(dataSize<pointSize)
			{
				meLinePath.moveTo(epoint[1].x, epoint[1].y);
			}
			else
			{
				int temp;
				EEGData=mmainset.EEG.get(startX/100);
				temp=startX%100;
				hx=epoint[1].x;
				
				if(EEGData[temp]>=0x80)
				{
					EEGData[temp]=0x7f-EEGData[temp];
				}
			//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
				if(EEGData[temp]>=16)
				{
					hyk=ey[EEGData[temp]/16-1]-eyScale*(EEGData[temp]%16);
				}
				else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
				{
					hyk=epoint[1].y-eyScale*(EEGData[temp]);
				}
				else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
				{
					hyk=epoint[1].y-EEGData[temp]*eyScale;
				}
				else
				{
					hyk=ey[-EEGData[temp]/16+7]-eyScale*(EEGData[temp]%16);
				}
				meLinePath.moveTo(hx, hyk);
			}
			int step = 1;
			if(scale==10)
			{
				step=1;
			}
			else if(scale==50)
			{
				step=2;
			}
			else if(scale==100)
			{
				step=5;
			}
			else if(scale==600)
			{
				step=29;
			}
			else if(scale==1200)
			{
				step=53;
			}
//			else if(scale==12000)
//			{
//				step=100;
//			}
			if(isLess)
			{	
				for(int k=0;k<(dataSize-100+EEGflen);k=k+step)
				{
					//hx[k]=ex[k/10]-xScale*((10-k)%10);
					int temp;
					hx=ex[k/scale]-xScale*(scale-k%scale-1);
					temp=(startX+k)%100;
					EEGData=mmainset.EEG.get((startX+k)/100);				
					if(EEGData[temp]>=0x80)
					{
						EEGData[temp]=0x7f-EEGData[temp];
					}
				//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
					if(EEGData[temp]>=16)
					{
						hy=ey[EEGData[temp]/16-1]-eyScale*(EEGData[temp]%16);
					}
					else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
					{
						hy=epoint[1].y-eyScale*(EEGData[temp]);
					}
					else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
					{
						hy=epoint[1].y-EEGData[temp]*eyScale;
					}
					else
					{
						hy=ey[-EEGData[temp]/16+7]-eyScale*(EEGData[temp]%16);
					}
					meLinePath.lineTo(hx, hy);
				}
			}
			else
			{
				//Log.i(TAG,"liuliangxiang isLess=false");
				for(int k=0;k<(dataSize);k=k+step)
				{
					//hx[k]=ex[k/10]-xScale*((10-k)%10);
					int temp;
					hx=ex[k/scale]-xScale*(scale-k%scale-1);
					EEGData=mmainset.EEG.get((startX+k+EEGflen)/100);	
					temp=(startX+k+EEGflen)%100;
					if(EEGData[temp]>=0x80)
					{
						EEGData[temp]=0x7f-EEGData[temp];
					}
				//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
					if(EEGData[temp]>=16)
					{
						hy=ey[EEGData[temp]/16-1]-eyScale*(EEGData[temp]%16);
					}
					else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
					{
						hy=epoint[1].y-eyScale*(EEGData[temp]);
					}
					else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
					{
						hy=epoint[1].y-EEGData[temp]*eyScale;
					}
					else
					{
						hy=ey[-EEGData[temp]/16+7]-eyScale*(EEGData[temp]%16);
					}
					meLinePath.lineTo(hx, hy);
				}
			}
			if(scale==10)
				EEGflen=EEGflen+20;
			else
				EEGflen=EEGflen+20;
		}
		
	//	Log.i(TAG,"EEGflen="+EEGflen);
		if(dataSize>0)
		{
			meLinePath.moveTo(hx, hy);
		//	Log.i(TAG,"SCIData="+SCIData);
		//	Log.i(TAG,"hy["+(mmainset.CSI.size()-1)+"]"+hy[dataSize-1]);
		}
		mCanvas.drawPath(meLinePath, mELinePaint);
		meLinePath.close();
		for(int k=0;k<=30;k=k+10)
		{
			float x;//x1[]=new int[19];

			x=epoint[0].x+(k*10)*ex1Scale;
			//x=ex[k/10]-xScale*(9-k%10);
		//	int scale=timeScale;
			//if(k%6==0)
			{
				int hour=(startX/100+(k/10)*timeScale)/3600;
				int min=(startX/100+(k/10)*timeScale)%3600/60;
				int second=(startX/100+(k/10)*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, epoint[2].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, epoint[2].y+12,CoordinatePaint);
			}
		}	
		mCanvas.drawText("127", epoint[0].x-28, ey[7]+8, CoordTextPaint);
		mCanvas.drawText("112", epoint[0].x-28, ey[6]+8, CoordTextPaint);
		mCanvas.drawText("96", epoint[0].x-20, ey[5]+8, CoordTextPaint);
		mCanvas.drawText("80", epoint[0].x-20, ey[4]+8, CoordTextPaint);
		mCanvas.drawText("64", epoint[0].x-20, ey[3]+8, CoordTextPaint);
		mCanvas.drawText("48", epoint[0].x-20, ey[2]+8, CoordTextPaint);
		mCanvas.drawText("32", epoint[0].x-20, ey[1]+8, CoordTextPaint);
		mCanvas.drawText("16", epoint[0].x-20, ey[0]+8, CoordTextPaint);
		mCanvas.drawText("0uv", epoint[0].x-26, epoint[1].y+8, CoordTextPaint);		
		
		mCanvas.drawText("-16", epoint[0].x-26, ey[8]+8, CoordTextPaint);
		mCanvas.drawText("-32", epoint[0].x-26, ey[9]+8, CoordTextPaint);
		mCanvas.drawText("-48", epoint[0].x-26, ey[10]+8, CoordTextPaint);
		mCanvas.drawText("-64", epoint[0].x-26, ey[11]+8, CoordTextPaint);
		mCanvas.drawText("-80", epoint[0].x-26, ey[12]+8, CoordTextPaint);
		mCanvas.drawText("-96", epoint[0].x-26, ey[13]+8, CoordTextPaint);
		mCanvas.drawText("-112", epoint[0].x-36, ey[14]+8, CoordTextPaint);
		mCanvas.drawText("-128", epoint[0].x-36, ey[15]+2, CoordTextPaint);
		
//		mCanvas.drawText("-100", epoint[0].x-32, epoint[2].y+8, CoordTextPaint);
//		mCanvas.drawText("-80", epoint[0].x-26, y[9]+8, CoordTextPaint);
//		mCanvas.drawText("-60", epoint[0].x-26, y[8]+8, CoordTextPaint);
//		mCanvas.drawText("-40", epoint[0].x-26, y[7]+8, CoordTextPaint);
//		mCanvas.drawText("-20", epoint[0].x-26, y[6]+8, CoordTextPaint);

	}
	*/
	
//	public void drawecoord()
//	{
//		mCanvas.drawLine(epoint[0].x,epoint[0].y,epoint[2].x,epoint[2].y,CoordinatePaint);
//		mCanvas.drawLine(epoint[1].x,epoint[1].y,epoint[3].x,epoint[3].y,CoordinatePaint);
//		mCanvas.drawLine(epoint[2].x,epoint[2].y,epoint[4].x,epoint[4].y,mGLinePaint);
//		
//		for(int i=0;i<ey.length;i++)
//		{
//			mCanvas.drawLine(epoint[0].x, ey[i], epoint[3].x, ey[i], mGLinePaint);
//		}
//		for(int j=0;j<ex.length;j++)
//		{
//			mCanvas.drawLine(ex[j],epoint[0].y ,ex[j], epoint[2].y, mGLinePaint);
//		}
//		mCanvas.drawText("EEG", ex[28], ey[6], WhitePaint);
//
//		int startX;
//		int dataSize = 0;
//		int timeScale;
//		int scale;
//		int pointSize=300;
//		int pointScale=1;
//		boolean isLess=false;
//		float xScale=ex1Scale;
//		timeScale=5;
//		int eegLen=mmainset.EEG.size();
//		scale=10;		
//		int endXSize;
//		int startX2;
//		int endX2Size;
//		if(timeScale==5)
//		{
//			xScale=ex5Scale;
//			pointSize=1500;
//			pointScale=5;
//			scale=50;
//		}
//		else if(timeScale==10)
//		{
//			xScale=ex10Scale;
//			pointSize=3000;
//			pointScale=10;
//			scale=100;
//		}
//		else if(timeScale==60)
//		{
//			xScale=ex60Scale;
//			pointSize=18000;
//			pointScale=20;
//			scale=600;
//		}
//		else if(timeScale==120)
//		{
//			xScale=ex120Scale;
//			pointSize=36000;
//			pointScale=20;
//			scale=1200;
//		}
//		int starttemp=(mmainset.EEG.size()-1)/(timeScale*3)*(timeScale*3);
//		startX=starttemp;
//		endXSize=mmainset.EEG.size()-startX;
//	/*	if(mmainset.EEG.size()%(timeScale*3)==0)  
//		{
//			startX2=-1;
//			endX2Size=-1;
//		}
//		else */
//		{
//			startX2=mmainset.EEG.size()-timeScale*3;
//			endX2Size=timeScale*3;
//		}
//		mCanvas.drawText("127", epoint[0].x-28, ey[7]+8, CoordTextPaint);
//		mCanvas.drawText("112", epoint[0].x-28, ey[6]+8, CoordTextPaint);
//		mCanvas.drawText("96", epoint[0].x-20, ey[5]+8, CoordTextPaint);
//		mCanvas.drawText("80", epoint[0].x-20, ey[4]+8, CoordTextPaint);
//		mCanvas.drawText("64", epoint[0].x-20, ey[3]+8, CoordTextPaint);
//		mCanvas.drawText("48", epoint[0].x-20, ey[2]+8, CoordTextPaint);
//		mCanvas.drawText("32", epoint[0].x-20, ey[1]+8, CoordTextPaint);
//		mCanvas.drawText("16", epoint[0].x-20, ey[0]+8, CoordTextPaint);
//		mCanvas.drawText("0uv", epoint[0].x-26, epoint[1].y+8, CoordTextPaint);		
//		
//		mCanvas.drawText("-16", epoint[0].x-26, ey[8]+8, CoordTextPaint);
//		mCanvas.drawText("-32", epoint[0].x-26, ey[9]+8, CoordTextPaint);
//		mCanvas.drawText("-48", epoint[0].x-26, ey[10]+8, CoordTextPaint);
//		mCanvas.drawText("-64", epoint[0].x-26, ey[11]+8, CoordTextPaint);
//		mCanvas.drawText("-80", epoint[0].x-26, ey[12]+8, CoordTextPaint);
//		mCanvas.drawText("-96", epoint[0].x-26, ey[13]+8, CoordTextPaint);
//		mCanvas.drawText("-112", epoint[0].x-36, ey[14]+8, CoordTextPaint);
//		mCanvas.drawText("-128", epoint[0].x-36, ey[15]+2, CoordTextPaint);
//		meLinePath.reset();		
//		mLinePath.reset();
//		if(mmainset.EEG.size()<0)
//			return;
//		float hx = 0;
//		float hy = 0;
//		Integer[] EEGData = null;
//		
//		
//		if(preEEGSize!=mmainset.EEG.size())
//		{
//			preEEGSize=mmainset.EEG.size();
//			animStep=0;
//		}
//		animStep=animStep+20;
//		float hyk;		
//		{
//			if((mmainset.EEG.size()*100)<pointSize)
//			{
//				meLinePath.moveTo(epoint[1].x, epoint[1].y);
//			}
//			else
//			{
//				EEGData=mmainset.EEG.get(startX);
//				hx=epoint[1].x;
//				
//				if(EEGData[0]>=0x80)
//				{
//					EEGData[0]=0x7f-EEGData[0];
//				}
//			//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
//				if(EEGData[0]>=16)
//				{
//					hyk=ey[EEGData[0]/16-1]-eyScale*(EEGData[0]%16);
//				}
//				else if((EEGData[0]<16)&&(EEGData[0]>=0))
//				{
//					hyk=epoint[1].y-eyScale*(EEGData[0]);
//				}
//				else if((EEGData[0]>-16)&&(EEGData[0]<0))
//				{
//					hyk=epoint[1].y-EEGData[0]*eyScale;
//				}
//				else
//				{
//					hyk=ey[-EEGData[0]/16+7]-eyScale*(EEGData[0]%16);
//				}
//				if(hyk-eyScale*56<epoint[0].y)
//				{
//					meLinePath.moveTo(hx, epoint[0].y);
//				}
//				else if(EEGData[0]==0)
//					meLinePath.moveTo(hx, epoint[1].y);
//				else
//					meLinePath.moveTo(hx, hyk-eyScale*56);
//			}
//			int step = 1;
//			if(scale==10)
//			{
//				step=1;
//			}
//			else if(scale==50)
//			{
//				step=2;
//			}
//			else if(scale==100)
//			{
//				step=5;
//			}
//			else if(scale==600)
//			{
//				step=29;
//			}
//			else if(scale==1200)
//			{
//				step=53;
//			}
//			if(animStep>=100)
//			{
//				animStep=100;
//			}
//			int temp = 0;//=(startX+k+EEGflen)%100;
//			for(int k=step;k<((endXSize-1)*100+animStep);k=k+step)
//			{			
//				hx=ex[k/scale]-xScale*(scale-k%scale-1);
//				EEGData=mmainset.EEG.get(startX+k/100);
//				temp=k%100;
//				
//				if(EEGData[temp]>=0x80)
//				{
//					EEGData[temp]=0x7f-EEGData[temp];
//				}
//				if(EEGData[temp]>=16)
//				{
//					hy=ey[EEGData[temp]/16-1]-eyScale*(EEGData[temp]%16);
//				}
//				else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
//				{
//					hy=epoint[1].y-eyScale*(EEGData[temp]);
//				}
//				else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
//				{
//					hy=epoint[1].y-EEGData[temp]*eyScale;
//				}
//				else
//				{
//					hy=ey[-EEGData[temp]/16+7]-eyScale*(EEGData[temp]%16);
//				}
//				if(hy-eyScale*56<epoint[0].y)
//				{
//					meLinePath.lineTo(hx, epoint[0].y);
//				}
//				else if(EEGData[temp]==0)
//					meLinePath.lineTo(hx, epoint[1].y);
//				else
//					meLinePath.lineTo(hx, hy-eyScale*56);
//				//mCanvas.drawPath(meLinePath, mELinePaint);
//			}
//			if(dataSize>0)
//			{
//				if(hy-eyScale*56<epoint[0].y)
//				{
//					meLinePath.moveTo(hx, epoint[0].y);
//				}
//				else if(EEGData[temp]==0)
//					meLinePath.moveTo(hx, epoint[1].y);
//				else
//					meLinePath.moveTo(hx, hy-eyScale*56);
//			}
//			if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
//				mCanvas.drawPath(meLinePath, mELinePaint);
//			meLinePath.close();
//			if(mmainset.EEG.size()<=timeScale*3)
//			{
//				return;
//			}
//		//	if(startX2<0)
//		//		return;
//			
//		//	hx=ex[(endXSize*100+step*12)/scale]-xScale*(scale-(endXSize*100+step*12)%scale-1);
//		//	EEGData=mmainset.EEG.get(startX2);
//			int k1=(endXSize-1)*100+10*step+animStep;
//			if(k1>=pointSize)
//				k1=pointSize-1;
//			hx=ex[k1/scale]-xScale*(scale-k1%scale-1);
//			EEGData=mmainset.EEG.get(startX2+k1/100-endXSize);			
//			temp=k1%100;
//			if(EEGData[temp]>=0x80)
//			{
//				EEGData[temp]=0x7f-EEGData[temp];
//			}
//			if(EEGData[temp]>=16)
//			{
//				hy=ey[EEGData[temp]/16-1]-eyScale*(EEGData[temp]%16);
//			}
//			else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
//			{
//				hy=epoint[1].y-eyScale*(EEGData[temp]);
//			}
//			else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
//			{
//				hy=epoint[1].y-EEGData[temp]*eyScale;
//			}
//			else
//			{
//				hy=ey[-EEGData[temp]/16+7]-eyScale*(EEGData[temp]%16);
//			}
//			if(hy-eyScale*56<epoint[0].y)
//			{
//				mLinePath.moveTo(hx, epoint[0].y);
//			}
//			else if(EEGData[temp]==0)
//			{
//				mLinePath.moveTo(hx, epoint[1].y);
//			}
//			else
//				mLinePath.moveTo(hx, hy-eyScale*56);
//
//			for(int k=(endXSize-1)*100+10*step+animStep;k<(pointSize);k=k+step)
//			{					
//				hx=ex[k/scale]-xScale*(scale-k%scale-1);
//				EEGData=mmainset.EEG.get(startX2+k/100-endXSize);
//				temp=k%100;
//				if(EEGData[temp]>=0x80)
//				{
//					EEGData[temp]=0x7f-EEGData[temp];
//				}
//				if(EEGData[temp]>=16)
//				{
//					hy=ey[EEGData[temp]/16-1]-eyScale*(EEGData[temp]%16);
//				}
//				else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
//				{
//					hy=epoint[1].y-eyScale*(EEGData[temp]);
//				}
//				else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
//				{
//					hy=epoint[1].y-EEGData[temp]*eyScale;
//				}
//				else
//				{
//					hy=ey[-EEGData[temp]/16+7]-eyScale*(EEGData[temp]%16);
//				}
//				if(hy-eyScale*56<epoint[0].y)
//				{
//					mLinePath.lineTo(hx, epoint[0].y);
//				}
//				else
//					mLinePath.lineTo(hx, hy-eyScale*56);
//			}
//			if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
//				mCanvas.drawPath(mLinePath, mELinePaint);
//			mLinePath.close();
//			meLinePath.reset();		
//			mLinePath.reset();
//		}
//	}
	
	public void drawecoord()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawText("BS", ix[16]-26, iy[1]-2, BSTextCoordiatePaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		float xScale=ix1Scale;
		timeScale=mmainset.majuitimescale;
		scale=1;
		int endXSize;
		int startX2;
		int endX2Size;

		//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==1){
			mCanvas.drawText("/1sec", ix[16]+4, iy[1]-2, BSTextCoordiatePaint);
		}
		else if(timeScale==2)
		{
			xScale=ix2Scale;
			pointSize=36;
			pointScale=1;
			scale=2;
			mCanvas.drawText("/2sec", ix[16]+4, iy[1]-2, BSTextCoordiatePaint);
		}
		else if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
			mCanvas.drawText("/5sec", ix[16]+4, iy[1]-2, BSTextCoordiatePaint);
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
			mCanvas.drawText("/10sec", ix[16]+4, iy[1]-2, BSTextCoordiatePaint);
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
			mCanvas.drawText("/1min", ix[16]+4, iy[1]-2, BSTextCoordiatePaint);
		}
		else if(timeScale==120)
		{
			xScale=ix120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
			mCanvas.drawText("/2min", ix[16]+4, iy[1]-2, BSTextCoordiatePaint);
		}
//		startscishowx++;
//		if((startscishowx>mmainset.CSISHOW.length))
//		{
//			startscishowx=0;
//		}
		int starttemp=(mmainset.BS.size()-1)/pointSize*pointSize;
		startX=starttemp-1;
		endXSize=mmainset.BS.size()-startX;
		if(mmainset.BS.size()%pointSize==0)
		{
			startX2=-2;
			endX2Size=-1;
		}
		else
		{
			startX2=starttemp-pointSize;
			endX2Size=pointSize+1;
		}
		for(int k=0,j=0;k<=pointSize;k=k+6*timeScale,j=j+6)
		{

			float x;//x1[]=new int[19];
			x=ipoint[0].x+j*ix1Scale;
			int hour;//=(startX+1+k)/3600;
			int min;//=(startX+1+k)%3600/60;
			int second;//=(startX+1+k)%60;
			if((k>endXSize)&&(startX2>=0))
			{
				hour=(startX-pointSize+1+k)/3600;
				min=(startX-pointSize+1+k)%3600/60;
				second=(startX-pointSize+1+k)%60;
			}
			else
			{
				hour=(startX+1+k)/3600;
				min=(startX+1+k)%3600/60;
				second=(startX+1+k)%60;
			}
			String strhour=Integer.toString(hour);
			String strmin=Integer.toString(min);
			if(min<10)
			{
				strmin="0"+strmin;
			}
			String strsec=Integer.toString(second);
			if(second<10)
			{
				strsec="0"+strsec;
			}
			if(hour<=0)
				mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			else
				mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
		}		
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y+0, CoordTextPaint);
		miLinePath.reset();
		mLinePath.reset();
		if(mmainset.BS.size()<=0)
			return;
		float hx = ipoint[0].x;
		float hy = ipoint[1].y;
		int SCIData=0;
		
		if(startX<=0)
		{
			miLinePath.moveTo(ipoint[1].x,ipoint[1].y);
		}
		else
		{
			hx=ipoint[1].x;
			hy=ipoint[1].y;
			int temp = mmainset.BS.get(startX)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			if(SCIData<100)
				hy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=ipoint[0].y;
			else if(SCIData==100)
				hy=ipoint[1].y;
			miLinePath.moveTo(hx, hy);
			miLinePath.lineTo(hx, hy);
		}
		int temp;

		for(int i=1;i<endXSize;i=i+pointScale)
		{
			temp = mmainset.BS.get(i+startX)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=ipoint[1].y; 		
			if(SCIData<100)
				hy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=ipoint[0].y;
			else if(SCIData==100)
				hy=ipoint[1].y;
			//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
			if(i<scale)
			{
				hx=ipoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=ix[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
			//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
			miLinePath.lineTo(hx, hy);
		}
		miLinePath.moveTo(hx,hy);
		if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			mCanvas.drawPath(miLinePath, mSLinePaint);
	//	mCanvas.drawPoint(hx,hy,mELinePaint);
		miLinePath.close();
		if((endX2Size<0)||(startX2<-1))
			return;
		
		if((endXSize)<scale)
		{
			hx=ipoint[0].x+xScale*((endXSize)%scale);
		}
		else
		{
			hx=ix[(endXSize)/scale-1]+xScale*((endXSize)%scale);
		}

		temp = mmainset.BS.get(startX2+endXSize)&0xff;
		if(temp>100)
			temp=0;
		SCIData=100-temp;
		//Log.i(TAG, "liuliangxiang mmainset.BS.get(startX2+endXSize)&0xff="+(mmainset.BS.get(startX2+endXSize)&0xff));
		hy=ipoint[1].y; 		
		if(SCIData<100)
			hy=iy[SCIData/10]+iyScale*(SCIData%10);
		else if(SCIData==0)
			hy=ipoint[0].y;
		else if(SCIData==100)
			hy=ipoint[1].y;
		mLinePath.moveTo(hx,hy);
		for(int i=endXSize;i<endX2Size;i=i+pointScale)
		{
			temp = mmainset.BS.get(i+startX2)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=ipoint[1].y; 		
			if(SCIData<100)
				hy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=ipoint[0].y;
			else if(SCIData==100)
				hy=ipoint[1].y;
			//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
			if(i<scale)
			{
				hx=ipoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=ix[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
			//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
			mLinePath.lineTo(hx, hy);
		}
		mLinePath.moveTo(hx,hy);
		if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			mCanvas.drawPath(mLinePath, mSlinePaint1);
		mLinePath.close();
		miLinePath.reset();
		mLinePath.reset();
	}
	
	public void drawecoordrec()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawText(mcontext.getString(R.string.bs_review), ix[14]+4, iy[1]-2, BSTextCoordiatePaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		//int CSIData=
		float xScale=ix1Scale;
		timeScale=mmainset.majuirectimeScale;
		scale=1;
		
//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}else if(timeScale==2)
		{
			xScale=ix2Scale;
			pointSize=36;
			pointScale=1;
			scale=2;
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=ix120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		startX=scimovereclen;
		dataSize=pointSize;
		if(mmainset.RecBS.size()<=(pointSize))
		{
			startX=-1;
			dataSize=mmainset.RecBS.size();
		}
		else if(startX>=(mmainset.RecBS.size()-pointSize))
		{
			startX=mmainset.RecBS.size()-pointSize-1;
			eegmovereclen=mmainset.RecBS.size()-pointSize-1;
			dataSize=pointSize;
		}
		CurDataLen=(startX+dataSize);
		if(CurDataLen>=mmainset.RecBS.size())
			CurDataLen=mmainset.RecBS.size()-1;
		
		float hx = ipoint[0].x;
        float hy = ipoint[1].y;
		int SCIData=0;
		
		for(int k=0,j=0;k<=pointSize;k=k+6*timeScale,j=j+6)
		{

			float x;//x1[]=new int[19];
			x=ipoint[0].x+j*ix1Scale;
			int hour;//=(startX+1+k)/3600;
			int min;//=(startX+1+k)%3600/60;
			int second;//=(startX+1+k)%60;
			
			hour=(mmainset.curminTime+startX+1+k)/3600;
			min=(mmainset.curminTime+startX+1+k)%3600/60;
			second=(mmainset.curminTime+startX+1+k)%60;
			
			String strhour=Integer.toString(hour);
			String strmin=Integer.toString(min);
			if(min<10)
			{
				strmin="0"+strmin;
			}
			String strsec=Integer.toString(second);
			if(second<10)
			{
				strsec="0"+strsec;
			}
			if(hour<=0)
				mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			else
				mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
		}		
	/*	
		for(int k=0;k<=18;k=k+6)
		{
			float x;//x1[]=new int[19];
			x=ipoint[0].x+k*ix1Scale;
		//	int scale=timeScale;
			if(k%6==0)
			{
				int hour=(mmainset.curminTime+startX+1+k*timeScale)/3600;
				int min=(mmainset.curminTime+startX+1+k*timeScale)%3600/60;
				int second=(mmainset.curminTime+startX+1+k*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[2].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[2].y+12,CoordinatePaint);
			}
		}*/
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y+0, CoordTextPaint);
		if(mmainset.RecBS.size()<=0)
			return;
		{
			miLinePath.reset();
			bmpdownarray.clear();
			bmpuparray.clear();
			{				
				hx=ipoint[1].x;
				hy=ipoint[1].y;
				if(mmainset.RecBS.size()<=dataSize)
				{
					miLinePath.moveTo(ipoint[1].x, ipoint[1].y);
					miLinePath.lineTo(ipoint[1].x, ipoint[1].y);
				}
				else
				{
					int tempStartX = 0;
					if(startX >=0)
						tempStartX=startX;
					int temp = mmainset.RecBS.get(tempStartX)&0xff;
					if(temp>100)
						temp=0;
					SCIData=100-temp;
					if(SCIData<100)
						hy=iy[SCIData/10]+iyScale*(SCIData%10);
					else if(SCIData==0)
						hy=ipoint[0].y;
					else if(SCIData==100)
						hy=ipoint[1].y;
					miLinePath.moveTo(hx, hy);
					miLinePath.lineTo(hx, hy);
				}
			}
			float px;
			float height = 0;
			for(int i=1;i<dataSize+1;i=i+pointScale)
			{
				int temp = mmainset.RecBS.get(startX+i)&0xff;
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				hy=ipoint[1].y;			
				if(SCIData<100)
					hy=iy[SCIData/10]+iyScale*(SCIData%10);
				else if(SCIData==0)
					hy=ipoint[0].y;
				else if(SCIData==100)
					hy=ipoint[1].y;
				if(i<scale)
				{
					hx=ipoint[0].x+xScale*(i%scale)+xScale/6;
				}
				else
				{
					hx=ix[i/scale-1]+xScale*(i%scale)+xScale/6;
				}
				miLinePath.lineTo(hx, hy);

			}			
			if(dataSize>0)
			{
				miLinePath.moveTo(hx, hy);
			}
		}
		mCanvas.drawPath(miLinePath, mSLinePaint);
		if(curix!=-1)
		{
			int temp=(int)((curix-ipoint[0].x)/xScale)+startX+1;
			if(temp>=mmainset.RecBS.size())
				DataNum=temp-1;
			else
				DataNum=temp;
			if((temp<mmainset.RecBS.size())&&(temp>0))
			{
				int data=mmainset.RecBS.get(temp);
				int hour=(temp+mmainset.curminTime)/3600;
				int min=(temp+mmainset.curminTime)%3600/60;
				int second=(temp+mmainset.curminTime)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if((data&0xff)>100){
					if(hour>0)
						mCanvas.drawText("("+strhour+":"+strmin+":"+strsec+","+ "▂ ▂"+")", curix, ipoint[0].y+40, BSTextPaint);
					else{
						mCanvas.drawText("("+strhour+":"+strmin+":"+strsec+","+"▂ ▂"+")", curix, ipoint[0].y+40, BSTextPaint);
					}
				}else{
					if(hour>0)
						mCanvas.drawText("("+strhour+":"+strmin+":"+strsec+","+Integer.toString(data&0xff)+")", curix, ipoint[0].y+40, BSTextPaint);
					else{
						mCanvas.drawText("("+strhour+":"+strmin+":"+strsec+","+Integer.toString(data&0xff)+")", curix, ipoint[0].y+40, BSTextPaint);
					}
				}
				mCanvas.drawLine(curix, ipoint[0].y, curix, ipoint[2].y, mSLine2ePaint);
			}
			
		}
		miLinePath.close();
//		for(int j=0;j<bmpuparray.size();j=j+2)
//		{
//			mCanvas.drawBitmap(bitmapDown, bmpuparray.get(j)-8, bmpuparray.get(j+1), null);
//		}
//		for(int j=0;j<bmpdownarray.size();j=j+2)
//		{
//			mCanvas.drawBitmap(bitmapUp, bmpdownarray.get(j)-8, bmpdownarray.get(j+1), null);
//		}
	}
	
	public void drawseegcoord()
	{
		mCanvas.drawLine(spoint[0].x, spoint[0].y, spoint[2].x, spoint[2].y, CoordinatePaint);
		mCanvas.drawLine(spoint[3].x, spoint[3].y, spoint[5].x, spoint[5].y, CoordinatePaint);
	//	mCanvas.drawLine(spoint[1].x, spoint[1].y, spoint[6].x, spoint[6].y, CoordinatePaint);
		mCanvas.drawLine(spoint[2].x, spoint[2].y, spoint[7].x, spoint[7].y, CoordinatePaint);
		mCanvas.drawLine(spoint[4].x, spoint[4].y, spoint[8].x, spoint[8].y, CoordinatePaint);
		mCanvas.drawLine(spoint[5].x, spoint[5].y, spoint[9].x, spoint[9].y, CoordinatePaint);
		
		for(int i=0;i<siy.length;i++)
		{
			mCanvas.drawLine(spoint[0].x, siy[i], spoint[6].x, siy[i], mGLinePaint);
		}
		for(int j=0;j<six.length;j++)
		{
			mCanvas.drawLine(six[j],spoint[0].y ,six[j], spoint[2].y, mGLinePaint);
		}
		
//		for(int i=0;i<biy.length;i++)
//		{
//			mCanvas.drawLine(spoint[3].x, biy[i], spoint[7].x, biy[i], mGLinePaint);
//		}
//		for(int j=0;j<bix.length;j++)
//		{
//			mCanvas.drawLine(bix[j],spoint[3].y ,bix[j], spoint[5].y, mGLinePaint);
//		}
		
		mCanvas.drawText("100",spoint[0].x-28, siy[0]+8, CoordTextPaint);
		mCanvas.drawText("80",spoint[1].x-20, siy[2]+8, CoordTextPaint);
		mCanvas.drawText("60",spoint[1].x-20, siy[4]+8, CoordTextPaint);
		mCanvas.drawText("40",spoint[1].x-20, siy[6]+8, CoordTextPaint);
		mCanvas.drawText("20",spoint[1].x-20, siy[8]+8, CoordTextPaint);
		mCanvas.drawText("0",spoint[2].x-12, spoint[2].y-2, CoordTextPaint);
		
//		mCanvas.drawText("200",spoint[0].x-30, spoint[3].y+8, CoordTextPaint);
//		mCanvas.drawText("0",spoint[1].x-12, spoint[4].y+8, CoordTextPaint);
//		mCanvas.drawText("-200",spoint[2].x-38, spoint[5].y-2, CoordTextPaint);
		mCanvas.drawText("CSI", six[16]-30, siy[1]-2, CurCSIPaint);
		mCanvas.drawText("EEG", sex[28], sey[6], CurCSIPaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		float xScale=ix1Scale;
		timeScale=mmainset.majuitimescale;
		
		int value;
		int bannerSelect=mmainset.bannerSelect;
		float bannerX;
		float bannerYS;
		float bannerYL;

		bannerX = spoint[0].x;
		bannerYS = spoint[0].y;
		int tempValue = mmainset.shangxianshezValue;
		if (tempValue > 100)
			tempValue = 0;
		value = 100 - tempValue;
		if (value < 100)
			bannerYS = siy[value / 10] + siyScale * (value % 10);
		else if (value == 0)
			bannerYS = spoint[0].y;
		else if (value == 100)
			bannerYS = spoint[2].y;
		bannerYL = spoint[2].y;
		tempValue = mmainset.xiaxianshezValue;
		if (tempValue > 100)
			tempValue = 0;
		value = 100 - tempValue;
		if (value < 100)
			bannerYL = siy[value / 10] + siyScale * (value % 10);
		else if (value == 0)
			bannerYL = spoint[0].y;
		else if (value == 100)
			bannerYL = spoint[2].y;
		if(bannerSelect==1){
			mCanvas.drawLine(bannerX, bannerYS, spoint[6].x, bannerYS, shandowPaint);
			mCanvas.drawLine(bannerX, bannerYL, spoint[6].x, bannerYL, shandowPaint);
		}else if(bannerSelect==2){
			mCanvas.drawRect(bannerX, bannerYS,spoint[6].x,bannerYL,shandowPaint);
		}
		scale=1;
		int endXSize;
		int startX2;
		int endX2Size;
		if(timeScale==1){
			mCanvas.drawText("/1sec", six[16]+4, siy[1]-2, CurCSIPaint);
		}
		else if(timeScale==2)
		{
			xScale=ix2Scale;
			pointSize=36;
			pointScale=1;
			scale=2;
			mCanvas.drawText("/2sec", six[16]+4, siy[1]-2, CurCSIPaint);
		}
		else if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
			mCanvas.drawText("/5sec", six[16]+4, siy[1]-2, CurCSIPaint);
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
			mCanvas.drawText("/10sec", six[16]+4, siy[1]-2, CurCSIPaint);
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
			mCanvas.drawText("/1min", six[16]+4, siy[1]-2, CurCSIPaint);
		}
		else if(timeScale==120)
		{
			xScale=ix120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
			mCanvas.drawText("/2min", six[16]+4, siy[1]-2, CurCSIPaint);
		}
	//	Log.i(TAG,"liuliangxiang timeScale ="+timeScale);
		int starttemp=(mmainset.CSI.size()-1)/pointSize*pointSize;
		startX=starttemp-1;
		endXSize=mmainset.CSI.size()-startX;
		if(mmainset.CSI.size()%pointSize==0)
		{
			startX2=-2;
			endX2Size=-1;
		}
		else
		{
			startX2=starttemp-pointSize;
			endX2Size=pointSize+1;
		}		
		for(int k=0,j=0;k<=pointSize;k=k+6*timeScale,j=j+6)
		{
			{
				float x;//x1[]=new int[19];
				x=ipoint[0].x+j*ix1Scale;
				int hour;//=(startX+1+k)/3600;
				int min;//=(startX+1+k)%3600/60;
				int second;//=(startX+1+k)%60;
				if((k>endXSize)&&(startX2>=0))
				{
					hour=(startX-pointSize+1+k)/3600;
					min=(startX-pointSize+1+k)%3600/60;
					second=(startX-pointSize+1+k)%60;
				}
				else
				{
					hour=(startX+1+k)/3600;
					min=(startX+1+k)%3600/60;
					second=(startX+1+k)%60;
				}
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, spoint[2].y+12,CoordinatePaint);
			}
		}
		

		float Scale=seeg25Scale;
		int eegScaleSet=mmainset.eegScale;
		int ScaleSize = 6;
		int StartSize;
		int EndSize;
		int SeStartSize;
		int SeEndSize;
		int number=0;
		if(eegScaleSet==1){
			Scale=seeg12Scale;
			ScaleSize=12;
			mCanvas.drawText("12.5mm/s", spoint[0].x+4, biy[9]+10, CurCSIPaint);
		}else if(eegScaleSet==2){
			Scale=seeg25Scale;
			ScaleSize=6;
			mCanvas.drawText("25mm/s", spoint[0].x+4, biy[9]+10, CurCSIPaint);
		}else if(eegScaleSet==3){
			Scale=seeg50Scale;
			ScaleSize=3;
			mCanvas.drawText("50mm/s", spoint[0].x+4, biy[9]+10, CurCSIPaint);
		}
		Integer[] eegData=new Integer[100];
		StartSize = (mmainset.EEG.size()-1)/ScaleSize*ScaleSize;
		EndSize = mmainset.EEG.size();
		SeStartSize = EndSize-ScaleSize;
		SeEndSize = StartSize;
		mCanvas.drawLine(spoint[3].x,spoint[3].y ,spoint[6].x, spoint[3].y, mGLinePaint);
		float yScale=(spoint[5].y-spoint[3].y)/8;
		
		for(int i=1;i<ScaleSize+1;i++)
		{
			mCanvas.drawLine(spoint[0].x+Scale*i, spoint[5].y, spoint[0].x+Scale*i, spoint[3].y, mGLinePaint);
		}
		for(int i=1;i<8;i++)
		{
			if(i!=4)
				mCanvas.drawLine(spoint[0].x, spoint[3].y+yScale*i, spoint[6].x, spoint[3].y+yScale*i, mGLinePaint);
		}
//		Log.i(TAG, "liuliangxiang drawseegcoord StartSize="+StartSize);
//		Log.i(TAG, "liuliangxiang drawseegcoord EndSize="+EndSize);
//		Log.i(TAG, "liuliangxiang drawseegcoord SeStartSize="+SeStartSize);
//		Log.i(TAG, "liuliangxiang drawseegcoord SeEndSize="+SeEndSize);
		for(int i=StartSize;i<EndSize;i++){
			int hour;//=(startX+1+k)/3600;
			int min;//=(startX+1+k)%3600/60;
			int second;//=(startX+1+k)%60;
			hour=(i)/3600;
			min=(i)%3600/60;
			second=(i)%60;
			String strhour=Integer.toString(hour);
			String strmin=Integer.toString(min);
			if(min<10)
			{
				strmin="0"+strmin;
			}
			String strsec=Integer.toString(second);
			if(second<10)
			{
				strsec="0"+strsec;
			}
			//Log.i(TAG, "liuliangxiang i%ScaleSize="+(i%ScaleSize));
			if(hour<=0)
				mCanvas.drawText(strmin+":"+strsec, ipoint[0].x+(i-StartSize)*Scale-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+6,CoordinatePaint);
			else
				mCanvas.drawText(strhour+":"+strmin+":"+strsec, ipoint[0].x+(i-StartSize)*Scale-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+6,CoordinatePaint);
		}
		if(SeEndSize>0){
			for(int i=SeStartSize;i<=SeEndSize;i++){
				int hour;//=(startX+1+k)/3600;
				int min;//=(startX+1+k)%3600/60;
				int second;//=(startX+1+k)%60;
				hour=(i)/3600;
				min=(i)%3600/60;
				second=(i)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec,ipoint[0].x+(i%ScaleSize)*Scale-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+6,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, ipoint[0].x+(i%ScaleSize)*Scale-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+6,CoordinatePaint);
			}
		}
		miLinePath.reset();
		mLinePath.reset();
		float hx = spoint[0].x;
		float hy = spoint[2].y;
		int SCIData=0;
		
		if(startX<=0)
		{
			miLinePath.moveTo(spoint[1].x,spoint[2].y);
		}
		else
		{
			hx=spoint[0].x;
			hy=spoint[2].y;
			int temp = mmainset.CSI.get(startX)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			if(SCIData<100)
				hy=siy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[0].y;
			else if(SCIData==100)
				hy=spoint[2].y;
			miLinePath.moveTo(hx, hy);
			miLinePath.lineTo(hx, hy);
		}
		int temp = 0;

		for(int i=1;i<endXSize;i=i+pointScale)
		{
			temp = mmainset.CSI.get(i+startX)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=ipoint[1].y; 		
			if(SCIData<100)
				hy=siy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[0].y;
			else if(SCIData==100)
				hy=spoint[2].y;
			if(i<scale)
			{
				hx=spoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			miLinePath.lineTo(hx, hy);
		}
		miLinePath.moveTo(hx,hy);
		if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			mCanvas.drawPath(miLinePath, mCSILinePaint);
		miLinePath.close();		
		if((startX2>-1))
		{	
			if((endXSize)<scale)
			{
				hx=spoint[0].x+xScale*((endXSize)%scale);
			}
			else
			{
				hx=six[(endXSize)/scale-1]+xScale*((endXSize)%scale);
			}
	
			temp = mmainset.CSI.get(startX2+endXSize)&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=spoint[2].y; 		
			if(SCIData<100)
				hy=siy[SCIData/10]+siyScale*(SCIData%10);
			else if(SCIData==0)
				hy=spoint[0].y;
			else if(SCIData==100)
				hy=spoint[2].y;
			mLinePath.moveTo(hx,hy);
			//int j=1;
			for(int i=endXSize+1;i<endX2Size;i=i+pointScale)
			{
				temp = mmainset.CSI.get(i+startX2)&0xff;
				if(temp>100)
					temp=0;
				SCIData=100-temp;
				hy=spoint[2].y; 		
				if(SCIData<100)
					hy=siy[SCIData/10]+siyScale*(SCIData%10);
				else if(SCIData==0)
					hy=spoint[0].y;
				else if(SCIData==100)
					hy=spoint[2].y;
				if(i<scale)
				{
					hx=spoint[0].x+xScale*(i%scale)+xScale/6;
				}
				else
				{
					hx=six[i/scale-1]+xScale*(i%scale)+xScale/6;
				}
				mLinePath.lineTo(hx, hy);
			}
			mLinePath.moveTo(hx,hy);
			if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
				mCanvas.drawPath(mLinePath, mSlinePaint1);
		}
		mLinePath.close();
		miLinePath.reset();
		mLinePath.reset();
		int fudu=mmainset.fudu;
		int max=200;
		int fuduScale=1;
		if(fudu==1){
			mCanvas.drawText("5uV", six[16]-20, biy[9]+10, CurCSIPaint);
			max=20;
			fuduScale=10;
		}else if(fudu==2){
			mCanvas.drawText("10uV", six[16]-20, biy[9]+10, CurCSIPaint);
			max=40;
			fuduScale=5;
		}else if(fudu==3){
			mCanvas.drawText("25uV", six[16]-20, biy[9]+10, CurCSIPaint);
			max=100;
			fuduScale=2;
		}else if(fudu==4){
			mCanvas.drawText("50uV", six[16]-20, biy[9]+10, CurCSIPaint);
			max=200;
			fuduScale=1;
		}
		mCanvas.drawText(Integer.toString(max),spoint[0].x-30, spoint[3].y+8, CoordTextPaint);
		mCanvas.drawText("0",spoint[1].x-12, spoint[4].y+8, CoordTextPaint);
		mCanvas.drawText(Integer.toString(-max),spoint[2].x-38, spoint[5].y-2, CoordTextPaint);
	
		if(StartSize<=0){
			miLinePath.moveTo(spoint[0].x, spoint[4].y);
		}else{
			//Integer[] eegData=new Integer[100];
			eegData=mmainset.EEG.get(StartSize);
			//Log.i(TAG, "liuliangxiang eegData0="+((signed char)eegData[0]));
			temp=eegData[0];
			if(temp>127){
				temp=(256-temp);
				temp=temp*45/32;
				if(temp>=max)temp=max;
				hy=spoint[4].y+temp*fuduScale*seegyScale;
			}else{
				temp=temp*45/32;
				if(temp>=max)temp=max;
				hy=spoint[4].y-temp*fuduScale*seegyScale;
			}
			miLinePath.moveTo(spoint[0].x, hy);
		}
		for(int i = StartSize;i<EndSize;i++){
			//Integer[] eegData=new Integer[100];
			eegData=mmainset.EEG.get(i);
			
			for(int j=0;j<100;j=j+1){
			//	Log.i(TAG, "liuliangxiang eegData["+j+"]"+eegData[j]);
				if(eegData[j]==null)break;
				int data=(int)(eegData[j]);
				if(data>127){
					data=(256-data);
					data=data*45/32;
					if(data>=max)data=max;
					hy=spoint[4].y+data*fuduScale*seegyScale;
				}else{
					data=data*45/32;
					if(data>=max)data=max;
					hy=spoint[4].y-data*fuduScale*seegyScale;
				}
				hx=spoint[0].x+Scale*number+j*Scale/100;
				miLinePath.lineTo(hx, hy);
			}
			number++;
		}
		if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			mCanvas.drawPath(miLinePath, mCSILinePaint);
//	//	mCanvas.drawPoint(hx,hy,mELinePaint);
		miLinePath.close();
		if((SeEndSize<0)||(SeStartSize<0))
			return;
		eegData=mmainset.EEG.get(SeStartSize);
		if(eegData[0]==null) return;
		temp = eegData[0];
		if(temp>127){
			temp=(256-temp);
			temp=temp*45/32;
			if(temp>=max)temp=max;
			hy=spoint[4].y+temp*fuduScale*seegyScale;
		}else{
			temp=temp*45/32;
			if(temp>=max)temp=max;
			hy=spoint[4].y-temp*fuduScale*seegyScale;
		}
		hx=spoint[0].x+Scale*number;
		mLinePath.moveTo(hx, hy);
		for(int i = SeStartSize;i<SeEndSize;i++){
		    eegData=mmainset.EEG.get(i);
		    for(int j=0;j<100;j=j+1){
			//	Log.i(TAG, "liuliangxiang eegData["+j+"]"+eegData[j]);
				if(eegData[j]==null)break;
				int data=(int)(eegData[j]);
				if(data>127){
					data=(256-data);
					data=data*45/32;
					if(data>=max)data=max;
					hy=spoint[4].y+data*fuduScale*seegyScale;
				}else{
					data=data*45/32;
					if(data>=max)data=max;
					hy=spoint[4].y-data*fuduScale*seegyScale;
				}
				hx=spoint[0].x+Scale*number+j*Scale/100;
				mLinePath.lineTo(hx, hy);	
			}
			number++;
		}
		if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			mCanvas.drawPath(mLinePath, mSlinePaint1);
////	//	mCanvas.drawPoint(hx,hy,mELinePaint);
		mLinePath.close();
		miLinePath.reset();
		mLinePath.reset();
		
	}
	
	public void draweegcoord(){
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,(ipoint[1].y-ipoint[0].y)/2+ipoint[0].y,ipoint[2].x,(ipoint[1].y-ipoint[0].y)/2+ipoint[0].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[0].x,ipoint[1].y,ipoint[2].x,ipoint[1].y,CoordinatePaint);
		float Scale=seeg25Scale;
		int ScaleSize = 6;
		int eegScaleSet=mmainset.eegScale;
		if(eegScaleSet==1){
			Scale=seeg12Scale;
			ScaleSize=12;
		}else if(eegScaleSet==2){
			Scale=seeg25Scale;
			ScaleSize=6;
		}else if(eegScaleSet==3){
			Scale=seeg50Scale;
			ScaleSize=3;
		}
		for(int i=1;i<ScaleSize+1;i++)
		{
			mCanvas.drawLine(ipoint[0].x+Scale*i, ipoint[0].y, ipoint[0].x+Scale*i, ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y ,ipoint[2].x, ipoint[0].y, mGLinePaint);
		mCanvas.drawText(mcontext.getString(R.string.eeg1), ix[14]+4, iy[1]-2, WhitePaint);
		int PointSize;
		int DataSize;
		int StartSize;
		int EndSize;
		int SeStartSize;
		int SeEndSize;
		float hx;
		float hy;
		int number=0;
		
		Integer[] eegData=new Integer[100];
		StartSize = (mmainset.EEG.size()-1)/ScaleSize*ScaleSize;
		EndSize = mmainset.EEG.size();
		SeStartSize = EndSize-ScaleSize;
		SeEndSize = StartSize;
		for(int i=StartSize;i<EndSize;i++){
			int hour;//=(startX+1+k)/3600;
			int min;//=(startX+1+k)%3600/60;
			int second;//=(startX+1+k)%60;
			hour=(i)/3600;
			min=(i)%3600/60;
			second=(i)%60;
			String strhour=Integer.toString(hour);
			String strmin=Integer.toString(min);
			if(min<10)
			{
				strmin="0"+strmin;
			}
			String strsec=Integer.toString(second);
			if(second<10)
			{
				strsec="0"+strsec;
			}
			if(hour<=0)
				mCanvas.drawText(strmin+":"+strsec, ipoint[0].x+(i%ScaleSize)*Scale-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			else
				mCanvas.drawText(strhour+":"+strmin+":"+strsec, ipoint[0].x+(i%ScaleSize)*Scale-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
		}
		for(int i=SeStartSize;i<=SeEndSize;i++){
			int hour;//=(startX+1+k)/3600;
			int min;//=(startX+1+k)%3600/60;
			int second;//=(startX+1+k)%60;
			hour=(i)/3600;
			min=(i)%3600/60;
			second=(i)%60;
			String strhour=Integer.toString(hour);
			String strmin=Integer.toString(min);
			if(min<10)
			{
				strmin="0"+strmin;
			}
			String strsec=Integer.toString(second);
			if(second<10)
			{
				strsec="0"+strsec;
			}
			if(hour<=0)
				mCanvas.drawText(strmin+":"+strsec,ipoint[0].x+(i%ScaleSize)*Scale-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			else
				mCanvas.drawText(strhour+":"+strmin+":"+strsec, ipoint[0].x+(i%ScaleSize)*Scale-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
		}
		
//		for(int k=0,j=0;k<=pointSize;k=k+6*timeScale,j=j+6)
//		{
//
//			float x;//x1[]=new int[19];
//			x=ipoint[0].x+j*ix1Scale;
//			int hour;//=(startX+1+k)/3600;
//			int min;//=(startX+1+k)%3600/60;
//			int second;//=(startX+1+k)%60;
//			if((k>endXSize)&&(startX2>=0))
//			{
//				hour=(startX-pointSize+1+k)/3600;
//				min=(startX-pointSize+1+k)%3600/60;
//				second=(startX-pointSize+1+k)%60;
//			}
//			else
//			{
//				hour=(startX+1+k)/3600;
//				min=(startX+1+k)%3600/60;
//				second=(startX+1+k)%60;
//			}
//			String strhour=Integer.toString(hour);
//			String strmin=Integer.toString(min);
//			Log.i(TAG,"liuliangxiang hour="+hour);
//			Log.i(TAG,"liuliangxiang x="+x);
//			if(min<10)
//			{
//				strmin="0"+strmin;
//			}
//			String strsec=Integer.toString(second);
//			if(second<10)
//			{
//				strsec="0"+strsec;
//			}
//			if(hour<=0)
//				mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
//			else
//				mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
//		}		
		mCanvas.drawText("180", ipoint[0].x-30, ipoint[0].y+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, (ipoint[1].y-ipoint[0].y)/2+ipoint[0].y+8, CoordTextPaint);
		mCanvas.drawText("-180", ipoint[0].x-38, ipoint[1].y+0, CoordTextPaint);

		miLinePath.reset();
		mLinePath.reset();
		if(StartSize<=0){
			miLinePath.moveTo(ipoint[1].x, ipoint[1].y);
		}else{
			//Integer[] eegData=new Integer[100];
			eegData=mmainset.EEG.get(StartSize);
			hy=ipoint[0].y+(255-eegData[0])*eegyScale;
			miLinePath.moveTo(ipoint[0].x, hy);
		}
		for(int i = StartSize;i<EndSize;i++){
			//Integer[] eegData=new Integer[100];
			eegData=mmainset.EEG.get(i);
			
			for(int j=0;j<100;j=j+1){
			//	Log.i(TAG, "liuliangxiang eegData["+j+"]"+eegData[j]);
				if(eegData[j]==null)break;
				int data=(int)(eegData[j]);
				hx=ipoint[0].x+Scale*number+j*Scale/100;
				hy=ipoint[0].y+(255-data)*eegyScale;
				miLinePath.lineTo(hx, hy);
			}
			number++;
		}
		if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			mCanvas.drawPath(miLinePath, mCSILinePaint);
//	//	mCanvas.drawPoint(hx,hy,mELinePaint);
		miLinePath.close();
		if((SeEndSize<0)||(SeStartSize<0))
			return;
		eegData=mmainset.EEG.get(SeStartSize);
		if(eegData[0]==null) return;
		hy=ipoint[0].y+(255-eegData[0])*eegyScale;
		hx=ipoint[0].x+Scale*number;
		mLinePath.moveTo(hx, hy);
		for(int i = SeStartSize;i<SeEndSize;i++){
		    eegData=mmainset.EEG.get(i);
		    for(int j=0;j<100;j=j+1){
			//	Log.i(TAG, "liuliangxiang eegData["+j+"]"+eegData[j]);
				if(eegData[j]==null)break;
				int data=(int)(eegData[j]);
				hx=ipoint[0].x+Scale*number+j*Scale/100;
				hy=ipoint[0].y+(255-data)*eegyScale;
				mLinePath.lineTo(hx, hy);	
			}
			number++;
		}
		if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			mCanvas.drawPath(mLinePath, mSlinePaint1);
////	//	mCanvas.drawPoint(hx,hy,mELinePaint);
		mLinePath.close();
	}
	
	
	public void draweegcoordRec(){
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,(ipoint[1].y-ipoint[0].y)/2+ipoint[0].y,ipoint[2].x,(ipoint[1].y-ipoint[0].y)/2+ipoint[0].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[0].x,ipoint[1].y,ipoint[2].x,ipoint[1].y,CoordinatePaint);
		float Scale=seeg25Scale;
		int ScaleSize = 6;
		int eegScaleSet=mmainset.eegrecScale;
		if(eegScaleSet==1){
			Scale=seeg12Scale;
			ScaleSize=12;
		}else if(eegScaleSet==2){
			Scale=seeg25Scale;
			ScaleSize=6;
		}else if(eegScaleSet==3){
			Scale=seeg50Scale;
			ScaleSize=3;
		}
		for(int i=1;i<ScaleSize+1;i++)
		{
			mCanvas.drawLine(ipoint[0].x+Scale*i, ipoint[0].y, ipoint[0].x+Scale*i, ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y ,ipoint[2].x, ipoint[0].y, mGLinePaint);
		mCanvas.drawText(mcontext.getString(R.string.eeg1), ix[14]+4, iy[1]-2, WhitePaint);
		int PointSize;
		int DataSize;
		int StartSize;
		int EndSize;
		int SeStartSize;
		int SeEndSize;
		float hx;
		float hy;
		int number=0;
		Integer[] eegData=new Integer[100];
		StartSize = eegmovereclen;
		EndSize = eegmovereclen+ScaleSize;
		for(int i=StartSize;i<EndSize;i++){
			int hour;//=(startX+1+k)/3600;
			int min;//=(startX+1+k)%3600/60;
			int second;//=(startX+1+k)%60;
			hour=(i)/3600;
			min=(i)%3600/60;
			second=(i)%60;
			String strhour=Integer.toString(hour);
			String strmin=Integer.toString(min);
			if(min<10)
			{
				strmin="0"+strmin;
			}
			String strsec=Integer.toString(second);
			if(second<10)
			{
				strsec="0"+strsec;
			}
			if(hour<=0)
				mCanvas.drawText(strmin+":"+strsec, ipoint[0].x+(i%ScaleSize)*Scale-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			else
				mCanvas.drawText(strhour+":"+strmin+":"+strsec, ipoint[0].x+(i%ScaleSize)*Scale-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
		}
		mCanvas.drawText("180", ipoint[0].x-30, ipoint[0].y+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, (ipoint[1].y-ipoint[0].y)/2+ipoint[0].y+8, CoordTextPaint);
		mCanvas.drawText("-180", ipoint[0].x-38, ipoint[1].y+0, CoordTextPaint);

		miLinePath.reset();
		mLinePath.reset();
		if(StartSize<=0){
			miLinePath.moveTo(ipoint[1].x, ipoint[1].y);
		}else{
			//Integer[] eegData=new Integer[100];
			eegData=mmainset.RecEEG.get(StartSize);
			hy=ipoint[0].y+(255-eegData[0])*eegyScale;
			miLinePath.moveTo(ipoint[0].x, hy);
		}
		for(int i = StartSize;i<EndSize;i++){
			//Integer[] eegData=new Integer[100];
			eegData=mmainset.RecEEG.get(i);
			
			for(int j=0;j<100;j=j+1){
			//	Log.i(TAG, "liuliangxiang eegData["+j+"]"+eegData[j]);
				if(eegData[j]==null)break;
				int data=(int)(eegData[j]);
				hx=ipoint[0].x+Scale*number+j*Scale/100;
				hy=ipoint[0].y+(255-data)*eegyScale;
				miLinePath.lineTo(hx, hy);
			}
			number++;
		}
		if((mmainset.isConnected)&&(!mmainset.isWarnConnected))
			mCanvas.drawPath(miLinePath, mCSILinePaint);
//	//	mCanvas.drawPoint(hx,hy,mELinePaint);
		miLinePath.close();
	}


	
	public void drawecoordShow()
	{
		mCanvas.drawLine(ipoint[0].x,ipoint[0].y,ipoint[1].x,ipoint[1].y,CoordinatePaint);
		mCanvas.drawLine(ipoint[1].x,ipoint[1].y,ipoint[2].x,ipoint[2].y,CoordinatePaint);
		for(int i=0;i<iy.length;i++)
		{
			mCanvas.drawLine(ipoint[0].x, iy[i], ipoint[2].x, iy[i], mGLinePaint);
		}
		for(int j=0;j<ix.length;j++)
		{
			mCanvas.drawLine(ix[j],ipoint[0].y ,ix[j], ipoint[1].y, mGLinePaint);
		}
		mCanvas.drawText(mcontext.getString(R.string.bs_show), ix[14]+4, iy[1]-2, BSTextCoordiatePaint);
		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=18;
		int pointScale=1;
		float xScale=ix1Scale;
		timeScale=5;
		scale=1;
		int endXSize;
		int startX2;
		int endX2Size;

		//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==5)
		{
			xScale=ix5Scale;
			pointSize=90;
			pointScale=1;
			scale=5;
		}
		else if(timeScale==10)
		{
			xScale=ix10Scale;
			pointSize=180;
			pointScale=1;
			scale=10;
		}
		else if(timeScale==60)
		{
			xScale=ix60Scale;
			pointSize=1080;
			//scale=3;
			pointScale=8;
			scale=60;
		}
		else if(timeScale==120)
		{
			xScale=ix120Scale;
			pointSize=2160;
			//scale=30;
			pointScale=14;
			scale=120;
		}
		startscishowx++;
	//	if((startscishowx>mmainset.CSISHOW.length)||(startscishowx<pointSize))
		if((startscishowx>mmainset.BSSHOW.length))
		{
			startscishowx=0;
		}
		//startscishowx=94;
		int starttemp=(startscishowx-1)/pointSize*pointSize;
		startX=starttemp-1;
		endXSize=startscishowx-startX;
		if(startscishowx%pointSize==0)
		{
			startX2=-2;
			endX2Size=-1;
		}
		else
		{
			startX2=starttemp-pointSize;
			endX2Size=pointSize+1;
		}
	//	Log.i(TAG,"liuliangxiang startscishowx="+startscishowx);
	//	Log.i(TAG,"liuliangxiang startX2="+startX2);
	//	Log.i(TAG,"liuliangxiang endX2Size="+endX2Size);
	//	Log.i(TAG,"liuliangxiang endXSize="+endXSize);
		
		for(int k=0;k<=pointSize;k=k+6*timeScale)
		{
			Log.i(TAG,"liuliangxiang k="+k);
			//float x;//x1[]=new int[19];
			//x=ipoint[0].x+k*ix1Scale;
		//	int scale=timeScale;
			//if(k%(6*timeScale)==0)
			{
				float x;//x1[]=new int[19];
				//Log.i(TAG);
				x=ipoint[0].x+k/timeScale*ix1Scale;
			//	int hour=(startX+1+k)/3600;
			//	int min=(startX+1+k)%3600/60;
			//	int second=(startX+1+k)%60;
				int hour;//=(startX+1+k)/3600;
				int min;//=(startX+1+k)%3600/60;
				int second;//=(startX+1+k)%60;
				if((k>endXSize)&&(startX2>=0))
				{
					hour=(startX-pointSize+1+k)/3600;
					min=(startX-pointSize+1+k)%3600/60;
					second=(startX-pointSize+1+k)%60;
				}
				else
				{
					hour=(startX+1+k)/3600;
					min=(startX+1+k)%3600/60;
					second=(startX+1+k)%60;
				}
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
			}
		}
	//	Log.i(TAG,"liuliangxiang endXSize="+endXSize);
		/*
		if(startX2>0)
		{
			for(int k=endXSize;k<=pointSize;k=k+timeScale)
			{
			//	Log.i(TAG,"liuliangxiang k="+k);
				
			//	int scale=timeScale;
				if(k%(6*timeScale)==0)
				{
					float x;//x1[]=new int[19];
					x=ipoint[0].x+k/timeScale*ix1Scale;
					int hour=(startX2+1+k*timeScale)/3600;
					int min=(startX2+1+k*timeScale)%3600/60;
					int second=(startX2+1+k*timeScale)%60;
					String strhour=Integer.toString(hour);
					String strmin=Integer.toString(min);
					if(min<10)
					{
						strmin="0"+strmin;
					}
					String strsec=Integer.toString(second);
					if(second<10)
					{
						strsec="0"+strsec;
					}
					if(hour<=0)
						mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
					else
						mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, ipoint[1].y+12,CoordinatePaint);
				}
			}
		}*/
		
		mCanvas.drawText("100", ipoint[0].x-28, iy[0]+8, CoordTextPaint);
		mCanvas.drawText("80", ipoint[0].x-20, iy[2]+8, CoordTextPaint);
		mCanvas.drawText("60", ipoint[0].x-20, iy[4]+8, CoordTextPaint);
		mCanvas.drawText("40", ipoint[0].x-20, iy[6]+8, CoordTextPaint);
		mCanvas.drawText("20", ipoint[0].x-20, iy[8]+8, CoordTextPaint);
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y+0, CoordTextPaint);
		//if(mmainset.CSI.size()<=0)
			//return;
		miLinePath.reset();
		mLinePath.reset();
		float hx = ipoint[0].x;
		float hy = ipoint[1].y;
		int SCIData=0;
		
		if(startX<=0)
		{
			miLinePath.moveTo(ipoint[1].x,ipoint[1].y);
		}
		else
		{
			hx=ipoint[1].x;
			hy=ipoint[1].y;
			int temp = mmainset.BSSHOW[startX]&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			if(SCIData<100)
				hy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=ipoint[0].y;
			else if(SCIData==100)
				hy=ipoint[1].y;
			miLinePath.moveTo(hx, hy);
			miLinePath.lineTo(hx, hy);
		}
		int temp;

		for(int i=1;i<endXSize;i=i+pointScale)
		{
			temp = mmainset.BSSHOW[i+startX]&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=ipoint[1].y; 		
			if(SCIData<100)
				hy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=ipoint[0].y;
			else if(SCIData==100)
				hy=ipoint[1].y;
			//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
			if(i<scale)
			{
				hx=ipoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=ix[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
			//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
			miLinePath.lineTo(hx, hy);
		}
		miLinePath.moveTo(hx,hy);
		mCanvas.drawPath(miLinePath, mSLinePaint);
	//	mCanvas.drawPoint(hx,hy,mELinePaint);
		miLinePath.close();
		if((endX2Size<0)||(startX2<-1))
			return;
		
		if((endXSize)<scale)
		{
			hx=ipoint[0].x+xScale*((endXSize)%scale);
		//	Log.i(TAG,"liuliangxiang endXSize11="+endXSize);
		}
		else
		{
			hx=ix[(endXSize)/scale-1]+xScale*((endXSize)%scale);
		//	Log.i(TAG,"liuliangxiang endXSize22="+endXSize);
		}

		temp = mmainset.BSSHOW[startX2+endXSize]&0xff;
	//	Log.i(TAG,"liuliangxiang temp="+temp);
		if(temp>100)
			temp=0;
		SCIData=100-temp;
		hy=ipoint[1].y; 		
		if(SCIData<100)
			hy=iy[SCIData/10]+iyScale*(SCIData%10);
		else if(SCIData==0)
			hy=ipoint[0].y;
		else if(SCIData==100)
			hy=ipoint[1].y;
		mLinePath.moveTo(hx,hy);
		//int j=1;
		for(int i=endXSize+1;i<endX2Size;i=i+pointScale)
		{
			//j++;
			//Log.i(TAG,"liuliangxiang endX2Size222="+endX2Size);
			//Log.i(TAG,"liuliangxiang i="+i);
			//Log.i(TAG,"liuliangxiang i="+i);
			temp = mmainset.BSSHOW[i+startX2]&0xff;
			if(temp>100)
				temp=0;
			SCIData=100-temp;
			hy=ipoint[1].y; 		
			if(SCIData<100)
				hy=iy[SCIData/10]+iyScale*(SCIData%10);
			else if(SCIData==0)
				hy=ipoint[0].y;
			else if(SCIData==100)
				hy=ipoint[1].y;
			//Log.i(TAG,"liuliangxiang CSI="+((100-mmainset.CSI.get(i*scale+startX))&0xff));
			if(i<scale)
			{
				hx=ipoint[0].x+xScale*(i%scale)+xScale/6;
			}
			else
			{
				hx=ix[i/scale-1]+xScale*(i%scale)+xScale/6;
			}
			//hx[i]=ex[i/scale]-xScale*(scale-i%scale-1);
			//hx[i]=ix[(i-1)/pointScale]-xScale*((pointScale-(i-1)%pointScale));
			mLinePath.lineTo(hx, hy);
		}
		mLinePath.moveTo(hx,hy);
		mCanvas.drawPath(mLinePath, mSlinePaint1);
		mLinePath.close();
		miLinePath.reset();
		mLinePath.reset();
	}
	
	
	public void drawecoorddongjie()
	{
		mCanvas.drawLine(epoint[0].x,epoint[0].y,epoint[2].x,epoint[2].y,CoordinatePaint);
		mCanvas.drawLine(epoint[1].x,epoint[1].y,epoint[3].x,epoint[3].y,CoordinatePaint);
		mCanvas.drawLine(epoint[2].x,epoint[2].y,epoint[4].x,epoint[4].y,mGLinePaint);
		
		for(int i=0;i<ey.length;i++)
		{
			mCanvas.drawLine(epoint[0].x, ey[i], epoint[3].x, ey[i], mGLinePaint);
		}
		for(int j=0;j<ex.length;j++)
		{
			mCanvas.drawLine(ex[j],epoint[0].y ,ex[j], epoint[2].y, mGLinePaint);
		}
		mCanvas.drawText("EEG", ex[28], ey[6], WhitePaint);

		int startX;
		int dataSize;
		int timeScale;
		int scale;
		int pointSize=300;
		int pointScale=1;
		boolean isLess=false;
		//int CSIData=
		float xScale=ex1Scale;
		timeScale=mmainset.eegtimescale;
		int eegLen=mmainset.eegdongjielen;
		//Log.i(TAG,"Liuliangxiang eegLen="+eegLen);
		scale=10;
		
//		if(timeScale==1)
//		{
//			xScale=ix1Scale;
//			pointSize=19;
//		}
//		else 
		if(timeScale==5)
		{
			xScale=ex5Scale;
			pointSize=1500;
			pointScale=5;
			scale=50;
		}
		else if(timeScale==10)
		{
			xScale=ex10Scale;
			pointSize=3000;
			pointScale=10;
			scale=100;
		}
		else if(timeScale==60)
		{
		xScale=ex60Scale;
		pointSize=18000;
		pointScale=53;
		scale=600;
		}
		else if(timeScale==120)
		{
		xScale=ex120Scale;
		pointSize=36000;
		pointScale=95;
		scale=1200;
		}

		startX=(eegLen-pointSize/100)*100-eegmovedongjielen*100;
	//	Log.i(TAG,"liuliangxiang StartX="+startX);
		if(startX<0)
		{
			startX=0;
			eegmovereclen=(eegLen-pointSize/100);
		}
		dataSize=pointSize;
		if(eegLen<=pointSize/100)
		{
			startX=0;
			dataSize=eegLen*100;
		}
		CurDataLen=(startX+dataSize)/100-1;
	//	Log.i(TAG,"liuliangxiang eegLen="+eegLen);
		//	float[] hx=new float[dataSize];
		//	float[] hy=new float[dataSize];
		float hx = 0;
		float hy = 0;
		Integer[] EEGData=new Integer[100];
		float hyk;		
		if((dataSize>=100))
		{
		meLinePath.reset(); 		
		if(dataSize<pointSize)
		{
			meLinePath.moveTo(epoint[1].x, epoint[1].y);
		}
		else
		{
			int temp;
			int temp1=startX/100;
			EEGData=mmainset.EEG.get(temp1);
			//Log.i(TAG,"liuliangxiang startX="+temp1);
			temp=startX%100;
			hx=epoint[1].x;
	
			if(EEGData[temp]>=0x80)
			{
				EEGData[temp]=0x7f-EEGData[temp];
			}
			//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
			if(EEGData[temp]>=16)
			{
				hyk=ey[EEGData[temp]/16-1]-eyScale*(EEGData[temp]%16);
			}
			else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
			{
				hyk=epoint[1].y-eyScale*(EEGData[temp]);
			}
			else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
			{
				hyk=epoint[1].y-EEGData[temp]*eyScale;
			}
			else
			{
				hyk=ey[-EEGData[temp]/16+7]-eyScale*(EEGData[temp]%16);
			}
			meLinePath.moveTo(hx, hyk);
		}
		int step = 1;
		if(scale==10)
			{
				step=1;
			}
			else if(scale==50)
			{
				step=2;
			}
			else if(scale==100)
			{
				step=5;
			}
			else if(scale==600)
			{
				step=29;
			}
			else if(scale==1200)
			{
				step=53;
			}
//			else if(scale==12000)
		if(isLess)
		{
		/*	
		for(int k=0;k<(dataSize-100+EEGflen);k=k+step)
		{
		//hx[k]=ex[k/10]-xScale*((10-k)%10);
		hex=sex[k/scale]-xScale*(scale-k%scale-1);
		int temp;
		EEGData=mmainset.EEG.get((startX+k)/100);
		temp=(startX+k)%100;
		if(EEGData[temp]>=0x80)
		{
		EEGData[temp]=0x7f-EEGData[temp];
		}
		//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
		if(EEGData[temp]>=16)
		{
		hey=sey[EEGData[temp]/16-1]-seyScale*(EEGData[temp]%16);
		}
		else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
		{
		hey=spoint[4].y-seyScale*(EEGData[temp]);
		}
		else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
		{
		hey=spoint[4].y-EEGData[temp]*seyScale;
		}
		else
		{
		hey=sey[-EEGData[temp]/16+7]-seyScale*(EEGData[temp]%16);
		}
		mLinePath.lineTo(hex, hey);
		}*/
		}
		else
		{
			for(int k=0;k<(dataSize);k=k+step)
			{
				//hx[k]=ex[k/10]-xScale*((10-k)%10);
				int temp;
				hx=ex[k/scale]-xScale*(scale-k%scale-1);
				temp=(startX+k)%100;
				EEGData=mmainset.EEG.get((startX+k)/100);	
			//	Log.i(TAG,"liuliangxiang Startx="+(startX+k)/100);
				if(EEGData[temp]>=0x80)
				{
					EEGData[temp]=0x7f-EEGData[temp];
				}
				//	Log.i(TAG,"liuliangxiang EEGData ["+k+"]="+EEGData);
				if(EEGData[temp]>=16)
				{
					hy=ey[EEGData[temp]/16-1]-eyScale*(EEGData[temp]%16);
				}
				else if((EEGData[temp]<16)&&(EEGData[temp]>=0))
				{
					hy=epoint[1].y-eyScale*(EEGData[temp]);
				}
				else if((EEGData[temp]>-16)&&(EEGData[temp]<0))
				{
					hy=epoint[1].y-EEGData[temp]*eyScale;
				}
				else
				{
					hy=ey[-EEGData[temp]/16+7]-eyScale*(EEGData[temp]%16);
				}
				meLinePath.lineTo(hx, hy);
				}
			}
		}

		//	Log.i(TAG,"EEGflen="+EEGflen);
		if(dataSize>0)
		{
			meLinePath.moveTo(hx, hy);
		//	Log.i(TAG,"SCIData="+SCIData);
		//	Log.i(TAG,"hy["+(mmainset.CSI.size()-1)+"]"+hy[dataSize-1]);
		}
		mCanvas.drawPath(meLinePath, mELinePaint);
		if(curex!=-1)
		{
			int temp=(int)((curex-epoint[0].x)/xScale)+startX;
			if((temp/100)<mmainset.eegdongjielen)
				DataNum=temp/100-1;
			else
				DataNum=temp/100;
			if(((temp/100)<mmainset.eegdongjielen)&&(temp>0))
			{
				//int data=mmainset.RecCSI.get(temp);
				Integer[] data1=mmainset.EEG.get(temp/100);
				int data=(int)data1[temp%100];
				int hour=(temp/100)/3600;
				int min=(temp/100)%3600/60;
				int second=(temp/100)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				mCanvas.drawText("("+strmin+":"+strsec+","+Integer.toString(temp%100)+","+Integer.toString(data)+")", curex, epoint[0].y+40, BSTextPaint);
				mCanvas.drawLine(curex, epoint[0].y, curex, epoint[2].y, mSLine2ePaint);
			}

		}
		meLinePath.close();
		for(int k=0;k<=30;k=k+10)
		{
		float x;//x1[]=new int[19];

			x=epoint[0].x+(k*10)*ex1Scale;
			//x=ex[k/10]-xScale*(9-k%10);
		//	int scale=timeScale;
			//if(k%6==0)
			{
				int hour=(startX/100+(k/10)*timeScale)/3600;
				int min=(startX/100+(k/10)*timeScale)%3600/60;
				int second=(startX/100+(k/10)*timeScale)%60;
				String strhour=Integer.toString(hour);
				String strmin=Integer.toString(min);
				if(min<10)
				{
					strmin="0"+strmin;
				}
				String strsec=Integer.toString(second);
				if(second<10)
				{
					strsec="0"+strsec;
				}
				if(hour<=0)
					mCanvas.drawText(strmin+":"+strsec, x-mGLinePaint.measureText(strmin+":"+strsec)/2, epoint[2].y+12,CoordinatePaint);
				else
					mCanvas.drawText(strhour+":"+strmin+":"+strsec, x-mGLinePaint.measureText(strhour+":"+strmin+":"+strsec)/2, epoint[2].y+12,CoordinatePaint);
			}
		}	
		mCanvas.drawText("127", epoint[0].x-28, ey[7]+8, CoordTextPaint);
		mCanvas.drawText("112", epoint[0].x-28, ey[6]+8, CoordTextPaint);
		mCanvas.drawText("96", epoint[0].x-20, ey[5]+8, CoordTextPaint);
		mCanvas.drawText("80", epoint[0].x-20, ey[4]+8, CoordTextPaint);
		mCanvas.drawText("64", epoint[0].x-20, ey[3]+8, CoordTextPaint);
		mCanvas.drawText("48", epoint[0].x-20, ey[2]+8, CoordTextPaint);
		mCanvas.drawText("32", epoint[0].x-20, ey[1]+8, CoordTextPaint);
		mCanvas.drawText("16", epoint[0].x-20, ey[0]+8, CoordTextPaint);
		mCanvas.drawText("0uv", epoint[0].x-26, epoint[1].y+8, CoordTextPaint);		
		
		mCanvas.drawText("-16", epoint[0].x-26, ey[8]+8, CoordTextPaint);
		mCanvas.drawText("-32", epoint[0].x-26, ey[9]+8, CoordTextPaint);
		mCanvas.drawText("-48", epoint[0].x-26, ey[10]+8, CoordTextPaint);
		mCanvas.drawText("-64", epoint[0].x-26, ey[11]+8, CoordTextPaint);
		mCanvas.drawText("-80", epoint[0].x-26, ey[12]+8, CoordTextPaint);
		mCanvas.drawText("-96", epoint[0].x-26, ey[13]+8, CoordTextPaint);
		mCanvas.drawText("-112", epoint[0].x-36, ey[14]+8, CoordTextPaint);
		mCanvas.drawText("-128", epoint[0].x-36, ey[15]+2, CoordTextPaint);
		
//		mCanvas.drawText("-100", epoint[0].x-32, epoint[2].y+8, CoordTextPaint);
//		mCanvas.drawText("-80", epoint[0].x-26, y[9]+8, CoordTextPaint);
//		mCanvas.drawText("-60", epoint[0].x-26, y[8]+8, CoordTextPaint);
//		mCanvas.drawText("-40", epoint[0].x-26, y[7]+8, CoordTextPaint);
//		mCanvas.drawText("-20", epoint[0].x-26, y[6]+8, CoordTextPaint);

	}
	
	public void drawData()
	{
		int curIndex = 0;
		if(!mmainset.isdongjie)
			curIndex=mmainset.BS.size()-1;
		float startx=0;
		float starty=0;
		mCanvas.drawRoundRect(new RectF(startx,0,width*2/3,height/10+height/40+height/40),6.0f,6.0f,CoordinatePaint);
		mCanvas.drawRoundRect(new RectF(width*2/3+2,0,width,height/10+height/40+height/40),6.0f,6.0f,CoordinatePaint);
		mCanvas.drawRoundRect(new RectF(startx+1,2,width*2/3-1,height/10-1+height/40+height/40),6.0f,6.0f,groundPaint);
		mCanvas.drawRoundRect(new RectF(width*2/3+3,2,width-1,height/10-1+height/40+height/40),6.0f,6.0f,groundPaint);
		mCanvas.drawLine(width*2/10, 0, width*2/10, height/10+height/40+height/40, CoordinatePaint);
		mCanvas.drawLine(width*4/8, 0, width*4/8, height/10+height/40+height/40, CoordinatePaint);
		mCanvas.drawText(mcontext.getString(R.string.majui), startx+10, starty+30, CsiTitlePaint);
		mCanvas.drawText(mcontext.getString(R.string.baofayizhibi),width*2/10+2, starty+20+10,BSTextPaint2);
		mCanvas.drawText(mcontext.getString(R.string.jidianzhi),width*2/10+2, starty+20+62,EMGPaint);
		mCanvas.drawText(mcontext.getString(R.string.xinhaoliang),width*4/8+4, starty+20+8,WhitePaint2);
		mCanvas.drawText(mcontext.getString(R.string.heise), width*4/8+4, starty+17+height/24+14, WhitePaint2);		
		mCanvas.drawText(mcontext.getString(R.string.baise), width*4/8+4, starty+17+height*2/24+17, WhitePaint2);
		
		
		mCanvas.drawText(Integer.toString(mmainset.getshangxianshezValue()),startx+15, starty+15+height*1/30+height/80+10, CurCSIPaint);
		mCanvas.drawText(Integer.toString(mmainset.getxiaxianshezValue()),startx+15, starty+15+height*2/30+height/80+10,CurCSIPaint);
		if(mmainset.isRecData)
		{
			if((DataNum!=-1)&&(DataNum<(mmainset.RecCSI.size()))){
				if((mmainset.RecCSI.get(DataNum)&0xff)>100)
					mCanvas.drawText("▂ ▂",width*4/30-64, starty+15+height*5/80+20, CsiTitlePaint2);
				else
					mCanvas.drawText(Integer.toString(mmainset.RecCSI.get(DataNum)&0xff),width*4/30-64, starty+21+height*5/80+30,CSITextPaint);
				mCanvas.drawText(getThingFromCSI(mmainset.RecCSI.get(DataNum)),six[8]+4, siy[1]+5, WhitePaint);
			}
			else
			{
				if(CurDataLen>=0)
				{
					if(mmainset.RecCSI.size()>0){
						if(CurDataLen>=mmainset.RecCSI.size())
						{
							CurDataLen=mmainset.RecCSI.size()-1;
						}
						if((mmainset.RecCSI.get(CurDataLen)&0xff)>100)
							mCanvas.drawText("▂ ▂",width*4/30-64, starty+15+height*5/80+20, CsiTitlePaint2);
						else
							mCanvas.drawText(Integer.toString(mmainset.RecCSI.get(CurDataLen)&0xff),width*4/30-64, starty+21+height*5/80+30,CSITextPaint);
						mCanvas.drawText(getThingFromCSI(mmainset.RecCSI.get(CurDataLen)),six[8]+4, siy[1]+5, WhitePaint);
					}
				}
			}
		}
		else
		{
			if(mmainset.isdongjie)
			{
				if(DataNum!=-1)
					mCanvas.drawText(Integer.toString(mmainset.CSI.get(DataNum)&0xff),width*4/30-64, starty+15+height*5/80,CSITextPaint);
				else
				{
					if(CurDataLen>=0)
						mCanvas.drawText(Integer.toString(mmainset.CSI.get(CurDataLen)&0xff),width*4/30-64, starty+15+height*5/80,CSITextPaint);
				}
			}
			else
			{
				//mCanvas.drawText("鎺㈠ご娌￠敓鏂ゆ嫹閿熸枻鎷穕閿熸枻鎷�, width*2/3+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))+10, starty+15+height/30+30, TredTextPaint);
				if(mmainset.CSI.size()>0)
				{
				//	if(mmainset.CSI.get(mmainset.CSI.size()-1)&0xff==0xff)
					if((mmainset.CSI.get(mmainset.CSI.size()-1)&0xff) == 0xff)
					{
						mCanvas.drawText("▂ ▂",width*4/30-64, starty+9+height*5/80+30,CsiTitlePaint2);
						//mCanvas.drawText("鎺㈠ご娌￠敓鏂ゆ嫹閿熸枻鎷穕閿熸枻鎷�,width*2/3+20+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))+10, starty+15+height/30,TblueTextPaint);
						mCanvas.drawText(mcontext.getString(R.string.tantou_noconnect), width*2/3+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))+10, starty+15+height/30+30, TredTextPaint);
					}
					else			
					{
						mCanvas.drawText(Integer.toString(mmainset.CSI.get(mmainset.CSI.size()-1)&0xff),width*4/30-64, starty+21+height*5/80+30,CSITextPaint);
						mCanvas.drawText(getThingFromCSI(mmainset.things),six[8]+4, siy[1]+5, WhitePaint);
					}
				}
				else
					mCanvas.drawText("▂ ▂",width*4/30-64, starty+9+height*5/80+30,CSITextPaint);
			}
			mCanvas.drawText(getThingFromCSI(mmainset.things),six[8]+4, siy[1]+5, CurCSIPaint);
				
		}

		if(mmainset.isRecData)
		{
			if((DataNum!=-1)&&(DataNum<(mmainset.RecBS.size())))
			{
				if(mmainset.RecBS.get(DataNum)==0xff)
					mCanvas.drawText("▂ ▂",width*7/20+12+52, starty+20+12, BSTextPaintText);
				else
					mCanvas.drawText(Integer.toString(mmainset.RecBS.get(DataNum)),width*7/20+12+52, starty+20+15, BSTextPaintText);
			}
			else
			{
				if(CurDataLen>=0)
				{
					if(mmainset.RecBS.size()>0){
						if(CurDataLen>=mmainset.RecCSI.size())
						{
							CurDataLen=mmainset.RecCSI.size()-1;
						}
						if((mmainset.RecBS.get(CurDataLen)&0xff)==0xff)
							mCanvas.drawText("▂ ▂",width*7/20+12+52, starty+20+12, BSTextPaintText);
						else
							mCanvas.drawText(Integer.toString(mmainset.RecBS.get(CurDataLen)),width*7/20+12+52, starty+20+15, BSTextPaintText);
					}
				}
			}
			
			if((DataNum!=-1)&&(DataNum<(mmainset.RecEMG.size())))
			{
				if((mmainset.RecEMG.get(DataNum)&0xff)==0xff)
					mCanvas.drawText("▂ ▂",width*7/20+12+52, starty+10+62, EMGPaintText);
				else
					mCanvas.drawText(Integer.toString(mmainset.RecEMG.get(DataNum)),width*7/20+12+52, starty+20+64, EMGPaintText);
			}
			else
			{
				if(CurDataLen>=0)
				{
					if(mmainset.RecEMG.size()>0){
						if(CurDataLen>=mmainset.RecEMG.size())
						{
							CurDataLen=mmainset.RecEMG.size()-1;
						}
						if(mmainset.RecEMG.get(CurDataLen)==0xff)
							mCanvas.drawText("▂ ▂",width*7/20+12+52, starty+10+62, EMGPaintText);
						else
							mCanvas.drawText(Integer.toString(mmainset.RecEMG.get(CurDataLen)),width*7/20+12+52, starty+20+64, EMGPaintText);
					}
				}
			}

			if((DataNum!=-1)&&(DataNum<(mmainset.RecSQI.size())))
			{
				if(mmainset.RecSQI.get(DataNum)==0xff)
					mCanvas.drawText("▂ ▂",width*2/3-40,starty+20+2, WhitePaint);
				else
					mCanvas.drawText(Integer.toString(mmainset.RecSQI.get(DataNum)),width*2/3-40,starty+20+8, WhitePaint2);
			}
			else
			{
				if(CurDataLen>=0)
				{
					if(mmainset.RecSQI.size()>0){
						if(CurDataLen>=mmainset.RecSQI.size())
						{
							CurDataLen=mmainset.RecSQI.size()-1;
						}
						if(mmainset.RecSQI.get(CurDataLen)!=null){
							if(mmainset.RecSQI.get(CurDataLen)==0xff)
								mCanvas.drawText("▂ ▂",width*2/3-40,starty+20+2, WhitePaint);
							else
								mCanvas.drawText(Integer.toString(mmainset.RecSQI.get(CurDataLen)),width*2/3-40,starty+20+8, WhitePaint2);
						}
					}
				}		
			}
			if((DataNum!=-1)&&(DataNum<(mmainset.RecBLACK.size())))
			{
				if(!mmainset.isConnected)
					mCanvas.drawText("▂ ▂",width*2/3-40,starty+9+height/30+5+12, WhitePaint);
				else
					mCanvas.drawText(Integer.toString(mmainset.RecBLACK.get(DataNum)),width*2/3-40,starty+17+height/24+14, WhitePaint2);
			}
			else
			{
				if((CurDataLen>=0)&&(CurDataLen<mmainset.RecBLACK.size()))
					mCanvas.drawText(Integer.toString(mmainset.RecBLACK.get(CurDataLen)),width*2/3-40,starty+17+height/24+14, WhitePaint2);
			}
			if((DataNum!=-1)&&(DataNum<(mmainset.RecWHITE.size())))
			{
				mCanvas.drawText(Integer.toString(mmainset.RecWHITE.get(DataNum)),width*2/3-40,starty+17+height*2/24+17, WhitePaint2);
			}
			else
			{
				if((CurDataLen>=0)&&(CurDataLen<mmainset.RecWHITE.size()))
					mCanvas.drawText(Integer.toString(mmainset.RecWHITE.get(CurDataLen)),width*2/3-40,starty+17+height*2/24+17, WhitePaint2);
			}
				
		}
		else
		{
			if(mmainset.isdongjie)
			{
				if(DataNum!=-1)
				{
					mCanvas.drawText(Integer.toString(mmainset.BS.get(DataNum)),width*7/20+12+52, starty+20+15, BSTextPaintText);
				}
				else
				{
					if(CurDataLen>=mmainset.BS.size()){
						CurDataLen = mmainset.BS.size()-1;
					}
					if(CurDataLen>=0)
						mCanvas.drawText(Integer.toString(mmainset.BS.get(CurDataLen)),width*7/20+12+52, starty+20+15, BSTextPaintText);
					else
						mCanvas.drawText("▂ ▂",width*7/20+12+52, starty+20,BSTextPaintText);
				}
				if(DataNum!=-1)
				{
					mCanvas.drawText(Integer.toString(mmainset.EMG.get(DataNum)),width*7/20+12+52, starty+20+64, EMGPaintText);
				}
				else
				{
					if(CurDataLen>=0)
						mCanvas.drawText(Integer.toString(mmainset.EMG.get(CurDataLen)),width*7/20+12+52, starty+20+64, EMGPaintText);
				}
				if(DataNum!=-1)
				{
					mCanvas.drawText(Integer.toString(mmainset.SQI.get(DataNum)),width*2/3-20,starty+15+2, WhitePaint);
				}
				else
				{
					if(CurDataLen>=0)
						mCanvas.drawText(Integer.toString(mmainset.SQI.get(CurDataLen)),width*2/3-20,starty+15+2, WhitePaint);
				}
				if(DataNum!=-1)
				{
					mCanvas.drawText(Integer.toString(mmainset.BLACK.get(DataNum)),width*2/3-40,starty+15+height/30+2, WhitePaint);
				}
				else
				{
					if(CurDataLen>=0)
						mCanvas.drawText(Integer.toString(mmainset.BLACK.get(CurDataLen)),width*2/3-40,starty+15+height/30+2, WhitePaint);
				}
				if(DataNum!=-1)
				{
					mCanvas.drawText(Integer.toString(mmainset.WHITE.get(DataNum)),width*2/3-40,starty+15+height*2/30+2, WhitePaint);	
				}
				else
				{
					if(CurDataLen>=0)
						mCanvas.drawText(Integer.toString(mmainset.WHITE.get(CurDataLen)),width*2/3-40,starty+15+height*2/30+2, WhitePaint);	
				}	
			}
			else
			{
				if(curIndex>=0)
				{
				//	Log.i(TAG,"liuliangxiang mmainset.isConnected"+mmainset.isConnected);
				//	Log.i(TAG,"liuliangxiang mmainset.isWarnConnected"+mmainset.isWarnConnected);
					if((!mmainset.isConnected)||(mmainset.isWarnConnected))
					{
						//Log.i(TAG,"liuliangxiang 111");
						mCanvas.drawText("▂ ▂",width*7/20+12+52, starty+20,BSTextPaintText);
						mCanvas.drawText("▂ ▂",width*7/20+12+52, starty+10+62, EMGPaintText);	
						mCanvas.drawText("▂ ▂",width*2/3-40,starty+20+2, WhitePaint);
						mCanvas.drawText("▂ ▂",width*2/3-40,starty+9+height/30+5+12, WhitePaint);
						mCanvas.drawText("▂ ▂",width*2/3-40,starty+17+height*2/30+12, WhitePaint);
					}
					else
					{
						//Log.i(TAG,"liuliangxiang 222");
						if(mmainset.BS.get(curIndex)==0xff)
							mCanvas.drawText("▂ ▂",width*7/20+12+52, starty+20,BSTextPaintText);
						else
							mCanvas.drawText(Integer.toString(mmainset.BS.get(curIndex)),width*7/20+12+52, starty+20+15,BSTextPaintText);
						if(mmainset.EMG.get(curIndex)==0xff)
							mCanvas.drawText("▂ ▂",width*7/20+12+52, starty+10+62, EMGPaintText);
						else
							mCanvas.drawText(Integer.toString(mmainset.EMG.get(curIndex)),width*7/20+12+52, starty+20+64, EMGPaintText);
						if(mmainset.SQI.get(curIndex)>100)
	                        mCanvas.drawText("▂ ▂",width*2/3-40,starty+20+2, WhitePaint);
	                    else
	                    	mCanvas.drawText(Integer.toString(mmainset.SQI.get(curIndex)),width*2/3-40,starty+20+8, WhitePaint2);
						if(mmainset.SQI.get(curIndex)>100){
							mCanvas.drawText("▂ ▂",width*2/3-40,starty+17+height/24+14+4, WhitePaint);
						}else {
							if(mmainset.BLACK.get(curIndex)>=1)
								mCanvas.drawText(Integer.toString(mmainset.BLACK.get(curIndex)),width*2/3-40,starty+17+height/24+14, WhitePaint2);
							else
								mCanvas.drawText("<1",width*2/3-40,starty+17+height/24+14, WhitePaint2);
						}
						if (mmainset.SQI.get(curIndex) > 100) {
							mCanvas.drawText("▂ ▂", width * 2 / 3 - 40, starty + 17+ height * 2 / 24 + 17+4, WhitePaint);
						} else {
							if (mmainset.WHITE.get(curIndex) >= 1)
								mCanvas.drawText(Integer.toString(mmainset.WHITE.get(curIndex)),width * 2 / 3 - 40, starty + 17+ height * 2 / 24 + 17,WhitePaint2);
							else
								mCanvas.drawText("<1", width * 2 / 3 - 40,starty + 17 + height * 2 / 24 + 17,WhitePaint2);
						}
					}
				}
				else
				{
					mCanvas.drawText("▂ ▂",width*7/20+12+52, starty+20,BSTextPaintText);
					mCanvas.drawText("▂ ▂",width*7/20+12+52, starty+10+62, EMGPaintText);	
					mCanvas.drawText("▂ ▂",width*2/3-40,starty+20+2, WhitePaint);
					mCanvas.drawText("▂ ▂",width*2/3-40,starty+9+height/30+5+12, WhitePaint);
					mCanvas.drawText("▂ ▂",width*2/3-40,starty+17+height*2/30+12, WhitePaint);
				}
			}
		}

	//	mCanvas.drawText(mcontext.getString(R.string.xinxitishi),width*2/3+20, starty+15,WhitePaint);
	//	mCanvas.drawText(mcontext.getString(R.string.lianjiezhuangtai),width*2/3+20, starty+15+height/30,TblueTextPaint);
	/*	if(mmainset.isConnected)
		{
			if(mmainset.isWarnConnected)
			{
				mCanvas.drawText("閿熸枻鎷烽敓鏂ゆ嫹l閿熸枻鎷�,width*2/3+20+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))+30, starty+15+height/30,TblueTextPaint);
			}
			else
			{
				mCanvas.drawText("閿熸枻鎷穕閿熸枻鎷�,width*2/3+20+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))+30, starty+15+height/30,TblueTextPaint);
			}
			
		}
		else
		{
			mCanvas.drawText("鏈猯閿熸枻鎷�,width*2/3+20+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))+30, starty+15+height/30,TblueTextPaint);
		}*/
		if(mmainset.isStartRecord)
		{
			mCanvas.drawText(mcontext.getString(R.string.saving),width*2/3+20+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))+10, starty+15+height/30,TblueTextPaint);
		}
		else
		{
			if(mmainset.msgindex==0)
			{
				mCanvas.drawText(mcontext.getString(R.string.data_nosave),width*2/3+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))+10, starty+15+height/30,TblueTextPaint);
			}
			else if(mmainset.msgindex==1)
			{
				mCanvas.drawText(mcontext.getString(R.string.sd_unmount),width*2/3+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))+10, starty+15+height/30,TblueTextPaint);
			}
			else if(mmainset.msgindex==2)
			{
				mCanvas.drawText(mcontext.getString(R.string.sd_full),width*2/3+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))+10, starty+15+height/30,TblueTextPaint);
			}
			else if(mmainset.msgindex==3)
			{
				mCanvas.drawText(mcontext.getString(R.string.data_error),width*2/3+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))+10, starty+15+height/30,TblueTextPaint);
			}
			else if(mmainset.msgindex==4)
			{
				mCanvas.drawText(mcontext.getString(R.string.info_changed),width*2/3+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))-64, starty+15+height/30,TblueTextPaint);
			}
			else if(mmainset.msgindex==5)
			{
				mCanvas.drawText(mcontext.getString(R.string.info_error),width*2/3+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))-64, starty+15+height/30,TblueTextPaint);
			}
			else if(mmainset.msgindex==6)
			{
				mCanvas.drawText(mcontext.getString(R.string.review_record),width*2/3+grayTextPaint.measureText(mcontext.getString(R.string.lianjiezhuangtai))-64, starty+15+height/30,TblueTextPaint);
			}
		}
		/*
		mCanvas.drawText(mcontext.getString(R.string.diancidianliang),width*2/3+20, starty+15+height*2/30,TblueTextPaint);
		String str=mcontext.getString(R.string.diancidianliang);
		float lenth=grayTextPaint.measureText(str);
		if(pluggedInStatus==BatteryManager.BATTERY_STATUS_CHARGING)
		{			
			mCanvas.drawText(mcontext.getString(R.string.zhengzaichongdian),width*2/3+20+lenth+30, starty+15+height*2/30,TblueTextPaint);
		}
		else
		{
			mCanvas.drawText(Integer.toString(batteryLevel),width*2/3+20+lenth+30, starty+15+height*2/30,TblueTextPaint);
		}*/
		
/*		
		private Paint blueTextPaint;
		private Paint orageTextPaint;
		private Paint greenTextPaint;
		private Paint grayTextPaint;
		private Paint TblueTextPaint;
		private Paint TredTextPaint;
		private Paint TorageTextPaint;*/
		
	
	//	mCanvas.drawLine(startx+10, height/12, width, height/12, groundPaint);
	//	mCanvas.drawLine(startx+10, height/3, width, height/3, groundPaint);
	//	mCanvas.drawLine(startx+10, height/3+height/12, width, height/3+height/12, groundPaint);
	//	mCanvas.drawLine(startx+10, height*5/6, width, height*5/6, groundPaint);
	//	mCanvas.drawLine(startx+10, height*5/6+height/12, width, height*5/6+height/12, groundPaint);
		
	}
	
	
	public void lockCanvas()
	{
		mCanvas=this.mSurfaceHolder.lockCanvas();
	}
	public void unlockCanvas()
	{
		this.mSurfaceHolder.unlockCanvasAndPost(mCanvas);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		Log.i(TAG,"liuliangxiang surfaceChanged");
		
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i(TAG,"liuliangxiang surfaceCreated");
		
//		if(mThread.isAlive())
//		{
//			mThread.isRun=true;
//			mThread.run();
//		}
//		else
//		{
//			mThread.isRun=true;
//			mThread.start();
//		}
		mIsRunning=true;
		handler.post(mRunnable);
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i(TAG,"liuliangxiang surfaceDestroyed");
		synchronized (mSurfaceHolder)
		{
			mIsRunning=false;
			bitmapDown.recycle();
			bitmapUp.recycle();
		}
		
	}
	
	public class myRunnable implements Runnable
	{
        public void run() {
    		// TODO Auto-generated method stub
    	{
    			// TODO Auto-generated method stub
    			//while(boxing.mIsRunning)
    			boolean isFirstIn=false;
    		//	while(isRun)
    			{
    				synchronized (Application.mLock)
    				{   					
    					mCanvas=mSurfaceHolder.lockCanvas();
    					if(mCanvas==null)
    					{
    						return;
    					}
    					mCanvas.drawRect(new RectF(0,0,width,height-height/80),paint);
    					drawbackground();
    					//drawData();
    					if(mmainset.isRecData)
    					{
    						try {
								Thread.sleep(80);
							} catch (Exception e) {
								// TODO: handle exception
							}
    						scimovedongjielen=0;
    						eegmovedongjielen=0;
    						sci2movedongjielen=0;			
    					}
    					else
    					{
    						scimovereclen=0;
    						eegmovereclen=0;
    						sci2movereclen=0;
    						if(!mmainset.isdongjie)
    						{
    							scimovedongjielen=0;
    							eegmovedongjielen=0;
    							sci2movedongjielen=0;
    						}
    					}
    					if(mmainset.boxingType==mainset.DOUBLETYPE)
    					{
    						if(mmainset.isRecData)
    						{
    							drawseegcoordrec();
    						}
    						else
    						{
    							
    							if(!mmainset.isdongjie)
    								//drawscoord();
    								//drawscoord();
    								drawseegcoord();
    							else
    							{
    								drawscoorddongjie();
    							}
    						}
    						
    					}
    					else if(mmainset.boxingType==mainset.EEGTYPE)
    					{
    						if(mmainset.isRecData)
    						{
    							drawecoordrec();
    						}
    						else
    						{
    							if(!mmainset.isdongjie)
    								drawecoord();
    							else{
    								drawecoorddongjie();
    							}
    						}
    						
    					}
    					else if(mmainset.boxingType==mainset.SCITYPE)
    					{
    						if(mmainset.isRecData)
    						{
    							drawicoordrec();
    						}
    						else
    						{						
    							if(!mmainset.isdongjie)
    								//drawicoord();
    								drawicoord();
    							else
    								drawicoorddongjie();
    						}
    					}
    					else if(mmainset.boxingType==mainset.SCI2TYPE)
    					{
    						if(mmainset.isRecData)
    						{
    							drawscoordrec();
    						}
    						else
    						{
    							if(!mmainset.isdongjie)
    								//draweegcoord();
    								drawscoord();
    							else
    								drawi2coorddongjie();
    						}
    					}
    					else if(mmainset.boxingType==mmainset.SCITYPESHOW)
    					{
    						if(mmainset.isRecData){
    							mmainset.boxingType=mainset.SCITYPE;
    							drawicoordrec();
    						}else{
    							drawicoordshow();
    						}
    					}
    					else if(mmainset.boxingType==mmainset.SCI2TYPESHOW)
    					{
    						if(mmainset.isRecData){
    							mmainset.boxingType=mainset.SCI2TYPE;
    							drawscoordrec();
    						}else{
    							drawi2coordshow();
    						}
    					}
    					else if(mmainset.boxingType==mmainset.DOUBLETYPESHOW)
    					{
    						if(mmainset.isRecData){
    							mmainset.boxingType=mainset.DOUBLETYPE;
    							drawseegcoordrec();
    						}else{
    							drawscoordshow();
    						}
    					}
    					else if(mmainset.boxingType==mmainset.EEGTYPESHOW)
    					{
    						if(mmainset.isRecData){
    							mmainset.boxingType=mainset.EEGTYPE;
    							drawecoordrec();
    						}else{
    							drawecoordShow();
    						}
    					}

    					drawData();
    					//drawicoord();
    					//drawscoord();
    				//	drawecoord();
    					mSurfaceHolder.unlockCanvasAndPost(mCanvas);
    				}
    				
    			}
    			
    		}
    		if(mIsRunning)
    		{
    			//handler.postDelayed(mRunnable,500);
					if(mmainset.boxingType==mainset.EEGTYPE)
					{
						handler.postDelayed(mRunnable,15);
					}
					else if(mmainset.boxingType==mainset.SCI2TYPE)
					{
						handler.postDelayed(mRunnable,100);
					}
					else if(mmainset.boxingType==mainset.DOUBLETYPE)
					{
						handler.postDelayed(mRunnable,15);
					}
					else if(mmainset.boxingType==mainset.SCITYPE)
					{
						handler.postDelayed(mRunnable,500);
					}
					
					else if(mmainset.boxingType==mainset.EEGTYPESHOW)
					{
						handler.postDelayed(mRunnable,120);
					}
					else if(mmainset.boxingType==mainset.SCI2TYPESHOW)
					{
						handler.postDelayed(mRunnable,500);
					}
					else if(mmainset.boxingType==mainset.DOUBLETYPESHOW)
					{
						handler.postDelayed(mRunnable,120);
					}
					else if(mmainset.boxingType==mainset.SCITYPESHOW)
					{
						handler.postDelayed(mRunnable,500);
					}
    		}
    		
    	}
	
	}
	
	public String getThingFromCSI(int data)
		{
			String a = "";
			if((data>>8)%8==0x0)
			{
				if(data>=8){
					a="";
				}else{
					a=mcontext.getString(R.string.youdao);
				}
			}
			else if((data>>8)%8==0x1)
			{
				a=mcontext.getString(R.string.chaguan);
			}
			if((data>>8)%8==0x2)
			{
				a=mcontext.getString(R.string.weichi);
			}
			if((data>>8)%8==0x3)
			{
				a=mcontext.getString(R.string.waike);
			}
			if((data>>8)%8==0x4)
			{
				a=mcontext.getString(R.string.zhushe);
			}
			if((data>>8)%8==0x5)
			{
				a=mcontext.getString(R.string.beizhu);
			}
			if((data>>8)%8==0x6)
			{
				a=mcontext.getString(R.string.weichijies);
			}
			if((data>>8)%8==0x7)
			{
				a=mcontext.getString(R.string.yidong);
			} 
			if((data>>8)%8==0x8)
			{
				a=" ";
			} 
			
			return (a);
		}

	


	
}
