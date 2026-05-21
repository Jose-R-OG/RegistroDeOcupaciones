package com.example.registrodeocupaciones.data.empleado.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.registrodeocupaciones.data.empleado.local.EmpleadoEntity
import com.example.registrodeocupaciones.domain.empleado.model.Empleado

fun EmpleadoEntity.toDomain() : Empleado = Empleado(
    empleadoId = empleadoId,
    fechaIngreso = fechaIngreso,
    nombres = nombres,
    sexo = sexo,
    sueldo = sueldo,
)

@RequiresApi(Build.VERSION_CODES.O)
fun Empleado.toEntity(): EmpleadoEntity = EmpleadoEntity(
    empleadoId = empleadoId,
    fechaIngreso = fechaIngreso,
    nombres = nombres,
    sexo = sexo,
    sueldo = sueldo,
)