package com.example.registrodeocupaciones.presentation.HoraExtra.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.registrodeocupaciones.presentation.empleado.list.EmpleadoListViewModel

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
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Horas Extras (${state.horasExtras.size})") },
                navigationIcon = {
                    IconButton(onClick = onDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = createHoraExtra) {
                Icon(Icons.Default.Add, contentDescription = "Nueva Hora Extra")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Horas extras registradas (${state.horasExtras.size})",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else if (state.horasExtras.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No hay horas extras registradas", color = Color.Gray)
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.horasExtras) { horaExtra ->
                        val empleado = empleadoState.empleados
                            .find { it.empleadoId == horaExtra.empleadoId }
                        val nombreEmpleado = empleado?.nombres ?: "Empleado #${horaExtra.empleadoId}"
                        val sueldoEmpleado = empleado?.sueldo ?: 0.0

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clickable { goToHoraExtra(horaExtra.horaExtraId) }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        "#${horaExtra.horaExtraId} - $nombreEmpleado",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        "Sueldo mensual: $${sueldoEmpleado}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Black
                                    )
                                    Text(
                                        "Desde: ${dateFormatter.format(Date(horaExtra.fechaDesde))} " +
                                                "| Hasta: ${dateFormatter.format(Date(horaExtra.fechaHasta))}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        "Horas totales: ${horaExtra.horasTotales} " +
                                                "| Nocturnas: ${horaExtra.horasNocturnas}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        "Al 35%: ${horaExtra.horasAl35} hrs " +
                                                "| Al 100%: ${horaExtra.horasAl100} hrs",
                                        style = MaterialTheme.typography.bodySmall
                                    )

                                    Spacer(Modifier.height(4.dp))
                                    HorizontalDivider()
                                    Spacer(Modifier.height(4.dp))

                                    Text(
                                        "Monto Por Horas Extras: $${horaExtra.totalAPagar}",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(Modifier.height(2.dp))
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    IconButton(onClick = { goToHoraExtra(horaExtra.horaExtraId) }) {
                                        Icon(
                                            Icons.Default.Edit,
                                            contentDescription = "Editar",
                                            tint = Color.Black
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}