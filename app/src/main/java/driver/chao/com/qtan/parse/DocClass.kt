package driver.chao.com.qtan.parse

import driver.chao.com.qtan.bean.MainBean
import driver.chao.com.qtan.util.getMD
import driver.chao.com.qtan.util.getMYDR

/**
 * Created by aaron on 2018/9/18.
 *
 */
fun zhuZhuZhong(mainBean: MainBean): String {
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
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        sb.append("+")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(1, endPan.length - 2))
        } else {
            sb.append(endPan.substring(1))
        }
    } else {
        sb.append("平手")
    }
    sb.append("\n\n").append("建议: 稳胆单 双注，实力单 均注！！！")

    return sb.toString()
}

fun zhuZhuLiu(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("诸葛看球").append(getMD()).append("渠道推荐\n\n")
    sb.append("琉璃翡翠单\n")
            .append("联赛：").append(mainBean.liansai).append("\n")
            .append("时间：").append(mainBean.time).append("\n")
            .append("场次：").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
    sb.append("方向：").append(mainBean.zhu)
    if (mainBean.yList[0].endPan.toFloat() > 0) {
        sb.append("-")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        sb.append("+")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(1, endPan.length - 2))
        } else {
            sb.append(endPan.substring(1))
        }
    } else {
        sb.append("平手")
    }
    sb.append("\n\n").append("*本赛事分析支持中国竞彩，仅供参阅，据此购彩，风险自担")

    return sb.toString()
}

fun zhuKeZhong(mainBean: MainBean): String {
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
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else {
        sb.append("平手")
    }
    sb.append("\n\n").append("建议: 稳胆单 双注，实力单 均注！！！")

    return sb.toString()
}

fun zhuKeLiu(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("诸葛看球").append(getMD()).append("渠道推荐\n\n")
    sb.append("琉璃翡翠单\n")
            .append("联赛：").append(mainBean.liansai).append("\n")
            .append("时间：").append(mainBean.time).append("\n")
            .append("场次：").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
    sb.append("方向：").append(mainBean.ke)
    if (mainBean.yList[0].endPan.toFloat() > 0) {
        sb.append("+")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else {
        sb.append("平手")
    }
    sb.append("\n\n").append("*本赛事分析支持中国竞彩，仅供参阅，据此购彩，风险自担")

    return sb.toString()
}

fun diaoZhuZhong(mainBean: MainBean): String {
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
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        sb.append("+")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(1, endPan.length - 2))
        } else {
            sb.append(endPan.substring(1))
        }
    } else {
        sb.append("平手")
    }
    sb.append("  【重心单】")
    sb.append("\n\n").append("温馨提示：信心推荐，常跟才是王道")

    return sb.toString()
}

fun diaoZhuFu(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("貂蝉看盘").append(getMYDR()).append("福利推荐\n\n")
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
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        sb.append("+")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(1, endPan.length - 2))
        } else {
            sb.append(endPan.substring(1))
        }
    } else {
        sb.append("平手")
    }
    sb.append("  【福利单】")
    sb.append("\n\n").append("温馨提示：信心推荐，常跟才是王道")

    return sb.toString()
}

fun diaoKeZhong(mainBean: MainBean): String {
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
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else {
        sb.append("平手")
    }
    sb.append("  【重心单】")
    sb.append("\n\n").append("温馨提示：信心推荐，常跟才是王道")

    return sb.toString()
}

fun diaoKeFu(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("貂蝉看盘").append(getMYDR()).append("福利推荐\n\n")
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
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else {
        sb.append("平手")
    }
    sb.append("  【福利单】")
    sb.append("\n\n").append("温馨提示：信心推荐，常跟才是王道")

    return sb.toString()
}

fun shiZhuShen(mainBean: MainBean): String {
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
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        sb.append("+")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(1, endPan.length - 2))
        } else {
            sb.append(endPan.substring(1))
        }
    } else {
        sb.append("平手")
    }
    sb.append("(大圣神威单)")
    sb.append("\n\n").append("彩市有风险，下注需谨慎，神威单双注，冲锋单均注")

    return sb.toString()
}

fun shiZhuFu(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("〔大师兄").append(getMYDR()).append("福利推荐〕\n\n")
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
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        sb.append("+")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(1, endPan.length - 2))
        } else {
            sb.append(endPan.substring(1))
        }
    } else {
        sb.append("平手")
    }
    sb.append("(福利单)")
    sb.append("\n\n").append("彩市有风险，下注需谨慎，神威单双注，冲锋单均注")

    return sb.toString()
}

fun shiKeShen(mainBean: MainBean): String {
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
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else {
        sb.append("平手")
    }
    sb.append("(大圣神威单)")
    sb.append("\n\n").append("彩市有风险，下注需谨慎，神威单双注，冲锋单均注")

    return sb.toString()
}

fun shiKeFu(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("〔大师兄").append(getMYDR()).append("福利推荐〕\n\n")
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
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else {
        sb.append("平手")
    }
    sb.append("(福利单)")
    sb.append("\n\n").append("彩市有风险，下注需谨慎，神威单双注，冲锋单均注")

    return sb.toString()
}

fun qiZhuLong(mainBean: MainBean): String {
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
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        sb.append("+")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(1, endPan.length - 2))
        } else {
            sb.append(endPan.substring(1))
        }
    } else {
        sb.append("平手")
    }
    sb.append("\n\n").append("*本赛事分析支持中国竞彩，仅供参阅，据此购彩，风险自担")

    return sb.toString()
}

fun qiKeLong(mainBean: MainBean): String {
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
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else {
        sb.append("平手")
    }
    sb.append("\n\n").append("*本赛事分析支持中国竞彩，仅供参阅，据此购彩，风险自担")

    return sb.toString()
}

fun guiZhuJing(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append(getMD()).append("鬼谷子精算单推荐\n\n")
    sb.append(mainBean.liansai).append(" ").append(mainBean.time)
            .append(" ").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
    sb.append("推荐：").append(mainBean.zhu)
    if (mainBean.yList[0].endPan.toFloat() > 0) {
        sb.append("-")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        sb.append("+")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(1, endPan.length - 2))
        } else {
            sb.append(endPan.substring(1))
        }
    } else {
        sb.append("平手")
    }

    sb.append("(精算单)\n\n").append("建议：彩市有风险，长跟有保证")

    return sb.toString()
}

fun guiKeJing(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append(getMD()).append("鬼谷子精算单推荐\n\n")
    sb.append(mainBean.liansai).append(" ").append(mainBean.time)
            .append(" ").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
    sb.append("推荐：").append(mainBean.ke)
    if (mainBean.yList[0].endPan.toFloat() > 0) {
        sb.append("+")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else {
        sb.append("平手")
    }

    sb.append("(精算单)\n\n").append("建议：彩市有风险，长跟有保证")

    return sb.toString()
}


fun guiZhuShen(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append(getMD()).append("鬼谷子神隐单推荐\n\n")
    sb.append(mainBean.liansai).append(" ").append(mainBean.time)
            .append(" ").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
    sb.append("推荐：").append(mainBean.zhu)
    if (mainBean.yList[0].endPan.toFloat() > 0) {
        sb.append("-")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        sb.append("+")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(1, endPan.length - 2))
        } else {
            sb.append(endPan.substring(1))
        }
    } else {
        sb.append("平手")
    }

    sb.append("(神隐单)\n\n").append("建议：彩市有风险，长跟有保证")

    return sb.toString()
}

fun guiKeShen(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append(getMD()).append("鬼谷子神隐单推荐\n\n")
    sb.append(mainBean.liansai).append(" ").append(mainBean.time)
            .append(" ").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
    sb.append("推荐：").append(mainBean.ke)
    if (mainBean.yList[0].endPan.toFloat() > 0) {
        sb.append("+")
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else if (mainBean.yList[0].endPan.toFloat() < 0) {
        val endPan = mainBean.yList[0].endPan
        if (endPan.endsWith(".0")) {
            sb.append(endPan.substring(0, endPan.length - 2))
        } else {
            sb.append(endPan)
        }
    } else {
        sb.append("平手")
    }

    sb.append("(神隐单)\n\n").append("建议：彩市有风险，长跟有保证")

    return sb.toString()
}