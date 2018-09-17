package jpm.example.retrofit;

import java.util.List;

import io.reactivex.Observable;
import jpm.example.ModelCategory;
import retrofit2.http.GET;


public interface ApiClientInterface {

    @GET("app/request_category.php")
    Observable<List<ModelCategory>> getCategory();
}
