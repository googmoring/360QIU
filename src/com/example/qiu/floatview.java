package com.example.qiu;



import java.lang.reflect.Field;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class floatview extends LinearLayout {
private float downx;
private float downy;
private float onx;
private float ony;
private int cdy;
public static float xview;
public static float yview;
private WindowManager windowmanager;
private TextView tv;
private  WindowManager.LayoutParams mparams ; 
	public floatview(final Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.small,this);
		 tv=(TextView)findViewById(R.id.small);
		windowmanager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE); 
		 
		tv.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
				downx=event.getRawX();
				downy=event.getRawY()-cdy;
				onx=event.getRawX();
				ony=event.getRawY()-cdy;
				xview=event.getX();
				yview=event.getY()-cdy;
				break;
				case MotionEvent.ACTION_MOVE:
					onx=event.getRawX();
					ony=event.getRawY()-cdy;
					upget();
					break;
				case MotionEvent.ACTION_UP:
					onx=event.getRawX();
					ony=event.getRawY()-cdy;
					if(onx==downx&&ony==downy){
						openbig(context);
					}
					break;
				}
				return true;
			}});
		
		
	}
public void setparam(WindowManager.LayoutParams params){
	this.mparams=params;
}
public void upget(){

	mparams.x=(int)(onx-xview);
    mparams.y = (int) (ony - yview); 
    windowmanager.updateViewLayout(this, mparams);
	
}
public void openbig(Context context){
	MyWindowManager.removefloat(context);
	MyWindowManager.creatbig(context);
}
private int getStatusBarHeight() {  
    if (cdy == 0) {  
        try {  
            Class<?> c = Class.forName("com.android.internal.R$dimen");  
            Object o = c.newInstance();  
           Field field = c.getField("status_bar_height");  
            int x = (Integer) field.get(o);  
            cdy = getResources().getDimensionPixelSize(x);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    return cdy;  
}  
}
