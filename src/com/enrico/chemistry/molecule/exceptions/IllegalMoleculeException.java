package com.enrico.chemistry.molecule.exceptions;

import com.enrico.chemistry.molecule.Molecule;

public class IllegalMoleculeException extends IllegalArgumentException {
    public IllegalMoleculeException(Molecule m) {
        super("Molecule: " + m.getMoleculeShape() + " is not a valid molecule.");
    }
}