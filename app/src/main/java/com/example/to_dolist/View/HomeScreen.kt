package com.example.to_dolist.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.to_dolist.Data.Task
import com.example.to_dolist.ViewModel.TaskViewModel


@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel = viewModel()
){

    val taskList = taskViewModel.getAllTasks.collectAsState(initial = listOf())
    var dialogControllerForAdd by remember{mutableStateOf(false)}
    var dialogTextFieldForAdd by remember{mutableStateOf("")}

    var updateDialogController by remember{ mutableStateOf(false) }
    var updateDialogTextField by remember{ mutableStateOf("") }
    var updateTaskId by remember { mutableStateOf(0L) }

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
                Task(task = it, taskViewModel = taskViewModel){
                    taskStatement, taskId ->
                    updateDialogController = true
                    updateDialogTextField = taskStatement
                    updateTaskId = taskId
                }
            }
        }

        //add button
        FloatingActionButton(
            onClick = {
                dialogControllerForAdd = true
            },
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
        
        Spacer(modifier = Modifier.padding(30.dp))

        if(updateDialogController){
            AlertDialog(
                onDismissRequest = {
                    updateDialogController = false
                },
                confirmButton = {},
                title = {
                    Text(text = "Update Task")
                },
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = updateDialogTextField,
                            onValueChange = {
                                updateDialogTextField = it
                            }
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        Button(
                            onClick = {
                                updateDialogController = false
                                taskViewModel.updateTask(Task(
                                    id = updateTaskId,
                                    isCompleted = false,
                                    taskStatement = updateDialogTextField
                                ))
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
                            Text(text = "Update")
                        }
                    }
                }
            )
        }

        if(dialogControllerForAdd){
            AlertDialog(
                onDismissRequest = { dialogControllerForAdd = false },
                confirmButton = {},
                title = {
                    Text(text = "Add new task")
                },
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = dialogTextFieldForAdd,
                            onValueChange = {
                                dialogTextFieldForAdd = it
                            }
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        Button(
                            onClick = {
                                dialogControllerForAdd = false
                                taskViewModel.addTask(Task(taskStatement = dialogTextFieldForAdd.trim()))
                                dialogTextFieldForAdd = ""
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
fun Task(
    task: Task,
    taskViewModel: TaskViewModel,
    onClick: (String, Long) -> Unit
){
    Card(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            //Checkbox
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = {
                    taskViewModel.updateTask(task.copy(
                        isCompleted = !task.isCompleted
                    ))
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black
                )
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

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //task statement
                Text(
                    text = task.taskStatement,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(vertical = 12.dp)
                        .clickable {
                            onClick(task.taskStatement, task.id)
                        },
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

                //delete button
                IconButton(
                    onClick = {
                        taskViewModel.deleteTask(task)
                    }
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
        }
    }
}
