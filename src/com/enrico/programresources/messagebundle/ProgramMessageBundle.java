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

import org.jetbrains.annotations.NotNull;
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

    /**
     * We use reflection to get the interface name that contains the atom name to get the internationalized atom name.
     * @param atomInterface the interface of the atom which has the atom's name in its name.
     * @return the internationalized atom name.
     */
    @NotNull
    public static String getAtomClassName(@NotNull Class<?> atomInterface) {
        return messageBundle.getString(atomInterface.getCanonicalName().split("\\.")[5].split("Atom")[0].toLowerCase());
    }

    // Welcome window.
    public static final String WELCOME_TITLE = "welcome_title";
    public static final String WELCOME_SUBTITLE = "welcome_subtitle";
    public static final String WELCOME_BTN = "welcome_btn";

    // Elements class name.
    public static final String ALKALINE_METALS = "alkaline_metals";             // 0
    public static final String ALKALINE_EARTH_METALS = "alkaline_earth_metals"; // 1
    public static final String TRANSITION_METALS = "transition_metals";         // 2
    public static final String NON_METALS = "nonmetals";                        // 3
    public static final String SEMIMETALS = "semimetals";                       // 4
    public static final String P_BLOCK_METALS = "pblock_metals";                // 5
    public static final String HALOGENS = "halogens";                           // 6
    public static final String NOBLE_GASSES = "noble_gasses";                   // 7
    public static final String LANTHANIDES = "lanthanides";                     // 8
    public static final String ACTINIDES = "actinides";                         // 9

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

    // Atom popup menu.
    public static final String PROPERTIES_TXT = "properties_txt";
    public static final String SINGLE_BOND_TO_TXT = "single_bond_to_txt";
    public static final String REMOVE_SINGLE_BOND_TXT = "remove_single_bond_txt";
    public static final String REMOVE_ATOM_TXT = "remove_atom_txt";
    public static final String DOUBLE_BOND_TO_TXT = "double_bond_to_txt";
    public static final String REMOVE_DOUBLE_BOND_TXT = "remove_double_bond_txt";
    public static final String TRIPLE_BOND_TO_TXT = "triple_bond_to_txt";
    public static final String REMOVE_TRIPLE_BOND_TXT = "remove_triple_bond_txt";

    // Generic menu bar.
    public static final String PROGRAM_TXT = "program_txt";
    public static final String INFO_TXT = "info_txt";
    public static final String EXIT_TXT = "exit_txt";
    public static final String PROBLEM_TXT = "problem_txt";
    public static final String SOLVE_TXT = "solve_txt";
    public static final String SOLVE_ANOTHER_PROBLEM_TXT = "solve_another_problem_txt";
    public static final String SAVE_TXT = "save_txt";
    public static final String SAVE_IMAGE_TXT = "save_image_txt";

    // Compound classification window.
    public static final String FORMULA_INSERT_TXT = "formula_insert_txt";
    public static final String NUMBER_OF_ELEMENTS_INSERT_TXT = "number_of_elements_insert_txt";
    public static final String TYPE_OUT_TXT = "type_out_txt";
    public static final String INSERT_FORMULA_ERROR_TITLE_TXT = "insert_formula_error_title_txt";
    public static final String INSERT_FORMULA_ERROR_TXT = "insert_formula_error_txt";
    public static final String FORMULA_ERROR_TXT = "formula_error_txt";

    // Compound types.
    public static final String HYDRIDE_TXT = "hydride_txt";
    public static final String ANHYDRIDE_TXT = "anhydride_txt";
    public static final String OXOACID_TXT = "oxoacid_txt";
    public static final String PEROXIDE_TXT = "peroxide_txt";
    public static final String HYDROXIDE_TXT = "hydroxide_txt";
    public static final String BASIC_OXIDE_TXT = "basic_oxide_txt";
    public static final String BINARY_ACID_TXT = "binary_acid_txt";
    public static final String BINARY_IONIC_TXT = "binary_ionic_txt";
    public static final String TERNARY_SALT_TXT = "ternary_salt_txt";
}