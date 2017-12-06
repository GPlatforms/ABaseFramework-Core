package android.baseframework.core.base

import android.baseframework.core.R
import android.os.Bundle
import com.jayfeng.lesscode.core.ViewLess
import com.jayfeng.lesscode.headerbar.HeaderBar
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import me.yokeyword.fragmentation.ISupportActivity
import me.yokeyword.fragmentation.SupportActivityDelegate
import me.yokeyword.fragmentation.SupportHelper
import me.yokeyword.fragmentation.ISupportFragment
import android.support.annotation.DrawableRes
import me.yokeyword.fragmentation.anim.FragmentAnimator
import android.view.MotionEvent
import me.yokeyword.fragmentation.ExtraTransaction


open class BCActivity : RxAppCompatActivity(), ISupportActivity {

    val mDelegate = SupportActivityDelegate(this)

    val mHeaderBar by lazy { findViewById<HeaderBar>(R.id.header) }

    /**
     * *****************************************************************
     * optional init base views: header bar, loading view
     * *****************************************************************
     */
    @JvmOverloads
    open fun initHeaderBar(titleId: Int, showBack: Boolean = false) {
        initHeaderBar(getString(titleId), showBack)
    }

    @JvmOverloads
    open fun initHeaderBar(title: String, showBack: Boolean = false) {
        with(mHeaderBar) {
            setTitle(title)
            if (showBack) {
                showBack { onBackPressed() }
            }
        }
    }

    /**
     * *****************************************************************
     * Fragmentation
     * *****************************************************************
     */
    override fun getSupportDelegate(): SupportActivityDelegate {
        return mDelegate
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    override fun extraTransaction(): ExtraTransaction {
        return mDelegate.extraTransaction()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDelegate.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mDelegate.onPostCreate(savedInstanceState)
    }

    override fun onDestroy() {
        mDelegate.onDestroy()
        super.onDestroy()
    }

    /**
     * Note： return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev)
    }

    /**
     * 不建议复写该方法,请使用 [.onBackPressedSupport] 代替
     */
    override fun onBackPressed() {
        mDelegate.onBackPressed()
    }

    /**
     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
     */
    override fun onBackPressedSupport() {
        mDelegate.onBackPressedSupport()
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    override fun getFragmentAnimator(): FragmentAnimator {
        return mDelegate.fragmentAnimator
    }

    /**
     * Set all fragments animation.
     * 设置Fragment内的全局动画
     */
    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator) {
        mDelegate.fragmentAnimator = fragmentAnimator
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     *
     *
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return mDelegate.onCreateFragmentAnimator()
    }
}