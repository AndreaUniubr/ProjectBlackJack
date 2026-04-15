package view;

import controller.Controller;
import controller.StateObserver;
import model.balance.Balance;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame implements StateObserver {
    private final Controller controller;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);

    // pagine

    private HomePage homePage;
    private PlayPage playpage;
    private DetailsPage detailsPage;
    private BalancePage balancePage;

    public MainPage(Controller c)
    {
        Balance balance = new Balance(1000);

        this.controller = c;
        controller.addObserver(this);
        setTitle("Project Black Jack");

        setSize(1000, 700);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // pannelli delle varie schermate HOME, PLAYING, DETAILS, BALANCE
        homePage = new HomePage(controller);
        playpage = new PlayPage(controller);
        detailsPage = new DetailsPage(controller);
        balancePage = new BalancePage(controller, balance);
        cards.add(homePage,State.HOME.name());
        cards.add(playpage,State.PLAY.name());
        cards.add(detailsPage,State.DETAILS.name());
        cards.add(balancePage,State.BALANCE.name());

        add(cards);

        setVisible(true);
    }

    public void updateView() {
        cardLayout.show(this.cards, controller.getState().name());
    }

    @Override
    public void onStateChanged(State newState) {

        if (newState == State.PLAY) {
            createNewPlayPage();
        }

        cardLayout.show(cards, newState.name());
    }

    private void createNewPlayPage() {

        // 1️⃣ rimuovo eventuale PlayPage precedente
        cards.removeAll();

        // 2️⃣ riaggiungo pagine fisse
        cards.add(homePage,State.HOME.name());
        cards.add(detailsPage,"DETAILS");
        cards.add(balancePage,"BALANCE");

        // 3️⃣ creo nuova PlayPage
        PlayPage playPage = new PlayPage(controller);
        cards.add(playPage, State.PLAY.name());

        // 4️⃣ aggiorno grafica
        cards.revalidate();
        cards.repaint();
    }

}