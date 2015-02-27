/**
 * Copyright 2014
 *
 *
 * Sous licence Apache, Version 2.0 (la "Licence");
 * Vous ne pouvez pas utiliser ce fichier sauf en conformité avec la licence.
 * Vous pouvez obtenir une copie de la licence à l'adresse :
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Sauf si requis par la loi en vigueur ou accord écrit, le logiciel
 * Distribué sous licence est distribué «TEL QUEL»,
 * SANS GARANTIES OU CONDITIONS D'AUCUNE SORTE, express ou implicite.
 * Voir la licence pour les autorisations spécifiques aux différentes langues et
 * Limitations sous la licence.
 *
 * Contribué par : Hivinau GRAFFE
 */
package com.hg.development.apps.messagenotifier_v1.Socket;

import com.hg.development.apps.messagenotifier_v1.Socket.Communicate_Profile.Profile_Channel;
import com.hg.development.apps.messagenotifier_v1.Socket.Events.ConnectionEvent;
import com.hg.development.apps.messagenotifier_v1.Socket.Events.Enumeration.StateConnectionSocket;
import com.hg.development.apps.messagenotifier_v1.Socket.Events.Listeners.ConnectionChangeListener;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class : Client_Socket
 * @author Hivinau GRAFFE
 * @version 1.0
 */
public abstract class Client_Socket implements IClient_Socket {
    protected int PORT = 0;
    protected String IP = null;
    private ArrayList<ConnectionChangeListener> ConnectionListeners = new ArrayList<ConnectionChangeListener>();

    public abstract void Connect(int timeout_connection) throws Exception;
    public abstract void Disconnect() throws Exception;
    public abstract void Interrupt() throws Exception;
    public abstract void SendMessage(final String message) throws IOException;

    /**
     * Permet d'ajouter un nouveau listener à la liste de listeners de connection.
     * @param listener Listener de connection
     */
    public synchronized void addConnectionChangeListener(ConnectionChangeListener listener)
    {
        try {
            if (!ConnectionListeners.contains(listener)) {
                ConnectionListeners.add(listener);
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Permet de récupérer l'état de connection d'une socket et les paramètres de connection tels que IP ou PORT.
     * @param STATE Etat de connection d'une socket.
     * @param CHANNEL Paramètres de connection tels que IP ou PORT
     */
    protected void stateChanged(StateConnectionSocket STATE, Profile_Channel CHANNEL)
    {
        try
        {
            processStateEvent(new ConnectionEvent(this,STATE, CHANNEL));
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Permet d'"alarmer" qu'un évènement de connection est parvenue.
     * @param event Evènement de connection.
     */
    private void processStateEvent(ConnectionEvent event)
    {
        ArrayList<ConnectionChangeListener> listenerList = null ;

        try {
            synchronized (this) {
                if (this.ConnectionListeners.size() == 0)
                    return;
                listenerList = (ArrayList<ConnectionChangeListener>) this.ConnectionListeners.clone();
            }

            for (ConnectionChangeListener list : listenerList) {
                list.OnConnectionChangeOccured(event);
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    /**
     *
     * @return Port d'écoute sur le réseau.
     */
    public int getPORT() {
        return PORT;
    }

    /**
     *
     * @return Adresse IP du serveur.
     */
    public String getIP() {
        return IP;
    }
}
