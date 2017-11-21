package site.tsun.kotlinexamples.other.architecture.rxjava

import android.content.Context
import site.tsun.kotlinexamples.other.architecture.rxjava.persistence.UserDao
import site.tsun.kotlinexamples.other.architecture.rxjava.persistence.UsersDatabase
import site.tsun.kotlinexamples.other.architecture.rxjava.ui.ViewModelFactory

object Injection {

    fun provideUserDataSource(context: Context): UserDao {
        val database = UsersDatabase.getInstance(context)
        return database.userDao()
    }
    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}
