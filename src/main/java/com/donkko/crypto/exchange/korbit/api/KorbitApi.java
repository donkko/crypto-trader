package com.donkko.crypto.exchange.korbit.api;

import com.donkko.crypto.exchange.korbit.api.response.KorbitAccessToken;
import com.donkko.crypto.exchange.korbit.api.response.KorbitTicker;
import com.donkko.crypto.exchange.korbit.api.response.Mybalances;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface KorbitApi {

    @FormUrlEncoded
    @POST("/v1/oauth2/access_token")
    Call<KorbitAccessToken> issueAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("username") String username,
            @Field("password") String password,
            @Field("grant_type") String grantType);

    @FormUrlEncoded
    @POST("/v1/oauth2/access_token")
    Call<KorbitAccessToken> refreshAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("refresh_token") String refreshToken,
            @Field("grant_type") String grantType);

    @GET("/v1/ticker")
    Call<KorbitTicker> getTicker(@Query("currency_pair") String currencyPair);

    @GET("/v1/user/balances")
    Call<Mybalances> getMyBalances(@Header("Authorization") String authorization);

    @FormUrlEncoded
    @POST("/v1/user/orders/buy")
    Call<Object> placeMarketBuy(
            @Header("Authorization") String authorization,
            @Field("currency_pair") String currencyPair,
            @Field("type") String type,
            @Field("fiat_amount") Integer fiatAmount,
            @Field("nonce") Long nonce);

    @FormUrlEncoded
    @POST("/v1/user/orders/sell")
    Call<Object> placeMarketSell(
            @Header("Authorization") String authorization,
            @Field("currency_pair") String currencyPair,
            @Field("type") String type,
            @Field("coin_amount") Double coinAmount,
            @Field("nonce") Long nonce);
}
