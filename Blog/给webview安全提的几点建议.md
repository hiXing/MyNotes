0X06安全开发建议
 
1)建议开发者通过以下方式移除该JavaScript接口：
  removeJavascriptInterface("searchBoxJavaBridge_")
  removeJavascriptInterface("accessibility")；
  removeJavascriptInterface("accessibilityTraversal")
2)出于安全考虑，为了防止Java层的函数被随便调用，Google在4.2版本之后,规定允许被调用的函数必须以@JavascriptInterface进行注解
3)通过WebSettings.setSavePassword(false)关闭密码保存提醒功能
4)通过以下设置，防止越权访问，跨域等安全问题： 
  setAllowFileAccess(false)
  setAllowFileAccessFromFileURLs(false)
  setAllowUniversalAccessFromFileURLs(false)
  
  
0X07参考信息
 
https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2012-6636
https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2013-4710
https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2014-1939
https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2014-7224
http://drops.wooyun.org/webview.html