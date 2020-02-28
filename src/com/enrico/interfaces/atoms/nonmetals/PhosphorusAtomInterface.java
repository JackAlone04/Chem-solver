/*
 * Chem solver. A multi-platform chemistry and physics problem solver.
 *  Copyright (C) 2019 - 2020  Giacalone Enrico
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.enrico.interfaces.atoms.nonmetals;

import com.enrico.chemistry.atoms.GenericAtom;

public interface PhosphorusAtomInterface {
    String ATOM_SYMBOL = "P";
    String ATOM_NAME = "Phosphor";
    int ATOMIC_NUMBER = 15;
    int BINDING_ELECTRONS = 3;
    int IONIZATION_ENERGY = 1012;
    int DOUBLETS = 2;
    double ATOMIC_MASS = 30.97;
    double ELECTRONEGATIVITY = 2.19;
    GenericAtom.AtomClassType CLASS_TYPE = GenericAtom.AtomClassType.NotMetals;
}