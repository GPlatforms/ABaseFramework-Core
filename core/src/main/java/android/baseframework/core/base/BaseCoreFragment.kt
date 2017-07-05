package android.baseframework.core.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ikidou.fragmentBackHandler.FragmentBackHandler
import com.orhanobut.logger.Logger

abstract class BaseCoreFragment : Fragment(), IFragment, FragmentBackHandler {

    private var rootView: View? = null
    private var initialized = false
    private var isVisibleToUser = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (null == rootView) {
            rootView = inflater!!.inflate(layoutId, container, false)
            // 解决点击穿透问题
            rootView?.setOnTouchListener { _, _ -> true }
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initIntentData()
        if (isVisibleToUser && !initialized) {
            initData()
            initialized = true
        }
        initListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (null != rootView) {
            (rootView!!.parent as ViewGroup).removeView(rootView)
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        if (isAdded) {
            if (isVisibleToUser) {
                onResumeView()
            } else {
                onPauseView()
            }
        }
    }

    override fun initView() {}

    override fun initListener() {}

    override fun initIntentData() {}

    override fun onResumeView() {
        if (!initialized) {
            initData()
            initialized = true
        }
    }

    override fun onPauseView() {}
}