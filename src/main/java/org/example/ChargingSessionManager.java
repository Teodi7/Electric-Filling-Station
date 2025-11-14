package org.example;

import java.util.ArrayList;
import java.util.List;

public class ChargingSessionManager {

    private List<ChargingSession> sessions = new ArrayList<>();

    public ChargingSession startSession(ClientAccount account, Charger charger, int sessionId) {
        if (!account.canPay(0)) {
            return null;
        }

        ChargingSession session = new ChargingSession(sessionId);
        session.startSession();
        sessions.add(session);

        return session;
    }

    public void stopSession(int sessionId) {
        for (ChargingSession session : sessions) {
            if (session.getSessionId() == sessionId) {
                session.endSession();
            }
        }
    }

    public List<ChargingSession> getSessionsByAccount(ClientAccount account) {

        return sessions;
    }
}
