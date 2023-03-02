package com.mustafaunlu.composeinstaprofileui.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mustafaunlu.composeinstaprofileui.R
import com.mustafaunlu.composeinstaprofileui.model.ImageWithText


@Composable
fun ProfileScreen() {

    var selectedIndex by remember {
        mutableStateOf(0)
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
            TopBar(username = "ragnarmusti")
            ProfileSection(
                profileImage = painterResource(id =R.drawable.ragnar ),
                numberOfPosts = "589" ,
                numberOfFollowers = "100k",
                numberOfFollowing = "71"
            )
        Spacer(modifier = Modifier.height(25.dp))
        ButtonSection(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(25.dp))
        HighlightSection(highlights = listOf(
            ImageWithText(image = painterResource(id = R.drawable.war),"Wars"),
            ImageWithText(image = painterResource(id = R.drawable.journey),"Journeys"),
            ImageWithText(image = painterResource(id = R.drawable.kattegat),"Home"),
            ImageWithText(image = painterResource(id = R.drawable.maps),"Maps")

        ), modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(10.dp))
        PostTabView(imageWithTexts = listOf(
            ImageWithText(painterResource(id = R.drawable.ic_grid), text = "Posts"),
            ImageWithText(painterResource(id = R.drawable.ic_reels), text = "Reels"),
            ImageWithText(painterResource(id = R.drawable.ic_igtv), text = "Igtv"),
            ImageWithText(painterResource(id = R.drawable.profile), text = "Profile")
        )){
            selectedIndex=it
        }
        when(selectedIndex){
            0 -> PostSection(posts = listOf(
                painterResource(id = R.drawable.v1),
                painterResource(id = R.drawable.v2),
                painterResource(id = R.drawable.v3),
                painterResource(id = R.drawable.v4),
                painterResource(id = R.drawable.v5),
                painterResource(id = R.drawable.v6),
            ), modifier = Modifier.fillMaxWidth())
        }


    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostSection(
    posts : List<Painter>,
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(cells = GridCells.Fixed(3),
            modifier = modifier.scale(1.01f)
        ){
        items(posts.size){
            Image(painter = posts[it], contentDescription = null, contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .border(
                    width = 1.dp,
                    color = Color.White
                )
                )
        }

    }
}


@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    imageWithTexts: List<ImageWithText>,
    onTabSelected : (selectedIndex : Int) -> Unit
) {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    var inactiveColor = Color(0x77777777)

    TabRow(selectedTabIndex = selectedTabIndex,
            backgroundColor = Color.Transparent,
            contentColor = Color.Black,
            modifier = modifier
        ) {

        imageWithTexts.forEachIndexed { index, imageWithText ->

            Tab(selected = selectedTabIndex == index,
                onClick = {
                selectedTabIndex = index
                onTabSelected(index)
            },
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor
                ) {
                Icon(painter = imageWithText.image, contentDescription =imageWithText.text ,
                    tint = if(selectedTabIndex==index) Color.Black else inactiveColor,
                    modifier= Modifier
                        .padding(10.dp)
                        .size(20.dp))


            }

        }

    }
}

@Composable
fun HighlightSection(
    modifier: Modifier = Modifier,
    highlights : List<ImageWithText>
) {
    
    LazyRow(modifier = modifier){
        items(highlights.size){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(start = 15.dp)
            ) {
                RoundedImage(modifier = modifier.size(70.dp), profileImage = highlights[it].image)

                Text(text = highlights[it].text,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    )
                
            }
        }
    }
    
    
}

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier,
) {
    val minWidth= 95.dp
    val height = 30.dp

    Row(
        horizontalArrangement =  Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        ActionButton(
            text = "Following",
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .defaultMinSize(minWidth)
                .height(height)
        )

        ActionButton(
            text = "Message",
            modifier = Modifier
                .defaultMinSize(minWidth)
                .height(height)
        )

        ActionButton(
            text = "Email",
            modifier = Modifier
                .defaultMinSize(minWidth)
                .height(height)
        )

        ActionButton(
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .height(height)
        )

    }

}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text : String? = null,
    icon : ImageVector? = null
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(6.dp)
    ) {
        if(text != null){
            Text(text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
                )
        }
        if( icon != null){
            Icon(imageVector = icon, contentDescription =  null, tint = Color.Black )
        }

    }

}

//PROFILE SECTION
@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    profileImage : Painter,
    numberOfPosts : String,
    numberOfFollowers : String,
    numberOfFollowing : String
    ) {

    
    Column() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //Rounded Profile Image
            RoundedImage(modifier = Modifier
                .size(100.dp)
                .weight(3f), profileImage = profileImage)

            Spacer(modifier = modifier.width(14.dp))
            //Number of follow (one column)
            Row(modifier = Modifier
                .weight(7f), horizontalArrangement = Arrangement.SpaceAround,

                ) {
                InfoForFollow(modifier,numberOfPosts,"Posts")
                InfoForFollow(modifier,numberOfFollowers,"Followers")
                InfoForFollow(modifier,numberOfFollowing,"Following")
            }





        }

        //Description
        Description(nickname = "King of the Norh",
            desc ="Ragnar Lothbrok, Ragnar also spelled Regner or Regnar, Lothbrok also spelled Lodbrog or Lodbrok and King Ragnar."+" Visit for more info",
            url = "https://www.britannica.com/topic/Ragnar-Lothbrok",
            followedBy = listOf(
                "Rollo", "Lagertha Lothbrok"
            ),
            otherCount = 19


        )
    }


}

@Composable
fun Description(
    modifier: Modifier = Modifier,
    nickname : String,
    desc : String,
    url : String,
    followedBy : List<String>,
    otherCount : Int
) {

    val letterSpace = 0.5.sp
    val lineHeight = 20.sp



    
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)) {
        Text(text = nickname,  fontWeight = FontWeight.Bold, letterSpacing = letterSpace,lineHeight=lineHeight)
        Text(text = desc, letterSpacing = letterSpace,lineHeight=lineHeight)
        Text(text = url, color= Color(0xFF3D3D91), letterSpacing = letterSpace,lineHeight=lineHeight)

        if( followedBy.isNotEmpty()){
            Text(text = buildAnnotatedString {
                val boldStyle = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                append("Followed by ")
                followedBy.forEachIndexed { index, who ->
                    pushStyle(boldStyle)
                    append(who)
                    pop()
                    if (index < followedBy.size -1){
                        append(", ")
                    }

                }
                if (otherCount > 2){
                    append(" and ")
                    pushStyle(boldStyle)
                    append("$otherCount others")
                    pop()
                }
            }, letterSpacing = letterSpace,lineHeight=lineHeight)
        }
        
    }
    

}


@Composable
fun InfoForFollow(
    modifier: Modifier,
    numberOfInfo :String,
    info : String
) {

    Column( horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {

        Text(text =numberOfInfo, fontWeight = FontWeight.Bold, fontSize = 20.sp )
        Spacer(modifier = modifier.height(4.dp))
        Text(text =info, fontSize = 15.sp )


    }
    
}

@Composable
fun RoundedImage(
    modifier: Modifier,
    profileImage: Painter
) {
    Image(
        painter = profileImage, contentDescription = "profile image",
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(1.dp, Color.LightGray, CircleShape)
            .padding(3.dp)
            .clip(CircleShape),
        contentScale = ContentScale.FillBounds
    )
}

//TOP BAR
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    username : String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back",modifier=Modifier.size(24.dp))
        Text(text = username, fontSize = 20.sp, fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis)
        Icon(painter = painterResource(id = R.drawable.ic_bell), contentDescription = "notification",modifier=Modifier.size(24.dp))
        Icon(painter = painterResource(id = R.drawable.ic_dot_menu), contentDescription = "menu",modifier=Modifier.size(20.dp))


    }
}