package driver.chao.com.qtan

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import driver.chao.com.qtan.bean.MainBean
import driver.chao.com.qtan.parse.ParseClass
import driver.chao.com.qtan.util.getMD
import driver.chao.com.qtan.util.getMYDR
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast


class TDetailActivity : AppCompatActivity() {

    private lateinit var mainBean: MainBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tdetail)

        mainBean = intent.getSerializableExtra("mainBean") as MainBean
        fillItem(mainBean, R.id.detailLayout)

        ParseClass.parseRData(mainBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ s ->
                    mainBean.setdList(ParseClass.parseDList(s))
                    mainBean.setzList(ParseClass.parseZList(s))
                    mainBean.setkList(ParseClass.parseKList(s))
                    parseNear(findViewById(R.id.parseYaLayout), mainBean)
                }, {})
        initView()
    }

    private fun initView() {
        findViewById<Button>(R.id.zzButton).onClick {
            val sb = StringBuffer()
            sb.append("诸葛看球").append(getMD()).append("会员推荐\n\n")
            sb.append("重心单 ")
                    .append(mainBean.liansai)
                    .append(" ")
                    .append(mainBean.time)
                    .append(" ")
                    .append(mainBean.zhu)
                    .append("VS")
                    .append(mainBean.ke)
                    .append("\n")
            sb.append("推荐：").append(mainBean.zhu)
            if (mainBean.yList[0].endPan.toFloat() > 0) {
                sb.append("-")
                sb.append(mainBean.yList[0].endPan)
            } else if (mainBean.yList[0].endPan.toFloat() < 0) {
                sb.append("+")
                sb.append(mainBean.yList[0].endPan.substring(1))
            } else {
                sb.append("平手")
            }
            sb.append("\n\n").append("建议: 稳胆单 双注，实力单 均注！！！")
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(null, sb.toString())
            clipboard.primaryClip = clipData
            Toast.makeText(this, "诸葛重心单已经复制到粘贴板", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.zkButton).onClick {
            val sb = StringBuffer()
            sb.append("诸葛看球").append(getMD()).append("会员推荐\n\n")
            sb.append("重心单 ")
                    .append(mainBean.liansai)
                    .append(" ")
                    .append(mainBean.time)
                    .append(" ")
                    .append(mainBean.zhu)
                    .append("VS")
                    .append(mainBean.ke)
                    .append("\n")
            sb.append("推荐：").append(mainBean.ke)
            if (mainBean.yList[0].endPan.toFloat() > 0) {
                sb.append("+")
                sb.append(mainBean.yList[0].endPan)
            } else if (mainBean.yList[0].endPan.toFloat() < 0) {
                sb.append(mainBean.yList[0].endPan)
            } else {
                sb.append("平手")
            }
            sb.append("\n\n").append("建议: 稳胆单 双注，实力单 均注！！！")
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(null, sb.toString())
            clipboard.primaryClip = clipData
            Toast.makeText(this, "诸葛重心单已经复制到粘贴板", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.dzButton).onClick {
            val sb = StringBuffer()
            sb.append("貂蝉看盘").append(getMYDR()).append("足球推荐\n\n")
            sb.append(mainBean.liansai)
                    .append(" ")
                    .append(mainBean.time)
                    .append(" ")
                    .append(mainBean.zhu)
                    .append("VS")
                    .append(mainBean.ke)
                    .append("\n")
            sb.append("推荐：").append(mainBean.zhu)
            if (mainBean.yList[0].endPan.toFloat() > 0) {
                sb.append("-")
                sb.append(mainBean.yList[0].endPan)
            } else if (mainBean.yList[0].endPan.toFloat() < 0) {
                sb.append("+")
                sb.append(mainBean.yList[0].endPan.substring(1))
            } else {
                sb.append("平手")
            }
            sb.append("  【重心单】")
            sb.append("\n\n").append("温馨提示：信心推荐，常跟才是王道")
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(null, sb.toString())
            clipboard.primaryClip = clipData
            Toast.makeText(this, "貂蝉重心单已经复制到粘贴板", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.dkButton).onClick {
            val sb = StringBuffer()
            sb.append("貂蝉看盘").append(getMYDR()).append("足球推荐\n\n")
            sb.append(mainBean.liansai)
                    .append(" ")
                    .append(mainBean.time)
                    .append(" ")
                    .append(mainBean.zhu)
                    .append("VS")
                    .append(mainBean.ke)
                    .append("\n")
            sb.append("推荐：").append(mainBean.ke)
            if (mainBean.yList[0].endPan.toFloat() > 0) {
                sb.append("+")
                sb.append(mainBean.yList[0].endPan)
            } else if (mainBean.yList[0].endPan.toFloat() < 0) {
                sb.append(mainBean.yList[0].endPan)
            } else {
                sb.append("平手")
            }
            sb.append("  【重心单】")
            sb.append("\n\n").append("温馨提示：信心推荐，常跟才是王道")
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(null, sb.toString())
            clipboard.primaryClip = clipData
            Toast.makeText(this, "貂蝉重心单已经复制到粘贴板", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.szButton).onClick {
            val sb = StringBuffer()
            sb.append("〔大师兄").append(getMYDR()).append("会员推荐〕\n\n")
            sb.append(mainBean.liansai)
                    .append(" ")
                    .append(mainBean.time)
                    .append(" ")
                    .append(mainBean.zhu)
                    .append("VS")
                    .append(mainBean.ke)
                    .append("\n")
            sb.append("推荐：").append(mainBean.zhu)
            if (mainBean.yList[0].endPan.toFloat() > 0) {
                sb.append("-")
                sb.append(mainBean.yList[0].endPan)
            } else if (mainBean.yList[0].endPan.toFloat() < 0) {
                sb.append("+")
                sb.append(mainBean.yList[0].endPan.substring(1))
            } else {
                sb.append("平手")
            }
            sb.append("(大圣神威单)")
            sb.append("\n\n").append("彩市有风险，下注需谨慎，神威单双注，冲锋单均注")
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(null, sb.toString())
            clipboard.primaryClip = clipData
            Toast.makeText(this, "大师兄大圣神威单已经复制到粘贴板", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.skButton).onClick {
            val sb = StringBuffer()
            sb.append("〔大师兄").append(getMYDR()).append("会员推荐〕\n\n")
            sb.append(mainBean.liansai)
                    .append(" ")
                    .append(mainBean.time)
                    .append(" ")
                    .append(mainBean.zhu)
                    .append("VS")
                    .append(mainBean.ke)
                    .append("\n")
            sb.append("推荐：").append(mainBean.ke)
            if (mainBean.yList[0].endPan.toFloat() > 0) {
                sb.append("+")
                sb.append(mainBean.yList[0].endPan)
            } else if (mainBean.yList[0].endPan.toFloat() < 0) {
                sb.append("-")
                sb.append(mainBean.yList[0].endPan)
            } else {
                sb.append("平手")
            }
            sb.append("(大圣神威单)")
            sb.append("\n\n").append("彩市有风险，下注需谨慎，神威单双注，冲锋单均注")
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(null, sb.toString())
            clipboard.primaryClip = clipData
            Toast.makeText(this, "大师兄大圣神威单已经复制到粘贴板", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.qzButton).onClick {
            val sb = StringBuffer()
            sb.append("奇侠").append(getMD()).append("龙腾凤翔单\n\n")
            sb.append("〔").append(mainBean.liansai)
                    .append(" ")
                    .append(mainBean.time).append("〕")
                    .append(" ")
                    .append(mainBean.zhu)
                    .append("VS")
                    .append(mainBean.ke)
                    .append("\n")
            sb.append("推荐：").append(mainBean.zhu)
            if (mainBean.yList[0].endPan.toFloat() > 0) {
                sb.append("-")
                sb.append(mainBean.yList[0].endPan)
            } else if (mainBean.yList[0].endPan.toFloat() < 0) {
                sb.append("+")
                sb.append(mainBean.yList[0].endPan.substring(1))
            } else {
                sb.append("平手")
            }
            sb.append("\n\n").append("*本赛事分析支持中国竞彩，仅供参阅，据此购彩，风险自担")
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(null, sb.toString())
            clipboard.primaryClip = clipData
            Toast.makeText(this, "奇侠龙腾凤翔单已经复制到粘贴板", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.qkButton).onClick {
            val sb = StringBuffer()
            sb.append("奇侠").append(getMD()).append("龙腾凤翔单\n\n")
            sb.append("〔").append(mainBean.liansai)
                    .append(" ")
                    .append(mainBean.time).append("〕")
                    .append(" ")
                    .append(mainBean.zhu)
                    .append("VS")
                    .append(mainBean.ke)
                    .append("\n")
            sb.append("推荐：").append(mainBean.ke)
            if (mainBean.yList[0].endPan.toFloat() > 0) {
                sb.append("+")
                sb.append(mainBean.yList[0].endPan)
            } else if (mainBean.yList[0].endPan.toFloat() < 0) {
                sb.append(mainBean.yList[0].endPan)
            } else {
                sb.append("平手")
            }
            sb.append("\n\n").append("*本赛事分析支持中国竞彩，仅供参阅，据此购彩，风险自担")
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(null, sb.toString())
            clipboard.primaryClip = clipData
            Toast.makeText(this, "奇侠龙腾凤翔单已经复制到粘贴板", Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseNear(container: LinearLayout, mainBean: MainBean) {
        container.addView(LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_sai_near, null, false))
        for (i in 0 until Math.min(mainBean.dList.size, 5)) {
            val rootViews = LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_sai, null, false)
            rootViews.findViewById<TextView>(R.id.dateText).text = mainBean.dList[i].date.trim()
            rootViews.findViewById<TextView>(R.id.liansaiText).text = mainBean.dList[i].liansai.trim()
            rootViews.findViewById<TextView>(R.id.zhuText).text = mainBean.dList[i].zhudui.trim()
            rootViews.findViewById<TextView>(R.id.bifenText).text = mainBean.dList[i].zhuPoint.trim() + "-" + mainBean.dList[i].kePoint.trim()
            rootViews.findViewById<TextView>(R.id.keText).text = mainBean.dList[i].kedui.trim()
            if (mainBean.dList[i].zhuPoint.trim().toInt() > mainBean.dList[i].kePoint.trim().toInt()) {
                rootViews.findViewById<TextView>(R.id.resultText).text = "赢"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#FF0000")
            } else if (mainBean.dList[i].zhuPoint.trim().toInt() < mainBean.dList[i].kePoint.trim().toInt()) {
                rootViews.findViewById<TextView>(R.id.resultText).text = "输"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#00FF00")
            } else {
                rootViews.findViewById<TextView>(R.id.resultText).text = "平"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#888888")
            }
            container.addView(rootViews)
        }

        container.addView(LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_sai_near, null, false))
        for (i in 0 until Math.min(mainBean.zList.size, 5)) {
            val rootViews = LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_sai, null, false)
            rootViews.findViewById<TextView>(R.id.dateText).text = mainBean.zList[i].date.trim()
            rootViews.findViewById<TextView>(R.id.liansaiText).text = mainBean.zList[i].liansai.trim()
            rootViews.findViewById<TextView>(R.id.zhuText).text = mainBean.zList[i].zhudui.trim()
            rootViews.findViewById<TextView>(R.id.bifenText).text = mainBean.zList[i].zhuPoint.trim() + "-" + mainBean.zList[i].kePoint.trim()
            rootViews.findViewById<TextView>(R.id.keText).text = mainBean.zList[i].kedui.trim()
            if (mainBean.zList[i].zhuPoint.trim().toInt() > mainBean.zList[i].kePoint.trim().toInt()) {
                rootViews.findViewById<TextView>(R.id.resultText).text = "赢"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#FF0000")
            } else if (mainBean.zList[i].zhuPoint.trim().toInt() < mainBean.zList[i].kePoint.trim().toInt()) {
                rootViews.findViewById<TextView>(R.id.resultText).text = "输"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#00FF00")
            } else {
                rootViews.findViewById<TextView>(R.id.resultText).text = "平"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#888888")
            }
            container.addView(rootViews)
        }

        container.addView(LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_sai_near, null, false))
        for (i in 0 until Math.min(mainBean.kList.size, 5)) {
            val rootViews = LayoutInflater.from(this).inflate(R.layout.fragment_parser_item_sai, null, false)
            rootViews.findViewById<TextView>(R.id.dateText).text = mainBean.kList[i].date.trim()
            rootViews.findViewById<TextView>(R.id.liansaiText).text = mainBean.kList[i].liansai.trim()
            rootViews.findViewById<TextView>(R.id.zhuText).text = mainBean.kList[i].zhudui.trim()
            rootViews.findViewById<TextView>(R.id.bifenText).text = mainBean.kList[i].zhuPoint.trim() + "-" + mainBean.kList[i].kePoint.trim()
            rootViews.findViewById<TextView>(R.id.keText).text = mainBean.kList[i].kedui.trim()
            if (mainBean.kList[i].zhuPoint.trim().toInt() > mainBean.kList[i].kePoint.trim().toInt()) {
                rootViews.findViewById<TextView>(R.id.resultText).text = "赢"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#FF0000")
            } else if (mainBean.kList[i].zhuPoint.trim().toInt() < mainBean.kList[i].kePoint.trim().toInt()) {
                rootViews.findViewById<TextView>(R.id.resultText).text = "输"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#00FF00")
            } else {
                rootViews.findViewById<TextView>(R.id.resultText).text = "平"
                rootViews.findViewById<TextView>(R.id.resultText).textColor = Color.parseColor("#888888")
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
                rootViews.findViewById<TextView>(R.id.rText).textColor = Color.parseColor("#00FF00")
            } else {
                rootViews.findViewById<TextView>(R.id.rText).text = "平"
                rootViews.findViewById<TextView>(R.id.rText).textColor = Color.parseColor("#000000")
            }
        } else {
            rootViews.findViewById<TextView>(R.id.rText).text = "未"
            rootViews.findViewById<TextView>(R.id.rText).textColor = Color.parseColor("#000000")
        }
        rootViews.findViewById<LinearLayout>(R.id.parseYaLayout).visibility = View.VISIBLE
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
                    rootViews.findViewById<TextView>(R.id.endZRate).textColor = Color.parseColor("#00FF00")
                } else {
                    rootViews.findViewById<TextView>(R.id.endZRate).textColor = Color.parseColor("#000000")
                }
                rootViews.findViewById<TextView>(R.id.endPan).text = mainBean.yList[i].endPan.trim()
                if (mainBean.yList[i].endPan.toFloat() > mainBean.yList[i].startPan.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endPan).textColor = Color.parseColor("#FF0000")
                } else if (mainBean.yList[i].endPan.toFloat() < mainBean.yList[i].startPan.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endPan).textColor = Color.parseColor("#00FF00")
                } else {
                    rootViews.findViewById<TextView>(R.id.endPan).textColor = Color.parseColor("#000000")
                }
                rootViews.findViewById<TextView>(R.id.endKRate).text = mainBean.yList[i].endKRate.trim()
                if (mainBean.yList[i].endKRate.toFloat() > mainBean.yList[i].startKRate.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endKRate).textColor = Color.parseColor("#FF0000")
                } else if (mainBean.yList[i].endKRate.toFloat() < mainBean.yList[i].startKRate.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endKRate).textColor = Color.parseColor("#00FF00")
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
                    rootViews.findViewById<TextView>(R.id.endSRate).textColor = Color.parseColor("#00FF00")
                } else {
                    rootViews.findViewById<TextView>(R.id.endSRate).textColor = Color.parseColor("#000000")
                }
                rootViews.findViewById<TextView>(R.id.startPRate).text = mainBean.oList[i].startP
                rootViews.findViewById<TextView>(R.id.endPRate).text = mainBean.oList[i].endP
                if (mainBean.oList[i].endP.toFloat() > mainBean.oList[i].startP.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endPRate).textColor = Color.parseColor("#FF0000")
                } else if (mainBean.oList[i].endP.toFloat() < mainBean.oList[i].startP.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endPRate).textColor = Color.parseColor("#00FF00")
                } else {
                    rootViews.findViewById<TextView>(R.id.endPRate).textColor = Color.parseColor("#000000")
                }
                rootViews.findViewById<TextView>(R.id.startFRate).text = mainBean.oList[i].startF
                rootViews.findViewById<TextView>(R.id.endFRate).text = mainBean.oList[i].endF
                if (mainBean.oList[i].endF.toFloat() > mainBean.oList[i].startF.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endFRate).textColor = Color.parseColor("#FF0000")
                } else if (mainBean.oList[i].endF.toFloat() < mainBean.oList[i].startF.toFloat()) {
                    rootViews.findViewById<TextView>(R.id.endFRate).textColor = Color.parseColor("#00FF00")
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
}
