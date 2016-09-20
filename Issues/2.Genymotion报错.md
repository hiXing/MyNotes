
## Genymotion使用报错
	使用VirtualBox 安装虚拟机的时候，有提示Unable to load R3 module C:\Program Files\Oracle\VirtualBox/VBoxDD.DLL (VBoxDD): GetLastError=1790 (VERR_UNRESOLVED_ERROR).的错误，
	不能为虚拟电脑 windows 2003 server 打开一个新任务.
	Unable to load R3 module C:\Program Files\Oracle\VirtualBox/VBoxDD.DLL (VBoxDD): GetLastError=1790 (VERR_UNRESOLVED_ERROR).
	返回 代码:  E_FAIL   (0x80004005)
	组件:  Console
	界面:  IConsole   {8ab7c520-2442-4b66-8d74-4ff1e195d2b6}
原来是因为破解主题所导致的，可能你没有破解，但是你可能安装的操作系统本来就已经破解了。 所以除非你重新安装操作系统，要么就得使用下面的步骤进行操作了。

-	1.找到 C:/windows/system32 目录 下面的uxtheme.dll文件<记住此文件,就是这个文件导致的>
-	2.查看下你的操作系统类型，是32位的还是64位的。.然后去其它的电脑上面将上面的C:\Windows\system32\uxtheme.dll文件拷贝过来，直接覆盖上面的文件或者去网上下载一个这个文件。进行替换。如果提示失败先将原来的uxtheme.dll文件换个名称，然后再将新的此文件拷贝进来。这样就可以了。
有时提示正在使用不能覆盖，就得停用主题：
 Win+R 打开 运行窗口 ，输入 services.mcs 打开服务列表，找到一个叫 Theme 的 服务，右键点击停用，之后整个系统的主题会停用，然后再替换文件，
 这里有三个和主题相关的文件：themeservice.dll ， themeui.dll ， uxtheme.dll 最好是都进行替换

 ----------------
 
## Genymotion 运行提示找不到 **VBoxNetAdp.sys**
-	1.以兼容模式运行Genymoion和VirtualBox（没管用）
-	2.更改VirtualBox中的模拟器的网络配置：
	原因：模拟器网络的IP必须是192.168.56.X才能正确运行，这点很重要，这与你自身的电脑IP没有任何关系。
安装模拟器的时候他会默认安装虚拟的网卡适配器，一般叫做VirtualBox Host-Only Ethernet Adapter，所以你出现上述问题之1的话就需要检查你的这个IP配置。
	解决方案：
    *    第一步，打开网络共享中心，查看你VirtualBox Host-Only Ethernet Adapter的IP是多少？这里需要手动设置，IP为：192.168.56.X  子网掩码是：255.255.155.0。
	*	第二步：打开VirtualBox，选择一个模拟器点击设置，选择网络，进行网卡1和网卡2的配置:
	>网卡1的连接方式为 **仅主机（Host-only）适配器**；
	>网卡2的连接方式为 **网络地址转换（NAT）**
	*	第三步：同样在VirtualBox中选择你要配置的模拟器，点击管理，选择全局设定，再选择网络，跳转Host-only Networks标签在VirtualBox Host-Only Ethernet Adapter上点击右键选择编辑主机网络，进行配置，配置成同一网段
-	3.直接在指定文件夹下copy *(/VirtualBox/VBoxNetAdp.sys)*

----------------
