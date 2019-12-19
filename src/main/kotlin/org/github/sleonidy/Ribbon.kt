package org.github.sleonidy

import com.pixelduke.control.Ribbon
import com.pixelduke.control.ribbon.QuickAccessBar
import com.pixelduke.control.ribbon.RibbonGroup
import com.pixelduke.control.ribbon.RibbonItem
import com.pixelduke.control.ribbon.RibbonTab
import javafx.beans.property.StringProperty
import javafx.collections.ObservableList
import javafx.event.EventTarget
import javafx.scene.Node
import javafx.scene.control.Button
import tornadofx.opcr
import kotlin.reflect.KFunction1

fun EventTarget.ribbon(op: Ribbon.() -> Unit = {}): Ribbon {
    val ribbon = Ribbon()
    return opcr(this, ribbon, op)
}

fun Ribbon.quickAccessBar(op: QuickAccessBar.() -> Unit = {}): QuickAccessBar {
    return opcr(this, QuickAccessBar(), op)
}

@Suppress("UNCHECKED_CAST")
var QuickAccessBar.builderTarget: KFunction1<*, ObservableList<Button>>?
    get() = properties["tornadofx.builderTarget"] as KFunction1<Any, ObservableList<Button>>?
    set(value) {
        properties["tornadofx.builderTarget"] = value
    }

internal fun QuickAccessBar.region(
    region: KFunction1<QuickAccessBar, ObservableList<Button>>?,
    op: QuickAccessBar.() -> Unit
) {
    builderTarget = region
    op()
    builderTarget = null
}

fun QuickAccessBar.buttons(op: QuickAccessBar.() -> Unit = {}) = region(QuickAccessBar::getButtons, op)

fun QuickAccessBar.rightButtons(op: QuickAccessBar.() -> Unit = {}) = region(QuickAccessBar::getRightButtons, op)

fun QuickAccessBar.quickAccessBarButton(text: String = "", graphic: Node? = null, op: Button.() -> Unit = {}): Button {
    val button = Button(text).apply {
        if (graphic != null) this.graphic = graphic
    }
    button.op()
    return addButton(button)

}

fun QuickAccessBar.addButton(button: Button): Button {
    val target = this.builderTarget
    if (target != null) {
        // Trick to get around the disallowed use of invoke on out projected types
        @Suppress("UNNECESSARY_NOT_NULL_ASSERTION")
        target!!(this).add(button)

    }
    return button
}

fun Ribbon.ribbonTab(text: String, op: RibbonTab.() -> Unit = {}): RibbonTab {
    val tab = RibbonTab(text)
    tab.op()
    this.tabs.add(tab)
    return tab
}

fun Ribbon.ribbonTab(text: StringProperty? = null, op: RibbonTab.() -> Unit = {}): RibbonTab {
    val tab = RibbonTab()
    tab.op()
    if (text != null)
        tab.textProperty().bindBidirectional(text)

    this.tabs.add(tab)
    return tab
}


@Suppress("UNCHECKED_CAST")
var RibbonGroup.builderTarget: KFunction1<RibbonGroup, ObservableList<Node>>?
    get() = properties["tornadofx.builderTarget"] as KFunction1<Any, ObservableList<Node>>?
    set(value) {
        properties["tornadofx.builderTarget"] = value
    }

internal fun RibbonGroup.region(
    region: KFunction1<RibbonGroup, ObservableList<Node>>?,
    op: RibbonGroup.() -> Unit
) {
    builderTarget = region
    op()
    builderTarget = null
}

fun RibbonTab.ribbonGroup(title: String, op: RibbonGroup.() -> Unit = {}): RibbonGroup {
    val ribbonGroup = RibbonGroup().apply {
        this.title = title
        op()
    }
    ribbonGroups.add(ribbonGroup)
    return ribbonGroup
}

fun RibbonTab.ribbonGroup(title: StringProperty? = null, op: RibbonGroup.() -> Unit = {}): RibbonGroup {
    val ribbonGroup = RibbonGroup().apply {
        op()
        this.titleProperty().bindBidirectional(title)
    }
    ribbonGroups.add(ribbonGroup)
    return ribbonGroup
}

fun <T : Node> RibbonGroup.addTo(node: T, op: T.() -> Unit = {}): T {
    node.op()
    nodes.add(node)
    return node
}

fun RibbonGroup.ribbonItem(text: String, item: Node? = null, op: RibbonItem.() -> Unit = {}): RibbonItem {
    val ribbonItem = RibbonItem().apply {
        this.label = text
        if (item != null)
            this.item = item
    }
    return addTo(ribbonItem, op)
}