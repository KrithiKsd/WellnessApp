package com.example.wellnessapp

/*
* Filename: WellnessApp.kt
* Author: Krithika Kasaragod
* */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wellnessapp.ui.navigation.WellnessNavHost

@Composable
fun WellnessApp(navController: NavHostController = rememberNavController()) {
    WellnessNavHost(navController = navController)
}
@Composable
fun WellnessTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {}
) {
    /*if (canNavigateBack) {
        TopAppBar(
            title = { Text(title) },
            modifier = modifier,
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        )
    } else {
        TopAppBar(title = { Text(title) }, modifier = modifier)
    }*/
}