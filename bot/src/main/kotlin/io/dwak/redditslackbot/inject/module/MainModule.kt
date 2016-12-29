package io.dwak.redditslackbot.inject.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.dwak.redditslackbot.inject.module.action.ActionModule
import io.dwak.redditslackbot.inject.module.config.ConfigModule
import io.dwak.redditslackbot.reddit.network.adapter.KindAdapter
import io.dwak.redditslackbot.slack.network.SlackWebhookUrlComponentAdapter
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Singleton
@Module(includes = arrayOf(
    ActionModule::class,
    ConfigModule::class,
    DbModule::class,
    FirebaseModule::class,
    NetworkModule::class))
class MainModule {
  @Provides
  @Singleton
  fun moshi(): Moshi = Moshi.Builder()
      .add(KindAdapter())
      .add(SlackWebhookUrlComponentAdapter())
      .build()

  @Provides
  @Singleton
  fun moshiFactory(moshi: Moshi): Converter.Factory
      = MoshiConverterFactory.create(moshi).asLenient()

}