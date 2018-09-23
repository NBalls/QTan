package driver.chao.com.qtan.util

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by aaron on 2018/9/20.
 */
fun runOnIoThread(body:() -> Unit) {
    Observable.create<Boolean> { subscriber ->
        body
        subscriber.onNext(true)
        subscriber.onCompleted()
    }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {})
}