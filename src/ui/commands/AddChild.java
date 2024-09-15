package ui.commands;

import Presenter.Presenter;

public class AddChild extends Command{
    public AddChild(Presenter presenter) {
        super(presenter);
    }

    @Override
    public String description() {
        return "Добавить ребёнка";
    }

    @Override
    public void execute() {
        getPresenter().addChild();
    }
}
