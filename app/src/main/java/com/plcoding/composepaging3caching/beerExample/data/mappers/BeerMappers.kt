package com.plcoding.composepaging3caching.beerExample.data.mappers

import com.plcoding.composepaging3caching.beerExample.data.local.BeerEntity
import com.plcoding.composepaging3caching.beerExample.data.remote.BeerDto
import com.plcoding.composepaging3caching.beerExample.domain.Beer

fun BeerDto.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}