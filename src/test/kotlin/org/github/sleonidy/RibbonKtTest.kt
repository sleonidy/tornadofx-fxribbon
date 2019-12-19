package org.github.sleonidy

import javafx.scene.control.Button
import tornadofx.*


internal class RibbonTestApp : App(RibbonView::class)

internal class RibbonView : View() {
    override val root = borderpane {
        top {
            ribbon {
                quickAccessBar {
                    buttons {
                        quickAccessBarButton ("dasdas")
                    }
                    rightButtons {
                        quickAccessBarButton ("dasdas")
                    }
                }
                ribbonTab("Blala") {
                    ribbonGroup("sdadasd"){
                        ribbonItem("sdadas", Button("button").apply { action { println("dasda") } })
                    }
                    ribbonGroup("sdadasd"){
                        //                        ribbonItem("sdadas")
                    }
                }
                ribbonTab("Tab 2") {
                    ribbonGroup("sdadasd"){
                        ribbonItem("sdadas", Button("button").apply { action { println("dasda") } })
                    }
                    ribbonGroup("sdadasd"){
                        //                        ribbonItem("sdadas")
                    }
                }
            }
        }
    }

}