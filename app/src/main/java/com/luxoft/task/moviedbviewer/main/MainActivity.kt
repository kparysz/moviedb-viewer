package com.luxoft.task.moviedbviewer.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import com.luxoft.task.moviedbviewer.R
import com.luxoft.task.moviedbviewer.nowplaying.NowPlayingMoviesFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installGooglePlayServicesProvider(this)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, NowPlayingMoviesFragment.newInstance(), "fragmentTag")
            .disallowAddToBackStack()
            .commit()
    }

    private fun installGooglePlayServicesProvider(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            try {
                ProviderInstaller.installIfNeeded(context)
            } catch (e: GooglePlayServicesRepairableException) {
                GoogleApiAvailability.getInstance()
                    .showErrorNotification(context, e.connectionStatusCode)
            } catch (e: GooglePlayServicesNotAvailableException) {
            }
        }
    }
}