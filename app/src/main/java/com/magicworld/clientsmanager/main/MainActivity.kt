package com.magicworld.clientsmanager.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.magicworld.clientsmanager.bill.add.BillAddScreen
import com.magicworld.clientsmanager.main.list.BillListScreen
import com.magicworld.clientsmanager.model.Bill
import com.magicworld.clientsmanager.model.Product
import com.magicworld.clientsmanager.model.Routes.*
import com.magicworld.clientsmanager.model.User
import com.magicworld.clientsmanager.product.add.ProductAppScreen
import com.magicworld.clientsmanager.product.list.ProductListScreen
import com.magicworld.clientsmanager.product.update.ProductUpdateScreen
import com.magicworld.clientsmanager.ui.theme.ClientsManagerTheme
import com.magicworld.clientsmanager.user.add.UserAddScreen
import com.magicworld.clientsmanager.user.list.UserListScreen
import com.magicworld.clientsmanager.user.update.UserUpdateScreen
import com.magicworld.clientsmanager.viewmodel.*


class MainActivity : ComponentActivity() {
    private val userAddViewModel : UserAddViewModel by viewModels()
    private val userListViewModel: UserListViewModel by viewModels()
    private val userUpdateViewModel: UserUpdateViewModel by viewModels()
    private val productListViewModel: ProductListViewModel by viewModels()
    private val productAddViewModel: ProductAddViewModel by viewModels()
    private val productUpdateViewModel: ProductUpdateViewModel by viewModels()
    private val billAddViewModel: BillAddViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = this@MainActivity
        setContent {
            ClientsManagerTheme {

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = UserListScreen.route ){
                        composable(UserListScreen.route){ UserListScreen( navController, userListViewModel) }
                        composable(UserAddScreen.route){ UserAddScreen( navController, userAddViewModel ) }
                        composable(UserUpdateScreen.route){
                            val user = navController.previousBackStackEntry?.savedStateHandle?.get<User>("user")
                            user?.let {
                                UserUpdateScreen( navController , userUpdateViewModel , user)
                            }
                        }
                        composable(ProductListScreen.route){ ProductListScreen( navController, productListViewModel)}
                        composable(ProductAddScreen.route){ ProductAppScreen(navController, productAddViewModel)}
                        composable(ProductUpdateScreen.route){
                            val product = navController.previousBackStackEntry?.savedStateHandle?.get<Product>("product")
                            product?.let {
                                ProductUpdateScreen(navController, productUpdateViewModel, product)
                            }
                        }
                        composable(BillAddScreen.route){
                            val user = navController.previousBackStackEntry?.savedStateHandle?.get<User>("user")
                            user?.let {
                                BillAddScreen(navController, user , productListViewModel, billAddViewModel ,activity)
                            }
                        }
                        composable(BillListScreen.route){
                            val currentBill = navController.previousBackStackEntry?.savedStateHandle?.get<Bill>("bill")
                            currentBill?.let {
                                BillListScreen( currentBill)
                            }
                        }
                    }

                }
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)){ view , insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding( bottom = bottom)
            insets
        }
    }
}
