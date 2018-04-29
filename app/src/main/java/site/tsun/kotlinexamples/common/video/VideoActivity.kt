package site.tsun.kotlinexamples.common.video

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_video.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import site.tsun.kotlinexamples.R


class VideoActivity : AppCompatActivity() {

    private val BASE_URL = "http://192.168.178.35/"

    private var compositeDisposable: CompositeDisposable? = null
    private var adapter: MyRecyclerAdapter = MyRecyclerAdapter()
    private var subscribe: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        compositeDisposable = CompositeDisposable()

        initRecyclerView()

        loadJSON()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvList.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(this,
                layoutManager.orientation)
        rvList.addItemDecoration(dividerItemDecoration)
    }

    private fun loadJSON() {

        val requestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(VideoApiInterface::class.java)

        compositeDisposable?.add(requestInterface.getVideoData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))
    }
    private fun handleResponse(dataList: ArrayList<String>) {

        adapter.dataList = dataList
        subscribe = adapter.clickEvent
                .subscribe({
                    val newVideoPath = "http://192.168.178.35/video/"+it
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(Uri.parse(newVideoPath), "video/*")
                    startActivity(intent)
                })
        rvList.adapter = adapter
    }

    private fun handleError(error: Throwable) {

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
        Log.e("rxjava",error.localizedMessage)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscribe?.dispose()
    }
}