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
import com.hg.development.apps.messagenotifier_v1.Socket.Events.Enumeration.StateConnectionSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Class : Communicate_Provider
 * @author Hivinau GRAFFE
 * @version 1.0
 */
public class Communicate_Provider extends Client_Socket
{
    private Socket socket = null;
    private Sender sender = null;

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Socket.Communicate_Provider}.
     * @param channel paramètres de connection (IP, PORT, ...)
     */
    public Communicate_Provider(Profile_Channel channel){
        try
        {
            if(channel == null)
                throw new IllegalArgumentException("Spécifier les paramètres de connection (IP, PORT, ...)");

            this.IP = channel.getIP();
            this.PORT = channel.getPORT();

            socket = new Socket();
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public void Connect(int timeout_connection) throws Exception {
        Profile_Channel channel = new Profile_Channel(IP,PORT);

        try
        {
            if(socket == null)
                throw new Exception("La socket de connection n'a pas été instanciée");

            stateChanged(StateConnectionSocket.CONNECTING,channel);

            socket.connect(new InetSocketAddress(channel.getIP(), channel.getPORT()), timeout_connection * 1000);

            sender = new Sender();
            sender.setOutputStream(socket.getOutputStream());

            stateChanged(StateConnectionSocket.CONNECTED,channel);
        }
        catch (Exception ex) {
            stateChanged(StateConnectionSocket.DISCONNECTED,channel);
            throw ex;
        }
    }

    @Override
    public void Disconnect() throws Exception {
        Profile_Channel channel = new Profile_Channel(IP,PORT);

        try
        {
            stateChanged(StateConnectionSocket.DISCONNECTING, channel);
            synchronized (this) {
                if (socket != null) {
                    try {
                        socket.close();
                        socket = null;
                    } catch (IOException e) {
                    }
                }
            }
        }
        finally {
            stateChanged(StateConnectionSocket.DISCONNECTED, channel);
        }
    }

    @Override
    public void Interrupt() throws Exception {
        Profile_Channel channel = new Profile_Channel(IP,PORT);

        try
        {
            stateChanged(StateConnectionSocket.DISCONNECTING, channel);
            synchronized (this) {
                if (socket != null) {
                    try {
                        socket.close();
                        socket = null;
                    } catch (IOException e) {
                    }
                }
            }
        }
        finally {
            stateChanged(StateConnectionSocket.DISCONNECTED, channel);
        }
    }

    @Override
    public void SendMessage(String message) throws IOException {
        try{
            sender.Send(message);
        }
        catch(IOException ex)
        {
            throw ex;
        }
    }

    /**
     * Internal class : Sender
     * @author Hivinau GRAFFE
     * @version 1.0
     */
    private class Sender {
        private OutputStream sendStream = null;

        /**
         * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Socket.Communicate_Provider.Sender}.
         */
        public Sender()
        {
            try {
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /**
         * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Socket.Communicate_Provider.Sender}.
         * @param stream flux de messages.
         */
        public Sender(OutputStream stream)
        {
            try {
                this.sendStream = stream;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /**
         * Permet d'initialiser le flux de messages.
         * @param stream
         */
        public void setOutputStream(OutputStream stream)
        {
            try {
                this.sendStream = stream;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /**
         * Permet d'envoyer un message de façon synchronisé.
         * @param message
         * @throws IOException
         */
        public void Send(String message) throws IOException
        {
            try {
                int packetSize = message.length();

                synchronized(java.lang.Object.class){
                    sendStream.write(message.getBytes(), 0, packetSize);
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}
