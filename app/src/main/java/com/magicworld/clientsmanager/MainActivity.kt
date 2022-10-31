package com.magicworld.clientsmanager

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
import com.magicworld.clientsmanager.model.Routes.*
import com.magicworld.clientsmanager.model.User
import com.magicworld.clientsmanager.user.add.UserAddScreen
import com.magicworld.clientsmanager.user.list.UserListScreen
import com.magicworld.clientsmanager.ui.theme.ClientsManagerTheme
import com.magicworld.clientsmanager.user.update.UserUpdateScreen
import com.magicworld.clientsmanager.viewmodel.UserAddViewModel
import com.magicworld.clientsmanager.viewmodel.UserListViewModel
import com.magicworld.clientsmanager.viewmodel.UserUpdateViewModel


class MainActivity : ComponentActivity() {
    private val userAddViewModel : UserAddViewModel by viewModels()
    private val userListViewModel: UserListViewModel by viewModels()
    private val userUpdateViewModel: UserUpdateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClientsManagerTheme {
                // A surface container using the 'background' color from the theme
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
