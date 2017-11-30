package com.example.csm.boxingview;

import java.util.Calendar;

import com.example.csm.R;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;

public class DateTimeView extends TableLayout implements OnClickListener{

	int Hour;
	int Minitus;
	int Year;
	int Month;
	int Day;
	Button hour;
	Button houradd;
	Button hourplus;
	Button minutus;
	Button minadd;
	Button minplus;
	Button year;
	Button yearadd;
	Button yearplus;
	Button month;
	Button monadd;
	Button monplus;
	Button day;
	Button dayadd;
	Button dayplus;
	Button settime;
	Calendar c;

	public DateTimeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		c=Calendar.getInstance();
	}

	public DateTimeView(Context context) {
		//super(context);
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public void setHour(int Hour)
	{
		this.Hour=Hour;
		if(Hour<10)
		{
			hour.setText("0"+Integer.toString(Hour));
		}
		else
		{
			hour.setText(Integer.toString(Hour));
		}
	}
	public void setMinutus(int Minitus)
	{
		this.Minitus=Minitus;
		if(Minitus<10)
		{
			minutus.setText("0"+Integer.toString(Minitus));
		}
		else
		{
			minutus.setText(Integer.toString(Minitus));
		}
	}
	public void setYear(int Year)
	{
		this.Year=Year;
		year.setText(Integer.toString(Year));
	}
	public void setMonth(int Month)
	{
		this.Month=Month;
		if(Month<10)
		{
			month.setText("0"+Integer.toString(Month));
		}
		else
		{
			month.setText(Integer.toString(Month));
		}
	}
	public void setDay(int Day)
	{
		this.Day=Day;
		if(Day<10)
		{
			day.setText("0"+Integer.toString(Day));
		}
		else
		{
			day.setText(Integer.toString(Day));
		}
	}
	public int getHour()
	{
		return this.Hour;
	}
	public int getMinitus()
	{
		return this.Minitus;
	}
	public int getYear()
	{
		return this.Year;
	}
	public int getMonth()
	{
		return this.Month;
	}
	public int getDay()
	{
		return this.Day;
	}

	public int getMaxHour()
	{
		return 24;
	}
	public int getMaxMinitus()
	{
		return 60;
	}
	public int getMaxMonth()
	{
		return 12;
	}
	public int getMaxDay()
	{
		if((getMonth()==1)||(getMonth()==3)||(getMonth()==5)||(getMonth()==7)||(getMonth()==8)
			||(getMonth()==10)||(getMonth()==12))
		{
			return 31;
		}
		else if((getYear()%10!=0)&&(getYear()%4==0)&&(getMonth()==2))
		{
			return 28;
		}
		else if((getYear()%10==0)&&(getYear()%40==0)&&(getMonth()==2))
		{
			return 28;
		}
		else
		{
			return 30;
		}
	}

	public void onFinishInflate()
	{
		super.onFinishInflate();
		hour=(Button)findViewById(R.id.hour);
		houradd=(Button)findViewById(R.id.houradd);
		houradd.setOnClickListener(this);
		hourplus=(Button)findViewById(R.id.hourplus);
		houradd.setOnClickListener(this);
		minutus=(Button)findViewById(R.id.minutus);
		minadd=(Button)findViewById(R.id.minadd);
		minadd.setOnClickListener(this);
		minplus=(Button)findViewById(R.id.minplus);
		minplus.setOnClickListener(this);
		year=(Button)findViewById(R.id.year);
		yearadd=(Button)findViewById(R.id.yearadd);
		yearadd.setOnClickListener(this);
		yearplus=(Button)findViewById(R.id.yearplus);
		yearplus.setOnClickListener(this);
		month=(Button)findViewById(R.id.month);
		monadd=(Button)findViewById(R.id.monadd);
		monadd.setOnClickListener(this);
		monplus=(Button)findViewById(R.id.monplus);
		monplus.setOnClickListener(this);
		settime=(Button)findViewById(R.id.setTime);
		settime.setOnClickListener(this);
		day=(Button)findViewById(R.id.day);
		dayadd=(Button)findViewById(R.id.dayadd);
		dayadd.setOnClickListener(this);
		dayplus=(Button)findViewById(R.id.dayplus);
		dayplus.setOnClickListener(this);
		setYear(c.get(Calendar.YEAR));
		setMonth(c.get(Calendar.MONTH));
		setDay(c.get(Calendar.DATE));
		setHour(c.get(Calendar.HOUR));
		setMinutus(c.get(Calendar.MINUTE));
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.equals(houradd))
		{
			if(getHour()<(getMaxHour()-1))
			{
				setHour(getHour()+1);
			}
			else
			{
				setHour(0);
			}
		}
		else if(v.equals(hourplus))
		{
			if(getHour()>0)
			{
				setHour(getHour()-1);
			}
			else
			{
				setHour(getMaxHour()-1);
			}
		}

		else if(v.equals(minadd))
		{
			if(getMinitus()<(getMaxMinitus()-1))
			{
				setMinutus(getMinitus()+1);
			}
			else
			{
				setMinutus(0);
			}
		}
		else if (v.equals(minplus))
		{
			if(getMinitus()>0)
			{
				setMinutus(getMinitus()-1);
			}
			else
			{
				setMinutus(getMaxMinitus()-1);
			}
		}

		else if(v.equals(yearadd))
		{
			setYear(getYear()+1);
		}
		else if (v.equals(yearplus))
		{
			if(getYear()>0)
			{
				setYear(getYear()-1);
			}
		}

		else if(v.equals(monadd))
		{
			if(getMonth()<getMaxMonth())
			{
				setMonth(getMonth()+1);
			}
			else
			{
				setMonth(1);
			}
		}
		else if(v.equals(monplus))
		{
			if(getMonth()>1)
			{
				setMonth(getMonth()-1);
			}
			else
			{
				setMonth(getMaxMonth());
			}
		}

		else if(v.equals(dayadd))
		{
			if(getDay()<getMaxDay())
			{
				setDay(getDay()+1);
			}
			else
			{
				setDay(1);
			}
		}
		else if(v.equals(dayplus))
		{
			if(getDay()>1)
			{
				setDay(getDay()-1);
			}
			else
			{
				setDay(getMaxDay());
			}
		}
		else if(v.equals(settime))
		{
			Log.i("LIULIANGXIANG","liuliangxiang settime");
			c.set(Calendar.YEAR,2015);
			c.set(Calendar.MONTH,getMonth());
			c.set(Calendar.HOUR, 9);
			c.set(Calendar.MINUTE, getMinitus());
			c.set(Calendar.DATE,getDay());
			 long when = c.getTimeInMillis();

		        if (when / 1000 < Integer.MAX_VALUE) {
		            SystemClock.setCurrentTimeMillis(when);
		        }
		}




	}

}
