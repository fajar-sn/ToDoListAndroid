package com.pens.todolist.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.pens.todolist.R


abstract class BaseActivity<T : Fragment> : FragmentActivity(), FragmentListener {
    private lateinit var currentFragment : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeFragment()
        initializeView()
    }

    protected abstract fun initializeFragment()
    protected abstract fun initializeView()

    protected fun setCurrentFragment(fragment: T) {
        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_frame_layout, fragment)
        fragmentTransaction.commit()

        currentFragment = fragment
    }

}