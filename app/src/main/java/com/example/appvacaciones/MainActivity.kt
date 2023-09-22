package com.example.appvacaciones

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("nombre_tu_preferencia", Context.MODE_PRIVATE)

        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "appVacacionesScreen") {
                composable("appVacacionesScreen") {
                    AppVacacionesScreen(navController)
                }
                composable("vacacionesFormScreen") {
                    VacacionesFormScreen(navController, sharedPreferences)
                }
                composable("datosGuardadosScreen/{lugar}") { backStackEntry ->
                    val lugar = backStackEntry.arguments?.getString("lugar") ?: ""
                    DatosGuardadosScreen(lugar, sharedPreferences)
                }
            }
        }
    }
}

@Composable
fun AppVacacionesScreen(navController: NavHostController) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }

    // Un Box que ocupa todo el espacio disponible
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Una imagen en pantalla completa
        Image(
            painter = painterResource(id = R.drawable.playa_vacaciones),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Columna que organiza el contenido en el centro verticalmente
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título de la pantalla
            Text(
                text = "AppVacaciones",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para navegar a la pantalla de formulario
            Button(
                onClick = {
                    // Navegar a la pantalla de formulario
                    navController.navigate("vacacionesFormScreen")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ingresar a la aplicación")
            }
        }
    }
}


@Composable
fun VacacionesFormScreen(navController: NavHostController, sharedPreferences: SharedPreferences) {
    // Declaración de variables para los campos de entrada
    var lugar by remember { mutableStateOf("") }
    var imagenReferencia by remember { mutableStateOf("") }
    var latitud by remember { mutableStateOf("") }
    var longitud by remember { mutableStateOf("") }
    var orden by remember { mutableStateOf("") }
    var costoAlojamiento by remember { mutableStateOf("") }
    var costoTraslados by remember { mutableStateOf("") }
    var comentarios by remember { mutableStateOf("") }

    // Declaración de un scope para las operaciones asincrónicas
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ingresa tus vacaciones",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para Lugar
        TextField(
            value = lugar,
            onValueChange = { lugar = it },
            label = { Text(text = "Lugar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Campo de texto para Imagen de referencia
        TextField(
            value = imagenReferencia,
            onValueChange = { imagenReferencia = it },
            label = { Text(text = "Imagen de referencia") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Campos de texto para Latitud y Longitud, organizados en una fila
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = latitud,
                onValueChange = { latitud = it },
                label = { Text(text = "Latitud") },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextField(
                value = longitud,
                onValueChange = { longitud = it },
                label = { Text(text = "Longitud") },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        }

        // Campo de texto para Orden
        TextField(
            value = orden,
            onValueChange = { orden = it },
            label = { Text(text = "Orden") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Campo de texto para Costo Alojamiento
        TextField(
            value = costoAlojamiento,
            onValueChange = { costoAlojamiento = it },
            label = { Text(text = "Costo Alojamiento") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Campo de texto para Costos Traslados
        TextField(
            value = costoTraslados,
            onValueChange = { costoTraslados = it },
            label = { Text(text = "Costos Traslados") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Campo de texto para Comentarios
        TextField(
            value = comentarios,
            onValueChange = { comentarios = it },
            label = { Text(text = "Comentarios") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar los datos
        Button(
            onClick = {
                // Guardar los datos en SharedPreferences
                sharedPreferences.edit {
                    putString("Lugar", lugar)
                    putString("Imagen de Referencia", imagenReferencia)
                    putString("Latitud", latitud)
                    putString("Longitud", longitud)
                    putString("Orden", orden)
                    putString("Costo Alojamiento", costoAlojamiento)
                    putString("Costos Traslados", costoTraslados)
                    putString("Comentarios", comentarios)
                }

                // Navegar a la pantalla de datos guardados
                navController.navigate("datosGuardadosScreen/$lugar")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Guardar")
        }
    }
}


@Composable
fun DatosGuardadosScreen(lugar: String, sharedPreferences: SharedPreferences) {
    // Se recuperan los valores guardados en SharedPreferences utilizando las claves adecuadas
    val lugarGuardado = sharedPreferences.getString("Lugar_$lugar", "")
    val imagenReferenciaGuardada = sharedPreferences.getString("Imagen de Referencia_$lugar", "")
    val latitudGuardada = sharedPreferences.getString("Latitud_$lugar", "")
    val longitudGuardada = sharedPreferences.getString("Longitud_$lugar", "")
    val ordenGuardada = sharedPreferences.getString("Orden_$lugar", "")
    val costoAlojamientoGuardado = sharedPreferences.getString("Costo Alojamiento_$lugar", "")
    val costoTrasladosGuardado = sharedPreferences.getString("Costos Traslados_$lugar", "")
    val comentariosGuardados = sharedPreferences.getString("Comentarios_$lugar", "")

    // Se define la interfaz de usuario utilizando Compose
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Datos Guardados",
            style = MaterialTheme.typography.displaySmall,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Se muestran los datos guardados en Text
        Text(
            text = "Lugar: $lugarGuardado",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Imagen de Referencia: $imagenReferenciaGuardada",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Latitud: $latitudGuardada",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Longitud: $longitudGuardada",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Orden: $ordenGuardada",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Costo Alojamiento: $costoAlojamientoGuardado",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Costos Traslados: $costoTrasladosGuardado",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Comentarios: $comentariosGuardados",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(8.dp)
        )
    }
}






