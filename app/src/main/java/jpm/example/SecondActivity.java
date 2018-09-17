package jpm.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jpm.example.retrofit.RetrofitClient;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    List<ModelCategory.Datum> modelCategoryList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        hitApi();
    }

    private void hitApi() {
        mCompositeDisposable = new CompositeDisposable();
        Observable<ModelCategory> observable = RetrofitClient.getApiService().getCategory();
        mCompositeDisposable.add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getItems())
        );

    }


    private DisposableObserver<ModelCategory> getItems() {
        return new DisposableObserver<ModelCategory>() {
            @Override
            public void onNext(ModelCategory modelCategory) {
                modelCategoryList = modelCategory.getData();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("error", e.getMessage());
            }

            @Override
            public void onComplete() {
                mAdapter = new AdapterCategory(modelCategoryList);
                mRecyclerView.setAdapter(mAdapter);
            }
        };
    }


    private ModelCategory.Datum getDummyData(String category_name) {
        ModelCategory.Datum d = new ModelCategory.Datum();
        d.setCategory(category_name);
        return d;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        mCompositeDisposable.clear();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
