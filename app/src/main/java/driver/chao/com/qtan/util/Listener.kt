package driver.chao.com.qtan.util

import driver.chao.com.qtan.bean.MainBean

/**
 * Created by aaron on 2018/9/17.
 */
interface TanListener {
    fun onTanListener()
}

interface TanCompleteListener {
    fun onTanLoadMainDataListener()

    fun onTanLoadMainDataCompleteListener(total: Int)

    fun onTanLoadYaDataListener()

    fun onTanLoadOuDataListener()

    fun onTanLoadRaDataListener()

    fun onTanCompleteListener(mDataList: List<MainBean>)
}