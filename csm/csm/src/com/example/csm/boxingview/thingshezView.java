package com.example.csm.boxingview;

import java.util.ArrayList;

import com.example.csm.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TableLayout;
import android.view.View;
import android.view.View.OnClickListener;

public class thingshezView extends TableLayout implements  OnClickListener{
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	Button btn7;
	Button btn8;
	Button btn9;
	int item;
	int FlagValue;
	ArrayList<Button> btnarray=new ArrayList<Button>();
	thingsClickListener mthingsClickListener;
	
	public interface thingsClickListener
	{
		void onthingsselect(int things);
	}
	
	public void  setthingsClickListener(thingsClickListener l)
	{
		mthingsClickListener=l;
	}

	public thingshezView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public thingshezView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	protected void onFinishInflate()
	{
		super.onFinishInflate();
		btn1=(Button)findViewById(R.id.btn1);
		btn2=(Button)findViewById(R.id.btn2);
		btn3=(Button)findViewById(R.id.btn3);
		btn4=(Button)findViewById(R.id.btn4);
		btn5=(Button)findViewById(R.id.btn5);
		btn6=(Button)findViewById(R.id.btn6);
		btn7=(Button)findViewById(R.id.btn7);
		btn8=(Button)findViewById(R.id.btn8);
		btn9=(Button)findViewById(R.id.btn9);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btnarray.add(btn1);
		btnarray.add(btn2);
		btnarray.add(btn3);
		btnarray.add(btn4);
		btnarray.add(btn5);
		btnarray.add(btn6);
		btnarray.add(btn7);
		btnarray.add(btn8);
		btnarray.add(btn9);
		
		
	}
	
	public void setChecked(int item)
	{
		for(int i=0;i<btnarray.size();i++)
		{
			if(i==item)
			{
				btnarray.get(item).setBackgroundResource(R.drawable.radiobutton_on_background);
			}
			else
			{
				btnarray.get(i).setBackgroundResource(R.drawable.select);
			}
		}
	}
	
	public void setChecked(Button v)
	{
		for(int i=0;i<btnarray.size();i++)
		{
			if(v.equals(btnarray.get(i)))
			{
				v.setBackgroundResource(R.drawable.radiobutton_on_background);
			}
			else
			{
				v.setBackgroundResource(R.drawable.select);
			}
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.equals(btn1))
		{
			item=0;
		}
		else if(v.equals(btn2))
		{
			item=1;
		}
		else if(v.equals(btn3))
		{
			item=2;
		}
		else if(v.equals(btn4))
		{
			item=3;
		}
		else if(v.equals(btn5))
		{
			item=4;
		}
		else if(v.equals(btn6))
		{
			item=5;
		}
		else if(v.equals(btn7))
		{
			item=6;
		}
		else if(v.equals(btn8))
		{
			item=7;
		}
		else if(v.equals(btn9))
		{
			item=8;
		}
		mthingsClickListener.onthingsselect(item);
		setChecked(item);
							
		
	}

}
