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
var QuickAccessBar.quickAccessBuilderTarget: KFunction1<QuickAccessBar, ObservableList<Button>>?
    get() = properties["tornadofx.builderTarget"] as KFunction1<QuickAccessBar, ObservableList<Button>>?
    set(value) {
        properties["tornadofx.builderTarget"] = value
    }

internal fun QuickAccessBar.region(
    region: KFunction1<QuickAccessBar, ObservableList<Button>>?,
    op: QuickAccessBar.() -> Unit
) {
    quickAccessBuilderTarget = region
    op()
    quickAccessBuilderTarget = null
}

fun QuickAccessBar.buttons(op: QuickAccessBar.() -> Unit = {}) = region(QuickAccessBar::getButtons, op)

fun QuickAccessBar.rightButtons(op: QuickAccessBar.() -> Unit = {}) = region(QuickAccessBar::getRightButtons, op)

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


fun RibbonTab.ribbonGroup(title: String, op: RibbonGroup.() -> Unit = {}): RibbonGroup {
    val ribbonGroup = RibbonGroup().apply {
        this.title = title
    }

    return opcr(this, ribbonGroup, op)
}

fun RibbonTab.ribbonGroup(title: StringProperty? = null, op: RibbonGroup.() -> Unit = {}): RibbonGroup {
    val ribbonGroup = RibbonGroup().apply {
        op()
        this.titleProperty().bindBidirectional(title)
    }
    ribbonGroups.add(ribbonGroup)
    return ribbonGroup
}

fun RibbonGroup.ribbonItem(text: String, graphic: Node? = null, op: RibbonItem.() -> Unit = {}): RibbonItem {
    val ribbonItem = RibbonItem().apply {
        this.label = text
        if (graphic != null)
            this.graphic = graphic
    }
    return opcr(this, ribbonItem, op)
}

fun RibbonGroup.ribbonItem(
    text: StringProperty? = null,
    graphic: Node? = null,
    op: RibbonItem.() -> Unit = {}
): RibbonItem {
    val ribbonItem = RibbonItem().apply {
        if (text != null)
            this.labelPropery().bindBidirectional(text)
        if (graphic != null)
            this.graphic = graphic
    }
    return opcr(this, ribbonItem, op)
}

