package com.example.database

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.database.DataBase.UserDao
import com.example.database.DataBase.UserDataBase
import com.example.database.DataBase.UserEntity
import com.example.database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels { MainActivityViewModel.Factory }
    private lateinit var binding: ActivityMainBinding
    private lateinit var userDataBase: UserDataBase
    private lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        ContextProvider.setContext(applicationContext)
        userDataBase = UserDataBase.getDatabase(applicationContext)
        userDao = userDataBase.userDao()
        observe()
        setHabit()
        setContentView(binding.root)
    }

    private fun observe() {
        var tempText:String
        viewModel.userData.observe(this) { userList ->
            userList?.let { users ->
                tempText = ""
                for (user in users) {
                    tempText +="Name: ${user.userName}, Activity: ${user.userActivity}, Complete: ${user.isComplete}\n"
                }
                binding.displayText.text = tempText
                binding.displayText.visibility = View.VISIBLE
            }
        }
    }

    private fun setHabit() {
        binding.apply {
            var name: String
            var activity: String
            var isComplete: Boolean
            insertDataField.DoneButton.setOnClickListener {
                name = insertDataField.nameEdit.text.toString()
                activity = insertDataField.activityEdit.text.toString()
                isComplete = insertDataField.doneCheckBox.isChecked
                checkIsDataValidate(name, activity, isComplete) {
                    viewModel.insertUser(it)
                }
            }

            updateDataField.DoneButton.setOnClickListener {
                name = updateDataField.nameEdit.text.toString()
                activity = updateDataField.activityEdit.text.toString()
                isComplete = updateDataField.doneCheckBox.isChecked
                checkIsDataValidate(name, activity, isComplete) {
                    viewModel.updateUser(it)
                }
            }

            deleteButton.setOnClickListener {
                name = deleteDataField.text.toString()
                if (name.isNotEmpty()) {
                    viewModel.deleteUser(name)
                }
            }

            searchButton.setOnClickListener {
                name = nameSearch.text.toString()
                if (name.isNotEmpty()) {
                    viewModel.searchUser(name)
                }
            }

            showAllButton.setOnClickListener {
                viewModel.getAllUser()
            }

        }
    }

    private fun checkIsDataValidate(
        name: String,
        activity: String,
        isComplete: Boolean,
        action: (UserEntity) -> Unit
    ) {
        if (name.isNotEmpty() && activity.isNotEmpty()) {
            val userEntity =
                UserEntity(userName = name, userActivity = activity, isComplete = isComplete)
            action(userEntity)
        }
    }

}