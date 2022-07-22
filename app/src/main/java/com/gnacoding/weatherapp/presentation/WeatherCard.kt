package com.gnacoding.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gnacoding.weatherapp.R
import com.gnacoding.weatherapp.presentation.ui.theme.Black
import com.gnacoding.weatherapp.presentation.ui.theme.White
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        val formattedTime = data.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
        Card(
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today $formattedTime",
                    modifier = Modifier.align(Alignment.End),
                    color = if (isSystemInDarkTheme()) White else Black
                )
                Spacer(modifier = Modifier.height(56.dp))
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(184.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "${data.temperatureCelsius}°C",
                    fontSize = 48.sp,
                    color = if (isSystemInDarkTheme()) White else Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = data.weatherType.weatherDesc,
                    fontSize = 16.sp,
                    color = if (isSystemInDarkTheme()) White else Black
                )
                Spacer(modifier = Modifier.height(48.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = data.pressure.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                        textStyle = TextStyle(color = if (isSystemInDarkTheme()) White else Black),
                        iconTint = if (isSystemInDarkTheme()) White else Black
                    )
                    WeatherDataDisplay(
                        value = data.humidity.roundToInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                        textStyle = TextStyle(color = if (isSystemInDarkTheme()) White else Black),
                        iconTint = if (isSystemInDarkTheme()) White else Black
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.roundToInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                        textStyle = TextStyle(color = if (isSystemInDarkTheme()) White else Black),
                        iconTint = if (isSystemInDarkTheme()) White else Black
                    )
                }
            }
        }
    }
}