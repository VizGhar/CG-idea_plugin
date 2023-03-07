package xyz.kandrac.codingame.wizard.gametype

import com.intellij.ide.wizard.AbstractNewProjectWizardStep
import com.intellij.ide.wizard.NewProjectWizardStep
import com.intellij.openapi.project.Project
import xyz.kandrac.codingame.wizard.GENERATOR_TYPE_OPTIM
import xyz.kandrac.codingame.wizard.GeneratorContext

class CgGameTypeOptimStep(parentStep: NewProjectWizardStep) : AbstractNewProjectWizardStep(parentStep) {

    override fun setupProject(project: Project) {
        GeneratorContext.gameType = GENERATOR_TYPE_OPTIM
        GeneratorContext.numberOfPlayersMin = 1
        GeneratorContext.numberOfPlayersMax = 1
    }
}