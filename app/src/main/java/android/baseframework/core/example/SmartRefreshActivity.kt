package android.baseframework.core.example

import android.baseframework.core.base.BCListActivity
import android.baseframework.core.example.model.User
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.jayfeng.lesscode.core.AdapterLess

class SmartRefreshActivity : BCListActivity<User>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_refresh)

        initListView()
        initLayoutManager()

        requestData()
    }

    override fun requestData() {

        mListData?.clear()


        for (i in 1..20) {
            mListData?.add(User("$i", "name$i"))

            mAdapter = AdapterLess.`$recycler`(this, mListData, R.layout.activity_smart_refresh_item, object: AdapterLess.RecyclerCallBack<User> {

                override fun onBindViewHolder(pos: Int, viewHolder: AdapterLess.RecyclerViewHolder, user: User) {
                    var uidView = viewHolder.`$view`<TextView>(R.id.uid)
                    var nicknameView = viewHolder.`$view`<TextView>(R.id.nickname)

                    uidView.text = user.uid
                    nicknameView.text = user.nickname
                }
            })
            mRecyclerView?.adapter = mAdapter

        }

        mRefreshLayout?.finishRefresh()

        Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show()
    }

    override fun moreData() {

        if (mListData?.size!!.compareTo(50) > 0) {

            mRefreshLayout?.isLoadmoreFinished = true

            return
        }

        val size = mListData?.size!!
        for (i in 1..10 step 1) {

            val base = i + size

            mListData?.add(User("$base", "name$base"))

            mAdapter.notifyDataSetChanged()

            mRefreshLayout?.finishLoadmore()
        }
    }
}
