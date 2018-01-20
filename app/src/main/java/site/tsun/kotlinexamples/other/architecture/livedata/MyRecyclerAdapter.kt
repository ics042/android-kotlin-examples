package site.tsun.kotlinexamples.other.architecture.livedata

import android.content.Context
import android.support.v7.widget.RecyclerView
import site.tsun.kotlinexamples.R
import android.view.LayoutInflater
import android.view.ViewGroup


class MyRecyclerAdapter(private val context: Context, private val list: MutableList<User>) : RecyclerView.Adapter<MyViewHolder>() {

    private var inflater:LayoutInflater = LayoutInflater.from(this.context);

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv.setText(list.get(position).name)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun changeData(newUsers: MutableList<User>) {
        list.clear()
        list.addAll(newUsers)
        notifyDataSetChanged()
    }

}

