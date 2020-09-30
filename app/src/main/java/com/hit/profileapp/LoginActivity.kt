package com.hit.profileapp

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var colorFilledState: ColorStateList
    private lateinit var colorEmptyState: ColorStateList
    var emailPattern: String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        colorFilledState = ColorStateList.valueOf(resources.getColor(R.color.colorTurquoise))
        colorEmptyState = ColorStateList.valueOf(resources.getColor(R.color.colorGrey))

        tv_nav_register.setOnClickListener(this)
        check()
    }



    private fun check() {
        et_login_email.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (et_login_email.text!!.isNotEmpty()) {
                    if (et_login_email.text.toString().trim().matches(emailPattern.toRegex())) {
                        changeEditTextColorState(et_login_email, true)
                        til_login_email.error = null
                    } else {
                        til_login_email.error = "Harap gunakan format email yang benar"
                        changeEditTextColorState(et_login_email, false)
                    }
                } else {
                    til_login_email.error = "Email harus diisi"
                    changeEditTextColorState(et_login_email, false)
                }
            } else {
                changeEditTextColorState(et_login_email, false)
            }
        }

        et_login_password.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (et_login_password.text!!.isNotEmpty()) {
                    changeEditTextColorState(et_login_password, true)
                    til_login_email.error = null
                } else {
                    til_login_email.error = "Password harus diisi"
                    changeEditTextColorState(et_login_password, false)
                }
            }
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    Log.d("focus", "touchevent")
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    private fun changeEditTextColorState(editText: TextInputEditText, state: Boolean) {
        if (state) {
            editText.setTextColor(resources.getColor(R.color.colorTurquoise))
        } else {
            editText.setTextColor(resources.getColor(R.color.colorGrey))
        }
    }

    override fun onClick(v: View?) {
        when(v) {
            tv_nav_register -> {
                val intentRegister = Intent(this, RegisterActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }
}