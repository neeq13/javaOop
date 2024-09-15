package ui.commands;

import Presenter.Presenter;

public class Search extends Command{
    public Search(Presenter presenter) {
        super(presenter);
    }

    @Override
    public String description() {
        return "Поиск конкретного человека по его имени";
    }

    @Override
    public void execute() {
        getPresenter().search();
    }
}
