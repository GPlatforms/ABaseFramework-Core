package android.baseframework.core.example.webview

import android.baseframework.core.base.webview.BaseWebViewActivity
import android.baseframework.core.example.R
import android.baseframework.core.utils.startActivity
import android.content.Intent
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_webview_simple_recyclerview.*

/**
 * Created by Neil Zheng on 2017/6/26.
 */
class MySimpleRecyclerAdapter(val list: Array<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(vg: ViewGroup?, position: Int): RecyclerView.ViewHolder {
        return MyViewHolder(View.inflate(vg?.context, R.layout.item_webview_simple_recyclerview, null));
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is MyViewHolder) {
            holder.button.text = list[position]
            holder.button.setOnClickListener {
                val intent = Intent(holder.itemView.context, SimpleWebViewActivity::class.java)
                intent.putExtra(BaseWebViewActivity.EXTRA_URL, list[position])
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    fun getItem(position: Int): String {
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var button: AppCompatButton = view.findViewById(R.id.button) as AppCompatButton

    }

}