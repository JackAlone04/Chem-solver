package com.enrico.drawing.graphicalAtoms.semimetals;

import com.enrico.drawing.graphicalAtoms.GenericGraphicalAtom;
import com.enrico.interfaces.atoms.semimetals.BoronAtomInterface;

public final class GraphicalBoronAtom extends GenericGraphicalAtom implements BoronAtomInterface {
    public static final String IMAGE_PATH_STRING =
            "atom_icons/semimetals/atom_icon_boron.png";

    public static final int STD_BINDINGS = 3;

    public GraphicalBoronAtom(int startX, int startY, int endX, int endY, String atomId) {
        super (ATOM_SYMBOL, ATOM_NAME, ATOMIC_NUMBER, ATOMIC_MASS, ELECTRONEGATIVITY, BINDING_ELECTRONS, DOUBLETS,
                IONIZATION_NUMBER, CLASS_TYPE, startX, startY, endX, endY, STD_BINDINGS, IMAGE_PATH_STRING, atomId);
    }
}