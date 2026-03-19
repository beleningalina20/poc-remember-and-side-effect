package com.beleningalina.jetpackcomposepoc.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beleningalina.jetpackcomposepoc.ui.screens.CounterMutableStateScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.CounterRememberScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.CounterVariableScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.MainScreen

fun NavGraphBuilder.addFeedScreenGraph(navController: NavController) {
    composable(route = AppScreen.Main.route) {
        MainScreen(
            navigateTo = { route ->
                navController.navigate(route)
            }
        )
    }

    composable(route = AppScreen.CounterVariable.route) {
        CounterVariableScreen()
    }

    composable(route = AppScreen.CounterMutableState.route) {
        CounterMutableStateScreen()
    }

    composable(route = AppScreen.CounterRemember.route) {
        CounterRememberScreen()
    }
    /*composable(route = AppScreen.MarvelCharacterList.route) {
        MarvelCharacterListScreen(
            onItemClicked = { marvelCharacter -> navController.navigate(AppScreen.MarvelCharacterDetail.createRoute(marvelCharacter.id)) },
            onError = { navController.popBackStack() }
        )
    }*/
    // Ejemplo para ver como pasar algo por param que no sea string..
    // si es string no hay que definir el arguments
    /*composable(
        route = AppScreen.MarvelCharacterDetail.route,
        arguments = listOf(
            navArgument(MARVEL_CHARACTER_ID_PARAM) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val marvelCharacterId = backStackEntry.arguments?.getInt(MARVEL_CHARACTER_ID_PARAM)
        requireNotNull(marvelCharacterId)
        MarvelCharacterDetailScreen(
            marvelCharacterId = marvelCharacterId,
            onImageClicked = { marvelCharacter -> navController.navigate(AppScreen.MarvelCharacterDialog.createRoute(marvelCharacter)) },
            onError = { navController.popBackStack() }
        )
    }*/
    // Esto lo hice solamente como ejemplo para ver como pasar un objecto por param
    /*composable(
        route = AppScreen.MarvelCharacterDialog.route,
        arguments = listOf(
            navArgument(MARVEL_CHARACTER_PARAM) { type = MarvelCharacterArgType() }
        )
    ) { backStackEntry ->
        val navMarvelCharacter =
            backStackEntry.arguments?.getString(MARVEL_CHARACTER_PARAM)?.let { Gson().fromJson(it, NavMarvelCharacter::class.java) }
        requireNotNull(navMarvelCharacter)
        MarvelCharacterDialogScreen(
            navMarvelCharacter.toMarvelCharacter(),
            onDismiss = { navController.popBackStack() }
        )
    }*/
}

// Esto esta bueno para ver como mandar a otro NavGraph
// fun NavGraphBuilder.addMarvelCharacterDetailScreenGraph(navController: NavController) {
//    navigation(
//        route = AppScreen.MarvelCharacterDetail.route,
//        startDestination = MarvelCharacterScreen.Adopt.route
//    ) {
//        composable(route = MarvelCharacterScreen.Adopt.route) {
//            val characterName = it.arguments?.getString("name").orEmpty()
//            MarvelCharacterDetailScreen(
//                characterName,
//                navigateUp = { navController.popBackStack(MarvelCharacterScreen.Adopt.createRoute(characterName), inclusive = true) }
//            )
//        }
//        composable(route = MarvelCharacterScreen.ContactDetails.route) { backStackEntry ->
//            val characterName = backStackEntry.arguments?.getString("name").orEmpty()
//            AdoptionContactDetailsScreen(characterName)
//        }
//    }
// }