package view;

import controller.Controller;
import controller.StateObserver;
import model.balance.Balance;

import javax.swing.*;
import java.awt.*;

/** Main application window using CardLayout to switch between views */
public class MainPage extends JFrame implements StateObserver {
    private final Controller controller;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);
    private final Balance balance;

    private final HomePage homePage;
    private PlayPage playpage;
    private final DetailsPage detailsPage;
    private final BalancePage balancePage;

    public MainPage(Controller controller)
    {
        this.controller = controller;
        this.balance = this.controller.getBalance();

        controller.addObserver(this);
        setTitle("Project Black Jack");

        setSize(1000, 700);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Card-based navigation pages: HOME, PLAYING, DETAILS, BALANCE
        homePage = new HomePage(controller);
        playpage = new PlayPage(controller, controller.getnPartecipanti(), balance);
        detailsPage = new DetailsPage(controller);
        balancePage = new BalancePage(controller, balance);

        cards.add(homePage,State.HOME.name());
        cards.add(playpage,State.PLAY.name());
        cards.add(detailsPage,State.DETAILS.name());
        cards.add(balancePage,State.BALANCE.name());

        add(cards);

        setVisible(true);
    }

    @Override
    public void onStateChanged(State newState)
    {
        if (newState == State.PLAY) createNewPlayPage();

        cardLayout.show(cards, newState.name());
    }

    private void createNewPlayPage()
    {
        // remove eventual old PlayPage
        cards.remove(playpage);

        // create new PlayPage
        playpage = new PlayPage(controller, controller.getnPartecipanti(), balance);
        cards.add(playpage, State.PLAY.name());

        cards.revalidate();
        cards.repaint();
    }

}