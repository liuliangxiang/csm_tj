package com.example.csm.boxingview;
import java.util.ArrayList;

import com.example.csm.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TableLayout;
import android.view.View;
import android.view.View.OnClickListener;

public class showsetting extends TableLayout implements  OnClickListener{
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	int item;
	int FlagValue;
	ArrayList<Button> btnarray=new ArrayList<Button>();
	showmodeClickListener mshowmodeClickListener;
	
	public interface showmodeClickListener
	{
		void onshowmodesselect(int things);
	}
	
	public void  setshowClickListener(showmodeClickListener l)
	{
		mshowmodeClickListener=l;
	}

	public showsetting(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public showsetting(Context context) {
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
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btnarray.add(btn1);
		btnarray.add(btn2);
		btnarray.add(btn3);
		btnarray.add(btn4);	
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
		mshowmodeClickListener.onshowmodesselect(item);
		setChecked(item);	
	}

}

