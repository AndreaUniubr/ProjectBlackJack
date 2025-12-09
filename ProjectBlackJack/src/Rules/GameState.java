package Rules;

public enum GameState {
	
	NOTSTARTED,		//Fase iniziale
	BETTING,		//Piazzamento scommesse
	DIALING,		//Distribuzione delle carte
	PLAYER_TURN,	//Turno del player
	DEALER_TURN,	//Turno del dealer 
	PLAYER_BUST,	//Player sballa
	DEALER_BUST,	//Dealer sballa
	PLAYER_WIN,		//Player vince
	DEALER_WIN,		//Dealer vince
	PUSH,			//Pareggio
	END;			//Risoluzione
	
}
