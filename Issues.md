##	1 : android studio 导入工程时报错
![index](/ScreenShot/20151223151437.png)
>解决方案：
    
-----
##	2: 编译时报错
![index](/ScreenShot/20160111171956.png)

![index](/ScreenShot/20160113082630.png)

>原因：
	项目中jar包冲突、引用第三方类库导致jar冲突

>解决方案：
	在 Module 的build.gradle 中添加 `exclude`

	dependencies {
    	compile fileTree(include: ['*.jar'], dir: 'libs')
   		testCompile 'junit:junit:4.12'
    	compile 'com.android.support:appcompat-v7:23.0.1'
    	compile 'com.android.support:design:23.0.1'
    	
    	compile (':library_ViewpagerIndicator')
	}
	
--	对比

    dependencies {
    	compile fileTree(include: ['*.jar'], dir: 'libs')
   		testCompile 'junit:junit:4.12'
    	compile 'com.android.support:appcompat-v7:23.0.1'
    	compile 'com.android.support:design:23.0.1'
    	//去除lib引用重复的jar包
    	compile (project(':library_ViewpagerIndicator')){
        	exclude group: 'com.android.support', module: 'support-v4'
    	}
	}
	
----------------

##	4.Android Studio项目 编译运行报错
Error:Execution failed for task ':app:transformResourcesWithMergeJavaResForDebug'.
> com.android.build.api.transform.TransformException: com.android.builder.packaging.DuplicateFileException: Duplicate files copied in APK META-INF/LICENSE.txt
	File1: D:\BaoChunApp\BaoChunApp\app\libs\fastjson-1.2.6.jar
	File2: D:\BaoChunApp\BaoChunApp\app\libs\httpmime-4.1.3.jar
	
![index](/ScreenShot/20160205083112.png)
解决办法：
	在module的build.gradle文件中加入代码
	
	android{
		
		packagingOptions {  
			exclude 'META-INF/DEPENDENCIES.txt' 
			exclude 'META-INF/DEPENDENCIES'			
			exclude 'META-INF/LICENSE.txt' 
			exclude 'META-INF/LICENSE'
			exclude 'META-INF/NOTICE.txt'  
			exclude 'META-INF/NOTICE'  
			exclude 'META-INF/notice.txt'  
			exclude 'META-INF/license.txt'  
			exclude 'META-INF/dependencies.txt'  
			exclude 'META-INF/LGPL2.1'  
		}  
	}
	
  
  还可以试一试
  
	android{
		packagingOptions {
	    exclude 'META-INF/maven/com.alibaba.fastjson/pom.xml'
	    exclude 'META-INF/maven/com.alibaba.fastjson/pom.properties'
		}
	}