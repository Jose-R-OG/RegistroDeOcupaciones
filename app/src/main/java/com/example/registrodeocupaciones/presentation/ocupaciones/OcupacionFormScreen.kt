package com.example.registrodeocupaciones.presentation.ocupaciones

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OcupacionFormScreen(
    viewModel: OcupacionViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Registro de Ocupaciones") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.descripcion,
                onValueChange = { viewModel.onEvent(OcupacionUiEvent.DescripcionChanged(it)) },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.descripcionError != null,
                supportingText = { state.descripcionError?.let { Text(it) } }
            )

            OutlinedTextField(
                value = state.sueldo,
                onValueChange = { viewModel.onEvent(OcupacionUiEvent.SueldoChanged(it)) },
                label = { Text("Sueldo") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.sueldoError != null,
                supportingText = { state.sueldoError?.let { Text(it) } }
            )

            Button(
                onClick = { viewModel.onEvent(OcupacionUiEvent.Save) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isSaving
            ) {
                if (state.isSaving) CircularProgressIndicator(modifier = Modifier.size(24.dp))
                else Text("Guardar")
            }

            // Aquí se muestra el error de "Descripción Duplicada"
            state.errorGeneral?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}