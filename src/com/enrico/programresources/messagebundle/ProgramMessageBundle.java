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

package com.enrico.programresources.messagebundle;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public final class ProgramMessageBundle {
    // English is omitted because it is the default language.
    private static final Locale[] supportedLanguages = {Locale.ITALY};

    public static ResourceBundle messageBundle = null;

    // To be called ony once after the program is started.
    public static void init() {
        Locale currentLocale = new Locale(Locale.getDefault().getLanguage(), Locale.getDefault().getCountry());
        if (Arrays.asList(supportedLanguages).contains(currentLocale)) {
            messageBundle = ResourceBundle.getBundle("languages/MessageBundle",
                    currentLocale);
        } else {
            messageBundle = ResourceBundle.getBundle("languages/MessageBundle",
                    Locale.ROOT);
        }
    }

    @Nullable
    public static String getString(String key) {
        if (messageBundle != null)
            return messageBundle.getString(key);
        return null;
    }

    // Welcome window.
    public static final String WELCOME_TITLE = "welcome_title";
    public static final String WELCOME_SUBTITLE = "welcome_subtitle";
    public static final String WELCOME_BTN = "welcome_btn";

    // Elements class name.
    public static final String ALKALINE_METALS_TXT = "alkaline_metals";             // 0
    public static final String ALKALINE_EARTH_METALS_TXT = "alkaline_earth_metals"; // 1
    public static final String TRANSITION_METALS = "transition_metals";             // 2
    public static final String NON_METALS_TXT = "nonmetals";                        // 3
    public static final String SEMIMETALS = "semimetals";                           // 4
    public static final String P_BLOCK_METALS = "pblock_metals";                    // 5
    public static final String HALOGENS = "halogens";                               // 6
    public static final String NOBLE_GASSES = "noble_gasses";                       // 7
    public static final String LANTHANIDES = "lanthanides";                         // 8
    public static final String ACTINIDES = "actinides";                             // 9

    // Canvas resizing.
    public static final String CANVAS_SIZE = "canvas_size";
    public static final String SET_CANVAS_SIZE = "set_canvas_size";
    public static final String SELECT_CANVAS_SIZE = "select_canvas_size";

    // Text for the atom properties dialog.
    public static final String ATOM_PROPERTIES_TITLE = "atom_properties_title";
    public static final String ATOM_ID_PROPERTIES_TXT = "atom_id_properties_txt";
    public static final String ATOM_NAME_PROPERTIES_TXT = "atom_name_properties_txt";
    public static final String GENERAL_PROPERTIES_TXT = "general_properties_txt";
    public static final String ELECTRONEGATIVITY_PROPERTIES_TXT = "electronegativity_properties_txt";
    public static final String MASS_PROPERTIES_TXT = "mass_properties_txt";
    public static final String ATOMIC_NUMBER_PROPERTIES_TXT = "atomic_number_properties_txt";
    public static final String ATOMIC_CLASS_PROPERTIES_TXT = "atomic_class_properties_txt";

    // Error messages for the Molecule Builder.
    public static final String NO_ATOM_SELECTED_TXT = "no_atom_selected_txt";
    public static final String PLEASE_SELECT_VALID_ATOM_TXT = "please_select_atom_txt";
    public static final String CANT_REMOVE_BOND_FROM_ITSELF_TXT = "cant_remove_bond_from_itself_txt";
    public static final String NO_ATOM_FOR_BOND_TXT = "no_atom_for_bond_txt";
    public static final String CANT_BOND_ATOM_TO_ITSELF_TXT = "cant_bond_atom_to_itself_txt";
    public static final String NO_VALID_ATOM_SELECTED_TXT = "no_valid_atom_selected_txt";
    public static final String MAXIMUM_NUMBER_OF_BONDS_REACHED_1 = "maximum_number_of_bonds_reached_1";
    public static final String MAXIMUM_NUMBER_OF_BONDS_REACHED_2 = "maximum_number_of_bonds_reached_2";
    public static final String MAXIMUM_NUMBER_OF_BONDS_REACHED_TITLE = "maximum_number_of_bonds_reached_title";
    public static final String MAXIMUM_NUMBER_OF_BONDS_REACHED_FOR_SELECTED_ATOM = "maximum_number_of_bonds_reached_for_selected_atom";
    public static final String CANT_DOUBLE_BOND = "cant_double_bond";
    public static final String CANT_DOUBLE_BOND_TITLE = "cant_double_bond_title";
    public static final String CANT_TRIPLE_BOND = "cant_triple_bond";
    public static final String CANT_TRIPLE_BOND_TITLE = "cant_triple_bond_title";
    public static final String TO = "to";
    public static final String CANT_BOND_TWO_METALS = "cant_bond_two_metals";
    public static final String NOBLE_TO_FLUORINE = "noble_to_fluorine";
    public static final String CANT_BOND_NOBLE_GASSES = "cant_bond_noble_gasses";
    public static final String SELECT_AN_ATOM = "select_an_atom";
}