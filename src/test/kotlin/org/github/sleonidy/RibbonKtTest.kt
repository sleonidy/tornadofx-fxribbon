package org.github.sleonidy

import javafx.scene.control.TabPane
import org.kordamp.ikonli.devicons.Devicons
import org.kordamp.ikonli.javafx.FontIcon
import tornadofx.*

internal class RibbonTestApp : App(RibbonView::class)

internal class RibbonView : View() {
    var tabPane by singleAssign<TabPane>()
    override val root = borderpane {
        top {
            ribbon {
                quickAccessBar {
                    buttons {
                        button(graphic = FontIcon.of(Devicons.SAFARI,16))
                    }
                    rightButtons {
                        button("dasdas")
                    }
                }
                ribbonTab("Tab 1") {
                    ribbonGroup("Group 1") {
                        button(graphic = FontIcon.of(Devicons.CODE_BADGE, 32)) {
                            action {
                                println("code badge")
                            }
                        }
                    }
                    ribbonGroup("Group 2") {
                        //                        ribbonItem("sdadas")
                    }
                }
                ribbonTab("Tab 2") {
                    ribbonGroup("Group 3") {
                        ribbonItem("sdadas",FontIcon.of(Devicons.CODE,32)) {
                            combobox<String>(values = listOf("A","B","C")) {  }
                        }
                    }
                    ribbonGroup("Group 4") {
                        ribbonItem("sdadas") {
                            button {

                            }
                        }
                    }
                }
            }
        }
        center {
            tabPane = tabpane {

            }
        }
    }

    inline fun <reified T : UIComponent> dock() {
        tabPane.add<T>()
    }

    override fun onDock() {
        super.onDock()
        dock<AnotherView>()
        dock<AnotherView>()
    }
}

class AnotherView : Fragment("my view") {
    override val root = borderpane {
        center {
            button("Center"){
                action {

                }
            }
        }
    }
}