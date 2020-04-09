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

package com.enrico.windows.dialogs.graphicalatomproperties;

import com.enrico.drawing.graphicalAtoms.GenericGraphicalAtom;
import com.enrico.programresources.FontResources;
import com.enrico.programresources.messagebundle.ProgramMessageBundle;
import com.enrico.widgets.buttons.ProgramButton;
import com.enrico.widgets.label.ProgramLabel;
import com.enrico.widgets.textfiled.ProgramTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public final class GraphicalAtomPropertiesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private ProgramLabel atom_id_lbl;
    private ProgramTextField atom_id_txt_field;
    private ProgramLabel atom_name_lbl;
    private ProgramLabel atom_name_lbl_out;
    private ProgramLabel general_properties_lbl;
    private ProgramLabel electronegativity_lbl;
    private ProgramLabel electronegativity_out_lbl;
    private ProgramLabel mass_lbl;
    private ProgramLabel mass_out_lbl;
    private ProgramLabel atomic_number_lbl;
    private ProgramLabel atomic_number_out_lbl;
    private ProgramLabel atom_class_lbl;
    private ProgramLabel atom_class_out_lbl;

    private GenericGraphicalAtom atom;

    public GraphicalAtomPropertiesDialog(@NotNull GenericGraphicalAtom atom) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(500, 300));
        setTitle(ProgramMessageBundle.messageBundle.getString(ProgramMessageBundle.ATOM_PROPERTIES_TITLE));
        setResizable(false);

        this.atom = atom;

        atom_id_lbl.setFont(FontResources.normalTextFont);
        atom_id_lbl.setText(ProgramMessageBundle.messageBundle.getString(ProgramMessageBundle.ATOM_ID_PROPERTIES_TXT));
        atom_id_txt_field.setFont(FontResources.normalTextFont);
        atom_id_txt_field.setText(atom.getAtomId());

        atom_name_lbl.setFont(FontResources.normalTextFont);
        atom_name_lbl.setText(ProgramMessageBundle.messageBundle.getString(ProgramMessageBundle.ATOM_NAME_PROPERTIES_TXT));
        atom_name_lbl_out.setFont(FontResources.normalTextFont);
        atom_name_lbl_out.setText(atom.getCompleteName());

        general_properties_lbl.setFont(FontResources.normalTextFont);
        general_properties_lbl.setText(ProgramMessageBundle.messageBundle.getString(ProgramMessageBundle.GENERAL_PROPERTIES_TXT));

        electronegativity_lbl.setFont(FontResources.normalTextFont);
        electronegativity_lbl.setText(ProgramMessageBundle.messageBundle.getString(ProgramMessageBundle.ELECTRONEGATIVITY_PROPERTIES_TXT));
        electronegativity_out_lbl.setFont(FontResources.normalTextFont);
        electronegativity_out_lbl.setText(String.valueOf(atom.getElectronegativity()));

        mass_lbl.setFont(FontResources.normalTextFont);
        mass_lbl.setText(ProgramMessageBundle.messageBundle.getString(ProgramMessageBundle.MASS_PROPERTIES_TXT));
        mass_out_lbl.setFont(FontResources.normalTextFont);
        mass_out_lbl.setText(String.valueOf(atom.getAtomicMass()));

        atomic_number_lbl.setFont(FontResources.normalTextFont);
        atomic_number_lbl.setText(ProgramMessageBundle.messageBundle.getString(ProgramMessageBundle.ATOMIC_NUMBER_PROPERTIES_TXT));
        atomic_number_out_lbl.setFont(FontResources.normalTextFont);
        atomic_number_out_lbl.setText(String.valueOf(atom.getAtomicNumber()));

        atom_class_lbl.setFont(FontResources.normalTextFont);
        atom_class_lbl.setText(ProgramMessageBundle.messageBundle.getString(ProgramMessageBundle.ATOMIC_CLASS_PROPERTIES_TXT));
        atom_class_out_lbl.setFont(FontResources.normalTextFont);
        atom_class_out_lbl.setText(atom.getClassType().toString());

        buttonOK.addActionListener(e -> onOK());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (!(atom_id_txt_field.getText().equals(atom.getAtomId())))
            atom.setAtomId(atom_id_txt_field.getText());
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {

    }

    public void showDialog() {
        setVisible(true);
        pack();
    }
}
