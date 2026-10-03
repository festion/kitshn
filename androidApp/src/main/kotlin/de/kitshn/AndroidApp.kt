package de.kitshn

import android.app.Application
import android.content.Context
import de.kitshn.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class AndroidApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // festion fork: ACRA crash reporting is disabled. Upstream's init sends
        // reports to upstream's collector (acra.kitshn.app), which should not
        // receive crashes from our modified builds. Without init, ACRA's
        // ErrorReporter is a stub that ignores calls (breadcrumbs included).
    }

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@AndroidApp)
        }
    }

}