package com.example.todolist3.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todolist3.R

abstract class BaseFragment<T, U> : Fragment() {

    lateinit var fragmentListener: FragmentListener
    lateinit var fragmentView: View
    var mPresenter : U? = null
    var thisActivity : T? = null
    var title : String = ""
        set(value) {
            field = value
            fragmentListener.setTitle(field)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View? = super.onCreateView(inflater, container, savedInstanceState)
        title = resources.getString(R.string.app_name)
        return view
    }

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        thisActivity = context as T
        fragmentListener = context as FragmentListener
    }

}