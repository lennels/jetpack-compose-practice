package com.example.mallsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mallsapp.model.Mall

@Composable
fun MallApp(
//    windowSize: WindowWidthSizeClass,
//    onBackPressed: () -> Unit,
) {
    val viewModel: MallViewModel = viewModel()
    val mallUiState by viewModel.mallUiState.collectAsState()
    Scaffold(
        topBar = {
//            MallAppBar({}, true, windowSize)
        }
    ) { innerPadding ->
        MallList(
            mallUiState.mallList,
            { viewModel.selectMall(it) },
            modifier = Modifier.padding(innerPadding)
        )
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
    LazyColumn(modifier = modifier) {
        items(mall) {
            MallItem(it, onClick)
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
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                Image(
                    painter = painterResource(mall.imgRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
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

@Composable
fun MallDetail(

) {

}

@Preview(showBackground = true)
@Composable
fun MallAppPreview() {
    MallApp()
}

@Preview(showBackground = true)
@Composable
fun MallDetailPreview() {
    MallDetail()
}