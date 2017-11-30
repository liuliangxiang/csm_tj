package com.example.csm.boxingview;

import com.example.csm.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class tiaozhenView extends FrameLayout{
	Button textView;
	Button btn1;
	Button btn2;
	Context mContext;
	public static final int DOWN=0;
	public static final int UP=1;
	private static final String TAG = "tiaozhenView";
	private static int maxValue;
	private static int minValue;
	
	public interface tiaozhenClickListener
	{
		void onClickDownUp(int downup);
	}
	public void setMaxValue(int max)
	{
		maxValue=max;
	}
	public void setMinValue(int min)
	{
		minValue=min;
	}
	public int getMaxValue()
	{
		return maxValue;
	}
	public int getMinValue()
	{
		return minValue;
	}
	tiaozhenClickListener mtiaozhenClickListener;
	
	public void settiaozhenOnClickListener(tiaozhenClickListener l)
	{
		mtiaozhenClickListener=l;
	}

	public tiaozhenView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext=context;
		//initView();
	}

	public tiaozhenView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext=context;
		//initView();
	
	}
	
	public void initView()
	{
	//	textView=(Button)mContext.findViewById(R.id.maxVauleText);
		
		textView = (Button) findViewById(R.id.maxVauleText);
		
		textView.setText("50");
		textView.setTextSize(28);
		//textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		btn1=(Button)findViewById(R.id.add);
	//	btn1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		btn2=(Button)findViewById(R.id.plus);
	//	btn2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		btn1.setText("▲");
		btn2.setText("▼");
		btn1.setTextScaleX(2);
		btn2.setTextScaleX(2);
		btn1.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mtiaozhenClickListener.onClickDownUp(UP);				
			}			
		});
		
		btn2.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mtiaozhenClickListener.onClickDownUp(DOWN);
				
			}			
		});	
	}
	
	protected void onFinishInflate()
	{
		super.onFinishInflate();
		initView();
		
	}
	
	public int getValue()
	{
		CharSequence str1=textView.getText();
		
		Log.i(TAG,"liuliangxiang str="+str1);
		String str=str1.toString();
		
		if (str == null || str.trim().equals(""))
		{
			return 0;
		}
		else
		{
			
			return Integer.valueOf(str).intValue();
		}
	}
	public void setValue(int value)
	{
		textView.setText(Integer.toString(value));
	}


	
	
	/*
	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
	{
		int specMode = MeasureSpec.getMode(widthMeasureSpec);		
		int width = (int)(textView.getTextSize()*6);
		int height = (int)(textView.getTextSize());
		if(specMode==MeasureSpec.AT_MOST)
		{
			setMeasuredDimension(width, height);
		}
		else
		{
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
		
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		textView.layout(l, t, r, b);
		btn1.layout(l, t, (int) (r-textView.getTextSize()*2), t+(b-t)/2);
		btn2.layout(l, t+(b-t)/2, (int) (r-textView.getTextSize()*2), b);
	}
	*/
	/*
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

	      // TODO Auto-generated method stub

	      super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	      int childCount = getChildCount();

	      for(int i = 0; i < childCount; i ++){

	         View v = getChildAt(i);

	         v.measure(widthMeasureSpec, heightMeasureSpec);

	      }

	   }

	 

	 

	@Override

	   protected void onLayout(boolean changed, int l, int t, int r, int b) {

	      int childCount = getChildCount();
	      Log.i(TAG,"liuliangxiang l="+l+" t="+t+" r="+r+" b="+b);

	      textView.layout(l, t, l+80, t+80);
	      btn1.layout(l+40, t, l+80, t+40);
	      btn2.layout(l+40, t+40, l+80, t+80);


	   }*/



}
