package com.magicworld.clientsmanager.bill.add


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddAPhoto
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.magicworld.clientsmanager.R
import com.magicworld.clientsmanager.main.MainActivity
import com.magicworld.clientsmanager.model.Bill
import com.magicworld.clientsmanager.model.Product
import com.magicworld.clientsmanager.model.Routes
import com.magicworld.clientsmanager.model.User
import com.magicworld.clientsmanager.ui.theme.turquesaMedio
import com.magicworld.clientsmanager.ui.utils.*
import com.magicworld.clientsmanager.viewmodel.BillAddViewModel
import com.magicworld.clientsmanager.viewmodel.ProductListViewModel


@Composable
fun BillAddScreen(
    navController: NavHostController,
    user: User,
    productListViewModel: ProductListViewModel,
    billAddViewModel: BillAddViewModel,
    activity: MainActivity,
) {
    val view = LocalView.current
    val context = LocalContext.current
    var hideAppBar by rememberSaveable { mutableStateOf(true) }
    var show by rememberSaveable { mutableStateOf(false) }

    productListViewModel.getProducts()
    askForPermission(context, activity)

    val listBillProducts = billAddViewModel.listBillProducts
    val date = obtenerFechaConFormato("yyyy-MM-dd, HH:mm", "America/Bogota")
    val currentBill = Bill("", date!!, user, listBillProducts)

    Scaffold(
        topBar = {
            if (hideAppBar) {
                TopAppBarBillAdd(navController, productListViewModel, billAddViewModel) {
                    hideAppBar = false
                    show = true
                }
            }
        }, floatingActionButton = {
            FAV(currentBill, navController)
        }
    ) { padding ->
        Column(Modifier
            .padding(padding)
            .fillMaxSize()) {

            BillHeaderAdd(user, date)
            BillBodyAdd(listBillProducts) { billAddViewModel.removeItemInList(it) }
        }
    }

    MyAlertDialog(title = "Guardar imagen",
        description = "Desaea guardar imagen en el dispositivo",
        show = show,
        onDismiss = { show = false }) {
        saveView(view, context)
        show = false
        hideAppBar = true
    }

}

@Composable
fun FAV(currentBill: Bill, navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.set("bill", currentBill)
            navController.navigate(Routes.BillListScreen.route)
        },
        backgroundColor = turquesaMedio
    ) {
        Icon(
            imageVector = Icons.Outlined.PersonAdd,
            contentDescription = "Crear cliente", tint = Color.White,
            modifier = Modifier.scale(1.5f, 1.5f)
        )
    }
}

@Composable
fun TopAppBarBillAdd(
    navController: NavHostController,
    productListViewModel: ProductListViewModel,
    billAddViewModel: BillAddViewModel,
    onSaveView: () -> Unit,
) {

    var showMenu by rememberSaveable { mutableStateOf(false) }
    val listProducts by productListViewModel.onProductsLoaded.observeAsState(arrayListOf())

    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = {
                    billAddViewModel.cleanList()
                    navController.navigateUp()
                }
            ) {
                Icon(painterResource(R.drawable.arrow_back_ios),
                    null,
                    tint = Color.Black,
                    modifier = Modifier.scale(0.6f, 0.6f))
            }
        },
        actions = {
            IconButton(
                onClick = { showMenu = true }
            ) {
                Icon(imageVector = Icons.Outlined.AddCircleOutline,
                    contentDescription = "agrega un producto", tint = Color.Black,
                    modifier = Modifier.scale(1.2f, 1.2f))

            }
            IconButton(onClick = { onSaveView() })
            {
                Icon(imageVector = Icons.Outlined.AddAPhoto,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.scale(1.2f, 1.2f))
            }

        })

    MyDropDownMenu(showMenu, listProducts,
        onDismissMenu = {
            showMenu = false
        },
        onProductSelected = { product ->

            billAddViewModel.saveBillProduct(product)
        }
    )
}

@Composable
fun BillHeaderAdd(user: User, date: String?) {

    Column(modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Text(text = "$date", fontSize = 12.sp)
        Divider(Modifier.fillMaxWidth(), color = Color.DarkGray)
        Row(Modifier.fillMaxWidth()) {
            Image(painter = painterResource(R.drawable.mwlogo),
                contentDescription = null, modifier = Modifier
                    .weight(1f)
                    .scale(0.7f, 0.7f))

            InfoCompany(Modifier.weight(1f))

            NumBill(Modifier
                .align(Alignment.CenterVertically)
                .weight(1f))

        }
        Divider(Modifier.fillMaxWidth(), color = Color.DarkGray)
        InfoClient(user)

    }
}

@Composable
fun InfoCompany(modifier: Modifier) {
    Column(modifier.padding(4.dp)) {
        MyTextInfoBill(text = "Magic World", fontSize = 14)
        MyTextInfoBill(text = "NIT 923456719", fontSize = 10, Color.DarkGray)
        MyTextInfoBill(text = "Dg 43 # 70-79", fontSize = 10, Color.DarkGray)
        MyTextInfoBill(text = "3044751947", fontSize = 10, Color.DarkGray)
        MyTextInfoBill(text = "MagicWorld@google.com", fontSize = 9)
    }
}

@Composable
fun NumBill(modifier: Modifier) {
    Box(modifier
        .padding(2.dp)
        .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(5.dp))) {
        Column {
            Text(text = "factura electronica de venta",
                fontSize = 8.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp))

            Text(text = "No. FO-6709",
                fontSize = 8.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp))

        }
    }
}

@Composable
fun InfoClient(user: User) {

    Row(Modifier.padding(8.dp)) {
        Text(text = user.name,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 6.dp),
            color = Color.Black)
        Column {
            MyTextInfoBill(text = user.email, fontSize = 15, Color.Gray)
            MyTextInfoBill(text = user.phone, fontSize = 15, Color.Gray)
            MyTextInfoBill(text = user.address, fontSize = 13, Color.Gray)

        }

    }

}


@Composable
fun BillBodyAdd(
    listBillProducts: List<Product>,
    onItemListSelected: (Product) -> Unit,
) {
    var totalBruto: Long = 0

    Box(Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
        .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.TopStart
    ) {
        Column(Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)) {
            TableHeader()
            listBillProducts.forEach { product ->
                MyBillProductText(product,
                    onItemListSelected = { onItemListSelected(product) },
                    sumTotal = { total ->
                        totalBruto += total
                    }
                )
            }
        }
    }

    TotalResults(totalBruto)

}

@Composable
fun TotalResults(totalBruto: Long) {

    Row(Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp, horizontal = 4.dp)) {
        Box(Modifier.weight(1f))
        Row(Modifier.weight(1f)) {
            Column(Modifier.weight(1f)) {
                Text(text = "Total Bruto", fontSize = 12.sp)
                Text(text = "IVA 19%", fontSize = 12.sp)
                Text(text = "Total a Pagar", fontSize = 12.sp)
            }
            Column(Modifier.weight(1f)) {
                Text(text = totalBruto.toString(),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth())
                Text(text = (totalBruto * 0.19).toLong().toString(),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth())
                Text(text = ((totalBruto * 0.19) + totalBruto).toLong().toString(),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth())
            }

        }
    }

}

@Composable
fun TableHeader() {
    Row(Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        Text(text = "Descripción",
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            modifier = Modifier.weight(3f))
        Text(text = "Precio ",
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f))
        Text(text = "Total",
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f))
    }
}

@Composable
fun MyBillProductText(product: Product, onItemListSelected: () -> Unit, sumTotal: (Long) -> Unit) {
    val total = product.price.toLong() * product.quantity.toLong()
    sumTotal(total)
    Row(Modifier
        .fillMaxWidth()
        .clickable { onItemListSelected() }) {
        Text(text = " ${product.productName} x ${product.quantity} ",
            color = Color.DarkGray,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(3f)
        )
        Text(text = product.price,
            color = Color.DarkGray,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(text = total.toString(),
            color = Color.DarkGray,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }

}


