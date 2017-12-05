package android.baseframework.core.base

import android.baseframework.core.R
import android.baseframework.core.widget.BCStateView
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jayfeng.lesscode.core.other.DividerItemDecoration
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener


open class BCListFragment<T> : BCFragment() {

    protected val PAGE_LIMIT = 10

    protected val mListData: MutableList<T> = arrayListOf()

    protected val mRefreshLayout: SmartRefreshLayout by lazy { mRootView.findViewById<SmartRefreshLayout>(R.id.refreshLayout) }
    protected val mRecyclerView: RecyclerView by lazy { mRootView.findViewById<RecyclerView>(R.id.recyclerview) }
    protected var mLayoutManager: RecyclerView.LayoutManager? = null
    protected lateinit var mAdapter: BaseQuickAdapter<T, BaseViewHolder>
    protected lateinit var mDividerItemDecoration: DividerItemDecoration
    protected lateinit var mStateView: BCStateView

    protected var mPage = 1
    protected var mIsLoadingMore = false

    open fun initStateView() {
        mStateView = BCStateView(context)
        mStateView.showLoading()
    }

    open fun initListView() {
        with(mRefreshLayout) {
            isEnableRefresh = isEnableRefresh
            isEnableLoadmore = isEnableLoadMore()
            isEnableAutoLoadmore = isEnableAutoLoadMore()

            initLayoutManager()

            setOnRefreshLoadmoreListener(object : OnRefreshLoadmoreListener {
                override fun onLoadmore(refreshlayout: RefreshLayout?) {
                    moreData()
                }

                override fun onRefresh(refreshlayout: RefreshLayout?) {
                    resetPage()
                    requestData()
                }
            })
        }
    }

    open fun initLayoutManager() {
        // default LinearLayoutManager
        mLayoutManager = LinearLayoutManager(context).also {
            mRecyclerView.layoutManager = it
        }

        if (isEnableDivider()) {
            mDividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST, getDividerDrawable())
            if (getDividerWidth() != -1) {
                mDividerItemDecoration.setWidth(getDividerWidth())
            }
            if (getDividerHeight() != -1) {
                mDividerItemDecoration.setHeight(getDividerHeight())
            }
            mRecyclerView.addItemDecoration(mDividerItemDecoration)
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
        mListData.addAll(dataList)
        mAdapter.notifyDataSetChanged()
    }

    fun noMorePage() {
    }

    fun isLoadingMore(): Boolean {
        return mIsLoadingMore
    }

    fun resetPage() {
        mPage = 1
    }
    
    open fun showEmptyView() {}

    open fun requestData() {}

    open fun moreData() {}

    open fun getEmptyText(): String {
        return ""
    }

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