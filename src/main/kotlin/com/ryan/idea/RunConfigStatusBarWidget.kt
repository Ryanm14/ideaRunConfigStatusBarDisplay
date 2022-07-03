import com.intellij.execution.RunManager
import com.intellij.execution.RunManagerListener
import com.intellij.execution.RunnerAndConfigurationSettings
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.project.DumbService
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget.MultipleTextValuesPresentation
import com.intellij.openapi.wm.WindowManager
import com.intellij.openapi.wm.impl.status.EditorBasedWidget
import org.jetbrains.annotations.NonNls
import javax.swing.Icon

class RunConfigStatusBarWidget(project: Project) : EditorBasedWidget(project), MultipleTextValuesPresentation,
    RunManagerListener {
    private companion object {
        var text: String? = null
        var i: Icon? = null
    }

    @NonNls
    override fun ID() = "RunTimeConfiguration"

    override fun getPresentation() = this

    override fun getTooltipText() = null

    override fun getClickConsumer() = null

    override fun getPopupStep() = null

    override fun install(statusBar: StatusBar) {
        super.install(statusBar)
        DumbService.getInstance(myProject).runWhenSmart {
            update(RunManager.getInstance(project).selectedConfiguration?.configuration)
        }
    }

    override fun runConfigurationSelected(settings: RunnerAndConfigurationSettings?) {
        super.runConfigurationSelected(settings)
        update(settings?.configuration)

    }

    private fun update(configuration: RunConfiguration?) = configuration?.let { runConfig ->
        text = runConfig.name
        i = runConfig.icon
        WindowManager.getInstance().getStatusBar(project).updateWidget(ID())
    }

    override fun getSelectedValue() = text

    override fun getIcon() = i
}
