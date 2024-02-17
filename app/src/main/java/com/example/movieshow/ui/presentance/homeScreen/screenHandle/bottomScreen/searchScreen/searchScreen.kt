package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.searchScreen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieshow.ui.presentance.component.TvItem
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.searchScreen.stateEvent.SearchEvent
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.searchScreen.stateEvent.SearchState
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.homeSetUp.BottomItems


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navHostController: NavHostController,
    selectedItem: MutableState<Int>,
    searchState: SearchState,
    searchEvent: (SearchEvent) -> Unit
) {
    BackHandler(enabled = true) {
        selectedItem.value = 0
        navHostController.navigate(BottomItems.HomeScreen.route)
    }
    val focus = remember {
        FocusRequester()
    }
    val keyBoardManager = LocalFocusManager.current
    val keyBoardControl = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = Unit) {
        focus.requestFocus()
    }
    Box(
        modifier = Modifier
    ) {
        SearchBar(
            query = searchState.query,
            onQueryChange = {
                searchEvent(SearchEvent.OnValueChange(it))
            },
            onSearch = {
                searchEvent(SearchEvent.OnValueChange(it))
                searchEvent.invoke(SearchEvent.OnSearch)
                keyBoardManager.clearFocus(true)
                keyBoardControl?.hide()
            },
            active = searchState.searchBarActive,
            onActiveChange = {
                searchEvent.invoke(SearchEvent.SearchActive(it))
            },
            placeholder = {
                Text(text = "Search movie or tv")
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (searchState.query.isNotEmpty()) searchEvent(SearchEvent.OnValueChange(""))
                    else searchEvent(SearchEvent.SearchActive(false))
                }) {
                    Icon(
                        imageVector = Icons.Sharp.Close,
                        contentDescription = "close"
                    )
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Sharp.Search,
                    contentDescription = "search"
                )
            },
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 4.dp,
            modifier = Modifier
                .focusRequester(focus)
                .fillMaxWidth()

        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = rememberLazyGridState()
            ) {
                items(searchState.searchListData.size) { index ->
                    Log.d("myTag", "index: $index")
                    TvItem(searchData = searchState.searchListData[index]) {
                        if (index >= searchState.searchListData.size - 1 && !searchState.isLoading) {
                            searchEvent(SearchEvent.Paginate(searchState.searchPage))
                            Log.d("myTag", "indexSize: $index")

                        }
                    }
                }
            }
        }
    }


}

//  LazyVerticalGrid(
//                columns = GridCells.Fixed(2),
//                state = rememberLazyGridState()
//            ) {
//                items(searchState.searchListData.size) { index ->
//                    Log.d("myTag", "index: $index")
//                    TvItem(searchData = searchState.searchListData[index]) {
//                        if (index >= searchState.searchListData.size - 1 && !searchState.isLoading) {
//                            searchEvent(SearchEvent.Paginate(searchState.searchPage))
//                            Log.d("myTag", "indexSize: $index")
//
//                        }
//                    }
//                }
//            }