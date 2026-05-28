package com.example.registrodeocupaciones.presentation.HoraExtra.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.registrodeocupaciones.domain.horaExtra.model.HoraExtra
import com.example.registrodeocupaciones.presentation.empleado.list.EmpleadoListViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoraExtraListScreen(
    onDrawer: () -> Unit,
    goToHoraExtra: (Int) -> Unit,
    createHoraExtra: () -> Unit,
    viewModel: ListHoraExtraViewModel = hiltViewModel(),
    empleadoViewModel: EmpleadoListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val empleadoState by empleadoViewModel.state.collectAsStateWithLifecycle()
    val dateFormatter = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = createHoraExtra) {
                Icon(Icons.Default.Add, contentDescription = "Nueva Hora Extra")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.horasExtras.isEmpty()) {
                Text(
                    text = "No hay horas extras registradas",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.horasExtras) { horaExtra ->
                        val empleado = empleadoState.empleados.find { it.empleadoId == horaExtra.empleadoId }
                        val nombreEmpleado = empleado?.nombres ?: "Empleado #${horaExtra.empleadoId}"
                        val sueldoEmpleado = empleado?.sueldo ?: 0.0

                        HoraExtraItem(
                            horaExtra = horaExtra,
                            nombreEmpleado = nombreEmpleado,
                            sueldoEmpleado = sueldoEmpleado,
                            dateFormatter = dateFormatter,
                            onEdit = { goToHoraExtra(horaExtra.horaExtraId) },
                            onDelete = { viewModel.onEvent(ListHoraExtraUiEvent.Delete(horaExtra.horaExtraId)) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HoraExtraItem(
    horaExtra: HoraExtra,
    nombreEmpleado: String,
    sueldoEmpleado: Double,
    dateFormatter: SimpleDateFormat,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().clickable { onEdit() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "#${horaExtra.horaExtraId} - $nombreEmpleado",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Sueldo: RD$${sueldoEmpleado}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Periodo: ${dateFormatter.format(Date(horaExtra.fechaDesde))} - ${dateFormatter.format(Date(horaExtra.fechaHasta))}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Totales: ${horaExtra.horasTotales} | Nocturnas: ${horaExtra.horasNocturnas}",
                    style = MaterialTheme.typography.bodySmall
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                Text(
                    text = "Total a pagar: RD$${horaExtra.totalAPagar}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}