package com.enrico.widgets.menu;

import com.enrico.windows.BasicWindow;
import com.enrico.windows.main.MainWindow;

import javax.swing.*;

public final class ProblemWindowMenuBar extends MainMenuBar {

    public ProblemWindowMenuBar(BasicWindow win) {
        super();

        JMenu problemMenu = new JMenu("Problem");
        problemMenu.setFont(menuBarFont);

        JMenuItem problemMenuItemSolve = problemMenu.add("Solve");
        JMenuItem problemMenuItemStartAnother = problemMenu.add("Solve another problem");

        problemMenuItemSolve.setFont(menuBarFont);
        problemMenuItemStartAnother.setFont(menuBarFont);

        problemMenuItemSolve.addActionListener(actionEvent -> {

        });

        problemMenuItemStartAnother.addActionListener(actionEvent -> {
            win.dispose();
            MainWindow window = new MainWindow();
            window.showWindow();
        });

        add(problemMenu);
    }

    public void createUIComponents() {
    }
}
