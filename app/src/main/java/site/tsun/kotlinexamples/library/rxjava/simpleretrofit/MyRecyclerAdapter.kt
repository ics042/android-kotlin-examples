package site.tsun.kotlinexamples.library.rxjava.simpleretrofit

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.recycler_rxjava_item.view.*
import site.tsun.kotlinexamples.R

class MyRecyclerAdapter : RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    lateinit var dataList : ArrayList<GankDataModel>

    private val clickSubject = PublishSubject.create<String>()
    val clickEvent : Observable<String> = clickSubject
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_rxjava_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position].desc)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                clickSubject.onNext(dataList[layoutPosition].url)
            }
        }

        fun bind(desc: String) {
            itemView.tvDesc.text = desc
        }
    }
}
