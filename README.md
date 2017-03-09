# CourtCounter 积分APP
##需求
效果图如下

![](https://github.com/youchangxu1205/CourtCounter/blob/master/png/QQ%E6%88%AA%E5%9B%BE20170308162716.png)
##分析
根据图片可以分为两个区域,上下区域

上区域可以分为两个小区域,左右区域
	
	每个区域内的View是竖向排列的

下区域只有一个按钮reset

因此布局代码如下

	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical"
	    tools:context="com.example.android.courtcounter.MainActivity">
	
	    <LinearLayout
	        android:layout_width="match_parent"
	        android:layout_height="0dp"
	        android:layout_weight="0.64"
	        android:orientation="horizontal">
	
	        <LinearLayout
	            android:layout_width="0dp"
	            android:layout_height="wrap_content"
	            android:layout_weight="1"
	            android:orientation="vertical">
	
	            <TextView
	                android:layout_width="match_parent"
	                android:layout_height="wrap_content"
	                android:gravity="center"
	                android:padding="16dp"
	                android:text="Team A" />
	
	            <TextView
	                android:id="@+id/tv_team_a_score"
	                android:layout_width="match_parent"
	                android:layout_height="wrap_content"
	                android:gravity="center"
	                android:text="0"
	                android:textColor="@android:color/black"
	                android:textSize="48sp" />
	
	            <Button
	                android:id="@+id/btn_three_score_a"
	                android:layout_width="120dp"
	                android:layout_height="wrap_content"
	                android:layout_gravity="center"
	                android:layout_marginTop="16dp"
	                android:background="#F39128"
	                android:text="+3 Point" />
	
	            <Button
	                android:id="@+id/btn_two_score_a"
	                android:layout_width="120dp"
	                android:layout_height="wrap_content"
	                android:layout_gravity="center"
	                android:layout_marginTop="16dp"
	                android:background="#F39128"
	                android:text="+2 Point" />
	
	            <Button
	                android:id="@+id/btn_free_throw_a"
	                android:layout_width="120dp"
	                android:layout_height="wrap_content"
	                android:layout_gravity="center"
	                android:layout_marginTop="16dp"
	                android:background="#F39128"
	                android:text="FREE THROW" />
	        </LinearLayout>
	
	        <TextView
	            android:layout_width="1px"
	            android:layout_height="match_parent"
	            android:layout_margin="16dp"
	            android:background="@android:color/black" />
	
	        <LinearLayout
	            android:layout_width="0dp"
	            android:layout_height="wrap_content"
	            android:layout_weight="1"
	            android:orientation="vertical">
	
	            <TextView
	                android:layout_width="match_parent"
	                android:layout_height="wrap_content"
	                android:gravity="center"
	                android:padding="16dp"
	                android:text="Team B" />
	
	            <TextView
	                android:id="@+id/tv_team_b_score"
	                android:layout_width="match_parent"
	                android:layout_height="wrap_content"
	                android:gravity="center"
	                android:text="0"
	                android:textColor="@android:color/black"
	                android:textSize="48sp" />
	
	            <Button
	                android:id="@+id/btn_three_score_b"
	                android:layout_width="120dp"
	                android:layout_height="wrap_content"
	                android:layout_gravity="center"
	                android:layout_marginTop="16dp"
	                android:background="#F39128"
	                android:text="+3 Point" />
	
	            <Button
	                android:id="@+id/btn_two_score_b"
	                android:layout_width="120dp"
	                android:layout_height="wrap_content"
	                android:layout_gravity="center"
	                android:layout_marginTop="16dp"
	                android:background="#F39128"
	                android:text="+2 Point" />
	
	            <Button
	                android:id="@+id/btn_free_throw_b"
	                android:layout_width="120dp"
	                android:layout_height="wrap_content"
	                android:layout_gravity="center"
	                android:layout_marginTop="16dp"
	                android:background="#F39128"
	                android:text="FREE THROW" />
	        </LinearLayout>
	    </LinearLayout>
	
	    <LinearLayout
	        android:layout_width="match_parent"
	        android:layout_height="0dp"
	        android:layout_weight="0.3"
	        android:gravity="center"
	        android:orientation="vertical">
	
	        <Button
	            android:id="@+id/btn_reset"
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content"
	            android:background="#F39128"
	            android:text="reset" />
	    </LinearLayout>
	
	</LinearLayout>

编写MainAcitivity代码

通过findViewById获取所有的控件

利用setOnClickListener为按钮设置监听

添加addScore(int score,int team)方法,为各队加分,如果team为0则为Team A加分 score 为分值

并将结果显示到屏幕上

添加reset()方法,初始化分值,重置为0

添加end()方法,判断那一队获得胜利,如果分值相同,则进入加时赛

具体代码如下

	package com.example.android.courtcounter;
	
	import android.os.Bundle;
	import android.support.v7.app.AppCompatActivity;
	import android.view.View;
	import android.widget.Button;
	import android.widget.TextView;
	import android.widget.Toast;
	
	public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	
	    private TextView teamAScoreTv;
	    private Button teamAThreeScoreBtn;
	    private Button teamATwoScoreBtn;
	    private Button teamAFreeThrowBtn;
	
	    private TextView teamBScoreTv;
	    private Button teamBThreeScoreBtn;
	    private Button teamBTwoScoreBtn;
	    private Button teamBFreeThrowBtn;
	
	    private Button resetBtn;
	    private Button endBtn;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	
	        teamAScoreTv = (TextView) findViewById(R.id.tv_team_a_score);
	        teamAThreeScoreBtn = (Button) findViewById(R.id.btn_three_score_a);
	        teamATwoScoreBtn = (Button) findViewById(R.id.btn_two_score_a);
	        teamAFreeThrowBtn = (Button) findViewById(R.id.btn_free_throw_a);
	        teamBScoreTv = (TextView) findViewById(R.id.tv_team_b_score);
	        teamBThreeScoreBtn = (Button) findViewById(R.id.btn_three_score_b);
	        teamBTwoScoreBtn = (Button) findViewById(R.id.btn_two_score_b);
	        teamBFreeThrowBtn = (Button) findViewById(R.id.btn_free_throw_b);
	        resetBtn = (Button) findViewById(R.id.btn_reset);
	        endBtn = (Button) findViewById(R.id.btn_end);
	        init();
	    }
	
	    /**
	     * 初始化两队的分数
	     */
	    private int teamAScore = 0;
	    private int teamBScore = 0;
	
	    public void init() {
	        teamAScore = 0;
	        teamBScore = 0;
	        teamAThreeScoreBtn.setOnClickListener(this);
	        teamATwoScoreBtn.setOnClickListener(this);
	        teamAFreeThrowBtn.setOnClickListener(this);
	        teamBThreeScoreBtn.setOnClickListener(this);
	        teamBTwoScoreBtn.setOnClickListener(this);
	        teamBFreeThrowBtn.setOnClickListener(this);
	        resetBtn.setOnClickListener(this);
	        endBtn.setOnClickListener(this);
	    }
	
	    private static int TEAM_A = 0;
	    private static int TEAM_B = 1;
	
	    @Override
	    public void onClick(View view) {
	        int id = view.getId();
	        switch (id) {
	            case R.id.btn_three_score_a:
	                addScore(3, TEAM_A);
	                break;
	            case R.id.btn_two_score_a:
	                addScore(2, TEAM_A);
	                break;
	            case R.id.btn_free_throw_a:
	                addScore(1, TEAM_A);
	                break;
	            case R.id.btn_three_score_b:
	                addScore(3, TEAM_B);
	                break;
	            case R.id.btn_two_score_b:
	                addScore(2, TEAM_B);
	                break;
	            case R.id.btn_free_throw_b:
	                addScore(1, TEAM_B);
	                break;
	            case R.id.btn_reset:
	                reset();
	                break;
	            case R.id.btn_end:
	                end();
	                break;
	
	        }
	    }
	
	    private void end() {
	        if (teamAScore > teamBScore) {
	            Toast.makeText(this, "Team A Win", Toast.LENGTH_SHORT).show();
	        } else if (teamAScore < teamBScore) {
	            Toast.makeText(this, "Team B Win", Toast.LENGTH_SHORT).show();
	        } else {
	            Toast.makeText(this, "进入加时赛", Toast.LENGTH_SHORT).show();
	        }
	    }
	
	    private void reset() {
	        teamAScore = 0;
	        teamBScore = 0;
	        teamAScoreTv.setText(String.valueOf(teamAScore));
	        teamBScoreTv.setText(String.valueOf(teamBScore));
	    }
	
	    private void addScore(int i, int team) {
	        if (team == TEAM_A) {
	            teamAScore += i;
	            teamAScoreTv.setText(String.valueOf(teamAScore));
	        } else {
	            teamBScore += i;
	            teamBScoreTv.setText(String.valueOf(teamBScore));
	        }
	
	
	    }
	}


#编写代码时遇到的问题

1.	遇到空指针的问题

		E/AndroidRuntime: FATAL EXCEPTION: main
		Process: com.example.android.courtcounter, PID: 17814
	    java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.TextView.setText(java.lang.CharSequence)' on a null object reference
	    at com.example.android.courtcounter.MainActivity.addScore(MainActivity.java:116)
		at com.example.android.courtcounter.MainActivity.onClick(MainActivity.java:69)
	    at android.view.View.performClick(View.java:5198)
	    at android.view.View$PerformClick.run(View.java:21147)
	    at android.os.Handler.handleCallback(Handler.java:739)
	    at android.os.Handler.dispatchMessage(Handler.java:95)
	    at android.os.Looper.loop(Looper.java:148)
		at android.app.ActivityThread.main(ActivityThread.java:5417)
		at java.lang.reflect.Method.invoke(Native Method)
		at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:726)
		at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:616)
	原因:少写了一个findViewById,
	
	解决办法:找到空指针的地方,查看到底是啥原因导致的,然后初始化
2.	setText遇到的问题
	
	
	
		03-08 09:21:10.753 21145-21145/com.example.android.courtcounter E/AndroidRuntime: FATAL EXCEPTION: main
	                                                                                  Process: com.example.android.courtcounter, PID: 21145
	                                                                                  android.content.res.Resources$NotFoundException: String resource ID #0x3
	                                                                                      at android.content.res.Resources.getText(Resources.java:312)
	                                                                                      at android.widget.TextView.setText(TextView.java:4417)
	                                                                                      at com.example.android.courtcounter.MainActivity.addScore(MainActivity.java:117)
	                                                                                      at com.example.android.courtcounter.MainActivity.onClick(MainActivity.java:70)
	                                                                                      at android.view.View.performClick(View.java:5198)
	                                                                                      at android.view.View$PerformClick.run(View.java:21147)
	                                                                                      at android.os.Handler.handleCallback(Handler.java:739)
	                                                                                      at android.os.Handler.dispatchMessage(Handler.java:95)
	                                                                                      at android.os.Looper.loop(Looper.java:148)
	                                                                                      at android.app.ActivityThread.main(ActivityThread.java:5417)
	                                                                                      at java.lang.reflect.Method.invoke(Native Method)
	                                                                                      at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:726)
	                                                                                      at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:616)

	原因:setText只接受字符串,在显示分值的时候如果没有转换成字符串
	
	解决办法:利用String.valueof()方法将整形转为字符串,或者直接拼接字符串


#git地址

https://github.com/youchangxu1205/CourtCounter


有啥问题可以私信我哦!
学习资源来自[StudyJams](http://www.studyjamscn.com),面向零基础学员,大家可以一起加入哦!!
