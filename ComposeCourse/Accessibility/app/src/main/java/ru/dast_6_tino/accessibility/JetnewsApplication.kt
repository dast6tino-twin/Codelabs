package ru.dast_6_tino.accessibility

import android.app.Application
import ru.dast_6_tino.accessibility.data.AppContainer
import ru.dast_6_tino.accessibility.data.AppContainerImpl

class JetnewsApplication : Application() {

    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }

}
