# BroadcastBecriverDemo

这一期是广播接收器的用法，动态/静态接收系统广播，发送自己的广播，有序广播的截断。

动态注册：必须要做应用启动的时候才能接收
```kotlin
class MainActivity : AppCompatActivity() {
    lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter() // 实例化一个意图过滤器
        // 系统每隔一分钟就会发送一条这样的广播
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver() // 实例化一个广播接收器
        // 注册广播接收器，这是动态注册，必须在启动应用后才能接收广播，静态注册就可以不用启动应用就能接收
        // 但是现在的Android版本的一些隐式广播，不允许通过静态注册
        registerReceiver(timeChangeReceiver,intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        // 在应用退出是需要取消注册
        unregisterReceiver(timeChangeReceiver)
    }
    /**
     * 广播接收器
     */
    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // 这个方法在接收到广播时调用
            Toast.makeText(context,"Time has changed",Toast.LENGTH_SHORT).show()
        }
    }
}
```

静态注册：在配置文件中进行,所以在应用没有启动的时候也可以接收广播
首先要通过选择表名右键New -> Other -> Broadcast Receiver来创建一个广播接收器
```xml
<!-- 开机启动 -->
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
<receiver
    android:name=".BootCompleteReceiver"
    android:enabled="true"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED" />
    </intent-filter>
</receiver>
```

发送自己的广播

```kotlin
val intent = Intent("com.gaoxianglong.MY_BROADCAST") // 实例化一个意图带着广播信息
intent.setPackage(packageName) // 携带应用的包名 指定发个哪一个应用
//sendBroadcast(intent) // 发送广播,发送的是标准广播
sendOrderedBroadcast(intent,null) // 发送的是有序广播
```

如果发送的是一条有序广播，就可以在广播接收器中进行截断

```kotlin
override fun onReceive(context: Context, intent: Intent) {
    Toast.makeText(context,"这是有序广播",Toast.LENGTH_SHORT).show()
    abortBroadcast() // 阻止这条广播继续向后传递
}
```