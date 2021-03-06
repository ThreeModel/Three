# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#XSnow
-dontwarn com.vise.utils.**
-keep class com.vise.xsnow.event.inner.ThreadMode { *; }
-keep class com.vise.xsnow.http.api.ApiService { *; }
-keep class com.vise.xsnow.http.mode.CacheMode
-keep class com.vise.xsnow.http.mode.CacheResult { *; }
-keep class com.vise.xsnow.http.mode.DownProgress { *; }
-keep class com.vise.xsnow.http.strategy.**
-keepclassmembers class * {
    @com.vise.xsnow.event.Subscribe <methods>;
}
-keep class com.bumptech.glide.Glide

#netexpand
-keep class com.vise.netexpand.mode.ApiResult { *; }
-keep class com.example.asus.threemodel.view.activity{*;}