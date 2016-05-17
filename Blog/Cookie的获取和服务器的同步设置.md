					Cookie的获取和服务器的同步设置
一、http对Cookie的读取和服务器同步
1、服务器提交sessionId和服务器状态保持一致
	当Android应用程序访问WEB服务器的时候，我们为了与服务器保持同一会话，也就是说当前登录用户与服务器的交互是在同SessionId下。当我们登录成功的时候，可以通过HTTP请求获取到Cookie信息，其中包括会话的SessionId，同时也可以自己将SessionId放入Json中返回。Session我们可以用一个静态变量来存放，每次向服务器发送请求的时候将SessionId带过去，服务器会自动检验这个SessionId有没有失效。
	DefaultHttpClient httpclient = new DefaultHttpClient();
	HttpPost httpPost = new HttpPost(访问地址);
	httpPost.setHeader("Cookie", "JSESSIONID=" + value我们在静态变量里存放的SessionId);
	HttpResponse httpResponse = httpclient.execute(httpPost);
	这样就可以将SessionId带过去了。
备注：其中httpPost.setHeader("Cookie", "JSESSIONID=" + 我们在静态变量里存放的SessionId);其中的JSESSIONID是下面获取的	      cookieName也就是你要操作的cookie名称，value即是对应的cookie值
2、获取cookie信息
	DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("");
        List<Cookie> alCookies = httpClient.getCookieStore().getCookies();
        if(!alCookies.isEmpty())
        {
        	for(int i =0;i<alCookies.size();i++)
            {
            	Cookie cookie = alCookies.get(i);
            	Log.i("tag", "==========================Name: "+cookie.getName());
            	Log.i("tag", "==========================value: "+cookie.getValue());
            	Log.i("tag", "==========================path: "+cookie.getPath());
            	Log.i("tag", "==========================domain: "+cookie.getDomain());
            	Log.i("tag", "==========================date: "+cookie.getExpiryDate());
            }
        }
        try {
		httpClient.execute(httpPost);
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
二、WebView对cookie的同步操作
	1、创建cookie对象（一般在：onCreate()调用）
		CookieSyncManager cookieManager = CookieSyncManager.createInstance(this); //cookie管理
		//开始和webView同步
		cookieManager.startSync();
	2.进行同步操作（一般在：onResume()和onPause()）
		cookieManager.sync();
		cookieManager.stopSync();