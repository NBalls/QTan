package driver.chao.com.qtan.parse

import driver.chao.com.qtan.bean.MainBean
import driver.chao.com.qtan.util.getMD
import driver.chao.com.qtan.util.getMYDR
import driver.chao.com.qtan.util.getYMD

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

fun faZhuXiu(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("发哥").append(getMYDR()).append("修罗单推荐\n\n")
    sb.append(mainBean.liansai).append(" ").append(mainBean.time)
            .append(" ").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
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

    sb.append("\n\n").append("建议：彩市有风险，投资勿梭哈")

    return sb.toString()
}

fun faKeXiu(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("发哥").append(getMYDR()).append("修罗单推荐\n\n")
    sb.append(mainBean.liansai).append(" ").append(mainBean.time)
            .append(" ").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
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

    sb.append("\n\n").append("建议：彩市有风险，投资勿梭哈")

    return sb.toString()
}

fun faZhuLiao(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("发哥").append(getMYDR()).append("料王单推荐\n\n")
    sb.append(mainBean.liansai).append(" ").append(mainBean.time)
            .append(" ").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
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

    sb.append("\n\n").append("建议：彩市有风险，投资勿梭哈")

    return sb.toString()
}

fun faKeLiao(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("发哥").append(getMYDR()).append("料王单推荐\n\n")
    sb.append(mainBean.liansai).append(" ").append(mainBean.time)
            .append(" ").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
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

    sb.append("\n\n").append("建议：彩市有风险，投资勿梭哈")

    return sb.toString()
}

fun chongZhuNi(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append(getMYDR()).append("三叉戟-逆转乾坤单\n\n")
    sb.append(mainBean.liansai).append(" ").append(mainBean.time)
            .append(" ").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
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

    sb.append("【逆转乾坤单】\n\n").append("*温馨提示：信心推荐，长跟是王道")

    return sb.toString()
}

fun chongKeNi(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append(getMYDR()).append("魔尊重楼-逆转乾坤单\n\n")
    sb.append(mainBean.liansai).append(" ").append(mainBean.time)
            .append(" ").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
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

    sb.append("【逆转乾坤单】\n\n").append("*温馨提示：信心推荐，长跟是王道")

    return sb.toString()
}

fun guoshiZhuBo(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("国师【铂金单】\n\n")
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

    sb.append("\n\n").append("温馨提示：本赛事分析支持中国竞彩，仅供参阅，均码投注，切勿梭哈")

    return sb.toString()
}

fun guoshiKeBo(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("国师【铂金单】\n\n")
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
    sb.append("\n\n").append("温馨提示：本赛事分析支持中国竞彩，仅供参阅，均码投注，切勿梭哈")

    return sb.toString()
}

fun guoshiZhuZuan(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("国师【钻石单】\n\n")
    sb.append(mainBean.liansai).append(" ").append(mainBean.time)
            .append(" ").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
    sb.append("单场方向：").append(mainBean.zhu)
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

    sb.append("\n\n").append("※中国竞彩赛事分析，彩市有风险，入场需谨慎\n" +
            "※红黑并非百分百，不退不补，切勿梭哈或倍投，建设理性投资，长跟长赢。")

    return sb.toString()
}

fun guoshiKeZuan(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("国师【钻石单】\n\n")
    sb.append(mainBean.liansai).append(" ").append(mainBean.time)
            .append(" ").append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
    sb.append("单场方向：").append(mainBean.ke)
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
    sb.append("\n\n").append("※中国竞彩赛事分析，彩市有风险，入场需谨慎\n" +
            "※红黑并非百分百，不退不补，切勿梭哈或倍投，建设理性投资，长跟长赢。")

    return sb.toString()
}

fun liaowangZhuZong(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("至尊料王纵横单").append(getYMD()).append("\n\n")
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

    sb.append("\n\n").append("此乃竞彩赛事分析，非比赛最终结果，请勿倍投或梭哈\n" +
            "*支持竞彩就等于支持公益")

    return sb.toString()
}

fun liaowangKeZong(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("至尊料王纵横单").append(getYMD()).append("\n\n")
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
    sb.append("\n\n").append("此乃竞彩赛事分析，非比赛最终结果，请勿倍投或梭哈\n" +
            "*支持竞彩就等于支持公益")

    return sb.toString()
}

fun jinyaZhuYi(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("【移花接木】").append(getMD()).append("赛事分享").append("\n\n")
    sb.append("时间：").append(mainBean.time).append("\n")
    sb.append("联赛：").append(mainBean.liansai).append("\n")
    sb.append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
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

    sb.append("\n\n").append("※中国竞彩赛事分析，彩市有风险，入场需谨慎\n" +
            "※红黑并非百分百，不退不补，切勿梭哈或倍投，建设理性投资，长跟长赢。")

    return sb.toString()
}

fun jinyaKeYi(mainBean: MainBean): String {
    val sb = StringBuffer()
    sb.append("【移花接木】").append(getMD()).append("赛事分享").append("\n\n")
    sb.append("时间：").append(mainBean.time).append("\n")
    sb.append("联赛：").append(mainBean.liansai).append("\n")
    sb.append(mainBean.zhu).append("VS").append(mainBean.ke).append("\n")
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

    sb.append("\n\n").append("※中国竞彩赛事分析，彩市有风险，入场需谨慎\n" +
            "※红黑并非百分百，不退不补，切勿梭哈或倍投，建设理性投资，长跟长赢。")

    return sb.toString()
}