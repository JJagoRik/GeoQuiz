package com.bignerdranch.android.geoquiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val SHOW_BUTTON = "SHOW_BUTTON"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"
const val AMOUNT_OF_ATTEMPTS = "AMOUNT_OF_ATTEMPS"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))


    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)


    var isPressed: Boolean
        get() = savedStateHandle.get(SHOW_BUTTON) ?: false
        set(value) = savedStateHandle.set(SHOW_BUTTON, value)


    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)


    var amountOfAttempts: Int
        get() = savedStateHandle.get(AMOUNT_OF_ATTEMPTS) ?: 3
        set(value) = savedStateHandle.set(AMOUNT_OF_ATTEMPTS, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}