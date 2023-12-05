package com.zsondroid.roomapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zsondroid.roomapplication.room.database.UserDatabase
import com.zsondroid.roomapplication.room.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRoom()
    }

    private fun initRoom() {
        val newUser = User("마루", "5", "010-1234-5678")

        val db = UserDatabase.getDBInstance(this)

        CoroutineScope(Dispatchers.IO).launch {
            db?.userDao()?.insert(newUser)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val userDBList = db?.userDao()?.selectAll()
            Log.d("kwak", "user DB : " + userDBList)
        }
    }
}