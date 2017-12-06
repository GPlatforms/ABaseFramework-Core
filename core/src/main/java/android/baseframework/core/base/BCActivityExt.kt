package android.baseframework.core.base

import android.support.annotation.DrawableRes
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportHelper

/**
 * *****************************************************************
 * Fragmentation
 * *****************************************************************
 */
/****************************************以下为可选方法(Optional methods)******************************************************/

/**
 * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
 *
 * @param containerId 容器id
 * @param toFragment  目标Fragment
 */
fun BCActivity.loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
    mDelegate.loadRootFragment(containerId, toFragment)
}

fun BCActivity.loadRootFragment(containerId: Int, toFragment: ISupportFragment, addToBackStack: Boolean, allowAnimation: Boolean) {
    mDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnimation)
}

/**
 * 加载多个同级根Fragment,类似Wechat, QQ主页的场景
 */
fun BCActivity.loadMultipleRootFragment(containerId: Int, showPosition: Int, vararg toFragments: ISupportFragment) {
    mDelegate.loadMultipleRootFragment(containerId, showPosition, *toFragments)
}

/**
 * show一个Fragment,hide其他同栈所有Fragment
 * 使用该方法时，要确保同级栈内无多余的Fragment,(只有通过loadMultipleRootFragment()载入的Fragment)
 *
 *
 * 建议使用更明确的[.showHideFragment]
 *
 * @param showFragment 需要show的Fragment
 */
fun BCActivity.showHideFragment(showFragment: ISupportFragment) {
    mDelegate.showHideFragment(showFragment)
}

/**
 * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
 */
fun BCActivity.showHideFragment(showFragment: ISupportFragment, hideFragment: ISupportFragment) {
    mDelegate.showHideFragment(showFragment, hideFragment)
}

/**
 * It is recommended to use [SupportFragment.start].
 */
fun BCActivity.start(toFragment: ISupportFragment) {
    mDelegate.start(toFragment)
}

/**
 * It is recommended to use [SupportFragment.start].
 *
 * @param launchMode Similar to Activity's LaunchMode.
 */
fun BCActivity.start(toFragment: ISupportFragment, @ISupportFragment.LaunchMode launchMode: Int) {
    mDelegate.start(toFragment, launchMode)
}

/**
 * It is recommended to use [SupportFragment.startForResult].
 * Launch an fragment for which you would like a result when it poped.
 */
fun BCActivity.startForResult(toFragment: ISupportFragment, requestCode: Int) {
    mDelegate.startForResult(toFragment, requestCode)
}

/**
 * It is recommended to use [SupportFragment.startWithPop].
 * Launch a fragment while poping self.
 */
fun BCActivity.startWithPop(toFragment: ISupportFragment) {
    mDelegate.startWithPop(toFragment)
}

/**
 * It is recommended to use [SupportFragment.replaceFragment].
 */
fun BCActivity.replaceFragment(toFragment: ISupportFragment, addToBackStack: Boolean) {
    mDelegate.replaceFragment(toFragment, addToBackStack)
}

/**
 * Pop the fragment.
 */
fun BCActivity.pop() {
    mDelegate.pop()
}

/**
 * Pop the last fragment transition from the manager's fragment
 * back stack.
 *
 *
 * 出栈到目标fragment
 *
 * @param targetFragmentClass   目标fragment
 * @param includeTargetFragment 是否包含该fragment
 */
fun BCActivity.popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
    mDelegate.popTo(targetFragmentClass, includeTargetFragment)
}

/**
 * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
 * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
 */
fun BCActivity.popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean, afterPopTransactionRunnable: Runnable) {
    mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable)
}

fun BCActivity.popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean, afterPopTransactionRunnable: Runnable, popAnim: Int) {
    mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim)
}

/**
 * 当Fragment根布局 没有 设定background属性时,
 * Fragmentation默认使用Theme的android:windowbackground作为Fragment的背景,
 * 可以通过该方法改变其内所有Fragment的默认背景。
 */
fun BCActivity.setDefaultFragmentBackground(@DrawableRes backgroundRes: Int) {
    mDelegate.defaultFragmentBackground = backgroundRes
}

/**
 * 得到位于栈顶Fragment
 */
fun BCActivity.getTopFragment(): ISupportFragment? {
    return SupportHelper.getTopFragment(supportFragmentManager)
}

/**
 * 获取栈内的fragment对象
 */
fun <T : ISupportFragment> BCActivity.findFragment(fragmentClass: Class<T>): T? {
    return SupportHelper.findFragment(supportFragmentManager, fragmentClass)
}