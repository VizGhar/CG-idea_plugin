package xyz.kandrac.codingame.wizard.gametype

import com.intellij.ide.wizard.AbstractNewProjectWizardStep
import com.intellij.ide.wizard.NewProjectWizardStep
import com.intellij.openapi.project.Project
import com.intellij.ui.components.DropDownLink
import com.intellij.ui.dsl.builder.Panel
import xyz.kandrac.codingame.CodingameWizardBundle
import xyz.kandrac.codingame.wizard.GENERATOR_TYPE_MULTI
import xyz.kandrac.codingame.wizard.GeneratorContext

class CgGameTypeMultiStep(parentStep: NewProjectWizardStep) : AbstractNewProjectWizardStep(parentStep) {

    private lateinit var dropdownMin: DropDownLink<Int>
    private lateinit var dropdownMax: DropDownLink<Int>

    override fun setupUI(builder: Panel) {
        builder.twoColumnsRow({
            label(CodingameWizardBundle.getMessage("codingame.new.project.wizard.game.type.multi.players.min"))
        }) {
            dropDownLink(2, listOf(2, 3, 4, 5, 6, 7, 8), { value ->
                dropdownMax.selectedItem = maxOf(value, dropdownMax.selectedItem)
            }).also {
                dropdownMin = it.component
            }
        }

        builder.twoColumnsRow({
            label(CodingameWizardBundle.getMessage("codingame.new.project.wizard.game.type.multi.players.max"))
        }) {
            dropDownLink(2, listOf(2, 3, 4, 5, 6, 7, 8), { value ->
                dropdownMin.selectedItem = minOf(value, dropdownMin.selectedItem)
            }).also {
                dropdownMax = it.component
            }
        }
    }

    override fun setupProject(project: Project) {
        GeneratorContext.gameType = GENERATOR_TYPE_MULTI
        GeneratorContext.numberOfPlayersMin = dropdownMin.selectedItem
        GeneratorContext.numberOfPlayersMax = dropdownMax.selectedItem
    }
}