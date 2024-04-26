package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bignerdranch.android.geoquiz.databinding.ActivityCheatBinding


const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.goequiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"


class CheatActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCheatBinding
    private var answerIsTrue = false
    private val quizViewModel: QuizViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        binding.showAnswerButton.setOnClickListener {
            quizViewModel.isPressed = true
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }
        updateText()
        API_shower()
    }


    private fun setAnswerShownResult(isAnswerShown: Boolean){
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }


    private fun updateText(){
        val answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }
        if (quizViewModel.isPressed){
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }
    }


    private fun API_shower(){
        val API_level = "API level " + Build.VERSION.SDK_INT.toString()
        binding.apiLevel.setText(API_level)
    }


    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}