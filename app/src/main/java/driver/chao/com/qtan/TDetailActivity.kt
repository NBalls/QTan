package driver.chao.com.qtan

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import driver.chao.com.qtan.bean.MainBean
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.widget.*
import driver.chao.com.qtan.parse.*
import driver.chao.com.qtan.util.clipDoc
import java.io.File
import java.io.FileOutputStream
import android.content.ClipboardManager
import android.text.TextUtils


class TDetailActivity : AppCompatActivity() {

    private lateinit var mainBean: MainBean
    private val FILE_PATH = Environment.getExternalStorageDirectory().absolutePath
    private var uri: Uri?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tdetail)

        mainBean = intent.getSerializableExtra("mainBean") as MainBean
        fillItem(mainBean, R.id.detailLayout)
        parseNear(findViewById(R.id.parseYaLayout), mainBean)
        initView()
    }

    private fun initView() {
        findViewById<Button>(R.id.zzButton).onClick {
            val zzRadioButton = findViewById<RadioButton>(R.id.zzRadioButton)
            val zlRadioButton = findViewById<RadioButton>(R.id.zlRadioButton)
            if (zzRadioButton.isChecked) {
                val doc = zhuZhuZhong(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.zhugeInclude, doc)
            } else if (zlRadioButton.isChecked) {
                val doc = zhuZhuLiu(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.zhugeInclude, doc)
            }
        }

        findViewById<Button>(R.id.zkButton).onClick {
            val zzRadioButton = findViewById<RadioButton>(R.id.zzRadioButton)
            val zlRadioButton = findViewById<RadioButton>(R.id.zlRadioButton)
            if (zzRadioButton.isChecked) {
                val doc = zhuKeZhong(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.zhugeInclude, doc)
            } else if (zlRadioButton.isChecked) {
                val doc = zhuKeLiu(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.zhugeInclude, doc)
            }

        }

        findViewById<Button>(R.id.dzButton).onClick {
            val dzRadioButton = findViewById<RadioButton>(R.id.dzRadioButton)
            val dfRadioButton = findViewById<RadioButton>(R.id.dfRadioButton)
            if (dzRadioButton.isChecked) {
                val doc = diaoZhuZhong(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.diaochanInclude, doc)
            } else if (dfRadioButton.isChecked) {
                val doc = diaoZhuFu(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.diaochanInclude, doc)
            }
        }

        findViewById<Button>(R.id.dkButton).onClick {
            val dzRadioButton = findViewById<RadioButton>(R.id.dzRadioButton)
            val dfRadioButton = findViewById<RadioButton>(R.id.dfRadioButton)
            if (dzRadioButton.isChecked) {
                val doc = diaoKeZhong(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.diaochanInclude, doc)
            } else if (dfRadioButton.isChecked) {
                val doc = diaoKeFu(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.diaochanInclude, doc)
            }
        }

        findViewById<Button>(R.id.szButton).onClick {
            val szRadioButton = findViewById<RadioButton>(R.id.szRadioButton)
            val sfRadioButton = findViewById<RadioButton>(R.id.sfRadioButton)
            if (szRadioButton.isChecked) {
                val doc = shiZhuShen(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.shixiongInclude, doc)
            } else if (sfRadioButton.isChecked) {
                val doc = shiZhuFu(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.shixiongInclude, doc)
            }
        }

        findViewById<Button>(R.id.skButton).onClick {
            val szRadioButton = findViewById<RadioButton>(R.id.szRadioButton)
            val sfRadioButton = findViewById<RadioButton>(R.id.sfRadioButton)
            if (szRadioButton.isChecked) {
                val doc = shiKeShen(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.shixiongInclude, doc)
            } else if (sfRadioButton.isChecked) {
                val doc = shiKeFu(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.shixiongInclude, doc)
            }
        }

        findViewById<Button>(R.id.qzButton).onClick {
            val doc = qiZhuLong(mainBean)
            clipDoc(this, doc)
            saveSheetCut(R.id.qixiaInclude, doc)
        }

        findViewById<Button>(R.id.qkButton).onClick {
            val doc = qiKeLong(mainBean)
            clipDoc(this, doc)
            saveSheetCut(R.id.qixiaInclude, doc)
        }

        findViewById<Button>(R.id.gzButton).onClick {
            if (findViewById<RadioButton>(R.id.gjRadioButton).isChecked) {
                val doc = guiZhuJing(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.defaultInclude, doc)
            } else if (findViewById<RadioButton>(R.id.gsRadioButton).isChecked) {
                val doc = guiZhuShen(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.defaultInclude, doc)
            }
        }

        findViewById<Button>(R.id.gkButton).onClick {
            if (findViewById<RadioButton>(R.id.gjRadioButton).isChecked) {
                val doc = guiKeJing(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.defaultInclude, doc)
            } else if (findViewById<RadioButton>(R.id.gsRadioButton).isChecked) {
                val doc = guiKeShen(mainBean)
                clipDoc(this, doc)
                saveSheetCut(R.id.defaultInclude, doc)
            }
        }

        findViewById<Button>(R.id.fzButton).onClick {
            val doc = faZhuXiu(mainBean)
            clipDoc(this, doc)
            saveSheetCut(R.id.defaultInclude, doc)
        }

        findViewById<Button>(R.id.fkButton).onClick {
            val doc = faKeXiu(mainBean)
            clipDoc(this, doc)
            saveSheetCut(R.id.defaultInclude, doc)
        }

        findViewById<Button>(R.id.czButton).onClick {
            val doc = chongZhuNi(mainBean)
            clipDoc(this, doc)
            saveSheetCut(R.id.defaultInclude, doc)
        }

        findViewById<Button>(R.id.ckButton).onClick {
            val doc = chongKeNi(mainBean)
            clipDoc(this, doc)
            saveSheetCut(R.id.defaultInclude, doc)
        }

        findViewById<Button>(R.id.updateButton).onClick {
            val data = (getSystemService(CLIPBOARD_SERVICE) as ClipboardManager).primaryClip
            val item = data.getItemAt(0)
            val text = item.text.toString()
            if (TextUtils.isEmpty(text)) {
                Toast.makeText(this, "粘贴板内容为空", Toast.LENGTH_SHORT).show()
            } else {
                if (findViewById<RadioButton>(R.id.zRadioButton).isChecked) {
                    saveSheetCut(R.id.zhugeInclude, text)
                } else if (findViewById<RadioButton>(R.id.dRadioButton).isChecked) {
                    saveSheetCut(R.id.diaochanInclude, text)
                } else if (findViewById<RadioButton>(R.id.sRadioButton).isChecked) {
                    saveSheetCut(R.id.shixiongInclude, text)
                } else if (findViewById<RadioButton>(R.id.qRadioButton).isChecked) {
                    saveSheetCut(R.id.qixiaInclude, text)
                }
            }
        }

        findViewById<LinearLayout>(R.id.zhugeInclude).setOnLongClickListener(onLongClickListener)
        findViewById<LinearLayout>(R.id.diaochanInclude).setOnLongClickListener(onLongClickListener)
        findViewById<LinearLayout>(R.id.shixiongInclude).setOnLongClickListener(onLongClickListener)
        findViewById<LinearLayout>(R.id.qixiaInclude).setOnLongClickListener(onLongClickListener)
        findViewById<LinearLayout>(R.id.defaultInclude).setOnLongClickListener(onLongClickListener)
    }

    private val onLongClickListener = View.OnLongClickListener {
        if (uri != null) {
            var shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "image/*"
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
            shareIntent = Intent.createChooser(shareIntent, "分享")
            startActivity(shareIntent)
        }
        true
    }

    private fun parseNear(container: LinearLayout, mainBean: MainBean) {
        container.addView(LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_sai_near, null, false))
        for (i in 0 until Math.min(mainBean.dList.size, 10)) {
            // 对战历史
            val rootViews = LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_sai, null, false)
            rootViews.findViewById<TextView>(R.id.dateText).text = mainBean.dList[i].date.trim()
            rootViews.findViewById<TextView>(R.id.liansaiText).text = mainBean.dList[i].liansai.trim()
            rootViews.findViewById<TextView>(R.id.zhuText).text = mainBean.dList[i].zhudui.trim()
            rootViews.findViewById<TextView>(R.id.bifenText).text = mainBean.dList[i].zhuPoint.trim() + "-" + mainBean.dList[i].kePoint.trim()
            rootViews.findViewById<TextView>(R.id.keText).text = mainBean.dList[i].kedui.trim()
            rootViews.findViewById<TextView>(R.id.panText).text = mainBean.dList[i].yPan.trim()

            if (mainBean.zhu.contains(mainBean.dList[i].zhudui) || mainBean.dList[i].zhudui.trim().contains(mainBean.zhu)) {
                if (mainBean.dList[i].zhuPoint.trim().toInt() > mainBean.dList[i].kePoint.trim().toInt()) {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#FF0000")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#000000")
                } else if (mainBean.dList[i].zhuPoint.trim().toInt() < mainBean.dList[i].kePoint.trim().toInt()) {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#00AE7A")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#000000")
                } else {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#0000FF")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#000000")
                }
            } else if (mainBean.zhu.contains(mainBean.dList[i].kedui) || mainBean.dList[i].kedui.trim().contains(mainBean.zhu)) {
                if (mainBean.dList[i].zhuPoint.trim().toInt() > mainBean.dList[i].kePoint.trim().toInt()) {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#000000")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#00AE7A")
                } else if (mainBean.dList[i].zhuPoint.trim().toInt() < mainBean.dList[i].kePoint.trim().toInt()) {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#000000")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#FF0000")
                } else {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#000000")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#0000FF")
                }
            } else {
                rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#000000")
                rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#000000")
            }

            if (mainBean.dList[i].zhuPoint.trim().toInt() > mainBean.dList[i].kePoint.trim().toInt()) {
                rootViews.findViewById<TextView>(R.id.resultText).text = "赢"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#FF0000")
                rootViews.findViewById<TextView>(R.id.panText).textColor = Color.parseColor("#FF0000")
            } else if (mainBean.dList[i].zhuPoint.trim().toInt() < mainBean.dList[i].kePoint.trim().toInt()) {
                rootViews.findViewById<TextView>(R.id.resultText).text = "输"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#00AE7A")
                rootViews.findViewById<TextView>(R.id.panText).textColor = Color.parseColor("#00AE7A")
            } else {
                rootViews.findViewById<TextView>(R.id.resultText).text = "平"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#000000")
                rootViews.findViewById<TextView>(R.id.panText).textColor = Color.parseColor("#000000")
            }
            container.addView(rootViews)
        }

        // 主队近期比赛
        container.addView(LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_sai_near, null, false))
        for (i in 0 until Math.min(mainBean.zList.size, 10)) {
            val rootViews = LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_sai, null, false)
            rootViews.findViewById<TextView>(R.id.dateText).text = mainBean.zList[i].date.trim()
            rootViews.findViewById<TextView>(R.id.liansaiText).text = mainBean.zList[i].liansai.trim()
            rootViews.findViewById<TextView>(R.id.zhuText).text = mainBean.zList[i].zhudui.trim()
            rootViews.findViewById<TextView>(R.id.bifenText).text = mainBean.zList[i].zhuPoint.trim() + "-" + mainBean.zList[i].kePoint.trim()
            rootViews.findViewById<TextView>(R.id.keText).text = mainBean.zList[i].kedui.trim()
            rootViews.findViewById<TextView>(R.id.panText).text = mainBean.zList[i].yPan.trim()

            if (mainBean.zhu.contains(mainBean.zList[i].zhudui) || mainBean.zList[i].zhudui.trim().contains(mainBean.zhu)) {
                if (mainBean.zList[i].zhuPoint.trim().toInt() > mainBean.zList[i].kePoint.trim().toInt()) {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#FF0000")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#000000")
                } else if (mainBean.zList[i].zhuPoint.trim().toInt() < mainBean.zList[i].kePoint.trim().toInt()) {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#00AE7A")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#000000")
                } else {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#0000FF")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#000000")
                }
            } else if (mainBean.zhu.contains(mainBean.zList[i].kedui) || mainBean.zList[i].kedui.trim().contains(mainBean.zhu)) {
                if (mainBean.zList[i].zhuPoint.trim().toInt() > mainBean.zList[i].kePoint.trim().toInt()) {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#000000")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#00AE7A")
                } else if (mainBean.zList[i].zhuPoint.trim().toInt() < mainBean.zList[i].kePoint.trim().toInt()) {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#000000")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#FF0000")
                } else {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#000000")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#0000FF")
                }
            } else {
                rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#000000")
                rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#000000")
            }

            if (mainBean.zList[i].zhuPoint.trim().toInt() > mainBean.zList[i].kePoint.trim().toInt()) {
                rootViews.findViewById<TextView>(R.id.resultText).text = "赢"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#FF0000")
                rootViews.findViewById<TextView>(R.id.panText).textColor = Color.parseColor("#FF0000")
            } else if (mainBean.zList[i].zhuPoint.trim().toInt() < mainBean.zList[i].kePoint.trim().toInt()) {
                rootViews.findViewById<TextView>(R.id.resultText).text = "输"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#00AE7A")
                rootViews.findViewById<TextView>(R.id.panText).textColor = Color.parseColor("#00AE7A")
            } else {
                rootViews.findViewById<TextView>(R.id.resultText).text = "平"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#000000")
                rootViews.findViewById<TextView>(R.id.panText).textColor = Color.parseColor("#000000")
            }
            container.addView(rootViews)
        }

        // 客队近期比赛
        container.addView(LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_sai_near, null, false))
        for (i in 0 until Math.min(mainBean.kList.size, 10)) {
            val rootViews = LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_sai, null, false)
            rootViews.findViewById<TextView>(R.id.dateText).text = mainBean.kList[i].date.trim()
            rootViews.findViewById<TextView>(R.id.liansaiText).text = mainBean.kList[i].liansai.trim()
            rootViews.findViewById<TextView>(R.id.zhuText).text = mainBean.kList[i].zhudui.trim()
            rootViews.findViewById<TextView>(R.id.bifenText).text = mainBean.kList[i].zhuPoint.trim() + "-" + mainBean.kList[i].kePoint.trim()
            rootViews.findViewById<TextView>(R.id.keText).text = mainBean.kList[i].kedui.trim()
            rootViews.findViewById<TextView>(R.id.panText).text = mainBean.kList[i].yPan.trim()

            if (mainBean.ke.contains(mainBean.kList[i].zhudui) || mainBean.kList[i].zhudui.trim().contains(mainBean.ke)) {
                if (mainBean.kList[i].zhuPoint.trim().toInt() > mainBean.kList[i].kePoint.trim().toInt()) {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#FF0000")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#000000")
                } else if (mainBean.kList[i].zhuPoint.trim().toInt() < mainBean.kList[i].kePoint.trim().toInt()) {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#00AE7A")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#000000")
                } else {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#0000FF")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#000000")
                }
            } else if (mainBean.ke.contains(mainBean.kList[i].kedui) || mainBean.kList[i].kedui.trim().contains(mainBean.ke)) {
                if (mainBean.kList[i].zhuPoint.trim().toInt() > mainBean.kList[i].kePoint.trim().toInt()) {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#000000")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#00AE7A")
                } else if (mainBean.kList[i].zhuPoint.trim().toInt() < mainBean.kList[i].kePoint.trim().toInt()) {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#000000")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#FF0000")
                } else {
                    rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#000000")
                    rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#0000FF")
                }
            } else {
                rootViews.findViewById<TextView>(R.id.zhuText).textColor = Color.parseColor("#000000")
                rootViews.findViewById<TextView>(R.id.keText).textColor = Color.parseColor("#000000")
            }
            if (mainBean.kList[i].zhuPoint.trim().toInt() > mainBean.kList[i].kePoint.trim().toInt()) {
                rootViews.findViewById<TextView>(R.id.resultText).text = "赢"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#FF0000")
                rootViews.findViewById<TextView>(R.id.panText).textColor = Color.parseColor("#FF0000")
            } else if (mainBean.kList[i].zhuPoint.trim().toInt() < mainBean.kList[i].kePoint.trim().toInt()) {
                rootViews.findViewById<TextView>(R.id.resultText).text = "输"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#00AE7A")
                rootViews.findViewById<TextView>(R.id.panText).textColor = Color.parseColor("#00AE7A")
            } else {
                rootViews.findViewById<TextView>(R.id.resultText).text = "平"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#000000")
                rootViews.findViewById<TextView>(R.id.panText).textColor = Color.parseColor("#000000")
            }
            container.addView(rootViews)
        }
    }

    private fun fillItem(mainBean: MainBean, parentId: Int) {
        findViewById<LinearLayout>(parentId)?.removeAllViews()
        val rootViews = LayoutInflater.from(this).inflate(R.layout.fragment_parser_item, null, false)
        rootViews.findViewById<TextView>(R.id.bisai).text = mainBean.liansai
        rootViews.findViewById<TextView>(R.id.time).text = mainBean.time
        rootViews.findViewById<TextView>(R.id.zhudiu).text = mainBean.getZhu()
        rootViews.findViewById<TextView>(R.id.kedui).text = mainBean.getKe()
        rootViews.findViewById<TextView>(R.id.panText).text = getEndPan(mainBean)
        rootViews.findViewById<TextView>(R.id.resultText).text = mainBean.bifen
        val bifen = mainBean.bifen
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
        rootViews.findViewById<LinearLayout>(R.id.parseYaLayout).visibility = View.GONE
        parseYa(rootViews.findViewById(R.id.parseYaLayout), mainBean)

        rootViews.findViewById<TextView>(R.id.yaText).onClick {
            val layout = rootViews.findViewById<LinearLayout>(R.id.parseYaLayout)
            if (layout.visibility == View.VISIBLE) {
                layout.visibility = View.GONE
            } else {
                layout.visibility = View.VISIBLE
            }
        }

        val lps = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        findViewById<LinearLayout>(parentId)?.addView(rootViews, lps)
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

    private fun getEndPan(mainBean: MainBean): String {
        for (i in 0 until mainBean.yList.size) {
            if (mainBean.yList[i].company.contains("365")) {
                return mainBean.yList[i].endPan
            }
        }

        return ""
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

    private fun viewGone() {
        findViewById<LinearLayout>(R.id.zhugeInclude).visibility = View.GONE
        findViewById<LinearLayout>(R.id.diaochanInclude).visibility = View.GONE
        findViewById<LinearLayout>(R.id.qixiaInclude).visibility = View.GONE
        findViewById<LinearLayout>(R.id.shixiongInclude).visibility = View.GONE
        findViewById<LinearLayout>(R.id.defaultInclude).visibility = View.GONE
    }

    private fun saveSheetCut(viewId: Int, text: String) {
        viewGone()
        findViewById<LinearLayout>(viewId).visibility = View.VISIBLE
        findViewById<LinearLayout>(viewId).findViewById<TextView>(R.id.zzText).text = text
        findViewById<Button>(R.id.zzButton).post {
            val cv = findViewById<LinearLayout>(viewId)
            cv.isDrawingCacheEnabled = true
            cv.buildDrawingCache()
            val bmp = cv.drawingCache
            bmp.setHasAlpha(false)
            bmp.prepareToDraw()
            saveBitmapToLocal(System.currentTimeMillis().toString(), bmp)
        }
    }

    private fun saveBitmapToLocal(fileName: String, bitmap: Bitmap) {
        try {
            // 创建文件流，指向该路径，文件名叫做fileName
            val file = File(FILE_PATH, fileName + ".png")
            // file其实是图片，它的父级File是文件夹，判断一下文件夹是否存在，如果不存在，创建文件夹
            val fileParent = file.parentFile
            if (!fileParent.exists()) {
                // 文件夹不存在
                fileParent.mkdirs()// 创建文件夹
            }
            if (!file.exists()) {
                file.createNewFile()
            }
            // 将图片保存到本地
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    FileOutputStream(file))
            uri = Uri.fromFile(file)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
