package com.compose.camp.hun.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.camp.hun.lemonade.ui.theme.BorderLineColor
import com.compose.camp.hun.lemonade.ui.theme.LemonadeTheme
import com.compose.camp.hun.lemonade.ui.theme.TransParent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                MakeLemonade(
                    initLemonadeState = LevelOfLemonade.LemonTree,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    }
}

sealed class LevelOfLemonade(
    @StringRes val desc: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val imageDesc: Int
) {
    object LemonTree :
        LevelOfLemonade(R.string.lemon_tree_desc, R.drawable.lemon_tree, R.string.lemon_title)

    class Lemon(val timesOfTab: Int) :
        LevelOfLemonade(R.string.lemon_desc, R.drawable.lemon_squeeze, R.string.lemon_title)

    class Lemonade(val timesOfTab: Int) :
        LevelOfLemonade(R.string.tap_lemon_desc, R.drawable.lemon_drink, R.string.tap_lemon_title)

    object EmptyGlass : LevelOfLemonade(
        R.string.empty_glass_desc,
        R.drawable.lemon_restart,
        R.string.empty_glass_title
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        MakeLemonade(
            initLemonadeState = LevelOfLemonade.LemonTree,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

/**
 * 고민되는 문제.
 * 1. 이미지를 어떻게 클릭 가능하도록 할 것인가.
 *  1-1. Image를 Button으로 감싸자 -> Button 기본색상이 나오는데, 이걸 어떻게 투명하게 할 것인가.
 */
@Composable
fun MakeLemonade(initLemonadeState: LevelOfLemonade, modifier: Modifier = Modifier) {
    var tabCount by remember { mutableStateOf(0) }
    var stateOfLemonade by remember { mutableStateOf(initLemonadeState) }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = stateOfLemonade.desc), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                tabCount = changeTabCount(stateOfLemonade, tabCount)
                stateOfLemonade = changeState(stateOfLemonade, tabCount)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = TransParent),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
        ) {
            Image(
                painter = painterResource(id = stateOfLemonade.imageRes),
                contentDescription = stringResource(
                    id = stateOfLemonade.imageDesc
                ),
                modifier = Modifier.border(1.dp, BorderLineColor)
            )
        }
    }
}

private fun changeTabCount(lemonState: LevelOfLemonade, prevTabCount: Int): Int {
    return when (lemonState) {
        is LevelOfLemonade.Lemon, is LevelOfLemonade.Lemonade -> prevTabCount + 1
        else -> prevTabCount
    }
}

private fun changeState(prevState: LevelOfLemonade, tabCount: Int = 0): LevelOfLemonade {
    return when (prevState) {
        LevelOfLemonade.LemonTree -> LevelOfLemonade.Lemon((3..10).random())
        is LevelOfLemonade.Lemon -> {
            if (tabCount >= prevState.timesOfTab) LevelOfLemonade.Lemonade((3..10).random())
            else prevState
        }
        is LevelOfLemonade.Lemonade -> {
            if (tabCount >= prevState.timesOfTab) LevelOfLemonade.EmptyGlass
            else prevState
        }
        LevelOfLemonade.EmptyGlass -> LevelOfLemonade.LemonTree
    }
}
