package site.tsun.kotlinexamples.other.architecture.rxjava.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import site.tsun.kotlinexamples.other.architecture.rxjava.persistence.UserDao

class ViewModelFactory(private val dataSource: UserDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
