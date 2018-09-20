package driver.chao.com.qtan.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import driver.chao.com.qtan.MainActivity
import driver.chao.com.qtan.R
import driver.chao.com.qtan.bean.RBean
import driver.chao.com.qtan.parse.ParseClass
import org.jsoup.Jsoup
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by aaron on 2018/9/20.
 */

class WebLayout : LinearLayout {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    lateinit var webView: WebView
    lateinit var webView2: WebView
    lateinit var webView3: WebView
    var webLayoutListener: WebLayoutListener?= null

    private fun init() {
        val rootViews = LayoutInflater.from(context).inflate(R.layout.web_layout, null, false)
        this.addView(rootViews)

        webView = rootViews.findViewById(R.id.webView)
        webView2 = rootViews.findViewById(R.id.webView2)
        webView3 = rootViews.findViewById(R.id.webView3)

        webView.settings.javaScriptEnabled = true
        webView2.settings.javaScriptEnabled = true
        webView3.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(InJavaScriptLocalObj(), "java_obj")
        webView2.addJavascriptInterface(InJavaScriptLocalObj2(), "java_obj")
        webView3.addJavascriptInterface(InJavaScriptLocalObj3(), "java_obj")
        webView.webViewClient = CWebViewClient()
        webView2.webViewClient = CWebViewClient2()
        webView3.webViewClient = CWebViewClient3()
    }

    class CWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            view?.postDelayed({
                try {
                    view.loadUrl("javascript:window.java_obj.showSource("
                            + "document.getElementById('table_live').outerHTML);")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, 0)

            super.onPageFinished(view, url)
        }
    }

    class CWebViewClient2 : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            view?.postDelayed({
                try {
                    view.loadUrl("javascript:window.java_obj.showSource("
                            + "document.getElementById('table_live').outerHTML);")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, 0)

            super.onPageFinished(view, url)
        }
    }

    class CWebViewClient3 : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            view?.postDelayed({
                try {
                    view.loadUrl("javascript:window.java_obj.showSource("
                            + "document.getElementById('table_live').outerHTML);")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, 0)

            super.onPageFinished(view, url)
        }
    }

    inner class InJavaScriptLocalObj {

        @JavascriptInterface
        fun showSource(html: String) {
            val htmlStr = MainActivity.DEFAULT_HTML_PRE + html + MainActivity.DEFAULT_HTML_LAST
            val doc = Jsoup.parse(htmlStr)
            ParseClass.parseMainData(doc, 1)
        }
    }

    inner class InJavaScriptLocalObj2 {

        @JavascriptInterface
        fun showSource(html: String) {
            Observable.create<List<RBean>> { subscribe ->
                val htmlStr = MainActivity.DEFAULT_HTML_PRE + html + MainActivity.DEFAULT_HTML_LAST
                val doc = Jsoup.parse(htmlStr)
                val mList = ParseClass.parseResult(doc)
                subscribe.onNext(mList)
                subscribe.onCompleted()
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ mList ->
                        webLayoutListener?.onWebLayoutValidateListener(mList)
                    }, {})
        }
    }

    inner class InJavaScriptLocalObj3 {

        @JavascriptInterface
        fun showSource(html: String) {
            val htmlStr = MainActivity.DEFAULT_HTML_PRE + html + MainActivity.DEFAULT_HTML_LAST
            val doc = Jsoup.parse(htmlStr)
            ParseClass.parseMainData(doc, 2)
        }
    }
}

interface WebLayoutListener {
    fun onWebLayoutValidateListener(mList: List<RBean>)
}
