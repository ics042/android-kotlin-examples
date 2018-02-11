package site.tsun.kotlinexamples.library.rxjava.simpleretrofit

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
import kotlinx.android.synthetic.main.activity_rxjava.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import site.tsun.kotlinexamples.R

class RxJavaActivity : AppCompatActivity(){
    private val BASE_URL = "http://gank.io/api/"

    private var compositeDisposable: CompositeDisposable? = null
    private var gankDataArrayList: ArrayList<GankDataModel>? = null
    private var adapter: MyRecyclerAdapter = MyRecyclerAdapter()
    private var subscribe: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava)
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
                .build().create(GankApiInterface::class.java)

        compositeDisposable?.add(requestInterface.getCategoryData("Android", 10, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))
    }
    private fun handleResponse(gankResult: GankResult) {

        adapter.dataList = gankResult.results
        subscribe = adapter.clickEvent
                .subscribe({
                    Toast.makeText(this, "Clicked on $it", Toast.LENGTH_LONG).show()
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