import android.provider.ContactsContract.CommonDataKinds.Organization
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proiectmtdl.R
import com.example.proiectmtdl.model.Badge
import com.example.proiectmtdl.model.Event
import com.example.proiectmtdl.model.Organizer
import com.example.proiectmtdl.model.Quest
import com.example.proiectmtdl.model.User
import com.example.proiectmtdl.model.Volunteer
import com.example.proiectmtdl.ui.utils.ProgressCircle
import java.util.Date
import java.util.LinkedList

val mockedBadge = Badge(
    iconId = R.drawable.streaming,
    name = "Good player",
    color = Color.Magenta
)

val mockedQuest = Quest(
    title = "Help carry stuff",
    description = "This is a longer description. This should not be visible on the user's profile",
    event = Event(
        id = "eventId",
        name = "Event Name",
        description = "longer description. do not feature this in the profile",
        dates = LinkedList<Date>(),
        organizer = null,
        participants = LinkedList<Volunteer>(),
        quests = listOf()
    )
)

val mockedVolunteer = Volunteer(
    username = "Mocked volunteer username",
    email = "email (should not appear)",
    firstName = "FirstName",
    lastName = "LastName",
    friends = LinkedList(),
    participations = listOf(mockedQuest, mockedQuest, mockedQuest, mockedQuest),
    level = 7,
    currentExperience = 154,
    prizes = listOf(mockedBadge, mockedBadge, mockedBadge, mockedBadge, mockedBadge, mockedBadge),
)

//receives the username, fetches the user from the database and displays their profile
@Preview
@Composable
fun HelpNPlayProfilePage(
    username: String = "Some preview username",
    dominantColor : Color = Color.LightGray,
    topPadding: Dp = 20.dp,
    profilePictureSize : Dp = 200.dp,
    currentUser: Boolean = false
) {
    //get user info
    Box(modifier = Modifier
        .fillMaxSize()
        .background(dominantColor)
        .padding(bottom = 16.dp)
    ){
        ProfileTopSection() //just the black to transparent fade
        ProfileDetailsWrapper(
            username = username,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            currentUser = currentUser
        )
    }
}

@Composable
fun ProfileTopSection(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Black,
                        Color.Transparent
                    )
                )
            )
    ){
    }
}

@Composable
fun ProfileDetailsWrapper(
    username: String,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier,
    currentUser: Boolean
) {
    //TODO: here comes the loading part
    ProfileDetailsSection(username, modifier = modifier, currentUser = currentUser)
}

@Composable
fun ProfileDetailsSection(
    username: String,
    user: User = mockedVolunteer,
    modifier: Modifier,
    currentUser: Boolean
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .offset(y = 10.dp)
            .verticalScroll(scrollState)
    ){
        ProgressCircle(
            targetValue = 30.0f,
            radius = 400f,
            circleColor = Color.Green
        )
        Text(
            text = username,
            fontSize = 20.sp
        )
        when(user){
            is Volunteer -> VolunteerStats(volunteer = user, modifier = Modifier.padding(top = 20.dp), currentUser = currentUser)
            is Organizer -> OrganizerStats()
        }
    }
}

@Composable
fun VolunteerStats(
    volunteer: Volunteer,
    modifier: Modifier = Modifier,
    volunteerStatsLazyListState: LazyListState = LazyListState(0, 3),
    volunteerQuestsLazyListState: LazyListState = LazyListState(0, 3),
    currentUser: Boolean
) {
    //prizes
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Awards earned: ",
            modifier = Modifier.padding(10.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = volunteerStatsLazyListState
        ) {
            items(volunteer.prizes){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BadgeItem(prize = it, modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                    Text(
                        text = it.name)
                }

            }
        }

        if(currentUser){
            //logout button
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                LogoutButton()
            }
        }else{
            Text(
                text = "Completed quests: ",
                modifier = Modifier.padding(top = 20.dp, start = 10.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                state = volunteerQuestsLazyListState
            ) {
                items(volunteer.participations){
                    ParticipationItem(
                        quest = it
                    )
                }
            }
        }
    }
    
    //stats
}

@Composable
fun BadgeItem(
    prize: Badge,
    boxSize: Dp = 60.dp,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(boxSize / 10))
        .background(color = prize.color)
        .size(boxSize)){
        Image(
            painter = painterResource(id = prize.iconId),
            contentDescription = null,
            modifier = Modifier.padding(boxSize/10)
        )
    }
}

@Composable
fun ParticipationItem(
    quest: Quest = mockedQuest,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .semantics { selected = isSelected }
            .clip(CardDefaults.shape)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){
            Text(text = quest.title)
            val eventName = if(quest.event==null) "Unknown event" else quest.event.name
            val dates = if(quest.event?.dates?.size==2){
                //to from
                "To from"
            }else if(quest.event?.dates?.size==1){
                //date
                "On"
            }else{
                "Unknown dates"
            }
            Text(text  = "$eventName - $dates")
        }
    }
}

@Composable
fun OrganizerStats() {
    
}

@Preview
@Composable
fun LogoutButton(
    modifier: Modifier = Modifier
) {
    Button(onClick = { /*TODO*/ }, modifier = Modifier) {
        Text(text = "Log out")
    }
}