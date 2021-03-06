package io.dwak.redditslackbot.inject.annotation.qualifier.reddit

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Reddit(val login: Boolean = false)

