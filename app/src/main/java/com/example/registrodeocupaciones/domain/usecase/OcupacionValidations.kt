package com.example.registrodeocupaciones.domain.usecase

data class ValidationResult(
    val isValid: Boolean = true,
    val error: String? = null
)

fun validateDescripcion(descripcion: String): ValidationResult {
    return if (descripcion.isBlank()) {
        ValidationResult(false, "La descripción es obligatoria")
    } else {
        ValidationResult(true)
    }
}

fun validateSueldo(sueldo: String): ValidationResult {
    val s = sueldo.toDoubleOrNull()
    return if (s == null || s <= 0) {
        ValidationResult(false, "El sueldo debe ser un número mayor a 0")
    } else {
        ValidationResult(true)
    }
}