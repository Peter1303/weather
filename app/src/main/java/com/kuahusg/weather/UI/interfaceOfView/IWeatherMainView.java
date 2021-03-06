package com.kuahusg.weather.UI.interfaceOfView;

import android.content.DialogInterface;

import com.kuahusg.weather.UI.base.IBaseView;
import com.kuahusg.weather.model.bean.Forecast;
import com.kuahusg.weather.model.bean.ForecastInfo;

import java.util.List;

/**
 * Created by kuahusg on 16-9-27.
 */

public interface IWeatherMainView extends IBaseView {

    @Override
    void init();

    @Override
    void start();

    @Override
    void finish();

    void goToSelectLocationActivity();

    void loadWeatherError(String message);

    void showAlertDialog(String title, String message, String negativeString, String positiveString,
                         DialogInterface.OnClickListener listener);


    void loadWeatherDataSourceFinish(List<Forecast> forecasts, ForecastInfo info);

    void setToolbarSubTitle(String title);
}
