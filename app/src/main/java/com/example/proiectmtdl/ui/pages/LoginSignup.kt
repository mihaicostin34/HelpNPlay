package com.example.proiectmtdl.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proiectmtdl.R
import com.example.proiectmtdl.functionalities.account.checkLogin
import com.example.proiectmtdl.functionalities.account.LoginInformation
import com.example.proiectmtdl.functionalities.account.SignupInformation
import com.example.proiectmtdl.functionalities.account.checkSignup
import com.example.proiectmtdl.model.User
import com.example.proiectmtdl.model.UserType
import com.example.proiectmtdl.navigateSingleTopTo
import com.example.proiectmtdl.ui.navigation.FullMainPage
import com.example.proiectmtdl.ui.navigation.Login
import com.example.proiectmtdl.ui.navigation.News
import com.example.proiectmtdl.ui.navigation.Signup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun HelpNPlayLogin(
    navHostController: NavHostController
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        var loading by remember{ mutableStateOf(false) }
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            val style = TextStyle(
                fontSize =30.sp,
                color = Color.Black
            )
            Text(
                text = "Login",
                style = style,
                textAlign = TextAlign.Center,
            )
            val focusManager = LocalFocusManager.current
            val leadingIconUsername = @Composable{
                Icon(
                    Icons.Default.Person,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            var username by remember {
                mutableStateOf("")
            }
            TextField(
                value = username,
                onValueChange = {
                                username = it
                },
                modifier = Modifier.padding(bottom = 10.dp),
                leadingIcon = leadingIconUsername,
                placeholder = {Text(text = "username")}
            )
            val leadingIconPassword = @Composable{
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            var isPasswordVisible by remember{ mutableStateOf(false) }
            val trailingIconpassword = @Composable{
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(if(!isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription ="", tint = MaterialTheme.colorScheme.primary )
                }
            }
            var passwd by remember{
                mutableStateOf("")
            }
            TextField(
                value = passwd,
                onValueChange = { passwd = it},
                leadingIcon = leadingIconPassword,
                trailingIcon = trailingIconpassword,
                placeholder = {Text(text = "Password")},
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            var errorText by remember{mutableStateOf("")}
            var showErrorText by remember{mutableStateOf(false)}
            if(loading){
                CircularProgressIndicator(
                    modifier = Modifier
                )
            }else{
                Submit(
                    onClickSubmit = {
                        loading = true
                        CoroutineScope(Dispatchers.IO).launch{
                            val loginInformation = LoginInformation(username, passwd)
                            val loginResult = checkLogin(loginInformation)
                            when(loginResult){
                                "VOLUNTEER", "ORGANIZER", "COMPANY"->{
                                    withContext(Dispatchers.Main){
                                        loading = false
                                        navHostController.navigateSingleTopTo("main/${username}")
                                    }
                                }
                                else ->{
                                    withContext(Dispatchers.Main){
                                        loading = false
                                        errorText = loginResult
                                        showErrorText = true
                                    }
                                }
                            }
                        }
                })
            }
            if(showErrorText){
                Text(text = errorText)
            }
        }
    }
}

@Composable
fun HelpNPlaySignup(
    navHostController: NavHostController
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        var loading by remember{ mutableStateOf(false) }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            val style = TextStyle(
                fontSize =30.sp,
                color = Color.Black
            )
            Text(
                text = "Sign Up",
                style = style,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            var expanded by remember{ mutableStateOf(false) }
            val items = listOf("User Type", "Volunteer", "Organizer", "Company")
            var selectedIndex by remember{ mutableStateOf(0)}
            var selectedAccountType  by remember {mutableStateOf("User Type")}
            var signupInformation by remember{ mutableStateOf(SignupInformation()) }
            Box(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(start = 45.dp, end = 45.dp, top = 10.dp, bottom = 10.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ){
                Text(
                    text = items[selectedIndex],
                    modifier = Modifier
                        .clickable { expanded = true }
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                ) {
                    items.forEachIndexed{
                        index, s->
                        DropdownMenuItem(
                            onClick = {
                                selectedIndex = index
                                expanded = false
                                selectedAccountType = s
                            },
                            text = {Text( text = s)}
                        )
                    }
                }
            }
            when(selectedAccountType){
                "Organizer"->{
                    signupInformation = signupInformation.copy(accountType = UserType.ORGANIZER)
                    TextField(
                        value = signupInformation.firstName,
                        onValueChange = {signupInformation = signupInformation.copy(firstName = it)},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "First name")},
                        keyboardOptions = KeyboardOptions(
                            imeAction = androidx.compose.ui.text.input.ImeAction.Done,
                            keyboardType = KeyboardType.Password
                        )
                    )

                    TextField(
                        value = signupInformation.lastName,
                        onValueChange = {signupInformation = signupInformation.copy(lastName = it)},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "Last name")}
                    )

                    TextField(
                        value = signupInformation.companyName,
                        onValueChange = {signupInformation = signupInformation.copy(companyName = it)},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "Company name")}
                    )
                }
                "Company"->{
                    signupInformation = signupInformation.copy(accountType = UserType.COMPANY)
                    TextField(
                        value = signupInformation.companyName,
                        onValueChange = {signupInformation = signupInformation.copy(companyName = it)},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "Company name")}
                    )
                }
                "Volunteer"->{
                    signupInformation = signupInformation.copy(accountType = UserType.VOLUNTEER)
                    TextField(
                        value = signupInformation.firstName,
                        onValueChange = {signupInformation = signupInformation.copy(firstName = it)},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "First name")},
                    )

                    TextField(
                        value = signupInformation.lastName,
                        onValueChange = {signupInformation = signupInformation.copy(lastName = it)},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "Last name")}
                    )
                }
                else->{
                    TextField(
                        value = signupInformation.firstName,
                        onValueChange = {signupInformation = signupInformation.copy(firstName = it)},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "First name")},
                    )

                    TextField(
                        value = signupInformation.lastName,
                        onValueChange = {signupInformation = signupInformation.copy(lastName = it)},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "Last name")}
                    )
                }
            }

            TextField(
                value = signupInformation.email,
                onValueChange = {signupInformation = signupInformation.copy(email = it)},
                modifier = Modifier.padding(bottom = 10.dp),
                placeholder = {Text(text = "Email")}
            )

            val leadingIconUsername = @Composable{
                Icon(
                    Icons.Default.Person,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            TextField(
                value = signupInformation.username,
                onValueChange = { signupInformation = signupInformation.copy(username = it)},
                modifier = Modifier.padding(bottom = 10.dp),
                leadingIcon = leadingIconUsername,
                placeholder = {Text(text = "username")}
            )


            val leadingIconPassword = @Composable{
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            var isPasswordVisible by remember{ mutableStateOf(false) }
            val trailingIconpassword = @Composable{
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(if(!isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription ="", tint = MaterialTheme.colorScheme.primary )
                }
            }
            TextField(
                value = signupInformation.password,
                onValueChange = {signupInformation = signupInformation.copy(password = it)},
                leadingIcon = leadingIconPassword,
                trailingIcon = trailingIconpassword,
                placeholder = {Text(text = "Password")},
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.padding(bottom = 10.dp)
            )

            var isConfirmPasswordVisible by remember{ mutableStateOf(false) }
            val trailingIconCOnfirmPassword = @Composable{
                IconButton(onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }) {
                    Icon(if(!isConfirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription ="", tint = MaterialTheme.colorScheme.primary )
                }
            }
            TextField(
                value = signupInformation.confirmPassword,
                onValueChange = {signupInformation = signupInformation.copy(confirmPassword = it)},
                leadingIcon = leadingIconPassword,
                trailingIcon = trailingIconpassword,
                placeholder = {Text(text = "Confirm password")},
                visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            var showErrorText by remember{ mutableStateOf(false) }
            var errorText by remember{ mutableStateOf("") }
            if(loading){
                CircularProgressIndicator(
                    modifier = Modifier
                )
            }else{
                Submit(
                    onClickSubmit = {
                        loading = true
                        CoroutineScope(Dispatchers.IO).launch{
                            val signupResult = checkSignup(signupInformation)
                            if(signupResult == SignupInformation.SignupResult.SIGNUP_SUCCESS){
                                withContext(Dispatchers.Main){
                                    loading = false
                                    navHostController.navigateSingleTopTo("main/${signupInformation.username}")
                                }
                            }else{
                                //TODO: show error message
                                withContext(Dispatchers.Main){
                                    loading = false
                                    errorText= signupResult.message
                                    showErrorText = true
                                }
                            }
                        }
                    }
                )
            }
            if(showErrorText){
                Text(text = errorText)
            }
        }
    }
}

@Composable
fun OpeningPage(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(top = 150.dp)
                .width(200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pozamtdl),
                contentDescription = null,
                modifier = Modifier.padding(20.dp)
            )
            Button(onClick = { navHostController.navigateSingleTopTo(Login.route) },
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Login"
                )
            }
            Text(
                text = "Don't have an account?",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, start = 20.dp)
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .fillMaxWidth()
            )
            Button(onClick = { navHostController.navigateSingleTopTo(Signup.route) },
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Sign up"
                )
            }
        }
    }
}

@Composable
fun UsernameField(
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val leadingIconUsername = @Composable{
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.padding(bottom = 10.dp),
        leadingIcon = leadingIconUsername,
        placeholder = {Text(text = "username")}
    )
}

@Composable
fun PasswordField(
    placeholder: String = "password",
    modifier: Modifier = Modifier
) {
    val leadingIconPassword = @Composable{
        Icon(
            Icons.Default.Lock,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    var isPasswordVisible by remember{ mutableStateOf(false) }
    val trailingIconpassword = @Composable{
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(if(!isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription ="", tint = MaterialTheme.colorScheme.primary )
        }
    }
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = leadingIconPassword,
        trailingIcon = trailingIconpassword,
        placeholder = {Text(text = placeholder)},
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier.padding(bottom = 10.dp)
    )
}

@Composable
fun Submit(
    onClickSubmit: ()-> Unit,
    modifier: Modifier = Modifier
) {
    Button(onClick = { onClickSubmit() }) {
        Text(
            text = "Submit"
        )
    }
}