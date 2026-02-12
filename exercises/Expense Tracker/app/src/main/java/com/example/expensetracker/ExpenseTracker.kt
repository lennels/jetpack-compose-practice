package com.example.expensetracker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.ui.AddTransactionScreen
import com.example.expensetracker.ui.AddTransactionViewModel
import com.example.expensetracker.ui.DashboardScreen
import com.example.expensetracker.ui.TransactionViewModel
import kotlinx.serialization.Serializable

@Serializable
sealed interface Titles {
    val titleResId: Int
}

@Serializable
data object DashboardScreen

@Serializable
data object AddTransaction : Titles {
    override val titleResId: Int = R.string.add_transaction
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpenseTrackerApp() {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                canNavigateUp = navController.previousBackStackEntry != null,
                navigateUp = { navController.popBackStack() }
            )
        },
        floatingActionButton = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            if (currentDestination?.hasRoute<AddTransaction>() == false) {
                FAButton { navController.navigate(AddTransaction) }
            }
        },
        modifier = Modifier
    ) { innerPadding ->
        NavigationHost(
            navController = navController,
            modifier = Modifier.padding(
                innerPadding
            )
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel: TransactionViewModel = viewModel()
    val addTransactionViewModel: AddTransactionViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = DashboardScreen,
        modifier = modifier
    ) {
        composable<DashboardScreen> {
            DashboardScreen(
                viewModel = viewModel,
            )
        }
        composable<AddTransaction> {
            AddTransactionScreen(
                viewModel = addTransactionViewModel,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = { Text("Expense Tracker") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    canNavigateUp: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text("Expense Tracker") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
fun FAButton(
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        icon = { Icon(imageVector = Icons.Filled.Edit, contentDescription = null) },
        text = { Text(text = "Add Transaction") }
    )
}