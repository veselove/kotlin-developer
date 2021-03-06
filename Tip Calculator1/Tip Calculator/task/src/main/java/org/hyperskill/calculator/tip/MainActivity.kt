package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_text.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                    if(edit_text.text.toString().isEmpty()) {
                        text_view.text = ""
                    } else {
                        val inputValue: String = edit_text.text.toString()
                        val tipAmount: Double = inputValue.toDouble() * slider.value / 100
                        val outputResultRounded = getString(R.string.tip_amount, String.format("%.2f", tipAmount))
                        text_view.text = outputResultRounded
                    }

            }
        })

        slider.addOnChangeListener { slider, value, fromUser ->
            if(edit_text.text.toString().isEmpty()) {
                text_view.text = ""
            } else {
                val inputValue: String = edit_text.text.toString()
                val tipAmount: Double = inputValue.toDouble() * slider.value / 100
                val outputResultRounded = getString(R.string.tip_amount, String.format("%.2f", tipAmount))
                text_view.text = outputResultRounded
            }
        }
    }
}