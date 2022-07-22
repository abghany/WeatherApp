package com.gnacoding.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gnacoding.weatherapp.domain.weather.WeatherData
import com.gnacoding.weatherapp.presentation.ui.theme.Black
import java.time.format.DateTimeFormatter

@Composable
fun WeeklyWeatherDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    textColor: Color = Black
) {
    val formattedDay = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("EEEE")
        )
    }
    val formattedDate = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("dd MMM")
        )
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .padding(vertical = 8.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formattedDay,
                fontSize = 16.sp,
                color = textColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = formattedDate,
                color = Color.LightGray
            )
        }
        Text(
            text = "${weatherData.temperatureCelsius}°C",
            color = textColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.width(56.dp)
        )
    }
}