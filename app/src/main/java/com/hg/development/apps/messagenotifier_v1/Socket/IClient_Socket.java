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

import java.io.IOException;

/**
 * Interface : IClient_Socket
 * @author Hivinau GRAFFE
 * @version 1.0
 */
public interface IClient_Socket {
    /**
     * Permet de lancer le socket de connection.
     * @param timeout_connection temps imparti de connection en secondes.
     * @throws Exception
     */
    public void Connect(int timeout_connection) throws Exception;

    /**
     * Permet d'arrêter le socket de connection.
     * @throws Exception
     */
    public void Disconnect() throws Exception;

    /**
     * Permet d'interrompre instantanément le socket de connection.
     * @throws Exception
     */
    public void Interrupt() throws Exception;

    /**
     * Permet d'envoyer un message à travers le réseau IP.
     * @param message message à envoyer.
     * @throws IOException
     */
    public void SendMessage(final String message) throws IOException;
}
