package jpm.example.retrofit;

import io.reactivex.Observable;
import jpm.example.ModelCategory;
import retrofit2.http.GET;


public interface ApiClientInterface {

    @GET("app/request_category.php")
    Observable<ModelCategory> getCategory();
}
