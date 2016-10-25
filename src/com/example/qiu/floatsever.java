package com.example.qiu;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class floatsever extends Service {
	private Timer timer;
	 private Handler handler = new Handler();  
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		
		return null;
	}
	 public int onStartCommand(Intent intent, int flags, int startId){
		 if(timer==null){
			 Log.i("qcxx", "ydj");
			 timer=new Timer();
			 timer.scheduleAtFixedRate(new task1(), 0, 500);
		 }
		 return super.onStartCommand(intent, flags, startId);
	 }


	 public class task1 extends TimerTask {

	 	@Override
	 	public void run() {
	 		// TODO Auto-generated method stub
	 		if(isHome() && !MyWindowManager.isWindowShowing()){
	 			 handler.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						MyWindowManager.creatfloat(getApplicationContext());
						Log.i("lkk", "ssb");
					} });}
					else if(!isHome() && MyWindowManager.isWindowShowing()){
						handler.post(new Runnable(){

							@Override
							public void run() {
								// TODO Auto-generated method stub
								MyWindowManager.removefloat(getApplicationContext());
								MyWindowManager.removebig(getApplicationContext());
							}
							
						});
					}
					else if(isHome()&&MyWindowManager.isWindowShowing()){
						handler.post(new Runnable(){

							@Override
							public void run() {
								// TODO Auto-generated method stub
								MyWindowManager.upget(getApplicationContext());
							}
							
						});
					}
	 	
	 }}
	 private boolean isHome(){
		 	ActivityManager activitymanager=(ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		 	List<RunningTaskInfo> rti = activitymanager.getRunningTasks(1); 
		 	return getHomes().contains(rti.get(0).topActivity.getPackageName());  
		 	}
private List<String> getHomes(){
	PackageManager packmanager=this.getPackageManager();
	List<String> names=new ArrayList<String>();
	Intent intent = new Intent(Intent.ACTION_MAIN);  
    intent.addCategory(Intent.CATEGORY_HOME);  
    List<ResolveInfo> resolveInfo = packmanager.queryIntentActivities(intent,  
            PackageManager.MATCH_DEFAULT_ONLY);  
    for (ResolveInfo ri : resolveInfo) {  
        names.add(ri.activityInfo.packageName);  
    }  
    return names;  
}

}
