package site.tsun.kotlinexamples.other.architecture.lifecycles

import android.arch.lifecycle.ViewModel


class ChronometerViewModel : ViewModel() {
    private var mStartTime: Long? = null

    fun getStartTime() = mStartTime

    fun setStartTime(startTime: Long) {
        this.mStartTime = startTime
    }
}
