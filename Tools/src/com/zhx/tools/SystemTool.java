package com.zhx.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.NetworkInfo.State;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class SystemTool {
	private static final String TAG = SystemTool.class.getSimpleName();
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
	
	/**
     * 指定格式返回当前系统时间
     */
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format,Locale.US);
        return df.format(new Date());
    }

    /**
     * 返回当前系统时间(格式以HH:mm形式)
     */
    public static String getDataTime() {
        return getDataTime("HH:mm");
    }

    /**
     * 获取手机IMEI码
     */
    public static String getPhoneIMEI(Context cxt) {
        TelephonyManager tm = (TelephonyManager) cxt
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }
    /**
     * 获取java生成的唯一标识
     * @return
     */
    public static String getUUID() {
        return  UUID.randomUUID().toString();
    }
    public static String getDeviceId(Context context){
    	TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    	return tm.getDeviceId();
    	
    }
    /**
     * @TODO 获取手机设备信息
     * @Date 2015年11月2日
     * @param context
     * @return
     */
    public static String getDeviceInfo(Context context){
    	TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);  
        StringBuilder sb = new StringBuilder();  
        sb.append("\nDeviceId(IMEI) = " + tm.getDeviceId());  
        sb.append("\nDeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion());  
        sb.append("\nLine1Number = " + tm.getLine1Number());  
        sb.append("\nNetworkCountryIso = " + tm.getNetworkCountryIso());  
        sb.append("\nNetworkOperator = " + tm.getNetworkOperator());  
        sb.append("\nNetworkOperatorName = " + tm.getNetworkOperatorName());  
        sb.append("\nNetworkType = " + tm.getNetworkType());  
        sb.append("\nPhoneType = " + tm.getPhoneType());  
        sb.append("\nSimCountryIso = " + tm.getSimCountryIso());  
        sb.append("\nSimOperator = " + tm.getSimOperator());  
        sb.append("\nSimOperatorName = " + tm.getSimOperatorName());  
        sb.append("\nSimSerialNumber = " + tm.getSimSerialNumber());  
        sb.append("\nSimState = " + tm.getSimState());  
        sb.append("\nSubscriberId(IMSI) = " + tm.getSubscriberId());  
        sb.append("\nVoiceMailNumber = " + tm.getVoiceMailNumber());  
        return sb.toString();   
    }
    /**
     * 获取手机系统SDK版本
     * 
     * @return 如API 17 则返回 17
     */
    public static int getSDKVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }
   
    /**
     * 获取系统版本
     * 
     * @return 形如2.3.3
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 调用系统发送短信
     */
    public static void sendSMS(Context cxt, String smsBody) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", smsBody);
        cxt.startActivity(intent);
    }
    /**
     * 调用系统拨打电话
     */
    public static void callPhone(Context cxt, String number) {
    	Intent intent = new Intent(Intent.ACTION_DIAL);
    	Uri data = Uri.parse("tel:" + number);
    	intent.setData(data);
    	cxt.startActivity(intent);
    }
    /**
     * @TODO 测试网络是否联通
     * @Date 2015年11月19日
     * @return
     */
    public static boolean isConnect(){  
        boolean connect = false;  
        Runtime runtime = Runtime.getRuntime();  
        Process process;  
        try {  
            process = runtime.exec("ping " + "www.baidu.com");  
            InputStream is = process.getInputStream();   
            InputStreamReader isr = new InputStreamReader(is);   
            BufferedReader br = new BufferedReader(isr);   
            String line = null;   
            StringBuffer sb = new StringBuffer();   
            while ((line = br.readLine()) != null) {   
                sb.append(line);   
            }   
            System.out.println("返回值为:"+sb);    
            is.close();   
            isr.close();   
            br.close();   
   
            if (null != sb && !sb.toString().equals("")) {   
                if (sb.toString().indexOf("TTL") > 0) {   
                    // 网络畅通    
                    connect = true;  
                } else {   
                    // 网络不畅通    
                    connect = false;  
                }   
            }   
        } catch (IOException e) {  
            e.printStackTrace();  
        }   
        return connect;  
    }  
    /**
     * 判断网络是否连接
     */
    public static boolean checkNet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null;// 网络是否连接
    }

    /**
     * 仅wifi联网功能是否开启
     */
//    public static boolean checkOnlyWifi(Context context) {
//        if (PreferenceHelper.readBoolean(context, KJConfig.SETTING_FILE,
//                KJConfig.ONLY_WIFI)) {
//            return isWiFi(context);
//        } else {
//            return true;
//        }
//    }

    /**
     * 判断是否为wifi联网
     */
    public static boolean isWiFiConnected(Context cxt) {
        ConnectivityManager cm = (ConnectivityManager) cxt
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // wifi的状态：ConnectivityManager.TYPE_WIFI
        // 3G的状态：ConnectivityManager.TYPE_MOBILE
        State state = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        return State.CONNECTED == state;
    }

	/**
	 * 判断手机网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}
	/**
	 * 调用系统安装了的应用分享
	 * @param context
	 * @param title
	 * @param url
	 */
	public static void showShareMore(Activity context,final String title,final String url)
	{
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "分享：" + title);
		intent.putExtra(Intent.EXTRA_TEXT, title + " " +url);
		context.startActivity(Intent.createChooser(intent, "选择分享"));
	}
	/**
     * 创建快捷方式
     * 
     * @param cxt
     *            Context
     * @param icon
     *            快捷方式图标
     * @param title
     *            快捷方式标题
     * @param cls
     *            要启动的类
     */
    public static void createDeskShortCut(Context cxt, int icon, String title,
            Class<?> cls) throws Exception{
        // 创建快捷方式的Intent
        Intent shortcutIntent = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        // 不允许重复创建
        shortcutIntent.putExtra("duplicate", false);
        // 需要现实的名称
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        // 快捷图片
        Parcelable ico = Intent.ShortcutIconResource.fromContext(
                cxt.getApplicationContext(), icon);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, ico);
        Intent intent = new Intent(cxt, cls);
        // 下面两个属性是为了当应用程序卸载时桌面上的快捷方式会删除
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
//        Intent installer = new Intent();  
//        installer.putExtra("duplicate", false);  
//      installer.putExtra("android.intent.extra.shortcut.INTENT", installer);  
//      installer.putExtra("android.intent.extra.shortcut.NAME", "XXXX");  
//      installer.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(contxt, R.drawable.icon));  
//      installer.setAction("com.android.launcher.action.INSTALL_SHORTCUT");  
        // 点击快捷图片，运行的程序主入口
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        // 发送广播。OK
        cxt.sendBroadcast(shortcutIntent);
    }

    /**
     * 隐藏系统键盘
     * 
     * <br>
     * <b>警告</b> 必须是确定键盘显示时才能调用
     */
    public static void hideKeyBoard(Activity aty) {
        ((InputMethodManager) aty
                .getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(
                        aty.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断当前应用程序是否后台运行
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    // 后台运行
                    return true;
                } else {
                    // 前台运行
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 判断手机是否处理睡眠
     */
    public static boolean isSleeping(Context context) {
        KeyguardManager kgMgr = (KeyguardManager) context
                .getSystemService(Context.KEYGUARD_SERVICE);
        boolean isSleeping = kgMgr.inKeyguardRestrictedInputMode();
        return isSleeping;
    }

    /**
     * 安装apk
     * 
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("application/vnd.android.package-archive");
        intent.setData(Uri.fromFile(file));
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 获取当前应用程序的版本号
     */
    public static String getAppVersionName(Context context) {
        String version = "0";
        try {
            version = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(SystemTool.class.getName()
                    + "the application not found");
        }
        return version;
    }

    /**
     * 获取当前应用程序的版本号
     */
    public static int getAppVersionCode(Context context) {
        int version = 0;
        try {
            version = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(SystemTool.class.getName()
                    + "the application not found");
        }
        return version;
    }
    /**
   	 * 获取App安装包信息
   	 * @return
   	 */
       public static PackageInfo getPackageInfo(Context context) {
   		PackageInfo info = null;
   		try { 
   			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
   		} catch (NameNotFoundException e) {    
   			e.printStackTrace(System.err);
   		} 
   		if(info == null) info = new PackageInfo();
   		return info;
   	}
    /**
     * 回到home，后台运行
     */
    public static void goHome(Context context) {
        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(mHomeIntent);
    }

    /**
     * 获取应用签名
     * 
     * @param context
     * @param pkgName
     */
    public static String getSign(Context context, String pkgName) {
        try {
            PackageInfo pis = context.getPackageManager().getPackageInfo(
                    pkgName, PackageManager.GET_SIGNATURES);
            return hexdigest(pis.signatures[0].toByteArray());
        } catch (NameNotFoundException e) {
            throw new RuntimeException(SystemTool.class.getName() + "the "
                    + pkgName + "'s application not found");
        }
    }

    /**
     * 将签名字符串转换成需要的32位签名
     */
    private static String hexdigest(byte[] paramArrayOfByte) {
        final char[] hexDigits = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97,
                98, 99, 100, 101, 102 };
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            char[] arrayOfChar = new char[32];
            for (int i = 0, j = 0;; i++, j++) {
                if (i >= 16) {
                    return new String(arrayOfChar);
                }
                int k = arrayOfByte[i];
                arrayOfChar[j] = hexDigits[(0xF & k >>> 4)];
                arrayOfChar[++j] = hexDigits[(k & 0xF)];
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获取设备的可用内存大小
     * 
     * @param cxt
     *            应用上下文对象context
     * @return 当前内存大小
     */
    public static int getDeviceUsableMemory(Context cxt) {
        ActivityManager am = (ActivityManager) cxt
                .getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        // 返回当前系统的可用内存
        return (int) (mi.availMem / (1024 * 1024));
    }

    /**
     * 清理后台进程与服务
     * 
     * @param cxt
     *            应用上下文对象context
     * @return 被清理的数量
     */
    public static int gc(Context cxt) {
        long i = getDeviceUsableMemory(cxt);
        int count = 0; // 清理掉的进程数
        ActivityManager am = (ActivityManager) cxt
                .getSystemService(Context.ACTIVITY_SERVICE);
        // 获取正在运行的service列表
        List<RunningServiceInfo> serviceList = am.getRunningServices(100);
        if (serviceList != null)
            for (RunningServiceInfo service : serviceList) {
                if (service.pid == android.os.Process.myPid())
                    continue;
                try {
                    android.os.Process.killProcess(service.pid);
                    count++;
                } catch (Exception e) {
                    e.getStackTrace();
                    continue;
                }
            }

        // 获取正在运行的进程列表
        List<RunningAppProcessInfo> processList = am.getRunningAppProcesses();
        if (processList != null)
            for (RunningAppProcessInfo process : processList) {
                // 一般数值大于RunningAppProcessInfo.IMPORTANCE_SERVICE的进程都长时间没用或者空进程了
                // 一般数值大于RunningAppProcessInfo.IMPORTANCE_VISIBLE的进程都是非可见进程，也就是在后台运行着
                if (process.importance > RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                    // pkgList 得到该进程下运行的包名
                    String[] pkgList = process.pkgList;
                    for (String pkgName : pkgList) {
                        Log.d(TAG,"======正在杀死包名：" + pkgName);
                        try {
                            am.killBackgroundProcesses(pkgName);
                            count++;
                        } catch (Exception e) { // 防止意外发生
                            e.getStackTrace();
                            continue;
                        }
                    }
                }
            }
        Log.d(TAG,"清理了" + (getDeviceUsableMemory(cxt) - i) + "M内存");
        return count;
    }
}
