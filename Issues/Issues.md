##Issue1 : android studio 导入工程时报错
![index](/ScreenShot/20151223151437.png)
解决方案：
	



##Issue2 : Genymotion使用报错
	使用VirtualBox 安装虚拟机的时候，有提示Unable to load R3 module C:\Program Files\Oracle\VirtualBox/VBoxDD.DLL (VBoxDD): GetLastError=1790 (VERR_UNRESOLVED_ERROR).的错误，
	不能为虚拟电脑 windows 2003 server 打开一个新任务.
	Unable to load R3 module C:\Program Files\Oracle\VirtualBox/VBoxDD.DLL (VBoxDD): GetLastError=1790 (VERR_UNRESOLVED_ERROR).
	返回 代码:  E_FAIL   (0x80004005)
	组件:  Console
	界面:  IConsole   {8ab7c520-2442-4b66-8d74-4ff1e195d2b6}
原来是因为破解主题所导致的，可能你没有破解，但是你可能安装的操作系统本来就已经破解了。 所以除非你重新安装操作系统，要么就得使用下面的步骤进行操作了。
1.找到 C:/windows/system32 目录 下面的uxtheme.dll文件<记住此文件,就是这个文件导致的>
2.查看下你的操作系统类型，是32位的还是64位的。.然后去其它的电脑上面将上面的C:\Windows\system32\uxtheme.dll文件拷贝过来，直接覆盖上面的文件或者去网上下载一个这个文件。进行替换。如果提示失败先将原来的uxtheme.dll文件换个名称，然后再将新的此文件拷贝进来。这样就可以了。
有时提示正在使用不能覆盖，就得停用主题：
 Win+R 打开 运行窗口 ，输入 services.mcs 打开服务列表，找到一个叫 Theme 的 服务，右键点击停用，之后整个系统的主题会停用，然后再替换文件，
 这里有三个和主题相关的文件：themeservice.dll ， themeui.dll ， uxtheme.dll 最好是都进行替换
