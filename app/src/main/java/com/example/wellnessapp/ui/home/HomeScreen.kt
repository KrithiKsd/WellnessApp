package com.example.wellnessapp.ui.home

/*
* Filename: HomeScreen.kt
* Author: Krithika Kasaragod
* */

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wellnessapp.R
import com.example.wellnessapp.WellnessTopAppBar
import com.example.wellnessapp.data.Task
import com.example.wellnessapp.data.TasksRepository
import com.example.wellnessapp.ui.AppViewModelProvider
import com.example.wellnessapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name

 }
    @Composable
    fun HomeScreen(
        navigateToItemEntry: () -> Unit,
        modifier: Modifier = Modifier,
        viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
    ) {
        Scaffold(
            topBar = {
                WellnessTopAppBar(
                    title = stringResource(HomeDestination.titleRes),
                    canNavigateBack = false
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = navigateToItemEntry,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.item_entry_title),
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            },
        ) { innerPadding ->
            val homeUiState by viewModel.homeUiState.collectAsState()
            HomeBody(
                itemList = homeUiState.itemList,
                modifier = modifier.padding(innerPadding),
                viewModel = viewModel
            )
        }
    }

    @Composable
    private fun HomeBody(
        itemList: List<Task>,
        modifier: Modifier = Modifier,
        viewModel: HomeViewModel
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //InventoryListHeader()
            Divider()
            if (itemList.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_item_description),
                    style = MaterialTheme.typography.subtitle2
                )
            } else {
                TaskList(itemList = itemList, viewModel = viewModel)
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun TaskList(
        itemList: List<Task>,

        modifier: Modifier = Modifier,
        viewModel: HomeViewModel
    ) {

        LazyColumn(modifier = modifier) {
            items(items = itemList) { todo ->
                val name = rememberSaveable { mutableStateOf(todo.isDone) }
                val isChecked = remember(todo.id) { mutableStateOf(todo.isDone) }
                ListItem(
                    text = { Text(text = todo.name) },
                    icon = {

                        IconButton(onClick = { viewModel.deleteItem(todo) }

                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = null
                            )
                        }
                    },
                    trailing = {
                        Checkbox(
                            checked = isChecked.value,
                            onCheckedChange = {
                                isChecked.value = it
                                todo.isDone = isChecked.value
                                viewModel.updateItem(todo)
                            }
                        )
                    }
                )
                Divider()
            }
        }
    }

@Preview
@Composable
fun HomeScreenPreview() {
    val previewViewModel = HomeViewModel(MockTaskRepository())
    HomeScreen(navigateToItemEntry = {}, viewModel = previewViewModel)
}

class MockTaskRepository : TasksRepository {
    private val itemList = listOf(
        Task(1, "Task 1", false),
        Task(2, "Task 2", true),
        Task(3, "Task 3", false),
    )
    override fun getAllItemsStream(): Flow<List<Task>> {
        return flow {
            emit(itemList)
        }
    }
    override fun getItemStream(id: Int): Flow<Task?> {
        TODO("Not yet implemented")
    }
    override suspend fun insertItem(item: Task) {
        TODO("Not yet implemented")
    }
    override suspend fun deleteItem(item: Task) {
        TODO("Not yet implemented")
    }
    override suspend fun updateItem(item: Task) {
        TODO("Not yet implemented")
    }
}

