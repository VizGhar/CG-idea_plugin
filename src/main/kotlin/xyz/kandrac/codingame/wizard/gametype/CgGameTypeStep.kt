package xyz.kandrac.codingame.wizard.gametype

import com.intellij.ide.wizard.*
import com.intellij.ide.wizard.NewProjectWizardBaseData.Companion.baseData
import xyz.kandrac.codingame.wizard.CodingameWizardBundle

const val GAME_TYPE_SOLO = "Solo"
const val GAME_TYPE_MULTI = "Multi"
const val GAME_TYPE_OPTIM = "Optim"

class CgGameTypeStep(parent: NewProjectWizardStep) :
    AbstractNewProjectWizardMultiStepBase(parent),
    NewProjectWizardBaseData by parent.baseData {

    override val label = CodingameWizardBundle.getMessage("codingame.new.project.wizard.game.type.label")

    override fun initSteps(): Map<String, NewProjectWizardStep> {
        return mapOf(
            GAME_TYPE_MULTI to CgGameTypeMultiStep(this),
            GAME_TYPE_SOLO to CgGameTypeSoloStep(this),
            GAME_TYPE_OPTIM to CgGameTypeOptimStep(this),
        )
    }
}