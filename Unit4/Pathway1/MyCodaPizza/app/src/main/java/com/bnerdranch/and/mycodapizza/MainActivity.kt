package com.bnerdranch.and.mycodapizza

import android.inputmethodservice.Keyboard
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.semantics.Role.Companion.Checkbox

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToppingCell()
        }
    }

    @Composable
    fun ToppingCell() {
        Row {
            Checkbox(checked = true, onCheckedChange = {}
            )
            Column {
                Text(text = "Pineapple")
                Text(text = "Whole pizza")
            }
        }
    }
}