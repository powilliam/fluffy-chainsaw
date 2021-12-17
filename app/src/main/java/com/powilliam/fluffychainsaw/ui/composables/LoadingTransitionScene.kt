package com.powilliam.fluffychainsaw.ui.composables

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun LoadingTransitionScene(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    transitionSpec: AnimatedContentScope<Boolean>.() -> ContentTransform = { defaultTransitionSpec() },
    loadingContent: @Composable () -> Unit = { DefaultLoadingComposable() },
    content: @Composable () -> Unit
) {
    AnimatedContent(
        modifier = modifier.fillMaxSize(),
        targetState = isLoading,
        transitionSpec = transitionSpec
    ) { isLoadingAsTargetState ->
        if (isLoadingAsTargetState) {
            loadingContent()
        } else {
            content()
        }
    }
}

@Composable
private fun DefaultLoadingComposable(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier
                .size(24.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 2.dp
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun AnimatedContentScope<Boolean>.defaultTransitionSpec(): ContentTransform =
    fadeIn() + scaleIn() with fadeOut() + scaleOut()