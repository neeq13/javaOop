package ui.commands;

import Presenter.Presenter;

public class GetAll extends Command{
    public GetAll(Presenter presenter) {
        super(presenter);
    }

    @Override
    public String description() {
        return "Вывести всё генеалогическое древо";
    }

    @Override
    public void execute() {
        getPresenter().all();
    }
}
