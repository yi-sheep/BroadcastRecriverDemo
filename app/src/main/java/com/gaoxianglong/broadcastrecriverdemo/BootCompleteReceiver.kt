package com.gaoxianglong.broadcastrecriverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * 广播接收器
 * 静态注册在配置文件中完成
 */
class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"Boot Complete",Toast.LENGTH_SHORT).show()
    }
}
