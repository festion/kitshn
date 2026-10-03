package de.kitshn.crash

import androidx.compose.runtime.Composable

// festion fork: ACRA is not initialised (see AndroidApp.attachBaseContext), so
// there is no crash report handler. Returning null hides the "send crash
// report" buttons, exactly as on desktop (CrashReporting.jvm.kt).
@Composable
actual fun crashReportHandler(): ((error: Throwable?) -> Unit)? = null
