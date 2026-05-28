package com.example.registrodeocupaciones.data.horasExtras.mapper

import com.example.registrodeocupaciones.data.horasExtras.local.HoraExtraEntity
import com.example.registrodeocupaciones.domain.horaExtra.model.HoraExtra

fun HoraExtraEntity.toDomain(): HoraExtra =
    HoraExtra(
        horaExtraId = horaExtraId,
        empleadoId = empleadoId,
        fechaDesde = fechaDesde,
        fechaHasta = fechaHasta,
        horasTotales = horasTotales,
        horasNocturnas = horasNocturnas,
        horasNormales = horasNormales,
        horasAl35 = horasAl35,
        horasAl100 = horasAl100,
        monto35 = monto35,
        monto100 = monto100,
        montoNocturno = montoNocturno,
        totalAPagar = totalAPagar
    )

fun HoraExtra.toEntity(): HoraExtraEntity =
    HoraExtraEntity(
        horaExtraId = horaExtraId,
        empleadoId = empleadoId,
        fechaDesde = fechaDesde,
        fechaHasta = fechaHasta,
        horasTotales = horasTotales,
        horasNocturnas = horasNocturnas,
        horasNormales = horasNormales,
        horasAl35 = horasAl35,
        horasAl100 = horasAl100,
        monto35 = monto35,
        monto100 = monto100,
        montoNocturno = montoNocturno,
        totalAPagar = totalAPagar
    )