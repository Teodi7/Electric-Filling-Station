package org.example;

import java.util.ArrayList;
import java.util.List;

public class ChargingSessionManager {

    private List<ChargingSession> sessions = new ArrayList<>();

    public ChargingSession startSession(ClientAccount account, Charger charger, int sessionId) {

        // Session anlegen und starten.

        ChargingSession session = new ChargingSession(sessionId);
        session.startSession();
        sessions.add(session);

        return session;
    }

    public void stopSession(int sessionId) {
        for (ChargingSession s : sessions) {
            if (s.getSessionId() == sessionId) {
                s.endSession();
            }
        }
    }

    public List<ChargingSession> getSessionsByAccount(ClientAccount account) {
        // return all sessions
        return new ArrayList<>(sessions);
    }

    // für alle sessions, ist aber nicht gefragt
    public List<ChargingSession> getAllSessions() {
        return new ArrayList<>(sessions);
    }
}
