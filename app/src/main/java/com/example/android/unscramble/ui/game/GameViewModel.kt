package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {
    //------------------DATA------------------
    //data
    private var _score = 0
    val score:Int get() = _score

    private var _currentWordCount = 0
    val currentWordCount:Int get() = _currentWordCount

    private var _count = 0
    val count:Int get() = _count

    private lateinit var _currentScrambledWord :String
    val currentScrambledWord:String get() = _currentScrambledWord

    private var wordList:MutableList<String> = mutableListOf()

    private lateinit var currentWord:String

    init {
        Log.d("GameFragment","GameViewModel created!")
        getNextWord()
    }


    //------------------------------- Logic -------------------------------

    private fun getNextWord(){
        currentWord = allWordsList.random()
        val temptWord = currentWord.toCharArray()
        temptWord.shuffle()

        while(String(temptWord).equals(currentWord,true)){
            temptWord.shuffle()
        }

        if(wordList.contains(currentWord)){
            getNextWord()
        }else{
            _currentScrambledWord = String(temptWord)
            ++_currentWordCount
            wordList.add(currentWord)
        }

    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment","GameViewModel cleared!")







    }


    fun nextWord():Boolean{
        return if(currentWordCount< MAX_NO_OF_WORDS)
        {
            getNextWord()
            true
        }else false


    }


}