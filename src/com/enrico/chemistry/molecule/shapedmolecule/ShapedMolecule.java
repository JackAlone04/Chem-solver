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

package com.enrico.chemistry.molecule.shapedmolecule;

import com.enrico.chemistry.atoms.scientific.GenericScientificAtom;
import com.enrico.chemistry.atoms.scientific.HydrogenScientificAtom;
import com.enrico.chemistry.molecule.Molecule;
import com.enrico.chemistry.molecule.atomgroup.AtomGroup;
import com.enrico.drawing.Line;

import java.util.ArrayList;

/**
 * This class represents an entire molecule, except that it keeps track of the position of every single
 * atom.
 *
 * This task is handled internally by the AtomGroup class that uses the ShapeMolecule internal class
 * "AtomPlaceCard".
 */
public final class ShapedMolecule {
    private ArrayList<AtomGroup> atomGroups = new ArrayList<>();
    private ArrayList<Line> lineGroups = new ArrayList<>();
    private ArrayList<HydrogenAtomPlaceCard> placeCardsForHydrogen = new ArrayList<>();
    private final Molecule molecule;

    public ShapedMolecule(Molecule molecule, int xCenter, int yCenter) {

        ArrayList<HydrogenScientificAtom> hydrogenAtoms = molecule.getHydrogenAtoms();
        int hydrogenAtomsSize = hydrogenAtoms.size();
        int hydrogenAtomIndex = 0;
        this.molecule = molecule;

        /*
         * Every algorithm from now on follows the same basic structure.
         *
         * The first thing that the algorithm does is is checking if there is a sufficient number of atoms for that
         * kind of shape. This way we won't get and IndexOutFoBounds Exception.
         *
         * The second thing is to create an ArrayList of PlaceCards, and to add the central atom and all the surrounding
         * atoms to that PlaceCard.
         *
         * Next we add the atoms to a new group so we have the basic structure of the molecule.
         *
         * Next step is to copy the atoms ArrayList to another, since we can't modify atoms while iterating trough it
         * so the purpose of that array list is only to iterate inside atoms.
         * Before iterating inside it we check if the loop condition (so if there are Hydrogen atoms) and if true we
         * start iterating inside the ArrayList.
         *
         * When iterating inside the array list we check the position of every Hydrogen atom. To avoid adding mistakenly
         * an hydrogen atom as central (except for H2 molecule) we check the position end skip it if it's central.
         * For every atom we check the position and we understand if they're positioned to the position specified.
         * Note that this part of code changes for every shape because different shape can have different positions and
         * number of hydrogen atoms.
         *
         * After finishing this, we just add the lines to the LineGroups and exit the constructor.
         */
        switch (molecule.getMoleculeShape()) {
            case SquareShape:

                ArrayList<GenericScientificAtom> bondedGenericScientificAtoms = molecule.getBindedGenericScientificAtoms();

                if (bondedGenericScientificAtoms.size() == 4) {
                    ArrayList<AtomPlaceCard> atoms = new ArrayList<>();
                    atoms.add(new AtomPlaceCard(molecule.getCentralGenericScientificAtom(), xCenter, yCenter, AtomPlaceCard.Positions.Center));

                    atoms.add(new AtomPlaceCard(bondedGenericScientificAtoms.get(0), xCenter - 20, yCenter, AtomPlaceCard.Positions.Left));
                    atoms.add(new AtomPlaceCard(bondedGenericScientificAtoms.get(1), xCenter + 20, yCenter, AtomPlaceCard.Positions.Right));
                    atoms.add(new AtomPlaceCard(bondedGenericScientificAtoms.get(2), xCenter, yCenter - 20, AtomPlaceCard.Positions.Top));
                    atoms.add(new AtomPlaceCard(bondedGenericScientificAtoms.get(3), xCenter, yCenter + 20, AtomPlaceCard.Positions.Bottom));

                    atomGroups.add(new AtomGroup(atoms));

                    ArrayList<AtomPlaceCard> placeCardCopy = new ArrayList<>(atoms);

                    if (hydrogenLoopCondition(hydrogenAtomsSize, bondedGenericScientificAtoms, hydrogenAtoms)) {
                        for (AtomPlaceCard placeCard : placeCardCopy) {
                            if (hydrogenAtomIndex > hydrogenAtomsSize - 1)
                                break;

                            // Coordinates of the central atom.
                            if (placeCard.x == xCenter && placeCard.y == yCenter)
                                continue;

                            if (placeCard.x > xCenter && placeCard.y - yCenter == 0) {               // Left.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                      xCenter - 40, yCenter, AtomPlaceCard.Positions.Left));
                            } else if (placeCard.x < xCenter && placeCard.y - yCenter == 0) {        // Right.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                        xCenter + 40, yCenter, AtomPlaceCard.Positions.Right));
                            } else if (placeCard.x - xCenter == 0 && placeCard.y > yCenter) {        // Bottom.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                        xCenter, yCenter + 40, AtomPlaceCard.Positions.Bottom));
                            } else {                                                                 // Top.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                        xCenter, yCenter - 40, AtomPlaceCard.Positions.Top));
                            }

                            hydrogenAtomIndex++;
                        }
                    }

                    addLines(atoms);
                }
            break;

            case LineShape:
                ArrayList<GenericScientificAtom> bondedAtomsLine = molecule.getBindedGenericScientificAtoms();
                if (bondedAtomsLine.size() >= 1) {
                    ArrayList<AtomPlaceCard> atoms = new ArrayList<>();

                    atoms.add(new AtomPlaceCard(molecule.getCentralGenericScientificAtom(), xCenter, yCenter, AtomPlaceCard.Positions.Center));
                    atoms.add(new AtomPlaceCard(bondedAtomsLine.get(0), xCenter - 20, yCenter, AtomPlaceCard.Positions.Left));

                    if (bondedAtomsLine.size() >= 2)
                        atoms.add(new AtomPlaceCard(bondedAtomsLine.get(1), xCenter + 20, yCenter, AtomPlaceCard.Positions.Right));

                    atomGroups.add(new AtomGroup(atoms));

                    ArrayList<AtomPlaceCard> placeCardCopy = new ArrayList<>(atoms);

                    if (hydrogenLoopCondition(hydrogenAtomsSize, bondedAtomsLine, hydrogenAtoms)) {
                        for (AtomPlaceCard placeCard : placeCardCopy) {
                            if (hydrogenAtomIndex > hydrogenAtomsSize - 1)
                                break;

                            // Coordinates of the central atom.
                            if (placeCard.x == xCenter && placeCard.y == yCenter)
                                continue;

                            if (placeCard.x - xCenter > 0 && placeCard.y - yCenter == 0) {          // Left.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                        xCenter - 40, yCenter, AtomPlaceCard.Positions.Left));
                            } else {                                                                // Right.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                        xCenter + 40, yCenter, AtomPlaceCard.Positions.Right));
                            }

                            hydrogenAtomIndex++;
                        }
                    }
                    addLines(atoms);
                }
            break;

            case PyramidShape:
                ArrayList<GenericScientificAtom> bondedAtomsPyramid = molecule.getBindedGenericScientificAtoms();

                if (bondedAtomsPyramid.size() >= 2) {
                    ArrayList<AtomPlaceCard> atoms = new ArrayList<>();
                    atoms.add(new AtomPlaceCard(molecule.getCentralGenericScientificAtom(), xCenter, yCenter, AtomPlaceCard.Positions.Center));

                    atoms.add(new AtomPlaceCard(bondedAtomsPyramid.get(0), xCenter - 20, yCenter - 20, AtomPlaceCard.Positions.TopRight));
                    atoms.add(new AtomPlaceCard(bondedAtomsPyramid.get(1), xCenter + 20, yCenter - 20, AtomPlaceCard.Positions.TopLeft));

                    if (bondedAtomsPyramid.size() >= 3) {
                        atoms.add(new AtomPlaceCard(bondedAtomsPyramid.get(2), xCenter, yCenter - 30, AtomPlaceCard.Positions.Top));
                    }

                    atomGroups.add(new AtomGroup(atoms));

                    ArrayList<AtomPlaceCard> placeCardCopy = new ArrayList<>(atoms);

                    if (hydrogenLoopCondition(hydrogenAtomsSize, bondedAtomsPyramid, hydrogenAtoms)) {
                        for (AtomPlaceCard placeCard : placeCardCopy) {
                            if (hydrogenAtomIndex > hydrogenAtomsSize - 1)
                                break;

                            // Coordinates of the central atom.
                            if (placeCard.x == xCenter && placeCard.y == yCenter)
                                continue;

                            // No distinction is made here for position.
                            atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                    placeCard.x, yCenter - 40, AtomPlaceCard.Positions.Bottom));

                            hydrogenAtomIndex++;
                        }
                    }
                    addLines(atoms);
                }
            break;

            case TriangularShape:
                ArrayList<GenericScientificAtom> bondedAtomsTriangular = molecule.getBindedGenericScientificAtoms();

                if (bondedAtomsTriangular.size() == 3) {
                    ArrayList<AtomPlaceCard> atoms = new ArrayList<>();
                    atoms.add(new AtomPlaceCard(molecule.getCentralGenericScientificAtom(), xCenter, yCenter, AtomPlaceCard.Positions.Center));

                    atoms.add(new AtomPlaceCard(bondedAtomsTriangular.get(0), xCenter - 20, yCenter - 20, AtomPlaceCard.Positions.TopLeft));
                    atoms.add(new AtomPlaceCard(bondedAtomsTriangular.get(1), xCenter + 20, yCenter - 20, AtomPlaceCard.Positions.TopRight));
                    atoms.add(new AtomPlaceCard(bondedAtomsTriangular.get(2), xCenter, yCenter + 30, AtomPlaceCard.Positions.Bottom));

                    atomGroups.add(new AtomGroup(atoms));

                    ArrayList<AtomPlaceCard> placeCardCopy = new ArrayList<>(atoms);

                    if (hydrogenLoopCondition(hydrogenAtomsSize, bondedAtomsTriangular, hydrogenAtoms)) {
                        for (AtomPlaceCard placeCard : placeCardCopy) {
                            if (hydrogenAtomIndex > hydrogenAtomsSize - 1)
                                break;

                            // Coordinates of the central atom.
                            if (placeCard.x == xCenter && placeCard.y == yCenter)
                                continue;

                            if (placeCard.x - (xCenter - 20) == 0 && placeCard.y - (yCenter - 20) == 0) {        // Top - right.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                          placeCard.x - 20, placeCard.y, AtomPlaceCard.Positions.TopRight));
                            } else if (placeCard.x - (xCenter + 20) == 0 && placeCard.y - (yCenter - 20) == 0) { // Top - left.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                        placeCard.x + 20, placeCard.y, AtomPlaceCard.Positions.TopLeft));
                            } else {
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),               // Center - bottom.
                                        placeCard.x, placeCard.y + 20, AtomPlaceCard.Positions.Bottom));
                            }

                            hydrogenAtomIndex++;
                        }
                    }
                    addLines(atoms);
                }
            break;

            case FivePointedStar:
                ArrayList<GenericScientificAtom> bondedAtomsFiveStar = molecule.getBindedGenericScientificAtoms();

                if (bondedAtomsFiveStar.size() == 5) {
                    ArrayList<AtomPlaceCard> atoms = new ArrayList<>();
                    atoms.add(new AtomPlaceCard(molecule.getCentralGenericScientificAtom(), xCenter, yCenter, AtomPlaceCard.Positions.Center));

                    atoms.add(new AtomPlaceCard(bondedAtomsFiveStar.get(0), xCenter, yCenter - 40, AtomPlaceCard.Positions.Top));
                    atoms.add(new AtomPlaceCard(bondedAtomsFiveStar.get(1), xCenter - 20, yCenter - 20, AtomPlaceCard.Positions.TopLeft));
                    atoms.add(new AtomPlaceCard(bondedAtomsFiveStar.get(2), xCenter - 20, yCenter + 20, AtomPlaceCard.Positions.BottomRight));
                    atoms.add(new AtomPlaceCard(bondedAtomsFiveStar.get(3), xCenter + 20, yCenter + 20, AtomPlaceCard.Positions.BottomLeft));
                    atoms.add(new AtomPlaceCard(bondedAtomsFiveStar.get(4), xCenter + 20, yCenter - 20, AtomPlaceCard.Positions.TopRight));

                    atomGroups.add(new AtomGroup(atoms));

                    ArrayList<AtomPlaceCard> placeCardCopy = new ArrayList<>(atoms);

                    if (hydrogenLoopCondition(hydrogenAtomsSize, bondedAtomsFiveStar, hydrogenAtoms)) {
                        for (AtomPlaceCard placeCard : placeCardCopy) {
                            if (hydrogenAtomIndex > hydrogenAtomsSize - 1)
                                break;

                            // Coordinates of the central atom.
                            if (placeCard.x == xCenter && placeCard.y == yCenter)
                                continue;

                            if (placeCard.x - xCenter == 0 && placeCard.y - (yCenter - 20) == 0) { // Top.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                          placeCard.x, placeCard.y - 40, AtomPlaceCard.Positions.Top));
                            } else if ((placeCard.x - (xCenter - 20) == 0 && placeCard.y - (yCenter - 20) == 0) || // Top - right.
                                       (placeCard.x - (xCenter - 20) == 0 && placeCard.y - (yCenter + 20) == 0)) { // Bottom - right.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                          placeCard.x - 20, placeCard.y, AtomPlaceCard.Positions.Right));
                            } else { // Top - left & Bottom - left.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                        placeCard.x - 20, placeCard.y, AtomPlaceCard.Positions.Left));
                            }

                            hydrogenAtomIndex++;
                        }
                    }
                    addLines(atoms);
                }
            break;

            case SixPointedStar:
                ArrayList<GenericScientificAtom> bondedAtomsSixStar = molecule.getBindedGenericScientificAtoms();

                if (bondedAtomsSixStar.size() == 6) {
                    ArrayList<AtomPlaceCard> atoms = new ArrayList<>();
                    atoms.add(new AtomPlaceCard(molecule.getCentralGenericScientificAtom(), xCenter, yCenter, AtomPlaceCard.Positions.Center));

                    atoms.add(new AtomPlaceCard(bondedAtomsSixStar.get(0), xCenter, yCenter - 40, AtomPlaceCard.Positions.Top));
                    atoms.add(new AtomPlaceCard(bondedAtomsSixStar.get(1), xCenter, yCenter + 40, AtomPlaceCard.Positions.Bottom));
                    atoms.add(new AtomPlaceCard(bondedAtomsSixStar.get(2), xCenter - 20, yCenter - 20, AtomPlaceCard.Positions.TopLeft));
                    atoms.add(new AtomPlaceCard(bondedAtomsSixStar.get(3), xCenter - 20, yCenter + 20, AtomPlaceCard.Positions.BottomLeft));
                    atoms.add(new AtomPlaceCard(bondedAtomsSixStar.get(4), xCenter + 20, yCenter + 20, AtomPlaceCard.Positions.BottomRight));
                    atoms.add(new AtomPlaceCard(bondedAtomsSixStar.get(5), xCenter + 20, yCenter - 20, AtomPlaceCard.Positions.TopRight));

                    atomGroups.add(new AtomGroup(atoms));

                    ArrayList<AtomPlaceCard> placeCardCopy = new ArrayList<>(atoms);

                    if (hydrogenLoopCondition(hydrogenAtomsSize, bondedAtomsSixStar, hydrogenAtoms)) {
                        for (AtomPlaceCard placeCard : placeCardCopy) {
                            if (hydrogenAtomIndex > hydrogenAtomsSize - 1)
                                break;

                            // Coordinates of the central atom.
                            if (placeCard.x == xCenter && placeCard.y == yCenter)
                                continue;

                            if (placeCard.x - xCenter == 0 && placeCard.y - (yCenter - 40) == 0) { // Top.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                        placeCard.x, placeCard.y - 40, AtomPlaceCard.Positions.Bottom));
                            } else if (placeCard.x - xCenter == 0 && placeCard.y - (yCenter + 40) == 0) { // Bottom.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                        placeCard.x, placeCard.y + 40, AtomPlaceCard.Positions.Top));
                            } else if ((placeCard.x - (xCenter - 20) == 0 && placeCard.y - (yCenter - 20) == 0) || // Top - right.
                                    (placeCard.x - (xCenter - 20) == 0 && placeCard.y - (yCenter + 20) == 0)) { // Bottom - right.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                        placeCard.x - 20, placeCard.y, AtomPlaceCard.Positions.Right));
                            } else { // Top - left & Bottom - left.
                                atoms.add(new AtomPlaceCard(hydrogenAtoms.get(hydrogenAtomIndex),
                                        placeCard.x - 20, placeCard.y, AtomPlaceCard.Positions.Left));
                            }

                            hydrogenAtomIndex++;
                        }
                    }
                    addLines(atoms);
                }
            break;
        }
    }

    // This function adds the bond lines to an array list where they will be drawn.
    private void addLines(ArrayList<AtomPlaceCard> atoms) {
        GenericScientificAtom moleculeCentralGenericScientificAtom = molecule.getCentralGenericScientificAtom();
        AtomPlaceCard currentCentralAtomPlaceCard;
        AtomPlaceCard lastPlaceCard = null;

        if (!moleculeCentralGenericScientificAtom.getClass().equals(HydrogenScientificAtom.class)) {
            currentCentralAtomPlaceCard = atoms.get(0);
        } else {
            lineGroups.add(new Line(atoms.get(0).x + 10, atoms.get(1).x - 10,
                                    atoms.get(0).y, atoms.get(0).y));
            return;
        }

        for (AtomPlaceCard placeCard : atoms) {
            if (lastPlaceCard == null)
                lastPlaceCard = placeCard;

            if (placeCard.getAtomSymbol().equals(HydrogenScientificAtom.ATOM_SYMBOL)) {
                if (!molecule.isMoleculeSimple()) {
                    continue;
                }
            }

            switch (placeCard.position) {
                case Center:
                    continue;

                case Top:
                    lineGroups.add(new Line(placeCard.x + 4, placeCard.x + 4,
                            currentCentralAtomPlaceCard.y - 12, placeCard.y + 4));
                break;

                case Bottom:
                    lineGroups.add(new Line(placeCard.x + 4, placeCard.x + 4, currentCentralAtomPlaceCard.y + 4,
                            placeCard.y - 12));
                break;

                case Left:
                    lineGroups.add(new Line(placeCard.x + 10, currentCentralAtomPlaceCard.x - 5,
                            currentCentralAtomPlaceCard.y - 4, currentCentralAtomPlaceCard.y - 4));
                break;

                case Right:
                    lineGroups.add(new Line(placeCard.x - 5, currentCentralAtomPlaceCard.x + 10,
                            currentCentralAtomPlaceCard.y - 4, currentCentralAtomPlaceCard.y - 4));
                break;

                case TopRight:
                    lineGroups.add(new Line(placeCard.x + 5, currentCentralAtomPlaceCard.x,
                            placeCard.y, currentCentralAtomPlaceCard.y - 10));
                break;

                case TopLeft:
                    lineGroups.add(new Line(placeCard.x, currentCentralAtomPlaceCard.x + 7,
                            placeCard.y, currentCentralAtomPlaceCard.y - 11));
                break;

                case BottomLeft:
                    if (molecule.getMoleculeShape() == Molecule.ShapeEnum.FivePointedStar)
                        lineGroups.add(new Line(placeCard.x - 3, currentCentralAtomPlaceCard.x + 5,
                                placeCard.y - 5, currentCentralAtomPlaceCard.y + 3));
                    else
                        lineGroups.add(new Line(placeCard.x + 3, currentCentralAtomPlaceCard.x,
                                placeCard.y - 10, currentCentralAtomPlaceCard.y));
                break;

                case BottomRight:
                    if (molecule.getMoleculeShape() == Molecule.ShapeEnum.FivePointedStar)
                        lineGroups.add(new Line(placeCard.x + 3, currentCentralAtomPlaceCard.x - 5,
                                placeCard.y - 10, currentCentralAtomPlaceCard.y + 3));
                    else
                        lineGroups.add(new Line(placeCard.x - 2, currentCentralAtomPlaceCard.x + 5,
                                placeCard.y - 3, currentCentralAtomPlaceCard.y + 3));
                break;
            }
        }

        // If the molecule is more complex than just a simple atom surrounded by hydrogen atoms, then start adding atoms.
        if (!molecule.isMoleculeSimple() && molecule.containsHydrogen()) {
            addHydrogenToHydrogenAtomsList(atoms);

            for (HydrogenAtomPlaceCard placeCard : placeCardsForHydrogen) {
                if (placeCard.getPosition() == AtomPlaceCard.Positions.Left)
                    lineGroups.add(new Line(placeCard.getX() + 10, placeCard.getBindedAtomX() - 45,
                                     placeCard.getBindedAtomY() - 5, placeCard.getY() - 5));

                else if (placeCard.getPosition() == AtomPlaceCard.Positions.TopLeft ||
                        placeCard.getPosition() == AtomPlaceCard.Positions.BottomLeft)
                            lineGroups.add(new Line(placeCard.getX() - 5, placeCard.getBindedAtomX() + 10,
                            placeCard.getBindedAtomY() - 5, placeCard.getY() - 5));

                else if (placeCard.getPosition() == AtomPlaceCard.Positions.Right)
                            lineGroups.add(new Line(placeCard.getX() - 5, placeCard.getBindedAtomX() + 50,
                                                placeCard.getBindedAtomY() - 5, placeCard.getY() - 5));

                else if (placeCard.getPosition() == AtomPlaceCard.Positions.TopRight ||
                         placeCard.getPosition() == AtomPlaceCard.Positions.BottomRight)
                            lineGroups.add(new Line(placeCard.getX() + 10, placeCard.getBindedAtomX() - 5,
                                           placeCard.getBindedAtomY() - 5, placeCard.getY() - 5));

                else if (placeCard.getPosition() == AtomPlaceCard.Positions.Top) {
                    /*
                     * Apparently two hydrogen atoms are assigned the position top and this messes
                     * up the interface. This is only temporary until i fix the bug, since it only
                     * shows 2 hydrogen atoms instead of 3.
                     */
                    lineGroups.add(new Line(placeCard.getX() + 4, placeCard.getX() + 4,
                            placeCard.getY(), placeCard.getBindedAtomY() - 13));
                }

                else if (placeCard.getPosition() == AtomPlaceCard.Positions.Bottom)
                            lineGroups.add(new Line(placeCard.getX() + 4, placeCard.getBindedAtomX() + 4,
                                            placeCard.getY() - 10, placeCard.getBindedAtomY() + 3));
            }
        }
    }

    /*
     * This function makes two array lists, one that will contain all the hydrogen atoms, and one that contains all
     * of the other atoms (except the central one).
     *
     * Then it proceeds to iterate inside the array list that contains the atoms different from hydrogen, and for every
     * atom of that, it will add an hydrogen atom creating a new instance of the HydrogenAtomPlaceCard class.
     */
    private void addHydrogenToHydrogenAtomsList(ArrayList<AtomPlaceCard> atoms) {
        int atomPlaceCardIndex = 0;

        ArrayList<AtomPlaceCard> hydrogenAtoms = new ArrayList<>();
        ArrayList<AtomPlaceCard> otherAtoms = new ArrayList<>();

        for (AtomPlaceCard placeCard : atoms) {
            if (placeCard.getAtomSymbol().equals(HydrogenScientificAtom.ATOM_SYMBOL))
                hydrogenAtoms.add(placeCard);
            else
                if (placeCard.position != AtomPlaceCard.Positions.Center)
                    otherAtoms.add(placeCard);
        }

        AtomPlaceCard lastPlaceCard = null;
        for (AtomPlaceCard placeCard : otherAtoms) {
            if (atomPlaceCardIndex > hydrogenAtoms.size()-1)
                break;

            if (lastPlaceCard != null && lastPlaceCard.getPosition() == placeCard.getPosition())
                continue;

            placeCardsForHydrogen.add(new HydrogenAtomPlaceCard(hydrogenAtoms.get(atomPlaceCardIndex), placeCard));
            atomPlaceCardIndex++;

            lastPlaceCard = placeCard;
        }
    }

    private boolean hydrogenLoopCondition(int hydrogenAtomsSize, ArrayList<GenericScientificAtom> bondedList,
                                          ArrayList<HydrogenScientificAtom> hydrogenAtoms) {
        return hydrogenAtomsSize > 0 && !bondedList.containsAll(hydrogenAtoms);
    }

    public ArrayList<AtomGroup> getAtomGroups() {
        return atomGroups;
    }

    public ArrayList<Line> getLineGroups() {
        return lineGroups;
    }

    /**
     * This class represents an atom to be displayed on the canvas.
     *
     * Instead of the "Atom" class that is used to handle the operations for the physical part of the atom,
     * this class is only here to represent the Atom's symbol and its place on the Canvas.
     */
    public static class AtomPlaceCard {
        private final String atomSymbol;

        private final int x, y;
        private final Positions position;

        private final GenericScientificAtom GenericScientificAtom;

        public enum Positions {
            Center,
            Bottom,
            Top,
            Left,
            Right,
            TopLeft,
            TopRight,
            BottomLeft,
            BottomRight
        }

        AtomPlaceCard(GenericScientificAtom GenericScientificAtom, int x, int y, Positions position) {
            atomSymbol = GenericScientificAtom.getSymbol();
            this.x = x;
            this.y = y;
            this.position = position;
            this.GenericScientificAtom = GenericScientificAtom;
        }

        public final String getAtomSymbol() {
            return atomSymbol;
        }

        public final int getX() {
            return x;
        }

        public final int getY() {
            return y;
        }

        final Positions getPosition() {
            return position;
        }

        final GenericScientificAtom getAtomPlaceCardAtom() {
            return GenericScientificAtom;
        }
    }
}
