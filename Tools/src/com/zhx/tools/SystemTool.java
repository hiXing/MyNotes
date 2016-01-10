package com.zhx.tools;

import android.app.ActivityManager;
import android.content.Context;

public class SystemTool {
	/**
	 * 获取进程名
	 * @param context
	 */
	public static String getProcessName(Context context){
		String currentProcess = "";
		int pid = android.os.Process.myPid();
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
			if(processInfo.pid == pid){
				currentProcess = processInfo.processName;
				break;
			}
		}
		return currentProcess;
	}
	/**
	 * 判断是否为主继承,对比进程名是否和包名相同即可
	 * 
	 * 判断为某个进程，在mainifest这样这样声明
	 * 	<service android:name=".DroidService" android:process=":service"></service>
	 * 其对应的完整进程名为com.droidyue.avoidforceclosedemo:service，我们判断可以使用如下代码
		"com.droidyue.avoidforceclosedemo:service".equals(processName);
	 * @param context
	 * @param processName
	 * @return
	 */
	public static boolean isMainProcess(Context context, String processName){
		String packageName = context.getPackageName();
		if(packageName.equals(processName)){
			return true;
		}
		else return false;
	}
}
