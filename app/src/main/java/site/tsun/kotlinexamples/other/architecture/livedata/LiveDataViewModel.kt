package site.tsun.kotlinexamples.other.architecture.livedata

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


class LiveDataViewModel : ViewModel() {
    lateinit var users: MutableLiveData<MutableList<User>>

    fun getUsers(): LiveData<MutableList<User>> {
        users = MutableLiveData<MutableList<User>>()
        loadUsers()
        return users
    }

    private fun loadUsers() {
        users.value = mutableListOf(
                User("zhangsan", 21),
                User("lisi", 24),
                User("wangwu", 24)
        )
    }

    fun updateUsers() {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        users.value = mutableListOf(site.tsun.kotlinexamples.other.architecture.livedata.User("hello", 21),
                site.tsun.kotlinexamples.other.architecture.livedata.User("world", 24),
                site.tsun.kotlinexamples.other.architecture.livedata.User("it's me", 25),
                site.tsun.kotlinexamples.other.architecture.livedata.User(df.format(Date()), 24))
    }
}
