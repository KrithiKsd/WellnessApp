package com.example.wellnessapp.ui.home

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Application
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wellnessapp.MainActivity
import com.example.wellnessapp.R

import com.example.wellnessapp.ui.navigation.NavigationDestination
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

var user: FirebaseUser? = null

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name


    @Composable
    fun HomeView(
        navigateToItemEntry: () -> Unit
    ) {
        val context = LocalContext.current
       /* val mTodoViewModel: TodoViewModel = viewModel(
            factory = TodoViewModelFactory(context.applicationContext as Application)
        )*/

       // val items = mTodoViewModel.readAllData.observeAsState(listOf()).value

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Welcome",
                style = MaterialTheme.typography.h4,
            )
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
            Button(onClick = {

                             //signIn()
            }, Modifier.padding(top = 8.dp)) {
                Text("Login")
            }
            /*Text(
                TITLE_MAIN,
                style = MaterialTheme.typography.h6,
            )*/
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
            //CustomCardState()
            //CustomCardState(navController, mTodoViewModel)
            //TodoList(list = items, mTodoViewModel = mTodoViewModel)
            Spacer(modifier = Modifier.padding(top = 32.dp))
            //BackupOptions(mBackUpViewModel)
        }
    }



    /* @OptIn(ExperimentalMaterialApi::class)
     @Composable
     fun TodoList(
         list: List<TodoItem>,
         mTodoViewModel: TodoViewModel
     ) {
         val context = LocalContext.current

         LazyColumn() {
             items(list) { todo ->
                 val name = rememberSaveable { mutableStateOf(todo.isDone) }

                 ListItem(
                     text = { Text(text = todo.itemName) },
                     icon = {
                         IconButton(onClick = {
                             mTodoViewModel.deleteTodo(todo)
                         }) {
                             Icon(
                                 Icons.Default.Delete,
                                 contentDescription = null
                             )
                         }
                     },
                     trailing = {
                         Checkbox(
                             checked = name.value,
                             onCheckedChange = {
                                 name.value = it
                                 todo.isDone = name.value
                                 mTodoViewModel.updateTodo(todo)

                                 Toast.makeText(context, "Updated todo!", Toast.LENGTH_SHORT).show()
                             },
                         )
                     }
                 )
                 Divider()
             }
         }
     }*/

   /* @Composable
    private fun CustomCardState(


        ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
               *//* Button( onClick = {
                    navController.navigate(AddTodoViewDestination)
                })*//*
                Button(onClick = { navController }, Modifier.padding(top = 8.dp)) {
                    Text("Add one")
                }

            }
        }
    }*/



    /*private fun signIn() {
        val providers= arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        val signInIntent= AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        // signInLauncher.launch(signInIntent)


    }

    private fun signInResult(result: FirebaseAuthUIAuthenticationResult){
        val response= result.idpResponse

        if(result.resultCode== Activity.RESULT_OK){
            user = FirebaseAuth.getInstance().currentUser
        }else{
            Log.d("HomeScreen", "Error logging in "+response?.error?.errorCode)
        }
    }*/

}
