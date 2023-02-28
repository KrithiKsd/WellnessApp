package com.example.wellnessapp

/*
* Filename: MainActivity.kt
* Author: Krithika Kasaragod
* */

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wellnessapp.ui.theme.WellnessAppTheme
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : ComponentActivity() {

    var user: FirebaseUser? = null
    var isLoggedIn: MutableState<Boolean>? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseAuth.getInstance().addAuthStateListener { firebaseAuth ->
            val currentUser = firebaseAuth.currentUser
            isLoggedIn!!.value = currentUser != null
        }

        setContent {

            isLoggedIn= rememberSaveable {mutableStateOf(false)}

            WellnessAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        if (isLoggedIn!!.value) {
                            Text(
                                "Welcome "+"${FirebaseAuth.getInstance().currentUser?.displayName}!",
                                style = MaterialTheme.typography.h5,
                            )
                            Spacer(modifier = Modifier.padding(bottom = 16.dp))
                            Button(onClick = {
                                AuthUI.getInstance().signOut(applicationContext)
                                isLoggedIn!!.value=false
                            }, Modifier.padding(top = 8.dp)) {
                                Text("Logout")
                            }
                             WellnessApp()
                        }else{
                            Text(
                                "Welcome to Wellness App",
                                style = MaterialTheme.typography.h5,
                            )
                            Spacer(modifier = Modifier.padding(bottom = 16.dp))
                            Button(onClick = {
                                launchSignInFlowMethod()
                            }, Modifier.padding(top = 8.dp)) {
                                Text("Login")
                            }

                            Spacer(modifier = Modifier.padding(bottom = 16.dp))

                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        FirebaseAuth.getInstance().removeAuthStateListener { }
    }

    @SuppressLint("StringFormatInvalid")
    private fun getFactWithPersonalization(fact: String): String {
        return String.format(
            resources.getString(
                R.string.welcome,
                FirebaseAuth.getInstance().currentUser?.displayName,
                Character.toLowerCase(fact[0]) + fact.substring(1)
            )
        )
    }

     fun launchSignInFlowMethod() {

        // Give users the option to sign in / register with their email or Google account. If users
        // choose to register with their email, they will need to create a password as well.
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Create and launch sign-in intent. We listen to the response of this activity with the
        // SIGN_IN_RESULT_CODE code.
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                providers
            ).build(), SIGN_IN_RESULT_CODE
        )

    }

   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Companion.SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                user = FirebaseAuth.getInstance().currentUser
                // Successfully signed in user.
                isLoggedIn!!.value = true
                Log.i(
                    "TAG",
                    "Successfully signed in user " +
                            "${FirebaseAuth.getInstance().currentUser?.displayName}!"
                )
            } else {
                // Sign in failed. If response is null the user canceled the sign-in flow using
                // the back button. Otherwise check response.getError().getErrorCode() and handle
                // the error.
                Log.i("TAG", "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }

    companion object {
        const val SIGN_IN_RESULT_CODE = 1001
    }
}





