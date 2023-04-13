package ui.commands;

import service.TreeService;

public abstract class Command implements Option {
    private final TreeService treeService;

    public Command(TreeService treeService) {
        this.treeService = treeService;
    }

    public TreeService getTreeService() {
        return treeService;
    }
}
