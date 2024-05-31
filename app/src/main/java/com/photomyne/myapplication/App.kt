package com.photomyne.myapplication

import android.content.ContentResolver
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        ContentResolver.setMasterSyncAutomatically(true)
        instance = this
    }

    companion object {
        @JvmStatic
        lateinit var instance: App
    }
}