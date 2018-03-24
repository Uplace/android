package es.uplace.uplace.retrofit;

import java.util.List;

import es.uplace.uplace.domain.Property;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PropertyService {

    @GET("properties")
    Call<List<Property>> findAllProperties();
}
