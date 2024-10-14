package com.larc.appandroid.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larc.appandroid.R

@Composable
fun Calculos(modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFAF8FF)),
    ) {
        item {
            Text(
                text = "Tu ayuda cuenta",
                textAlign = TextAlign.Center,
                color = AppColors.RosaZazil,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "La vida útil de nuestros productos es de hasta 5 años.\n" +
                            "\nDe acuerdo con la Revista del Consumidor (2020), la masa promedio de 3 toallas " +
                            "es de 58.6 gramos, mientras que la Procuraduría Federal del Consumidor (2022) menciona que, " +
                            "en promedio, una persona menstruante utiliza 300 toallas sanitarias deshechables al año. " +
                            "Con estos datos, podemos observar que, al usar toallas deshechables, una persona menstruante genera " +
                            "aproximadamente 5,860 gramos de basura al año.\n" +
                            "\nSi se multiplica esto por los 5 años de vida útil de una de nuestras toallas, se obtiene un total de 29,300 gramos de basura. " +
                            "Por otra parte, la Procuraduría Federal del Consumidor (2022), también menciona que el costo por cada toalla es de entre $2 y $3. " +
                            "Si se multiplica la cantidad de $2.50 por las 300 toallas que una persona menstruante usa al año, por 5 años, " +
                            "se obtiene un costo total de $3,750.00.\n" +
                            "\nTambién es importante mencionar que, por cada toalla que compras, Zazil dona una más a personas menstruantes que las necesitan.\n" +
                            "\nPara tener un periodo cómodo, se recomienda tener mínimo 3 toallas Zazil reutilizables.\n" +
                            "\nGracias a tus compras, también podemos generar trabajo para las mujeres elaboran nuestros productos.\n" +
                            "Esperamos que esta información te muestre cómo tu ayuda es fundamental para que otras mujeres tengan una mestruación digna, o empleo, " +
                            "o también cómo al optar por nuestros productos ahorras y reduces tu impacto en el medio ambiente.",
                    textAlign = TextAlign.Start,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 32.dp, end = 32.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "Referencias:",
                    textAlign = TextAlign.Start,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 32.dp)
            ) {
                Text(
                    text = "Procuraduría Federal del Consumidor. (enero 6, 2022). Vigilará Profeco precios de productos de gestión menstrual." +
                            "Boletín de Prensa Profeco. Recuperado de: https://www.gob.mx/profeco/prensa/vigilara-profeco-precios-de-productos-de-gestion-menstrual?idiom=es \n" +
                            "\nRevista del consumidor. (marzo, 2020). Toallas sanitarias femeninas. Revista del consumidor, pp. 34-51. " +
                            "Recuperado de: https://www.gob.mx/cms/uploads/attachment/file/538780/ESTUDIO_CALIDAD_TOALLAS_SANITARIAS_FEMENINAS.pdf",
                    textAlign = TextAlign.Start,
                    color = AppColors.GrisOscuro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

}
