package com.larc.appandroid.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AvisoPrivacidad(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF8FF)),
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Aviso de privacidad",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = AppColors.RosaZazil,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    append("")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("En cumplimiento a la Ley Federal de Protección de Datos Personales en Posesión de Particulares (la \"Ley\"),")
                    }
                    append(" cuyo objeto es la protección de la información personal proporcionada a personas físicas o morales de carácter privado, ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Zazil")
                    }
                    append(", una empresa legalmente constituida bajo las leyes de la República Mexicana, comunica lo siguiente:")
                },
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    append("")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("I. Recolección de Datos:")
                    }
                    append(" Para brindar un mejor servicio a nuestros clientes, requerimos cierta información que puede incluir datos personales (información concerniente a una persona física identificada o identificable) y/o datos personales sensibles (datos que afecten a la esfera más íntima del titular o puedan dar origen a discriminación).")
                },
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    append("")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("II. Principios de Tratamiento de Datos:")
                    }
                    append(" Los datos recabados serán tratados bajo los principios de licitud, consentimiento, información, calidad, finalidad, lealtad, proporcionalidad y responsabilidad, conforme a la Ley.")
                },
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    append("")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("III. Uso de los Datos:")
                    }
                    append(" Los datos personales serán utilizados para fines relacionados con la venta y promoción de nuestros productos, evaluación crediticia para relaciones comerciales, y otros fines permitidos por la Ley que no requieran consentimiento expreso del titular.")
                },
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    append("")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("IV. Seguridad de los Datos:")
                    }
                    append(" Implementamos medidas de seguridad para garantizar la confidencialidad de los datos personales, protegiéndolos tanto física como electrónicamente.")
                },
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    append("")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("V. Transferencia de Datos:")
                    }
                    append(" Podemos transferir los datos personales a nuestras empresas controladoras, filiales, subsidiarias o a terceros, siempre con el propósito de cumplir con los fines establecidos en este aviso.")
                },
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    append("")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("VI. Derechos del Titular:")
                    }
                    append(" Los titulares pueden revocar su consentimiento, limitar el uso o divulgación de sus datos, y ejercer sus derechos de acceso, rectificación, cancelación y oposición mediante una solicitud escrita que cumpla con los requisitos del artículo 29° de la Ley.")
                },
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    append("")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("VII. Respuesta a Solicitudes:")
                    }
                    append(" Responderemos a las solicitudes de los titulares en un plazo máximo de veinte días hábiles, para implementar la determinación adoptada dentro de los quince días hábiles siguientes.")
                },
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    append("")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("VIII. Modificaciones al Aviso:")
                    }
                    append(" Nos reservamos el derecho de modificar este aviso en cualquier momento, notificando a los titulares a través de nuestra página web y otros medios establecidos por la Ley.")
                },
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    append("")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("IX. Consentimiento para Datos Sensibles:")
                    }
                    append(" En caso de recabar datos personales sensibles, requerimos el consentimiento expreso del titular, manifestado al final de este aviso.")
                },
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    append("")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("X. Validez del Aviso:")
                    }
                    append(" Este aviso es válido para todos los datos personales entregados a Zazil durante la relación comercial entre el titular y nuestra empresa.")
                },
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
