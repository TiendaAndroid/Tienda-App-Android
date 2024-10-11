package com.larc.appandroid.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larc.appandroid.R

@Composable
fun TodasBrillamos(modifier: Modifier = Modifier) {

    val dadata = listOf(
        "¿Quiénes somos?" to "Somos la Fundación Todas Brillamos AC, una organización sin fines de lucro y Donataria Autorizada comprometida con el cambio positivo en la sociedad mexicana. Nuestro enfoque se centra en la igualdad de género, el empoderamiento de las mujeres y la erradicación de la pobreza menstrual. Nuestra misión es crear unentorno donde todas las personas, sin importar su género, puedan vivir con dignidad y libertad. Trabajamos incansablemente para promover la igualdad, empoderar a las mujeres y asegurar que todas tengan acceso a productos de higiene menstrual. Únete a nosotros en esta importante causa y juntos hagamos brillar a todas las personas.",
        "Misión" to "Nuestra misión es impulsar un cambio positivo en la sociedad mexicana al abordar las desigualdades de género, empoderar a las mujeres y niñas, y promover la igualdad de oportunidades. Nos dedicamos a proporcionar educación menstrual, acceso a productos de higiene menstrual, y oportunidades de empoderamiento económico. Buscamos erradicar la pobreza menstrual y crear una sociedad donde todas las personas, sin importar su género, puedan vivir con dignidad y libertad. Nos esforzamos por inculcar una responsabilidad social y ambiental en las nuevas generaciones, resaltando la importancia de tomar decisiones informadas y sostenibles para un mundo mejor.",
        "Visión" to "Nuestra visión es un futuro en elque la igualdad de género es unarealidad, donde cada mujer yniña puede acceder a laeducación menstrual, recursoseconómicos, y oportunidadespara alcanzar su máximopotencial. Buscamos un mundodonde la pobreza menstrual seaun recuerdo del pasado y dondela sostenibilidad ambiental seauna parte intrínseca de nuestraforma de vida. Visualizamoscomunidades comprometidascon la responsabilidad social y elrespeto por nuestro planeta. Eneste futuro, todos descubren subrillo único y contribuyen albienestar de la humanidad."
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.White),
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                Text(
                    text = "Todas Brillamos AC",
                    textAlign = TextAlign.Center,
                    color = AppColors.RosaZazil,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Image(
                painter = painterResource(R.drawable.logotodasbrillamos),
                contentDescription = "Logo de Todas Brillamos",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = 30.dp)
            )
        }
        items(dadata.size) { index ->
            Row(modifier = modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                val (pregunta, respuesta) = dadata[index]
                ContactItem(pregunta, respuesta)
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
