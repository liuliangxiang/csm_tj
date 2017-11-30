package com.example.csm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.csm.mainset.mainset;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.res.Resources;
import zpSDK.zpSDK.zpSDK;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static BluetoothAdapter myBluetoothAdapter;
	public String SelectedBDAddress;
	StatusBox statusBox; 
	mainset mmainset;
	Bitmap bitmap;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if(!ListBluetoothDevice())finish();
        Button Button1 = (Button) findViewById(R.id.button1);
        mmainset = mainset.getInstance();
		statusBox = new StatusBox(this,Button1);
        Button1.setOnClickListener(new Button.OnClickListener()
        {
			public void onClick(View arg0)
			{
				Printbmp3(SelectedBDAddress);
			}
        });
        Button Button2 = (Button) findViewById(R.id.button2);
        Button2.setOnClickListener(new Button.OnClickListener()
        {
			public void onClick(View arg0)
			{
				//Print2(SelectedBDAddress);
				finish();
			}
        });
        Button Button3 = (Button) findViewById(R.id.button3);
        Button3.setOnClickListener(new Button.OnClickListener()
        {
			public void onClick(View arg0)
			{
				Print3(SelectedBDAddress);
			}
        });
        Button Button4 = (Button) findViewById(R.id.button4);
        Button4.setOnClickListener(new Button.OnClickListener()
        {
			public void onClick(View arg0)
			{
				Print4(SelectedBDAddress);
			}
        });
        Button Button5 = (Button) findViewById(R.id.button5);
        Button5.setOnClickListener(new Button.OnClickListener()
        {
			public void onClick(View arg0)
			{
				Print5(SelectedBDAddress);
			}
        });
        Button Button6 = (Button) findViewById(R.id.button6);
        Button6.setOnClickListener(new Button.OnClickListener()
        {
			public void onClick(View arg0)
			{
				Print6(SelectedBDAddress);
			}
        });
        zpSDK.zp_close(); 
       // bitmap=(Bitmap)(getIntent().getParcelableExtra("key"));
        
    }
    
    public void onResume()
    {
    	super.onResume();


   // 	finish();
    }

    
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
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
			Toast.makeText(this,R.string.no_printer, Toast.LENGTH_LONG).show();
    		return false;
    	}
		BluetoothDevice myDevice;
    	myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    	if(myBluetoothAdapter==null)
    	{
			Toast.makeText(this,R.string.bluetooth_error, Toast.LENGTH_LONG).show();
			return false;
    	}
    	myDevice = myBluetoothAdapter.getRemoteDevice(BDAddress);
    	if(myDevice==null)
    	{
			Toast.makeText(this,R.string.bluetooth_error, Toast.LENGTH_LONG).show();
			return false;
    	}
		if(zpSDK.zp_open(myBluetoothAdapter,myDevice)==false)
		{
			Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
    }
    public Bitmap GenerateBmp()
    {
    	Point ipoint[]=new Point[3];
    	float iy[]=new float[10];
    	float ix[]=new float[18];
    	Resources resources = getResources();
		DisplayMetrics display=resources.getDisplayMetrics();
		int height=display.heightPixels;
		int width=display.widthPixels;
		ipoint[0]=new Point();
		ipoint[0].x=20;
		ipoint[0].y=20;
		
		ipoint[1]=new Point();
		ipoint[1].x=20;
		ipoint[1].y=480;//+height/12;
	
		ipoint[2]=new Point();
		ipoint[2].x=1600;
		ipoint[2].y=480;
		float iyScale;
		float ix1Scale;
		float ix5Scale;
		float ix10Scale;
		float ix60Scale;
		float ix120Scale;
		iyScale=(iy[1]-iy[0])/10;
		//iyScale=(ipoint[1].y-ipoint[0].y)/100;
		ix1Scale=(ix[1]-ix[0]);
		ix5Scale=(ix1Scale)/5;
		ix10Scale=(ix1Scale)/10;
		ix60Scale=(ix1Scale)/60;
		ix120Scale=(ix1Scale)/120;
		for(int i=0;i<iy.length;i++)
		{
			iy[i]=ipoint[0].y+i*(ipoint[1].y-ipoint[0].y)/iy.length;
			
		}
		for(int j=0;j<ix.length;j++)
		{
			ix[j]=ipoint[0].x+(j+1)*(ipoint[2].x-ipoint[0].x)/ix.length;
		}
		Paint CoordinatePaint=new Paint();
		CoordinatePaint.setColor(Color.BLACK);
		CoordinatePaint.setStrokeWidth(2);
		
		Paint mGLinePaint=new Paint();
		mGLinePaint.setColor(Color.BLACK);
		mGLinePaint.setStrokeWidth(1.0f);
		PathEffect effects = new DashPathEffect(new float[]{5,5,5,5},1);  
		mGLinePaint.setPathEffect(effects);  
		
		Paint mSLinePaint = new Paint();
		mSLinePaint.setColor(Color.BLACK);
		mSLinePaint.setAntiAlias(true);
		mSLinePaint.setStyle(Paint.Style.STROKE);
		mSLinePaint.setPathEffect(new CornerPathEffect(10));
		mSLinePaint.setStrokeCap(Paint.Cap.ROUND);
		mSLinePaint.setStrokeWidth(2);
		
		
		Paint CoordTextPaint=new Paint();
		CoordTextPaint.setColor(0xee0000cc);
		CoordTextPaint.setTextSize(16);
		
		Paint WhitePaint=new Paint();
		WhitePaint.setColor(0xff0000ee);
		WhitePaint.setTextSize(16);
		
		Path mLinePath = new Path();
		Path miLinePath=new Path();
		
    	Bitmap bmp = Bitmap.createBitmap(1600,height, Bitmap.Config.ARGB_8888);
    	Canvas mCanvas = new Canvas(bmp);
    	
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
		mCanvas.drawText("0", ipoint[0].x-12, ipoint[1].y+0, CoordTextPaint);
		//if(mmainset.CSI.size()<=0)
			//return;
		miLinePath.reset();
		mLinePath.reset();
		float hx = ipoint[0].x;
		float hy = ipoint[1].y;
		int SCIData=0;
		
		mLinePath.moveTo(ipoint[0].x, ipoint[0].y);
		mLinePath.lineTo(ipoint[2].x, ipoint[2].y);
		mLinePath.moveTo(ipoint[2].x, ipoint[2].y);
		//mCanvas.rotate(90);
		mCanvas.drawPath(mLinePath, mSLinePaint);
		
    //	mCanvas.drawColor(Color.BLACK);   
    	return bmp;  	
    }
//    public void Printbitmap(String BDAddress)
//    {
//    	statusBox.Show("锟斤拷锟节达拷印...");
//		if(!OpenPrinter(BDAddress))
//		{
//			statusBox.Close();
//			return;
//		}
//		
//		if(!zpSDK.zp_page_create(150,120))
//		{
//     		Toast.makeText(this,"锟斤拷锟斤拷锟斤拷印页锟斤拷失锟斤拷", Toast.LENGTH_LONG).show();
//			statusBox.Close();
//     		return;
//		}
//		zpSDK.TextPosWinStyle = false;
//		Bitmap bmp=mmainset.genBitmap();
//		zpSDK.zp_draw_bitmap(bmp, 0, 400);
//		if(zpSDK.zp_printer_check_error())
//	    {
//			Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
//	    }
//		else
//		{
//			if(!zpSDK.zp_page_print(true))
//			{
//				Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
//			}
//			zpSDK.zp_page_free();
//			//zpSDK.zp_goto_mark_right(30);
//		}
//
//		if(zpSDK.zp_printer_check_error())
//	    {
//			Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
//	    }
//		zpSDK.zp_close();
//		statusBox.Close();
//		
//    }
    public void Printbitmap(String BDAddress)
    {
    	statusBox.Show(this.getString(R.string.printing));
    //	Bitmap bmp=bitmap;
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
		
		if(!zpSDK.zp_page_create(140,120))
		{
     		Toast.makeText(this,R.string.create_paper_error, Toast.LENGTH_LONG).show();
			statusBox.Close();
     		return;
		}
		zpSDK.TextPosWinStyle = false;
		//Bitmap bmp=mmainset.genBitmap();
		bitmap=BitmapFactory.decodeFile("/sdcard/DCIM/55.png");
		zpSDK.zp_draw_bitmap(bitmap, 0, 400);
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
		//finish();
		//bmp.recycle();
		//handler.sendEmptyMessage(9);
		
    }
    
    public void Printbmp3(String BDAddress)
    {
		statusBox.Show(this.getString(R.string.printing));

		if(!OpenPrinter(BDAddress))
		{
			statusBox.Close();
			return;
		}
		
		if(!zpSDK.zp_page_create(200,160))
		{	
			Toast.makeText(this,R.string.create_paper_error, Toast.LENGTH_LONG).show();
			statusBox.Close();
	 		return;
		}
		
		zpSDK.TextPosWinStyle = false;
		Bitmap bmp=BitmapFactory.decodeFile("/sdcard/DCIM/55.png");
		Matrix matrix = new Matrix();
		matrix.setRotate(90, bmp.getWidth()/2, bmp.getHeight()/2);
		
		bmp=Bitmap.createBitmap(bmp, 0,0, bmp.getWidth(), bmp.getHeight(), matrix, true);
		zpSDK.zp_draw_bitmap(bmp, 0, 0);
		zpSDK.zp_page_print(false);
		zpSDK.zp_goto_mark_left(10);
    	zpSDK.zp_printer_status_detect();
    	if(zpSDK.zp_printer_status_get(8000)!=0)
    	{
    		//showMessage(zpSDK.ErrorMessage);
    	}
       	zpSDK.zp_page_free();
    	zpSDK.zp_close();
		statusBox.Close();
		bmp.recycle();
		finish();
    }

	public void Print1(String BDAddress) 
	{
		statusBox.Show("锟斤拷锟节达拷印...");
		if(!OpenPrinter(BDAddress))
		{
			statusBox.Close();
			return;
		}
		
		if(!zpSDK.zp_page_create(80,108+64))
		{
     		Toast.makeText(this,"锟斤拷锟斤拷锟斤拷印页锟斤拷失锟斤拷", Toast.LENGTH_LONG).show();
			statusBox.Close();
     		return;
		}
		zpSDK.TextPosWinStyle = false;
		zpSDK.zp_draw_text_ex(18.3,3.4,"XX锟斤拷锟秸股凤拷锟斤拷锟睫癸拷司","锟斤拷锟斤拷",3.4,0,false,true,false);
		zpSDK.zp_draw_text_ex(17,7.6," 锟斤拷锟斤拷小锟斤拷锟斤拷失锟斤拷锟斤拷","锟斤拷锟斤拷",3.4,0,false,true,false);
		zpSDK.zp_draw_text_ex(0,12.4,"No. 1010000- 锟解案锟斤拷","锟斤拷锟斤拷",2.3,0,false,true,false);
		zpSDK.zp_draw_line(0, 14.5, 80,14.5,2); //锟斤拷一锟斤拷锟斤拷锟斤拷
		zpSDK.zp_draw_line(0, 18.5, 80,18.5,2); //锟节讹拷锟斤拷锟斤拷锟斤拷;
		zpSDK.zp_draw_line(0, 22.5, 80,22.5,2); //锟斤拷锟斤拷锟斤拷锟斤拷锟�;
		zpSDK.zp_draw_line(0, 26.5, 80,26.5,2); //锟斤拷锟侥革拷锟斤拷锟�;
		zpSDK.zp_draw_line(0, 30.5, 80,30.5,2); //锟斤拷锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(0,14.5,0,46.5,2);//锟斤拷一锟斤拷锟斤拷锟斤拷;;锟街斤拷锟斤拷
		zpSDK.zp_draw_line(10,14.5,10,26.5,2);//锟节讹拷锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(18,14.5,18,22.5,2);//锟斤拷锟斤拷锟斤拷锟斤拷锟�;
		zpSDK.zp_draw_line(26,18.5,26,22.5,2);//锟斤拷锟侥革拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(27,14.5,27,18.5,2);//锟斤拷锟侥革拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(22,22.5,22,26.5,2);//锟介勘锟截碉拷 锟斤拷锟斤拷锟斤拷;
		zpSDK.zp_draw_line(32,22.5,32,26.5,2);//锟介勘锟截碉拷 锟斤拷锟斤拷锟斤拷;
		zpSDK.zp_draw_line(43,18.5,43,22.5,2);//锟斤拷锟斤拷
		zpSDK.zp_draw_line(46,14.5,46,18.5,2);//锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(54,14.5,54,26.5,2);//锟斤拷锟侥革拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(63,22.5,63,26.5,2);//锟斤拷锟侥革拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(0.4,17,"锟斤拷锟秸猴拷锟斤拷","锟斤拷锟斤拷",2.4,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(18.4,17,"锟斤拷锟斤拷锟斤拷锟斤拷","锟斤拷锟斤拷",2.2,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(46.5,17,"锟斤拷锟斤拷锟斤拷","锟斤拷锟斤拷",2.2,0,false,true,false); //锟斤拷一锟斤拷锟斤拷锟斤拷;;;
		zpSDK.zp_draw_text_ex(0.4,21,"锟斤拷锟斤拷锟酵猴拷","锟斤拷锟斤拷",2.4,0,false,true,false);
		zpSDK.zp_draw_text_ex(18.5,21,"锟斤拷锟杰猴拷","锟斤拷锟斤拷",2.2,0,false,true,false);
		zpSDK.zp_draw_text_ex(43.5,21,"锟斤拷失锟斤拷锟","锟斤拷锟斤拷",2.4,0,false,true,false);
		zpSDK.zp_draw_text_ex(0.4,25,"锟介勘时锟斤拷","锟斤拷锟斤拷",2.4,0,false,true,false);
		zpSDK.zp_draw_text_ex(10.4,25," 锟斤拷 锟斤拷 锟斤拷","锟斤拷锟斤拷",2.2,0,false,true,false);
		zpSDK.zp_draw_text_ex(22.5,25,"锟介勘锟截碉拷","锟斤拷锟斤拷",2.4,0,false,true,false);
		zpSDK.zp_draw_text_ex(54.5,25,"锟斤拷锟睫碉拷位","锟斤拷锟斤拷",2.2,0,false,true,false);
		zpSDK.zp_draw_line(72,14.5,72,46.5,2);//末尾锟斤拷锟叫边斤拷锟斤拷;;;
		/* 锟斤拷锟斤拷目锟斤拷目锟斤拷始锟斤拷锟斤拷锟斤拷锟�*/
		zpSDK.zp_draw_line(6,26.5,6,46.5,2);//锟斤拷锟斤拷;; 锟斤拷锟斤拷锟斤拷锟�;
		zpSDK.zp_draw_line(22,26.5,22,46.5,2);//锟斤拷锟斤拷;; 锟斤拷锟斤拷锟斤拷锟�;
		zpSDK.zp_draw_line(31,26.5,31,46.5,2);//锟斤拷锟斤拷;; 锟斤拷锟斤拷锟斤拷锟�;
		zpSDK.zp_draw_line(38,26.5,38,46.5,2);//锟斤拷锟斤拷;; 锟斤拷锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(62,26.5,62,46.5,2);//锟斤拷锟斤拷;;锟斤拷锟揭伙拷械慕锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(0.4,29,"锟斤拷锟","锟斤拷锟斤拷",2.6,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(6.8,29," 锟斤拷 锟斤拷 锟斤拷 目","锟斤拷锟斤拷",2.6,0,true,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(23,29,"锟斤拷 锟斤拷","锟斤拷锟斤拷",2.6,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(31.5,29,"锟斤拷锟斤拷","锟斤拷锟斤拷",2.6,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(40.5,29," 维 锟斤拷 锟斤拷 目 ","锟斤拷锟斤拷",2.6,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(63.5,29,"锟斤拷 锟斤拷 ","锟斤拷锟斤拷",2.6,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(0, 34.5, 31,34.5,2); //锟斤拷一锟斤拷锟斤拷锟斤拷
		zpSDK.zp_draw_line(0, 38.5, 31,38.5,2); //锟节讹拷锟斤拷锟斤拷锟斤拷;
		zpSDK.zp_draw_line(0, 42.5, 31,42.5,2); //锟斤拷锟斤拷锟斤拷锟斤拷锟�;
		zpSDK.zp_draw_line(0, 46.5,80,46.5,2); //锟斤拷锟侥革拷锟斤拷锟�;
		zpSDK.zp_draw_line(38, 34.5, 80,34.5,2); //锟斤拷一锟斤拷锟斤拷锟斤拷
		zpSDK.zp_draw_line(38, 38.5, 80,38.5,2); //锟节讹拷锟斤拷锟斤拷锟斤拷;
		zpSDK.zp_draw_line(38, 42.5, 80,42.5,2); //锟斤拷锟斤拷锟斤拷锟斤拷锟�;
		//zpSDK.zp_draw_line(38, 46.5, 80,46.5,2); //锟斤拷锟侥革拷锟斤拷锟�;
		zpSDK.zp_draw_text_ex(33,35.5,"锟斤拷 ","锟斤拷锟斤拷",3.3,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(33,41.5,"锟斤拷","锟斤拷锟斤拷",3.3,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		//zpSDK.zp_goto_mark_label(1); 
		zpSDK.zp_draw_line(0,0+48,0,56+48,2);//锟斤拷一锟竭撅拷 锟斤拷锟斤拷 锟斤拷始锟斤拷
		zpSDK.zp_draw_line(0,0+48,80,0+48,2);//锟斤拷一锟竭撅拷 锟斤拷锟斤拷
		zpSDK.zp_draw_line(62,0+48,62,48+48,2);//锟斤拷一锟竭撅拷 末尾锟斤拷锟斤拷
		zpSDK.zp_draw_line(72,0+48,72,56+48,2);//锟斤拷一锟竭撅拷 末尾锟斤拷锟斤拷
		zpSDK.zp_draw_line(6,0+48,6,48+48,2);//锟斤拷2锟竭撅拷 锟斤拷锟斤拷
		zpSDK.zp_draw_line(22,0+48,22,52+48,2);//锟斤拷3锟竭撅拷 锟斤拷锟斤拷
		zpSDK.zp_draw_line(31,0+48,31,48+48,2);//锟斤拷3锟竭撅拷 锟斤拷锟斤拷
		zpSDK.zp_draw_line(38,0+48,38,52+48,2);//锟斤拷4锟竭撅拷 锟斤拷锟斤拷
		zpSDK.zp_draw_line(62,0+48,62,52+48,2);//锟斤拷4锟竭撅拷 锟斤拷锟斤拷*/
		zpSDK.zp_draw_line(0, 4+48, 31,4+48,2); //锟斤拷一锟斤拷锟斤拷锟斤拷 锟斤拷装1
		zpSDK.zp_draw_line(0, 8+48, 31,8+48,2); //锟节讹拷锟斤拷锟斤拷锟斤拷; 锟斤拷装1
		zpSDK.zp_draw_line(0, 12+48, 31,12+48,2); //锟斤拷锟斤拷锟斤拷锟斤拷锟�; 锟斤拷装1
		zpSDK.zp_draw_line(0, 16+48,80,16+48,2); //锟斤拷锟侥革拷锟斤拷锟�; 锟斤拷装1
		zpSDK.zp_draw_line(38, 4+48, 80,4+48,2); //锟斤拷一锟斤拷锟斤拷锟斤拷 锟斤拷装2
		zpSDK.zp_draw_line(38, 8+48, 80,8+48,2); //锟节讹拷锟斤拷锟斤拷锟斤拷; 锟斤拷装2
		zpSDK.zp_draw_line(38, 12+48, 80,12+48,2); //锟斤拷锟斤拷锟斤拷锟斤拷锟�; 锟斤拷装2
		zpSDK.zp_draw_text_ex(33,5+48,"锟斤拷 ","锟斤拷锟斤拷",3.3,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(33,11+48,"装","锟斤拷锟斤拷",3.3,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(0, 20+48, 31,20+48,2); //锟斤拷一锟斤拷锟斤拷锟斤拷 锟接斤拷1
		zpSDK.zp_draw_line(0, 24+48, 31,24+48,2); //锟节讹拷锟斤拷锟斤拷锟斤拷; 锟接斤拷1
		zpSDK.zp_draw_line(0, 28+48, 31,28+48,2); //锟斤拷锟斤拷锟斤拷锟斤拷锟�; 锟接斤拷1
		zpSDK.zp_draw_line(0, 32+48,80,32+48,2); //锟斤拷锟侥革拷锟斤拷锟�; 锟接斤拷1
		zpSDK.zp_draw_line(38, 20+48, 80,20+48,2); //锟斤拷一锟斤拷锟斤拷锟斤拷 锟接斤拷2
		zpSDK.zp_draw_line(38, 24+48, 80,24+48,2); //锟节讹拷锟斤拷锟斤拷锟斤拷; 锟接斤拷2
		zpSDK.zp_draw_line(38, 28+48, 80,28+48,2); //锟斤拷锟斤拷锟斤拷锟斤拷锟�; 锟接斤拷2
		zpSDK.zp_draw_text_ex(33,21+48,"锟斤拷 ","锟斤拷锟斤拷",3.3,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(33,27+48,"锟斤拷","锟斤拷锟斤拷",3.3,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(0, 36+48, 31,36+48,2); //锟斤拷一锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷1
		zpSDK.zp_draw_line(0, 40+48, 31,40+48,2); //锟节讹拷锟斤拷锟斤拷锟斤拷; 锟斤拷锟斤拷1
		zpSDK.zp_draw_line(0, 44+48, 31,44+48,2); //锟斤拷锟斤拷锟斤拷锟斤拷锟�; 锟斤拷锟斤拷1
		zpSDK.zp_draw_line(0, 48+48,80,48+48,2); //锟斤拷锟侥革拷锟斤拷锟�; 锟斤拷锟斤拷1
		zpSDK.zp_draw_line(38, 36+48, 80,36+48,2); //锟斤拷一锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷2
		zpSDK.zp_draw_line(38, 40+48, 80,40+48,2); //锟节讹拷锟斤拷锟斤拷锟斤拷; 锟斤拷锟斤拷2
		zpSDK.zp_draw_line(38, 44+48, 80,44+48,2); //锟斤拷锟斤拷锟斤拷锟斤拷锟�; 锟斤拷锟斤拷2
		zpSDK.zp_draw_text_ex(33,37+48,"锟斤拷 ","锟斤拷锟斤拷",3.3,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(33,41+48,"锟斤拷","锟斤拷锟斤拷",3.3,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(0,52+48,80,52+48,3); //锟斤拷锟较凤拷小锟斤拷;;;;;;;;;;;;;;
		zpSDK.zp_draw_line(0,56+48,80,56+48,3); //维锟睫凤拷小锟斤拷;;;;;;;;;;;;;;
		zpSDK.zp_draw_line(13.5,48+48,13.5,52+48,2); //锟斤拷锟较凤拷小锟斤拷锟斤拷锟斤拷;;;;;;;;;锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(48,48+48,48,52+48,2); //维锟睫凤拷小锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷;;;;;;;;;;;;;;
		zpSDK.zp_draw_text_ex(0.6,51.3+48,"锟斤拷锟较凤拷小锟斤拷","锟斤拷锟斤拷",2.6,0,false,true,false);
		zpSDK.zp_draw_text_ex(22.4,51.3+48," 锟斤拷 值 锟斤拷","锟斤拷锟斤拷",2.6,0,false,true,false);
		zpSDK.zp_draw_text_ex(48.6,51.3+48,"维锟睫凤拷小锟斤拷","锟斤拷锟斤拷",2.6,0,false,true,false);
		zpSDK.zp_draw_text_ex(3,54.9+48,"维锟睫合计ｏ拷 千 锟斤拷 拾 元 锟斤拷锟斤拷 .00) ","锟斤拷锟斤拷",3.1,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;

		zpSDK.zp_draw_text_ex(0,2.6+108,"锟截憋拷注锟斤拷锟斤拷锟斤拷","锟斤拷锟斤拷",3.1,0,true,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(0,6.1+108,"1.","锟斤拷锟斤拷",2.9,0,false,true,false);
		zpSDK.zp_draw_text_box(4,6.1+108,66,100,"锟杰憋拷锟秸癸拷司委锟叫憋拷锟斤拷锟金单核讹拷锟斤拷锟斤拷锟�000元锟斤拷锟斤拷,锟斤拷锟斤拷私锟斤拷锟斤拷锟绞э拷硕锟斤拷员锟斤拷展锟剿撅拷锟斤拷谐锟斤拷叩墓锟斤拷鄣锟轿�","锟斤拷锟斤拷",2.9,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(0,14+112,"2.","锟斤拷锟斤拷",2.9,0,false,true,false);
		zpSDK.zp_draw_text_box(4,14+112,66,100,"锟斤拷锟斤拷硕锟斤拷锟斤拷锟斤拷锟轿拷藜锟斤拷锟绞凤拷锟斤拷锟斤拷锟街伙拷锟斤拷锟斤拷锟斤拷锟絏X锟斤拷锟秸股凤拷锟斤拷锟睫癸拷司锟斤拷锟斤拷锟斤拷锟斤拷锟轿筹拷诺锟斤拷实锟绞碉拷锟解付锟斤拷锟�锟皆憋拷锟秸猴拷同锟较碉拷约锟斤拷锟斤拷锟斤拷为准锟斤拷","锟斤拷锟斤拷",2.9,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(0,27+112,"3 .锟介勘锟斤拷锟斤拷锟","锟斤拷锟斤拷",2.9,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(2.8,32+112,"锟介勘锟剿电话锟斤拷 ","锟斤拷锟斤拷",2.9,0,false,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(0,35+112,80,35+112,2); //锟斤拷锟斤拷锟斤拷锟斤拷锟�;
		zpSDK.zp_draw_line(0,55+112,80,55+112,2); //锟斤拷锟斤拷锟斤拷锟斤拷锟�;;
		zpSDK.zp_draw_line(0,35+112,0,55+112,2); //锟斤拷锟斤拷 锟斤拷叩锟�		zpSDK.zp_draw_line(24,35+112,24,55+112,2); //锟斤拷锟斤拷
		zpSDK.zp_draw_line(48,35+112,48,55+112,2); //48.--60锟斤拷锟斤拷;; 
		zpSDK.zp_draw_line(72,35+112,72,55+112,2); //锟斤拷锟揭憋拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(0,39+112,"锟斤拷锟斤拷锟斤拷锟斤拷(锟斤拷锟斤拷)签锟斤拷:","锟斤拷锟斤拷",2.6,0,true,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(26,39+112,"锟斤拷锟睫凤拷签锟斤拷:","锟斤拷锟斤拷",2.8,0,true,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;; 
		zpSDK.zp_draw_text_ex(50,39+112,"锟斤拷锟秸癸拷司签锟斤拷:","锟斤拷锟斤拷",2.8,0,true,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;; 
		zpSDK.zp_draw_text_ex(2,47+112,"锟斤拷锟斤拷锟斤拷","锟斤拷锟斤拷",2.8,0,true,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(26,47+112,"锟斤拷锟斤拷锟斤拷","锟斤拷锟斤拷",2.8,0,true,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(50,47+112,"锟介勘锟斤拷","锟斤拷锟斤拷",2.8,0,true,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(10,52+112," 锟斤拷 锟斤拷 锟斤拷","锟斤拷锟斤拷",2.8,0,true,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(34,52+112," 锟斤拷 锟斤拷 锟斤拷","锟斤拷锟斤拷",2.8,0,true,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(58,52+112," 锟斤拷 锟斤拷 锟斤拷","锟斤拷锟斤拷",2.8,0,true,true,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;

		if(zpSDK.zp_printer_check_error())
	    {
			Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
	    }
		else
		{
			if(!zpSDK.zp_page_print(false))
			{
				Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
			}
			zpSDK.zp_page_free();
			zpSDK.zp_goto_mark_right(30);
		}

		if(zpSDK.zp_printer_check_error())
	    {
			Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
	    }
		zpSDK.zp_close();
		statusBox.Close();
	}
	public void Print2(String BDAddress) 
	{
		statusBox.Show("锟斤拷锟节达拷印...");

		if(!OpenPrinter(BDAddress))
		{
			statusBox.Close();
			return;
		}
		if(!zpSDK.zp_page_create(82,72))
		{	
			Toast.makeText(this,"锟斤拷锟斤拷锟斤拷印页锟斤拷失锟斤拷", Toast.LENGTH_LONG).show();
			statusBox.Close();
	 		return;
		}
		zpSDK.TextPosWinStyle = false;
		zpSDK.zp_draw_text_ex(18.3,2.7,"XX锟斤拷锟秸股凤拷锟斤拷锟睫癸拷司","锟斤拷锟斤拷",3.4,0,false,true,false);
		zpSDK.zp_draw_text_ex(20,6.6," 锟斤拷锟斤拷小锟斤拷锟斤拷失锟斤拷锟斤拷","锟斤拷锟斤拷",3.4,0,false,true,false);
		zpSDK.zp_draw_text_ex(0,11.4,"No. 1010000- 锟解案锟斤拷","锟斤拷锟斤拷",2.5,0,false,true,false);

		zpSDK.zp_draw_line(0, 13.5, 80,13.5,2); //锟斤拷一锟斤拷锟斤拷锟斤拷
		zpSDK.zp_draw_line(0, 17.5, 80,17.5,2); //锟节讹拷锟斤拷锟斤拷锟斤拷;
		zpSDK.zp_draw_line(0, 21.5, 80,21.5,2); //锟斤拷锟斤拷锟斤拷锟斤拷锟�;
		zpSDK.zp_draw_line(0, 25.5, 80,25.5,2); //锟斤拷锟侥革拷锟斤拷锟�;
		zpSDK.zp_draw_line(0, 29.5, 80,29.5,2); //锟斤拷锟斤拷锟斤拷锟斤拷;;

		zpSDK.zp_draw_line(0,13.5,0,69.5,2);//锟斤拷一锟斤拷锟斤拷锟斤拷;;锟街斤拷锟斤拷
		zpSDK.zp_draw_line(13,13.5,13,25.5,2);//锟节讹拷锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(21,13.5,21,21.5,2);//锟斤拷锟斤拷锟斤拷锟斤拷锟�;
		zpSDK.zp_draw_line(30,17.5,30,21.5,2);//锟斤拷锟侥革拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(31,13.5,31,17.5,2);//锟斤拷锟侥革拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(26,21.5,26,25.5,2);//锟介勘锟截碉拷 锟斤拷锟斤拷锟斤拷;
		zpSDK.zp_draw_line(37,21.5,37,25.5,2);//锟介勘锟截碉拷 锟斤拷锟斤拷锟斤拷;
		zpSDK.zp_draw_line(47,17.5,47,21.5,2);//锟斤拷锟斤拷
		zpSDK.zp_draw_line(50,13.5,50,17.5,2);//锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(60.5,13.5,60.5,25.5,2);//锟斤拷锟侥革拷锟斤拷锟斤拷;
		zpSDK.zp_draw_line(70,21.5,70,25.5,2);//锟斤拷锟侥革拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(80,13.5,80,69.5,2);//末尾锟斤拷锟叫边斤拷锟斤拷;;;

		zpSDK.zp_draw_text_ex(1.1,16,"锟斤拷锟秸猴拷锟斤拷","锟斤拷锟斤拷",2.6,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(21,16,"锟斤拷锟斤拷锟斤拷锟斤拷","锟斤拷锟斤拷",2.6,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(51,16,"锟斤拷锟斤拷锟斤拷","锟斤拷锟斤拷",2.6,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		
		zpSDK.zp_draw_text_ex(1.1,20,"锟斤拷锟斤拷锟酵猴拷","锟斤拷锟斤拷",2.6,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(21,20,"锟斤拷锟杰猴拷","锟斤拷锟斤拷",2.6,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(49.5,20,"锟斤拷失锟斤拷锟","锟斤拷锟斤拷",2.6,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		
		zpSDK.zp_draw_text_ex(1.5,24,"锟介勘时锟斤拷","锟斤拷锟斤拷",2.6,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(13.5,24,"锟斤拷 锟斤拷  锟斤拷","锟斤拷锟斤拷",2.6,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(26.2,24,"锟介勘锟截碉拷","锟斤拷锟斤拷",2.6,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(60.5,24,"锟斤拷锟睫碉拷位","锟斤拷锟斤拷",2.4,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		
		//锟斤拷锟� 锟斤拷锟斤拷目  锟斤拷锟� 锟斤拷锟斤拷    维锟斤拷锟斤拷目     锟斤拷锟�
		zpSDK.zp_draw_text_ex(1.2,28.3,"锟斤拷锟","锟斤拷锟斤拷",2.9,0,false,false,false);//锟斤拷锟�;.
		zpSDK.zp_draw_text_ex(7.8,28.3," 锟斤拷  锟斤拷  锟斤拷  目","锟斤拷锟斤拷",2.9,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(27,28.3,"锟斤拷锟","锟斤拷锟斤拷",2.9,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(36.8,28.3,"锟斤拷锟斤拷","锟斤拷锟斤拷",2.9,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(48,28.3,"维  锟斤拷  锟斤拷  目","锟斤拷锟斤拷",2.9,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(69,28.3,"锟斤拷 锟斤拷","锟斤拷锟斤拷",2.9,0,false,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(37.6,35,"锟斤拷","锟斤拷锟斤拷",4,6,true,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(37.6,41,"锟斤拷","锟斤拷锟斤拷",4.2,0,true,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;

		for(double i=33.5;i<44;i+=4)
		{
			zpSDK.zp_draw_line(0, i,36,i,2); //锟斤拷锟�锟斤拷锟�		
		}
		
		for(double i=33.5;i<44;i+=4)
		{
			zpSDK.zp_draw_line(44, i,80,i,2); //锟斤拷锟�锟斤拷锟斤拷
		}
		
		zpSDK.zp_draw_line(0, 45.5,80,45.5,2); //锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷
		
		zpSDK.zp_draw_line(7.2, 25.5,7.2,69.5,2); //锟斤拷锟�锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(26, 25.5,26,69.5,2); //锟斤拷锟�锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(36, 25.5,36,69.5,2); //锟斤拷锟�锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(44, 25.5,44,69.5,2); //锟斤拷锟�锟节讹拷锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_line(68, 25.5,68,69.5,2); //锟斤拷锟�锟节讹拷锟斤拷锟斤拷锟斤拷;;
		
		//    锟斤拷装          锟斤拷锟揭猴拷锟竭诧拷锟斤拷 //
		for(double i=49.5;i<65.6;i+=4)
		{
			zpSDK.zp_draw_line(0, i,36,i,2); //锟斤拷装 锟斤拷锟�		
		}
		
		for(double i=49.5;i<65.6;i+=4)
		{
			zpSDK.zp_draw_line(44, i,80,i,2); //锟斤拷装 锟斤拷锟斤拷
		}
		zpSDK.zp_draw_line(0, 65.5,80,65.5,2); //锟斤拷锟斤拷锟斤拷;
		
		zpSDK.zp_draw_text_ex(37.6,52,"维","锟斤拷锟斤拷",4,6,true,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;
		zpSDK.zp_draw_text_ex(37.6,55.8,"锟斤拷","锟斤拷锟斤拷",4.2,0,true,false,false);//锟斤拷一锟斤拷锟斤拷锟斤拷;;

		//    锟斤拷装          锟斤拷锟揭猴拷锟竭诧拷锟斤拷 //
		zpSDK.zp_draw_line(0, 69.5,80,69.5,2); //锟斤拷锟斤拷锟斤拷;;;
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
			zpSDK.zp_goto_mark_right(30);
		}

//		if(zpSDK.zp_printer_check_error())
//	    {
//			Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
//	    }
	//	zpSDK.zp_close();
		statusBox.Close();
	}
	public void Print3(String BDAddress) 
	{
		statusBox.Show("锟斤拷锟节达拷印...");
		if(!OpenPrinter(BDAddress))
		{
			statusBox.Close();
			return;
		}
		if(!zpSDK.zp_page_create(82,72))
		{
			Toast.makeText(this,"锟斤拷锟斤拷锟斤拷印页锟斤拷失锟斤拷", Toast.LENGTH_LONG).show();
			statusBox.Close();
			return;	
		}
		zpSDK.TextPosWinStyle = true;

		zpSDK.zp_draw_rect(0.1, 0.1, 81.9, 71.9, 2);
		zpSDK.zp_draw_line(1, 1,5,5,2); //锟斤拷锟斤拷锟斤拷;;;
		zpSDK.zp_draw_text_ex(5, 4, "CODE128:","锟斤拷锟斤拷",4,0,false,false,false);
		zpSDK.zp_draw_barcode(25,2, "12345678", zpSDK.BARCODE_TYPE.BARCODE_CODE128, 8, 2, 0);
		zpSDK.zp_draw_text_ex(5, 14, "CODE93:","锟斤拷锟斤拷",4,0,false,false,false);
		zpSDK.zp_draw_barcode(25,12, "12345678", zpSDK.BARCODE_TYPE.BARCODE_CODE93, 8, 2, 0);
		zpSDK.zp_draw_text_ex(5, 24, "CODE39:","锟斤拷锟斤拷",4,0,false,false,false);
		zpSDK.zp_draw_barcode(25,22, "12345678", zpSDK.BARCODE_TYPE.BARCODE_CODE39, 8, 2, 0);
		zpSDK.zp_draw_text_ex(5, 34, "EAN8:","锟斤拷锟斤拷",4,0,false,false,false);
		zpSDK.zp_draw_barcode(25,32, "12345678", zpSDK.BARCODE_TYPE.BARCODE_EAN8, 8, 2, 0);
		zpSDK.zp_draw_text_ex(5, 44, "EAN13:","锟斤拷锟斤拷",4,0,false,false,false);
		zpSDK.zp_draw_barcode(25,42, "1234567890123", zpSDK.BARCODE_TYPE.BARCODE_EAN13, 8, 2, 0);
		zpSDK.zp_draw_text_ex(5, 54, "UPC:","锟斤拷锟斤拷",4,0,false,false,false);
		zpSDK.zp_draw_barcode(25,52, "123456789012", zpSDK.BARCODE_TYPE.BARCODE_UPC, 8, 2, 0);
		zpSDK.zp_draw_text_ex(5, 64, "CODABAR:","锟斤拷锟斤拷",4,0,false,false,false);
		zpSDK.zp_draw_barcode(25,62, "A23456789012A", zpSDK.BARCODE_TYPE.BARCODE_CODABAR, 8, 2, 0);

//		zpSDK.zp_draw_text_box(5, 5, 30,30, "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890","锟斤拷锟斤拷",2.5,0,false,false,false);

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
			zpSDK.zp_goto_mark_right(30);
		}

		if(zpSDK.zp_printer_check_error())
	    {
			Toast.makeText(this,zpSDK.ErrorMessage, Toast.LENGTH_LONG).show();
	    }
		zpSDK.zp_close();
		statusBox.Close();
	}
	public void Print4(String BDAddress) 
	{
		if(!OpenPrinter(BDAddress))return;
		zpSDK.zp_goto_mark_left(100);
		zpSDK.zp_close();
	}
	public void Print5(String BDAddress) 
	{
		if(!OpenPrinter(BDAddress))return;
		zpSDK.zp_goto_mark_right(100);
		zpSDK.zp_close();
	}
	public void Print6(String BDAddress) 
	{
		if(!OpenPrinter(BDAddress))return;
		zpSDK.zp_goto_mark_label(100);
		zpSDK.zp_close();
	}//print6
	
	public void windowInit(String Msg,String Title,View anchor) 
	{
	}//initpop
	  public void showMessage(String str)
	    {
	        Toast.makeText(this,str, Toast.LENGTH_LONG).show();
	    }

}
