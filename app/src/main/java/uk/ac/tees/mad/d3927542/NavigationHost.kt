package uk.ac.tees.mad.d3927542

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    startDes: String,
    dataVM: DataViewModel
) {

    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = startDes,

        // Set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues = padding),

        builder = {
            composable(Route.Home.name) {
                Home(navController, dataVM)
            }
            composable(Route.ProductDetail.name) {
                ProductDetail(navController, dataVM)
            }
            composable(Route.Cart.name) {
                Cart(navController, dataVM)
            }
            composable(Route.OrderPlaced.name) {
                OrderPlaced(navController, dataVM)
            }
            composable(Route.Search.name) {
                Search(dataVM,navController)
            }
            composable(Route.ProductPerCategory.name) {
                Products(dataVM,navController, true)
            }
            composable(Route.AllProducts.name) {
                Products(dataVM,navController, false)
            }
            composable(Route.Categories.name) {
                Category(dataVM,navController)
            }
            composable(Route.login.name) {
                GetLogin(navController = navController)
            }
            composable(Route.register.name) {
                GetRegister(navController = navController)
            }
            composable(Route.forgotPassword.name) {
                ForgotPassword(navHostController = navController)
            }
        })

}