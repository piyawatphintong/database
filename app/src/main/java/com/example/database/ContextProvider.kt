package com.example.database

import android.content.Context

class ContextProvider {

    companion object {
        private lateinit var appContext: Context

        fun setContext(context: Context) {
            appContext = context.applicationContext
        }

        fun getContext(): Context {
            return appContext
        }
    }

}
