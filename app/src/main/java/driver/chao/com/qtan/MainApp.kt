package driver.chao.com.qtan

import android.app.Application
import android.os.StrictMode

/**
 * Created by aaron on 2018/9/18.
 */
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        builder.detectFileUriExposure()
    }
}