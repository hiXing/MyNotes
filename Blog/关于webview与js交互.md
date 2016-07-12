
webview与js交互需要添加
	``` webview.getSetting().setJavaScriptEnable(true);```
方法调用：
	``` webView.addJavascriptInterface(new Object(), "call");```
	

* html调用java代码 
	报错1：`` E/Web Console: Uncaught TypeError: Object [object Object] has no method 'toString' ``
	
	在Android4.2以后的版本需要在调用的方法前加注释语句 ``  @JavascriptInterface ``
	
	```
		class Object {  
			@JavascriptInterface  
			public String toString() { return "callObject"; }			
		}  
	```
	
	报错2：`` E/Web Console(19235): Uncaught Error: Error calling method on NPObject. at http://192.168.1.11/mobile/..........js:428 ``
	
	大多是Android线程安全的问题,创建子线程进行操作,于是代码改成了下面这样问题就解决了:
	
	```
		new Thread(new Runnable(){
			@Override
			public void run(){
				//code
			}
		
		}).start();
		
		//或者
		Handler handler = new Handler();
		handler.post(new Runnable(){
			@Override
			public void run(){
				//code
			}
		});
	```


* JAVA和JS交互注意事项

	1、Java 调用 js 里面的函数、效率并不是很高、估计要200ms左右吧、做交互性很强的事情、这种速度很难让人接受、
	而js去调Java的方法、速度很快、50ms左右、所以尽量用js调用Java方法
	2、Java 调用 js 的函数、没有返回值、调用了就控制不到了
	3、Js 调用 Java 的方法、返回值如果是字符串、你会发现这个字符串是 native 的、转成 locale 的才能正常使用、使用 toLocaleString() 函数就可以了、不过这个函数的速度并不快、转化的字符串如果很多、将会很耗费时间
	4、网页中尽量不要使用jQuery、执行起来需要5-6秒、最好使用原生的js写业务脚本、以提升加载速度、改善用户体验	

	
参考实例链接：http://blog.csdn.net/ithomer/article/details/8737999