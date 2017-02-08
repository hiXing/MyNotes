[前人已栽树](https://github.com/mzlogin/awesome-adb)

* 测量app启动时间：
冷启动 ：在进程不存在的情况下，从点击应用 Icon 到用户能看到界面所占用的时间。 
热启动 ：在进程依然存在的情况下，点击应用 Icon 到用户能看到对应的界面所用的时间。
```
adb shell am start -W [packageName]/[activityName]
```

如果执行成功，会得到三个时间：ThisTime、TotalTime、WaitTime
* WaitTime：是 startActivityAndWait 这个方法的调用耗时
* ThisTime：是指调用过程中最后一个 Activity 启动时间到这个 Activity 的 startActivityAndWait 调用结束
* TotalTime 是指调用过程中第一个 Activity 的启动时间到最后一个 Activity 的 startActivityAndWait 结束
> 如果过程中只有一个 Activity ，则 TotalTime 等于 ThisTime


* 命令行查看签名的SHA1值

	```
	keytool -list -v -keystore <keystore文件名>
	```

默认的debug签名：
> debug.keystore：Android自动生成的一个用于测试的keystore
> 
> 位置：C:\Users\<用户名>\.android\debug.keystore
> 
> 密码：android
