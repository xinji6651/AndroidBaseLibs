package app.allever.android.lib.network.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import app.allever.android.lib.core.base.AbstractActivity;
import app.allever.android.lib.core.ext.LoggerKt;
import app.allever.android.lib.core.function.network.HttpConfig;
import app.allever.android.lib.core.function.network.ResponseCallback;
import app.allever.android.lib.core.function.network.response.NetResponse;
import app.allever.android.lib.core.helper.GsonHelper;
import app.allever.android.lib.network.ApiService;
import app.allever.android.lib.network.R;

/**
 * @author allever
 */
public class NetActivityJava extends AbstractActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        TextView textView = findViewById(R.id.tvResult);

        HttpConfig.INSTANCE.baseUrl("https://www.wanandroid.com/")
                .successCode(0)
                .init(ApiService.INSTANCE);

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppRepository.INSTANCE.getBannerCall(new BannerResponseCache(), new ResponseCallback<List<BannerData>>() {
                    @Override
                    public void onFail(@NonNull NetResponse<List<BannerData>> response) {
                        LoggerKt.log(response.getMsg());
                        textView.setText(GsonHelper.INSTANCE.toJson(response));
                    }

                    @Override
                    public void onSuccess(@NonNull NetResponse<List<BannerData>> response) {
                        LoggerKt.log(response.getMsg());
                        textView.setText(GsonHelper.INSTANCE.toJson(response));
                    }
                });
            }
        });

//        NetRepository.INSTANCE.getBannerForJava(new DefaultContinuation<BaseResponse<?>>() {
//            @Override
//            public void onSuccess(@NonNull BaseResponse<?> response) {
//                List<BannerData> list = (List<BannerData>) response.getData();
//                LogUtils.INSTANCE.d(list.size() + "");
//            }
//
//            @Override
//            public void onFail(int code, @NonNull String msg) {
//
//            }
//        });
//
//        NetRepository.INSTANCE.getBannerForJava(new Continuation<BaseResponse<?>>() {
//            @NonNull
//            @Override
//            public CoroutineContext getContext() {
//                return Dispatchers.getIO();
//            }
//
//            @Override
//            public void resumeWith(Object o) {
//                if (o != null) {
//                    BaseResponse<List<BannerData>> response = (BaseResponse<List<BannerData>>) o;
//                    response.getCode();
//                }
//            }
//        });
//
//        NetRepository.INSTANCE.test(new Continuation<String>() {
//            @NonNull
//            @Override
//            public CoroutineContext getContext() {
//                return Dispatchers.getDefault();
//            }
//
//            @Override
//            public void resumeWith(@NonNull Object o) {
//                LogUtils.INSTANCE.d(o.toString());
//            }
//        });
    }
}
