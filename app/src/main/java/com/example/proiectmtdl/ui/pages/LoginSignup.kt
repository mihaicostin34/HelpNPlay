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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proiectmtdl.R
import com.example.proiectmtdl.model.User
import com.example.proiectmtdl.navigateSingleTopTo
import com.example.proiectmtdl.ui.navigation.FullMainPage
import com.example.proiectmtdl.ui.navigation.Login
import com.example.proiectmtdl.ui.navigation.Signup

@Composable
fun HelpNPlayLogin(
    navHostController: NavHostController
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
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
            Submit({
                navHostController.navigateSingleTopTo(FullMainPage.route)
            }) //TODO: load checking coroutine here
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

        var user by remember{mutableStateOf(User())}
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
                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "First name")},
                        keyboardOptions = KeyboardOptions(
                            imeAction = androidx.compose.ui.text.input.ImeAction.Done,
                            keyboardType = KeyboardType.Password
                        )
                    )

                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "Last name")}
                    )

                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "Company name")}
                    )
                }
                "Company"->{
                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "Company name")}
                    )
                }
                else->{
                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "First name")},
                    )

                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.padding(bottom = 10.dp),
                        placeholder = {Text(text = "Last name")}
                    )
                }
            }

            TextField(
                value = "",
                onValueChange = {},
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
                value = "",
                onValueChange = {},
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
                value = "",
                onValueChange = {},
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
                value = "",
                onValueChange = {},
                leadingIcon = leadingIconPassword,
                trailingIcon = trailingIconpassword,
                placeholder = {Text(text = "Confirm password")},
                visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Submit({
                navHostController.navigateSingleTopTo(FullMainPage.route)
            })
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
                .padding(top = 350.dp)
                .width(200.dp)
        ) {
            //TODO: add app icon
//            Image(
//                painter = painterResource(id = R.drawable.picture1),
//                contentDescription = null,
//                modifier = Modifier.padding(20.dp)
//            )
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