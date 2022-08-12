package com.hero.ataa.data.local.repositories

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.hero.ataa.system.alarms.AlarmReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

const val NOTIFICATION_ALARM_ACTION = "notification_alarm_action"

class AlarmsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun setUpAlarm() {
        val intent = Intent(context.applicationContext, AlarmReceiver::class.java).apply {
            action = NOTIFICATION_ALARM_ACTION
        }
        val alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.timeInMillis, AlarmManager.INTERVAL_HOUR, alarmIntent)
    }
}