package lat.pam.pizzapp.api;

import java.util.List;

import lat.pam.pizzapp.model.ProductModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("uasresto")
    Call<List<ProductModel>> getProduct();
}
