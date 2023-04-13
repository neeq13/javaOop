package ui.commands;

import service.TreeService;

public class AddChild extends Command{
    public AddChild(TreeService treeService) {
        super(treeService);
    }

    @Override
    public String description() {
        return "Добавить ребёнка";
    }

    @Override
    public void execute() {
        getTreeService().addChild();
    }
}
