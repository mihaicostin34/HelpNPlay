package com.example.proiectmtdl.ui.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proiectmtdl.functionalities.account.checkUsername
import com.example.proiectmtdl.model.UserType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HelpNPlaySearchPage(
    username: String,
    userType: UserType,
    modifier: Modifier = Modifier
) {
    when(userType){
        UserType.ORGANIZER->{

        }
        UserType.VOLUNTEER->{

        }
        UserType.COMPANY->{
            HelpNPlayEventCreation(
                username = username
            )
        }
        UserType.ADMIN->{

        }
        UserType.NOT_SPECIFIED->{

        }
    }

}
