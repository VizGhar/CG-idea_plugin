package xyz.kandrac.codingame.wizard.language

import com.intellij.ide.wizard.*
import com.intellij.ide.wizard.NewProjectWizardBaseData.Companion.baseData
import com.intellij.ui.UIBundle

class CgLanguageStep(private val parent: NewProjectWizardStep) :
  AbstractNewProjectWizardMultiStepBase(parent),
  NewProjectWizardBaseData by parent.baseData {

  override val label = UIBundle.message("label.project.wizard.new.project.language")

  override fun initSteps(): Map<String, NewProjectWizardStep> {
    return mapOf(
      "Java" to CgJavaStep(this),
      "Kotlin" to CgKotlinStep(this),
    )
  }
}