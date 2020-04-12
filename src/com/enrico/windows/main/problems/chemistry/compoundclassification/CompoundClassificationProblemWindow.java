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

package com.enrico.windows.main.problems.chemistry.compoundclassification;

import com.enrico.chemistry.atoms.scientific.GenericScientificAtom;
import com.enrico.chemistry.formulaparser.FormulaParser;
import com.enrico.chemistry.molecule.Molecule;
import com.enrico.programresources.FontResources;
import com.enrico.programresources.messagebundle.ProgramMessageBundle;
import com.enrico.widgets.menu.ProblemWindowMenuBar;
import com.enrico.windows.main.problems.GenericProblemWindow;

import javax.swing.*;

public final class CompoundClassificationProblemWindow extends GenericProblemWindow {
    public static final String TITLE = "Compound classification";

    private JTextField formulaField;
    private JLabel numberOfElementsLbl;
    private JLabel moleculeTypeLbl;
    private JPanel mainPane;
    private JLabel formulaTxtLbl;
    private JLabel numOfElementsLbl;
    private JLabel typeLbl;

    public CompoundClassificationProblemWindow() {
        super(TITLE);

        ProblemWindowMenuBar problemWindowMenuBar = new ProblemWindowMenuBar(this);
        setJMenuBar(problemWindowMenuBar);

        setResizable(false);

        setContentPane(mainPane);

        formulaTxtLbl.setText(ProgramMessageBundle.getString(ProgramMessageBundle.FORMULA_INSERT_TXT));
        formulaTxtLbl.setFont(FontResources.normalTextFont);

        numOfElementsLbl.setText(ProgramMessageBundle.getString(ProgramMessageBundle.NUMBER_OF_ELEMENTS_INSERT_TXT));
        numOfElementsLbl.setFont(FontResources.normalTextFont);

        typeLbl.setText(ProgramMessageBundle.getString(ProgramMessageBundle.TYPE_OUT_TXT));
        typeLbl.setFont(FontResources.normalTextFont);

        moleculeTypeLbl.setFont(FontResources.normalTextFont);
        numberOfElementsLbl.setFont(FontResources.normalTextFont);
    }

    @Override
    public void saveProject() {
    }

    @Override
    public void solveProblem() {
        String formula = formulaField.getText();
        if (formula.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    ProgramMessageBundle.getString(ProgramMessageBundle.INSERT_FORMULA_ERROR_TXT),
                    ProgramMessageBundle.getString(ProgramMessageBundle.INSERT_FORMULA_ERROR_TITLE_TXT),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        FormulaParser parser;
        GenericScientificAtom[] GenericScientificAtomList;
        Molecule molecule;

        try {
            parser = new FormulaParser(formula);

            GenericScientificAtomList = parser.getAtoms();
            molecule = new Molecule(GenericScientificAtomList, formula);
            molecule.findCompoundType();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    ProgramMessageBundle.getString(ProgramMessageBundle.INSERT_FORMULA_ERROR_TITLE_TXT),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int elementsNum = molecule.getElementsNum();
        numberOfElementsLbl.setText(String.valueOf(elementsNum));
        moleculeTypeLbl.setText(molecule.getCompoundType().toString());
    }
}
