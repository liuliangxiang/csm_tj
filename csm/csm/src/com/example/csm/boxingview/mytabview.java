package com.example.csm.boxingview;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class mytabview extends View{
	
	private static final String TAG = "mytabview";
	private int ViewW;
	private int ViewH;
	private int TabH;
	private int TabW;
	//mainSet mmainSet;
	ArrayList<String> text=new ArrayList<String>();
	boolean[] clickItem;
	Paint mPaint;
	Paint mPaintText;
	Paint mExtraPaint;
	Paint mClickPaint;
	public int selectedId;
	
	TabClickLister tabClickListener;
	AudioManager audioManager;
	
	public interface TabClickLister
	{
		void onTabClick();
	}
	
	public void addHost(String text)
	{
		this.text.add(text);
		clickItem=new boolean[this.text.size()];
		invalidate();
	}
	public void clearHost()
	{
		this.text.clear();
		clickItem=null;
		invalidate();
	}
	public void setSelectHost(int Id)
	{
		selectedId=Id;
	}
	public int getSelectId()
	{
		return selectedId;
	}
	
	public void SetTabClickListener(TabClickLister listener)
	{
		tabClickListener=listener;
	}
	
	public boolean onTouchEvent (MotionEvent event)
	{
		float x=event.getX();
		float y=event.getY();
		final int Left = this.getLeft()*2/3;		
		final int Top = this.getTop()*2/3;
		//Log.i(TAG,"liuliangxiang onTouchEvent x="+x);
		//Log.i(TAG,"liuliangxiang onTouchEvent y="+y);
		for(int i=0;i<text.size();i++)
		{
			
			int L=(Left+i*TabW);
			clickItem[i]=false;
			//Log.i(TAG,"liuliangxiang Left="+Left);
			//Log.i(TAG,"liuliangxiang TabW="+TabW);
			//Log.i(TAG,"liuliangxiang Top="+Top);
			if((x>L&&x<(L+TabW))&&(y>(Top-30)&&(y<(Top+TabH+30))))
			{
				//Log.i(TAG,"liuliangxiang L="+L);
				//Log.i(TAG,"liuliangxiang Top="+Top);
				if(event.getAction()==MotionEvent.ACTION_DOWN)
				{
					clickItem[i]=true;
				}
				else if(event.getAction()==MotionEvent.ACTION_UP)
				{
					setSelectHost(i);
					if(tabClickListener!=null)
					{
					//	audioManager.playSoundEffect(AudioManager.FX_KEY_CLICK);
						tabClickListener.onTabClick();
					}
				}
				invalidate();
			}
		}
		return true;
	}

	public mytabview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	//	audioManager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		initView();
	}

	public mytabview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		audioManager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		initView();
	}
	
	public void initView()
	{
	//	mmainSet=mainSet.getInstance();
	//	text=mmainSet.getText();
		/*
		clickItem=new boolean[text.length];
		for(int i=0;i<text.length;i++)
		{
			clickItem[i]=false;
		}*/
		mPaint=new Paint();
		mPaint.setColor(0xcc555588);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.FILL);
		
		mClickPaint=new Paint();
		mClickPaint.setColor(0xaa559988);
		mClickPaint.setAntiAlias(true);
		mClickPaint.setStyle(Paint.Style.FILL);
					
		mPaintText=new Paint();
		mPaintText.setColor(0xffF5F5DC);
		mPaintText.setTextSize(20);	
		
		mExtraPaint = new Paint();
		mExtraPaint.setColor(0xee665588);
		mExtraPaint.setAntiAlias(true);
		mExtraPaint.setStyle(Paint.Style.STROKE);
		mExtraPaint.setPathEffect(new CornerPathEffect(10));
		mExtraPaint.setStrokeCap(Paint.Cap.ROUND);
		mExtraPaint.setStrokeWidth(3);
			
	}
	
	protected void onDraw(Canvas canvas) 
	{
	    super.onDraw(canvas);
		final int Left = getPaddingLeft();
		final int Top = getPaddingTop()+10;
		final int Right = Left+ViewW;
		final int Bottom =Top+ViewH;	
		canvas.save();
		canvas.drawRoundRect(new RectF((float)Left,(float)(Top+TabH),(float)Right,(float)(Bottom+TabH)), 20, 20, mPaint);
		canvas.drawRoundRect(new RectF((float)Left,(float)(Top+TabH),(float)Right,(float)(Bottom+TabH)), 20, 20, mExtraPaint);
	//	Log.i(TAG,"liuliangxiang onDraw Top="+Top);
	//	Log.i(TAG,"liuliangxiang onDraw Left="+Left);
		//canvas.drawRect(new RectF((float)Left,(float)Top,(float)(Left+20),(float)(Top+20)), mPaint);
		for(int i=0;i<text.size();i++)
		{
			int L=(Left+20+i*TabW);
			if(i==selectedId)
			{		
				if(clickItem[i]==true)
				{
					canvas.drawRoundRect(new RectF((float)L,(float)(Top-5),(float)(L+TabW),(float)(Top+TabH)),5,5 ,mClickPaint);
					canvas.drawRoundRect(new RectF((float)L,(float)(Top-5),(float)(L+TabW),(float)(Top+TabH)), 5,5,mExtraPaint);
				}
				else
				{
					canvas.drawRoundRect(new RectF((float)L,(float)Top-5,(float)(L+TabW),(float)(Top+TabH)),5,5 ,mPaint);
					canvas.drawRoundRect(new RectF((float)L,(float)Top-5,(float)(L+TabW),(float)(Top+TabH)), 5,5,mExtraPaint);
				}
			}
			else 
			{	
				if(clickItem[i]==true)
				{
					canvas.drawRoundRect(new RectF((float)L,(float)(Top-5),(float)(L+TabW),(float)(Top+TabH)), 5,5,mClickPaint);
					canvas.drawRoundRect(new RectF((float)L,(float)(Top-5),(float)(L+TabW),(float)(Top+TabH)), 5,5,mExtraPaint);
				}
				else
				{
					canvas.drawRoundRect(new RectF((float)L,(float)Top,(float)(L+TabW),(float)(Top+TabH)), 5,5,mPaint);
					canvas.drawRoundRect(new RectF((float)L,(float)Top,(float)(L+TabW),(float)(Top+TabH)), 5,5,mExtraPaint);
					
				}
					
			}
			float textw=mPaintText.measureText(text.get(i));
			float textH=mPaintText.getTextSize();
		//	Log.i(TAG,"liuliangxiang textw="+textw+" textH="+textH+" TabW="+TabW+" TabH="+TabH);
			canvas.drawText(text.get(i), L+(TabW-textw)/2, Top+24, mPaintText);		
		}
		
		canvas.restore();
			
	}
	
	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
	{
		int specMode = MeasureSpec.getMode(widthMeasureSpec);		
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);	
		if(specMode==MeasureSpec.AT_MOST)
		{
			ViewW=width*9/10;
			ViewH=height*3/4;
			TabH=ViewH/8;
			TabW=ViewW/5;
			setMeasuredDimension(ViewW, ViewH+TabH+10);
		}
		else
		{
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
	
	public void onLayout (boolean changed, int left, int top, int right, int bottom)
	{
		this.getLeft();
	//	Log.i(TAG,"liuliangxiang left="+left);
	//	Log.i(TAG,"liuliangxiang top="+top);
	//	Log.i(TAG,"liuliangxiang right="+right);
	//	Log.i(TAG,"liuliangxiang bottom="+bottom);
		super.onLayout(changed, left, top, right, bottom);
	}

}
