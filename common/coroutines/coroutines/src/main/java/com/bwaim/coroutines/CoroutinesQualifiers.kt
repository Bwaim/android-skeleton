package com.bwaim.coroutines

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
public annotation class ComputationDispatcher

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
public annotation class IODispatcher

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
public annotation class MainDispatcher

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
public annotation class ApplicationScope

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
public annotation class ViewModelScope
