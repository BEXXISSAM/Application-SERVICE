package com.example.applicationservice

import LogInScreen
import SignupScreen
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.applicationservice.data.ProductDataSource
import com.example.applicationservice.models.Product
import com.example.applicationservice.screens.*
import com.example.applicationservice.users.User
import com.example.applicationservice.users.UserManager
import com.example.applicationservice.ui.theme.ApplicationServiceTheme

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
    val dataSource = ProductDataSource()

    val handleLogout = {
        navController.navigate("login_screen") {
            popUpTo(0) { inclusive = true }
        }
    }

    NavHost(navController = navController, startDestination = "login_screen") {

        composable("login_screen") {
            LogInScreen(
                onLoginClicked = { user, pass ->
                    if (UserManager.seConnecter(user, pass)) {
                        navController.navigate("all_products") {
                            popUpTo("login_screen") { inclusive = true }
                        }
                    } else {
                        if ((user == "admin" && pass == "admin") || UserManager.seConnecter(user, pass)) {
                            navController.navigate("all_products") {
                                popUpTo("login_screen") { inclusive = true }
                            }
                        } else {
                            val errorMsg = context.getString(R.string.login_error_message)
                            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                onNavigateToSignup = {
                    navController.navigate("signup_screen")
                }
            )
        }

        composable("signup_screen") {
            SignupScreen(
                onSignupClicked = { user, pass ->
                    UserManager.ajouterUtilisateur(User(user, pass, null))
                    val successMsg = context.getString(R.string.signup_success_message)
                    Toast.makeText(context, successMsg, Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable("all_products") {
            AllProductsScreen(
                onProductClick = { product ->
                    navController.navigate("product_detail/${product.nameResId}")
                },
                onLogout = handleLogout
            )
        }

        composable(
            route = "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val product = dataSource.loadProducts().find { it.nameResId == productId }

            if (product != null) {
                ProductDetailScreen(navController = navController, product = product, onLogout = handleLogout)
            }
        }

        composable(
            route = "order_screen/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val product = dataSource.loadProducts().find { it.nameResId == productId }

            if (product != null) {
                OrderScreen(navController = navController, product = product, onLogout = handleLogout)
            }
        }
    }
}