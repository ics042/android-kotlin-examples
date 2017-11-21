package site.tsun.kotlinexamples.other.architecture.rxjava.ui

import android.arch.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.functions.Action
import io.reactivex.internal.operators.completable.CompletableFromAction
import site.tsun.kotlinexamples.other.architecture.rxjava.persistence.User
import site.tsun.kotlinexamples.other.architecture.rxjava.persistence.UserDao

class UserViewModel(private val dataSource: UserDao) : ViewModel() {

    val USER_ID = "1"

    fun userName(): Flowable<String> {
        return dataSource.getUserById(USER_ID).map { user -> user.userName }
    }

    fun updateUserName(userName: String): Completable {
        return CompletableFromAction(Action {
            val user = User(USER_ID, userName)
            dataSource.insertUser(user)
        })
    }
}
