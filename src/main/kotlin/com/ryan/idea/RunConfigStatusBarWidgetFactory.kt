package com.ryan.idea

import RunConfigStatusBarWidget
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.impl.status.widget.StatusBarEditorBasedWidgetFactory
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.NonNls

class FileNameStatusBarWidgetFactory : StatusBarEditorBasedWidgetFactory() {
    @NonNls
    override fun getId(): String {
        return "RunTimeConfiguration"
    }

    @Nls
    override fun getDisplayName(): String {
        return "Run Time Configuration"
    }

    override fun createWidget(project: Project): StatusBarWidget {
        return RunConfigStatusBarWidget(project)
    }

    override fun disposeWidget(widget: StatusBarWidget) {
        Disposer.dispose(widget)
    }
}
