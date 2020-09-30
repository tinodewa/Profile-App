package com.hit.profileapp

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_login_email
import kotlinx.android.synthetic.main.activity_login.et_login_password
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var colorFilledState: ColorStateList
    private lateinit var colorEmptyState: ColorStateList
    var emailPattern: String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        colorFilledState = ColorStateList.valueOf(resources.getColor(R.color.colorTurquoise))
        colorEmptyState = ColorStateList.valueOf(resources.getColor(R.color.colorGrey))

        check()
    }

    private fun check() {

        et_register_name.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (et_register_name.text!!.isNotEmpty()) {
                        changeEditTextColorState(et_register_name, true)
                        til_register_name.error = null
                } else {
                    til_register_email.error = "Email harus diisi"
                    changeEditTextColorState(et_register_name, false)
                }
            } else {
                changeEditTextColorState(et_register_name, false)
            }
        }

        et_register_email.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (et_register_email.text!!.isNotEmpty()) {
                    if (et_register_email.text.toString().trim().matches(emailPattern.toRegex())) {
                        changeEditTextColorState(et_register_email, true)
                        til_register_email.error = null
                    } else {
                        til_register_email.error = "Harap gunakan format email yang benar"
                        changeEditTextColorState(et_register_email, false)
                    }
                } else {
                    til_register_email.error = "Email harus diisi"
                    changeEditTextColorState(et_register_email, false)
                }
            } else {
                changeEditTextColorState(et_register_email, false)
            }
        }

        et_register_password.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (et_register_password.text!!.isNotEmpty()) {
                    changeEditTextColorState(et_register_password, true)
                    til_register_email.error = null
                } else {
                    til_login_email.error = "Password harus diisi"
                    changeEditTextColorState(et_register_password, false)
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
}