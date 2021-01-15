package com.pens.todolist.module.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.pens.todolist.R
import com.pens.todolist.base.BaseFragment
import com.pens.todolist.data.model.LoginRequest
import com.pens.todolist.data.repository.UserSessionRepository
import com.pens.todolist.module.todolist.ToDoListActivity

class LoginFragment : BaseFragment<AuthActivity, AuthContract.Presenter>(), AuthContract.View, View.OnClickListener{

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false)
        mPresenter = AuthPresenter(this, UserSessionRepository(activity))
        (mPresenter as AuthPresenter).start()

        emailEditText = fragmentView.findViewById(R.id.email_edit_text)
        passwordEditText = fragmentView.findViewById(R.id.pasword_edit_text)
        loginButton = fragmentView.findViewById(R.id.login_button)
        loginButton.setOnClickListener(this)

        title = "Login ToDoList"

        return fragmentView
    }

    override fun redirectToDashboard() {
        val intent = Intent(thisActivity, ToDoListActivity::class.java)
        startActivity(intent)
        thisActivity?.finish()
    }

    override fun showToast(message: String) {
        Toast.makeText(thisActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun setPresenter(presenter: AuthContract.Presenter) {
        mPresenter = presenter
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.login_button -> loginButtonOnClick()
        }
    }

    private fun loginButtonOnClick() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val request = LoginRequest(email, password)
        mPresenter?.performLogin(request)
    }
}