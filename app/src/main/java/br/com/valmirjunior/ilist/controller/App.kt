package br.com.valmirjunior.ilist.controller

import android.app.Application
import androidx.room.Room
import br.com.valmirjunior.ilist.model.dao.AppDB
import java.util.*

class App : Application() {

    val currentLocale: Locale
        get() = resources.configuration.locale

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        DB = Room.databaseBuilder(applicationContext, AppDB::class.java, AppDB.NAME)
                .fallbackToDestructiveMigration().build()
    }

    companion object {
        lateinit var INSTANCE: App
        lateinit var DB: AppDB
    }
}