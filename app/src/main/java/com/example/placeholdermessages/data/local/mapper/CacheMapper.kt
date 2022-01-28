package com.example.placeholdermessages.data.local.mapper

interface CacheMapper<D, E> {
    fun mapToCache(type: D): E
}
