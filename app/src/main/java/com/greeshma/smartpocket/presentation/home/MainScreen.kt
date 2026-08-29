package com.greeshma.smartpocket.presentation.home

import android.graphics.ImageDecoder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Beenhere
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.FilePresent
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.FilePresent
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.greeshma.smartpocket.MainActivity
import com.greeshma.smartpocket.R
import com.greeshma.smartpocket.domain.model.PocketItem
import com.greeshma.smartpocket.presentation.Items.Items
import com.greeshma.smartpocket.presentation.add.AddItemRoute
import com.greeshma.smartpocket.presentation.navigation.Screen
import com.greeshma.smartpocket.ui.theme.CategoryBackground
import com.greeshma.smartpocket.ui.theme.ErrorRed
import com.greeshma.smartpocket.ui.theme.ExpiryBackground
import com.greeshma.smartpocket.ui.theme.PurplePrimary
import com.greeshma.smartpocket.ui.theme.PurplePrimaryContainer
import com.greeshma.smartpocket.ui.theme.SmartPocketTheme
import com.greeshma.smartpocket.ui.theme.TotalBackground
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale
import javax.inject.Inject
@Composable
fun TopBar()
{
Row(modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically) {
    Row(modifier = Modifier.padding(5.dp),
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically)
    {
        Icon(
            imageVector = Icons.Default.Beenhere, contentDescription = "smartpocket",
            modifier = Modifier.size(30.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black))
                {
                    append( "Smart")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary))
                {
                    append( "Pocket")
                }
         } , modifier = Modifier.padding(2.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

    }
    Row(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(5.dp),
        horizontalArrangement = Arrangement.End
    )
    {

        TopBarIcon(Icons.Default.Search)
        Spacer(Modifier.width(10.dp))

        TopBarIcon(Icons.Default.Settings)

    }
}

}
@Composable
fun BottomBar(
    currentRoute:String?,
    onClick: (Screen)->Unit)
{
    NavigationBar {
        Screen.bottomScreens.forEach {screen->
            NavigationBarItem(
                selected = screen.route == currentRoute,
                onClick = { onClick(screen)},
                icon = { Icon(imageVector = screen.icon, contentDescription = "icon", tint = MaterialTheme.colorScheme.primary) },
                label = { Text(text = screen.title )}
                
            )

        }
    }
}

@Composable
fun TopBarIcon(icon: ImageVector)
{
    Surface(shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 5.dp) {
        Box(modifier = Modifier.padding(5.dp))
        {
            Icon(
                imageVector = icon,
                contentDescription = "smartpocket",
                modifier = Modifier.size(20.dp),
                tint = Color.White,
            )
        }
    }
}

@Composable
fun GreetingSection(greeting:String,
                    username:String,
                    modifier: Modifier)
{
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically)
    {
        Column(verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxWidth(0.7f))
        {

            Text(text = greeting, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium,modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Spacer(modifier = modifier.size(4.dp))
            Text(text = username, style= MaterialTheme.typography.bodyMedium,modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
        Image(painter =
            painterResource(R.drawable.dodc_illus),
            contentDescription = "document",modifier
                .size(100.dp)
                .fillMaxWidth(0.3f),
            alignment = Alignment.Center,

        )
    }
}
@Composable
fun HomeHeader(
    name: String,
    onNotificationClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .clip(
                RoundedCornerShape(
                    bottomStart = 32.dp,
                    bottomEnd = 32.dp
                )
            )
    ) {

        // Background illustration
        Image(
            painter = painterResource(
                id = R.drawable.home_background
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Optional overlay to make text easier to read
        Box(
            modifier = Modifier
                .fillMaxSize()
//                .background(
//                    MaterialTheme.colorScheme.primaryContainer.copy(
//                        alpha = 0.25f
//                    )
//                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 20.dp,
                    bottom = 28.dp
                )
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

//                IconButton(
//                    onClick = onNotificationClick,
//                    modifier = Modifier
//                        .size(46.dp)
//                        .background(
//                            MaterialTheme.colorScheme.surface.copy(
//                                alpha = 0.9f
//                            ),
//                            CircleShape
//                        )
//                ) {
//                    Icon(
//                        imageVector = Icons.Outlined.Notifications,
//                        contentDescription = "Notifications",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Good Morning 👋",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = name,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Keep your important items organized",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}
//@Composable
//fun SummarySection() {
//    Row(modifier = Modifier.fillMaxWidth())
//    {
//        SummaryCard(Icons.Default.FilePresent,Modifier.weight(1f), count = "12", msg = "All items are in one place")
//        Spacer(Modifier.size(1.dp))
//        SummaryCard(Icons.Default.Alarm,Modifier.weight(1f),count="3", msg = "Expiring soon")
//
//    }
//}
@Composable
fun QuickSummary(
    totalItems: Int,
    expiringItems: Int,
    totalCategories: Int
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        Text(
            text = "Quick Summary",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {

                SummaryCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.Inventory2,
                    count = totalItems.toString(),
                    msg = "Total Items",
                    cardColor = TotalBackground,
                    iconColor = PurplePrimary
                )


                SummaryCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.WarningAmber,
                    count = expiringItems.toString(),
                    msg = "Expiring",
                    cardColor = ExpiryBackground,
                    iconColor = ErrorRed
                )



        }
    }
}

@Composable
fun SummaryCard(icon: ImageVector,modifier: Modifier,count: String,msg:String,cardColor: Color,iconColor: Color) {
    Card (shape = RoundedCornerShape(10.dp), modifier = modifier
        .padding(10.dp)
        .height(120.dp)
       ,
     colors = CardColors(containerColor = cardColor, contentColor = Color.Black, disabledContentColor = Color.Gray, disabledContainerColor=Color.Gray)
   ) {

        Row(modifier = Modifier.padding(10.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                Surface(shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 5.dp) {
                    Box(modifier = Modifier.padding(5.dp))
                    {
                        Icon(
                            imageVector = icon,
                            contentDescription = "smartpocket",
                            modifier = Modifier.size(30.dp),
                            tint = iconColor,
                        )
                    }
                }
                Spacer(modifier = Modifier.size(5.dp))
                Text(text = count, style = MaterialTheme.typography.titleLarge, color = Color.Black,
                    fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Text(text = msg)
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column()
            {

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpirySection(
    items: List<PocketItem>,
    onViewAllClick:()-> Unit) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Expiring Soon",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "View All",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    onViewAllClick()
                }
            )
        }



            ExpiryCard(
                item = items.first(),
                icon = Icons.Default.FileCopy
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpiryCard(
    item: PocketItem,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {

    val daysRemaining = remember(item.expiryDate) {
        calculateDaysRemaining(item.expiryDate)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 2.dp),
    ) {

        // Status strip
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(8.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 25.dp,
                        bottomStart = 25.dp
                    )
                )
                .background(ErrorRed)
        )

        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            shape = RoundedCornerShape(
                topEnd = 12.dp,
                bottomEnd = 12.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = ExpiryBackground
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(46.dp)
                        .background(
                            MaterialTheme.colorScheme.surface.copy(
                                alpha = 0.9f
                            ),
                            CircleShape
                        )
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Document",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "Expiring Soon",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Purchased on ${item.purchaseDate?.toDateString()}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {

                    Text(
                        text = "$daysRemaining Days",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Red
                    )

                    item.expiryDate?.toDateString()?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun calculateDaysRemaining(
    expiryDate: Long?
): Long {

    if (expiryDate == null) return 0

    val today = LocalDate.now()

    val expiry = Instant.ofEpochMilli(expiryDate)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    return ChronoUnit.DAYS.between(
        today,
        expiry
    )
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable

fun RecentSection(
    items: List<PocketItem>,
    onViewAllClick: () -> Unit
) {
    if (items.isEmpty()) return

    Column(
        modifier = Modifier.padding(10.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Recently Added",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "View All",
                modifier = Modifier.clickable {
                    onViewAllClick()
                },
                color = MaterialTheme.colorScheme.primary
            )
        }

        val recentItem = items.first()

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {

            RecentItem(
                Icons.Default.PictureAsPdf,
                modifier = Modifier,
                item = recentItem
            )
        }
    }
}
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun RecentItem(
    icon: ImageVector,
    item: PocketItem,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        println("image uri:${item.imageUri}")
        AsyncImage(
            model = item.imageUri?.toUri(),
            contentDescription = item.name,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            onSuccess = {
                println("IMAGE SUCCESS: ${item.imageUri}")
            },
            onError = {
                println("IMAGE ERROR: ${it.result.throwable}")
                println("IMAGE URI: ${item.imageUri}")
            }
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium
            )

            item.purchaseDate?.toDateString().let {
                Text(
                    text = "Added on $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }

        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colorScheme.primary
        ) {

            Text(
                text = item.category,
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 4.dp
                ),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.background
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null
        )
    }
}
fun Long.toDateString(): String {
    val formatter = SimpleDateFormat(
        "dd MMM yyyy",
        Locale.getDefault()
    )
    return formatter.format(Date(this))
}
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    SmartPocketTheme {
     QuickSummary(12,3,3)
    }
}

