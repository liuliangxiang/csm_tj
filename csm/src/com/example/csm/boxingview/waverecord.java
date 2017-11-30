package com.example.csm.boxingview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import zpSDK.zpSDK.zpSDK;

import com.example.csm.Application;
import com.example.csm.Csmmain;
import com.example.csm.R;
import com.example.csm.Csmmain.mSaveRunnable;
import com.example.csm.boxingview.showsetting.showmodeClickListener;
import com.example.csm.mainset.mainset;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android_serialport_api.SerialPort;

public class waverecord extends LinearLayout implements OnClickListener{
	private static final String TAG = "waverecord";
	mainset mmainset=mainset.getInstance();
	Button minbtnup;
	Button minbtndn;
	Button maxbtnup;
	Button maxbtndn;
	Button delbtn;
	
	EditText nametext;
	EditText notext;
	TextView starttimeText;
	TextView endtimeText;
	public ListView nameList;
	public ListView noList;
	public ListView timeList;
	private static final int MATCH_NAME = 1;
	private static final int MATCH_NO = 1;
	Context context;
	int syearvalue;
	int smonvalue;
	int sdayvalue;
	int shourvalue;
	int sminvalue;
	int ssecvalue;
	
	int eyearvalue;
	int emonvalue;
	int edayvalue;
	int ehourvalue;
	int eminvalue;
	int esecvalue;
	
	int minTime;
	int maxTime;
	int curminTime;
	int curmaxTime;
	int timenum;
	ArrayList<Integer> maxtimeList=new ArrayList<Integer>();
	ArrayList<Integer> nametimeList=new  ArrayList<Integer>();
	ArrayList<String> nametimeStrList=new ArrayList<String>();
	ArrayList<String> listItem=new ArrayList<String>();
	public ArrayList<String> recordno = new ArrayList<String>();
	public ArrayList<String> recordname = new ArrayList<String>();
	public ArrayList<String> recordnoname = new ArrayList<String>();
	boolean isSettime=false;
	
	String tmptime;
	String watchNameText="";
	String watchNoText="";
	
	updateInfomationListener mupdateInfomationListener;
	
	
	public interface updateInfomationListener
	{
		void onupdateInfomation();
	}
	
	public void  setupdateInfomationListener(updateInfomationListener l)
	{
		mupdateInfomationListener=l;
	}
	
	public void setStartTime(String text)
	{
		//starttimeText.setText(text);
		final StringTokenizer tok=new StringTokenizer(text,"-");
		ArrayList<Integer> al=new ArrayList<Integer>();
		while (tok.hasMoreTokens()) {
			final String uid = tok.nextToken();
			if (!uid.equals("")) {
				try {
					al.add(Integer.parseInt(uid));
				} catch (Exception ex) {
				}
			}
		}
		syearvalue=al.get(0);
		smonvalue=al.get(1);
		sdayvalue=al.get(2);
		shourvalue=al.get(3);
		sminvalue=al.get(4);
		ssecvalue=al.get(5);
	}
	
	public void setMaxTime(int MaxTime)
	{
		this.maxTime=MaxTime;
	}
	public void setMinTime(int minTime)
	{
		this.minTime=minTime;
	}
	public void setCurMinTime(int curMinTime)
	{
		this.curminTime=curMinTime;
	}
	public void setCurMaxTime(int curMaxTime)
	{
		this.curmaxTime=curMaxTime;
	}
	public int getCurMinTime()
	{
		return curminTime;
	}
	public int getCurMaxTime()
	{
		return curmaxTime;
	}
	public boolean isSetDispTime()
	{
		return isSettime;
	}
	
	public static String getFileNameNoEx(String filename){
	    if((filename!=null)&&(filename.length()>0)){
	        int dot = filename.lastIndexOf('.');
	        if((dot>-1)&&(dot<filename.length())){
	            return filename.substring(0,dot);
	        }
	    }
	    return filename;
	}
	public void getRecData()
	{
		{
		String starfilename="";
		int fileId = 0;
		for(int i=0;i<nametimeList.size();i++)
		{			
			if(nametimeList.get(i)>=curminTime)
			{
				starfilename=nametimeStrList.get(i);
				fileId=i;
				break;
			}			
		}
		timenum=0;
		int temp=fileId;
	//	Log.i(TAG,"liuliangxiang temp="+temp);
		if(starfilename.equals("")||strFile.equals(""))
		{
			return;
		}
		if(!isSettime)
		{
			return;
		}
		mmainset.RecCSI.clear();
		mmainset.RecWHITE.clear();
		mmainset.RecBLACK.clear();
		mmainset.RecBS.clear();
		mmainset.RecEMG.clear();
		mmainset.RecSQI.clear();
		mmainset.RecEEG.clear();
		//while(true)
		{
			
			InputStream is = null;
			try {
			//	Log.i(TAG,"liuliangxiang temp="+temp);
				//Log.i(TAG,"liuliangxiangfile="+strFile);
				is=(InputStream)new FileInputStream(strFile);
				
				strFile="";
//				Log.i(TAG,"liuliangxiang strFile="+temp);
//				Log.i(TAG,"liuliangxiang temp="+nametimeStrList.get(temp));
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));   
			    StringBuilder sb = new StringBuilder();
			    String line = null;
			    mmainset.curminTime=this.curminTime;
		    	mmainset.curmaxTime=this.curmaxTime;
			    while ((line = reader.readLine())!=null)
			    {	
			    //	Log.i(TAG,"liuliangxiang temp=333");
					Thread.sleep(2);
					/*
					if(!line.endsWith(":"))
					{
					//	Log.i(TAG,"liuliangxiang temp=444");
						return;
					//	continue;
					}*/
					//Log.i(TAG,"liuliangxiang temp=111");
			
			    	if(line.startsWith("Name"))
			    	{
			    		mmainset.RecName=line.substring(line.lastIndexOf("Name:")+5, line.lastIndexOf("Sex:"));
			    		mmainset.RecSex=line.substring(line.lastIndexOf("Sex:")+4, line.lastIndexOf("PNo:"));
			    		mmainset.RecNO=line.substring(line.lastIndexOf("PNo:")+4, line.length());
			    		mupdateInfomationListener.onupdateInfomation();
			    	}
			    	if(line.startsWith("NO"))
			    	{
			    		//Log.i(TAG,"liuliangxiang temp=333");
			    		String tempthings = line.substring(line.lastIndexOf("THING:")+6, line.lastIndexOf("CSI:")-1);
			    		String tempCsi=line.substring(line.lastIndexOf("CSI:")+4, line.lastIndexOf("BS:")-1);
			    		String tempBs=line.substring(line.lastIndexOf("BS:")+3, line.lastIndexOf("EMG:")-1);
			    		String tempEmg=line.substring(line.lastIndexOf("EMG:")+4,line.lastIndexOf("SQI:")-1);
			    		String tempSqi=line.substring(line.lastIndexOf("SQI:")+4,line.lastIndexOf("BLACK:")-1);
			    		String tempBLACK=line.substring(line.lastIndexOf("BLACK:")+6, line.lastIndexOf("WHITE:")-1);
			    		String tempWhite=line.substring(line.lastIndexOf("WHITE:")+6, line.lastIndexOf("EEG:")-1);
			    		String tempEeg=line.substring(line.lastIndexOf("EEG")+3, line.length());
			    		int things = getThingValue(tempthings);
			    		mmainset.RecCSI.add(((Integer.parseInt(tempCsi))&0xff)|(things<<(8)));
			    		mmainset.RecWHITE.add(Integer.parseInt(tempWhite));
			    		mmainset.RecBLACK.add(Integer.parseInt(tempBLACK));
			    		mmainset.RecBS.add(Integer.parseInt(tempBs));
			    		mmainset.RecEMG.add(Integer.parseInt(tempEmg));
			    		mmainset.RecSQI.add(Integer.parseInt(tempSqi));
			    		Integer eeg[]=new Integer[100];
			    	//	Log.i(TAG,"liuliangxiang tempCsi="+tempCsi);
			    		
			    		final StringTokenizer tok=new StringTokenizer(tempEeg,":");
			    		int i=0;
			    		while (tok.hasMoreTokens()) {
			    			String eegvalue = tok.nextToken();
			    		//	Log.i(TAG,"liuliangxiang eeg["+i+"]"+eegvalue);
			    			if (!eegvalue.equals("")) {
			    				try {
			    					eeg[i]=Integer.parseInt(eegvalue);
			    					i++;
			    					
			    				} catch (Exception ex) {
			    				}
			    			}
			    		}
			    		mmainset.RecEEG.add(eeg.clone());	
			    		timenum++;
			    		//Log.i(TAG,"liuliangxiang timenum="+timenum);
			    		if(timenum>(curmaxTime-curminTime))
			    		{
			    			is.close();
			    			return;
			    		}
			    	}
			    }

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					if(is!=null)
						is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//temp=temp+1;
			
		}
		}
		
	}
	
	
	public void getPrintData()
    {
        String starfilename="";
        int fileId = 0;
        for(int i=0;i<nametimeList.size();i++)
        {           
            if(nametimeList.get(i)>=curminTime)
            {
                starfilename=nametimeStrList.get(i);
                fileId=i;
                break;
            }           
        }
        timenum=0;
        int temp=fileId;
    //  Log.i(TAG,"liuliangxiang temp="+temp);
        if(starfilename.equals("")||strFile.equals(""))
        {
            return;
        }
        if(!isSettime)
        {
            return;
        }
        mmainset.RecCSI.clear();
        mmainset.RecWHITE.clear();
        mmainset.RecBLACK.clear();
        mmainset.RecBS.clear();
        mmainset.RecEMG.clear();
        mmainset.RecSQI.clear();
        mmainset.RecEEG.clear();
     //   while(true)
        {
            
            InputStream is = null;
            try {
            //  Log.i(TAG,"liuliangxiang temp="+temp);
                File file =new File(getFileNameNoEx(strFile)+"print.txt");
                //Log.i(TAG,"liuliangxiangfile="+file);
                if(file.exists()){
                	is=(InputStream)new FileInputStream(file);
                	mmainset.isPrintOrignFile = true;

                } else{
                	 is=(InputStream)new FileInputStream(strFile);
                	 //mmainset.isPrintOrignFile = true;

                }
               // Log.i(TAG,"liuliangxiang isPrintOrignFile ="+mmainset.isPrintOrignFile);
                strFile="";
//              Log.i(TAG,"liuliangxiang strFile="+temp);
//              Log.i(TAG,"liuliangxiang temp="+nametimeStrList.get(temp));
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));   
                StringBuilder sb = new StringBuilder();
                String line = null;
                mmainset.curminTime=this.curminTime;
                mmainset.curmaxTime=this.curmaxTime;
                while ((line = reader.readLine())!=null)
                {   
                //  Log.i(TAG,"liuliangxiang temp=333");
                    Thread.sleep(1);
                    /*
                    if(!line.endsWith(":"))
                    {
                    //  Log.i(TAG,"liuliangxiang temp=444");
                        return;
                    //  continue;
                    }*/
                    //Log.i(TAG,"liuliangxiang temp=111");
            
                    if(line.startsWith("Name"))
                    {
                        //Log.i(TAG,"liuliangxiang temp=222");
                        mmainset.RecName=line.substring(line.lastIndexOf("Name:")+5, line.lastIndexOf("Sex:"));
                        mmainset.RecSex=line.substring(line.lastIndexOf("Sex:")+4, line.lastIndexOf("PNo:"));
                        mmainset.RecNO=line.substring(line.lastIndexOf("PNo:")+4, line.length());
                        mupdateInfomationListener.onupdateInfomation();
                    }
                    if(line.startsWith("NO"))
                    {
                        //Log.i(TAG,"liuliangxiang temp=333");
                        String tempthings = line.substring(line.lastIndexOf("THING:")+6, line.lastIndexOf("CSI:")-1);
                        String tempCsi=line.substring(line.lastIndexOf("CSI:")+4, line.lastIndexOf("BS:")-1);
                        String tempBs=line.substring(line.lastIndexOf("BS:")+3, line.lastIndexOf("EMG:")-1);
                        String tempEmg=line.substring(line.lastIndexOf("EMG:")+4,line.lastIndexOf("SQI:")-1);
                        String tempSqi=line.substring(line.lastIndexOf("SQI:")+4,line.lastIndexOf("BLACK:")-1);
                        String tempBLACK=line.substring(line.lastIndexOf("BLACK:")+6, line.lastIndexOf("WHITE:")-1);
                        String tempWhite=line.substring(line.lastIndexOf("WHITE:")+6, line.lastIndexOf("EEG:")-1);
                        String tempEeg=line.substring(line.lastIndexOf("EEG")+3, line.length());
                        int things = getThingValue(tempthings);
                        mmainset.RecCSI.add(((Integer.parseInt(tempCsi))&0xff)|(things<<(8)));
                        mmainset.RecWHITE.add(Integer.parseInt(tempWhite));
                        mmainset.RecBLACK.add(Integer.parseInt(tempBLACK));
                        mmainset.RecBS.add(Integer.parseInt(tempBs));
                        mmainset.RecEMG.add(Integer.parseInt(tempEmg));
                        mmainset.RecSQI.add(Integer.parseInt(tempSqi));
                        Integer eeg[]=new Integer[100];
                       // Log.i(TAG,"liuliangxiang tempCsi="+tempCsi);
                        
                        final StringTokenizer tok=new StringTokenizer(tempEeg,":");
                        int i=0;
                        while (tok.hasMoreTokens()) {
                            String eegvalue = tok.nextToken();
                        //  Log.i(TAG,"liuliangxiang eeg["+i+"]"+eegvalue);
                            if (!eegvalue.equals("")) {
                                try {
                                    eeg[i]=Integer.parseInt(eegvalue);
                                    i++;
                                    
                                } catch (Exception ex) {
                                }
                            }
                        }
                        mmainset.RecEEG.add(eeg.clone());   
                        timenum++;
                        //Log.i(TAG,"liuliangxiang timenum="+timenum);
                        if(timenum>(curmaxTime-curminTime))
                        {
                            is.close();
                            return;
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally
            {
                try {
                    if(is!=null)
                        is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            //temp=temp+1;
            
        }
        
    }
	public int getThingValue(String things) {
		if(things.equals("诱导 ")) {
			return 0;
		} else if(things.equals("插管 ")) {
			return 1;
		} else if(things.equals("维持 ")) {
			return 2;
		} else if(things.equals("外科 ")) {
			return 3;
		} else if(things.equals("注射 ")) {
			return 4;
		} else if(things.equals("备注 ")) {
			return 5;
		} else if(things.equals("维持结束 ")) {
			return 6;
		} else if(things.equals("移动 ")) {
			return 7;
		} else if(things.equals(" ")) {
			return 8;
		}
		return 8;
	}

	public String strFile = "";
	public void setListTime(final ArrayList<String> al)
	{
		strFile = "";
		ArrayList<String> modifyal = new ArrayList<String>();
		modifyal.clear();
		for(int i = 0; i <al.size();i++) {
			String str = al.get(i);
			String pstr=str.substring(0,str.lastIndexOf("---"));
			String estr=str.substring(str.lastIndexOf("---")+3,str.length());
		//	Log.i(TAG, "liuliangxiang pstr="+pstr);
		//	Log.i(TAG, "liuliangxiang estr="+estr);
			//int tScale=maxtimeList.get(i);
			//String estr=getDisplaytime(tScale);
			final StringTokenizer tok=new StringTokenizer(pstr,"-");
			int j=0;
			String a1 = "";
			String a2 = "";
			while (tok.hasMoreTokens()) {
				final String uid = tok.nextToken();
				if (!uid.equals("")) {
					j++;
					String a="-";
					if(j>=4)
						a=":";
					a1 = a1+uid+a;			
				}
			}
			a1 =a1.substring(0, a1.lastIndexOf(":"));
			final StringTokenizer tok2=new StringTokenizer(estr,"-");
			j=0;
			while (tok2.hasMoreTokens()) {
				final String uid = tok2.nextToken();
				if (!uid.equals("")) {
					j++;
					String a="-";
					if(j>=4)
						a=":";
					a2 = a2+uid+a;			
				}
			}
			a2 = a2.substring(0, a2.lastIndexOf(":"));
			modifyal.add(a1+"--"+a2);		
		}
		ArrayAdapter<String> av = new ArrayAdapter<String>(context, 
                android.R.layout.simple_dropdown_item_1line, modifyal); 
		timeList.setAdapter(av);
		timeList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub	
//				Log.i(TAG,"liuliangxiang timeFile="+timeFile.size());
//				Log.i(TAG,"liuliangxiang timeFile="+arg2);
//				Log.i(TAG,"liuliangxiang al="+al.size());
				strFile=(String) timeFile.get(arg2);
				//Log.i(TAG,"liuliangxiang strFile = "+ strFile);
				String  str=al.get(arg2);
				String stime=str.substring(0,str.lastIndexOf("---"));
				int timeScale=maxtimeList.get(arg2);
				String etime=getDisplaytime(timeScale);
				
				setStartTime("-"+stime);
				setEndTime("-"+etime);
				setMinTime(0);
				setMaxTime(timeScale);
				setCurMinTime(0);
				setCurMaxTime(timeScale);
			//	Log.i(TAG,"liuliangxiang maxTime="+maxTime);
			//	Log.i(TAG,"liuliangxiang minTime="+minTime);
				isSettime=true;
				starttimeText.setText(getDisplaytime2(minTime));
				mmainset.recstime=tmptime;
				endtimeText.setText(getDisplaytime2(maxTime));
				mmainset.recetime=tmptime;
				if (((ListView)arg0).getTag() != null){
        			((View)((ListView)arg0).getTag()).setBackgroundDrawable(null);
        		}
        		((ListView)arg0).setTag(arg1);
				arg1.setBackgroundColor(Color.BLUE);
			}});
	}
	
	public void setListName(final ArrayList<String> al)
	{
		ArrayAdapter<String> av = new ArrayAdapter<String>(context, 
                android.R.layout.simple_dropdown_item_1line, al); 
		
		ArrayList modifyal = new ArrayList();
		modifyal.clear();
		ArrayAdapter<String> av1 = new ArrayAdapter<String>(context, 
                android.R.layout.simple_dropdown_item_1line, modifyal); 
		timeList.setAdapter(av1);
		nameList.setAdapter(av);
		nameList.setOnItemClickListener(new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
			    String str = al.get(arg2);
			    Log.i(TAG, "liuliangxiang str="+str);
			    String str1 = str.substring(0,str.indexOf("--"));
			    String str2 = str.substring(str.indexOf("--")+2,str.length());
				ArrayList arrayList=GetTime(str1,str2);
				if (((ListView)arg0).getTag() != null){
        			((View)((ListView)arg0).getTag()).setBackgroundDrawable(null);
        		}
				setListTime(arrayList);		
				((ListView)arg0).setTag(arg1);
				//arg1.setBackgroundColor(Color.BLUE);
			}});
	}
//	public void setListNo(final ArrayList<String> al)
//	{
//		ArrayAdapter<String> av = new ArrayAdapter<String>(context, 
//                android.R.layout.simple_dropdown_item_1line, al); 
//		noList.setAdapter(av);
//		noList.setOnItemClickListener(new OnItemClickListener() {
//
//		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				// TODO Auto-generated method stub			
//			    notext.setText((String)(al.get(arg2)));	
//				ArrayList al=GetTime(notext.getText().toString());
//				setListTime(al);			
//			}});
//	}
	
	public ArrayList timeFile=new ArrayList<String>();
	public ArrayList<String> GetTime(String str1,String str2)
	{
		ArrayList al=new ArrayList<String>();
		String previewtime="";
		String previewetime="";
		String tempetime = "";
		String tempstime = "";
		File file=new File(Environment.getExternalStorageDirectory().toString()+"/csmdata");
	//	Log.i(TAG,"liuliangxiang file="+file.getAbsolutePath());
		maxtimeList.clear();
		if(file.isDirectory())
		{
			File listfile[]=file.listFiles();
			al.clear();
			timeFile.clear();
			nametimeList.clear();
			nametimeStrList.clear();
			for(int i=0;i<listfile.length;i++)
			{
				String tempstr1=listfile[i].toString();
				if(!(tempstr1.contains("print"))){
					//if((tempstr1.contains(str))&&(tempstr1.contains("print")))
					String name;
					String no;
					if((tempstr1.indexOf("(")>=0)&&(tempstr1.lastIndexOf(")")>0)){
						name=tempstr1.substring(tempstr1.indexOf("(")+1,tempstr1.lastIndexOf(")"));
					}else{
						name="";
					}
					if((tempstr1.indexOf("N")>=0)&&(tempstr1.lastIndexOf("N")>0)){
						no=tempstr1.substring(tempstr1.indexOf("N")+1,tempstr1.lastIndexOf("N"));
					}else{
						no="";
					}
					//String name=tempstr1.substring(tempstr1.indexOf("(")+1,tempstr1.lastIndexOf(")"));
					//Log.i(TAG, "liuliangxiang name="+name+" no="+no);
					if((no.equals(str1))&&(name.equals(str2)))
					{
					//	Log.i(TAG, "liuliangxiang tempstr1="+tempstr1);
						timeFile.add(tempstr1);
						tempetime=tempstr1.substring(tempstr1.lastIndexOf("-")+2, tempstr1.lastIndexOf("l"));
						tempstime=tempstr1.substring(tempstr1.lastIndexOf(")")+1,tempstr1.lastIndexOf("-"));
						nametimeList.add(Integer.parseInt(tempetime));
						nametimeStrList.add(tempstr1);
						if(previewtime.equals(""))
						{
							previewtime=tempstime;
							previewetime=tempetime;
						}
						else
						{
	//						if(!previewtime.equals(tempstime))
	//						{
	//							setStartTime("-"+previewtime);
	//							String time=getDisplaytime(Integer.parseInt(previewetime));
	//							maxtimeList.add((Integer)(Integer.parseInt(previewetime)));
	//							al.add(previewtime+"---"+time);
	//							previewtime=tempstime;
	//							previewetime=tempetime;													
	//						}
						}
						if((!tempstime.equals(""))&&(!tempetime.equals("")))
						{
							setStartTime("-"+tempstime);
							String time=getDisplaytime(Integer.parseInt(tempetime));
							al.add(tempstime+"---"+time);
							//Log.i(TAG, "liuliagnxiang time="+(tempstime+"---"+time));
							maxtimeList.add((Integer)(Integer.parseInt(tempetime)));
						}
					//	Log.i(TAG,"al.size="+al.size());
					//	Log.i(TAG,"timeFile.size="+timeFile.size());
					}
				}
				
			}
//			if((!tempstime.equals(""))&&(!tempetime.equals("")))
//			{
//				setStartTime("-"+tempstime);
//				String time=getDisplaytime(Integer.parseInt(tempetime));
//				al.add(tempstime+"---"+time);
//				maxtimeList.add((Integer)(Integer.parseInt(tempetime)));
//			}
		}
		return al;
	}
	
	public void setEndTime(String text)
	{
		//endtimeText.setText(text);
		final StringTokenizer tok=new StringTokenizer(text,"-");
		ArrayList<Integer> al=new ArrayList<Integer>();
		while (tok.hasMoreTokens()) {
			final String uid = tok.nextToken();
			if (!uid.equals("")) {
				try {
					al.add(Integer.parseInt(uid));
				} catch (Exception ex) {
				}
			}
		}
		eyearvalue=al.get(0);
		emonvalue=al.get(1);
		edayvalue=al.get(2);
		ehourvalue=al.get(3);
		eminvalue=al.get(4);
		esecvalue=al.get(5);
	}
	

	public waverecord(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		isSettime=false;
		this.context=context;
	}

	public waverecord(Context context, AttributeSet attrs) {
		super(context, attrs);
		isSettime=false;
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	
	protected void onFinishInflate()
	{
		super.onFinishInflate();
		minbtnup=(Button)findViewById(R.id.minuptime);
		minbtndn=(Button)findViewById(R.id.mindntime);
		minbtnup.setOnClickListener(this);
		minbtndn.setOnClickListener(this);
		maxbtnup=(Button)findViewById(R.id.maxuptime);
		maxbtndn=(Button)findViewById(R.id.maxdntime);
		maxbtnup.setOnClickListener(this);
		maxbtndn.setOnClickListener(this);
		starttimeText=(TextView)findViewById(R.id.starttimetext);
		endtimeText=(TextView)findViewById(R.id.endtimetext);
		nameList=(ListView)findViewById(R.id.nameList);
		timeList=(ListView)findViewById(R.id.timeList);	
		nametext=(EditText)findViewById(R.id.nametext);
		nametext.addTextChangedListener(namewatcher);
		notext = (EditText)findViewById(R.id.notext);
		notext.addTextChangedListener(nowatcher);
		noList = (ListView)findViewById(R.id.noList);
	}
	
	public Handler handler=new Handler();
	private TextWatcher namewatcher = new TextWatcher() {
	    
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	        // TODO Auto-generated method stub
	        
	    }
	    
	    public void beforeTextChanged(CharSequence s, int start, int count,
	            int after) {
	        // TODO Auto-generated method stub
	        
	    }
	    
	    public void afterTextChanged(Editable s) {
	        // TODO Auto-generated method stub
	    	watchNameText = s.toString();
	    	handler.post(new Runnable(){
				public void run() {
					// TODO Auto-generated method stub
					listItem.clear();
					for(int i=0;i<recordnoname.size();i++){	
						String str = recordnoname.get(i);
						if(str.contains(watchNoText)&&str.contains(watchNameText)){
							listItem.add(str);
						}
					}	
					setListName(listItem);
				}});
	        
	    }
	};
	
    private TextWatcher nowatcher = new TextWatcher() {
	    
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	        // TODO Auto-generated method stub
	        
	    }
	    
	    public void beforeTextChanged(CharSequence s, int start, int count,
	            int after) {
	        // TODO Auto-generated method stub
	        
	    }
	    
	    public void afterTextChanged(Editable s) {
	        // TODO Auto-generated method stub
	    	watchNoText = s.toString();
	    	handler.post(new Runnable(){
				public void run() {
					// TODO Auto-generated method stub
					listItem.clear();
					for(int i=0;i<recordnoname.size();i++){	
						String str = recordnoname.get(i);
						if(str.contains(watchNoText)&&str.contains(watchNameText)){
							listItem.add(str);
						}
					}	
					setListName(listItem);
				}});
	        
	    }
	};
	
	public int getDayofMonth(int year,int month)
	{
		switch(month)
		{
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 2:
				if(year%10==0)
				{
					if(year%40==0)
						return 29;
				}
				else
				{
					if(year%4==0)
						return 29;
				}
				return 28;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			default:
				return 30;
		}
	}
	
	public String getDisplaytime(int time)
	{
		int sec=ssecvalue+time;
		int min = sminvalue;
		int hour = shourvalue;
		int day= sdayvalue;
		int month= smonvalue;
		int year =syearvalue;
		if(sec>=60)
		{		
			min=min+sec/60;
			sec=sec%60;
			if(min>=60)
			{
				hour=hour+min/60;
				min=min%60;
				if(hour>=24)
				{
					day=day+hour/24;
					hour=hour%24;
					while(day>=getDayofMonth(year,month))
					{				
						day=day-getDayofMonth(year,month);
						month=month+1;
						if(month>12)
						{
							year++;
							month=month-12;
						}
							
					}
				}
				
			}
		}
		tmptime=int2time(year)+"."+int2time(month)+"."+int2time(day)+"  "+int2time(hour)+":"+Integer.toString(min)+":"+Integer.toString(sec);
		return int2time(year)+"-"+int2time(month)+"-"+int2time(day)+"-"+int2time(hour)+"-"+int2time(min)+"-"+int2time(sec);
		}
	
	public String getDisplaytime2(int time)
	{
		int sec=ssecvalue+time;
		int min = sminvalue;
		int hour = shourvalue;
		int day= sdayvalue;
		int month= smonvalue;
		int year =syearvalue;
		if(sec>=60)
		{		
			min=min+sec/60;
			sec=sec%60;
			if(min>=60)
			{
				hour=hour+min/60;
				min=min%60;
				if(hour>=24)
				{
					day=hour+hour/24;
					hour=hour%24;
					while(day>=getDayofMonth(year,month))
					{				
						day=day-getDayofMonth(year,month);
						month=month+1;
						if(month>12)
						{
							year++;
							month=month-12;
						}
							
					}
				}
				
			}
		}
		//tmptime=int2time(year)+"/"+int2time(month)+"/"+int2time(day)+" "+int2time(hour)+":"+Integer.toString(min)+":"+Integer.toString(sec);
		tmptime=int2time(year)+"/"+int2time(month)+"/"+int2time(day)+" "+int2time(hour)+":"+int2time(min);
		return int2time(year)+"-"+int2time(month)+"-"+int2time(day)+"-"+int2time(hour)+":"+int2time(min)+":"+int2time(sec);
		}
	
	private String int2time(int time)
	{
		if(time>=10)
		{
			return Integer.toString(time);
		}
		else
		{
			return "0"+Integer.toString(time);
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.equals(minbtnup))
		{
			if(curminTime<(curmaxTime-60))
				curminTime=curminTime+60;
			String str=getDisplaytime2(curminTime);
			mmainset.recstime=tmptime;
			starttimeText.setText(str);			
		}
		else if(v.equals(minbtndn))
		{
			curminTime=curminTime-60;
			if(minTime > curminTime){
				curminTime = minTime;
			}
			String str=getDisplaytime2(curminTime);
			mmainset.recstime=tmptime;
			starttimeText.setText(str);	
		}
		else if(v.equals(maxbtnup))
		{
//			if(curmaxTime<=(maxTime-60))
//				curmaxTime=curmaxTime+60;
			curmaxTime=curmaxTime+60;
			if(curmaxTime > maxTime)
				curmaxTime = maxTime;
			String str=getDisplaytime2(curmaxTime);
			mmainset.recetime=tmptime;
			endtimeText.setText(str);			
		}
		else if(v.equals(maxbtndn))
		{
			if(curmaxTime>=(curminTime+60))
			{
				curmaxTime=curmaxTime-60;
			}
			String str=getDisplaytime2(curmaxTime);
			mmainset.recetime=tmptime;
			endtimeText.setText(str);	
		}
//		if(v.equals(btndn))
//		{
//			
//			if(esecvalue==0)
//			{
//				esecvalue=59;
//				if(eminvalue==0)
//				{
//					eminvalue=59;
//					if(ehourvalue==0)
//					{
//						ehourvalue=23;
//					}
//					else
//					{
//						ehourvalue--;
//					}
//				}
//				else
//				{
//					eminvalue--;
//				}
//			}
//			else
//			{			
//				esecvalue--;
//			}
//			if((esecvalue==ssecvalue)&&(eminvalue==sminvalue)&&(ehourvalue==shourvalue)&&(edayvalue==sdayvalue)&&(emonvalue==smonvalue)&&(eyearvalue==syearvalue))
//			{
//				return;
//			}
//			else
//			{
//				String str=Integer.toString(eyearvalue)+"-"+Integer.toString(emonvalue)+"-"+Integer.toString(edayvalue)+"-"+Integer.toString(ehourvalue)+"-"+Integer.toString(eminvalue)+"-"+Integer.toString(esecvalue);
//				endtimeText.setText(str);
//			}
//		}
//		if(v.equals(btnup))
//		{
//			ssecvalue++;
//			if(ssecvalue==60)
//			{
//				ssecvalue=0;
//				sminvalue++;
//				if(sminvalue==60)
//				{
//					sminvalue=0;
//					shourvalue++;
//					if(shourvalue==24)
//					{
//						shourvalue=0;
//						sdayvalue++;
//					}
//				}	
//			}
//			
//			if((esecvalue==ssecvalue)&&(eminvalue==sminvalue)&&(ehourvalue==shourvalue)&&(edayvalue==sdayvalue)&&(emonvalue==smonvalue)&&(eyearvalue==syearvalue))
//			{
//				return;
//			}
//			else
//			{
//				String str=Integer.toString(eyearvalue)+"-"+Integer.toString(emonvalue)+"-"+Integer.toString(edayvalue)+"-"+Integer.toString(ehourvalue)+"-"+Integer.toString(eminvalue)+"-"+Integer.toString(esecvalue);
//				endtimeText.setText(str);
//			}
//		}
		
	}
	

}
