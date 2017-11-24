package android.baseframework.core.base

import android.baseframework.core.R
import android.baseframework.core.widget.BCStateView
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jayfeng.lesscode.core.other.DividerItemDecoration
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener

open class BCListActivity<T> : BCActivity() {

    protected val PAGE_LIMIT = 10

    protected var mListData: MutableList<T>? = ArrayList()

    protected var mRefreshLayout: SmartRefreshLayout? = null
    protected var mRecyclerView: RecyclerView? = null
    protected var mLayoutManager: RecyclerView.LayoutManager? = null
    protected lateinit var mAdapter: BaseQuickAdapter<T, BaseViewHolder>
    protected lateinit var mDividerItemDecoration: DividerItemDecoration
    protected lateinit var mStateView: BCStateView

    protected var mPage = 1
    protected var mIsLoadingMore = false

    open fun initStateView() {
        mStateView = BCStateView(this)
        mStateView.showLoading()
    }

    open fun initListView() {
        mRefreshLayout = findViewById<SmartRefreshLayout>(R.id.refreshLayout)
        mRefreshLayout?.isEnableRefresh = isEnableRefresh()
        mRefreshLayout?.isEnableLoadmore = isEnableLoadMore()
        mRefreshLayout?.isEnableAutoLoadmore = isEnableAutoLoadMore()

        mRecyclerView = findViewById(R.id.recyclerview)

        initLayoutManager()

        mRefreshLayout?.setOnRefreshLoadmoreListener(object : OnRefreshLoadmoreListener {
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                moreData()
            }

            override fun onRefresh(refreshlayout: RefreshLayout?) {
                resetPage()
                requestData()
            }
        })
    }

    open fun initLayoutManager() {
        // default LinearLayoutManager
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView?.layoutManager = mLayoutManager

        if (isEnableDivider()) {
            mDividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, getDividerDrawable())
            if (getDividerWidth() != -1) {
                mDividerItemDecoration.setWidth(getDividerWidth())
            }
            if (getDividerHeight() != -1) {
                mDividerItemDecoration.setHeight(getDividerHeight())
            }
            mRecyclerView?.addItemDecoration(mDividerItemDecoration)
        }
    }

    open fun moreList(dataList: List<T>?) {
        if (dataList == null || dataList.size == 0) {
            noMorePage()
        } else if (dataList.size < PAGE_LIMIT) {
            noMorePage()
            moreListToView(dataList)
        } else {
            moreListToView(dataList)
        }
    }

    open fun moreListToView(dataList: List<T>) {
        mListData!!.addAll(dataList)
        mAdapter.notifyDataSetChanged()
    }

    open fun noMorePage() {
    }

    open fun isLoadingMore(): Boolean {
        return mIsLoadingMore
    }

    open fun resetPage() {
        mPage = 1
    }

    open fun requestData() {}

    open fun moreData() {}

    open fun getDividerDrawable(): Drawable {
        return ColorDrawable(Color.parseColor("#66000000"))
    }

    open fun getDividerWidth(): Int {
        return -1
    }

    open fun getDividerHeight(): Int {
        return 0
    }

    open fun isEnableDivider(): Boolean {
        return true
    }

    open fun isEnableRefresh(): Boolean {
        return true
    }

    open fun isEnableLoadMore(): Boolean {
        return true
    }

    open fun isEnableAutoLoadMore(): Boolean {
        return true
    }
}