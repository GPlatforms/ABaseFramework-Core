package android.baseframework.core.base

import android.baseframework.core.R
import android.baseframework.core.config.BCConstant
import android.baseframework.core.widget.BCEmptyView
import android.baseframework.core.widget.BCErrorView
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jayfeng.lesscode.core.AdapterLess
import com.jayfeng.lesscode.core.other.DividerItemDecoration
import com.scwang.smartrefresh.layout.SmartRefreshLayout


abstract class BCListFragment<T> : BCFragment() {

    protected val PAGE_LIMIT = 10

    protected var mListData: MutableList<T>? = ArrayList()

    protected var mRefreshLayout: SmartRefreshLayout? = null
    protected var mRecyclerView: RecyclerView? = null
    protected var mLayoutManager: RecyclerView.LayoutManager? = null
    protected lateinit var mAdapter: RecyclerView.Adapter<AdapterLess.RecyclerViewHolder>
    protected lateinit var mDividerItemDecoration: DividerItemDecoration
    protected var mEmptyView: BCEmptyView? = null
    protected var mErrorView: BCErrorView? = null

    protected var mPage = 1
    protected var mIsLoadingMore = false

    protected fun initListView(rootView: View) {
        mRefreshLayout = rootView.findViewById<SmartRefreshLayout>(R.id.refreshLayout)
        mRefreshLayout!!.isEnableRefresh = isEnableRefresh()
        mRefreshLayout!!.isEnableLoadmore = isEnableLoadMore()
        mRefreshLayout!!.isEnableAutoLoadmore = isEnableAutoLoadMore()

        mRecyclerView = view!!.findViewById(R.id.recyclerview)

        initLayoutManager()

        mEmptyView = rootView.findViewById<BCEmptyView>(R.id.empty_view)
        mErrorView = rootView.findViewById<BCErrorView>(R.id.error_view)
        mEmptyView!!.setEmptyText(getEmptyText())

        mRefreshLayout!!.setOnLoadmoreListener {
            moreData()
        }
    }

    protected fun initLayoutManager() {
        // default LinearLayoutManager
        mLayoutManager = LinearLayoutManager(context)
        mRecyclerView?.layoutManager = mLayoutManager

        mDividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST, getDividerDrawable())
        if (getDividerWidth() != -1) {
            mDividerItemDecoration.setWidth(getDividerWidth())
        }
        if (getDividerHeight() != -1) {
            mDividerItemDecoration.setHeight(getDividerHeight())
        }
        mRecyclerView?.addItemDecoration(mDividerItemDecoration)
    }

    protected fun moreList(dataList: List<T>?) {
        if (dataList == null || dataList.size == 0) {
            noMorePage()
        } else if (dataList.size < PAGE_LIMIT) {
            noMorePage()
            moreListToView(dataList)
        } else {
            moreListToView(dataList)
        }
    }

    protected fun moreListToView(dataList: List<T>) {
        mListData!!.addAll(dataList)
        mAdapter!!.notifyDataSetChanged()
    }

    fun noMorePage() {
    }

    fun isLoadingMore(): Boolean {
        return mIsLoadingMore
    }

    fun resetPage() {
        mPage = 1
    }

    protected fun requestData() {}

    protected fun moreData() {}

    protected fun emptyOrErrorView(errorType: Int) {
        if (errorType == BCConstant.EMPTY_TYPE_EMPTY) {
            mEmptyView?.visibility = View.VISIBLE
            mEmptyView?.setEmptyText(R.string.bc_empty_empty_text)
        } else if (errorType == BCConstant.EMPTY_TYPE_NETWORK) {
            mEmptyView?.visibility = View.VISIBLE
            mEmptyView?.setEmptyText(R.string.bc_empty_network_text)
        } else if (errorType == BCConstant.EMPTY_TYPE_ERROR) {
            mEmptyView?.visibility = View.VISIBLE
            mEmptyView?.setEmptyText(R.string.bc_empty_error_text)
        } else {
            mEmptyView?.visibility = View.GONE
        }
    }

    protected fun getEmptyText(): String {
        return ""
    }

    protected fun getDividerDrawable(): Drawable {
        return ColorDrawable(Color.parseColor("#66000000"))
    }

    protected fun getDividerWidth(): Int {
        return -1
    }

    protected fun getDividerHeight(): Int {
        return 0
    }

    protected fun isEnableRefresh(): Boolean {
        return true
    }

    protected fun isEnableLoadMore(): Boolean {
        return true
    }

    protected fun isEnableAutoLoadMore(): Boolean {
        return true
    }
}