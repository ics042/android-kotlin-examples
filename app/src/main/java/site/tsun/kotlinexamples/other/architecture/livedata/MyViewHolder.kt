package site.tsun.kotlinexamples.other.architecture.livedata

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import site.tsun.kotlinexamples.R


class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var tv: TextView

    init {
        tv =  itemView.findViewById(R.id.tvRecyclerItem)

    }
}