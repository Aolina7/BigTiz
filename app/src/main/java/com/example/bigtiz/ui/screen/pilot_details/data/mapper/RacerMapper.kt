package com.example.bigtiz.data.mapper

import com.example.bigtiz.data.model.RacerEntity
import com.example.bigtiz.domain.model.Racer

object RacerMapper {
    fun toDomain(entity: RacerEntity): Racer {
        return Racer(
            id = entity.id,
            name = entity.name,
            fullName = entity.fullName,
            imageResId = entity.imageResId,
            bio = entity.bio,
            age = entity.age,
            country = entity.country,
            wins = entity.wins,
            quote = entity.quote
        )
    }

    fun toDomainList(entities: List<RacerEntity>): List<Racer> {
        return entities.map { toDomain(it) }
    }
}