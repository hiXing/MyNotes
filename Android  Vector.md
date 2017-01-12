摘自徐宜生：[Android Vector曲折的兼容之路](http://blog.csdn.net/eclipsexys/article/details/51838119)

### 1.Vector语法简介

Android以一种简化的方式对SVG进行了兼容，这种方式就是通过使用它的Path标签，通过Path标签，几乎可以实现SVG中的其它所有标签，虽然可能会复杂一点，但这些东西都是可以通过工具来完成的，所以，不用担心写起来会很复杂。

一个基本的Vector图像，实际上也是一个xml文件，如下所示：
```
<vector xmlns:android="http://schemas.android.com/apk/res/android"
        android:width="200dp"
        android:height="200dp"
        android:viewportHeight="500"
        android:viewportWidth="500">

    <path
        android:name="square"
        android:fillColor="#000000"
        android:pathData="M100,100 L400,100 L400,400 L100,400 z"/>

</vector>
```


Path指令解析如下所示：

* 支持的指令：
  
  * M = moveto(M X,Y) ：将画笔移动到指定的坐标位置
  
  * L = lineto(L X,Y) ：画直线到指定的坐标位置
  
  * H = horizontal lineto(H X)：画水平线到指定的X坐标位置
  
  * V = vertical lineto(V Y)：画垂直线到指定的Y坐标位置
  
  * C = curveto(C X1,Y1,X2,Y2,ENDX,ENDY)：三次贝赛曲线
  
  * S = smooth curveto(S X2,Y2,ENDX,ENDY)
  
  * Q = quadratic Belzier curve(Q X,Y,ENDX,ENDY)：二次贝赛曲线
  
  * T = smooth quadratic Belzier curveto(T ENDX,ENDY)：映射
  
  * A = elliptical Arc(A RX,RY,XROTATION,FLAG1,FLAG2,X,Y)：弧线
  
  * Z = closepath()：关闭路径


* 使用原则:

  * 坐标轴为以(0,0)为中心，X轴水平向右，Y轴水平向下
  * 所有指令大小写均可。大写绝对定位，参照全局坐标系；小写相对定位，参照父容器坐标系
  * 指令和数据间的空格可以省略
  * 同一指令出现多次可以只用一个
  
> 注意，’M’处理时，只是移动了画笔， 没有画任何东西。 它也可以在后面给出上同时绘制不连续线。



### 2.在项目中使用

首先，你需要在项目的build.gradle脚本中，增加对Vector兼容性的支持，代码如下所示：

使用Gradle Plugin 2.0以上：
```
android {

    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }
}
```

使用Gradle Plugin 2.0以下，Gradle Plugin 1.5以上：
```
android {
  defaultConfig {
    // Stops the Gradle plugin’s automatic rasterization of vectors
    generatedDensities = []
  }
  // Flag to tell aapt to keep the attribute ids around
  aaptOptions {
    additionalParameters "--no-version-vectors"
  }
}
```

要兼容普通控件还需要在Activity的前面添加下面代码开启flag：
```
static {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
}
```


