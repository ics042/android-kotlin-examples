package site.tsun.kotlinexamples.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class FrameworkAdapter(private val context: Context, private val data: Array<String>?) : BaseAdapter() {

    override fun getCount(): Int {
        if (data == null) {
            return 0
        } else {
            return data.size
        }
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = TextView(context)
        textView.setPadding(50, 30, 0, 30)
        textView.setTextColor(Color.BLACK)
        textView.textSize = 20f
        if (data != null) {
            textView.text = data[position]
        }
        return textView
    }
}
