package io.dwak.redditslackbot.http

import com.spotify.apollo.AppInit
import com.spotify.apollo.Environment
import com.spotify.apollo.route.Route.async
import io.dwak.redditslackbot.database.DbHelper
import io.dwak.redditslackbot.http.action.CheckPosts
import io.dwak.redditslackbot.http.action.FinalizeReddit
import io.dwak.redditslackbot.http.action.OnButton
import io.dwak.redditslackbot.http.action.RedditLogin
import io.dwak.redditslackbot.http.action.SlackLogin
import io.dwak.redditslackbot.reddit.RedditBot
import javax.inject.Inject

class Bot @Inject constructor(private val actions: Map<String, @JvmSuppressWildcards RequestAction>,
                              private val redditBot: RedditBot,
                              private val dbHelper: DbHelper) : AppInit {

  override fun create(env: Environment) {
    with(env.routingEngine()) {
      registerAutoRoute(async("GET", "/init", actions[SlackLogin.name]?.action))
      registerAutoRoute(async("GET", "/init-reddit", actions[RedditLogin.name]?.action))
      registerAutoRoute(async("POST", "/finalize-reddit", actions[FinalizeReddit.name]?.action))
      registerAutoRoute(async("POST", "/check-posts", actions[CheckPosts.name]?.action))
      registerAutoRoute(async("POST", "/button", actions[OnButton.name]?.action))
    }
  }
}