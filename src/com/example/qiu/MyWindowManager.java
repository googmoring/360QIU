package com.example.qiu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class MyWindowManager  {
	 private static LayoutParams smallWindowParams;  
	 private static LayoutParams bigWindowParams;
	 private static floatview small;
	 private static bigview big;
	 private static WindowManager windowManager;
	 private static ActivityManager activitymanager;
	public static void creatfloat(Context context){
		 windowManager = getWindowManager(context); 
		int screenWidth = windowManager.getDefaultDisplay().getWidth();  
        int screenHeight = windowManager.getDefaultDisplay().getHeight();  
        if(small==null){
		small=new floatview(context);
		 if(smallWindowParams==null){
		 smallWindowParams = new LayoutParams();  
         smallWindowParams.type = LayoutParams.TYPE_PHONE;  
         smallWindowParams.format = PixelFormat.RGBA_8888;  
         smallWindowParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL  
                 | LayoutParams.FLAG_NOT_FOCUSABLE;  
         smallWindowParams.gravity = Gravity.LEFT | Gravity.TOP;  
         smallWindowParams.width = (int)floatview.xview;  
         smallWindowParams.height =(int)floatview.yview;  
         smallWindowParams.x = screenWidth;  
         smallWindowParams.y = screenHeight / 2; }}
        small.setparam(smallWindowParams);
        windowManager.addView(small, smallWindowParams);
		
	}
	public static void creatbig(Context context){
		WindowManager windowManager = getWindowManager(context);  
        int screenWidth = windowManager.getDefaultDisplay().getWidth();  
        int screenHeight = windowManager.getDefaultDisplay().getHeight();  
        if (big == null) {  
            big = new bigview(context);  
            if (bigWindowParams == null) {  
                bigWindowParams = new LayoutParams();  
                bigWindowParams.x = screenWidth / 2 - bigview.xview / 2;  
                bigWindowParams.y = screenHeight / 2 - bigview.yview / 2;  
                bigWindowParams.type = LayoutParams.TYPE_PHONE;  
                bigWindowParams.format = PixelFormat.RGBA_8888;  
                bigWindowParams.gravity = Gravity.LEFT | Gravity.TOP;  
                bigWindowParams.width = bigview.xview;
                bigWindowParams.height = bigview.yview;  
            }  
            windowManager.addView(big, bigWindowParams);  
        }  
	}
	public static void removefloat(Context context){
		WindowManager windowManager = getWindowManager(context);  
		if(small!=null){
			windowManager.removeView(small);
			small=null;
		}
		
	}
	public static void removebig(Context context){
		WindowManager windowManager = getWindowManager(context);  
		if(big!=null){
			windowManager.removeView(big);
			big=null;
		}
		
	}
	public static WindowManager getWindowManager(Context context){
		WindowManager mm=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		return mm;
	}
	public static boolean isWindowShowing() {  
        return small != null || big != null;  
    }  
	public static void upget(Context context) {  
        if (small != null) {  
            TextView percentView = (TextView) small.findViewById(R.id.small);  
            percentView.setText(getUsedPercentValue(context));
           
        }  
    }
	public static String getUsedPercentValue(Context context) {  
        String dir = "/proc/meminfo";  
        try {  
            FileReader fr = new FileReader(dir);  
            BufferedReader br = new BufferedReader(fr, 2048);  
            String memoryLine = br.readLine();  
            String subMemoryLine = memoryLine.substring(memoryLine.indexOf("MemTotal:"));  
            br.close();  
            long totalMemorySize = Integer.parseInt(subMemoryLine.replaceAll("\\D+", ""));  
            long availableSize = getAvailableMemory(context) / 1024;  
            int percent = (int) ((totalMemorySize - availableSize) / (float) totalMemorySize * 100);  
            return percent + "%";  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return "Ðü¸¡´°";  
    }  
	private static long getAvailableMemory(Context context) {  
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();  
        getActivityManager(context).getMemoryInfo(mi);  
        return mi.availMem;  
    }  
	private static ActivityManager getActivityManager(Context context) {  
        if (activitymanager == null) {  
            activitymanager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);  
        }  
        return activitymanager;  
    }  
}
