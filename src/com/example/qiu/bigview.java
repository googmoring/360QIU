package com.example.qiu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class bigview extends LinearLayout {
private Button destory;
private Button back;
public static int xview;  
public static int yview;  
	public bigview( final Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.big,null);
		View view = findViewById(R.id.layout); 
		xview=view.getWidth();
		yview=view.getHeight();
		destory=(Button)findViewById(R.id.destory);
		back=(Button)findViewById(R.id.back);
		destory.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyWindowManager.removefloat(context);
				MyWindowManager.removebig(context);
				Intent intent=new Intent(getContext(),floatsever.class);
				context.stopService(intent);
			}
			
		});
		back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyWindowManager.removebig(context);
				MyWindowManager.creatfloat(context);
			}
			
		});
	} 

}
