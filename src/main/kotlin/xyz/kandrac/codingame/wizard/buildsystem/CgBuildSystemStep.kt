package xyz.kandrac.codingame.wizard.buildsystem

import com.intellij.ide.wizard.AbstractNewProjectWizardMultiStepBase
import com.intellij.ide.wizard.NewProjectWizardBaseData
import com.intellij.ide.wizard.NewProjectWizardBaseData.Companion.baseData
import com.intellij.ide.wizard.NewProjectWizardStep
import xyz.kandrac.codingame.wizard.CodingameWizardBundle
import xyz.kandrac.codingame.wizard.GENERATOR_BUILD_SYSTEM_GRADLE
import xyz.kandrac.codingame.wizard.GENERATOR_BUILD_SYSTEM_MAVEN

class CgBuildSystemStep(parent: NewProjectWizardStep) :
    AbstractNewProjectWizardMultiStepBase(parent),
    NewProjectWizardBaseData by parent.baseData {

    override val label = CodingameWizardBundle.getMessage("codingame.new.project.wizard.build.system.label")

    override fun initSteps(): Map<String, NewProjectWizardStep> {
        return mapOf(
            GENERATOR_BUILD_SYSTEM_MAVEN to CgBuildSystemMavenStep(this),
            GENERATOR_BUILD_SYSTEM_GRADLE to CgBuildSystemGradleStep(this),
        )
    }
}