package ui.commands;

import service.TreeService;

public class Exit extends Command{
    public Exit(TreeService treeService) {
        super(treeService);
    }

    @Override
    public String description() {
        return "Выход";
    }

    @Override
    public void execute() {
        getTreeService().exit();
    }
}
