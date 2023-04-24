package ui.commands;

import Presenter.Presenter;

public class Sorted extends Command {
    public Sorted(Presenter presenter) {
        super(presenter);
    }

    @Override
    public String description() {
        return "Сортировка дерева";
    }

    @Override
    public void execute() {
        getPresenter().sorted();
    }
}
