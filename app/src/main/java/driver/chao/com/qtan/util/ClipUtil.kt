package driver.chao.com.qtan.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

/**
 * Created by aaron on 2018/9/18.
 */
fun clipDoc(context: Context, result: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText(null, result)
    clipboard.primaryClip = clipData
}