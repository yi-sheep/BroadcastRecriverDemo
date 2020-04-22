package com.gaoxianglong.broadcastrecriverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AnotherReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"这是有序广播",Toast.LENGTH_SHORT).show()
        abortBroadcast() // 阻止这条广播继续向后传递
    }
}
