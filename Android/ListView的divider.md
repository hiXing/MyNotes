### ListView的divider

1. 定制divider的边距
	ListView的divider默认是左右两头到底的，如何简单的设置一个边距呢？
	新建 ` drawables `文件  `list_divider.xml`

	* 方法一:
	```
	<?xml version="1.0" encoding="utf-8"?>
	<inset xmlns:android="http://schemas.android.com/apk/res/android"
		android:insetLeft="16dp" >
		<shape android:shape="rectangle" >
			<solid android:color="#f00" />
		</shape>
	</inset>
	```
	* 方法二:
	```
	<?xml version="1.0" encoding="utf-8"?>
	<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
		<item android:left="16dp">
			<shape android:shape="rectangle">
				<solid android:color="#f00" />
			</shape>
		</item>
	</layer-list>
   ```

在xml中使用：
 >android:divider="@drawable/list_divider"  
 >android:dividerHeight="1dp"

