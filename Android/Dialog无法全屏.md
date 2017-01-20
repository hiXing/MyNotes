 android dialog无法全屏
---

我自定义了一个LodingDialog 继承自 AlertDialog，inflate 了一个自定义的xml布局，可是怎么都不能全屏。
查阅网上资料，有好多种办法，但都不好使啊，愁。。。啊

* 1.添加自定义的样式：

```
	<style name="LoadingDialog"  parent="android:Theme.Dialog">
        <item name="android:windowFrame">@null</item>
        <item name="android:windowNoTitle">true</item>
        <item name="android:background">@android:color/white</item>
        <item name="android:windowBackground">@android:color/white</item><!--设置背景-->
        <item name="android:windowIsFloating">true</item>
        <item name="android:windowContentOverlay">@null</item>
    </style>
```

* 2.代码中设置
```
	Window dialogWindow = getWindow();
        if (dialogWindow==null)return;
        dialogWindow.requestFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        lp.alpha = 0.9f;
        lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND|WindowManager.LayoutParams.FLAG_FULLSCREEN;
        lp.dimAmount = 0.0f;
        lp.gravity = Gravity.CENTER;
        lp.windowAnimations = android.R.style.Animation_InputMethod;
        dialogWindow.setAttributes(lp);
        dialogWindow.setBackgroundDrawableResource(android.R.color.white);//设置dialog背景
		dialogWindow.setDimAmount(0);//设置dialog之外的背景,0或者1
      

```
然而都设置了并没有使dialog全屏，四周还是有个边距。。。。

* 3.设置padding
```
Window window = getWindow();
window.getDecorView().setPadding(0,0,0,0);
```

还是不管用啊，

* 4.还有一种偏方(不懂咋来的)
```
Window window = getWindow();
window.getDecorView().setMinimumWidth(10000);
```

* 5.把xml填充到DecorView的content
```
ViewGroup rootView = null;
        if (getWindow()!=null){
            rootView = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        }
        View view = getLayoutInflater().inflate(R.layout.progress_dialog,rootView,false);
        setContentView(view);
```
虽然是全屏了，设置背景透明度为
最后，
继承AlertDialog的还是没有解决不能全屏问题  ╮(╯▽╰)╭

