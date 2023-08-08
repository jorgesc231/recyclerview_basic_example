package com.example.recyclerviewexample

import android.app.Application

class RecyclerViewExampleApplication : Application() {
    companion object {
        lateinit var wordList : MutableList<String>
    }

    override fun onCreate() {
        super.onCreate()

        wordList = mutableListOf()
    }
}