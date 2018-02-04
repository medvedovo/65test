package com.test.employees.domain.interactor;

import android.util.Log;

import com.test.employees.data.net.RetrofitRepository;
import com.test.employees.data.db.DBRepository;
import com.test.employees.data.net.dataclass.Response;
import com.test.employees.other.eventbus.GetDataEvent;
import com.test.employees.other.eventbus.Status;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class LoadDataInteractor {
    private Disposable disposable;

    public void execute() {
        RetrofitRepository.getApi().getData().subscribe(new SingleObserver<Response>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(Response response) {
                Log.i("", response.toString());
                DBRepository.saveData(response.response);
                EventBus.getDefault().post(new GetDataEvent(Status.SUCCESS));
            }

            @Override
            public void onError(Throwable e) {
                Log.e("", e.getMessage());
                EventBus.getDefault().post(new GetDataEvent(Status.NOT_FOUND));
            }
        });
    }

    public void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
