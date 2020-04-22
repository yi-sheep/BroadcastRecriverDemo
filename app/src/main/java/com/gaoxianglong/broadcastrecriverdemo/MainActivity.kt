package com.gaoxianglong.broadcastrecriverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

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

        button.setOnClickListener {
            val intent = Intent("com.gaoxianglong.MY_BROADCAST") // 实例化一个意图带着广播信息
            intent.setPackage(packageName) // 携带应用的包名 指定发个哪一个应用
//            sendBroadcast(intent) // 发送广播,发送的是标准广播
            sendOrderedBroadcast(intent,null) // 发送的是有序广播
        }
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
