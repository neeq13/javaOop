package ui.commands;

import service.TreeService;

public class Search extends Command{
    public Search(TreeService treeService) {
        super(treeService);
    }

    @Override
    public String description() {
        return "Поиск конкретного человека по его имени";
    }

    @Override
    public void execute() {
        getTreeService().search();
    }
}
