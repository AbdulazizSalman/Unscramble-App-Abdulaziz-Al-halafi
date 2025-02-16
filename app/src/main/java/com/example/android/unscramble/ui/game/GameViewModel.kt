package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {
    //------------------DATA------------------
    //data
    private var _score = MutableLiveData<Int>(0)
    val score:LiveData<Int> get() = _score

    private var _currentWordCount = MutableLiveData<Int>(0)
    val currentWordCount:LiveData<Int> get() = _currentWordCount

    private var _count = 0
    val count:Int get() = _count

    private  var _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord:LiveData<String> get() = _currentScrambledWord

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
        while(String(temptWord).equals(currentWord,false)){
            temptWord.shuffle()
        }

        if(wordList.contains(currentWord)){
            getNextWord()
        }else{
            _currentScrambledWord.value = String(temptWord)
            _currentWordCount.value = (_currentWordCount.value)?.inc()
            wordList.add(currentWord)
        }

    }

    fun nextWord():Boolean{
        return if(currentWordCount.value!! < MAX_NO_OF_WORDS)
        {
            getNextWord()
            true
        }else false
    }

    private fun increaseScore(){
        _score.value =(_score.value)?.plus(SCORE_INCREASE)
    }

    fun isUserWordCorrect(playerWord:String):Boolean{
        if(playerWord.equals(currentWord,true)){
            increaseScore()
            return true
        }
        return false
    }

   fun reinitializeData(){
       _score.value = 0
       _currentWordCount.value=0
       wordList.clear()
       getNextWord()
   }



}