/** 
* deviceID的组成为：渠道标志+识别符来源标志+hash后的终端识别符 
*  
* 渠道标志为： 
* 1，andriod（a） 
* 
* 识别符来源标志： 
* 1， wifi mac地址（wifi）； 
* 2， IMEI（imei）； 
* 3， 序列号（sn）； 
* 4， id：随机码。若前面的都取不到时，则随机生成一个随机码，需要缓存。 
* 
* @param context 
* @return 
*/ 
public static String getDeviceId(Context context) { 

	StringBuilder deviceId = new StringBuilder(); 
	// 渠道标志 
	deviceId.append("id"); 

	try { 

	//wifi mac地址 
	WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE); 
	WifiInfo info = wifi.getConnectionInfo(); 
	String wifiMac = info.getMacAddress(); 
	if(!isEmpty(wifiMac)){ 
	deviceId.append("wifi"); 
	deviceId.append(wifiMac); 
	Log.e("getDeviceId : ", deviceId.toString()); 
	return deviceId.toString(); 
	} 

	//IMEI（imei） 
	TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE); 
	String imei = tm.getDeviceId(); 
	if(!isEmpty(imei)){ 
	deviceId.append("imei"); 
	deviceId.append(imei); 
	Log.e("getDeviceId : ", deviceId.toString()); 
	return deviceId.toString(); 
	} 

	//序列号（sn） 
	String sn = tm.getSimSerialNumber(); 
	if(!isEmpty(sn)){ 
	deviceId.append("sn"); 
	deviceId.append(sn); 
	Log.e("getDeviceId : ", deviceId.toString()); 
	return deviceId.toString(); 
	} 

	//如果上面都没有， 则生成一个id：随机码 
	String uuid = getUUID(context); 
	if(!isEmpty(uuid)){ 
		deviceId.append("id"); 
		deviceId.append(uuid); 
		Log.e("getDeviceId : ", deviceId.toString()); 
		return deviceId.toString(); 
		} 
	} 	catch (Exception e) { 
	e.printStackTrace(); 
	deviceId.append("id").append(getUUID(context)); 
	} 

	Log.e("getDeviceId : ", deviceId.toString()); 

	return deviceId.toString(); 
}


       /** 
* 得到全局唯一UUID 
*/ 
public static String getUUID(Context context){ 
	SharedPreferences mShare = getSysShare(context, "sysCacheMap"); 
	if(mShare != null){ 
	uuid = mShare.getString("uuid", ""); 
	} 

	if(isEmpty(uuid)){ 
	uuid = UUID.randomUUID().toString(); 
	saveSysMap(context, "sysCacheMap", "uuid", uuid); 
	} 

	Log.e(tag, "getUUID : " + uuid); 
	return uuid; 
} 
private String getMyUUID(){
 
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(this.TELEPHONY_SERVICE);   
 
        final String tmDevice, tmSerial, tmPhone, androidId;   
 
        tmDevice = "" + tm.getDeviceId();  
 
        tmSerial = "" + tm.getSimSerialNumber();   
 
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);   
 
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());   
 
        String uniqueId = deviceUuid.toString();
 
        Log.d("debug","uuid="+uniqueId);
 
        return uniqueId;
 
       }