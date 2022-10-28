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
import com.magicworld.clientsmanager.ui.add.AddClientScreen
import com.magicworld.clientsmanager.ui.list.ListScreen
import com.magicworld.clientsmanager.ui.theme.ClientsManagerTheme
import com.magicworld.clientsmanager.ui.update.UpdateScreen
import com.magicworld.clientsmanager.viewmodel.AddViewModel
import com.magicworld.clientsmanager.viewmodel.ListViewModel
import com.magicworld.clientsmanager.viewmodel.UpdateViewModel


class MainActivity : ComponentActivity() {
    private val addViewModel : AddViewModel by viewModels()
    private val listViewModel: ListViewModel by viewModels()
    private val updateViewModel: UpdateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClientsManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = ListScreen.route ){
                        composable(ListScreen.route){ ListScreen( navController, listViewModel)}
                        composable(AddScreen.route){ AddClientScreen( navController, addViewModel )}
                        composable(UpdateScreen.route){
                            val user = navController.previousBackStackEntry?.savedStateHandle?.get<User>("user")
                            user?.let {
                                UpdateScreen( navController , updateViewModel , user)
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
