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

    open val PAGE_LIMIT = 10

    open val mListData: MutableList<T> = arrayListOf()

    open val mRefreshLayout: SmartRefreshLayout by lazy { mRootView.findViewById<SmartRefreshLayout>(R.id.refreshLayout) }
    open val mRecyclerView: RecyclerView by lazy { mRootView.findViewById<RecyclerView>(R.id.recyclerview) }
    open var mLayoutManager: RecyclerView.LayoutManager? = null
    open lateinit var mAdapter: BaseQuickAdapter<T, BaseViewHolder>
    open lateinit var mDividerItemDecoration: DividerItemDecoration
    open lateinit var mStateView: BCStateView

    open var mPage = 1
    open var mIsLoadingMore = false
    open var mEmptyText = ""

    open var mRefreshEnableRefresh = true
    open var mRefreshEnableLoadMore = true
    open var mRefreshEnableAutoMore = true
    open var mRefreshEnableDivider = true
    open var mDividerDrawable: Drawable = ColorDrawable(Color.parseColor("#66000000"))
    open var mDividerWidth = -1
    open var mDividerHeight = 0

    open fun initStateView() {
        mStateView = BCStateView(context).apply {
            showLoading()
        }
    }

    open fun initListView() {
        with(mRefreshLayout) {
            isEnableRefresh = mRefreshEnableRefresh
            isEnableLoadmore = mRefreshEnableLoadMore
            isEnableAutoLoadmore = mRefreshEnableAutoMore

            initLayoutManager()

            setOnRefreshLoadmoreListener(object : OnRefreshLoadmoreListener {
                override fun onLoadmore(refreshlayout: RefreshLayout) {
                    moreData()
                }

                override fun onRefresh(refreshlayout: RefreshLayout) {
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

        if (mRefreshEnableDivider) {
            mDividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST, mDividerDrawable)
            if (mDividerWidth != -1) {
                mDividerItemDecoration.setWidth(mDividerWidth)
            }
            if (mDividerHeight != -1) {
                mDividerItemDecoration.setHeight(mDividerHeight)
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

    fun resetPage() {
        mPage = 1
    }
    
    open fun showEmptyView() {}

    open fun requestData() {}

    open fun moreData() {}
}