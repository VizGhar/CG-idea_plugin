package xyz.kandrac.codingame.wizard

import xyz.kandrac.codingame.wizard.gametype.GAME_TYPE_MULTI

const val GENERATOR_LANGUAGE_JAVA = "Java"
const val GENERATOR_LANGUAGE_KOTLIN = "Kotlin"

const val GENERATOR_TYPE_MULTI = "Multi"
const val GENERATOR_TYPE_SOLO = "Solo"
const val GENERATOR_TYPE_OPTIM = "Optim"

// these values can be read in .ft (file template) files
// you can find those in resources/fileTemplates/j2ee folder
object GeneratorContext {

    var language: String = GENERATOR_LANGUAGE_JAVA
    var gameTitle: String = "Game Example"
    var gameType: String = GAME_TYPE_MULTI
    var numberOfPlayersMin: Int = 2
    var numberOfPlayersMax: Int = 2
    var endScreenModule: Boolean = true
    var toggleModule: Boolean = false

    fun reset() {
        language = GENERATOR_LANGUAGE_JAVA
        gameTitle = "Game Example"
        gameType = GENERATOR_TYPE_MULTI
        numberOfPlayersMin = 2
        numberOfPlayersMax = 2
        endScreenModule = true
        toggleModule = false
    }
}