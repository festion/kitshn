package de.kitshn.ui.route.recipe.cook.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.kitshn.KitshnViewModel
import de.kitshn.api.tandoor.model.TandoorIngredient
import de.kitshn.api.tandoor.rememberTandoorRequestState
import de.kitshn.ui.TandoorRequestErrorHandler
import de.kitshn.ui.component.model.ingredient.IngredientsList
import de.kitshn.ui.dialog.recipe.RecipeLinkDialog
import de.kitshn.ui.dialog.recipe.rememberRecipeLinkDialogState
import de.kitshn.ui.theme.Typography
import de.kitshn.ui.view.ViewParameters
import kitshn.shared.generated.resources.Res
import kitshn.shared.generated.resources.common_ingredients
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

/**
 * festion fork: cook-mode page listing every ingredient before step 1.
 *
 * Shown only when the recipe's ingredients are NOT split across steps (at most
 * one step has ingredients, typical for imported recipes). The step pages then
 * hide their ingredient list, so the full list is not filed under "step 1".
 */
@Composable
fun RouteRecipeCookPageIngredients(
    topPadding: Dp,
    vm: KitshnViewModel,
    ingredients: List<TandoorIngredient>,
    servingsFactor: Double,
    showFractionalValues: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val fetchRequestState = rememberTandoorRequestState()
    val recipeLinkDialogState = rememberRecipeLinkDialogState()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = topPadding + 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = stringResource(Res.string.common_ingredients),
            style = Typography().displayMedium
        )

        Spacer(Modifier.height(4.dp))
        HorizontalDivider()
        Spacer(Modifier.height(24.dp))

        IngredientsList(
            list = ingredients,
            factor = servingsFactor,
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow
            ),
            enableTickingOff = true,
            showFractionalValues = showFractionalValues,
            onNotEnoughSpace = { },
            onOpenRecipe = { foodRecipe ->
                coroutineScope.launch {
                    fetchRequestState.wrapRequest {
                        // fetch the linked recipe and show the recipe link dialog
                        val linked = vm.tandoorClient!!.recipe.retrieve(foodRecipe.id)
                        recipeLinkDialogState.open(linked.toOverview())
                    }
                }
            }
        )
    }

    RecipeLinkDialog(
        p = ViewParameters(
            vm, null
        ),
        state = recipeLinkDialogState
    )

    TandoorRequestErrorHandler(fetchRequestState)
}
