package com.kiran.student.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.kiran.student.R
import com.kiran.student.api.ServiceBuilder
import com.kiran.student.api.ServiceBuilder.token
import com.kiran.student.repository.UserRepository
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    private var username : String = ""
    private var password : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getSharedPref()
        if(username!="") {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val repository = UserRepository()
                    val response = repository.loginUser(username, password)
                    if (response.success == true) {
                        ServiceBuilder.token = "Bearer ${response.token}"
                        startActivity(
                            Intent(
                                this@SplashActivity,
                                DashboardActivity::class.java
                            )
                        )
                        finish()
                    } else {
                        startActivity(
                            Intent(
                                this@SplashActivity,
                                LoginActivity::class.java
                            )
                        )
                        finish()
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@SplashActivity,
                            ex.toString(), Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }
        }else{
            startActivity(
                Intent(
                    this@SplashActivity,
                    LoginActivity::class.java
                )
            )
            finish()
        }
    }
    private fun getSharedPref() {
        val sharedPref = getSharedPreferences("LoginPref", MODE_PRIVATE)
        username = sharedPref.getString("username", "").toString()
        password = sharedPref.getString("password", "").toString()
    }
}