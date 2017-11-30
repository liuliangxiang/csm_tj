package com.example.csm.boxingview;

import com.example.csm.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;
import android.view.View;
import android.view.View.OnClickListener;

public class selectlanguageview extends FrameLayout implements OnClickListener{
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn;

	public selectlanguageview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public selectlanguageview(Context context) {
	//	super(context);
		this(context,null);
		// TODO Auto-generated constructor stub
	}
	
	public void onFinishInflate()
	{
		super.onFinishInflate();
		btn=(Button)findViewById(R.id.show);
		btn1=(Button)findViewById(R.id.expand);
		btn2=(Button)findViewById(R.id.englishselect);
		btn3=(Button)findViewById(R.id.chineseselect);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		
	}
	
	private void hide()
	{
		if(btn2.getVisibility()==View.VISIBLE)
		{
			btn3.setVisibility(View.GONE);
			btn2.setVisibility(View.GONE);
		}
	}
	private void show()
	{
		if(btn2.getVisibility()==View.GONE)
		{
			btn3.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.equals(btn1))
		{
			if(btn2.getVisibility()==View.GONE)
			{
				show();
			}
			else
			{
				hide();
			}
		}
		if(v.equals(btn2))
		{
			btn.setText(R.string.english);
			hide();
		}
		if(v.equals(btn3))
		{
			btn.setText(R.string.chinese);
			hide();
		}
		
	}

}
