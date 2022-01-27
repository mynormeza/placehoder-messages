package com.example.placeholdermessages.core

sealed class Failure() {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object CacheError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure() : Failure()
}
