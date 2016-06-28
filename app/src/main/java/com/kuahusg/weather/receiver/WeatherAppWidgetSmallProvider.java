package com.kuahusg.weather.receiver;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.RemoteViews;

import com.kuahusg.weather.R;
import com.kuahusg.weather.activities.WeatherActivity;
import com.kuahusg.weather.model.Forecast;
import com.kuahusg.weather.util.Utility;

import java.util.List;

/**
 * Created by kuahusg on 16-6-18.
 * com.kuahusg.weather.receiver
 */
public class WeatherAppWidgetSmallProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        String tempAndDate;
        tempAndDate = Utility.loadForecastInfoFromDatabase();
        String temp_now = "NaN";
        if (!TextUtils.isEmpty(tempAndDate)) {
            String[] t = tempAndDate.split("\\|");
            temp_now = t[0];
        }
        List<Forecast> forecastList = Utility.loadForecastFromDatabase();
        Forecast forecast_to_show = null;
        if (forecastList != null && forecastList.size() > 0) {
            forecast_to_show = forecastList.get(0);
        } else {
            return;
        }


        /*
        update view
         */

        for (int appwidgetId :
                appWidgetIds) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.weather_appwidget_small);
            rv.setImageViewResource(R.id.weather_pic, getWeatherPicId(forecast_to_show.getText()));
            rv.setTextViewText(R.id.weather_info, forecast_to_show.getText());
            rv.setTextViewText(R.id.temp_now, temp_now);

            Intent intent = new Intent(context, WeatherActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            rv.setOnClickPendingIntent(R.id.main_container, pendingIntent);
            appWidgetManager.updateAppWidget(appwidgetId, rv);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private int getWeatherPicId(String weatherText) {
        if (weatherText.contains("Thunderstorms")) {
            return R.drawable.weather_thunderstorm;

        } else if (weatherText.contains("Cloudy")) {
            return R.drawable.weather_cloudy;
        } else if (weatherText.contains("Sunny")) {
            return R.drawable.weather_sun_day;

        } else if (weatherText.contains("Showers") || weatherText.contains("Rain")) {
            return R.drawable.weather_rain;
        } else if (weatherText.contains("Breezy")) {
            return R.drawable.weather_wind;
        }
        return 0;


    }
}
