package site.tsun.kotlinexamples.other.architecture.livedata

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import site.tsun.kotlinexamples.R
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_live_data.*
import android.support.v7.widget.DividerItemDecoration
import android.view.View


class LiveDataActivity : AppCompatActivity() {

    private lateinit var viewModel: LiveDataViewModel
    private lateinit var adapter: MyRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        viewModel = ViewModelProviders.of(this).get(LiveDataViewModel::class.java)
        adapter = MyRecyclerAdapter(this, viewModel.getUsers().value!!)
        rvMain.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMain.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(this,
                linearLayoutManager.orientation)
        rvMain.addItemDecoration(dividerItemDecoration)
        subscribe()
        initEvent()
    }

    private fun subscribe() {
        val userObserver = Observer<MutableList<User>> {
            users: MutableList<User>? ->
            if (users != null) {
                adapter.changeData(users)
            }
        }
        viewModel.getUsers().observe(this, userObserver)
    }

    private fun initEvent()
    {
        btnLiveData.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                viewModel.updateUsers()
            }
        })
    }
}
