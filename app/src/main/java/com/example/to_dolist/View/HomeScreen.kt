package com.example.to_dolist.View

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.to_dolist.Data.Graph
import com.example.to_dolist.Data.Task
import com.example.to_dolist.ViewModel.TaskViewModel
import kotlin.concurrent.timerTask


@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel = viewModel()
){

    val tag = "TEST"
    Log.d(tag, "Entered Home Screen")

    val taskList = taskViewModel.getAllTasks.collectAsState(initial = listOf())
    Log.d(tag, "taskList created")

    val dummyList = listOf(Task(101, taskStatement = "Complete Project"), Task(102, taskStatement = "Quickly"))

    var dialogController by remember{mutableStateOf(false)}
    var dialogTextField by remember{mutableStateOf("")}

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

        Log.d(tag, "Enter column scope")

        //spacer to avoid notch
        Spacer(modifier = Modifier.padding(20.dp))

        //headline
        Text(
            text = "To-D0 List",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color(0xA67E3409),
            textDecoration = TextDecoration.Underline
        )

        //task list
        LazyColumn (
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /*items(dummyList){
                Task(task = it)
            }*/
            
            items(taskList.value, key = {it.id}){
                Task(task = it)
            }
        }

        //add button
        FloatingActionButton(
            onClick = {
                dialogController = true
            },
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }

        if(dialogController){
            AlertDialog(
                onDismissRequest = { dialogController = false },
                confirmButton = {},
                title = {
                    Text(text = "Add new task")
                },
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = dialogTextField,
                            onValueChange = {
                                dialogTextField = it
                            }
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        Button(
                            onClick = {
                                dialogController = false
                                taskViewModel.addTask(Task(taskStatement = dialogTextField.trim()))
                                dialogTextField = ""
                            },
                            colors = ButtonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White,
                                disabledContentColor = Color.DarkGray,
                                disabledContainerColor = Color.White
                            ),
                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 8.dp
                            )
                        ) {
                            Text(text = "Add")
                        }
                    }
                }
            )
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

//    HomeScreen(taskViewModel = TaskViewModel())
}
