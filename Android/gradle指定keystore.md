app.gradle

```
def releaseTime() {
    return new Date().format("yyyyMMdd", TimeZone.getTimeZone("UTC"))
}

android {
    compileSdkVersion Integer.parseInt(project.ANDROID_BUILD_SDK_VERSION)
    buildToolsVersion project.ANDROID_BUILD_TOOLS_VERSION
    defaultConfig {
        applicationId "com.baochunsteel.app"
        minSdkVersion 16
        targetSdkVersion Integer.parseInt(project.ANDROID_BUILD_TARGET_SDK_VERSION)
        versionCode 1
        versionName "1.0.0"
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            //apk命名
            android.applicationVariants.all { variant ->
                variant.outputs.each { output ->
                    def outputFile = output.outputFile
                    if (outputFile != null && outputFile.name.endsWith('.apk')) {
                        //这里修改apk文件名
                        def fileName = "BCNews${defaultConfig.versionName}_${releaseTime()}.apk"
                        output.outputFile = new File(outputFile.parent, fileName)
                    }
                }
            }
        }
        debug {
            minifyEnabled true
            shrinkResources false
            signingConfig signingConfigs.debug
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    signingConfigs {
        debug {
            storeFile file('BCNews.keystore')
            storePassword "bcapps"
            keyAlias "bc"
            keyPassword "bcapps"
        }
    }
}

```