
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

package com.enrico.interfaces.atoms.lanthanides;

import com.enrico.chemistry.atoms.GenericAtom;
import com.enrico.programresources.messagebundle.ProgramMessageBundle;

public interface EuropiumAtomInterface {
    String ATOM_SYMBOL = "Eu";
    String ATOM_NAME = ProgramMessageBundle.getAtomClassName(EuropiumAtomInterface.class);
    int ATOMIC_NUMBER = 63;
    int BONDING_ELECTRONS = 2;
    int IONIZATION_NUMBER = 547;
    int DOUBLETS = 1;
    double ATOMIC_MASS = 151.964;
    double ELECTRONEGATIVITY = 1.2;
    GenericAtom.AtomClassType CLASS_TYPE = GenericAtom.AtomClassType.Lanthanides;
}
