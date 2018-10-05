package driver.chao.com.qtan

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import driver.chao.com.qtan.bean.MainBean
import driver.chao.com.qtan.bean.RBean
import driver.chao.com.qtan.parse.ParseClass
import driver.chao.com.qtan.parse.PrintClass
import driver.chao.com.qtan.util.ParserUtil.getEndPan
import driver.chao.com.qtan.util.TanCompleteListener
import driver.chao.com.qtan.util.getYMD
import driver.chao.com.qtan.util.getYMDHMS
import driver.chao.com.qtan.widget.SimpleRoundProgress
import driver.chao.com.qtan.widget.WebLayout
import driver.chao.com.qtan.widget.WebLayoutListener
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 应用解析数据主页面
 * Feature功能列表：
 * 1、解析
 * 2、验证
 * 3、获取
 */
class MainActivity : AppCompatActivity() {

    companion object {
        val SHARE_NAME = "parser_data"
        val SP_TIME_KEY = "sp_time_key"
        val SP_DATE_KEY = "sp_date_key"
        val SP_DATA_KEY = "sp_data_key"

        val TOAST_VALIDATE_ERROR = "数据无法验证....."
        val TOAST_DATE_ERROR = "日期格式不正确...."
        val TEXT_LOADING = "正在加载数据......"
        val TEXT_LOADING_YA = "正在加载亚盘数据......"
        val TEXT_LOADING_OU = "正在加载欧指数据......"
        val TEXT_LOADING_RA = "正在加载近期数据......"
        val TEXT_VALIDATEING = "验证中......"

        val TAG = "TanFragment"
        val DEFAULT_WEB_URL = "http://www.win0168.com"
        val DEFAULT_VALIDATE_URL = "http://www.win0168.com/football/hg/Over_"
        val DEFAULT_HTML_PRE = "<html><head></head><body>"
        val DEFAULT_HTML_LAST = "</body></html>"
    }
    private val webLayout by lazy {
        findViewById<WebLayout>(R.id.webLayout)
    }
    private val titleText by lazy {
        findViewById<TextView>(R.id.titleText)
    }
    private val freshButton by lazy {
        findViewById<Button>(R.id.freshButton)
    }
    private val validateButton by lazy {
        findViewById<Button>(R.id.validateButton)
    }
    private val imageButton by lazy {
        findViewById<Button>(R.id.imageButton)
    }
    private val queryButton by lazy {
        findViewById<Button>(R.id.queryButton)
    }
    private val goneButton by lazy {
        findViewById<Button>(R.id.goneButton)
    }
    private val sureButton by lazy {
        findViewById<Button>(R.id.sureButton)
    }
    private val sharedPreferences by lazy {
        getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE)
    }
    private val tanCompleteListener = object : TanCompleteListener {

        override fun onTanLoadMainDataCompleteListener(total: Int) {
            Handler(Looper.getMainLooper()).post {
                val totalTime = 20 + total / 10 * 10 + total / 10 * 10 + total / 5 * 10 + 10

                Toast.makeText(this@MainActivity, "大概需要解析时间：" + (totalTime / 60 + 1) + "分钟......", Toast.LENGTH_LONG).show()
                findViewById<FrameLayout>(R.id.progressLayout).visibility = View.VISIBLE
                findViewById<SimpleRoundProgress>(R.id.simpleRoundProgress).setMax(totalTime)
                (findViewById<TextView>(R.id.timeText) as TextView).text = "" + (totalTime / 60 + 1) + "分钟"
                Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ count ->
                            if (count <= totalTime) {
                                findViewById<SimpleRoundProgress>(R.id.simpleRoundProgress).progress = count.toInt()
                            } else {
                                // findViewById<FrameLayout>(R.id.progressLayout).visibility = View.GONE
                            }
                        }, {})
            }
        }

        override fun onTanLoadMainDataListener() {
            Handler(Looper.getMainLooper()).post {
                titleText.text = TEXT_LOADING
            }
        }

        override fun onTanLoadYaDataListener() {
            Handler(Looper.getMainLooper()).post {
                titleText.text = TEXT_LOADING_YA
            }
        }

        override fun onTanLoadOuDataListener() {
            Handler(Looper.getMainLooper()).post {
                titleText.text = TEXT_LOADING_OU
            }
        }

        override fun onTanLoadRaDataListener() {
            Handler(Looper.getMainLooper()).post {
                titleText.text = TEXT_LOADING_RA
            }
        }

        override fun onTanCompleteListener(mDataList: List<MainBean>) {
            Handler(Looper.getMainLooper()).post {
                if (ParseClass.subscription != null) {
                    ParseClass.subscription.unsubscribe()
                }
                saveData(mDataList)
            }
        }
    }
    private val webLayoutListener = object : WebLayoutListener {
        override fun onWebLayoutValidateListener(mList: List<RBean>) {
            val data = sharedPreferences.getString(MainActivity.SP_DATA_KEY, "{}")
            val dataList = Gson().fromJson<List<MainBean>>(data, object : TypeToken<List<MainBean>>() {}.type)
            for (i in 0 until dataList.size) {
                for (j in 0 until mList.size) {
                    if (mList[j].getZhudui().contains(dataList[i].zhu)
                            && mList[j].getLiansai().contains(dataList[i].liansai)) {
                        dataList[i].bifen = mList[j].points
                        break
                    }
                }
            }

            runOnUiThread {
                // 更新保存数据
                validateButton.text = "验证"
                fillData(dataList)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWebView()
    }

    private val onCheckedChangeListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        if (buttonView?.id == R.id.coverCheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outCoverLayout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outCoverLayout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.downCheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outDownLayout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outDownLayout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.pingCheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outZeroLayout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outZeroLayout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.cutCheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outCutLayout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outCutLayout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.pingToPBCheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outOne25Layout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outOne25Layout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.overCheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outDeepLayout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outDeepLayout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.one44Checkbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outOneLayout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outOneLayout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.one65Checkbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outOne65Layout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outOne65Layout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.allCheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outAllLayout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outAllLayout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.likeCheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outLikeLayout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outLikeLayout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.byToBQCheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.out075To05Layout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.out075To05Layout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.byToYQCheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.out075To1Layout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.out075To1Layout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.yqToYECheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outTo125Layout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outTo125Layout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.overMoreCheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outOverMoreLayout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outOverMoreLayout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.diffPanCheckbox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outDiffPanLayout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outDiffPanLayout).visibility = View.GONE
            }
        } else if (buttonView?.id == R.id.banTo25CheckBox) {
            if (isChecked) {
                findViewById<LinearLayout>(R.id.outBanTo25PanLayout).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.outBanTo25PanLayout).visibility = View.GONE
            }
        }
    }

    private fun initWebView() {
        ParseClass.tanCompleteListener = tanCompleteListener
        webLayout.webLayoutListener = webLayoutListener

        (findViewById<CheckBox>(R.id.coverCheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.downCheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.pingCheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.cutCheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.pingToPBCheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.overCheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.one44Checkbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.one65Checkbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.allCheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.likeCheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.byToBQCheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.byToYQCheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.yqToYECheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.overMoreCheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.diffPanCheckbox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        (findViewById<CheckBox>(R.id.banTo25CheckBox) as CheckBox).setOnCheckedChangeListener(onCheckedChangeListener)
        if ((findViewById<CheckBox>(R.id.coverCheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outCoverLayout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outCoverLayout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.downCheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outDownLayout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outDownLayout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.pingCheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outZeroLayout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outZeroLayout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.cutCheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outCutLayout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outCutLayout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.pingToPBCheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outOne25Layout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outOne25Layout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.overCheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outDeepLayout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outDeepLayout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.one44Checkbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outOneLayout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outOneLayout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.one65Checkbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outOne65Layout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outOne65Layout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.allCheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outAllLayout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outAllLayout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.likeCheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outLikeLayout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outLikeLayout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.byToBQCheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.out075To05Layout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.out075To05Layout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.byToYQCheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.out075To1Layout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.out075To1Layout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.yqToYECheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outTo125Layout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outTo125Layout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.overMoreCheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outOverMoreLayout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outOverMoreLayout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.diffPanCheckbox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outDiffPanLayout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outDiffPanLayout).visibility = View.GONE
        }
        if ((findViewById<CheckBox>(R.id.banTo25CheckBox) as CheckBox).isChecked) {
            findViewById<LinearLayout>(R.id.outBanTo25PanLayout).visibility = View.VISIBLE
        } else {
            findViewById<LinearLayout>(R.id.outBanTo25PanLayout).visibility = View.GONE
        }
        findViewById<Button>(R.id.selectAllButton).onClick {
            (findViewById<CheckBox>(R.id.coverCheckbox) as CheckBox).isChecked = true
            (findViewById<CheckBox>(R.id.downCheckbox) as CheckBox).isChecked = true
            (findViewById<CheckBox>(R.id.pingCheckbox) as CheckBox).isChecked = true
            (findViewById<CheckBox>(R.id.cutCheckbox) as CheckBox).isChecked = true
            (findViewById<CheckBox>(R.id.pingToPBCheckbox) as CheckBox).isChecked = true
            (findViewById<CheckBox>(R.id.overCheckbox) as CheckBox).isChecked = true
            (findViewById<CheckBox>(R.id.one44Checkbox) as CheckBox).isChecked = true
            (findViewById<CheckBox>(R.id.one65Checkbox) as CheckBox).isChecked = true

            (findViewById<CheckBox>(R.id.byToBQCheckbox) as CheckBox).isChecked = true
            (findViewById<CheckBox>(R.id.byToYQCheckbox) as CheckBox).isChecked = true
            (findViewById<CheckBox>(R.id.yqToYECheckbox) as CheckBox).isChecked = true
            (findViewById<CheckBox>(R.id.overMoreCheckbox) as CheckBox).isChecked = true
            (findViewById<CheckBox>(R.id.diffPanCheckbox) as CheckBox).isChecked = true
            (findViewById<CheckBox>(R.id.banTo25CheckBox) as CheckBox).isChecked = true
        }
        findViewById<Button>(R.id.unselectAllButton).onClick {
            (findViewById<CheckBox>(R.id.coverCheckbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.downCheckbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.pingCheckbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.cutCheckbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.pingToPBCheckbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.overCheckbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.one44Checkbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.one65Checkbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.byToBQCheckbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.byToYQCheckbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.yqToYECheckbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.overMoreCheckbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.diffPanCheckbox) as CheckBox).isChecked = false
            (findViewById<CheckBox>(R.id.banTo25CheckBox) as CheckBox).isChecked = false
        }

        freshButton.onClick {
            webLayout.webView.loadUrl(DEFAULT_WEB_URL)
        }

        validateButton.onClick {
            val sb = StringBuffer()
            val date = sharedPreferences.getString(SP_DATE_KEY, "")
            sb.append(date.substring(0, date.indexOf("-")))
            sb.append(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")))
            sb.append(date.substring(date.lastIndexOf("-") + 1))
            val sdf = SimpleDateFormat("yyyyMMdd")
            if (sb.toString() != sdf.format(Date())) {
                validateButton.text = TEXT_VALIDATEING
                val url = DEFAULT_VALIDATE_URL + sb.toString() + ".htm"
                webLayout.webView2.loadUrl(url)
            } else {
                Toast.makeText(this, TOAST_VALIDATE_ERROR, Toast.LENGTH_SHORT).show()
            }
        }

        imageButton.onClick {
            // applyPermission()
        }

        queryButton.onClick {
            findViewById<LinearLayout>(R.id.dateLayout)?.visibility = View.VISIBLE
        }

        goneButton.onClick {
            findViewById<LinearLayout>(R.id.dateLayout)?.visibility = View.GONE
        }

        sureButton.onClick {
            val data = (findViewById<EditText>(R.id.dateEdit) as EditText).text.toString()
            if (TextUtils.isEmpty(data) || !data.matches(Regex("\\d\\d\\d\\d\\d\\d\\d\\d"))) {
                Toast.makeText(this, TOAST_DATE_ERROR, Toast.LENGTH_SHORT).show()
            } else {
                val url = DEFAULT_VALIDATE_URL + data + ".htm"
                webLayout.webView3.loadUrl(url)
            }
        }

        (findViewById<TextView>(R.id.titleText) as TextView).text = "数据从本地拉取中....."
        Observable.create<Boolean> { subscribe ->
            val data = sharedPreferences.getString(SP_DATA_KEY, "[]")
            val date = sharedPreferences.getString(SP_DATE_KEY, "")
            val dateTime = sharedPreferences.getString(SP_TIME_KEY, "")
            val time = dateTime.substring(dateTime.indexOf(" ") + 1)
            parseData(data, date, time)
            subscribe.onNext(true)
            subscribe.onCompleted()
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {})
    }

    private fun saveData(mDataList: List<MainBean>) {
        Log.i(TAG,"保存本次数据....... 比赛数量：" + mDataList.size)
        sharedPreferences.edit().putString(SP_DATA_KEY, "[]").apply()
        sharedPreferences.edit().putString(SP_TIME_KEY, getYMDHMS()).apply()
        sharedPreferences.edit().putString(SP_DATE_KEY, getYMD()).apply()
        sharedPreferences.edit().putString(SP_DATA_KEY, Gson().toJson(mDataList)).apply()

        Handler(Looper.getMainLooper()).post {
            val dateTime = sharedPreferences.getString(SP_TIME_KEY, "")
            val time = dateTime.substring(dateTime.indexOf(" ") + 1)
            val date = sharedPreferences.getString(SP_DATE_KEY, "")
            parseData(sharedPreferences.getString(SP_DATA_KEY, "{}"), date, time)
        }
    }

    private fun parseData(data: String, date: String, time: String) {
        val dataList = Gson().fromJson<List<MainBean>>(data, object : TypeToken<List<MainBean>>() {}.type)
        val sb = StringBuffer()
        sb.append("有效比赛:").append(dataList.size).append("场\n")
        sb.append("刷新日期:").append(date).append("\n")
        sb.append("刷新时间:").append(time).append("\n")
        runOnUiThread {
            titleText.text = sb.toString()
            fillItem(PrintClass.parse144(dataList) as ArrayList<MainBean>, R.id.oneLayout)
            fillItem(PrintClass.parse165(dataList) as ArrayList<MainBean>, R.id.one65Layout)
            fillItem(PrintClass.parseCOver(dataList) as ArrayList<MainBean>, R.id.coverLayout)
            fillItem(PrintClass.parseDown(dataList) as ArrayList<MainBean>, R.id.downLayout)
            fillItem(PrintClass.parseZero(dataList) as ArrayList<MainBean>, R.id.zeroLayout)
            fillItem(PrintClass.parseCut(dataList) as ArrayList<MainBean>, R.id.cutLayout)
            fillItem(PrintClass.parse025(dataList) as ArrayList<MainBean>, R.id.one25Layout)
            fillItem(PrintClass.parseDeep(dataList) as ArrayList<MainBean>, R.id.deepLayout)
            fillItem(dataList as ArrayList<MainBean>, R.id.allLayout)
            fillItem(PrintClass.parseNear(dataList) as ArrayList<MainBean>, R.id.likeMainLayout)
            fillItem(PrintClass.parse075To05(dataList) as ArrayList<MainBean>, R.id.one75To05Layout)
            fillItem(PrintClass.parse075To1(dataList) as ArrayList<MainBean>, R.id.one75To1Layout)
            fillItem(PrintClass.parse1To125(dataList) as ArrayList<MainBean>, R.id.oneTo125Layout)
            fillItem(PrintClass.parseOverMore(dataList) as ArrayList<MainBean>, R.id.overMoreLayout)
            fillItem(PrintClass.parseNear(dataList) as ArrayList<MainBean>, R.id.diffPanLayout)
            fillItem(PrintClass.parserBanTo25(dataList) as ArrayList<MainBean>, R.id.banTo25Layout)
        }
    }

    private fun fillItem(mList: ArrayList<MainBean>, parentId: Int) {
        findViewById<LinearLayout>(parentId)?.removeAllViews()
        for (i in 0 until mList.size) {
            val rootViews = LayoutInflater.from(this).inflate(R.layout.fragment_parser_item, null, false)
            rootViews.findViewById<TextView>(R.id.bisai).text = mList[i].liansai
            rootViews.findViewById<TextView>(R.id.time).text = mList[i].time
            rootViews.findViewById<TextView>(R.id.zhudiu).text = mList[i].zhu
            rootViews.findViewById<TextView>(R.id.kedui).text = mList[i].ke
            rootViews.findViewById<TextView>(R.id.panText).text = getEndPan(mList[i])
            rootViews.findViewById<TextView>(R.id.resultText).text = mList[i].bifen
            if (mList[i].like) {
                rootViews.findViewById<ImageView>(R.id.likeImage).setImageResource(R.drawable.ic_like_already)
                rootViews.setBackgroundColor(Color.parseColor("#FF0000"))
            } else {
                rootViews.findViewById<ImageView>(R.id.likeImage).setImageResource(R.drawable.ic_like)
                rootViews.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
            val bifen = mList[i].bifen
            if (bifen.matches(Regex("\\d-\\d"))) {
                val zPoint = bifen.substring(0, 1)
                val kPoint = bifen.substring(2, 3)
                if (zPoint.toInt() > kPoint.toInt()) {
                    rootViews.findViewById<TextView>(R.id.rText).text = "赢"
                    rootViews.findViewById<TextView>(R.id.rText).textColor = Color.parseColor("#FF0000")
                } else if (zPoint.toInt() < kPoint.toInt()) {
                    rootViews.findViewById<TextView>(R.id.rText).text = "输"
                    rootViews.findViewById<TextView>(R.id.rText).textColor = Color.parseColor("#00AE7A")
                } else {
                    rootViews.findViewById<TextView>(R.id.rText).text = "平"
                    rootViews.findViewById<TextView>(R.id.rText).textColor = Color.parseColor("#000000")
                }
            } else {
                rootViews.findViewById<TextView>(R.id.rText).text = "未"
                rootViews.findViewById<TextView>(R.id.rText).textColor = Color.parseColor("#000000")
            }
            parseYa(rootViews.findViewById(R.id.parseYaLayout), mList[i])

            rootViews.findViewById<TextView>(R.id.yaText).onClick {
                val layout = rootViews.findViewById<LinearLayout>(R.id.parseYaLayout)
                if (layout.visibility == View.VISIBLE) {
                    layout.visibility = View.GONE
                } else {
                    layout.visibility = View.VISIBLE
                }
            }

            /**
             * 关注比赛
             */
            rootViews.findViewById<LinearLayout>(R.id.likeLayout).onClick {
                mList[i].like = !mList[i].like
                if (mList[i].like) {
                    rootViews.findViewById<ImageView>(R.id.likeImage).setImageResource(R.drawable.ic_like_already)
                    rootViews.setBackgroundColor(Color.parseColor("#FF0000"))
                } else {
                    rootViews.findViewById<ImageView>(R.id.likeImage).setImageResource(R.drawable.ic_like)
                    rootViews.setBackgroundColor(Color.parseColor("#FFFFFF"))
                }
                Observable.create<List<MainBean>> { subscribe ->
                    val data = sharedPreferences.getString(SP_DATA_KEY, "{}")
                    val dataList = Gson().fromJson<List<MainBean>>(data, object : TypeToken<List<MainBean>>() {}.type)
                    for (j in 0 until dataList.size) {
                        if (dataList[j].id.equals(mList[i].id)) {
                            dataList[j].like = mList[i].like
                            sharedPreferences.edit().putString(SP_DATA_KEY, Gson().toJson(dataList)).apply()
                            break
                        }
                    }
                    subscribe.onNext(dataList)
                    subscribe.onCompleted()
                }.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ dataList ->
                            val likeList = PrintClass.parseLike(dataList)
                            fillItem(PrintClass.parseLike(dataList) as ArrayList<MainBean>, R.id.likeMainLayout)
                        }, {})
            }

            rootViews.findViewById<LinearLayout>(R.id.liansaiLayout).onClick {
                val intent = Intent(this, TDetailActivity::class.java)
                intent.putExtra("mainBean", mList[i])
                startActivity(intent)
            }

            rootViews.findViewById<TextView>(R.id.analyseText).onClick {
                Toast.makeText(this, "打开分析数据......", Toast.LENGTH_SHORT).show()
            }

            rootViews.onClick {
                // val intent = Intent("bet007.main")
                // startActivity(intent)
            }
            val lps = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            findViewById<LinearLayout>(parentId)?.addView(rootViews, lps)
        }
    }

    private fun parseYa(container: LinearLayout, mainBean: MainBean) {
        container.addView(LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_ya_title, null, false))
        for (i in 0 until mainBean.yList.size) {
            if (isFilter(mainBean.yList[i].company)) {
                val rootViews = LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_ya, null, false)
                rootViews.findViewById<TextView>(R.id.yCompany).text = mainBean.yList[i].company.trim()
                rootViews.findViewById<TextView>(R.id.startZRate).text = mainBean.yList[i].startZRate.trim()
                rootViews.findViewById<TextView>(R.id.startPan).text = mainBean.yList[i].startPan.trim()
                rootViews.findViewById<TextView>(R.id.startKRate).text = mainBean.yList[i].startKRate.trim()

                rootViews.findViewById<TextView>(R.id.endZRate).text = mainBean.yList[i].endZRate.trim()
                if (mainBean.yList[i].endZRate.toFloat() > mainBean.yList[i].startZRate.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endZRate).textColor = Color.parseColor("#FF0000")
                } else if (mainBean.yList[i].endZRate.toFloat() < mainBean.yList[i].startZRate.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endZRate).textColor = Color.parseColor("#00AE7A")
                } else {
                    rootViews.findViewById<TextView>(R.id.endZRate).textColor = Color.parseColor("#000000")
                }
                rootViews.findViewById<TextView>(R.id.endPan).text = mainBean.yList[i].endPan.trim()
                if (mainBean.yList[i].endPan.toFloat() > mainBean.yList[i].startPan.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endPan).textColor = Color.parseColor("#FF0000")
                } else if (mainBean.yList[i].endPan.toFloat() < mainBean.yList[i].startPan.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endPan).textColor = Color.parseColor("#00AE7A")
                } else {
                    rootViews.findViewById<TextView>(R.id.endPan).textColor = Color.parseColor("#000000")
                }
                rootViews.findViewById<TextView>(R.id.endKRate).text = mainBean.yList[i].endKRate.trim()
                if (mainBean.yList[i].endKRate.toFloat() > mainBean.yList[i].startKRate.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endKRate).textColor = Color.parseColor("#FF0000")
                } else if (mainBean.yList[i].endKRate.toFloat() < mainBean.yList[i].startKRate.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endKRate).textColor = Color.parseColor("#00AE7A")
                } else {
                    rootViews.findViewById<TextView>(R.id.endKRate).textColor = Color.parseColor("#000000")
                }
                container.addView(rootViews)
            }
        }
        container.addView(LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_ou_title, null, false))
        for (i in 0 until mainBean.oList.size) {
            if (isFilter(mainBean.oList[i].company)) {
                val rootViews = LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_ou, null, false)
                rootViews.findViewById<TextView>(R.id.oCompany).text = mainBean.oList[i].company
                rootViews.findViewById<TextView>(R.id.startSRate).text = mainBean.oList[i].startS
                rootViews.findViewById<TextView>(R.id.endSRate).text = mainBean.oList[i].endS
                if (mainBean.oList[i].endS.trim().toFloat() > mainBean.oList[i].startS.trim().toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endSRate).textColor = Color.parseColor("#FF0000")
                } else if (mainBean.oList[i].endS.trim().toFloat() < mainBean.oList[i].startS.trim().toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endSRate).textColor = Color.parseColor("#00AE7A")
                } else {
                    rootViews.findViewById<TextView>(R.id.endSRate).textColor = Color.parseColor("#000000")
                }
                rootViews.findViewById<TextView>(R.id.startPRate).text = mainBean.oList[i].startP
                rootViews.findViewById<TextView>(R.id.endPRate).text = mainBean.oList[i].endP
                if (mainBean.oList[i].endP.toFloat() > mainBean.oList[i].startP.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endPRate).textColor = Color.parseColor("#FF0000")
                } else if (mainBean.oList[i].endP.toFloat() < mainBean.oList[i].startP.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endPRate).textColor = Color.parseColor("#00AE7A")
                } else {
                    rootViews.findViewById<TextView>(R.id.endPRate).textColor = Color.parseColor("#000000")
                }
                rootViews.findViewById<TextView>(R.id.startFRate).text = mainBean.oList[i].startF
                rootViews.findViewById<TextView>(R.id.endFRate).text = mainBean.oList[i].endF
                if (mainBean.oList[i].endF.toFloat() > mainBean.oList[i].startF.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endFRate).textColor = Color.parseColor("#FF0000")
                } else if (mainBean.oList[i].endF.toFloat() < mainBean.oList[i].startF.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endFRate).textColor = Color.parseColor("#00AE7A")
                } else {
                    rootViews.findViewById<TextView>(R.id.endFRate).textColor = Color.parseColor("#000000")
                }
                container.addView(rootViews)
            }
        }
    }

    private fun isFilter(company: String): Boolean {
        return company.contains("365") ||
                company.contains("易胜博") ||
                company.contains("Crown") ||
                company.contains("威廉") ||
                company.contains("韦德") ||
                company.contains("澳门") ||
                company.contains("金宝博") ||
                company.contains("盈禾") ||
                company.contains("立博")

    }

    private fun fillData(dataList: List<MainBean>) {
        sharedPreferences.edit().putString(SP_DATA_KEY, Gson().toJson(dataList)).apply()
        runOnUiThread {
            fillItem(PrintClass.parse144(dataList) as ArrayList<MainBean>, R.id.oneLayout)
            fillItem(PrintClass.parse165(dataList) as ArrayList<MainBean>, R.id.one65Layout)
            fillItem(PrintClass.parseCOver(dataList) as ArrayList<MainBean>, R.id.coverLayout)
            fillItem(PrintClass.parseDown(dataList) as ArrayList<MainBean>, R.id.downLayout)
            fillItem(PrintClass.parseZero(dataList) as ArrayList<MainBean>, R.id.zeroLayout)
            fillItem(PrintClass.parseCut(dataList) as ArrayList<MainBean>, R.id.cutLayout)
            fillItem(PrintClass.parse025(dataList) as ArrayList<MainBean>, R.id.one25Layout)
            fillItem(PrintClass.parseDeep(dataList) as ArrayList<MainBean>, R.id.deepLayout)
            fillItem(dataList as ArrayList<MainBean>, R.id.allLayout)
            fillItem(PrintClass.parseNear(dataList) as ArrayList<MainBean>, R.id.likeMainLayout)
            fillItem(PrintClass.parse075To05(dataList) as ArrayList<MainBean>, R.id.one75To05Layout)
            fillItem(PrintClass.parse075To1(dataList) as ArrayList<MainBean>, R.id.one75To1Layout)
            fillItem(PrintClass.parse1To125(dataList) as ArrayList<MainBean>, R.id.oneTo125Layout)
            fillItem(PrintClass.parseOverMore(dataList) as ArrayList<MainBean>, R.id.overMoreLayout)
            fillItem(PrintClass.parseNear(dataList) as ArrayList<MainBean>, R.id.diffPanLayout)
            fillItem(PrintClass.parserBanTo25(dataList) as ArrayList<MainBean>, R.id.banTo25Layout)
        }
    }
}
