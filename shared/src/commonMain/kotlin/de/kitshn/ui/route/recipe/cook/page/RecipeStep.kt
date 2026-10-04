package de.kitshn.ui.route.recipe.cook.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.AssistChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.kitshn.KitshnViewModel
import de.kitshn.api.tandoor.model.TandoorStep
import de.kitshn.api.tandoor.model.recipe.TandoorRecipe
import de.kitshn.api.tandoor.rememberTandoorRequestState
import de.kitshn.formatDuration
import de.kitshn.isLaunchTimerHandlerImplemented
import de.kitshn.launchTimerHandler
import de.kitshn.ui.TandoorRequestErrorHandler
import de.kitshn.ui.component.MarkdownRichTextWithTimerDetection
import de.kitshn.ui.component.model.ingredient.IngredientsList
import de.kitshn.ui.component.model.recipe.step.RecipeStepMultimediaBox
import de.kitshn.ui.component.model.recipe.step.RecipeStepRecipeLink
import de.kitshn.ui.dialog.LaunchTimerInfoBottomSheet
import de.kitshn.ui.dialog.LaunchTimerRangeBottomSheet
import de.kitshn.ui.dialog.recipe.RecipeLinkDialog
import de.kitshn.ui.dialog.recipe.rememberRecipeLinkDialogState
import de.kitshn.ui.dialog.rememberLaunchTimerInfoBottomSheetState
import de.kitshn.ui.dialog.rememberLaunchTimerRangeBottomSheetState
import de.kitshn.ui.layout.ResponsiveSideBySideLayout
import de.kitshn.ui.theme.Typography
import de.kitshn.ui.view.ViewParameters
import kitshn.shared.generated.resources.Res
import kitshn.shared.generated.resources.common_minute_min
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import kotlin.math.roundToInt

@Composable
fun RouteRecipeCookPageStep(
    topPadding: Dp,
    vm: KitshnViewModel,
    recipe: TandoorRecipe,
    step: TandoorStep,
    servingsFactor: Double,
    showFractionalValues: Boolean,
    // festion fork: true when the ingredients are on cook mode's own
    // Ingredients page instead (they are not split across steps).
    hideIngredients: Boolean = false
) {
    val coroutineScope = rememberCoroutineScope()
    val fetchRequestState = rememberTandoorRequestState()

    val launchTimerInfoBottomSheetState = rememberLaunchTimerInfoBottomSheetState()
    val launchTimerRangeBottomSheetState = rememberLaunchTimerRangeBottomSheetState()
    val launchTimerHandler = launchTimerHandler(
        vm = vm,
        infoBottomSheetState = launchTimerInfoBottomSheetState,
        rangeBottomSheetState = launchTimerRangeBottomSheetState
    )

    @Composable
    fun StepBody(
        maxHeightPx: Int,
        sideBySideLayout: Boolean
    ) {
        BoxWithConstraints(
            Modifier.fillMaxSize()
        ) {
            val density = LocalDensity.current

            val textMeasurer = rememberTextMeasurer()
            var fontSize by remember { mutableStateOf(14.sp) }

            val maxWidthPx = with(density) { maxWidth.roundToPx() }
            LaunchedEffect(step.instruction, sideBySideLayout) {
                var newFontSize = 18

                while(newFontSize < 44) {
                    val textLayout = textMeasurer.measure(
                        text = step.instruction,
                        style = TextStyle(
                            fontSize = newFontSize.sp,
                            lineHeight = newFontSize.sp
                        ),
                        constraints = Constraints(
                            maxWidth = maxWidthPx
                        )
                    )

                    if(textLayout.size.height > maxHeightPx) break
                    newFontSize += 2
                }

                fontSize = newFontSize.sp
            }

            Column(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .fillMaxSize()
            ) {
                if(step.name.isNotBlank()) {
                    Text(
                        text = step.name,
                        style = Typography().displayMedium
                    )

                    Spacer(Modifier.height(4.dp))
                    HorizontalDivider()
                    Spacer(Modifier.height(24.dp))
                }

                MarkdownRichTextWithTimerDetection(
                    modifier = Modifier
                        .fillMaxSize(),
                    timerName = step.name,
                    markdown = step.instructionsWithTemplating(
                        servingsFactor,
                        showFractionalValues
                    ),
                    fontSize = fontSize,
                    onStartTimer = { fromSeconds, toSeconds, timerName ->
                        launchTimerHandler(fromSeconds, toSeconds, timerName)
                    }
                )
            }
        }
    }

    val verticalScroll = rememberScrollState()
    val recipeLinkDialogState = rememberRecipeLinkDialogState()

    BoxWithConstraints(
        Modifier
            .fillMaxSize()
    ) {
        val density = LocalDensity.current

        val maxHeightPx = with(density) { maxWidth.roundToPx() }
        val maxHeight = maxHeight

        val statusBarHeight = with(density) {
            WindowInsets.statusBars
                .getTop(density)
                .toDp()
        }

        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(verticalScroll)
        ) {
            RecipeStepMultimediaBox(
                recipe = recipe,
                step = step
            ) {
                Spacer(Modifier.height(topPadding))
            }

            if(step.time > 0) AssistChip(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                onClick = {
                    if(isLaunchTimerHandlerImplemented)
                        launchTimerHandler(step.time * 60, step.time * 60, step.name)
                },
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Timer,
                        stringResource(Res.string.common_minute_min)
                    )
                },
                label = { Text(step.time.formatDuration()) }
            )

            if(step.ingredients.isEmpty() || hideIngredients) {
                if(step.step_recipe != null) RecipeStepRecipeLink(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    step = step
                ) {
                    recipeLinkDialogState.open(it.toOverview())
                }

                StepBody(maxHeightPx, false)
            } else {
                var disableSideBySideLayout by remember { mutableStateOf(false) }

                ResponsiveSideBySideLayout(
                    rightMinWidth = 300.dp,
                    rightMaxWidth = 300.dp,
                    leftMinWidth = 300.dp,
                    disable = maxHeight > 800.dp || disableSideBySideLayout,
                    leftLayout = { sideBySide ->
                        // festion fork: when stacked (phones), the instructions
                        // render AFTER the ingredients, in the right slot below.
                        if(sideBySide) StepBody(
                            (maxHeightPx / 2.5f).roundToInt(),
                            true
                        )
                    }
                ) { sideBySide ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        ) {
                            RecipeStepRecipeLink(
                                modifier = Modifier.fillMaxWidth(),
                                step = step
                            ) {
                                recipeLinkDialogState.open(it.toOverview())
                            }
                        }

                        Box(
                            Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        ) {
                            IngredientsList(
                                list = step.ingredients,
                                factor = servingsFactor,
                                colors = ListItemDefaults.colors(
                                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                                ),
                                onNotEnoughSpace = {
                                    disableSideBySideLayout = true
                                },
                                onOpenRecipe = { foodRecipe ->
                                    coroutineScope.launch {
                                        fetchRequestState.wrapRequest {
                                            // fetch full recipe using id and show recipe link dialog

                                            // festion fork: upstream retrieved `recipe.id`, i.e. the
                                            // recipe being cooked, so a linked-recipe ingredient
                                            // reopened the current recipe. Use the tapped one.
                                            val linked = vm.tandoorClient!!.recipe.retrieve(foodRecipe.id)
                                            recipeLinkDialogState.open(linked.toOverview())
                                        }
                                    }
                                },
                                enableTickingOff = true,
                                showFractionalValues = showFractionalValues
                            )
                        }
                    }

                    if(!sideBySide) StepBody(maxHeightPx, false)
                }
            }
        }

        Box(
            Modifier
                .fillMaxWidth()
                .alpha((verticalScroll.value / 200f).coerceIn(0f, 1f))
                .height(statusBarHeight)
                .background(MaterialTheme.colorScheme.background)
        )
    }

    RecipeLinkDialog(
        p = ViewParameters(
            vm, null
        ),
        state = recipeLinkDialogState
    )

    LaunchTimerInfoBottomSheet(
        vm = vm,
        state = launchTimerInfoBottomSheetState
    )

    LaunchTimerRangeBottomSheet(
        state = launchTimerRangeBottomSheetState
    )

    TandoorRequestErrorHandler(fetchRequestState)
}