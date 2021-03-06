package io.dwak.redditslackbot.reddit.network.service

import io.dwak.redditslackbot.reddit.model.RedditCommentResponse
import io.dwak.redditslackbot.reddit.model.RedditFlairResponse
import io.dwak.redditslackbot.reddit.model.RedditListing
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


interface RedditService {
  @GET("{subreddit}/about/unmoderated")
  fun unmoderated(@Header("Authorization") auth: String,
                  @Path("subreddit") subreddit: String): Single<RedditListing>

  @FormUrlEncoded
  @POST("/api/remove")
  fun removePost(@Header("Authorization") auth: String,
                 @Field("id") id: String,
                 @Field("spam") spam: Boolean): Completable

  @FormUrlEncoded
  @POST("/api/comment")
  fun postComment(@Header("Authorization") auth: String,
                  @Field("api_type") apiType: String = "json",
                  @Field("thing_id") thingId: String,
                  @Field("text") text: String): Single<RedditCommentResponse>

  @FormUrlEncoded
  @POST("/api/distinguish")
  fun distinguish(@Header("Authorization") auth: String,
                  @Field("api_type") apiType: String = "json",
                  @Field("id") id: String,
                  @Field("how") how: String = "yes"): Completable

  @FormUrlEncoded
  @POST("{subreddit}/api/flairselector")
  fun flairSelector(@Header("Authorization") authorization: String,
                    @Path("subreddit") subreddit: String,
                    @Field("link") fullname: String): Single<RedditFlairResponse>

  @FormUrlEncoded
  @POST("{subreddit}/api/selectflair")
  fun selectFlair(@Header("Authorization") authorization: String,
                  @Path("subreddit") subreddit: String,
                  @Field("api_type") apiType: String = "json",
                  @Field("flair_template_id") flairTemplateId: String,
                  @Field("link") fullname: String): Completable

  @GET("{subreddit}/wiki/slackbot")
  fun wikiSettings(@Path("subreddit") subreddit: String): Single<ResponseBody>
}