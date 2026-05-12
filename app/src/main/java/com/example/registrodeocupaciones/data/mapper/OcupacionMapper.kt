package com.example.registrodeocupaciones.data.mapper

import com.example.registrodeocupaciones.data.local.OcupacionEntity
import com.example.registrodeocupaciones.domain.model.Ocupacion

fun OcupacionEntity.toDomain(): Ocupacion = Ocupacion(
    ocupacionId = ocupacionId,
    descripcion = descripcion,
    sueldo = sueldo
)


fun Ocupacion.toEntity(): OcupacionEntity {
    return OcupacionEntity(
        ocupacionId = this.ocupacionId,
        descripcion = this.descripcion,
        sueldo = this.sueldo
    )
}
