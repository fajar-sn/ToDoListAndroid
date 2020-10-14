package com.example.todolist3.base

import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.todolist3.R

abstract class BaseFragmentHolderActivity : BaseActivity<Fragment>(), View.OnClickListener {
    lateinit var toolbarTitleTextView : TextView
    lateinit var fragmentContainerFrameLayout : FrameLayout
    lateinit var optionMenuImageButton: ImageButton
    lateinit var iconImageView : ImageView
    lateinit var backButton : ImageButton
    lateinit var menuBarShadowView : View
    lateinit var activityFragmentHolderRelativeLayout : RelativeLayout

    override fun initializeView() {
        setContentView(R.layout.base_activity)
        toolbarTitleTextView = findViewById(R.id.toolbar_title_text_view)
        fragmentContainerFrameLayout = findViewById(R.id.fragment_container_frame_layout)
        optionMenuImageButton = findViewById(R.id.option_menu_image_button)
        iconImageView = findViewById(R.id.icon_image_view)
        backButton = findViewById(R.id.back_image_button)
        menuBarShadowView = findViewById(R.id.menu_bar_shadow_view)
        activityFragmentHolderRelativeLayout = findViewById(R.id.activity_fragment_holder_relative_layout)

        backButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back_image_button -> {
                onBackPressed()
            }
        }
    }

    override fun setTitle(title: String) {
        this.toolbarTitleTextView.text = title
    }
}