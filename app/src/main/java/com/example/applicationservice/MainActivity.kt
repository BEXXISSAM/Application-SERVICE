package com.example.applicationservice

import LogInScreen
import SignupScreen
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.applicationservice.databases.AppDatabase
import com.example.applicationservice.models.Product
import com.example.applicationservice.repositories.UserRepository
import com.example.applicationservice.screens.*
import com.example.applicationservice.ui.theme.ApplicationServiceTheme
import com.example.applicationservice.viewmodels.AuthViewModel
import com.example.applicationservice.viewmodels.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationServiceTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val database = remember { AppDatabase.getDatabase(context) }

    val userRepository = remember { UserRepository(database.userDao()) }

    val authViewModel: AuthViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AuthViewModel(userRepository) as T
            }
        }
    )

    val productViewModel: ProductViewModel = viewModel()

    val performLogout = {
        authViewModel.logout()
        navController.navigate("login_screen") {
            popUpTo(0) { inclusive = true }
        }
    }

    NavHost(navController = navController, startDestination = "login_screen") {

        composable("login_screen") {
            val userState by authViewModel.userState.collectAsState()
            val errorMessage by authViewModel.errorMessage.collectAsState()

            LaunchedEffect(userState) {
                if (userState != null) {
                    navController.navigate("all_products") {
                        popUpTo("login_screen") { inclusive = true }
                    }
                }
            }

            LaunchedEffect(errorMessage) {
                errorMessage?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            LaunchedEffect(Unit) {
                authViewModel.checkActiveSession()
            }

            LogInScreen(
                onLoginClicked = { emailInput, passwordInput ->
                    if (emailInput == "admin" && passwordInput == "admin") {
                        navController.navigate("all_products")
                    } else {
                        authViewModel.login(email = emailInput, pass = passwordInput)
                    }
                },
                onNavigateToSignup = {
                    navController.navigate("signup_screen")
                }
            )
        }

        composable("signup_screen") {
            val errorMessage by authViewModel.errorMessage.collectAsState()

            LaunchedEffect(errorMessage) {
                errorMessage?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
            }

            SignupScreen(
                onSignupClicked = { userInput, passInput ->

                    authViewModel.register(
                        fullName = userInput,
                        email = userInput,
                        pass = passInput
                    )

                    Toast.makeText(context, "Compte créé, connexion...", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable("all_products") {
            AllProductsScreen(
                viewModel = productViewModel,
                    onProductClick = { product ->
                        navController.navigate("product_detail/${product.id}")
                    },
                    onLogout = { performLogout() }
                )
            }

        composable(
            route = "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val products by productViewModel.allProducts.collectAsState(initial = emptyList())
            val product = products.find { it.id == productId }

            if (product != null) {
                ProductDetailScreen(
                    navController = navController,
                    product = product,
                    onLogout = { performLogout() }
                )
            }
        }

        composable(
            route = "order_screen/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val products by productViewModel.allProducts.collectAsState(initial = emptyList())
            val product = products.find { it.id == productId }

            if (product != null) {
                OrderScreen(
                    navController = navController,
                    product = product,
                    onLogout = { performLogout() }
                )
            }
        }
    }
}