package android.baseframework.core.base

import android.view.View
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportHelper

/**
 * *****************************************************************
 * Fragmentation
 * *****************************************************************
 */
/****************************************以下为可选方法(Optional methods)******************************************************/

/**
 * 隐藏软键盘
 */
fun BCFragment.hideSoftInput() {
    mDelegate.hideSoftInput()
}

/**
 * 显示软键盘,调用该方法后,会在onPause时自动隐藏软键盘
 */
fun BCFragment.showSoftInput(view: View) {
    mDelegate.showSoftInput(view)
}

/**
 * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
 *
 * @param containerId 容器id
 * @param toFragment  目标Fragment
 */
fun BCFragment.loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
    mDelegate.loadRootFragment(containerId, toFragment)
}

fun BCFragment.loadRootFragment(containerId: Int, toFragment: ISupportFragment, addToBackStack: Boolean, allowAnim: Boolean) {
    mDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnim)
}

/**
 * 加载多个同级根Fragment,类似Wechat, QQ主页的场景
 */
fun BCFragment. loadMultipleRootFragment(containerId: Int, showPosition: Int, vararg toFragments: ISupportFragment) {
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
fun BCFragment.showHideFragment(showFragment: ISupportFragment) {
    mDelegate.showHideFragment(showFragment)
}

/**
 * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
 */
fun BCFragment.showHideFragment(showFragment: ISupportFragment, hideFragment: ISupportFragment) {
    mDelegate.showHideFragment(showFragment, hideFragment)
}

fun BCFragment.start(toFragment: ISupportFragment) {
    mDelegate.start(toFragment)
}

/**
 * @param launchMode Similar to Activity's LaunchMode.
 */
fun BCFragment.start(toFragment: ISupportFragment, @ISupportFragment.LaunchMode launchMode: Int) {
    mDelegate.start(toFragment, launchMode)
}

/**
 * Launch an fragment for which you would like a result when it poped.
 */
fun BCFragment.startForResult(toFragment: ISupportFragment, requestCode: Int) {
    mDelegate.startForResult(toFragment, requestCode)
}

/**
 * Launch a fragment while poping self.
 */
fun BCFragment.startWithPop(toFragment: ISupportFragment) {
    mDelegate.startWithPop(toFragment)
}

fun BCFragment.replaceFragment(toFragment: ISupportFragment, addToBackStack: Boolean) {
    mDelegate.replaceFragment(toFragment, addToBackStack)
}

fun BCFragment.pop() {
    mDelegate.pop()
}

/**
 * Pop the child fragment.
 */
fun BCFragment.popChild() {
    mDelegate.popChild()
}

/**
 * Pop the last fragment transition from the manager's fragment
 * back stack.
 *
 * 出栈到目标fragment
 *
 * @param targetFragmentClass   目标fragment
 * @param includeTargetFragment 是否包含该fragment
 */
fun BCFragment.popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
    mDelegate.popTo(targetFragmentClass, includeTargetFragment)
}

/**
 * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
 * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
 */
fun BCFragment.popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean, afterPopTransactionRunnable: Runnable) {
    mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable)
}

fun BCFragment.popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean, afterPopTransactionRunnable: Runnable, popAnim: Int) {
    mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim)
}

fun BCFragment.popToChild(targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
    mDelegate.popToChild(targetFragmentClass, includeTargetFragment)
}

fun BCFragment.popToChild(targetFragmentClass: Class<*>, includeTargetFragment: Boolean, afterPopTransactionRunnable: Runnable) {
    mDelegate.popToChild(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable)
}

fun BCFragment.popToChild(targetFragmentClass: Class<*>, includeTargetFragment: Boolean, afterPopTransactionRunnable: Runnable, popAnim: Int) {
    mDelegate.popToChild(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim)
}

/**
 * 得到位于栈顶Fragment
 */
fun BCFragment.getTopFragment(): ISupportFragment {
    return SupportHelper.getTopFragment(fragmentManager)
}

fun BCFragment.getTopChildFragment(): ISupportFragment {
    return SupportHelper.getTopFragment(childFragmentManager)
}

/**
 * @return 位于当前Fragment的前一个Fragment
 */
fun BCFragment.getPreFragment(): ISupportFragment {
    return SupportHelper.getPreFragment(this)
}

/**
 * 获取栈内的fragment对象
 */
fun <T : ISupportFragment> BCFragment.findFragment(fragmentClass: Class<T>): T? {
    return SupportHelper.findFragment(fragmentManager, fragmentClass)
}

/**
 * 获取栈内的fragment对象
 */
fun <T : ISupportFragment> BCFragment.findChildFragment(fragmentClass: Class<T>): T? {
    return SupportHelper.findFragment(childFragmentManager, fragmentClass)
}