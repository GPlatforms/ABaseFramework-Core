package android.baseframework.core.example

import android.baseframework.core.base.BCListActivity
import android.baseframework.core.example.model.User
import android.os.Bundle
import android.widget.TextView
import com.jayfeng.lesscode.core.AdapterLess

class SmartRefreshActivity : BCListActivity<User>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_refresh)

        initListView()
        initLayoutManager()

        initData()
    }

    fun initData() {

        for (i in 1..100) {
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
    }
}
