package com.example.movieshow.ui.presentance.homeScreen.screenHandle.homeSetUp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.example.movieshow.ui.presentance.navGraph.graph.BottomNavGraph
import com.example.movieshow.ui.presentance.navGraph.screenRoot.BottomRoute
import com.exyte.animatednavbar.AnimatedNavigationBar


@Composable
fun MediaMainScreen() {
    val navHostController = rememberNavController()
    val selectedItem = rememberSaveable {
        mutableIntStateOf(0)
    }


    val items = mutableListOf(
        BottomItems.HomeScreen,
        BottomItems.SearchScreen,
        BottomItems.DiscoverScreen,
        BottomItems.TvScreen
    )

    Scaffold (
        bottomBar = {
            AnimatedNavigationBar(
                selectedIndex = selectedItem.intValue,
                ballColor = MaterialTheme.colorScheme.secondary,
                barColor = MaterialTheme.colorScheme.surface
            ) {
               items.forEachIndexed { index, bottomItems ->
                   NavigationRailItem(
                       selected =selectedItem.intValue==index,
                       onClick = {
                                 selectedItem.intValue=index
                           when(selectedItem.intValue){
                                0 -> navHostController.navigate(bottomItems.route){
                                    launchSingleTop=true
                                    restoreState=true
                                }
                                1 -> navHostController.navigate(bottomItems.route){
                                    launchSingleTop=true
                                    restoreState=true
                                }
                                2 -> navHostController.navigate(bottomItems.route){
                                    launchSingleTop=true
                                    restoreState=true
                                }
                                3 -> navHostController.navigate(bottomItems.route){
                                    launchSingleTop=true
                                    restoreState=true
                                }
                           }
                       },
                       icon = {
                           Icon(
                               imageVector =if(selectedItem.intValue==index) bottomItems.selectedIcon
                               else bottomItems.unSelectedIcon,
                               contentDescription =bottomItems.title
                           )
                       },
                       label = {
                           Text(text = bottomItems.title)
                       },
                       colors = NavigationRailItemDefaults.colors(
                           indicatorColor = MaterialTheme.colorScheme.surface
                       )
                   )
               }
            }
        }
    ){paddingValues ->
        BottomNavGraph(
            navHostController =navHostController,
            selectedItem =selectedItem,
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding())
        )
    }

}

sealed class BottomItems(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route: String
)
{
    object HomeScreen : BottomItems(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
        route = BottomRoute.HomeScreen.route
    )

    object DiscoverScreen : BottomItems(
        title = "Discover",
        selectedIcon = Icons.Filled.Explore,
        unSelectedIcon = Icons.Outlined.Explore,
        route = BottomRoute.DiscoverScreen.route
    )

    object TvScreen : BottomItems(
        title = "Tv",
        selectedIcon = Icons.Filled.LiveTv,
        unSelectedIcon = Icons.Outlined.LiveTv,
        route = BottomRoute.TvScreen.route
    )

    object SearchScreen : BottomItems(
        title = "Search",
        selectedIcon = Icons.Filled.Search,
        unSelectedIcon = Icons.Outlined.Search,
        route = BottomRoute.SearchScreen.route
    )

}

