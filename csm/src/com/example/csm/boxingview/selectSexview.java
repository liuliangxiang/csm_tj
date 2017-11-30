package com.example.csm.boxingview;

import com.example.csm.R;
import com.example.csm.boxingview.showsetting.showmodeClickListener;
import com.example.csm.mainset.mainset;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;
import android.view.View;
import android.view.View.OnClickListener;

public class selectSexview extends FrameLayout implements OnClickListener{
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn;
	Context mContext;
	selectSex mselectSex;
	
	public interface selectSex
	{
		void onSelectSex(int Sex);
	}
	
	public void  setselectSexListener(selectSex l)
	{
		mselectSex=l;
	}

	public selectSexview(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext=context;
		// TODO Auto-generated constructor stub
	}

	public selectSexview(Context context) {
	//	super(context);
		this(context,null);
		mContext=context;
		// TODO Auto-generated constructor stub
	}
	
	public void onFinishInflate()
	{
		super.onFinishInflate();
		btn=(Button)findViewById(R.id.show);
		btn1=(Button)findViewById(R.id.expandsex);
		btn2=(Button)findViewById(R.id.manselect);
		btn3=(Button)findViewById(R.id.wowenselect);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn.setOnClickListener(this);
		int xingbie = Settings.System.getInt(mContext.getContentResolver(), "xingbie",0);
		if(xingbie == 0){
			btn.setText(R.string.man);
		}else{
			btn.setText(R.string.woman);
		}
		mainset.getInstance().mperinfomation.sex=btn.getText().toString();
		//Settings.System.putString(mContext.getContentResolver(), "xingbie",mainset.getInstance().mperinfomation.sex);
		
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
		if(v.equals(btn1)||v.equals(btn))
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
			
			btn.setText(R.string.man);
			mainset.getInstance().mperinfomation.sex=btn.getText().toString();
			mselectSex.onSelectSex(0);
			//Settings.System.putInt(mContext.getContentResolver(), "xingbie",0);
			hide();
		}
		if(v.equals(btn3))
		{
			btn.setText(R.string.woman);
			mainset.getInstance().mperinfomation.sex=btn.getText().toString();
			mselectSex.onSelectSex(1);
			//Settings.System.putInt(mContext.getContentResolver(), "xingbie",1);
			hide();
		}
		
	}

}
