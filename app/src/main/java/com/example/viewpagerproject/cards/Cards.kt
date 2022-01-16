package com.example.viewpagerproject.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.viewpagerproject.R
import com.example.viewpagerproject.pojo.Card


class TestCardPreviewParamProvider : PreviewParameterProvider<Card> {
    override val values: Sequence<Card>
        get() = sequenceOf(
            Card(
                testField1 = "asadasd",
                testField2 = "adsdasdasd",
            ),
            Card(
                testField1 = "233qwrqw",
                testField2 = "adsdadf23asdasd",
            )
        )
}

class TestCardListPreviewParamProvider : PreviewParameterProvider<List<Card>> {
    override val values: Sequence<List<Card>>
        get() = sequenceOf(
            listOf(
                Card(
                    testField1 = "asadasd2222",
                    testField2 = "adsdasdasd33333",
                ),
                Card(
                    testField1 = "233qwrqwasdasd",
                    testField2 = "adsdadf23asdasd",
                )
            )
        )
}

@Composable
@Preview
fun TestCard(
    @PreviewParameter(TestCardPreviewParamProvider::class) card: Card
) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Content, Description",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(2.dp))
        Column {
            Text(text = card.testField1)
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = card.testField2,
                style = MaterialTheme.typography.body2
            )
        }
    }
}


@Composable
@Preview
fun TestCardList(
    @PreviewParameter(TestCardListPreviewParamProvider::class) cards: List<Card>
) {
    LazyColumn {
        items(cards) { card->
            TestCard(card)
        }
    }
}
