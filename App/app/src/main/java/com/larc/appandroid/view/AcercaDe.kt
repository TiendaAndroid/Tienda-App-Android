package com.larc.appandroid.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larc.appandroid.R

@Composable
fun AcercaDe(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Acerca de",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD5507C),
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        item {
            Image(
                painter = painterResource(R.drawable.logozazil),
                contentDescription = "Logo de Todas Brillamos",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = 30.dp)
            )
        }

        items(expandableSections) { section ->
            ExpandableTextButton(
                title = section.title,
                content = section.content
            )
        }
    }
}

@Composable
fun ExpandableTextButton(title: String, content: String) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD5507C),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable { expanded = !expanded }
        )

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Text(
                text = content,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

val expandableSections = listOf(
    ExpandableSection("¿Quiénes somos?", "Somos la Fundación Todas Brillamos AC, una organización sin fines de lucro y Donataria Autorizada comprometida con el cambio positivo en la sociedad mexicana. Nuestro enfoque se centra en la igualdad de género, el empoderamiento de las mujeres y la erradicación de la pobreza menstrual. Nuestra misión es crear unentorno donde todas las personas, sin importar su género, puedan vivir con dignidad y libertad. Trabajamos incansablemente para promover la igualdad, empoderar a las mujeres y asegurar que todas tengan acceso a productos de higiene menstrual. Únete a nosotros en esta importante causa y juntos hagamos brillar a todas las personas\"."),
    ExpandableSection("Misión", "Nuestra misión es impulsar un cambio positivo en la sociedad mexicana al abordar las desigualdades de género, empoderar a las mujeres y niñas, y promover la igualdad de oportunidades. Nos dedicamos a proporcionar educación menstrual, acceso a productos de higiene menstrual, y oportunidades de empoderamiento económico. Buscamos erradicar la pobreza menstrual y crear una sociedad donde todas las personas, sin importar su género, puedan vivir con dignidad y libertad. Nos esforzamos por inculcar una responsabilidad social y ambiental en las nuevas generaciones, resaltando la importancia de tomar decisiones informadas y sostenibles para un mundo mejor."),
    ExpandableSection("Visión", "Nuestra visión es un futuro en elque la igualdad de género es unarealidad, donde cada mujer yniña puede acceder a laeducación menstrual, recursoseconómicos, y oportunidadespara alcanzar su máximopotencial. Buscamos un mundodonde la pobreza menstrual seaun recuerdo del pasado y dondela sostenibilidad ambiental seauna parte intrínseca de nuestraforma de vida. Visualizamoscomunidades comprometidascon la responsabilidad social y elrespeto por nuestro planeta. Eneste futuro, todos descubren subrillo único y contribuyen albienestar de la humanidad."),
)

data class ExpandableSection(val title: String, val content: String)


@Preview(showBackground = true)
@Composable
fun AcercaDePreview() {
    AcercaDe()
}

