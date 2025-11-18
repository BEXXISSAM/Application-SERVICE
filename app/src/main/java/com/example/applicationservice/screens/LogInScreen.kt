import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.applicationservice.R

@Composable
fun LogInScreen(
        onLoginClicked: (username: String, password: String) -> Unit,
        onNavigateToSignup: () -> Unit
) {
// Gestion de l'état des champs de saisie
var username by remember { mutableStateOf("") }
var password by remember { mutableStateOf("") }

// Utilisation de Scaffold pour la structure de base
Scaffold { paddingValues ->
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(paddingValues)
                        .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
        ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "Logo de Service",
            modifier = Modifier
                .size(120.dp)
        )

        Spacer(Modifier.height(40.dp))

        Text(
                text = "WELCOME BACK",
                style = MaterialTheme.typography.headlineMedium
        )

        Text(
                text = "Log in to complete your journey",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("username") },
                modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(32.dp))

        Button(
                onClick = { onLoginClicked(username, password) },
                modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
        ) {
            Text("LOG IN")
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
                text = "you're new?",
                style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(4.dp))

        TextButton(onClick = onNavigateToSignup) {
            Text("CREATE AN ACCOUNT")
        }
    }
}
}
}

