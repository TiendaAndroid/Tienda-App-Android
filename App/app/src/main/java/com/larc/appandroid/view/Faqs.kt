package com.larc.appandroid.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Representa la vista de las preguntas frecuentes.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
@Composable
fun Faqs(modifier: Modifier = Modifier) {
    // Lista de preguntas frecuentes con respuestas
    val faqs = listOf(
        "¿Qué es menstruación?" to "La menstruación es un fenómeno que se produce de forma cíclica, cada vez que una mujer ovula, pero no se queda embarazada.",
        "¿Qué es la ovulación?" to "Es el nombre del proceso en el que los cambios hormonales hacen que un ovario libere un óvulo y se produce normalmente una vez en cada ciclo menstrual. Solo puede quedarse embarazada si un espermatozoide fecunda un óvulo. La ovulación se produce, generalmente, de 12 a 16 días antes del inicio del siguiente periodo.",
        "¿Cuándo es más fértil una mujer?" to "Los días de mayor fertilidad de cada ciclo y, por tanto, en los que tiene mayor probabilidad de quedarse embarazada tras tener relaciones sexuales sin protección, son el día de la ovulación y el día previo.",
        "¿Cuántos días tiene un ciclo menstrual normal?" to "En la mayoría de los casos, el ciclo menstrual tiene entre 28 y 35 días. Sin embargo, los ciclos menstruales que tienen entre 24 y 38 días de diferencia todavía se consideran normales.",
        "¿Cuántos días debe durar la menstruación?" to "En la mayoría de los casos, el período menstrual dura de 4 a 6 días. Sin embargo, hasta 8 días de pérdidas menstruales se consideran normales.",
        "¿Cómo se llama la primera menstruación?" to  "El primer periodo menstrual se llama menarca.",
        "¿Cuáles son las principales causas de la menstruación retrasada?" to "La principal causa de retraso menstrual es el embarazo. Sin embargo, infecciones, uso de medicamentos, estrés, cambios significativos de peso corporal, cambios hormonales, también pueden ser causa de un retraso.",
        "¿Cuál es la edad normal para el primer período?" to "Se considera normal cuando la menarquia se presenta entre los 9 y los 15 años.",
        "¿Cuál es la edad normal para la menopausia?" to "La mayoría de las mujeres presenta la menopausia entre los 45 y 55 años de edad, con un promedio de 51 años. Cuando la menopausia llega antes de 40 años, ella se llama menopausia temprana, porque los ovarios han fallado antes de lo habitual.",
        "¿Cuál es el color normal de la menstruación?" to "Puede comenzar con un color más oscuro y poco flujo. A medida que aumenta el flujo menstrual, se volverá más rojizo y también puede ser rosado o vino tinto. Al final, con la reducción del flujo, la menstruación vuelve a ponerse un poco marrón, ya que la sangre que tarda en ser expulsada se oscurece con el paso de los días.",
        "¿Es posible quedarse embarazada mientras se está menstruando?" to "Sí, pero es inusual. Los espermatozoides de buena calidad pueden permanecer viables en el tracto ginecológico de la mujer por hasta 7 días. Si la mujer tiene un corto ciclo y ha tenido relaciones sexuales al final del período, ella puede ovular dentro de estos 7 días y así correr el riesgo de quedarse embarazada.",
        "¿Cuál es la cantidad de sangre que se pierde normalmente en la menstruación?" to "En promedio, las mujeres pierden aproximadamente de 30 a 50 ml de sangre en cada ciclo menstrual. El límite que se considera normal es de aproximadamente 80 ml por ciclo.",
        "¿Cuáles son los factores a considerar para decir que un sangrado menstrual está siendo anormal?" to "1. Duración mayor de 8 días.\n" +
                "2. Necesidad de cambiar el absorbente más de 6 veces al día.\n" +
                "3. Ciclos menstruales que se producen con intervalos de menos de 24 días.\n" +
                "4. Impresión de que el flujo menstrual es mucho más grande de lo habitual, inclusive si no cabe en las 3 condiciones anteriores.\n",
        "¿Qué días somos más fértiles dentro de nuestro ciclo menstrual? " to "Los proveedores de atención médica recomiendan que las parejas que estén tratando de tener un bebé tengan relaciones sexuales entre los días 7 y 20 del ciclo menstrual de la mujer. El día 1 es el primer día de sangrado.",
        "¿Cuántas toallas desechables ocupa una mujer en su ciclo menstrual?" to "Tomando en cuenta la recomendación de usar una toalla cada cuatro horas durante el periodo, por 5 días regulares, tenemos que, al mes, utilizará 30 toallas. El cálculo para un año es de 360. Si esta mujer llega a la menopausia a los 50 años y comenzó su ciclo a los 13, significa que utilizará 13 mil 320 toallas femeninas durante su vida.",
        "¿Cuánto tarda en degradarse una toalla femenina desechable?" to "Tardan entre 500 y 800 años en degradarse y que, una vez que han cumplido su labor, terminan en vertederos, cursos de agua o sencillamente en nuestros océanos.",
        "¿Cuánto tiempo dura la menstruación en la vida de una mujer?" to "Para una mujer, la regla será una compañera de vida, mes tras mes, durante unos 40 años",
        "¿Qué daños causa usar toallas femeninas desechables?" to "Las investigaciones han vinculado esta práctica con un incremento del riesgo de infecciones bacterianas y por levaduras, enfermedad inflamatoria de la pelvis, cáncer cervical, incremento de la transmisión de ETS y otras consecuencias adversas para la salud.",
        "Toxinas encontradas en tampones y toallas femeninas desechables:" to "•\tAluminio\n" +
                "•\tAlcohol\n" +
                "•\tFragancias\n" +
                "•\tHidrocarbonos\n" +
                "•\tDioxina (componente químico relacionado al proceso de blanqueamiento)\n" +
                "•\tPlástico\n" +
                "•\tCloruro de metileno\n" +
                "•\tXileno\n" +
                "•\tFtalatos\n" +
                "•\tTolueno\n",
        "¿Cuánto dinero gasta una mujer en toallas femeninas desechables?" to "\$26,000 pesos sería el gasto promedio en toallas sanitarias a lo largo de la vida si se considera que el precio es de \$2 a \$3 por cada una; o \$30,000 en tampones si se considera que el precio promedio es de \$4 por cada uno."
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
                    text = "Preguntas frecuentes",
                    textAlign = TextAlign.Center,
                    color = AppColors.RosaZazil,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(faqs.size) { index ->
            Row(modifier = modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                val (pregunta, respuesta) = faqs[index]
                FaqItem(pregunta, respuesta)
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun FaqItem(pregunta: String, respuesta: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded },
        border = BorderStroke(2.dp, Color.LightGray),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.White,
            contentColor = AppColors.GrisOscuro
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = pregunta,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = AppColors.GrisOscuro
            )

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Text(
                    text = respuesta,
                    fontSize = 16.sp,
                    color = AppColors.GrisOscuro,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FaqsPreview() {
    Faqs()
}
