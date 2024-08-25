package com.example.to_dolist.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_dolist.Data.Task


@Composable
fun HomeScreen(
//    taskViewModel: TaskViewModel
){

//    val taskList = taskViewModel.getAllTasks.collectAsState(initial = listOf())

    val dummyList = listOf(Task(101, taskStatement = "Complete Project"), Task(102, taskStatement = "Quickly"))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFf0d07a), Color(0xFFeda479))
                )
            ),
        horizontalAlignment = Alignment.End
    ){
        //headline
        Text(
            text = "To-D0 List",
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color(0xA67E3409),
            textDecoration = TextDecoration.Underline
        )

        //task list
        LazyColumn (
            modifier = Modifier.padding(8.dp).weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(dummyList){
                Task(task = it)
            }

            /*items(taskList.value){

            }*/
        }

        //add button
        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

@Composable
fun Task(task: Task){

    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            //Radio Button
            RadioButton(
                selected = task.isCompleted,
                onClick = {
                    task.isCompleted = !task.isCompleted
                }
            )

            //Vertical Divider
            VerticalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxHeight(),
                thickness = 2.dp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.padding(8.dp))

            //task statement
            Text(
                text = task.taskStatement,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 12.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                textDecoration =
                    if (task.isCompleted){
                        TextDecoration.LineThrough
                    }
                    else{
                        TextDecoration.None
                    }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .padding(16.dp)
    ) {
        Task(task = Task(101, taskStatement = "Complete Project"))
    }*/

    HomeScreen()
}
