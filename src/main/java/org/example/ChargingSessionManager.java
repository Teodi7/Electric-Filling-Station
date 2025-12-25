package org.example;

import java.util.ArrayList;
import java.util.List;


 // US8.1 – Charge EV

public class ChargingSessionManager {

    private List<ChargingSession> sessions = new ArrayList<>();

    // US8.1 – Charge EV
    public ChargingSession startSession(ClientAccount account, Charger charger, int sessionId) {

        ChargingSession session = new ChargingSession(sessionId);


        session.setAccount(account);
        session.setCharger(charger);


        session.startSession();
        sessions.add(session);

        return session;
    }


    public void stopSession(int sessionId) {
        for (ChargingSession s : sessions) {
            if (s.getSessionId() == sessionId) {
                s.endSession();
                break;
            }
        }
    }

    public List<ChargingSession> getSessionsByAccount(ClientAccount account) {
        List<ChargingSession> result = new ArrayList<>();
        if (account == null) {
            return result;
        }

        for (ChargingSession s : sessions) {
            if (s.getAccount() == account) {
                result.add(s);
            }
        }
        return result;
    }


    public List<ChargingSession> getAllSessions() {
        return new ArrayList<>(sessions);
    }
}
