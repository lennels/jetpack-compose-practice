package com.example.mallsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mallsapp.model.Mall

@Composable
fun MallApp(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {

        }
    ) { innerPadding ->
        MallAppBar({}, true, windowSize, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MallAppBar(
    onBackPressed: () -> Unit,
    isShowingList: Boolean,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        title = {
            Text(
                "Singapore Malls",
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )

    )
}

@Composable
fun MallList(
    mall: List<Mall>,
    onClick: (Mall) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn() {
        items(mall) {
            MallItem(it, {})
        }
    }
}

@Composable
fun MallItem(
    mall: Mall,
    onItemClick: (Mall) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
    ) {
        Row {
            Image(
                painter = painterResource(mall.imgRes),
                contentDescription = null
            )
            Column {
                Text(
                    mall.name,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    mall.region.toString()
                )
            }
        }
    }
}