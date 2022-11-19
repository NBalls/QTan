package driver.chao.com.qtan

import android.app.Application
import android.os.StrictMode
import driver.chao.com.qtan.video.SPUtil

/**
 * Created by aaron on 2018/9/18.
 */
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // 解决高版本图片保存问题
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        builder.detectFileUriExposure()

        SPUtil.init(this)
    }
}