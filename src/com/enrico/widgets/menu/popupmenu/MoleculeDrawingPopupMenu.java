package com.enrico.widgets.menu.popupmenu;

import com.enrico.drawing.graphicalAtoms.GenericGraphicalAtom;
import com.enrico.widgets.canvas.moleculedrawingcanvas.MoleculeDrawingCanvas;
import com.enrico.windows.dialogs.graphicalatomproperties.GraphicalAtomPropertiesDialog;

import javax.swing.*;

public final class MoleculeDrawingPopupMenu extends GenericPopupMenu {
    public MoleculeDrawingPopupMenu(GenericGraphicalAtom atom, MoleculeDrawingCanvas canvas) {
        super("Atom: " + atom.getAtomId());

        JMenuItem propertiesItem = new JMenuItem("Properties");
        propertiesItem.addActionListener(actionEvent -> {
            GraphicalAtomPropertiesDialog dialog = new GraphicalAtomPropertiesDialog(atom);
            dialog.showDialog();
        });

        JMenuItem singleBindingItem = new JMenuItem("Single bind to");
        singleBindingItem.addActionListener(actionEvent -> {
            canvas.setCursorState(MoleculeDrawingCanvas.CursorStates.CursorSingleBinding);
        });

        add(propertiesItem);
        add(singleBindingItem);
    }
}
